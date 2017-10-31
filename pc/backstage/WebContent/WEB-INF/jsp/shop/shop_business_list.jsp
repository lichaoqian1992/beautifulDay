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
						<jsp:include page="/WEB-INF/jsp/banner.html" flush="true"/>
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
			<jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
		</div>

		<div class="page-content">

			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
										业务类别统计<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">业务类别统计</a></li>
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
									<a  class="btn blue" href="insBusiness"><i class="icon-pencil"></i>新增业务类别统计</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
					                 <li>业务名称：<input type="text" id="name" class="small m-wrap" ></li>
					                 <li>频道ID：<input type="text" id="channel_id" class="small m-wrap" ></li>
					                 <li style="width:80px"><a class="btn btn-primary search_sub blue" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
				                </ul> 
							</div>
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>业务名称</th>
												<th>业务简称</th>
												<th>对应频道ID</th>
												<th>业务简介</th>
												<th>申请类别</th>
												<th>业务有效期</th>
												<th>默认保证金</th>
												<th>默认手续费</th>
												<th>默认提成比例</th>
												<th>电子协议</th>
												<th>当前状态</th>
												<th>添加时间</th>
												
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
		    onkey(3);
		    queryinfo(1);
		    setactive("331");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	if($("#name").val()!=""){
		ajaxdatas+="&name="+$("#name").val();
	}
	
	if($("#channel_id").val()!=""){
		ajaxdatas+="&channel_id="+$("#channel_id").val();
	}
	
	
	
	$.ajax({
		type : "GET",
		url : "queryBusiness",
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
					var name =item.name;
					var call_index =item.call_index;
					var channel_id =item.channel_id;
					var content =item.content;
					var role_type =item.role_type; 
					var valid_time =item.valid_time;
					var deposit =item.deposit;
					var poundage =item.poundage;
					var percentage =item.percentage;
					var agreement =item.agreement;
					var status =item.status;
					var add_time =item.add_time;

					var statusStr = "";
					switch(status){
					   case 0:
					   	  statusStr = "未生效";
					   	  break;
					   case 1:
					   	  statusStr = "已生效";
					   	  break;
					   case 2:
					   	  statusStr = "已禁用";
					   	  break;
					   default:
						  statusStr = "";
					}
					
					
					 var rowhtml ="<tr>"+
						"<td>"+no+"</td>"+
						"<td>"+name+"</td>"+
						"<td>"+call_index+"</td>"+
						"<td>"+channel_id+"</td>"+
						"<td>"+content+"</td>"+
						"<td>"+role_type+"</td>"+
						"<td>"+valid_time+"</td>"+
						"<td>"+deposit+"</td>"+
						"<td>"+poundage+"</td>"+
						"<td>"+percentage+"</td>"+
						"<td>"+agreement+"</td>"+
						"<td>"+statusStr+"</td>"+
						"<td>"+add_time+"</td>"+
						"<td>"+"<a href=readBusiness?id="+id+">修改</a><br><a href=delBusiness?id="+id+">删除</a>"+"</td>"+
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