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
							修改代理公告
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">代理商信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">修改代理公告</a></li>
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
									<span class="hidden-480">修改代理公告列表</span>
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
													<label class="control-label">公告标题：</label>
													<div class="controls">
														<input id="title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">公告内容：</label>
													<div class="controls">
														<input id="content" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">公告对象：</label>
													<div class="controls">
														<input id="getuserroles" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">公告用户组别：</label>
													<div class="controls">
														<input id="getusergroups" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">公告发布地区：</label>
													<div class="controls">
														<input id="getuserarea" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">发布用户：</label>
													<div class="controls">
														<input id="userid" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">发布用户角色：</label>
													<div class="controls">
														<input id="userroletype" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色对应值：</label>
													<div class="controls">
														<input id="userrolevalue" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">状态：</label>
													<div class="controls">
														<select class="m-warp small" id="state">
														<option value="0">未生效</option>
														<option value="1">已生效</option>
														<option value="2">已失效</option>
														</select>
													</div>
												</div>
												<div class="control-group" style="display:none;">
													<label class="control-label">更新时间：</label>
													<div class="controls">
														<input id="updatetime" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">更新提示：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selAgentNotice">返回</a>

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
		    onkey(4);
		    infoset();
		    setactive("423");
		});
	
	function infoset(){
		
		var agentnoticeinfo=${agentnoticeinfo };
		
		var id=agentnoticeinfo.id;
		var title=agentnoticeinfo.title;
		var content=agentnoticeinfo.content;
		var get_user_roles=agentnoticeinfo.get_user_roles;
		var get_user_groups=agentnoticeinfo.get_user_groups;
		var get_user_area=agentnoticeinfo.get_user_area;
		var user_id=agentnoticeinfo.user_id;
		var user_role_type=agentnoticeinfo.user_role_type;
		var user_role_value=agentnoticeinfo.user_role_value;
		var state=agentnoticeinfo.state;
		var update_time=agentnoticeinfo.update_time;
		var remark=agentnoticeinfo.remark;


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


		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updAgentNotice",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			title:$("#title").val(),
			content:$("#content").val(),
			get_user_roles:$("#getuserroles").val(),
			get_user_groups:$("#getusergroups").val(),
			get_user_area:$("#getuserarea").val(),
			user_id:$("#userid").val(),
			user_role_type:$("#userroletype").val(),
			user_role_value:$("#userrolevalue").val(),
			state:$("#state").val(),
			update_time:$("#updatetime").val(),
			remark:$("#remark").val()
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