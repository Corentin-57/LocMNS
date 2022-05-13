package edu.mns.locmns.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AccueilController {



    @GetMapping("/")
    public String hello(){

        return "Serveur Spring OK";
    }

    @GetMapping("/connexion")
    public String connexion(){

        return "Connexion avec succès";
    }


}
