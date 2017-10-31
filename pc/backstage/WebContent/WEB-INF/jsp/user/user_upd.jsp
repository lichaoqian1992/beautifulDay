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
							用户信息详情
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">角色信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户信息详情</a></li>
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
									<span class="hidden-480">用户信息详情</span>
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
													<label class="control-label">用户组ID：</label>
													<div class="controls">
<!-- 														<input id="group_id" type="text" 	class="m-wrap small" /> <span class="help-inline">Some hint here</span>
 -->												<select id="group_id">
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">用户名：</label>
													<div class="controls">
														<input id="user_name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">加密字符串：</label>
													<div class="controls">
														<input id="salt" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户密码：</label>
													<div class="controls">
														<input id="password" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户手机：</label>
													<div class="controls">
														<input id="mobile" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">电子邮箱：</label>
													<div class="controls">
														<input id="email" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">头像：</label>
													<div class="controls">
														<input id="avatar" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">昵称：</label>
													<div class="controls">
														<input id="nick_name" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">性别：</label>
													<div class="controls">
														<input id="sex" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">生日：</label>
													<div class="controls">
														<input id="birthday" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">电话：</label>
													<div class="controls">
														<input id="telphone" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">所属地区：</label>
													<div class="controls">
														<input id="area" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">详细地址：</label>
													<div class="controls">
														<input id="address" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">QQ号码：</label>
													<div class="controls">
														<input id="qq" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">支付密码：</label>
													<div class="controls">
														<input id="pay_password" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">账户状态：</label>
													<div class="controls">
														<select id="status">
														  <option value="0">正常</option>
														  <option value="1">待验证</option>
														  <option value="2">待审核</option>
														  <option value="3">黑名单</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">状态对应说明：</label>
													<div class="controls">
														<input id="remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">注册时间：</label>
													<div class="controls">
														<input id="reg_time" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">注册IP：</label>
													<div class="controls">
														<input id="reg_ip" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">注册来源：</label>
													<div class="controls">
														<input id="from_value" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
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
												
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selUser">返回</a>

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
		    setactive("221");
		    var user=${userinfo };
		    $.ajax({
	            type : "GET",
	            url : "findUserGroup",
	            dataType : "json",
	            contentType : "application/json; charset=utf-8",
	            data : {},
	            success : function(data) {
	              console.log(data);
	              var dataList = data.result;
	              for(var i in dataList){
	               $('#group_id').append('<option value='+dataList[i].id+'>'+dataList[i].title+'</option>');
	            	  
	              }
	              $('#group_id option[value="'+user.group_id+'"]').attr('selected',true); 
	            }
		    });      
		});
	
	function infoset(){
		
		var userinfo=${userinfo };
		
		var id=userinfo.id;
		var group_id=userinfo.group_id;
		var user_name=userinfo.user_name;
		var salt=userinfo.salt;
		var password=userinfo.password;
		var mobile=userinfo.mobile;
		var email=userinfo.email;
		var avatar=userinfo.avatar;
		var nick_name=userinfo.nick_name;
		var sex=userinfo.sex;
		var birthday=userinfo.birthday;
		var telphone=userinfo.telphone;
		var area=userinfo.area;
		var address=userinfo.address;
		var qq=userinfo.qq;
		var pay_password=userinfo.pay_password;
		var status=userinfo.status;
		var remark=userinfo.remark;
		var reg_time=userinfo.reg_time;
		var reg_ip=userinfo.reg_ip;
		var from_value=userinfo.from_value;
		var is_del=userinfo.is_del;
		

		$("#hiddenid").val(id);
		$("#group_id").val(group_id);
		$("#user_name").val(user_name);
		$("#salt").val(salt);
		$("#password").val(password);
		$("#mobile").val(mobile);
		$("#email").val(email);
		$("#avatar").val(avatar);
		$("#nick_name").val(nick_name);
		$("#sex").val(sex);
		$("#birthday").val(birthday);
		$("#telphone").val(telphone);
		$("#area").val(area);
		$("#address").val(address);
		$("#qq").val(qq);
		$("#pay_password").val(pay_password);
		$("#status").val(status);
		$("#remark").val(remark);
		$("#reg_time").val(reg_time);
		$("#reg_ip").val(reg_ip);
		$("#from_value").val(from_value);
		$("#is_del").val(is_del);

	};
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "updUser",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			id:$("#hiddenid").val(),
			group_id:$("#group_id").val(),
			user_name:$("#user_name").val(),
			salt:$("#salt").val(),
			password:$("#password").val(),
			mobile:$("#mobile").val(),
			email:$("#email").val(),
			avatar:$("#avatar").val(),
			nick_name:$("#nick_name").val(),
			sex:$("#sex").val(),
			birthday:$("#birthday").val(),
			telphone:$("#telphone").val(),
			area:$("#area").val(),
			address:$("#address").val(),
			qq:$("#qq").val(),
			pay_password:$("#pay_password").val(),
			status:$("#status").val(),
			remark:$("#remark").val(),
			reg_time:$("#reg_time").val(),
			reg_ip:$("#reg_ip").val(),
			from_value:$("#from_value").val(),
			is_del:$("#is_del").val()
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