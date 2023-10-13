package com.eazybytes.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazybytes.model.Person;
import com.eazybytes.model.Profile;
import com.eazybytes.service.PersonService;


@Controller
public class ProfileController {
	
	Logger log = Logger.getLogger(ProfileController.class.getName());
	
	@Autowired
	PersonService personService;
	
	@RequestMapping("/displayProfile")
	public String displayProfile(ModelMap model, Authentication auth, HttpSession session) {
		//fetch Person
		Person p =  (Person)session.getAttribute("person");
		Profile p1 = personService.personProfile(p);
		
		model.addAttribute("profile", p1);
		
		return "profile.html";
	}
	
	@PostMapping("/updateProfile")
	public String updateProfile(@Valid @ModelAttribute Profile profile, Errors errors, HttpSession session) {
		if(errors.hasErrors()) {
			log.warning("Validation is unsuccessful!!");
			return "profile.html";
		}
		Person p =  personService.updatePersonProfile((Person)session.getAttribute("person"),profile);
		session.setAttribute("profile", p); //updated person details
		return "redirect:/dashboard";
	}
}
