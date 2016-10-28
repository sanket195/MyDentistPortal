<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Login Form</title>
	<link rel="stylesheet" type="text/CSS" href="CSS/style.css">
  </head>
  <body>
  <form action="login" method="post">
    <div class="login">
	<h1>Login</h1>
	
	Username : <input type="text" name="username" id="username" required/><br><br>
	Password : <input type="password" name="password" id="password" required/><br><br>
	<input type="hidden" name="_csrf" value="${_csrf.token}">
	<button type="submit" class="btn btn-primary btn-block btn-large" name="login" value="Login" >Let me in.</button>
	<br><br>
	<a href="PatientRegistration.html">New Patient ? Sign-up here</a>
 
</div>
</form>
  </body>
</html>