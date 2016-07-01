angular.module("myApp", ['controllers','ngRoute']).config(function($routeProvider){
	$("[data-toggle='tooltip']").tooltip();
	
	$routeProvider
	.when('/start',{
		controller:'greetingController',
		templateUrl: 'start.html'
	})
	.when('/chat',{
		controller:'chatController',
		templateUrl: 'chat.html'
	})
	.when('/info',{
		templateUrl: 'info.html'
	})
	.when('/maps',{
		templateUrl: 'maps.html'
	})
	.when('/stats',{
		templateUrl: 'stats.html'
	})
	.when('/edit',{
		templateUrl: 'edit.html'
	})
	.when('/edit',{
		templateUrl: 'edit.html'
	})
	.when('/map',{
		templateUrl: 'map.html'
	})
	.otherwise({
		redirectTo:'/start'
	});
});

