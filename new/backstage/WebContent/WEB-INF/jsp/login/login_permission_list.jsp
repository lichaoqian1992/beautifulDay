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
										权限管理<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">权限管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">权限管理</a></li>
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

									<!-- <a  class="btn blue"><i class="icon-pencil"></i>新增</a> -->

								</div>

							</div>
							
							<div class="portlet-body">
							<div class="row_fluid">
								<ul>
									<!-- <li>充值用户ID：<input type="text" id="useridstr" class="small m-wrap" ></li>
									<li>充值到账户：<input  type="text" id="accountidstr" class="small m-wrap" ></li>
									<li>充值订单号：<input  type="text" id="ordernostr" class="small m-wrap" ></li>
									<li>订单状态：<select id="statusstr" class="small m-wrap" style="width:100px !important" >
														<option value=" ">不限</option>
														<option value="0">未完成</option>
														<option value="1">已完成</option>
														</select></li>
									<li style="width:80px;text-align:left;"><a class="btn btn-primary search_sub" style="margin-top:-9px;margin-left:12px;" onclick="queryinfo(1)">搜索</a></li>
								 --></ul>						
							</div>
							
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>模块</th>
												<th>模块名</th>
												<th>标题</th>
												<th>接口</th>
												<th>序号</th>
												<!-- <th>操作</th> -->
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
		    menuact("11_03_01");
		    /* setinfo(); */
		    queryinfo(1); 
		});
	
/* 	function setinfo(){
		
		var perlist =${perlist };
		console.log(perlist);
		var tablehtml="";
		$.each(perlist,function(i,item){
			
			var no =Number(i+1);
			var id=item.id;
			var model =item.model;
			var modelname =item.modelname;
			var title =item.title;
			var url =item.url;
			var sort =item.sort;
			
			
			var rowhtml ="<tr>"+
			 "<td>"+no+"</td>"+
			 "<td>"+model+"</td>"+
			 "<td>"+modelname+"</td>"+
			 "<td>"+title+"</td>"+
			 "<td>"+url+"</td>"+
			 "<td>"+sort+"</td>"
			 "<td>"+"<a href='readLocalPermission?id="+id+"'>编辑权限</a>"+"</td>"+ 
			 "</tr>";
			tablehtml+=rowhtml;
			
			
			
		})
		
		$("#tablebody").html(tablehtml);
		
		
	} */
	
	 function queryinfo(index){
		
		if(index==0||index==""){
			index =1;
		}
		ajaxdatas ="index="+index;
				
		$.ajax({
			type : "GET",
			url : "queryPermission",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : ajaxdatas,
			success : function(freshdata) {
				console.log(freshdata)
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
						var model =item.model;
						var modelname =item.modelname;
						var title =item.title;
						var url =item.url;
						var sort =item.sort;
						
						
						rowhtml ="<tr>"+
								 "<td>"+no+"</td>"+
								 "<td>"+model+"</td>"+
								 "<td>"+modelname+"</td>"+
								 "<td>"+title+"</td>"+
								 "<td>"+url+"</td>"+
								 "<td>"+sort+"</td>"+
								 /* "<td>"+"<a href='readRecharge?id="+id+"'>修改</a>"+"</td>"+ */
								 "</tr>";
						tablehtml+=rowhtml;
								 
					})
					$("#tablebody").html(tablehtml);
					deviderset(totalcount,pagecount,pageindex);
				}else{
					
				}
				
				
				
			}
		
		});
		
		
		
		
	} 

	</script>

</body>

</html>