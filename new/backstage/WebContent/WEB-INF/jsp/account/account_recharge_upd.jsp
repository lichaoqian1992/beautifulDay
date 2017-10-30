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
							充值信息详情
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">充值信息详情</a></li>
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
									<span class="hidden-480">充值信息</span>
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
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="userid" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">用户角色类型：</label>
													<div class="controls">
														<select id="roletype" class="small m-wrap" >
															<option value="Buyer">买家</option>
															<option value="Shop">商家</option>
															<option value="Agent">代理商</option>
															<option value="Manager">管理员</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">用户角色值：</label>
													<div class="controls">
														<input id="rolevalue" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">充值到账户ID：</label>
													<div class="controls">
														<input id="accountid" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">充值订单号：</label>
													<div class="controls">
														<input id="rechargeno" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">支付方式：</label>
													<div class="controls">
														<select id="paymentid" class="small m-wrap" >
															<option value="0">未开启</option>
															<option value="1">开启</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">充值金额：</label>
													<div class="controls">
														<input id="amount" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">充值状态：</label>
													<div class="controls">
														<select id="status" class="small m-wrap" >
															<option value="0">未完成</option>
															<option value="1">已完成</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">生成时间：</label>
													<div class="controls">
														<input id="addtime" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">完成时间：</label>
													<div class="controls">
														<input id="completetime" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">三方支付交易号：</label>
													<div class="controls">
														<input id="transactionno" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
													
												</div>
												
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<input id="isdel" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">充值满意券：</label>
													<div class="controls">
														<input id="voucher" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selRecharge">返回</a>

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
		    menuact("08_02_01");
		    infoset();
		});
	
	function infoset(){
		
		var rechargeinfo=${rechargeinfo };
		
		var id=rechargeinfo.id;
		var user_id =rechargeinfo.user_id;
		var role_type =rechargeinfo.user_role_type;
		var role_value =rechargeinfo.user_role_value;
		var account_id =rechargeinfo.account_id;
		var recharge_no =rechargeinfo.recharge_no;
		var payment_id =rechargeinfo.payment_id;
		var amount =rechargeinfo.amount;
		var status =rechargeinfo.status;
		var add_time =rechargeinfo.add_time;
		var complete_time =rechargeinfo.complete_time;
		var transaction_no =rechargeinfo.transaction_no;
		var is_del =rechargeinfo.is_del;
		var voucher =rechargeinfo.voucher;
		
		
		$("#hiddenid").val(id);
		$("#userid").val(user_id);
		$("#roletype").val(role_type);
		$("#rolevalue").val(role_value);
		$("#accountid").val(account_id);
		$("#rechargeno").val(recharge_no);
		$("#paymentid").val(payment_id);
		$("#amount").val(amount);
		$("#status").val(status);
 		$("#addtime").val(add_time);
		$("#completetime").val(complete_time);
		$("#transactionno").val(transaction_no);
		$("#isdel").val(is_del);
		$("#voucher").val(voucher);
		
	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updRecharge",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			user_id:$("#userid").val(),
			user_role_type:$("#roletype").val(),
			user_role_value:$("#rolevalue").val(),
			account_id:$("#accountid").val(),
			recharge_no:$("#rechargeno").val(),
			payment_id:	$("#paymentid").val(),	
			amount:		$("#amount").val(),
			status:		$("#status").val(),
			add_time:$("#add_time").val(),
			complete_time:$("#completetime").val(),		
			transaction_no:$("#transactionno").val(),
			is_del:$("#isdel").val(),
			voucher:$("#voucher").val()
			
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