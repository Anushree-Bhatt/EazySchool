package com.eazybytes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eazybytes.model.Person;
import com.eazybytes.repository.PersonRepository;

import java.util.*;

@Component("authProvider")
public class EazySchoolUnamePwdAuthProvider implements AuthenticationProvider {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String email = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		Person p =  personRepository.findByEmail(email);
		if(p!=null && p.getPersonId()>0 && passwordEncoder.matches(pwd, p.getPwd())) { //pwd is encoded and checked with getPwd in matches method
			return new UsernamePasswordAuthenticationToken(p.getEmail(), null, associated_role(p.getRole().getRoleName()));
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method 
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public List<GrantedAuthority> associated_role(String role){
		List<GrantedAuthority> l = new ArrayList<>();
		l.add(new SimpleGrantedAuthority("ROLE_"+role));
		return l;
	}

}
