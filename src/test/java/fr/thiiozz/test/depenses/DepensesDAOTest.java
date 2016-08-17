package fr.thiiozz.test.depenses;



import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.thiiozz.dao.DepenseDAO;
import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.Depense;
import fr.thiiozz.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepensesDAOTest {
    @Autowired
    private DepenseDAO repository;
    
    @Autowired
    private UtilisateurDAO repositoryUser;
    
    private User user;
    
    @Before
    public void setUp(){
    	user = new User("titi", "titi", true);
    	repositoryUser.save(user);
    }
    
    @Test
    public void desDepensesPeuventEtreAjoutees() throws Exception {
    	Set<Depense> depenses = new HashSet<Depense>();
    	depenses.add(new Depense("Libelle", 100.5f, user));
    	depenses.add(new Depense("Libelle", 100.5f, user, false, false));
    	depenses.add(new Depense("Libelle", 100.5f, user));
    	depenses.add(new Depense("Libelle", 100.5f, user));
    	depenses.add(new Depense("Libelle", 100.5f, user));
        repository.save(depenses);
        
        user.getDepenses().addAll(depenses);
        repositoryUser.save(user);
        
        
        List<Depense> depensesEnBases = (List<Depense>) repository.findAll();
        Depense depensesUnitaire = depensesEnBases.get(0);
        User utilisateurProprietaire = repositoryUser.findByUsername("titi");
        
        assertThat(depensesUnitaire.getLabel()).isEqualTo("Libelle");
        assertThat(depensesUnitaire.getMontant()).isEqualTo(100.5f);
        assertThat(depensesUnitaire.getOffert()).isEqualTo(false);
        assertThat(depensesUnitaire.getRembourser()).isEqualTo(false);
        assertThat(depensesUnitaire.getDateCreation()).isBefore(new Date());
        
        assertThat(depensesUnitaire.getUser().getUsername()).isEqualTo("titi");
        assertThat(utilisateurProprietaire.getDepenses().size()).isEqualTo(5);
    }
}
