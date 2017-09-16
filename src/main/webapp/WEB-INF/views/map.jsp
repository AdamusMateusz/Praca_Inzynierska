
<div class="row">
	<div class="hidden-lg hidden-md alert alert-danger fade in">
		<span class="glyphicon glyphicon-exclamation-sign"></span> Strona moze
		nie byc wyswietlana poprawnie przy tak niskiej rozdzielczosci <a
			class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
</div>

<div class="tabs">
<tabs> 
<pane heading="Mapa">


	<div class="row">
		<div class="col-lg-12">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" ng-click="refreshButton()" id="refreshButton">
						<span id="refreshIcon" class="glyphicon glyphicon-refresh"></span>
					</button>
				</span> <input type="range" class="form-control" ng-model="seconds.seconds" min="1"
					max="20">
				<div class="input-group-addon">{{seconds.seconds}}</div>
			</div>
</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
				<div class="svg-container">
					<svg version="1.1" style="margin-top: 15px;" viewBox="0 0 1 1"
						preserveAspectRatio="xMinYMin meet" class="svg-content">
				
				
				<path ng-attr-d="{{points}}" style="fill:none" class="map"/>
				<circle id="map" class="map animated-circle" ng-repeat="c in map.cities" ng-attr-cx="{{c.x}}" ng-attr-cy="{{c.y/2}}" r="0.02" style="transform-origin:{{c.x}}px {{c.y/2}}px" class="animated-circle"/>
				<text ng-if="showNumbers" font-size="0.02" ng-repeat="c in map.cities" ng-attr-x="{{c.x-( $index < 10? 0.005 : 0.01)}}" ng-attr-y="{{(c.y/2)+0.005}}" fill="#455a64">{{$index}}</text>
				
					</svg>
				</div>
			</div>
	</div>

<label><input type="checkbox" ng-model="showNumbers"> Pokaz numery</label>		



<div class="table-responsive">
	<table class="table table-hover">
	<thead>
		<tr>
			<td>Dlugosc trasy</td><td>Kolejnosc miast</td>
		</tr>
	</thead>
	<tr ng-repeat="row in map.parents" ng-class="{'info': $index == selectedRoute}" ng-click="selectRoute($index)">
		<td>{{row.quality == -1 ? -1 :(row.quality * 1000 | number :2)}}</td>
		<td>{{row.cities}}</td>
	</tr>
	</table>
</div>
	
</pane>
	<pane heading="Ustawienia">
		<div class="well">

	<form class="form-horizontal addMap" role="form">
	
	<!-- Poczatek formularza -->



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
									ng-model="request.heuristic"> Policz dodatkowo rezultat przeliczajac wszystkie rozwiazania</label>
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
		
		<!-- Buttons -->

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="btn-group">
					<button type="submit" class="btn btn-success" ng-click="updateRequest()">
						Zapisz nowe ustawienia <span class="glyphicon glyphicon-edit"></span>
					</button>
					<button ng-if="running" ng-click="stop()" type="submit" class="btn btn-warning">
						<span class="glyphicon glyphicon-pause"></span>
					</button>
					<button ng-if="!running" ng-click="resume()" type="submit" class="btn btn-warning">
						<span class="glyphicon glyphicon-play"></span>
					</button>
					
					<button type="submit" class="btn btn-danger" ng-click="deleteThisMap()">
						Usun <span class="glyphicon glyphicon-trash"></span>
					</button>
				</div>
			</div>
		</div>
	</form>
</div>
	</pane>
<pane heading="Tabela odleglosci">
		<div class="table-responsive text-center">
			<table class="table">
			<thead>
					<tr class="info">
						<td><strong>Miasto</strong></td>
						<td ng-repeat="e in map.distanceMatrix[0]"><strong>{{$index}}</strong></td>
					</tr>
			</thead>
				<tr ng-repeat="element in map.distanceMatrix">
					<td><strong>{{$index}}</strong></td>
					<td ng-repeat="e in element">{{($parent.$index == $index)? 0 : (e * 1000| number : 2)}}</td>
				</tr>
			</table>
		</div>
		
		
</pane>
 <pane heading="Wykres wartosci funkcji oceny" ng-if="request.saveFittingFunctionValue">

	<div class="svg-container">
		<svg version="1.1" style="margin-top: 15px;" viewBox="0 0 1000 1000"
				preserveAspectRatio="xMinYMin meet" class="svg-content">
				
			<g ng-repeat="elem in svg.lines">
				<line class="map" x1="50" ng-attr-y1="{{elem.h}}" x2="995" ng-attr-y2="{{elem.h}}"/>
				<text font-size="10" x="0" ng-attr-y="{{elem.h+2.5}}" fill="#455a64">{{elem.txt|number :0}}</text>
			</g>
			<path class="fitting map" ng-attr-d="{{pointsOfFitting}}"/>
			
			<g >
				<line class="helper" x1="50" ng-attr-y1="{{svg.last.h}}" x2="995" ng-attr-y2="{{svg.last.h}}"/>
				<text font-size="10" class="helper" x="55" ng-attr-y="{{svg.last.h-5}}">{{svg.last.txt|number :0}}</text>
			</g>
			<g >
				<line class="helper" x1="50" ng-attr-y1="{{svg.avg.h}}" x2="995" ng-attr-y2="{{svg.avg.h}}"/>
				<text font-size="10" class="helper" x="55" ng-attr-y="{{svg.avg.h-5}}">{{svg.avg.txt|number :0}}</text>
			</g>
			
		</svg>
	</div>
	
	<label>Zacznij od dodatnich wartosci <input type="checkbox" ng-model="showPositive.value"></label>
</pane>
 </tabs>
 </div>