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
<title>满集数据后台系统</title>

<link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
<link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
<link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=2.2.0" rel="stylesheet">
<link href="js/plugins/layer/skin/layer.css" rel="stylesheet">
<script src="js/jquery-2.1.1.min.js"></script>
<style type="text/css">
        .row-pd{
            padding:10px 0;
        }

        /*搜索*/
        ._span{
            line-height: 34px;
            text-align: right;
            width: 80px;
            float:left;
            font-weight: bold;
            margin-right:8px;
        }
        .search_sub{
            position: absolute;
        }
        label{
            min-width: 100px;
            padding:0 5px !important;
            line-height: 34px;
        }
        /*搜索*/
    </style>

</head>

<body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
		<div id="side-menu">
			<jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
			</div>
		</div>
		</nav>

		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
						href="#"><i class="fa fa-bars"></i> </a>
					<!-- <form role="search" class="navbar-form-custom" method="post"
						action="search_results.html"> -->
					<div class="row border-bottom">
						<div class="col-lg-9">
							<div class="form-group" style="width: 1000px">
								<jsp:include page="/WEB-INF/jsp/banner.html" flush="true"/>
		
							</div>
						</div>
						
						
						<ul class="nav navbar-top-links navbar-right">
							<li><span>${sessionScope.username}</span></li>
							<li><a href="loginout"> <i class="fa fa-sign-out"></i>
									退出
							</a></li>
						</ul>

					</div>
				</div>


				</nav>
			</div>
			<div id="maincontent">
				<div class="row wrapper border-bottom white-bg page-heading">
					<div class="col-lg-10">
						<h2>转账列表管理</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>账户信息管理</a></li>
							<li><strong>转账列表管理</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>






				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">

									<form class="form-horizontal">
									
										<div class="form-group">
											<label class="col-lg-1 control-label lable_a">转账用户ID：</label>
											<div class="col-lg-10">
												<div class="row">
													<div class="col-md-2">
														
														<input type="text" id="useridstr" placeholder="" class="form-control"> 
														
													</div>
													<span class="_span">收账用户ID：</span>
													<div class="col-md-2">
														<input type="text" id="touseridstr" placeholder="" class="form-control">
														
													</div>
													<span class="_span">转账订单号：</span>
													<div class="col-md-2">
														<input type="text" id="ordernostr" placeholder="" class="form-control">
														
													</div>
													
													<span class="_span">转账标记：</span>
													<div class="col-md-2">
														<select id="statusstr" class="form-control">
														<option value="">不限</option>
														<option value="0">正常转款</option>
														<option value="1">借款</option>
														</select>
													</div>
													
													<a type="submit" class="btn btn-primary search_sub"
														onclick="queryinfo()">搜索</a>
												</div>
											</div>
										</div>

									</form>



									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>转账订单号</th>
												<th>用户ID</th>
												<th>角色类别</th>
												<th>用户角色值</th>
												<th>对象用户ID</th>
												<th>对象角色类型</th>
												<th>对象角色值</th>
												<th>充值金额</th>
												<th>转账标记</th>
												<th>生成时间</th>
												<th>完成时间</th>
												<th>转账说明</th>
												<th>是否删除</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="tablebody">

										</tbody>
									</table>
									<div class="row">
										<div class="col-sm-6">
											<div class="dataTables_info" id="DataTables_Table_0_info"
												role="alert" aria-live="polite" aria-relevant="all">
												<span id="totalinfo"></span></div>
										</div>
										<div class="col-sm-6">
											<div class="dataTables_paginate paging_simple_numbers"
												id="pageinfo">
												
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>

				





			</div>

		</div>
	</div>

	<script>
	$(document).ready(function(){
		onkey(5);
		
	});
	
	
	function queryinfo(inx){
		
		var index =1;
		ajaxdatas ="index="+index;
		if($("#useridstr").val()!=""){
			ajaxdatas+="&user_id="+$("#useridstr").val();
		}
		if($("#statusstr").val()!=""){
			ajaxdatas+="&status="+$("#statusstr").val();
		}
		if($("#ordernostr").val()!=""){
			ajaxdatas+="&transaction_no="+$("#ordernostr").val();
		}
		if($("#touseridstr").val()!=""){
			ajaxdatas+="&touser_id="+$("#touseridstr").val();
		}
		
		$.ajax({
			type : "GET",
			url : "queryTransaction",
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
						var user_id =item.user_id;
						var role_type =item.user_role_type;
						var role_value =item.user_role_value;
						var touser_id =item.touser_id;
						var touser_role_type =item.touser_role_type;
						var touser_role_value =item.touser_role_value;
						var transaction_no =item.transaction_no;
						var amount =item.amount;
						var status =item.status;
						var add_time =item.add_time;
						var complete_time =item.complete_time;
						var is_del =item.is_del;
						var remark =item.remark;
		
						
						var statusstr ="";
						switch(status){
						case 0:
							statusstr="正常转款";
							break;
						case 1:
							statusstr="借款";
							break;
						
						}
						
						var is_delstr ="";
						switch(is_del){
						case 0:
							is_delstr="正常";
							break;
						case 1:
							is_delstr="删除";
							break;
						
						}
						
						rowhtml ="<tr>"+
								 "<td>"+no+"</td>"+
								 "<td>"+transaction_no+"</td>"+
								 "<td>"+user_id+"</td>"+
								 "<td>"+role_type+"</td>"+
								 "<td>"+role_value+"</td>"+
								 "<td>"+touser_id+"</td>"+
								 "<td>"+touser_role_type+"</td>"+
								 "<td>"+touser_role_value+"</td>"+
								 
								 "<td>"+amount+"</td>"+
								 "<td>"+statusstr+"</td>"+
								 "<td>"+add_time+"</td>"+
								 "<td>"+complete_time+"</td>"+
								 "<td>"+remark+"</td>"+
								 "<td>"+is_del+"</td>"+
								 "<td>"+"<a href=''>查看</a>"+"</td>"+
								 "</tr>";
						tablehtml+=rowhtml;
								 
					})
					$("#tablebody").html(tablehtml);
				}else{
					
				}
				
				
				
			}
		
		});
		
		
		
		
	}
	
	</script>


	<!-- Mainly scripts -->

	<script src="js/bootstrap.min.js?v=3.4.0"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="js/hplus.js?v=2.2.0"></script>
	<script src="js/plugins/pace/pace.min.js"></script>

	<!-- jQuery UI -->
	<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

	<!-- GITTER -->
	<script src="js/plugins/gritter/jquery.gritter.min.js"></script>

	<!-- EayPIE -->
	<script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>

	<!-- Sparkline -->
	<script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

	<!-- Sparkline demo data  -->
	<script src="js/demo/sparkline-demo.js"></script>

	<script src="js/plugins/layer/layer.min.js"></script>





</body>

</html>
