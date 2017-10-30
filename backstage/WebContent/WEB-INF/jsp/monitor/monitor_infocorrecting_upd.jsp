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
										信息纠错记录<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">纠错信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selInfoCorrecting">信息纠错记录</a></li>
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
									<span class="hidden-480">修改信息纠错记录</span>
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
													<label class="control-label">提交人：</label>
													<div class="controls">
														<input id="user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">提交人角色：</label>
													<div class="controls">
														<input id="user_role_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">提交人角色值：</label>
													<div class="controls">
														<input id="user_role_value" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">信息类别：</label>
													<div class="controls">
														<!-- <input id="type" type="text" 	class="m-wrap small" /> --><!--  <span class="help-inline">Some hint here</span> -->
														<select id="type" class="m-wrap small">
															<option value="0">商家</option>
															<option value="1">商品</option>
															<option value="2">新闻</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">对应值：</label>
													<div class="controls">
														<input id="record_id" type="text" 	class="m-wrap large" ><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">修改内容：</label>
													<div class="controls">
														<textarea id="content" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">	
													<label class="control-label">修改时间：</label>
													<div class="controls">
														<input id="add_time" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">	
													<label class="control-label">审核时间：</label>
													<div class="controls">
														<input id="update_time" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">	
													<label class="control-label">审核说明：</label>
													<div class="controls">
														<!-- <input id="remark" type="text" 	class="m-wrap small" /> --><!--  <span class="help-inline">Some hint here</span> -->
														<textarea id="remark" type="text" 	class="m-wrap large">
														
														</textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">处理状态：</label>
													<div class="controls">
														<!-- <input id="status" type="text" 	class="m-wrap small" /> --><!--  <span class="help-inline">Some hint here</span> -->
														<select id="status" type="text" 	class="m-wrap small">
															<option value="0">待处理</option>
															<option value="1">已处理</option>
															<option value="2">已修改</option>
														</select>
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selInfoCorrecting">返回</a>

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
		    menuact("06_02_01");
		    infoset();
		});
	
	function infoset(){
		
		var infocorrectinginfo=${infocorrectinginfo };
		
		var id =infocorrectinginfo.id;
		var user_id =infocorrectinginfo.user_id;
		var user_role_type =infocorrectinginfo.user_role_type;
		var user_role_value =infocorrectinginfo.user_role_value;
		var type =infocorrectinginfo.type;
		var record_id =infocorrectinginfo.record_id;
		var content =infocorrectinginfo.content;
		var add_time =infocorrectinginfo.add_time;
		var update_time =infocorrectinginfo.update_time;
		var remark =infocorrectinginfo.remark;
		var status =infocorrectinginfo.status;

		$("#hiddenid").val(id);
		$("#user_id").val(user_id);
		$("#user_role_type").val(user_role_type);
		$("#user_role_value").val(user_role_value);
		$("#type").val(type);
		$("#record_id").val(record_id);
		$("#content").val(content);
		$("#add_time").val(add_time);
		$("#update_time").val(update_time);
		$("#remark").val(remark);
		$("#status").val(status);

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updInfoCorrecting",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			user_id:$("#user_id").val(),
			user_role_type:$("#user_role_type").val(),
			user_role_value:$("#user_role_value").val(),
			type:$("#type").val(),
			record_id:$("#record_id").val(),
			content:$("#content").val(),
			add_time:$("#add_time").val(),
			update_time:$("#update_time").val(),
			remark:$("#remark").val(),
			status:$("#status").val()

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