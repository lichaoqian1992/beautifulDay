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
								托管店铺开关		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">托管店铺开关</a></li>
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
									<a  class="btn blue" href="insBusinessGroupshop"><i class="icon-pencil"></i>新增托管店铺开关</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
									<li>店铺ID：<input type="text" id="shop_id" class="small m-wrap" ></li>
									<li>开关类型:
										<select style="width:102px;" id="type">
										  <option value="-1"></option>
										  <option value="0">内容</option>
										  <option value="1">订单</option>
										  <option value="2">财务</option>
										  <option value="3">人事</option>
										</select>
									</li>
									<li>开关:
										<select style="width:102px;" id="is_open">
										  <option value="-1"></option>
										  <option value="0">关</option>
										  <option value="1">开</option>
										</select>
									</li>
									<li>通知方式:
										<select style="width:102px;" id="notice_type">
										  <option value="-1"></option>
										  <option value="0">短信</option>
										  <option value="1">邮件</option>
										  <option value="2">人工</option>
										</select>
									</li>
					                <li>频道(业务id):
										<select style="width:102px;" id="channel_id">
											<option value="-1"></option>
										</select>
									</li>
									<li>是否删除:
										<select style="width:102px;" id="is_del">
										  <option value="-1"></option>
										  <option value="0">正常</option>
										  <option value="1">删除</option>
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
												<td>集团店ID</td>
												<td>店铺ID</td>
												<td>开关类型</td>
												<td>业务Id</td>
												<td>开关</td>
												<td>通知方式</td>
												<td>添加时间</td>
												<td>更新时间</td>
												<td>是否删除</td>
												<td>备注</td>

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
		    menuact("02_02_08");
		    $.ajax({
	            type : "GET",
	            url : "findChannelList",
	            dataType : "json",
	            contentType : "application/json; charset=utf-8",
	            data : {},
	            success : function(data) {
	              var dataList = data.result;
	              for(var i in dataList){
	                 $('#channel_id').append('<option value='+dataList[i].id+'>'+dataList[i].title+'</option>');
	              } 
	            }
	      	});
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#shop_id").val()!=""){
		ajaxdatas+="&shop_id="+$("#shop_id").val();
	}
	if($("#type").val()!=""){
		ajaxdatas+="&type="+$("#type").val();
	}
	if($("#channel_id").val()!=""){
		ajaxdatas+="&channel_id="+$("#channel_id").val();
	}
	if($("#is_open").val()!=""){
		ajaxdatas+="&is_open="+$("#is_open").val();
	}
	if($("#notice_type").val()!=""){
		ajaxdatas+="&notice_type="+$("#notice_type").val();
	}
	if($("#is_del").val()!=""){
		ajaxdatas+="&is_del="+$("#is_del").val();
	}
	
	
	$.ajax({
		type : "GET",
		url : "queryBusinessGroupshop",
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
					var	groupshop_id=item.groupshop_id;
					var	shop_id=item.shop_id;
					var	type=item.type;
					var	channel_id=item.title;
					var	is_open=item.is_open;
					var	notice_type=item.notice_type;
					var	add_time=item.add_time;
					var	update_time=item.update_time;
					var	is_del=item.is_del;
					var	remark=item.remark;


					var One = "";
					
					switch(type){
						case 0:
							One = "内容";
						break;
						
						case 1:
							One = "订单";
						break;
						
						case 2:
							One = "财务";
						break;
						
						case 3:
							One = "人事";
						break;
						
						default:
							One = "";
						break;
					}
					
					var Tow = "";
					
					switch(is_open){
						case 0:
							Tow = "关";
						break;
						
						case 1:
							Tow = "开";
						break;
						
						default:
							Tow = "";
						break;
					}
					

					var There = "";
					
					switch(notice_type){
						case 0:
							There = "短信";
						break;
						
						case 1:
							There = "邮件";
						break;
						
						case 2:
							There = "人工";
						break;
						
						default:
							There = "";
						break;
					}
					
					var Four = "";
					
					switch(is_del){
						case 0:
							Four = "正常";
						break;
						
						case 1:
							Four = "删除";
						break;
						
						default:
							Four = "";
						break;
					}
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+groupshop_id+"</td>"+
					 "<td>"+shop_id+"</td>"+
					 "<td>"+One+"</td>"+
					 "<td>"+channel_id+"</td>"+
					 "<td>"+Tow+"</td>"+
					 "<td>"+There+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+Four+"</td>"+
					 "<td>"+remark+"</td>"+


					 "<td>"+"<a href='readBusinessGroupshop?id="+id+"'>修改</a><br><a href='delBusinessGroupshop?id="+id+"'>删除</a>"+"</td>"+
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