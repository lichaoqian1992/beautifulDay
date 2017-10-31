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

<body class="page-header-fixed">
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
            <h3 class="page-title" style="margin:20px 0px 15px 0px">
                             规格列表<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">规格管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">规格列表</a></li>
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
                  <a  class="btn blue" href="insSpec"><i class="icon-pencil"></i>添加规格</a>
                </div>
              </div>
              <div class="portlet-body">
                 <div class="span2" style="display:none;">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
              
                <div class="row-fluid ">
									<div class="span2">
									<input type="hidden" id="categoryid">
										<select class="small m-wrap" id="firstcate">
										</select>
									</div>
									<div class="span2">
										<select class="small m-wrap" id="secondcate">
										</select>
									</div>
									<div class="span2">
										<select class="small m-wrap" id="thirdcate">
										</select>
									</div>
									<div class="span2">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-primary" id="querySpec">搜索</button>
											</span>
										</div>
									</div>
								</div>
                
                <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>一级类别</th>
												<th>二级类别</th>
												<th>三级类别</th>
												<th>规格</th>
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
		$(document).ready(function(){
			App.init(); 
			onkey(1);
			firstcate();
			setactive("121");
			
		});
		
/* 	  function onkey(key){
             var li=key+" ul li"; 

             $('#side-menu li').css("display","none");
             $('.abc').css("display","block");
             $('.k'+key).css("display","block");
             $('.k'+li).css("display","block"); 
         } */
      
		
		function firstcate(){
			$("#firstcate").prepend("<option value='0'>全部一级分类</option>"); 
			$("#secondcate").prepend("<option value='0'>全部二级分类</option>"); 
			$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
			$.ajax({
				type : "GET",
				url : "firstCategory",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : [],
				success : function(data) {
					var catelist =data.result;
					$.each(catelist,function(i, cateitem) {
						var id =cateitem.id;
						var title =cateitem.title;
						$("#firstcate").append("<option value='"+id+"'>"+title+"</option>"); 
					});
					
				}});
		};
		
		$("#firstcate").change(function(){
			$("#secondcate").empty();
			$("#secondcate").prepend("<option value='0'>全部二级分类</option>"); 
			$("#thirdcate").empty();
			$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
			var fcateid =$("#firstcate").val();
			$("#categoryid").val(fcateid);
			
			$.ajax({
				type : "GET",
				url : "secondCategory",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {id:$("#categoryid").val()},
				success : function(data) {
					var catelist =data.result;
					$.each(catelist,function(i, cateitem) {
						var id =cateitem.id;
						var title =cateitem.title;
						$("#secondcate").append("<option value='"+id+"'>"+title+"</option>"); 
					});
					/* $("#secondcate").prepend("<option value='0'>全部二级分类</option>");  */
				}});
			
		});
		
		$("#secondcate").change(function(){
			$("#thirdcate").empty();
			$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
			var scateid =$("#secondcate").val();
			$("#categoryid").val(scateid);
			
			$.ajax({
				type : "GET",
				url : "secondCategory",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {id:$("#categoryid").val()},
				success : function(data) {
					var catelist =data.result;
					$.each(catelist,function(i, cateitem) {
						var id =cateitem.id;
						var title =cateitem.title;
						$("#thirdcate").append("<option value='"+id+"'>"+title+"</option>"); 
					});
					/* $("#secondcate").prepend("<option value='0'>全部二级分类</option>");  */
				}});
			
		});
		
		$("#thirdcate").change(function(){
			
			var tcateid =$("#thirdcate").val();
			$("#categoryid").val(tcateid);
			
		});
		
		
		$("#querySpec").click("on",function(){
			
			
			
			
			$("#tablebody").empty();
			
			 /* $("#attritable tr:gt(0)").remove(); */
			$.ajax({
				type : "GET",
				url : "querySpec",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {category_id:$("#categoryid").val()},
				success : function(data) {
					console.log(data);
					var speclist =data.result;
					
					var tablecont ="";
					$.each(speclist,function(i, specitem) {
						
						var no =Number(i+1);
						var id =specitem.thirdId;
						var fname =specitem.firstName;
						var sname =specitem.secondName;
						var tname =specitem.thirdName;
						var values =specitem.specStr;
						
						
						var newRow = "<tr>"+
						"<td>"+no+"</td>"+
						"<td>"+fname+"</td>"+
						"<td>"+sname+"</td>"+
						"<td>"+tname+"</td>"+
						"<td>"+values+"</td>"+
						"<td>"+"<a href='readSpec?category_id="+id+"'>查看</a>&nbsp;</td>"+
						"</tr>";
						/* $("#attritable tr:last").after(newRow); */
						tablecont +=newRow;
						
						
					});
					$("#tablebody").html(tablecont);
				}});
			
			
			
			
		});
	</script>


	




</body>

</html>
