package com.eazybytes.service;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eazybytes.model.Address;
import com.eazybytes.model.Person;
import com.eazybytes.model.Profile;
import com.eazybytes.model.Role;
import com.eazybytes.repository.AddressRepository;
import com.eazybytes.repository.PersonRepository;
import com.eazybytes.repository.RoleRepository;

@Service
public class PersonService {
	
	Logger log = Logger.getLogger(PersonService.class.getCanonicalName());
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	public PersonService() {
		// TODO Auto-generated constructor stub
	}

	public boolean createNewPerson(@Valid Person person) {
	
		//Before saving the person set the role to him
		Role r =  roleRepository.findByRoleName("STUDENT");
		if(r!=null) {
			person.setRole(r);
			
			person.setPwd(passwordEncoder.encode(person.getPwd()));
			
			Person p =  personRepository.save(person);
			if(p.getPersonId() > 0)
				return true;
		}
		return false;
	}
	
	public Person getPerson(String email) {
		Person p = personRepository.findByEmail(email);
		if(p!=null && p.getPersonId()>0)
			return p;
		else
			return new Person();
	}
	
	public Profile personProfile(Person p) {
		Profile profile = new Profile();
		profile.setEmail(p.getEmail());
		profile.setName(p.getName());
		profile.setMobileNumber(p.getMobileNumber());
		if(p.getAddress()!=null) {
			profile.setAddress1(p.getAddress().getAddress1());
			profile.setAddress2(p.getAddress().getAddress2());
			profile.setCity(p.getAddress().getCity());
			profile.setState(p.getAddress().getState());
			profile.setZipCode(p.getAddress().getZipCode());
		}
		return profile;
		
	}

	public Person updatePersonProfile(Person p, Profile profile) {
		// TODO Auto-generated method stub
		
		log.info(""+p);
		
		Address addr = new Address();
		addr.setAddress1(profile.getAddress1());
		addr.setAddress2(profile.getAddress2());
		addr.setCity(profile.getCity());
		addr.setState(profile.getState());
		addr.setZipCode(profile.getZipCode());
		
		p.setAddress(addr);
		
		Person p1 =  personRepository.save(p);
		log.info(p1+"");
		
		if(p1!=null && p1.getPersonId()>0 && p1.getAddress().getAddressId()>0 )
			return null;
		return p1;
		
	}
	
	
}
