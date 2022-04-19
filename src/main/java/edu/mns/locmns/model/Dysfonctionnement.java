package edu.mns.locmns.model;

import java.util.Date;

public class Dysfonctionnement {
    private Integer id;
    private Date dateDysfonctionnement;
    private String descriptif;
    private Date datePriseEnCharge;

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
}
