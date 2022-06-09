package edu.mns.locmns.model;


import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.view.View;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Modele {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModele;

    @JsonView(View.ListeDemandesEmprunt.class)
    private String nomModele;

    @ManyToMany
    @JoinTable(
            name="detailler",
            joinColumns = @JoinColumn(name="id_modele"),
            inverseJoinColumns = @JoinColumn(name="id_caracteristique")
    )
    private List<Caracteristique> listcaracteristique = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="id_marque")
    private Marque marque;

    @ManyToOne
    @JoinColumn(name="id_type")
    @JsonView(View.ListeDemandesEmprunt.class)
    private TypeMateriel typeMateriel;

    @OneToMany(mappedBy = "modele")
    private List<Materiel> materiel = new ArrayList<>();

    public Integer getIdModele() {
        return idModele;
    }

    public void setIdModele(Integer idModele) {
        this.idModele = idModele;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }
}