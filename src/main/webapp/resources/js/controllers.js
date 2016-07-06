var app = angular.module("controllers", [ 'drawingsService']);

app.controller('mapsController', [ '$scope', '$http', 'draw',
		function($scope, $http, draw) {

		} ]);

app.controller('statsController', [ '$scope', '$http', '$timeout',
		function($scope, $http, $timeout) {

		} ]);

app.controller('chatController', [
		'$scope',
		'$http',
		'$timeout',
		function($scope, $http, $timeout) {
			$scope.sent=false;
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
									$scope.sent=true;
								}
							})
				};
			}
			
		} ]);
