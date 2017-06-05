$(document).on('click', '.btn', function() {

    var parameter = $(this).data('city');
    if (parameter != undefined && parameter != null) {
        window.location = 'http://localhost:8080/prognosis?city=' + parameter;
    }
});