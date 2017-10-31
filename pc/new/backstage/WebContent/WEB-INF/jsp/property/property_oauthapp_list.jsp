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
								用户第三方登陆类型信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">系统配置</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户第三方登陆类型信息</a></li>
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
									<a  class="btn blue" href="insUserOauthApp"><i class="icon-pencil"></i>新增用户第三方登陆类型信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							
								<div class=row_fluid>
									<ul>
										<li>标题：<input type="text" id="title" class="small m-wrap" ></li>
										<li>描述：<input type="text" id="remark" class="small m-wrap" ></li>
										<li style="width:230px">支持移动设备：
											<select  id="is_mobile"	 style="width:102px">
												<option value="-1">不限</option>
												<option value="0">通用</option>
												<option value="1">电脑</option>
												<option value="1">移动</option>
											</select>
										</li>
										<li>是否启用：
											<select  id="is_lock"	 style="width:102px">
												<option value="-1">不限</option>
												<option value="0">启用</option>
												<option value="1">不启用</option>
											</select>
										</li>
										<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
									</ul>	
								</div>							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<td>标题</td>
												<td>显示图片地址</td>
												<td>三方标记ID</td>
												<td>三方通讯KEY</td>
												<td>描述</td>
												<td>排序数字</td>
												<td>支持移动设备</td>
												<td>是否启用</td>
												<td>接口目录</td>
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
		    menuact("03_04_09");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#title").val()!=""){
		ajaxdatas+="&title="+$("#title").val();
	}
	if($("#remark").val()!=""){
		ajaxdatas+="&remark="+$("#remark").val();
	}
	if($("#is_mobile").val()!=""){
		ajaxdatas+="&is_mobile="+$("#is_mobile").val();
	}
	if($("#is_lock").val()!=""){
		ajaxdatas+="&is_lock="+$("#is_lock").val();
	}
	 
	
	$.ajax({
		type : "GET",
		url : "queryUserOauthApp",
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
					var	title=item.title
					var	img_url=item.img_url
					var	app_id=item.app_id
					var	app_key=item.app_key
					var	remark=item.remark
					var	sort_id=item.sort_id
					var	is_mobile=item.is_mobile
					var	is_lock=item.is_lock
					var	api_path=item.api_path
					var	is_del=item.is_del

					var mobileStr = "";
					switch (is_mobile) {
					case 0:
					  mobileStr = "通用";
					  break;
					case 1:
					  mobileStr = "电脑";
					  break;
					case 2:
					  mobileStr = "移动";
					  break;
					default:
					  mobileStr = "";
					}
					var lockStr = "";
					switch (is_lock) {
					case 0:
						lockStr = "启用";
					  break;
					case 1:
						lockStr = "不启用";
					  break;
					default:
						lockStr = "";
					}
					var delStr = "";
					switch (is_del) {
					case 0:
						delStr = "删除";
					  break;
					case 1:
						delStr = "正常";
					  break;
					default:
						delStr = "";
					}
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td><img src='"+img_url+"' style='width:100px'></td>"+
					 "<td>"+app_id+"</td>"+
					 "<td>"+app_key+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+sort_id+"</td>"+
					 "<td>"+mobileStr+"</td>"+
					 "<td>"+lockStr+"</td>"+
					 "<td>"+api_path+"</td>"+
					 "<td>"+delStr+"</td>"+

					 "<td>"+"<a href='readUserOauthApp?id="+id+"'>修改</a><br><a href='delUserOauthApp?id="+id+"'>删除</a>"+"</td>"+
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