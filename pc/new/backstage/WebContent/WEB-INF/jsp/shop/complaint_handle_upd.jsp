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
									投诉处理记录信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">投诉信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selCompHandle">投诉处理记录信息</a></li>
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
                  <span class="hidden-480">修改投诉处理记录信息</span>
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
                          <label class="control-label">对应投诉ID：</label>
                          <div class="controls">
                            <input id="complaint_id" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->

                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">处理人ID：</label>
                          <div class="controls">
                            <input id="user_id" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">角色类型：</label>
                          <div class="controls">
                            <input id="role_type" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">角色对应值：</label>
                          <div class="controls">
                            <input id="role_value" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">更新时间：</label>
                          <div class="controls">
                            <input id="update_time" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">处理进度内容：</label>
                          <div class="controls">
                            <input id="remark" type="text"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">用户是否满意：</label>
                          <div class="controls">
                            <select id="is_satisfaction">
								<option value="0">不满意</option>
								<option value="1">满意</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">服务处理状态：</label>
                          <div class="controls">
                            <select id="service_type">
								<option value="0">和解</option>
								<option value="1">自己赔付</option>
								<option value="2">公司代赔</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">服务赔偿金额：</label>
                          <div class="controls">
                            <input id="service_value" type="text"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">服务赔偿积分：</label>
                          <div class="controls">
                            <input id="service_point" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selCompHandle">返回</a>

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
        menuact("06_01_03");
        infoset();
       
    });
  
  function infoset(){
    
    var comphandle=${comphandle };
    var id=comphandle.id;
    var complaint_id=comphandle.complaint_id;
    var user_id=comphandle.user_id;
    var role_type=comphandle.role_type;
    var role_value=comphandle.role_value;
    var update_time=comphandle.update_time;
    var remark=comphandle.remark;
    var is_satisfaction=comphandle.is_satisfaction;
    var service_type=comphandle.service_type;
    var service_value=comphandle.service_value;
    var service_point=comphandle.service_point;
   

    
    $("#hiddenid").val(id);
    $("#complaint_id").val(complaint_id);
    $("#user_id").val(user_id);
    $("#role_type").val(role_type);
    $("#role_value").val(role_value);
    $("#update_time").val(update_time);
    $("#remark").val(remark);
    $("#is_satisfaction").val(is_satisfaction);
    $("#service_type").val(service_type);
    $("#service_value").val(service_value);
    $("#service_point").val(service_point);

  };
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "updCompHandle",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
      id:$("#hiddenid").val(),
      complaint_id:$("#complaint_id").val(),  
      user_id:$("#user_id").val(),  
      role_type:$("#role_type").val(),  
      role_value:$("#role_value").val(),  
      update_time:$("#update_time").val(),  
      remark:$("#remark").val(),  
      is_satisfaction:$("#is_satisfaction").val(),  
      service_type:$("#service_type").val(),  
      service_value:$("#service_value").val(),  
      service_point:$("#service_point").val()
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