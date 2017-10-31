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

<style type="text/css">
        .row-pd{
            padding:10px 0;
        }

        /*搜索*/
        ._span{
            line-height: 34px;
            text-align: right;
            width: 80px;
            float:left;
            font-weight: bold;
            margin-right:8px;
        }
        .search_sub{
            position: absolute;
        }
        label{
            min-width: 100px;
            padding:0 5px !important;
            line-height: 34px;
        }
        /*搜索*/
    </style>

</head>

<body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
		<div id="side-menu">
			<jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true"/>
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
						<h2>附件下载日志</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a href="selAttri">系统日志</a></li>
							<li><strong>附件下载日志</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>






				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">
									
									
									<!-- <form class="form-horizontal">
										<div class="form-group">
											<label class="col-lg-1 control-label lable_a">用户ID：</label>
											<div class="col-lg-10">
												<div class="row">
													<div class="col-md-2">
														<input type="text" id="useridstr" placeholder="" class="form-control">
													</div>
													<span class="_span">浏览时间：</span>
													<div class="col-md-2">
														<input type="text" id="logintimestr" placeholder="yyyy-mm-dd" class="form-control">
													</div>
													<a id="querybutton" class="btn btn-primary search_sub">查询</a>
													
													<input type="submit" class="btn btn-primary search_sub"
														value="搜索">
												</div>
											</div>
										</div>

									</form> -->
									
									
									
									
									<table id="attritable" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>用户ID</th>
												<th>用户角色类型</th>
												<th>用户角色值</th>
												<th>附件ID</th>
												<th>附件名称</th>
												<th>增加时间</th>
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
			onkey(8);
			filltable();

			});
			
			function filltable(){
				
				var listdate =${filedownloglist };
				var tablehtml="";
				$.each(listdate,function(i,item){
					var no =Number(i+1);
					var id =item.id;
					var user_id =item.user_id;
					var user_role_type =item.user_role_type;
					var user_role_value =item.user_role_value;
					var attach_id =item.attach_id;
					var file_name =item.file_name;
					var add_time=item.add_time;
					
					var row ="<tr>"+
							 "<td>"+id+"</td>"+
							 "<td>"+user_id+"</td>"+
							 "<td>"+user_role_type+"</td>"+
							 "<td>"+user_role_value+"</td>"+
							 "<td>"+attach_id+"</td>"+
							 "<td>"+file_name+"</td>"+
							 "<td>"+add_time+"</td>"+
							 "</tr>";
					
					
						
						tablehtml+=row;
						
				});
				$("#tablebody").html(tablehtml);
			};
	
			
		
			function querylog(){
				
				
				$.ajax({
					
					type : "GET",
					url : "queryLoginLogById",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : {user_id: $("#useridstr").val()  },
					success : function(data) {
						
						if(data.status==0){
						
						var listdate =data.result;
						var tablehtml="";
						$.each(listdate,function(i,item){
							var id =item.id;
							var user_id =item.user_id;
							var remark =item.remark;
							var login_time =item.login_time;
							var login_ip =item.login_ip;
							var login_from =item.login_from;
							
							var row ="<tr>"+
									 "<td>"+id+"</td>"+
									 "<td>"+user_id+"</td>"+
									 "<td>"+remark+"</td>"+
									 "<td>"+login_time+"</td>"+
									 "<td>"+login_ip+"</td>"+
									 "<td>"+login_from+"</td>"+
									 "</tr>";
							
							
								tablehtml+=row;
								
						});
						$("#tablebody").html(tablehtml);
						
						
						}else{
							alert("失败");
						}
					
						
					},
					
					
					
				});
				
				
			}
		
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
