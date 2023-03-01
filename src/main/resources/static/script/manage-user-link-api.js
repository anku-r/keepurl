const TABLE_ID = "#urlList";
const TITLE_ID = "#title"
const endpoint = "api/userlink";
const URL_ID = "#url";
const CT_JSON = "application/json; charset=utf-8";
const EMPTY = "";
const DELETE_CL = ".del";

$(document).ready(function () {

	$.get(endpoint).done(function (data) {
		$.each(data, function (index, value) {
			addRow(value);
		});
	});

	$("form").submit(function (event) {
		var fd = {
			title: $(TITLE_ID).val(),
			url: $(URL_ID).val(),
		};
		$.post({
			url: endpoint,
			data: JSON.stringify(fd),
			contentType: CT_JSON,
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
					.append($('<span>')
						.attr('class', 'title-long')
						.text(value.title)
					)
					.append($('<span>')
						.attr('class', 'title-short')
						.text(value.titleShort)
					)
				)
				.append($('<td>')
					.append($('<div>')
						.attr('class', 'btn-group btn-group-sm d-flex')
						.append($('<a>')
							.attr('class', 'btn btn-primary')
							.attr('href', value.url)
							.attr('target', '_blank')
							.attr('title', 'Go to URL')
							.append($('<i>')
								.attr('class', 'fa-solid fa-arrow-up-right-from-square')
							)
						)
						.append($('<button>')
							.attr('class', 'btn btn-secondary del')
							.attr('value', value.id)
							.attr('title', 'Delete')
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
		$("#" + id).remove();
		$.ajax({
			type: "DELETE",
			url: endpoint.concat("/", id)
		});
	});
});