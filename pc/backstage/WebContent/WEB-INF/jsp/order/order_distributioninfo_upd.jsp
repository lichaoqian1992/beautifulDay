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
							订单详细配送类信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单状态信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单详细配送类信息</a></li>
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
									<span class="hidden-480">修改订单详细配送类信息</span>
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
													<label class="control-label">对应订单ID：</label>
													<div class="controls">
														<input id="order_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">订购人姓名：</label>
													<div class="controls">
														<input id="name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订购联系电话：</label>
													<div class="controls">
														<input id="mobile" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">所属地区：</label>
													<div class="controls">
														<input id="area" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">详细地址：</label>
													<div class="controls">
														<input id="address" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">期望送达时间：</label>
													<div class="controls">
														<input id="hop_arrive_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">配送时间：</label>
													<div class="controls">
														<input id="send_time" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">配送人：</label>
													<div class="controls">
														<input id="send_user_name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">配送联系电话：</label>
													<div class="controls">
														<input id="send_user_mobile" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">实际送达时间：</label>
													<div class="controls">
														<input id="real_arrive_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">短信通知状态：</label>
													<div class="controls">
														<select id="sms_send_state">
														    <option value="1">已发送</option>
														    <option value="0">未发送</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">短信通知时间：</label>
													<div class="controls">
														<input id="sms_send_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">配送状态：</label>
													<div class="controls">
														<select id="send_status">
														    <option value="1">待配送</option>
														    <option value="2">已配送</option>
														    <option value="3">已送达</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">人数：</label>
													<div class="controls">
														<input id="members" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">地理坐标经度：</label>
													<div class="controls">
														<input id="longitude" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">地理坐标维度：</label>
													<div class="controls">
														<input id="latitude" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selOrderDistributioninfo">返回</a>

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
		    setactive("634");
		});
	
	function infoset(){
		
		var orderdistributioninfo=${orderdistributioninfo };
		
		var id=orderdistributioninfo.id;
		var order_id=orderdistributioninfo.order_id;
		var name=orderdistributioninfo.name;
		var mobile=orderdistributioninfo.mobile;
		var area=orderdistributioninfo.area;
		var address=orderdistributioninfo.address;
		var hop_arrive_time=orderdistributioninfo.hop_arrive_time;
		var send_time=orderdistributioninfo.send_time;
		var send_user_name=orderdistributioninfo.send_user_name;
		var send_user_mobile=orderdistributioninfo.send_user_mobile;
		var real_arrive_time=orderdistributioninfo.real_arrive_time;
		var sms_send_state=orderdistributioninfo.sms_send_state;
		var sms_send_time=orderdistributioninfo.sms_send_time;
		var send_status=orderdistributioninfo.send_status;
		var members=orderdistributioninfo.members;
		var longitude=orderdistributioninfo.longitude;
		var latitude=orderdistributioninfo.latitude;

		
		$("#hiddenid").val(id);
		$("#order_id").val(order_id);
		$("#name").val(name);
		$("#mobile").val(mobile);
		$("#area").val(area);
		$("#address").val(address);
		$("#hop_arrive_time").val(hop_arrive_time);
		$("#send_time").val(send_time);
		$("#send_user_name").val(send_user_name);
		$("#send_user_mobile").val(send_user_mobile);
		$("#real_arrive_time").val(real_arrive_time);
		$("#sms_send_state").val(sms_send_state);
		$("#sms_send_time").val(sms_send_time);
		$("#send_status").val(send_status);
		$("#members").val(members);
		$("#longitude").val(longitude);
		$("#latitude").val(latitude);
		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updOrderDistributioninfo",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			order_id:$("#order_id").val(),
			name:$("#name").val(),
			mobile:$("#mobile").val(),
			area:$("#area").val(),
			address:$("#address").val(),
			hop_arrive_time:$("#hop_arrive_time").val(),
			send_time:$("#send_time").val(),
			send_user_name:$("#send_user_name").val(),
			send_user_mobile:$("#send_user_mobile").val(),
			real_arrive_time:$("#real_arrive_time").val(),
			sms_send_state:$("#sms_send_state").val(),
			sms_send_time:$("#sms_send_time").val(),
			send_status:$("#send_status").val(),
			members:$("#members").val(),
			longitude:$("#longitude").val(),
			latitude:$("#latitude").val()
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