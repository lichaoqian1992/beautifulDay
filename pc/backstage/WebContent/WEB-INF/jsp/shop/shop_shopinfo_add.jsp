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
            商家正式列表
              <!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">商家管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">商家正式列表</a></li>
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
                  <span class="hidden-480">新增商家正式列表</span>
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
                          <label class="control-label">商家分组：</label>
                          <div class="controls">
                             <select id="group_id">
                            </select>
                          </div>
                        </div>
                        
                        <div class="control-group">
                          <label class="control-label">用户ID：</label>
                          <div class="controls">
                            <input id="user_id" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺名称：</label>
                          <div class="controls">
                            <input id="name" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺简介：</label>
                          <div class="controls">
                            <input id="content" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺图片：</label>
                          <div class="controls">
                            <input id="pics" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺关键字：</label>
                          <div class="controls">
                            <input id="TAG" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺标志图片：</label>
                          <div class="controls">
                            <input id="pc_logo" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">手机店铺标志：</label>
                          <div class="controls">
                            <input id="wap_logo" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">联系电话：</label>
                          <div class="controls">
                            <input id="mobile" type="text"  class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">在线联系：</label>
                          <div class="controls">
                            <input id="online_content" type="text"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">联系地址：</label>
                          <div class="controls">
                            <input id="address" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">二级域名：</label>
                          <div class="controls">
                            <input id="weburl" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺所在区域：</label>
                          <div class="controls">
                            <input id="area" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">地理坐标经度：</label>
                          <div class="controls">
                            <input id="longitude" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">地理坐标纬度：</label>
                          <div class="controls">
                            <input id="latitude" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否支持配送：</label>
                          <div class="controls">
                            <select id="is_distribution">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">配送范围：</label>
                          <div class="controls">
                            <input id="distribution_area" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">配送地理范围：</label>
                          <div class="controls">
                            <input id="distribution_distance" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否支持快递：</label>
                          <div class="controls">
                            <select id="is_express">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">支持快递类型：</label>
                          <div class="controls">
                            <input id="express_types" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否支持到付：</label>
                          <div class="controls">
                            <select id="is_local_transaction">
                                <option value="1">支持</option>
                                <option value="0">不支持</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否需要预约：</label>
                          <div class="controls">
                            <select id="is_booking">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否接入插件：</label>
                          <div class="controls">
                            <select id="is_plugins">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">评价次数：</label>
                          <div class="controls">
                            <input id="review_times" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">整体评分：</label>
                          <div class="controls">
                            <input id="review_score" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">服务评分：</label>
                          <div class="controls">
                            <input id="service_review_score" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">包装评分：</label>
                          <div class="controls">
                            <input id="pack_review_score" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">配送评分：</label>
                          <div class="controls">
                            <input id="distribution_review_score" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最近代赔次数：</label>
                          <div class="controls">
                            <input id="damage_times" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">最近代赔金额：</label>
                          <div class="controls">
                            <input id="damage_money" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        <div class="control-group">
                          <label class="control-label">最近代赔时间：</label>
                          <div class="controls">
                            <input id="damage_date" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺状态：</label>
                          <div class="controls">
                            <select id="state">
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
                            <input id="remark" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">更新时间：</label>
                          <div class="controls">
                            <input id="update_time" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">新增时间：</label>
                          <div class="controls">
                            <input id="add_time" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否删除：</label>
                          <div class="controls">
                             <select id="is_del">
                                <option value="1">删除</option>
                                <option value="0">正常</option>
                             </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">主营业务：</label>
                          <div class="controls">
                            <input id="main_business" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">客服来源：</label>
                          <div class="controls">
                            <input id="from_value" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否签约：</label>
                          <div class="controls">
                            <select id="is_sign_up">
                                <option value="1">是</option>
                                <option value="0">否</option>
                             </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">推荐：</label>
                          <div class="controls">
                            <select id="hot">
                                <option value="1">全国</option>
                                <option value="2">省</option>
                                <option value="3">市</option>
                                <option value="4">区县</option>
                                <option value="5">乡镇</option>
                                <option value="0">不推荐</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">营业时间：</label>
                          <div class="controls">
                            <input id="yysj" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺开关：</label>
                          <div class="controls">
                            <select id="dpkg">
                                <option value="1">开启</option>
                                <option value="0">关闭</option>
                             </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">业务提点：</label>
                          <div class="controls">
                            <input id="percentage" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">店铺帮助须知：</label>
                          <div class="controls">
                            <input id="matter" type="text"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">提现开关：</label>
                          <div class="controls">
                            <select id="is_balance">
                                <option value="0">自动提现</option>
                                <option value="1">关闭自动提现</option>
                             </select>
                          </div>
                        </div>
                        
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selShopInfo">返回</a>

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
        setactive("321");
        $.ajax({
            type : "GET",
            url : "findShopGroup",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : {},
            success : function(data) {
              console.log(data);
              var dataList = data.result;
              for(var i in dataList){
                 $('#group_id').append('<option value='+dataList[i].id+'>'+dataList[i].title+'</option>');
               
             } 
            }
      });      
    });
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "addShopInfo",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
    	   group_id:$("#group_id").val(),
         user_id:$("#user_id").val(),
         name:$("#name").val(),
         content:$("#content").val(),
         pics:$("#pics").val(),
         TAG:$("#TAG").val(),
         pc_logo:$("#pc_logo").val(),
         wap_logo:$("#wap_logo").val(),
         mobile:$("#mobile").val(),
         online_content:$("#online_content").val(),
         address:$("#address").val(),
         weburl:$("#weburl").val(),
         area:$("#area").val(),
         longitude:$("#longitude").val(),
         latitude:$("#latitude").val(),
         is_distribution:$("#is_distribution").val(),
         distribution_area:$("#distribution_area").val(),
         distribution_distance:$("#distribution_distance").val(),
         is_express:$("#is_express").val(),
         express_types:$("#express_types").val(),
         is_local_transaction:$("#is_local_transaction").val(),
         is_booking:$("#is_booking").val(),
         is_plugins:$("#is_plugins").val(),
         review_times:$("#review_times").val(),
         review_score:$("#review_score").val(),
         service_review_score:$("#service_review_score").val(),
         pack_review_score:$("#pack_review_score").val(),
         distribution_review_score:$("#distribution_review_score").val(),
         damage_times:$("#damage_times").val(),
         damage_money:$("#damage_money").val(),
         damage_date:$("#damage_date").val(),
         state:$("#state").val(),
         remark:$("#remark").val(),
         update_time:$("#update_time").val(),
         add_time:$("#add_time").val(),
         is_del:$("#is_del").val(),
         main_business:$("#main_business").val(),
         from_value:$("#from_value").val(),
         is_sign_up:$("#is_sign_up").val(),
         hot:$("#hot").val(),
         yysj:$("#yysj").val(),
         dpkg:$("#dpkg").val(),
         percentage:$("#percentage").val(),
         matter:$("#matter").val(),
         is_balance:$("#is_balance").val()
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