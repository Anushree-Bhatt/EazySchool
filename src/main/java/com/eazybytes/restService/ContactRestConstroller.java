package com.eazybytes.restService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.model.Contact;
import com.eazybytes.repository.ContactRepository;

//@Controller
@RestController
@RequestMapping("/api/contact")
public class ContactRestConstroller {
	
	Logger log = Logger.getLogger(ContactRestConstroller.class.getCanonicalName());
	
	@Autowired
	ContactRepository contactRepository;
	
	@GetMapping("/getOpenMsgs")
//	@ResponseBody //Telling dispacther servlet to not to send any view , you directly send the data from this service as they will have their own view.
	public List<Contact> getOpenMessagesUsingRequestParam(@RequestParam String status){
		List<Contact> l = new ArrayList<>();
		if(status!=null) {
			l = contactRepository.findByStatus(status);
		}
		return l;
	}
	
	@PostMapping("/getOpenMsgs")
//	@ResponseBody
	public List<Contact> getOpenMessagesUsingResponseBody(@RequestBody Contact c){
		List<Contact> l = new ArrayList<>();
		if(c!=null && c.getStatus()!=null) {
			l = contactRepository.findOpenMessages(c.getStatus());
		}
		return l;
	}
	
	@PostMapping("/saveMsg")
	public ResponseEntity<String> saveMessage(@RequestHeader String invocationFrom, @Valid @RequestBody Contact contact){
		
		log.info("Request is sent from "+invocationFrom);
		contactRepository.save(contact);
		return ResponseEntity.accepted().body("Your message is saved successfully!");
		
	}
}
