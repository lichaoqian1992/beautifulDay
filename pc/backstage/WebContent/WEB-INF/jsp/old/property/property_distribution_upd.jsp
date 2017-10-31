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
        .attri_pt1{
            padding-top: 6px;
        }
        .attri_pt1 span{
            color:#0933D4;
            margin: 0 30px 0 20px;
        }
        .attri_add,.attri_update{
            display:none;
            width:400px;
            height: 300px;
            background: #fff;
            padding:30px 20px;
            border: 1px solid #ccc;
            position: relative;
            margin: 0 auto;     
            top:-300px;
        }
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
						<h2>修改快递功能设置</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a href="selAttri">功能管理</a></li>
							<li><strong>修改快递功能设置</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>






				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">


									<div class="form-horizontal">
										<input type="hidden" id="hiddenid" value="">
										<div class="form-group">
											<label class="col-lg-2 control-label">配送名称：</label>
											
											<div class="col-lg-5 ">
												<input type="text" id="titlestr" class="form-control">
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">配送编码：</label>
											
											<div class="col-lg-5 ">
												<input type="text" id="codestr" class="form-control">
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">配送费用：</label>
											
											<div class="col-lg-5 ">
												<input type="text" id="feestr" class="form-control">
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">备注说明：</label>
											
											<div class="col-lg-5 ">
												<textarea type="text" id="remarkstr" class="form-control"></textarea>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">排序数字：</label>
											
											<div class="col-lg-5 ">
												<textarea type="text" id="sortstr" class="form-control"></textarea>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">是否锁定：</label>
											
											<div class="col-lg-1 ">
											<!-- 	<input type="text" id="mailcontent" class="form-control"> -->
												<select id="lockflag" class="form-control">
												<option value="0">不锁定</option>
												<option value="1">锁定</option>
												</select>
											</div>
										</div>
										
										
										
										
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<a class="btn btn-w-m btn-success ajax_btn" id="subbutton">保
													存</a>
												<button class="btn btn-w-m btn-default" type="submit"
													onclick="javascript:history.back();">返 回</button>
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
			infoset();
			
		$("#subbutton").on("click",function(){
			
			$.ajax({
				type : "GET",
				url : "updDistribution",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {
					id:$("#hiddenid").val(),
					title:$("#titlestr").val(),
					express_code:$("#codestr").val(),
					express_fee:$("#feestr").val(),
					remark:$("#remarkstr").val(),
					sort_id:$("#sortstr").val(),
					is_lock:$("#slockflag").val()
				},
				success : function(freshdata) {
					if(freshdata.status==0){
						location.reload();
					}
				}
			})
			
			
			
			
		})
			
		});
		
		
		 function infoset(){
			 
			var info =${distinfo };			 
			var id =info.id;
			var title =info.title;
			var express_code =info.express_code;
			var express_fee =info.express_fee;
			var remark =info.remark;
			var sort_id =info.id;
			var is_lock =info.is_lock;
			
			$("#titlestr").val(title);
			$("#codestr").val(express_code);
			$("#feestr").val(express_fee);
			$("#remarkstr").val(remark);
			$("#sortstr").val(sort_id);
			$("#lockstr").val(is_lock);
			$("#hiddenid").val(id);
			 
			 
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
