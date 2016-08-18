package fr.thiiozz.test.utilisateur;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.thiiozz.model.User;
import fr.thiiozz.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ServiceUtilisateurTest {
	
	@Autowired
    private UserService service;
	
	@Test
	public void unUntilisateurPeutEtreAjoute() throws Exception{
		User user = new User("titi", "titi", true);
		service.sauvegarderUtilisateur(user);
		List<User> usersTrouves = (List<User>) service.trouverToutUtilisateurs();
		assertThat(usersTrouves).isNotNull();
		assertThat(usersTrouves.size()).isEqualTo(1);
	}
}
