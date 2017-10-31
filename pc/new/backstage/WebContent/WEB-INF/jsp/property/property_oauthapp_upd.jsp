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
								用户第三方登陆类型信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">系统配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selUserOauthApp">用户第三方登陆类型信息</a></li>
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
									<span class="hidden-480">修改用户第三方登陆类型信息</span>
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
											
											<form action="updUserOauthApp" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
												<input type="hidden" id="hiddenid" name="id" value="">
												<div class="control-group">
													<label class="control-label">标题：</label>
													<div class="controls">
														<input id="title" type="text" name="title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">显示图片地址：</label>
													<div class="controls">
														<img id="img_url" name="img_url"  style="height:100px;">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgUrl" name="imgUrl" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">三方标记ID：</label>
													<div class="controls">
														<input id="app_id" type="text" name="app_id"	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">三方通讯KEY：</label>
													<div class="controls">
														<input id="app_key" type="text" name="app_key"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">描述：</label>
													<div class="controls">
														<input id="remark" type="text" name="remark"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序数字：</label>
													<div class="controls">
														<input id="sort_id" type="text" name="sort_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支持移动设备：</label>
													<div class="controls">
														<select id="is_mobile" name="is_mobile" class="m-wrap small">
															<option value="0">通用</option>
															<option value="1">电脑</option>
															<option value="2">移动</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否启用：</label>
													<div class="controls">
														<select id="is_lock" name="is_lock" class="m-wrap small">
															<option value="0">启用</option>
															<option value="1">不启用</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">接口目录：</label>
													<div class="controls">
														<input id="api_path" type="text" name="api_path"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select id="is_del" name="is_del" class="m-wrap small">
															<option value="0">正常</option>
															<option value="1">删除</option>
														</select>
													</div>
												</div>
												
												
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selUserOauthApp">返回</a>

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
		    menuact("03_04_09");
		});
	
	function infoset(){
		
		var oauthapp=${oauthapp };
		
		var id=oauthapp.id;
		
		var	title=oauthapp.title
		var	img_url=oauthapp.img_url
		var	app_id=oauthapp.app_id
		var	app_key=oauthapp.app_key
		var	remark=oauthapp.remark
		var	sort_id=oauthapp.sort_id
		var	is_mobile=oauthapp.is_mobile
		var	is_lock=oauthapp.is_lock
		var	api_path=oauthapp.api_path
		var	is_del=oauthapp.is_del


	 	$("#hiddenid").val(id);
		$("#title").val(title);
		$("#img_url").attr('src',img_url);
		$("#app_id").val(app_id);
		$("#app_key").val(app_key);
		$("#remark").val(remark);
		$("#sort_id").val(sort_id);
		$("#is_mobile").val(is_mobile);
		$("#is_lock").val(is_lock);
		$("#api_path").val(api_path);
		$("#is_del").val(is_del);
		$("#imgUrl").val(img_url);




	};
	
	
	 function uploadpicdiv(){
		$('#imgUrl').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

	</script>

</body>

</html>