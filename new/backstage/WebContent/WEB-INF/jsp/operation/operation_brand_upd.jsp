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

  <link href="media/css/jqpagination.css" rel="stylesheet" type="text/css"/>
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
            <jsp:include page="/WEB-INF/jsp/banner.jsp" flush="true" />
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
  
    <div class="page-container row-fluid">
			<div class="page-sidebar nav-collapse collapse">
				<jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true" />
			</div>
      <div class="page-content">
	        <div class="container-fluid">
	            <div class="row-fluid">
				          <div class="span12">
				            <h3 class="page-title" style="margin: 20px 0px 15px 0px">
				                                                                                        品牌库
				              <!-- <small>正常账户列表</small> -->
				            </h3>
				            <ul class="breadcrumb">
				              <li><i class="icon-home"></i> <a href="toMain">主页</a><i
				                class="icon-angle-right"></i></li>
				              <li><a href="#">品牌管理</a><i class="icon-angle-right"></i></li>
				              <li><a href="selBrand"> 品牌库</a></li>
				            </ul>
				          </div>
			         </div>
			         
			         <div class="row-fluid margin-bottom-20">

               </div>
               <div class="span12">
                  <div class="portlet box grey">
										<div class="portlet-title">
											<div class="caption"></div>
										</div>
										<div class="portlet-body">
											<form class="form-horizontal" action="updBrand" method="POST"
													enctype="multipart/form-data"> 
													<input type="hidden" name="id" id="id"> <input
														type="hidden" name="logo" id="logo">
													<div class="form-group">
													  <div class="row-fluid">
															<label class="span2 control-label">品牌名称：</label>
															<div class="span6">
																<input type="text" id="name" name="name" class="form-control">
															</div>
													  </div>
													</div>
													<div class="form-group">
													   <div class="row-fluid">
														  <label class="span2 control-label">品牌LOGO：</label>
															<div class="span6">
																<div id="brandlogo">
																	<input type='file' name='file' style="display: none">
															<a onclick="uploadpicdiv()">重新上传</a>
																</div>
													   </div>
					
														</div>
													</div>
													<div class="form-group">
													   <div class="row-fluid">
														    <label class="span2 control-label">类别设置：</label>
																<div class="span6">
																	<div id="radio">
																		  <input type="radio"	value="0" name="brand_type" />国 内&nbsp;&nbsp;&nbsp;
																		  <input type="radio" value="1" name="brand_type" />国 外
																	</div>
															 </div>
														</div>
													</div>
													<div class="form-group">
													   <div class="row-fluid">
																<label class="span2 control-label">品牌介绍：</label>
																<div class="span6">
																	<textarea class="form-control" id="introduction"
																		name="introduction"></textarea>
																</div>
													   </div>
													</div>
													<br>
													<div class="form-group">
														<div class="col-lg-offset-2 col-lg-10" style="margin-left:17%">
															<button class="btn btn-w-m btn-success" >保存</button>
															<a class="btn btn-w-m btn-default" 
																href="selBrand">返 回</a>
														</div>
													</div>
											</form> 
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
			/* onkey(1); */
			console.log("2222222222");
			menuact("03_05_01");
			var brand =${branditem };
			console.log(brand);
			var id=brand.id;
			var name =brand.name;
			var logo =brand.logo;
			var type =brand.brand_type;
			var intro =brand.introduction;
			
			$("#id").val(id);
			$("#logo").val(logo);
			$("#name").val(name);
			$("#brandlogo").prepend("<img src='"+logo+" ' width='180' height='80'/>");
			
			
			if(0==type){
				$("input[name='brand_type'][value=0]").attr("checked",true); 
			}else if(1==type){
				$("input[name='brand_type'][value=1]").attr("checked",true); 
			}
			
			$("#introduction").html(intro);
			
			
			
		});
		

		
		/* function onkey(key){
            var li=key+" ul li"; 

            $('#side-menu li').css("display","none");
            $('.abc').css("display","block");
            $('.k'+key).css("display","block");
            $('.k'+li).css("display","block"); 
        } */
    
		function uploadpicdiv(){
			
			$("#brandlogo").empty();
			$("#brandlogo").html("<input type='file' name='file' required='required'>");
			
			
		};
		
	
	</script>







</body>

</html>
