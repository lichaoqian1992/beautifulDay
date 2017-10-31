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
								广告位放置内容		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">导航信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selAdvertContent">广告位放置内容</a></li>
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
									<span class="hidden-480">修改广告位放置内容</span>
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
											
											<form action="updAdvertContent" class="form-horizontal" method="post" enctype="multipart/form-data">
												<input type="hidden" id="hiddenid"value="" name="id">
												<div class="control-group">
													<label class="control-label">对应广告位ID：</label>
													<div class="controls">
														<input id="advert_id" type="text" name="advert_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">对应购买ID：</label>
													<div class="controls">
														<input id="advert_banner_id" type="text" name="advert_banner_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">内容标注：</label>
													<div class="controls">
														<input id="name" type="text" name="name"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">图片：</label>
													<div class="controls">
														<img id="file_path" name="file_path"  style="height:100px;">
							                          	<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgUrl" name="icon" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">链接地址：</label>
													<div class="controls">
														<input id="link_url" type="text" name="link_url"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">广告内容：</label>
													<div class="controls">
														<input id="content" type="text" name="content" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">广告状态：</label>
													<div class="controls">
														<select class="m-wrap small" id="state" name="state">
									                		<option value="-1"></option>
															<option value="0">未审核</option>
															<option value="1">已审核</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">广告是否显示：</label>
													<div class="controls">
														<select class="m-wrap small" id="is_show" name="is_show">
									                		<option value="-1"></option>
															<option value="0">不显示</option>
															<option value="1">已显示</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">状态对应提醒：</label>
													<div class="controls">
														<input id="remark" type="text" name="remark"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">更新时间：</label>
													<div class="controls">
														<input id="update_time" type="text" name="update_time"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">发布用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" name="user_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色类型：</label>
													<div class="controls">
														<select class="m-wrap small" id="user_role_type" name="user_role_type">
									                		<option value="-1"></option>
															<option value="Buyer">用户</option>
															<option value="Shop">商家</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色类型值：</label>
													<div class="controls">
														<input id="user_role_value" type="text" name="user_role_value"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select class="m-wrap small" id="is_del" name="is_del">
									                		<option value="-1"></option>
															<option value="0">正常</option>
															<option value="1">删除</option>
														</select>
													</div>
												</div>
												
												<div class="form-actions">

													 <button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selAdvertContent">返回</a>

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
		    menuact("04_01_06");
		});
	
	function infoset(){
		
		var advertcontent=${advertcontent };
		
		var id=advertcontent.id;
		
		var	advert_id=advertcontent.advert_id;
		var	advert_banner_id=advertcontent.advert_banner_id;
		var	name=advertcontent.name;
		var	file_path=advertcontent.file_path;
		var	link_url=advertcontent.link_url;
		var	content=advertcontent.content;
		var	state=advertcontent.state;
		var	is_show=advertcontent.is_show;
		var	remark=advertcontent.remark;
		var	update_time=advertcontent.update_time;
		var	user_id=advertcontent.user_id;
		var	user_role_type=advertcontent.user_role_type;
		var	user_role_value=advertcontent.user_role_value;
		var	is_del=advertcontent.is_del;


	 	$("#hiddenid").val(id);
	 	$("#advert_id").val(advert_id);
	 	$("#advert_banner_id").val(advert_banner_id);
	 	$("#name").val(name);
	 	$("#file_path").attr('src',file_path);
	 	$("#imgUrl").val(file_path);
	 	$("#link_url").val(link_url);
	 	$("#content").val(content);
	 	$("#state").val(state);
	 	$("#is_show").val(is_show);
	 	$("#remark").val(remark);
	 	$("#update_time").val(update_time);
	 	$("#user_id").val(user_id);
	 	$("#user_role_type").val(user_role_type);
	 	$("#user_role_value").val(user_role_value);
	 	$("#is_del").val(is_del);




		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updAdvertContent",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			advert_id:$("#advert_id").val(),
			advert_banner_id:$("#advert_banner_id").val(),
			name:$("#name").val(),
			file_path:$("#file_path").val(),
			link_url:$("#link_url").val(),
			content:$("#content").val(),
			state:$("#state").val(),
			is_show:$("#is_show").val(),
			remark:$("#remark").val(),
			update_time:$("#update_time").val(),
			user_id:$("#user_id").val(),
			user_role_type:$("#user_role_type").val(),
			user_role_value:$("#user_role_value").val(),
			is_del:$("#is_del").val()
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
	
	 function uploadpicdiv(){
		 $("#imgUrl").val("");
			$('#file_path').hide();
			$('#upFile').show();
			$('#upAgain').hide();
		}

	</script>

</body>

</html>