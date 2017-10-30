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
							系统用户管理
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">系统用户管理</a><i class="icon-angle-right"></i></li>
							<li><a href="selLocalUser">系统用户管理</a></li>
						</ul>
					</div>
				</div>

				<div class="row-fluid margin-bottom-20">

				</div>

				<div class="row-fluid">

					<div class="span12">

						<div class="portlet box blue tabbable">

							<div class="portlet-title">
								<div class="caption">
									<span class="hidden-480">用户信息</span>
								</div>
							</div>
							<div class="portlet-body form">
							
								<div class="tabbable portlet-tabs">
									<ul class="nav nav-tabs">
										<li><a href="#portlet_tab3" data-toggle="tab">&nbsp;</a></li>
									</ul>
									
									<div class="tab-content">
										<div class="tab-pane active" id="portlet_tab1">
											<!-- BEGIN FORM-->
											
											<form action="#" class="form-horizontal">
												<input type="hidden" id="hiddenid"value="">
												
												
												
												<div class="control-group">
													<label class="control-label">用户名：</label>
													<div class="controls">
														<input id="username" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">用户密码：</label>
													<div class="controls">
														<input id="password" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户状态：</label>
													<div class="controls">
														<select id="state" class="m-wrap small" >
															<option value="0">未生效</option>
															<option value="1">正常</option>
															<option value="2">异常</option>
															
														</select>
													</div>
												</div>
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selLocalUser">返回</a> 

												</div>

											</form>

											<!-- END FORM-->

										</div>

										

										

									</div>

								</div>

							</div>
							
							
							<div class="portlet box orange tabbable">

							<div class="portlet-title">
								<div class="caption">
									<span class="hidden-480">角色信息</span>
								</div>
							</div>
							
							<div class="portlet-body form">
							
								<div class="tabbable portlet-tabs">
									<ul class="nav nav-tabs">
										<li><a href="#portlet_tab3" data-toggle="tab">&nbsp;</a></li>
										<li ><a onclick="addRole();" class="btn green " style="margin-top:3px">新增角色</a></li>
									</ul>
									
									<div class="tab-content">
										<div class="tab-pane active" id="portlet_tab2">
											
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>角色</th>
												<th>添加时间</th>
												<th>操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">
										</tbody>
										</table>
											

										</div>

									</div>

								</div>

							</div>
							
							
							</div>

						</div>

						<!-- END SAMPLE FORM PORTLET-->

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
			var sum;
		    App.init(); 
		    menuact("11_01_01");
		    infoset();
		});
	
	function infoset(){
		
		var localuser=${localuser };
		console.log("1311");
		console.log(localuser);
		
		var id =localuser.id;
		var username =localuser.userName;
		var password =localuser.password;
		var state =localuser.state;
		
		/* var statusstr ="";
		switch(status){
		case 0:
			statusstr="未生效";
			break;
		case 1:
			statusstr="正常";
			break;
		default :
			statusstr="异常";
			break;
		} */
		
		
		$("#hiddenid").val(id);
		$("#username").val(username);
		$("#password").val(password);
		$("#state").val(state);
		
		
		
		//角色信息
		var localrel=${localrel };
		console.log(localrel);
		var tablehtml ="";
		$.each(localrel,function(i,item){
			var no =Number(i+1);
			var relid =item.id;
			
			var roleid =item.roleId;
			var rolename =item.roleName;
			var addtime =item.addTime;
			
			var html ="<tr>"+
			 "<td>"+no+"</td>"+
			 "<td>"+rolename+"</td>"+
			 "<td>"+addtime+"</td>"+
			 "<td>"+"<a href='delLocalRoleToUser?id="+relid+"'>删除</a>"+"</td>"+
			 "</tr>";
			
			tablehtml+=html;
			 
		})
		
		$("#tablebody").html(tablehtml);
		
		
	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updLocalUser",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			userName:$("#username").val(),
			password:$("#password").val(),
			state:$("#state").val()
				},
		success : function(data) {
			if(data.status==0){
				location.reload();
			}else{
				alert("修改失败");
			}
		}
				
		})
	});
	
	function addRole(){
		var local=${localrel };
		var localArr = new Array();
		for(var i in local){
			localArr.push(local[i].roleName);
		}
		console.info(localArr);
		$.ajax({
			type : "GET",
			url : "getLocalRole",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {},
			success : function(data) {
				console.log(data);
				if(data.status==0){
					var roleData = '';
					var dataArr = [];
					
					for(var i in data.result){
						dataArr.push(data.result[i].roleName);
					}
					
					var addRoleArr = chaji_array(dataArr,localArr);
					var roleObj = [];
					for(var i in data.result){
						for(var j in addRoleArr){
							if(data.result[i].roleName == addRoleArr[j]){
								roleObj.push({});
								roleObj[j].roleId = data.result[i].id;
								roleObj[j].roleName = data.result[i].roleName;
							}
						}
					}
					sum=roleObj.length;
				
					for(var i in roleObj){
						roleData += '<option value="'+roleObj[i].roleId+'">'+roleObj[i].roleName+'</option>';
					}
					
					
					$("#portlet_tab2").before('<div id="model">'
							+'<label>角色:<select id="addData" style="margin-left:1%">'
							+roleData
							+'</select>'
							+'<a style="margin-left:4%" class="btn blue " onclick="save()">保存</a><a style="margin-left:2%" class="btn" onclick="cancel()">取消</a></label>'
							+'</div>');
					
				}else{
					alert("修改失败");
				}
			}
					
			})
		
	}
	
		/* 求差集 */
		function chaji_array(arr1, arr2) {

			var arr3 = [];

			for (var i = 0; i < arr1.length; i++) {
				var flag = true;
				for (var j = 0; j < arr2.length; j++) {
					if (arr2[j] == arr1[i]) {
						flag = false;
					}
				}
				if (flag) {
					arr3.push(arr1[i]);
				}
			}

			return arr3;

		}
		function save() {
			if(sum == 0){
				alert("当前没有可添加的角色!");
			}
			var localuser = ${localuser};
			console.log(localuser);
			var locrel = ${localrel};
			console.log(locrel)
			/* if(localuser.roles.length == 0){
				alert("当前没有可添加的角色!");
				return false; 
				console.info("11");
			} */
			$.ajax({
				type : "GET",
				url : "addLocalRoleToUser",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {
					userId : localuser.id,
					userName : localuser.userName,
					roleId : $("#addData").val(),
					roleName : $("#addData option:selected").text(),
					addTime : ''
				},
				success : function(data) {
					if (data.status == 0) {
						location.reload();  
					} else {
						alert("修改失败");
					}
				}

			})

		}
		function cancel(){
			$("#model").remove();
		}
	</script>

</body>

</html>