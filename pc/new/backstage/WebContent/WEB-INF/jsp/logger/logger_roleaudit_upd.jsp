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
                角色后台管理员审核分配   <!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">角色后台管理</a><i class="icon-angle-right"></i></li>
              <li><a href="selRoleAudit"> 角色后台管理员审核分配</a></li>
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
									<span class="hidden-480">修改角色后台管理员审核分配</span>
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
													<label class="control-label">新增/编辑ID：</label>
													<div class="controls">
														<input id="add_user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">业务人员ID：</label>
													<div class="controls">
														<textarea id="bus_user_id" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">审核用户ID：</label>
													<div class="controls">
														<textarea id="audit_user_id" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">录入用户ID：</label>
													<div class="controls">
														<textarea id="input_user_id" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">检查用户ID：</label>
													<div class="controls">
														<textarea id="check_user_id" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">分配类型：</label>
													<div class="controls">
														<select id="audit_type"  class="m-wrap small">
															<option value="0">商家入驻</option>
															<option value="1">商家变更</option>
															<option value="2">商品审核</option>
															<option value="3">纠错审核</option>
														</select>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">分配对应值：</label>
													<div class="controls">
														<textarea id="audit_value" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">分配增加时间：</label>
													<div class="controls">
														<textarea id="add_time" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">审核完成时间：</label>
													<div class="controls">
														<textarea id="audit_end_time" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">审核结果：</label>
													<div class="controls">
														<textarea id="audit_result" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">录入开始时间：</label>
													<div class="controls">
														<textarea id="input_begin_time" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">录入完成时间：</label>
													<div class="controls">
														<textarea id="input_end_time" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">录入结果：</label>
													<div class="controls">
														<textarea id="input_result" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">检查完成时间：</label>
													<div class="controls">
														<textarea id="check_end_time" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">检查结果：</label>
													<div class="controls">
														<textarea id="check_result" type="text" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												

												
											
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selRoleAudit">返回</a>

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
		    menuact("05_02_04");
		    infoset();
		});
	
	function infoset(){
		
		var roleaudit=${roleaudit };
		console.log(roleaudit);
		var id =roleaudit.id;
		var add_user_id =roleaudit.add_user_id;
		var bus_user_id =roleaudit.bus_user_id;
		var audit_user_id =roleaudit.audit_user_id;
		var input_user_id =roleaudit.input_user_id;
		var check_user_id =roleaudit.check_user_id;
		var audit_type =roleaudit.audit_type;
		var audit_value =roleaudit.audit_value;
		var add_time =roleaudit.add_time;
		var audit_end_time =roleaudit.audit_end_time;
		var audit_result =roleaudit.audit_result;
		var input_begin_time =roleaudit.input_begin_time;
		var input_end_time =roleaudit.input_end_time;
		var input_result =roleaudit.input_result;
		var check_end_time =roleaudit.check_end_time;
		var check_result =roleaudit.check_result;

		$("#hiddenid").val(id);
		$("#add_user_id").val(add_user_id);
		$("#bus_user_id").val(bus_user_id);
		$("#audit_user_id").val(audit_user_id);
		$("#input_user_id").val(input_user_id);
		$("#check_user_id").val(check_user_id);
		$("#audit_type").val(audit_type);
		$("#audit_value").val(audit_value);
		$("#add_time").val(add_time);
		$("#audit_end_time").val(audit_end_time);
		$("#audit_result").val(audit_result);
		$("#input_begin_time").val(input_begin_time);
		$("#input_end_time").val(input_end_time);
		$("#input_result").val(input_result);
		$("#check_end_time").val(check_end_time);
		$("#check_result").val(check_result);
		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updRoleAudit",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			add_user_id:$("#add_user_id").val(),
			/* role_type:$("#role_type").val(),
			role_value:$("#role_value").val(), */
			bus_user_id:$("#bus_user_id").val(),
			audit_user_id:$("#audit_user_id").val(),
			input_user_id:$("#input_user_id").val(),
			check_user_id:$("#check_user_id").val(),
			audit_type:$("#audit_type").val(),
			audit_value:$("#audit_value").val(),
			add_time:$("#add_time").val(),
			audit_end_time:$("#audit_end_time").val(),
			audit_result:$("#audit_result").val(),
			input_begin_time:$("#input_begin_time").val(),
			input_end_time:$("#input_end_time").val(),
			input_result:$("#input_result").val(),
			check_end_time:$("#check_end_time").val(),
			check_result:$("#check_result").val()

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