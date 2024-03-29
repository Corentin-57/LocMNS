package edu.mns.locmns.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Caracteristique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCaracteristique;

    private String nomCaracteristique;

    public Integer getIdCaracteristique() {
        return idCaracteristique;
    }

    public void setIdCaracteristique(Integer idCaracteristique) {
        this.idCaracteristique = idCaracteristique;
    }

    public String getNomCaracteristique() {
        return nomCaracteristique;
    }

    public void setNomCaracteristique(String nomCaracteristique) {
        this.nomCaracteristique = nomCaracteristique;
    }
}
