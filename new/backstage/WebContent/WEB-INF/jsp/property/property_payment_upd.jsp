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
										支付方式信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">系统配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selPayment">支付方式信息</a></li>
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
									<span class="hidden-480">修改支付方式信息</span>
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
											
											<form action="updPayment" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
												<input type="hidden" id="hiddenid" value="" name="id">
												<div class="control-group">
													<label class="control-label">父级ID：</label>
													<div class="controls">
														<input id="parentid" type="text" name="parent_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付方式名称：</label>
													<div class="controls">
														<input id="title" type="text" name="title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">显示图片地址：</label>
													<div class="controls">
														<img id="imgurl" name="img_url"  style="height:100px;">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgUrl" name="imgUrl" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">备注说明：</label>
													<div class="controls">
														<input id="remark" type="text" name="remark" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付类型：</label>
													<div class="controls">
														<select id="type" name="type" class="m-wrap small">
															<option value="1">线上</option>
															<option value="2">线下</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">手续费类型：</label>
													<div class="controls">
														<select id="poundagetype" name="poundage_type" class="m-wrap small">
															<option value="1">百分比</option>
															<option value="2">固定金额</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">手续费金额：</label>
													<div class="controls">
														<input id="poundageamount" name="poundage_amount" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序：</label>
													<div class="controls">
														<input id="sortid" name="sort_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支持移动设备：</label>
													<div class="controls">
														<select id="ismobile" name="is_mobile" class="m-wrap small">
															<option value="0">通用</option>
															<option value="1">电脑</option>
															<option value="2">移动</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否启用：</label>
													<div class="controls">
														<select id="islock" name="is_lock" class="m-wrap small">
															<option value="0">不启用</option>
															<option value="1">启用</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">API目录名称：</label>
													<div class="controls">
														<input id="apipath" name="api_path" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												
												
												
												
												
												
												
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selPayment">返回</a>

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
		    menuact("03_04_03");
		});
	
	function infoset(){
		
		var paymentinfo=${paymentinfo };
		
		var id=paymentinfo.id;
		var parent_id=paymentinfo.parent_id;
		var title=paymentinfo.title;
		var img_url=paymentinfo.img_url;
		var remark=paymentinfo.remark;
		var type=paymentinfo.type;
		var poundage_type=paymentinfo.poundage_type;
		var poundage_amount=paymentinfo.poundage_amount;
		var sort_id=paymentinfo.sort_id;
		var is_mobile=paymentinfo.is_mobile;
		var is_lock=paymentinfo.is_lock;
		var api_path=paymentinfo.api_path;

		
		$("#hiddenid").val(id);
		$("#parentid").val(parent_id);
		$("#title").val(title);
		$("#imgurl").attr('src',img_url);
		$("#remark").val(remark);
		$("#type").val(type);
		$("#poundagetype").val(poundage_type);
		$("#poundageamount").val(poundage_amount);
		$("#sortid").val(sort_id);
		$("#ismobile").val(is_mobile);
		$("#islock").val(is_lock);
		$("#apipath").val(api_path);
		$("#imgUrl").val(img_url);

	};
	
	function uploadpicdiv(){
		$('#imgUrl').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

	</script>

</body>

</html>