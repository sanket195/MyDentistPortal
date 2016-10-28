package myDentist.web.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import myDentist.model.Appointments;
import myDentist.model.Patient;
import myDentist.model.User;
import myDentist.model.dao.appointmentsDao;
import myDentist.model.dao.doctorDao;
import myDentist.model.dao.patientDao;
import myDentist.model.dao.userDao;
import myDentist.model.dao.jpa.appointmentsDaoImpl;

@Controller
public class UserController {
	
	@Autowired
	private userDao userDao;
	
	@Autowired
	private appointmentsDao appointmentsDao;
	
	@Autowired
	private patientDao patientDao;
	
	
	@RequestMapping("/display.html")
	public String displayUser(ModelMap models)
	{
		
		models.put("users", userDao.getUsers());
		return "display";
	}
	
	
	
	@RequestMapping("/adminHome.html")
	public String adminHome(ModelMap models)
	{
		models.put("appointments", appointmentsDao.getAppointments());
		return "adminHome";
	}
	
	
	
	@RequestMapping(value="/PatientRegistration.html", method=RequestMethod.GET)
	public String PatientRegistration(ModelMap models)
	{	
		models.put("user", new User());
		return "PatientRegistration";
	}
	
	@RequestMapping(value="/PatientRegistration.html", method=RequestMethod.POST)
	public String PatientRegistration(@ModelAttribute ("User") User user, BindingResult result)
	{	
		user.setUserType("patient");
		// save user to database
		user=userDao.saveUser(user);
		System.out.println("Data saved in db :"+user.getUsername());
		//redirect to display page
		return "redirect:loginPage.html";
	}
	
	@RequestMapping(value="/doctorRegistration.html", method=RequestMethod.GET)
	public String doctorRegistration(ModelMap models)
	{	
		models.put("user", new User());
		return "doctorRegistration";
	}
	
	@RequestMapping(value="/doctorRegistration.html", method=RequestMethod.POST)
	public String doctorRegistration(@ModelAttribute ("User") User user, BindingResult result)
	{	
		user.setUserType("doctor");
		// save user to database
		user=userDao.saveUser(user);
		System.out.println("Data saved in db :"+user.getUsername());
		//redirect to display page
		return "redirect:doctorHome.html";
	}
	
	
	
}
