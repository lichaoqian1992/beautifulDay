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
										用户站内短消息信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">消息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户站内短消息信息</a></li>
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
									<a  class="btn blue" href="insUserMessage"><i class="icon-pencil"></i>新增用户站内短消息信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul><li class="span2">收件人ID：<input type="text" id="userid" class="small m-wrap" ></li>
								<li class="span2" style="width:300px;">角色类型：
										<select class="m-warp small" id="userType">
										<option value="">全部</option>
										<option value="Buyer">用户</option>
										<option value="Shop">商家</option>
										<option value="Agent">代理</option>
										</select>
									</li>
								<li class="span2" style="width:300px;">消息类型：
										<select class="m-warp small" id="mType">
										<option value="">全部</option>
										<option value="0">系统消息</option>
										<option value="1">收件箱</option>
										<option value="2">发件箱</option>
										</select>
									</li>
								<li class="span2" style="width:300px;">是否查看:
										<select class="m-warp small" id="is_read">
										<option value="">全部</option>
										<option value="0">未阅</option>
										<option value="1">已阅</option>
										</select>
									</li>
								<li class="span2"><a  class="btn btn-primary search_sub"
														onclick="queryinfo(1)">搜索</a></li>
								
								</ul>
							</div>
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>短消息类型</th>
												<th>收件人</th>
												<th>收件人角色</th>
												<th>角色对应值</th>
												<th>是否查看</th>
												<th>短消息标题</th>
												<th>短消息内容</th>
												<th>发送时间</th>
												<th>阅读时间</th>
												<th>是否删除</th>
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
		    menuact("10_03_02");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	 if($("#userid").val()!=""){
		ajaxdatas+="&accept_user_id="+$("#userid").val();
     } 
	 if($("#userType").val()!=""){
			ajaxdatas+="&accept_user_role_type="+$("#userType").val();
	     } 
	 if($("#mType").val()!=""){
			ajaxdatas+="&type="+$("#mType").val();
	     } 
     if($("#is_read").val()!=""){
			ajaxdatas+="&is_read="+$("#is_read").val();
	     }
	$.ajax({
		type : "GET",
		url : "queryUserMessage",
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
					var id =item.id;
					var type=item.type;
					var post_user_id=item.post_user_id;
					var post_user_role_type=item.post_user_role_type;
					var post_user_role_value=item.post_user_role_value;
					var accept_user_id=item.accept_user_id;
					var accept_user_role_type=item.accept_user_role_type;
					var accept_user_role_value=item.accept_user_role_value;
					var is_read=item.is_read;
					var title=item.title;
					var content=item.content;
					var post_time=item.post_time;
					var read_time=item.read_time;
					var is_del=item.is_del;
					
					var typestr ="";
					switch(type){
					case 0:
						typestr="系统消息";
						break;
					case 1:
						typestr="收件箱";
						break;
					case 2:
						typestr="发件箱";
						break;	
					default :
						typestr=type;
						break;
					}
					
					var is_readstr="";
					switch(is_read){
					case 0:
						is_readstr="未阅";
						break;
					case 1:
						is_readstr="已阅";
						break;
					default :
						is_readstr=is_read;
						break;
					}
					
					var is_delstr="";
					switch(is_del){
					case 0:
						is_delstr="正常";
						break;
					case 0:
						is_delstr="删除";
						break;
					default :
						is_delstr=is_del;
						break;
					}
					switch(accept_user_role_type){
					case "Shop":
						accept_user_role_type="商家";
						break;
					case "Buyer":
						accept_user_role_type="用户";
						break;
					case "Agent":
						accept_user_role_type="代理";
						break;
					}
					 
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+typestr+"</td>"+
					/*  "<td>"+post_user_id+"</td>"+
					 "<td>"+post_user_role_type+"</td>"+
					 "<td>"+post_user_role_value+"</td>"+ */
					 "<td>"+accept_user_id+"</td>"+
					 "<td>"+accept_user_role_type+"</td>"+
					 "<td>"+accept_user_role_value+"</td>"+
					 "<td>"+is_readstr+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+content+"</td>"+
					 "<td>"+post_time+"</td>"+
					 "<td>"+read_time+"</td>"+
					 "<td>"+is_delstr+"</td>"+
					 "<td>"+"<a href='readUserMessage?id="+id+"'>修改</a><br><a href='delUserMessage?id="+id+"'>删除</a>"+"</td>"+
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