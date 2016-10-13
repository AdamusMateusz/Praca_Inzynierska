angular.module('chatControllerModule',[]).controller('chatController', [
                                           		'$scope',
                                        		'$http',
	function($scope, $http) {
		$scope.message={author:"",message:""};

	
	$http.get("chat/topics").then(
			function(response) {
				if (response.status == 200){
						$scope.topics = JSON.parse(JSON
								.stringify(response.data));
				}
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
			$http.post("chat/addTopic", $scope.chatTitle)
			.then(
					function(response) {
						if (response.status == 200) {
							/* $scope.topics.push(new Topic(
									$scope.chatTitle, 0)); */
							$scope.chatTitle = "";
						}
					})
		}
	};
	
	$scope.addMessage = function() {
		if(validate($scope.message)){
			$http.post("chat/addMessage/"+$scope.active,$scope.message)
			.then(
					function(response) {
						if (response.status == 200) {
							/*$scope.messages.push(new Message($scope.message.author,$scope.message.message));	
							$scope.topics[$scope.active].messagesCount++;*/
							$scope.message.message="";
						}
					})
		}
	};
	
	var stompClient = null;

	var socket = new SockJS('/komiwojazer/socket');
	stompClient = Stomp.over(socket);  

	stompClient.connect({}, function(frame){

		stompClient.subscribe('/topic/topics', function(topic) {
			$scope.topics.push(new Topic(topic.body,0));
			$scope.$apply();
		});	
		
		stompClient.subscribe('/topic/messages', function(message) {
			
			$scope.topics[message.headers.topicID].messagesCount++;
			if(message.headers.topicID == $scope.active){
				let body = JSON.parse(message.body);
				$scope.messages.push(new Message(body.author,body.message));
			}
			$scope.$apply();
		});	
		
	});
	
	$scope.$on("$destroy", function() {
		stompClient.disconnect();
	});
	
} ]);