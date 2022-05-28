const RED = "red";
const GREEN = "green"

$(document).ready(function () {
	
	var urlParams = new URLSearchParams(window.location.search);
	if (urlParams.has('error')) {
		setMessage("Invalid username or password", RED);
	} else if (urlParams.has('logout')) {
		setMessage("You have been logged out", GREEN);
	} else if (urlParams.has('no-user')) {
	    setMessage("User does not exist", RED);
	}

	const setMessage = function(message, color) {
		$("#msg").css("color", color);
		$("#msg").html(message);
	}
});