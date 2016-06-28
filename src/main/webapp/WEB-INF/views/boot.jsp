<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap tutorial</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

</head>
<body style="background-color:#337ab7">
	<nav class="navbar navbar-inverse navbar-static-top" style="margin-bottom:0">
		<div class="container-fluid">
			<div class="navbar-header">
			 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	     	 </button>
				<a class="navbar-brand" href="#"><span class="glyphicon glyphicon-education"> </span>Adamus</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li ><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
				<li><a href="#">Page 1</a></li>
				<li><a href="#">Page 2</a></li>
				<li><a href="#">Page 3</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-comment  "></span></a></li>
			<li><a href="#"><span class="glyphicon glyphicon-info-sign"></span></a></li>

			</ul>
			</div>
		</div>
	</nav>

	<div class="container" style=" box-shadow: 0 0 20px 10px #424242; background-color:  #FFF  ">

		<div class="page-header" ><h1>Bootstrap Page</h1></div>




		<div class="row">

			<div class="alert alert-info fade in"> 
				<span class="glyphicon glyphicon-info-sign"></span> Strona nie uzywa plikow cookie
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>
			<div class="hidden-lg hidden-md alert alert-danger fade in"> 
			<span class="glyphicon glyphicon-exclamation-sign"></span>
				Strona moze nie byc wyswietlana poprawnie przy niskich rozdzielczosciach
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			</div>

			<div class="col-md-8">
			<div class="jumbotron">
			 Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
					tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
					quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
					cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
					proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
			 </div>
				<br>
				Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
				Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
				
				
				<p class="text-info">This is some text</p>
				<p class="text-success">Wszystko dziala</p>
				<button type="button" class="btn btn-primary btn-lg">Large</button>
				<button type="button" class="btn btn-primary btn-md">Medium</button>
				<button type="button" class="btn btn-primary btn-sm">Small</button>
				<button type="button" class="btn btn-primary btn-xs">XSmall</button>
					<div class="well well-sm">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
					tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
					quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
					cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
					</div>
				</div>

				<div class="col-md-4">
				
					<div class="well well-sm">
						
						<blockquote >
								Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. 
								<footer>Somebody</footer>
							</blockquote>
						<a href="#">News <span class="badge">5</span></a><br>
						<a href="#">Comments <span class="badge">10</span></a><br>
						<a href="#">Updates <span class="badge">2</span></a> 

						
						<div class="collapse" id="mati">
						
							<div class="text-info text-center">Mati</div>
						</div>	
						<button type="button" class="btn btn-primary btn-block btn-xs" data-toggle="collapse" data-target="#mati">Pokaz obraz</button>
				</div>
				
			</div>

		</div>

			<ul class="nav nav-tabs">
				  <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
				  <li><a data-toggle="tab" href="#menu1">Menu 1</a></li>
				  <li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
				  <li><a data-toggle="tab" href="#menu3">Menu 3</a></li>
				</ul>

				<div class="tab-content">
				  <div id="home" class="tab-pane fade in active">
				    <h3>HOME</h3>
				    <p>Some content.</p>
				    <button type="button" class="btn btn-primary" data-toggle="tab" href="#menu3">Menu2</button>
				    <a data-toggle="tab" href="#menu3">Menu 3</a>
				  </div>
				  <div id="menu1" class="tab-pane fade">
				    <h3>Menu 1</h3>
				    
				    <div class="well well-sm"><p>Some content in menu 1.</p>elo</div>
				  </div>
				  <div id="menu2" class="tab-pane fade">
				    <h3>Menu 2</h3>
				    <div class="progress">
				    	<div class="progress-bar progress-bar-danger" role="progressbar" style="width:20%"></div>
				    	<div class="progress-bar progress-bar-success" role="progressbar" style="width:20%"></div>
						<div class="progress-bar progress-bar-info" role="progressbar" style="width:20%"></div>
				    	<div class="progress-bar progress-bar-success" role="progressbar" style="width:20%"></div>
				    	<div class="progress-bar progress-bar-danger" role="progressbar" style="width:20%"></div>

				    </div>
				  </div>
				  <div id="menu3" class="tab-pane fade">
				  <h3>Menu 3</h3>
				  	<p>Content of hiden menu</p>
				  </div>
				</div>

		<div class="jumbotron"><div class="page-header"><h1>Lorem ipsum
			<div class="btn-group">
			  <button type="button" class="btn btn-primary">Sony</button>
			  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			    <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu" role="menu">
			    <li><a href="#">Tablet</a></li>
			    <li><a href="#">Smartphone</a></li>
			  </ul>
			</div>

		</h1></div> 
		dolor sit amet, consectetur adipisicing elit, sed do eiusmod
		tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
		quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
		consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
		cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
		proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	</div>
	<div class="well well-lg">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
		tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
		quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
		consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
		cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
		proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </div>
		
		<div class="well well-lg ">
		<div class="btn-group"> 
			<button type="button" class="btn btn-default">Default</button>
			<button type="button" class="btn btn-primary">Primary</button>
			<button type="button" class="btn btn-success">Success</button>
			<button type="button" class="btn btn-info">Info</button>
			<button type="button" class="btn btn-link">Link</button>
			<button type="button" class="btn btn-warning">Warning</button>
			<button type="button" class="btn btn-danger">Danger</button>
			
			</div>
		</div> 

		
			 <div class="progress">
			  <div class="progress-bar progress-bar-success" role="progressbar" style="width:40%">
			    40% Complete (success)
			  </div>
			</div>

			<div class="progress">
			  <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow="50"
			  aria-valuemin="0" aria-valuemax="100" style="width:50%">
			    50% Complete (info)
			  </div>
			</div>

			<div class="progress">
			  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
			  aria-valuemin="0" aria-valuemax="100" style="width:60%">
			    60% Complete (warning)
			  </div>
			</div>

			<div class="progress">
			  <div class="progress-bar progress-bar-danger progress-bar-striped active" role="progressbar" aria-valuenow="70"
			  aria-valuemin="0" aria-valuemax="100" style="width:70%">
			    70% Complete (danger)
			  </div>
			</div>

			 <div class="progress">
			  <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" style="width:40%">
			    Free Space
			  </div>
			  <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar" style="width:10%">
			    Warning
			  </div>
			  <div class="progress-bar progress-bar-danger" role="progressbar" style="width:20%">
			    Danger
			  </div>
			</div>

		


		<div class="well well-sm">
			
			 <p>Envelope icon: <span class="glyphicon glyphicon-envelope"></span></p>
			<p>Envelope icon as a link:
			  <a href="mailto:adamus.mat@gmail.com">Adamus.Mat@gmail.com <span class="glyphicon glyphicon-envelope"></span></a>
			</p>
			<p>Search icon: <span class="glyphicon glyphicon-search"></span></p>
			<p>Search icon on a button:
			  <button type="button" class="btn btn-default">
			    <span class="glyphicon glyphicon-search"></span> Search
			  </button>
			</p>
			<p>Search icon on a styled button:
			  <button type="button" class="btn btn-info">
			    <span class="glyphicon glyphicon-search"></span> Search
			  </button>
			</p>
			<p>Print icon: <span class="glyphicon glyphicon-print"></span></p>
			<p>Print icon on a styled link button:
			  <a href="#" class="btn btn-success btn-lg">
			    <span class="glyphicon glyphicon-print"></span> Print
			  </a>
			</p> 
		</div>

		<div class="well">
			<form class="form-horizontal" role="form">
			  <div class="form-group">
			    <label class="control-label col-sm-2" for="email">Email:</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="email" placeholder="Enter email">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="control-label col-sm-2" for="pwd">Password:</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="pwd" placeholder="Enter password">
			    </div>
			  </div>
			  <div class="form-group">
			  	<label class="control-label col-sm-2" for="number">Number:</label>
			  	<div class="col-sm-10">
			  		<input type="range" class="form-control" id="number">
			  	</div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <div class="checkbox">
			        <label><input type="checkbox"> Remember me</label>
			      </div>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default">Submit</button>
			    </div>
			  </div>
			</form>
		</div>

		 <ul class="list-group">
		  <li class="list-group-item "><span class="badge">12</span> New</li>
		  <li class="list-group-item list-group-item-info"><span class="badge">5</span> Deleted</li>
		  <li class="list-group-item"><span class="badge">3</span> Warnings</li>
		</ul>

		 <div class="panel-group" id="accordion">
		  <div class="panel panel-default">
		    <div class="panel-heading">
		      <h4 class="panel-title">
		        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
		        Collapsible Group 1</a>
		      </h4>
		    </div>
		    <div id="collapse1" class="panel-collapse collapse in">
		      <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
		      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
		      minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
		      commodo consequat.</div>
		    </div>
		  </div>
		  <div class="panel panel-primary">
		    <div class="panel-heading">
		      <h4 class="panel-title">
		        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
		        Collapsible Group 2</a>
		      </h4>
		    </div>
		    <div id="collapse2" class="panel-collapse collapse">
		      <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
		      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
		      minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
		      commodo consequat.</div>
		    </div>
		  </div>
		  <div class="panel panel-default">
		    <div class="panel-heading">
		      <h4 class="panel-title">
		        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
		        Collapsible Group 3</a>
		      </h4>
		    </div>
		    <div id="collapse3" class="panel-collapse collapse">
		      <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
		      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
		      minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
		      commodo consequat.</div>
		    </div>
		  </div>
		</div> 
			
		
			<div class="panel panel-default">
			  <div class="panel-heading">Panel Heading</div>
			  <div class="panel-body">Panel Content</div>
			</div>

			<div class="panel-group">
			  <div class="panel panel-default">
			  <div class="panel-heading">Panel Heading</div>
			    <div class="panel-body">Panel Content</div>
			  </div>
			  <div class="panel panel-primary">
			  <div class="panel-heading">Panel Heading</div>
			    <div class="panel-body">Panel Content
			    	<div class="progress">
			    		<div class="progress-bar progress-bar-primary progress-bar-striped active" role="progressbar" style="width:75%">75%</div>
			    	</div>
			    </div>
			  </div>
			  <div class="panel panel-info">
			  <div class="panel-heading">Panel Heading</div>
			    <div class="panel-body">Panel Content</div>
			  </div>
			  <div class="panel panel-default">
			  <div class="panel-heading">Panel Heading</div>
			    <div class="panel-body">Panel Content</div>
			  </div>
			</div>
		

		<div class="btn-group  btn-group-justified btn-group-xs ">
			<div class="btn-group">
				<button type="button" class="btn btn-primary">Apple</button>
				</div><div class="btn-group">
				<button type="button" class="btn btn-warning">Samsung</button>
				</div><div class="btn-group">
				<button type="button" class="btn btn-primary">Sony</button>
				</div><div class="btn-group">
				<button type="button" class="btn btn-danger">Samsung</button>
				</div><div class="btn-group">
				<button type="button" class="btn btn-primary">Sony</button>
				</div><div class="btn-group">
				<button type="button" class="btn btn-warning">Samsung</button>
				</div><div class="btn-group">
				<button type="button" class="btn btn-primary">Sony</button>
				</div>
			</div>
	
			 <ul class="pager">
			  <li class="previous"><a href="#">Previous</a></li>
			  <li class="next"><a href="#">Next</a></li>
			</ul>
	</div>

<footer class="container-fluid text-center small" style="background-color:#222; margin-top:0px border-top:10px solid #000">
  <p class="text-muted" style="padding:7px; margin-bottom:0; ">Mateusz Adamus</p>
</footer>

	</body>
	</html>