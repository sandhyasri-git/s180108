<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta name="viewport" content="width=device-width,initial-scale=1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>


<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">


<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script> 
  <link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet">
  
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#myNavbar">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="Welcome">MusicStore</a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav">
			<li class=""><a href="Home">Home</a></li>
			<li><a href="Welcome">AboutUS</a></li>
			<li><a href="viewproducts">Products</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${empty loggedInUser }">
					<li><a href="Register"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li>
					<li><a href="Login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</c:when>
				<c:when test="${not empty loggedInUser }">
				
					<li><a href="Cart">Basket</a></li>
					<li><a href="${loggedInUserID}/Profile">View Profile</a></li>
					<div class="media" style="float:left;">
        <img src="/musicstorefront/images/pic6.jpg" class="media-object" style="width:80px">
     </div>
   <li class="navbar-text" style="font-size:100%;">Welcome ${loggedInUser} !</li>
   					<form class="navbar-form navbar-right">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search">
							<div class="input-group-btn">
								<button class="btn btn-info" type="submit">
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</div>
							</div>
						<li><a href="perform_logout"><span
								class="glyphicon glyphicon-log-out"> </span>Logout</a></li>
					</form>
				<!-- <li><a href="perform_logout">Signout</a></li>	 -->	
				</c:when>
			
       <c:when test="${loggedOut==true }">
    <li class="navbar-text" style="font-size=150%;">${logoutMessage}</li>
    </c:when>
			</c:choose>
		</ul>
	</div>

</nav>


<title>NIIT Portal</title>

</head>
<body>