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
							修改用户绑定信用卡列表
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">修改用户绑定信用卡列表</a></li>
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
									<span class="hidden-480">修改用户绑定信用卡列表</span>
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
													<label class="control-label">类型ID：</label>
													<div class="controls">
														<input id="bank_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色类型：</label>
													<div class="controls">
														<input id="user_role_type" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色值：</label>
													<div class="controls">
														<input id="user_role_value" type="text" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">卡号/账户号：</label>
													<div class="controls">
														<input id="bank_type_value" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">开户行地址：</label>
													<div class="controls">
														<input id="bank_address" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">开户行地区：</label>
													<div class="controls">
														<input id="bank_area" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">添加时间：</label>
													<div class="controls">
														<input id="add_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否最近使用：</label>
													<div class="controls">
														<select id="is_last_use">
														  <option value="0">否</option>
														  <option value="1">是</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">提现失败次数：</label>
													<div class="controls">
														<input id="failed_times" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select id="is_del">
                              <option value="0">正常</option>
                              <option value="1">删除</option>
                            </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">银行卡类型：</label>
													<div class="controls">
														<select id="usertype">
                              <option>个人银行</option>
                              <option>法人银行卡</option>
                              <option>公司基本账户</option>
                              <option>委托收款账户</option>
                            </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">审核状态：</label>
													<div class="controls">
														<select id="status">
                              <option value="0">待审核</option>
                              <option value="1">正常</option>
                              <option value="2">审核不通过</option>
                              <option value="3">冻结</option>
                            </select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">备注：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												
												
												
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selBankType">返回</a>

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
		    onkey(2);
		    infoset();
		    setactive("224");
		});
	
	function infoset(){
		
		var banktypeinfo=${banktypeinfo };
		
		var id=banktypeinfo.id;
		
		var bank_type =banktypeinfo.bank_type;
		var user_id =banktypeinfo.user_id;
		var user_role_type =banktypeinfo.user_role_type;
		var user_role_value =banktypeinfo.user_role_value;
		var bank_type_value =banktypeinfo.bank_type_value;
		var bank_address =banktypeinfo.bank_address;
		var bank_area =banktypeinfo.bank_area;
		var add_time =banktypeinfo.add_time;
		var is_last_use =banktypeinfo.is_last_use;
		var failed_times =banktypeinfo.failed_times;
		var is_del =banktypeinfo.is_del;
		var usertype =banktypeinfo.usertype;
		var status =banktypeinfo.status;
		var remark =banktypeinfo.remark;


	 	$("#hiddenid").val(id);
	 	$("#bank_type").val(bank_type);
	 	$("#user_id").val(user_id);
	 	$("#user_role_type").val(user_role_type);
	 	$("#user_role_value").val(user_role_value);
	 	$("#bank_type_value").val(bank_type_value);
	 	$("#bank_address").val(bank_address);
	 	$("#bank_area").val(bank_area);
	 	$("#add_time").val(add_time);
	 	$("#is_last_use").val(is_last_use);
	 	$("#failed_times").val(failed_times);
	 	$("#is_del").val(is_del);
	 	$("#usertype").val(usertype);
	 	$("#status").val(status);
	 	$("#remark").val(remark);
		
		
 

		

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updBankType",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			bank_type:$("#bank_type").val(),
			user_id:$("#user_id").val(),
			user_role_type:$("#user_role_type").val(),
			user_role_value:$("#user_role_value").val(),
			bank_type_value:$("#bank_type_value").val(),
			bank_address:$("#bank_address").val(),
			bank_area:$("#bank_area").val(),
			add_time:$("#add_time").val(),
			is_last_use:$("#is_last_use").val(),
			failed_times:$("#failed_times").val(),
			is_del:$("#is_del").val(),
			usertype:$("#usertype").val(),
			status:$("#status").val(),
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