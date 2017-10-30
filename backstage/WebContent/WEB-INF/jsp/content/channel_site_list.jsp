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
								频道所属站点详细信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">频道配置</a><i class="icon-angle-right"></i></li>
							<li><a href="#">频道所属站点详细信息</a></li>
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
									<a  class="btn blue" href="insChannelSite"><i class="icon-pencil"></i>新增频道所属站点详细信息</a>
								</div>
							</div>
							
							<div class="portlet-body">
								<div class=row_fluid>
									<ul>
										<li>站点标题：<input type="text" id="title" class="small m-wrap" ></li>
										<li>网站名称：<input type="text" id="name" class="small m-wrap" ></li>
										<li>公司名称：<input type="text" id="company" class="small m-wrap" ></li>
										<li>联系电话：<input type="text" id="tel" class="small m-wrap" ></li>
										<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
									</ul>	
								</div>
							<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
								
									<table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

										<thead>
											<tr role="row">
												<th>序号</th>
												
												<td>站点标题</td>
												<td>生成目录名</td>
												<td>模板目录名</td>
												<td>绑定域名</td>
												<td>网站名称</td>
												<!-- <td>网站LOGO</td> -->
												<td>公司名称</td>
												<td>通讯地址</td>
												<td>联系电话</td>
												<td>传真号码</td>
												<td>电子邮箱</td>
												<td>备案号</td>
												<td>版权信息</td>
												<td>SEO标题</td>
												<td>SEO关键字</td>
												<td>SEO描述</td>
												<td>是否移动站</td>
												<td>是否默认</td>
												<td>排序数字</td>

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
		    menuact("03_02_04");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	ajaxdatas ="index="+index;
	
	if($("#title").val()!=""){
		ajaxdatas+="&title="+$("#title").val();
	} 
	if($("#name").val()!=""){
		ajaxdatas+="&name="+$("#name").val();
	} 
	if($("#company").val()!=""){
		ajaxdatas+="&company="+$("#company").val();
	} 
	if($("#tel").val()!=""){
		ajaxdatas+="&tel="+$("#tel").val();
	} 

	
	$.ajax({
		type : "GET",
		url : "queryChannelSite",
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
					var	title=item.title;
					var	build_path=item.build_path;
					var	templet_path=item.templet_path;
					var	domain=item.domain;
					var	name=item.name;
					var	logo=item.logo;
					var	company=item.company;
					var	address=item.address;
					var	tel=item.tel;
					var	fax=item.fax;
					var	email=item.email;
					var	crod=item.crod;
					var	copyright=item.copyright;
					var	seo_title=item.seo_title;
					var	seo_keyword=item.seo_keyword;
					var	seo_description=item.seo_description;
					var	is_mobile=item.is_mobile;
					var	is_default=item.is_default;
					var	sort_id=item.sort_id;

					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+build_path+"</td>"+
					 "<td>"+templet_path+"</td>"+
					 "<td>"+domain+"</td>"+
					 "<td>"+name+"</td>"+
					 /* "<td>"+logo+"</td>"+ */
					 "<td>"+company+"</td>"+
					 "<td>"+address+"</td>"+
					 "<td>"+tel+"</td>"+
					 "<td>"+fax+"</td>"+
					 "<td>"+email+"</td>"+
					 "<td>"+crod+"</td>"+
					 "<td>"+copyright+"</td>"+
					 "<td>"+seo_title+"</td>"+
					 "<td>"+seo_keyword+"</td>"+
					 "<td>"+seo_description+"</td>"+
					 "<td>"+is_mobile+"</td>"+
					 "<td>"+is_default+"</td>"+
					 "<td>"+sort_id+"</td>"+

					 
					 "<td>"+"<a href='readChannelSite?id="+id+"'>修改</a><br><a href='delChannelSite?id="+id+"'>删除</a>"+"</td>"+
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