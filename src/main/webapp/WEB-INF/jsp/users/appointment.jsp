<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css"></script>
<!--  jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />

<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
<title>Take Appointment</title>
<script>
    $(document).ready(function(){
      var date_input=$('input[name="appointmentDate"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
</script>
</head>
<body>
<security:authorize access="authenticated">
<div class="bootstrap-iso">
 <div class="container-fluid">
<form:form modelAttribute="appointments">
  <div class="row">

   <div class="col-md-6 col-sm-6 col-xs-12">

    <!-- Form code begins -->
    
      <div class="form-group"> <!-- Date input -->
        <label class="control-label" for="date">Date</label>
        <spring:bind path="appointmentDate"><input class="form-control" id="appointmentDate" name="appointmentDate" placeholder="MM/DD/YYY" type="text"></spring:bind>
        
      </div>
      <div>
      
      <label for="appointmentTime">Time</label>
      <spring:bind path="appointmentTime"><input type="text" class="form-control" name="appointmentTime" id="appointmentTime">
      </spring:bind>
               <select id="appointmentTime" name="appointmentTime" class="form-control" required>
								              
												    <option value="9-10">9-10</option>
												    <option value="10-11">10-11</option>
												    <option value="11-12">11-12</option>
											   		 <option value="12-1">12-1</option>
												</select>  
	</div>
	
	<div  class="form-group">
      <label for="doctorId">   Doctor's Name :</label>
        
         <select id="doctorname" name="doctorname" class="form-control" required>
								                <option>Select Doctor</option>
												<c:forEach items="${doctors}" var="d">												
												<option value="${d.doctorName}">${d.doctorName}</option>
												</c:forEach>
												</select>
			</div>      
    <div class="form-group">
		<input type="hidden" value="${userid}" name="userid"/>
        <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
      </div>
     <!-- Form code ends --> 

    </div>
      </div>
   
    
</form:form>
  </div>    
 </div>
</security:authorize>
</body>
</html>