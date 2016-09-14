angular.module('statsControllerModule',[]).controller('statsController', [ '$scope', '$http', '$timeout',
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