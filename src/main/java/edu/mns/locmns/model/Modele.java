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
    @JsonView(View.listeModeles.class)
    private Integer idModele;

    @JsonView({View.ListeDemandesEmprunt.class, View.listeHistoriqueMateriels.class, View.listeModeles.class})
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
    @JsonView({View.ListeDemandesEmprunt.class, View.listeHistoriqueMateriels.class})
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

    public List<Caracteristique> getListcaracteristique() {
        return listcaracteristique;
    }

    public void setListcaracteristique(List<Caracteristique> listcaracteristique) {
        this.listcaracteristique = listcaracteristique;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public TypeMateriel getTypeMateriel() {
        return typeMateriel;
    }

    public void setTypeMateriel(TypeMateriel typeMateriel) {
        this.typeMateriel = typeMateriel;
    }

    public List<Materiel> getMateriel() {
        return materiel;
    }

    public void setMateriel(List<Materiel> materiel) {
        this.materiel = materiel;
    }
}
