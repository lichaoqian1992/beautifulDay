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
                                  修改买家信息记录
              <!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">买家信息管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">修改买家信息记录</a></li>
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
                  <span class="hidden-480">新增用户个人信息</span>
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
                          <label class="control-label">用户经验值：</label>
                          <div class="controls">
                            <input id="user_exp" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最近代赔次数：</label>
                          <div class="controls">
                            <input id="damage_times" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最近代培金额：</label>
                          <div class="controls">
                            <input id="damage_money" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最近代培时间：</label>
                          <div class="controls">
                            <input id="damage_date" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">评价次数：</label>
                          <div class="controls">
                            <input id="review_times" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">整体评分：</label>
                          <div class="controls">
                            <input id="review_score" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最近购买过TAG：</label>
                          <div class="controls">
                            <input id="buy_tags" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最近浏览过TAG：</label>
                          <div class="controls">
                            <input id="view_tags" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最近所在地区：</label>
                          <div class="controls">
                            <input id="areas" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最后更新时间：</label>
                          <div class="controls">
                            <input id="update_time" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selBuyerInfo">返回</a>

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
        onkey(2);
        infoset();
        setactive("241");
    });
  
  function infoset(){
    
    var buyerinfo=${buyerinfo };
    
    var id=buyerinfo.id;
    var user_id=buyerinfo.user_id;
    var user_exp=buyerinfo.user_exp;
    var damage_times=buyerinfo.damage_times;
    var damage_money=buyerinfo.damage_money;
    var damage_date=buyerinfo.damage_date;
    var review_times=buyerinfo.review_times;
    var review_score=buyerinfo.review_score;
    var buy_tags=buyerinfo.buy_tags;
    var view_tags=buyerinfo.view_tags;
    var areas=buyerinfo.areas;
    var update_time=buyerinfo.update_time;
    
    $("#hiddenid").val(id);
    $("#user_id").val(user_id);
    $("#user_exp").val(user_exp);
    $("#damage_times").val(damage_times);
    $("#damage_money").val(damage_money);
    $("#damage_date").val(damage_date);
    $("#review_times").val(review_times);
    $("#review_score").val(review_score);
    $("#buy_tags").val(buy_tags);
    $("#view_tags").val(view_tags);
    $("#areas").val(areas);
    $("#update_time").val(update_time);

  };
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "updBuyerInfo",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
      id:$("#hiddenid").val(),
      user_id:$("#user_id").val(),
      user_exp:$("#user_exp").val(),
      damage_times:$("#damage_times").val(),
      damage_money:$("#damage_money").val(),
      damage_date:$("#damage_date").val(),
      review_times:$("#review_times").val(),
      review_score:$("#review_score").val(),
      buy_tags:$("#buy_tags").val(),
      view_tags:$("#view_tags").val(),
      areas:$("#areas").val(),
      update_time:$("#update_time").val()
        },
    success : function(data) {
      if(data.status==0){
        location.reload();
      }else{
        alert(修改失败);
      }
    }
        
    })
  });
  
  

  </script>

</body>

</html>