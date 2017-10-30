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
									投诉记录信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">投诉信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">投诉记录信息</a></li>
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
									<a  class="btn blue" href="insComplaint"><i class="icon-pencil"></i>新增投诉记录信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
								<div class=row_fluid>
									<ul>
										<li>投诉人ID：<input type="text" id="from_user_id" class="small m-wrap" ></li>
									   	<li>被投诉人ID：<input type="text" id="to_user_id" class="small m-wrap" ></li>
									   	<li>投诉状态：
											<select  id="state"	 style="width:102px">
												<option value="-1">不限</option>
												<option value="0">待受理</option>
												<option value="1">处理中</option>
												<option value="2">未处理</option>
												<option value="3">用户胜诉</option>
												<option value="4">商家胜诉</option>
											</select>
										</li>
										<li>订单状态：
											<select type="text" id="order_status" style="width:102px">
												<option value="-1">不限</option>
												<option value="1">生成订单</option>
												<option value="2">确认订单</option>
												<option value="3">完成订单</option>
												<option value="4">取消订单</option>
												<option value="5">锁定订单</option>
												<option value="6">卖家取消订单</option>
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
												
<td>投诉人ID      </td>

<td>被投诉人ID    </td>

<td>投诉标题      </td>
<td>投诉详细内容  </td>
<td>投诉相关图片  </td>
<td>关联ID        </td>
<td>投诉时间      </td>
<td>投诉分配人员  </td>
<td>投诉状态      </td>
<td>投诉人联系电话</td>
<td>投诉人姓名    </td>
<td>投诉所在地区  </td>
<td>关联类型      </td>
<td>订单状态      </td>



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
		    menuact("06_01_01");
		    queryinfo(1);
		   
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	  if($("#from_user_id").val()!=""){
		ajaxdatas+="&from_user_id="+$("#from_user_id").val();
	}
	  if($("#to_user_id").val()!=""){
		ajaxdatas+="&to_user_id="+$("#to_user_id").val();
	}
	  if($("#state").val()!=""){
		ajaxdatas+="&state="+$("#state").val();
	}
	  if($("#order_status").val()!=""){
		ajaxdatas+="&order_status="+$("#order_status").val();
	}
	
	$.ajax({
		type : "GET",
		url : "queryComplaint",
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
					var from_user_id =item.from_user_id;
					var to_user_id =item.to_user_id;
					var type =item.type;
					var title =item.title;
					var content =item.content;
					var pics =item.pics;
					var correlation_order_id =item.correlation_order_id;
					var addtime =item.addtime;
					var handle_user_id =item.handle_user_id;
					var state =item.state;
					var mobile =item.mobile;
					var name =item.name;
					var area =item.area;
					var orderid_type =item.orderid_type;
					var order_status =item.order_status;
					

				  var stateStr = "";
		          switch (state) {
		            case 0:
		              stateStr = "待受理";
		              break;
		            case 1:
		              stateStr = "处理中";
		              break;
		            case 2:
		              stateStr = "未处理";
		              break;
		            case 3:
		              stateStr = "用户胜诉";
		              break;
		            case 4:
		              stateStr = "商家胜诉";
		              break;
		            default:
		              stateStr = "";
		            }
				  var order_statusStr = "";
		          switch (order_status) {
		            case 1:
		              order_statusStr = "生成订单";
		              break;
		            case 2:
		              order_statusStr = "确认订单";
		              break;
		            case 3:
		              order_statusStr = "完成订单";
		              break;
		            case 4:
		              order_statusStr = "取消订单";
		              break;
		            case 4:
		              order_statusStr = "锁定订单";
		              break;
		            case 4:
		              order_statusStr = "卖家取消订单";
		              break;
		            default:
		              order_statusStr = "";
		            }
					
		          	var arr = new Array();
		          	var img = "";
		          	arr = pics.split(',');
		          	for(var i=0;i<arr.length;i++){
		          		img += "<img style='width:100px' src="+arr[i]+">";
		          	}
				
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+from_user_id+"</td>"+
					 "<td>"+to_user_id+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+content+"</td>"+
					 "<td>"+img+"</td>"+
					 "<td>"+correlation_order_id+"</td>"+
					 "<td>"+addtime+"</td>"+
					 "<td>"+handle_user_id+"</td>"+
					 "<td>"+stateStr+"</td>"+
					 "<td>"+mobile+"</td>"+
					 "<td>"+name+"</td>"+
					 "<td>"+area+"</td>"+
					 "<td>"+orderid_type+"</td>"+
					 "<td>"+order_statusStr+"</td>"+
					 "<td>"+"<a href='readComplaint?id="+id+"'>修改</a>&nbsp<a href='delComplaint?id="+id+"'>删除</a>"+"</td>"+
					 "</tr>";
					
					tablehtml+=rowhtml;
							 
				})
				$("#tablebody").html(tablehtml);
				
				
				deviderset(totalcount,pagecount,pageindex);
			}else{
				alert("暂无数据！");
			}
			
			
			
		},error:function(){
			console.log("error");
		}
	
	});
	
	}
	
	</script>

</body>

</html>