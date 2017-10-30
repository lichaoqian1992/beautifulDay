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
						<h2>代理商组别列表</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>代理商组别管理</a></li>
							<li><strong>代理商组别列表</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>






				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">

								<div class="ibox-content">
									<div class="row  ">
										<div class="col-lg-2">
											<select class="form-control m-b" id="brandtype">
												<option value="">全部</option>
												<option value="0">国内</option>
												<option value="1">国外</option>
											</select>
										</div>
										<div class="col-lg-5">
											<div class="input-group">
												<input type="text" class="form-control" id="searchstr">
												<span class="input-group-btn">
													<button type="button" class="btn btn-primary"
														onclick="querybrand()">搜索</button>
												</span>
											</div>
										</div>
										<div class="col-lg-5" style="text-align: right;">
											<a href="insBrand" class="btn btn-primary">添加品牌</a>
										</div>
									</div>




									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>LOGO</th>
												<th>类别</th>
												<th>名称</th>
												<th>更新时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="tablecontent">

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
		$(document).ready(
				
						function() {
							onkey(1);
						/* 	var data = ${brandlist};

							$.each(data,function(i, branditem) {

												var no = Number(i + 1);
												var logo = branditem.logo;
												var brandtype = branditem.brand_type;
												var name = branditem.name;
												var time = branditem.update_time;
												var id = branditem.id;

												
												var brandtypestr ="";
												
												switch(brandtype){
												case 0:
													
													brandtypestr="国内";
													break;
												case 1:
													brandtypestr="国外";
													break;
												}
												
												$("#tablecontent").append(
																"<tr><td>"
																		+ no
																		+ "</td><td>"
																		+ "<img src='"+logo +"' style='height:30px;'>"
																		+ "</td><td>"
																		+ brandtypestr
																		+ "</td><td>"
																		+ name
																		+ "</td><td>"
																		+ time
																		+ "</td><td>"
																		+ "<a href='readBrand?id="
																		+ id
																		+ "'>查看</a>&nbsp;&nbsp;<a href='chgBrand?id="
																		+ id
																		+ "'>编辑</a>&nbsp;&nbsp;<a onclick='del("
																		+ id
																		+ ")'>删除</a>"
																		+ "</td></tr>");

											});
 */
						});

		function querybrand() {

			var name = $("#searchstr").val();
			var type = $("#brandtype").val() + "";

			
			
			var ajaxdatas = "name=" + name;
			if (null == type || "" == type) {

			} else {
				ajaxdatas += "&brand_type=" + type;
			}

			$.ajax({
						type : "GET",
						url : "queryBrand",
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						data : ajaxdatas,
						success : function(freshdata) {
							
							
							if(freshdata.status ==0){
								var page = freshdata.result;
								var data =page.dataList;
								
								
								
								$("#tablecontent").empty();
								$.each(data,function(i, branditem) {
									var no = Number(i + 1);
									var logo = branditem.logo;
									var brandtype = branditem.brand_type;
									var name = branditem.name;
									var time = branditem.update_time;
									var id = branditem.id;
									var brandtypestr ="";
									switch(brandtype){
									case 0:
										brandtypestr="国内";
										break;
									case 1:
										brandtypestr="国外";
										break;
									}
									
									$("#tablecontent").append(
													"<tr><td>"
															+ no
															+ "</td><td>"
															+ "<img src='"+logo +"' style='height:30px;'>"
															+ "</td><td>"
															+ brandtypestr
															+ "</td><td>"
															+ name
															+ "</td><td>"
															+ time
															+ "</td><td>"
															+ "<a href='readBrand?id="
															+ id
															+ "'>查看</a>&nbsp;&nbsp;<a href='chgBrand?id="
															+ id
															+ "'>编辑</a>&nbsp;&nbsp;<a onclick='delBrand("
															+ id
															+ ")'>删除</a>"
															+ "</td></tr>");

								});
								
							}else{
								
							}
							
							
							var totalcount=page.totalCount;
							var pagecount =page.pageCount;
							var index =page.index;
							
							var totalinfo ="总条数:"+totalcount+"&nbsp;&nbsp;"+"总页数:"+pagecount;
							$("#totalinfo").html(totalinfo);
							
							
							htmlpage(index,pagecount);
							

						},
					});

		};
		
		
		
		function delBrand(id){
			alert(id);
			$.ajax({
				
				type : "GET",
				url : "delBrand",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {"id":id},
				success : function(freshdata) {
					if(freshdata.status){
						window.location.reload();
					}else{
						alert(删除失败);
					}
				
					
				},
				
				
				
			});
			
			
			
		}
		
		
		
		function htmlpage(index,pagecount){
			
		
			var pagehtml ="";
			
			var indexinfo ="";
			
			for(var i=1;i<pagecount;i++){
				
				indexinfo+='<li class="paginate_button "aria-controls="DataTables_Table_0"'+
				 'tabindex="0"><a class="btn " onclick="freshdata('+i+')">'+i+'</a></li>';
				
			}
			
			pagehtml +='<ul class="pagination">';
			if(index>1){
				pagehtml+= '<li class="paginate_button previous " aria-controls="DataTables_Table_0" '+
				'tabindex="0" id="DataTables_Table_0_previous"><a class="btn" data='+Number(i-1)+'>上一页</a></li>';
				
			}
			
			
			pagehtml+=indexinfo;
			
			if(index<pagecount-1){
				pagehtml+='<li class="paginate_button next"aria-controls="DataTables_Table_0"'+
	 			'tabindex="0" id="DataTables_Table_0_next"><a class="btn" data='+Number(i+1)+'>下一页</a></li>';
			}
			
			 pagehtml+='</ul>';
			 
			 $("#pageinfo").html(pagehtml);
			 
			/* if(i)
			
			
			<ul class="pagination">
			
			
			<li class="paginate_button "
				aria-controls="DataTables_Table_0" tabindex="0"><a
				href="#">2</a></li>
			<li class="paginate_button next"
				aria-controls="DataTables_Table_0" tabindex="0"
				id="DataTables_Table_0_next"><a href="#">下一页</a></li>
			
		
			 */
			
		}
		
		
		
		function freshdata(index){
			
			
			var name = $("#searchstr").val();
			var type = $("#brandtype").val() + "";

			
			
			var ajaxdatas = "name=" + name;
			ajaxdatas+= "&index="+index;
			if (null == type || "" == type) {

			} else {
				ajaxdatas += "&brand_type=" + type;
			}

			$.ajax({
						type : "GET",
						url : "queryBrand",
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						data : ajaxdatas,
						success : function(freshdata) {
							
							
							if(freshdata.status ==0){
								var page = freshdata.result;
								var data =page.dataList;
								
								
								
								$("#tablecontent").empty();
								$.each(data,function(i, branditem) {
									var no = Number(i + 1);
									var logo = branditem.logo;
									var brandtype = branditem.brand_type;
									var name = branditem.name;
									var time = branditem.update_time;
									var id = branditem.id;
									var brandtypestr ="";
									switch(brandtype){
									case 0:
										brandtypestr="国内";
										break;
									case 1:
										brandtypestr="国外";
										break;
									}
									
									$("#tablecontent").append(
													"<tr><td>"
															+ no
															+ "</td><td>"
															+ "<img src='"+logo +"' style='height:30px;'>"
															+ "</td><td>"
															+ brandtypestr
															+ "</td><td>"
															+ name
															+ "</td><td>"
															+ time
															+ "</td><td>"
															+ "<a href='readBrand?id="
															+ id
															+ "'>查看</a>&nbsp;&nbsp;<a href='chgBrand?id="
															+ id
															+ "'>编辑</a>&nbsp;&nbsp;<a onclick='delBrand("
															+ id
															+ ")'>删除</a>"
															+ "</td></tr>");

								});
								
							}else{
								
							}
							
							
							var totalcount=page.totalCount;
							var pagecount =page.pageCount;
							var index =page.index;
							
							var totalinfo ="总条数:"+totalcount+"&nbsp;&nbsp;"+"总页数:"+pagecount;
							$("#totalinfo").html(totalinfo);
							
							
							htmlpage(index,pagecount);
							

						},
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
