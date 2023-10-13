package com.eazybytes.service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.eazybytes.controller.ContactController;
import java.util.*;
import com.eazybytes.model.Contact;
import com.eazybytes.model.EazySchoolConstants;
import com.eazybytes.model.Holidays;
import com.eazybytes.repository.ContactRepository;
import com.eazybytes.repository.HolidaysRepository;

@Service
@RequestScope
public class ContactService {
	private static final Logger LOGGER = Logger.getLogger(ContactService.class.getCanonicalName());
	
	@Autowired
	private ContactRepository contactRepository;
	
	public ContactService() {
		// TODO Auto-generated constructor stub
	//	LOGGER.info("Creating Contact Service Bean");	
	}
	
	public boolean saveMessageDetails(Contact contact) {
		boolean isSaved = false;
		contact.setStatus(EazySchoolConstants.OPEN);
//		contact.setCreatedAt(LocalDateTime.now());
//		contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
//		int row = contactRepository.saveMessage(contact);
//		if(row==1)
//			isSaved = true;
//		return isSaved;
		
		
		Contact c =  contactRepository.save(contact); // after saving it returns contact object along with Id details
		if(c!=null && c.getContactId()>0) {
			isSaved = true;
		}
		return isSaved;
		
	}



	public List<Contact> findMessagesWithOpenStatus() {
		// TODO Auto-generated method stub
//		//List<Contact> l = contactRepository.findMessagesWithStatus(EazySchoolConstants.OPEN);
//		List<Contact> l = contactRepository.findByStatus(EazySchoolConstants.OPEN);
//		return l;
		
		//using namedQuery
		List<Contact> l  = contactRepository.findOpenMessages(EazySchoolConstants.OPEN);
		return l;
	}


	
	public boolean updateMessage(int contactId) { //Close message
		// TODO Auto-generated method stub
//		boolean flag = false;
//		Optional<Contact> o =  contactRepository.findById(contactId);
//		if(o.get()!=null) {
//			
//		}
//		o.ifPresent(c1 -> {c1.setStatus(EazySchoolConstants.CLOSE); //c1 -> c.get();
////		c1.setUpdatedAt(LocalDateTime.now());
////		c1.setUpdatedBy(updatedBy);
//		});
//		
//		Contact res =  contactRepository.save(o.get());
//		if(res!=null && res.getContactId()>0) {
//			flag = true;
//		}
//		return flag;
		
		//updating using query method
		
		int rows = contactRepository.updateStatusById(EazySchoolConstants.CLOSE, contactId);
		if(rows == 1)
			return true;
		return false;
		
	}



}
