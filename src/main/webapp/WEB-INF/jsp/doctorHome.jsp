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
 <security:authorize access="anonymous">
<div class="container">
  <h1>Welcome ${doctorid}</h1>
  <br><br>
  <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse1"><h4>Profile</h4></a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body"></div>
        <div class="panel-footer"><a href="doctorProfile.html?userid=${userid}">View/Edit Profile</a>
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
        <div class="panel-body">Want to check out patient's medical information?</div>
        <div class="panel-footer">Here's the link for it.</div>
      </div>
    </div>
    
</div>
<form action="logout" method="post">

<div class="container">
  <input type="submit" class="btn btn-lg btn-info collapsed" value="Logout" >
  <a href="<c:url value='/logout'/>">Logout</a>
</div>
</form>
</security:authorize>
</body>
</html>