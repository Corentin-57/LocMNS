package edu.mns.locmns.controller;

import edu.mns.locmns.dao.TypeMaterielDao;
import edu.mns.locmns.model.TypeMateriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@CrossOrigin
@RestController
public class TypeMaterielController {

    private TypeMaterielDao typeMaterielDao;

    @Autowired
    public TypeMaterielController(TypeMaterielDao typeMaterielDao) {
        this.typeMaterielDao = typeMaterielDao;
    }

    @GetMapping("/liste-typeMateriels")
    public List<TypeMateriel> ListeTypeMateriels(){
        return this.typeMaterielDao.findAll();
    }

    @GetMapping("/typeMateriel/{id}")
    public TypeMateriel typeMateriel(@PathVariable Integer id){
        return this.typeMaterielDao.findById(id).orElse(null);
    }

}
