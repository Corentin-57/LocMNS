package edu.mns.locmns;

import edu.mns.locmns.model.Dysfonctionnement;
import edu.mns.locmns.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocMnsApplicationTests {

    @Autowired
    private WebApplicationContext context; //Récupère le contexte de l'app (configuration, état actuel)

    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @BeforeEach // Lance cette méthode avant chaque test
    private void initialisation(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

    }


    //Tests Page Etudiant

    @Test
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurRecupereListeTypeMateriels_reponse200ok() throws Exception{
        mvc.perform(get("/liste-typeMateriels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomType").value("ordinateur portable"))
                .andExpect(jsonPath("$[1].nomType").value("projecteur"))
                .andExpect(jsonPath("$[2].nomType").value("Webcam"))
                .andExpect(jsonPath("$[3].nomType").value("casque VR"));
    }

    @Test
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurRecupereListeModeles_reponse200ok() throws Exception{
        mvc.perform(get("/liste-modeles")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurRecupereListeCadresUtilisation_reponse200ok() throws Exception{
        mvc.perform(get("/liste-cadres-utilisation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].typeEvenement").value("Salon professionnel"))
                .andExpect(jsonPath("$[1].typeEvenement").value("Réunion"))
                .andExpect(jsonPath("$[2].typeEvenement").value("Journée portes ouvertes"))
                .andExpect(jsonPath("$[3].typeEvenement").value("Cours"))
                .andExpect(jsonPath("$[4].typeEvenement").value("Location longue"));
    }

    @Test //Test que l'utilisateur ne puisse pas récupérer liste matériels (uniquement gestionnaire)
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurRecupereListeMateriel_reponse403forbidden() throws Exception{
        mvc.perform(get("/gestionnaire/liste-materiels")).andExpect(status().isForbidden());
    }


    @Test //Test que l'utilisateur puisse envoyer un dysfonctionnement
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurSaisirDysfonctionnement_reponse200ok() throws Exception{
        mvc.perform(post("/saisir-dysfonctionnement")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"descriptif\": \"mon ordi est cassé\", \"dateDysfonctionnement\": \"2022-06-14\", \"utilisateur\": {\"id\": 5}, \"materiel\": {\"idMateriel\": 5} }")
        ).andExpect(status().isOk());
    }

    @Test //Test que l'utilisateur puisse faire une demande de prolongation
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurSaisirProlongation_reponse200ok() throws Exception{
        mvc.perform(post("/demande-prolongation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"dateProlongation\": \"2022-06-14\", \"utilisateur\": {\"id\": 14}, \"materiel\": {\"idMateriel\": 1} }")
        ).andExpect(status().isOk());
    }

    @Test //Test que l'utilisateur puisse faire une demande de retour
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurSaisirRetour_reponse200ok() throws Exception{
        mvc.perform(post("/demande-retour")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"dateDemandeRetour\": \"2022-09-10\", \"utilisateur\": {\"id\": 5}, \"materiel\": {\"idMateriel\": 5} }")
        ).andExpect(status().isOk());
    }



    //Tests page gestionnaire


    //Test que l'on récupère bien la listes des roles du Statut
    @Test
    @WithMockUser(username = "lorem@icloud.com", roles = {"GESTIONNAIRE"})
    void GestonnaireRecupereListeStatut_reponse200ok() throws Exception{
        mvc.perform(get("/liste-statut"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].role").value("etudiant"))
                .andExpect(jsonPath("$[1].role").value("intervenant"));
    }

    //Test que l'on récupère bien la liste des lieux de stockage
    @Test
    @WithMockUser(username = "lorem@icloud.com", roles = {"GESTIONNAIRE"})
    void GestonnaireRecupereListeLieuxStockage_reponse200ok() throws Exception{
        mvc.perform(get("/gestionnaire/liste-lieuxStockage"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomLieuStockage").value("MNS"))
                .andExpect(jsonPath("$[1].nomLieuStockage").value("IFA"));
    }

}
