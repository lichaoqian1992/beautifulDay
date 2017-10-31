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
							订单在线支付信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单关联信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selOrderOnlinePay">订单在线支付信息</a></li>
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
									<span class="hidden-480">修改订单在线支付信息</span>
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
													<label class="control-label">付款用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">付款用户角色：</label>
													<div class="controls">
														<select id="user_role_type" class="m-wrap small">
															<option value="Buyer">用户</option>
															<option value="Shop">商家</option>
															<option value="Agent">代理商</option>
															<option value="Manager">管理员</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">付款用户角色值：</label>
													<div class="controls">
														<input id="user_role_value" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单类型：</label>
													<div class="controls">
														<input id="order_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单ID列表：</label>
													<div class="controls">
														<input id="order_id_list" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">使用代金券数量：</label>
													<div class="controls">
														<input id="use_voucher" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付方式名称：</label>
													<div class="controls">
														<input id="payment_name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付类型表ID：</label>
													<div class="controls">
														<input id="payment_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">添加时间：</label>
													<div class="controls">
														<input id="add_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付回调时间：</label>
													<div class="controls">
														<input id="notify_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付成功的单号：</label>
													<div class="controls">
														<input id="transaction_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付成功的金额：</label>
													<div class="controls">
														<input id="transaction_money" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付用户标识：</label>
													<div class="controls">
														<input id="transaction_identity" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付回调数据：</label>
													<div class="controls">
														<input id="transaction_txt" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付状态：</label>
													<div class="controls">
														<select id="status">
														    <option value="0">未支付</option>
														    <option value="1">已支付</option>
														    <option value="2">申请退款中</option>
														    <option value="3">退款成功</option>
														    <option value="4">退款失败</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select id="is_del" class="m-wrap small">
															<option value="0">否</option>
															<option value="1">是</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付成功同步回调地址：</label>
													<div class="controls">
														<input id="return_url" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付类型：</label>
													<div class="controls">
														<select id="payment_type" class="m-wrap small">
															<option value="0">PC</option>
															<option value="1">移动</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">期望支付的金额：</label>
													<div class="controls">
														<input id="payment_money" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付成功异步通知地址：</label>
													<div class="controls">
														<input id="notify_url" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">合作通道：</label>
													<div class="controls">
														<input id="partner_channel" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">备注：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selOrderOnlinePay">返回</a>

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
		    menuact("09_03_04");
		});
	
	function infoset(){
		
		var orderonlinepayinfo=${orderonlinepayinfo };
		console.log(orderonlinepayinfo);
		  var id=orderonlinepayinfo.id;
      var user_id=orderonlinepayinfo.user_id;
      var user_role_type=orderonlinepayinfo.user_role_type;
      var user_role_value=orderonlinepayinfo.user_role_value;
      var order_type=orderonlinepayinfo.order_type;
      var order_id_list=orderonlinepayinfo.order_id_list;
      var use_voucher=orderonlinepayinfo.use_voucher;
      var payment_name=orderonlinepayinfo.payment_name;
      var payment_id=orderonlinepayinfo.payment_id;
      var add_time=orderonlinepayinfo.add_time;
      var notify_time=orderonlinepayinfo.notify_time;
      var transaction_no=orderonlinepayinfo.transaction_no;
      var transaction_money=orderonlinepayinfo.transaction_money;
      var transaction_identity=orderonlinepayinfo.transaction_identity;
      var transaction_txt=orderonlinepayinfo.transaction_txt;
      var status=orderonlinepayinfo.status;
      var is_del=orderonlinepayinfo.is_del;
      var return_url=orderonlinepayinfo.return_url;
      var payment_type=orderonlinepayinfo.payment_type;
      var payment_money=orderonlinepayinfo.payment_money;
      var notify_url=orderonlinepayinfo.notify_url;
      var partner_channel=orderonlinepayinfo.partner_channel;
      var remark=orderonlinepayinfo.remark;

		
		$("#hiddenid").val(id);
		$("#user_id").val(user_id);
		$("#user_role_type").val(user_role_type);
		$("#user_role_value").val(user_role_value);
		$("#order_type").val(order_type);
		$("#order_id_list").val(order_id_list);
		$("#use_voucher").val(use_voucher);
		$("#payment_name").val(payment_name);
		$("#payment_id").val(payment_id);
		$("#add_time").val(add_time);
		$("#notify_time").val(notify_time);
		$("#transaction_no").val(transaction_no);
		$("#transaction_money").val(transaction_money);
		$("#transaction_identity").val(transaction_identity);
		$("#transaction_txt").val(transaction_txt);
		$("#status").val(status);
		$("#is_del").val(is_del);
		$("#return_url").val(return_url);
		$("#payment_type").val(payment_type);
		$("#payment_money").val(payment_money);
		$("#notify_url").val(notify_url);
		$("#partner_channel").val(partner_channel);
		$("#remark").val(remark);
		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updOrderOnlinePay",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			user_id:$("#user_id").val(),
			user_role_type:$("#user_role_type").val(),
			user_role_value:$("#user_role_value").val(),
			order_type:$("#order_type").val(),
			order_id_list:$("#order_id_list").val(),
			use_voucher:$("#use_voucher").val(),
			payment_name:$("#payment_name").val(),
			payment_id:$("#payment_id").val(),
			add_time:$("#add_time").val(),
			notify_time:$("#notify_time").val(),
			transaction_no:$("#transaction_no").val(),
			transaction_money:$("#transaction_money").val(),
			transaction_identity:$("#transaction_identity").val(),
			transaction_txt:$("#transaction_txt").val(),
			status:$("#status").val(),
			is_del:$("#is_del").val(),
			return_url:$("#return_url").val(),
			payment_type:$("#payment_type").val(),
			payment_money:$("#payment_money").val(),
			notify_url:$("#notify_url").val(),
			partner_channel:$("#partner_channel").val(),
			remark:$("#remark").val()
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