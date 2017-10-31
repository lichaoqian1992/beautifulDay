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
							修改文章基本信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家发布内容管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">修改文章基本信息</a></li>
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
									<span class="hidden-480">修改文章基本信息</span>
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
													<label class="control-label">副标题：</label>
													<div class="controls">
														<input id="sub_title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">货号：</label>
													<div class="controls">
														<input id="goods_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">库存数量：</label>
													<div class="controls">
														<input id="stock_quantity" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">市场价格：</label>
													<div class="controls">
														<input id="market_price" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">销售价格：</label>
													<div class="controls">
														<input id="sell_price" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">积分：</label>
													<div class="controls">
														<input id="point" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">品牌：</label>
													<div class="controls">
														<input id="brand" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">是否上架：</label>
													<div class="controls">
														<input id="is_show" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">是否外卖：</label>
													<div class="controls">
														<input id="is_distribution" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">属性描述：</label>
													<div class="controls">
														<!-- <input id="goods_describe" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="goods_describe" type="text" 	class="m-wrap large"></textarea>
													</div>
												</div>
													<div class="control-group">
													<label class="control-label">商品运费信息：</label>
													<div class="controls">
														<!-- <input id="goods_expenses" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
														<textarea id="goods_expenses" type="text" 	class="m-wrap large"></textarea>
													</div>
												</div>
												
												
												
												
												
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selArticleInfo">返回</a>

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
		    onkey(3);
		    infoset();
		    setactive("342");
		});
	
	function infoset(){
		
		var articleinfo=${articleinfo };
		var article_id     =articleinfo.article_id ;
		var sub_title      =articleinfo.sub_title ;
		var goods_no       =articleinfo.goods_no ;
		var stock_quantity =articleinfo.stock_quantity ;
		var market_price   =articleinfo.market_price ;
		var sell_price     =articleinfo.sell_price ;
		var point          =articleinfo.point ;
		var brand          =articleinfo.brand ;
		var is_show        =articleinfo.is_show ;
		var is_distribution=articleinfo.is_distribution ;
		var goods_describe =articleinfo.goods_describe ;
		var goods_expenses =articleinfo.goods_expenses ;
		
		
		$("#hiddenid").val(article_id);
		$("#sub_title").val(sub_title);
		$("#goods_no").val(goods_no);
		$("#stock_quantity").val(stock_quantity);
		$("#market_price").val(market_price);
		$("#sell_price").val(sell_price);
		$("#point").val(point);
		$("#brand").val(brand);
		$("#is_show").val(is_show);
		$("#is_distribution").val(is_distribution);
		$("#goods_describe").val(goods_describe);
		$("#goods_expenses").val(goods_expenses);


		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updArticleInfo",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			
			article_id:$("#hiddenid").val(),
			sub_title:$("#sub_title").val(),
			goods_no:$("#goods_no").val(),
			stock_quantity:$("#stock_quantity").val(),
			market_price:$("#market_price ").val(),
			sell_price:$("#sell_price ").val(),
			point:$("#point ").val(),
			brand:$("#brand ").val(),
			is_show:$("#is_show ").val(),
			is_distribution:$("#is_distribution ").val(),
			goods_describe:$("#goods_describe ").val(),
			goods_expenses:$("#goods_expenses ").val()

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