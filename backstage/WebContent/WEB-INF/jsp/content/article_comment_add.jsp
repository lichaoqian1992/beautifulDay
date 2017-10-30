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
									内容评论信息记录	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">发布信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selArticleComment">内容评论信息记录</a></li>
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
									<span class="hidden-480">新增内容评论信息记录</span>
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
											
											<form action="addArticleComment" class="form-horizontal" method="POST" enctype="multipart/form-data">
												<div class="control-group">
													<label class="control-label">频道ID：</label>
													<div class="controls">
														<input id="channel_id" type="text" name="channel_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">文章ID：</label>
													<div class="controls">
														<input id="article_id" type="text" name="article_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订单ID：</label>
													<div class="controls">
														<input id="order_id" type="text" name="order_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">父评论ID：</label>
													<div class="controls">
														<input id="parent_id" type="text" name="parent_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" name="user_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色类型：</label>
													<div class="controls">
														<select class="m-wrap small" id="user_role_type" name="user_role_type">
									                		<option value=""></option>
															<option value="Buyer">用户</option>
															<option value="Shop">商家</option>
															<option value="Agent">代理</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色对应值：</label>
													<div class="controls">
														<input id="user_role_value" type="text" name="user_role_value"	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户IP：</label>
													<div class="controls">
														<input id="user_ip" type="text" name="user_ip"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">评论内容：</label>
													<div class="controls">
														<input id="content" type="text" name="content"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">评论图片内容：</label>
													<div class="controls">
														<div id="pics"></div>
														<div>
															<input id="upFile2" type='file' multiple  name='file' style="display: none">
															<input type="hidden" id="icon2" name="icon2" />
															<a id="upAgain2" onclick="uploadpicdiv2()" style="margin-left:5%">重新上传</a>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否锁定：</label>
													<div class="controls">
														<select class="m-wrap small" id="is_lock" name="is_lock">
									                		<option value=""></option>
															<option value="0">正常</option>
															<option value="1">锁定</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">发表时间：</label>
													<div class="controls">
														<input id="add_time" type="text" name="add_time"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否已答复：</label>
													<div class="controls">
														<input id="is_reply" type="text" name="is_reply"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">回复内容：</label>
													<div class="controls">
														<input id="reply_content" type="text" name="reply_content"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">回复时间：</label>
													<div class="controls">
														<input id="reply_time" type="text" name="reply_time"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<input id="is_del" type="text" name="is_del"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												
												
												
												
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selArticleComment">返回</a>

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
		    menuact("04_03_06");
		});
	

	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "addArticleComment",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
				channel_id:$("#channel_id").val(),
				article_id:$("#article_id").val(),
				order_id:$("#order_id").val(),
				parent_id:$("#parent_id").val(),
				user_id:$("#user_id").val(),
				user_role_type:$("#user_role_type").val(),
				user_role_value:$("#user_role_value").val(),
				user_ip:$("#user_ip").val(),
				content:$("#content").val(),
				pics:$("#pics").val(),
				is_lock:$("#is_lock").val(),
				add_time:$("#add_time").val(),
				is_reply:$("#is_reply").val(),
				reply_content:$("#reply_content").val(),
				reply_time:$("#reply_time").val(),
				is_del:$("#is_del").val()
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
	
	function uploadpicdiv2(){
		$("#icon2").val("");
		$('.aa').hide();
		$('#upFile2').show();
		$('#upAgain2').hide();
	}

	</script>

</body>

</html>