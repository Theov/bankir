package fr.thiiozz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.thiiozz.services.UtilisateurService;

@Controller
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService service;
	
	@RequestMapping("/login")
	public String acceuilLogin(@RequestParam(value="utilisateur", required=false, defaultValue="") String utilisateurVerifie){
		String retour = "login";
		
		if(!utilisateurVerifie.isEmpty() && service.authentifier(utilisateurVerifie)){
			retour = "redirect:depenses?utilisateur=" + utilisateurVerifie;
		}
		
		return retour;
	}
	
}
