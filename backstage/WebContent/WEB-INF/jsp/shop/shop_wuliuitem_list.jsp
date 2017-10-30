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
								商家物流模板明细		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家物流模板明细</a></li>
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
									<a  class="btn blue" href="insShopWuliumubanItem"><i class="icon-pencil"></i>新增商家物流模板明细</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
					                <li>商家ID：<input type="text" id="shop_id" class="small m-wrap" ></li>
					                <li>模板ID：<input type="text" id=mb_id class="small m-wrap" ></li>
					                <li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
				                </ul>   
							</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<td>商家ID</td>
												<td>模板ID</td>
												<td>配送区域</td>
												<td>计算方式</td>
												<td>首重</td>
												<td>运费</td>
												<td>每增</td>
												<td>加收金额</td>
												<td>是否免运费</td>
												<td>免运费金额</td>
												<td>是否全国</td>
												<td>添加时间</td>
												<td>更新时间</td>
												<td>备注</td>


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
		    menuact("02_02_03");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#shop_id").val()!=""){
		ajaxdatas+="&shop_id="+$("#shop_id").val();
	}
	if($("#mb_id").val()!=""){
		ajaxdatas+="&mb_id="+$("#mb_id").val();
	}
	 
	
	$.ajax({
		type : "GET",
		url : "queryShopWuliumubanItem",
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
					var	shop_id=item.shop_id;
					var	mb_id=item.mb_id;
					var	send_area=item.send_area;
					var	type=item.type;
					var	start=item.start;
					var	start_price=item.start_price;
					var	augment=item.augment;
					var	augment_price=item.augment_price;
					var	is_free=item.is_free;
					var	free_price=item.free_price;
					var	is_all=item.is_all;
					var	add_time=item.add_time;
					var	update_time=item.update_time;
					var	remark=item.remark;


					var One = "";
					
					switch(is_free){
						case 0:
							One = "运费";
						break;
						
						case 1:
							One = "免运费";
						break;
						
						default:
							statusStr = "";
						break;
					}
					
					var Tow = "";
					
					switch(is_free){
						case 0:
							Tow = "不是全国";
						break;
						
						case 1:
							Tow = "全国";
						break;
						
						default:
							statusStr = "";
						break;
					}
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+shop_id+"</td>"+
					 "<td>"+mb_id+"</td>"+
					 "<td><textarea>"+send_area+"</textarea></td>"+
					 "<td>"+type+"</td>"+
					 "<td>"+start+"</td>"+
					 "<td>"+start_price+"</td>"+
					 "<td>"+augment+"</td>"+
					 "<td>"+augment_price+"</td>"+
					 "<td>"+One+"</td>"+
					 "<td>"+free_price+"</td>"+
					 "<td>"+Tow+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+remark+"</td>"+



					 "<td>"+"<a href='readShopWuliumubanItem?id="+id+"'>修改</a><br><a href='delShopWuliumubanItem?id="+id+"'>删除</a>"+"</td>"+
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