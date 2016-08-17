package fr.thiiozz.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import fr.thiiozz.model.Authority;

@Transactional
public interface RoleDAO extends CrudRepository<Authority, String>{
}
