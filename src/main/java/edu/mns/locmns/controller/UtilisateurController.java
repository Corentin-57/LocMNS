package edu.mns.locmns.controller;

import edu.mns.locmns.dao.UtilisateurDao;
import edu.mns.locmns.model.Personne;
import edu.mns.locmns.model.Utilisateur;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UtilisateurController {

    private UtilisateurDao utilisateurDao;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UtilisateurController(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @GetMapping("/gestionnaire/liste-utilisateurs")
    public List<Utilisateur> listeUtilisateurs(){
       return this.utilisateurDao.findAll();
    }

    @GetMapping("/gestionnaire/utilisateur/{id}")
    public Utilisateur utilisateur(@PathVariable Integer id){
        return this.utilisateurDao.findById(id).orElse(null);
    }

    @PostMapping("/gestionnaire/inscription")
    public String inscription(@RequestBody Utilisateur utilisateur) throws Exception {

        utilisateur.setMotDePasse(encoder.encode(utilisateur.getMotDePasse()));
        utilisateurDao.save(utilisateur);

        return "Utilisateur crée";
    }

    /*@GetMapping("/cryptage")
    public String chiffrer(){
        return encoder.encode("QYM49WMG7AE");
    }*/






}
