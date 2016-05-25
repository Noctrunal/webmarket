<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="webjars/datatables/1.10.11/css/jquery.dataTables.min.css">
<head>
    <title>Web Market</title>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div class="view-box">
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Год</th>
                        <th>Производитель</th>
                        <th>Тип</th>
                        <th>Описание</th>
                        <th>Цена</th>
                        <th>Количество</th>
                        <th>Дата</th>
                        <th>Наличие</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
                <a class="btn btn-sm btn-info" onclick="add()">Добавить товар</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Редактирование</h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">
                    <input type="text" hidden="hidden" id="date" name="date">

                    <div class="form-group">
                        <label for="releaseYear" class="control-label col-xs-3">Год выхода</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="releaseYear" name="releaseYear" placeholder="Год выхода">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="brand" class="control-label col-xs-3">Производитель</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="brand" name="brand" placeholder="Производитель">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="type" class="control-label col-xs-3">Тип</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="type" name="type" placeholder="Тип">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Описание</label>

                        <div class="col-xs-9">
                            <textarea class="form-control" id="description" name="description" placeholder="Описание" cols="80" rows="3"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="price" class="control-label col-xs-3">Цена</label>

                        <div class="col-xs-9">
                            <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Цена">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="amount" class="control-label col-xs-3">Количество</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="amount" name="amount" placeholder="Количество">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/javascript/datatablesUtils.js"></script>
<script type="text/javascript" src="resources/javascript/productsDatatables.js"></script>
</html>
