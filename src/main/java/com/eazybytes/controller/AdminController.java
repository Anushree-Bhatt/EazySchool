package com.eazybytes.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.FalseCondition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eazybytes.model.Contact;
import com.eazybytes.model.Courses;
import com.eazybytes.model.EazyClass;
import com.eazybytes.model.Person;
import com.eazybytes.repository.CoursesRepository;
import com.eazybytes.repository.EazyClassRepository;
import com.eazybytes.repository.PersonRepository;
import com.eazybytes.service.ContactService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private EazyClassRepository eazyClassRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	private Logger log = Logger.getLogger(AdminController.class.getCanonicalName());
	
	@GetMapping("/displayMessages")
	public ModelAndView displayMessages() {
		List<Contact> l = contactService.findMessagesWithOpenStatus();
		ModelAndView m = new ModelAndView("messages.html");
		m.addObject("contactMsgs", l);
		return m;
	}
	
	@GetMapping("/closeMsg")
	public String closeMsg( @RequestParam int id) {
		contactService.updateMessage(id);
		return "redirect:/admin/displayMessages";
	}
	
	@GetMapping("/displayClasses")
	public String displayClasses(Model model) {
		List<EazyClass> l = eazyClassRepository.findAll();
		model.addAttribute("eazyClass", new EazyClass()); //to add new class
		model.addAttribute("eazyClasses", l); //to display all classes
		return "classes.html";
	}
	
	@GetMapping("/displayStudents")
	public String displayStudents(Model model, @RequestParam int classId) {
		Optional<EazyClass> o = eazyClassRepository.findById(classId);
		EazyClass c = o.get();
		List<Person> list = c.getList_persons();
		list = list.stream().filter(i -> (i.getRole().getRoleName().equals("STUDENT"))).collect(Collectors.toList());
		log.info(""+list);
		model.addAttribute("students", list);
		model.addAttribute("person",new Person());
		model.addAttribute("eazyClass", c);
		return "Students.html";
		
	}
	
	
	@RequestMapping("/displayCourses")
	public String displayCourses(Model model) {
		
		//to show all courses available send List<Courses>
	//	List<Courses> all_courses = coursesRepository.findAll();
		
		//Static sorting -> restricted to sort based on any one column only by hard coding 
		//List<Courses> all_courses = coursesRepository.findAllByOrderByName();
		//List<Courses> all_courses = coursesRepository.findAllByOrderByFeesDesc();
		
		//dynamic sorting -> Use Sort class thats it, and using Dynamic sorting , we sort any number of columns
		List<Courses> all_courses = coursesRepository.findAll(Sort.by("name").descending().and(Sort.by("courseId")));
		model.addAttribute("courses", all_courses);
		
		//to add a new Courses - send new object
		model.addAttribute("course", new Courses());
		
		return "courses_secure.html";
	}
	
	@PostMapping("/addNewClass")
	public String addNewClass(Model model, @Valid @ModelAttribute EazyClass eazyClass, Errors err) {
		if(err.hasErrors()) {
			log.info(err.toString());
			return "classes.html";
		}
		eazyClassRepository.save(eazyClass);
		return "redirect:/admin/displayClasses";
	}
	
	@GetMapping("/deleteClass")
	public String deleteClass(@RequestParam int id) {
		Optional<EazyClass> o =  eazyClassRepository.findById(id);
		EazyClass c =  o.get();
		List<Person> l = null;
		if(o.isPresent() && c!=null && c.getClassId()>0) {
			//find all persons associated to this class
			l = c.getList_persons();
			//set null to classId column of all these persons
			for(Person i:l) {
				i.setEazyClass(null);
				//update it into database
				personRepository.save(i);
			}
			
			//now you can delete class
			eazyClassRepository.delete(c);
		}
		return "redirect:/admin/displayClasses";
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@RequestParam int id, Model model, @ModelAttribute Person person, Errors err) {
		String emessage;
		if(err.hasErrors()) {
			log.info("Errors...");
			return "errorPage.html";
		}
		//Check if person exists
		String email = person.getEmail();
		Person p = personRepository.findByEmail(email);
		if(p==null) {
			model.addAttribute("emessage", "Inalid email!!");
			return "errorPage.html";
		}
		
		//check if Class exists
		Optional<EazyClass> o = eazyClassRepository.findById(id);
		if(o.isEmpty()) {
			model.addAttribute("emessage", "Inalid class!!");
			return "errorPage.html";
		}
		
		EazyClass c = o.get();
		c.add_person(p);
		p.setEazyClass(c);
		
		personRepository.save(p);
		eazyClassRepository.save(c);
		return "redirect:/admin/displayStudents?classId="+c.getClassId();
		
	}
	
	
	@GetMapping("/deleteStudent")
	public String deleteStudent(Model model, @RequestParam int personId) {
		Optional<Person> o = personRepository.findById(personId);
		if(o.isEmpty()) {
			model.addAttribute("emessage", "No Such Person Exists!!");
			return "errorPage.html";
		}
		Person p = o.get();
		EazyClass c= p.getEazyClass();
		if(p.getPersonId()>0) {
			c.delete_person(p);
			eazyClassRepository.save(c);
			p.setEazyClass(null);
			personRepository.save(p);
		}
		
		return "redirect:/admin/displayStudents?classId="+c.getClassId();
	}
	
	@PostMapping("/addNewCourse")
	public String addCourse(@Valid @ModelAttribute Courses course) {
		coursesRepository.save(course);
		return "redirect:/admin/displayCourses"; //"It's /admin/displayCourses" NOT -> "admin/siaplyCourses" which will be considered as "/admin/admin/displayCourses"
	}
	
	@RequestMapping(method = RequestMethod.GET, value  = "/viewCourseStudents")
	public String viewCourseStudents(@RequestParam int id,@RequestParam(required = false)String error ,Model model, HttpSession session) {
		
		//display errors that occurred while adding student to course
		if(error!=null){//there is some value stored in error variable
			model.addAttribute("errorMessage","Invalid student mail id!!");
		}
		//to add new student, send an empty person object
		model.addAttribute("person", new Person());
		
		//to display students, send list of persons who have taken this course
		Optional<Courses> o =  coursesRepository.findById(id);
		Courses c = o.get();
		Set<Person> s =  c.getOur_students();
		model.addAttribute("courseStudents", s);
		
		//to display course details
		session.setAttribute("courses", c);
		model.addAttribute("courses", c);
		
		return "course_students.html";
	}
	
	@PostMapping("/addStudentToCourse")
	public String addStudentToCourse(@ModelAttribute Person person, Model model, HttpSession session) {
		//Fetch and keep the course
		Courses c = (Courses)session.getAttribute("courses");
		
		String email = person.getEmail();
		//Check if any person exists with this email..
		Person p = personRepository.findByEmail(email);
		if(p==null || p.getPersonId()<=0) {
			//invalid email 
			log.info("Returning to students view for course page");
			return "redirect:/admin/viewCourseStudents?id="+c.getCourseId()+"&error="+true;
		}
		
		//if person exists -> add person to course
		p.addCourses(c);
		c.addStudent(p);
		
		personRepository.save(p);
		
		//update session courses
		session.setAttribute("courses", c);
		
		return "redirect:/admin/viewCourseStudents?id="+c.getCourseId();
	}
	
	@GetMapping("/deleteStudentFromCourse")
	public String deleteStudentFromCourse(Model model, HttpSession session, @RequestParam int personId) {
		//check id person id exists
		Optional<Person> o =  personRepository.findById(personId);
		Person p =  o.get();
		
		//Fetch course from session
		Courses c = (Courses)session.getAttribute("courses");
		
		c.removeStudent(p);
		//Firstly remove course from person's course list
		p.removeCourses(c);
		//Secondly remove person from course list
		
		
		log.info("Person's courses are: "+p.getMy_courses());
		log.info("Courses students are "+c.getOur_students());
		
		//save changes in person repository
		personRepository.save(p);
		
		//update course in session
		session.setAttribute("courses", c);
		
		return "redirect:/admin/viewCourseStudents?id="+c.getCourseId();
	}
	
}
