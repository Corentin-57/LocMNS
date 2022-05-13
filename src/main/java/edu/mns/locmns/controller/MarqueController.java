package edu.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.dao.MarqueDao;
import edu.mns.locmns.model.Marque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MarqueController {

    private MarqueDao marqueDao;

    @Autowired
    public MarqueController(MarqueDao marqueDao) {this.marqueDao = marqueDao;}

    @GetMapping("/liste-marques")
    //@JsonView(VueMarque.class)
    public List<Marque> listeMarques () {

        return this.marqueDao.findAll();
    }

    @GetMapping("/marque/{id}")
    //@JsonView(VueMarque.class)
    public Marque marque(@PathVariable Integer id){

        return this.marqueDao.findById(id).orElse(null);
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
