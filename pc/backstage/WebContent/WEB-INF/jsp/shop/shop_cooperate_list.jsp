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
										商家业务合作列表<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务合作列表</a></li>
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
									<a  class="btn blue" href="insCooperate"><i class="icon-pencil"></i>新增商家业务合作列表</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row-fluid ">
								<div class="span2">商家ID：<input type="text" id="shopuseridstr"class="small m-wrap" ></div>
							
								<div class="span2"><a  class="btn btn-primary search_sub blue"
														onclick="queryinfo(1)">搜索</a></div>
								
								
							</div>
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>用户ID      </th>
												<th>角色类型    </th>
												<th>角色对应值  </th>
												<th>业务名称    </th>
												<th>业务简称    </th>
												<th>对应频道ID  </th>
												<th>业务简介    </th>
												<th>业务有效期  </th>
												<th>默认保证金  </th>
												<th>默认手续费  </th>
												<th>默认提成比例</th>
												<th>实缴保证金  </th>
												<th>实缴手续费  </th>
												<th>实缴提成比例</th>
												<th>电子协议    </th>
												<th>签约协议    </th>
												<th>当前状态    </th>
												<th>申请时间    </th>
												<th>更新时间    </th>
												<th>描述        </th>
												<th>是否删除    </th>
												<th>业务开关    </th>
												<th>业务描述    </th>
												<th>业务规则    </th>
												
												
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
		    onkey(3);
		    queryinfo(1);
		    setactive("332");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	if($("#shopuseridstr").val()!=""){
		ajaxdatas+="&shop_user_id="+$("#shopuseridstr").val();
	}
	
	
	
	$.ajax({
		type : "GET",
		url : "queryCooperate",
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
					var id             =item.id;
					var user_id        =item.user_id;
					var user_role_type =item.user_role_type;
					var user_role_value=item.user_role_value;
					var name           =item.name;
					var call_index     =item.call_index;
					var channel_id     =item.channel_id;
					var content        =item.content;
					var valid_time     =item.valid_time;
					var deposit        =item.deposit;
					var poundage       =item.poundage;
					var percentage     =item.percentage;
					var user_deposit   =item.user_deposit;
					var user_poundage  =item.user_poundage;
					var user_percentage=item.user_percentage;
					var agreement      =item.agreement;
					var user_agreement =item.user_agreement;
					var status         =item.status;
					var add_time       =item.add_time;
					var update_time    =item.update_time;
					var remark         =item.remark;
					var is_del         =item.is_del;
					var ywkg           =item.ywkg;
					var ywms           =item.ywms;
					var ywgz           =item.ywgz;
					is_del == 1 ? is_del = "删除" :is_del = "正常";
					ywkg == 0 ? ywkg = "关闭" :ywkg = "开启";
					var statusStr = "";
					switch (status) {
				      case 0:
				        statusStr = "待审核";
				        break;
				      case 1:
				        statusStr = "已通过";
				        break;
				      case 2:
				        statusStr = "不通过";
				        break;
				      case 3:
				        statusStr = "被冻结";
				        break;
				      default:
				        statusStr = "";
				      }
					
					
					
					 var rowhtml ="<tr>"+
						"<td>"+no+"</td>"+
						"<td>"+user_id        +"</td>"+
						"<td>"+user_role_type +"</td>"+
						"<td>"+user_role_value+"</td>"+
						"<td>"+name           +"</td>"+
						"<td>"+call_index     +"</td>"+
						"<td>"+channel_id     +"</td>"+
						"<td><textarea>"+content      +"</textarea></td>"+
						"<td>"+valid_time     +"</td>"+
						"<td>"+deposit        +"</td>"+
						"<td>"+poundage       +"</td>"+
						"<td>"+percentage     +"</td>"+
						"<td>"+user_deposit   +"</td>"+
						"<td>"+user_poundage  +"</td>"+
						"<td>"+user_percentage+"</td>"+
						"<td>"+agreement      +"</td>"+
						"<td>"+user_agreement +"</td>"+
						"<td>"+statusStr         +"</td>"+
						"<td>"+add_time       +"</td>"+
						"<td>"+update_time    +"</td>"+
						"<td>"+remark         +"</td>"+
						"<td>"+is_del         +"</td>"+
						"<td>"+ywkg           +"</td>"+
						"<td>"+ywms           +"</td>"+
						"<td>"+ywgz           +"</td>"+

						"<td>"+"<a href=readCooperate?id="+id+">修改</a><a href=delCooperate?id="+id+">删除</a>"+"</td>"+
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