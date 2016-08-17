package fr.thiiozz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
  @RequestMapping(value="/")
  public String index() {
      return "redirect:depenses";
  }
  
  @RequestMapping(value="/home")
  public String home() {
      return "redirect:depenses";
  }
}
