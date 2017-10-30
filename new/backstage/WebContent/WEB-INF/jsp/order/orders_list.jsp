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
										订单信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">订单信息</a></li>
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
									<a  class="btn blue" href="insOrders"><i class="icon-pencil"></i>新增订单信息</a>
								</div>
							</div>
							
							<div class="portlet-body">


								<div class=row_fluid>
									<ul>
										<li>用户ID：<input type="text" id="user_id" class="small m-wrap" ></li>
										<li>订单状态:<select id="status"  style="width:102px;">
											<option value="">请选择</option>
											<option value="1">生成订单</option>
											<option value="2">确认订单</option>
											<option value="3">完成订单</option>
											<option value="4">取消订单</option>
											<option value="5">锁定订单</option>
											<option value="6">卖家取消订单</option>
											<option value="7">投诉冻结中</option>
										</select></li>
										<li>支付状态:<select id="payment_status"  style="width:102px;">
											<option value="">请选择</option>
											<option value="1">未支付</option>
											<option value="2">已支付</option>
											<option value="3">已预付</option>
											<option value="4">线下支付</option>
											<option value="5">免单</option>
											<option value="6">仅预订</option>
										</select></li>
										<li>退订状态:<select id="book_back_status"  style="width:102px;">
											<option value="">请选择</option>
											<option value="1">未申请</option>
											<option value="2">待确认</option>
											<option value="3">卖家同意</option>
											<option value="4">卖家不同意</option>
											<option value="5">全部退单</option>
										</select></li>

										<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索
										</a></li>
									</ul>
								</div>




								<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>订单标题</th>
												<th>订单号</th>
												<th>订单类型</th>
												<th>商家所属用户</th>
												<th>商家用户角色</th>
												<th>商家用户角色值</th>
												<th>购买用户ID</th>
												<th>购买用户角色</th>
												<th>购买用户角色值</th>
												<th>物流方式</th>
												<th>物流费用</th>
												<th>订单留言</th>
												<th>订单备注</th>
												<th>是否索要发票</th>
												<th>发票抬头</th>
												<th>应付总金额</th>
												<th>预付金额</th>
												<th>实付总金额</th>
												<th>订单总金额</th>
												<th>订单代金券</th>
												<th>订单总积分</th>
												<th>订单状态</th>
												<!-- <th>下单时间</th>
												<th>确认时间</th>
												<th>失效时间</th>
												<th>完成时间</th> -->
												<th>支付方式</th>
												<th>支付手续费</th>
												<th>支付状态</th>
												<!-- <th>支付时间</th> -->
												<!-- <th>结算状态</th> -->
												<!-- <th>结算时间</th> -->
												<!-- <th>是否需要回调</th>
												<th>回调地址</th> -->
												<!-- <th>通知地址</th> -->
												<th>回调通知状态</th>
											<!-- 	<th>成功失败时间</th> -->
												<th>是否删除</th>
												<th>退订状态</th>
												<th>商家活动id</th>
												<th>处理次数</th>
												<th>是否异常</th>
											<!-- 	<th>商家拒绝接单原因</th>
												<th>取消退单原因</th>
												<th>实际付款修改之前价格</th>
												<th>修改之前价格原因</th> -->
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
		    menuact("09_01_01");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}




		var ajaxdatas ="index="+index;


		if($("#user_id").val()!=""){
			ajaxdatas+="&user_id="+$("#user_id").val();
		}
		if($("#status").val()!=""){
			ajaxdatas+="&status="+$("#status").val();
		}
		if($("#payment_status").val()!=""){
			ajaxdatas+="&payment_status="+$("#payment_status").val();
		}
		if($("#book_back_status").val()!=""){
			ajaxdatas+="&book_back_status="+$("#book_back_status").val();
		}



	
	
	$.ajax({
		type : "GET",
		url : "queryOrders",
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
					console.log(item);
					var id=item.id ;
					var order_title=item.order_title ;
					var order_no=item.order_no ;
					var order_type=item.order_type ;
					var shop_user_id=item.shop_user_id ;
					var shop_user_role_type=item.shop_user_role_type ;
					var shop_user_role_value=item.shop_user_role_value ;
					var user_id=item.user_id ;
					var user_role_type=item.user_role_type ;
					var user_role_value=item.user_role_value ;
					var express_type=item.express_type ;
					var express_fee=item.express_fee ;
					var message=item.message ;
					var remark=item.remark ;
					var is_invoice=item.is_invoice ;
					var invoice_title=item.invoice_title ;
					var payable_amount=item.payable_amount ;
					var pre_payable_amount=item.pre_payable_amount ;
					var real_amount=item.real_amount ;
					var order_amount=item.order_amount ;
					var voucher=item.voucher ;
					var point=item.point ;
					var status=item.status ;
					var add_time=item.add_time ;
					var confirm_time=item.confirm_time ;
					var invalid_time=item.invalid_time ;
					var complete_time=item.complete_time ;
					var payment_id=item.payment_id ;
					var payment_fee=item.payment_fee ;
					var payment_status=item.payment_status ;
					var payment_time=item.payment_time ;
					var settlement_status=item.settlement_status ;
					var settlement_time=item.settlement_time ;
					var is_callback=item.is_callback ;
					var back_url=item.back_url ;
					var notice_url=item.notice_url ;
					var back_status=item.back_status ;
					var back_time=item.back_time ;
					var is_del=item.is_del ;
					var book_back_status=item.book_back_status ;
					var activity_id=item.activity_id ;
					var exec_num=item.exec_num ;
					var is_normal=item.is_normal ;
					var reject_remark=item.reject_remark ;
					var cancelremark=item.cancelremark ;
					var old_real_amount=item.old_real_amount ;
					var cancel_amount_remark=item.cancel_amount_remark ;

					switch (shop_user_role_type){
						case "Buyer":
							shop_user_role_type="用户"
							break
						case "Shop":
							shop_user_role_type="商家"
							break
						case "Agent":
							shop_user_role_type="代理商"
							break
						case "Manager":
							shop_user_role_type="管理员"
							break
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





					
					settlement_status == 0 ? settlement_status = "未结算" : settlement_status = "已结算";
					is_callback == 0 ? is_callback = "不需要" : is_callback = "需要";
					is_del == 0 ? is_del = "正常" : is_del = "删除";
					is_normal == 1 ? is_normal = "正常" : is_normal = "异常";
					var statusStr = "";
					switch (status) {
				    case 1:
				      statusStr = "生成订单";
				      break;
				    case 2:
				      statusStr = "确认订单";
				      break;
				    case 3:
				      statusStr = "完成订单";
				      break;
				    case 4:
				      statusStr = "取消订单";
				      break;
				    case 5:
				      statusStr = "锁定订单";
				      break;
				    case 6:
				      statusStr = "卖家取消订单";
				      break;
				    case 7:
				      statusStr = "投诉冻结中";
				      break;
				    default:
				      statusStr = "";
				    }
					var payment_statusStr = "";
			    switch (payment_status) {
            case 1:
            	payment_statusStr = "未支付";
              break;
            case 2:
            	payment_statusStr = "已支付";
              break;
            case 3:
            	payment_statusStr = "已预付";
              break;
            case 4:
            	payment_statusStr = "线下支付";
              break;
            case 5:
            	payment_statusStr = "免单";
              break;
            case 6:
            	payment_statusStr = "仅预定";
              break;
            default:
            	payment_statusStr = "";
	            }
			   var back_statusStr = "";
			   switch (back_status) {
            case 1:
            	back_statusStr = "已成功";
              break;
            case 2:
            	back_statusStr = "已失败";
              break;
            case 0:
            	back_statusStr = "等待通知";
              break;
            default:
            	back_statusStr = "";
	              }
				var book_back_statusStr = "";
				switch (book_back_status) {
	            case 1:
	            	book_back_statusStr = "未申请";
	              break;
	            case 2:
	            	book_back_statusStr = "待确认";
	              break;
	            case 3:
	            	book_back_statusStr = "卖家同意";
	              break;
	            case 4:
	            	book_back_statusStr = "卖家不同意";
	              break;
	            case 5:
	            	book_back_statusStr = "全部退单";
	              break;
	            case 6:
	            	book_back_statusStr = "部分退单";
	              break;
	            default:
	            	book_back_statusStr = "";
	              }
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+order_title+"</td>"+
					 "<td>"+order_no+"</td>"+
					 "<td>"+order_type+"</td>"+
					 "<td>"+shop_user_id+"</td>"+
					 "<td>"+shop_user_role_type+"</td>"+
					 "<td>"+shop_user_role_value+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+express_type+"</td>"+
					 "<td>"+express_fee+"</td>"+
					 "<td>"+message+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+is_invoice+"</td>"+
					 "<td>"+invoice_title+"</td>"+
					 "<td>"+payable_amount+"</td>"+
					 "<td>"+pre_payable_amount+"</td>"+
					 "<td>"+real_amount+"</td>"+
					 "<td>"+order_amount+"</td>"+
					 "<td>"+voucher+"</td>"+
					 "<td>"+point+"</td>"+
					 "<td>"+statusStr+"</td>"+
				/* 	 "<td>"+add_time+"</td>"+
					 "<td>"+confirm_time+"</td>"+
					 "<td>"+invalid_time+"</td>"+
					 "<td>"+complete_time+"</td>"+ */
					 "<td>"+payment_id+"</td>"+
					 "<td>"+payment_fee+"</td>"+
					 "<td>"+payment_statusStr+"</td>"+
					 /* "<td>"+payment_time+"</td>"+ */
					/*  "<td>"+settlement_status+"</td>"+ */
					 /* "<td>"+settlement_time+"</td>"+
					  "<td>"+is_callback+"</td>"+
					 "<td>"+back_url+"</td>"+ */
					/*  "<td>"+notice_url+"</td>"+ */
					 "<td>"+back_statusStr+"</td>"+
					 /* "<td>"+back_time+"</td>"+ */
					 "<td>"+is_del+"</td>"+
					 "<td>"+book_back_statusStr+"</td>"+
					 "<td>"+activity_id+"</td>"+
					 "<td>"+exec_num+"</td>"+
					 "<td>"+is_normal+"</td>"+
				/* 	 "<td>"+reject_remark+"</td>"+
					 "<td>"+cancelremark+"</td>"+
					 "<td>"+old_real_amount+"</td>"+
					 "<td>"+cancel_amount_remark+"</td>"+ */
					 "<td>"+"<a href='readOrders?id="+id+"'>修改</a><a href='delOrders?id="+id+"'>删除</a>"+"</td>"+
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