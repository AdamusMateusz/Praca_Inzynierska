<div class="page-header">
	<h1>Chat</h1>
	<div class="text-muted">Miejsce dla uzytkownikow poswiecone
		wymianie zdan na rozne tematy</div>
</div>

<div class="row">

	<div class="col-md-3">

		<ul class="list-group" style="margin-bottom: 5px;">
			<li class="list-group-item active">Tematy</li>
			<a class="list-group-item "><span class="badge">4</span> O
				stronie</a>
			<a class="list-group-item "><span class="badge">5</span> Rozmowy
				ogolone</a>
			<a class="list-group-item"><span class="badge">12</span> Algorytm</a>
			<a class="list-group-item"><span class="badge">174</span>
				Problemy</a>
			<a class="list-group-item"><span class="badge">1</span>
				Propozycje rozbudowy</a>
			<a class="list-group-item"><span class="badge">5</span> Cokolwiek</a>
		</ul>

		<button type="button" class="btn btn-primary btn-block addChat"
			data-toggle="collapse" data-target="#add">Dodaj temat</button>

		<div class="collapse addChat" id="add">
			<div class="input-group ">
				<input type="text" class="form-control" placeholder="Nazwa">
				<span class="input-group-btn">
					<button class="btn btn-secondary" type="button">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</span>
			</div>
		</div>
	</div>


	<div class="col-md-9">
		<div class="chat-btns col-lg-offset-9">
			<div class="btn-group">
				<button type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-refresh"></span>
				</button>
				<button type="button" class="btn btn-success addMessage"
					data-toggle="collapse" data-target="#message">
					Dodaj wiadomosc <span class="glyphicon glyphicon-plus"></span>
				</button>
			</div>
		</div>

		<div class="collapse" id="message">
			<div class="well">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="nick" class="col-sm-1 control-label">Nick</label>
						<div class="col-sm-11">
							<input type="text" class="form-control" id="nick"
								placeholder="Twoje imie">
						</div>
					</div>
					<div class="form-group">
						<label for="post" class="col-sm-1 control-label">Post</label>
						<div class="col-sm-11">
							<textarea class="form-control" id="post" rows="3"
								placeholder="Tresc twojej wiadomosci"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-11">
							<button type="submit" class="btn btn-success">Dodaj
								wiadomosc <span class="glyphicon glyphicon-plus"></button>
						</div>
					</div>
				</form>
			</div>
		</div>


		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-user"></span> Autor
			</div>
			<div class="panel-body">Tekst</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-user"></span> Marek
			</div>
			<div class="panel-body">Bardzo mi sie podoba</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-user"></span> August
			</div>
			<div class="panel-body">Przykladowa tresc bez sensu</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-user"></span> Zbyszek
			</div>
			<div class="panel-body">Fajna strona</div>
		</div>


	</div>
</div>

