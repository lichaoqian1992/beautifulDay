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
									内容筛选信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商布内容管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">内容筛选信息</a></li>
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
									<a  class="btn blue" href="insArticleScreen"><i class="icon-pencil"></i>新增内容帅选信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
								<div class="row_fluid ">
									<ul>
								    <li>商家ID：<input type="text" id="shop_id" class="small m-wrap" ></li>
								    <li>商品ID：<input type="text" id="article_id" class="small m-wrap" ></li>
								    <li>标题：<input type="text" id="article_title" class="small m-wrap" ></li>
								
								    <li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
								  </ul>
									
								</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<td>商家ID</td>
												<td>发货地址</td>
												<td>商品ID</td>
												<td>类别ID</td>
												<td>类别名</td>
												<td>自定义类别ID</td>
												<td>自定义类别名</td>
												<td>品牌ID</td>
												<td>品牌名</td>
												<td>标题</td>
												<td>货号</td>
												<!-- <td>图片地址</td> -->
												<td>TAG标签</td>
												<td>创建时间</td>
												<td>市场价格</td>
												<td>销售价格</td>
												<td>成功订单数</td>
												<td>整体评分</td>
												<td>商品活动</td>
												<!-- <td>商品包邮区域</td> -->
												<!-- <td>商品配送区域</td> -->
												<td>修改时间</td>


												<th>操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">
										</tbody>
										</table>
									<jsp:include page="/WEB-INF/jsp/pager.html" flush="true"/>  

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
		    onkey(3);
		    queryinfo(1);
		    setactive("348");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
 	if($("#shop_id").val()!=""){
		ajaxdatas+="&shop_id="+$("#shop_id").val();
	}
 	if($("#article_id").val()!=""){
		ajaxdatas+="&article_id="+$("#article_id").val();
	}
 	if($("#article_title").val()!=""){
		ajaxdatas+="&article_title="+$("#article_title").val();
	}
	
	 
	$.ajax({
		type : "GET",
		url : "queryArticleScreen",
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
					var id=item.id;
					var shop_id =item.shop_id;
					var shop_send_area =item.shop_send_area;
					var article_id =item.article_id;
					var article_category_id =item.article_category_id;
					var article_category_name =item.article_category_name;
					var article_user_category_id =item.article_user_category_id;
					var article_user_category_name =item.article_user_category_name;
					var article_brand_id =item.article_brand_id;
					var article_brand_name =item.article_brand_name;
					var article_title =item.article_title;
					var article_goods_no =item.article_goods_no;
					var article_img_url =item.article_img_url;
					var article_tags =item.article_tags;
					var article_add_time =item.article_add_time;
					var article_market_price =item.article_market_price;
					var article_sell_price =item.article_sell_price;
					var article_order_times =item.article_order_times;
					var article_review_score =item.article_review_score;
					var article_activity =item.article_activity;
					var article_freeshipping_area =item.article_freeshipping_area;
					var article_distribution_area =item.article_distribution_area;
					var update_time =item.update_time;
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
						"<td>"+shop_id                   +"</td>"+
						"<td>"+shop_send_area            +"</td>"+
						"<td>"+article_id                +"</td>"+
						"<td>"+article_category_id       +"</td>"+
						"<td>"+article_category_name     +"</td>"+
						"<td>"+article_user_category_id  +"</td>"+
						"<td>"+article_user_category_name+"</td>"+
						"<td>"+article_brand_id          +"</td>"+
						"<td>"+article_brand_name        +"</td>"+
						"<td>"+article_title             +"</td>"+
						"<td>"+article_goods_no          +"</td>"+
						/* "<td>"+article_img_url           +"</td>"+ */
						"<td>"+article_tags              +"</td>"+
						"<td>"+article_add_time          +"</td>"+
						"<td>"+article_market_price      +"</td>"+
						"<td>"+article_sell_price        +"</td>"+
						"<td>"+article_order_times       +"</td>"+
						"<td>"+article_review_score      +"</td>"+
						"<td>"+article_activity          +"</td>"+
						/* "<td>"+article_freeshipping_area +"</td>"+ */
						/* "<td>"+article_distribution_area +"</td>"+ */
						"<td>"+update_time               +"</td>"+
					 
					 "<td>"+"<a href='readArticleScreen?id="+id+"'>修改</a><a href='delArticleScreen?id="+id+"'>删除</a>"+"</td>"+
					 "</tr>";
					
					tablehtml+=rowhtml;
							 
				})
				$("#tablebody").html(tablehtml);
				
				
				deviderset(totalcount,pagecount,pageindex);
			}else{
				alert("暂无数据！");
			}
			
			
			
		}
	
	});
	
	
	
	
		
	}
	
	
	</script>

</body>

</html>