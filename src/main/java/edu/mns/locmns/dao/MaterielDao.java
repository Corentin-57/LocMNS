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
import java.time.LocalDateTime;
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

    //Recherche d'un materiel présent table emprunt qui correspond au modèle selectionné  et qui a une date de demande null et une date de retour inférieure ou égale à la date de demande d'emprunt sasie par l'utilisateur (matériel déjà été emprunté)
    @Query(value = "SELECT materiel.id_materiel FROM materiel INNER JOIN modele ON materiel.id_modele = modele.id_modele INNER JOIN emprunt ON materiel.id_materiel = emprunt.id_materiel WHERE modele.id_modele = :idModele AND emprunt.date_demande_emprunt IS NULL AND emprunt.date_retour <= :dateEmprunt LIMIT 1", nativeQuery = true)
    Integer RechercheMaterielDemandeEmprunt(@Param("idModele")Integer idModele,@Param("dateEmprunt") LocalDateTime dateEmprunt);

    //Recherche d'un matériel qui n'est pas présent dans table emprunt (nouveau matériel)
    @Query(value="SELECT materiel.id_materiel FROM materiel INNER JOIN modele ON materiel.id_modele = modele.id_modele LEFT JOIN emprunt ON materiel.id_materiel = emprunt.id_materiel WHERE modele.id_modele = :idModele AND emprunt.id_emprunt IS NULL LIMIT 1", nativeQuery = true)
    Integer RechercheNouveauxMaterielDemandeEmprunt(@Param("idModele") Integer idModele);



    @Query(value= "SELECT count(*) FROM materiel m INNER JOIN etat e ON m.id_etat = e.id_etat  WHERE m.id_etat = 2", nativeQuery = true)
    Integer RechercherNombreMaterielDefectueux();

    //Recherche nombre matériels en stock (opérationnel)
    @Query(value= "SELECT count(*) FROM materiel m INNER JOIN etat e ON m.id_etat = e.id_etat  WHERE m.id_etat = 1", nativeQuery = true)
    Integer RechercherNombreMaterielOperationnel();


    @Query(value="SELECT count(*) FROM materiel m INNER JOIN emprunt e ON m.id_materiel = e.id_materiel WHERE e.date_retour > curdate() AND e.date_validation_emprunt is null", nativeQuery = true)
    Integer RechercherNombreMaterielEnRetard();

    List<Materiel> findAllByEtatIdEtat(Integer idEtat);

}


