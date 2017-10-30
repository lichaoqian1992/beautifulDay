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
								买家家批量充值记录
		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户业务信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">买家批量充值记录</a></li>
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
									<a  class="btn blue" href="insUserFixed"><i class="icon-pencil"></i>新增买家批量充值记录</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
					                <li>用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
					                <li>状态:
										<select style="width:102px;" id="state">
										  <option value="-1"></option>
										  <option value="1">原始数据导入成功</option>
										  <option value="2">原始数据异常(用户或账户未找到)</option>
										  <option value="3">账户待充值</option>
										  <option value="4">账户预充值</option>
										  <option value="5">充值失败</option>
										  <option value="6">充值成功</option>
										  <option value="7">预撤销充值</option>
										  <option value="8">撤销充值失败</option>
										  <option value="9">撤销充值成功</option>
										  <option value="10">系统修正</option>
										  <option value="11">数据作废</option>
										</select>
									</li>
									<li>充值类型:
										<select style="width:102px;" id="category">
										  <option value="-1"></option>
										  <option value="4">不能提现</option>
										  <option value="6">可提现</option>
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
												<td>文件名称</td>
												<td>序号</td>
												<td>用户标识</td>
												<td>充值金额</td>
												<td>充值代金券</td>
												<td>用户ID</td>
												<td>角色类型</td>
												<td>角色值</td>
												<td>状态</td>
												<td>是否删除</td>
												<td>备注</td>
												<td>添加时间</td>
												<td>充值类型</td>
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
		    menuact("01_03_02");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#is_del").val()!=""){
		ajaxdatas+="&is_del="+$("#is_del").val();
	}
	if($("#category").val()!=""){
		ajaxdatas+="&category="+$("#category").val();
	}
	if($("#state").val()!=""){
		ajaxdatas+="&state="+$("#state").val();
	}
	
	$.ajax({
		type : "GET",
		url : "queryUserFixed",
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
					var	excel_file=item.excel_file;
					var	row_no=item.row_no;
					var	user_key=item.user_key;
					var	amount=item.amount;
					var	voucher=item.voucher;
					var	user_id=item.user_id;
					var	role_type=item.role_type;
					var	role_value=item.role_value;
					var	state=item.state;
					var	is_del=item.is_del;
					var	remark=item.remark;
					var	add_time=item.add_time;
					var	category=item.category;

					var One = "";
					
					switch(state){
						case 1:
							One = "原始数据导入成功";
						break;
						
						case 2:
							One = "原始数据异常(用户或账户未找到)";
						break;
						
						case 3:
							One = "账户待充值";
						break;
						
						case 4:
							One = "账户预充值";
						break;
						
						case 5:
							One = "充值失败";
						break;
						
						case 6:
							One = "充值成功";
						break;
						
						case 7:
							One = "预撤销充值";
						break;
						
						case 8:
							One = "撤销充值失败";
						break;
						
						case 9:
							One = "撤销充值成功";
						break;
						
						case 10:
							One = "系统修正";
						break;
						
						case 11:
							One = "数据作废";
						break;
						
						default:
							One = "";
						break;
					}
					
					var Tow = "";
					
					switch(is_del){
						case 0:
							Tow = "正常";
						break;
						
						case 1:
							Tow = "删除";
						break;
						
						default:
							Tow = "";
						break;
					}
					
					var There = "";
					
					switch(category){
						case 4:
							There = "不能提现";
						break;
						
						case 6:
							There = "可提现";
						break;
						
						default:
							There = "";
						break;
					}
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+excel_file+"</td>"+
					 "<td>"+row_no+"</td>"+
					 "<td>"+user_key+"</td>"+
					 "<td>"+amount+"</td>"+
					 "<td>"+voucher+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+role_type+"</td>"+
					 "<td>"+role_value+"</td>"+
					 "<td>"+One+"</td>"+
					 "<td>"+Tow+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+There+"</td>"+
					 "<td>"+"<a href='readUserFixed?id="+id+"'>修改</a><br><a href='delUserFixed?id="+id+"'>删除</a>"+"</td>"+
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