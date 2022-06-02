package edu.mns.locmns.dao;

import edu.mns.locmns.model.Emprunt;
import edu.mns.locmns.model.Utilisateur;
import jdk.jshell.execution.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByMail(String mail);

//    @Query("FROM Utilisateur u JOIN Emprunt e ON u.id = e.idEmprunt WHERE e.dateDemande is not null")
//    List<Utilisateur> RechercheUtilisateursDemandeEmprunt();

}
