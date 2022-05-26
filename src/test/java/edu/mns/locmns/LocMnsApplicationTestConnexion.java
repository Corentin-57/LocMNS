package edu.mns.locmns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LocMnsApplicationTestConnexion {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    private void initialisation(){ //Fonction va se lancer à chaque test
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

//    @Test
//    public void connexionAvecIdentifiantsIncorrects() throws Exception {
//        mockMvc.perform(post("/connexion")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("username", "username")
//                        .param("password", "password")
//                )
//                .andExpect(status().isUnauthorized());
//    }

//    @Test
//    @WithMockUser(username = "username", password = "password")
//    public void connexionAvecIdentifiantsIncorrects() throws Exception{
//        mockMvc.perform(post("/connexion"))
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                       .param("username", "username")
//                        .param("password", "password")
//                .andExpect(status().isUnauthorized());
//    }

//    @Test
//    public void connexionAvecIdentifiantsIncorrects() throws Exception{
//        mockMvc.perform(formLogin().password("invalid"))
//                .andExpect(unauthenticated());
//    }
}

