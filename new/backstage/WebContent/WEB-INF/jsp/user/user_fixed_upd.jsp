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
								买家家批量充值记录
		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户业务信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selUserFixed">买家批量充值记录</a></li>
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
									<span class="hidden-480">修改买家批量充值记录</span>
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
													<label class="control-label">文件名称：</label>
													<div class="controls">
														<input id="excel_file" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">序号：</label>
													<div class="controls">
														<input id="row_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户标识：</label>
													<div class="controls">
														<input id="user_key" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">充值金额：</label>
													<div class="controls">
														<input id="amount" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">充值代金券：</label>
													<div class="controls">
														<input id="voucher" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色类型：</label>
													<div class="controls">
														<input id="role_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色值：</label>
													<div class="controls">
														<input id="role_value" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">状态：</label>
													<div class="controls">
														<select id="state">
														  <option value="1">原始数据导入成功</option>
														  <option value="2">原始数据异常(用户或账户未找到)</option>
														  <option value="3">账户待充值</option>
														  <option value="4">账户预充值</option>
														  <option value="5">充值失败</option>
														  <option value="6">充值成功</option>
														  <option value="7">预撤销充值</option>
														  <option value="8">撤销充值失败</option>
														  <option value="9">撤销充值成功</option>
														  <option value="10">系统修正</option>
														  <option value="11">数据作废</option>
														</select>
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
													<label class="control-label">备注：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">添加时间：</label>
													<div class="controls">
														<input id="add_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">充值类型：</label>
													<div class="controls">
														<select id="category">
														  <option value="4">不能提现</option>
										  				  <option value="6">可提现</option>
														</select>
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selUserFixed">返回</a>

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
		    menuact("01_03_02");
		});
	
	function infoset(){
		
		var userfixed=${userfixed };
		
		var id=userfixed.id;
		
		var	excel_file=userfixed.excel_file;
		var	row_no=userfixed.row_no;
		var	user_key=userfixed.user_key;
		var	amount=userfixed.amount;
		var	voucher=userfixed.voucher;
		var	user_id=userfixed.user_id;
		var	role_type=userfixed.role_type;
		var	role_value=userfixed.role_value;
		var	state=userfixed.state;
		var	is_del=userfixed.is_del;
		var	remark=userfixed.remark;
		var	add_time=userfixed.add_time;
		var	category=userfixed.category;


	 	$("#hiddenid").val(id);
	 	$("#excel_file").val(excel_file);
	 	$("#row_no").val(row_no);
	 	$("#user_key").val(user_key);
	 	$("#amount").val(amount);
	 	$("#voucher").val(voucher);
	 	$("#user_id").val(user_id);
	 	$("#role_type").val(role_type);
	 	$("#role_value").val(role_value);
	 	$("#state").val(state);
	 	$("#is_del").val(is_del);
	 	$("#remark").val(remark);
	 	$("#add_time").val(add_time);
	 	$("#category").val(category);
		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updUserFixed",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			excel_file:$("#excel_file").val(),
			row_no:$("#row_no").val(),
			user_key:$("#user_key").val(),
			amount:$("#amount").val(),
			voucher:$("#voucher").val(),
			user_id:$("#user_id").val(),
			role_type:$("#role_type").val(),
			role_value:$("#role_value").val(),
			state:$("#state").val(),
			is_del:$("#is_del").val(),
			remark:$("#remark").val(),
			add_time:$("#add_time").val(),
			category:$("#category").val()
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