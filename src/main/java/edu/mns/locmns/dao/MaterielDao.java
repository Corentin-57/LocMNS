package edu.mns.locmns.dao;

import edu.mns.locmns.model.Emprunt;
import edu.mns.locmns.model.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MaterielDao extends JpaRepository<Materiel, Integer> {

    Optional<Materiel> findAllByEmpruntUtilisateur(
            Integer idUtilisateur
    );

    //Récupére l'id et numéro de série des matériels en cours d'emprunt de l'utilisateur
    @Query("FROM Materiel m JOIN Emprunt e ON m.idMateriel = e.materiel.idMateriel WHERE e.utilisateur.id = :id AND e.dateRetour > current_date")
    List<Materiel> listeMaterielsUtilisateur(Integer id);

    @Query(value = "SELECT materiel.id_materiel FROM materiel INNER JOIN modele ON materiel.id_modele = modele.id_modele INNER JOIN emprunt ON materiel.id_materiel = emprunt.id_materiel WHERE modele.id_modele = :idModele AND emprunt.date_demande IS NULL AND emprunt.date_retour <= :dateEmprunt LIMIT 1", nativeQuery = true)
    Integer RechercheMaterielDemandeEmprunt(@Param("idModele")Integer idModele,@Param("dateEmprunt") LocalDate dateEmprunt);

    @Query(value="SELECT materiel.id_materiel FROM materiel INNER JOIN modele ON materiel.id_modele = modele.id_modele LEFT JOIN emprunt ON materiel.id_materiel = emprunt.id_materiel WHERE modele.id_modele = :idModele AND emprunt.id_emprunt IS NULL LIMIT 1", nativeQuery = true)
    Integer RechercheNouveauxMaterielDemandeEmprunt(@Param("idModele") Integer idModele);
}


