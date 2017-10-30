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
			<jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true"/>
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
								<jsp:include page="/WEB-INF/jsp/banner.jsp" flush="true"/>
		
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
						<h2>快递功能设置</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>功能设置</a></li>
							<li><strong>快递功能设置</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>






				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">

									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>配送名称</th>
												<th>配送编码</th>
												<th>配送费用</th>
												<th>备注说明</th>
												<th>排序数字</th>
												<th>是否锁定</th>
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
		onkey(10);
		filltable();
		
		
	});
	
	function filltable(){
		var data =${conflist };
		var datalist =data.dataList;
		
		var tablehtml="";
		
		$.each(datalist,function(i,item){
			
			var no =Number(i+1);
			var id=item.id;
			var title =item.title;
			var express_code =item.express_code;
			var express_fee =item.express_fee;
			var remark =item.remark;
			var sort_id =item.sort_id;
			var is_lock =item.is_lock;
			
			var is_lockstr="";
			switch(is_lock){
			case 0:
				is_lockstr="不锁定";
				break;
			case 1:
				is_lockstr="锁定";
				break;
			}
			
			var rowstr ="<tr>"+
						"<td>"+no+"</td>"+
						"<td>"+title+"</td>"+
						"<td>"+express_code+"</td>"+
						"<td>"+express_fee+"</td>"+
						"<td>"+remark+"</td>"+
						"<td>"+sort_id+"</td>"+
						"<td>"+is_lockstr+"</td>"+
						"<td>"+"<a href=chgDistribution?id="+id+">修改</a>&nbsp<a href=delDistribution?id="+id+">删除</a>"+"</td>"+
						"</tr>";
						
			tablehtml +=rowstr;
			
		})
		$("#tablebody").html(tablehtml);
		
	};
	
	
	
		
		
	
	
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
