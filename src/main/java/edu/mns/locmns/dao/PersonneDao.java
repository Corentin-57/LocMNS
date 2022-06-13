package edu.mns.locmns.dao;

import edu.mns.locmns.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonneDao extends JpaRepository<Personne, Integer> {

    Optional<Personne> findByMail(String mail);


}
