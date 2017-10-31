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
						<jsp:include page="/WEB-INF/jsp/banner.html" flush="true"/>
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
			<jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
		</div>

		<div class="page-content">

			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							角色管理
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">权限管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">角色管理</a></li>
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
									<span class="hidden-480">角色信息</span>
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
													<label class="control-label">角色名：</label>
													<div class="controls">
														<input id="rolename" type="text" 	class="m-wrap small disabled" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="control-group">
													<label class="control-label">添加时间：</label>
													<div class="controls">
														<input id="addtime" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">权限：</label>
													<div class="controls" id="remark">
														<!-- <input id="remark" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selLocalRole">返回</a> 

												</div>

											</form>

											<!-- END FORM-->

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

		    App.init(); 
		    onkey(11);
		    infoset();
		    setactive("1121");
		});
	
	var str1=[];
    function first(args){
        for(var i=0;i<args.length;i++){
            if(str1.indexOf(args[i])<0){
                str1.push(args[i])
            }
        }
        return str1;
    }
	
	function infoset(){
		var roleinfo = ${roleinfo};
		var roleInfo = new Array();
		for(var i in roleinfo){
			
			roleInfo.push(roleinfo[i].remark);
		}
		for(var i in roleInfo){
			if(roleInfo[i] == ''){
				roleInfo.splice(i,1);
			}
		}
		var roleA = new Array();
		var roleB = new Array();
		for(var i in roleInfo){
			if(roleInfo[i].indexOf(",")!=-1){
				
			roleB=	roleInfo[i].split(",");
				for(var j in roleB){
					roleA.push(roleB[j]);
				}
			}else{
				roleA.push(roleInfo[i]);
			}
			
		}
		var roleC = first(roleA);
		for(var i in roleC){
			$('#remark').append('<label><input name="'+roleC[i]+'"  type="checkbox" />'+roleC[i]+'</label>');
		}
		
		var roleList = ${roleList};
		var id =roleList.id;
		var rolename =roleList.roleName;
		var addtime =roleList.addTime;
		var remark =roleList.remark;
		
		$("#hiddenid").val(id);
		$("#rolename").val(rolename);
		$("#addtime").val(addtime);
		
		/* $("#remark").val(remark); */
		 
		var roleD = roleList.remark.split(",");
		for(var i in roleD){
			
				$('#remark').find('input[name='+roleD[i]+']').prop('checked',true);
			
			}
		}
		
	
	
	$("#subbutton").on("click",function(){
		
		 var roleCheck = new Array();
			$('#remark').find('input:checked').each(function(){
				
					roleCheck.push($(this).attr('name'));
			});
			roleCheck = roleCheck.join(); 
			if(roleCheck==''){
				roleCheck = null;
			}
			console.log(roleCheck);
		$.ajax({
		type : "GET",
		url : "updLocalRole",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			roleName:$("#rolename").val(),
			addTime:$("#addtime").val(),
			remark:roleCheck
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
	

	</script>

</body>

</html>