//noinspection JSUnresolvedFunction
$(document).ready(function () {
    //noinspection JSUnresolvedFunction
    $('#list').click(function (event) {
        event.preventDefault();//noinspection JSJQueryEfficiency,JSUnresolvedFunction
        $('#products .item').addClass('list-group-item');
    });
    //noinspection JSUnresolvedFunction
    $('#grid').click(function (event) {
        event.preventDefault();//noinspection JSJQueryEfficiency,JSUnresolvedFunction
        $('#products .item').removeClass('list-group-item');
        //noinspection JSJQueryEfficiency,JSUnresolvedFunction
        $('#products .item').addClass('grid-group-item');
    });
});