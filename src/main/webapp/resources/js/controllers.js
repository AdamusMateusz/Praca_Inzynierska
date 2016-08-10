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

			$scope.request = {
					quantity : 5,
					parents : 10,
					kids : 20,
					change : 20,
					crossingGene : 50,
					crossingChromosome : 50,
					mutationGene : 5,
					mutationChromosome : 5,
					useParents : true
				};
			
			$http.get("komiwojazer/getMap/" + $scope.id).then(
					function(response) {
						if (response.status == 200) {
							$scope.map = JSON.parse(JSON
									.stringify(response.data));
							//Sets points of route
							$scope.setPoints();
						}
					});
			
			$http.get("komiwojazer/getRequest/" + $scope.id).then(
					function(response) {
						if (response.status == 200) {
							$scope.request = JSON.parse(JSON
									.stringify(response.data));
						}
					});
			
			$scope.points = "";
			$scope.seconds = 5;
			$scope.selectedRoute = 0;
			$scope.showNumbers = false;
			$scope.setPoints = function(){
				var s = "";
				
				$scope.map.parents[$scope.selectedRoute].cities
				.forEach(function(elem){
					s = s+ $scope.map.cities[elem].x +","+$scope.map.cities[elem].y/2+" ";
				
				});
				
				s += $scope.map.cities[$scope.map.parents[$scope.selectedRoute].cities[0]].x;
				s += ","+$scope.map.cities[$scope.map.parents[$scope.selectedRoute].cities[0]].y/2+" ";
				
				$scope.points = s;
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
