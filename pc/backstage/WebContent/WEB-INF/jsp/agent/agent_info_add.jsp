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
							代理商列表
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">代理商信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">代理商列表</a></li>
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
									<span class="hidden-480">新增代理商信息</span>
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
                          <label class="control-label">代理商组别：</label>
                          <div class="controls">
                            <select id="groupid" class="small m-wrap" >
                              
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">用户ID：</label>
                          <div class="controls">
                            <input id="userid" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">代理区域：</label>
                          <div class="controls">
                            <input id="agentarea" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">详细地址：</label>
                          <div class="controls">
                            <input id="address" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">联系电话：</label>
                          <div class="controls">
                            <input id="telephone" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">代理联系QQ：</label>
                          <div class="controls">
                            <input id="qq" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">负责人：</label>
                          <div class="controls">
                            <input id="name" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">手机号码：</label>
                          <div class="controls">
                            <input id="mobile" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">负责人邮箱：</label>
                          <div class="controls">
                            <input id="email" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">代理开始时间：</label>
                          <div class="controls">
                            <input id="begintime" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">代理结束时间：</label>
                          <div class="controls">
                            <input id="endtime" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">代理状态：</label>
                          <div class="controls">
                            <select id="state" class="small m-wrap" >
                              <option value="0">待审核</option>
                              <option value="1">正常</option>
                              <option value="2">冻结</option>
                              <option value="3">过期</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">状态更新内容：</label>
                          <div class="controls">
                            <input id="remark" type="text"  class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">状态更新时间：</label>
                          <div class="controls">
                            <input id="updatetime" type="text"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">新增时间：</label>
                          <div class="controls">
                            <input id="addtime" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        
                        
                        <div class="control-group">
                          <label class="control-label">是否删除：</label>
                          <div class="controls">
                            <input id="isdel" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selAgentInfo">返回</a>

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
		    onkey(4);
		    setactive("422");
		    /* infoset(); */
		    $.ajax({
	            type : "GET",
	            url : "findAgentGroup",
	            dataType : "json",
	            contentType : "application/json; charset=utf-8",
	            data : {},
	            success : function(data) {
	              console.log(data);
	              var dataList = data.result;
	              for(var i in dataList){
	                 $('#groupid').append('<option value='+dataList[i].id+'>'+dataList[i].title+'</option>');
	               
	             } 
	            }
	      });
		});
	
/* 	function infoset(){
		
		var noticeinfo=${noticeinfo };
		
		var id=noticeinfo.id;
		var title=noticeinfo.title;
		var content=noticeinfo.content;
		var get_user_roles=noticeinfo.get_user_roles;
		var get_user_groups=noticeinfo.get_user_groups;
		var get_user_area=noticeinfo.get_user_area;
		var user_id=noticeinfo.user_id;
		var user_role_type=noticeinfo.user_role_type;
		var user_role_value=noticeinfo.user_role_value;
		var state=noticeinfo.state;
		var update_time=noticeinfo.update_time;
		var remark=noticeinfo.remark;


		$("#hiddenid").val(id);
		$("#title").val(title);
		$("#content").val(content);
		$("#getuserroles").val(get_user_roles);
		$("#getusergroups").val(get_user_groups);
		$("#getuserarea").val(get_user_area);
		$("#userid").val(user_id);
		$("#userroletype").val(user_role_type);
		$("#userrolevalue").val(user_role_value);
		$("#state").val(state);
		$("#updatetime").val(update_time);
		$("#remark").val(remark);


		

	}; */
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "addAgentInfo",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			    group_id:$("#groupid").val(),
		      user_id:$("#userid").val(),
		      agent_area:$("#agentarea").val(),
		      address:$("#address").val(),
		      telephone:$("#telephone").val(),
		      qq:$("#qq").val(),
		      name:$("#name").val(),
		      mobile:$("#mobile").val(),
		      email:$("#email").val(),
		      begin_time:$("#begintime").val(),
		      end_time:$("#endtime").val(),
		      state:$("#state").val(),
		      remark:$("#remark").val(),
		      update_time:$("#updatetime").val(),
		      add_time:$("#addtime").val(),
		      is_del:$("#isdel").val()
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