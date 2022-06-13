package edu.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.dao.MaterielDao;
import edu.mns.locmns.model.Materiel;
import edu.mns.locmns.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MaterielController {

    private MaterielDao materielDao;

    @Autowired
    public MaterielController(MaterielDao materielDao) { this.materielDao = materielDao;}

    @JsonView(View.listeNumerosSerieModification.class)
    @GetMapping("/gestionnaire/liste-materiels")
    public List<Materiel> listeMateriels(){
        return this.materielDao.findAll();
    }

    @GetMapping("/gestionnaire/materiel/{id}")
    public Materiel materiel(@PathVariable Integer id) {
        return this.materielDao.findById(id).orElse(null);
    }


    @GetMapping("/liste-materiels-utilisateur/{idUtilisateur}") //Récupérer les utilisateurs qui ont emprunté un matériel
    @JsonView(View.ListeMaterielsUtilisateur.class)
    public List<Materiel> listeMaterielsUtiisateur(@PathVariable Integer idUtilisateur){
        return this.materielDao.listeMaterielsUtilisateur(idUtilisateur);
    }

    @PostMapping("/gestionnaire/materiel")
    public String createMateriel(@RequestBody Materiel materiel) {
        this.materielDao.save(materiel);
        return "Matériel crée";
    }

    @DeleteMapping("/gestionnaire/materiel/{id}")
    public String deleteMateriel(@PathVariable int id) {
        this.materielDao.deleteById(id);
        return "Le materiel à été supprimé";
    }

    @GetMapping("/gestionnaire/materiels-defectueux") //Récupérer la liste des matériels défectueux
    public List<Materiel> listeMaterielsDisponibles(){
        return this.materielDao.findAllByEtatIdEtat(1);
   }

    @GetMapping("gestionnaire/nombre-materiels-defectueux") //Recherche nombre de retours d'emprunt
    public Integer RechercherNombreMaterielsDefectueux(){
        return this.materielDao.RechercherNombreMaterielDefectueux();
    }

    @GetMapping("gestionnaire/nombre-materiels-retard") //Recherche nombre de retours d'emprunt
    public Integer RechercherNombreMaterielEnRetard(){
        return this.materielDao.RechercherNombreMaterielEnRetard();
    }

    @GetMapping("gestionnaire/nombre-materiels-operationnel") //Recherche nombre de retours d'emprunt
    public Integer RechercherNombreMaterielOperationnel(){
        return this.materielDao.RechercherNombreMaterielOperationnel();
    }

    @JsonView(View.ListeMaterielsNumeroSerie.class)
    @GetMapping("gestionnaire/liste-materiel-numeroSerie")
    public List<Materiel> ListeMaterielNumeroSerie(){
        return this.materielDao.findAll();
    }

}
