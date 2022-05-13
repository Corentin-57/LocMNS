package edu.mns.locmns.controller;

import edu.mns.locmns.model.Personne;
import edu.mns.locmns.security.JwtUtils;
import edu.mns.locmns.security.PersonneDetailsServiceLocMns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PersonneController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonneDetailsServiceLocMns personneDetailsServiceLocMns;


    @PostMapping("/connexion")            // renvoyer un token si l'utilisateur existe dans la BDD
    public String connexion(@RequestBody Personne personne) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            personne.getMail(),
                            personne.getMotDePasse()
                    )
            );
        }catch (BadCredentialsException e) {
            throw new Exception(e);
        }

        UserDetails userDetails = personneDetailsServiceLocMns.loadUserByUsername(personne.getMail());

        return jwtUtils.generateToken(userDetails);
    }
}
