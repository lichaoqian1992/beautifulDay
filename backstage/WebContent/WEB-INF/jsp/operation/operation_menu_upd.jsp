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
                    菜单信息<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">导航信息</a><i class="icon-angle-right"></i></li>
              <li><a href="selMenu">菜单信息</a></li>
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
                  <span class="hidden-480">修改菜单信息</span>
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
                      
                      <form action="updMenu" class="form-horizontal" method="POST" enctype="multipart/form-data">
                        <input type="hidden" id="hiddenid"value="" name="id">
                        <div class="control-group">
                          <label class="control-label">标题：</label>
                          <div class="controls">
                            <input id="title" type="text" name="title"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">图标：</label>
                          <div class="controls">
                          		<img id="icon" name="icon"  style="height:100px;">
	                          	<input id="upFile" type='file' name='file' style="display: none">
								<input type="hidden" id="imgUrl" name="imgUrl" />
								<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">链接：</label>
                          <div class="controls">
                            <input id="url" type="text" name="url"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">类别：</label>
                          <div class="controls">
                          	<select class="m-wrap small" id="type" name="type">
								<option value=""></option>
								<option value="PC">PC</option>
								<option value="WAP">WAP</option>
								<option value="APP">APP</option>
								<option value="APP_ACT">APP_ACT</option>
								<option value="APP_HOTEL_CATEGORY">APP_HOTEL_CATEGORY</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">排序数字：</label>
                          <div class="controls">
                            <input id="sortid" type="text" name="sort_id"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">显示地区：</label>
                          <div class="controls">
	                           <select class="m-wrap small" id="showarea" name="show_area">
									<option value="0">主站</option>
									<option value="1">省市</option>
									<option value="2">区县</option>
									<option value="3">乡镇</option>
								</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否启用：</label>
                          <div class="controls">
                          	<select class="m-wrap small" id="status" name="status">
								<option value="0">停用</option>
								<option value="1">启用</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点编号：</label>
                          <div class="controls">
                            <input id="siteid" type="text" name="site_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        <div class="form-actions">

                         <button  class="btn blue">
							<i class="icon-ok"></i> 保存
						</button>

                          <a type="button" class="btn"  href="selMenu">返回</a>

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
        menuact("04_01_01");
        infoset();
    });
  
  function infoset(){
    
    var menuinfo=${menuinfo };
    var id=menuinfo.id;
    var title=menuinfo.title;
    var icon=menuinfo.icon;
    var url=menuinfo.url;
    var type=menuinfo.type;
    var sort_id=menuinfo.sort_id;
    var show_area=menuinfo.show_area;
    var status=menuinfo.status;
    var site_id=menuinfo.site_id;

    $("#hiddenid").val(id);
    $("#title").val(title);
    $("#icon").attr('src',icon);
    $("#imgUrl").val(icon);
    $("#url").val(url);
    $("#type").val(type);
    $("#sortid").val(sort_id);
    $("#showarea").val(show_area);
    $("#status").val(status);
    $("#siteid").val(site_id);

  };
  
  
 /*  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "updMenu", 
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
      id:$("#hiddenid").val(),
      title:$("#title").val(),
      icon:$("#icon").val(),
      url:$("#url").val(),
      type:$("#type").val(),
      sort_id:$("#sortid").val(),
      show_area:$("#showarea").val(),
      status:$("#status").val(),
      site_id:$("#siteid").val()
        },
    success : function(data) {
      if(data.status==0){
        location.reload(); 
      }else{
        alert("修改失败");
      }
    }
        
    })
  }); */
  function uploadpicdiv(){
	  $("#imgUrl").val("");
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
  

  </script>

</body>

</html>