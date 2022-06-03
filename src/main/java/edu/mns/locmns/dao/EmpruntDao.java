package edu.mns.locmns.dao;

import edu.mns.locmns.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
