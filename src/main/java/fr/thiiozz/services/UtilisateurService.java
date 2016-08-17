package fr.thiiozz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.User;

@Service
public class UtilisateurService {
	
	@Autowired
	UtilisateurDAO repository;
	
	public boolean ajouterUnUtilisateur(User utilisateur) {
		boolean resultat = true;
		
		try{
			repository.save(utilisateur);
		}catch(Exception ex){
			System.out.println(ex.toString());
			resultat = false;
		}
		
		return resultat;
	}

}
