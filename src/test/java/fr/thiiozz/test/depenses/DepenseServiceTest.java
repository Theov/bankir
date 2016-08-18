package fr.thiiozz.test.depenses;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.thiiozz.model.Depense;
import fr.thiiozz.model.User;
import fr.thiiozz.services.DepenseService;
import fr.thiiozz.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class DepenseServiceTest {
	@Autowired
    private UserService serviceUser;
	
	@Autowired
    private DepenseService service;
	
	User userTest;
	Depense depenseTest;
	
	@Before
	public void setUp(){
		userTest = new User("titi", "titi", true);
		
		depenseTest = new Depense("Test", 100f, userTest);
		userTest.getDepenses().add(depenseTest);
		
		serviceUser.sauvegarderUtilisateur(userTest);
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void UneDepensePeutEtreAjoutee(){
		List<Depense> depenses = service.getAllDepenses();
		assertThat(depenses).isNotEmpty();
		assertThat(depenses.get(0).getMontant()).isEqualTo(100f);
	}
	
	@Test
	public void UneDepensePeutEtreModifiee(){
		List<Depense> depenses = service.getAllDepenses();
		assertThat(depenses).isNotEmpty();
		
		depenses.get(0).setMontant(200f);
		service.sauvegarderDepense(depenses.get(0));
		
		List<Depense> depensesApresOperation = service.getAllDepenses();
		assertThat(depensesApresOperation.get(0).getMontant()).isEqualTo(200f);
	}
	
	@Test
	public void UneDepensePeutEtreSupprimee(){
		List<Depense> depenses = service.getAllDepenses();
		assertThat(depenses).isNotEmpty();
		
		service.supprimerUneDepense(depenses.get(0).getId());
		
		List<Depense> depensesApresOperation = service.getAllDepenses();
		assertThat(depensesApresOperation).isEmpty();
	}
	
	@Test
	public void UneDepensePeutEtreOfferte(){
		List<Depense> depenses = service.getAllDepenses();
		assertThat(depenses).isNotEmpty();
		assertThat(depenses.get(0).getOffert()).isFalse();
		
		service.offrirDepense(depenses.get(0), true);
		
		List<Depense> depensesApresOperation = service.getAllDepenses();
		assertThat(depensesApresOperation.get(0).getOffert()).isTrue();
	}
	
	/*@Test
	public void uneDepensePeutEtreRembousee(){
		List<Depense> depenses = service.getAllDepenses();
		assertThat(depenses).isNotEmpty();
		assertThat(depenses.get(0).getRembourser()).isFalse();
		
		service.rembourserDepense(depenses.get(0));
		
		List<Depense> depensesApresOperation = service.getAllDepenses();
		assertThat(depensesApresOperation.size()).isEqualTo(3);
		assertThat(depensesApresOperation.get(0).getRembourser()).isTrue();
		assertThat(depensesApresOperation.get(1).getMontant()).isEqualTo(50f);
		assertThat(depensesApresOperation.get(2).getMontant()).isEqualTo(50f);
	}*/
}
