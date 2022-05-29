const RED = "red";
const GREEN = "green"

const setMessage = function(message, color) {
	$("#msg").css("color", color);
	$("#msg").html(message);
}

$(document).ready(function () {	
	param = window.location.search;
	if (param.endsWith('error')) {
		setMessage("Invalid username or password", RED);
	} else if (param.endsWith('logout')) {
		setMessage("You have been logged out", GREEN);
	} else if (param.endsWith('no-user')) {
	    setMessage("User does not exist", RED);
	}
});