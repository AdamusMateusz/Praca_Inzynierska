function Message(author, message) {
	this.author = author;
	this.message = message;
}

function Topic(title, messagesCount) {
	this.title = title;
	this.messagesCount = messagesCount;
}


function FittingElements(elementsObject,showPositive){
	//transform map to arrays of values
	//'elements' consists values of fitting function in ascending order
	//[-1, 3.2, 3.5, 4.54, 6.1]
	let elements=[];
	let quantitiesOf=[];
	for(name in elementsObject){
		elements.push( name === "-1.0" ? Number(name) : Number(name) * 1000  );
		quantitiesOf.push(elementsObject[name]);
	}
	console.log("Strart");
	console.log("elements",elements);
	console.log("quantitiesOf",quantitiesOf);
	//reverse 'elements' array
	if(showPositive){
		//exclude -1 if present
		let lastIndex = elements.lastIndexOf(-1)+1;
		quantitiesOf = quantitiesOf.slice(lastIndex).reverse();
		elements = elements.slice(lastIndex).reverse();
		
	}else{

		elements = elements.reverse();
		quantitiesOf = quantitiesOf.reverse();
		
		let tmp = quantitiesOf[quantitiesOf.length-1];
		
		for(var i =elements.length-1 ; i >0; i--){
			elements[i] = elements[i-1];
			quantitiesOf[i] = quantitiesOf[i-1];
		}
		
		quantitiesOf[0] = tmp;
		elements[0] = -1;
		
	}
	console.log("elements",elements);
	console.log("quantitiesOf",quantitiesOf);
	
	//now arrays are in correct order
	//[-1, 6.1, 4.53, 3.5, 3.2]
		
	this.max = Math.max.apply(null,elements);
	this.min = showPositive ? 0 : Math.min.apply(null,elements);

	//compute average
	let sum = 0;
	let quantity = 0;
	for(var i=0; i< elements.length; i++){
		sum += elements[i] * quantitiesOf[i];
		quantity += quantitiesOf[i];
	}
	
	//svg specific values
	let left = 50;
	let	right = 995;
	let	top = 10/2;
	let	bottom = ((1000 - (10/2)) - (50))/2;
	let	jmpX = (right-left)/(quantity);
	let positionX = left;
	let range = this.max - this.min;
	let jmpY = (bottom-top)/(range-1);

	let getY = function(value){
		return Math.abs((bottom+top) - (top + (jmpY * value)));
	};
	
	//determine the lowest value
	let positionOfLast = elements.length-1 ;
	this.lastTxt = elements[positionOfLast];
	this.lastH = getY(elements[positionOfLast]);
	

	let avg = sum / quantity;
	this.avgTxt = avg;
	this.avgH   = getY(avg);
	
	
	//prepare string for path attribute
	var string = "M " + positionX + " " + bottom;
	for(var i = 0; i< elements.length; i++){

		string += " L " + positionX + " " + getY(elements[i]);
		positionX += jmpX * quantitiesOf[i];
		string += " L " + positionX + " " + getY(elements[i]);

	}
	string += " L " + right + " " + bottom;
	this.path = string + " Z";
}


function validate(input) {
	// console.log(input);
	if (input instanceof String) {
		return input.trim().length > 0;
	} else if (input instanceof Message) {
		return validate(new String(input.author))
				&& validate(new String(input.message));
	} else if (input == undefined) {
		return false;
	} else if (input instanceof Object) {
		if ("author" in input)
			if ("message" in input)
				return validate(new Message(input.author, input.message));
	} else if (input instanceof Number) {
		return isNaN(input) ? false : true;
	} else
		return validate(new String(input));
}
