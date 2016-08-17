package fr.thiiozz.test.utilisateur;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.thiiozz.dao.RoleDAO;
import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.Authority;
import fr.thiiozz.model.User;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleDAOTest {
	@Autowired
	private RoleDAO repositoryRole;
	
	@Autowired
	private UtilisateurDAO repositoryUtilisateur;
	
	private User userTest;
	
	@Before
	public void setUp(){
		userTest = new User("titi", "titi", true);
		repositoryUtilisateur.save(userTest);
	}
	
	@Test
	public void desRolesPeuventEtrePersistes() throws Exception {
		Set<Authority> roles = new HashSet<Authority>();
		roles.add(new Authority("ADMIN", userTest));
		roles.add(new Authority("USER", userTest));
		repositoryRole.save(roles);
		
		List<Authority> rolesEnBase = (List<Authority>) repositoryRole.findAll();
		
		assertThat(rolesEnBase.size()).isEqualTo(2);
	}
}
