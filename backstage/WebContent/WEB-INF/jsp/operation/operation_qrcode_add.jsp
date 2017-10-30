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
                   二维码信息<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">版本管理</a><i class="icon-angle-right"></i></li>
              <li><a href="selQrCode">二维码信息</a></li>
            </ul>
          </div>
        </div>

        <div class="row-fluid margin-bottom-20">

        </div>

        <div class="row-fluid">

          <div class="span12">

            <div class="portlet box blue tabbable">

              <div class="portlet-title">
                <div class="caption">
                  <span class="hidden-480">新增二维码信息</span>
                </div>
              </div>
              <div class="portlet-body form">
              
                <div class="tabbable portlet-tabs">
                  <ul class="nav nav-tabs">
                    <li><a href="#portlet_tab3" data-toggle="tab">&nbsp;</a></li>
                  </ul>
                  
                  <div class="tab-content">
                    <div class="tab-pane active" id="portlet_tab1">
                      <!-- BEGIN FORM-->
                      
                      <form action="#" class="form-horizontal">
                        <input type="hidden" id="hiddenid"value="">
                        <div class="control-group">
                          <label class="control-label">类别：</label>
                          <div class="controls">
                            <select id="type" type="text"  class="m-wrap small">
                          		<option value="1">收款</option>
                          		<option value="2">信息</option>
                          	</select><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">名称：</label>
                          <div class="controls">
                            <input id="name" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">金额：</label>
                          <div class="controls">
                            <input id="amount" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">生成时间：</label>
                          <div class="controls">
                            <input id="addtime" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">失效时间：</label>
                          <div class="controls">
                            <input id="invalidtime" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">状态：</label>
                          <div class="controls">
                            <select id="status" type="text"  class="m-wrap small">
                         		<option value="0">停用</option>
                         		<option value="1">启用</option>
                         		
                         	</select><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">标识：</label>
                          <div class="controls">
                            <input id="content" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">用户ID：</label>
                          <div class="controls">
                            <input id="userid" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">用户角色类型：</label>
                          <div class="controls">
                          	<select id="userroletype" type="text"  class="m-wrap small">
                         		<option value="Buyer">用户</option>
                         		<option value="Shop">商家</option>
                         		
                         	</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">用户角色值：</label>
                          <div class="controls">
                            <input id="userrolevalue" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn"  href="selQrCode">返回</a>

                        </div>
                        

                      </form>

                      <!-- END FORM-->

                    </div>

                    

                    

                  </div>

                </div>

              </div>

            </div>

            <!-- END SAMPLE FORM PORTLET-->

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
        menuact("04_04_02");	
    });
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "addQrCode",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
        type:$("#type").val(),
        name:$("#name").val(),
        amount:$("#amount").val(),
        add_time:$("#addtime").val(),
        invalid_time:$("#invalidtime").val(),
        status:$("#status").val(),
        content:$("#content").val(),
        user_id:$("#userid").val(),
        user_role_type:$("#userroletype").val(),
        user_role_value:$("#userrolevalue").val()
        },
    success : function(data) {
      if(data.status==0){
        location.reload();
      }else{
        alert("修改失败");
      }
    },
    })
  });
  
  

  </script>

</body>

</html>