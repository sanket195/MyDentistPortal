package myDentist.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myDentist.model.Appointments;
import myDentist.model.Doctor;
import myDentist.model.MakeAvailability;
import myDentist.model.User;
import myDentist.model.dao.MakeAvailabilityDao;
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
	private MakeAvailabilityDao availabilityDao;
	
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
	
	@RequestMapping(value="/appointment.html", method=RequestMethod.GET)
	public String takeAppointment(@ModelAttribute("appointments") Appointments appointment,ModelMap models,@RequestParam(required=false) Integer doctorId2,@RequestParam(required=false) List<String> slots,@RequestParam Integer userid,@RequestParam(required=false) String appointmentDate)
	{
		if(appointmentDate==null){
		models.put("appointments",appointment);
		models.put("doctors", doctorDao.getDoctors());
			
		models.put("doctors", doctorDao.getDoctors());
		System.out.println("Doctor ID is :"+userid+"||||| Selected date is (GET) : "+appointmentDate);
		//System.out.println("doctor"+doctorDao.getDoctor(102).getDoctorName());
		models.put("userid", userid);
		models.put("appointments",appointment);
		models.put("appointmentDate", appointmentDate);
		
		}
		if(slots!=null){
			System.out.println("GET doctor ="+doctorId2);
			models.put("slots", slots);
			models.put("doctorid", doctorId2);
		}
		return "appointment";
		
	}

	
	@RequestMapping(value="/appointment.html",method=RequestMethod.POST)
	public String takeAppointment(@RequestParam Integer userid,@RequestParam(required=false) Integer doctorId,@RequestParam(required=false) Integer doctorid, @RequestParam String appointmentDate,ModelMap models,@RequestParam(required=false) String appointmentTime,@RequestParam(required=false) String slot)
	{		
		if(appointmentTime!=null){
		System.out.println("Doctor ID is :"+doctorid+"||||| Selected date is : "+appointmentDate);
		Appointments apt=new Appointments();
		
		System.out.println("selected slot = "+appointmentTime);
		Doctor d=doctorDao.getDoctor(doctorid);
		User u=userDao.getUser(userid);
		apt.setDoctorId(d);
		apt.setUserId(u);
		apt.setAppointmentTime(appointmentTime);
		apt.setAppointmentDate(appointmentDate);
		//System.out.println("value is :"+appointment.getAppointmentDate());
		appointmentsDao.saveAppointment(apt);
		
		return "redirect:PatientHome.html?userid="+userid;
		}
		else{
			try{
			List<String> slots=new ArrayList<String>();
			Doctor d=doctorDao.getDoctor(doctorId);
			MakeAvailability doc = new MakeAvailability();
			List<MakeAvailability> mk= availabilityDao.getAvailabilities();
			for (MakeAvailability makeAvailability : mk) {
				if(makeAvailability.getDoctorId().getDoctorId()==d.getDoctorId()){
					doc=makeAvailability;
				}
			}	
			boolean b=true;
			System.out.println("value is "+doc.isSlot1011()+doc.getDoctorId());
			if(doc.isSlot910()){slots.add("9-10");}
			if(doc.isSlot1011()){slots.add("10-11");}
			if(doc.isSlot1112()==b){slots.add("11-12");}
			if(doc.isSlot121()==b){slots.add("12-1");}
			if(doc.isSlot12()==b){slots.add("1-2");}
			if(doc.isSlot23()==b){slots.add("2-3");}
			if(doc.isSlot34()==b){slots.add("3-4");}
			if(doc.isSlot45()==b){slots.add("4-5");}
			
			System.out.println("LIST"+slots);
			models.put("appointmentDate", appointmentDate);
			models.put("slots", slots);
			models.put("doctorId2", doctorId);
			System.out.println("POST doctor "+doctorId);
			}
			catch (Exception e) {
			}
			return "redirect:appointment.html?userid="+userid;
		}
		//models.put("appointments",new Appointments());
	
	}
	
	@RequestMapping(value="/rescheduleAppointment.html",method=RequestMethod.GET)
	public String rescheduleAppointment(ModelMap models,@RequestParam Integer id,@RequestParam Integer doctorid,@RequestParam String appointmentDate)
	{
		Appointments app = appointmentsDao.getAppointment(id);
		System.out.println("useris"+app.getUserId());
		models.put("userid", app.getUserId());
		models.put("doctorid", doctorid);
		models.put("appointments", appointmentsDao.getAppointment(id));
		try{
			List<String> slots=new ArrayList<String>();
			Doctor d=doctorDao.getDoctor(doctorid);
			MakeAvailability doc = new MakeAvailability();
			List<MakeAvailability> mk= availabilityDao.getAvailabilities();
			for (MakeAvailability makeAvailability : mk) {
				if(makeAvailability.getDoctorId().getDoctorId()==d.getDoctorId()&&makeAvailability.getAvailableDate().equals(appointmentDate)){
					doc=makeAvailability;
				}else{
					//slots.add("Not Available");
				}
				
			}	
			boolean b=true;
			System.out.println("value is "+doc.isSlot1011()+doc.getDoctorId());
			if(doc.isSlot910()){slots.add("9-10");}
			if(doc.isSlot1011()){slots.add("10-11");}
			if(doc.isSlot1112()==b){slots.add("11-12");}
			if(doc.isSlot121()==b){slots.add("12-1");}
			if(doc.isSlot12()==b){slots.add("1-2");}
			if(doc.isSlot23()==b){slots.add("2-3");}
			if(doc.isSlot34()==b){slots.add("3-4");}
			if(doc.isSlot45()==b){slots.add("4-5");}
			
			models.put("slots", slots);
			}
			catch (Exception e) {
			}
		return "rescheduleAppointment";
	}
	
	@RequestMapping(value="/rescheduleAppointment.html",method=RequestMethod.POST)
	public String rescheduleAppointment(@ModelAttribute("appointments") Appointments appointment,@RequestParam Integer doctorid,@RequestParam Integer userid,@RequestParam Integer appid)
	{
		//System.out.println("userrrrrrrrrrrrrrrrrrrrr-----"+appointmentsDao.getappointmentId(id));
		//appointment.setPatientId(userDao.getUser(id));	
		//User user = userDao.getUser(userid);
		Appointments ap = appointmentsDao.getAppointmentbyAptid(appid);		
		
		System.out.println("value is :"+appointment.getAppointmentDate());
		System.out.println(appointment.getAppointmentId());
		//ap.setAppointmentDate(appointment.getAppointmentDate());
		//ap.setAppointmentTime(appointment.getAppointmentTime());
		appointment.setDoctorId(ap.getDoctorId());
		appointment.setUserId(ap.getUserId());
		//appointment.setAppointmentDate(appointmentDate2);
		//appointment.setAppointmentId();
		appointment = appointmentsDao.saveAppointment(appointment);
		return "redirect:PatientHome.html?userid="+userid;
	}
	
	@RequestMapping(value="/SetSchedule.html",method=RequestMethod.GET)
	public String SetSchedule( ModelMap models,@RequestParam Integer userid,@RequestParam(required=false) String availableDate)
	{
		System.out.println("new user"+userid);
		models.put("setdate", new MakeAvailability());
		models.put("userid", userid);
		if(availableDate !=null){
			System.out.println("Available date is : "+availableDate);			
		}
		return "SetSchedule";
	}
	
	@RequestMapping(value="/SetSchedule.html",method=RequestMethod.POST)
	public String SetSchedule(@ModelAttribute("setdate") MakeAvailability makeAvailability, ModelMap models,@RequestParam Integer userid,@RequestParam String availableDate,@RequestParam(required=false) List<String> slot)
	{
		
		
		Random rand = new Random();
		int  n = rand.nextInt(1000) + 1;		
		Integer id = n;
		System.out.println("ID is : "+id);
		List<Doctor> d=doctorDao.getDoctorbyUserId(userid);
		if(slot!=null){
			int count=0;
			for (String s : slot) {
			System.out.println("column name"+s);
			System.out.println("date POST"+availableDate);
			Integer temp=id;
			if(count==0){
				availabilityDao.setSlots(s,availableDate,id,d.get(0));
			}
			else{
					availabilityDao.updateSlots(s, availableDate, temp, d.get(0));
			}
			count++;
			}
			return "redirect:doctorHome.html?userid="+userid;
		}else{
		models.put("availableDate", availableDate);
		models.put("userid", userid);
		
		}
		return "redirect:SetSchedule.html";
	}

}
