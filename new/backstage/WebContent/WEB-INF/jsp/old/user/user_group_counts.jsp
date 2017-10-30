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
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">
	<script src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
		<div id="side-menu">
			<ul class="nav" >
				<li class="nav-header"></li>

				<li class="active"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">用户组别管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li class="active"><a href="selUserGroup">用户组别列表</a></li>
						<li><a href="*">用户组别统计</a></li>
						<li><a href="*">用户组别调整</a></li>
						<li><a href="*">用户组别变化记录</a></li>
					</ul></li>

				<li><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">用户安全</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">用户验证记录</a></li>
						<li><a href="*">用户安全状态</a></li>

					</ul></li>


				
			</ul>
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


									<li class="dropdown"><a  class="dropdown-toggle" onclick="menu_side(0)"
										> 运维 </a></li>
									<li class="dropdown"><a  class="dropdown-toggle" onclick="menu_side(1)"
										> 用户 </a></li>
									<li class="dropdown"><a   class="dropdown-toggle" onclick="menu_side(2)"
										> 商家 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(3)"
										> 代理</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(4)"
										> 账户 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(5)"
										> 交易 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(6)"
										> 消息 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(7)"
										> 日志</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(8)"
										> 监控 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(9)"
										> 配置 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick="menu_side(10)"
										> 运维 </a></li>

								</ul>

		

							</div>
						</div>
						
						<script>
						
						function menu_side(index){
							
							alert(index);
							
							$("#side-menu").empty();
							
							
							
							  switch(index){
							  case 0:
								  alert(2);
								  
								  
								  var html='<ul class="nav" ><li class="nav-header"></li>				<li class="active"><a><i class="fa fa-th-large"></i> <span						class="nav-label">用户组别管理</span> <span class="fa arrow"></span></a>					<ul class="nav nav-second-level">						<li class="active"><a href="selUserGroup">用户组别列表</a></li>						<li><a href="*">用户组别统计</a></li>						<li><a href="*">用户组别调整</a></li>						<li><a href="*">用户组别变化记录</a></li>					</ul></li>				<li><a><i class="fa fa fa-globe"></i> <span						class="nav-label">用户安全</span><span class="fa arrow"></span></a>					<ul class="nav nav-second-level">						<li><a href="*">用户验证记录</a></li>					<li><a href="*">用户安全状态</a></li>					</ul></li></ul>';
								  
								  $("#side-menu").append(html);
								  
								  
								  break;
							  }
							  
							  
						}
						
						
						
						</script>
						
						
						<ul class="nav navbar-top-links navbar-right">
							<li><span>${sessionScope.username}</span></li>
							<li><a href="login.html"> <i class="fa fa-sign-out"></i>
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
						<h2>用户组别列表</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>用户组别管理</a></li>
							<li><strong>用户组别统计</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>

				
				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							
						<div class="col-lg-6">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>用户组别统计</h5>
                                
                            </div>
                            
                            <div class="ibox-content">
                               <div class="echarts" id="charts"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                    <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>上月新增用户统计</h5>
                            </div>
                            <div class="ibox-content">
                                <div class="echarts" id="lastcharts"></div>
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
				
		
		function(){
			
			chart1();
			
			chart2();
		}
		
		
		
		);
		
		function chart1(){
			var usergroupcounts =${usergroupcounts };
			
			var namearray=new Array();
			var valuearray =new Array();
			
			var seriesdata =new Array();
			
			$.each(usergroupcounts,
					function(i, gcounts) {
				
				var value =gcounts.count;
				var name =gcounts.title;
				
				namearray[i] =name;
				
				var obj =new Object();
				obj.value=value;
				obj.name =name;
				
				seriesdata[i] =obj;
				
			});
			
			alert(1);
			alert(seriesdata);
			
			var charts = echarts.init(document.getElementById("charts"));
			
			var chartsoption = {
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					x: 'left',
					data: namearray
				},
				calculable: true,
				series: [{
					name: '组别信息',
					type: 'pie',
					radius: '55%',
					center: ['50%', '60%'],
					data: seriesdata
				}]
			};
			charts.setOption(chartsoption);
		};
		
		
		
		function chart2(){
			
			

			var lastusergroupcounts =${lastcountslist };
			
			var namearray=new Array();
			var valuearray =new Array();
			
			var seriesdata =new Array();
			
			$.each(lastusergroupcounts,
					function(i, gcounts) {
				
				var value =gcounts.count;
				var name =gcounts.title;
				
				namearray[i] =name;
				
				var obj =new Object();
				obj.value=value;
				obj.name =name;
				
				seriesdata[i] =obj;
				
			});
			
			alert(1);
			alert(seriesdata);
			
			var charts = echarts.init(document.getElementById("lastcharts"));
			
			var chartsoption = {
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					x: 'left',
					data: namearray
				},
				calculable: true,
				series: [{
					name: '组别信息',
					type: 'pie',
					radius: '55%',
					center: ['50%', '60%'],
					data: seriesdata
				}]
			};
			charts.setOption(chartsoption);
			
			
		}

			
			
			
	</script>


	<!-- Mainly scripts -->

   
    <script src="js/bootstrap.min.js?v=3.4.0"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Flot -->
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/hplus.js?v=2.2.0"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
    
	<!-- ECharts -->
	<script src="js/plugins/echarts/echarts-all.js"></script>







</body>

</html>
