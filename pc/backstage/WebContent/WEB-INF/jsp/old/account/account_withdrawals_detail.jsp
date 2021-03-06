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
			<jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
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
								
						<jsp:include page="/WEB-INF/jsp/banner.html" flush="true"/>
		
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
						<h2>账户详细信息</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>账户列表管理</a></li>
							<li><strong>账户详细信息</strong></li>
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
											<label class="control-label">用户ID：</label>
										</div>
										<div class="col-lg-10">
											<label id="user_id"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">角色类别：</label>
										</div>
										<div class="col-lg-10">
											<label id="role_type"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">用户角色值：</label>
										</div>
										<div class="col-lg-10">
											<label id="role_value"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">余额：</label>
										</div>
										<div class="col-lg-10">
											<label id="amount"></label>
										</div>
									</div>
									
									
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">积分：</label>
										</div>
										<div class="col-lg-10">
											<label id="point"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">信誉值：</label>
										</div>
										<div class="col-lg-10">
											<label id="reputation"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">信用：</label>
										</div>
										<div class="col-lg-10">
											<label id="credit"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">代金券：</label>
										</div>
										<div class="col-lg-10">
											<label id="voucher"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">开启极速付：</label>
										</div>
										<div class="col-lg-10">
											<label id="is_fastpay"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">账户状态：</label>
										</div>
										<div class="col-lg-10">
											<label id="state"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">状态描述：</label>
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
											<label class="control-label">待结算余额：</label>
										</div>
										<div class="col-lg-10">
											<label id="float_amount"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">待结算代金券：</label>
										</div>
										<div class="col-lg-10">
											<label id="float_voucher"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">待结算积分：</label>
										</div>
										<div class="col-lg-10">
											<label id="float_point"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">可提现余额：</label>
										</div>
										<div class="col-lg-10">
											<label id="allow_amount"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">可提现代金券：</label>
										</div>
										<div class="col-lg-10">
											<label id="allow_voucher"></label>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-2">
											<label class="control-label">手续费规则：</label>
										</div>
										<div class="col-lg-10">
											<label id="withdrawals_commission"></label>
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
		onkey(5);
		setinfo();
	});
	
	
	function setinfo(){
		
		var withdrawalinfo=${withdrawalsinfo };
		
		var id=withdrawalinfo.id;
		var user_id =withdrawalinfo.user_id;
		var role_type =withdrawalinfo.user_role_type;
		var role_value =withdrawalinfo.user_role_value;
		var account_id =withdrawalinfo.account_id;
		var withdrawals_no =withdrawalinfo.withdrawals_no;
		var bank_name =withdrawalinfo.bank_name;
		var bank_card =withdrawalinfo.bank_card;
		var bank_area =withdrawalinfo.bank_area;
		var bank_address =withdrawalinfo.bank_address;
		var amount =withdrawalinfo.amount;
		var status =withdrawalinfo.status;
		var add_time =withdrawalinfo.add_time;
		var complete_time =withdrawalinfo.complete_time;
		var transaction_no =withdrawalinfo.transaction_no;
		var remark =withdrawalinfo.remark;
		var is_del =withdrawalinfo.is_del;
		var commission =withdrawalinfo.commission;
		var bank_user =withdrawalinfo.bank_user;
		var voucher =withdrawalinfo.voucher;
		var total_money =withdrawalinfo.total_money;
		
		var statustr ="";
		
		switch(status){
		case 0:
			statusstr="待审核";
			break;
		case 1:
			statusstr="用户撤销";
			break;
		case 2:
			statusstr="回退";
			break;
		case 3:
			statusstr="冻结";
			break;
		case 4:
			statusstr="审核通过";
			break;
		case 5:
			statusstr="提现成功";
			break;
		}
		var is_fastpaystr="";
		switch(is_fastpay){
		case 0:
			is_fastpaystr="未开启";
			break;
		case 1:
			is_fastpaystr="已开启";
			break;
		}
		
		$("#user_id").html(user_id);
		$("#role_type").html(role_type);
		$("#role_value").html(role_value);
		$("#account_id").html(account_id);
		$("#withdrawals_no").html(withdrawals_no);
		$("#bank_name").html(bank_name);
		$("#bank_card").html(bank_card);
		$("#bank_area").html(bank_area);
		$("#bank_address").html(bank_address);
		$("#amount").html(amount);
		$("#status").html(statusstr);
		$("#add_time").html(add_time);
		$("#complete_time").html(complete_time);
		$("#transaction_no").html(transaction_no);
		$("#remark").html(remark);
		$("#is_del").html(is_delstr);
		$("#commission").html(commission);
		$("#bank_user").html(bank_user);
		$("#voucher").html(voucher);
		$("#total_money").html(total_money);
		
		
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
