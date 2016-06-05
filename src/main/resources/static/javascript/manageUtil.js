var form;

function makeEditable() {
    form = $('#detailsForm');
    form.submit(function () {
        save();
        return false;
    });
    
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNote(event, jqXHR, options, jsExc);
    });
}

function login() {
    $('#loginModal').modal();
}

function add() {
    form.find(':input').val('');
    form.find("img[name='imageUrl']").attr('src', 'https://placehold.it/400x250/000/fff');
    $('#releaseYear').val(2000);
    $('#price').val(0);
    $('#amount').val(0);
    $('#editModal').modal();
}

function save() {
    var formData = new FormData(form[0]);
    $.ajax({
        url: ajaxUrl,
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        success: function () {
            $('#editModal').modal('hide');
            updateTable();
            successNote('Saved');
        }
    });
}

function viewRow(id) {
    var viewForm = $('#viewForm');
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            viewForm.find("input[name='" + key + "']").val(value);
            viewForm.find("textArea[name='" + key + "']").val(value);
            viewForm.find("img[name='" + key + "']").attr('src', value);
        });
    });
    $('#viewModal').modal();
}

function updateRow(id) {
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            form.find("textArea[name='" + key + "']").val(value);
            form.find("img[name='" + key + "']").attr('src', value === '' ? 'https://placehold.it/400x250/000/fff' : value);
        });
        $('#editModal').modal();
    });
}

function removeRow(id) {
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
    datatableApi.clear().rows.add(data).draw();
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
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNote(event, jqXHR, options, jsExc) {
    closeNote();
    var httpExceptionInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + '<br>' + httpExceptionInfo.cause + '<br>' + httpExceptionInfo.detail,
        type: 'error',
        layout: 'bottomRight'
    });
}

function renderEditButton(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="updateRow(' + '\'' + row.id + '\'' + ');">Edit <span class="glyphicon glyphicon-edit"></span></a>';
    }
    return data;
}

function renderDeleteButton(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-danger" onclick="removeRow(' + '\'' + row.id + '\'' + ');">Delete <span class="glyphicon glyphicon-trash"></span></a>';
    }
    return data;
}

function renderViewButton(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="viewRow(' + '\'' + row.id + '\'' + ');">View <span class="glyphicon glyphicon-picture"></span></a>';
    }
    return data;
}