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
									基础内容筛选信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">发布信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selArticleScreen">基础内容筛选信息</a></li>
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
									<span class="hidden-480">修改基础内容筛选信息</span>
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
													<label class="control-label">发货地址：</label>
													<div class="controls">
														<input id="shop_send_area" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品ID：</label>
													<div class="controls">
														<input id="article_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类别ID：</label>
													<div class="controls">
														<input id="article_category_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类别名：</label>
													<div class="controls">
														<input id="article_category_name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">自定义类别ID：</label>
													<div class="controls">
														<input id="article_user_category_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">自定义类别名：</label>
													<div class="controls">
														<input id="article_user_category_name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">品牌ID：</label>
													<div class="controls">
														<input id="article_brand_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">品牌名：</label>
													<div class="controls">
														<input id="article_brand_name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">标题：</label>
													<div class="controls">
														<input id="article_title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">货号：</label>
													<div class="controls">
														<input id="article_goods_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">图片地址：</label>
													<div class="controls">
														<input id="article_img_url" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">TAG标签：</label>
													<div class="controls">
														<input id="article_tags" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">创建时间：</label>
													<div class="controls">
														<input id="article_add_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">市场价格：</label>
													<div class="controls">
														<input id="article_market_price" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">销售价格：</label>
													<div class="controls">
														<input id="article_sell_price" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">成功订单数：</label>
													<div class="controls">
														<input id="article_order_times" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">整体评分：</label>
													<div class="controls">
														<input id="article_review_score" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">商品活动：</label>
													<div class="controls">
														<input id="article_activity" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">商品包邮区域：</label>
													<div class="controls">
														<input id="article_freeshipping_area" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">商品配送区域：</label>
													<div class="controls">
														<input id="article_distribution_area" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">修改时间：</label>
													<div class="controls">
														<input id="update_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
											
												
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selArticleScreen">返回</a>

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
		    menuact("04_03_15");
		});
	
	function infoset(){
		
		var articlescreen=${articlescreen };
		
		var id=articlescreen.id;
		var shop_id=articlescreen.shop_id;
		var shop_send_area=articlescreen.shop_send_area;
		var article_id=articlescreen.article_id;
		var article_category_id=articlescreen.article_category_id;
		var article_category_name=articlescreen.article_category_name;
		var article_user_category_id=articlescreen.article_user_category_id;
		var article_user_category_name=articlescreen.article_user_category_name;
		var article_brand_id=articlescreen.article_brand_id;
		var article_brand_name=articlescreen.article_brand_name;
		var article_title=articlescreen.article_title;
		var article_goods_no=articlescreen.article_goods_no;
		var article_img_url=articlescreen.article_img_url;
		var article_tags=articlescreen.article_tags;
		var article_add_time=articlescreen.article_add_time;
		var article_market_price=articlescreen.article_market_price;
		var article_sell_price=articlescreen.article_sell_price;
		var article_order_times=articlescreen.article_order_times;
		var article_review_score=articlescreen.article_review_score;
		var article_activity=articlescreen.article_activity;
		var article_freeshipping_area=articlescreen.article_freeshipping_area;
		var article_distribution_area=articlescreen.article_distribution_area;
		var update_time=articlescreen.update_time;
		

		$("#hiddenid").val(id);
		$("#shop_id").val(shop_id);
		$("#shop_send_area").val(shop_send_area);
		$("#article_id").val(article_id);
		$("#article_category_id").val(article_category_id);
		$("#article_category_name").val(article_category_name);
		$("#article_user_category_id").val(article_user_category_id);
		$("#article_user_category_name").val(article_user_category_name);
		$("#article_brand_id").val(article_brand_id);
		$("#article_brand_name").val(article_brand_name);
		$("#article_title").val(article_title);
		$("#article_goods_no").val(article_goods_no);
		$("#article_img_url").val(article_img_url);
		$("#article_tags").val(article_tags);
		$("#article_add_time").val(article_add_time);
		$("#article_market_price").val(article_market_price);
		$("#article_sell_price").val(article_sell_price);
		$("#article_order_times").val(article_order_times);
		$("#article_review_score").val(article_review_score);
		$("#article_activity").val(article_activity);
		$("#article_freeshipping_area").val(article_freeshipping_area);
		$("#article_distribution_area").val(article_distribution_area);
		$("#update_time").val(update_time);



	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updArticleScreen",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			shop_id:	$("#shop_id").val(),
			shop_send_area:	$("#shop_send_area").val(),
			article_id:	$("#article_id").val(),
			article_category_id:	$("#article_category_id").val(),
			article_category_name:	$("#article_category_name").val(),
			article_user_category_id:	$("#article_user_category_id").val(),
			article_user_category_name:	$("#article_user_category_name").val(),
			article_brand_id:	$("#article_brand_id").val(),
			article_brand_name:	$("#article_brand_name").val(),
			article_title:	$("#article_title").val(),
			article_goods_no:	$("#article_goods_no").val(),
			article_img_url:	$("#article_img_url").val(),
			article_tags:	$("#article_tags").val(),
			article_add_time:	$("#article_add_time").val(),
			article_market_price:	$("#article_market_price").val(),
			article_sell_price:	$("#article_sell_price").val(),
			article_order_times:	$("#article_order_times").val(),
			article_review_score:	$("#article_review_score").val(),
			article_activity:	$("#article_activity").val(),
			article_freeshipping_area:	$("#article_freeshipping_area").val(),
			article_distribution_area:	$("#article_distribution_area").val(),
			update_time:	$("#update_time").val()
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