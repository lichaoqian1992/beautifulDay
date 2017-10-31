<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>满集数据总后台</title>
    <link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="media/image/favicon.ico" />

</head>



<body class="page-header-fixed">

<div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">

            <a class="brand" href="index.html">
                <img src="media/image/logo.png" alt="logo" />
            </a>
            <div class="navbar hor-menu hidden-phone hidden-tablet">
                <div class="navbar-inner">
                    <jsp:include page="/WEB-INF/jsp/banner.jsp" flush="true"/>
                </div>
            </div>
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <img src="media/image/menu-toggler.png" alt="" />
            </a>
            <jsp:include page="/WEB-INF/jsp/userinfo.jsp" flush="true"/>
        </div>
    </div>
</div>



<div class="page-container row-fluid" >
    <div class="page-sidebar nav-collapse collapse">
        <jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true"/>
    </div>

    <div class="page-content">

        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        新建查询		<!-- <small>正常账户列表</small> -->
                    </h3>
                    <ul class="breadcrumb">
                        <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
                        <li><a href="#">新建查询</a><i class="icon-angle-right"></i></li>
                        <li><a href="#">SQL查询</a></li>
                    </ul>
                </div>
            </div>

            <div class="row-fluid margin-bottom-20">

            </div>
            <div class=row_fluid>
                <ul>
                        <textarea rows="10" cols="20" style="width: 100%;height: 160px; font-size: 24px;" id="sql" placeholder="请输入sql语句"></textarea>
                        <a class="btn btn-primary search_sub" style="margin-top:20px;width: 100px;height: 30px;text-align: center;line-height: 30px
                        " onclick="findsql()">执行</a>

                </ul>
            </div>


            <div class="row-fluid" style="margin-top:250px;">

                <div class="span12">
                    <input type="button" value="导出excl" class="btn btn-info" style="float: right" id="excl"/>
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption"> 查询结果</div>
                        </div>



                        <div class="portlet-body">

                            <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid" style="width:100%; height:1024px; overflow:scroll;">

                                <table class="table table-striped table-bordered table-hover dataTable" >

                                    <thead>
                                    <tr role="row" id="thead">

                                    </tr>
                                    </thead>

                                    <tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">

                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>
<script src="media/js/app.js"></script>
<script>

    $(document).ready(function() {

        //点击导出excl
        $('#excl').click(function(){
            var sql=$('#sql').val();

            if (sql==""){
                alert("请输入sql语句");
                return;
            }
            location.href="/backstage/exclSqlfind?sql="+sql;
        });

        App.init();
        menuact("12_01_01");
    });

    function findsql(){

        var sql=$('#sql').val();

        if (sql==""){
            alert("请输入sql语句");
            return;
        }


        $.ajax({
            type: "GET",
            url: "sqlfind",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: {"sql":sql},
            success: function (data) {
                var columnhtml="";
                var tablebodyhtml="";
                if (data.isok=="success"){
                    for (var i=0;i<data.column.length;i++){
                        columnhtml+="<th>"+data.column[i]+"</th>";
                    }

                        var k=0;
                    for (var j=0;j<data.val.length;j++) {

                        if (k == 0) {
                            tablebodyhtml += "<tr><td>" + data.val[j] + "</td>";
                        } else if (k == data.column.length) {
                            tablebodyhtml += "<td>" + data.val[j] + "</td></tr>";
                        } else {
                            tablebodyhtml += "<td>" + data.val[j] + "</td>";
                        }
                        k++;
                        if (k==data.column.length){
                            k=0;
                        }
                    }
                    $('#thead').html("");
                    $('#tablebody').html("");
                    $('#thead').html(columnhtml);
                    $('#tablebody').html(tablebodyhtml);

                //修改成功提示
                }else if(data.isok=="successupdate"){
                    $('#thead').html("");
                    $('#tablebody').html("");
                     var  thead = "<th>update success</th>";
                    $('#thead').html(thead);
                 //新增成功提示
                }else if(data.isok=="successinsert"){
                    $('#thead').html("");
                    $('#tablebody').html("");
                    var  thead = "<th>insert success</th>";
                    $('#thead').html(thead);
                 //删除成功提示
                }else if(data.isok=="successdelect"){
                    $('#thead').html("");
                    $('#tablebody').html("");
                    var  thead = "<th>delect success</th>";
                    $('#thead').html(thead);
                }
                else{
                    $('#thead').html("");
                    $('#tablebody').html("");
                    var  thead = "<th style='color: red'>"+data.isok+"</th>";
                    $('#thead').html(thead);
                }


            }
        });




    }






</script>

</body>

</html>