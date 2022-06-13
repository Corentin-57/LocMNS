package edu.mns.locmns.dao;

import edu.mns.locmns.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpruntDao extends JpaRepository<Emprunt, Integer> {

    Optional<Emprunt> findByUtilisateurIdAndMaterielIdMaterielAndDateEmprunt(
            Integer idUtilisateur,
            Integer idMateriel,
            Date date
    );

    void deleteByUtilisateurIdAndMaterielIdMaterielAndDateEmprunt(
            Integer idUtilisateur,
            Integer idMateriel,
            Date dateEmprunt
    );

    Emprunt findByUtilisateurIdAndMaterielIdMateriel(
            Integer idUtilisateur,
            Integer idMateriel
    );

    List findAllByDateDemandeEmpruntIsNotNull();

    List findAllByDateDemandeRetourIsNotNull();

    List findAllByDateProlongationIsNotNull();

    @Query("SELECT count(e) FROM Emprunt e WHERE e.dateDemandeEmprunt is not null")
    Integer RechercherNombreDemandesEmprunt();

    @Query("SELECT count(e) FROM Emprunt e WHERE e.dateDemandeRetour is not null")
    Integer RechercherNombreDemandesRetour();

    @Query("SELECT count(e) FROM Emprunt e WHERE e.dateProlongation is not null")
    Integer RechercherNombreDemandesProlongation();

//    @Query("FROM Emprunt e INNER JOIN Materiel m ON e.materiel.idMateriel = m.idMateriel  WHERE e.dateValidationRetour is not null AND m.idMateriel = :idMateriel")
//    List RechercherHistoriqueEmpruntEffectueUnMateriel(@Param("idMateriel") Integer idMateriel);

    List findAllByDateValidationRetourIsNotNull();

}
