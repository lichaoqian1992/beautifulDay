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
								频道所属站点详细信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">频道配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selChannelSite">频道所属站点详细信息</a></li>
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
									<span class="hidden-480">修改频道所属站点详细信息</span>
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
													<label class="control-label">站点标题：</label>
													<div class="controls">
														<input id="title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">生成目录名：</label>
													<div class="controls">
														<input id="build_path" type="text" 	class="m-wrap small" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">模板目录名：</label>
													<div class="controls">
														<input id="templet_path" type="text" 	class="m-wrap small" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">绑定域名：</label>
													<div class="controls">
														<input id="domain" type="text" 	class="m-wrap small" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">网站名称：</label>
													<div class="controls">
														<input id="name" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">网站LOGO：</label>
													<div class="controls">
														<input id="logo" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">公司名称：</label>
													<div class="controls">
														<input id="company" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">通讯地址：</label>
													<div class="controls">
														<textarea id="address" type="text" 	class="m-wrap medium" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">联系电话：</label>
													<div class="controls">
														<input id="tel" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">传真号码：</label>
													<div class="controls">
														<input id="fax" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">电子邮箱：</label>
													<div class="controls">
														<input id="email" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">备案号：</label>
													<div class="controls">
														<input id="crod" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">版权信息：</label>
													<div class="controls">
														<textarea id="copyright" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO标题：</label>
													<div class="controls">
														<textarea id="seo_title" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">	
													<label class="control-label">SEO关键字：</label>
													<div class="controls">
														<textarea id="seo_keyword" 	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO描述：</label>
													<div class="controls">
														<textarea id="seo_description"  	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否移动站：</label>
													<div class="controls">
														<input id="is_mobile" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否默认：</label>
													<div class="controls">
														<input id="is_default" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序数字：</label>
													<div class="controls">
														<input id="sort_id" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selChannelSite">返回</a>

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
		    menuact("03_02_04");
		});
	
	function infoset(){
		
		var channelsite=${channelsite };
		
		var id=channelsite.id;
		var	title=channelsite.title;
		var	build_path=channelsite.build_path;
		var	templet_path=channelsite.templet_path;
		var	domain=channelsite.domain;
		var	name=channelsite.name;
		var	logo=channelsite.logo;
		var	company=channelsite.company;
		var	address=channelsite.address;
		var	tel=channelsite.tel;
		var	fax=channelsite.fax;
		var	email=channelsite.email;
		var	crod=channelsite.crod;
		var	copyright=channelsite.copyright;
		var	seo_title=channelsite.seo_title;
		var	seo_keyword=channelsite.seo_keyword;
		var	seo_description=channelsite.seo_description;
		var	is_mobile=channelsite.is_mobile;
		var	is_default=channelsite.is_default;
		var	sort_id=channelsite.sort_id;


		$("#hiddenid").val(id);
		$("#title").val(title);
		$("#build_path").val(build_path);
		$("#templet_path").val(templet_path);
		$("#domain").val(domain);
		$("#name").val(name);
		$("#logo").val(logo);
		$("#company").val(company);
		$("#address").val(address);
		$("#tel").val(tel);
		$("#fax").val(fax);
		$("#email").val(email);
		$("#crod").val(crod);
		$("#copyright").val(copyright);
		$("#seo_title").val(seo_title);
		$("#seo_keyword").val(seo_keyword);
		$("#seo_description").val(seo_description);
		$("#is_mobile").val(is_mobile);
		$("#is_default").val(is_default);
		$("#sort_id").val(sort_id);

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updChannelSite",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			title:$("#title").val(),
			build_path:$("#build_path").val(),
			templet_path:$("#templet_path").val(),
			domain:$("#domain").val(),
			name:$("#name").val(),
			logo:$("#logo").val(),
			company:$("#company").val(),
			address:$("#address").val(),
			tel:$("#tel").val(),
			fax:$("#fax").val(),
			email:$("#email").val(),
			crod:$("#crod").val(),
			copyright:$("#copyright").val(),
			seo_title:$("#seo_title").val(),
			seo_keyword:$("#seo_keyword").val(),
			seo_description:$("#seo_description").val(),
			is_mobile:$("#is_mobile").val(),
			is_default:$("#is_default").val(),
			sort_id:$("#sort_id").val()

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