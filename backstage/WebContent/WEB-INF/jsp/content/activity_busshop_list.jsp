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
									商家活动信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家活动信息</a></li>
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
									<a  class="btn blue" href="insActBusShop"><i class="icon-pencil"></i>新增商家活动信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
								<li class="span2">用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
								<li class="span2">活动类型：
				                	<select class="m-wrap small" id="act_type">
				                		<option value="">全部</option>
										<option value="zk">折扣</option>
										<option value="mj">满减</option>
										<option value="lj">立减</option>
									</select>
				                </li>
				                
				                <li class="span2">活动状态：
				                	<select class="m-wrap small" id="status">
				                		<option value="-1">全部</option>
										<option value="0">未生效</option>
										<option value="1">已生效</option>
										<option value="2">已过期</option>
										<option value="3">禁用</option>
									</select>
				                </li>
								<li class="span2"><a  class="btn btn-primary search_sub"
														onclick="queryinfo(1)">搜索</a></li>
								
								</ul>
							</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												
												<td>用户ID</td>
												<td>角色类型</td>
												<td>角色对应值</td>
												<td>活动规则简介</td>
												<td>活动开始时间</td>
												<td>活动结束时间</td>
												<td>活动名称</td>
												<td>活动小标题</td>
												<td>活动图片</td>
												<td>活动状态</td>
												<td>活动添加时间</td>
												<td>活动更新时间</td>
												<td>是否删除</td>
												<td>记录</td>
												<td>活动类型</td>
												<td>活动是否线上</td>
												<td>是否支持商品</td>
												<td>活动是否全店通用</td>
												<td>打折折扣值</td>
												<td>满减活动值</td>
												<td>立减活动值</td>


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
		    menuact("04_02_09");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#act_type").val()!=""){
		ajaxdatas+="&act_type="+$("#act_type").val();
	}
	if($("#status").val()!=""){
		ajaxdatas+="&status="+$("#status").val();
	}
	
	$.ajax({
		type : "GET",
		url : "queryActBusShop",
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
					var user_id =item.user_id;
					var role_type =item.role_type;
					var role_value =item.role_value;
					var content =item.content;
					var begin_time =item.begin_time;
					var end_time =item.end_time;
					var title =item.title;
					var sub_title =item.sub_title;
					var pics =item.pics;
					var status =item.status;
					var add_time =item.add_time;
					var update_time =item.update_time;
					var is_del =item.is_del;
					var remark =item.remark;
					var act_type =item.act_type;
					var is_online =item.is_online;
					var is_good_add =item.is_good_add;
					var act_business =item.act_business;
					var zk_value =item.zk_value;
					var mj_value =item.mj_value;
					var lj_value =item.lj_value;
					
					switch(status){
			          	case 0:
			          		status = "未生效";
			          		break;
			          	case 1:
			          		status = "已生效";
			          		break;
			          	case 2:
			          		status = "已过期";
			          		break;
			          	case 3:
			          		status = "禁用";
			          		break;
			          }
					switch(act_type){
			          	case "mj":
			          		act_type = "满减";
			          		break;
			          	case "zk":
			          		act_type = "折扣";
			          		break;
			          	case "lj":
			          		act_type = "立减";
			          		break;
			          }
					switch(role_type){
		          	case "Buyer":
		          		role_type = "用户";
		          		break;
		          	case "Shop":
		          		role_type = "商家";
		          		break;
		          	case "Agent":
		          		role_type = "代理";
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
					switch(is_online){
			          	case 0:
			          		is_online = "仅线上展示";
			          		break;
			          	case 1:
			          		is_online = "用于结算";
			          		break;
			          }
					switch(is_good_add){
			          	case 0:
			          		is_good_add = "不支持";
			          		break;
			          	case 1:
			          		is_good_add = "支持";
			          		break;
			          }
					switch(act_business){
			          	case "1":
			          		act_business = "全店";
			          		break;
			          	case "2":
			          		act_business = "点餐";
			          		break;
			          	case "3":
			          		act_business = "外卖";
			          		break;
			          	case "4":
			          		act_business = "商品";
			          		break;
			          }
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+role_type+"</td>"+
					 "<td>"+role_value+"</td>"+
					 "<td>"+content+"</td>"+
					 "<td>"+begin_time+"</td>"+
					 "<td>"+end_time+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+sub_title+"</td>"+
					 "<td><img style='width:100px' src='"+pics+"'</td>"+
					 "<td>"+status+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+is_del+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+act_type+"</td>"+
					 "<td>"+is_online+"</td>"+
					 "<td>"+is_good_add+"</td>"+
					 "<td>"+act_business+"</td>"+
					 "<td>"+zk_value+"</td>"+
					 "<td>"+mj_value+"</td>"+
					 "<td>"+lj_value+"</td>"+
					 
					 "<td>"+"<a href='readActBusShop?id="+id+"'>修改</a><a href='delActBusShop?id="+id+"'>删除</a>"+"</td>"+
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