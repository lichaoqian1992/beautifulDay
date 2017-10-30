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
										用户转账记录信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户变动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户转账记录信息</a></li>
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
									<!-- <a  class="btn blue"><i class="icon-pencil"></i>新增</a> -->
								</div>
							</div>
							
							<div class="portlet-body">
							
							<div class="row_fluid">
								<ul>
									<li>转账用户ID：<input type="text" id="useridstr" class="small m-wrap" ></li>
									<li>收账用户ID：<input  type="text" id="touseridstr" class="small m-wrap" ></li>
									<li>转账订单号：<input  type="text" id="ordernostr" class="large m-wrap" ></li>
									<li>转账标记：<select id="statusstr" class="small m-wrap" style="width:100px !important" >
														<option value="-1">不限</option>
														<option value="0">正常转款</option>
														<option value="1">借款</option>
														</select></li>
									<li style="width:80px;text-align:left;"><a class="btn btn-primary search_sub" style="margin-top:-9px;margin-left:12px;" onclick="queryinfo(1)">搜索</a></li>
								</ul>						
							</div>
							
							
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>转账订单号</th>
												<th>用户ID</th>
												<th>角色类别</th>
												<th>用户角色值</th>
												<th>对象用户ID</th>
												<th>对象角色类型</th>
												<th>对象角色值</th>
												<th>充值金额</th>
												<th>转账标记</th>
												<th>生成时间</th>
												<th>完成时间</th>
												<th>是否删除</th>
												<!-- <th>操作</th> -->
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
		    menuact("08_02_02");
		});
	
function queryinfo(inx){
		
		
		ajaxdatas ="index="+inx;
		if($("#useridstr").val()!=""){
			ajaxdatas+="&user_id="+$("#useridstr").val();
		}
		if($("#statusstr").val()!=""){
			ajaxdatas+="&status="+$("#statusstr").val();
		}
		if($("#ordernostr").val()!=""){
			ajaxdatas+="&transaction_no="+$("#ordernostr").val();
		}
		if($("#touseridstr").val()!=""){
			ajaxdatas+="&touser_id="+$("#touseridstr").val();
		}
		
		$.ajax({
			type : "GET",
			url : "queryTransaction",
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
						var user_id =item.user_id;
						var role_type =item.user_role_type;
						var role_value =item.user_role_value;
						var touser_id =item.touser_id;
						var touser_role_type =item.touser_role_type;
						var touser_role_value =item.touser_role_value;
						var transaction_no =item.transaction_no;
						var amount =item.amount;
						var status =item.status;
						var add_time =item.add_time;
						var complete_time =item.complete_time;
						var is_del =item.is_del;
						var remark =item.remark;


						switch (role_type){
							case "Buyer":
								role_type="用户"
								break
							case "Shop":
								role_type="商家"
								break
							case "Agent":
								role_type="代理商"
								break
						}
						
						var statusstr ="";
						switch(status){
						case 0:
							statusstr="正常转款";
							break;
						case 1:
							statusstr="借款";
							break;
						
						}
						

						switch(is_del){
						case 0:
							is_del="正常";
							break;
						case 1:
							is_del="删除";
							break;
						
						}
						
						rowhtml ="<tr>"+
								 "<td>"+no+"</td>"+
								 "<td>"+transaction_no+"</td>"+
								 "<td>"+user_id+"</td>"+
								 "<td>"+role_type+"</td>"+
								 "<td>"+role_value+"</td>"+
								 "<td>"+touser_id+"</td>"+
								 "<td>"+touser_role_type+"</td>"+
								 "<td>"+touser_role_value+"</td>"+
								 
								 "<td>"+amount+"</td>"+
								 "<td>"+statusstr+"</td>"+
								 "<td>"+add_time+"</td>"+
								 "<td>"+complete_time+"</td>"+
								 "<td>"+is_del+"</td>"+
								/*  "<td>"+"<a href='readTransaction?id="+id+"'>查看</a>"+"</td>"+ */
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