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
	
	<style>
		a{
			cursor:pointer;
		}
	</style>
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
								分类信息记录	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">规格配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selArticleCategory">分类信息记录</a></li>
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
									<span class="hidden-480">修改分类信息记录</span>
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
											
											<form action="updArticleCategory" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
												<input type="hidden" id="hiddenid" value="" name="id">
												<div class="control-group">
													<label class="control-label">频道：</label>
													<div class="controls">
														<!-- <input id="channel_id" name="channel_id" type="text" 	class="m-wrap small" /> -->
														<select id="channel_id" name="channel_id">
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">分类类型：</label>
													<div class="controls">
														<!-- <input id="type" type="text" 	class="m-wrap small" /> -->
														<select id="type" name="type">
															<option value="0">商品</option>
															<option value="1">服务</option>
															<option value="2">文章</option>
															<option value="3">外卖</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类别标题：</label>
													<div class="controls">
														<input id="title" name="title" type="text" 	class="m-wrap small" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">调用别名：</label>
													<div class="controls">
														<input id="call_index" name="call_index" type="text" 	class="m-wrap small" /> 
													</div>
												</div>
												<!-- <div class="control-group">
													<label class="control-label">父类别ID：</label>
													<div class="controls">
														<input id="parent_id" name="parent_id" type="text" 	class="m-wrap medium" /> <span class="help-inline">Some hint here</span>
													</div>
												</div> -->
												<!-- <div class="control-group">
													<label class="control-label">类别ID列表：</label>
													<div class="controls">
														<input id="class_list" name="class_list" type="text" 	class="m-wrap medium" /> <span class="help-inline">Some hint here</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类别深度：</label>
													<div class="controls">
														<input id="class_layer" name="class_layer" type="text" 	class="m-wrap medium" /> <span class="help-inline">Some hint here</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序数字：</label>
													<div class="controls">
														<input id="sort_id" name="sort_id" type="text" 	class="m-wrap medium" /> <span class="help-inline">Some hint here</span>
													</div>
												</div> -->
												<div class="control-group">
													<label class="control-label">URL跳转地址：</label>
													<div class="controls">
														<input id="link_url" name="link_url" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">图片地址：</label>
													<div class="controls">
														<!-- <input id="img_url" type="text" 	class="m-wrap medium" /> --><!--  <span class="help-inline">Some hint here</span> -->
														<img id="img_url" name="img_url"  style="width:120px;">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgUrl" name="imgUrl" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:1%">重新上传</a>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">备注说明：</label>
													<div class="controls">
														<input id="content" name="content" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<!-- <div class="control-group">
													<label class="control-label">SEO标题：</label>
													<div class="controls">
														<input id="seo_title" name="seo_title" type="text" 	class="m-wrap medium" /> <span class="help-inline">Some hint here</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO关健字：</label>
													<div class="controls">
														<input id="seo_keywords" name="seo_keywords" type="text" 	class="m-wrap medium" /> <span class="help-inline">Some hint here</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">SEO描述：</label>
													<div class="controls">
														<input id="seo_description" name="seo_description" type="text" 	class="m-wrap medium" /> <span class="help-inline">Some hint here</span>
													</div>
												</div> -->
											 	<div class="control-group">
													<label class="control-label">分类默认图：</label>
													<div id="morenDis" style="margin-left:12%"></div> 
													<div class="controls" style="margin-top:20px">
														<input id="oldUrl" name="oldUrl" type="hidden" style="width:1000px">
														<!-- <img id="img_moren" name="img_moren"  style="height:100px;">  -->
														<input id="upMoren" type='file' name='file2' multiple  style="display: none">
														<input type="hidden" id="imgMoren"  name="imgMoren"/>
														<a id="moAagin" onclick="upMoren()" style="margin-left:1%">添加图片</a>
													</div>
												</div> 
											
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selArticleCategory">返回</a>

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
		    menuact("03_06_01");
		    
		    $.ajax({
				type : "GET",
				url : "queryChannel",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {},
				success : function(data) {
					var item = data.result.dataList;
					for(var i=0;i<item.length;i++){
						$('#channel_id').append('<option value='+item[i].id+'>'+item[i].title+'</option>');
					}
					var article=${articlecategory };
			    	$('#channel_id option[value="'+article.channel_id+'"]').prop('selected',true);
				}
				})
		});
	
	function infoset(){
		var articlecategory=${articlecategory };
		/* console.log(articlecategory); */
		var id=articlecategory.id;
		/* var	channel_id=articlecategory.channel_id; */
		var	type=articlecategory.type;
		var	title=articlecategory.title;
		var	call_index=articlecategory.call_index;
		var	parent_id=articlecategory.parent_id;
		var	class_list=articlecategory.class_list;
		var	class_layer=articlecategory.class_layer;
		var	sort_id=articlecategory.sort_id;
		var	link_url=articlecategory.link_url;
		var	img_url=articlecategory.img_url;
		var	content=articlecategory.content;
		/* var	seo_title=articlecategory.seo_title;
		var	seo_keywords=articlecategory.seo_keywords;
		var	seo_description=articlecategory.seo_description; */
		var	moren=articlecategory.moren; 
		$("#oldUrl").val(moren);
		console.log(moren);
		if(moren != null && moren != ""){
			var morenArr = moren.split(";");
			morenArr.splice(morenArr.length-1,1);
			var morenStr = "";
			for(var i in morenArr){
				morenStr += "<div class='morenClass"+i+"' style='margin-top:10px'><img style='width:120px' src='"+morenArr[i]+"'></img><a onclick='aaa(this)' style='margin-left:15px'>删除</a><br></div>";
			}
		$("#morenDis").append(morenStr); 
		}else{
			console.log("llllll");
			$("#moAagin").show();
		}
		
		
		$("#hiddenid").val(id);
		$("#channel_id").val(channel_id); 
		$("#type").val(type);
		$("#title").val(title);
		$("#call_index").val(call_index);
		/* $("#parent_id").val(parent_id); */
		/* $("#class_list").val(class_list);
		$("#class_layer").val(class_layer);
		$("#sort_id").val(sort_id); */
		$("#link_url").val(link_url);
		$("#img_url").attr('src',img_url);
		$("#imgUrl").val(img_url);
		$("#content").val(content);
		/* $("#seo_title").val(seo_title);
		$("#seo_keywords").val(seo_keywords);
		$("#seo_description").val(seo_description); */
		/* $("#moren").val(moren); */
		$("#img_moren").attr('src',moren);
		$("#imgMoren").val(moren); 



	};
	
	
	function aaa(a){
		var removeImg = $(a).prev().attr('src')+";";
		var oldImg = $("#oldUrl").val();
		var newImg = "";
		console.log(a);
		console.log(removeImg);
		console.log(oldImg); 
		oldImg = oldImg.replace(removeImg,"");
		console.log(oldImg);
		$("#oldUrl").val(oldImg);
		$(a).parent().remove();
		
	}
	
	
	function uploadpicdiv(){
		$('#img_url').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
	
	function upMoren(){
		$('#img_moren').hide();
		$('#upMoren').show();
		$('#moAgain').hide();
	} 
	
	</script>

</body>

</html>