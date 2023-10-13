package com.eazybytes.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazybytes.model.Courses;
import com.eazybytes.model.Person;

@Controller
@RequestMapping("student")
public class StudentController {

	@GetMapping("/displayCourses")
	public String displayCourses(Model model, HttpSession session) {
		
		//retrieve who the person is 
		Person p = (Person)session.getAttribute("person");
		
		//retrieve courses
		model.addAttribute("person", p);
		
		return "courses_enrolled.html";
		
	}
}
