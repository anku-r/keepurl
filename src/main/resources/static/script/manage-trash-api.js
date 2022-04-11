const TABLE_ID = "#urlList";
const endpoint = "api/trash";
const RESTORE_PATH = "/restore/"
const DELETE_CL = ".del";
const RESTORE_CL = ".res";

$(document).ready(function () {

	$.get(endpoint).done(function(data) {
		$.each(data, function (index, value) {
			addRow(value);
		});
	});

	const addRow = function (value) {
		$(TABLE_ID).find('tbody')
			.append($('<tr>')
				.attr('id', value.id)
				.append($('<td>')
					.text(value.title)
				)
                .append($('<td>')
					.attr('class', 'bold-text')
					.text(value.date)
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
							.attr('class', 'btn btn-secondary res')
							.attr('value', value.id)
                            .attr('title', 'Restore')
							.append($('<i>')
								.attr('class', 'fa-solid fa-rotate-left')
							)
						)
						.append($('<button>')
							.attr('class', 'btn btn-danger del')
							.attr('value', value.id)
                            .attr('title', 'Permanently Delete')
							.append($('<i>')
								.attr('class', 'fa-solid fa-trash-can')
							)
						)
					)
				)
			);
	}

	$(TABLE_ID).on("click", DELETE_CL, function () {
		removeFromTrash("/", $(this).val())
	});

    $(TABLE_ID).on("click", RESTORE_CL, function () {
		removeFromTrash(RESTORE_PATH, $(this).val())
	});

    const removeFromTrash = function(path, id) {
		$("#" + id).remove();
		$.ajax({
			type: "DELETE",
			url: endpoint.concat(path, id)
		});
    }
});