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
						<jsp:include page="/WEB-INF/jsp/banner.html" flush="true"/>
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
			<jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
		</div>

		<div class="page-content">

			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							发布动态消息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">动态消息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">发布动态信息</a></li>
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
									<span class="hidden-480">发布动态信息</span>
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
                          <label class="control-label">动态类型：</label>
                          <div class="controls">
                            <select id="type"  	class="m-wrap small">
															<option value="0">个人动态</option>
															<option value="1">系统动态</option>
														</select><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">动态标题：</label>
                          <div class="controls">
                            <!-- <input id="title" type="text"   class="m-wrap small" /> --><!--  <span class="help-inline">Some hint here</span> -->
                          	<textarea id="title" type="text"   class="m-wrap large">
                          	</textarea>
                          
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">动态链接：</label>
                          <div class="controls">
                            <input id="url" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group" style="display:none;">
                          <label class="control-label">发布时间：</label>
                          <div class="controls">
                            <input id="add_time" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">动态对象：</label>
                          <div class="controls">
                            <input id="user_id" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">动态对象类型：</label>
                          <div class="controls">
                            <input id="user_role_type" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">动态对象值：</label>
                          <div class="controls">
                            <input id="user_role_value" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否删除：</label>
                          <div class="controls">
                           <select id="is_del" 	class="m-wrap small">
															<option value="0">正常</option>
												            <option value="1">删除</option>							
														</select><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selUserNotice">返回</a>

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
		    onkey(7);
		    setactive("742");
		    /* infoset(); */
		});
	
/* 	function infoset(){
		
		var noticeinfo=${noticeinfo };
		
		var id=noticeinfo.id;
		var title=noticeinfo.title;
		var content=noticeinfo.content;
		var get_user_roles=noticeinfo.get_user_roles;
		var get_user_groups=noticeinfo.get_user_groups;
		var get_user_area=noticeinfo.get_user_area;
		var user_id=noticeinfo.user_id;
		var user_role_type=noticeinfo.user_role_type;
		var user_role_value=noticeinfo.user_role_value;
		var state=noticeinfo.state;
		var update_time=noticeinfo.update_time;
		var remark=noticeinfo.remark;


		$("#hiddenid").val(id);
		$("#title").val(title);
		$("#content").val(content);
		$("#getuserroles").val(get_user_roles);
		$("#getusergroups").val(get_user_groups);
		$("#getuserarea").val(get_user_area);
		$("#userid").val(user_id);
		$("#userroletype").val(user_role_type);
		$("#userrolevalue").val(user_role_value);
		$("#state").val(state);
		$("#updatetime").val(update_time);
		$("#remark").val(remark);


		

	}; */
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "addUserNotice",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			    type:$("#type").val(),
		      title:$("#title").val(),
		      url:$("#url").val(),
		      add_time:$("#add_time").val(),
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
	
	

	</script>

</body>

</html>