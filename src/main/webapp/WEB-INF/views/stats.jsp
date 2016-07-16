<div class="page-header">
	<h1>Strona przedstawiajaca statystyki serwera</h1>
</div>


<div class="alert alert-info" ng-init="test()" ng-if="progress != -1">
	<span class="label label-info">Info</span> Trwa wczytywanie danych z
	serwera
	<hr>
	<div class="progress">

		<div ng-if="progress != 100"
			class="progress-bar progress-bar-striped active" role="progressbar"
			style="width: {{progress}}%">Wczytywanie danych</div>

		<div ng-if="progress == 100" class="progress-bar progress-bar-success"
			role="progressbar" style="width: {{progress}}%">Wczytywanie
			zakonczone</div>
	</div>
</div>

<h3>Mapy</h3>

<div class=well>
	<div class="row">
		<div class="col-md-3 ">
			Mapy
			<div class="text-small text-muted">20</div>
		</div>
		<div class="col-md-3 ">
			Mapy w toku
			<div class="text-small text-muted">5</div>
		</div>
		<div class="col-md-3 ">
			Rozwiazane zadania
			<div class="text-small text-muted">15</div>
		</div>
		<div class="col-md-3 ">
			Zadania zakonczone niepowodzeniem
			<div class="text-small text-muted">8</div>
		</div>

	</div>
</div>


<div class=well>
	<div class="row">
		<div class="col-md-3 ">
			Mapy z funkcja oceny = -1
			<div class="text-small text-muted">0</div>
		</div>
		<div class="col-md-3 ">
			Srednia ilosc miast
			<div class="text-small text-muted">10</div>
		</div>
		<div class="col-md-3 ">
			Najwieksza ilosc miast
			<div class="text-small text-muted">25</div>
		</div>
		<div class="col-md-3 ">
			Srednia ilosc rodzicow
			<div class="text-small text-muted">6</div>
		</div>

	</div>
</div>


<h3>Chat</h3>
<div class="well">
	<div class="row">
		<div class="col-md-4 ">
			Tematy
			<div class="text-small text-muted">8</div>
		</div>
		<div class="col-md-4 ">
			Wiadomosci
			<div class="text-small text-muted">256</div>
		</div>
		<div class="col-md-4 ">
			Uzytkownicy
			<div class="text-small text-muted">15</div>
		</div>
	</div>
</div>

<h3>Serwer</h3>
<div class="well">
Zapotrzebowanie serwera na pamiec
<div class="progress">
	<div
		class="progress-bar progress-bar-success"
		role="progressbar" style="width: 40%">Free Space</div>
	<div
		class="progress-bar progress-bar-warning"
		role="progressbar" style="width: 10%">Warning</div>
	<div class="progress-bar progress-bar-danger" role="progressbar"
		style="width: 20%">Danger</div>
</div>
</div>