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
							订单详细内容信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单内容信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selOrderGoods">订单详细内容信息</a></li>
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
									<span class="hidden-480">修改订单详细内容信息</span>
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
											
											<form action="updOrderGoods" class="form-horizontal" method="POST" enctype="multipart/form-data">
												<input type="hidden" id="hiddenid" name="id" value="">
												<div class="control-group">
													<label class="control-label">对应内容ID：</label>
													<div class="controls">
														<input id="article_id" name="article_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">对应订单ID：</label>
													<div class="controls">
														<input id="order_id" name="order_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">对应商品ID：</label>
													<div class="controls">
														<input id="goods_id" name="goods_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品货号：</label>
													<div class="controls">
														<input id="goods_no" name="goods_no" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品标题：</label>
													<div class="controls">
														<input id="goods_title" name="goods_title" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">内容图片：</label>
													<div class="controls">
														<img id="img_url" name="img_url"  style="height:100px;">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden"  name="icon" id="icon"/>
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">规格描述：</label>
													<div class="controls">
														<input id="spec_text" name="spec_text" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品价格：</label>
													<div class="controls">
														<input id="goods_price" name="goods_price" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">实际销售价：</label>
													<div class="controls">
														<input id="real_price" name="real_price" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">订购数量：</label>
													<div class="controls">
														<input id="quantity" name="quantity" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">积分数量：</label>
													<div class="controls">
														<input id="point" name="point" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品状态：</label>
													<div class="controls">
														<select name="state" id="state">
														  <option value="1">正常</option>
														  <option value="2">退款中</option>
														  <option value="3">退款完成</option>
														  <option value="4">退款失败</option>
														  <option value="5">退款待确认</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否可退货：</label>
													<div class="controls">
														<select name="is_back" id="is_back">
														  <option value="0">不允许</option>
														  <option value="1">允许</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">对应退单ID：</label>
													<div class="controls">
														<input name="back_order_id" type="text" 	class="m-wrap small" id="back_order_id"/><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">退货数量：</label>
													<div class="controls">
														<input name="back_quantity" type="text" 	class="m-wrap small"  id="back_quantity"/><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">商品评价：</label>
													<div class="controls">
														<select name="goods_comment" id="goods_comment">
														  <option value="1">好评</option>
														  <option value="2">中评</option>
														  <option value="3">差评</option>
														  <option value="0">暂无</option>
														</select>
													</div>
												</div>
												
												
												<div class="form-actions">

													<input type="submit" class="btn blue" id="subbutton" value="保存"/>

													<a type="button" class="btn" href="selOrderGoods">返回</a>

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
		    menuact("09_02_01");

		});
	function uploadpicdiv(){
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
	function infoset(){
		
		var ordergoodsinfo=${ordergoodsinfo };
		
		var id=ordergoodsinfo.id;
		var article_id=ordergoodsinfo.article_id;
		var order_id=ordergoodsinfo.order_id;
		var goods_id=ordergoodsinfo.goods_id;
		var goods_no=ordergoodsinfo.goods_no;
		var goods_title=ordergoodsinfo.goods_title;
		var img_url=ordergoodsinfo.img_url;
		var spec_text=ordergoodsinfo.spec_text;
		var goods_price=ordergoodsinfo.goods_price;
		var real_price=ordergoodsinfo.real_price;
		var quantity=ordergoodsinfo.quantity;
		var point=ordergoodsinfo.point;
		var state=ordergoodsinfo.state;
		var is_back=ordergoodsinfo.is_back;
		var back_order_id=ordergoodsinfo.back_order_id;
		var back_quantity=ordergoodsinfo.back_quantity;
		var goods_comment=ordergoodsinfo.goods_comment;

		
		$("#hiddenid").val(id);
		$("#article_id").val(article_id);
		$("#order_id").val(order_id);
		$("#goods_id").val(goods_id);
		$("#goods_no").val(goods_no);
		$("#goods_title").val(goods_title);
		$("#img_url").attr('src',img_url);
		$("#spec_text").val(spec_text);
		$("#goods_price").val(goods_price);
		$("#real_price").val(real_price);
		$("#quantity").val(quantity);
		$("#point").val(point);
		$("#state").val(state);
		$("#is_back").val(is_back);
		$("#back_order_id").val(back_order_id);
		$("#back_quantity").val(back_quantity);
		$("#goods_comment").val(goods_comment);
		$("#icon").val(img_url);

	};
	
	
	/*$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updOrderGoods",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			article_id:$("#article_id").val(),
			order_id:$("#order_id").val(),
			goods_id:$("#goods_id").val(),
			goods_no:$("#goods_no").val(),
			goods_title:$("#goods_title").val(),
			img_url:$("#img_url").val(),
			spec_text:$("#spec_text").val(),
			goods_price:$("#goods_price").val(),
			real_price:$("#real_price").val(),
			quantity:$("#quantity").val(),
			point:$("#point").val(),
			state:$("#state").val(),
			is_back:$("#is_back").val(),
			back_order_id:$("#back_order_id").val(),
			back_quantity:$("#back_quantity").val(),
			goods_comment:$("#goods_comment").val()
				},
		success : function(data) {
			if(data.status==0){
				location.reload();
			}else{
				alert("修改失败");
			}
		}
				
		})
	});*/
	
	

	</script>

</body>

</html>