package fr.thiiozz.test.utilisateur;

import static org.assertj.core.api.Assertions.*;

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
	public void unUtilisateurPeutEtrePersisteAvecDesRolesEtUnAssocie() throws Exception {
		User userTest1 = new User("titi", "titi", true);
		User userTest2 = new User("toto", "toto", true);
		
		userTest1.getRoles().add(new Authority("ADMIN", userTest1));
		userTest1.getRoles().add(new Authority("USER", userTest2));
		
		userTest1.setTiers(userTest2);
		userTest2.setTiers(userTest1);
		
		repository.save(userTest1);
		repository.save(userTest2);
		
		
		long id = userTest1.getId();
		
		assertThat(repository.exists(id)).isTrue();
		User userEnBase = repository.findOne(id);
		
		assertThat(userEnBase.getUsername()).isEqualTo("titi");
		assertThat(userEnBase.getRoles().size()).isEqualTo(2);
		assertThat(userEnBase.getTiers().getUsername()).isEqualTo("toto");
	}
}
