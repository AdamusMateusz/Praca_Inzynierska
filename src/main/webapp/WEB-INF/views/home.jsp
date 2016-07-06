<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">

<title>Home</title>

<!-- AngularJS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular-route.min.js"></script>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- My stuff -->
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/app.js" />"></script>
<script src="<c:url value="/resources/js/controllers.js" />"></script>
<script src="<c:url value="/resources/js/drawings.js" />"></script>
<script src="<c:url value="/resources/js/model.js" />"></script>

</head>
<body ng-app="myApp">
	<nav class="navbar navbar-inverse navbar-static-top navi"
		style="margin-bottom: 0px">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#/start"><span
					class="glyphicon glyphicon-education"> </span> Komiwojazer</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="#/maps"> Mapy</a></li>
					<li><a href="#/stats"> Statystyki</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#/chat" data-toggle="tooltip"
						data-placement="left" title="Chat"><span
							class="glyphicon glyphicon-comment"></span></a></li>
					<li><a href="#/info" data-toggle="tooltip"
						data-placement="bottom" title="Info"><span
							class="glyphicon glyphicon-info-sign"></span></a></li>

				</ul>
			</div>
		</div>
	</nav>
	<div class="container content">


		<div class="row">
				<div class="alert alert-info fade in">
		<span class="glyphicon glyphicon-info-sign"></span> Strona uzywa plikow cookie <a
			class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
		</div>


		<div ng-view>
			<div class="load">Loading site ...</div>
		</div>


		<div class="row">
			<div class="small bg-info text-center text-info">Mateusz Adamus</div>
		</div>

	</div>
</body>
</html>
