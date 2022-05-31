package edu.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprunt;

    private LocalDate dateDemande;

    private LocalDate dateEmprunt;

    private Date dateRetour;

    private Date dateValidation;

    private Date dateProlongation;

    private Date dateDemandeRetour;


    @ManyToOne
    @JoinColumn(name="id_gestionnaire_entree")
    private Gestionnaire validationEntree;

    @ManyToOne
    @JoinColumn(name="id_gestionnaire_retour")
    private Gestionnaire validationRetour;

    @ManyToOne
    @JoinColumn(name="id_gestionnaire_prolongation")
    private Gestionnaire validationProlongation;

    @ManyToOne
    @JoinColumn(name="id_materiel")
    Materiel materiel;

    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name="id_cadre")
    private CadreUtilisation cadreUtilisation;

    public Integer getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDateProlongation() {
        return dateProlongation;
    }

    public void setDateProlongation(Date dateProlongation) {
        this.dateProlongation = dateProlongation;
    }

    public Date getdateDemandeRetour() {
        return dateDemandeRetour;
    }

    public void setdateDemandeRetour(Date demandeRetour) {
        this.dateDemandeRetour = demandeRetour;
    }

    public CadreUtilisation getCadreUtilisation() {
        return cadreUtilisation;
    }

    public void setCadreUtilisation(CadreUtilisation cadreUtilisation) {
        this.cadreUtilisation = cadreUtilisation;
    }
}
