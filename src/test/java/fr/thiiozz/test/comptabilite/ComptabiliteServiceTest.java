package fr.thiiozz.test.comptabilite;

import static org.assertj.core.api.Assertions.*;

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
import fr.thiiozz.services.ComptabiliteService;
import fr.thiiozz.services.DepenseService;
import fr.thiiozz.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ComptabiliteServiceTest {
	@Autowired
    private UserService serviceUser;
	
	@Autowired
    private ComptabiliteService serviceCompta;
	
	User userTest;
	User userTiers;
	
	@Before
	public void setUp(){
		userTest = new User("titi", "titi", true);
		userTiers = new User("toto", "toto", true);
		
		userTest.setTiers(userTiers);
		userTiers.setTiers(userTest);
		
		serviceUser.sauvegarderUtilisateur(userTest);
		serviceUser.sauvegarderUtilisateur(userTiers);
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void leMontantDepensePeutEtreCalculePourUnUtilisateur(){
		float montantUser1 = serviceCompta.getTotalDepensePour(userTest);
		
		assertThat(montantUser1).isEqualTo(0);
		
		userTest.getDepenses().add(new Depense("test", 100f, userTest));
		userTest.getDepenses().add(new Depense("test", 100f, userTest));
		userTest.getDepenses().add(new Depense("test", 100f, userTest));
		serviceUser.sauvegarderUtilisateur(userTest);
		
		montantUser1 = serviceCompta.getTotalDepensePour(userTest);
		assertThat(montantUser1).isEqualTo(300);
	}
	
	@Test
	public void leMontantDepensePeutEtreCalculePourUnUtilisateurEnTenantCompteDesDepensesOffertes(){
		float montantUser1 = serviceCompta.getTotalDepensePour(userTest);
		
		assertThat(montantUser1).isEqualTo(0);
		
		Depense depense = new Depense("test", 100f, userTest);
		depense.setOffert(true);
		userTest.getDepenses().add(depense);
		serviceUser.sauvegarderUtilisateur(userTest);
		
		montantUser1 = serviceCompta.getTotalDepensePour(userTest);
		assertThat(montantUser1).isEqualTo(0);
	}
	
	@Test
	public void leMontantDepensePeutEtreCalculePourLesUtilisateursEnTenantCompteDesDepensesRemboursee(){
		float montantUser1 = serviceCompta.getTotalDepensePour(userTest);
		
		Depense depense = new Depense("test", 100f, userTest);
		depense.setRembourser(true);
		userTest.getDepenses().add(depense);
		serviceUser.sauvegarderUtilisateur(userTest);
		
		montantUser1 = serviceCompta.getTotalDepensePour(userTest);
		assertThat(montantUser1).isEqualTo(0);
	}


}
