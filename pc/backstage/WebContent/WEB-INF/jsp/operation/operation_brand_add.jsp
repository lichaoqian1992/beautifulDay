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
<style>
#oper_name{
  display:none;
}
</style>
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
                                            添加品牌
              <!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">品牌管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">添加品牌</a></li>
            </ul>
          </div>
        </div>
       
        <div class="row-fluid margin-bottom-20">

        </div>
        <div class="row-fluid">

        
            <form class="form-horizontal" action="addBrand" method="POST" enctype="multipart/form-data">
              <div class="form-group">
                 <div class="row-fluid">
                    <label class="span2 control-label">品牌名称：</label>
                    <div class="span4">
                      <input type="text" id="insName" placeholder="请输入品牌名称" name="name" class="form-control oper_name">
                <span id="oper_name" >
                                                       
                </span>
                    </div>
                 </div>
              </div><br>
              <div class="form-group">
                 <div class="row-fluid">
                    <label class="span2 control-label">品牌LOGO：</label>
                    <div class="span6">
                      <input type="file" name="file" required="required"  >
                    </div>
                 </div>
              </div><br>
              <div class="form-group">
                 <div class="row-fluid">
                    <label class="span2 control-label">类别设置：</label>
                    <div class="span6">
                      <div class="">
                        <input type="radio" checked="checked"
                          value="0" name="brand_type">国 内 &nbsp;&nbsp;&nbsp;<input
                          type="radio" value="1" name="brand_type">国 外
                      </div>
                    </div>
                 </div>
              </div><br>
              <div class="form-group">
                 <div class="row-fluid">
                    <label class="span2 control-label">品牌介绍：</label>
                    <div class="span6">
                      <textarea class="form-control" name="introduction" row="5"></textarea>
                    </div>
                 </div>
              </div><br>
              <div class="form-group">
                <div class="row_fluid">
		                <div class="span2" style="margin-left:17%">
		                  <button class="btn btn-w-m btn-success" type="submit">保 存</button>
		                  <button class="btn btn-w-m btn-default" type="submit"
		                    onclick="javascript:history.back();">返 回</button>
                    </div>
                </div>
              </div>
            </form>
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
      	setactive("112");
      $(".oper_name").blur(function(){
          //$("#oper_name").css("display","block");
        $.ajax({
          type : "GET",
          url : "checkBrandName",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {brandname:$(".oper_name").val()},
          success : function(data) {
            var setStatus =data.status;
            if(setStatus==1){
              /* $("#oper_name").css("display","block"); */
              $("#oper_name").show();
              $("#oper_name").css("color","#f00");
              $("#oper_name").text("品牌名称重复！");
            }else{
            	$("#oper_name").show();
              if($("#insName").val()!=""){
            	  $("#oper_name").css("color","#093");
                $("#oper_name").text("品牌名称可用！");
              }else{
            	  $("#oper_name").css("color","#f00");
                $("#oper_name").text("请输入品牌名称！");
              }
              
              
            }
            /* $("#secondcate").prepend("<option value='0'>全部二级分类</option>");  */
          }});
        });
    });
     
    
  </script>


  




</body>

</html>
