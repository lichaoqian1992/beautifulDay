<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>满集数据后台系统</title>

<link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
<link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
<link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=2.2.0" rel="stylesheet">
<link href="js/plugins/layer/skin/layer.css" rel="stylesheet">
<script src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
		<div id="side-menu">
			<ul class="nav" >
				<li class="nav-header abc"></li>
				
				
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">品牌管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="selBrand">品牌列表</a></li>
						<li><a href="insBrand">新增品牌</a></li>
					</ul></li>
					
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">规格管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="selSpec">规格列表</a></li>
						<li><a href="insSpec">新增规格</a></li>
					</ul></li>
					
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">属性管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="selAttri">属性列表</a></li>
						<li><a href="insAttri">新增属性</a></li>
					</ul></li>
				
				
				<li class="k2"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">用户组别管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="selUserGroup">用户组别列表</a></li>
						<li><a href="*">用户组别统计</a></li>
						<li><a href="*">用户组别调整</a></li>
						<li><a href="*">用户组别变化记录</a></li>
					</ul></li>

				<li class="k2"><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">用户安全</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">用户验证记录</a></li>
						<li><a href="*">用户安全状态</a></li>

					</ul></li>
					
					
				<li class="k11"><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">后台用户管理</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">登录用户列表</a></li>
						<li><a href="*">分配用户角色</a></li>

					</ul></li>
					
				<li class="k11"><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">权限管理</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">角色管理</a></li>
						<li><a href="*">权限管理</a></li>
						<li><a href="*">资源管理</a></li>
					</ul></li>
					
				<li class="active k11"><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">操作记录管理</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li class="active"><a href="*">操作记录查询</a></li>
					</ul></li>


				
			</ul>
			</div>
		</div>
		</nav>

		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
						href="#"><i class="fa fa-bars"></i> </a>
					<!-- <form role="search" class="navbar-form-custom" method="post"
						action="search_results.html"> -->
					<div class="row border-bottom">
						<div class="col-lg-9">
							<div class="form-group" style="width: 1000px">
								<ul class="nav navbar-nav menu_first">


									<li class="dropdown"><a  class="dropdown-toggle" onclick="onkey(1)"
										> 运维 </a></li>
									<li class="dropdown"><a  class="dropdown-toggle" onclick="onkey(2)"
										> 用户 </a></li>
									<li class="dropdown"><a   class="dropdown-toggle" onclick="onkey(3)"
										> 商家 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="onkey(4)"
										> 代理</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="onkey(5)"
										> 账户 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="onkey(6)"
										> 交易 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="onkey(7)"
										> 消息 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="onkey(8)"
										> 日志</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="onkey(9)"
										> 监控 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="onkey(10)"
										> 配置 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="onkey(11)"
										> 后台 </a></li>

								</ul>

		

							</div>
						</div>
						
						<script>
						function onkey(key){
				            /* var ul=key+" ul";*/
				            var li=key+" ul li"; 

				            $('#side-menu li').css("display","none");
				            $('.abc').css("display","block");
				            $('.k'+key).css("display","block");
				            $('.k'+li).css("display","block"); 
				            /* $('.k'+key).addClass("active");
				            $('.k'+ul).addClass("in"); */
				        }
						</script>
						
						
						<ul class="nav navbar-top-links navbar-right">
							<li><span>${sessionScope.username}</span></li>
							<li><a href="login.html"> <i class="fa fa-sign-out"></i>
									退出
							</a></li>
						</ul>

					</div>
				</div>


				</nav>
			</div>
			<div id="maincontent">
				<div class="row wrapper border-bottom white-bg page-heading">
					<div class="col-lg-10">
						<h2>操作记录列表</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a href="selAttri">后台管理</a></li>
							<li><strong>操作记录列表</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>






				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">
									
									
									
									<table id="attritable" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>操作人</th>
												<th>操作时间</th>
												<th>模块</th>
												<th>操作类型</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="tablebody">
											
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
	
	<script>
		$(document).ready(function() {
			
			onkey(11);
			filltable();

		
			});
			
			
		function filltable(){
			var date =${loglist }
			var tablehtml="";
			
			$.each(date,function(i,loggers){
				
				var no =Number(i+1);
				var name =loggers.user_name;
				var time =loggers.time;
				var module =loggers.module;
				var type =loggers.type;
				var id =loggers.id;
				
				var tablerow ="<tr>"+
							  "<td>"+no+"</td>"+
							  "<td>"+name+"</td>"+
							  "<td>"+time+"</td>"+
							  "<td>"+module+"</td>"+
							  "<td>"+type+"</td>"+
							  "<td>"+"<a href='readLoggers?id="+id+"'>查看记录</a>"+"</td>"+
							  "</tr>";
							  
				
				
				tablehtml+=tablerow;
				
			})
			
			
			$("#tablebody").html(tablehtml);
			
		};
		
		function querytable(){
			
			$.ajax({
				type : "GET",
				url : "queryLocalLoggers",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {id:$("#categoryid").val()},
				success : function(logdata) {
					var freshhtml="";
					$.each(logdata,function(i,loggers){
						
						var no =Number(i+1);
						var name =loggers.user_name;
						var time =loggers.time;
						var module =loggers.module;
						var type =loggers.type;
						var id =loggers.id;
						var tablerow ="<tr>"+
						  "<td>"+no+"</td>"+
						  "<td>"+name+"</td>"+
						  "<td>"+time+"</td>"+
						  "<td>"+module+"</td>"+
						  "<td>"+type+"</td>"+
						  "<td>"+"<a href='readLoggers("+id+")'>查看记录</a>"+"</td>"+
						  "</tr>";
						  
						  freshhtml+=tablerow;
						
					});
					
					$("#tablebody").html(freshhtml);
				}});
			
			
		};
		
		function readLoggers(id){
			
			
			
			
			
		};
		
		
		
	</script>


	<!-- Mainly scripts -->

	<script src="js/bootstrap.min.js?v=3.4.0"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="js/hplus.js?v=2.2.0"></script>
	<script src="js/plugins/pace/pace.min.js"></script>

	<!-- jQuery UI -->
	<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

	<!-- GITTER -->
	<script src="js/plugins/gritter/jquery.gritter.min.js"></script>

	<!-- EayPIE -->
	<script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>

	<!-- Sparkline -->
	<script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

	<!-- Sparkline demo data  -->
	<script src="js/demo/sparkline-demo.js"></script>
	
	<script src="js/plugins/layer/layer.min.js"></script>





</body>

</html>
