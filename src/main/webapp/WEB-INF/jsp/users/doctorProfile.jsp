<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor Profile</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row" >
        <form:form modelAttribute="doctors">
            <div class="col-sm-10" >
                <div class="well well-sm" align="center"><strong>Doctor Registration Form</strong></div>
                <div class="form-group">
                    <label for="doctorName">Doctor Name</label>
                        <spring:bind path="doctorName"><input type="text" class="form-control" name="doctorName" id="doctorName" placeholder="Enter name" required>
                    	</spring:bind>
                </div>
                <div class="form-group">
                    <label for="designation">Designation</label>
                    <spring:bind path="designation"><input type="text" class="form-control" name="designation" id="designation" placeholder="Enter designation" required>
             
                    </spring:bind>
                </div>
                <div class="form-group">
                    <label for="specialization">specialization</label>
                    <spring:bind path="specialization"><input type="text" class="form-control" name="specialization" id="specialization" required>
                    </spring:bind>
                </div>
                
                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
            </div>
        </form:form>
    </div>
</div>
</body>
</html>