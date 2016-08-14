package fr.thiiozz.test.utilisateur;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UtilisateurDAOTest {

	@Autowired
	private UtilisateurDAO repository;

	@Test
	public void unUtilisateurPeutEtrePersisteEtRetrouveParSonNom() throws Exception {
		Utilisateur utilisateurTest = new Utilisateur("MrTest");

		repository.save(utilisateurTest);

		Utilisateur user = repository.findByNom("MrTest");
		assertThat(user.getNom()).isEqualTo("MrTest");
	}
}
