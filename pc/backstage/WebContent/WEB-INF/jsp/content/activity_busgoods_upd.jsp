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
							修改商家活动商品记录信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">参与活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">修改商家活动商品记录信息</a></li>
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
									<span class="hidden-480">修改商家活动商品记录</span>
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
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色类型：</label>
													<div class="controls">
														<!-- <input id="matter" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="role_type" type="text" 	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">角色对应值：</label>
													<div class="controls">
														<!-- <input id="content" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="role_value" type="text" 	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">活动ID：</label>
													<div class="controls">
														<!-- <input id="content_wap" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="act_bus_shop_id" type="text" 	class="m-wrap large"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品ID：</label>
													<div class="controls">
														<input id="article_id" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">规格ID：</label>
													<div class="controls">
														<input id="spec_id" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品原价：</label>
													<div class="controls">
														<input id="old_value" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品活动价：</label>
													<div class="controls">
														<input id="new_value" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否生效失效：</label>
													<div class="controls">
														<input id="status" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
											
												
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selActBusGoods">返回</a>

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
		    onkey(1);
		    infoset();
		    setactive("183");
		});
	
	function infoset(){
		
		var actbusgoods=${actbusgoods };
		
		var id=actbusgoods.id;
		var user_id  =actbusgoods.user_id ;
		var role_type      =actbusgoods.role_type ;
		var role_value     =actbusgoods.role_value ;
		var act_bus_shop_id =actbusgoods.act_bus_shop_id ;
		var article_id =actbusgoods.article_id ;
		var spec_id =actbusgoods.spec_id ;
		var old_value =actbusgoods.old_value ;
		var new_value =actbusgoods.new_value ;
		var status =actbusgoods.status ;

		$("#hiddenid").val(id);
		$("#user_id").val(user_id);
		$("#role_type").val(role_type);
		$("#role_value").val(role_value);
		$("#act_bus_shop_id").val(act_bus_shop_id);
		$("#article_id").val(article_id);
		$("#spec_id").val(spec_id);
		$("#old_value").val(old_value);
		$("#new_value").val(new_value);
		$("#status").val(status);


		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updActBusGoods",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			user_id:$("#user_id").val(),
			role_type:$("#role_type").val(),
			role_value:$("#role_value").val(),
			act_bus_shop_id:$("#act_bus_shop_id").val(),
			article_id:$("#article_id").val(),
			spec_id:$("#spec_id").val(),
			old_value:$("#old_value").val(),
			new_value:$("#new_value").val(),
			status:$("#status").val()

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
	
	

	</script>

</body>

</html>