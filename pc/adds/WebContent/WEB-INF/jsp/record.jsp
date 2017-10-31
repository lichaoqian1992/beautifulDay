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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告管理系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">

                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="img/profile_small.jpg" />
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold" id="user-name">张三丰</strong>
                             </span>  <span class="text-muted text-xs block" id="lv-privilege">超级管理员 <b class="caret"></b></span> </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="javascript:;">修改头像</a>
                                </li>
                                <li><a href="javascript:;">个人资料</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="login.html">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            广告管理系统
                        </div>

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
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a href="toMain" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用广告管理系统</span>
                        </li>
                        <li>
                            <a href="login.html">
                                <i class="fa fa-sign-out"></i>退出
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            
            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="ibox-content timeline" id="timeLine">


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
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/hplus.js?v=2.2.0"></script>
    <script src="js/plugins/pace/pace.min.js"></script>

    <!-- Page-Level Scripts -->
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
         
        });
        
        
        $.ajax({
            type : "GET",
            url : "allRecords",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : {},
            success : function(data) {
              console.log(data)
              if(data.errCode == 0){
                var item = data.result;
                var box = $("#timeLine");
                for(var i = 0; i < item.length; i ++){
                  var operate = "";
                  var talk = "";
                  switch(item[i].operate){
                    case '0': 
                      operate = '登陆了广告管理系统';
                      talk = '准备开始工作了';
                      break;
                    case '1': 
                      operate = '录入了一条广告';
                      talk = '这是第几条来着？';
                      break;
                    case '2': 
                      operate = '修改了一条广告';
                      talk = '改改改。。。。';
                      break;
                    case '3': 
                      operate = '发布了一条广告';
                      talk = '又多了一条续费广告';
                      break;
                    case '4': 
                      operate = '关闭了一条广告';
                      talk = '广告到期了就关闭了吧';
                      break;
                  }
                  var txt = '<div class="timeline-item">'+
                                '<div class="row">'+
                                    '<div class="col-xs-3 date ui-sortable">'+
                                        '<i class="fa fa-briefcase"></i>'+
                                        item[i].date+
                                        '<br>'+
                                        '<small class="text-navy">'+item[i].time+'</small>'+
                                    '</div>'+
                                    '<div class="col-xs-7 content no-top-border ui-sortable">'+
                                        '<p class="m-b-xs"><strong>'+item[i].username+'</strong>'+operate+'</p>'+
                                        '<p>'+talk+'</p>'+
                                    '</div>'+
                                '</div>'+
                            '</div>';
                  box.append(txt)
                }
              }
            }
          });

    </script>

</body>

</html>
    