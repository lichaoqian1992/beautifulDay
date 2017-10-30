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
										订单详细内容信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单内容信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单详细内容信息</a></li>
						</ul>
					</div>
				</div>

				<div class="row-fluid margin-bottom-20">

				</div>

				<div class="row-fluid">

					<div class="span12">

						<div class="portlet box grey">
							<div class="portlet-title">
								<div class="caption"> </div>
								<div class="actions">
									<a  class="btn blue" href="insOrderGoods"><i class="icon-pencil"></i>新增订单详细内容信息</a>
								</div>
							</div>
							
							<div class="portlet-body">


								<div class=row_fluid>
									<ul>
										<li>商品状态:
											<select id="state"  style="width:102px;">
												<option value="">请选择</option>
												<option value="1">正常</option>
												<option value="2">退款中</option>
												<option value="3">退款完成</option>
												<option value="4">退款失败</option>
												<option value="5">退款待确认</option>
											</select></li>
										<li>商品评价:
											<select id="goods_comment"  style="width:102px;">
												<option value="">请选择</option>
												<option value="1">好评</option>
												<option value="2">中评</option>
												<option value="3">差评</option>
											</select></li>
										<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索
										</a></li>
									</ul>
								</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>对应内容ID</th>
												<th>对应订单ID</th>
												<th>对应商品ID</th>
												<th>商品货号</th>
												<th>商品标题</th>
												<th>内容图片</th>
												<th>规格描述</th>
												<th>商品价格</th>
												<th>实际销售价</th>
												<th>订购数量</th>
												<th>积分数量</th>
												<th>商品状态</th>
												<th>是否可退货</th>
												<th>对应退单ID</th>
												<th>退货数量</th>
												<th>商品评价</th>
												<th>操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">
										</tbody>
										</table>
									<div class="row-fluid">
										<div class="span6">
											<div class="dataTables_info" id="dividersummery"></div>
										</div>
										<div class="span6">
											<div class="dataTables_paginate paging_bootstrap pagination" id="devider">
												
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
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
		    
		    queryinfo(1);
		    menuact("09_02_01");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}




		var ajaxdatas ="index="+index;


		if($("#state").val()!=""){
			ajaxdatas+="&state="+$("#state").val();
		}
		if($("#goods_comment").val()!=""){
			ajaxdatas+="&goods_comment="+$("#goods_comment").val();
		}


		$.ajax({
		type : "GET",
		url : "queryOrderGoods",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : ajaxdatas,
		success : function(freshdata) {
			
			if(freshdata.status ==0){
				var page =freshdata.result;
				var pageindex =page.index;
				var totalcount =page.totalCount;
				var pagecount =page.pageCount;
				var data =page.dataList;
				
				
				var tablehtml="";
				$.each(data,function(i,item){
					
					var no =Number(i+1);
					console.log(item);
					var id=item.id;
					var article_id=item.article_id;
					var order_id=item.order_id;
					var goods_id=item.goods_id;
					var goods_no=item.goods_no;
					var goods_title=item.goods_title;
					var img_url=item.img_url;
					var spec_text=item.spec_text;
					var goods_price=item.goods_price;
					var real_price=item.real_price;
					var quantity=item.quantity;
					var point=item.point;
					var state=item.state;
					var is_back=item.is_back;
					var back_order_id=item.back_order_id;
					var back_quantity=item.back_quantity;
					var goods_comment=item.goods_comment;
					
					is_back == 0 ? is_back = "不允许" : is_back = "允许";
					var stateStr = "";
					var good_commentStr = "";
								
					switch (state) {
										case 1:
											stateStr = "正常";
											break;
										case 2:
											stateStr = "退款中";
											break;
										case 3:
											stateStr = "退款完成";
											break;
										case 4:
											stateStr = "退款失败";
											break;
										case 5:
											stateStr = "退款待确认";
											break;
										}

							
					switch (goods_comment) {
										case 1:
											goods_commentStr = "好评";
											break;
										case 2:
											goods_commentStr = "中评";
											break;
										case 3:
											goods_commentStr = "差评";
											break;
										default:
											goods_commentStr = "暂无";
										}

							var rowhtml = "<tr>" + "<td>" + no + "</td>"
									+ "<td>" + article_id + "</td>" + "<td>"
									+ order_id + "</td>" + "<td>" + goods_id
									+ "</td>" + "<td>" + goods_no + "</td>"
									+ "<td>" + goods_title + "</td>" +  "<td><img style='width:100px' src='"+img_url+"'</td>" + "<td>" + spec_text
									+ "</td>" + "<td>" + goods_price + "</td>"
									+ "<td>" + real_price + "</td>" + "<td>"
									+ quantity + "</td>" + "<td>" + point
									+ "</td>" + "<td>" + stateStr + "</td>"
									+ "<td>" + is_back + "</td>" + "<td>"
									+ back_order_id + "</td>" + "<td>"
									+ back_quantity + "</td>" + "<td>"
									+ goods_commentStr + "</td>" + "<td>"
									+ "<a href='readOrderGoods?id=" + id
									+ "'>修改</a><a href='delOrderGoods?id=" + id
									+ "'>删除</a>" + "</td>" + "</tr>";

							tablehtml += rowhtml;

						})
						$("#tablebody").html(tablehtml);

						deviderset(totalcount, pagecount, pageindex);
					} else {
						alert("暂无数据！");
					}

				}

			});

		}

		function deviderset(totalcount, pagecount, pageindex) {

			var summery = "共" + totalcount + "条数据--共" + pagecount + "页";
			$("#dividersummery").html(summery);

			var deviderhtml = "";
			if (pageindex == 1) {
				deviderhtml += "<ul><li  class='prev ' style='display:none'><a  onclick='queryinfo("
						+ Number(pageindex - 1)
						+ ")'>← <span class='hidden-480'>Prev</span></a></li>";
			} else {
				deviderhtml += "<ul><li  id='firstdiv' class='prev '><a  onclick='queryinfo("
						+ Number(pageindex - 1)
						+ ")'>← <span class='hidden-480'>Prev</span></a></li>";
			}

			if (pagecount < 6) {

				for (var i = 1; i < pageindex; i++) {

					deviderhtml += "<li ><a onclick='queryinfo(" + i + ")'>"
							+ i + "</a></li>";

				}

			} else {

				if (pageindex < 3) {
					for (var j = 1; j < 6; j++) {

						deviderhtml += "<li ><a onclick='queryinfo(" + j
								+ ")'>" + j + "</a></li>";

					}

				} else if (pageindex <= pagecount - 2) {

					var m = pageindex - 2;
					for (var k = 0; k < 5; k++) {

						deviderhtml += "<li ><a onclick='queryinfo(" + m
								+ ")'>" + m + "</a></li>";
						m++;
					}

				} else if (pageindex > pagecount - 2) {
					var n = pageindex - 4;
					for (var q = 0; q < 5; q++) {
						deviderhtml += "<li ><a onclick='queryinfo(" + n
								+ ")'>" + n + "</a></li>";
						n++;
					}
				}

			}

			if (pageindex == pagecount) {
				deviderhtml += "<li id='lastdiv ' style='display:none'><a  onclick='queryinfo("
						+ Number(pageindex + 1)
						+ ")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
			} else {
				deviderhtml += "<li id='lastdiv '><a  onclick='queryinfo("
						+ Number(pageindex + 1)
						+ ")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
			}

			$("#devider").html(deviderhtml);
		}
	</script>

</body>

</html>