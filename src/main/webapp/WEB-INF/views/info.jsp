<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sztuczna inteligencja</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/app.js" />"></script>



</head>
<body ng-app="myApp">
	<div ng-controller="indexController" ng-init="init()">
		<div style="position: relative;">
			<canvas id="layer1" width="1000" height="600" style="z-index: 1"></canvas>
			<canvas id="layer2" width="1000" height="600" style="z-index: 0"></canvas>
		</div>

		<div class="navbar">
			<h3>Opcje:</h3>
			<br> <span>Ilosc miast:</span> <input type="range" min="5" max="99"
				ng-model="quantity">
			<br>
			<span>Ilosc rodziców:</span> <input type="number" min="2" ng-model="parents">
			<button ng-click="start()" >{{quantity}}</button>
			<br>
			<hr>
			<br>
			<span>Licznosc potomkow:</span> <input type="number" ng-model="iterations.kids">
			<br>
			<span>Prawdopodobienstwo krzyzowania:<br>
			Chromosomu:
			<input type="range" min="1" max="100" ng-model="iterations.crossingChromosome" ng-disabled="autoCrossingChromosome">{{iterations.crossingChromosome}}
			<input type="checkbox" ng-model="autoCrossingChromosome" >auto
			</span>
			<br>
			Genu:
			<input type="range" min="1" max="100" ng-model="iterations.crossingGene" ng-disabled="autoCrossingGene">{{iterations.crossingGene}}
			<input type="checkbox" ng-model="autoCrossingGene" >auto
			</span>
			<br>
			<span>Prawdopodobiesstwo mutacji:<br>
			Chromosomu:
			<input type="range" min="0.1" max="10" step="0.1" ng-model="iterations.mutationChromosome" ng-disabled="autoMutationChromosome">{{iterations.mutationChromosome}}
			<input type="checkbox" ng-model="autoMutationChromosome" >auto
			</span>
			<br>
			Genu:
			<input type="range" min="0.1" max="10" step="0.1" ng-model="iterations.mutationGene" ng-disabled="autoMutationGene">{{iterations.mutationGene}}
			<input type="checkbox" ng-model="autoMutationGene" >auto
			</span>
			<br>
			<span>Procent wymaganych osobników do zmiany:<br>
			<input type="range" min="0" max="100" ng-model="iterations.change">{{iterations.change}}
			</span>
			<br>
			<span>Uwzglsdniaj populacjs rodziców podczas selekcji:
			<input type="checkbox" ng-model="iterations.useParents">
			</span>
			<br>
			<span>Iteracje:</span> <input type="number" min="1" max="100000"
				ng-model="iterations.iterations">
				<button ng-click="iterationsFun()" ng-disabled="cities==undefined">Dalej</button>
			<br><span>Automatycznie wykonuj kolejne operacje: <input type="checkbox" ng-model="redo"></span>
		</div>

		<label>Ukryj trasy: <input type="checkbox" ng-model="checked"></label>
		<div class="ways" ng-hide="checked">
			<div class="way" ng-repeat="route in routes"
				ng-click="drawRoute($index)">
				<span>{{route.quality | number:2}}: {{route.cities}}</span>
			</div>
		</div>

	</div>
</body>
</html>


