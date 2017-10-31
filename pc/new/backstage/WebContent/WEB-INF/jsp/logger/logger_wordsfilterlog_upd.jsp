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
								关键字屏蔽记录信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">敏感库配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selWordsFilterLog">关键字屏蔽记录信息</a></li>
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
									<span class="hidden-480">修改关键字屏蔽记录信息</span>
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
													<label class="control-label">对应记录表：</label>
													<div class="controls">
														<input id="tablename" type="text" 	class="m-wrap small" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">对应记录值：</label>
													<div class="controls">
														<input id="tablevalueid" type="text" 	class="m-wrap small" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="userid" type="text" 	class="m-wrap small" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色类型：</label>
													<div class="controls">
														<textarea id="userroletype" type="text" 	class="m-wrap medium" ></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色值：</label>
													<div class="controls">
														<input id="userrolevalue" type="text" 	class="m-wrap small" >
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">出现关键字：</label>
													<div class="controls">
														<textarea id="keys" type="text" 	class="m-wrap medium" ></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">敏感词次数：</label>
													<div class="controls">
														<input id="keyscount" type="text" 	class="m-wrap small" >
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">时间：</label>
													<div class="controls">
														<input id="addtime" type="text" 	class="m-wrap medium" >
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">提交IP：</label>
													<div class="controls">
														<input id="addip" type="text" 	class="m-wrap medium" >
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否处理：</label>
													<div class="controls">
														<select id="isfilter"   class="m-wrap medium">
							                          		<option value="0">未启用</option>
							                          		<option value="1">启用</option>
							                          	</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">处理进度：</label>
													<div class="controls">
														<textarea id="remark" type="text" 	class="m-wrap medium" ></textarea>
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selWordsFilterLog">返回</a>

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
		   
		    infoset();
		    menuact("06_03_03");
		});
	
	function infoset(){
		
		var wordsfilterlog=${wordsfilterloginfo };
		var wordsfilterloginfo=wordsfilterlog[0];
		console.log(wordsfilterloginfo);
		
		var id =wordsfilterloginfo.id;
		var table_name =wordsfilterloginfo.table_name;
		var table_value_id =wordsfilterloginfo.table_value_id;
		var user_id =wordsfilterloginfo.user_id;
		var user_role_type =wordsfilterloginfo.user_role_type;
		var user_role_value =wordsfilterloginfo.user_role_value;
		var keys =wordsfilterloginfo.keys;
		var keys_count =wordsfilterloginfo.keys_count;
		var add_time =wordsfilterloginfo.add_time;
		var add_ip =wordsfilterloginfo.add_ip;
		var is_filter =wordsfilterloginfo.is_filter;
		var remark =wordsfilterloginfo.remark;

		$("#hiddenid").val(id);
		$("#tablename").val(table_name);
		$("#tablevalueid").val(table_value_id);
		$("#userid").val(user_id);
		$("#userroletype").val(user_role_type);
		$("#userrolevalue").val(user_role_value);
		$("#keys").val(keys);
		$("#keyscount").val(keys_count);
		$("#addtime").val(add_time);
		$("#addip").val(add_ip);
		$("#isfilter").val(is_filter);
		$("#remark").val(remark);
		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updWordsFilterLog",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			table_name:$("#tablename").val(),
			table_value_id:$("#tablevalueid").val(),
			user_id:$("#userid").val(),
			user_role_type:$("#userroletype").val(),
			user_role_value:$("#userrolevalue").val(),
			keys:$("#keys").val(),
			keys_count:$("#keyscount").val(),
			add_time:$("#addtime").val(),
			add_ip:$("#addip").val(),
			is_filter:$("#isfilter").val(),
			remark:$("#remark").val()

				},
		success : function(data) {
			if(data.status==0){
				location.reload();
			}else{
				alert(修改失败);
			}
		}
				
		})
	});
	
	

	</script>

</body>

</html>