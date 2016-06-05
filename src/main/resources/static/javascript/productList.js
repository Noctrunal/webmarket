var products;
$(function () {
    products = $('#products');
    $('#list').click(function (event) {
        event.preventDefault();
        products.find('.item').addClass('list-group-item');
    });
    $('#grid').click(function (event) {
        event.preventDefault();
        products.find('.item').removeClass('list-group-item');
        products.find('.item').addClass('grid-group-item');
    });
});