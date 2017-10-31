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
										用户列表<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户信息管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户列表</a></li>
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
									<a  class="btn blue" href="insUser"><i class="icon-pencil"></i>新增用户</a>
								</div>
							</div>
							
							<div class="portlet-body">
							
							<div class=row_fluid>
							<ul>
								<li>用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
							   	<li>用户名：<input type="text" id="username" class="small m-wrap" ></li>
							
								<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
							</ul>	
							</div>
							
							
							
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>用户组ID</th>
												<th>用户名</th>
												<!-- <th>加密字符串</th>
												<th>用户密码</th> -->
												<th>用户手机</th>
												<th>电子邮箱</th>
												<!-- <th>头像</th> -->
												<th>昵称</th>
												<th>性别</th>
												<th>生日</th>
												<th>电话</th>
												<th>所属地区</th>
												<th>详细地址</th>
												<th>QQ号码</th>
												<!-- <th>支付密码</th> -->
												<th>账户状态</th>
												<th>状态对应说明</th>
												<th>注册时间</th>
												<th>注册IP</th>
												<th>注册来源</th>
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
		    onkey(2);
		    queryinfo(1);
		    setactive("221");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#userid").val()!=""){
		ajaxdatas+="&id="+$("#userid").val();
	}
	if($("#username").val()!=""){
		ajaxdatas+="&user_name="+$("#username").val();
	}
	
	$.ajax({
		type : "GET",
		url : "queryUser",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : ajaxdatas,
		success : function(freshdata) {
			console.log(freshdata);
			if(freshdata.status=="1"){
				alert("结果不存在！");
			}
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
					var group_id=item.group_id;
					var user_name=item.user_name;
					/* var salt=item.salt;
					var password=item.password; */
					var mobile=item.mobile;
					var email=item.email;
					/* var avatar=item.avatar; */
					var nick_name=item.nick_name;
					var sex=item.sex;
					var birthday=item.birthday;
					var telphone=item.telphone;
					var area=item.area;
					var address=item.address;
					var qq=item.qq;
				/* 	var pay_password=item.pay_password; */
					var status=item.status;
					var remark=item.remark;
					var reg_time=item.reg_time;
					var reg_ip=item.reg_ip;
					var from_value=item.from_value;
					var is_del=item.is_del;

					var statusStr = "";
				    switch(status){
				        case 0:
				        statusStr = "正常";
				        break;
				        
				        case 1:
				        statusStr = "待验证";
				        break;
				       
				        case 2:
				        statusStr = "待审核";
				        break;
				       
				        case 3:
				        statusStr = "锁定";
				        break;
				       
				        default:
				        statusStr = "黑名单";
				        break;
				    }
				    
				    var is_delStr = "";
				    switch(is_del){
				        case 0:
				        is_delStr = "正常";
				        break;
				        
				        case 1:
				        is_delStr = "删除";
				        break;
				        
				    }
					
					
					 
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+group_id+"</td>"+
					 "<td>"+user_name+"</td>"+
					 /* "<td>"+salt+"</td>"+ 
					 "<td>"+password+"</td>"+ */
					 "<td>"+mobile+"</td>"+
					 "<td>"+email+"</td>"+
					/*  "<td>"+avatar+"</td>"+ */
					 "<td>"+nick_name+"</td>"+
					 "<td>"+sex+"</td>"+
					 "<td>"+birthday+"</td>"+
					 "<td>"+telphone+"</td>"+
					 "<td>"+area+"</td>"+
					 "<td>"+address+"</td>"+
					 "<td>"+qq+"</td>"+
					 /* "<td>"+pay_password+"</td>"+ */
					 "<td>"+statusStr+"</td>"+
					 "<td>"+remark+"</td>"+
					 "<td>"+reg_time+"</td>"+
					 "<td>"+reg_ip+"</td>"+
					 "<td>"+from_value+"</td>"+
					 "<td>"+is_delStr+"</td>"+
					 "<td>"+"<a href='readUser?id="+id+"'>修改</a>&nbsp<a href='delUser?id="+id+"'>删除</a>"+"</td>"+
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