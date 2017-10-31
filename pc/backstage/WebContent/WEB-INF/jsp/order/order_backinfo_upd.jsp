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
							订单详细退款/退货类信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单状态信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单详细退款/退货类信息</a></li>
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
									<span class="hidden-480">修改订单详细退款/退货类信息</span>
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
													<label class="control-label">订单号：</label>
													<div class="controls">
														<input id="order_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">对应订单ID：</label>
													<div class="controls">
														<input id="order_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">应退金额：</label>
													<div class="controls">
														<input id="back_goods_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">实退金额：</label>
													<div class="controls">
														<input id="real_back_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">实退积分：</label>
													<div class="controls">
														<input id="point" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">退款状态：</label>
													<div class="controls">
														<select id="status">
														    <option value="1">等待确认</option>
														    <option value="2">退款中</option>
														    <option value="3">退款完成</option>
														    <option value="4">退款失败</option>
														    <option value="5">不同意退款</option>
														    <option value="6">买家取消退款</option>
														    <option value="7">卖家同意退款</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">状态更新时间：</label>
													<div class="controls">
														<input id="update_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单代金券：</label>
													<div class="controls">
														<input id="voucher" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">退单来源：</label>
													<div class="controls">
														<select id="back_from">
                                <option value="1">全部退单</option>
                                <option value="2">不分退单</option>
                            </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">失效时间：</label>
													<div class="controls">
														<input id="invalid_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">处理次数：</label>
													<div class="controls">
														<input id="exec_num" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否退货：</label>
													<div class="controls">
														<select id="back_goods">
                                <option value="1">退款退货</option>
                                <option value="0">仅退款</option>
                            </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">退款原因：</label>
													<div class="controls">
														<input id="back_category" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">退款备注：</label>
													<div class="controls">
														<input id="back_remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">退款图片：</label>
													<div class="controls">
														<input id="back_resource" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">拒绝退款原因：</label>
													<div class="controls">
														<input id="reject_remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">创建日期：</label>
													<div class="controls">
														<input id="add_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">物流退单号：</label>
													<div class="controls">
														<input id="back_return_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">卖家同意退款时间：</label>
													<div class="controls">
														<input id="agree_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selOrderBackInfo">返回</a>

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
		    onkey(6);
		    infoset();
		    setactive("633");
		});
	
	function infoset(){
		
		var orderbackinfo=${orderbackinfo };
		
		var id=orderbackinfo.id;
		var order_no=orderbackinfo.order_no;
		var order_id=orderbackinfo.order_id;
		var back_goods_amount=orderbackinfo.back_goods_amount;
		var real_back_amount=orderbackinfo.real_back_amount;
		var point=orderbackinfo.point;
		var status=orderbackinfo.status;
		var update_time=orderbackinfo.update_time;
		var voucher=orderbackinfo.voucher;
		var back_from=orderbackinfo.back_from;
		var invalid_time=orderbackinfo.invalid_time;
		var exec_num=orderbackinfo.exec_num;
		var back_goods=orderbackinfo.back_goods;
		var back_category=orderbackinfo.back_category;
		var back_remark=orderbackinfo.back_remark;
		var back_resource=orderbackinfo.back_resource;
		var reject_remark=orderbackinfo.reject_remark;
		var add_time=orderbackinfo.add_time;
		var back_return_no=orderbackinfo.back_return_no;
		var agree_time=orderbackinfo.agree_time;

		
		$("#hiddenid").val(id);
		$("#order_no").val(order_no);
		$("#order_id").val(order_id);
		$("#back_goods_amount").val(back_goods_amount);
		$("#real_back_amount").val(real_back_amount);
		$("#point").val(point);
		$("#status").val(status);
		$("#update_time").val(update_time);
		$("#voucher").val(voucher);
		$("#back_from").val(back_from);
		$("#invalid_time").val(invalid_time);
		$("#exec_num").val(exec_num);
		$("#back_goods").val(back_goods);
		$("#back_category").val(back_category);
		$("#back_remark").val(back_remark);
		$("#back_resource").val(back_resource);
		$("#reject_remark").val(reject_remark);
		$("#add_time").val(add_time);
		$("#back_return_no").val(back_return_no);
		$("#agree_time").val(agree_time);
		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updOrderBackInfo",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			order_no:$("#order_no").val(),
			order_id:$("#order_id").val(),
			back_goods_amount:$("#back_goods_amount").val(),
			real_back_amount:$("#real_back_amount").val(),
			point:$("#point").val(),
			status:$("#status").val(),
			update_time:$("#update_time").val(),
			voucher:$("#voucher").val(),
			back_from:$("#back_from").val(),
			invalid_time:$("#invalid_time").val(),
			exec_num:$("#exec_num").val(),
			back_goods:$("#back_goods").val(),
			back_category:$("#back_category").val(),
			back_remark:$("#back_remark").val(),
			back_resource:$("#back_resource").val(),
			reject_remark:$("#reject_remark").val(),
			add_time:$("#add_time").val(),
			back_return_no:$("#back_return_no").val()
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