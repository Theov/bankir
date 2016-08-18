package fr.thiiozz.test.utilisateur;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.Authority;
import fr.thiiozz.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@Transactional
public class UtilisateurDAOTest {

	@Autowired
	private UtilisateurDAO repository;

	@Test
	public void unUtilisateurPeutEtrePersisteAvecDesRoles() throws Exception {
		User userTest = new User("titi", "titi", true);
		
		Set<Authority> roles = new HashSet<Authority>();
		roles.add(new Authority("ADMIN", userTest));
		roles.add(new Authority("USER", userTest));
		
		userTest.getRoles().addAll(roles);
		repository.save(userTest);
		
		long id = userTest.getId();
		
		assertThat(repository.exists(id)).isTrue();
		User userEnBase = repository.findOne(id);
		
		assertThat(userEnBase.getUsername()).isEqualTo("titi");
		assertThat(userEnBase.getRoles().size()).isEqualTo(2);
	}
}
