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
									内容基本描述	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">发布信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">内容基本描述</a></li>
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
									<a  class="btn blue" href="insArticleInfo"><i class="icon-pencil"></i>新增内容基本描述</a>
								</div>
							</div>
							<div class="portlet-body">
							<div class="row_fluid ">
							<ul>
								<li>副标题：<input type="text" id="sub_title" class="small m-wrap" ></li>
			                <li style="width:300px;">具体内容：
			                	<select class="small m-wrap" id="is_show">
			                		<option value="">全部</option>
									<option value="0">下架</option>
									<option value="1">上架</option>
								</select>
			                </li>
			                <li style="width:300px;">是否外卖：
			                	<select class="m-wrap small" id="is_distribution">
			                		<option value="">全部</option>
									<option value="0">不支持</option>
									<option value="1">支持</option>
								</select>
			                </li>
			                <li><a  class="btn btn-primary search_sub"
			                            onclick="queryinfo(1)">搜索</a></li>
							</ul>
			              </div>
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												
												<td>文章编号</td>
												<td>副标题</td>
												<td>货号</td>
												<td>库存数量</td>
												<td>市场价格</td>
												<td>销售价格</td>
												<td>积分</td>
												<td>品牌</td>
												<td>是否上架</td>
												<td>是否外卖</td>
												<!-- <td>属性描述</td>
												<td>商品运费信息</td> -->

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
		    queryinfo(1);
		    menuact("04_03_01");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	ajaxdatas ="index="+index;
	
	if($("#sub_title").val()!=""){
		ajaxdatas+="&sub_title="+$("#sub_title").val();
	}
	if($("#is_show").val()!=""){
		ajaxdatas+="&is_show="+$("#is_show").val();
	}
	if($("#is_distribution").val()!=""){
		ajaxdatas+="&is_distribution="+$("#is_distribution").val();
	}
	$.ajax({
		type : "GET",
		url : "queryArticleInfo",
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
					var article_id =item.article_id;
					var sub_title=item.sub_title;
					var goods_no=item.goods_no;
					var stock_quantity=item.stock_quantity;
					var market_price=item.market_price;
					var sell_price=item.sell_price;
					var point=item.point;
					var brand=item.brand;
					var is_show=item.is_show;
					var is_distribution=item.is_distribution;
					var goods_describe=item.goods_describe;
					var goods_expenses=item.goods_expenses;
					is_show == 0 ? is_show = "下架" : is_show = "上架";
					is_distribution == 1 ? is_distribution = "支持" : is_distribution = "不支持";
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+article_id+"</td>"+
					 "<td>"+sub_title+"</td>"+
					 "<td>"+goods_no+"</td>"+
					 "<td>"+stock_quantity+"</td>"+
					 "<td>"+market_price+"</td>"+
					 "<td>"+sell_price+"</td>"+
					 "<td>"+point+"</td>"+
					 "<td>"+brand+"</td>"+
					 "<td>"+is_show+"</td>"+
					 "<td>"+is_distribution+"</td>"+
					/*  "<td>"+goods_describe+"</td>"+
					 "<td>"+goods_expenses+"</td>"+ */
					 
					 "<td>"+"<a href='readArticleInfo?id="+article_id+"'>修改</a>&nbsp;<a href='delArticleInfo?id="+article_id+"'>删除</a>"+"</td>"+
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