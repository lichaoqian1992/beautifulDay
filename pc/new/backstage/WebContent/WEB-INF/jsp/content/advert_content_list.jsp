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
								广告位放置内容		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">导航信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">广告位放置内容</a></li>
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
									<a  class="btn blue" href="insAdvertContent"><i class="icon-pencil"></i>新增广告位放置内容</a>
								</div>
							</div>
							
							<div class="portlet-body">
							 <div class="row-fluid ">
				                <div class="span2">广告内容：<input type="text" id="content" class="small m-wrap" ></div>
				                <div class="span2">广告状态：
				                	<select class="m-wrap small" id="state">
				                		<option value="-1"></option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
									</select>
				                </div>
				                <div class="span2">广告是否显示：
				                	<select class="m-wrap small" id="is_show">
				                		<option value="-1"></option>
										<option value="0">不显示</option>
										<option value="1">已显示</option>
									</select>
				                </div>
				                <div class="span2">是否删除：
				                	<select class="m-wrap small" id="is_del">
				                		<option value="-1"></option>
										<option value="0">正常</option>
										<option value="1">删除</option>
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
												<td>对应广告位ID</td>
												<td>对应购买ID</td>
												<td>内容标注</td>
												<td>图片</td>
												<td>链接地址</td>
												<td>广告内容</td>
												<td>广告状态</td>
												<td>广告是否显示</td>
												<td>状态对应提醒</td>
												<td>更新时间</td>
												<td>发布用户ID</td>
												<td>用户角色类型</td>
												<td>角色类型值</td>
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
		    menuact("04_01_06");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#content").val()!=""){
		ajaxdatas+="&content="+$("#content").val();
	}
	if($("#state").val()!=""){
		ajaxdatas+="&state="+$("#state").val();
	}
	if($("#is_show").val()!=""){
		ajaxdatas+="&is_show="+$("#is_show").val();
	}
	if($("#is_del").val()!=""){
		ajaxdatas+="&is_del="+$("#is_del").val();
	}
	$.ajax({
		type : "GET",
		url : "queryAdvertContent",
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
					var	advert_banner_id=item.advert_banner_id;
					var	name=item.name;
					var	file_path=item.file_path;
					var	link_url=item.link_url;
					var	content=item.content;
					var	state=item.state;
					var	is_show=item.is_show;
					var	remark=item.remark;
					var	update_time=item.update_time;
					var	user_id=item.user_id;
					var	user_role_type=item.user_role_type;
					var	user_role_value=item.user_role_value;
					var	is_del=item.is_del;

					switch(is_del){
			          	case 0:
			          		is_del = "正常";
			          		break;
			          	case 1:
			          		is_del = "删除";
			          		break;
		          	}
					switch(user_role_type){
			          	case "Buyer":
			          		user_role_type = "用户";
			          		break;
			          	case "Shop":
			          		user_role_type = "商家";
			          		break;
		          	}
					switch(is_show){
			          	case 0:
			          		is_show = "不显示";
			          		break;
			          	case 1:
			          		is_show = "已显示";
			          		break;
	          		}
					switch(state){
			          	case 0:
			          		state = "未审核";
			          		break;
			          	case 1:
			          		state = "已审核";
			          		break;
          			}
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+advert_id+"</td>"+
					 "<td>"+advert_banner_id+"</td>"+
					 "<td>"+name+"</td>"+
					 "<td><img style='width:100px' src="+file_path+"></td>"+
					 "<td>"+link_url+"</td>"+
					 "<td>"+content+"</td>"+
					 "<td>"+state+"</td>"+
					 "<td>"+is_show+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+is_del+"</td>"+

					 "<td>"+"<a href='readAdvertContent?id="+id+"'>修改</a><br><a href='delAdvertContent?id="+id+"'>删除</a>"+"</td>"+
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