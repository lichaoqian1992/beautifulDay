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
<title>满集数据后台系统</title>

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

<body>
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">

				<a class="brand" href="index.html"> <img
					src="media/image/logo.png" alt="logo" />
				</a>
				<div class="navbar hor-menu hidden-phone hidden-tablet">
					<div class="navbar-inner">
						<jsp:include page="/WEB-INF/jsp/banner.html" flush="true" />
					</div>
				</div>
				<a href="javascript:;" class="btn-navbar collapsed"
					data-toggle="collapse" data-target=".nav-collapse"> <img
					src="media/image/menu-toggler.png" alt="" />
				</a>
				<jsp:include page="/WEB-INF/jsp/userinfo.jsp" flush="true" />
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
									<h3 class="page-title" style="margin: 20px 0px 15px 0px">
										属性列表
										<!-- <small>正常账户列表</small> -->
									</h3>
									<ul class="breadcrumb">
										<li><i class="icon-home"></i> <a href="*">主页</a><i
											class="icon-angle-right"></i></li>
										<li><a href="#">属性管理</a><i class="icon-angle-right"></i></li>
										<li><a href="#">属性列表</a></li>
									</ul>
								</div>
							</div>
	            
	            <div class="row-fluid margin-bottom-20">

              </div>
	 
	            <div class="row-fluid">
	               <div class="span12">
		                   <div class="portlet box grey">
													<div class="portlet-title">
														<div class="caption"></div>
														    <div class="actions">
															     <a class="btn blue" href="insBrand"><i class="icon-pencil"></i>添加品牌</a>
														    </div>
													</div>
													
													<div class="portlet-body">
													   <div class="span2" style="display:none;">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
														   <div class="row_fluid ">
														   <ul>
																	<li >
																		<select class="form-control m-b" id="brandtype">
																			<option value="-1">不限</option>
																			<option value="0">国内</option>
																			<option value="1">国外</option>
																		</select>
																	</li>
																	<li >
																		品牌名:
																			<input type="text" id="searchstr" class="small m-wrap" >
																			
																		
																	</li>
																	<li>
																	<span class="input-group-btn">
																				<button type="button" class="btn btn-primary"
																					onclick="queryinfo(1)">搜索</button>
																			</span>
																	</li>
																	
												   </ul>	
									          </div>
									        

                          <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
														<table class="table table-bordered table-hover">
															<thead>
																<tr>
																	<th>序号</th>
																	<th>LOGO</th>
																	<th>类别</th>
																	<th>名称</th>
																	<th>更新时间</th>
																	<th>操作</th>
																</tr>
															</thead>
															<tbody id="tablebody">
						
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
    onkey(1);
    queryinfo(1);
    setactive("111");
});
	
	
	
function queryinfo(inx){
	
	
	ajaxdatas ="index="+inx;
	 /* ajaxdatas +="&state="+$("#state").val(); */
	if($("#brandtype").val()!=""){
		ajaxdatas+="&brand_type="+$("#brandtype").val();
	}
	if($("#searchstr").val()!=""){
		ajaxdatas+="&name="+$("#searchstr").val();
	}  
	
	$.ajax({
		type : "GET",
		url : "queryBrand",
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
					var no = Number(i + 1);
					var logo = item.logo;
					var brandtype = item.brand_type;
					var name = item.name;
					var time = item.update_time;
					var id = item.id;
					var brandtypestr ="";
					switch(brandtype){
					case 0:
						brandtypestr="国内";
						break;
					case 1:
						brandtypestr="国外";
						break;
					}
					
					rowhtml ="<tr><td>"
						+ no
						+ "</td><td>"
						+ "<img src='"+logo +"' style='height:30px;'>"
						+ "</td><td>"
						+ brandtypestr
						+ "</td><td>"
						+ name
						+ "</td><td>"
						+ time
						+ "</td><td>"
						+ "<a href='readBrand?id="
						+ id
						+ "'>查看</a>&nbsp;&nbsp;<a onclick='delBrand("
						+ id
						+ ")'>删除</a>"
						+ "</td></tr>";
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
