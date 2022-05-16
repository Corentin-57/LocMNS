package edu.mns.locmns.controller;

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
    public EmpruntController(EmpruntDao empruntDao) {
        this.empruntDao = empruntDao;
    }

    @GetMapping("/gestionnaire/liste-emprunts")
    public List<Emprunt> listeEmprunts(){
        return this.empruntDao.findAll();
    }

    @GetMapping("gestionnaire/emprunt/{idUtilisateur}/{idMateriel}/{dateEmprunt}")
    public Emprunt emprunt(@PathVariable Integer idUtilisateur,
                           @PathVariable Integer idMateriel,
                           @PathVariable String dateEmprunt)
                           throws ParseException {
        Date nouvelleDateEmprunt = new SimpleDateFormat("yyyy-MM-dd").parse(dateEmprunt);

        return this.empruntDao.findByUtilisateurIdAndMaterielIdMaterielAndDateEmprunt(idUtilisateur, idMateriel, nouvelleDateEmprunt).orElse(null);

    }

    @PostMapping("/reservation")
    public String createReservation (@RequestBody Emprunt emprunt){
        this.empruntDao.save(emprunt);
        return "La demande de réservation est créee";
    }

/*    @DeleteMapping("/gestionnaire/reservation/{idUtilisateur}/{idMateriel}/{dateEmprunt})")
    public String deleteReservation(@PathVariable Integer idUtilisateur, @PathVariable Integer idMateriel, @PathVariable Date dateEmprunt) {
        //this.empruntDao.deleteByIdUtilisateurAndIdMaterielAndDateEmprunt(idUtilisateur, idMateriel, dateEmprunt);
        return "Le matériel a bien été supprimé";

    }*/
}
