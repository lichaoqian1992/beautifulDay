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
								用户发布内容模板		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">模板配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selMbArticle">用户发布内容模板</a></li>
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
									<span class="hidden-480">修改用户发布内容模板</span>
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
											
											<form action="updMbArticle" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
												<input type="hidden" id="hiddenid" name="id" value="">
												<div class="control-group">
													<label class="control-label">对应分类：</label>
													<div class="controls">
														<input id="category_id" type="text" name="category_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">关键词：</label>
													<div class="controls">
														<input id="keywords" type="text" name="keywords"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">标题：</label>
													<div class="controls">
														<input id="title" type="text" name="title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">图片地址：</label>
													<div class="controls">
														<img id="img_url" name="img_url"  style="height:100px;"> 
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgUrl" name="imgUrl" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO标题：</label>
													<div class="controls">
														<input id="seo_title" type="text" name="seo_title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO关健字：</label>
													<div class="controls">
														<input id="seo_keywords" type="text" name="seo_keywords"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO描述：</label>
													<div class="controls">
														<input id="seo_description" type="text"  name="seo_description"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">TAG标签：</label>
													<div class="controls">
														<input id="tags" type="text" name="tags"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">内容摘要：</label>
													<div class="controls">
														<input id="zhaiyao" type="text"  name="zhaiyao"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">副标题：</label>
													<div class="controls">
														<input id="sub_title" type="text" name="sub_title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">货号：</label>
													<div class="controls">
														<input id="goods_no" type="text" name="goods_no"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">品牌：</label>
													<div class="controls">
														<input id="brand" type="text" name="brand"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">规格描述：</label>
													<div class="controls">
														<input id="goods_describe" type="text" name="goods_describe"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">附加内容：</label>
													<div class="controls">
														<input id="matter" type="text" name="matter"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">详细内容：</label>
													<div class="controls">
														<input id="content" type="text" name="content"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">详细内容_WAP：</label>
													<div class="controls">
														<input id="content_wap" type="text" name="content_wap"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否生效：</label>
													<div class="controls">
														<select id="status" class="m-wrap small" name="status">
															<option value="0">未生效</option>
															<option value="1">已生效</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">添加时间：</label>
													<div class="controls">
														<input id="add_time" type="text" name="add_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">更新时间：</label>
													<div class="controls">
														<input id="update_time" type="text"  name="update_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">更新说明：</label>
													<div class="controls">
														<input id="remark" type="text" name="remark"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selMbArticle">返回</a>

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
		    menuact("03_03_02");
		});
	
	function infoset(){
		
		var mbarticle=${mbarticle };
		
		var id=mbarticle.id;
		
		var	category_id=mbarticle.category_id;
		var	keywords=mbarticle.keywords;
		var	title=mbarticle.title;
		var	img_url=mbarticle.img_url;
		var	seo_title=mbarticle.seo_title;
		var	seo_keywords=mbarticle.seo_keywords;
		var	seo_description=mbarticle.seo_description;
		var	tags=mbarticle.tags;
		var	zhaiyao=mbarticle.zhaiyao;
		var	sub_title=mbarticle.sub_title;
		var	goods_no=mbarticle.goods_no;
		var	brand=mbarticle.brand;
		var	goods_describe=mbarticle.goods_describe;
		var	matter=mbarticle.matter;
		var	content=mbarticle.content;
		var	content_wap=mbarticle.content_wap;
		var	status=mbarticle.status;
		var	add_time=mbarticle.add_time;
		var	update_time=mbarticle.update_time;
		var	remark=mbarticle.remark;


	 	$("#hiddenid").val(id);
	 	$("#category_id").val(category_id);
	 	$("#keywords").val(keywords);
	 	$("#title").val(title);
	 	$("#img_url").attr('src',img_url);
	 	$("#seo_title").val(seo_title);
	 	$("#seo_keywords").val(seo_keywords);
	 	$("#seo_description").val(seo_description);
	 	$("#tags").val(tags);
	 	$("#zhaiyao").val(zhaiyao);
	 	$("#sub_title").val(sub_title);
	 	$("#goods_no").val(goods_no);
	 	$("#brand").val(brand);
	 	$("#goods_describe").val(goods_describe);
	 	$("#matter").val(matter);
	 	$("#content").val(content);
	 	$("#content_wap").val(content_wap);
	 	$("#status").val(status);
	 	$("#add_time").val(add_time);
	 	$("#update_time").val(update_time);
	 	$("#remark").val(remark);
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