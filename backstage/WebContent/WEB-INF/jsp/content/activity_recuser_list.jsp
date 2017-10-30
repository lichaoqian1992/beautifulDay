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
									充值赠送首单返现参与用户	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">充值赠送首单返现参与用户</a></li>
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
									<a  class="btn blue" href="insActRecUser"><i class="icon-pencil"></i>新增充值赠送首单返现参与用户</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul><li class="span2">用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
								<li class="span2">返现单号：<input type="text" id="first_order_no" class="small m-wrap" ></li>
								<li class="span2">返现状态：
									<select class="m-wrap small" id="first_state">
				                		<option value="-1">全部</option>
										<option value="0">未返现</option>
										<option value="1">已返现</option>
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
												<td>区域推广用户</td>
												<td>充值赠送金额</td>
												<td>返现金额</td>
												<td>返现状态</td>
												<td>返现时间</td>
												<td>返现订单号</td>
												<td>返现订单类型</td>
												<td>状态</td>
												<td>添加时间</td>

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
		    menuact("04_02_12");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#first_order_no").val()!=""){
		ajaxdatas+="&first_order_no="+$("#first_order_no").val();
	}
	if($("#first_state").val()!=""){
		ajaxdatas+="&first_state="+$("#first_state").val();
	}
	
	$.ajax({
		type : "GET",
		url : "queryActRecUser",
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
					console.log(item);
					var no =Number(i+1);
					var id=item.id;
					var user_id =item.user_id;
					var activity_user_id =item.activity_user_id;
					var recherge_give =item.recherge_give;
					var first_give =item.first_give;
					var first_state =item.first_state;
					var first_time =item.first_time;
					var first_order_no =item.first_order_no;
					var first_order_type =item.first_order_type;
					var state =item.state;
					var add_time =item.add_time;
					switch(first_state){
		          	case 0:
		          		first_state = "未返现";
		          		break;
		          	case 1:
		          		first_state = "已返现";
		          		break;
		          }
					
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+activity_user_id+"</td>"+
					 "<td>"+recherge_give+"</td>"+
					 "<td>"+first_give+"</td>"+
					 "<td>"+first_state+"</td>"+
					 "<td>"+first_time+"</td>"+
					 "<td>"+first_order_no+"</td>"+
					 "<td>"+first_order_type+"</td>"+
					 "<td>"+state+"</td>"+
					 "<td>"+add_time+"</td>"+
					 
					 "<td>"+"<a href='readActRecUser?id="+id+"'>修改</a>&nbsp;<a href='delActRecUser?id="+id+"'>删除</a>"+"</td>"+
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