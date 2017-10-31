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
								支持绑定银行卡/第三方账户信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">系统配置</a><i class="icon-angle-right"></i></li>
							<li><a href="#">支持绑定银行卡/第三方账户信息	</a></li>
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
									<a  class="btn blue" href="insBanktype"><i class="icon-pencil"></i>新增支持绑定银行卡/第三方账户信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
								<div class=row_fluid>
									<ul>
										<li>类型名称：<input type="text" id="title" class="small m-wrap" ></li>
										<li>类型分类：
											<select  id="type"	 style="width:102px">
												<option value="-1">不限</option>
												<option value="1">银行卡</option>
												<option value="2">三方支付</option>
											</select>
										</li>
										<li style="width:232px">提现手续费类型：
											<select  id="poundage_type"	 style="width:102px">
												<option value="-1">不限</option>
												<option value="1">百分比</option>
												<option value="2">固定金额</option>
											</select>
										</li>
										<li>是否启用：
											<select  id="is_lock"	 style="width:102px">
												<option value="-1">不限</option>
												<option value="0">启用</option>
												<option value="1">锁定</option>
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
												<td>类型名称</td>
												<td>类型图片</td>
												<td>备注说明</td>
												<td>类型分类</td>
												<td>提现手续费类型</td>
												<td>提现手续费金额</td>
												<td>提现最小额</td>
												<td>提现最大额</td>
												<td>排序</td>
												<td>是否启用</td>
												<td>背景色彩值</td>
												<td>BIN值</td>

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
		    menuact("03_04_04");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#title").val()!=""){
		ajaxdatas+="&title="+$("#title").val();
	}
	
	if($("#type").val()!=""){
		ajaxdatas+="&type="+$("#type").val();
	}
	if($("#poundage_type").val()!=""){
		ajaxdatas+="&poundage_type="+$("#poundage_type").val();
	}
	if($("#is_lock").val()!=""){
		ajaxdatas+="&is_lock="+$("#is_lock").val();
	}
	
	$.ajax({
		type : "GET",
		url : "queryBanktype",
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
					var	title=item.title;
					var	img_url=item.img_url;
					var	remark=item.remark;
					var	type=item.type;
					var	poundage_type=item.poundage_type;
					var	poundage_amount=item.poundage_amount;
					var	min_amount=item.min_amount;
					var	max_amount=item.max_amount;
					var	sort_id=item.sort_id;
					var	is_lock=item.is_lock;
					var	colour=item.colour;
					var	bin=item.bin;
					
					var typeStr = "";
						switch (type) {
						case 1:
							typeStr = "银行卡";
						  break;
						case 2:
							typeStr = "三方支付";
						  break;
						default:
						  stateStr = "";
						}
					var lockStr = "";
						switch (is_lock) {
						case 0:
							lockStr = "启用";
						  break;
						case 1:
							lockStr = "锁定";
						  break;
						default:
							lockStr = "";
						}
					var pouStr = "";
						switch (poundage_type) {
						case 1:
							pouStr = "百分比";
						  break;
						case 2:
							pouStr = "固定金额";
						  break;
						default:
							pouStr = "";
						}
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td><img style='width:100px' src="+img_url+"></td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+typeStr+"</td>"+
					 "<td>"+pouStr+"</td>"+
					 "<td>"+poundage_amount+"</td>"+
					 "<td>"+min_amount+"</td>"+
					 "<td>"+max_amount+"</td>"+
					 "<td>"+sort_id+"</td>"+
					 "<td>"+lockStr+"</td>"+
					 "<td>"+colour+"</td>"+
					 "<td>"+bin+"</td>"+

					 "<td>"+"<a href='readBanktype?id="+id+"'>修改</a><br><a href='delBanktype?id="+id+"'>删除</a>"+"</td>"+
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