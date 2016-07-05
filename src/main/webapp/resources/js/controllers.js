var app = angular.module("controllers", ['drawingsService']);

app.controller('mapsController', [ '$scope', '$http', 'draw',
		function($scope, $http, draw) {

		} ]);

app.controller('statsController', [ '$scope', '$http', '$timeout',
		function($scope, $http, $timeout) {
			
		} ]);

app.controller('chatController', [ '$scope', '$http','$timeout', function($scope, $http,$timeout) {

	$timeout(function(){$http.get("chat/topics").then(function(response){
		if(response.status == 200){
			$scope.topics = JSON.parse(JSON.stringify(response.data));
		}
	});},250);
	
	$scope.setActive = function(val,title) {
		$scope.active = val;
		$scope.title = title;
		$http.get("chat/messages?id="+val).then(function(response){
			if(response.status == 200){
				$scope.messages = JSON.parse(JSON.stringify(response.data));
			}
		});
	};
	
	$scope.refresh = function(){
		if($scope.active != undefined)
			$http.get("chat/messages?id="+$scope.active).then(function(response){
				if(response.status == 200){
					$scope.messages = JSON.parse(JSON.stringify(response.data));
				}
			});
	}
	
} ]);

function Message(autor,tekst){
	this.autor=autor;
	this.tekst=tekst;
}

function Topic(id,title,count){
	this.id = id;
	this.title=title;
	this.count=count;
}