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
								账户结算主表	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户结算信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">账户结算主表</a></li>
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
									<a  class="btn blue" href="insUserBalanceLog"><i class="icon-pencil"></i>新增账户结算主表</a>
								</div>
							</div>
							
							<div class="portlet-body">
								<div class=row_fluid>
									<ul>
										<li>用户ID：<input type="text" id="user_id" class="small m-wrap" ></li>
										<li>角色类型:
											<select id="role_type"  style="width:102px;">
												<option value="">请选择</option>
												<option value="Buyer">用户</option>
												<option value="Shop">商家</option>
												<option value="Agent">代理商</option>
												<option value="Manager">管理员</option>
											</select></li>
										<li>订单来源:<select id="order_platform"  style="width:102px;">
											<option value="">请选择</option>
											<option value="1">满集网</option>
											<option value="2">生活服务</option>
										</select></li>
										<li>订单状态:<select id="balance_state"  style="width:102px;">
											<option value="">请选择</option>
											<option value="1">待结算</option>
											<option value="2">已结算</option>
											<option value="4">已作废</option>
										</select></li>
										<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索
										</a></li>
									</ul>
								</div>
							<!-- <div class="row_fluid ">
								<ul>
	                <li>用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
	                <li>卡号：<input type="text" id="banktypevalue" class="small m-wrap" ></li>
	                <li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
                </ul>   
							</div> -->
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<td>用户Id</td>
<td>用户角色</td>
<td>用户角色值</td>
<td>订单来源</td>
<td>订单类型</td>
<td>订单编号</td>
<!-- <td>记录标识</td> -->
<td>订单标题</td>
<td>查看地址</td>
<td>结算金额</td>
<td>结算代金券</td>
<td>结算积分</td>
<td>预期结算时间</td>
<td>实际结算时间</td>
<td>结算状态</td>
<td>记录写入时间</td>
<td>是否删除</td>

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
		    menuact("08_04_01");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}




		var ajaxdatas ="index="+index;


		if($("#user_id").val()!=""){
			ajaxdatas+="&user_id="+$("#user_id").val();
		}
		if($("#role_type").val()!=""){
			ajaxdatas+="&role_type="+$("#role_type").val();
		}
		if($("#order_platform").val()!=""){
			ajaxdatas+="&order_platform="+$("#order_platform").val();
		}
		if($("#balance_state").val()!=""){
			ajaxdatas+="&balance_state="+$("#balance_state").val();
		}


		$.ajax({
		type : "GET",
		url : "queryUserBalanceLog",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : ajaxdatas,
		success : function(freshdata) {
			console.log(freshdata);
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
					var	user_id=item.user_id;
					var	role_type=item.role_type;
					var	role_value=item.role_value;
					var	order_platform=item.order_platform;
					var	order_type=item.order_type;
					var	order_no=item.order_no;
					var	balance_hash=item.balance_hash;
					var	order_title=item.order_title;
					var	order_url=item.order_url;
					var	balance_amount=item.balance_amount;
					var	balance_voucher=item.balance_voucher;
					var	balance_point=item.balance_point;
					var	will_balance_date=item.will_balance_date;
					var	real_balance_date=item.real_balance_date;
					var	balance_state=item.balance_state;
					var	add_time=item.add_time;
					var	is_del=item.is_del


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
						case "Manager":
							role_type="管理员"
							break
					}

					switch (order_platform){
						case 1:
							order_platform="满集网"
							break
						case 2:
							order_platform="生活服务类"
							break
					}

					switch (balance_state){
						case 1:
							balance_state="待结算"
							break
						case 2:
							balance_state="已结算"
							break
						case 4:
							balance_state="已作废"
							break
					}

					switch (is_del){
						case 0:
							is_del="未删除"
							break
						case 1:
							is_del="已删除"
							break
					}




					var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+role_type+"</td>"+
					 "<td>"+role_value+"</td>"+
					 "<td>"+order_platform+"</td>"+
					 "<td>"+order_type+"</td>"+
					 "<td>"+order_no+"</td>"+
					/*  "<td>"+balance_hash+"</td>"+ */
					 "<td>"+order_title+"</td>"+
					 "<td>"+order_url+"</td>"+
					 "<td>"+balance_amount+"</td>"+
					 "<td>"+balance_voucher+"</td>"+
					 "<td>"+balance_point+"</td>"+
					 "<td>"+will_balance_date+"</td>"+
					 "<td>"+real_balance_date+"</td>"+
					 "<td>"+balance_state+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+is_del+"</td>"+

					 "<td>"+"<a href='readUserBalanceLog?id="+id+"'>修改</a><br><a href='delUserBalanceLog?id="+id+"'>删除</a>"+"</td>"+
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