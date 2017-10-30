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
										用户商家信息
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家详细信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户商家信息</a></li>
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
									<a  class="btn blue" href="insShopInfo"><i class="icon-pencil"></i>新增用户商家信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<!-- <div class="row-fluid ">
								<div class="span2">商家ID：<input type="text" id="shopuseridstr"class="small m-wrap" ></div>
							
								<div class="span2"><a  class="btn btn-primary search_sub blue"
														onclick="queryinfo(1)">搜索</a></div>
								
								
							</div> -->
							<div class=row_fluid>
							<ul>
								<li>用户ID：<input type="text" id="user_id" class="small m-wrap" ></li>
							   	<li>店铺名称：<input type="text" id="name" class="small m-wrap" ></li>
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
												<th>商家分组    </th>
<th>用户ID      </th>
<th>店铺名称    </th>
<!-- <th>店铺简介    </th> -->
<!-- <th>店铺图片    </th> -->
<th>店铺关键字  </th>
<!-- <th>店铺标志图片</th> -->
<!-- <th>手机店铺标志</th> -->
<th>联系电话    </th>
<th>在线联系    </th>
<th>联系地址    </th>
<!-- <th>二级域名    </th> -->
<th>店铺所在区域</th>
<!-- <th>地理坐标经度</th>
<th>地理坐标维度</th> -->
<th>是否支持配送</th>
<th>配送范围    </th>
<th>配送地理范围</th>
<th>是否支持快递</th>
<th>支持快递类型</th>
<th>是否支持到付</th>
<th>是否需要预约</th>
<!-- <th>是否接入插件</th> -->
<th>评价次数    </th>
<th>整体评分    </th>
<!-- <th>服务评分    </th>
<th>包装评分    </th>
<th>配送评分    </th> -->
<!-- <th>最近代赔次数</th>
<th>最近代赔金额</th>
<th>最近代赔时间</th> -->
<th>店铺状态    </th>
<th>更新内容    </th>
<th>更新时间    </th>
<th>新增时间    </th>
<th>是否删除    </th>
<th>主营业务    </th>
<th>客服来源    </th>
<th>是否签约    </th>
<th>推荐        </th>
<th>营业时间    </th>
<th>店铺开关    </th>
<th>业务提点    </th>
<th>店铺帮助须知</th>
<!-- <th>提现开关</th> -->



												
												
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
		  	menuact("02_01_01");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	if($("#user_id").val()!=""){
		ajaxdatas+="&user_id="+$("#user_id").val();
	}
	if($("#name").val()!=""){
		ajaxdatas+="&name="+$("#name").val();
	}
	if($("#is_del").val()!=""){
		ajaxdatas+="&is_del="+$("#is_del").val();
	}
	
	
	$.ajax({
		type : "GET",
		url : "queryShopInfo",
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
					var id =item.id;
					var group_id =item.group_id;
					var user_id =item.user_id;
					var name =item.name;
					var content =item.content;
					var pics =item.pics;
					var TAG =item.TAG;
					var pc_logo =item.pc_logo;
					var wap_logo =item.wap_logo;
					var mobile =item.mobile;
					var online_content =item.online_content;
					var address =item.address;
					/* var weburl =item.weburl; */
					var area =item.area;
					var longitude =item.longitude;
					var latitude =item.latitude;
					var is_distribution =item.is_distribution;
					var distribution_area =item.distribution_area;
					var distribution_distance =item.distribution_distance;
					var is_express =item.is_express;
					var express_types =item.express_types;
					var is_local_transaction =item.is_local_transaction;
					var is_booking =item.is_booking;
					var is_plugins =item.is_plugins;
					var review_times =item.review_times;
					var review_score =item.review_score;
					var service_review_score =item.service_review_score;
					var pack_review_score =item.pack_review_score;
					var distribution_review_score =item.distribution_review_score;
					var damage_times =item.damage_times;
					var damage_money =item.damage_money;
					var damage_date =item.damage_date;
					var state =item.state;
					var remark =item.remark;
					var update_time =item.update_time;
					var add_time =item.add_time;
					var is_del =item.is_del;
					var main_business =item.main_business;
					var from_value =item.from_value;
					var is_sign_up =item.is_sign_up;
					var hot =item.hot;
					var yysj =item.yysj;
					var dpkg =item.dpkg;
					var percentage =item.percentage;
					var matter =item.matter;
					var is_balance =item.is_balance;
					is_distribution == 1 ? is_distribution = "是" : is_distribution = "否";
					is_express == 0 ? is_express = "不支持" : is_express = "支持";
					is_local_transaction == 0 ? is_local_transaction = "不支持" : is_local_transaction = "支持";
					is_booking == 1 ? is_booking = "是" : is_booking = "否";
					is_plugins == 1 ? is_plugins = "是" : is_plugins = "否";
					is_del == 1 ? is_del = "删除" : is_del = "正常";
					is_sign_up == 1 ? is_sign_up = "是" : is_sign_up = "否";
					dpkg == 0 ? dpkg = "关闭" : dpkg = "开启";
					is_balance == 0||is_balance == null ? is_balance = "自动提现" : is_balance = "关闭自动提现";
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
					var hotStr = "";
					switch (hot) {
				    case 0:
				      hotStr = "不推荐";
				      break;
				    case 1:
				      hotStr = "全国";
				      break;
				    case 2:
				      hotStr = "省";
				      break;
				    case 3:
				      hotStr = "市";
				      break;
				    case 4:
				      hotStr = "区县";
				      break;
				    case 5:
				      hotStr = "乡镇";
				      break;
				    default:
				      hotStr = "";
				    }
					
					
					
					 var rowhtml ="<tr>"+
						"<td>"+no+"</td>"+
						"<td>"+group_id                 +"</td>"+
						"<td>"+user_id                  +"</td>"+
						"<td>"+name                     +"</td>"+
						/* "<td>"+content                  +"</td>"+ */
						/* "<td>"+pics                     +"</td>"+ */
						"<td>"+TAG                      +"</td>"+
						/* "<td>"+pc_logo                  +"</td>"+ */
						/* "<td>"+wap_logo                 +"</td>"+ */
						"<td>"+mobile                   +"</td>"+
						"<td>"+online_content           +"</td>"+
						"<td>"+address                  +"</td>"+
						/* "<td>"+weburl                   +"</td>"+ */
						"<td>"+area                     +"</td>"+
						/* "<td>"+longitude                +"</td>"+
						"<td>"+latitude                 +"</td>"+ */
						"<td>"+is_distribution          +"</td>"+
						"<td>"+distribution_area        +"</td>"+
						"<td>"+distribution_distance    +"</td>"+
						"<td>"+is_express               +"</td>"+
						"<td>"+express_types            +"</td>"+
						"<td>"+is_local_transaction     +"</td>"+
						"<td>"+is_booking               +"</td>"+
						/* "<td>"+is_plugins               +"</td>"+ */
						"<td>"+review_times             +"</td>"+
						"<td>"+review_score             +"</td>"+
						/* "<td>"+service_review_score     +"</td>"+
						"<td>"+pack_review_score        +"</td>"+
						"<td>"+distribution_review_score+"</td>"+ */
						/* "<td>"+damage_times             +"</td>"+
						"<td>"+damage_money             +"</td>"+
						"<td>"+damage_date              +"</td>"+ */
						"<td>"+stateStr                    +"</td>"+
						"<td>"+remark                   +"</td>"+
						"<td>"+update_time              +"</td>"+
						"<td>"+add_time                 +"</td>"+
						"<td>"+is_del                   +"</td>"+
						"<td>"+main_business            +"</td>"+
						"<td>"+from_value               +"</td>"+
						"<td>"+is_sign_up               +"</td>"+
						"<td>"+hotStr                      +"</td>"+
						"<td>"+yysj                     +"</td>"+
						"<td>"+dpkg                     +"</td>"+
						"<td>"+percentage               +"</td>"+
						"<td>"+matter                   +"</td>"+
						/* "<td>"+is_balance                   +"</td>"+ */


						"<td>"+"<a href=readShopInfo?id="+id+">修改</a><a href=delShopInfo?id="+id+">删除</a>"+"</td>"+
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
	
	function deviderset(totalcount,pagecount,pageindex){
		
		
		var summery ="共"+totalcount+"条数据--共"+pagecount+"页";
		$("#dividersummery").html(summery);
		
		var deviderhtml="";
		if(pageindex==1){
			deviderhtml+="<ul><li  class='prev ' style='display:none'><a  onclick='queryinfo("+Number(pageindex-1)+")'>← <span class='hidden-480'>Prev</span></a></li>";
		}else{
			deviderhtml+="<ul><li  id='firstdiv' class='prev '><a  onclick='queryinfo("+Number(pageindex-1)+")'>← <span class='hidden-480'>Prev</span></a></li>";
		}
		
		
		if(pagecount<6){
			
			for(var i=1;i<pageindex;i++){
				
				deviderhtml+="<li ><a onclick='queryinfo("+i+")'>"+i+"</a></li>";
				
			}
			
			
		}else{
			
			if(pageindex<3){
				for(var j=1;j<6;j++){
					
					deviderhtml+="<li ><a onclick='queryinfo("+j+")'>"+j+"</a></li>";
					
				}
				
			}else if(pageindex<=pagecount-2){
				
				var m =pageindex-2;
				for(var k=0;k<5;k++){
					
					deviderhtml+="<li ><a onclick='queryinfo("+m+")'>"+m+"</a></li>";
					m++;
				}
				
				
			}else if(pageindex>pagecount-2){
				var n =pageindex-4;
				for(var q=0;q<5;q++){
					deviderhtml+="<li ><a onclick='queryinfo("+n+")'>"+n+"</a></li>";
					n++;
				}
			}
			
		}
		

		if(pageindex ==pagecount){
			deviderhtml+="<li id='lastdiv ' style='display:none'><a  onclick='queryinfo("+Number(pageindex+1)+")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
		}else{
			deviderhtml+="<li id='lastdiv '><a  onclick='queryinfo("+Number(pageindex+1)+")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
		}
		
		$("#devider").html(deviderhtml);
	}

	</script>

</body>

</html>

</body>
</html>