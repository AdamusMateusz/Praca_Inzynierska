angular.module('errorControllerModule',[]).controller('errorController', ['$scope','$routeParams',function($scope,$routeParams){

	$scope.id = $routeParams.id;
	if($routeParams.message == 'nf')
		$scope.txt= "PAGE NOT FOUND";
	else
		$scope.txt = $routeParams.message;
}]);