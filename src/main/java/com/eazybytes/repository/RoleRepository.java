package com.eazybytes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.model.Person;
import com.eazybytes.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	//Custom method
	Role findByRoleName(String roleName);
}


