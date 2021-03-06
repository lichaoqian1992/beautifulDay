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
										用户收藏信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户详细信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selUserFavorites">用户收藏信息</a></li>
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
                  <span class="hidden-480">修改用户收藏信息</span>
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
                      
                      <form action="updUserFavorites" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
                        <input type="hidden" id="hiddenid"value="" name="id">
                        <div class="control-group">
                          <label class="control-label">收藏类别：</label>
                          <div class="controls">
                            <select id="type" name="type"> 
                              <option value="0">店铺</option>
                              <option value="1">商品</option>
                              <option value="2">其它</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">收藏类别值：</label>
                          <div class="controls">
                            <input id="value" type="text" name="value"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">标题：</label>
                          <div class="controls">
                            <input id="title" type="text" name="title"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">图片：</label>
                          <div class="controls">
							<img id="pics" name="pics"  style="height:100px;">
							<input id="upFile" type='file' name='file' style="display: none">
							<input type="hidden"  name="icon" id="icon"/>
							<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
							<!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">链接：</label>
                          <div class="controls">
                            <input id="url" type="text"  name="url"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">收藏者：</label>
                          <div class="controls">
                            <input id="user_id" type="text"  name="user_id"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">关键字：</label>
                          <div class="controls">
                            <input id="TAG" type="text" name="TAG"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">添加时间：</label>
                          <div class="controls">
                            <input id="add_times" type="text" name="add_times"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否删除：</label>
                          <div class="controls">
                            <select id="is_del" name="is_del"> 
                              <option value="1">删除</option>
                              <option value="0">正常</option>
                            </select>
                          </div>
                        </div>
                        
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selUserFavorites">返回</a>

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
        
        infoset();
        menuact("01_02_02");
    });
	function uploadpicdiv(){
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
  function infoset(){
    
    var ufinfo=${ufinfo };
    console.log(ufinfo);
    
    var id=ufinfo.id;
    var type=ufinfo.type;
    var value=ufinfo.value;
    var title=ufinfo.title;
    var pics=ufinfo.pics;
    var url=ufinfo.url;
    var user_id=ufinfo.user_id;
    var TAG=ufinfo.TAG;
    var add_times=ufinfo.add_times;
    var is_del=ufinfo.is_del;

    $("#hiddenid").val(id);
    $("#type").val(type);
    $("#value").val(value);
    $("#title").val(title);
    $("#pics").attr('src',pics);
    $("#url").val(url);
    $("#user_id").val(user_id);
    $("#TAG").val(TAG);
    $("#add_times").val(add_times);
    $("#is_del").val(is_del);
    $("#icon").val(pics);
  };
  
  
  $("#subbutton").on("click",function(){
	  $("form").submit();
  });
  
  

  </script>

</body>

</html>