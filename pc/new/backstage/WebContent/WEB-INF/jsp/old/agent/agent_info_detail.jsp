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
								
						<jsp:include page="/WEB-INF/jsp/banner.jsp" flush="true"/>
		
							</div>
						</div>
						
						<ul class="nav navbar-top-links navbar-right">
							<li><span>${sessionScope.username}</span></li>
							<li><a href="loginout"> <i class="fa fa-sign-out"></i>
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
						<h2>代理商详细信息</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>代理商管理</a></li>
							<li><strong>代理商详细信息</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>



				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">
							<div  class="form-horizontal">

									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">代理商组别：</label>
										</div>
										<div class="col-lg-10">
											<label id="group_id"></label>
										</div>
									</div>									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">用户ID：</label>
										</div>
										<div class="col-lg-10">
											<label id="user_id"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">代理区域：</label>
										</div>
										<div class="col-lg-10">
											<label id="agentarea"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">代理开始时间：</label>
										</div>
										<div class="col-lg-10">
											<label id="begin_time"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">代理结束时间：</label>
										</div>
										<div class="col-lg-10">
											<label id="end_time"></label>
										</div>
									</div>
									
									
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">负责人：</label>
										</div>
										<div class="col-lg-10">
											<label id="name"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">联系电话：</label>
										</div>
										<div class="col-lg-10">
											<label id="telephone"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">手机号码：</label>
										</div>
										<div class="col-lg-10">
											<label id="mobile"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">QQ：</label>
										</div>
										<div class="col-lg-10">
											<label id="qq"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">邮箱：</label>
										</div>
										<div class="col-lg-10">
											<label id="email"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">详细地址：</label>
										</div>
										<div class="col-lg-10">
											<label id="address"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">代理状态：</label>
										</div>
										<div class="col-lg-10">
											<label id="state"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">状态更新内容：</label>
										</div>
										<div class="col-lg-10">
											<label id="remark"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">状态更新时间：</label>
										</div>
										<div class="col-lg-10">
											<label id="update_time"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">添加时间：</label>
										</div>
										<div class="col-lg-10">
											<label id="add_time"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">删除状态：</label>
										</div>
										<div class="col-lg-10">
											<label id="is_del"></label>
										</div>
									</div>

</div>

								</div>
							</div>
						</div>
					</div>

				</div>

				





			</div>

		</div>
	</div>

	<script>
	$(document).ready(function(){
		onkey(4);
		setinfo();
	});
	
	
	function setinfo(){
		
		var agentinfo=${agentinfo };
		
		var id=agentinfo.id;
		var group_id=agentinfo.group_id;
		var user_id =agentinfo.user_id;
		var agent_area =agentinfo.agent_area;
		var address =agentinfo.address;
		var telephone =agentinfo.telephone;
		var qq =agentinfo.qq;
		var name =agentinfo.name;
		var mobile =agentinfo.mobile;
		var email =agentinfo.email;
		var begin_time =agentinfo.begin_time;
		var end_time =agentinfo.end_time;
		var state =agentinfo.state;
		var remark =agentinfo.remark;
		var update_time =agentinfo.update_time;
		var add_time =agentinfo.add_time;
		var is_del =agentinfo.is_del;
		
		$("#group_id").html(group_id);
		$("#user_id").html(user_id);
		$("#agent_area").html(agent_area);
		$("#address").html(address);
		$("#telephone").html(telephone);
		$("#qq").html(qq);
		$("#name").html(name);
		$("#mobile").html(mobile);
		$("#email").html(email);
		$("#begin_time").html(begin_time);
		$("#end_time").html(end_time);
		$("#state").html(state);
		$("#remark").html(remark);
		$("#update_time").html(update_time);
		$("#add_time").html(add_time);
		$("#is_del").html(is_del);
		
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
