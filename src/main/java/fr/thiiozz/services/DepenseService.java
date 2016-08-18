package fr.thiiozz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.thiiozz.dao.DepenseDAO;
import fr.thiiozz.model.Depense;
import fr.thiiozz.model.User;
import fr.thiiozz.utils.StringHelper;

@Service
public class DepenseService {
	@Autowired
	private DepenseDAO repository; 
	
	@Autowired
	private UserService serviceUtilisateurs;
	
	public List<Depense> getAllDepenses(){
		return (List<Depense>) repository.findAll();
	}
	
	public boolean supprimerUneDepense(String id){
		boolean suppresionEffectue = true;
		
		try{
			repository.delete(StringHelper.convertirChaineVersEntier(id));
		}catch(Exception ex){
			System.out.println(ex.toString());
			suppresionEffectue = false;
		}
		
		return suppresionEffectue;
	}
	
	public boolean supprimerUneDepense(long id) {
		boolean suppresionEffectue = true;
		
		try{
			repository.delete(id);
		}catch(Exception ex){
			System.out.println(ex.toString());
			suppresionEffectue = false;
		}
		
		return suppresionEffectue;
	}
	
	public boolean ajouterUneDepenseSansUtilisateur(Depense depenseAjoute){
		boolean ajoutEffectue = true;
		
		try{
			User proprietaire = serviceUtilisateurs.getUtilisateurCourant();
			Depense depensePersiste = new Depense(depenseAjoute.getLabel(), depenseAjoute.getMontant(), proprietaire);
			repository.save(depensePersiste);
		}catch(Exception ex){
			System.out.println(ex.toString());
			ajoutEffectue = false;
		}
		
		return ajoutEffectue;
	}

	public boolean sauvegarderDepense(Depense depenseSauvegarde) {
		boolean ajoutEffectue = true;
		
		try{
			repository.save(depenseSauvegarde);
		}catch(Exception ex){
			System.out.println(ex.toString());
			ajoutEffectue = false;
		}
		
		return ajoutEffectue;
	}

	public void offrirDepense(Depense depense, boolean estOfferte) {
		depense.setOffert(estOfferte);
		sauvegarderDepense(depense);
	}

	public void rembourserDepense(Depense depenseRembourser) {
		depenseRembourser.setRembourser(true);
		
		//Depense remboursementPremiereMoitie = new Depense();
	}
}
