package com.eazybytes.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eazybytes.model.Contact;
import com.eazybytes.model.EazySchoolConstants;
import com.eazybytes.model.Holidays;

@Repository
public interface HolidaysRepository extends CrudRepository<Holidays, String> {
	
}
//	private final JdbcTemplate jdbcTemplate;
//	
//	Logger log = Logger.getLogger(HolidaysRepository.class.getCanonicalName());
//	
//	@Autowired
//	public HolidaysRepository(JdbcTemplate jdbcTemplate) {
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
//			log.info(e.getLocalizedMessage());
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
////	class HolidayRowMapper implements RowMapper<Holidays>{
////		@Override
////		public Holidays mapRow(ResultSet rs, int rowNum) throws SQLException {
////			// TODO Auto-generated method stub
////			Holidays h = new Holidays();
////			h.set rs.getString("day")
////			return null;
////		}
////	}
//
//	public List<Holidays> fetchHolidays() {
//		// TODO Auto-generated method stub
//		List<Holidays> l = null;
//		try {
//			String sql = "select * from holidays;";
//			l =  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Holidays.class));
//			log.info(l+"");
//		}catch (DataAccessException e) {
//			// TODO: handle exception
//			log.info(e.getLocalizedMessage());
//		}
//		return l;
//		
//	}
	

