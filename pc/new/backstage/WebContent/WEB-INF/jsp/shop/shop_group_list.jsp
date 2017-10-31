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
										商家分组信息<!-- <small>代理商列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">角色分组管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家分组信息</a></li>
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

									<a href="insShopGroup" class="btn blue"><i class="icon-pencil"></i>新增商家分组信息</a>

								</div>

							</div>
							
							<div class="portlet-body">
							
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												<th>组别名称</th>
												<th>组别等级值</th>
												<th>升级经验值</th>
												<th>默认预存款</th>
												<th>默认积分</th>
												<th>购物折扣</th>
												<th>是否默认组</th>
												<th>是否自动升级</th>
												<th>是否禁用</th>
												<th>操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">
										</tbody>
										</table>
									

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

$(document).ready(
		
			function() {
				App.init();
				menuact("05_03_02");
			 	var data = ${shopgroups};

				$.each(data,function(i, branditem) {
									var id = branditem.id;
									var no = Number(i + 1);
									var title = branditem.title;
									var grade = branditem.grade;
									var upgrade_exp = branditem.upgrade_exp;
									var amount = branditem.amount;
									var point = branditem.point;
									var discount = branditem.discount;
									var is_default = branditem.is_default;
									var is_upgrade = branditem.is_upgrade;
									var is_lock = branditem.is_lock;
									is_default == 0 ? is_default = "否" : is_default = "是";
									is_upgrade == 0 ? is_default = "否" : is_default = "是";
									is_lock == 0 ? is_lock = "正常" : is_lock = "禁用";
									
		
									$("#tablebody").append(
													"<tr><td>"
															+ no
															+ "</td><td>"
															+ title
															+ "</td><td>"
															+ grade
															+ "</td><td>"
															+ upgrade_exp
															+ "</td><td>"
															+ amount
															+ "</td><td>"
															+ point
															+ "</td><td>"
															+ discount
															+ "</td><td>"
															+ is_default
															+ "</td><td>"
															+ is_upgrade
															+ "</td><td>"
															+ is_lock
															+"<td>"+"<a href='chgShopGroup?id="+id+"'>修改</a>&nbsp;<a href='delShopGroup?id="+id+"'>删除</a>"+"</td>"+ 
															+ "</td></tr>");

								});

			});
	
	
	
	
	
	function delShopGroup(id){
		
		$.ajax({
			
			type : "GET",
			url : "delShopGroup",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {"id":id},
			success : function(freshdata) {
				if(freshdata.status){
					window.location.reload();
				}else{
					alert("删除失败");
				}
			
				
			},
			
			
			
		});
		
		
		
	}
	
	
	function queryinfo(inx){
		
		var index =1;
		ajaxdatas ="index="+index;
		ajaxdatas +="&state="+$("#state").val();
		if($("#useridstr").val()!=""){
			ajaxdatas+="&user_id="+$("#useridstr").val();
		}
		if($("#roletypestr").val()!=""){
			ajaxdatas+="&role_type="+$("#roletypestr").val();
		}
		
		$.ajax({
			type : "GET",
			url : "queryAccount",
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
						var user_id =item.user_id;
						var role_type =item.role_type;
						var role_value =item.role_value;
						var amount =item.amount;
						var point =item.point;
						var reputation =item.reputation;
						var credit =item.credit;
						var voucher =item.voucher;
						var state =item.state;
						var statestr ="";
						switch(state){
						case 0:
							statestr="冻结";
							break;
						case 1:
							statestr="正常";
							break;
						case 9:
							statestr="异常";
							break;
						}
						rowhtml ="<tr>"+
								 "<td>"+no+"</td>"+
								 "<td>"+user_id+"</td>"+
								 "<td>"+role_type+"</td>"+
								 "<td>"+role_value+"</td>"+
								 "<td>"+amount+"</td>"+
								 "<td>"+point+"</td>"+
								 "<td>"+reputation+"</td>"+
								 "<td>"+credit+"</td>"+
								 "<td>"+voucher+"</td>"+
								 "<td>"+statestr+"</td>"+
								 "<td>"+"<a href='readAccount?id="+id+"'>查看</a>"+"</td>"+
								 "</tr>";
						tablehtml+=rowhtml;
					})
					$("#tablebody").html(tablehtml);
				}else{
				}
				
			}
		
		});
		
	}
	
	
	function queryshop(){
		
		
		$.ajax({
			
			type : "GET",
			url : "queryShopById",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {id: $("#useridstr").val()  },
			success : function(data) {
				
				if(data.status==0){
				
				var listdate =data.result;
				var tablehtml="";
				$.each(listdate,function(i,item){
					var no = Number(i + 1);
					var title =item.title;
					var grade =item.grade;
					var upgrade_exp =item.upgrade_exp;
					var amount =item.amount;
					var point =item.point;
					var discount =item.discount;
					var is_default =item.is_default;
					var is_upgrade =item.is_upgrade;
					var is_lock =item.is_lock;
					var id =item.id;
					
					var row ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+grade+"</td>"+
					 "<td>"+upgrade_exp+"</td>"+
					 "<td>"+amount+"</td>"+
					 "<td>"+point+"</td>"+
					 "<td>"+discount+"</td>"+
					 "<td>"+is_default+"</td>"+
					 "<td>"+is_upgrade+"</td>"+
					 "<td>"+is_lock+"</td>"+
					 "<td>"+"<a href='chgShopGroup?id="+id+"'>修改</a><a href='delShopGroup?id="+id+"'>删除</a>"+"</td>"+ 

					 "</tr>";
					
						tablehtml+=row;
						
				});
				$("#tablebody").html(tablehtml);
				
				
				}else{
					alert("暂无数据！");
				}
},
});





}
	
	
	</script>

</body>

</html>