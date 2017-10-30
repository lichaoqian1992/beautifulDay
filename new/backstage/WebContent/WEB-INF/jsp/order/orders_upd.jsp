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
							订单信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selOrders">订单信息</a></li>
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
									<span class="hidden-480">修改订单信息</span>
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
													<label class="control-label">订单标题：</label>
													<div class="controls">
														<input id="order_title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">订单号：</label>
													<div class="controls">
														<input id="order_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单类型：</label>
													<div class="controls">
														<input id="order_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商家所属用户：</label>
													<div class="controls">
														<input id="shop_user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商家用户角色：</label>
													<div class="controls">
														<select id="shop_user_role_type" class="m-wrap small">
															<option value="Buyer">用户</option>
															<option value="Shop">商家</option>
															<option value="Agent">代理商</option>
															<option value="Manager">管理员</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商家用户角色值：</label>
													<div class="controls">
														<input id="shop_user_role_value" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">购买用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">购买用户角色：</label>
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
													<label class="control-label">购买用户角色值：</label>
													<div class="controls">
														<input id="user_role_value" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">物流方式：</label>
													<div class="controls">
														<input id="express_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">物流费用：</label>
													<div class="controls">
														<input id="express_fee" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单留言：</label>
													<div class="controls">
														<input id="message" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单备注：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否索要发票：</label>
													<div class="controls">
														<input id="is_invoice" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">发票抬头：</label>
													<div class="controls">
														<input id="invoice_title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">应付总金额：</label>
													<div class="controls">
														<input id="payable_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">预付金额：</label>
													<div class="controls">
														<input id="pre_payable_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">实付总金额：</label>
													<div class="controls">
														<input id="real_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单总金额：</label>
													<div class="controls">
														<input id="order_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单代金券：</label>
													<div class="controls">
														<input id="voucher" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单总积分：</label>
													<div class="controls">
														<input id="point" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单状态：</label>
													<div class="controls">
														<select id="status">
														    <option value="1">生成订单</option>
														    <option value="2">确认订单</option>
														    <option value="3">完成订单</option>
														    <option value="4">取消订单</option>
														    <option value="5">锁定订单</option>
														    <option value="5">卖家取消订单</option>
														    <option value="5">投诉冻结中</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">下单时间：</label>
													<div class="controls">
														<input id="add_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">确认时间：</label>
													<div class="controls">
														<input id="confirm_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">失效时间：</label>
													<div class="controls">
														<input id="invalid_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">完成时间：</label>
													<div class="controls">
														<input id="complete_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付方式：</label>
													<div class="controls">
														<input id="payment_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付手续费：</label>
													<div class="controls">
														<input id="payment_fee" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付状态：</label>
													<div class="controls">
														  <select id="payment_status">
															<option value="1">未支付</option>
															<option value="2">已支付</option>
															<option value="3">已预付</option>
															<option value="4">线下支付</option>
															<option value="5">免单</option>
															<option value="6">仅预付</option>
														  </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付时间：</label>
													<div class="controls">
														<input id="payment_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">结算状态：</label>
													<div class="controls">
														<select id="settlement_status">
                                  <option value="1">已结算</option>
                                  <option value="0">未结算</option>
                            </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">结算时间：</label>
													<div class="controls">
														<input id="settlement_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否需要回调：</label>
													<div class="controls">
														  <select id="is_callback">
                                  <option value="1">需要</option>
                                  <option value="0">不需要</option>
                              </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">回调地址：</label>
													<div class="controls">
														<input id="back_url" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">通知地址：</label>
													<div class="controls">
														<input id="notice_url" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">回调通知状态：</label>
													<div class="controls">
														<select id="back_status">
                                 <option value="1">已成功</option>
                                 <option value="2">已失败</option>
                                 <option value="0">等待通知</option>
                            </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">成功失败时间：</label>
													<div class="controls">
														<input id="back_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select id="is_del">
                                 <option value="0">正常</option>
                                 <option value="1">删除</option>
                             </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">退订状态：</label>
													<div class="controls">
														<select id="book_back_status">
                                  <option value="1">未申请</option>
                                  <option value="2">待确认</option>
                                  <option value="3">卖家同意</option>
                                  <option value="4">卖家不同意</option>
                                  <option value="5">全部退单</option>
                                  <option value="6">部分退单</option>
                              </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商家活动id：</label>
													<div class="controls">
														<input id="activity_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">处理次数：</label>
													<div class="controls">
														<input id="exec_num" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否异常：</label>
													<div class="controls">
														<select id="is_normal">
                                 <option value="1">正常</option>
                                 <option value="2">异常</option>
                             </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商家拒绝接单原因：</label>
													<div class="controls">
														<input id="reject_remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">取消退单原因：</label>
													<div class="controls">
														<input id="cancelremark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">实际付款修改之前价格：</label>
													<div class="controls">
														<input id="old_real_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">修改之前价格原因：</label>
													<div class="controls">
														<input id="cancel_amount_remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="form-actions">
													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selOrders">返回</a>

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
		    menuact("09_01_01");
		});
	
	function infoset(){
		
		var ordersinfo=${ordersinfo };
		
		var id=ordersinfo.id ;
    var order_title=ordersinfo.order_title ;
    var order_no=ordersinfo.order_no ;
    var order_type=ordersinfo.order_type ;
    var shop_user_id=ordersinfo.shop_user_id ;
    var shop_user_role_type=ordersinfo.shop_user_role_type ;
    var shop_user_role_value=ordersinfo.shop_user_role_value ;
    var user_id=ordersinfo.user_id ;
    var user_role_type=ordersinfo.user_role_type ;
    var user_role_value=ordersinfo.user_role_value ;
    var express_type=ordersinfo.express_type ;
    var express_fee=ordersinfo.express_fee ;
    var message=ordersinfo.message ;
    var remark=ordersinfo.remark ;
    var is_invoice=ordersinfo.is_invoice ;
    var invoice_title=ordersinfo.invoice_title ;
    var payable_amount=ordersinfo.payable_amount ;
    var pre_payable_amount=ordersinfo.pre_payable_amount ;
    var real_amount=ordersinfo.real_amount ;
    var order_amount=ordersinfo.order_amount ;
    var voucher=ordersinfo.voucher ;
    var point=ordersinfo.point ;
    var status=ordersinfo.status ;
    var add_time=ordersinfo.add_time ;
    var confirm_time=ordersinfo.confirm_time ;
    var invalid_time=ordersinfo.invalid_time ;
    var complete_time=ordersinfo.complete_time ;
    var payment_id=ordersinfo.payment_id ;
    var payment_fee=ordersinfo.payment_fee ;
    var payment_status=ordersinfo.payment_status ;
    var payment_time=ordersinfo.payment_time ;
    var settlement_status=ordersinfo.settlement_status ;
    var settlement_time=ordersinfo.settlement_time ;
    var is_callback=ordersinfo.is_callback ;
    var back_url=ordersinfo.back_url ;
    var notice_url=ordersinfo.notice_url ;
    var back_status=ordersinfo.back_status ;
    var back_time=ordersinfo.back_time ;
    var is_del=ordersinfo.is_del ;
    var book_back_status=ordersinfo.book_back_status ;
    var activity_id=ordersinfo.activity_id ;
    var exec_num=ordersinfo.exec_num ;
    var is_normal=ordersinfo.is_normal ;
    var reject_remark=ordersinfo.reject_remark ;
    var cancelremark=ordersinfo.cancelremark ;
    var old_real_amount=ordersinfo.old_real_amount ;
    var cancel_amount_remark=ordersinfo.cancel_amount_remark ;

		
		$("#hiddenid").val(id);
		$("#order_title").val(order_title);
		$("#order_no").val(order_no);
		$("#order_type").val(order_type);
		$("#shop_user_id").val(shop_user_id);
		$("#shop_user_role_type").val(shop_user_role_type);
		$("#shop_user_role_value").val(shop_user_role_value);
		$("#user_id").val(user_id);
		$("#user_role_type").val(user_role_type);
		$("#user_role_value").val(user_role_value);
		$("#express_type").val(express_type);
		$("#express_fee").val(express_fee);
		$("#message").val(message);
		$("#remark").val(remark);
		$("#is_invoice").val(is_invoice);
		$("#invoice_title").val(invoice_title);
		$("#payable_amount").val(payable_amount);
		$("#pre_payable_amount").val(pre_payable_amount);
		$("#real_amount").val(real_amount);
		$("#order_amount").val(order_amount);
		$("#voucher").val(voucher);
		$("#point").val(point);
		$("#status").val(status);
		$("#add_time").val(add_time);
		$("#confirm_time").val(confirm_time);
		$("#invalid_time").val(invalid_time);
		$("#complete_time").val(complete_time);
		$("#payment_id").val(payment_id);
		$("#payment_fee").val(payment_fee);
		$("#payment_status").val(payment_status);
		$("#payment_time").val(payment_time);
		$("#settlement_status").val(settlement_status);
		$("#settlement_time").val(settlement_time);
		$("#is_callback").val(is_callback);
		$("#back_url").val(back_url);
		$("#notice_url").val(notice_url);
		$("#back_status").val(back_status);
		$("#back_time").val(back_time);
		$("#is_del").val(is_del);
		$("#book_back_status").val(book_back_status);
		$("#activity_id").val(activity_id);
		$("#exec_num").val(exec_num);
		$("#is_normal").val(is_normal);
		$("#reject_remark").val(reject_remark);
		$("#cancelremark").val(cancelremark);
		$("#old_real_amount").val(old_real_amount);
		$("#cancel_amount_remark").val(cancel_amount_remark);
		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updOrders",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			order_title:$("#order_title").val(),
			order_no:$("#order_no").val(),
			order_type:$("#order_type").val(),
			shop_user_id:$("#shop_user_id").val(),
			shop_user_role_type:$("#shop_user_role_type").val(),
			shop_user_role_value:$("#shop_user_role_value").val(),
			user_id:$("#user_id").val(),
			user_role_type:$("#user_role_type").val(),
			user_role_value:$("#user_role_value").val(),
			express_type:$("#express_type").val(),
			express_fee:$("#express_fee").val(),
			message:$("#message").val(),
			remark:$("#remark").val(),
			is_invoice:$("#is_invoice").val(),
			invoice_title:$("#invoice_title").val(),
			payable_amount:$("#payable_amount").val(),
			pre_payable_amount:$("#pre_payable_amount").val(),
			real_amount:$("#real_amount").val(),
			order_amount:$("#order_amount").val(),
			voucher:$("#voucher").val(),
			point:$("#point").val(),
			status:$("#status").val(),
			add_time:$("#add_time").val(),
			confirm_time:$("#confirm_time").val(),
			invalid_time:$("#invalid_time").val(),
			complete_time:$("#complete_time").val(),
			payment_id:$("#payment_id").val(),
			payment_fee:$("#payment_fee").val(),
			payment_status:$("#payment_status").val(),
			payment_time:$("#payment_time").val(),
			settlement_status:$("#settlement_status").val(),
			settlement_time:$("#settlement_time").val(),
			is_callback:$("#is_callback").val(),
			back_url:$("#back_url").val(),
			notice_url:$("#notice_url").val(),
			back_status:$("#back_status").val(),
			back_time:$("#back_time").val(),
			is_del:$("#is_del").val(),
			book_back_status:$("#book_back_status").val(),
			activity_id:$("#activity_id").val(),
			exec_num:$("#exec_num").val(),
			is_normal:$("#is_normal").val(),
			reject_remark:$("#reject_remark").val(),
			cancelremark:$("#cancelremark").val(),
			old_real_amount:$("#old_real_amount").val(),
			cancel_amount_remark:$("#cancel_amount_remark").val()
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