package com.eazybytes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.model.EazyClass;

@Repository
public interface EazyClassRepository extends JpaRepository<EazyClass, Integer> {
	
}


