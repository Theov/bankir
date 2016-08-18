package fr.thiiozz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.thiiozz.dao.UtilisateurDAO;
import fr.thiiozz.model.User;

@Service
public class UserService {
	@Autowired
	UtilisateurDAO dao;
	
	public User getUtilisateurCourant(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return dao.findByUsername(auth.getName());
	}

	public void sauvegarderUtilisateur(User userSauvegarder) {
		dao.save(userSauvegarder);
	}

	public User trouverUtilisateurParNom(String username) {
		return dao.findByUsername(username);
	}
	
	public User trouverUtilisateurParId(long id) {
		return dao.findOne(id);
	}

	public Iterable<User> trouverToutUtilisateurs() {
		return dao.findAll();
	}
	
	public boolean utilisateurExiste(long id){
		return dao.exists(id);
	}
	
	public User trouverPremierUtilisateur(){
		User utilisateurRetourne = new User();
		List<User> tousUtilisateurs = (List<User>) trouverToutUtilisateurs();
		return tousUtilisateurs.size() > 0 ? tousUtilisateurs.get(0) : utilisateurRetourne;
	}
}
