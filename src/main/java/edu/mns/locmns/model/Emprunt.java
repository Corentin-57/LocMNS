package edu.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.view.View;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.ListeDemandesEmprunt.class)
    private Integer idEmprunt;

    @JsonView(View.ListeDemandesEmprunt.class)
    private LocalDateTime dateDemande;

    @JsonView(View.ListeDemandesEmprunt.class)
    private LocalDateTime dateEmprunt;

    @JsonView(View.ListeDemandesEmprunt.class)
    private LocalDateTime dateRetour;

    private LocalDateTime dateValidation;

    @JsonView(View.ListeDemandesEmprunt.class)
    private LocalDateTime dateProlongation;

    private LocalDateTime dateDemandeRetour;


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
    @JsonView(View.ListeDemandesEmprunt.class)
    Materiel materiel;

    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    @JsonView(View.ListeDemandesEmprunt.class)
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

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalDateTime getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDateTime dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDateTime getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDateTime dateRetour) {
        this.dateRetour = dateRetour;
    }

    public LocalDateTime getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(LocalDateTime dateValidation) {
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

    public LocalDateTime getDateProlongation() {
        return dateProlongation;
    }

    public void setDateProlongation(LocalDateTime dateProlongation) {
        this.dateProlongation = dateProlongation;
    }

    public LocalDateTime getdateDemandeRetour() {
        return dateDemandeRetour;
    }

    public void setdateDemandeRetour(LocalDateTime demandeRetour) {
        this.dateDemandeRetour = demandeRetour;
    }

    public CadreUtilisation getCadreUtilisation() {
        return cadreUtilisation;
    }

    public void setCadreUtilisation(CadreUtilisation cadreUtilisation) {
        this.cadreUtilisation = cadreUtilisation;
    }
}
