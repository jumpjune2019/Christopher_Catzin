//Define vars to hold time values
let seconds = 0;
let minutes = 0;
let hours = 0;

// Define vars to hold "display" value
let displaySeconds = 0;
let displayMinutes = 0;
let displayHours = 0;

// Define var to hold setInterval() function
// null because I do not want the the startwatch
// to start when the page is refreshed.
let interval = null;

// Define var to hold stopwatch status
let status = "stopped";

// Stopwatch 
function stopWatch(){
	seconds++;
	if(seconds / 60 === 1){
		seconds = 0;
		minutes++;
		if(minutes / 60 === 1){
			minutes = 0;
			hours++;
		}
	}

	// If seconds/minutes/hours are only one digit
	// add a leading 0 to the value
	if(seconds < 10) {
		displaySeconds = "0" + seconds.toString();
	}
	else{
		displaySeconds = seconds;
	}
	if(minutes < 10){
		displayMinutes = "0" + minutes.toString();
	}
	else{
		displayMinutes = minutes;
	}
	if(hours < 10){
		displayHours = "0" + hours.toString();
	}
	else{
		displayHours = hours;
	}

	//Display updated time values
	document.getElementById("display").innerHTML = displayHours + ":" + displayMinutes + ":" + displaySeconds;
}


function startStop(){
	if(status === "stopped"){
		interval=window.setInterval(stopWatch, 1000);
		document.getElementById("play").innerHTML = "Stop";
		status = "started";
		//starttimer()

	}

	else if{
		window.clearInterval(interval);
		document.getElementById("play").innerHTML = "Start";
		status = "stopped";
		//stopTimer()
	}

	else if(elem.value == "End"){
		elem.value = "SIIIIIII";

	}
}

// Function to reset the stopwatch
function reset(){
	window.clearInterval(interval);2
	seconds = 0;
	minutes = 0;
	hours = 0;
	document.getElementById("display").innerHTML = "00:00:00";
	document.getElementById("play").innerHTML = "Start";
}





