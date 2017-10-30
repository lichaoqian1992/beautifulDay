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
			<!--  <ul class="page-sidebar-menu hidden-phone hidden-tablet " id="menuid" >
		        <li>
		          <div class="sidebar-toggler hidden-phone"></div> 
		        </li> 
		        
		    </ul>  -->
			
			
		</div>

		<div class="page-content">


		<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="toMain">Home</a> 
								<!-- <i class="icon-angle-right"></i> -->
							</li>
							<!-- <li>
								<a href="#">UI Features</a>
								<i class="icon-angle-right"></i>
							</li>
							<li><a href="#">Buttons</a></li> -->
		</ul>
		<div class="portlet-body" id="floatmenu">
							</div>
			

			<!-- END PAGE CONTAINER-->

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
		    mainmenu(); 
		    menuact("01_01_01"); 
		});
	
	
	function mainmenu(){
		
		var menu =${sessionScope.menu};
		var number =menu.length;
		
			$("#floatmenu").append("<div class='row-fluid' id='one'></div>");
		
			$("#floatmenu").append("<div class='row-fluid' id='two'></div>");
			
		
			$("#floatmenu").append("<div class='row-fluid' id='three'></div>");
		
			
			var num =0;
			for(var i=0;i<menu.length;i++){
			var item =menu[i];
			var layer =item.layer;
			var title =item.title;
			var address =item.address;
			var url =item.url;
			var menustr ="";
			
			if(layer =="1"){
				
				num =Number(num+1);
				switch(title){
				case "用户":
					
					menustr="<a href='"
								+url+
							"' class='icon-btn span3'> <i class='icon-user'></i><div>用户</div></a>"
					break;
				case "商家":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-shopping-cart'></i><div>商家</div></a>"
					
					break;
				case "配置":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-cogs'></i><div>配置</div></a>"
					
					break;
				case "运维":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-wrench'></i><div>运维</div></a>"
					
					
					break;
				case "角色管理":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-group'></i><div>角色管理</div></a>"
					
					
					break;
				case "监控":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-eye-open'></i><div>监控</div></a>"
					
					
					break;
				case "代理":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-sitemap'></i><div>代理商</div></a>"
					
					
					break;
				case "账户":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-list-alt'></i><div>账户</div></a>"
					
					
					break;
				case "订单":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-barcode'></i><div>订单</div></a>"
					
					
					break;
				case "消息日志":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-file'></i><div>消息日志</div></a>"
					
					
					break;
				case "系统管理":
					menustr="<a href='"
						+url+
					"' class='icon-btn span3'> <i class='icon-windows'></i><div>系统管理</div></a>"
					
					break;
					case "新建查询":
						menustr="<a href='"
								+url+
								"' class='icon-btn span3'> <i class='icon-windows'></i><div>新建查询</div></a>"

						break;
				
				default :
					
					break;
				}
				
				if((num<=4)){
					$("#one").append(menustr);
				}else if(num<=8){
					$("#two").append(menustr);
				}else if(num<=12){
					$("#three").append(menustr);
				}
			}
			
		}
		
	}

	</script>

</body>

</html>