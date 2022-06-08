package edu.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.view.View;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class LieuStockage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.ListeLieux.class)
    private Integer idStock;

    @JsonView(View.ListeLieux.class)
    private String nomLieuStockage;

    @OneToMany(mappedBy = "lieuStockage")
    private List<Materiel> listemateriel = new ArrayList<>();

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public String getNomLieuStockage() {
        return nomLieuStockage;
    }

    public void setNomLieuStockage(String nomLieuStockage) {
        this.nomLieuStockage = nomLieuStockage;
    }

    public List<Materiel> getListemateriel() {
        return listemateriel;
    }

    public void setListemateriel(List<Materiel> listemateriel) {
        this.listemateriel = listemateriel;
    }
}
