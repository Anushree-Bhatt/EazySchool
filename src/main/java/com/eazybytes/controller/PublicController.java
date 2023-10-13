package com.eazybytes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eazybytes.model.Person;
import com.eazybytes.service.PersonService;

@Controller
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	PersonService personService;
	
	public PublicController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String displayRegisterPage(Model m) {
		m.addAttribute("person", new Person());
		return "register.html";
	}
	
	@PostMapping("/register")
	public String processRegisterPage(@Valid @ModelAttribute Person person, Errors error) {
		if(error.hasErrors())
			return "register.html";
		boolean isSaved = personService.createNewPerson(person);
		if(isSaved==true)
			return "redirect:/login?register=true";
		return "register.html";
	}
	

	
	

}
