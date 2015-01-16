<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="ru">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/man.css">
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <title>Аутентификация</title>
</head>
<body>
<div class="container-fluid">
    <div class="row" id="header">
        <div class="col-xs-8">
            <h4>Акты сверок.</h4>
        </div>
        <div class="col-xs-4">
            <a href="#" id="ff" ></a>
            <span class="glyphicon glyphicon-user" id="enter"></span>
        </div>
    </div>
    <hr>

    <div class="row" id="content_login">
        <div class="col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">Войти</div>
                <div class="panel-body">
                    <input id="login" type="text" class="form-control" placeholder="Введите логин" required>
                    <input id="password" type="password" class="form-control" placeholder="Пароль" required>
                    <button class="btn btn-lg btn-primary btn-block" id="ssilka">Войти</button>
                </div>
            </div>
        </div>
    </div>

    <div class="mes">

    </div>
    <hr>
    <div id="footer">
        <div class="container">
            <p>&copy; 2014.</p>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#ssilka').click(function () {
            if($('#login').val()=="" || $('#password').val()==""){
                if($('#password').val()==""){$('#password').focus();}
                if($('#login').val()==""){$('#login').focus();}

                $('.mes').html("");
                $('.mes').append("Введите корректные данные!!!");
            }
            else
            {
                var params = 'login=' + encodeURIComponent($('#login').val()) +
                        '&password=' + encodeURIComponent($('#password').val());
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/Acts/login",
                    data: params,
                    beforeSend: function(data){
                        $(".mes").html("Ждите ответа");
                    },
                    success: function (data) {
                        if(data.username=="")
                        {
                            $('.mes').html("");
                            $('.mes').append("Неверное имя пользователя или пароль!!!");
                            $('#login').val("");
                            $('#password').val("");
                            $('#login').focus();

                        }
                        else{
                            window.location.replace("/Acts/welcome.jsp");
                        }

                    },
                    error: function (result) {
                        alert("error with server!!!");
                    }
                });
            }
        });

    });
</script>

</body>
</html>