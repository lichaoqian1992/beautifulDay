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
								用户标识申请信息
		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户登录信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户标识申请信息
							</a></li>
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
									<a  class="btn blue" href="insUserFromValue"><i class="icon-pencil"></i>新增用户随机码</a>
								</div>
							</div>
							
							<div class="portlet-body">
							
							<div class="row_fluid ">
								<ul>
									<li>标识类型：
										<select id="from_type" style="width:102px;">
										  <option value="-1"></option>
										  <option value="0">临时</option>
										  <option value="1">永久</option>
										</select>
									</li>
									<li>是否有效:
										<select id="status" style="width:102px;">
										  <option value="-1"></option>
										  <option value="0">无效</option>
										  <option value="1">有效</option>
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
												<td>用户ID</td>
												<td>用户角色</td>
												<td>用户角色值</td>
												<td>申请标识</td>
												<td>标识对应公众账号</td>
												<td>标识二维码图片</td>
												<td>标识类型</td>
												<td>标识有效期</td>
												<td>申请时间</td>
												<td>是否有效</td>
												<td>是否删除</td>
												<td>来源关注总量</td>
												<td>来源注册总量</td>
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
		    menuact("01_01_03");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;

	if($("#from_type").val()!=""){
		ajaxdatas+="&from_type="+$("#from_type").val();
	}
	if($("#status").val()!=""){
		ajaxdatas+="&status="+$("#status").val();
	}
	 
	
	$.ajax({
		type : "GET",
		url : "queryUserFromValue",
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
					var	user_id=item.user_id;
					var	user_role_type=item.user_role_type;
					var	user_role_value=item.user_role_value;
					var	from_value=item.from_value;
					var	wechat_id=item.wechat_id;
					var	img_url=item.img_url;
					var	from_type=item.from_type;
					var	yx_time=item.yx_time;
					var	add_time=item.add_time;
					var	status=item.status;
					var	is_del=item.is_del;
					var	subscribe_count=item.subscribe_count;
					var	reg_count=item.reg_count;
					
					var One = "";
				    switch(is_del){
				        case 0:
				        	One = "未删除";
				        break;
				        
				        case 1:
				        	One = "已删除";
				        break;
				       
				        default:
				        	One = "";
				        break;
				    }
				    
				    var Tow = "";
				    switch(status){
				        case 0:
				        	Tow = "无效";
				        break;
				        
				        case 1:
				        	Tow = "有效";
				        break;
				        
				        default:
				        	Tow = "";
				        break;
				        
				    }
				    
				    var There = "";
				    switch(from_type){
				        case 0:
				        	There = "临时";
				        break;
				        
				        case 1:
				        	There = "永久";
				        break;
				        
				        default:
				        	There = "";
				        break;
				        
				    }

					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+from_value+"</td>"+
					 "<td>"+wechat_id+"</td>"+
					 "<td><img style='width:100px' src="+img_url+"></td>"+
					 "<td>"+There+"</td>"+
					 "<td>"+yx_time+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+Tow+"</td>"+
					 "<td>"+One+"</td>"+
					 "<td>"+subscribe_count+"</td>"+
					 "<td>"+reg_count+"</td>"+
					 "<td>"+"<a href='readUserFromValue?id="+id+"'>修改</a><br><a href='delUserFromValue?id="+id+"'>删除</a>"+"</td>"+
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