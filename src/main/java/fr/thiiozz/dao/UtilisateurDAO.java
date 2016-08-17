package fr.thiiozz.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import fr.thiiozz.model.User;

@Transactional
public interface UtilisateurDAO extends CrudRepository<User, Long>{
	User findByUsername(String username);
}
