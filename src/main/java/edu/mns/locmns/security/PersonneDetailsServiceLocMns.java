package edu.mns.locmns.security;

import edu.mns.locmns.dao.GestionnaireDao;
import edu.mns.locmns.dao.PersonneDao;
import edu.mns.locmns.model.Gestionnaire;
import edu.mns.locmns.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonneDetailsServiceLocMns implements UserDetailsService { //Provider
    private PersonneDao personneDao;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private GestionnaireDao gestionnaireDao;

    @Autowired
    PersonneDetailsServiceLocMns(PersonneDao personneDao){
        this.personneDao = personneDao;
    }

    @Override
    public PersonneDetailsLocMns loadUserByUsername(String mail) throws UsernameNotFoundException {

        Personne personne = personneDao.findByMail(mail).orElseThrow(() -> new UsernameNotFoundException("Mauvais mail / mot de passe"));

        Optional<Gestionnaire> gestionnaire  = gestionnaireDao.findById(personne.getId());

        PersonneDetailsLocMns personneDetailsLocMns = new PersonneDetailsLocMns(personne, gestionnaire.isPresent() );
        return personneDetailsLocMns;
    }
}
