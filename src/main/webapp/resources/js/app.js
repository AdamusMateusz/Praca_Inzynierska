angular.module("myApp", [ 'controllers', 'ngRoute' ,'ui.bootstrap','ngAnimate' ]).config(
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
			}).when('/map/:id', {
				controller : 'mapController',
				templateUrl : 'map.html'
			}).when('/error/:id/:message', {
				templateUrl : 'error.html',
				controller : 'errorController'
			}).when('/', {
				redirectTo : '/start',
			}).otherwise({
				redirectTo : '/error/404/nf'
			});
			
		});
