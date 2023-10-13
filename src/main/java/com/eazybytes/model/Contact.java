package com.eazybytes.model;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.eazybytes.controller.ContactController;

//@RequestScope

@Entity
@Table(name = "contact")
//@NamedQuery(name = "Contact.findOpenMessages", query = "select c from Contact c where c.status = ?1")
@NamedNativeQuery(name = "Contact.findOpenMessages", query = "select * from contact c where c.status = ?1", resultClass = Contact.class)
public class Contact extends BaseEntity {
	
	private static final long max = 9999999999L;
	private static final long min = 1000000000L;
	private static final Logger LOGGER = Logger.getLogger(Contact.class.getCanonicalName());
	
	public Contact() {

	}
	
	@NotBlank(message = "Name should not be blank")
	@Pattern(regexp = "[A-Za-z]{5,}", message = "Name is not following pattern")
	private String name;
	
	@NotNull
	@Max(value = max )
	@Min(value = min)
	@Column(name = "mobile_num")
	private long mobileNum;
	
	@NotBlank(message = "Email should not be blank")
	@Email(message = "Must be in email format")
	private String email;
	
	@NotBlank(message = "Subject should not be blank")
	@Size(min = 5, message = "Subject has to be minimum of 5 letters")
	private String subject;
	
	@NotBlank(message = "Message should not be blank")
	@Size(min = 5, message = "Message has to be minimum of 5 letters")
	private String message;
	
	//Primary key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "native") //How the value is generated in DB -> Auto -> 
	@GenericGenerator(name = "native", strategy = "native") // -> Who is the generator of id, strategy = "native" -> In DB, there is a strategy done which auto increments to generate ID
	private int contactId;
	
	private String status;
	
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(long mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Contact [name=" + name + ", mobileNum=" + mobileNum + ", email=" + email + ", subject=" + subject
				+ ", message=" + message + ", contactId=" + contactId + ", status=" + status + ", getCreatedAt()="
				+ getCreatedAt() + ", getCreatedBy()=" + getCreatedBy() + ", getUpdatedAt()=" + getUpdatedAt()
				+ ", getUpdatedBy()=" + getUpdatedBy() + "staus="+getStatus()+"]";
	}
	
}
