package com.eazybytes.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eazybytes.model.Contact;
import com.eazybytes.model.EazySchoolConstants;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer>{
	List<Contact> findByStatus(String status); //defining derived named query methods
	
	//Using Query methods
	@org.springframework.transaction.annotation.Transactional
	@Modifying
	@Query("UPDATE Contact c SET c.status = :status WHERE c.contactId = :id")
	int updateStatusById(String status, int id);
	
	//Using native query
	List<Contact> findOpenMessages(String status);

}
//	private final JdbcTemplate jdbcTemplate;
//	
//	@Autowired
//	public ContactRepository(JdbcTemplate jdbcTemplate) {
//		// TODO Auto-generated constructor stub
//		this.jdbcTemplate = jdbcTemplate;
//	}
//	
//	public int saveMessage(Contact contact) {
//		String sql = "insert into contact(name,mobileNum,email,subject,message,status,createdAt,createdBy) values(?,?,?,?,?,?,?,?);";
//		int count = jdbcTemplate.update(sql, new Object[] {contact.getName(), contact.getMobileNum(), contact.getEmail(), contact.getSubject(), contact.getMessage(), contact.getStatus(), contact.getCreatedAt(), contact.getCreatedBy()});
//		return count;
//	}
//
//	public List<Contact> findMessagesWithStatus(String open) {
//		// TODO Auto-generated method stub
//		List<Contact> l = null;
//		try {
//			String sql = "select * from contact where status = ?";
//			 l = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class),new Object[] {open});
//		}catch (DataAccessException e) {
//			// TODO: handle exception
//			System.out.println(e.getLocalizedMessage());
//		}
//		return l;
//		
//	}
//
//	public void closeMessage(int contactId) {
//		// TODO Auto-generated method stub
//		
//		String sql = "update contact set status = ? where contact_id = ?";
//		jdbcTemplate.update(sql,new Object[] {EazySchoolConstants.CLOSE,contactId});
//		
//	}
//	
//	



