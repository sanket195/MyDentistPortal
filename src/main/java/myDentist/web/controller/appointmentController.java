package myDentist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myDentist.model.Appointments;
import myDentist.model.Doctor;
import myDentist.model.User;
import myDentist.model.dao.appointmentsDao;
import myDentist.model.dao.doctorDao;
import myDentist.model.dao.patientDao;
import myDentist.model.dao.userDao;

@Controller
public class appointmentController {
	
	@Autowired
	private userDao userDao;
	
	@Autowired
	private doctorDao doctorDao;
	
	@Autowired
	private appointmentsDao appointmentsDao;
	
	@Autowired
	private patientDao patientDao;
	
	@RequestMapping(value="/PatientHome.html", method=RequestMethod.GET)
	public String PatientHom(ModelMap models,@RequestParam Integer userid)
	{
		models.put("userid", userid);
		System.out.println("home page"+userid);
		models.put("users", userDao.getUser(userid));
		models.put("appointments",appointmentsDao.getAppointments());
		System.out.println("out of appoinment for loop");
		
		for(Appointments a:appointmentsDao.getAppointments())
		{
			System.out.println("In appoinment for loop");
			System.out.println(a.getAppointmentId());
			System.out.println(a.getAppointmentDate());
		}
		return "PatientHome";
		
	}
	
	@RequestMapping(value="/users/appointment.html", method=RequestMethod.GET)
	public String takeAppointment(ModelMap models,@RequestParam Integer userid)
	{
		List listA = new ArrayList();
		listA.add("10");
		listA.add("element 2");
		listA.add("element 3");
		models.put("list", listA);
		models.put("doctors", doctorDao.getDoctors());
		//System.out.println("doctor"+doctorDao.getDoctor(102).getDoctorName());
		models.put("userid", userid);
		return "/users/appointment";
		
	}
	
	@RequestMapping(value="/users/appointment.html",method=RequestMethod.POST)
	public String takeAppointment(@ModelAttribute("appointments") Appointments appointment,@RequestParam Integer userid,BindingResult results,@ModelAttribute ("doctors") Doctor doctors)
	{		
		System.out.println("-------------"+userid);
		//appointment.setDoctorId("10");
		appointment.setUserId(userDao.getUser(userid));
		//Doctor d=doctorDao.getDoctor(doctorId);
		//appointment.setDoctorId(doctorDao.getDoctor(doctorId));		
		System.out.println("value is :"+appointment.getAppointmentDate());
		appointment=appointmentsDao.saveAppointment(appointment);
		return "redirect:profile.html?userid="+userid;		
	}
	
	@RequestMapping(value="/users/rescheduleAppointment.html",method=RequestMethod.GET)
	public String rescheduleAppointment(ModelMap models,@RequestParam Integer id)
	{
		Appointments app = appointmentsDao.getAppointment(id);
		System.out.println("usssssssssssssssssssssseris"+app.getUserId());
		models.put("userid", app.getUserId());
		models.put("appointments", appointmentsDao.getAppointment(id));
		return "/users/rescheduleAppointment";
	}
	
	@RequestMapping(value="/users/rescheduleAppointment.html",method=RequestMethod.POST)
	public String rescheduleAppointment(@ModelAttribute("appointments") Appointments appointment,@RequestParam Integer userid,@RequestParam Integer appid)
	{
		//System.out.println("userrrrrrrrrrrrrrrrrrrrr-----"+appointmentsDao.getappointmentId(id));
		//appointment.setPatientId(userDao.getUser(id));	
		//User user = userDao.getUser(userid);
		Appointments ap = appointmentsDao.getAppointmentbyAptid(appid);		
		
		System.out.println("value is :"+appointment.getAppointmentDate());
		System.out.println(appointment.getAppointmentId());
		//ap.setAppointmentDate(appointment.getAppointmentDate());
		//ap.setAppointmentTime(appointment.getAppointmentTime());
		appointment.setUserId(ap.getUserId());
		//appointment.setAppointmentId();
		appointment = appointmentsDao.saveAppointment(appointment);
		return "redirect:profile.html?userid="+userid;
	}

}
