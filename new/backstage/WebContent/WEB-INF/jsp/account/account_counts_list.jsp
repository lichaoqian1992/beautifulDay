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
	
<style>
/* .graph{
 width: 400px;  
 height: 300px;  
 border: 1px dashed gainsboro;  
} */
</style>	
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
										账户数量统计<!-- <small>代理商列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户列表管理管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户数量统计</a></li>
						</ul>
					</div>
				</div>

				<div class="row-fluid margin-bottom-20">

				</div>

				<div class="row-fluid">
					<div  id="pie1" class="graph span4" style="height:300px;width:400px">
					</div>

					<div class="span6" id="table">
	           <div><h3>账户状态及数量统计：</h3></div>
	           <table class="table table-striped table-bordered table-hover dataTable">
	             <thead id="tableHead">
	              <tr>
	                <th>账户状态</th>
	                <th>数量</th>
	              </tr>
	            </thead>
	            <tbody id="tableBody">
	            
	            </tbody>
	           </table>
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
	<script src="media/js/jquery.flot.js" type="text/javascript" ></script>  
  <script src="media/js/jquery.flot.pie.js" type="text/javascript" ></script> 
	
	
	<script>

$(document).ready(
		
			function() {
				App.init();
				onkey(5);
				setactive("514");
			 	var datalist = ${accountpielist};
			 	console.info(datalist);
		  	var tablehtml='';
		    var rowhtml='';
        for(var i=0;i<datalist.length;i++){
	          rowhtml = '<tr>'+
	          '<td>'+datalist[i].state+'</td>'+
	          '<td>'+datalist[i].count+'</td>'+
	          '</tr>';
	          tablehtml+= rowhtml;
        }
        $("#tableBody").html(tablehtml);
			
			  var list=[];      
			  for(var i=0;i<datalist.length;i++){
				  list.push({});
				  list[i].label=datalist[i].state;
				  list[i].data =  datalist[i].count;
			  }
		
			            
        $.plot($("#pie1"), list, {  
            series: {  
                         pie: {   
                                    show: true,
                         }  
            } ,  
            legend: {  
                        show: false 
            }   
        });            
			 	
			});
	
	</script>

</body>

</html>