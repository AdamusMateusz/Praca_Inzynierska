function Message(author, message) {
	this.author = author;
	this.message = message;
}

function Topic(title, messagesCount) {
	this.title = title;
	this.messagesCount = messagesCount;
}

function validate(input) {
	console.log(input);
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
