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
						修改业务活动信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selActBusiness">修改业务活动信息</a></li>
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
									<span class="hidden-480">修改业务活动信息</span>
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
											
											<form action="updActBusiness" class="form-horizontal" method="POST" enctype="multipart/form-data">
												<input type="hidden" id="hiddenid"value="" name="id">
												<div class="control-group">
													<label class="control-label">活动开始时间：</label>
													<div class="controls">
														<input id="begin_time" type="text" name="begin_time"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动结束时间：</label>
													<div class="controls">
														<!-- <input id="matter" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="end_time" type="text" name="end_time"	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动有效地区：</label>
													<div class="controls">
														<!-- <input id="content" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="act_area" type="text" name="act_area"	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动名称：</label>
													<div class="controls">
														<!-- <input id="content_wap" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="title" type="text" name="title"	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动图片：</label>
													<div class="controls">
														<img id="pics" name="pics"  style="height:100px;">
							                          	<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgUrl" name="icon" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动规则简介：</label>
													<div class="controls">
														<input id="content" type="text" name="content"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">折扣类型：</label>
													<div class="controls">
														<select class="m-wrap small" id="act_type">
									                		<option value="-1"></option>
															<option value="0">注册</option>
															<option value="1">结算</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动保证金：</label>
													<div class="controls">
														<input id="deposit" type="text" name="deposit"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动手续费：</label>
													<div class="controls">
														<input id="poundage" type="text" name="poundage"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动提成比例：</label>
													<div class="controls">
														<input id="percentage" type="text" name="percentage"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动状态：</label>
													<div class="controls">
														<select class="m-wrap small" id="status">
									                		<option value="-1"></option>
															<option value="0">禁用</option>
															<option value="1">正常</option>
															<option value="2">过期</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动添加时间：</label>
													<div class="controls">
														<input id="add_time" type="text" name="add_time" class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												
												
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selActBusiness">返回</a>

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
		    menuact("04_02_01");
		});
	
	function infoset(){
		
		var actbusiness=${actbusiness };
		
		var id=actbusiness.id;
		var begin_time  =actbusiness.begin_time ;
		var end_time      =actbusiness.end_time ;
		var act_area     =actbusiness.act_area ;
		var title =actbusiness.title ;
		var pics =actbusiness.pics ;
		var content =actbusiness.content ;
		var act_type =actbusiness.act_type ;
		var deposit =actbusiness.deposit ;
		var poundage =actbusiness.poundage ;
		var percentage =actbusiness.percentage ;
		var status =actbusiness.status ;
		var add_time =actbusiness.add_time ;

		$("#hiddenid").val(id);
		$("#begin_time").val(begin_time);
		$("#end_time").val(end_time);
		$("#act_area").val(act_area);
		$("#title").val(title);
		$("#pics").attr("src",pics);
		$("#imgUrl").val(pics);
		$("#content").val(content);
		$("#act_type").val(act_type);
		$("#deposit").val(deposit);
		$("#poundage").val(poundage);
		$("#percentage").val(percentage);
		$("#status").val(status);
		$("#add_time").val(add_time);



		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updActBusiness",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			begin_time:$("#begin_time").val(),
			end_time:$("#end_time").val(),
			act_area:$("#act_area").val(),
			title:$("#title").val(),
			pics:$("#pics").val(),
			content:$("#content").val(),
			act_type:$("#act_type").val(),
			deposit:$("#deposit").val(),
			poundage:$("#poundage").val(),
			percentage:$("#percentage").val(),
			status:$("#status").val(),
			add_time:$("#add_time").val()

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
		$('#pics').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

	</script>

</body>

</html>