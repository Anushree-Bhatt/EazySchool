package com.eazybytes.controller;


import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eazybytes.model.Contact;
import com.eazybytes.service.ContactService;


@Controller
//@Slf4j - creates logger - lombok annotation
public class ContactController {
	private static final Logger LOGGER = Logger.getLogger(ContactController.class.getCanonicalName());
	
	//@Autowired
	private ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService) {
		// TODO Auto-generated constructor stub
		this.contactService = contactService;
		
	}
	
	@RequestMapping(value = {"/contact"}, method = RequestMethod.GET) //We can map multiple paths to the same method
	public String displayContactPage(ModelMap map) {
		map.addAttribute("contact", new Contact());
		return "contact.html";
	}
	
	@PostMapping(value = "/saveMsg")
	public String saveMessage(@Valid @ModelAttribute Contact contact, Errors error) {
		if(error.hasErrors()) {
			LOGGER.warning("Contact form validation failed"+error.toString());
			return "contact.html";
		}
		contactService.saveMessageDetails(contact);
		return "redirect:/contact";
	}
	
	
}
