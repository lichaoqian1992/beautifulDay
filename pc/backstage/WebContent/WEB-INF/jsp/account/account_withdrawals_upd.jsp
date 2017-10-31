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
			</div>
		</div>
	</div>



	<div class="page-container row-fluid" >
		<div class="page-sidebar nav-collapse collapse">
			<jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
		</div>

		<div class="page-content">

			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							提现记录详情
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">提现记录详情</a></li>
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
									<span class="hidden-480">提现信息</span>
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
													<label class="control-label">提现账户ID：</label>
													<div class="controls">
														<input id="accountid" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">提现订单号：</label>
													<div class="controls">
														<input id="withdrawalsno" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">银行卡名称：</label>
													<div class="controls">
														<input id="bankname" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">银行卡卡号：</label>
													<div class="controls">
														<input id="bankcard" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="control-group">
													<label class="control-label">银行卡地区：</label>
													<div class="controls">
														<input id="bankarea" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												
												<div class="control-group">
													<label class="control-label">详细开户地址：</label>
													<div class="controls">
														<input id="bankaddress" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												

												
												<div class="control-group">
													<label class="control-label">提现金额：</label>
													<div class="controls">
														<input id="amount" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">提现状态：</label>
													<div class="controls">
														<select id="status" class="small m-wrap" >
														<option value="0">待审核</option>
														<option value="1">撤销</option>
														<option value="2">回退</option>
														<option value="3">冻结</option>
														<option value="4">审核通过</option>
														<option value="5">成功</option>
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
													<label class="control-label">转账说明：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
													
												</div>
												
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<input id="isdel" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">提现手续费：</label>
													<div class="controls">
														<input id="commission" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">开户姓名：</label>
													<div class="controls">
														<input id="bankuser" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">提现满意券：</label>
													<div class="controls">
														<input id="voucher" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">提现总金额：</label>
													<div class="controls">
														<input id="totalmoney" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selWithdrawals">返回</a>

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
		    onkey(5);
		    infoset();
		});
	
	function infoset(){
		
		var withdrawalsinfo=${withdrawalsinfo };
		
		
		
		var id =withdrawalsinfo.id;
		var user_id =withdrawalsinfo.user_id;
		var user_role_type =withdrawalsinfo.user_role_type;
		var user_role_value =withdrawalsinfo.user_role_value;
		var account_id =withdrawalsinfo.account_id;
		var withdrawals_no =withdrawalsinfo.withdrawals_no;
		var bank_name =withdrawalsinfo.bank_name;
		var bank_card =withdrawalsinfo.bank_card;
		var bank_area =withdrawalsinfo.bank_area;
		var bank_address =withdrawalsinfo.bank_address;
		var amount =withdrawalsinfo.amount;
		var status =withdrawalsinfo.status;
		var add_time =withdrawalsinfo.add_time;
		var complete_time =withdrawalsinfo.complete_time;
		var transaction_no =withdrawalsinfo.transaction_no;
		var remark =withdrawalsinfo.remark;
		var is_del =withdrawalsinfo.is_del;
		var commission =withdrawalsinfo.commission;
		var bank_user =withdrawalsinfo.bank_user;
		var voucher =withdrawalsinfo.voucher;
		var total_money =withdrawalsinfo.total_money;

		
		$("#hiddenid").val(id);
		$("#userid").val(user_id);
		$("#roletype").val(user_role_type);
		$("#rolevalue").val(user_role_value);
		
		$("#accountid").val(account_id);
		$("#withdrawalsno").val(withdrawals_no);
		$("#bankname").val(bank_name);
		$("#bankcard").val(bank_card);
		$("#bankarea").val(bank_area);
		$("#bankaddress").val(bank_address);
		$("#commission").val(commission);
		$("#bankuser").val(bank_user);
		$("#voucher").val(voucher);
		$("#totalmoney").val(total_money);
		
		$("#transactionno").val(transaction_no);
		$("#amount").val(amount);
		$("#status").val(status);
 		$("#addtime").val(add_time);
		$("#completetime").val(complete_time);
		$("#remark").val(remark);
		$("#isdel").val(is_del);
		
	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updWithdrawals",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			user_id:$("#userid").val(),
			user_role_type:$("#roletype").val(),
			user_role_value:$("#rolevalue").val(),
			account_id:$("#accountid").val(),
			withdrawals_no:$("#withdrawalsno").val(),
			bank_name:$("#bankname").val(),
			bank_card:$("#bankcard").val(),
			bank_area:$("#bankarea").val(),
			bank_address:$("#bankaddress").val(),
			amount:$("#amount").val(),
			status:$("#status").val(),
			add_time:$("#addtime").val(),
			complete_time:$("#completetime").val(),		
			transaction_no:$("#transactionno").val(),
			remark:$("#remark").val(),
			is_del:$("#isdel").val(),
			commission:$("#commission").val(),
			bank_user:$("#bank_user").val(),
			voucher:$("#voucher").val(),
			total_money:$("#total_money").val()
				},
		success : function(data) {
			if(data.status==0){
				location.reload();
			}else{
				alert(修改失败);
			}
		}
				
		})
	});
	
	

	</script>

</body>

</html>