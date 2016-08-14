package fr.thiiozz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.Utilisateur;

@Service
public class UtilisateurService {
	
	@Autowired
	UtilisateurDAO repository;
	
	public boolean ajouterUnUtilisateur(Utilisateur utilisateur) {
		boolean resultat = true;
		
		try{
			repository.save(utilisateur);
		}catch(Exception ex){
			System.out.println(ex.toString());
			resultat = false;
		}
		
		return resultat;
	}

	public boolean authentifier(String nomUtilisateur) {
		boolean resultat = false;
		
		try{
			resultat = repository.findByNom(nomUtilisateur).getNom().equals(nomUtilisateur);
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		
		return resultat;
	}

}
