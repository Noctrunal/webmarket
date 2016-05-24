var ajaxUrl = 'ajax/profile/products/';
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        'ajax': {
            'url': ajaxUrl,
            'dataSrc': ''
        },
        'paging': true,
        'info': true,
        'columns': [
            {
                'data': 'releaseYear'
            },
            {
                'data': 'brand'
            },
            {
                'data': 'type'
            },
            {
                'data': 'description'
            },
            {
                'data': 'price'
            },
            {
                'data': 'amount'
            },
            {
                'data': 'date'
            },
            {
                'orderable': false,
                'defaultContent': '',
                'render': function (data, type, row) {
                    if (type == 'display') {
                        return '<span>' + (row.amount > 0 ? 'Да' : 'Нет') + '</span>';
                    }
                    return data;
                }
            },
            {
                'orderable': false,
                'defaultContent': '',
                'render': renderEditButton
            },
            {
                'orderable': false,
                'defaultContent': '',
                'render': renderDeleteButton
            }
        ],
        'order': [
            [
                0,
                'desc'
            ]
        ],
        'createdRow': function (row, data, dataIndex) {
            $(row).addClass(data.amount > 0 ? 'available' : 'not_available')
        },
        'initComplete': makeEditable
    });
});
