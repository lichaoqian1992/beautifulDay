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
								用户微信信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户登录信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selUserWechat">用户微信信息</a></li>
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
									<span class="hidden-480">修改用户微信信息</span>
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
											
											<form action="updUserWechat" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
												<input type="hidden" id="hiddenid"value="" name="id">
												<div class="control-group">
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" name="user_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">公众账号ID：</label>
													<div class="controls">
														<input id="wechat_id" type="text" name="wechat_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">OpenID：</label>
													<div class="controls">
														<input id="open_id" type="text" name="open_id"		class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">昵称：</label>
													<div class="controls">
														<input id="nick_name" type="text" name="nick_name"	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">性别：</label>
													<div class="controls">
														<select id="sex" name="sex">
														  <option value="1">男</option>
														  <option value="2">女</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">所在国家：</label>
													<div class="controls">
														<input id="country" type="text" name="country" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">所在省份：</label>
													<div class="controls">
														<input id="province" type="text"  name="province" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">所在城市：</label>
													<div class="controls">
														<input id="city" type="text"  name="city"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">头像地址：</label>
													<div class="controls">
														<img id="headimg" name="headimg"  style="height:100px;">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden"  name="icon" id="icon"/>
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否关注：</label>
													<div class="controls">
														<select id="is_subscribe" name="is_subscribe">
														  <option value="0">未关注</option>
														  <option value="1">已关注</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">最近更新时间：</label>
													<div class="controls">
														<input id="update_time" type="text" name="update_time"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">来源标识：</label>
													<div class="controls">
														<input id="form_id" type="text" name="form_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selUserWechat">返回</a>

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
		    menuact("01_01_02");
	});
	
	function uploadpicdiv(){
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
	
	function infoset(){
		
		var userwechat=${userwechat };
		
		var id=userwechat.id;
		
		var	user_id=userwechat.user_id;
		var	wechat_id=userwechat.wechat_id;
		var	open_id=userwechat.open_id;
		var	nick_name=userwechat.nick_name;
		var	sex=userwechat.sex;
		var	country=userwechat.country;
		var	province=userwechat.province;
		var	city=userwechat.city;
		var	headimg =userwechat.headimg ;
		var	is_subscribe=userwechat.is_subscribe;
		var	update_time=userwechat.update_time;
		var	form_id=userwechat.form_id;


	 	$("#hiddenid").val(id);
	 	$("#user_id").val(user_id);
	 	$("#wechat_id").val(wechat_id);
	 	$("#open_id").val(open_id);
	 	$("#nick_name").val(nick_name);
	 	$("#sex").val(sex);
	 	$("#country").val(country);
	 	$("#province").val(province);
	 	$("#city").val(city);
	 	$("#headimg").attr('src',headimg);
	 	$("#is_subscribe").val(is_subscribe);
	 	$("#update_time").val(update_time);
	 	$("#form_id").val(form_id);
	 	$("#icon").val(headimg);

	};
	
	
	$("#subbutton").on("click",function(){
		$("form").submit();
	});
	
	

	</script>

</body>

</html>