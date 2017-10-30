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
								商家物流模板明细		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selShopWuliumubanItem">商家物流模板明细</a></li>
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
									<span class="hidden-480">修改商家物流模板明细</span>
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
													<label class="control-label">商家ID：</label>
													<div class="controls">
														<input id="shop_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">模板ID：</label>
													<div class="controls">
														<input id="mb_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">配送区域：</label>
													<div class="controls">
														<textarea id="send_area" 	class="m-wrap small" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">计算方式：</label>
													<div class="controls">
														<input id="type" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">首重：</label>
													<div class="controls">
														<input id="start" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">运费：</label>
													<div class="controls">
														<input id="start_price" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">每增：</label>
													<div class="controls">
														<input id="augment" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">加收金额：</label>
													<div class="controls">
														<input id="augment_price" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否免运费：</label>
													<div class="controls">
														<select id="is_free">
														  <option value="0">运费</option>
														  <option value="1">免运费</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">免运费金额：</label>
													<div class="controls">
														<input id="free_price" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否全国：</label>
													<div class="controls">
														<select id="is_all">
														  <option value="0">不是全国</option>
														  <option value="1">全国</option>
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
														<input id="update_time" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">备注：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selShopWuliumubanItem">返回</a>

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
		    menuact("02_02_03");
		});
	
	function infoset(){
		
		var wuliuitem=${wuliuitem };
		
		var id=wuliuitem.id;
		
		var	shop_id=wuliuitem.shop_id;
		var	mb_id=wuliuitem.mb_id;
		var	send_area=wuliuitem.send_area;
		var	type=wuliuitem.type;
		var	start=wuliuitem.start;
		var	start_price=wuliuitem.start_price;
		var	augment=wuliuitem.augment;
		var	augment_price=wuliuitem.augment_price;
		var	is_free=wuliuitem.is_free;
		var	free_price=wuliuitem.free_price;
		var	is_all=wuliuitem.is_all;
		var	add_time=wuliuitem.add_time;
		var	update_time=wuliuitem.update_time;
		var	remark=wuliuitem.remark;


	 	$("#hiddenid").val(id);
	 	$("#shop_id").val(shop_id);
	 	$("#mb_id").val(mb_id);
	 	$("#send_area").val(send_area);
	 	$("#type").val(type);
	 	$("#start").val(start);
	 	$("#start_price").val(start_price);
	 	$("#augment").val(augment);
	 	$("#augment_price").val(augment_price);
	 	$("#is_free").val(is_free);
	 	$("#free_price").val(free_price);
	 	$("#is_all").val(is_all);
	 	$("#add_time").val(add_time);
	 	$("#update_time").val(update_time);
	 	$("#remark").val(remark);


	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updShopWuliumubanItem",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			shop_id:$("#shop_id").val(),
			mb_id:$("#mb_id").val(),
			send_area:$("#send_area").val(),
			type:$("#type").val(),
			start:$("#start").val(),
			start_price:$("#start_price").val(),
			augment:$("#augment").val(),
			augment_price:$("#augment_price").val(),
			is_free:$("#is_free").val(),
			free_price:$("#free_price").val(),
			is_all:$("#is_all").val(),
			add_time:$("#add_time").val(),
			update_time:$("#update_time").val(),
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