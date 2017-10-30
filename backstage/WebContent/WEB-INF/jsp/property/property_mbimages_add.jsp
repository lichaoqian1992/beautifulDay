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
					用户发布所用图片模板		<!-- <small>正常账户列表</small> -->
			</h3>
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
				<li><a href="#">模板配置</a><i class="icon-angle-right"></i></li>
				<li><a href="selMbImages">用户发布所用图片模板</a></li>
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
                  <span class="hidden-480">新增用户发布所用图片模板</span>
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
                      
                      <form action="addMbImages" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
							<div class="control-group">
								<label class="control-label"> 对应分类：</label>
								<div class="controls">
									<input id="category_id" name="category_id" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">关键词：</label>
								<div class="controls">
									<input id="keywords" name="keywords" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">图片地址：</label>
								<div class="controls">
												<img id="url" name="url"  style="height:100px;">
									<input id="upFile" type='file' name='file' style="display: none">
									<input type="hidden" id="imgUrl" name="imgUrl" />
									<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">是否生效：</label>
								<div class="controls">
									<select id="status" name="status" class="m-wrap small">
										<option value="0">未生效</option>
										<option value="1">已生效</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">添加时间：</label>
								<div class="controls">
									<input id="add_time" name="add_time" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">更新时间：</label>
								<div class="controls">
									<input id="update_time" name="update_time" type="text" 	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">更新说明：</label>
								<div class="controls">
									<input id="remark" name="remark" type="text" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
								</div>
							</div>
							
							
							<div class="form-actions">

								 <button  class="btn blue">
									<i class="icon-ok"></i> 保存
								</button>

								<a type="button" class="btn" href="selMbImages">返回</a>

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
        menuact("03_03_03");
    });
  
  function uploadpicdiv(){
		$('#img_url').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

  </script>

</body>

</html>