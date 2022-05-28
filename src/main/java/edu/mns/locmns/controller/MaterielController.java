package edu.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

        //return "Le materiel " + materiel +  " à été créer";
    }

    @DeleteMapping("/gestionnaire/materiel/{id}")
    public String deleteMateriel(@PathVariable int id) {

        this.materielDao.deleteById(id);

        return "Le materiel à été supprimer";

    }
}
