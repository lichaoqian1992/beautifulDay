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
								用户商务角色信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户角色管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户商务角色信息</a></li>
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
									<a  class="btn blue" href="insUserRoleBusiness"><i class="icon-pencil"></i>新增用户商务角色信息</a>
								</div>
							</div>
							
							<div class="portlet-body">

								<div class=row_fluid>
									<ul>
										<li>用户ID：<input type="text" id="user_id" class="small m-wrap" ></li>
										<li>角色状态:<select id="status"  style="width:102px;">
											<option value="-1">请选择</option>
											<option value="0">待审核</option>
											<option value="1">正常</option>
											<option value="2">冻结</option>
											<option value="3">过期</option>
										</select></li>
										<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
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
												<td>用户ID</td>
												<td>商务角色类型</td>
												<td>角色有效期</td>
												<td>角色状态</td>
												<td>状态更新内容</td>
												<td>状态更新时间</td>
												<td>新增时间</td>
												<td>是否删除</td>
												<td>姓名</td>
												<td>电话</td>
												<td>邮箱</td>
												<td>即时通讯</td>
												<td>所在地区</td>
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
		    menuact("05_01_04");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}




		var ajaxdatas ="index="+index;


		if($("#user_id").val()!=""){
			ajaxdatas+="&user_id="+$("#user_id").val();
		}

			ajaxdatas+="&status="+$("#status").val();

	
	/* if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#banktypevalue").val()!=""){
		ajaxdatas+="&bank_type_value="+$("#banktypevalue").val();
	}
	 */
	
	$.ajax({
		type : "GET",
		url : "queryUserRoleBusiness",
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
					var	user_id=item.user_id;
					var	buiness_type=item.buiness_type;
					var	end_time=item.end_time;
					var	status=item.status;
					var	remark=item.remark;
					var	update_time=item.update_time;
					var	add_time=item.add_time;
					var	is_del=item.is_del;
					var	name=item.name;
					var	mobile=item.mobile;
					var	email=item.email;
					var	qq=item.qq;
					var	area=item.area;


					switch (buiness_type){
						case 0:
							buiness_type="代理制"
							break
						case 1:
							buiness_type="合同制"
							break
					}

					switch (status){
						case 0:
							status="待审核"
							break
						case 1:
							status="正常"
							break
						case 2:
							status="冻结"
							break
						case 3:
							status="过期"
							break
					}

					switch (is_del){
						case 0:
							is_del="正常"
							break
						case 1:
							is_del="删除"
							break
					}






					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+buiness_type+"</td>"+
					 "<td>"+end_time+"</td>"+
					 "<td>"+status+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+is_del+"</td>"+
					 "<td>"+name+"</td>"+
					 "<td>"+mobile+"</td>"+
					 "<td>"+email+"</td>"+
					 "<td>"+qq+"</td>"+
					 "<td>"+area+"</td>"+
					 "<td>"+"<a href='readUserRoleBusiness?id="+id+"'>修改</a><br><a href='delUserRoleBusiness?id="+id+"'>删除</a>"+"</td>"+
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