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
            <a href="logout" id="ff" >Выход</a>
            <span class="glyphicon glyphicon-user" id="enter">
            <%
                String user = (String)request.getSession().getAttribute("username");
                out.println(user);
            %>
            </span>
        </div>
    </div>
    <hr>
    <div class="row" id="content">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="/Acts/welcome.jsp"><span class="glyphicon glyphicon-home" id="r_home"></span>Главная</a></li>
                <li><a href="/Acts/roptions.jsp"><span class="glyphicon glyphicon-wrench" id="r_home"></span>Настройки</a></li>
            </ul>
        </div>

        <div class="col-xs-9">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-xs-4">
                        <select class="form-control input-sm" id="street">
                        </select>
                    </div>
                    <div class="col-xs-8">
                        <select class="form-control input-sm" id="dom">
                            <option selected disabled>Выберите дом</option>
                            <option>1</option>
                            <option>1A</option>
                            <option>5</option>
                        </select>
                        <select class="form-control input-sm" id="kw">
                            <option selected disabled>Выберите квартиру</option>
                            <option>11</option>
                            <option>12</option>
                            <option>51</option>
                        </select>
                    </div>
                   <br>
                    <div class="col-xs-4">
                        <div id="ls_id" style="display: none"></div>
                        <input type="text" class="form-control" id="fio">

                        <button type="button" class="btn btn-primary" id="data">Вывести данные</button>
                    </div>
                    <div class="col-xs-8">
                        <select class="form-control input-sm" id="org">
                            <option selected disabled>Выберите организацию</option>
                        </select>

                    </div>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-body">
                    <img id="loadImg" src="ajax-loader.gif" style="display: none"/>
                    <div class="real_data" id="real_data">

                    </div>
                    <button type="button" class="btn btn-primary" id="print">Распечатать</button>
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

        function Print() {
            text = document.getElementById('real_data').innerHTML;
            printwin = open('', 'printwin', 'width=900,height=600');
            printwin.document.open();
            printwin.document.writeln('<html><head>' +
                    '<link rel="stylesheet" href="css/bootstrap.min.css">'+
                    '<link rel="stylesheet" href="css/man.css">'+
                    '<title></title></head><body onload=print();close()>');
            printwin.document.writeln(" <div class='well well-sm'>Расчет суммы задолженности за потребленные услуги."+
                    "<br><br>Квартиросъемщик:  "+$("#fio").val()+
                    "<br>Адрес:  ул. "+ $("#street option:selected" ).text()+" д. "+$("#dom option:selected" ).text()+" кв. "+$("#kw option:selected" ).text()+
                    "<br>Организация: "+ $("#org option:selected" ).text()+

                    "</div>");

            printwin.document.writeln(text);
            printwin.document.writeln('</body></html>');
            printwin.document.close();
        }

        $("#data").hide();
        $("#print").hide();

function All_data(){
    var param = "name=dat&ls_id="+$("#ls_id").val();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/Acts/adr",
        data:param,
        beforeSend:function(data){
            $("#loadImg").show();
            $("#loadImg").append("Подождите! Идет загрузка данных.");
            $(".real_data").empty(); $("#print").hide();
            $(".real_data").append("<div class='col-xs-12 table-responsive'>"+
                "<table class='table table-hover table-bordered table-condensed' >"+
                "<thead><tr><th>Дата</th> <th>ВхСальдо</th><th>Начислено</th><th>Акт</th><th>Перерасчет</th><th>Оплачено</th><th>ИсхСальдо</th> </tr></thead>"+
                " <tbody id='rr'></tbody></table></div>"
            );
        },
        success:function(data){
            var inach = 0;
            var iopl = 0;
            for(var i =0;i<data.length;i++)
            {
                $("#rr").append("<tr><td>"+data[i].month+"</td><td>"+data[i].vx+"</td><td>"+data[i].nach+
                        "<td>"+data[i].act+"</td><td>"+data[i].per+"</td><td>"+data[i].opl+"<td>"+data[i].isx+
                        "</td></tr>");
                inach +=data[i].nach;
                iopl +=data[i].opl;
            }
            $("#rr").append("<tr><td>ИТОГО:"+"</td><td>"+"</td><td>"+inach.toFixed(2)+
                    "<td>"+"</td><td>"+"</td><td>"+iopl.toFixed(2)+"<td>"+
                    "</td></tr>");
            $("#loadImg").hide();

            $("#print").show();

        },
        error:function(result){
            alert("Server Error!!!");
        }
    })
}

function All_streets(){
    var param = "name=streets";
    $.ajax({
       type:"POST",
       dataType:"json",
       url:"/Acts/adr",
       data:param,
        beforeSend:function(data){
            $("#loadImg").show();
            $("#loadImg").append("Подождите! Идет загрузка данных.");
        },
        success:function(data){
            $('#street').attr('disabled', false);
            $("#street").empty();
            $("#street").append("<option selected disabled>Выберите улицу</option>");
            for(var i =0;i<data.length;i++)
            {
                $("#street").append("<option value="+data[i].id+">"+data[i].name+"</option>");
            }
            $("#loadImg").hide();
        },
        error:function(result){
            alert("Server Error!!!");
        }
    });
}
function All_doma(){
    var param = "name=doma&str_id="+$('#street').val();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/Acts/adr",
        data:param,
        beforeSend:function(data){
            $("#loadImg").show();
        },
        success:function(data){
            $('#dom').attr('disabled', false);
            $("#dom").empty();
            $("#dom").append("<option selected disabled>Выберите дом</option>");
            for(var i =0;i<data.length;i++)
            {
                $("#dom").append("<option value="+data[i].id+">"+data[i].name+"</option>");
            }
            $("#loadImg").hide();
        },
        error:function(result){
            alert("Server Error!!!");
        }
    })
}
function ALL_kw(){
    var param = "name=kw&dom_id="+$('#dom').val();

    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/Acts/adr",
        data:param,
        beforeSend:function(data){
            $("#loadImg").show();
        },
        success:function(data){
            $('#kw').attr('disabled', false);
            $("#kw").empty();
            $("#kw").append("<option selected disabled>Выберите квартира</option>");
            for(var i =0;i<data.length;i++)
            {
                $("#kw").append("<option value="+data[i].id+">"+data[i].name+"</option>");
            }
            $("#loadImg").hide();
        },
        error:function(result){
            alert("Server Error!!!");
        }
    })

}
function Get_ls(){
    var param = "name=ls&kw_id="+$('#kw').val();

    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/Acts/adr",
        data:param,
        beforeSend:function(data){
            $("#loadImg").show();
        },
        success:function(data){
          $("#fio").val(data.name);
          $("#ls_id").val(data.id);
          $("#loadImg").hide();
        },
        error:function(result){
            alert("Server Error!!!");
        }
    });
}
function All_org(){
    var param = "name=orgs";

    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/Acts/adr",
        data:param,
        beforeSend:function(data){
            $("#loadImg").show();
        },
        success:function(data){
            $("#org").empty();
            $("#org").append("<option selected disabled>Выберите организацию</option>");
            for(var i =0;i<data.length;i++)
            {
                $("#org").append("<option value="+data[i].id+">"+data[i].name+"</option>");
            }
            $("#loadImg").hide();
        },
        error:function(result){
            alert("Server Error!!!");
        }
    })
}

        $("#print").click(function(){
            Print();
        });

        $('#street').attr('disabled', true);
        $('#dom').attr('disabled', true);
        $('#kw').attr('disabled', true);
        $('#fio').attr('disabled', true);
        $('#ls_id').val("22");

        All_streets();


        $("#street").change(function(){
            All_doma();
            $('#kw').empty();
            $("#kw").append("<option selected disabled>Выберите квартира</option>");
            $('#kw').attr('disabled', true);
        });
        $("#dom").change(function(){
            $('#fio').val("");
            ALL_kw();
        });
        $("#kw").change(function(){
            Get_ls();
            All_org();
        });
        $("#org").change(function() {
            $("#data").show();
            });

        $("#data").click(function(){
           All_data();
        });
    });
</script>
</body>
</html>