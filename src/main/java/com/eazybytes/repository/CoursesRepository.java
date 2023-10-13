package com.eazybytes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.model.Courses;
import com.eazybytes.model.Person;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> { //JpaRepository internally extends PagingAndSortingRepository anyhow

	//static sorting - desc
	List<Courses> findAllByOrderByFeesDesc();
	
	//static sorting  - asc
	List<Courses> findAllByOrderByName();
	
}


