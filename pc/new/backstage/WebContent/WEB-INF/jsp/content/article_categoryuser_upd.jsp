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
								用户自定义分类信息记录	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">规格配置</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户自定义分类信息记录</a></li>
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
									<span class="hidden-480">修改用户自定义分类信息记录</span>
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
													<label class="control-label">频道ID：</label>
													<div class="controls">
														<input id="channel_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户ID：</label>
													<div class="controls">
 														<input id="user_id" type="text" 	class="m-wrap small" /> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色：</label>
													<div class="controls">
														<input id="user_role_type" type="text" 	class="m-wrap small" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色值：</label>
													<div class="controls">
														<input id="user_role_value" type="text" 	class="m-wrap small" /> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">分类类型：</label>
													<div class="controls">
														<input id="type" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类别标题：</label>
													<div class="controls">
														<input id="title" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">调用别名：</label>
													<div class="controls">
														<input id="call_index" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">父类别ID：</label>
													<div class="controls">
														<input id="parent_id" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类别ID列表：</label>
													<div class="controls">
														<input id="class_list" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类别深度：</label>
													<div class="controls">
														<input id="class_layer" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序数字：</label>
													<div class="controls">
														<input id="sort_id" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">URL跳转地址：</label>
													<div class="controls">
														<input id="link_url" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">图片地址：</label>
													<div class="controls">
														<input id="img_url" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">备注说明：</label>
													<div class="controls">
														<input id="content" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO标题：</label>
													<div class="controls">
														<input id="seo_title" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO关健字：</label>
													<div class="controls">
														<input id="seo_keywords" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO描述：</label>
													<div class="controls">
														<input id="seo_description" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
											
												
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selArticleCategoryUser">返回</a>

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
		    menuact("03_06_03");
		});
	
	function infoset(){
		
		var articlecategoryuser=${articlecategoryuser };
		
		var id=articlecategoryuser.id;
		var	channel_id=articlecategoryuser.channel_id;
		var	user_id=articlecategoryuser.user_id;
		var	user_role_type=articlecategoryuser.user_role_type;
		var	user_role_value=articlecategoryuser.user_role_value;
		var	type=articlecategoryuser.type;
		var	title=articlecategoryuser.title;
		var	call_index=articlecategoryuser.call_index;
		var	parent_id=articlecategoryuser.parent_id;
		var	class_list=articlecategoryuser.class_list;
		var	class_layer=articlecategoryuser.class_layer;
		var	sort_id=articlecategoryuser.sort_id;
		var	link_url=articlecategoryuser.link_url;
		var	img_url=articlecategoryuser.img_url;
		var	content=articlecategoryuser.content;
		var	seo_title=articlecategoryuser.seo_title;
		var	seo_keywords=articlecategoryuser.seo_keywords;
		var	seo_description=articlecategoryuser.seo_description;

		$("#hiddenid").val(id);
		$("#channel_id").val(channel_id);
		$("#user_id").val(user_id);
		$("#user_role_type").val(user_role_type);
		$("#user_role_value").val(user_role_value);
		$("#type").val(type);
		$("#title").val(title);
		$("#call_index").val(call_index);
		$("#parent_id").val(parent_id);
		$("#class_list").val(class_list);
		$("#class_layer").val(class_layer);
		$("#sort_id").val(sort_id);
		$("#link_url").val(link_url);
		$("#img_url").val(img_url);
		$("#content").val(content);
		$("#seo_title").val(seo_title);
		$("#seo_keywords").val(seo_keywords);
		$("#seo_description").val(seo_description);




		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updArticleCategoryUser",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			channel_id:$("#channel_id").val(),
			user_id:$("#user_id").val(),
			user_role_type:$("#user_role_type").val(),
			user_role_value:$("#user_role_value").val(),
			type:$("#type").val(),
			title:$("#title").val(),
			call_index:$("#call_index").val(),
			parent_id:$("#parent_id").val(),
			class_list:$("#class_list").val(),
			class_layer:$("#class_layer").val(),
			sort_id:$("#sort_id").val(),
			link_url:$("#link_url").val(),
			img_url:$("#img_url").val(),
			content:$("#content").val(),
			seo_title:$("#seo_title").val(),
			seo_keywords:$("#seo_keywords").val(),
			seo_description:$("#seo_description").val()

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