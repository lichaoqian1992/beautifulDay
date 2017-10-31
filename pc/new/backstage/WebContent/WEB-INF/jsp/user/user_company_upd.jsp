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
										用户公司信息<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">用户详细</a><i class="icon-angle-right"></i></li>
							<li><a href="selCompanyInfo">用户公司信息</a></li>
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
                  <span class="hidden-480">修改用户公司信息列表</span>
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
                      
                      <form action="updCompanyInfo" class="form-horizontal" method="POST"
													enctype="multipart/form-data">
                        <input type="hidden" id="hiddenid"value="" name="id">
                        <div class="control-group">
                          <label class="control-label">用户ID：</label>
                          <div class="controls">
                            <input id="user_id" type="text"  name="user_id" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司类型：</label>
                          <div class="controls">
                            <select id="type" name="type">
                              <option>个体户</option>
                              <option>私营企业</option>
                              <option>国有企业</option>
                              <option>上市公司</option>
                              <option>政府机构</option>
                              <option>事业单位</option>
                              <option>公益机构</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司名称：</label>
                          <div class="controls">
                            <input id="name" type="text" name="name" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司所在地区：</label>
                          <div class="controls">
                            <input id="area" type="text"  name="area"   class="m-wrap large" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司网址：</label>
                          <div class="controls">
                            <input id="website" type="text" name="website"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司邮箱：</label>
                          <div class="controls">
                            <input id="email" type="text"  name="email"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司联系电话：</label>
                          <div class="controls">
                            <input id="telephone" type="text" name="telephone"   class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司注册地址：</label>
                          <div class="controls">
                            <input id="address" type="text"  name="address"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司经营范围：</label>
                          <div class="controls">
                            <input id="business" type="text"  name="business"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司证件号码：</label>
                          <div class="controls">
                            <input id="card_number" type="text" name="card_number" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司证件图片：</label>
                          <div class="controls">
							<img id="card_pics" name="card_pics"  style="height:100px;">
							<input id="upFile" type='file' name='file' style="display: none">
							<input type="hidden"  name="icon" id="icon"/>
							<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
							<!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">公司证件类型：</label>
                          <div class="controls">
                            <select id="card_type" name="card_type">
                              <option>营业执照</option>
                              <option>组织机构代码证</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">法人代表：</label>
                          <div class="controls">
                            <input id="legal_person" type="text" name="legal_person" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">法人手机号码：</label>
                          <div class="controls">
                            <input id="legal_person_mobile" type="text" name="legal_person_mobile"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">法人身份证号：</label>
                          <div class="controls">
                            <input id="legal_person_idcard" type="text" name="legal_person_idcard"  class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">其他资质证件：</label>
                          <div class="controls">
                            <input id="license_pics" type="text" name="license_pics" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">其他证件号码：</label>
                          <div class="controls">
                            <input id="license_num" type="text" name="license_num" class="m-wrap small" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>

                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn" href="selCompanyInfo">返回</a>

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
        menuact("01_02_01");
    });
  
	function uploadpicdiv(){
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}
  function infoset(){
    
    var companyinfo=${companyinfo };
    
    var id=companyinfo.id;
    
    var user_id =companyinfo.user_id;
    var type =companyinfo.type;
    var name =companyinfo.name;
    var area =companyinfo.area;
    var website =companyinfo.website;
    var email =companyinfo.email;
    var telephone =companyinfo.telephone;
    var address =companyinfo.address;
    var business =companyinfo.business;
    var card_number =companyinfo.card_number;
    var card_pics =companyinfo.card_pics;
    var card_type =companyinfo.card_type;
    var legal_person =companyinfo.legal_person;
    var legal_person_mobile =companyinfo.legal_person_mobile;
    var legal_person_idcard =companyinfo.legal_person_idcard;
    var license_pics =companyinfo.license_pics;
    var license_num =companyinfo.license_num;


    $("#hiddenid").val(id);
    $("#user_id").val(user_id);
    $("#type").val(type);
    $("#name").val(name);
    $("#area").val(area);
    $("#website").val(website);
    $("#email").val(email);
    $("#telephone").val(telephone);
    $("#address").val(address);
    $("#business").val(business);
    $("#card_number").val(card_number);
    $("#card_pics").attr('src',card_pics);
    $("#card_type").val(card_type);
    $("#legal_person").val(legal_person);
    $("#legal_person_mobile").val(legal_person_mobile);
    $("#legal_person_idcard").val(legal_person_idcard);
    $("#license_pics").val(license_pics);
    $("#license_num").val(license_num);
    $("#icon").val(card_pics);
    
 

    

  };
  
  
  $("#subbutton").on("click",function(){
	  $("form").submit();
  });
  
  

  </script>

</body>

</html>