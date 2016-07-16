var app = angular.module("controllers", [ 'drawingsService']);

app.controller('mapsController', [ '$scope', '$http', 'draw',
		function($scope, $http, draw) {
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

			$scope.maps= mapsService;
			
			$scope.getClass = function(string, val, bar){
				
				if(val == -1)
					return string + "danger";
				else if(val == 100)
					return string + "success";
				else if(val == 0)
					return string + "default";
				
				if (bar != undefined && bar){
					return string + "primary progress-bar-striped active";
				}
				return string + "primary";
			};
			
		} ]);

app.controller('mapController', [ '$scope', '$http', 'draw','$routeParams',
		function($scope, $http, draw, $routeParams) {
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
			
			$scope.id = $routeParams.id;
			$scope.seconds = 5;
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
		'$timeout',
		function($scope, $http, $timeout) {
			$scope.message={author:"",message:""};
			
			$timeout(function() {
				$http.get("chat/topics").then(
						function(response) {
							if (response.status == 200)
									$scope.topics = JSON.parse(JSON
											.stringify(response.data));
						});
			}, 200);

			$scope.setActive = function(val, title) {
				$scope.active = val;
				$scope.title = title;
				$http.get("chat/messages?id=" + val).then(
						function(response) {
							if (response.status == 200) {
								$scope.messages = JSON.parse(JSON
										.stringify(response.data));
							}
						});
			};

			$scope.refresh = function() {
				$http.get("chat/messages?id=" + $scope.active).then(
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
					$http.post("chat/addMessage?topicId="+$scope.active,$scope.message).then(
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
