package fr.thiiozz.test.depenses;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import fr.thiiozz.controller.DepenseController;
import fr.thiiozz.dao.DepenseDAO;
import fr.thiiozz.model.Depense;
import fr.thiiozz.services.DepenseService;


@RunWith(SpringRunner.class)
@WebMvcTest(DepenseController.class)
public class DepenseControllerTest  {
	
	@Autowired
    private MockMvc mvc;
	
    @MockBean
    private DepenseService depenseService;
    
    @MockBean
    private DepenseDAO dao;
    
    @Test
    public void testExample() throws Exception {
        given(this.depenseService.ajouterUneDepense(new Depense("test", "test", 123))).willReturn(true);
        this.mvc.perform(get("/depenses").accept(MediaType.TEXT_PLAIN));
    }
}
