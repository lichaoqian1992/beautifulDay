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
             订单结算内容信息
              <!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">订单关联信息</a><i class="icon-angle-right"></i></li>
              <li><a href="selOrderSettlement">订单结算内容信息</a></li>
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
                  <span class="hidden-480">新增订单结算内容信息</span>
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
                          <label class="control-label">对应订单ID：</label>
                          <div class="controls">
                            <input id="order_id" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">订单号：</label>
                          <div class="controls">
                            <input id="order_no" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">订单类别：</label>
                          <div class="controls">
                            <input id="order_type" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">订单所属用户：</label>
                          <div class="controls">
                            <input id="shop_user_id" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">订单所属用户角色：</label>
                          <div class="controls">
                            <input id="shop_user_role_type" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">订单所属用户角色值：</label>
                          <div class="controls">
                            <input id="shop_user_role_value" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">结算金额：</label>
                          <div class="controls">
                            <input id="settlement_money" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">返利金额：</label>
                          <div class="controls">
                            <input id="percentage_money" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">实得金额：</label>
                          <div class="controls">
                            <input id="real_money" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">结算时间：</label>
                          <div class="controls">
                            <input id="add_time" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">结算说明：</label>
                          <div class="controls">
                            <input id="remark" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selOrderSettlement">返回</a>

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
        menuact("09_03_03");
    });
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "addOrderSettlement",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
    	  order_id:$("#order_id").val(),
        order_no:$("#order_no").val(),
        order_type:$("#order_type").val(),
        shop_user_id:$("#shop_user_id").val(),
        shop_user_role_type:$("#shop_user_role_type").val(),
        shop_user_role_value:$("#shop_user_role_value").val(),
        settlement_money:$("#settlement_money").val(),
        percentage_money:$("#percentage_money").val(),
        real_money:$("#real_money").val(),
        add_time:$("#add_time").val(),
        remark:$("#remark").val()
        },
    success : function(data) {
      if(data.status==0){
        location.reload();
      }else{
        alert("修改失败");
      }
    },
    })
  });
  
  

  </script>

</body>

</html>