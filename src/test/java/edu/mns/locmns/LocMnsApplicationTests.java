package edu.mns.locmns;

import edu.mns.locmns.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Test
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurRecupereListeMarques_reponse200ok() throws Exception{
        mvc.perform(get("/liste-marques")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "donec@aol.ca", roles = {"UTILISATEUR"})
    void UtilisateurRecupereListeMateriel_reponse403forbidden() throws Exception{
        mvc.perform(get("/gestionnaire/liste-materiels")).andExpect(status().isForbidden());
    }

}
