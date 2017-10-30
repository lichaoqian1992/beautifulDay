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
							普通活动信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selActCommon">普通活动信息</a></li>
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
									<span class="hidden-480">修改普通活动信息</span>
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
											
											<form action="updActCommon" class="form-horizontal" method="POST" enctype="multipart/form-data">
												<input type="hidden" id="hiddenid" value="" name="id">
												<div class="control-group">
													<label class="control-label">活动标题：</label>
													<div class="controls">
														<input id="title" type="text" name="title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动图片地址：</label>
													<div class="controls">
														<img id="pics" name="pics"  style="height:100px;">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgUrl" name="icon" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
														<!-- <input id="matter" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动开始时间：</label>
													<div class="controls">
														<!-- <input id="content" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="begin_time" type="text" name="begin_time"	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动结束时间：</label>
													<div class="controls">
														<!-- <input id="content_wap" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="end_time" type="text" name="end_time"	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动简介：</label>
													<div class="controls">
														<input id="content" type="text" name="content"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动链接地址：</label>
													<div class="controls">
														<input id="link_url" type="text" name="link_url"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序数字：</label>
													<div class="controls">
														<input id="sort_id" type="text" name="sort_id"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动状态：</label>
													<div class="controls">
														<select class="m-wrap small" id="state" name="state">
															<option value="-1"></option>
															<option value="0">待通过</option>
															<option value="1">正常</option>
															<option value="2">过期</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">参与人员级别：</label>
													<div class="controls">
														<input id="join_user_groups" type="text" name="join_user_groups"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">参与人员角色：</label>
													<div class="controls">
														<input id="join_user_role" type="text" name="join_user_role"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">参与终端类别：</label>
													<div class="controls">
														<input id="join_terminal_type" type="text" name="join_terminal_type"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">参与人数：</label>
													<div class="controls">
														<input id="join_user_count" type="text" name="join_user_count"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
											
												
												
												
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selActCommon">返回</a>

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
		    menuact("04_02_04");
		});
	
	function infoset(){
		
		var actcommon=${actcommon };
		
		var id=actcommon.id;
		var title  =actcommon.title ;
		var pics      =actcommon.pics ;
		var begin_time     =actcommon.begin_time ;
		var end_time =actcommon.end_time ;
		var content =actcommon.content ;
		var link_url =actcommon.link_url ;
		var sort_id =actcommon.sort_id ;
		var state =actcommon.state ;
		var join_user_groups =actcommon.join_user_groups ;
		var join_user_role =actcommon.join_user_role ;
		var join_terminal_type =actcommon.join_terminal_type ;
		var join_user_count =actcommon.join_user_count ;

		$("#hiddenid").val(id);
		$("#title").val(title);
		$("#pics").attr("src",pics);
		$("#imgUrl").val(pics);
		$("#begin_time").val(begin_time);
		$("#end_time").val(end_time);
		$("#content").val(content);
		$("#link_url").val(link_url);
		$("#sort_id").val(sort_id);
		$("#state").val(state);
		$("#join_user_groups").val(join_user_groups);
		$("#join_user_role").val(join_user_role);
		$("#join_terminal_type").val(join_terminal_type);
		$("#join_user_count").val(join_user_count);



		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updActCommon",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			title:$("#title").val(),
			pics:$("#pics").val(),
			begin_time:$("#begin_time").val(),
			end_time:$("#end_time").val(),
			content:$("#content").val(),
			link_url:$("#link_url").val(),
			sort_id:$("#sort_id").val(),
			state:$("#state").val(),
			join_user_groups:$("#join_user_groups").val(),
			join_user_role:$("#join_user_role").val(),
			join_terminal_type:$("#join_terminal_type").val(),
			join_user_count:$("#join_user_count").val()

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
	
	function uploadpicdiv(){
		$("#imgUrl").val("");
		$('#pics').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

	</script>

</body>

</html>