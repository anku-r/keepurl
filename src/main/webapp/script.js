$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/api/userlink/1"
    }).then(function(data) {
       $('.title').append(data.title);
       $('.url').append(data.url);
    });
});