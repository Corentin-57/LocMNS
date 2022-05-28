package edu.mns.locmns.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Dysfonctionnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dateDysfonctionnement;

    private String descriptif;

    private Date datePriseEnCharge;

    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name="id_gestionnaire")
    private Gestionnaire gestionnaire;

    @ManyToOne
    @JoinColumn(name="id_materiel")
    private Materiel materiel;

    public Dysfonctionnement(Date dateDysfonctionnement, String descriptif, Utilisateur utilisateur, Materiel materiel) {
        this.dateDysfonctionnement = dateDysfonctionnement;
        this.descriptif = descriptif;
        this.utilisateur = utilisateur;
        this.materiel = materiel;
    }

    public Dysfonctionnement() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDysfonctionnement() {
        return dateDysfonctionnement;
    }

    public void setDateDysfonctionnement(Date dateDysfonctionnement) {
        this.dateDysfonctionnement = dateDysfonctionnement;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Date getDatePriseEnCharge() {
        return datePriseEnCharge;
    }

    public void setDatePriseEnCharge(Date datePriseEnCharge) {
        this.datePriseEnCharge = datePriseEnCharge;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }
}
