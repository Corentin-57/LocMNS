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

    private Date dateDemande;

    private Date dateEmprunt;

    private Date dateRetour;

    private Date dateValidation;

    private Date dateProlongation;



    @ManyToMany
    @JoinTable(
            name="contient",
            joinColumns = @JoinColumn(name="id_emprunt"),
            inverseJoinColumns = @JoinColumn(name="id_cadre")
    )
    private List<CadreUtilisation> listeCadresUtilisation = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="affecter",
            joinColumns = @JoinColumn(name="id_emprunt"),
            inverseJoinColumns = @JoinColumn(name="id_lieu")
    )
    private List<LieuUtilisation> listeLieuxUtilisation = new ArrayList<>();

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
    @JsonIgnore
    Materiel materiel;

    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;


    public Integer getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
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
}
