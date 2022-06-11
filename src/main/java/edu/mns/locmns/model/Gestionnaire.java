package edu.mns.locmns.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Gestionnaire extends Personne{

    @ManyToOne
    @JoinColumn(name="id_personne")
    private Personne personne;

    @OneToMany(mappedBy = "gestionnaire")
    private List<Retard> listeRetard = new ArrayList<>();

    @OneToMany(mappedBy = "gestionnaire")
    private List<Dysfonctionnement> listeDysfonctionnements = new ArrayList<>();

//    @OneToMany(mappedBy = "validationEntree")
//    //@JoinColumn(name = "id_personne")
//    private List<Emprunt> listeEmpruntsEntree = new ArrayList<>();
//
//    @OneToMany(mappedBy = "validationRetour")
//    private List<Emprunt> listeEmpruntsRetour = new ArrayList<>();
//
//    @OneToMany(mappedBy = "validationProlongation")
//    private List<Emprunt> listeEmpruntsProlongation = new ArrayList<>();


    public Gestionnaire() {
    }

    public Gestionnaire(Integer id, String nom, String prenom, String mail) {
        super(id, nom, prenom, mail);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

}
