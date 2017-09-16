<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- Chrome, Firefox OS, Opera and Vivaldi -->
<meta name="theme-color" content="#337ab7">
<!-- Windows Phone -->
<meta name="msapplication-navbutton-color" content="#337ab7">
<!-- iOS Safari -->
<meta name="apple-mobile-web-app-status-bar-style" content="#337ab7">
<title>Problem komiwojazera</title>
<link rel="icon" href="<c:url value="/resources/images/icon.png" />">

<!-- AngularJS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-route.min.js"></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-animate.js"></script>


<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- Bootstrap and Angular integration-->
<script src="http://angular-ui.github.com/bootstrap/ui-bootstrap-tpls-0.1.0-SNAPSHOT.js"></script>

<!-- My stuff -->
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/app.js" />"></script>
<script src="<c:url value="/resources/js/model.js" />"></script>

<!-- Angular Controllers -->
<script src="<c:url value="/resources/js/controllers.js" />"></script>
<script src="<c:url value="/resources/js/mapsController.js" />"></script>
<script src="<c:url value="/resources/js/mapController.js" />"></script>
<script src="<c:url value="/resources/js/chatController.js" />"></script>
<script src="<c:url value="/resources/js/errorController.js" />"></script>

<!-- WebSockets -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.1/sockjs.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>

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
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#/chat" data-toggle="tooltip"
						data-placement="left" title="Chat">
						<div class="hidden-lg hidden-md hidden-sm">Chat</div><span
							class="glyphicon glyphicon-comment"></span></a></li>
					<li><a href="#/info" data-toggle="tooltip"
						data-placement="bottom" title="Info"><div class="hidden-lg hidden-md hidden-sm">Info</div><span
							class="glyphicon glyphicon-info-sign"></span></a></li>

				</ul>
			</div>
		</div>
	</nav>
	<div class="container content">

	<!-- 
		<div class="row">
				<div class="alert alert-info fade in">
		<span class="glyphicon glyphicon-info-sign"></span> Strona uzywa plikow cookie <a
			class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
		</div>
		-->

	
		<div ng-view >
			
		<div class="svg-container-watch">
			
				<svg class="svg-content" version="1.1" id="Warstwa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
				 viewBox="0 0 500 500" style="enable-background:new 0 0 500 500;" xml:space="preserve">
				<circle id="tarcza" style="fill:none;stroke:#333333;stroke-width:16;stroke-miterlimit:10;" cx="250" cy="250" r="218.178"/>
				<line id="podzialka" style="fill:none;stroke:#333333;stroke-width:10;stroke-miterlimit:10;" x1="250" y1="66.413" x2="250" y2="106.667"/>
				<line id="podzialka_2_" style="fill:none;stroke:#333333;stroke-width:10;stroke-miterlimit:10;" x1="120.185" y1="120.185" x2="148.648" y2="148.648"/>
				<line id="podzialka_1_" style="fill:none;stroke:#333333;stroke-width:10;stroke-miterlimit:10;" x1="66.413" y1="250" x2="106.667" y2="250"/>
				<line id="podzialka_3_" style="fill:none;stroke:#333333;stroke-width:10;stroke-miterlimit:10;" x1="120.185" y1="379.815" x2="148.648" y2="351.352"/>
				<line id="podzialka_4_" style="fill:none;stroke:#333333;stroke-width:10;stroke-miterlimit:10;" x1="250" y1="433.587" x2="250" y2="393.333"/>
				<line id="podzialka_5_" style="fill:none;stroke:#333333;stroke-width:10;stroke-miterlimit:10;" x1="379.815" y1="379.815" x2="351.352" y2="351.352"/>
				<line id="podzialka_6_" style="fill:none;stroke:#333333;stroke-width:10;stroke-miterlimit:10;" x1="433.587" y1="250" x2="393.333" y2="250"/>
				<line id="podzialka_7_" style="fill:none;stroke:#333333;stroke-width:10;stroke-miterlimit:10;" x1="379.815" y1="120.185" x2="351.352" y2="148.648"/>
				<circle id="srodek" style="fill:#333333;" cx="250" cy="250" r="12.861"/>
				<line id="godziny" style="fill:none;stroke:#333333;stroke-width:5;stroke-miterlimit:10;" x1="250" y1="250" x2="250" y2="181.667"/>
				<line id="minuty" style="fill:none;stroke:#333333;stroke-width:5;stroke-miterlimit:10;" x1="250" y1="250" x2="250" y2="120.185"/>
				<line id="sekundy" style="fill:none;stroke:#D9534F;stroke-width:4;stroke-miterlimit:10;" x1="250" y1="273.667" x2="250" y2="98"/>
			</svg>
		
			
			</div>
			<div class="text-center">Trwa ladowanie..</div>
		</div>


		<div class="row">
			<div class="small bg-info text-center text-info">Mateusz Adamus</div>
		</div>

	</div>
</body>
</html>
