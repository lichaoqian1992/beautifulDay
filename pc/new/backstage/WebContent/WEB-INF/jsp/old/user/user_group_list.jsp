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
				<li class="nav-header"></li>

				<li class="active"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">用户组别管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li class="active"><a href="selUserGroup">用户组别列表</a></li>
						<li><a href="*">用户组别统计</a></li>
						<li><a href="*">用户组别调整</a></li>
						<li><a href="*">用户组别变化记录</a></li>
					</ul></li>

				<li><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">用户安全</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">用户验证记录</a></li>
						<li><a href="*">用户安全状态</a></li>

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


									<li class="dropdown"><a  class="dropdown-toggle" onclick="menu_side(0)"
										> 运维 </a></li>
									<li class="dropdown"><a  class="dropdown-toggle" onclick="menu_side(1)"
										> 用户 </a></li>
									<li class="dropdown"><a   class="dropdown-toggle" onclick="menu_side(2)"
										> 商家 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(3)"
										> 代理</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(4)"
										> 账户 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(5)"
										> 交易 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(6)"
										> 消息 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(7)"
										> 日志</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(8)"
										> 监控 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(9)"
										> 配置 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(10)"
										> 运维 </a></li>

								</ul>

		

							</div>
						</div>
						
						<script>
						
						function menu_side(index){
							
							alert(index);
							
							$("#side-menu").empty();
							
							
							
							  switch(index){
							  case 0:
								  alert(2);
								  
								  
								  var html='<ul class="nav" ><li class="nav-header"></li>				<li class="active"><a><i class="fa fa-th-large"></i> <span						class="nav-label">用户组别管理</span> <span class="fa arrow"></span></a>					<ul class="nav nav-second-level">						<li class="active"><a href="selUserGroup">用户组别列表</a></li>						<li><a href="*">用户组别统计</a></li>						<li><a href="*">用户组别调整</a></li>						<li><a href="*">用户组别变化记录</a></li>					</ul></li>				<li><a><i class="fa fa fa-globe"></i> <span						class="nav-label">用户安全</span><span class="fa arrow"></span></a>					<ul class="nav nav-second-level">						<li><a href="*">用户验证记录</a></li>					<li><a href="*">用户安全状态</a></li>					</ul></li></ul>';
								  
								  $("#side-menu").append(html);
								  
								  
								  break;
							  }
							  
							  
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
						<h2>用户组别列表</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>用户组别管理</a></li>
							<li><strong>用户组别列表</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>

				

				
				
				
				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-title">
									<h5>用户组别</h5>

								</div>
								<div class="ibox-content">



									<table class="table  table-bordered table-hover">
										<thead>
											<tr>
												<th>编号</th>
												<th>组别名称</th>
												<th>会员等级值</th>
												<th>升级经验值</th>
												<th>默认预存款</th>
												<th>默认积分</th>
												<th>购物折扣</th>
												<th>是否默认组</th>
												<th>是否自动升级</th>
												<th>是否禁用</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="table">
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>

				</div>
				
				
				<div id="layer_upd_form" class="ibox float-e-margins" style="display:none; ">
                            <div class="ibox-title" style="width:650px;">
                            	<h5>修改用户分组</h5>
                            </div>
                            <div class="ibox-content">
                            	
                            <form class="form-horizontal m-t" id="signupForm" novalidate="novalidate" >
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">组别名称：</label>
                                        <div class="col-sm-8">
                                            <input id="firstname" name="firstname" class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">会员等级值：</label>
                                        <div class="col-sm-8">
                                            <input id="lastname" name="lastname" class="form-control" type="text" aria-required="true" aria-invalid="false">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">升级经验值：</label>
                                        <div class="col-sm-8">
                                            <input id="username" name="username" class="form-control" type="text" aria-required="true" aria-invalid="true">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">默认预存款：</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="password" class="form-control" type="password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">默认积分：</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="password" class="form-control" type="password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">购物折扣：</label>
                                        <div class="col-sm-8">
                                            <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">是否默认组：</label>
                                        <div class="col-sm-8">
                                            <input id="email" name="email" class="form-control" type="email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">是否自动升级：</label>
                                        <div class="col-sm-8">
                                            <input type="checkbox" class="checkbox" id="agree" name="agree">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">是否禁用：</label>
                                        <div class="col-sm-8">
                                            <input type="checkbox" class="checkbox" id="agree" name="agree">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">提交</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            
                            
                        </div>
				
			
				
				

			</div>

		</div>
	</div>
	
	<script>
	
		$(document).ready(
				
				function() {

					var data = ${usergrouplist };

					$.each(data,
							function(i, groupitem) {
								var id =Number(i+1);
								var title = groupitem.title;
								var grade = groupitem.grade;
								var upgrade_exp = groupitem.upgrade_exp;
								var amount = groupitem.amount;
								var point = groupitem.point;
								var discount = groupitem.discount;
								var is_default = groupitem.is_default;
								var is_upgrade = groupitem.is_upgrade;
								var is_lock = groupitem.is_lock;
								

								$("#table").append(
										"<tr><td>" + id+ "</td><td>" + title
												+ "</td><td>" + grade
												+ "</td><td>" + upgrade_exp
												+ "</td><td>" + amount
												+ "</td><td>" + point
												+ "</td><td>" + discount
												+ "</td><td>" + is_default
												+ "</td><td>" + is_upgrade
												+ "</td><td>" + is_lock
												+ "</td><td>" + "<a onclick='upd(this)' data='"+groupitem+"'>修改</a><br><a onclick='del(this)' data='"+groupitem+"'>删除</a>"
												+ "</td></tr>");
							});
				})
		
		
	
			function upd(obj){
			
			alert(1);
			
			alert($(obj).attr("data"));
			
			$.layer({
                type: 1,
                shade: [0],
                area: ['auto', 'auto'],
                title: false,
                border: [0],
                page: {dom : '#layer_upd_form'},
                close: function(index){
                    layer.close(index);
                    $('#layer_upd_form').hide();
                }
            });
			
		}
		
		function del(obj){
			
			
			
			alert(2);
			alert($(obj).attr("data"));
			
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
