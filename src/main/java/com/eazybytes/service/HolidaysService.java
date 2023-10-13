package com.eazybytes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazybytes.model.Holidays;
import com.eazybytes.repository.HolidaysRepository;

@Service
public class HolidaysService {

	
	@Autowired
	private HolidaysRepository holidaysRepository;
	
	public HolidaysService() {
		// TODO Auto-generated constructor stub
	}
	


	public  List<Holidays> fetchHolidays() {
		// TODO Auto-generated method stub
		//List<Holidays> l = holidaysRepository.fetchHolidays();
		Iterable<Holidays> i =  holidaysRepository.findAll();
		List<Holidays>  l = StreamSupport.stream(i.spliterator(), false).collect(Collectors.toList());
		return l;
	}

}
