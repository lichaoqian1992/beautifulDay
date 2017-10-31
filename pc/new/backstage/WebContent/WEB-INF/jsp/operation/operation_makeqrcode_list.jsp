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
                    生成二维码<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">二维码管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">生成二维码</a></li>
            </ul>
          </div>
        </div>

        <div class="row-fluid margin-bottom-20">

        </div>

        <div class="row-fluid">

          <div class="span12" >

            <div class="portlet box grey">
             <div class="portlet-title" style="height:15px">
               
              </div>
              
              <div class="portlet-body form-group" style="height:400px">
                           
                <div class="row-fluid">
                  <div class="span5">
									              <div class="row-fluid">
									                <div class="span12">
									                 <div class="form-controller">
									                    <label class="">请输入二维码内容:</label>
									                    <input type="text" id="content" value=""/>
									                    <span style="margin-left:20px;color: #FF0033">默认为:www.manjiwang.com</span>
									                 </div>
									                </div>
									              </div>  
									                <div class="row-fluid">
										                 <div class="span12">
												                 <div class="form-controller">
												                    <label class="label-controller">请输入二维码宽度:</label>
												                    <input type="text" id="width" value=""/>
												                    <span style="margin-left:20px;color: #FF0033">默认为:200</span>
												                 </div>
										                 </div>
									                </div>
									                <div class="row-fluid">
										                 <div class="span12">
												                 <div class="form-controller">
												                    <label class="label-controller">请输入二维码高度:</label>
												                    <input type="text" id="height" value=""/>
												                    <span style="margin-left:20px;color: #FF0033">默认为:200</span>
												                 </div>
										                 </div>
									                </div>
									</div>
									   
                 <div class="span2" style="margin-top:83px"><button class="btn btn-primary" onclick="getCode()">获取二维码</button>
                 </div>
			                 
                 <div class="span3">
                      <div >
                          <img id="image" src=""/>
                      </div>
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
        setactive("162");
    });
  
  function getCode(){
		var content = "http://"+$("#content").val();
	    var width = $("#width").val();
	    var height = $("#height").val();
	    $("#image").attr("src","/backstage/makeQrCode?content="+content+"&width="+width+"&height="+height);
  }
  
  

  </script>

</body>

</html>