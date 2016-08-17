package fr.thiiozz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.thiiozz.services.UtilisateurService;

@Controller
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String acceuilLogin(){
		return "login";
	}
}
