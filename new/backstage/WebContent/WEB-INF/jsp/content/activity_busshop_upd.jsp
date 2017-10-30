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
							商家活动信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selActBusShop">商家活动信息</a></li>
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
									<span class="hidden-480">修改商家活动信息</span>
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
											
											<form action="updActBusShop" class="form-horizontal" method="POST" enctype="multipart/form-data">
												<input type="hidden" id="hiddenid" value="" name="id">
												<div class="control-group">
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" name="user_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色类型：</label>
													<div class="controls">
														<!-- <input id="matter" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="role_type" type="text" name="role_type"	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色对应值：</label>
													<div class="controls">
														<!-- <input id="content" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="role_value" type="text" name="role_value"	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动规则简介：</label>
													<div class="controls">
														<!-- <input id="content_wap" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="content" type="text" name="content"	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动开始时间：</label>
													<div class="controls">
														<input id="begin_time" type="text" name="begin_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动结束时间：</label>
													<div class="controls">
														<input id="end_time" type="text" name="end_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动名称：</label>
													<div class="controls">
														<input id="title" type="text" name="title"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动小标题：</label>
													<div class="controls">
														<input id="sub_title" type="text" name="sub_title"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
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
													<label class="control-label">活动状态：</label>
													<div class="controls">
														<select class="m-wrap small" id="status" name="status">
									                		<option value="0">未生效</option>
															<option value="1">已生效</option>
															<option value="2">已过期</option>
															<option value="3">禁用</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动添加时间：</label>
													<div class="controls">
														<input id="add_time" type="text" name="add_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动更新时间：</label>
													<div class="controls">
														<input id="update_time" type="text" name="update_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select class="m-wrap small" id="is_del" name="is_del">
									                		<option value="0">正常</option>
															<option value="1">删除</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">记录：</label>
													<div class="controls">
														<input id="remark" type="text" name="remark"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动类型：</label>
													<div class="controls">
														<select class="m-wrap small" id="act_type" name="act_type">
									                		<option value="mj">满减</option>
															<option value="zk">折扣</option>
															<option value="lj">立减</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动是否线上：</label>
													<div class="controls">
														<select class="m-wrap small" id="is_online" name="is_online">
									                		<option value="0">仅线上展示</option>
															<option value="1">用于结算</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否支持商品：</label>
													<div class="controls">
														<select class="m-wrap small" id="is_good_add" name="is_good_add">
									                		<option value="0">不支持</option>
															<option value="1">支持</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动是否全店通用：</label>
													<div class="controls">
														<select class="m-wrap small" id="act_business" name="act_business">
									                		<option value="1">全店</option>
															<option value="2">点餐</option>
															<option value="3">外卖</option>
															<option value="4">商品</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">打折折扣值：</label>
													<div class="controls">
														<input id="zk_value" type="text" name="zk_value"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">满减活动值：</label>
													<div class="controls">
														<input id="mj_value" type="text" name="mj_value"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">立减活动值：</label>
													<div class="controls">
														<input id="lj_value" type="text" name="lj_value"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
											
												
												
												
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selActBusShop">返回</a>

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
		    menuact("04_02_09");
		});
	
	function infoset(){
		
		var actbusshop=${actbusshop };
		
		var id=actbusshop.id;
		var user_id  =actbusshop.user_id ;
		var role_type      =actbusshop.role_type ;
		var role_value     =actbusshop.role_value ;
		var content =actbusshop.content ;
		var begin_time =actbusshop.begin_time ;
		var end_time =actbusshop.end_time ;
		var title =actbusshop.title ;
		var sub_title =actbusshop.sub_title ;
		var pics =actbusshop.pics ;
		var status =actbusshop.status ;
		var add_time =actbusshop.add_time ;
		var update_time =actbusshop.update_time ;
		var is_del =actbusshop.is_del ;
		var remark =actbusshop.remark ;
		var act_type =actbusshop.act_type ;
		var is_online =actbusshop.is_online;
    var is_good_add =actbusshop.is_good_add;
    var act_business =actbusshop.act_business;
		var zk_value =actbusshop.zk_value ;
		var mj_value =actbusshop.mj_value ;
		var lj_value =actbusshop.lj_value ;

		$("#hiddenid").val(id);
		$("#user_id").val(user_id);
		$("#role_type").val(role_type);
		$("#role_value").val(role_value);
		$("#content").val(content);
		$("#begin_time").val(begin_time);
		$("#end_time").val(end_time);
		$("#title").val(title);
		$("#sub_title").val(sub_title);
		$("#pics").attr("src",pics);
		$("#imgUrl").val(pics);
		$("#status").val(status);
		$("#add_time").val(add_time);
		$("#update_time").val(update_time);
		$("#is_del").val(is_del);
		$("#remark").val(remark);
		$("#act_type").val(act_type);
		$("#is_online").val(is_online);
		$("#is_good_add").val(is_good_add);
		$("#act_business").val(act_business);
		$("#zk_value").val(zk_value);
		$("#mj_value").val(mj_value);
		$("#lj_value").val(lj_value);


	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updActBusShop",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			user_id:$("#user_id").val(),
			role_type:$("#role_type").val(),
			role_value:$("#role_value").val(),
			content:$("#content").val(),
			begin_time:$("#begin_time").val(),
			end_time:$("#end_time").val(),
			title:$("#title").val(),
			sub_title:$("#sub_title").val(),
			pics:$("#pics").val(),
			status:$("#status").val(),
			add_time:$("#add_time").val(),
			update_time:$("#update_time").val(),
			is_del:$("#is_del").val(),
			remark:$("#remark").val(),
			act_type:$("#act_type").val(),
			is_online:$("#is_online").val(),
			is_good_add:$("#is_good_add").val(),
			act_business:$("#act_business").val(),
			zk_value:$("#zk_value").val(),
			mj_value:$("#mj_value").val(),
			lj_value:$("#lj_value").val()

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
		$('#pics').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

	</script>

</body>

</html>