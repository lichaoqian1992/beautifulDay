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
										用户余额日志<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户变动日志</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户余额日志</a></li>
						</ul>
					</div>
				</div>

				<div class="row-fluid margin-bottom-20">

				</div>

				<div class="row-fluid">

					<div class="span12">

						<div class="portlet box grey">
							<div class="portlet-title" style="height:20px">
								<div class="caption"> </div>
								<div class="actions">
									<!-- <a  class="btn blue"><i class="icon-pencil"></i>新增</a> -->
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row-fluid ">
								<div class="span2">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>

								<div class="span2">
									角色类型:
									<select id="role_type"  style="width:102px;">
										<option value="">请选择</option>
										<option value="Buyer">用户</option>
										<option value="Shop">商家</option>
										<option value="Agent">代理商</option>
										<option value="Manager">管理员</option>
									</select>
								</div>
								<div class="span2"><a  class="btn btn-primary search_sub"
														onclick="queryinfo(1)">搜索</a></div>
								
								
							</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>用户ID</th>
												<th>用户角色类型</th>
												<th>用户角色值</th>
												<th>账户ID</th>
												<th>增减值</th>
												<th>所属订单号</th>
												<th>操作类别</th>
												<th>备注说明</th>
												<th>增加时间</th>
												<th>变动前金额</th>
												<th>变动后金额</th>
												<th>变动发生IP</th>
												<th>三方支付交易号</th>
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
		    menuact("08_03_01");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
		if($("#role_type").val()!=""){
			ajaxdatas+="&user_role_type="+$("#role_type").val();
		}
	
	$.ajax({
		type : "GET",
		url : "queryAmountLog",
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
					var id =item.id;
					var user_id =item.user_id;
					var user_role_type =item.user_role_type;
					var user_role_value =item.user_role_value;
					var account_id =item.account_id;
					var value =item.value;
					var order_no =item.order_no;
					var type =item.type;
					var remark =item.remark;
					var add_time =item.add_time;
					var old_value =item.old_value;
					var new_value =item.new_value;
					var user_ip =item.user_ip;
					var transaction_no =item.transaction_no;
					var no =Number(i+1);

					switch (user_role_type){
						case "Buyer":
							user_role_type="用户"
							break
						case "Shop":
							user_role_type="商家"
							break
						case "Agent":
							user_role_type="代理商"
							break
						case "Manager":
							user_role_type="管理员"
							break
					}

					 
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+account_id+"</td>"+
					 "<td>"+value+"</td>"+
					 "<td>"+order_no+"</td>"+
					 "<td>"+type+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+old_value+"</td>"+
					 "<td>"+new_value+"</td>"+
					 "<td>"+user_ip+"</td>"+
					 "<td>"+transaction_no+"</td>"+
					 "<td>"+"<a href='readAmountLog?id="+id+"'>修改</a>"+"</td>"+
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