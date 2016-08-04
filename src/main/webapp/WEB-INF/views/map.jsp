
<div class="row">
	<div class="hidden-lg hidden-md alert alert-danger fade in">
		<span class="glyphicon glyphicon-exclamation-sign"></span> Strona moze
		nie byc wyswietlana poprawnie przy tak niskich rozdzielczosciach <a
			class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
</div>

<div class="tabs">
<tabs> <pane heading="Mapa">
<div class="well">

	<div class="row">
		<div class="col-lg-12">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">
						<span class="glyphicon glyphicon-refresh"></span>
					</button>
				</span> <input type="range" class="form-control" ng-model="seconds" min="2"
					max="20">
				<div class="input-group-addon">{{seconds}}</div>
			</div>

			<canvas id="map"></canvas>
		</div>
	</div>
</div>
</pane>
 <pane heading="Wykresy">
		<div class="well">
			<canvas id="chart" ></canvas>
		</div>
	</pane>
<pane heading="Tabela odleglosci">
<div class="well">
Tabela odleglosci
</div>
</pane>
	<pane heading="Ustawienia">
		<div class="well">

	<form class="form-horizontal addMap" role="form">

		<div class="form-group">
			<label class="control-label col-md-2" for="iloscM">Ilosc
				miast:</label>
			<div class="col-md-10">
				<div class="input-group">
					<input type="range" class="form-control" id="iloscM"
						ng-model="request.quantity" min="5" max="100"> <span
						class="input-group-addon">{{request.quantity}}</span>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2" for="number">Ilosc
				rodzicow:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input type="range" class="form-control" id="number"
						ng-model="request.parents" min="2"> <span
						class="input-group-addon">{{request.parents}}</span>
				</div>
			</div>

			<label class="control-label col-md-2" for="child">Ilosc
				potomkow:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input type="range" class="form-control" id="child"
						ng-model="request.kids" min="2"> <span
						class="input-group-addon">{{request.kids}}</span>
				</div>
			</div>
		</div>
		<div class="text-center mapsTitle">
			<strong>Prawdopodobienstwo krzyzowania</strong>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2" for="genC">Genu: </label>
			<div class="col-md-4">
				<div class="input-group">
					<input type="range" class="form-control" id="genC"
						ng-model="request.crossingGene" min="1" max="100"> <span
						class="input-group-addon">{{request.crossingGene}}</span>
				</div>
			</div>

			<label class="control-label col-md-2" for="chromoC">Chromosomu:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input type="range" class="form-control" id="chromoC"
						ng-model="request.crossingChromosome" min="1"> <span
						class="input-group-addon">{{request.crossingChromosome}}</span>
				</div>
			</div>
		</div>

		<div class="text-center mapsTitle">
			<strong>Prawdopodobienstwo mutacji</strong>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2" for="genM">Genu: </label>
			<div class="col-md-4">
				<div class="input-group">
					<input type="range" class="form-control" id="genM"
						ng-model="request.mutationGene" min="0.1" max="10" step="0.1">
					<span class="input-group-addon">{{request.mutationGene |
						number:1}}</span>
				</div>
			</div>

			<label class="control-label col-md-2" for="chromoM">Chromosomu:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input type="range" class="form-control" id="chromoM"
						ng-model="request.mutationChromosome" min="0.1" max="10"
						step="0.1"> <span class="input-group-addon">{{request.mutationChromosome
						| number:1}}</span>
				</div>
			</div>
		</div>


		<div class="form-group">
			<div class="col-md-offset-2 col-md-10">
				<div class="checkbox">
					<label><input type="checkbox" ng-model="request.useParents">
						Uzywaj populacji rodzicielskiej</label>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2" for="email">Haslo:</label>
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
				<div class="btn-group">
				<button type="submit" class="btn btn-danger">
						Usun <span class="glyphicon glyphicon-trash"></span>
					</button>
					<button type="submit" class="btn btn-warning">
						<span class="glyphicon glyphicon-pause"></span>
					</button>
					<button type="submit" class="btn btn-warning">
						<span class="glyphicon glyphicon-play"></span>
					</button>
					<button type="submit" class="btn btn-warning">
					 <span class="glyphicon glyphicon-refresh"></span>
					</button>
					<button type="submit" class="btn btn-success">
						Zapisz nowe ustawienia <span class="glyphicon glyphicon-edit"></span>
					</button>
				</div>
			</div>
		</div>
	</form>
</div>
	</pane>


 </tabs>
 </div>