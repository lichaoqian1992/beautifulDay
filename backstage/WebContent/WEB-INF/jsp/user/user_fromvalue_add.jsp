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
								用户标识申请信息
		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户登录信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selUserFromValue">用户标识申请信息
							</a></li>
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
                  <span class="hidden-480">新增用户标识申请信息</span>
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
                      
                      <form action="addUserFromValue" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
												<input type="hidden" id="hiddenid"value="">
												<div class="control-group">
													<label class="control-label">用户ID：</label>
													<div class="controls">
														<input id="user_id" type="text"	name="user_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色：</label>
													<div class="controls">
														<input id="user_role_type" type="text" name="user_role_type"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">用户角色值：</label>
													<div class="controls">
														<input id="user_role_value" type="text" name="user_role_value"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">申请标识：</label>
													<div class="controls">
														<input id="from_value" type="text" name="from_value"	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">标识对应公众账号：</label>
													<div class="controls">
														<input id="wechat_id" type="text" name="wechat_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">标识二维码图片：</label>
													<div class="controls">
														<img id="img_url" name="img_url"  style="height:100px;">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" name="icon" id="icon"/>
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
														<!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">标识类型：</label>
													<div class="controls">
														<select id="from_type" name="from_type">
														  <option value="0">临时</option>
														  <option value="1">永久</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">标识有效期：</label>
													<div class="controls">
														<input id="yx_time" type="text" name="yx_time" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">申请时间：</label>
													<div class="controls">
														<input id="add_time" type="text" name="add_time" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">是否有效：</label>
													<div class="controls">
														<select id="status" name="status" > 
														  <option value="0">无效</option>
														  <option value="1">有效</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否删除：</label>
													<div class="controls">
														<select id="is_del" name="is_del">
														  <option value="0">未删除</option>
														  <option value="1">已删除</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">来源关注总量：</label>
													<div class="controls">
														<input id="subscribe_count" type="text" name="subscribe_count"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">来源注册总量：</label>
													<div class="controls">
														<input id="reg_count" type="text" name="reg_count"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												<div class="form-actions">

													<a type="submit" class="btn blue" id="subbutton">
														<i class="icon-ok"></i> 保存
													</a>

													<a type="button" class="btn" href="selUserFromValue">返回</a>

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
        menuact("01_01_03");
    });
	function uploadpicdiv(){
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
  
 /*$("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "addUserFromValue",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
    	user_id:$("#user_id").val(),
		user_role_type:$("#user_role_type").val(),
		user_role_value:$("#user_role_value").val(),
		from_value:$("#from_value").val(),
		wechat_id:$("#wechat_id").val(),
		img_url:$("#img_url").val(),
		from_type:$("#from_type").val(),
		yx_time:$("#yx_time").val(),
		add_time:$("#add_time").val(),
		status:$("#status").val(),
		is_del:$("#is_del").val(),
		subscribe_count:$("#subscribe_count").val(),
		reg_count:$("#reg_count").val()
        },
    success : function(data) {
      if(data.status==0){
        location.reload();
      }else{
        alert("修改失败");
      }
    },
    })
  });*/
  $("#subbutton").on("click",function(){
	$("form").submit();
  });
  
  

  </script>

</body>

</html>