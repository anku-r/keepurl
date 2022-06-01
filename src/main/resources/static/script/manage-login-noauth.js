const RED = "red";
const GREEN = "green"
const endpoint = "api/userdata/register";
const CT_JSON = "application/json; charset=utf-8";
const EMAIL_ID = "#rmail";
const PASS_ID = "#rpass";
const CPASS_ID = "#rcpass";
const EMPTY = "";

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

	$("#reg-form").submit(function(event) {
		var fd = {
			email: $(EMAIL_ID).val(),
			password: $(PASS_ID).val(),
			confirmPassword: $(CPASS_ID).val()
		};
		$.post({
			url: endpoint,
			data: JSON.stringify(fd),
			contentType: CT_JSON,
			encode: true
		}).done(function (data) {
			setMessage(data, GREEN);
			$(PASS_ID).val(EMPTY);
			$(EMAIL_ID).val(EMPTY);
			$(CPASS_ID).val(EMPTY);
		}).fail(function (jqXHR) {
			setMessage(jqXHR.responseJSON.message, RED);
		});
		event.preventDefault();
	});
});