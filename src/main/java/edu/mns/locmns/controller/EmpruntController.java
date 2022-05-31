package edu.mns.locmns.controller;

import edu.mns.locmns.dao.EmpruntDao;
import edu.mns.locmns.dao.MaterielDao;
import edu.mns.locmns.model.Emprunt;
import edu.mns.locmns.model.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class EmpruntController {

    private EmpruntDao empruntDao;
    private MaterielDao materielDao;

    @Autowired
    public EmpruntController(EmpruntDao empruntDao, MaterielDao materielDao) {
        this.empruntDao = empruntDao;
        this.materielDao = materielDao;
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
        emprunt.setDateDemande(LocalDate.from(LocalDateTime.now()));

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
    public String DemandeProlongationEmprunt(@RequestBody Emprunt emprunt){
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
    public String DemandeRetourEmprunt(@RequestBody Emprunt emprunt){
        Emprunt empruntBdd = empruntDao.findByUtilisateurIdAndMaterielIdMateriel(emprunt.getUtilisateur().getId(), emprunt.getMateriel().getIdMateriel()); //Recupère les infos post et effectue une recherche pour retrouver l'emprunt

        if(empruntBdd.getdateDemandeRetour() == null) { //Vérifie qu'une demande de retour ne soit pas en cours
            empruntBdd.setdateDemandeRetour(emprunt.getdateDemandeRetour());
            this.empruntDao.save(empruntBdd);
            return "La demande de retour a été envoyée";
        }else{
            return "Une demande de retour est déjà en cours";
        }
    }


}
