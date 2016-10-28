package myDentist.web.controller;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myDentist.model.Doctor;
import myDentist.model.User;
import myDentist.model.dao.appointmentsDao;
import myDentist.model.dao.doctorDao;
import myDentist.model.dao.userDao;

@Controller
public class doctorController {

	@Autowired
	private userDao userDao;
	
	@Autowired
	private doctorDao doctorDao;
	
	@Autowired
	private appointmentsDao appointmentsDao;
	
	@RequestMapping(value="/users/profile.html",method=RequestMethod.GET)
	public String doctorHome(ModelMap models)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user= userDao.getUserByUsername(currentPrincipalName);
		models.put("userid",user.getUserId());
		models.put("appointments",appointmentsDao.getAppointments());
		List<Doctor> d=doctorDao.getDoctorbyUserId(user.getUserId());
		models.put("doctorid", " ");
		
		//System.out.println("mmm"+);
		System.out.println("user 1"+currentPrincipalName);
		return "/users/profile";
	}
	
	@RequestMapping(value="/users/profile.html",method=RequestMethod.POST)
	public String doctorHome(@RequestParam Integer userid)
	{
		System.out.println("user 2"+userid);
		return "redirect:/users/profile.html?userid="+userid;
	}
	
	@RequestMapping(value="/users/doctorProfile.html",method=RequestMethod.GET)
	public String doctorProfile(ModelMap models,@RequestParam Integer userid)
	{
		System.out.println("user 3"+userid);
		models.put("userid", userid);
		models.put("users", userDao.getUsers());
		models.put("doctors", doctorDao.getDoctors());
		return "/users/doctorProfile";
	}
	
	@RequestMapping(value="/users/doctorProfile.html",method=RequestMethod.POST)
	public String doctorProfile(@ModelAttribute ("doctors") Doctor doctor,BindingResult result,@RequestParam Integer userid)
	{
		User us=userDao.getUser(userid);
		doctor.setUserId(us);
		System.out.println(doctor.getDesignation());
		doctorDao.saveDoctor(doctor);
		return "redirect:/users/doctorProfile.html?userid="+userid;
	}
	
}