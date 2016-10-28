<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
  <h1>Welcome ${doctorid}</h1>
  <br><br>
   <security:authorize access="hasAnyRole('ADMIN')">
  <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse1"><h4>Profile</h4></a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body"></div>
        <div class="panel-footer"><a href="users/doctorProfile.html?userid=${userid}">View/Edit Profile</a>
        </div>
      </div>
    </div>
  </div>
 
  <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse3"><h4>View Patient Info</h4></a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body">---</div>
        <div class="panel-footer">---</div>
      </div>
    </div>
    </security:authorize>
        <security:authorize access="hasAnyRole('USER')">
        <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse1"><h4>Make new Appointment</h4></a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body">Haven't schedule an appointment? Here's your chance!</div>
        <div class="panel-footer">Make it. <a href="users/appointment.html?userid=${userid}">Appointment</a>
        </div>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse2"><h4>Reschedule Appointment</h4></a>
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body">
  <c:forEach items="${appointments}" var="apt">
        <c:if test="${apt.userId.userId eq userid}" >
        <table>
        <tr>
        <td>${apt.appointmentId}</td><td><a href="users/rescheduleAppointment.html?id=${apt.appointmentId}">Reschedule</a></td>
        </tr>        
        </table>
        </c:if>
        </c:forEach>
  	      </div>
        <div class="panel-footer"><a href="users/rescheduleAppointment.html?id=${users}">Appointment</a></div>
      </div>
    </div>
  
  <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse3"><h4>View Reports</h4></a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body">Haven't gone through the reports? Go through it then!</div>
        <div class="panel-footer">Want to check the Lab reports?</div>
      </div>
  </div>
  <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse4"><h4>Payments</h4></a>
        </h4>
      </div>
      <div id="collapse4" class="panel-collapse collapse">
        <div class="panel-body">Pay your all th bills here!</div>
        <div class="panel-footer">Here's the link for paying the bills.</div>
      </div>
    </div>
    </security:authorize>
    
</div>
<form action="logout" method="post">

<div class="container">
  <a href="<c:url value='/logout'/>" class="btn btn-info" role="button">Logout</a>
</div>
</form>

</body>
</html>