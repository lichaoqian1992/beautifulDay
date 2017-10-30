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
									投诉记录信息	<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">投诉信息</a><i class="icon-angle-right"></i></li>
							<li><a href="selComplaint">投诉记录信息</a></li>
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
                  <span class="hidden-480">新增投诉记录信息</span>
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
                      
                      <form action="addComplaint" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
                        <div class="control-group">
                          <label class="control-label">投诉人ID：</label>
                          <div class="controls">
                            <input id="from_user_id" type="text" name="from_user_id"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->

                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉人角色：</label>
                          <div class="controls">
                            <input id="from_user_role" type="text" name="from_user_role" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉人角色值：</label>
                          <div class="controls">
                            <input id="from_user_role_value" type="text" name="from_user_role_value" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">被投诉人ID：</label>
                          <div class="controls">
                            <input id="to_user_id" type="text" name="to_user_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">被投诉人角色：</label>
                          <div class="controls">
                            <input id="to_user_role" type="text"  name="to_user_role" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">被投诉人角色值：</label>
                          <div class="controls">
                            <input id="to_user_role_value" type="text"  name="to_user_role_value" class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉类别：</label>
                          <div class="controls">
                            <input id="type" type="text"  name="type" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉标题：</label>
                          <div class="controls">
                            <input id="title" type="text"  name="title" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉详细内容：</label>
                          <div class="controls">
                            <input id="content" type="text" name="content" class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉相关图片：</label>
                          <div class="controls">
                          	<div id="pics"></div>
                           <!--  <img id="pics" name="pics"  style="height:100px;"> -->
							<input id="upFile" type='file' name='file' style="display: none" multiple>
							<input type="hidden" id="icon" name="icon" />
							<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">关联ID：</label>
                          <div class="controls">
                            <input id="correlation_order_id" type="text" name="correlation_order_id"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉时间：</label>
                          <div class="controls">
                            <input id="addtime" type="text" name="addtime"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉分配人员：</label>
                          <div class="controls">
                            <input id="handle_user_id" type="text"  name="handle_user_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉状态：</label>
                          <div class="controls">
                             <select id="state" name="state">
                                <option value="0">待受理</option>
                                <option value="1">处理中</option>
                                <option value="2">未处理</option>
                                <option value="3">用户胜诉</option>
                                <option value="4">商家胜诉</option>
                            </select>
                            
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉人联系电话：</label>
                          <div class="controls">
                            <input id="mobile" type="text" name="mobile"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉人姓名：</label>
                          <div class="controls">
                            <input id="name" type="text" name="name"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">投诉所在地区：</label>
                          <div class="controls">
                            <input id="area" type="text" name="area"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">是否删除：</label>
                          <div class="controls">
                            <select id="is_del" name="is_del">
                                <option value="0">正常</option>
                                <option value="1">删除</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">关联类型：</label>
                          <div class="controls">
                            <input id="orderid_type" type="text" name="orderid_type"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">订单状态：</label>
                          <div class="controls">
                            <select id="order_status" name="order_status">
                                <option value="1">生成订单</option>
                                <option value="2">确认订单</option>
                                <option value="3">完成订单</option>
                                <option value="4">取消订单</option>
                                <option value="5">锁定订单</option>
                                <option value="6">卖家取消订单</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="form-actions">

                            <button  class="btn blue">
								<i class="icon-ok"></i> 保存
							</button>
                          

                          <a type="button" class="btn" href="selComplaint">返回</a>

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
        menuact("06_01_01");
    });
  
  function uploadpicdiv(){
	  	$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

  </script>

</body>

</html>