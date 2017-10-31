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
										信誉规则
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">系统配置</a><i class="icon-angle-right"></i></li>
							<li><a href="#">信誉规则</a></li>
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
									<a  class="btn blue" href="insRules"><i class="icon-pencil"></i>新增信誉规则</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>枚举业务类型</th>
												<th>参数键</th>
												<th>参数值</th>
												<th>起始次数</th>
												<th>标题</th>
												<th>描述</th>
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
	    menuact("03_04_10");
	});
	
	function queryinfo(index){
		
		if(index==0||index==""){
			index =1;
		}
		
		ajaxdatas ="index="+index;
		
		$.ajax({
			type : "GET",
			url : "queryRules",
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
						var id=item.id;
						var ebusiness_type =item.ebusiness_type;
						var param_key =item.param_key;
						var param_value =item.param_value;
						var start_count =item.start_count;
						var title =item.title;
						var descr =item.descr;
						
						var One="";
						switch(ebusiness_type){
							case 1:
								One="商品评分";
								break;
							case 2:
								One="店铺评分";
								break;
							case 3:
								One="未发货导致取消";
								break;
							case 4:
								One="超过72小时";
								break;
							case 5:
								One="商家原因退换货";
								break;
							case 6:
								One="商家无贷退款";
								break;
							case 7:
								One="买家退款不处理";
								break;
						}
						 
						 var rowhtml ="<tr>"+
							"<td>"+no+"</td>"+
							"<td>"+One+"</td>"+
							"<td>"+param_key+"</td>"+
							"<td>"+param_value+"</td>"+
							"<td>"+start_count+"</td>"+
							"<td>"+title+"</td>"+
							"<td>"+descr+"</td>"+
							"<td>"+"<a href='readRules?id="+id+"'>修改</a>&nbsp<a href='delRules?id="+id+"'>删除</a>"+"</td>"+
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