package fr.thiiozz.test.depenses;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.thiiozz.controller.DepenseController;
import fr.thiiozz.dao.DepenseDAO;
import fr.thiiozz.dao.RoleDAO;
import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.Authority;
import fr.thiiozz.model.User;
import fr.thiiozz.services.DepenseService;


@RunWith(SpringRunner.class)
@WebMvcTest(DepenseController.class)
@WebAppConfiguration
public class DepenseControllerTest  {
	
	@Autowired
    private MockMvc mvc;
	
    @MockBean
    private DepenseService depenseService;
    
    @MockBean
    private DepenseDAO dao;
    
    @Test
    public void unUtilisateurPeAccederAuDepensesSiIlEstAuthentifie() throws Exception {
    	mvc.perform(get("/depenses").with(user("titi").password("titi").roles("ADMIN", "USER")))
    		.andExpect(status().isOk());
    }
    
    @Test
    public void unUtilisateurNePeuPasAccederAuDepensesSiIlEstPasAuthentifie() throws Exception {
    	mvc.perform(get("/depenses").with(user("titi").password("toto").roles("NUllos")))
        		.andExpect(status().isForbidden());
    }
}
