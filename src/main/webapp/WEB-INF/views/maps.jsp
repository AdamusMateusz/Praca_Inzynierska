<div class="page-header">
	<h1>Lista wszystkich map</h1>
</div>

<button type="button"
	class="btn btn-block btn-primary btn-xs btn-block mapsTitle"
	data-toggle="collapse" data-target="#add">Dodaj nowa mape</button>



<div class="collapse in" id="add">
	<div class="well well-sm addMap">
		<div class="alert alert-warning fade in">
			<div class="text-center">
				<strong>Uwaga! </strong> Przed dodaniem nowej mapy przeczytaj
				instrukcje <a href="#/info"><span
					class="glyphicon glyphicon-arrow-right"></span> </a> <a class="close"
					data-dismiss="alert" aria-label="close">&times;</a>
			</div>
		</div>

		<form class="form-horizontal addMap" role="form">

			<div class="form-group">
				<label class="control-label col-md-2" for="iloscM">Ilosc
					miast</label>
				<div class="col-md-10">
					<div class="input-group">
						<input type="range" class="form-control" id="iloscM"
							ng-model="request.quantity" min="5" max="99"> <span
							class="input-group-addon">{{request.quantity}}</span>
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
							<button class="btn btn-default" type="button">
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
							<button class="btn btn-default" type="button">
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
							<button class="btn btn-default" type="button">
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
							<button class="btn btn-default" type="button">
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
				<label class="control-label col-md-2" for="email">Nowe haslo</label>
				<div class="col-md-10">
					<div class="input-group">
						<input type="password" class="form-control" id="password"
							placeholder="Podaj haslo" ng-model="request.password"> <span
							class="input-group-addon "><span
							class="glyphicon glyphicon-lock"></span></span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-md">
						Dodaj nowa mape <span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
			</div>
		</form>

	</div>
</div>

<div ng-repeat="map in maps">
	<div ng-class='getClass("panel panel-",map.progress)'>
		<div class="panel-heading">Head</div>
		<div class="panel-body">
			Ilosc miast: {{map.cities}}

			<div class="text-right">
				<div class="btn-group btn-group-sm" role="group">

					<a class='btn btn-default'>
						<span class="glyphicon glyphicon-eye-open"></span>
						<a class='btn btn-default'><span class="glyphicon glyphicon-pause"></span></a>
					</a>
					 <a ng-class='getClass("btn btn-",map.progress)'
						href="#/map/{{map.id}}">Wiecej <span
						class="glyphicon glyphicon-search"></span></a>

				</div>
			</div>
		</div>
		<div class="progress myBar">
			<div
				ng-class='getClass("progress-bar progress-bar-",map.progress,true)'
				role="progressbar"
				style="width: {{map.progress!= -1? map.progress: 100}}%"></div>
		</div>

	</div>
</div>

