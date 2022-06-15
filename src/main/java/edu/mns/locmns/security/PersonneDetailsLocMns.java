package edu.mns.locmns.security;

import edu.mns.locmns.model.Personne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PersonneDetailsLocMns implements UserDetails {

    private Personne personne;
    private Boolean isGestionnaire;


    public PersonneDetailsLocMns(Personne personne, Boolean isGestionnaire) {
        this.personne = personne;
        this.isGestionnaire = isGestionnaire;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //----GESTION DES DROITS PAR HERITAGE----

        ArrayList<SimpleGrantedAuthority> listeAuthority = new ArrayList<>();

        if(isGestionnaire) {
            listeAuthority.add(new SimpleGrantedAuthority("ROLE_GESTIONNAIRE"));
        }else{
            listeAuthority.add(new SimpleGrantedAuthority("ROLE_UTILISATEUR"));
        }

        return listeAuthority;
    }

    @Override
    public String getPassword() {
        return personne.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return personne.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Personne getPersonne(){
        return personne;
    }
}
