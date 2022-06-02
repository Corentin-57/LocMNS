package edu.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.dao.EmpruntDao;
import edu.mns.locmns.dao.MaterielDao;
import edu.mns.locmns.dao.UtilisateurDao;
import edu.mns.locmns.model.Emprunt;
import edu.mns.locmns.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class EmpruntController {

    private EmpruntDao empruntDao;
    private MaterielDao materielDao;
    private UtilisateurDao utilisateurDao;

    @Autowired
    public EmpruntController(EmpruntDao empruntDao, MaterielDao materielDao, UtilisateurDao utilisateurDao) {
        this.empruntDao = empruntDao;
        this.materielDao = materielDao;
        this.utilisateurDao = utilisateurDao;
    }

    @GetMapping("/gestionnaire/liste-emprunts")
    public List<Emprunt> listeEmprunts(){
        return this.empruntDao.findAll();
    }

    @GetMapping("gestionnaire/emprunt/{idUtilisateur}/{idMateriel}/{dateEmprunt}")
    public Emprunt emprunt(@PathVariable Integer idUtilisateur,
                           @PathVariable Integer idMateriel,
                           @PathVariable String dateEmprunt)
                           throws ParseException {
        Date nouvelleDateEmprunt = new SimpleDateFormat("yyyy-MM-dd").parse(dateEmprunt);

        return this.empruntDao.findByUtilisateurIdAndMaterielIdMaterielAndDateEmprunt(idUtilisateur, idMateriel, nouvelleDateEmprunt).orElse(null);

    }

    @PostMapping("/reservation")
    public String createReservation (@RequestBody Emprunt emprunt){
        this.empruntDao.save(emprunt);
        return "La demande de réservation est créee";
    }

    @DeleteMapping("/gestionnaire/reservation/{idUtilisateur}/{idMateriel}/{dateEmprunt})")
    public String deleteReservation(@PathVariable Integer idUtilisateur, @PathVariable Integer idMateriel, @PathVariable Date dateEmprunt) {
        this.empruntDao.deleteByUtilisateurIdAndMaterielIdMaterielAndDateEmprunt(idUtilisateur, idMateriel, dateEmprunt);
        return "Le matériel a bien été supprimé";

    }

    @PostMapping("/demande-emprunt")
    public String demandeEmprunt (@RequestBody Emprunt emprunt){

        //Récupère l'id d'un nouveau matériel qui correspond au modèle souhaité
        Integer idMaterielNouveau = this.materielDao.RechercheNouveauxMaterielDemandeEmprunt(emprunt.getMateriel().getModele().getIdModele());

        //Récupère l'id d'un matériel déjà emprunté
        Integer idMaterielAncien = this.materielDao.RechercheMaterielDemandeEmprunt(emprunt.getMateriel().getModele().getIdModele(), emprunt.getDateEmprunt());

        //Enregistre la date du jour pour la date de demande
        emprunt.setDateDemande(LocalDateTime.now());

        if(idMaterielNouveau != null){
            emprunt.getMateriel().setIdMateriel(idMaterielNouveau);
            this.empruntDao.save(emprunt);
            return "La demande d'emprunt a été envoyée";
        }else if(idMaterielAncien != null){
            emprunt.getMateriel().setIdMateriel(idMaterielAncien);
            this.empruntDao.save(emprunt);
            return "La demande d'emprunt a été envoyée";
        }
        return "Il n'y a pas de matériel disponible pour ce modèle";

    }

    @PostMapping("/demande-prolongation")
    public String demandeProlongationEmprunt(@RequestBody Emprunt emprunt){
        Emprunt empruntBdd = empruntDao.findByUtilisateurIdAndMaterielIdMateriel(emprunt.getUtilisateur().getId(), emprunt.getMateriel().getIdMateriel()); //Recupère les infos post et effectue une recherche pour retrouver l'emprunt

        if(empruntBdd.getDateProlongation() == null) { //Vérifie qu'une demande de prolongation ne soit pas en cours
            empruntBdd.setDateProlongation(emprunt.getDateProlongation());
            this.empruntDao.save(empruntBdd);
            return "La demande de prolongation a été envoyée";
        }else{
            return "Une demande de prolongation est déjà en cours";
        }
    }

    @PostMapping("/demande-retour")
    public String demandeRetourEmprunt(@RequestBody Emprunt emprunt){
        Emprunt empruntBdd = empruntDao.findByUtilisateurIdAndMaterielIdMateriel(emprunt.getUtilisateur().getId(), emprunt.getMateriel().getIdMateriel()); //Recupère les infos post et effectue une recherche pour retrouver l'emprunt

        if(empruntBdd.getdateDemandeRetour() == null) { //Vérifie qu'une demande de retour ne soit pas en cours
            empruntBdd.setdateDemandeRetour(emprunt.getdateDemandeRetour());
            this.empruntDao.save(empruntBdd);
            return "La demande de retour a été envoyée";
        }else{
            return "Une demande de retour est déjà en cours";
        }
    }

    @GetMapping("gestionnaire/listeDemandesEmprunt")
    @JsonView(View.ListeDemandesEmprunt.class)
    public List listeDemandesEmprunt(){

        List listeDemandesEmprunt = this.empruntDao.findAllByDateDemandeIsNotNull();

        return listeDemandesEmprunt;
    }

    @PostMapping("gestionnaire/valider-demande-emprunt")
    public String validationDemandeEmprunt(@RequestBody Emprunt emprunt){
        emprunt = empruntDao.findById(emprunt.getIdEmprunt()).orElse(null);
        emprunt.setDateValidation(LocalDateTime.now()); //Enregistre la date du jour pour la date de validation
        emprunt.setDateDemande(null); //Met la date de demande à null la demande n'existe plus
        empruntDao.save(emprunt);
        return "La demande d'emprunt est validée";
    }

    @DeleteMapping("gestionnaire/supprimer-demande-emprunt/{idEmprunt}")
    public String supprimerDemandeEmprunt(@PathVariable Integer idEmprunt){
        this.empruntDao.deleteById(idEmprunt);
        return "La demande a bien été supprimée";
    }


}
