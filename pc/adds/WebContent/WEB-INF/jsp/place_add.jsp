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
<link href="css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="css/plugins/switchery/switchery.css" rel="stylesheet">
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
  
          <li>
              <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">广告管理</span><span class="fa arrow"></span></a>
              <ul class="nav nav-second-level">
                  <li><a href="toAdds">广告列表</a>
                  </li>
                  <li><a href="toAddAdds">新增广告</a>
                  </li>
              </ul>
          </li>
          <li class="active">
              <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">广告位管理</span><span class="fa arrow"></span></a>
              <ul class="nav nav-second-level">
                  <li><a href="toPlace">广告位列表</a>
                  </li>
                  <li class="active"><a href="toAddPlace">新增广告位</a>
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
                            <strong>新增广告位</strong>
                        </li>
          </ol>
        </div>
        <div class="col-lg-2"></div>
      </div>
      <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox-content">
            <form action="addPlace" method="GET" id="add" role="form" class="form-horizontal">
              <legend>新增广告位</legend>
              
             
              <div class="form-group">
                <label class="col-sm-3 control-label" for="pl_id">广告位：</label>
                <div class="col-sm-7">
                  <input type="text" maxlength="2" class="form-control" id="pl_id" name="pl_id" required="required" placeholder="广告位为纯数字">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">终端类型：</label>
                <div class="col-sm-7">
                  <select name="channel"  class="form-control">
                    <option value="0">PC端</option>
                    <option value="1">APP端</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="title">广告位类型：</label>
                <div class="col-sm-7">
                  <select name="type"  class="form-control">
                    <option value="0">主页</option>
                    <option value="1">餐饮美食</option>
                    <option value="2">休闲娱乐</option>
                    <option value="3">酒店住宿</option>
                    <option value="4">旅游观光</option>
                    <option value="5">日用百货</option>
                    <option value="6">外卖服务</option>
                    <option value="7">农村电商</option>
                    <option value="8">便民生活</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="brief">广告方式：</label>
                <div class="col-sm-7">
                  <select class="form-control" name="display" id="type" onchange="checkType()">
                    <option value="0">一张</option>
                    <option value="1">轮播</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" for="type">数量：</label>
                <div class="col-sm-7">
                  <input id="number" name="number" class="form-control" type="number" onchange="checkNumber()" value="1">
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
  <script src="js/hplus.js?v=2.2.0"></script>
  <script src="js/plugins/pace/pace.min.js"></script>
  <script src="js/plugins/validate/jquery.validate.min.js"></script>
  <script src="js/plugins/validate/messages_zh.min.js"></script>
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
            pl_id: {
              required: true
            },
            display: {
              required: true
            },
            number: {
              required: true
            }
          }
        });
      });
      //校验数量和广告的方式，即一张对应的就是一张方式，多张对应的就是轮播
      function checkNumber(){
    	  var type = $("#type").val();//广告的方式
    	  var number = $("#number").val();//图片的数量
    	  if(type == 1){//轮播方式
    		  if(number<2){
    			  alert("图片数量不对");
    			  $("#number").val("");
    			  return;
    		  }
    	  }
      }
      //选择广告类型的时候动态的改变图片的数量
      function checkType(){
    	  var type = $("#type").val();//广告的方式
    	  if(type == 1){
    		  $("#number").val(""); 
    	  }else if(type == 0){
    		  $("#number").val("1"); 
    	  }
      }
  </script>
</body>
</html>