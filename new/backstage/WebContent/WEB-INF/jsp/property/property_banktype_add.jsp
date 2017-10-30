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
								支持绑定银行卡/第三方账户信息		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">系统配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selBanktype">支持绑定银行卡/第三方账户信息	</a></li>
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
                  <span class="hidden-480">新增支持绑定银行卡/第三方账户信息</span>
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
                      
                      <form action="addBanktype" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
												<div class="control-group">
													<label class="control-label">类型名称：</label>
													<div class="controls">
														<input id="title" type="text" name="title"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类型图片：</label>
													<div class="controls">
														<img id="img_url" name="img_url"  style="height:100px;">
														<input id="upFile" type='file' name='file' style="display: none">
														<input type="hidden" id="imgUrl" name="imgUrl" />
														<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">备注说明：</label>
													<div class="controls">
														<input id="remark" type="text" name="remark" 	class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">类型分类：</label>
													<div class="controls">
														<select id="type" name="type" class="m-wrap small">
															<option value="1">银行卡</option>
															<option value="2">三方支付</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">提现手续费类型：</label>
													<div class="controls">
														<select id="poundage_type" name="poundage_type" class="m-wrap small">
															<option value="1">百分比</option>
															<option value="2">固定金额</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">提现手续费金额：</label>
													<div class="controls">
														<input id="poundage_amount" type="text"  name="poundage_amount"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">提现最小额：</label>
													<div class="controls">
														<input id="min_amount" type="text" name="min_amount" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">提现最大额：</label>
													<div class="controls">
														<input id="max_amount" type="text" name="max_amount" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">排序：</label>
													<div class="controls">
														<input id="sort_id" type="text"  name="sort_id"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">是否启用：</label>
													<div class="controls">
														<select id="is_lock" name="is_lock" class="m-wrap small">
															<option value="0">启用</option>
															<option value="1">锁定</option>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">背景色彩值：</label>
													<div class="controls">
														<input id="colour" type="text" name="colour" 	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">BIN值：</label>
													<div class="controls">
														<input id="bin" type="text"  name="bin"	class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
													</div>
												</div>
												
												
												<div class="form-actions">

													<button  class="btn blue">
														<i class="icon-ok"></i> 保存
													</button>

													<a type="button" class="btn" href="selBanktype">返回</a>

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
        menuact("03_04_04");
    });
  
  
  /* $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "addBanktype",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
    	account_name:$("#account_name").val(),
		account_title:$("#account_title").val(),
		role_type:$("#role_type").val(),
		account_type:$("#account_type").val(),
		is_encash:$("#is_encash").val(),
		is_transaction:$("#is_transaction").val(),
		is_payment:$("#is_payment").val(),
		account_unit_type:$("#account_unit_type").val(),
		account_unit_name:$("#account_unit_name").val(),
		is_system:$("#is_system").val(),
		is_del:$("#is_del").val()
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
   */
   function uploadpicdiv(){
		$('#img_url').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

  </script>

</body>

</html>