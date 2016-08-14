package fr.thiiozz.test.depenses;



import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.thiiozz.dao.DepenseDAO;
import fr.thiiozz.model.Depense;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepensesDAOTest {
    @Autowired
    private DepenseDAO repository;

    @Test
    public void testExample() throws Exception {
       Depense depenseTest = new Depense("Libelle", "TestUser", 100.5f);
       
       repository.save(depenseTest);
       
        Depense user = this.repository.findByProprietaire("TestUser");
        assertThat(user.getProprietaire()).isEqualTo("TestUser");
        assertThat(user.getLabel()).isEqualTo("Libelle");
        assertThat(user.getMontant()).isEqualTo(100.5f);
    }
}
