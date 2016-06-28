<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html lang="pl">
<head>

 <meta charset="utf-8">
	<title>Home</title>
	
	<!-- AngularJS -->
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular-route.min.js"></script> -->
	
	<!-- Bootstrap -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	<!-- My stuff -->
	<link href="<c:url value="/resources/styles.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/app.js" />" ></script>
	<!-- <script src="<c:url value="/resources/js/controllers.js" />" ></script> -->

</head>
 <body ng-app="myApp">
	<nav class="navbar navbar-inverse navbar-static-top navi" style="margin-bottom:0px">
		<div class="container-fluid">
			<div class="navbar-header">
			 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	     	 </button>
				<a class="navbar-brand" href="#/start"><span class="glyphicon glyphicon-education"> </span> Komiwojazer</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="#/maps"> Mapy</a></li>
				<li><a href="#/stats"> Statystyki</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
			<li><a href="#/chat"><span class="glyphicon glyphicon-comment  "></span></a></li>
			<li><a href="#/info"><span class="glyphicon glyphicon-info-sign"></span></a></li>

			</ul> 
			</div>
		</div>
	</nav>
		<div class="container content">

			<div ng-controller="GreetingController">
			  {{ greeting }}
			</div>

		</div>
	</body>
</html>
