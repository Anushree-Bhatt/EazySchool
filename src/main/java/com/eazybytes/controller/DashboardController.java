package com.eazybytes.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazybytes.model.Person;
import com.eazybytes.service.PersonService;

@Controller
public class DashboardController {
	
	@Autowired
	PersonService personService;
	
	public DashboardController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/dashboard")
	public String dashboardPage(Authentication auth, ModelMap model, HttpSession session) {
		String username ="";
		
		//In the whole session of dashboard, I want person details to be saved for all pages from here onwards
		Person p = personService.getPerson(auth.getName()); // auth.getName -> email
		//model.addAttribute("person",p);
		session.setAttribute("person", p);
		
		//To display welcome message in dashboard
		model.put("username", p.getName());
		model.put("roles", auth.getAuthorities());
		
		//If the logged in person is student -> we also want to display enrolled class and enrolled courses
		if(p.getRole().getRoleName().equals("STUDENT") && p.getEazyClass()!=null) {
			model.put("enrolledClass", p.getEazyClass().getName());
		}
		
		return "dashboard.html";
	}

}
