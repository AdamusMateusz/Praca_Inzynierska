var app = angular.module("myApp",[]);

app.controller('indexController',['$scope','$http',function($scope,$http){
	
	$scope.quantity = 5;
	$scope.parents = 10;
	$scope.iterations = {
		iterations : 1,
		kids : 20,
		change : 20,
		crossingGene : 50,
		crossingChromosome : 50,
		mutationGene : 5,
		mutationChromosome : 5,
		useParents : true
	};
	
	$scope.init = function(){

		ctx1 = document.getElementById("layer1").getContext("2d");
		ctx2 = document.getElementById("layer2").getContext("2d");
	}
	
	$scope.start = function(){
		$http.get('http://' + window.location.hostname + '/start?quantity='+$scope.quantity).success(function(data, status, headers, config) {
			if (status == 200) {
				$scope.cities = JSON.parse(JSON.stringify(data));
			} else
				console.error("Wystąpił błąd: " + status);
			
		});
	}
	
	$scope.iterationsFun = function(){
		
		$http.post('http://' + window.location.hostname +'/iterations',$scope.iterations)
		.success(function(data, status, headers, config) {
			if (status == 200) {
				$scope.routes = JSON.parse(JSON.stringify(data));
				$scope.drawRoute(0);
				
				if($scope.redo)
					$scope.iterationsFun();
				
			} else
				console.error("Wystąpił błąd: " + status);
			
			
		});
		
	}
	
	$scope.$watch("cities",function(){
		if($scope.cities != null){
			
			$http.get('http://' + window.location.hostname + '/parents?parents='+$scope.parents).success(function(data, status, headers, config) {
				if (status == 200) {
					$scope.routes = JSON.parse(JSON.stringify(data));
					$scope.drawRoute(0);
				} else
					console.error("Wystąpił błąd: " + status);
			});
					
			clearCities();
			clearWays();
			$scope.cities.forEach(function(element,index){
				drawCity(element,index);
			});
		}
	});
	
	$scope.$watch("autoCrossingChromosome",function(value){
		if(value)
			$scope.iterations.crossingChromosome = -1;
		else
			$scope.iterations.crossingChromosome = 50;
	});
	
	$scope.$watch("autoCrossingGene",function(value){
		if(value)
			$scope.iterations.crossingGene = -1;
		else
			$scope.iterations.crossingGene = 50;
	});
	
	
	$scope.$watch("autoMutationChromosome",function(value){
		if(value)
			$scope.iterations.mutationChromosome = -1;
		else
			$scope.iterations.mutationChromosome = 5;
	});
	
	$scope.$watch("autoMutationGene",function(value){
		if(value)
			$scope.iterations.mutationGene = -1;
		else
			$scope.iterations.mutationGene = 5;
	});
	
	
	$scope.drawRoute= function(number){
		clearWays();

		for(var i=0; i<$scope.routes[number].cities.length -1;i++){
			linkCities($scope.cities[$scope.routes[number].cities[i]], $scope.cities[$scope.routes[number].cities[i+1]]);
		}
		
		linkCities($scope.cities[$scope.routes[number].cities[0]],$scope.cities[$scope.routes[number].cities[$scope.routes[number].cities.length -1]]);
		
	}
	
	
}]);

function City(x,y){
	this.x=x;
	this.y=y;
}
function drawRect(x,y,number){
	ctx1.beginPath();
	ctx1.fillStyle = "#FFF";
	ctx1.arc(x+20,y+20,18,0,2*Math.PI);
	ctx1.fill();
	
	ctx1.strokeStyle = "#000";
	ctx1.arc(x+20,y+20,19,0,2*Math.PI);
	ctx1.stroke();
	
	ctx1.font = "20px Arial";
	
	if(number < 10)
		ctx1.strokeText(number,x+14,y+26);
	else
		ctx1.strokeText(number,x+9,y+26);
}
function drawCity(city,number){
	ctx1.beginPath();
	ctx1.fillStyle = "#FFF";
	ctx1.arc(city.x+20,city.y+20,18,0,2*Math.PI);
	ctx1.fill();
	
	ctx1.strokeStyle = "#000";
	ctx1.arc(city.x+20,city.y+20,19,0,2*Math.PI);
	ctx1.stroke();
	
	ctx1.font = "20px Arial";
	
	if(number < 10)
		ctx1.strokeText(number,city.x+14,city.y+26);
	else
		ctx1.strokeText(number,city.x+9,city.y+26);
}
function drawLine(x1,y1,x2,y2){
	ctx2.beginPath();
	ctx2.lineWidth=2;
	ctx2.moveTo(x1,y1);
	ctx2.lineTo(x2,y2);
	ctx2.stroke();
}
function linkCities(city1,city2){
	drawLine(city1.x+20,city1.y+20,city2.x+20,city2.y+20);
};

function clearCities(){
	ctx1.clearRect(0, 0, 1000, 600);
}
function clearWays(){
	ctx2.clearRect(0, 0, 1000, 600);
}
var ctx1,ctx2;	