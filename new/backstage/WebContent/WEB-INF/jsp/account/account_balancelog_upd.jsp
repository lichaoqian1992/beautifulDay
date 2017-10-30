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
								账户结算主表	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户结算信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selUserBalanceLog">账户结算主表</a></li>
						</ul>
					</div>
				</div>

				<div class="row-fluid margin-bottom-20">

				</div>

				<div class="row-fluid">

					<div class="span12">

						<div class="portlet box blue tabbable">

							<div class="portlet-title">
								<div class="caption">
									<span class="hidden-480">修改账户结算主表</span>
								</div>
							</div>
							<div class="portlet-body form">




								<div class="tabbable portlet-tabs">
									<ul class="nav nav-tabs">
										<li><a href="#portlet_tab3" data-toggle="tab">&nbsp;</a></li>
									</ul>
									
									<div class="tab-content">
										<div class="tab-pane active" id="portlet_tab1">
											<!-- BEGIN FORM-->
											
											<form action="#" class="form-horizontal">
												<input type="hidden" id="hiddenid"value="">
												<div class="control-group">
													<label class="control-label">用户id：</label>
													<div class="controls">
														<input id="user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色：</label>
													<div class="controls">
														<select id="role_type" class="m-wrap small">
															<option value="Buyer">用户</option>
															<option value="Shop">商家</option>
															<option value="Agent">代理商</option>
															<option value="Manager">管理员</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色值：</label>
													<div class="controls">
														<input id="role_value" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单来源：</label>
													<div class="controls">
														<select id="order_platform" class="m-wrap small">
															<option value="1">满集网</option>
															<option value="2">生活服务类</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单类型：</label>
													<div class="controls">
														<input id="order_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单编号：</label>
													<div class="controls">
														<input id="order_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">记录标识：</label>
													<div class="controls">
														<input id="balance_hash" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单标题：</label>
													<div class="controls">
														<input id="order_title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">查看地址：</label>
													<div class="controls">
														<input id="order_url" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">结算金额：</label>
													<div class="controls">
														<input id="balance_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">结算代金券：</label>
													<div class="controls">
														<input id="balance_voucher" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">结算积分：</label>
													<div class="controls">
														<input id="balance_point" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">预期结算时间：</label>
													<div class="controls">
														<input id="will_balance_date" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">实际结算时间：</label>
													<div class="controls">
														<input id="real_balance_date" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">结算状态：</label>
													<div class="controls">
														<select id="balance_state" class="m-wrap small">
															<option value="1">待结算</option>
															<option value="2">已结算</option>
															<option value="4">已作废</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">记录写入时间：</label>
													<div class="controls">
														<input id="add_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select id="is_del" class="m-wrap small">
															<option value="0">未删除</option>
															<option value="1">已删除</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selUserBalanceLog">返回</a>

												</div>

											</form>

											<!-- END FORM-->

										</div>

										

										

									</div>

								</div>

							</div>

						</div>

						<!-- END SAMPLE FORM PORTLET-->

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

		    App.init(); 
		    infoset();
		    menuact("08_04_01");
		});
	
	function infoset(){
		
		var balancelog=${balancelog };
		console.log(balancelog)
		var id=balancelog.id;
		
		var	user_id=balancelog.user_id;
		var	role_type=balancelog.role_type;
		var	role_value=balancelog.role_value;
		var	order_platform=balancelog.order_platform;
		var	order_type=balancelog.order_type;
		var	order_no=balancelog.order_no;
		var	balance_hash=balancelog.balance_hash;
		var	order_title=balancelog.order_title;
		var	order_url=balancelog.order_url;
		var	balance_amount=balancelog.balance_amount;
		var	balance_voucher=balancelog.balance_voucher;
		var	balance_point=balancelog.balance_point;
		var	will_balance_date=balancelog.will_balance_date;
		var	real_balance_date=balancelog.real_balance_date;
		var	balance_state=balancelog.balance_state;
		var	add_time=balancelog.add_time;
		var	is_del=balancelog.is_del



	 	$("#hiddenid").val(id);
		$("#user_id").val(user_id);
		$("#role_type").val(role_type);
		$("#role_value").val(role_value);
		$("#order_platform").val(order_platform);
		$("#order_type").val(order_type);
		$("#order_no").val(order_no);
		$("#balance_hash").val(balance_hash);
		$("#order_title").val(order_title);
		$("#order_url").val(order_url);
		$("#balance_amount").val(balance_amount);
		$("#balance_voucher").val(balance_voucher);
		$("#balance_point").val(balance_point);
		$("#will_balance_date").val(will_balance_date);
		$("#real_balance_date").val(real_balance_date);
		$("#balance_state").val(balance_state);
		$("#add_time").val(add_time);
		$("#is_del").val(is_del);

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updUserBalanceLog",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			user_id:$("#user_id").val(),
			role_type:$("#role_type").val(),
			role_value:$("#role_value").val(),
			order_platform:$("#order_platform").val(),
			order_type:$("#order_type").val(),
			order_no:$("#order_no").val(),
			balance_hash:$("#balance_hash").val(),
			order_title:$("#order_title").val(),
			order_url:$("#order_url").val(),
			balance_amount:$("#balance_amount").val(),
			balance_voucher:$("#balance_voucher").val(),
			balance_point:$("#balance_point").val(),
			will_balance_date:$("#will_balance_date").val(),
			real_balance_date:$("#real_balance_date").val(),
			balance_state:$("#balance_state").val(),
			add_time:$("#add_time").val(),
			is_del:$("#is_del").val()

				},
		success : function(data) {
			if(data.status==0){
				location.reload();
			}else{
				alert("修改失败");
			}
		}
				
		})
	});
	
	

	</script>

</body>

</html>