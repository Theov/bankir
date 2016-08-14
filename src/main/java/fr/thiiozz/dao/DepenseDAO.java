package fr.thiiozz.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import fr.thiiozz.model.Depense;

@Transactional
public interface DepenseDAO extends CrudRepository<Depense, Long>{
	Depense findByProprietaire(String proprietaire);
}
