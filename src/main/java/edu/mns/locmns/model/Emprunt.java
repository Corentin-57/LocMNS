package edu.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import edu.mns.locmns.view.View;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

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
    private LocalDateTime dateDemandeEmprunt;

    @JsonView({View.ListeDemandesEmprunt.class, View.listeHistoriqueMateriels.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateEmprunt;


    @JsonView({View.ListeDemandesEmprunt.class, View.listeHistoriqueMateriels.class})
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateRetour;

    @JsonView(View.ListeDemandesEmprunt.class)
    private LocalDateTime dateProlongation;

    private LocalDateTime dateValidationEmprunt;

    private LocalDateTime dateValidationRetour;

    private LocalDateTime dateValidationProlongation;

    @JsonView(View.ListeDemandesEmprunt.class)
    private LocalDateTime dateDemandeRetour;


//    @ManyToOne
//    @JoinColumn(name="id_gestionnaire_entree")
//    //@JoinColumn(name ="id_personne", insertable = false, updatable = false) //Ignore this field when save or update request
//    private Gestionnaire validationEntree;
//
//    @ManyToOne
//    @JoinColumn(name="id_gestionnaire_retour")
//    private Gestionnaire validationRetour;
//
//    @ManyToOne
//    @JoinColumn(name="id_gestionnaire_prolongation")
//    private Gestionnaire validationProlongation;

    @ManyToOne
    @JoinColumn(name="id_gestionnaire")
    private Gestionnaire gestionnaire;

    @ManyToOne
    @JoinColumn(name="id_materiel")
    @JsonView({View.ListeDemandesEmprunt.class, View.listeHistoriqueMateriels.class})
    private Materiel materiel;

    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    @JsonView({View.ListeDemandesEmprunt.class, View.listeHistoriqueMateriels.class})
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

    public LocalDateTime getDateDemandeEmprunt() {
        return dateDemandeEmprunt;
    }

    public void setDateDemandeEmprunt(LocalDateTime dateDemandeEmprunt) {
        this.dateDemandeEmprunt = dateDemandeEmprunt;
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

    public LocalDateTime getDateValidationEmprunt() {
        return dateValidationEmprunt;
    }

    public void setDateValidationEmprunt(LocalDateTime dateValidationEmprunt) {
        this.dateValidationEmprunt = dateValidationEmprunt;
    }

    public LocalDateTime getDateValidationRetour() {
        return dateValidationRetour;
    }

    public void setDateValidationRetour(LocalDateTime dateValidationRetour) {
        this.dateValidationRetour = dateValidationRetour;
    }

    public LocalDateTime getDateValidationProlongation() {
        return dateValidationProlongation;
    }

    public void setDateValidationProlongation(LocalDateTime dateValidationProlongation) {
        this.dateValidationProlongation = dateValidationProlongation;
    }

    public Gestionnaire getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(Gestionnaire gestionnaire) {
        this.gestionnaire = gestionnaire;
    }


}
