<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Home Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h1>Welcome to Home Page</h1>
  <br><br>
  <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse1"><h4>Make new Appointment</h4></a>
        </h5>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body">Haven't schedule an appointment? Here's your chance!</div>
        <div class="panel-footer">Make it. <a href="appointment.html?userid=${userid}">Appointment</a>
        </div>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse2"><h4>Reschedule Appointment</h4></a>
        </h5>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body">
  		View All my Appointments
  	    </div>
        <div class="panel-footer"></div>
      	<table border="1">
        <tr>
        <th>Appointment ID</th><th>Operations</th>
        </tr>
  		<c:forEach items="${appointments}" var="apt">
        <c:if test="${apt.userId.userId eq userid}" >
        <tr>
        <td>${apt.appointmentId}</td><td><a href="rescheduleAppointment.html?id=${apt.appointmentId}">Reschedule My Appointment</a></td>
        </tr>        
        </c:if>
        </c:forEach>
  	    </table>
      </div>
    </div>
  
<div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse3"><h4>Update My Info!</h4></a>
        </h5>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body">Pay your all th bills here!</div>
        <div class="panel-footer">Here's the link for paying the bills.</div>
      </div>
    </div>

  
  <div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse4"><h4>View Reports</h4></a>
        </h5>
      </div>
      <div id="collapse4" class="panel-collapse collapse">
        <div class="panel-body">Haven't gone through the reports? Go through it then!</div>
        <div class="panel-footer">Want to check the Lab reports?</div>
      </div>
  </div>
  <div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse5"><h4>Payments</h4></a>
        </h5>
      </div>
      <div id="collapse5" class="panel-collapse collapse">
        <div class="panel-body">Pay your all th bills here!</div>
        <div class="panel-footer">Here's the link for paying the bills.</div>
      </div>
    </div>
</div>
<form action="logout" method="post">
<input name="_csrf" type="hidden" value="${_csrf.token}"/>
<div class="container">
  <input type="submit" class="btn btn-lg btn-info collapsed" value="Logout" >
  <a href="<c:url value='/logout' />">Logout</a>
</div>
</form>
</body>
</html>

 