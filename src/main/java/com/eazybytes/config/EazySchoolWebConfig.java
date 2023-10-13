package com.eazybytes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EazySchoolWebConfig implements WebMvcConfigurer{ //WMC
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/courses").setViewName("courses");
		registry.addViewController("/about").setViewName("about");
	}
}