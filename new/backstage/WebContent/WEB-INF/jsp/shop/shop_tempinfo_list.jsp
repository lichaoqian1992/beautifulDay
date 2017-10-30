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
										用户商家信息修改临存
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家详细信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户商家信息修改临存</a></li>
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
									<a  class="btn blue" href="insTempShopInfo"><i class="icon-pencil"></i>新增用户商家信息修改临存</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row_fluid ">
								<ul>
					                 <li>用户ID：<input type="text" id="userid" class="small m-wrap" ></li>
					                 <li>商家ID：<input type="text" id="shopuseridstr" class="small m-wrap" ></li>
					                 <li>店铺名：<input type="text" id="name" class="small m-wrap" ></li>
					                 <li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
				                </ul> 
							</div>
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th style="display:none">ID      </th>
												<th>信息ID      </th>
												<th>商家分组    </th>
												<th>用户ID      </th>
												<th>店铺名称    </th>
												<th>店铺简介    </th>
												<th>店铺关键字  </th>
												<th>店铺标志图片</th>
												<th>手机店铺标志</th>
												<th>联系电话    </th>
												<th>联系地址    </th>
												<th>二级域名    </th>
												<th>店铺所在区域</th>
												<th>是否支持配送</th>
												<th>配送范围    </th>
												<th>配送地理范围</th>
												<th>是否支持快递</th>
												<th>支持快递类型</th>
												<th>是否支持到付</th>
												<th>是否需要预约</th>
												<th>是否接入插件</th>
												<th>店铺状态    </th>
												<!-- <th>更新内容    </th> -->
												<th>更新时间    </th>
												<th>业务提点    </th>
												
												
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
		    menuact("02_01_02");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	if($("#shopuseridstr").val()!=""){
		ajaxdatas+="&id="+$("#shopuseridstr").val();
	}
	if($("#userid").val()!=""){
		ajaxdatas+="&user_id="+$("#userid").val();
	}
	if($("#name").val()!=""){
		ajaxdatas+="&name="+$("#name").val();
	}
	
	
	
	$.ajax({
		type : "GET",
		url : "queryTempShopInfo",
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
					var id                   =item.id;
					var recorded_id            =item.recorded_id;
					var group_id             =item.group_id;
					var user_id              =item.user_id;
					var name                 =item.name;
					var content              =item.content;
					var TAG                  =item.TAG;
					var pc_logo              =item.pc_logo;
					var wap_logo             =item.wap_logo;
					var mobile               =item.mobile;
					var address              =item.address;
					var weburl               =item.weburl;
					var area                 =item.area;
					var is_distribution      =item.is_distribution;
					var distribution_area    =item.distribution_area;
					var distribution_distance=item.distribution_distance;
					var is_express           =item.is_express;
					var express_types        =item.express_types;
					var is_local_transaction =item.is_local_transaction;
					var is_booking           =item.is_booking;
					var is_plugins           =item.is_plugins;
					var state                =item.state;
					var remark               =item.remark;
					var update_time          =item.update_time;
					var percentage           =item.percentage;
					 
				    is_distribution == 1 ? is_distribution = "是" : is_distribution = "否";
				    is_express == 0 ? is_express = "不支持" : is_express = "支持";
				    is_local_transaction == 0 ? is_local_transaction = "不支持" : is_local_transaction = "支持";
				    is_booking == 1 ? is_booking = "是" : is_booking = "否";
				    is_plugins == 1 ? is_plugins = "是" : is_plugins = "否";
				    var stateStr = "";
				    switch (state) {
				      case 0:
				        stateStr = "待审核";
				        break;
				      case 1:
				        stateStr = "已通过";
				        break;
				      case 2:
				        stateStr = "不通过";
				        break;
				      case 3:
				        stateStr = "被冻结";
				        break;
				      default:
				        stateStr = "";
				      }
				    

 
					
					
					
					 var rowhtml ="<tr>"+
						"<td>"+no+"</td>"+
						"<td style='display:none'>"+id          +"</td>"+
						"<td>"+recorded_id          +"</td>"+
						"<td>"+group_id             +"</td>"+
						"<td>"+user_id              +"</td>"+
						"<td>"+name                 +"</td>"+
						"<td>"+content              +"</td>"+
						"<td>"+TAG                  +"</td>"+
						"<td><img style='width:100px' src="+pc_logo+"></td>"+
						"<td><img style='width:100px' src="+wap_logo+"></td>"+
						"<td>"+mobile               +"</td>"+
						"<td>"+address              +"</td>"+
						"<td>"+weburl               +"</td>"+
						"<td>"+area                 +"</td>"+
						"<td>"+is_distribution      +"</td>"+
						"<td>"+distribution_area    +"</td>"+
						"<td>"+distribution_distance+"</td>"+
						"<td>"+is_express           +"</td>"+
						"<td>"+express_types        +"</td>"+
						"<td>"+is_local_transaction +"</td>"+
						"<td>"+is_booking           +"</td>"+
						"<td>"+is_plugins           +"</td>"+
						"<td>"+stateStr                +"</td>"+
						/* "<td>"+remark               +"</td>"+ */
						"<td>"+update_time          +"</td>"+
						"<td>"+percentage           +"</td>"+



						"<td>"+"<a href=readTempShopInfo?id="+id+">修改</a><a href=delTempShopInfo?id="+id+">删除</a>"+"</td>"+
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

</body>
</html>