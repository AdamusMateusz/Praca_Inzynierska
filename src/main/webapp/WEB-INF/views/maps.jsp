<div class="page-header">
	<h1>Lista wszystkich map</h1>
</div>

<button type="button"
	class="btn btn-block btn-primary btn-xs btn-block mapsTitle"
	data-toggle="collapse" data-target="#add">Dodaj nowa mape</button>



<div class="collapse " id="add">
	<div class="well well-sm ">

		<form class="form-horizontal addMap" role="form">
	
	<!-- Poczatek formularza-->
	
			<div class="form-group">
				<label class="control-label col-md-2" for="iloscM">Ilosc
					miast</label>
				<div class="col-md-10">
					<div class="input-group">
						<input type="range" class="form-control" id="iloscM"
							ng-model="request.citiesQuantity" min="5" max="99"> <span
							class="input-group-addon">{{request.citiesQuantity}}</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-2" for="number">Ilosc
					rodzicow</label>
				<div class="col-md-4">
					<div class="input-group">
						<input type="range" class="form-control" id="number"
							ng-model="request.parents" min="2"> <span
							class="input-group-addon">{{request.parents}}</span>
					</div>
				</div>

				<label class="control-label col-md-2" for="child">Ilosc
					potomkow</label>
				<div class="col-md-4">
					<div class="input-group">
						<input type="range" class="form-control" id="child"
							ng-model="request.kids" min="2"> <span
							class="input-group-addon">{{request.kids}}</span>
					</div>
				</div>
			</div>
			<div class="text-center mapsTitle">
				<strong>Prawdopodobienstwo krzyzowania:</strong>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2" for="genC">Genu </label>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" ng-click="request.crossingGene = -1">
								<span class="glyphicon glyphicon-random"></span>
							</button>
						</span> <input type="range" class="form-control" id="genC"
							ng-model="request.crossingGene" min="1" max="100"> <span
							class="input-group-addon">{{request.crossingGene}}</span>
					</div>
				</div>

				<label class="control-label col-md-2" for="chromoC">Chromosomu</label>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" ng-click="request.crossingChromosome = -1">
								<span class="glyphicon glyphicon-random"></span>
							</button>
						</span> <input type="range" class="form-control" id="chromoC"
							ng-model="request.crossingChromosome" min="1"> <span
							class="input-group-addon">{{request.crossingChromosome}}</span>
					</div>
				</div>
			</div>

			<div class="text-center mapsTitle">
				<strong>Prawdopodobienstwo mutacji:</strong>
			</div>

			<div class="form-group">
				<label class="control-label col-md-2" for="genM">Genu </label>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" ng-click="request.mutationGene = -1">
								<span class="glyphicon glyphicon-random"></span>
							</button>
						</span> <input type="range" class="form-control" id="genM"
							ng-model="request.mutationGene" min="0.1" max="10" step="0.1">
						<span class="input-group-addon">{{request.mutationGene |
							number:1}}</span>
					</div>
				</div>

				<label class="control-label col-md-2" for="chromoM">Chromosomu</label>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" ng-click="request.mutationChromosome = -1">
								<span class="glyphicon glyphicon-random"></span>
							</button>
						</span> <input type="range" class="form-control" id="chromoM"
							ng-model="request.mutationChromosome" min="0.1" max="10"
							step="0.1"> <span class="input-group-addon">{{request.mutationChromosome
							| number:1}}</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-2" for="change">Wymagany procent zmiany</label>
				<div class="col-md-10">
					<div class="input-group">
						<input type="range" class="form-control" id="change"
							ng-model="request.change" min="5" max="100"> <span
							class="input-group-addon">{{request.change}}</span>
					</div>
				</div>
			</div>


<div class="row">
			<div class="col-md-offset-1 col-md-5">
					<div class="form-group">
						<div class="col-md-offset-2 col-md-10">
							<div class="checkbox">
								<label><input type="checkbox"
									ng-model="request.useParents"> Uzywaj populacji
									rodzicielskiej</label>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-offset-2 col-md-10">
							<div class="checkbox">
								<label><input type="checkbox"
									ng-model="request.heuristic"> Policz dodatkowo rezultat w sposob heurystyczny</label>
							</div>
						</div>
					</div>
			</div>
				<div class="col-md-5">
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<div class="checkbox">
							<label><input type="checkbox"
								ng-model="request.saveFittingFunctionValue"> Zapisuj wartosc funkcji oceny</label>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<div class="checkbox">
							<label><input type="checkbox"
								ng-model="request.stopped"> Dodaj zatrzymane zadanie</label>
						</div>
					</div>
				</div>
			</div>
		</div>


			<!-- Koniec formularza -->
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-md" ng-click="newMap()">
						Dodaj nowa mape <span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
			</div>
		</form>

		<div class="alert alert-warning">
			<div class="text-center">
				<strong>Uwaga! </strong> <a href="#/info"> Przed dodaniem nowej mapy przeczytaj
				instrukcje <span
					class="glyphicon glyphicon-arrow-right"></span></a>
			</div>
		</div> 

	</div>
</div>

<div ng-repeat="map in maps">
	<div ng-class='getClass("panel panel-",map)'>
		<div class="panel-heading">
		<span class="glyphicon glyphicon-flag"></span>
		
		</div>
		<div class="panel-body">
		
		<div class="row">

				<div class="col-md-4">
					<div class="svg-container-mapList">
						<svg version="1.1" style="margin-top: 15px;" viewBox="0 0 1 2"
							preserveAspectRatio="xMinYMin meet" class="svg-content">
				
				<circle id="map" class="map" ng-repeat="c in map.cities" r="0.02" ng-attr-cx="{{c.x}}" ng-attr-cy="{{c.y/2}}" />
						</svg>
					</div>
				</div>

				<div class="col-md-offset-1 col-md-6">
				
					<span class="glyphicon glyphicon-globe"></span> Ilosc miast: <span class="badge">{{map.cities.length}}</span><br>
					<span class="glyphicon glyphicon-road"></span> Ilosc rodzicow: <span class="badge">{{map.parents}}</span><br>
					<span ng-class="getTick(map.heuristic)"></span> Dodatkowy rezultat heurestyczny <span ng-if="map.heuristicValue != -2" class="badge">{{map.heuristicValue * 1000 | number :2}}</span><br>
					<span ng-class="getTick(map.saveFittingFunctionValue)"></span> Zapisuj wartosc funkcji oceny<br>
					<span ng-class="getTick(!map.running)"></span> Zatrzymane<br>
					<span class="glyphicon glyphicon-scale"></span> Jakosc najlepszego rozwiazania <span class="badge">{{map.quality == -1 ? -1 :( map.quality * 1000 | number :2)}}</span><br>
					<a href="#/map/{{map.id}}"><span class="glyphicon glyphicon-search"></span> Wiecej...</a><br>
					
				</div>
			
		
			
			</div>
			
		</div>

	</div>
</div>

<div ng-if="maps.length == 0 " class="alert alert-info text-center"><span class="glyphicon glyphicon-info-sign"></span> Obenie nie ma zadnych map do wyswietlenia. Sproboj dodac wlasna <span class=" glyphicon glyphicon-chevron-up"></span></div>

<div class="text-center mapsTitle">
	<div class="btn-group btn-group-sm" >
	<button class="btn btn-default" ng-click="loadMore (lastID)"><span class="glyphicon glyphicon-chevron-down"></span> Wiecej</button>
	<button class="btn btn-default" ng-click="loadAll()">Wszystkie <span class="glyphicon glyphicon-chevron-down"></span></button>
	</div>
</div>
