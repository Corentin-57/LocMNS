package edu.mns.locmns.dao;

import edu.mns.locmns.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EmpruntDao extends JpaRepository<Emprunt, Integer> {

    /*Optional<Emprunt> findByidUtilisateurAndidMaterielAnddateEmprunt(
            Integer idUtilisateur,
            Integer idMateriel,
            Date date
    );

    void deleteByidUtilisateurAndidMaterielAndDateEmprunt(
            Integer idUtilisateur,
            Integer idMateriel,
            Date dateEmrunt
    );*/
}
