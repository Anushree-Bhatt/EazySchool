
package com.eazybytes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eazybytes.model.Holidays;
import com.eazybytes.service.ContactService;
import com.eazybytes.service.HolidaysService;

@Controller
public class HolidaysController {
	
	@Autowired
	private HolidaysService holidaysService;
	
	public HolidaysController() {
		// TODO Auto-generated constructor stub
		
	}
	
	@GetMapping(value = {"/holidays/{display}","/holidays"})
	public String getHolidays(Model model,@PathVariable(required = false) String display, @RequestParam(required = false, name = "festivals", defaultValue = "true")boolean is_festivals, @RequestParam(required = false, name = "federals",defaultValue = "true")boolean is_federals) {
		
//		PathVariable
		if(display==null || display.equals("all")) {
			model.addAttribute("is_festivals", true);
			model.addAttribute("is_federals", true);
		}
		else if(display!=null && display.equals("federal"))
			model.addAttribute("is_federals", true);
		else if(display!=null && display.equals("festival"))
			model.addAttribute("is_festivals", true);

		
//		RequestParam -> Query params
//		model.addAttribute("is_festivals", is_festivals);
//		model.addAttribute("is_federals", is_federals);
		
//		List<Holidays> l = new ArrayList<>();
//		l.add(new Holidays("Jan 1", "New Year", Holidays.Type.FESTIVAL));
//		l.add(new Holidays("Oct 31", "Halloween", Holidays.Type.FESTIVAL));
//		l.add(new Holidays("Nov 24", "Thanks giving day", Holidays.Type.FESTIVAL));
//		l.add(new Holidays("Dec 25", "Christmas", Holidays.Type.FESTIVAL));
//		l.add(new Holidays("Jan 17", "Martin Luther ling Jr. day", Holidays.Type.FEDERAL));
//		l.add(new Holidays("July 4", "Independance day", Holidays.Type.FEDERAL));
//		l.add(new Holidays("Sep 5", "Labor day", Holidays.Type.FEDERAL));
//		l.add(new Holidays("Nov 11", "Veterans day", Holidays.Type.FEDERAL));
//		
//		List<Holidays> festivals = null;
//		List<Holidays> federals = null;
//		
//		festivals =  l.stream().filter(holiday -> holiday.getType().toString().equals("FESTIVAL")).collect(Collectors.toList());
//		federals = l.stream().filter(holiday -> holiday.getType().toString().equals("FEDERAL")).collect(Collectors.toList());
//		
//		System.out.println(festivals);
//		System.out.println(federals);
//		
//		model.addAttribute("festivals", festivals);
//		model.addAttribute("federals", federals);
		
		List<Holidays> l = holidaysService.fetchHolidays();
		List<Holidays> festivals = l.stream().filter(i -> i.getType().toString().equals(Holidays.Type.FESTIVAL.toString())).collect(Collectors.toList());
		List<Holidays> federals = l.stream().filter(holiday -> holiday.getType().toString().equals("FEDERAL")).collect(Collectors.toList());
		model.addAttribute("festivals", festivals);
		model.addAttribute("federals", federals);
		return "holidays.html";
		
	}

}
