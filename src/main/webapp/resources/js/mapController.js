angular.module('mapControllerModule',[])
.controller('mapController', [ '$scope', '$http','$routeParams','$timeout','$location','$window',
function($scope, $http, $routeParams,$timeout,$location,$window) {
	
	$scope.id = $routeParams.id;
	$scope.running = true;
	//angular ma problem z prostymi wartościami przy sprawdzaniu watchem,
	//więc dodany jest obiekt z jedną wartością
	$scope.showPositive = {value:true};
	$scope.pointsOfFitting ="";
	$scope.points = "";
	$scope.seconds = {seconds: 2.0};
	$scope.selectedRoute = 0;
	$scope.showNumbers = false;
	$scope.fittingValues= [100,75,50,25];
	$scope.svg = {minimum: {txt:0,h:50}};
	$scope.svg.avg = {txt:0,h:50};
	$scope.distance = {length:0};
	
	$scope.svg.lines=[{txt:100,h:10/2},
				{txt:75,h:(((1000 - (10/2)) - (50))/2)/4},
				{txt:50,h:(((1000 - (10/2)) - (50))/2)/2},
				{txt:25,h:3*(((1000 - (10/2)) - (50))/2)/4},
                {txt:0,h:(((1000 - (10/2)) - (50))/2)}];

	$scope.$watch('showPositive.value',function(){
		$scope.setPointsofFittingFunction();
	});
	
	$scope.$watch('distance.txt',function(){
		var cities = $scope.distance.txt.split(",");
		var result = 0;

		for(var i = 1; i < cities.length; i++){
			result += $scope.map.distanceMatrix[Number.parseInt(cities[i-1])][Number.parseInt(cities[i])];
		}
		
		result += $scope.map.distanceMatrix[Number.parseInt(cities[cities.length-1])][Number.parseInt(cities[0])]
		
		$scope.distance.length = result|| 0;
	});
	
	$scope.getMap = function(){
		$http.get("komiwojazer/getMap/" + $scope.id).then(
				function(response) {
					if (response.status == 200) {
						$scope.map = JSON.parse(JSON
								.stringify(response.data));
						//Sets points of route
						$scope.setPoints();
					}
				});
	};
	$scope.getMap();
	
	$http.get("komiwojazer/getFittingFunctionValues/" + $scope.id).then(
			function(response) {
				if (response.status == 200) {
					$scope.fittingValues = JSON.parse(JSON
							.stringify(response.data));
					//Sets points of route
					for(var i=0; i < $scope.fittingValues.length; i++){
						if($scope.fittingValues[i] != -1)
							$scope.fittingValues[i] *=1000;
					}
					$scope.setPointsofFittingFunction();
				}
			});
	
	$http.get("komiwojazer/getRequest/" + $scope.id).then(
			function(response) {
				if (response.status == 200) {
					$scope.request = JSON.parse(JSON
							.stringify(response.data));
				}
			});
	
	$http.get("komiwojazer/isRunningMap/" + $scope.id).then(
			function(response) {
				if (response.status == 200) {
					$scope.running = JSON.parse(JSON
							.stringify(response.data));
				}
			});		
	
	$scope.setPoints = function(){
		if($scope.map.cities != undefined){
			var cords = $scope.map.cities;
			var cities = $scope.map.parents[$scope.selectedRoute].cities
			var s = "M " + cords[cities[0]].x + " " + cords[cities[0]].y/2+" ";
			
			for(var i=1; i< cities.length;i++){
				s+= "L " + cords[cities[i]].x + " " + cords[cities[i]].y/2+" ";
			}
			
			$scope.points = s + "Z";
		}
	};
	
	$scope.getY = function(value){
		return 0;
	};
	
	$scope.setPointsofFittingFunction = function(){
		var elements = $scope.fittingValues;
		
		if($scope.showPositive.value){
			//exclude -1
			elements = elements.slice(elements.lastIndexOf(-1)+1);
		}
		
		var max = Math.max.apply(null,elements);
		var min = $scope.showPositive.value? 0 : Math.min.apply(null,elements);
		
		$scope.svg.lines[0].txt = max;
		$scope.svg.lines[1].txt = 3*(max+min)/4;
		$scope.svg.lines[2].txt = (max+min)/2;
		$scope.svg.lines[3].txt = (max+min)/4;
		$scope.svg.lines[4].txt = min;
		
		var left = 50;
		var	right = 995;
		var	top = 10/2;
		var	bottom = ((1000 - (10/2)) - (50))/2;
		var	jmpX = (right-left)/(elements.length-1);
		var positionX = left;
		var range = max - min;
		var jmpY = (bottom-top)/(range-1);

		//Create path attribute
		$scope.getY = function(value){
			return Math.abs((bottom+top) - (top + (jmpY * value)));
		};
		
		$scope.svg.minimum.txt = elements[elements.length-1];
		$scope.svg.minimum.h = $scope.getY(elements[elements.length-1]);
		
		var s = "M " + positionX + " " + bottom +" ";
		
		for(var i = 0; i< elements.length; i++){
			s+= "L " + positionX + " " + $scope.getY(elements[i]);
			positionX += jmpX;
		}
		
		s+= "L " + right + " " + bottom;
		$scope.pointsOfFitting = s + " Z";
		
		//Compute average

		var avg =elements.reduce( function(a,b){return  a+b;} ) / elements.length;

		$scope.svg.avg.txt = avg;
		$scope.svg.avg.h = $scope.getY(avg);
	};
	
	
	$scope.stop = function(){
		$http.patch("komiwojazer/stopMap/" + $scope.id).then(
				function(response) {
					if (response.status == 200) {
						var stopped = JSON.parse(JSON
								.stringify(response.data));
						if(stopped){
							$scope.running = false;
						}
						
					}
				});
	};
	
	$scope.resume = function(){
		$http.patch("komiwojazer/resumeMap/" + $scope.id).then(
				function(response) {
					if (response.status == 200) {
						var running = JSON.parse(JSON
								.stringify(response.data));
						if(running){
							$scope.running = true;
						}
					}
				});
	};
	
	$scope.selectRoute = function(index){
		$scope.selectedRoute = index;
		$scope.setPoints();
	};
	
	$scope.refreshButton = function(){
		document.querySelector("#refreshButton").setAttribute("disabled","true");
		$scope.refresh($scope.seconds * 1000);
	}
	
	$scope.refresh = function(){
		$timeout(function(){
			$scope.getMap();
			$scope.refresh();

		},$scope.seconds.seconds * 1000);
	};

	
	$scope.updateRequest = function(){
		$http.put("komiwojazer/changeRequest/"+$scope.id+"/?stopped="+$scope.request.stopped,$scope.request)
		.then(function(response){
		    $window.location.reload();
		});
	};
	
	$scope.deleteThisMap = function(){
		$http({
			  method: 'DELETE',
			  url: 'komiwojazer/deleteMap/' + $scope.id
			}).then(function(response){
				if(response.status == 200)
					$location.path('maps').replace();
			});
	}
	
} ]);
