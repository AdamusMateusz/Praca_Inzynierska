function Message(author, message) {
	this.author = author;
	this.message = message;
}

function Topic(title, messagesCount) {
	this.title = title;
	this.messagesCount = messagesCount;
}

function Map(id,progress,cities){
	this.id = id;
	this.progress = progress;
	this.cities = cities;
}

var mapsService = [new Map(0,0,5),
                   new Map(1,-1,15),
                   new Map(2,76,5),
                   new Map(3,100,20),
                   new Map(4,45,45),
                   new Map(5,12,7),
                   new Map(6,83,8),
                   new Map(7,45,14),
                   new Map(8,18,12),
                   new Map(9,53,5)];



function validate(input) {
//	console.log(input);
	if (input instanceof String) {
		return input.trim().length > 0;
	} else if (input instanceof Message) {
		return validate(new String(input.author)) && validate(new String(input.message));
	} else if (input == undefined) {
		return false;
	} else if (input instanceof Object) {
		if("author" in input)
			if("message" in input)
				return validate(new Message(input.author,input.message));
	}else if (input instanceof Number) {
		return isNaN(input) ? false : true;
	} else 
		return validate(new String(input));
}
