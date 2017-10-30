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
								广告位购买信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">导航信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">广告位购买信息</a></li>
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
									<a  class="btn blue" href="insAdvertBanner"><i class="icon-pencil"></i>新增广告位购买信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
					                <div class="span2">广告位名称：<input type="text" id="title" class="small m-wrap" ></div>
					                <div class="span2">广告位状态：
					                	<select class="m-wrap small" id="state">
					                		<option value="-1"></option>
											<option value="0">待确认</option>
											<option value="1">已确认</option>
											<option value="2">已支付</option>
											<option value="3">通过正常</option>
											<option value="4">已过期</option>
											<option value="5">被冻结</option>
										</select>
					                </div>
					                <div class="span2">是否删除：
					                	<select class="m-wrap small" id="is_del">
					                		<option value="-1"></option>
											<option value="0">正常</option>
											<option value="1">删除</option>
										</select>
					                </div>
					                <div class="span2"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></div>
							</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<td>对应广告位ID</td>
												<td>广告位名称</td>
												<td>开始时间</td>
												<td>结束时间</td>
												<td>发布地区</td>
												<td>广告状态</td>
												<td>状态对应提醒</td>
												<td>更新时间</td>
												<td>购买用户ID</td>
												<td>角色类型</td>
												<td>角色类型值</td>
												<td>广告位花费</td>
												<td>是否删除</td>


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
		    menuact("04_01_05");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#title").val()!=""){
		ajaxdatas+="&title="+$("#title").val();
	}
	if($("#is_del").val()!=""){
		ajaxdatas+="&is_del="+$("#is_del").val();
	}
	if($("#state").val()!=""){
		ajaxdatas+="&state="+$("#state").val();
	}
	$.ajax({
		type : "GET",
		url : "queryAdvertBanner",
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
					var	advert_id=item.advert_id;
					var	title=item.title;
					var	start_time=item.start_time;
					var	end_time=item.end_time;
					var	area=item.area;
					var	state=item.state;
					var	remark=item.remark;
					var	update_time=item.update_time;
					var	user_id=item.user_id;
					var	user_role_type=item.user_role_type;
					var	user_role_value=item.user_role_value;
					var	cust_money=item.cust_money;
					var	is_del=item.is_del;

					switch(user_role_type){
		          	case "Buyer":
		          		user_role_type = "用户";
		          		break;
		          	case "Shop":
		          		user_role_type = "商家";
		          		break;
		          	}
					switch(is_del){
		          	case 0:
		          		is_del = "正常";
		          		break;
		          	case 1:
		          		is_del = "删除";
		          		break;
		          	}
					switch(state){
		          	case 0:
		          		state = "待确认";
		          		break;
		          	case 1:
		          		state = "已确认";
		          		break;
		          	case 2:
		          		state = "已支付";
		          		break;
		          	case 3:
		          		state = "通过正常";
		          		break;
		          	case 4:
		          		state = "已过期";
		          		break;
		          	case 5:
		          		state = "被冻结";
		          		break;
		          	}
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+advert_id+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+start_time+"</td>"+
					 "<td>"+end_time+"</td>"+
					 "<td>"+area+"</td>"+
					 "<td>"+state+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+cust_money+"</td>"+
					 "<td>"+is_del+"</td>"+
					 "<td>"+"<a href='readAdvertBanner?id="+id+"'>修改</a><br><a href='delAdvertBanner?id="+id+"'>删除</a>"+"</td>"+
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