package fr.thiiozz.test.utilisateur;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import fr.thiiozz.controller.UtilisateurController;
import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.User;
import fr.thiiozz.services.UtilisateurService;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UtilisateurController.class)
public class UtilisateurControllerTest {
	@Autowired
    private MockMvc mvc;
	
    @MockBean
    private UtilisateurService utilisateurService;
    
    @MockBean
    private UtilisateurDAO dao;
    
    @Test
    public void testExample() throws Exception {
    	mvc.perform(get("/login").accept(MediaType.TEXT_PLAIN))
    		.andExpect(status().isOk());
    	
    	User utilisateur = new User("MrTest", "TEST", true);
    	
        given(utilisateurService.ajouterUnUtilisateur(utilisateur)).willReturn(true);
        
        mvc.perform(get("/login?utilisateur=MrTest").accept(MediaType.TEXT_PLAIN))
        	.andExpect(status().isOk());
        	//.andExpect(redirectedUrl("/depenses?utilisateur=MrTest"));
    }
}
