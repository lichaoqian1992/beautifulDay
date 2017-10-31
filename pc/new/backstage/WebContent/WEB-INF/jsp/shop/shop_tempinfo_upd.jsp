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
										用户商家信息修改临存
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">商家详细信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selTempShopInfo">用户商家信息修改临存</a></li>
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
                  <span class="hidden-480">修改用户商家信息修改临存</span>
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
                      
                      <form action="updTempShopInfo" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
                        <input type="hidden" id="hiddenid" value="" name="id">
                        <div class="control-group">
                          <label class="control-label">信息ID  ：</label>
                          <div class="controls">
                            <input id="recorded_id" type="text" name="recorded_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">商家分组：</label>
                          <div class="controls">
                            <input id="group_id" type="text" name="group_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">用户ID：</label>
                          <div class="controls">
                            <input id="user_id" type="text" name="user_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺名称：</label>
                          <div class="controls">
                            <input id="name" type="text" name="name" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺简介：</label>
                          <div class="controls">
                            <input id="content" type="text" name="content" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺关键字 ：</label>
                          <div class="controls">
                            <input id="TAG" type="text" name="TAG"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺标志图片：</label>
                          <div class="controls">
                    			<img id="pc_logo" name="pc_logo"  style="height:100px;">
								<input id="upFile" type='file' name='file' style="display: none">
								<input type="hidden"  name="icon" id="icon"/>
								<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">手机店铺标志：</label>
                          <div class="controls">
	                            <img id="wap_logo" name="wap_logo"  style="height:100px;">
								<input id="upFile1" type='file' name='file1' style="display: none">
								<input type="hidden"  name="icon1" id="icon1"/>
								<a id="upAgain1" onclick="uploadpicdivTow()" style="margin-left:5%">重新上传</a>
                            </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">联系电话：</label>
                          <div class="controls">
                            <input id="mobile" type="text" name="mobile" class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">联系地址：</label>
                          <div class="controls">
                            <input id="address" type="text" name="address"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">二级域名：</label>
                          <div class="controls">
                            <input id="weburl" type="text"  name="weburl" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺所在区域：</label>
                          <div class="controls">
                            <input id="area" type="text" name="area"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否支持配送：</label>
                          <div class="controls">
                            <select id="is_distribution" name="is_distribution">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">配送范围：</label>
                          <div class="controls">
                            <input id="distribution_area" name="distribution_area" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">配送地理范围：</label>
                          <div class="controls">
                            <input id="distribution_distance" name="distribution_distance"  type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否支持快递：</label>
                          <div class="controls">
                            <select id="is_express" name="is_express">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">支持快递类型：</label>
                          <div class="controls">
                            <input id="express_types" type="text" name="express_types"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否支持到付：</label>
                          <div class="controls">
                            <select id="is_local_transaction" name="is_local_transaction">
                                <option value="1">支持</option>
                                <option value="0">不支持</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否需要预约：</label>
                          <div class="controls">
                            <select id="is_booking" name="is_booking">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否接入插件：</label>
                          <div class="controls">
                            <select id="is_plugins" name="is_plugins">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺状态：</label>
                          <div class="controls">
                            <select id="state" name="state">
                                <option value="1">已通过</option>
                                <option value="2">不通过</option>
                                <option value="3">被冻结</option>
                                <option value="0">待审核</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">更新内容：</label>
                          <div class="controls">
                            <input id="remark" type="text" name="remark"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">更新时间：</label>
                          <div class="controls">
                            <input id="update_time" type="text" name="update_time"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">业务提点：</label>
                          <div class="controls">
                            <input id="percentage" type="text" name="percentage"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selTempShopInfo">返回</a>

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
        menuact("02_01_02");
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
  function infoset(){
    
    var tempshopinfo=${tempshopinfo };
    console.log(tempshopinfo);
    var id                   =tempshopinfo.id;
    var recorded_id            =tempshopinfo.recorded_id;
    var group_id             =tempshopinfo.group_id;
    var user_id              =tempshopinfo.user_id;
    var name                 =tempshopinfo.name;
    var content              =tempshopinfo.content;
    var TAG                  =tempshopinfo.TAG;
    var pc_logo              =tempshopinfo.pc_logo;
    var wap_logo             =tempshopinfo.wap_logo;
    var mobile               =tempshopinfo.mobile;
    var address              =tempshopinfo.address;
    var weburl               =tempshopinfo.weburl;
    var area                 =tempshopinfo.area;
    var is_distribution      =tempshopinfo.is_distribution;
    var distribution_area    =tempshopinfo.distribution_area;
    var distribution_distance=tempshopinfo.distribution_distance;
    var is_express           =tempshopinfo.is_express;
    var express_types        =tempshopinfo.express_types;
    var is_local_transaction =tempshopinfo.is_local_transaction;
    var is_booking           =tempshopinfo.is_booking;
    var is_plugins           =tempshopinfo.is_plugins;
    var state                =tempshopinfo.state;
    var remark               =tempshopinfo.remark;
    var update_time          =tempshopinfo.update_time;
    var percentage           =tempshopinfo.percentage;
    
    $("#hiddenid").val(id);
    $("#recorded_id").val(recorded_id);
    $("#group_id").val(group_id);
    $("#user_id").val(user_id);
    $("#name").val(name);
    $("#content").val(content);
    $("#TAG").val(TAG);
    $("#pc_logo").attr('src',pc_logo);
    $("#wap_logo").attr('src',wap_logo);
    $("#mobile").val(mobile);
    $("#address").val(address);
    $("#weburl").val(weburl);
    $("#area").val(area);
    $("#is_distribution").val(is_distribution);
    $("#distribution_area").val(distribution_area);
    $("#distribution_distance").val(distribution_distance);
    $("#is_express").val(is_express);
    $("#express_types").val(express_types);
    $("#is_local_transaction").val(is_local_transaction);
    $("#is_booking").val(is_booking);
    $("#is_plugins").val(is_plugins);
    $("#state").val(state);
    $("#remark").val(remark);
    $("#update_time").val(update_time);
    $("#percentage").val(percentage);
 	$("#icon").val(pc_logo);
 	$("#icon1").val(wap_logo);

  };
  
  
  $("#subbutton").on("click",function(){
	  $("form").submit();
  });
  
  

  </script>

</body>

</html>