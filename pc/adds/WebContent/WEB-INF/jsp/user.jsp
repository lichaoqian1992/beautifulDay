<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告管理系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">

                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="img/profile_small.jpg" />
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold" id="user-name">张三丰</strong>
                             </span>  <span class="text-muted text-xs block" id="lv-privilege">超级管理员 <b class="caret"></b></span> </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="javascript:;">修改头像</a>
                                </li>
                                <li><a href="javascript:;">个人资料</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="login.html">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            广告管理系统
                        </div>

                    </li>
                    <li><a href="toMain"><i class="fa fa-th-large"></i> <span
                        class="nav-label">主页</span></a></li>

                    <li>
                        <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">广告管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="toAdds">广告列表</a>
                            </li>
                            <li><a href="toAddAdds">新增广告</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">广告位管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="toPlace">广告位列表</a>
                            </li>
                            <li><a href="toAddPlace">新增广告位</a>
                            </li>
                        </ul>
                    </li>
                    <li class="active">
                        <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">用户管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="toUser">用户列表</a>
                            </li>
                            <li><a href="toAddUser">添加用户</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>

        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a href="toMain" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用广告管理系统</span>
                        </li>
                        <li>
                            <a href="login.html">
                                <i class="fa fa-sign-out"></i>退出
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>用户管理</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="toMain">主页</a>
                        </li>
                        <li>
                            <strong>用户列表</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>用户列表</h5>
                                
                            </div>
                            <div class="ibox-content">
                                <div class="">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newUser">
                                        新增
                                    </button>
                                </div>
                                <table class="table table-striped table-bordered table-hover " id="userList">
                                    <thead>
                                        <tr>
                                            <th>用户名</th>
                                            <th>用户组</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal inmodal" id="newUser" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span>
                            </button>
                            <h4 class="modal-title">新增用户</h4>
                        </div>
                        <small class="font-bold">
                        <form class="form-horizontal m-t" action="addUser" method="post" id="signupForm" novalidate="novalidate">
                            <div class="modal-body">
                                
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">用户名：</label>
                                        <div class="col-sm-8">
                                            <input id="username" name="username" required="required" class="form-control" type="text" aria-required="true" aria-invalid="true">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码：</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="password" required="required" class="form-control" type="password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">确认密码：</label>
                                        <div class="col-sm-8">
                                            <input id="surePassword" required="required" class="form-control" type="password" onchange="checkPassword()">
                                        </div>
                                        <span id="showMessage" style="color: red;display: none;font-size: 10px;"></span>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">用户等级：</label>
                                        <div class="col-sm-8">
                                            <input  maxlength="1" required="required" name="privilege" placeholder="请输入0-5之间的数字" class="form-control" type="text" aria-required="true" aria-invalid="true">
                                        </div>
                                    </div>
                                
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary">保存</button>
                            </div>
                            </form>
                        </small>
                    </div>
                    <small class="font-bold">
                    </small>
                </div>
                <small class="font-bold">
                </small>
            </div>
            <div class="modal inmodal" id="updateUser" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span>
                            </button>
                            <h4 class="modal-title">修改用户</h4>
                        </div>
                        <small class="font-bold">
                        <form class="form-horizontal m-t" action="updUser" method="post" id="updateForm" novalidate="novalidate">
                            <div class="modal-body">
                                
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">用户名：</label>
                                        <div class="col-sm-8">
                                            <input  name="username" readonly  class="form-control" type="text" aria-required="true" aria-invalid="true">
                                        </div>
                                        <input type="hidden" name="id" value="">
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码：</label>
                                        <div class="col-sm-8">
                                            <input id="password1" name="password" class="form-control" required="required" type="password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">确认密码：</label>
                                        <div class="col-sm-8">
                                            <input id="surePassword1" class="form-control" required="required" type="password" onchange="checkPassword1()">
                                        </div>
                                        <span id="showMessage1" style="color: red;display: none;font-size: 10px;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">用户等级：</label>
                                        <div class="col-sm-8">
                                            <input  maxlength="1" required="required" name="privilege" placeholder="请输入0-5之间的数字" class="form-control" type="text" aria-required="true" aria-invalid="true">
                                        </div>
                                    </div>
                                
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary">保存</button>
                            </div>
                            </form>
                        </small>
                    </div>
                    <small class="font-bold">
                    </small>
                </div>
                <small class="font-bold">
                </small>
            </div>
            <div class="footer">
                <div class="pull-right">
                    By：<a href="javascript:void" target="_blank">manjiwang.com</a>
                </div>
                <div>
                    <strong>Copyright</strong> manjiwang.com &copy; 2016
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
 <script src="js/plugins/layer/layer.min.js"></script>
    <!-- Custom and plugin javascript -->
    <script src="js/hplus.js?v=2.2.0"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
 <script src="js/plugins/validate/jquery.validate.min.js"></script>
  <script src="js/plugins/validate/messages_zh.min.js"></script>
    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
        	$.validator.setDefaults({
                highlight: function (element) {
                    $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
                },
                success: function (element) {
                    element.closest('.form-group').removeClass('has-error').addClass('has-success');
                },
                errorElement: "span",
                errorClass: "help-block m-b-none",
                validClass: "help-block m-b-none"


            });
      	  $("#signupForm").validate({
      		  ignore: []
      	  });
      	 $("#updateForm").validate({
     		  ignore: []
     	  });
        	var username="<%=session.getAttribute("username")%>";
      	  var privilege="<%=session.getAttribute("privilege")%>";
      	  var lPrivilege = ""
      	  switch (privilege){
  	          case "0": 
  	        	  lPrivilege = "普通用户";
  	          break;
  	          
  	          case "1": 
  	        	  lPrivilege = "普通用户";
  	          break;
  	          
  	          case "2": 
  	        	  lPrivilege = "管理员";
  	          break;
  	          
  	          case "3": 
  	        	  lPrivilege = "管理员";
  	          break;
  	          
  	          case "4": 
  	        	  lPrivilege = "管理员";
  	          break;
  	          
  	          case "5": 
  	        	  lPrivilege = "管理员";
  	          break;
  	      }
      	  $("#user-name").text(username);
      	  $("#lv-privilege").text(lPrivilege);
         // 加载全部电脑端数据
            $.ajax({
                type : "GET",
                url : "getAllUsers",
                dataType : "json",
                contentType : "application/json; charset=utf-8",
                data : {},
                success : function(data) {
                    if(data.errCode == 0){
                        var user = data.result;
                        var lv;
                        var tbody = $("#userList tbody");
                        for(var i = 0; i < user.length; i++){
                            switch (user[i].privilege){
                                case "0": 
                                    lv = "普通用户";
                                break;
                                
                                case "1": 
                                    lv = "普通用户";
                                break;
                                
                                case "2": 
                                    lv = "管理员";
                                break;
                                
                                case "3": 
                                    lv = "管理员";
                                break;
                                
                                case "4": 
                                    lv = "管理员";
                                break;
                                
                                case "5": 
                                    lv = "管理员";
                                break;
                            }
                            userData = [];
                            var item = '<tr>'+
                                            '<th>'+user[i].username+'</th>'+
                                            '<th>'+lv+'</th>'+
                                            '<th><button type="button" class="un-margin btn btn-xs btn-outline btn-info updatebtn">修改</button><input type="hidden" value="'+user[i].id+'">&nbsp;&nbsp;<button class="un-margin btn btn-xs btn-outline btn-primary delUser">删除</button></th>'+
                                        '</tr>';
                            tbody.append(item);
                        }
                    }
                }
            });

            $("#userList").on("click",".delUser",function(){
                var id = $(this).siblings('input').val();
                var row = $(this).parent().parent();
               $.ajax({
                    type : "GET",
                    url : "delUser",
                    dataType : "json",
                    contentType : "application/json; charset=utf-8",
                    data : {
                        id: id
                    },
                    success : function(data) {
                        if(data.errCode == 0){
                        	console.log($(this))
                        	row.remove();
                        	layer.alert(data.result, 9);
                        }else{
                        	layer.alert(data.errInfo == "" ? '删除失败' : data.errInfo, 8);
                        }
                    }
                });
            });
            $("#userList").on("click",".updatebtn",function(){
                var id = $(this).siblings('input').val();

                $.ajax({
                    type : "GET",
                    url : "getUserById",
                    dataType : "json",
                    contentType : "application/json; charset=utf-8",
                    data : {
                        id: id
                    },
                    success : function(data) {
                    	$("#updateUser input[name='username']").siblings('.username').text(data.username);
                        $("#updateUser input[name='username']").val(data.username);
                        $("#updateUser input[name='privilege']").val(data.privilege);
                        $("#updateUser input[name='id']").val(data.id);
                        $("#password1").val("");
                        $("#surePassword1").val("");
                        $("#showMessage1").removeClass('show');
                        $("#updateUser").modal('show');
                    }
                })
            });
        });
      //校验前后密码是否输入一致
  	  function checkPassword(){
  		  var password = $("#password").val();
  		  var surePassword = $("#surePassword").val();
  		  if(surePassword.trim() != password){
  			  $("#showMessage").addClass("show");
  			  $("#showMessage").html("*前后密码不一致*");
  			  $("#surePassword").val("");
  			  return;
  		  }else{
  			  $("#showMessage").removeClass("show");
  		  }
  	  }
  	//校验前后密码是否输入一致
  	  function checkPassword1(){
  		  var password = $("#password1").val();
  		  var surePassword = $("#surePassword1").val();
  		  if(surePassword.trim() != password){
  			  $("#showMessage1").addClass("show");
  			  $("#showMessage1").html("*前后密码不一致*");
  			  $("#surePassword1").val("");
  			  return;
  		  }else{
  			  $("#showMessage1").removeClass("show");
  		  }
  	  }
    </script>

</body>

</html>
    