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