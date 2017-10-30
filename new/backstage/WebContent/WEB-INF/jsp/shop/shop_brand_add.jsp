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
								商家品牌		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家业务信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selShopBrand">商家品牌</a></li>
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
                  <span class="hidden-480">新增商家品牌</span>
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
                      
							<form action="addShopBrand" class="form-horizontal" method="POST"
									enctype="multipart/form-data">
								<input type="hidden" id="hiddenid"value="">
								<div class="control-group">
									<label class="control-label">商品ID：</label>
									<div class="controls">
										<input id="shop_id" type="text" name="shop_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">品牌名称：</label>
									<div class="controls">
										<input id="name" type="text" name="name"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">商标注册号：</label>
									<div class="controls">
										<input id="register_no" type="text" name="register_no"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">品牌类型：</label>
									<div class="controls">
										<input id="brand_type" type="text" name="brand_type"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">品牌logo：</label>
									<div class="controls">
										<img id="logo" name="logo"  style="height:100px;">
										<input id="upFile" type='file' name='file' style="display: none">
										<input type="hidden"  name="icon" id="icon"/>
										<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">品牌归属人：</label>
									<div class="controls">
										<input id="ascription_person" type="text" name="ascription_person"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">品牌授权书：</label>
									<div class="controls">
										<img id="authorize_pics" name="authorize_pics"  style="height:100px;">
										<input id="upFile1" type='file' name='file1' style="display: none">
										<input type="hidden"  name="icon1" id="icon1"/>
										<a id="upAgain1" onclick="uploadpicdivTow()" style="margin-left:5%">重新上传</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">授权到期时间：</label>
									<div class="controls">
										<input id="authorize_time" type="text" name="authorize_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">系统品牌Id：</label>
									<div class="controls">
										<input id="sys_brandId" type="text" name="sys_brandId"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">申请类型：</label>
									<div class="controls">
										<select id="apply_type" name="apply_type">
										  <option value="0">以后品牌授权</option>
						  				  <option value="1">新品牌申请</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">状态：</label>
									<div class="controls">
										<select id="state" name="state">
										  <option value="0">申请中</option>
										  <option value="1">审核通过</option>
										  <option value="2">不通过</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">是否删除：</label>
									<div class="controls">
										<select id="is_del" name="is_del">
										  <option value="0">默认</option>
										  <option value="1">删除</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">添加时间：</label>
									<div class="controls">
										<input id="add_time" type="text" name="add_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">更新时间：</label>
									<div class="controls">
										<input id="update_time" type="text" name="update_time"	class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">备注：</label>
									<div class="controls">
										<textarea id="remark" type="text" name="remark"	class="m-wrap large" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
									</div>
								</div>
								
								<div class="form-actions">

									<a type="submit" class="btn blue" id="subbutton">
										<i class="icon-ok"></i> 保存
									</a>

									<a type="button" class="btn" href="selShopBrand">返回</a>

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
        menuact("02_02_01");
    });
	
	function uploadpicdiv(){
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
	function uploadpicdivTow(){
		$('#icon1').hide();
		$('#upFile1').show();
		$('#upAgain1').hide();
	}
  
  $("#subbutton").on("click",function(){
	  $("form").submit();
  });
  
  

  </script>

</body>

</html>