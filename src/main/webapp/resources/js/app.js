angular.module("myApp", [ 'controllers', 'ngRoute' ]).config(
		function($routeProvider) {
			$("[data-toggle='tooltip']").tooltip();

			$routeProvider.when('/start', {
				templateUrl : 'start.html'
			}).when('/chat', {
				controller : 'chatController',
				templateUrl : 'chat.html'
			}).when('/info', {
				templateUrl : 'info.html'
			}).when('/maps', {
				controller : 'mapsController',
				templateUrl : 'maps.html'
			}).when('/stats', {
				controller : 'statsController',
				templateUrl : 'stats.html'
			}).when('/edit', {
				templateUrl : 'edit.html'
			}).when('/edit', {
				templateUrl : 'edit.html'
			}).when('/map', {
				templateUrl : 'map.html'
			}).otherwise({
				redirectTo : '/start'
			});

		});
