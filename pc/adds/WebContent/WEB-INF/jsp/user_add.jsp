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

<title>广告管理系统</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link href="${base}/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
<link href="${base}/font-awesome/css/font-awesome.css?v=4.3.0"
	rel="stylesheet">

<!-- Data Tables -->
<link href="${base}/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<link href="${base}/css/animate.css" rel="stylesheet">
<link href="${base}/css/style.css?v=2.2.0" rel="stylesheet">

</head>
<body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
	
						<div class="dropdown profile-element">
							<span> <img alt="image" class="img-circle"
								src="img/profile_small.jpg" />
							</span> <a data-toggle="dropdown" class="dropdown-toggle"
								href="index.html#"> <span class="clear"> <span
									class="block m-t-xs"> <strong class="font-bold" id="user-name">张三丰</strong>
								</span> <span class="text-muted text-xs block" id="lv-privilege">超级管理员 <b
										class="caret"></b></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a href="javascript:;">修改头像</a></li>
								<li><a href="javascript:;">个人资料</a></li>
								<li class="divider"></li>
								<li><a href="javascript:;">安全退出</a></li>
							</ul>
						</div>
						<div class="logo-element">广告管理系统</div>
	
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
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><span class="m-r-sm text-muted welcome-message">
							<a href="toMain" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用广告管理系统</span>
						</li>
						<li><a href="javascript:;"> <i class="fa fa-sign-out"></i>退出
						</a></li>
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
                            <strong>添加用户</strong>
                        </li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<form class="form-horizontal m-t" id="signupForm" method="post" action="addUser" novalidate="novalidate">
                    
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
                            <input id="surePassword" class="form-control" required="required" type="password" onchange="checkPassword()">
                        </div>
                        <span id="showMessage" style="color: red;display: none;"></span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户等级：</label>
                        <div class="col-sm-8">
                            <input  maxlength="1" required="required" name="privilege" placeholder="请输入0-5之间的数字" class="form-control" type="text" aria-required="true" aria-invalid="true">
                        </div>
                    </div>
                    <div class="form-group" style="padding-left: 18px;">
                        <button class="btn btn-primary col-sm-offset-3" type="submit">提交</button>
                    </div>
                    
                </form>
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

	<script src="js/plugins/jeditable/jquery.jeditable.js"></script>

 <script src="js/plugins/validate/jquery.validate.min.js"></script>
  <script src="js/plugins/validate/messages_zh.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="js/hplus.js?v=2.2.0"></script>
	<script src="js/plugins/pace/pace.min.js"></script>

	<!-- Page-Level Scripts -->
	<script>
	$("#signupForm").validate({
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
    </script>
</body>
</html>