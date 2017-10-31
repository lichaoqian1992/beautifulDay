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
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
				<img src="media/image/menu-toggler.png" alt="" />
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
                    属性列表<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
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
                <div class="caption"> </div>
                <div class="actions">
                  <a  class="btn blue" href="insAttri"><i class="icon-pencil"></i>添加属性</a>
                </div>
              </div>
               <div class="portlet-body">
               <div class="span2" style="display:none;">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
              
              
              <div class="row-fluid ">  
                <%-- <input type="hidden" id="state" value="${accountstate } "> --%>
<!--                 <div class="span2">用户ID：<input type="text" id="useridstr"class="small m-wrap" ></div>
 -->                
                <div class="span2">
                <input type="hidden" id="categoryid">
                    <select id="firstcate" class="small m-wrap" >
                    </select>
                </div>
                <div class="span2">
                    <select id="secondcate" class="small m-wrap" >
                    </select>
                </div>
                <div class="span2">
                    <select id="thirdcate" class="small m-wrap" >
                    </select>
                </div>
               <div class="span2">
                    <div class="input-group">
                       <span
                        class="input-group-btn">
                        <button type="button" class="btn btn-primary" id="querybtn">搜索</button>
                      </span>
                    </div>
              </div>
               
                
              </div>
                 
              <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                  <table id="attritable" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>序号</th>
                        <th>一级类别</th>
                        <th>二级类别</th>
                        <th>三级类别</th>
                        <th>属性值</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody id="tablebody">
                      
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
	

		$(document).ready(function() { 
			App.init();
			onkey(1);
			firstcate();
			setactive("131");
			

		
		});
		
	 
		$('#spec_add').on('click', function() {
			$.layer({
				type : 1,
				shade : [ 0 ],//遮罩透明度                    
				area : [ 'auto', 'auto' ],
				offset : [ '100px', '' ],
				title : false,
				border : [ 0 ],
				page : {
					dom : '.spec_add'
				}
			});
		});
		// $('#spec_update').on('click', function(){
		//    $.layer({
		//         type: 1,                    
		//         shade: [0],
		//         area: ['auto', 'auto'],
		//         offset:['100px', ''],
		//         title: false,
		//         border: [0],                    
		//         page: {dom : '.spec_update'}
		//     });
		// });
		$(".spec_update_a").click(function() {
			$(".spec_update").css("display", "block");
		});
		$(".close_update").click(function() {
			$(".spec_update").css("display", "none");
		});
	 
	 
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
		
		
		
		$("#querybtn").click(function(){
			$("#tablebody").empty();
			
			 /* $("#attritable tr:gt(0)").remove(); */
			$.ajax({
				type : "GET",
				url : "queryAttri",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {category_id:$("#categoryid").val()},
				success : function(data) {
					var attrilist =data.result;
					
					var tablecont ="";
					$.each(attrilist,function(i, attriitem) {
						
						var no =Number(i+1);
						var id =attriitem.attri_id;
						var fname =attriitem.first_name;
						var sname =attriitem.second_name;
						var tname =attriitem.third_name;
						var values =attriitem.attri_str;
						
						
						var newRow = "<tr>"+
						"<td>"+no+"</td>"+
						"<td>"+fname+"</td>"+
						"<td>"+sname+"</td>"+
						"<td>"+tname+"</td>"+
						"<td>"+values+"</td>"+
						"<td>"+"<a href='readAttri?attri_id="+id+"'>查看</a>&nbsp;</td>"+
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
