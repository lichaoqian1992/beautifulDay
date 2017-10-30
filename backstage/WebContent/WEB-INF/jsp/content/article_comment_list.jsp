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
									内容评论信息记录	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">发布信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">内容评论信息记录</a></li>
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
									<a  class="btn blue" href="insArticleComment"><i class="icon-pencil"></i>新增内容评论信息记录</a>
								</div>
							</div>
							
							<div class="portlet-body">
							
								
							<div class="row_fluid ">
								<ul>
									 <li>文章ID：<input type="text" id="article_id" class="small m-wrap" ></li>
					                <li>评论内容：<input type="text" id="content" class="small m-wrap" >
					                </li>
					                <li style="width:300px;">是否锁定：
					                	<select class="m-wrap small" id="is_lock">
					                		<option value="">全部</option>
											<option value="0">正常</option>
											<option value="1">锁定</option>
										</select>
					                </li>
					                <li style="width:300px;">是否删除：
					                	<select class="m-wrap small" id="is_del">
					                		<option value="">全部</option>
											<option value="0">正常</option>
											<option value="1">删除</option>
										</select>
					                </li>
					                <li><a  class="btn btn-primary search_sub"
					                            onclick="queryinfo(1)">搜索</a></li>
								</ul>
			              </div>
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<td>频道ID</td>
												<td>文章ID</td>
												<td>订单ID</td>
												<td>父评论ID</td>
												<td>用户ID</td>
												<td>用户角色类型</td>
												<td>角色对应值</td>
												<td>用户IP</td>
												<td>评论内容</td>
												<td>评论图片内容</td>
												<td>是否锁定</td>
												<td>发表时间</td>
												<td>是否已答复</td>
												<td>回复内容</td>
												<td>回复时间</td>
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
		    menuact("04_03_06");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#is_del").val()!=""){
		ajaxdatas+="&is_del="+$("#is_del").val();
	}
	if($("#is_lock").val()!=""){
		ajaxdatas+="&is_lock="+$("#is_lock").val();
	}
	if($("#article_id").val()!=""){
		ajaxdatas+="&article_id="+$("#article_id").val();
	}
	if($("#content").val()!=""){
		ajaxdatas+="&content="+$("#content").val();
	}
	
	
	$.ajax({
		type : "GET",
		url : "queryArticleComment",
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
					var channel_id =item.channel_id;
					var article_id =item.article_id;
					var order_id =item.order_id;
					var parent_id =item.parent_id;
					var user_id =item.user_id;
					var user_role_type =item.user_role_type;
					var user_role_value =item.user_role_value;
					var user_ip =item.user_ip;
					var content =item.content;
					var pics =item.pics;
					var is_lock =item.is_lock;
					var add_time =item.add_time;
					var is_reply =item.is_reply;
					var reply_content =item.reply_content;
					var reply_time =item.reply_time;
					var is_del =item.is_del;
					var xx = "";
					for(var j=0;j<pics.split(",").length;j++){
						xx += "<img style='width:100px;' src='"+pics.split(",")[j]+"'>";
					}
					
					switch(user_role_type){
		          	case "Buyer":
		          		user_role_type = "用户";
		          		break;
		          	case "Shop":
		          		user_role_type = "商家";
		          		break;
		          	case "Agent":
		          		user_role_type = "代理";
		          		break;
		          }
					switch(is_lock){
		          	case 0:
		          		is_lock = "正常";
		          		break;
		          	case 1:
		          		is_lock = "锁定";
		          		break;
		          }
					switch(is_del){
			          	case 0:
			          		is_del = "正常";
			          		break;
			          	case 1:
			          		is_del = "删除";
			          		break;
			          }
					
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+channel_id+"</td>"+
					 "<td>"+article_id+"</td>"+
					 "<td>"+order_id+"</td>"+
					 "<td>"+parent_id+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+user_ip+"</td>"+
					 "<td><textarea>"+content+"</textarea></td>"+
					 "<td><div style='float:left;'>"+xx+"</div></td>"+
					 "<td>"+is_lock+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+is_reply+"</td>"+
					 "<td>"+reply_content+"</td>"+
					 "<td>"+reply_time+"</td>"+
					 "<td>"+is_del+"</td>"+
					 
					 "<td>"+"<a href='readArticleComment?id="+id+"'>修改</a><a href='delArticleComment?id="+id+"'>删除</a>"+"</td>"+
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