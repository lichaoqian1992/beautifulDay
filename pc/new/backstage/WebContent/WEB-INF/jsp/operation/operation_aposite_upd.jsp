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
                    全国地区站点信息<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">站点配置</a><i class="icon-angle-right"></i></li>
              <li><a href="selApoSite">全国地区站点信息</a></li>
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
                  <span class="hidden-480">用户全国地区站点信息</span>
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
                          <label class="control-label">站点名称：</label>
                          <div class="controls">
                            <input id="name" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点绑定地区：</label>
                          <div class="controls">
                            <input id="area" type="text"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点绑定地区编码：</label>
                          <div class="controls">
                            <input id="areacode" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点地区级别：</label>
                          <div class="controls">
                            <input id="arealevel" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点上级编号：</label>
                          <div class="controls">
                            <input id="parentid" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点状态：</label>
                          <div class="controls">
                            <select id="status" class="m-wrap small" >
								<option value="0">未生效</option>
								<option value="1">已生效</option>
								<option value="2">已关闭</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点关闭原因：</label>
                          <div class="controls">
                            <input id="remark" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点服务状态：</label>
                          <div class="controls" >
                            <select id="shopstatus" class="m-wrap small" >
								<option value="0">关闭</option>
								<option value="1">开启</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点商家阈值：</label>
                          <div class="controls">
                            <input id="shopminnumber" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">当前站点商家数：</label>
                          <div class="controls">
                            <input id="shopnumber" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点网址：</label>
                          <div class="controls">
                            <input id="url" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">站点添加时间：</label>
                          <div class="controls">
                            <input id="addtime" type="text"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">背景图片：</label>
                          <div class="controls">
                            <input id="sitebanner" type="text"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                       
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn"  href="selApoSite">返回</a>

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
        menuact("03_01_01");
        infoset();
    });
  
  function infoset(){
    
    var apositeinfo=${apositeinfo };
    var id=apositeinfo.id;
    var name=apositeinfo.name;
    var area=apositeinfo.area;
    var area_code=apositeinfo.area_code;
    var area_level=apositeinfo.area_level;
    var parent_id=apositeinfo.parent_id;
    var status=apositeinfo.status;
    var remark=apositeinfo.remark;
    var shop_status=apositeinfo.shop_status;
    var shop_min_number=apositeinfo.shop_min_number;
    var shop_number=apositeinfo.shop_number;
    var url=apositeinfo.url;
    var add_time=apositeinfo.add_time;
    var site_banner=apositeinfo.site_banner;

    $("#hiddenid").val(id);
    $("#name").val(name);
    $("#area").val(area);
    $("#areacode").val(area_code);
    $("#arealevel").val(area_level);
    $("#parentid").val(parent_id);
    $("#status").val(status);
    $("#remark").val(remark);
    $("#shopstatus").val(shop_status);
    $("#shopminnumber").val(shop_min_number);
    $("#shopnumber").val(shop_number);
    $("#url").val(url);
    $("#addtime").val(add_time);
    $("#sitebanner").val(site_banner);

  };
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "updApoSite", 
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
      id:$("#hiddenid").val(),
      name:$("#name").val(),
      area:$("#area").val(),
      area_code:$("#areacode").val(),
      area_level:$("#arealevel").val(),
      parent_id:$("#parentid").val(),
      status:$("#status").val(),
      remark:$("#remark").val(),
      shop_status:$("#shopstatus").val(),
      shop_min_number:$("#shopminnumber").val(),
      shop_number:$("#shopnumber").val(),
      url:$("#url").val(),
      add_time:$("#addtime").val(),
      site_banner:$("#sitebanner").val()
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