package fr.thiiozz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.thiiozz.constants.DepenseConstants;
import fr.thiiozz.model.Depense;
import fr.thiiozz.services.DepenseService;
import fr.thiiozz.constants.GeneralConstants;

@Controller
public class DepenseController {
	
	@Autowired
	private DepenseService service;
	
	@RequestMapping(value=DepenseConstants.routeAfficher)
	public String afficherToutesLesDepenses(@RequestParam(value=DepenseConstants.parametreAction, required=true, defaultValue=DepenseConstants.actionAfficher) String action, Model model){
		model.addAttribute(GeneralConstants.messageModelAttribute, DepenseConstants.getMessage(action));
		model.addAttribute(DepenseConstants.toutesLesDepensesModelAttribut, service.getAllDepenses());
		
		return DepenseConstants.templateDepenses;
	}
	
	@RequestMapping(value=DepenseConstants.routeSupprimer)
	public String supprimerUneDepense(@RequestParam(value=DepenseConstants.parametreId, required=true, defaultValue="0") String id){
		String resultatDeSortie = service.supprimerUneDepense(id) ? DepenseConstants.actionSupprimerOk : DepenseConstants.actionSupprimerKo;
		return GeneralConstants.springRedirectionString + DepenseConstants.routeAfficher + DepenseConstants.parametreActionDecore + resultatDeSortie;
	}
	
	@RequestMapping(value=DepenseConstants.routeAjouter, method=RequestMethod.POST)
	public String ajouterUneDepense(Depense depenseAjoute){
		String resultatDeSortie;
		resultatDeSortie = service.ajouterUneDepenseSansUtilisateur(depenseAjoute) ? DepenseConstants.actionAjouterOk : DepenseConstants.actionAjouterKo;
		return GeneralConstants.springRedirectionString + DepenseConstants.routeAfficher + DepenseConstants.parametreActionDecore + resultatDeSortie;
	}
}
