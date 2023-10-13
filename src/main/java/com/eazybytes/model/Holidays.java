package com.eazybytes.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holidays")
public class Holidays extends BaseEntity{
	
	@Id //Compulsorily to have to define primary id for a table when you are using Spring data JPA
	private String day;
	private String reason;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	public enum Type{
		FESTIVAL, FEDERAL;
	}
	
	public Holidays() {
		
	}
	
	public Holidays(String day, String reason, Type type) {
		this.day = day;
		this.reason = reason;
		this.type = type;
	}
	
	

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Holidays [day=" + day + ", reason=" + reason + ", type=" + type + "]";
	}

}
