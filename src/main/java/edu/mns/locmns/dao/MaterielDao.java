package edu.mns.locmns.dao;

import edu.mns.locmns.model.Emprunt;
import edu.mns.locmns.model.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
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

}
