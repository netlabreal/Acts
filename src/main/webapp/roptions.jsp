<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="ru">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/man.css">
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <title>Настройки</title>
</head>
<body>
<div class="container-fluid">
    <div class="row" id="header">
        <div class="col-xs-8">
            <h4>Акты сверок</h4>
        </div>
        <div class="col-xs-4">
            <a href="#" id="ff" ></a>
            <span class="glyphicon glyphicon-user" id="enter"> </span>
        </div>
    </div>
    <hr>
    <div class="row" id="content">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li ><a href="/Acts/welcome.jsp"><span class="glyphicon glyphicon-home" id="r_home"></span>Главная</a></li>
                <li class="active"><a href="/Acts/roptions.jsp"><span class="glyphicon glyphicon-wrench" id="r_home"></span>Настройки</a></li>
            </ul>
        </div>

        <div class="col-xs-9">
            <ul class="nav nav-tabs" id="doppanel">
                <li class="active"><a href="#users" data-toggle="tab">Пользователи</a></li>
                <li><a href="#params" data-toggle="tab">Данные</a></li>
            </ul>
            <div class="tab-content" id="doppanel">

                <div class="tab-pane" id="params">
                    <br>
                    <table class="table table-hover table-bordered table-condensed" >
                        <thead>
                        <tr>
                            <th>Организация</th>
                            <th>Количество записей</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr id="curs">
                            <td >Комфорт Сервис</td>
                            <td>125000 </td>
                        </tr>
                        <tr id="curs">
                            <td>ЧГ ЮГ</td>
                            <td>100</td>
                        </tr>
                        </tbody>
                    </table>

                    <button type="button" class="btn btn-primary">Добавить данные</button>
                </div>

                <div class="tab-pane active" id="users">
                    <br>
                    <table class="table table-hover table-bordered table-condensed" >
                        <thead id="user_table">
                        <tr>
                            <th>№</th>
                            <th>Логин</th>
                            <th>Пользователь</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>


                    <button type="submit" class="btn btn-primary" id="ssilka" data-toggle="modal" data-target="#EditModal">Добавить пользователя</button>
                    <img id="loadImg" src="ajax-loader.gif" />
                </div>
            </div>

        </div>
        --


    </div>

</div>

<br>
<hr>
<div id="footer">
    <div class="container">
        <div class="well well-sm"> <p>&copy; Argument 2014.</p></div>
    </div>
</div>

</div>



<div class="modal fade" id="EditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div class="panel panel-default" id="paneledit">
                    <div class="panel-body">
                        Логин<input type="text" class="form-control" id="modallogin">
                        Пользователь<input type="text" class="form-control" id="modaluser">
                        Пароль<input type="password" class="form-control" id="modalpass">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" id="modalcheck"> Административные права
                            </label>
                        </div>
                    </div>
                </div>
                </div>
            <div class="modal-footer">
                <img id="loadImgModal" src="ajax-loader.gif" style="display: none"/>
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button type="button" class="btn btn-primary" id="add_user">Сохранить изменения</button>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">
    $(function () {
        var par = 0;
function all_users(){
    var param = "name=all_users";
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/Acts/data",
        data: param,
        beforeSend: function (data) {
            $("#loadImg").show();
            $("#loadImg").append("Подождите! Идет загрузка данных.");
        },
        success: function (data) {
            $('#user_table').empty();
            $('#user_table').append("<tr><th>№</th><th>Логин</th><th>Пользователь</th><th></th></tr>");
            $("#loadImg").empty();
            $("#loadImg").hide();
            for(var i = 0;i<data.length;i++)
            {
                $('#user_table').append(" <tr id='curs'>" +
                        "<td>"+data[i].id+"</td>" +
                        "<td>"+data[i].login+"</td>" +
                        "<td>"+data[i].username+"</td>" +
                        "<td><button type='button' class='btn btn-primary btn-xs btn_edit' id='ssilka' style='float: right;margin-left: 5px'>Изменить</button>" +
                        "<button type='button' class='btn btn-primary btn-xs btn_del' id='ssilka' style='float: right;margin-left: 5px'>Удалить</button>" +
                        "</td></tr>");
            }
////////////////////////////////////////////////////////////////////////////////
            $('.btn_del').click(function(){
                var kk = $(this).parent().parent();
                var ss = kk.find("td:eq(1)");
                var param = 'name=del_user'+'&login=' + encodeURIComponent(ss.text());
////////////////////////////////////////////////////////////////////////////////
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/Acts/data",
                    data: param,
                    beforeSend: function (data) {
                        $("#loadImg").show();
                        $("#loadImg").append("Подождите! Идет удаление данных.");
                    },
                    success: function (data) {
                        $("#loadImg").hide();
                        $("#loadImg").empty();
                        if(data.res==0){
                            $("#loadImg").append("Такого пользователя не существует!")
                        }
                        else{
                        all_users();
                        }
                    },
                    error: function (result) {
                        alert("error");
                    }
                });
////////////////////////////////////////////////////////////////////////////////
            });

////////////////////////////////////////////////////////////////////////////////
            $('.btn_edit').click(function(){
                par =1;
                var kk = $(this).parent().parent();
                var ss = kk.find("td:eq(1)");
                $('#modallogin').attr('disabled', true);
                var param = 'name=user'+'&login=' + encodeURIComponent(ss.text());
////////////////////////////////////////////////////////////////////////////////
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/Acts/data",
                    data: param,
                    beforeSend: function (data) {
                        $("#loadImgModal").append("Подождите! Идет загрузка данных.");
                        $("#loadImgModal").show();
                    },
                    success: function (data) {
                        $('#modallogin').val(data.login);
                        $('#modaluser').val(data.username);
                        $('#modalpass').val(data.pass);
                        if(data.adm==1){
                            $('#modalcheck').prop("checked",true);
                        }
                        else{
                            $('#modalcheck').prop("checked",false);
                        }
                        $('#myModalLabel').empty();
                        $('#myModalLabel').append("Изменить пользователя");
                        $('#EditModal').modal();
                        $("#loadImgModal").empty();
                        $("#loadImgModal").hide();
                    },
                    error: function (result) {
                        alert("error");
                    }
                });
////////////////////////////////////////////////////////////////////////////////
            });
        },
        error: function (result) {
            alert("error");
        }
    });
}
        all_users();

        $('#sell').change( function () {
            $('#dom').html("");
            $('#dom').append(" <option>"+$('#sell').val()+" d1</option> <option>"+$('#sell').val()+" d2</option> <option>"+$('#sell').val()+"d3</option>");
            $('#dom').focus();
        });

        $('#ssilka').click(function(){
            $('#modallogin').val("");
            $('#modaluser').val("");
            $('#modalpass').val("");
            $('#modalcheck').prop("checked",false);

            $('#modallogin').attr('disabled', false);


            $('#myModalLabel').empty();
            $('#myModalLabel').append("Добавить нового пользователя");
        });

        $('#add_user').click(function(){
            if($('#modallogin').val()!="" && $('#modaluser').val()!="" && $('#modalpass').val()!="") {
                if(par==0) {
                    var param = 'name=add_user' + '&login=' + encodeURIComponent($('#modallogin').val()) + '&username=' +
                            encodeURIComponent($('#modaluser').val()) +
                            '&pass=' + encodeURIComponent($('#modalpass').val()) +
                            '&adm=' + encodeURIComponent($('#modalcheck').prop("checked"));
                }
                else{
                    var param = 'name=edit_user' + '&login=' + encodeURIComponent($('#modallogin').val()) + '&username=' +
                            encodeURIComponent($('#modaluser').val()) +
                            '&pass=' + encodeURIComponent($('#modalpass').val()) +
                            '&adm=' + encodeURIComponent($('#modalcheck').prop("checked"));

                    par=0;
                }

////////////////////////////////////////////////////////////////////////////////
                            $.ajax({
                                type: "POST",
                                dataType: "json",
                                url: "/Acts/data",
                                data: param,
                                beforeSend: function (data) {

                        //            $("#loadImgModal").show();
                                },
                                success: function (data) {
                                    all_users();
                          //          $("#loadImgModal").hide();
                                },
                                error: function (result) {
                                    alert("error");
                                }
                            });
////////////////////////////////////////////////////////////////////////////////
                $('#EditModal').modal("hide");
            }
        });


    });
</script>
</body>
</html>