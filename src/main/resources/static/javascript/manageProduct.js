var ajaxUrl = '/ajax/admin/products/';
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
                'orderable': false,
                'defaultContent': '',
                'render': function (data, type, row) {
                    if (type == 'display') {
                        return '$' + row.price;
                    }
                }
            },
            {
                'data': 'amount'
            },
            {
                'orderable': false,
                'defaultContent': '',
                'render': function (data, type, row) {
                    if (type == 'display') {
                        return '<span>' + (row.amount > 0 ? 'Yes' : 'No') + '</span>';
                    }
                    return data;
                }
            },
            {
                'orderable': false,
                'defaultContent': '',
                'render': renderViewButton
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
        dom: 'Bfrtip',
        buttons: [
            {
                extend: 'collection',
                text: 'Export <span class="glyphicon glyphicon-export"></span>',
                buttons: [
                    {
                        extend: 'copyHtml5',
                        text: 'Copy <i class="fa fa-files-o"></i>',
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6, 7]
                        }
                    },
                    {
                        extend: 'csvHtml5',
                        text: 'CSV <i class="fa fa-file-text-o"></i>',
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6, 7]
                        }
                    },
                    {
                        extend: 'excelHtml5',
                        text: 'Excel <i class="fa fa-file-excel-o"></i>',
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6, 7]
                        }
                    },
                    {
                        extend: 'pdfHtml5',
                        text: 'PDF <i class="fa fa-file-pdf-o"></i>',
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6, 7]
                        }
                    },
                    {
                        extend: 'print',
                        text: 'Print <span class="glyphicon glyphicon-print"></span>',
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6, 7]
                        }
                    }
                ]
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
