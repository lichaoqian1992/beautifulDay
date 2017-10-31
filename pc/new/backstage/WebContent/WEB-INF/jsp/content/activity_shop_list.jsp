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
									商城活动信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商城活动信息</a></li>
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
									<a  class="btn blue" href="insActShop"><i class="icon-pencil"></i>新增商城活动信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row-fluid ">
			                <div class="span2">活动类型：
								<select class="m-wrap small" id="add_type">
			                		<option value="-1">全部</option>
									<option value="0">商品</option>
									<option value="1">商家</option>
								</select>
							</div>
			                <div class="span2">折扣类型：
			                	<select class="m-wrap small" id="act_type">
			                		<option value="-1">全部</option>
									<option value="0">自定价</option>
									<option value="1">同意折扣</option>
									<option value="2">公司补贴</option>
								</select>
			                </div>
			                <div class="span2">是否首单：
			                	<select class="m-wrap small" id="is_first">
			                		<option value="-1">全部</option>
									<option value="0">不是</option>
									<option value="1">首单</option>
								</select>
			                </div>
			                <div class="span2">活动状态：
			                	<select class="m-wrap small" id="status">
			                		<option value="-1">全部</option>
									<option value="0">禁用</option>
									<option value="1">正常</option>
									<option value="2">过期</option>
								</select>
			                </div>
			                <div class="span2"><a  class="btn btn-primary search_sub"
			                            onclick="queryinfo(1)">搜索</a></div>
			                 
			                
			              </div>
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												
												<td>活动申请时间</td>
												<td>申请结束时间</td>
												<td>活动开始时间</td>
												<td>活动结束时间</td>
												<td>活动有效地区</td>
												<td>活动业务类型</td>
												<td>活动加入类型</td>
												<td>活动名称</td>
												<td>活动图片</td>
												<td>活动规则简介</td>
												<td>折扣类型</td>
												<td>折扣/补贴值</td>
												<td>是否首单</td>
												<td>活动状态</td>
												<td>活动添加时间</td>

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
		    menuact("04_02_05");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	if($("#act_type").val()!=""){
	    ajaxdatas+="&act_type="+$("#act_type").val();
	  }
	if($("#status").val()!=""){
	    ajaxdatas+="&status="+$("#status").val();
	  }
	if($("#is_first").val()!=""){
	    ajaxdatas+="&is_first="+$("#is_first").val();
	  }
	if($("#add_type").val()!=""){
	    ajaxdatas+="&add_type="+$("#add_type").val();
	  }
	
	$.ajax({
		type : "GET",
		url : "queryActShop",
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
					var apply_begin =item.apply_begin;
					var apply_end =item.apply_end;
					var begin_time =item.begin_time;
					var end_time =item.end_time;
					var act_area =item.act_area;
					var channel_id =item.channel_id;
					var add_type =item.add_type;
					var title =item.title;
					var pics =item.pics;
					var content =item.content;
					var act_type =item.act_type;
					var discount =item.discount;
					var is_first =item.is_first;
					var status =item.status;
					var add_time =item.add_time;
					switch(act_type){
		          	case 0:
		          		act_type = "自定价";
		          		break;
		          	case 1:
		          		act_type = "统一折扣";
		          		break;
		          	case 2:
		          		act_type = "公司补贴";
		          		break;
		          }
					switch(status){
			          	case 0:
			          		status = "禁用";
			          		break;
			          	case 1:
			          		status = "正常";
			          		break;
			          	case 2:
			          		status = "过期";
			          		break;
			          }
					switch(is_first){
			          	case 0:
			          		is_first = "不是";
			          		break;
			          	case 1:
			          		is_first = "首单";
			          		break;
			          }
					switch(add_type){
		          	case 0:
		          		add_type = "商品";
		          		break;
		          	case 1:
		          		add_type = "商家";
		          		break;
		          }
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+apply_begin+"</td>"+
					 "<td>"+apply_end+"</td>"+
					 "<td>"+begin_time+"</td>"+
					 "<td>"+end_time+"</td>"+
					 "<td>"+act_area+"</td>"+
					 "<td>"+channel_id+"</td>"+
					 "<td>"+add_type+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td><img style='width:100px' src='"+pics+"'</td>"+
					 "<td>"+content+"</td>"+
					 "<td>"+act_type+"</td>"+
					 "<td>"+discount+"</td>"+
					 "<td>"+is_first+"</td>"+
					 "<td>"+status+"</td>"+
					 "<td>"+add_time+"</td>"+
					 
					 "<td>"+"<a href='readActShop?id="+id+"'>修改</a><a href='delActShop?id="+id+"'>删除</a>"+"</td>"+
					 "</tr>";
					
					tablehtml+=rowhtml;
							 
				})
				$("#tablebody").html(tablehtml);
				
				
				deviderset(totalcount,pagecount,pageindex);
			}else{
				alert("暂无数据!");
			}
			
			
			
		}
	
	});
	
	
	
	
		
	}
	</script>

</body>

</html>