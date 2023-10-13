package com.eazybytes.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.websocket.AuthenticationException;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginAndLogoutController {
	
	private static final Logger logger = Logger.getLogger(LoginAndLogoutController.class.getName());

	public LoginAndLogoutController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(path = "/login", method = {RequestMethod.GET})
	public String login(ModelMap model, @RequestParam(required = false) boolean error, @RequestParam(required = false) boolean logout, @RequestParam(required = false) boolean register) {
		String message = null;
		if(error == true) { //When login is unsuccessful, redirect to login page
			message = "Invalid credentials!!";
			logger.info(message);
		}
		else if(register == true) {
			message = "Registered successfully !! Log in with your credentials now";
			logger.info(message);
		}
		else if(logout == true) { //after logout, you have to go to login page
			message = "Logged out successfully!!!";
			logger.info(message);
		}
		model.put("message", message);
		
		return "login.html";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if(a!=null) {
			new SecurityContextLogoutHandler().logout(request, response, a);
		}
		
		return "redirect:/login?logout=true";
	}
}
