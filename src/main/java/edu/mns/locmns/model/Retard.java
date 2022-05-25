package edu.mns.locmns.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Retard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dateAlerteRetard;

    private Date datePriseEnCharge;

    @ManyToOne
    @JoinColumn(name="id_personne_gestionnaire")
    private Gestionnaire gestionnaire;

    @ManyToOne
    @JoinColumn(name="id_personne_utilisateur")
    private Utilisateur utilisateur;

    //Faut il mettre many to one inversé

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateAlerteRetard() {
        return dateAlerteRetard;
    }

    public void setDateAlerteRetard(Date dateAlerteRetard) {
        this.dateAlerteRetard = dateAlerteRetard;
    }

    public Date getDatePriseEnCharge() {
        return datePriseEnCharge;
    }

    public void setDatePriseEnCharge(Date datePriseEnCharge) {
        this.datePriseEnCharge = datePriseEnCharge;
    }
}
