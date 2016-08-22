var app = angular.module("controllers", []);

app.controller('mapsController', [ '$scope', '$http', 
		function($scope, $http) {
			$scope.lastID = -1;
			$scope.maps = [];
			
			$scope.request = {
				quantity: 5,
				parents: 10,
				kids : 20,
				change : 20,
				crossingGene : 50,
				crossingChromosome : 50,
				mutationGene : 5,
				mutationChromosome : 5,
				useParents : true
			};

			
			$scope.getTick = function(val){
				return val ? "glyphicon glyphicon-ok" : "glyphicon glyphicon-remove" ;
			}
			$scope.getClass = function(string, map, bar){
				
				if(!map.running)
					return string + "default";
				else if(!map.heuristic)
					return string + "info";
				else if(map.complete)
					return string + "success";
				
					
				if (bar != undefined && bar){
					return string + "primary progress-bar-striped active";
				}
				return string + "primary";
			};
			
			$scope.loadMore = function(last){
				$http.get("komiwojazer/getMapsOverview/" +  last).then(
						function(response) {
							if (response.status == 200) {
								var entries = JSON.parse(JSON
										.stringify(response.data));
								if(entries.length > 0){
									entries.forEach(function(element){
												$scope.maps.push(element);
											});
									$scope.lastID = entries[entries.length-1].id;
								}
								
							}
						});
			}
			$scope.loadMore($scope.lastID);
			$scope.loadAll = function(){
				$http.get("komiwojazer/getAllMapsOverview" ).then(
						function(response) {
							if (response.status == 200) {
								$scope.maps = JSON.parse(JSON
										.stringify(response.data));
								
							}
						});
			};
			
			
		} ]);

app.controller('mapController', [ '$scope', '$http','$routeParams',
		function($scope, $http, $routeParams) {
		
			$scope.id = $routeParams.id;
			$scope.running = true;
			$scope.showPositive = {value:true};
			$scope.pointsOfFitting ="";
			$scope.points = "";
			$scope.seconds = 5;
			$scope.selectedRoute = 0;
			$scope.showNumbers = false;
			$scope.fittingValues= [100,75,50,25];
			$scope.svg = {minimum: {txt:0,h:50}};
			$scope.svg.lines=[{txt:100,h:10/2},
						{txt:75,h:(((1000 - (10/2)) - (50))/2)/4},
						{txt:50,h:(((1000 - (10/2)) - (50))/2)/2},
						{txt:25,h:3*(((1000 - (10/2)) - (50))/2)/4},
	                    {txt:0,h:(((1000 - (10/2)) - (50))/2)}];
		
			$scope.$watch('showPositive.value',function(){
				$scope.setPointsofFittingFunction();
			});
			

			$http.get("komiwojazer/getMap/" + $scope.id).then(
					function(response) {
						if (response.status == 200) {
							$scope.map = JSON.parse(JSON
									.stringify(response.data));
							//Sets points of route
							$scope.setPoints();
						}
					});
			
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
				
				var cords = $scope.map.cities;
				var cities = $scope.map.parents[$scope.selectedRoute].cities
				
				var s = "M " + cords[cities[0]].x + " " + cords[cities[0]].y/2+" ";
				
				for(var i=1; i< cities.length;i++){
					s+= "L " + cords[cities[i]].x + " " + cords[cities[i]].y/2+" ";
				}
				
				$scope.points = s + "Z";
			};
			
			$scope.getY = function(value){
				return 0;
			}
			
			$scope.setPointsofFittingFunction = function(){
				var elements = $scope.fittingValues;
				
				if($scope.showPositive.value){
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
				
			}
			
			
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
		
			
		} ]);

app.controller('statsController', [ '$scope', '$http', '$timeout',
		function($scope, $http, $timeout) {
			$scope.progress = 0;
			$scope.test = function(){

				$timeout(function(){
					$scope.progress+=10;
					if($scope.progress < 100)
						$scope.test();
					else
						$timeout(function(){
							$scope.progress = -1;
						},7000);
				},(Math.random()*750));
			}
		} ]);

app.controller('chatController', [
		'$scope',
		'$http',
		function($scope, $http) {
			$scope.message={author:"",message:""};
			
			
			$http.get("chat/topics").then(
					function(response) {
						if (response.status == 200)
								$scope.topics = JSON.parse(JSON
										.stringify(response.data));
					});


			$scope.setActive = function(val, title) {
				$scope.active = val;
				$scope.title = title;
				$http.get("chat/messages/" + val).then(
						function(response) {
							if (response.status == 200) {
								$scope.messages = JSON.parse(JSON
										.stringify(response.data));
							}
						});
			};

			$scope.refresh = function() {
				$http.get("chat/topics").then(
						function(response) {
							if (response.status == 200)
									$scope.topics = JSON.parse(JSON
											.stringify(response.data));
						});
				
				$http.get("chat/messages/" + $scope.active).then(
						function(response) {
							if (response.status == 200) {
								$scope.messages = JSON.parse(JSON
										.stringify(response.data));
							}
						});
			};

			$scope.addTopic = function() {
				if (validate($scope.chatTitle)) {
					$http.post("chat/addTopic", $scope.chatTitle).then(
							function(response) {
								if (response.status == 200) {
									$scope.topics.push(new Topic(
											$scope.chatTitle, 0));
								}
							})
				}
			};
			
			$scope.addMessage = function() {
				if(validate($scope.message)){
					$http.post("chat/addMessage/"+$scope.active,$scope.message).then(
							function(response) {
								if (response.status == 200) {
									$scope.messages.push(new Message($scope.message.author,$scope.message.message));	
									$scope.topics[$scope.active].messagesCount++;
									$scope.message.message="";
								}
							})
				};
			}
			
		} ]);
