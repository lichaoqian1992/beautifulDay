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
								角色后台管理中菜单信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">角色后台管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">角色后台管理中菜单信息</a></li>
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
									<a  class="btn blue" href="insRoleOperatorNavigation"><i class="icon-pencil"></i>新增角色后台管理中菜单信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
								<div class=row_fluid>
									<ul>
										<li>角色类型:
											<select id="role_type"  style="width:102px;">
												<option value="">请选择</option>
												<option value="Buyer">用户</option>
												<option value="Shop">商家</option>
												<option value="Agent">代理商</option>
											</select></li>
										<li>是否隐藏:<select id="is_lock"  style="width:102px;">
											<option value="0">显示</option>
											<option value="1">隐藏</option>
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
												<td>父级导航ID</td>
												<td>角色类型</td>
												<td>导航标识</td>
												<td>导航标题</td>
												<td>导航副标题</td>
												<td>图标地址</td>
												<td>链接地址</td>
												<td>排序数字</td>
												<td>是否隐藏</td>
												<td>备注说明</td>
												<td>权限资源</td>
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
		    menuact("05_02_03");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}



		var ajaxdatas ="index="+index;

		if($("#role_type").val()!=""){
			ajaxdatas+="&role_type="+$("#role_type").val();
		}
		ajaxdatas+="&is_lock="+$("#is_lock").val();



	/* if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#banktypevalue").val()!=""){
		ajaxdatas+="&bank_type_value="+$("#banktypevalue").val();
	}
	 */
	
	$.ajax({
		type : "GET",
		url : "queryRoleOperatorNavigation",
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
					var	parent_id=item.parent_id;
					var	role_type=item.role_type;
					var	name=item.name;
					var	title=item.title;
					var	sub_title=item.sub_title;
					var	icon_url=item.icon_url;
					var	link_url=item.link_url;
					var	sort_id=item.sort_id;
					var	is_lock=item.is_lock;
					var	remark=item.remark;
					var	action_type=item.action_type;


					switch (role_type){
						case "Buyer":
							role_type="用户"
							break
						case "buyer":
							role_type="用户"
							break
						case "Shop":
							role_type="商家"
							break
						case "shop":
							role_type="商家"
							break
						case "Agent":
							role_type="代理商"
							break
						case "agent":
							role_type="代理商"
							break
					}

					switch (is_lock){
						case 0:
							is_lock="显示"
							break
						case 1:
							is_lock="隐藏"
							break
					}
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+parent_id+"</td>"+
					 "<td>"+role_type+"</td>"+
					 "<td>"+name+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+sub_title+"</td>"+
					 "<td>"+icon_url+"</td>"+
					 "<td>"+link_url+"</td>"+
					 "<td>"+sort_id+"</td>"+
					 "<td>"+is_lock+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+action_type+"</td>"+
					 "<td>"+"<a href='readRoleOperatorNavigation?id="+id+"'>修改</a><br><a href='delRoleOperatorNavigation?id="+id+"'>删除</a>"+"</td>"+
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