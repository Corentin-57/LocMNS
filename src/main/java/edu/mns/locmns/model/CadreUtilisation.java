package edu.mns.locmns.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CadreUtilisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCadre;

    private String typeEvenement;

    public Integer getIdCadre() {
        return idCadre;
    }

    public void setIdCadre(Integer idCadre) {
        this.idCadre = idCadre;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }
}
