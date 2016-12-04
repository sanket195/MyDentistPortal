<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="/myDentist/images/logo.png"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="We Care Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	Smartphone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //Custom Theme files -->
<link href="/myDentist/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="/myDentist/css/style.css" type="text/css" rel="stylesheet" media="all">
<link rel="stylesheet" href="../css/flexslider.css" type="text/css" media="screen" />
<!-- start-smooth-scrolling-->
<script type="text/javascript" src="/myDentist/js/move-top.js"></script>
<script type="text/javascript" src="/myDentist/js/easing.js"></script>	
<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
		
		$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
			});
		});
</script>
<title><tiles:insertAttribute name="title" defaultValue="Projects" defaultValueType="string" /></title>
</head>
<body>
	<!--header-->
	<div class="header">
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header navbar-left">
					<h1><a href="Home.html"><img src="/myDentist/images/logo.png" alt="">My Dentist Portal</a></h1>
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
							<li class="active"><a href="/myDentist/users/Home.html"><span>H</span><span>O</span><span>M</span><span>E</span></a></li>
						</ul>		
						<div class="clearfix"> </div>
					</div><!--//navigation-->
				</div>
				<div class="clearfix"> </div>
			</div>	
		</nav>		
	</div>	
	<!--//header-->
	<div>
  <tiles:insertAttribute name="content" />
</div>
	<!-- footer -->
	<div class="footer">
		<div class="container">
			<div class="footer-grds">
				<ul style="text-align:center;">
					<li><a>Privacy Policy |</a></li>
					<li><a>Contact |</a></li>
					<li><a>Terms of Use</a></li>
				</ul>
				<p align="center"> Copyright © 2016 My Dentist. All Rights Reserved </p>
			</div>
		</div>
	</div>
<!-- //footer --><!-- here stars scrolling icon -->
<!-- //here ends scrolling icon -->
<!-- for bootstrap working -->
		<script src="js/bootstrap.js"> </script>
<!-- //for bootstrap working -->
	
</body>
</html>