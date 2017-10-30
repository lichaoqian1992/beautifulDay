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
								用户角色荣誉勋章信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户角色管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户角色荣誉勋章信息</a></li>
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
									<a  class="btn blue" href="insUserRoleMedal"><i class="icon-pencil"></i>新增用户角色荣誉勋章信息	</a>
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
											</select></li>
										<li>状态:<select id="state"  style="width:102px;">
											<option value="">请选择</option>
											<option value="1">生效</option>
											<option value="2">失效</option>
											<option value="3">冻结</option>
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
												<td>角色类型</td>
												<td>角色对应值</td>
												<td>勋章/荣誉值</td>
												<td>添加时间</td>
												<td>过期时间</td>
												<td>状态</td>
												<td>状态更新时间</td>
												<td>状态更新提示</td>
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
		    menuact("05_01_02");
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
		if($("#state").val()!=""){
			ajaxdatas+="&state="+$("#state").val();
		}



		/* if($("#userid").val()!=""){
            ajaxdatas+="&user_id="+$("#userid").val();
        }
        if($("#banktypevalue").val()!=""){
            ajaxdatas+="&bank_type_value="+$("#banktypevalue").val();
        }
         */
	
	$.ajax({
		type : "GET",
		url : "queryUserRoleMedal",
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
					var	role_type=item.role_type;
					var	role_value=item.role_value;
					var	medal_value=item.medal_value;
					var	add_time=item.add_time;
					var	expired_time=item.expired_time;
					var	state=item.state;
					var	update_time=item.update_time;
					var	remark=item.remark;
					var	is_del=item.is_del;

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
					}

					switch (state){
						case 1:
							state="生效"
							break
						case 2:
							state="失效"
							break
						case 3:
							state="冻结"
							break
					};


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
					 "<td>"+user_id	+"</td>"+
					 "<td>"+role_type+"</td>"+
					 "<td>"+role_value+"</td>"+
					 "<td>"+medal_value+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+expired_time+"</td>"+
					 "<td>"+state+"</td>"+
					 "<td>"+update_time+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+is_del+"</td>"+

					 "<td>"+"<a href='readUserRoleMedal?id="+id+"'>修改</a><br><a href='delUserRoleMedal?id="+id+"'>删除</a>"+"</td>"+
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