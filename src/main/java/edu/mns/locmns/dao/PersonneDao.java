package edu.mns.locmns.dao;

import edu.mns.locmns.model.Personne;
import edu.mns.locmns.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonneDao extends JpaRepository<Personne, Integer> {

    Optional<Personne> findByMail(String mail);


}
