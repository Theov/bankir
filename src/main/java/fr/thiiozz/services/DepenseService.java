package fr.thiiozz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.thiiozz.dao.DepenseDAO;
import fr.thiiozz.model.Depense;
import fr.thiiozz.utils.StringHelper;

@Service
public class DepenseService {
	@Autowired
	private DepenseDAO repository; 
	
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
	
	public boolean ajouterUneDepense(Depense depenseAjoute){
		boolean ajoutEffectue = true;
		
		try{
			repository.save(depenseAjoute);
		}catch(Exception ex){
			System.out.println(ex.toString());
			ajoutEffectue = false;
		}
		
		return ajoutEffectue;
	}
}
