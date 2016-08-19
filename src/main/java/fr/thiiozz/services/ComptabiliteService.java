package fr.thiiozz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.thiiozz.model.Depense;
import fr.thiiozz.model.User;

@Service
public class ComptabiliteService {
	@Autowired
	private UserService serviceUtilisateurs;
	
	@Autowired
	private DepenseService serviceDepenses;

	public float getTotalDepensePour(User userTest) {
		List<Depense> depensesDuts = serviceDepenses.trouverToutesDepensesDut();
		
		float montantTotalDesDepenses = 0;
		
		for(Depense depense : depensesDuts){
			if(depense.getUser().getUsername().equals(userTest.getUsername())){
				montantTotalDesDepenses += depense.getMontant();
			}
		}
		
		return montantTotalDesDepenses;
	}
	
	private String construireChaineDeTotal(String username, float montant){
		return "Total des dépenses pour : " + username + " " + String.valueOf(montant) + "€";
	}
	public String getPremiereChaineTotal() {
		User premierUser = serviceUtilisateurs.getUtilisateurCourant();
		String totalDepensePremierUtilisateur = construireChaineDeTotal(premierUser.getUsername(), getTotalDepensePour(premierUser));
		return totalDepensePremierUtilisateur;
	}

	public String getSecondeChaineTotal() {
		User secondUser = serviceUtilisateurs.getUtilisateurCourant().getTiers();
		String totalDepenseSecondUtilisateur = construireChaineDeTotal(secondUser.getUsername(), getTotalDepensePour(secondUser));
		return totalDepenseSecondUtilisateur;
	}

}
