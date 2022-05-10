package edu.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.locmns.dao.EmpruntDao;
import edu.mns.locmns.model.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class EmpruntController {

    private EmpruntDao empruntDao;

    @Autowired
    public EmpruntController(EmpruntDao empruntDao) {this.empruntDao = empruntDao;}

    @GetMapping("liste-reservations")
    public List<Emprunt> listeEmprunts () {
        return this.empruntDao.findAll();
    }

   /* @GetMapping("/emprunt/{idUtilisateur}/{idMateriel}/{dateEmprunt}")
    public Emprunt emprunt(
            @PathVariable Integer idUtilisateur,
            @PathVariable Integer idMateriel,
            @PathVariable String dateEmprunt
    ) throws ParseException {

        Date nouvelleDateEmprunt = new SimpleDateFormat("yyyy-MM-dd").parse(dateEmprunt);

        return this.empruntDao
                .findByidUtilisateurAndidMaterielAnddateEmprunt(idUtilisateur,idMateriel,nouvelleDateEmprunt)
                .orElse(null);
    }*/

    @PostMapping("/emprunt")
    public String createEmprunt(@RequestBody Emprunt emprunt){

        this.empruntDao.save(emprunt);

        return "La demande de réservation est créer";
    }

   /* @DeleteMapping("/gestionnaire/emprunt/{idUtilisateur}/{idMateriel}/{dateEmprunt}")
    public String deleteReservation(
            @PathVariable Integer idUtilisateur,
            @PathVariable Integer idMateriel,
            @PathVariable Date dateEmprunt
    ){
        this.empruntDao.deleteByidUtilisateurAndidMaterielAndDateEmprunt(
                idUtilisateur,
                idMateriel,
                dateEmprunt);

        return "Le materiel à bien été supprimer";
    }*/
}
