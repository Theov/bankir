package fr.thiiozz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.thiiozz.constants.DepenseConstants;
import fr.thiiozz.model.Depense;
import fr.thiiozz.model.DepensePartiel;
import fr.thiiozz.services.ComptabiliteService;
import fr.thiiozz.services.DepenseService;
import fr.thiiozz.constants.GeneralConstants;

@Controller
public class DepenseController {
	
	@Autowired
	private DepenseService serviceDepenses;
	
	@Autowired
	private ComptabiliteService serviceComptabilite;
	
	@RequestMapping(value=DepenseConstants.routeAfficher)
	public String afficherToutesLesDepenses(@RequestParam(value=DepenseConstants.parametreAction, required=true, defaultValue=DepenseConstants.actionAfficher) String action, Model model){
		
		//Model Depenses
		model.addAttribute(GeneralConstants.messageModelAttribute, DepenseConstants.getMessage(action));
		model.addAttribute(DepenseConstants.toutesLesDepensesModelAttribut, serviceDepenses.getAllDepenses());
		
		//Model Compta
		model.addAttribute("total_1", serviceComptabilite.getPremiereChaineTotal());
		model.addAttribute("total_2", serviceComptabilite.getSecondeChaineTotal());
		model.addAttribute("dette", serviceComptabilite.getChaineRedevabilite());
		
		return DepenseConstants.templateDepenses;
	}
	
	@RequestMapping(value=DepenseConstants.routeSupprimer)
	public String supprimerUneDepense(@RequestParam(value=DepenseConstants.parametreId, required=true, defaultValue="0") String id){
		String resultatDeSortie = serviceDepenses.supprimerUneDepense(id) ? DepenseConstants.actionSupprimerOk : DepenseConstants.actionSupprimerKo;
		return GeneralConstants.springRedirectionString + DepenseConstants.routeAfficher + DepenseConstants.parametreActionDecore + resultatDeSortie;
	}
	
	@RequestMapping(value=DepenseConstants.routeAjouter, method=RequestMethod.POST)
	public String ajouterUneDepense(@Valid Depense depenseAjoute, BindingResult bindingResult){
		String resultatDeSortie = DepenseConstants.actionAjouterErreurFormulaire;
		
		if(!bindingResult.hasErrors()){
			resultatDeSortie = serviceDepenses.ajouterUneDepenseSansUtilisateur(depenseAjoute) ? DepenseConstants.actionAjouterOk : DepenseConstants.actionAjouterKo;
		}
		
		return GeneralConstants.springRedirectionString + DepenseConstants.routeAfficher + DepenseConstants.parametreActionDecore + resultatDeSortie;
	}
	
	@RequestMapping(value="/depenses/form", method=RequestMethod.GET)
	public String proposerModificationDepense(@RequestParam(value=DepenseConstants.parametreId, required=true, defaultValue="0") String id, Model model){
		model.addAttribute("depenseModifier", serviceDepenses.trouverDepenseParId(id));
		return "formdepense";
	}
	
	@RequestMapping(value="/depenses/form", method=RequestMethod.POST)
	public String modifierUneDepense(DepensePartiel Depense){
		serviceDepenses.modifierDepense(Depense);
		return GeneralConstants.springRedirectionString + DepenseConstants.routeAfficher + DepenseConstants.parametreActionDecore + DepenseConstants.actionModifier;
	}
	
	@RequestMapping(value="/depenses/offrir", method=RequestMethod.GET)
	public String offirUneDepense(@RequestParam(value=DepenseConstants.parametreId, required=true, defaultValue="0") String id){
		serviceDepenses.offrirDepense(id);
		return GeneralConstants.springRedirectionString + DepenseConstants.routeAfficher + DepenseConstants.parametreActionDecore + DepenseConstants.actionOffre;
	}
	
	@RequestMapping(value="/depenses/rembourser", method=RequestMethod.GET)
	public String rembourserUneDepense(@RequestParam(value=DepenseConstants.parametreId, required=true, defaultValue="0") String id){
		serviceDepenses.rembourserDepense(id);
		return GeneralConstants.springRedirectionString + DepenseConstants.routeAfficher + DepenseConstants.parametreActionDecore + DepenseConstants.actionRembourse;
	}
}
