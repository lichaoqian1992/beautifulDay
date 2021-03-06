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
										第三方支付信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单关联信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">第三方支付信息</a></li>
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
									<a  class="btn blue" href="insOrderPartner"><i class="icon-pencil"></i>新增第三方支付信息</a>
								</div>
							</div>
							
							<div class="portlet-body">

								<div class=row_fluid>
									<ul>
										付款用户ID：<input type="text" id="user_id" class="small m-wrap" >
										订单号：<input type="text" id="order_no" class="small m-wrap" >
										付款用户角色:
											<select id="user_role_type"  style="width:102px;">
												<option value="">请选择</option>
												<option value="Buyer">用户</option>
												<option value="Shop">商家</option>
												<option value="Agent">代理商</option>
												<option value="Manager">管理员</option>
											</select>
										支付状态:<select id="pay_status"  style="width:102px;">
											<option value="-1">请选择</option>
											<option value="0">待支付</option>
											<option value="1">已支付</option>
											<option value="3">退款中</option>
											<option value="4">已退款</option>
										</select>

										<a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a>
									</ul>
								</div>




								<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>付款用户ID</th>
												<th>付款用户角色</th>
												<th>付款用户角色值</th>
												<th>合作通道</th>
												<th>订单号</th>
												<th>订单标题</th>
												<th>订单金额</th>
												<th>支付状态</th>
												<!-- <th>支付成功同步回调地址</th>
												<th>支付成功异步通知地址</th> -->
												<th>异步通知次数</th>
												<th>异步通知结果</th>
												<th>异步通知状态</th>
												<th>添加时间</th>
												<th>支付时间</th>
												<th>退款申请时间</th>
												<th>退款成功时间</th>
												<th>是否删除</th>
												<th>操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">
										</tbody>
										</table>
									<div class="row-fluid">
										<div class="span6">
											<div class="dataTables_info" id="dividersummery"></div>
										</div>
										<div class="span6">
											<div class="dataTables_paginate paging_bootstrap pagination" id="devider">
												
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
		    menuact("09_03_05");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}




		var ajaxdatas ="index="+index;


		if($("#user_id").val()!=""){
			ajaxdatas+="&user_id="+$("#user_id").val();
		}
		if($("#user_role_type").val()!=""){
			ajaxdatas+="&user_role_type="+$("#user_role_type").val();
		}
		if($("#order_no").val()!=""){
			ajaxdatas+="&order_no="+$("#order_no").val();
		}

		ajaxdatas+="&pay_status="+$("#pay_status").val();




		$.ajax({
		type : "GET",
		url : "queryOrderPartner",
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
					var user_id=item.user_id;
					var user_role_type=item.user_role_type;
					var user_role_value=item.user_role_value;
					var partner_channel=item.partner_channel;
					var order_no=item.order_no;
					var order_title=item.order_title;
					var order_money=item.order_money;
					var pay_status=item.pay_status;
					/* var return_url=item.return_url;
					var notify_url=item.notify_url; */
					var notify_number=item.notify_number;
					var notify_result=item.notify_result;
					var notify_status=item.notify_status;
					var add_time=item.add_time;
					var pay_time=item.pay_time;
					var back_time=item.back_time;
					var over_time=item.over_time;
					var is_del=item.is_del;
					
					var pay_statusStr = "";
					switch (pay_status) {
				    case 0:
				      pay_statusStr = "待支付";
				      break;
				    case 1:
				      pay_statusStr = "已支付";
				      break;
				    case 3:
				      pay_statusStr = "退款中";
				      break;
				    case 4:
				      pay_statusStr = "已退款";
				      break;
				    default:
				      pay_statusStr = "";
				    }
					var notify_statusStr = "";
					   switch (notify_status) {
			            case 0:
			            	notify_statusStr = "未通知";
			              break;
			            case 1:
			            	notify_statusStr = "未送达";
			              break;
			            case 2:
			            	notify_statusStr = "已送达";
			              break;
			            default:
			            	notify_statusStr = "";
			            }



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


					switch (is_del){
						case 0:
							is_del="否"
							break
						case 1:
							is_del="是"
							break
					}

					switch (notify_status){
						case 0:
							notify_status="未通知"
							break
						case 1:
							notify_status="未送达"
							break
						case 2:
							notify_status="已送达"
							break
					}


					 
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+partner_channel+"</td>"+
					 "<td>"+order_no+"</td>"+
					 "<td>"+order_title+"</td>"+
					 "<td>"+order_money+"</td>"+
					 "<td>"+pay_statusStr+"</td>"+
					/*  "<td>"+return_url+"</td>"+
					 "<td>"+notify_url+"</td>"+ */
					 "<td>"+notify_number+"</td>"+
					 "<td>"+notify_result+"</td>"+
					 "<td>"+notify_status+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+pay_time+"</td>"+
					 "<td>"+back_time+"</td>"+
					 "<td>"+over_time+"</td>"+
					 "<td>"+is_del+"</td>"+
					 "<td>"+"<a href='readOrderPartner?id="+id+"'>修改</a><a href='delOrderPartner?id="+id+"'>删除</a>"+"</td>"+
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
	
	function deviderset(totalcount,pagecount,pageindex){
		
		
		var summery ="共"+totalcount+"条数据--共"+pagecount+"页";
		$("#dividersummery").html(summery);
		
		var deviderhtml="";
		if(pageindex==1){
			deviderhtml+="<ul><li  class='prev ' style='display:none'><a  onclick='queryinfo("+Number(pageindex-1)+")'>← <span class='hidden-480'>Prev</span></a></li>";
		}else{
			deviderhtml+="<ul><li  id='firstdiv' class='prev '><a  onclick='queryinfo("+Number(pageindex-1)+")'>← <span class='hidden-480'>Prev</span></a></li>";
		}
		
		
		if(pagecount<6){
			
			for(var i=1;i<pageindex;i++){
				
				deviderhtml+="<li ><a onclick='queryinfo("+i+")'>"+i+"</a></li>";
				
			}
			
			
		}else{
			
			if(pageindex<3){
				for(var j=1;j<6;j++){
					
					deviderhtml+="<li ><a onclick='queryinfo("+j+")'>"+j+"</a></li>";
					
				}
				
			}else if(pageindex<=pagecount-2){
				
				var m =pageindex-2;
				for(var k=0;k<5;k++){
					
					deviderhtml+="<li ><a onclick='queryinfo("+m+")'>"+m+"</a></li>";
					m++;
				}
				
				
			}else if(pageindex>pagecount-2){
				var n =pageindex-4;
				for(var q=0;q<5;q++){
					deviderhtml+="<li ><a onclick='queryinfo("+n+")'>"+n+"</a></li>";
					n++;
				}
			}
			
		}
		

		if(pageindex ==pagecount){
			deviderhtml+="<li id='lastdiv ' style='display:none'><a  onclick='queryinfo("+Number(pageindex+1)+")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
		}else{
			deviderhtml+="<li id='lastdiv '><a  onclick='queryinfo("+Number(pageindex+1)+")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
		}
		
		$("#devider").html(deviderhtml);
	}

	</script>

</body>

</html>