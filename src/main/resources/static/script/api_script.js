const TABLE_ID = "#urlList";
const TITLE_ID = "#title"
const endpoint = "api/userlink";
const URL_ID = "#url";
const CONTENT_TYPE = "application/json; charset=utf-8";
const EMPTY = "";
const DELETE_CL = ".del";

$(document).ready(function () {

	$.get(endpoint).done(function(data) {
		$.each(data, function (index, value) {
			addRow(value);
		});
	});

	$("form").submit(function(event) {
		var formData = {
			title: $(TITLE_ID).val(),
			url: $(URL_ID).val(),
		};
		$.post({
			url: endpoint,
			data: JSON.stringify(formData),
			contentType: CONTENT_TYPE,
			encode: true
		}).done(function (data) {
			addRow(data);
			$(TITLE_ID).val(EMPTY);
			$(URL_ID).val(EMPTY);
		});
		event.preventDefault();
	});

	const addRow = function (value) {
		$(TABLE_ID).find('tbody')
			.append($('<tr>')
				.attr('id', value.id)
				.append($('<td>')
					.text(value.title)
				)
				.append($('<td>')
					.append($('<div>')
						.attr('class', 'btn-group btn-group-sm d-flex')
						.append($('<a>')
							.attr('class', 'btn btn-primary')
							.attr('href', value.url)
							.attr('target', '_blank')
							.append($('<i>')
								.attr('class', 'fa-solid fa-arrow-up-right-from-square')
							)
						)
						.append($('<button>')
							.attr('class', 'btn btn-secondary del')
							.attr('value', value.id)
							.append($('<i>')
								.attr('class', 'fa-solid fa-trash-can')
							)
						)
					)
				)
			);
	}

	$(TABLE_ID).on("click", DELETE_CL, function () {
		var id = $(this).val();
		$.ajax({
			type: "DELETE",
			url: endpoint.concat("/", id)
		}).done(function (data) {
			$("#" + id).remove();
		});
	});
});