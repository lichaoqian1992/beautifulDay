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
								托管店铺开关		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selBusinessGroupshop">托管店铺开关</a></li>
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
									<span class="hidden-480">修改托管店铺开关</span>
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
													<label class="control-label">集团店ID：</label>
													<div class="controls">
														<input id="groupshop_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">店铺ID：</label>
													<div class="controls">
														<input id="shop_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												 <div class="control-group">
													<label class="control-label">开关类型：</label>
													<div class="controls">
														<select id="type">
														  <option value="0">内容</option>
														  <option value="1">订单</option>
														  <option value="2">财务</option>
														  <option value="3">人事</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">业务Id：</label>
													<div class="controls">
														<select id="channel_id">
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">开关：</label>
													<div class="controls">
														<select id="is_open">
															  <option value="0">关</option>
															  <option value="1">开</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">通知方式：</label>
													<div class="controls">
														<select id="notice_type">
															  <option value="0">短信</option>
															  <option value="1">邮件</option>
															  <option value="2">人工</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">添加时间：</label>
													<div class="controls">
														<input id="add_time" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">更新时间：</label>
													<div class="controls">
														<input id=update_time type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select id="is_del">
														  <option value="0">正常</option>
														  <option value="1">删除</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">备注：</label>
													<div class="controls">
														<input id=remark type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selBusinessGroupshop">返回</a>

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
		    menuact("02_02_08");
		    var group = ${groupshop};
	        $.ajax({
	            type : "GET",
	            url : "findChannelList",
	            dataType : "json",
	            contentType : "application/json; charset=utf-8",
	            data : {},
	            success : function(data) {
	              var dataList = data.result;
	              for(var i in dataList){
	                 $('#channel_id').append('<option value='+dataList[i].id+'>'+dataList[i].title+'</option>');
	              } 
	              $('#channel_id option[value="'+group.channel_id+'"]').attr('selected',true); 
	            }
	      	});
		});
	
	function infoset(){
		
		var groupshop=${groupshop };
		
		var id=groupshop.id;
		var	groupshop_id=groupshop.groupshop_id;
		var	shop_id=groupshop.shop_id;
		var	type=groupshop.type;
		var	channel_id=groupshop.channel_id;
		var	is_open=groupshop.is_open;
		var	notice_type=groupshop.notice_type;
		var	add_time=groupshop.add_time;
		var	update_time=groupshop.update_time;
		var	is_del=groupshop.is_del;
		var	remark=groupshop.remark;




	 	$("#hiddenid").val(id);
	 	$("#groupshop_id").val(groupshop_id);
	 	$("#shop_id").val(shop_id);
	 	$("#type").val(type);
	 	$("#channel_id").val(channel_id);
	 	$("#is_open").val(is_open);
	 	$("#notice_type").val(notice_type);
	 	$("#add_time").val(add_time);
	 	$("#update_time").val(update_time);
	 	$("#is_del").val(is_del);
	 	$("#remark").val(remark);




	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updBusinessGroupshop",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			groupshop_id:$("#groupshop_id").val(),
			shop_id:$("#shop_id").val(),
			type:$("#type").val(),
			channel_id:$("#channel_id").val(),
			is_open:$("#is_open").val(),
			notice_type:$("#notice_type").val(),
			add_time:$("#add_time").val(),
			update_time:$("#update_time").val(),
			is_del:$("#is_del").val(),
			remark:$("#remark").val()


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