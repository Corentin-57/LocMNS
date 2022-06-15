package edu.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.view.View;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.ListeMaterielsUtilisateur.class, View.ListeMaterielsNumeroSerie.class, View.listeNumerosSerieModification.class })
    private Integer idMateriel;  // Integer car c'est un objet il peut contenir la valeur Null contrairement a int

    @JsonView({View.ListeMaterielsUtilisateur.class, View.ListeDemandesEmprunt.class, View.ListeMaterielsNumeroSerie.class, View.listeNumerosSerieModification.class })
    private String numeroSerie;

    @ManyToOne
    @JoinColumn(name="id_lieu_stockage")
    private LieuStockage lieuStockage;

    @ManyToOne
    @JoinColumn(name="id_modele")
    @JsonView({View.ListeDemandesEmprunt.class, View.listeHistoriqueMateriels.class})
    private Modele modele;

    @ManyToOne
    @JoinColumn(name="id_etat")
    private Etat etat;

    @ManyToMany
    @JoinTable(
            name="fournit",
            joinColumns = @JoinColumn(name="id_materiel"),
            inverseJoinColumns = @JoinColumn(name="id_document")

    )
    private List<Document> listdocument = new ArrayList<>();

    @OneToMany(mappedBy = "materiel")
    private List<Emprunt> emprunt = new ArrayList<>();

    @OneToMany(mappedBy = "materiel")
    private List <Dysfonctionnement>  dysfonctionnement = new ArrayList<>();

    public Integer getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(Integer idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public LieuStockage getLieuStockage() {
        return lieuStockage;
    }

    public void setLieuStockage(LieuStockage lieuStockage) {
        this.lieuStockage = lieuStockage;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public List<Document> getListdocument() {
        return listdocument;
    }

    public void setListdocument(List<Document> listdocument) {
        this.listdocument = listdocument;
    }

    public List<Emprunt> getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(List<Emprunt> emprunt) {
        this.emprunt = emprunt;
    }

    public List<Dysfonctionnement> getDysfonctionnement() {
        return dysfonctionnement;
    }

    public void setDysfonctionnement(List<Dysfonctionnement> dysfonctionnement) {
        this.dysfonctionnement = dysfonctionnement;
    }




}
