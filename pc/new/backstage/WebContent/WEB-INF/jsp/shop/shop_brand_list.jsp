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
								商家品牌		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家品牌</a></li>
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
									<a  class="btn blue" href="insShopBrand"><i class="icon-pencil"></i>新增商家品牌</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
					                <li>商品ID：<input type="text" id="shop_id" class="small m-wrap" ></li>
					                <li>品牌名称：<input type="text" id="name" class="small m-wrap" ></li>
					                <li>品牌类型:
										<select style="width:102px;" id="brand_type">
										  <option value="-1"></option>
										  <option value="0">国内</option>
										  <option value="1">国外</option>
										</select>
									</li>
									<li>申请类型:
										<select style="width:102px;" id="apply_type">
										  <option value="-1"></option>
										  <option value="0">以后品牌授权</option>
										  <option value="1">新品牌申请</option>
										</select>
									</li>
									<li>状态:
										<select style="width:102px;" id="state">
										  <option value="-1"></option>
										  <option value="0">申请中</option>
										  <option value="1">审核通过</option>
										  <option value="2">不通过</option>
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
												<td>商品ID</td>
												<td>品牌名称</td>
												<td>商标注册号</td>
												<td>品牌类型</td>
												<td>品牌logo</td>
												<td>品牌归属人</td>
												<td>品牌授权书</td>
												<td>授权到期时间</td>
												<td>系统品牌Id</td>
												<td>申请类型</td>
												<td>状态</td>
												<td>是否删除</td>
												<td>添加时间</td>
												<td>更新时间</td>
												<!-- <td>备注</td> -->

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
		    menuact("02_02_01");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#shop_id").val()!=""){
		ajaxdatas+="&shop_id="+$("#shop_id").val();
	}
	if($("#brand_type").val()!=""){
		ajaxdatas+="&brand_type="+$("#brand_type").val();
	}
	if($("#name").val()!=""){
		ajaxdatas+="&name="+$("#name").val();
	}
	if($("#apply_type").val()!=""){
		ajaxdatas+="&apply_type="+$("#apply_type").val();
	}
	if($("#state").val()!=""){
		ajaxdatas+="&state="+$("#state").val();
	}
	
	$.ajax({
		type : "GET",
		url : "queryShopBrand",
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
					var	shop_id=item.shop_id;
					var	name=item.name;
					var	register_no=item.register_no;
					var	brand_type=item.brand_type;
					var	logo=item.logo;
					var	ascription_person=item.ascription_person;
					var	authorize_pics=item.authorize_pics;
					var	authorize_time=item.authorize_time;
					var	sys_brandId=item.sys_brandId;
					var	apply_type=item.apply_type;
					var	state=item.state;
					var	is_del=item.is_del;
					var	add_time=item.add_time;
					var	update_time=item.update_time;
					var	remark=item.remark;


					var One = "";
										
					switch(brand_type){
						case 0:
							One = "国内";
						break;
						
						case 1:
							One = "国外";
						break;
						
						default:
							One = "";
						break;
					}
					
					var Tow = "";
					
					switch(apply_type){
						case 0:
							Tow = "以后品牌授权";
						break;
						
						case 1:
							Tow = "新品牌申请";
						break;
						
						default:
							Tow = "";
						break;
					}
					
					var There = "";
					
					switch(state){
						case 0:
							There = "申请中";
						break;
						
						case 1:
							There = "审核通过";
						break;
						
						case 2:
							There = "不通过";
						break;
						
						default:
							There = "";
						break;
					}
					
					var Four = "";
					
					switch(is_del){
						case 0:
							Four = "默认";
						break;
						
						case 1:
							Four = "删除";
						break;
						
						default:
							Four = "";
						break;
					}
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+shop_id+"</td>"+
					 "<td>"+name+"</td>"+
					 "<td>"+register_no+"</td>"+
					 "<td>"+One+"</td>"+
					 "<td><img style='width:100px' src="+logo+"></td>"+
					 "<td>"+ascription_person+"</td>"+
					 "<td><img style='width:100px' src="+authorize_pics+"></td>"+
					 "<td>"+authorize_time+"</td>"+
					 "<td>"+sys_brandId+"</td>"+
					 "<td>"+Tow+"</td>"+
					 "<td>"+There+"</td>"+
					 "<td>"+Four+"</td>"+
					 "<td>"+add_time+"</td>"+
					 "<td>"+update_time+"</td>"+
					 /* "<td>"+remark+"</td>"+ */


					 "<td>"+"<a href='readShopBrand?id="+id+"'>修改</a><br><a href='delShopBrand?id="+id+"'>删除</a>"+"</td>"+
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