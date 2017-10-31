<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>广告管理系统</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link href="${base}/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
<link href="${base}/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
<link href="${base}/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${base}/css/plugins/switchery/switchery.css" rel="stylesheet">
<link href="${base}/css/animate.css" rel="stylesheet">
<link href="${base}/css/style.css?v=2.2.0" rel="stylesheet">

<!-- upload -->
<link href="${base}/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

</head>
<body>
  <div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
      <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
          <li class="nav-header">
            <div class="dropdown profile-element">
              <span> <img alt="image" class="img-circle" src="img/profile_small.jpg" />
              </span> <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#"> 
              <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold" id="user-name">张三丰</strong>
                </span> <span class="text-muted text-xs block" id="lv-privilege">超级管理员 <b class="caret"></b></span>
              </span>
              </a>
              <ul class="dropdown-menu animated fadeInRight m-t-xs">
                <li><a href="javascript:;">修改头像</a></li>
                <li><a href="javascript:;">个人资料</a></li>
                <li class="divider"></li>
                <li><a href="javascript:;">安全退出</a></li>
              </ul>
            </div>
            <div class="logo-element">广告管理系统</div>
  
          </li>
          <li><a href="toMain"><i class="fa fa-th-large"></i> <span
              class="nav-label">主页</span></a></li>
  
          <li class="active">
              <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">广告管理</span><span class="fa arrow"></span></a>
              <ul class="nav nav-second-level">
                  <li><a href="toAdds">广告列表</a>
                  </li>
                  <li class=""><a href="toAddAdds">新增广告</a>
                  </li>
                   <li class="active"><a href="toAddPCAdds">新增PC广告</a>
                  </li>
              </ul>
          </li>
          <li>
              <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">广告位管理</span><span class="fa arrow"></span></a>
              <ul class="nav nav-second-level">
                  <li><a href="toPlace">广告位列表</a>
                  </li>
                  <li><a href="toAddPlace">新增广告位</a>
                  </li>
              </ul>
          </li>
          <li>
              <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">用户管理</span><span class="fa arrow"></span></a>
              <ul class="nav nav-second-level">
                  <li><a href="toUser">用户列表</a>
                  </li>
                  <li><a href="toAddUser">添加用户</a>
                  </li>
              </ul>
          </li>
        </ul>
  
      </div>
    </nav>

    <div id="page-wrapper" class="gray-bg dashbard-1">
      <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation"
          style="margin-bottom: 0">
        <div class="navbar-header">
          <a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
            href="#"><i class="fa fa-bars"></i> </a>
        </div>
        <ul class="nav navbar-top-links navbar-right">
          <li><span class="m-r-sm text-muted welcome-message">
            <a href="toMain" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用广告管理系统</span>
          </li>
          <li><a href="javascript:;"> <i class="fa fa-sign-out"></i>退出
          </a></li>
        </ul>

        </nav>
      </div>
      <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
          <h2>广告管理系统</h2>
          <ol class="breadcrumb">
            <li>
                            <a href="toMain">主页</a>
                        </li>
                        <li>
                            <strong>新增广告</strong>
                        </li>
          </ol>
        </div>
        <div class="col-lg-2"></div>
      </div>
      <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox-content">
            <form enctype="multipart/form-data" action="addAdds" method="POST" id="add" role="form" class="form-horizontal">
              <legend>新增广告信息</legend>
              
              <div class="form-group">
                <label class="col-sm-3 control-label" for="channel">广告渠道：</label>
                <div class="col-sm-7">
                  <div class="radio col-sm-3 i-checks">
                    <label for="pc">
                      <div class="iradio_square-green">
                        <input type="radio" value="0" class="channel" name="channel" id="pc">
                      </div> 
                      <i></i> PC端
                    </label>
                  </div>
                  
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="pl_id">广告位：</label>
                <div class="col-sm-7">
                  <!-- <input type="text" maxlength="2" class="form-control" id="pl_id" name="pl_id" required="required" placeholder="广告位为纯数字"> -->
                	<select class="form-control" required="required" name="pl_id"id="pl_id" >
                     <option value="1">PC首页</option>
                      <option value="2">2号广告位</option>
                      <option value="3">3号广告位</option>
					  <option value="5">4号广告位</option>
					  <option value="6">5号广告位</option>
				   	  <option value="7">专场活动</option>
				  	  <option value="8">美丽装扮</option>
					  <option value="9">母婴玩具</option>
					  <option value="10">农特产品</option>
					  <option value="11">居家生活</option>
					  <option value="12">休闲时光</option>
					  <option value="13">户外出行</option>
					  <option value="14">打造爱巢</option>
					</select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">投放区域：</label>
                <div class="col-sm-7 row">
                  <div class="col-sm-4">
                    <select class="form-control" required="required" id="province">
                      <option>====省===</option>
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select class="form-control" id="city">
                      <option>===市===</option>
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select class="form-control" id="country">
                      <option>===县===</option>
                    </select>
                  </div>
                  <input type="hidden" class="form-control" value="全国" name="region" id="region">
                  <input type="hidden" class="form-control" value="00" name="code" id="code">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="title">广告标题：</label>
                <div class="col-sm-7">
                  <input type="text" class="form-control" id="title" required="required" name="title" placeholder="请输入广告标题">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="brief">广告描述：</label>
                <div class="col-sm-7">
                  <textarea name="brief" id="brief" class="form-control" rows="1" required="required"></textarea>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="type">广告类型：</label>
                <div class="col-sm-7">
                  <select class="form-control" required="required" name="type">
                      <option value="0">商家</option>
                      <option value="1">商品</option>
                      <option value="2">活动</option>
                       <option value="3">楼层广告——竖</option>
                        <option value="4">楼层广告——横</option>
                    </select>
                </div>
              </div>
              
              <div class="form-group">
                <label class="col-sm-3 control-label" for="shop">商家信息：</label>
                <div class="col-sm-7">
                  <input type="text" class="form-control" name="shop" required="required" id="shop" placeholder="请输入商家信息">
                  
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="val">链接地址：</label>
                <div class="col-sm-7">
                  <input type="text" class="form-control" required="required" name="val" id="val" placeholder="请输入链接地址">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="val">专场信息：</label>
                <div class="col-sm-7">
                  <input type="text" class="form-control" required="required" name="remark" id="remark" placeholder="请输入专场信息">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="state">上传图片：</label>
                <div class="col-sm-7">
                  <input id="upload-pic" class="file" required="required" name="files" type="file" multiple data-preview-file-type="any" >
                </div>
              </div>
              <button type="submit" class="btn btn-primary">提交</button>
            </form>
        </div>
      </div>
      <div class="footer">
        <div class="pull-right">
          By：<a href="javascript:void" target="_blank">manjiwang.com</a>
        </div>
        <div>
          <strong>Copyright</strong> manjiwang.com &copy; 2016
        </div>
      </div>

    </div>
  </div>


  </div>

  <!-- Mainly scripts -->
  <script src="${base}/js/jquery-2.1.1.min.js"></script>
  <!-- upload -->
  <script src="${base}/js/fileinput.js" type="text/javascript"></script>
  
  <script src="${base}/js/bootstrap.min.js"></script>
  <script src="${base}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
  <!-- Custom and plugin javascript -->
  <script src="${base}/js/hplus.js?v=2.2.0"></script>
  <script src="${base}/js/plugins/pace/pace.min.js"></script>
  <script src="${base}/js/plugins/validate/jquery.validate.min.js"></script>
  <script src="${base}/js/plugins/validate/messages_zh.min.js"></script>
  <script>
      $(document).ready(function () {
    	  var username="<%=session.getAttribute("username")%>";
    	  var privilege="<%=session.getAttribute("privilege")%>";
    	  var lPrivilege = ""
    	  switch (privilege){
	          case "0": 
	        	  lPrivilege = "普通用户";
	          break;
	          
	          case "1": 
	        	  lPrivilege = "普通用户";
	          break;
	          
	          case "2": 
	        	  lPrivilege = "管理员";
	          break;
	          
	          case "3": 
	        	  lPrivilege = "管理员";
	          break;
	          
	          case "4": 
	        	  lPrivilege = "管理员";
	          break;
	          
	          case "5": 
	        	  lPrivilege = "管理员";
	          break;
	      }
    	  $("#user-name").text(username);
    	  $("#lv-privilege").text(lPrivilege);
    	  $.validator.setDefaults({
              highlight: function (element) {
                  $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
              },
              success: function (element) {
                  element.closest('.form-group').removeClass('has-error').addClass('has-success');
              },
              errorElement: "span",
              errorClass: "help-block m-b-none",
              validClass: "help-block m-b-none"


          });
    	  $("#add").validate({
    		  ignore: [],
    		  rules:{
				  channel: {
			        required: true
			      },
			 	  type: {
			        required: true
			      },
    		  }
    	  });
        $('.radio input:radio').css('opacity',0);
        $(".radio label").hover(function(){
          $(this).addClass("hover");
          $(this).find('.iradio_square-green').addClass('hover');
        },function(){
          $(this).removeClass("hover");
          $(this).find('.iradio_square-green').removeClass('hover');
        });
        

        $('input:radio').on("change",function(){
          checkChannel();
        });

        checkChannel();
        function checkChannel(){
          $('.radio input:radio').parent().removeClass('checked');
          $('.radio input:radio:checked').parent().addClass('checked');
          if($("#pc").prop("checked")){
            $("#area1").addClass('hide');
            $("#info-list").addClass('hide');
            $("#shop").blur(function(){
              $('input[name="shop"]').val($(this).val());
            });
          }else{
            $('#shop').blur(function(){
              var shopName = $(this).val();
              var address = $("#country1 option:selected").val();
              $.ajax({
                type : "GET",
                url : "queryShop",
                dataType : "json",
                contentType : "application/json; charset=utf-8",
                data : {
                  shopName: shopName,
                  address: address
                },
                success : function(data) {
                  var list = $("#info-list");
                  list.empty();
                  if(data.errCode == 0){
                    var lists = data.result;
                    if(lists.ErrCode == 0){
                      var data = lists.Data;
                      for(var i = 0; i < data.length; i ++){
                        var item = '<li class="list-group-item">'+data[i].item+'</li>'
                        list.append(item);
                      }
                    }else{
                      // var item = '<li class="list-group-item">'+lists.Message+'</li>'
                      // list.append(item);
                    }
                  }else{
                    // var item = '<li class="list-group-item">'+data.errInfo+'</li>'
                    // list.append(item);
                  }
                  $('#info-list').removeClass('hide');
                }
              });
            });
          }
        }

        // 省市县三级联动
        $.ajax({
          type : "GET",
          url : "queryProvince",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {},
          success : function(data) {
            if(data.errCode == "0"){
              var data = data.result;
              var province = $("#province");
              var province1 = $("#province1");
              province.empty();
              province1.empty();
              province.append('<option value="-1">===省===</option>');
              province1.append('<option value="-1">===省===</option>');
              for(var i = 0; i < data.length; i++){
                var option = '<option value="'+data[i].code+'">'+data[i].region+'</option>';
                province.append(option);
                province1.append(option);
              }
            }
          }
        });

        $("#province").change(function(){
          var code = $(this).val();
          $("#code").val(code);
          var region = $("#province option:selected").text();
          $("#region").val(region);
          getCity(code);
        });
        $("#city").change(function(){
          if($(this).val() == '-1'){
            var code = $('#province').val();
            $("#code").val(code);
            var region = $("#province option:selected").text();
            $("#region").val(region);
          }else{
            var code = $(this).val();
            $("#code").val(code);
            var region = $("#province option:selected").text() + ',' + $("#city option:selected").text();
            $("#region").val(region);
            getCountry(code);
          }
        });
        $("#country").change(function(){
          if($(this).val() == '-1'){
            if($(this).val() == '-1'){
              var code = $('#province').val();
              $("#code").val(code);
              var region = $("#province option:selected").text();
              $("#region").val(region);
            }else{
              var code = $(this).val();
              $("#code").val(code);
              var region = $("#province option:selected").text() + ',' + $("#city option:selected").text();
              $("#region").val(region);
            }
          }else{
            var code = $(this).val();
            $("#code").val(code);
            var region = $("#province option:selected").text() + ',' + $("#city option:selected").text() + ',' + $("#country option:selected").text();
            $("#region").val(region);
          }
        });

        function getCity(code){
          $.ajax({
            type : "GET",
            url : "queryCity",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : {
              code: code
            },
            success : function(data) {
              if(data.errCode == "0"){
                var data = data.result;
                var city = $("#city");
                city.empty();
                city.append('<option value="-1">===市===</option>');
                if(data.length == 1){
                  var code = data[0].code;
                  getCountry(code);
                }
                for(var i = 0; i < data.length; i++){
                  var option = '<option value="'+data[i].code+'">'+data[i].region+'</option>';
                  city.append(option);
                }
              }
            }
          });
        }
        function getCountry(code){
          $.ajax({
            type : "GET",
            url : "queryCountry",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : {
              code: code
            },
            success : function(data) {
              if(data.errCode == "0"){
                var data = data.result;
                var country = $("#country");
                country.empty();
                country.append('<option value="-1">===县===</option>');
                for(var i = 0; i < data.length; i++){
                  var option = '<option value="'+data[i].code+'">'+data[i].region+'</option>';
                  country.append(option);
                }
              }
            }
          });
        }

        var getParam = function(name){
          var search = document.location.search;
          var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
          var matcher = pattern.exec(search);
          var items = null;
          if(null != matcher){
            try{
              items = decodeURIComponent(decodeURIComponent(matcher[1]));
            }catch(e){
              try{
                items = decodeURIComponent(matcher[1]);
              }catch(e){
                items = matcher[1];
              }
            }
          }
          return items;
        };
        var queryID = getParam('pl_id');
        var queryChannel = getParam('channel');
        if(queryID){
          $("#pl_id").val(queryID);
        }
        if(queryChannel){
          $("input:radio[name='channel'][value='"+queryChannel+"']").attr("checked","checked");

          $("input:radio[name='channel'][value='"+queryChannel+"']").parent().addClass('checked');
        }
    });
  </script>
</body>
</html>