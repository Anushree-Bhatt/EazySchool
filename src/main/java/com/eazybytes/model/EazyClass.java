package com.eazybytes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "class")
public class EazyClass extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "class_id")
	private int classId;
	
	@NotBlank(message="Name must not be blank")
	@Size(min=3, message = "Name must atleast 3 letters long!")
	private String name ;
	
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Person.class, mappedBy = "eazyClass")
	private List<Person> list_persons;

	public EazyClass() {
		super();
	}

	public int getClassId() {
		return classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public List<Person> getList_persons() {
		return list_persons;
	}

	public void add_person(Person person) {
		this.list_persons.add(person);
	}
	
	public void delete_person(Person person) {
		this.list_persons.remove(person);
	}

	@Override
	public String toString() {
		return "EazyClass [classId=" + classId + ", name=" + name + "]";
	}
	
	
	
	
}
