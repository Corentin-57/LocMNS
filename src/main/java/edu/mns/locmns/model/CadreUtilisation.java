package edu.mns.locmns.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class CadreUtilisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCadre;

    private String typeEvenement;

//    @OneToMany(mappedBy = "cadreUtilisation")
//    List<CadreUtilisation> listeCadresUtilisation = new ArrayList<>();

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
