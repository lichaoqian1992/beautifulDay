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
										用户代理商信息修改临存<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">代理商信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户代理商信息修改临存</a></li>
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
									<a  class="btn blue" href="insAgentInfoTemp"><i class="icon-pencil"></i>新增用户代理商信息修改临存</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
							<ul>
								<li>用户ID：<input type="text" id="user_id" class="small m-wrap" ></li>
								<li>负责人：<input type="text" id="name" class="small m-wrap" ></li>
								<li>状态：<select id="state" class="small m-wrap" >
														<option value="-1"></option>
														<option value="0">待审核</option>
							                            <option value="1">正常</option>
							                            <option value="2">冻结</option>
							                            <option value="3">过期</option>
														</select></li>
									<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
								
								</ul>
							</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>记录ID</th>
												<th>代理分组</th>
												<th>用户ID</th>
												<th>代理区域</th>
												<th>代理开始时间</th>
												<th>代理结束时间</th>
												<th>负责人</th>
												<th>联系电话</th>
												<th>代理状态</th>
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
		    menuact("07_01_02");
		});
	
function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	/* var index =1; */
	ajaxdatas ="index="+index;
	
	if($("#state").val()!=""){
		ajaxdatas+="&state="+$("#state").val();
	}
	if($("#user_id").val()!=""){
		ajaxdatas+="&user_id="+$("#user_id").val();
	}
	if($("#name").val()!=""){
		ajaxdatas+="&name="+$("#name").val();
	}
	
	
	$.ajax({
		type : "GET",
		url : "queryAgentInfoTemp",
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
					var id =item.id;
					var record_id=item.record_id;
					var group_id =item.group_id;
					var user_id =item.user_id;
					var agent_area =item.agent_area;
					var name =item.name;
					var telephone =item.telephone;
					var begin_time =item.begin_time;
					var end_time =item.end_time;
					var state =item.state;
					var statestr ="";
					switch(state){
					case 0:
						statestr="未审核";
						break;
					case 1:
						statestr="正常";
						break;
					case 2:
						statestr="冻结";
						break;
					case 3:
						statestr="过期";
						break;
					
					}
					
					rowhtml ="<tr>"+
							 "<td>"+no+"</td>"+
							 "<td>"+record_id+"</td>"+
							 "<td>"+group_id+"</td>"+
							 "<td>"+user_id+"</td>"+
							 "<td>"+agent_area+"</td>"+
							 "<td>"+begin_time+"</td>"+
							 "<td>"+end_time+"</td>"+
							 "<td>"+name+"</td>"+
							 "<td>"+telephone+"</td>"+
							 "<td>"+statestr+"</td>"+
							 "<td>"+"<a href='readAgentInfoTemp?id="+id+"'>修改</a>&nbsp<a href='delAgentInfoTemp?id="+id+"'>删除</a>"+"</td>"+
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