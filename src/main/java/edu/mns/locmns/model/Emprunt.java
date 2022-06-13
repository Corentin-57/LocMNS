package edu.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.view.View;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateRetour;

    @JsonView(View.ListeDemandesEmprunt.class)
    private LocalDateTime dateProlongation;

    private LocalDateTime dateValidationEmprunt;

    private LocalDateTime dateValidationRetour;

    private LocalDateTime dateValidationProlongation;

    @JsonView(View.ListeDemandesEmprunt.class)
    private LocalDateTime dateDemandeRetour;


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
