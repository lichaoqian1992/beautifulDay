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
								商家托管商家店铺关联	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家集团信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家托管商家店铺关联</a></li>
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
									<a  class="btn blue" href="insUserRoleShopinfoGrouprelation"><i class="icon-pencil"></i>新增商家托管商家店铺关联</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
					                <li>店铺ID：<input type="text" id="shop_id" class="small m-wrap" ></li>
					                <li>管理类型:
										<select style="width:102px;" id="manager_type">
										  <option value="-1"></option>
										  <option value="0">直营店</option>
										  <option value="1">加盟店</option>
										</select>
									</li>
									<li>托管状态:
										<select style="width:102px;" id="status">
										  <option value="-1"></option>
										  <option value="1">正常</option>
										  <option value="2">暂停</option>
										  <option value="3">删除</option>
										  <option value="4">加盟申请中</option>
										  <option value="5">加盟拒绝</option>
										  <option value="6">拒绝再申请</option>
										  <option value="7">商家暂停</option> 
										</select>
									</li>
					                <li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
				                </ul>   
							</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<td>店铺ID</td>
												<td>管理类型</td>
												<td>集团店铺</td>
												<td>托管状态</td>
												<td>创建时间</td>
												<td>更新时间</td>
												<td>更新说明</td>
												<td>Auditinfo</td>
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
		    menuact("02_03_02");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#shop_id").val()!=""){
		ajaxdatas+="&shop_id="+$("#shop_id").val();
	}
	if($("#manager_type").val()!=""){
		ajaxdatas+="&manager_type="+$("#manager_type").val();
	}
	if($("#status").val()!=""){
		ajaxdatas+="&status="+$("#status").val();
	}
	
	
	$.ajax({
		type : "GET",
		url : "queryUserRoleShopinfoGrouprelation",
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
					var	shop_id=item.shop_id;
					var	manager_type=item.manager_type;
					var	groupshop_id=item.groupshop_id;
					var	status=item.status;
					var	add_time=item.add_time;
					var	update_time=item.update_time;
					var	remark=item.remark;
					var	auditinfo=item.auditinfo;


					var One = "";
					
					switch(manager_type){
						case 0:
							One = "直营店";
						break;
						
						case 1:
							One = "加盟店";
						break;
						
						default:
							One = "";
						break;
					}
					
					var Tow = "";
					
					switch(status){
						
						case 1:
							Tow = "正常";
						break;
						
						case 2:
							Tow = "暂停";
						break;
						
						case 3:
							Tow = "删除";
						break;
						
						case 4:
							Tow = "加盟申请中";
						break;
						
						case 5:
							Tow = "加盟拒绝";
						break;
						
						case 6:
							Tow = "拒绝再申请";
						break;
						
						case 7:
							Tow = "商家暂停";
						break;
						
						default:
							Tow = "";
						break;
					}
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+shop_id+"</td>"+
					 "<td>"+One+"</td>"+
					 "<td>"+groupshop_id+"</td>"+
					 "<td>"+Tow+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+auditinfo+"</td>"+

					 "<td>"+"<a href='readUserRoleShopinfoGrouprelation?id="+id+"'>修改</a><br><a href='delUserRoleShopinfoGrouprelation?id="+id+"'>删除</a>"+"</td>"+
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