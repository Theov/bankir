package fr.thiiozz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import fr.thiiozz.model.Depense;

@Transactional
public interface DepenseDAO extends CrudRepository<Depense, Long>{

	List<Depense> findByRembourserAndOffert(boolean rembourser, boolean offerte);
}
