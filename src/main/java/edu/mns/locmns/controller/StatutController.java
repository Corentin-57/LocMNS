package edu.mns.locmns.controller;

import edu.mns.locmns.dao.StatutDao;
import edu.mns.locmns.model.Statut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StatutController {

    private StatutDao statutDao;

    @Autowired
    public StatutController(StatutDao statutDao) { this.statutDao = statutDao; }

    @GetMapping("/liste-statut")
    public List<Statut> listeStatut() { return this.statutDao.findAll();}

    @GetMapping("/statut/{id}")
    public Statut statut(@PathVariable Integer id){
        return this.statutDao.findById(id).orElse(null);
    }
}
