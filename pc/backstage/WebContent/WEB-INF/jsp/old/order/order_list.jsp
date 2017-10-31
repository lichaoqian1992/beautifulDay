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
								<ul class="nav navbar-nav menu_first">


									<li class="dropdown"><a  class="dropdown-toggle" onclick="onkey(1)"
										> 运维 </a></li>
									<li class="dropdown"><a  class="dropdown-toggle" onclick=""
										> 用户 </a></li>
									<li class="dropdown"><a   class="dropdown-toggle" onclick=""
										> 商家 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 代理</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 账户 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 交易 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 消息 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 日志</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 监控 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 配置 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 登录 </a></li>

								</ul>

		

							</div>
						</div>
						
						<script>
						function onkey(key){
				            /* var ul=key+" ul";*/
				            var li=key+" ul li"; 

				            $('#side-menu li').css("display","none");
				            $('.abc').css("display","block");
				            $('.k'+key).css("display","block");
				            $('.k'+li).css("display","block"); 
				            /* $('.k'+key).addClass("active");
				            $('.k'+ul).addClass("in"); */
				        }
						</script>
						
						
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
						<h2>订单列表</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a href="selAttri">订单管理</a></li>
							<li><strong>订单列表</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>






				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">

								
								<div class="row ">
									
									<div class="col-lg-2 dropdown">
										<label class="">订单编号：</label>
										<input type="text"  id="searchstr"/>
									</div>
									<div class="col-lg-2 dropdown">
										<label class="">订单类型：</label>
										<select>
										<option>1</option>
										</select>
									</div>
									<div class="col-lg-2">
										<label class="">订单状态：</label>
										<select>
										<option>1</option>
										</select>
									</div>
									<div class="col-lg-2">
										<label class="">商家ID：</label>
										<input type="text"  id="searchstr"/>
									</div>
									<div class="col-lg-2">
										<label class="">买家ID：</label>
										<input type="text"  id="searchstr"/>
									</div>
									<div class="col-lg-4">
										<div class="input-group">
											 <span
												class="input-group-btn">
												<button type="button" class="btn btn-primary" id="querybtn">搜索</button>
											</span>
										</div>
									</div>
									<div class="col-lg-2" style="text-align: right;">
										<a href="insAttri" class="btn btn-primary">添加属性</a>
									</div>

									</div>



									<table id="attritable" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>一级类别</th>
												<th>二级类别</th>
												<th>三级类别</th>
												<th>属性值</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="tablebody">
											
										</tbody>
									</table>




									
									


								</div>
							</div>
						</div>
					</div>

				</div>

				
				
				
				
				
			
				
				

			</div>

		</div>
	</div>
	
	<script>
		$(document).ready(function() {
			
			onkey(1);
			
			firstcate();
			

			$('#spec_add').on('click', function() {
				$.layer({
					type : 1,
					shade : [ 0 ],//遮罩透明度                    
					area : [ 'auto', 'auto' ],
					offset : [ '100px', '' ],
					title : false,
					border : [ 0 ],
					page : {
						dom : '.spec_add'
					}
				});
			});
			// $('#spec_update').on('click', function(){
			//    $.layer({
			//         type: 1,                    
			//         shade: [0],
			//         area: ['auto', 'auto'],
			//         offset:['100px', ''],
			//         title: false,
			//         border: [0],                    
			//         page: {dom : '.spec_update'}
			//     });
			// });
			$(".spec_update_a").click(function() {
				$(".spec_update").css("display", "block");
			});
			$(".close_update").click(function() {
				$(".spec_update").css("display", "none");
			});
		});
		
		
		function firstcate(){
			$("#firstcate").prepend("<option value='0'>全部一级分类</option>"); 
			$("#secondcate").prepend("<option value='0'>全部二级分类</option>"); 
			$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
			$.ajax({
				type : "GET",
				url : "firstCategory",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : [],
				success : function(data) {
					var catelist =data.result;
					$.each(catelist,function(i, cateitem) {
						var id =cateitem.id;
						var title =cateitem.title;
						$("#firstcate").append("<option value='"+id+"'>"+title+"</option>"); 
					});
					
				}});
		};
		
		
		$("#firstcate").change(function(){
			$("#secondcate").empty();
			$("#secondcate").prepend("<option value='0'>全部二级分类</option>"); 
			$("#thirdcate").empty();
			$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
			var fcateid =$("#firstcate").val();
			$("#categoryid").val(fcateid);
			
			$.ajax({
				type : "GET",
				url : "secondCategory",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {id:$("#categoryid").val()},
				success : function(data) {
					var catelist =data.result;
					$.each(catelist,function(i, cateitem) {
						var id =cateitem.id;
						var title =cateitem.title;
						$("#secondcate").append("<option value='"+id+"'>"+title+"</option>"); 
					});
					/* $("#secondcate").prepend("<option value='0'>全部二级分类</option>");  */
				}});
			
		});
		
		
		$("#secondcate").change(function(){
			$("#thirdcate").empty();
			$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
			var scateid =$("#secondcate").val();
			$("#categoryid").val(scateid);
			
			$.ajax({
				type : "GET",
				url : "secondCategory",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {id:$("#categoryid").val()},
				success : function(data) {
					var catelist =data.result;
					$.each(catelist,function(i, cateitem) {
						var id =cateitem.id;
						var title =cateitem.title;
						$("#thirdcate").append("<option value='"+id+"'>"+title+"</option>"); 
					});
					/* $("#secondcate").prepend("<option value='0'>全部二级分类</option>");  */
				}});
			
		});
		
		
		
		
		$("#thirdcate").change(function(){
			
			var tcateid =$("#thirdcate").val();
			$("#categoryid").val(tcateid);
			
		});
		
		
		
		$("#querybtn").click(function(){
			$("#tablebody").empty();
			
			 /* $("#attritable tr:gt(0)").remove(); */
			$.ajax({
				type : "GET",
				url : "queryAttri",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {category_id:$("#categoryid").val()},
				success : function(data) {
					var attrilist =data.result;
					
					var tablecont ="";
					$.each(attrilist,function(i, attriitem) {
						
						var no =Number(i+1);
						var id =attriitem.attri_id;
						var fname =attriitem.first_name;
						var sname =attriitem.second_name;
						var tname =attriitem.third_name;
						var values =attriitem.attri_str;
						
						
						var newRow = "<tr>"+
						"<td>"+no+"</td>"+
						"<td>"+fname+"</td>"+
						"<td>"+sname+"</td>"+
						"<td>"+tname+"</td>"+
						"<td>"+values+"</td>"+
						"<td>"+"<a href='readAttri?attri_id="+id+"'>查看</a>&nbsp;</td>"+
						"</tr>";
						/* $("#attritable tr:last").after(newRow); */
						tablecont +=newRow;
						
						
					});
					$("#tablebody").html(tablecont);
				}});
			
			
			
		});
		
		
		
		
		
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
