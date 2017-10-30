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
							账户详细信息 
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户详细信息</a></li>
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
									<span class="hidden-480">账户信息</span>
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
														<input id="user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">角色类别：</label>
													<div class="controls">
														<select id="role_type" class="small m-wrap" >
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
														<input id="role_value" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">余额：</label>
													<div class="controls">
														<input id="amount" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">积分：</label>
													<div class="controls">
														<input id="point" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">信誉值：</label>
													<div class="controls">
														<input id="reputation" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">信用：</label>
													<div class="controls">
														<input id="credit" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">代金券：</label>
													<div class="controls">
														<input id="voucher" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">开启极速付：</label>
													<div class="controls">
														<select id="is_fastpay" class="small m-wrap" >
															<option value="0">未开启</option>
															<option value="1">开启</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">账户状态：</label>
													<div class="controls">
														<select id="state" class="small m-wrap" >
															<option value="0">冻结</option>
															<option value="1">正常</option>
															<option value="9">异常</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">状态描述：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
													
												</div>
												
												<div class="control-group">
													<label class="control-label">状态更新时间：</label>
													<div class="controls">
														<input id="update_time" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">待结算余额：</label>
													<div class="controls">
														<input id="float_amount" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">待结算代金券：</label>
													<div class="controls">
														<input id="float_voucher" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">待结算积分：</label>
													<div class="controls">
														<input id="float_point" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">可提现余额：</label>
													<div class="controls">
														<input id="allow_amount" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">可提现代金券：</label>
													<div class="controls">
														<input id="allow_voucher" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">手续费规则：</label>
													<div class="controls">
														<input id="withdrawals_commission" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												

												

												



												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selAccount">返回</a>

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
		    menuact("08_01_01");
		});
	
	function infoset(){
		
		var accountinfo=${accountinfo };
		
		var id=accountinfo.id;
		var user_id =accountinfo.user_id;
		var role_type =accountinfo.role_type;
		var role_value =accountinfo.role_value;
		var point =accountinfo.point;
		var amount =accountinfo.amount;
		var reputation =accountinfo.reputation;
		var credit =accountinfo.credit;
		var voucher =accountinfo.voucher;
		var is_fastpay =accountinfo.is_fastpay;
		var state =accountinfo.state;
		var remark =accountinfo.remark;
		var update_time =accountinfo.update_time;
		var float_amount =accountinfo.float_amount;
		var float_voucher =accountinfo.float_voucher;
		var float_point =accountinfo.float_point;
		var allow_amount =accountinfo.allow_amount;
		var allow_voucher =accountinfo.allow_voucher;
		var withdrawals_commission =accountinfo.withdrawals_commission;
		
		$("#hiddenid").val(id);
		$("#user_id").val(user_id);
		$("#role_type").val(role_type);
		$("#role_value").val(role_value);
		$("#amount").val(amount);
		$("#point").val(point);
		$("#reputation").val(reputation);
		$("#credit").val(credit);
		$("#voucher").val(voucher);
 		$("#is_fastpay").val(is_fastpay);
		$("#state").val(state);
		$("#remark").val(remark);
		$("#update_time").val(update_time);
		$("#float_amount").val(float_amount);
		$("#float_voucher").val(float_voucher);
		$("#float_point").val(float_point);
		$("#allow_amount").val(allow_amount);
		$("#allow_voucher").val(allow_voucher);
		$("#withdrawals_commission").val(withdrawals_commission);
		
		
		
	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updAccount",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			user_id:$("#user_id").val(),
			role_type:$("#role_type").val(),
			role_value:$("#role_value").val(),
			amount:$("#amount").val(),
			point:$("#point").val(),
			reputation:$("#reputation").val(),
			credit:$("#credit").val(),
			voucher:$("#voucher").val(),
			is_fastpay:$("#is_fastpay").val(),
			state:$("#state").val(),
			remark:$("#remark").val(),
			update_time:$("#update_time").val(),
			float_amount:$("#float_amount").val(),
			float_voucher:$("#float_voucher").val(),
			float_point:$("#float_point").val(),
			allow_amount:$("#allow_amount").val(),
			allow_voucher:$("#allow_voucher").val(),
			withdrawals_commission:$("#withdrawals_commission").val()
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