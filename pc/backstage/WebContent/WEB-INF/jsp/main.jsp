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
						<jsp:include page="/WEB-INF/jsp/banner.html" flush="true"/>
					</div>
				</div>
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
				<img src="media/image/menu-toggler.png" alt="" />
				</a>
				<jsp:include page="/WEB-INF/jsp/userinfo.jsp" flush="true"/>      
				<%-- <ul class="nav pull-right">
	<li class="dropdown user"><a href="#" class="dropdown-toggle"
		data-toggle="dropdown"> <!-- <img alt=""
			src="media/image/avatar1_small.jpg" /> --> <span class="username">${sessionScope.username}</span> <i class="icon-angle-down"></i>
	</a>
		<ul class="dropdown-menu">
			<li class="divider"></li>
			<!-- <li><a href="lockOut"><i class="icon-lock"></i> Lock
					Screen</a></li> -->
			<li><a href="loginout"><i class="icon-key"></i> 退出登陆</a></li>
		</ul></li>
</ul>     --%>
			</div>
		</div>
	</div>



	<div class="page-container row-fluid" >
		<div class="page-sidebar nav-collapse collapse">
			<jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
		</div>

		<div class="page-content">


<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="toMain">Home</a> 
								<!-- <i class="icon-angle-right"></i> -->
							</li>
							<!-- <li>
								<a href="#">UI Features</a>
								<i class="icon-angle-right"></i>
							</li>
							<li><a href="#">Buttons</a></li> -->
						</ul>
		<div class="portlet-body">
								<div class="row-fluid">
									<a href="selUser" class="icon-btn span3">
										<i class="icon-wrench"></i>
										<div>运维</div>
										<!-- <span class="badge badge-important">2</span> -->
									</a>
									<a href="#" class="icon-btn span3">
										<i class="icon-group"></i>
										<div>用户</div>
										<!-- <span class="badge badge-success">4</span> -->
									</a>
									<a href="selShopGroup" class="icon-btn span3">
										<i class=" icon-shopping-cart"></i>
										<div>商家</div>
									</a>
									<a href="selAgentGroup" class="icon-btn span3">
										<i class="icon-sitemap"></i>
										<div>代理</div>
									</a>
								</div>
								<div class="row-fluid">
									<a href="selAccount" class="icon-btn span3">
										<i class=" icon-list-alt"></i>
										<div>账户</div>
										
									</a>
									<a href="selOrders" class="icon-btn span3">
										<i class=" icon-barcode"></i>
										<div>订单</div>
									</a>
									<a href="selNotice" class="icon-btn span3">
										<i class="icon-envelope"></i>
										<div>消息</div>
									</a>
									<a href="selLoginLog" class="icon-btn span3">
										<i class="icon-file"></i>
										<div>日志</div>
									</a>
								</div>
								<div class="row-fluid">
									<a href="selComplaint" class="icon-btn span3">
										<i class="icon-eye-open"><i></i></i>
										<div>监控</div>
									</a>
									<a href="selSysConfig" class="icon-btn span3">
										<i class="icon-cogs"></i>
										<div>配置</div>
									
									</a>
									
								</div>
							</div>
			

			<!-- END PAGE CONTAINER-->

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

		    App.init(); 
		    onkey(11);
		   
		});

	</script>

</body>

</html>