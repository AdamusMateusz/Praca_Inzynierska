 angular.module("mapsControllerModule", []).controller('mapsController', [ '$scope', '$http', '$location',
		function($scope, $http,$location) {
			$scope.lastID = -1;
			$scope.maps = [];
			
			$scope.request = {
				citiesQuantity: 5,
				parents: 10,
				kids : 20,
				change : 20,
				crossingGene : 50,
				crossingChromosome : 50,
				mutationGene : 5,
				mutationChromosome : 5,
				useParents : true,
				saveFittingFunctionValue: false,
				stopped: true
			};

			$scope.newMap = function(){
				$http.post("komiwojazer/startMap?stopped="+$scope.request.stopped,$scope.request)
				.then(function(response){
					if(response.status == 200){
						$location.path('map/' + response.data).replace();
					}
				}
						);
			};
			
			$scope.getTick = function(val){
				return val ? "glyphicon glyphicon-ok" : "glyphicon glyphicon-remove" ;
			}
			$scope.getClass = function(string, map, bar){
				
				if(!map.running)
					return string + "default";
				else if(!map.heuristic)
					return string + "info";
				else if(map.complete)
					return string + "success";
				
					
				if (bar != undefined && bar){
					return string + "primary progress-bar-striped active";
				}
				return string + "primary";
			};
			
			$scope.loadMore = function(last){
				$http.get("komiwojazer/getMapsOverview/" +  last).then(
						function(response) {
							if (response.status == 200) {
								var entries = JSON.parse(JSON
										.stringify(response.data));
								if(entries.length > 0){
									entries.forEach(function(element){
												$scope.maps.push(element);
											});
									$scope.lastID = entries[entries.length-1].id;
								}
								
							}
						});
			}
			
			$scope.loadMore($scope.lastID);
			$scope.loadAll = function(){
				$http.get("komiwojazer/getAllMapsOverview" ).then(
						function(response) {
							if (response.status == 200) {
								$scope.maps = JSON.parse(JSON
										.stringify(response.data));
								
							}
						});
			};
			
			
		} ]);