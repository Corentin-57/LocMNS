package edu.mns.locmns.controller;

import edu.mns.locmns.dao.ModeleDao;
import edu.mns.locmns.model.Modele;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ModeleController {

    private ModeleDao modeleDao;

    @Autowired
    public ModeleController(ModeleDao modeleDao) {
        this.modeleDao = modeleDao;
    }

    @GetMapping("/liste-modeles")
    public List<Modele> listemodeles() {
        return this.modeleDao.findAll();
    }

    @GetMapping("/gestionnaire/modele/{id}")
    public Modele modele(@PathVariable Integer id) {

        return this.modeleDao.findById(id).orElse(null);
    }
}
