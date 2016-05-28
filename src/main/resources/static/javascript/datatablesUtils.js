var form;

function makeEditable() {
    //noinspection JSUnresolvedFunction
    form = $('#detailsForm');

    form.submit(function () {
        save();
        return false;
    });

    //noinspection JSUnresolvedFunction
    $(function () {
        //noinspection JSUnresolvedFunction
        var token = $("meta[name='_csrf']").attr("content");
        //noinspection JSUnresolvedFunction
        var header = $("meta[name='_csrf_header']").attr("content");
        //noinspection JSUnresolvedFunction,JSUnusedLocalSymbols
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

}

function add() {
    //noinspection JSUnresolvedFunction
    form.find(':input').val('');
    //noinspection JSUnresolvedFunction
    $('#id').val(0);
    //noinspection JSUnresolvedFunction
    $('#editModal').modal();
}

function updateRow(id) {
    //noinspection JSUnresolvedVariable
    $.get(ajaxUrl + id, function (data) {
        //noinspection JSUnresolvedFunction,JSUnresolvedVariable
        $.each(data, function (key, value) {
            //noinspection JSUnresolvedFunction
            form.find("input[name='" + key + "']").val(value);
            //noinspection JSUnresolvedFunction
            form.find("textArea[name='" + key + "']").val(value);
        });
        //noinspection JSUnresolvedFunction
        $('#editModal').modal();
    });
}

function removeRow(id) {
    //noinspection JSUnresolvedVariable
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNote('Deleted')
        }
    });
}

function updateTableByData(data) {
    //noinspection JSUnresolvedFunction
    datatableApi.clear().rows.add(data).draw();
}

function save() {
    //noinspection JSUnresolvedFunction,JSUnresolvedVariable
    $.ajax({
        type: 'POST',
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            //noinspection JSUnresolvedFunction
            $('#editModal').modal('hide');
            updateTable();
            successNote('Saved')
        }
    });
}

var failedNote;

function closeNote() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNote(text) {
    closeNote();
    //noinspection JSUnresolvedFunction
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

//noinspection JSUnusedGlobalSymbols,JSUnusedLocalSymbols
function failNote(event, jqXHR, options, jsExc) {
    closeNote();
    //noinspection JSUnresolvedFunction,JSUnresolvedVariable
    var errorInfo = $.parseJSON(jqXHR.responseText);
    //noinspection JSUnresolvedFunction,JSUnresolvedVariable
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + '<br>' + errorInfo.cause + '<br>' + errorInfo.detail,
        type: 'error',
        layout: 'bottomRight'
    });
}

function renderEditButton(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="updateRow(' + '\''+ row.id +'\'' + ');">Edit <span class="glyphicon glyphicon-edit"></span></a>';
    }
    return data;
}

function renderDeleteButton(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-danger" onclick="removeRow(' + '\''+ row.id +'\'' + ');">Delete <span class="glyphicon glyphicon-trash"></span></a>';
    }
    return data;
}

