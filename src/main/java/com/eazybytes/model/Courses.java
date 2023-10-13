package com.eazybytes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "courses")
public class Courses extends BaseEntity{
	
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int courseId;
	
	@NotNull(message = "Name cannot be empty!")
	private String name;
	
	@NotNull(message = "Fees cannot be for free!")
	private String fees;
	
	//Course- Parent entity, person - Child Entity
	@ManyToMany(mappedBy = "my_courses", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Person> our_students =  new HashSet<>();

	public Courses() {
		// TODO Auto-generated constructor stub
	}

	public int getCourseId() {
		return courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public Set<Person> getOur_students() {
		return our_students;
	}

	public void addStudent(Person p) {
		this.our_students.add(p);
	}
	
	public void removeStudent(Person p) {
		for(Person i:our_students) {
			if(i.getPersonId()==p.getPersonId()) {
				this.our_students.remove(i);
				break;
			}	
		}
	}

	@Override
	public String toString() {
		return "Courses [courseId=" + courseId + ", name=" + name + ", fees=" + fees + ", our_students=" + our_students
				+ "]";
	}
	
	
	
	
	
	
}
