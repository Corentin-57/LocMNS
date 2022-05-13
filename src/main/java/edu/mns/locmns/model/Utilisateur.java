package edu.mns.locmns.model;

import edu.mns.locmns.model.Personne;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Utilisateur extends Personne {

    private String numeroTelephone;

    private String adresse;

    @ManyToOne
    @JoinColumn(name="id_statut")
    private Statut statut;

    @OneToMany(mappedBy = "utilisateur")
    private List<Retard> listeRetards = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<Dysfonctionnement> listeDysfonctionnements = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<Emprunt> listeEmprunts = new ArrayList<>();

    public Utilisateur() {

    }

    public Utilisateur(Integer id, String nom, String prenom, String mail, String numeroTelephone, String adresse) {
        super(id, nom, prenom, mail);
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
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

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
