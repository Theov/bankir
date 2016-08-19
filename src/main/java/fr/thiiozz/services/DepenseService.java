package fr.thiiozz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.thiiozz.dao.DepenseDAO;
import fr.thiiozz.model.Depense;
import fr.thiiozz.model.DepensePartiel;
import fr.thiiozz.model.User;
import fr.thiiozz.utils.StringHelper;

@Service
public class DepenseService {
	@Autowired
	private DepenseDAO repository; 
	
	@Autowired
	private UserService serviceUtilisateurs;
	
	public List<Depense> getAllDepenses(){
		return (List<Depense>) repository.findAll();
	}
	
	public boolean supprimerUneDepense(String id){
		boolean suppresionEffectue = true;
		
		try{
			supprimerUneDepense(trouverDepenseParId(id));
		}catch(Exception ex){
			System.out.println(ex.toString());
			suppresionEffectue = false;
		}
		
		return suppresionEffectue;
	}
	
	public Depense trouverDepenseParId(String id){
		long idEntier = StringHelper.convertirChaineVersEntier(id);
		return repository.exists(idEntier) ? repository.findOne(idEntier) : new Depense();
	}
	
	public boolean supprimerUneDepense(Depense depenseSupprime) {
		boolean suppresionEffectue = true;
		
		try{
			repository.delete(depenseSupprime);
		}catch(Exception ex){
			System.out.println(ex.toString());
			suppresionEffectue = false;
		}
		
		return suppresionEffectue;
	}
	
	public boolean ajouterUneDepenseSansUtilisateur(Depense depenseAjoute){
		boolean ajoutEffectue = true;
		
		try{
			User proprietaire = serviceUtilisateurs.getUtilisateurCourant();
			Depense depensePersiste = new Depense(depenseAjoute.getLabel(), depenseAjoute.getMontant(), proprietaire);
			repository.save(depensePersiste);
		}catch(Exception ex){
			System.out.println(ex.toString());
			ajoutEffectue = false;
		}
		
		return ajoutEffectue;
	}

	public boolean sauvegarderDepense(Depense depenseSauvegarde) {
		boolean ajoutEffectue = true;
		
		try{
			repository.save(depenseSauvegarde);
		}catch(Exception ex){
			System.out.println(ex.toString());
			ajoutEffectue = false;
		}
		
		return ajoutEffectue;
	}

	public void offrirDepense(String id) {
		Depense depense = trouverDepenseParId(id);
		depense.setOffert(!depense.getOffert());
		sauvegarderDepense(depense);
	}
	
	public void rembourserDepense(String id) {
		rembourserDepense(trouverDepenseParId(id));
	}
	
	public void rembourserDepense(Depense depenseRembourser) {
		if(!depenseRembourser.getRembourser()){
			depenseRembourser.setRembourser(true);
			depenseRembourser.setLabel(depenseRembourser.getLabel());
			depenseRembourser.setOffert(false);
			
			String labelRemboursement = depenseRembourser.getLabel() + " - remboursement";
			float montantRemboursement = depenseRembourser.getMontant() / 2;
			
			Depense remboursementPremiereMoitie = new Depense(labelRemboursement,  montantRemboursement, depenseRembourser.getUser());
			Depense remboursementDeuxiemeMoitie = new Depense(labelRemboursement,  montantRemboursement, depenseRembourser.getUser().getTiers());
			
			sauvegarderDepense(depenseRembourser);
			sauvegarderDepense(remboursementPremiereMoitie);
			sauvegarderDepense(remboursementDeuxiemeMoitie);
		}
	}

	public void modifierDepense(DepensePartiel depensePartiel) {
		Depense depenseCorrespondante = repository.findOne(depensePartiel.getId());	
		depenseCorrespondante.setLabel(depensePartiel.getLabel());
		depenseCorrespondante.setMontant(depensePartiel.getMontant());
		sauvegarderDepense(depenseCorrespondante);
	}
	
	public List<Depense> trouverToutesDepensesDut(){
		List<Depense> depensesDut = new ArrayList<Depense>();
		depensesDut = repository.findByRembourserAndOffert(false, false);
		return depensesDut;
	}
}
