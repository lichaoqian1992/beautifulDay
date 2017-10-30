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
							基础内容信息
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">活动信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selArticle">基础内容信息</a></li>
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
									<span class="hidden-480">新增基础内容信息</span>
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
											
											<form action="addArticle" class="form-horizontal" method="POST" enctype="multipart/form-data">
												
												<div class="control-group">
													<label class="control-label">频道ID：</label>
													<div class="controls">
														<input id="channelid" type="text" name="channel_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类别ID：</label>
													<div class="controls">
														<input id="categoryid" type="text" name="category_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">自定义类别ID：</label>
													<div class="controls">
														<input id="usercategoryid" type="text" name="user_category_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">调用别名：</label>
													<div class="controls">
														<input id="callindex" type="text" name="call_index"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">标题：</label>
													<div class="controls">
														<input id="title" type="text" name="title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">外部链接：</label>
													<div class="controls">
														<input id="linkurl" type="text" name="link_url"	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">图片地址：</label>
													<div class="controls">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgurl" name="icon" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO标题：</label>
													<div class="controls">
														<input id="seotitle" type="text" name="seo_title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO关健字：</label>
													<div class="controls">
														<input id="seokeywords" type="text" name="seo_keywords"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO描述：</label>
													<div class="controls">
														<input id="seodescription" type="text" name="seo_description"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">TAG标签：</label>
													<div class="controls">
														<input id="tags" type="text" name="tags"	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">内容摘要：</label>
													<div class="controls">
														<input id="zhaiyao" type="text" name="zhaiyao"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序：</label>
													<div class="controls">
														<input id="sortid" type="text" name="sort_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">内容状态：</label>
													<div class="controls">
														<input id="status" type="text" name="status"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否允许评论：</label>
													<div class="controls">
														<input id="ismsg" type="text" name="is_msg"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否置顶：</label>
													<div class="controls">
														<input id="istop" type="text" name="is_top"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否推荐：</label>
													<div class="controls">
														<input id="isred" type="text" name="is_red"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否热门：</label>
													<div class="controls">
														<input id="ishot" type="text" name="is_hot"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否幻灯片：</label>
													<div class="controls">
														<input id="isslide" type="text" name="is_slide"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">发布用户ID：</label>
													<div class="controls">
														<input id="userid" type="text" name="user_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色类型：</label>
													<div class="controls">
														<select class="m-wrap small" id="user_role_type" name="user_role_type">
									                		<option value="-1"></option>
															<option value="Buyer">用户</option>
															<option value="Shop">商家</option>
															<option value="Agent">代理</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色值：</label>
													<div class="controls">
														<input id="userrolevalue" type="text" name="user_role_value"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">创建时间：</label>
													<div class="controls">
														<input id="addtime" type="text" name="add_time"	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">修改时间：</label>
													<div class="controls">
														<input id="updatetime" type="text" name="update_time"	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select class="m-wrap small" id="is_del" name="is_del">
									                		<option value="-1"></option>
															<option value="0">正常</option>
															<option value="1">删除</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">修改历史：</label>
													<div class="controls">
														<input id="remark" type="text" name="remark"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												
												
												
												
												<div class="form-actions">

													 <button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selArticle">返回</a>

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
		    menuact("04_02_13");
		   
		});
	
	
	
	
	$("#subbutton").on("click",function(){

		$.ajax({
		type : "GET",
		url : "addArticle",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			channel_id:$("#channelid").val(),
			category_id:$("#categoryid").val(),
			user_category_id:$("#usercategoryid").val(),
			call_index:$("#callindex").val(),
			title:$("#title").val(),
			link_url:$("#linkurl").val(),
			img_url:$("#imgurl").val(),
			seo_title:$("#seotitle").val(),
			seo_keywords:$("#seokeywords").val(),
			seo_description:$("#seodescription").val(),
			tags:$("#tags").val(),
			zhaiyao:$("#zhaiyao").val(),
			sort_id:$("#sortid").val(),
			status:$("#status").val(),
			is_msg:$("#ismsg").val(),
			is_top:$("#istop").val(),
			is_red:$("#isred").val(),
			is_hot:$("#ishot").val(),
			is_slide:$("#isslide").val(),
			user_id:$("#userid").val(),
			user_role_type:$("#userroletype").val(),
			user_role_value:$("#userrolevalue").val(),
			add_time:$("#addtime").val(),
			update_time:$("#updatetime").val(),
			is_del:$("#isdel").val(),
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
	
	function uploadpicdiv(){
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

	</script>

</body>

</html>