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
									首页推荐活动申请审核信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">首页推荐活动申请审核信息</a></li>
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
									<a  class="btn blue" href="insActIndUser"><i class="icon-pencil"></i>新增首页推荐活动申请审核信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
									<li>用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
								<li>标题：<input type="text" id="title" class="small m-wrap" ></li>
								<li>状态：
									<select class="m-wrap small" id="status">
										<option value="-1">全部</option>
										<option value="0">待审核</option>
										<option value="1">通过</option>
										<option value="2">拒绝</option>
										<option value="3">已过期</option>
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
												
												<td>活动编号</td>
												<td>站点编号</td>
												<td>申请时间</td>
												<td>申请标题</td>
												<td>申请描述</td>
												<td>商家商品ID</td>
												<td>申请图片</td>
												<td>申请用户</td>
												<td>申请角色</td>
												<td>申请角色值</td>
												<td>状态</td>
												<td>审核更新时间</td>
												<td>状态记录</td>
												<td>审核展示时间</td>
												<td>展示结束时间</td>
												<td>自定义链接</td>

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
		    menuact("04_02_03");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#title").val()!=""){
		ajaxdatas+="&title="+$("#title").val();
	}
	if($("#status").val()!=""){
		ajaxdatas+="&status="+$("#status").val();
	}
	$.ajax({
		type : "GET",
		url : "queryActIndUser",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : ajaxdatas,
		success : function(freshdata) {
			console.log(freshdata);
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
					var index_id =item.index_id;
					var site_id =item.site_id;
					var add_time =item.add_time;
					var title =item.title;
					var content =item.content;
					var content_value =item.content_value;
					var image_url =item.image_url;
					var user_id =item.user_id;
					var role_type =item.role_type;
					var role_value =item.role_value;
					var status =item.status;
					var update_time =item.update_time;
					var remark =item.remark;
					var show_time =item.show_time;
					var show_endtime =item.show_endtime;
					var custom_url =item.custom_url;
					
					switch(status){
			          	case 0:
			          		status = "待审核";
			          		break;
			          	case 1:
			          		status = "通过";
			          		break;
			          	case 2:
			          		status = "拒绝";
			          		break;
			          	case 3:
			          		status = "已过期";
			          		break;
			          }
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+index_id+"</td>"+
					 "<td>"+site_id+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+content+"</td>"+
					 "<td>"+content_value+"</td>"+
					 "<td><img style='width:100px' src="+image_url+"></td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+role_type+"</td>"+
					 "<td>"+role_value+"</td>"+
					 "<td>"+status+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+show_time+"</td>"+
					 "<td>"+show_endtime+"</td>"+
					 "<td>"+custom_url+"</td>"+
					 
					 "<td>"+"<a href='readActIndUser?id="+id+"'>修改</a><a href='delActIndUser?id="+id+"'>删除</a>"+"</td>"+
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