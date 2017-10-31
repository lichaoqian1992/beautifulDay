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
										用户 个人信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户详细信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selPersonInfo">用户个人信息</a></li>
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
                  <span class="hidden-480">修改用户个人信息</span>
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
                      
                      <form action="updPersonInfo" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
                        <input type="hidden" id="hiddenid"value="" name="id">
                        <div class="control-group">
                          <label class="control-label">用户ID：</label>
                          <div class="controls">
                            <input id="user_id" type="text" name="user_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">真实姓名：</label>
                          <div class="controls">
                            <input id="person_name" type="text" name="person_name"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">证件类型：</label>
                          <div class="controls">
                            <select id="card_type" name="card_type">
                              <option>身份证</option>
                              <option>驾照</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">证件照片：</label>
                          <div class="controls">
                          	  <div id="card_pics"></div>
							  <input id="upFile" type='file' name='file' style="display: none" multiple>
							  <input type="hidden"  name="icon" id="icon"/>
							  <a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">户籍地：</label>
                          <div class="controls">
                            <input id="person_area" type="text" name="person_area"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">民族：</label>
                          <div class="controls">
                            <input id="person_nation" type="text" name="person_nation"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">出生年月日：</label>
                          <div class="controls">
                            <input id="person_brithday" type="text" name="person_brithday"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">所属地区：</label>
                          <div class="controls">
                            <input id="local_area" type="text" name="local_area"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selPersonInfo">返回</a>

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
        menuact("01_02_08");
    });
	function uploadpicdiv(){
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
  
  function infoset(){
    
    var personinfo=${personinfo};
    
    var id=personinfo.id;
    var user_id=personinfo.user_id;
    var person_name=personinfo.person_name;
    var card_type=personinfo.card_type;
    var card_number=personinfo.card_number;
    var card_pics=personinfo.card_pics;
    var person_area=personinfo.person_area;
    var person_nation=personinfo.person_nation;
    var person_brithday=personinfo.person_brithday;
    var local_area=personinfo.local_area;
    
	var xx = "";
	for(var i=0;i<=card_pics.split(",").length-1;i++){
		xx += "<img class='aa' style='width:100px;' src='"+card_pics.split(",")[i]+"'>&nbsp;&nbsp;&nbsp;&nbsp;";
	}
    
    
    $("#hiddenid").val(id);
    $("#user_id").val(user_id);
    $("#person_name").val(person_name);
    $("#card_type").val(card_type);
    $("#card_number").val(card_number);
    $("#card_pics").html(xx);
    $("#person_area").val(person_area);
    $("#person_nation").val(person_nation);
    $("#person_brithday").val(person_brithday);
    $("#local_area").val(local_area);
    $("#icon").val(card_pics);
  };
  
  
  $("#subbutton").on("click",function(){
	  $("form").submit();
  });
  
  

  </script>

</body>

</html>