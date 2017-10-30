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
										信誉规则
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">系统配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selDistribution">信誉规则</a></li>
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
									<span class="hidden-480">修改信誉规则</span>
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
												<input type="hidden" id="hiddenid" value="">
												<div class="control-group">
													<label class="control-label">枚举业务类型：</label>
													<div class="controls">
														<select id="ebusiness_type" class="m-wrap small">
															<option value="1">商品评分</option>
															<option value="2">店铺评分</option>
															<option value="3">未发货导致取消</option>
															<option value="4">超过72小时</option>
															<option value="5">商家原因退换货</option>
															<option value="6">商家无贷退款</option>
															<option value="7">买家退款不处理</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">参数键：</label>
													<div class="controls">
														<input id="param_key" type="text" onkeyup="value=value.replace(/[^\- \d]/g,'')" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">参数值：</label>
													<div class="controls">
														<input id="param_value" type="text" onkeyup="value=value.replace(/[^\- \d]/g,'')" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">起始次数：</label>
													<div class="controls">
														<input id="start_count" type="text" onkeyup="value=value.replace(/[^\- \d]/g,'')" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">标题	：</label>
													<div class="controls">
														<input id="title" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">描述：</label>
													<div class="controls">
														<input id="descr" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selRules">返回</a>

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
		    menuact("03_04_01");
		});
	
	function infoset(){
		
		var distinfo=${Rules};
		
		var id=distinfo.id;
		var ebusiness_type=distinfo.ebusiness_type;
		var param_key=distinfo.param_key;
		var param_value=distinfo.param_value;
		var start_count=distinfo.start_count;
		var title=distinfo.title;
		var descr=distinfo.descr;

		$("#hiddenid").val(id);
		$("#ebusiness_type").val(ebusiness_type);
		$("#param_key").val(param_key);
		$("#param_value").val(param_value);
		$("#start_count").val(start_count);
		$("#title").val(title);
		$("#descr").val(descr);


		

	};
	
	
	$("#subbutton").on("click",function(){
		$.ajax({
		type : "GET",
		url : "updRules",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			ebusiness_type:$("#ebusiness_type").val(),
			param_key:$("#param_key").val(),
			param_value:$("#param_value").val(),
			start_count:$("#start_count").val(),
			title:$("#title").val(),
			descr:$("#descr").val()
				},
		success : function(data) {
			if(data.status==0){
				location.reload();
			}else{
				alert("修改失败");
			}
		},error:function(){
			alert("修改参数出错!");
		}
		})
	});
	
	

	</script>

</body>

</html>