package edu.mns.locmns.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class LieuUtilisation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLieu;

    private String nomLieuUtilisation;

    public Integer getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Integer idLieu) {
        this.idLieu = idLieu;
    }

    public String getNomLieuUtilisation() {
        return nomLieuUtilisation;
    }

    public void setNomLieuUtilisation(String nomLieuUtilisation) {
        this.nomLieuUtilisation = nomLieuUtilisation;
    }
}
