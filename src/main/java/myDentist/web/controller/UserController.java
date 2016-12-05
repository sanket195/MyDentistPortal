package myDentist.web.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.internal.util.xml.ErrorLogger;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import myDentist.model.Appointments;
import myDentist.model.Doctor;
import myDentist.model.MakeAvailability;
import myDentist.model.Patient;
import myDentist.model.User;
import myDentist.model.dao.MakeAvailabilityDao;
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
	private doctorDao doctorDao;
	
	@Autowired
	private MakeAvailabilityDao makeAvailabilityDao;
	
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
	public ModelAndView PatientRegistration(ModelMap models)
	{	
		models.put("user", new User());
		return new ModelAndView("PatientRegistration");
	}
	
	@RequestMapping(value="/PatientRegistrationAJAX.html", method=RequestMethod.GET)
	@ResponseBody
	public String Patientajax(ModelMap models,@RequestParam String userEmail)
	{	
		//for email
		Boolean check=true;
		List<User> userCheck=userDao.getUsers();
		for (User u2 : userCheck) {
			if(u2.getUserEmail().equals(userEmail))
			{
				check=false;
				System.out.println("boolean is "+check.toString());
			}
		}
		if(!check){
			return "Email already Exists";
		}
		return " ";
	}
	
	@RequestMapping(value="/PatientRegistrationusernameAJAX.html", method=RequestMethod.GET)
	@ResponseBody
	public String PatientUsernameajax(ModelMap models,@RequestParam String userName)
	{	
		//for user name
		Boolean check=true;
		List<User> userCheck=userDao.getUsers();
		for (User u2 : userCheck) {
			if(u2.getUsername().equals(userName))
			{
				check=false;
				System.out.println("boolean is "+check.toString());
			}
		}
		if(!check){
			return "Username already Exists";
		}
		return " ";
	}
	
	@RequestMapping(value="/PatientRegistration.html", method=RequestMethod.POST)
	public String PatientRegistration(ModelMap models,@ModelAttribute ("User") User user, BindingResult result)
	{	
		try{
			Boolean check=true;
			List<User> userCheck=userDao.getUsers();
			for (User u2 : userCheck) {
				if(u2.getUserEmail().equals(user.getUserEmail()))
				{
					check=false;
					System.out.println("boolean is "+check.toString());
				}
			}
			if(check){
				user.setUserType("patient");
				// save user to database
				Set<String> roles =new HashSet<String>();
				roles.add("ROLE_USER");
				user.setRoles(roles);
				user=userDao.saveUser(user);
				System.out.println("Data saved in db :"+user.getUsername());			
				//redirect to display page
				return "redirect:/users/Home.html?userid="+user.getUserId();
			}
			else{
				models.put("uniqueEmailId", "Email already exists..Try with Different one");
				return "redirect:PatientRegistration.html";
			}
		}catch (Exception e) {
			  if (e instanceof JpaSystemException) {
			        // Duplicate entry
				  System.out.println("unique :"+e.getMessage());				  
					//for unique user name
				  	models.put("uniqueUsername", "Username already exists..");
					//models.put("uniqueEmailId", "Username already exists..");
			  } 
			  else 
			  {
				 // models.put("uniqueEmailId", "Email already exists..Try with Different one");
			        // Other SQL Exception
				  	System.out.println("unique2 :"+e.getMessage());
			  }
			return "redirect:PatientRegistration.html";
		}
	}
	
	@RequestMapping(value="/doctorRegistration.html", method=RequestMethod.GET)
	public String doctorRegistration(ModelMap models)
	{	
		models.put("user", new User());
		return "doctorRegistration";
	}
	
	@RequestMapping(value="/doctorRegistration.html", method=RequestMethod.POST)
	public String doctorRegistration(ModelMap models, @ModelAttribute ("User") User user, BindingResult result)
	{	
	
		try{
			Boolean check=true;		
			List<User> userCheck=userDao.getUsers();
			for (User u2 : userCheck) {
				if(u2.getUserEmail().equals(user.getUserEmail()))
				{
					check=false;
					System.out.println("boolean is "+check.toString());
				}
			}

			if(check){	
					user.setUserType("doctor");
					// save user to database
					Integer id=user.getUserId();
					Set<String> roles =new HashSet<String>();
					roles.add("ROLE_DOCTOR");
					user.setRoles(roles);
					user=userDao.saveUser(user);		
					System.out.println("Data saved in db :"+user.getUsername());
					//redirect to display page	
					return "redirect:/users/Home.html?userid="+user.getUserId();
			}else{
				models.put("uniqueEmailId", "Email already exists..Try with Different one");
				return "redirect:doctorRegistration.html";
			}
			}catch (Exception e) {
				  if (e instanceof JpaSystemException) {
				        // Duplicate entry
					  System.out.println("unique :"+e.getMessage());				  
						//for unique user name
					  	models.put("uniqueUsername", "Username already exists..");
						//models.put("uniqueEmailId", "Username already exists..");
				  } 
				  else 
				  {
					 // models.put("uniqueEmailId", "Email already exists..Try with Different one");
				        // Other SQL Exception
					  	System.out.println("unique2 :"+e.getMessage());
				  }
				  return "redirect:doctorRegistration.html";
			}	
	}
	
	@RequestMapping(value="/editPatient.html", method=RequestMethod.GET)
	public String editPatient(ModelMap models,@RequestParam Integer userid)
	{	
		User u=userDao.getUser(userid);
		models.put("user", u);
		return "editPatient";
	}
	
	@RequestMapping(value="/editPatient.html", method=RequestMethod.POST)
	public String editPatient(@ModelAttribute ("user") User user,@RequestParam Integer userid)
	{	
		User u=userDao.getUser(userid);
		user.setUsername(u.getUsername());
		user.setPassword(u.getPassword());
		user.setUserType(u.getUserType());
		user.setDateOfBirth(u.getDateOfBirth());
		user.setUserId(userid);
		user=userDao.saveUser(user);
		return "redirect:/users/Home.html?userid="+userid;
	}
	
	@RequestMapping(value = "/editPatientAJAX.html", method = RequestMethod.GET)
    @ResponseBody
    public String getUser( @RequestParam Integer userid, ModelMap models )
    {
        
        return new Gson().toJson(userDao.getUser( userid ));
        
    }
	
	@RequestMapping(value="/editPatientAJAX.html", method=RequestMethod.PUT)
	@ResponseBody
	public String editPatientAJAX(@RequestBody User user)
	{	
		System.out.println("ajax edit user");
		
		User u=userDao.getUser(user.getUserId());		
		u.setUsername(u.getUsername());
		u.setUserContact(user.getUserContact());
		u.setUserEmail(user.getUserEmail());
		u.setUserAddress(user.getUserAddress());
		userDao.saveUser(u);
		return new Gson().toJson(u);
	}
	
	@RequestMapping(value="/users/editUser.html", method=RequestMethod.GET)
	public String editUser(ModelMap models,@RequestParam("searchUsers") String username,@RequestParam("userid") String userid)
	{	
		try{
			//System.out.println("username is "+username);
			User u=userDao.getUserByUsername(username);
			models.put("alluser", u);
			models.put("userid", u.getUserId());
			return "/users/editUser";
		}catch (Exception e) {
			
		}
		return "redirect:/users/Home.html?userid="+userid;
	}
	

	@RequestMapping(value="/users/editUser.html", method=RequestMethod.POST)
	public String editUser(@RequestParam Integer userid,@RequestParam("usr") Integer usr,@RequestParam(required=false) String Enable,@RequestParam(required=false) String Disable)
	{	
		User u=userDao.getUser(usr);
		if(Enable!=null){
			u.setEnabled(true);
			Set<String> roles =new HashSet<String>();
			if(u.getUserType().matches("patient")){
				roles.add("ROLE_USER");
			}else if(u.getUserType().matches("doctor")){
				roles.add("ROLE_DOCTOR");
			}else{
				roles.add("ROLE_ADMIN");
			}
			u.setRoles(roles);
		}else{
			u.setEnabled(false);
		}
		userDao.saveUser(u);
		return "redirect:/users/Home.html?userid="+userid;
	}
	
	@RequestMapping(value="/setScheduleDoctor.html",method=RequestMethod.GET)
	public String setSDoctor(ModelMap models, @RequestParam Integer userid,  @RequestParam(required=false) Integer nextweek)	{
		//add 14 days to list
		Calendar now = Calendar.getInstance();
	    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	    String[] days = new String[7];
	    String[] days2 = new String[7];
	    int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; 
	    int r=0;
	    for (int i = 0; i < 14; i++)
	    {
	    	if(i<7){
	    		days[i] = format.format(now.getTime());
	    	}
	    	else{	    		
	    		System.out.println(r);
	    		days2[r] = format.format(now.getTime());
	    		r++;
	    	}
	        now.add(Calendar.DATE, 1);
	        
	    }
		//
		if(nextweek==1){
		
	    System.out.println(Arrays.toString(days));
	    models.put("dates", days);
	    models.put("userid", userid);
	    java.util.List<String> slots=Arrays.asList("9-10","10-11","11-12","12-1","1-2","2-3","3-4","4-5");
	    models.put("slots",slots);
			//look for previous records
			List<Boolean> bslot=new ArrayList<>();
			HashMap<String, List<Boolean>> dc = new HashMap<String, List<Boolean>>();
			dc.put("date", bslot);
			List<MakeAvailability> make=makeAvailabilityDao.getAvailabilities();
			for (MakeAvailability mA : make) {
				if(mA.getDoctorId().getUserId().getUserId().equals(userid)&& Arrays.asList(days).contains(mA.getAvailableDate())){	
					bslot=Arrays.asList(mA.isSlot910(),mA.isSlot1011(),mA.isSlot1112(),mA.isSlot121(),
							mA.isSlot12(),mA.isSlot23(),mA.isSlot34(),mA.isSlot45());
					dc.put(mA.getAvailableDate(), bslot);
				}
			}
			models.put("hashset", dc);
			//
		}else{
			models.put("dates", days2);
		    models.put("userid", userid);
		    java.util.List<String> slots=Arrays.asList("9-10","10-11","11-12","12-1","1-2","2-3","3-4","4-5");
		    models.put("slots",slots);
				//look for previous records
				List<Boolean> bslot=new ArrayList<>();
				HashMap<String, List<Boolean>> dc = new HashMap<String, List<Boolean>>();
				dc.put("date", bslot);
				List<MakeAvailability> make=makeAvailabilityDao.getAvailabilities();
				for (MakeAvailability mA : make) {
					if(mA.getDoctorId().getUserId().getUserId().equals(userid)&& Arrays.asList(days2).contains(mA.getAvailableDate())){	
						bslot=Arrays.asList(mA.isSlot910(),mA.isSlot1011(),mA.isSlot1112(),mA.isSlot121(),
								mA.isSlot12(),mA.isSlot23(),mA.isSlot34(),mA.isSlot45());
						dc.put(mA.getAvailableDate(), bslot);
					}
				}
				models.put("hashset", dc);
		    System.out.println("2:  "+Arrays.toString(days2));
		} 	
		return "setScheduleDoctor";
		
	}
	
	@RequestMapping(value="/setScheduleDoctor.html", method=RequestMethod.POST)
	public String setSDoctor(@RequestParam java.util.List<String> getindex,  @RequestParam Integer week,@RequestParam Integer userid, ModelMap models)	{
		
		System.out.println("result is"+getindex);
		java.util.List<String> slots=new ArrayList<String>();
		 java.util.List<String> slotsnew=new ArrayList<String>();
		 List<Doctor> doc=doctorDao.getDoctorbyUserId(userid);
		 //calendar functions
		 Calendar now = Calendar.getInstance();
		    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		    String[] days = new String[7];
		    String[] days2 = new String[7];		 
		    int r=0;
		    for (int i = 0; i < 14; i++)
		    {
		    	if(i<7){
		    		days[i] = format.format(now.getTime());
		    	}
		    	else{	    		
		    		System.out.println(r);
		    		days2[r] = format.format(now.getTime());
		    		r++;
		    	}
		        now.add(Calendar.DATE, 1);
		        
		    }
		 //uncheck 
		 if(week==1){
			 List<MakeAvailability> mk= makeAvailabilityDao.getAvailabilities();
			 for (MakeAvailability makeAvailability : mk) {
				 if(Arrays.toString(days).contains(makeAvailability.getAvailableDate()))
				 {
					if(makeAvailability.getDoctorId().getDoctorId().equals(doc.get(0).getDoctorId())){
						makeAvailabilityDao.removeAvailability(makeAvailability.getmId());
					}
				}
			 }
		 }else
		 {//for next week=2
			 List<MakeAvailability> mk= makeAvailabilityDao.getAvailabilities();
			 for (MakeAvailability makeAvailability : mk) {
				 if(Arrays.toString(days2).contains(makeAvailability.getAvailableDate()))
				 {
					if(makeAvailability.getDoctorId().getDoctorId().equals(doc.get(0).getDoctorId())){
						makeAvailabilityDao.removeAvailability(makeAvailability.getmId());
					}
				}
			 }
		 }
		 //
		 
		 for (String g : getindex) {
			
			try{
			String[] date=g.split(" ");
			String slot =date[0];
			String str = slot.replace("-", "");
			System.out.println("slot is  :"+str);
			String availableDate = date[1];
			//
			Random rand = new Random();
			int  n = rand.nextInt(1000000) + 1;		
			Integer id = n;
			System.out.println("ID is : "+id);
			List<Doctor> d=doctorDao.getDoctorbyUserId(userid);
			List<MakeAvailability> m= makeAvailabilityDao.getAvailabilities();
			List<String> dts=new ArrayList<String>();
			int prevoiusID=0;
			for (MakeAvailability makeAvailability : m) {
				
				if(makeAvailability.getAvailableDate().matches(availableDate)&& d.get(0).getDoctorId().equals(makeAvailability.getDoctorId().getDoctorId())){
					dts.add(makeAvailability.getAvailableDate());
					prevoiusID= makeAvailability.getmId();
				}
			}
			//unchecked checkbox
			 slots=Arrays.asList("9-10","10-11","11-12","12-1","1-2","2-3","3-4","4-5");
			 slotsnew.add(slot);
			 
			
			 //
			if(dts.contains(availableDate)){
				makeAvailabilityDao.updateSlots(str, availableDate,prevoiusID, d.get(0));
			}else{
				makeAvailabilityDao.setSlots(str,availableDate,id,d.get(0));
			}
			
			}catch(Exception e){}
			
		}
		return "redirect:/users/Home.html?userid="+userid;
	}
	
	@RequestMapping(value="/users/changePassword.html", method=RequestMethod.GET)
	public String changePassword(ModelMap models,@RequestParam Integer userid)
	{	
		User u=userDao.getUser(userid);
		models.put("user", u);
		models.put("userid", userid);
		return "/users/changePassword";
	}
	
	@RequestMapping(value="/changePasswordAJAX.html", method=RequestMethod.GET)
	@ResponseBody
	public String changePasswordAJAX(ModelMap models,@RequestParam Integer userid,@RequestParam String prevPassword)
	{	
		System.out.println("previous password ="+prevPassword);
		User u=userDao.getUser(userid);
		if(prevPassword.equals(u.getPassword())){
			return "";
		}else{
			return "Invalid Password";
		}
	}
	
	@RequestMapping(value="/users/changePassword.html", method=RequestMethod.POST)
	public String changePassword(@RequestParam Integer userid,@RequestParam String inputPassword,@RequestParam String previousPassword)
	{			
		User u=userDao.getUser(userid);
		if(previousPassword.equals(u.getPassword())){
			//System.out.println(previousPassword);
			u.setPassword(inputPassword);
			userDao.saveUser(u);
		}
		return "redirect:/users/Home.html?userid="+userid;
	}
}
