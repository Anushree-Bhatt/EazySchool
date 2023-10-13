package com.eazybytes.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorInterceptor {

	public ErrorInterceptor() {
		// TODO Auto-generated constructor stub
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllExceptions(Exception e) {
		ModelAndView m = new ModelAndView();
		m.addObject("emessage", e.getMessage());
		m.setViewName("errorPage.html");
		return m;
	}

}
