<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="ru">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/man.css">
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <title>Акты сверок</title>
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
                <li class="active"><a href="#"><span class="glyphicon glyphicon-home" id="r_home"></span>Главная</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-hdd" id="r_home"></span>Загрузка данных</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-wrench" id="r_home"></span>Настройки</a></li>
            </ul>
        </div>

        <div class="col-xs-9">





            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-xs-12">
                        <input type="File" id="file_send">
                        </select>
                    </div>

                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-body">
                </div>
            </div>

        </div>


    </div>

</div>

<br>
<hr>
<div id="footer">
    <div class="container">
        <div class="well well-sm"> <p>&copy; .</p></div>
    </div>
</div>

</div>



<script type="text/javascript">
    $(function () {

    });
</script>
</body>
</html>