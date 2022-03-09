const endpoint = "api/userlink";

$(document).ready(function () {

	$.get(endpoint).done(function(data) {
		$.each(data, function (index, value) {
			addRow(value);
		});
	});

	$("form").submit(function(event) {
		var formData = {
			title: $("#title").val(),
			url: $("#url").val(),
		};
		$.post({
			url: endpoint,
			data: JSON.stringify(formData),
			contentType: "application/json; charset=utf-8",
			encode: true
		}).done(function (data) {
			addRow(data);
			$("#url").val("");
			$("#title").val("");
		});
		event.preventDefault();
	});

	const addRow = function (value) {
		$("#urlList").find('tbody')
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

	$("#urlList").on("click", ".del", function () {
		var id = $(this).val();
		$.ajax({
			type: "DELETE",
			url: endpoint.concat("/", id)
		}).done(function (data) {
			$("#" + id).remove();
		});
	});
});