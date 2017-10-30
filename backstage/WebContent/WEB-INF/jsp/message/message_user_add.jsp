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
										用户站内短消息信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">消息</a><i class="icon-angle-right"></i></li>
							<li><a href="selUserMessage">用户站内短消息信息</a></li>
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
									<span class="hidden-480">新增用户站内短消息</span>
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
                          <label class="control-label">短消息类型：</label>
                          <div class="controls">
                            <!-- <input id="type" type="text"  class="m-wrap small" /> --><!--  <span class="help-inline">Some hint here</span> -->
                         	<select id="type" class="m-wrap small">
								<option value="0">系统信息</option>
								<option value="1">收件箱</option>
								<option value="2">发件箱</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">发件人：</label>
                          <div class="controls">
                            <input id="post_user_id" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">发件人角色：</label>
                          <div class="controls">
                          	<select class="m-wrap small" id="post_user_role_type">
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
                            <input id="post_user_role_value" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">收件人：</label>
                          <div class="controls">
                            <input id="accept_user_id" type="text"  class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">收件人角色：</label>
                          <div class="controls">
                          	<select class="m-wrap small" id="accept_user_role_type">
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
                            <input id="accept_user_role_value" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否查看：</label>
                          <div class="controls">
                            <!-- <input id="is_read" type="text"   class="m-wrap small" /> --><!--  <span class="help-inline">Some hint here</span> -->
                          	<select id="is_read" class="m-wrap small">
								<option value="0">未阅</option>
								<option value="1">已阅</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">短消息标题：</label>
                          <div class="controls">
                            <input id="title" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">短消息内容：</label>
                          <div class="controls">
                            <input id="content" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">发送时间：</label>
                          <div class="controls">
                            <input id="post_time" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">阅读时间：</label>
                          <div class="controls">
                            <input id="read_time" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否删除：</label>
                          <div class="controls">
                           <!--  <input id="is_del" type="text"  class="m-wrap large" /> --><!--  <span class="help-inline">Some hint here</span> -->
                          	<select id="is_del"  class="m-wrap large">
                          		<option value="0">正常</option>
                          		<option value="1">删除</option>
                          	</select>
                          </div>
                        </div>
                        
                        
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selUserMessage">返回</a>

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
		    menuact("10_03_02");
		});
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "addUserMessage",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			    type:$("#type").val(),
		      post_user_id:$("#post_user_id").val(),
		      post_user_role_type:$("#post_user_role_type").val(),
		      post_user_role_value:$("#post_user_role_value").val(),
		      accept_user_id:$("#accept_user_id").val(),
		      accept_user_role_type:$("#accept_user_role_type").val(),
		      accept_user_role_value:$("#accept_user_role_value").val(),
		      is_read:$("#is_read").val(),
		      title:$("#title").val(),
		      content:$("#content").val(),
		      post_time:$("#post_time").val(),
		      read_time:$("#read_time").val(),
		      is_del:$("#is_del").val()
				},
		success : function(data) {
			console.log(data);
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