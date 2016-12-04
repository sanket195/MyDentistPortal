package myDentist.emailTo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import myDentist.model.Appointments;
import myDentist.model.MakeAvailability;
import myDentist.model.User;
import myDentist.model.dao.MakeAvailabilityDao;
import myDentist.model.dao.appointmentsDao;
import myDentist.model.dao.userDao;

public class PlainTextEmailSender {
	
	@Autowired
	appointmentsDao appointmentsDao;
	
	@Autowired
	userDao userDao;
	
	//@Scheduled(cron="*/20 * * * * ?")
	
	@Scheduled(cron="0 0 10 * * *")
	public void GetAllAppointments(){
		
		List<Appointments> ap =appointmentsDao.getAppointments();
		Calendar calendar = Calendar.getInstance();
	    // get a date to represent "today"
	    Date today = calendar.getTime();
	    calendar.add(Calendar.DAY_OF_YEAR, 1);
	    // now get "tomorrow"
	    Date tomorrow = calendar.getTime();
	    String tom=new SimpleDateFormat("MM/dd/yyyy").format(tomorrow);
	    System.out.println("tommorow's date :"+tomorrow);
	    
	    /* Patient Name, Date, Slot, Doctor Name,doctor email
	     * 
	     *	 
	     */
	    
	    String sendAddress=null;
	    String slot=null;
	    String doctorName=null;
	    String patientName=null;
	    
	    for (Appointments appointments : ap) {
			if(appointments.getAppointmentDate().equals(tom)){
				sendAddress=appointments.getUserId().getUserEmail();
				slot=appointments.getAppointmentTime();
				doctorName=appointments.getDoctorId().getDoctorName();
				patientName=appointments.getUserId().getUsername();
				
				try {
					sendPlainTextEmail(sendAddress,slot,doctorName,patientName);
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	
	public void sendPlainTextEmail (String sendAddress, String slot, String doctorName, String pateintName) throws MessagingException{
		
		String host = "smtp.gmail.com";
		String port = "587";
		final String  username = "mydentist70@gmail.com";
		final String password = "myDentist1234";
		//sendAddress="sanket176@gmail.com";
		// outgoing message information
		String subject = "Appointment Notification";
		String message = "Hello"+pateintName+",\n\n"
							+ "Your appointment is scheduled for tommorow between "+slot+" with "+doctorName+"\n\n"
							+ "\n\n"
							+ "Thank you,\n\n"
							+ "MyDentist Dental Care";

		
		Calendar calendar = Calendar.getInstance();
	    
	    // get a date to represent "today"
	    Date today = calendar.getTime();
	    System.out.println("today:    " + today);
	 
	    // add one day to the date/calendar
	    calendar.add(Calendar.DAY_OF_YEAR, 1);
	    
	    // now get "tomorrow"
	    Date tomorrow = calendar.getTime();
	    
	    System.out.println("tommorow's date :"+tomorrow);
		
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(username));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InternetAddress[] toAddresses = { new InternetAddress(sendAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(tomorrow);
		// set plain text message
		msg.setText(message);

		// sends the e-mail
		
		Transport.send(msg);
		System.out.println("Email sent !!!!");
	}
	
	
}