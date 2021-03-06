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
									普通活动信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">普通活动信息</a></li>
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
									<a  class="btn blue" href="insActCommon"><i class="icon-pencil"></i>新增普通活动信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
									<li>标题：<input type="text" id="title" class="small m-wrap" ></li>
					                <li style="width:300px;">活动状态：
					                	<select class="m-wrap small" id="status">
											<option value="-1">全部</option>
											<option value="0">待通过</option>
											<option value="1">正常</option>
											<option value="2">过期</option>
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
												
												<td>活动标题</td>
												<td>活动图片地址</td>
												<td>活动开始时间</td>
												<td>活动结束时间</td>
												<td>活动简介</td>
												<td>活动链接地址</td>
												<td>排序数字</td>
												<td>活动状态</td>
												<td>参与人员级别</td>
												<td>参与人员角色</td>
												<td>参与终端类别</td>
												<td>参与人数</td>

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
		    menuact("04_02_04");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	if($("#title").val()!=""){
	    ajaxdatas+="&title="+$("#title").val();
	  }
	if($("#status").val()!=""){
	    ajaxdatas+="&state="+$("#status").val();
	  }
	$.ajax({
		type : "GET",
		url : "queryActCommon",
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
					var title =item.title;
					var pics =item.pics;
					var begin_time =item.begin_time;
					var end_time =item.end_time;
					var content =item.content;
					var link_url =item.link_url;
					var sort_id =item.sort_id;
					var state =item.state;
					var join_user_groups =item.join_user_groups;
					var join_user_role =item.join_user_role;
					var join_terminal_type =item.join_terminal_type;
					var join_user_count =item.join_user_count;
					switch(state){
			          	case 0:
			          		state = "待通过";
			          		break;
			          	case 1:
			          		state = "正常";
			          		break;
			          	case 2:
			          		state = "过期";
			          		break;
			          }
					
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td><img style='width:100px' src="+pics+"></td>"+
					 "<td>"+begin_time+"</td>"+
					 "<td>"+end_time+"</td>"+
					 "<td>"+content+"</td>"+
					 "<td>"+link_url+"</td>"+
					 "<td>"+sort_id+"</td>"+
					 "<td>"+state+"</td>"+
					 "<td>"+join_user_groups+"</td>"+
					 "<td>"+join_user_role+"</td>"+
					 "<td>"+join_terminal_type+"</td>"+
					 "<td>"+join_user_count+"</td>"+
					 
					 "<td>"+"<a href='readActCommon?id="+id+"'>修改</a><a href='delActCommon?id="+id+"'>删除</a>"+"</td>"+
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