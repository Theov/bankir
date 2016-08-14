package fr.thiiozz.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import fr.thiiozz.model.Utilisateur;

@Transactional
public interface UtilisateurDAO extends CrudRepository<Utilisateur, Long>{
	Utilisateur findByNom(String nom);
}
