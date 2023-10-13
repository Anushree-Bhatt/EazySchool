package com.eazybytes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	public HomeController() {
		// TODO Auto-generated constructor stub
		
	}
	
	@RequestMapping(value = {"/home", "", "/"}, method = RequestMethod.GET) //We can map multiple paths to the same method
	public String getHomePage(ModelMap map) {
		map.put("username", "anu");
		return "home.html";
	}
	
//	@RequestMapping(value = {"/courses"}, method = RequestMethod.GET)
//	public String getCoursesPage(ModelMap map) {
//		map.put("username", "anu");
//		return "courses.html";
//	}
//	Courses, About -> addView
	
	@RequestMapping(method = RequestMethod.GET, path = "/dummy")
	public String dummy(ModelMap model) {
		model.put("user", "Anuuuuuuu");
		return "dummy.html";
	}

}
