$(document).ready(function () {
	var urlParams = new URLSearchParams(window.location.search);
	if (urlParams.has('error')) {
		$("#msg").css("color", "red");
		$("#msg").html("Invalid username or password");
	} else if (urlParams.has('logout')) {
	    $("#msg").css("color", "green");
		$("#msg").html("You have been logged out");
	} else if (urlParams.has('no-user')) {
	    $("#msg").css("color", "red");
		$("#msg").html("No User Found");
	}
});