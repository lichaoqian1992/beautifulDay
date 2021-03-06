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
            <jsp:include page="/WEB-INF/jsp/banner.html" flush="true"/>
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
      <jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
    </div>

    <div class="page-content">

      <div class="container-fluid">
        <div class="row-fluid">
          <div class="span12">
            <h3 class="page-title">
                                  商家业务合作列表
              <!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">商家业务管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">商家业务合作列表</a></li>
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
                  <span class="hidden-480">新增商家业务信息</span>
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
                          <label class="control-label">用户ID：</label>
                          <div class="controls">
                            <input id="user_id" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">角色类型：</label>
                          <div class="controls">
                            <input id="user_role_type" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">角色对应值：</label>
                          <div class="controls">
                            <input id="user_role_value" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">业务名称：</label>
                          <div class="controls">
                            <input id="name" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">业务简称：</label>
                          <div class="controls">
                            <input id="call_index" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">对应频道ID：</label>
                          <div class="controls">
                            <input id="channel_id" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">业务简介：</label>
                          <div class="controls">
                            <textarea id="content"   class="m-wrap small" ></textarea><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">业务有效期：</label>
                          <div class="controls">
                            <input id="valid_time" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">默认保证金：</label>
                          <div class="controls">
                            <input id="deposit" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">默认手续费：</label>
                          <div class="controls">
                            <input id="poundage" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">默认提成比例：</label>
                          <div class="controls">
                            <input id="percentage" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">实缴保证金：</label>
                          <div class="controls">
                            <input id="user_deposit" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">实缴手续费：</label>
                          <div class="controls">
                            <input id="user_poundage" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">实缴提成比例：</label>
                          <div class="controls">
                            <input id="user_percentage" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">电子协议：</label>
                          <div class="controls">
                            <input id="agreement" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">签约协议：</label>
                          <div class="controls">
                            <input id="user_agreement" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">当前状态：</label>
                          <div class="controls">
                            <select id="status">
                            	<option value="0">待确认</option>
                            	<option value="1">已付款</option>
                            	<option value="2">已生效</option>
                            	<option value="3">已禁用</option>
                            	<option value="4">已退款</option>
                            	<option value="5">已确认</option>
                            	<option value="6">已过期</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">申请时间：</label>
                          <div class="controls">
                            <input id="add_time" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">更新时间：</label>
                          <div class="controls">
                            <input id="update_time" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">描述：</label>
                          <div class="controls">
                            <input id="remark" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否删除：</label>
                          <div class="controls">
                            <select id="is_del">
                            	<option value="0">正常</option>
                            	<option value="1">删除</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">业务开关：</label>
                          <div class="controls">
                            <select id="ywkg">
                            	<option value="0">关闭</option>
                            	<option value="1">开启</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">业务描述：</label>
                          <div class="controls">
                            <input id="ywms" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">业务规则：</label>
                          <div class="controls">
                            <input id="ywgz" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selCooperate">返回</a>

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
        onkey(3);
        infoset();
        setactive("332");
    });
  
  function infoset(){
    
    var cooperateinfo=${cooperateinfo };
    
    var id=cooperateinfo.id;
    var	user_id=cooperateinfo.user_id;
    var	user_role_type=cooperateinfo.user_role_type;
    var	user_role_value=cooperateinfo.user_role_value;
    var	name=cooperateinfo.name;
    var	call_index=cooperateinfo.call_index;
    var	channel_id=cooperateinfo.channel_id;
    var	content=cooperateinfo.content;
    var	valid_time=cooperateinfo.valid_time;
    var	deposit=cooperateinfo.deposit;
    var	poundage=cooperateinfo.poundage;
    var	percentage=cooperateinfo.percentage;
    var	user_deposit=cooperateinfo.user_deposit;
    var	user_poundage=cooperateinfo.user_poundage;
    var	user_percentage=cooperateinfo.user_percentage;
    var	agreement=cooperateinfo.agreement;
    var	user_agreement=cooperateinfo.user_agreement;
    var	status=cooperateinfo.status;
    var	add_time=cooperateinfo.add_time;
    var	update_time=cooperateinfo.update_time;
    var	remark=cooperateinfo.remark;
    var	is_del=cooperateinfo.is_del;
    var	ywkg=cooperateinfo.ywkg;
    var	ywms=cooperateinfo.ywms;
    var	ywgz=cooperateinfo.ywgz;

    
    $("#hiddenid").val(id);
    $("#user_id").val(user_id);	
    $("#user_role_type").val(user_role_type);
    $("#user_role_value").val(user_role_value);
    $("#name").val(name);
    $("#call_index").val(call_index);
    $("#channel_id").val(channel_id);
    $("#content").val(content);
    $("#valid_time").val(valid_time);
    $("#deposit").val(deposit);
    $("#poundage").val(poundage);
    $("#percentage").val(percentage);
    $("#user_deposit").val(user_deposit);
    $("#user_poundage").val(user_poundage);
    $("#user_percentage").val(user_percentage);
    $("#agreement").val(agreement);
    $("#user_agreement").val(user_agreement);
    $("#status").val(status);
    $("#add_time").val(add_time);
    $("#update_time").val(update_time);
    $("#remark").val(remark);
    $("#is_del").val(is_del);
    $("#ywkg").val(ywkg);
    $("#ywms").val(ywms);
    $("#ywgz").val(ywgz);

  };
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "updCooperate",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
      id:$("#hiddenid").val(),
      user_id:$("#user_id").val(),
      user_role_type:$("#user_role_type").val(),
      user_role_value:$("#user_role_value").val(),
      name:$("#name").val(),
      call_index:$("#call_index").val(),
      channel_id:$("#channel_id").val(),
      content:$("#content").val(),
      valid_time:$("#valid_time").val(),
      deposit:$("#deposit").val(),
      poundage:$("#poundage").val(),
      percentage:$("#percentage").val(),
      user_deposit:$("#user_deposit").val(),
      user_poundage:$("#user_poundage").val(),
      user_percentage:$("#user_percentage").val(),
      agreement:$("#agreement").val(),
      user_agreement:$("#user_agreement").val(),
      status:$("#status").val(),
      add_time:$("#add_time").val(),
      update_time:$("#update_time").val(),
      remark:$("#remark").val(),
      is_del:$("#is_del").val(),
      ywkg:$("#ywkg").val(),
      ywms:$("#ywms").val(),
      ywgz:$("#ywgz").val()
        },
    success : function(data) {
    	console.log(data);
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