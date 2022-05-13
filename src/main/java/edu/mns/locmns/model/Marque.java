package edu.mns.locmns.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Marque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarque;

    private String nomMarque;

    @OneToMany
    private List<Modele> listeModeles = new ArrayList<>();

    public Integer getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(Integer idMarque) {
        this.idMarque = idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public List<Modele> getListeModeles() {
        return listeModeles;
    }

    public void setListeModeles(List<Modele> listeModeles) {
        this.listeModeles = listeModeles;
    }
}
