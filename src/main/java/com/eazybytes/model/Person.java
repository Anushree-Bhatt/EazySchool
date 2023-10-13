package com.eazybytes.model;

import org.hibernate.annotations.GenericGenerator;

import com.eazybytes.annotations.FieldsValueMatch;
import com.eazybytes.annotations.PasswordValidator;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldsValueMatch.List(values = {
	@FieldsValueMatch(
		field = "email",
		fieldMatch = "confirmEmail",
		message = "Emails do not match"	
	),
	@FieldsValueMatch(
		field = "pwd",
		fieldMatch = "confirmPwd",
		message = "Passwords do not match"
	)
})
@Entity
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native") //Auto increment
    @GenericGenerator(name = "native",strategy = "native")
    private int personId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Column(name = "mobile_num")
    private String mobileNumber;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @NotBlank(message="Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address" )
    @Transient
    private String confirmEmail;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Size(min=5, message="Confirm Password must be at least 5 characters long")
    @Transient
    private String confirmPwd;
    
    
    //define relationship - step 1
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
  //define link - step 2
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = true)
    private Address address;
    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false) //Person must have one role, so nullable = false
    private Role role;
    
    @ManyToOne(targetEntity = EazyClass.class)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = true)
    private EazyClass eazyClass;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_courses", joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "personId", nullable = false)}, 
    inverseJoinColumns = {@JoinColumn(name="course_id", referencedColumnName = "course_id", nullable = false)} )
    private Set<Courses> my_courses = new HashSet<>();
    
    
    
    public Set<Courses> getMy_courses() {
		return my_courses;
	}

	public void addCourses(Courses c) {
		this.my_courses.add(c);
	}
	
	public void removeCourses(Courses c) { //Be careful here, bcz what you get is Different Course object , and your list contains different object - they are not refreencing to same object to do : this.my_courses.remove(Object) directly
		//You can also use hashcode inside equals method when comparing 2 objects bcz it helps Java to identify the equality of 2 different location objects but with same properties
		System.out.println("Removing teh course");
		for(Courses i: my_courses) {
			if(i.getCourseId()==c.getCourseId()) {
				this.my_courses.remove(i);
				break;
			}
		}
	}

	public EazyClass getEazyClass() {
		return eazyClass;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}


	public Person() {
		// TODO Auto-generated constructor stub
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

	public void setEazyClass(EazyClass eazyClass) {
		this.eazyClass = eazyClass;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", mobileNumber=" + mobileNumber + ", email=" + email
				+ ", confirmEmail=" + confirmEmail + ", pwd=" + pwd + ", confirmPwd=" + confirmPwd + ", address="
				+ address + ", role=" + role + "]";
	}
	
	
    
    

}