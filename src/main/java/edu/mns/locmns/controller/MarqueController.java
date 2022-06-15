package edu.mns.locmns.controller;

import edu.mns.locmns.dao.MarqueDao;
import edu.mns.locmns.model.Marque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class MarqueController {

    private MarqueDao marqueDao;

    @Autowired
    public MarqueController(MarqueDao marqueDao) {this.marqueDao = marqueDao;}

    @GetMapping("/liste-marques")
    public List<Marque> listeMarques () {
        return this.marqueDao.findAll();
    }

    @GetMapping("/marque/{id}") //Retourner le code de retour de la requête
    public ResponseEntity<Marque> marque(@PathVariable Integer id){
        Optional<Marque> retour = this.marqueDao.findById(id);

        if(retour.isPresent()){
            return ResponseEntity.ok(retour.get()); //Retourne notre marque
        }else{
            return ResponseEntity.noContent().build(); //Retourne le code erreur 204, pas de contenu
        }
    }

    @PostMapping("/gestionnaire/marque")
    public String createMarque (@RequestBody Marque marque){
        this.marqueDao.save(marque);
        return "La marque " + marque +  " a été créee";
    }

    @DeleteMapping("/gestionnaire/marque/{id}")
    public String deleteMarque(@PathVariable int id){
        this.marqueDao.deleteById(id);
        return "La marque a été surpimée";
    }

}
