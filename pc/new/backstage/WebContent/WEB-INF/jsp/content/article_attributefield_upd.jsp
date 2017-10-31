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
								内容扩展属性		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">发布信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selArticleAttributeField">内容扩展属性</a></li>
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
									<span class="hidden-480">修改内容扩展属性</span>
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
													<label class="control-label">列名：</label>
													<div class="controls">
														<input id="name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">标题：</label>
													<div class="controls">
														<input id="title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">控件类型：</label>
													<div class="controls">
														<input id="control_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">字段类型：</label>
													<div class="controls">
														<input id="data_type" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">字段长度：</label>
													<div class="controls">
														<input id="data_length" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">小数点位数：</label>
													<div class="controls">
														<input id="data_place" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">选项列表：</label>
													<div class="controls">
														<input id="item_option" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">默认值：</label>
													<div class="controls">
														<input id="default_value" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否必填：</label>
													<div class="controls">
														<select class="m-wrap small" id="is_required">
									                		<option value=""></option>
															<option value="0">非必填</option>
															<option value="1">必填</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">是否密码框：</label>
													<div class="controls">
														<input id="is_password" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否允许HTML：</label>
													<div class="controls">
														<input id="is_html" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">编辑器类型：</label>
													<div class="controls">
														<select class="m-wrap small" id="editor_type">
									                		<option value=""></option>
															<option value="0">标准型</option>
															<option value="1">简洁型</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">验证提示信息：</label>
													<div class="controls">
														<input id="valid_tip_msg" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">验证失败提示：</label>
													<div class="controls">
														<input id="valid_error_msg" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">验证正则表达式：</label>
													<div class="controls">
														<input id="valid_pattern" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序数字：</label>
													<div class="controls">
														<input id="sort_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">系统默认：</label>
													<div class="controls">
														<input id="is_sys" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selArticleAttributeField">返回</a>

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
		    menuact("04_03_04");
		});
	
	function infoset(){
		
		var artfield=${artfield };
		
		var id=artfield.id;
		
		var	name=artfield.name;
		var	title=artfield.title;
		var	control_type=artfield.control_type;
		var	data_type=artfield.data_type;
		var	data_length=artfield.data_length;
		var	data_place=artfield.data_place;
		var	artfield_option=artfield.artfield_option;
		var	default_value=artfield.default_value;
		var	is_required=artfield.is_required;
		var	is_password=artfield.is_password;
		var	is_html=artfield.is_html;
		var	editor_type=artfield.editor_type;
		var	valid_tip_msg=artfield.valid_tip_msg;
		var	valid_error_msg=artfield.valid_error_msg;
		var	valid_pattern=artfield.valid_pattern;
		var	sort_id=artfield.sort_id;
		var	is_sys=artfield.is_sys;


	 	$("#hiddenid").val(id);
	 	$("#name").val(name);
	 	$("#title").val(title);
	 	$("#control_type").val(control_type);
	 	$("#data_type").val(data_type);
	 	$("#data_length").val(data_length);
	 	$("#data_place").val(data_place);
	 	$("#item_option").val(item_option);
	 	$("#default_value").val(default_value);
	 	$("#is_required").val(is_required);
	 	$("#is_password").val(is_password);
	 	$("#is_html").val(is_html);
	 	$("#editor_type").val(editor_type);
	 	$("#valid_tip_msg").val(valid_tip_msg);
	 	$("#valid_error_msg").val(valid_error_msg);
	 	$("#valid_pattern").val(valid_pattern);
	 	$("#sort_id").val(sort_id);
	 	$("#is_sys").val(is_sys);





		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updArticleAttributeField",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			name:$("#name").val(),
			title:$("#title").val(),
			control_type:$("#control_type").val(),
			data_type:$("#data_type").val(),
			data_length:$("#data_length").val(),
			data_place:$("#data_place").val(),
			item_option:$("#item_option").val(),
			default_value:$("#default_value").val(),
			is_required:$("#is_required").val(),
			is_password:$("#is_password").val(),
			is_html:$("#is_html").val(),
			editor_type:$("#editor_type").val(),
			valid_tip_msg:$("#valid_tip_msg").val(),
			valid_error_msg:$("#valid_error_msg").val(),
			valid_pattern:$("#valid_pattern").val(),
			sort_id:$("#sort_id").val(),
			is_sys:$("#is_sys").val()

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