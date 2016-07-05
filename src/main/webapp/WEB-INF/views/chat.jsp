<div class="row">
	<div class="alert alert-danger fade in">
		<span class="glyphicon glyphicon-exclamation-sign"></span> Autor strony nie
		ponosi odpowiedzialnosci za tresci umieszane przez uzytkownikow <a
			class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
</div>

<div class="page-header">
	<h1>Chat</h1>
	<div class="text-muted">Miejsce dla uzytkownikow poswiecone
		wymianie zdan na rozne tematy</div>

</div>

<div class="row">

	<div class="col-md-3">


		<ul class="list-group" style="margin-bottom: 5px;">
			<li class="list-group-item active">Tematy</li>

			<a ng-repeat="topic in topics" class="list-group-item"
				ng-click="setActive($index,topic.title)"><span class="badge">{{topic.messagesCount}}</span>
				{{topic.title}}</a>

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

		<div class="row">
			<div class="col-md-8">
				<h4 class="text-muted">{{title}}</h4>
			</div>
			<div class="chat-btns col-lg-offset-9">
				<div class="btn-group">
					<button type="button" class="btn btn-default" ng-click="refresh()">
						<span class="glyphicon glyphicon-refresh"></span>
					</button>
					<button type="button" class="btn btn-success addMessage"
						data-toggle="collapse" data-target="#message">
						Dodaj wiadomosc <span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
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
							<button type="submit" class="btn btn-success">
								Dodaj wiadomosc <span class="glyphicon glyphicon-plus"></span>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div ng-repeat="message in messages">
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-user"></span> {{message.autor}}
					</div>
					<div class="panel-body">{{message.message}}</div>
				</div>
			</div>
		</div>

	</div>


