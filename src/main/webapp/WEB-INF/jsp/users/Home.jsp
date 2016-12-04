<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>My Dentist Portal</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="We Care Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	Smartphone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //Custom Theme files -->
<link href="../css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="../css/style.css" type="text/css" rel="stylesheet" media="all">
<link rel="stylesheet" href="../css/flexslider.css" type="text/css" media="screen" />
<!-- js -->
<script src="../js/jquery-1.11.1.min.js"></script> 
<!-- //js -->
<!-- start-smooth-scrolling-->
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>	
<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
		
		$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
			});
		});
</script>
<!--//end-smooth-scrolling-->
<script type="text/javascript">
$(function(){
	var userId = ${userid};	 
	$("input[name='Delete']").click(function(){
		var apt =$(this).closest("tr").attr("data-apt-id");	 
		$.ajax('CancelAptAJAX.html?userid='+userId+'&aptid='+apt,{
			cache:false,
			method: "POST",
			context: $(this),
			data: {
				userid: userId,
				aptid: apt
            },
			success:function(data){
				$(this).closest("tr").remove();
			}
		});
		
	});
	
	
});
</script>
	
</head>
<body>
	<!--header-->
	<div class="header">
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header navbar-left">
					<h1><a href="Home.html"><img src="../images/logo.png" alt="">My Dentist Portal</a></h1>
				</div>
				<!--navigation-->
				<div class="header-text navbar-left">
					<p>Creating vibrant smiles for healthy lifestyles!<p>
				</div>
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<div class="header-right">
					<div class="top-nav-text">
						<ul>
							<li>Call us: <span>+11 111 2222</span></li>
							<li>Email : <a class="email-link" href="mailto:example@mail.com">mydentist70@gmail.com</a></li>
							<li>
								<ul class="social-icons">
									<li><a href="#"></a></li>
									<li><a href="#" class="pin"></a></li>
									<li><a href="#" class="in"></a></li>
								</ul>
							</li>
						</ul>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">					
						<ul class="nav navbar-nav navbar-left">
							<li class="active"><a href="Home.html"><span>H</span><span>O</span><span>M</span><span>E</span></a></li>
							<li>
								<a href="<c:url value='/logout'/>" class="link link--yaku"><span>L</span><span>O</span><span>G</span><span>O</span><span>U</span><span>T</span></a>
								
							</li>						
						</ul>		
						<div class="clearfix"> </div>
					</div><!--//navigation-->
				</div>
				<div class="clearfix"> </div>
			</div>	
		</nav>		
	</div>	
	<!--//header-->
	<security:authorize access="anonymous">
	<!--banner-->
	<div class="banner">
		<div class="container">
			<section class="slider">
				<div class="flexslider">
					<ul class="slides">
						<li>
							<div class="banner-info">
								<h2 style="color: black;">Temporibus autem debitis </h2>
								<p style="color: black;">Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable </p>								
								<a href="#" class="effect6">Get More</a>
							</div>
						</li>
						<li>
							<div class="banner-info">
								<h2 style="color: black;"> Lautem quibusdam debitis </h2>
								<p style="color: black;">Orem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable </p>								
								<a href="#" class="effect6">Get More</a>
							</div>
						</li>
					</ul>
				</div>
			</section>
		</div>
		<!-- FlexSlider -->
			<script defer src="../js/jquery.flexslider.js"></script>
			<script type="text/javascript">
				$(window).load(function(){
				  $('.flexslider').flexslider({
					animation: "slide",
					start: function(slider){
					  $('body').removeClass('loading');
					}
				  });
				});
			</script>
		<!-- //FlexSlider -->
	</div>
	<!--banner-->
	<!--banner-bottom-->
	<div class="banner-bottom">
		<div class="container">
			<h4>Lorem Ipsum has been the <span>industry's</span> standard dummy text. </h4>
			<p>Contrary to popular belief when an unknown printer took a galley of type and scrambled it to make a type specimen book. </p>
		</div>
	</div>
	<!--//banner-bottom-->
	<!--copy-right-->
	</security:authorize>
	    <security:authorize access="hasAnyRole('USER')">
          <br/><div class="panel-group">
  <div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse2"><h4>Manage your Appointments</h4></a>
        </h5>
      </div>
      <div id="collapse2">
        <div class="panel-body">
        Haven't schedule your appointment! Here's your chance to do it!
        <a href="/myDentist/appointment/appointment.html?userid=${userid}">Appointment</a>
        <br><br>
  		View All my Appointments
  	    </div>
        <div class="panel-footer"></div>
      	<table class="table table-striped">
        <thead>
        <tr>
        <th>Appointment ID</th>
        <th>Appointment Date</th>
        <th>Appointment Time</th>
        <th>Doctor ID</th>
  		<th>Doctor Name</th>  
        <th>Operations</th>
        </tr></thead>
  		<c:forEach items="${appointments}" var="apt">
        <c:if test="${apt.userId.userId eq userid}" >
        <tbody>
        <tr data-apt-id="${apt.appointmentId}" >
        <td>${apt.appointmentId}</td>
        <td>${apt.appointmentDate}</td>
        <td>${apt.appointmentTime}</td>
        <td>${apt.doctorId.doctorId}</td>
        <td>${apt.doctorId.doctorName}</td>
        <td><a href="/myDentist/users/rescheduleAppointment.html?id=${apt.appointmentId}&doctorid=${apt.doctorId.doctorId}&&appointmentDate=${apt.appointmentDate}">Edit My appointment</a></td>
        <td>
        <input style="color: black;" type="submit" name="Delete" class="delete" value="Cancel Appointment">
        <input type="hidden" name="aptid" value="${apt.appointmentId}">
        <input type="hidden" name="userid" value="${userid}">
        <c:set var="aapt" scope="application" value="${apt.appointmentId}"></c:set>
        </td>
        </tr>  
        </tbody>      
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
        <div class="panel-body">
        <table class="table table-striped">
        <tbody>
        <tr>
        <th>${users.userId}</th>
        <th>${users.username}</th>
        <th>${users.userEmail}</th>
        <th>${users.userAddress}</th>
        <th>${users.userContact}</th>
        </tr>
        </tbody>
        </table>
        </div>
        <div class="panel-footer"><a href="/myDentist/editPatient.html?userid=${userid}">Edit Profile Here</a></div>
        <div class="panel-footer"><a href="/myDentist/users/changePassword.html?userid=${userid}">Change Password</a></div>
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
        <div class="panel-body"> Pay your all th bills here!</div>
        <div class="panel-footer">Here's the link for paying the bills.</div>
      </div>
    </div>
</div>

    </security:authorize>
    <security:authorize access="hasAnyRole('DOCTOR')" >
    
    <div style="height: 400px">

<div class="panel-group" >
    <div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse1"><h3>Profile</h3></a>
        </h5>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body"></div>
        <div class="panel-footer"><a href="/myDentist/users/doctorProfile.html?userid=${userid}">View/Edit Profile</a>
        </div>
      </div>
    </div>
  </div>
 
  <div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse3"><h3>Make yourself available for Appointments</h3></a>
        </h5>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body">Set your appointment schedule for the next one week</div>
        <div class="panel-footer"><a href="/myDentist/setScheduleDoctor.html?nextweek=1&userid=${userid}">setSchedule</a></div>
      </div>
    </div>
 
<div class="panel panel-default">
      <div class="panel-heading">
        <h5 class="panel-title">
          <a data-toggle="collapse" href="#collapse4"><h3>View All Appointments</h3></a>
        </h5>
      </div>
      <div id="collapse4" class="panel-collapse collapse">
        <div class="panel-body">Check All your appointments here</div>
        <div class="panel-footer">
        	<table border="1">
        <tr>
        <th>Appointment Date</th><th>Appointment Time</th>
        <th>PatientID</th>
        <th>Patient Name</th>
        <th>Operations</th>
        </tr>
  		<c:forEach items="${appointments}" var="apt">
        <c:if test="${apt.doctorId.doctorId eq doctorid && apt.appointmentDate > tomorrow}" >
        <tr>
        
		<td>${apt.appointmentDate}</td>
		<td>${apt.appointmentTime}</td>
		<td>${apt.userId.userId}</td>	
        <td>${apt.userId.username}</td>
		<td><a href="/myDentist/users/GenerateReport.html?userid=${apt.userId.userId}&doctorid=${doctorid}&user=${userid}">Create/View Report</a></td>

		</tr>        
        </c:if>
        </c:forEach>
  	    </table>
        </div>
      </div>
  </div>
</div>
    </security:authorize>
    <security:authorize access="hasAnyRole('ADMIN')">
    <br/>
    <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse1"><h4>Add Doctor</h4></a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body">Want to add new Doctor? Add it then</div>
        <div class="panel-footer"><a href="../doctorRegistration.html">Add New Doctor</a>
        </div>
      </div>
    </div>
  </div>
  
  <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse2"><h4>View All Appointment Records</h4></a>
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body">Appointment records</div>
        <div class="panel-footer">
        <table border="1">
        	<tr><th>Appointment ID</th><th>Patient ID</th><th>Doctor ID</th><th>Appointment Date</th><th>Appointment Time</th></tr>
        	<c:forEach items="${appointments}" var="apt">
        	<tr>
        	<td>${apt.appointmentId}</td>
        	<td>${apt.userId.userId}</td>
        	<td>${apt.doctorId.doctorId}</td>
        	<td>${apt.appointmentDate}</td>
        	<td>${apt.appointmentTime}</td>
        	</tr>        
        	</c:forEach>
        </table>
        </div>
      </div>
    </div>
  
  <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse3"><h4>Enable/Disable Users</h4></a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
         <table border="1">
        	<tr><th>User ID</th><th>User Email</th><th>Type</th><th></th></tr>
        	<c:forEach items="${alluser}" var="usr">
        	<tr>
        	<td>${usr.userId}</td>
        	<td>${usr.userEmail}</td>
        	<td>${usr.userType}</td>
        	<td><a href="/myDentist/users/editUser.html?userid=${usr.userId}">Enable/Disable User</a></td>
        	</tr>        
        	</c:forEach>
        </table>
      </div>
    </div>
<br>
<br><br>
    </security:authorize>
<%@ include file="/WEB-INF/footer.jsp" %>
	<!--//copy-right-->
	<!--smooth-scrolling-of-move-up-->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
			};
			*/
			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
	<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
	<!--//smooth-scrolling-of-move-up-->
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/bootstrap.js"></script>
</body>
</html>