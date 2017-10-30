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
										用户收货地址信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">买家详细信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户收货地址信息</a></li>
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
									<a  class="btn blue" href="insRecAddr"><i class="icon-pencil"></i>新增用户收货地址信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
                 <li>用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
                 <li>收件人：<input type="text" id="acceptname" class="small m-wrap" ></li>
                 <li>手机：<input type="text" id="mobile" class="small m-wrap" ></li>
                 <li>是否默认:
					<select style="width:102px;" id="is_default">
					  <option value="-1"></option>
					  <option value="0">否</option>
					  <option value="1">是</option>
					</select>
				</li>
				<li>是否外卖:
					<select style="width:102px;" id="is_distribution">
					  <option value="-1"></option>
					  <option value="0">否</option>
					  <option value="1">是</option>
					</select>
				</li>
				<li>是否删除:
					<select style="width:102px;" id="is_del">
					  <option value="-1"></option>
					  <option value="0">正常</option>
					  <option value="1">删除</option>
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
												<td>用户角色类型</td>
												<td>角色对应值</td>
												<td>收件人姓名</td>
												<td>省市区</td>
												<td>详细地址</td>
												<td>手机号码</td>
												<td>电话号码</td>
												<td>电子邮箱</td>
												<td>邮政编码</td>
												<td>是否默认</td>
												<td>添加时间</td>
												<td>是否删除</td>
												<!-- <td>地理坐标经度</td>
												<td>地理坐标维度</td> -->
												<td>是否外卖</td>
												<td>性别</td>
												<td>门牌</td>
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
		    menuact("01_02_06");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#acceptname").val()!=""){
		ajaxdatas+="&accept_name="+$("#acceptname").val();
	}
	if($("#mobile").val()!=""){
		ajaxdatas+="&mobile="+$("#mobile").val();
	}
	
	if($("#is_default").val()!=""){
		ajaxdatas+="&is_default="+$("#is_default").val();
	}
	if($("#is_distribution").val()!=""){
		ajaxdatas+="&is_distribution="+$("#is_distribution").val();
	}
	if($("#is_del").val()!=""){
		ajaxdatas+="&is_del="+$("#is_del").val();
	}
	
	
	$.ajax({
		type : "GET",
		url : "queryRecAddr",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : ajaxdatas,
		success : function(freshdata) {
			console.log(freshdata);
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
					var user_id=item.user_id;
					var user_role_type=item.user_role_type;
					var user_role_value=item.user_role_value;
					var accept_name=item.accept_name;
					var area=item.area;
					var address=item.address;
					var mobile=item.mobile;
					var telphone=item.telphone;
					var email=item.email;
					var post_code=item.post_code;
					var is_default=item.is_default;
					var add_time=item.add_time;
					var is_del=item.is_del;
					var longitude=item.longitude;
					var latitude=item.latitude;
					var is_distribution=item.is_distribution;
					var sex=item.sex;
					var doorplate=item.doorplate;
					 
					is_default == 0 ? is_default = "否" : is_default = "是";
					is_del == 0 ? is_del = "删除" : is_del = "正常";
					is_distribution == 0 ? is_distribution = "否" : is_distribution = "是";
					
					
					 
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+user_id+"</td>"+
					 "<td>"+user_role_type+"</td>"+
					 "<td>"+user_role_value+"</td>"+
					 "<td>"+accept_name+"</td>"+
					 "<td>"+area+"</td>"+
					 "<td>"+address+"</td>"+
					 "<td>"+mobile+"</td>"+
					 "<td>"+telphone+"</td>"+
					 "<td>"+email+"</td>"+
					 "<td>"+post_code+"</td>"+
					 "<td>"+is_default+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+is_del+"</td>"+
					/*  "<td>"+longitude+"</td>"+
					 "<td>"+latitude+"</td>"+ */
					 "<td>"+is_distribution+"</td>"+
					 "<td>"+sex+"</td>"+
					 "<td>"+doorplate+"</td>"+
					 "<td>"+"<a href='readRecAddr?id="+id+"'>修改</a><br><a href='delRecAddr?id="+id+"'>删除</a>"+"</td>"+
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