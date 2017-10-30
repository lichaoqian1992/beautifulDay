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
										用户绑定信用卡<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户详细信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户绑定信用卡</a></li>
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
									<a  class="btn blue" href="insBankType"><i class="icon-pencil"></i>新增绑定信用卡</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
	                <li>用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
	                <li>卡号：<input type="text" id="banktypevalue" class="small m-wrap" ></li>
	                <li>审核状态:
						<select style="width:102px;" id="status">
						  <option value="-1"></option>
						  <option value="0">待审核</option>
						  <option value="1">正常</option>
						  <option value="2">审核不通过</option>
						  <option value="3">冻结</option>
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
												<td>类型ID</td>
												<td>用户ID</td>
												<td>用户角色类型</td>
												<td>用户角色值</td>
												<td>卡号/账户号</td>
												<td>开户行地址</td>
												<td>开户行地区</td>
												<td>添加时间</td>
												<td>是否最近使用</td>
												<td>提现失败次数</td>
												<td>是否删除</td>
												<td>银行卡类型</td>
												<td>审核状态</td>
												<!-- <td>备注</td> -->
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
		    menuact("01_02_03");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#banktypevalue").val()!=""){
		ajaxdatas+="&bank_type_value="+$("#banktypevalue").val();
	}
	if($("#status").val()!=""){
		ajaxdatas+="&status="+$("#status").val();
	}
	
	
	
	$.ajax({
		type : "GET",
		url : "queryBankType",
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
					var bank_type=item.bank_type;
					var user_id=item.user_id;
					var user_role_type=item.user_role_type;
					var user_role_value=item.user_role_value;
					var bank_type_value=item.bank_type_value;
					var bank_address=item.bank_address;
					var bank_area=item.bank_area;
					var add_time=item.add_time;
					var is_last_use=item.is_last_use;
					var failed_times=item.failed_times;
					var is_del=item.is_del;
					var usertype=item.usertype;
					var status=item.status;
					var remark=item.remark;
					
					is_last_use == 0 ? is_last_use = "否":is_last_use = "是";
					is_del == 0 ? is_del = "正常":is_del = "删除";
					
					var statusStr = "";
					switch(status){
					    case 0:
					    statusStr = "待审核";
					    break;
					    case 1:
					    statusStr = "正常";
					    break;
					    case 2:
					    statusStr = "审核不通过";
					    break;
					    case 3:
					    statusStr = "冻结";
					    break;
					}

					
					
					 
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+bank_type+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+bank_type_value+"</td>"+
					 "<td>"+bank_address+"</td>"+
					 "<td>"+bank_area+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+is_last_use+"</td>"+
					 "<td>"+failed_times+"</td>"+
					 "<td>"+is_del+"</td>"+
					 "<td>"+usertype+"</td>"+
					 "<td>"+statusStr+"</td>"+
					 /* "<td>"+remark+"</td>"+ */
					 "<td>"+"<a href='readBankType?id="+id+"'>修改</a><br><a href='delBankType?id="+id+"'>删除</a>"+"</td>"+
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