package edu.mns.locmns.controller;

import edu.mns.locmns.dao.DysfonctionnementDao;
import edu.mns.locmns.dao.UtilisateurDao;
import edu.mns.locmns.model.Dysfonctionnement;
import edu.mns.locmns.model.Emprunt;
import edu.mns.locmns.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@CrossOrigin
@RestController
public class DysfonctionnementController {

    private DysfonctionnementDao dysfonctionnementDao;

    private UtilisateurDao utilisateurDao;

    @Autowired
    public DysfonctionnementController(DysfonctionnementDao dysfonctionnementDao, UtilisateurDao utilisateurDao) {
        this.dysfonctionnementDao = dysfonctionnementDao;
        this.utilisateurDao = utilisateurDao;
    }

    @GetMapping("/gestionnaire/liste-dysfonctionnements")
    public List<Dysfonctionnement> listeDysfonctionnements() {

        return this.dysfonctionnementDao.findAll();
    }

    @GetMapping("/gestionnaire/liste-dysfonctionnements/{id}")
    public Dysfonctionnement dysfonctionnement(@PathVariable Integer id){

        return this.dysfonctionnementDao.findById(id).orElse(null);
    }

    @PostMapping("/saisir-dysfonctionnement")
    public String saisirDysfonctionnement (@RequestBody Dysfonctionnement dysfonctionnement){
        System.out.println("D" + dysfonctionnement.getDateDysfonctionnement());
//        System.out.println(dysfonctionnement.getUtilisateur().getId());
//        Utilisateur utilisateur = this.utilisateurDao.findById(dysfonctionnement.getUtilisateur().getId())
//                .orElseThrow(() -> new IllegalArgumentException());
//
//        dysfonctionnement.setUtilisateur(utilisateur);
//        dysfonctionnementDao.save(new Dysfonctionnement(dysfonctionnement.getDateDysfonctionnement(), dysfonctionnement.getDescriptif(), dysfonctionnement.getUtilisateur()));
        this.dysfonctionnementDao.save(dysfonctionnement);
        return "La demande de dysfonctionnement est enregistrée";
    }
}
