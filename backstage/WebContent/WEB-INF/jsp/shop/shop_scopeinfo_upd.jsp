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
										用户商家经营范围
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家详细信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selScopeInfo">用户商家经营范围</a></li>
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
                  <span class="hidden-480">修改用户商家经营范围</span>
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
                          <label class="control-label">用户ID：</label>
                          <div class="controls">
                            <input id="user_id" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">角色类型：</label>
                          <div class="controls">
                            <input id="user_role_type" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">用户角色值：</label>
                          <div class="controls">
                            <input id="user_role_value" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">商家经营范围：</label>
                          <div class="controls">
                            <input id="scope_values" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">状态：</label>
                          <div class="controls">
                            <select   id="status">      
							  <option value="0">待审核</option>
							  <option value="1">已通过</option> 
						  	</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">所属地区：</label>
                          <div class="controls">
                            <input id="local_area" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selScopeInfo">返回</a>

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
        onkey(3);
        infoset();
        menuact("02_01_06");
    });
  
  function infoset(){
    
    var scopeinfo=${scopeinfo };
    
    var id=scopeinfo.id;
    var	user_id=scopeinfo.user_id;
    var	user_role_type=scopeinfo.user_role_type;
    var	user_role_value=scopeinfo.user_role_value;
    var	scope_values=scopeinfo.scope_values;
    var	status=scopeinfo.status;
    var	local_area=scopeinfo.local_area;

 
    
    $("#hiddenid").val(id);
    $("#user_id").val(user_id);	
    $("#user_role_type").val(user_role_type);
    $("#user_role_value").val(user_role_value);
    $("#scope_values").val(scope_values);
    $("#status").val(status);
    $("#local_area").val(local_area);

  };
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "updScopeInfo",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
      id:$("#hiddenid").val(),
      user_id:$("#user_id").val(),
      user_role_type:$("#user_role_type").val(),
      user_role_value:$("#user_role_value").val(),
      scope_values:$("#scope_values").val(),
      status:$("#status").val(),
      local_area:$("#local_area").val()
        },
    success : function(data) {
      if(data.status==0){
        location.reload();
      }else{
        alert("修改失败");
      }
    }
        
    })
  });
  
  

  </script>

</body>

</html>