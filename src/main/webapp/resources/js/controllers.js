var app = angular.module("controllers", ['drawingsService']);

app.controller('greetingController', [ '$scope', '$http', 'draw',
		function($scope, $http,draw) {
			$scope.greeting = "Hello";
			
		} ]);

app.controller('chatController', [ '$scope', '$http',
   		function($scope, $http) {
   			$scope.greeting = "Hello"
   		} ]);