package edu.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.view.View;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class TypeMateriel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idType;

    @JsonView(View.ListeDemandesEmprunt.class)
    private String nomType;

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
}
