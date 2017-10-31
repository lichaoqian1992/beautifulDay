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

<!-- Data Tables -->
<link href="${base}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

<link href="${base}/css/animate.css" rel="stylesheet">
<link href="${base}/css/style.css?v=2.2.0" rel="stylesheet">

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
					<li class="active"><a href="toMain"><i class="fa fa-th-large"></i> <span
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
					<li><a href="logOut"> <i class="fa fa-sign-out"></i>退出
					</a></li>
				</ul>

				</nav>
			</div>
      <div class="sidebard-panel" style="min-height: 600px;z-index: 1;">
        <div id="record">
            <h4>操作记录 <span class="badge badge-info pull-right"></span></h4>
            
        </div>
      </div>
			<div class="wrapper wrapper-content animated fadeInRight" style="padding-right: 330px;">
          <div class="row">
            <div class="col-md-">
              <div class="row">
                <div class="col-md-6">
                  <div class="widget navy-bg p-lg text-center">
                    <div class="m-b-md">
                        <i class="fa fa-bell fa-4x"></i>
                        <h1 class="m-xs" id="clickCounts">47</h1>
                        <h3 class="font-bold no-margins">
                        点击量
                    </h3>
                        <small>广告的总点击量</small>
                    </div>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="widget lazur-bg p-lg text-center">
                    <div class="m-b-md">
                        <i class="fa fa-laptop fa-4x"></i>
                        <h1 class="m-xs" id="revCounts">47</h1>
                        <h3 class="font-bold no-margins">
                        生效
                    </h3>
                        <small>当前生效的广告数量</small>
                    </div>
                  </div>
                </div>
              </div>
              <div class="">
                <div class="">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                      <span class="label label-success pull-right">月</span>
                      <h5>录入</h5>
                    </div>
                    <div class="ibox-content">
                      <h1 class="no-margins" id="MCounts"></h1>
                      <div class="stat-percent font-bold text-success" id="mnum">38% <i class="fa fa-bolt"></i>
                      </div>
                      <small class="stat-percent">总录入 </small>
                    </div>
                  </div>
                </div>
                <div class="">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                      <span class="label label-primary pull-right">昨日</span>
                      <h5>录入</h5>
                    </div>
                    <div class="ibox-content">
                      <h1 class="no-margins" id="DCounts"></h1>
                      <div class="stat-percent font-bold text-navy" id="dnum">98% <i class="fa fa-bolt"></i>
                      </div>
                      <small class="stat-percent">总录入 </small>
                    </div>
                  </div>
                </div>
                <div class="">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                      <span class="label label-info pull-right">总</span>
                      <h5>录入</h5>
                    </div>
                    <div class="ibox-content">
                      <h1 class="no-margins" id="AllCounts"></h1>
                      <div class="stat-percent font-bold text-info">100% <i class="fa fa-bolt"></i>
                      </div>
                      <small class="stat-percent">总录入 </small>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
			
			</div>
			<div class="footer" style="z-index: 2;">
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

	<script src="js/plugins/jeditable/jquery.jeditable.js"></script>

	<!-- Data Tables -->
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="js/hplus.js?v=2.2.0"></script>
	<script src="js/plugins/pace/pace.min.js"></script>

	<!-- Page-Level Scripts -->
	<script>
    $(document).ready(function () {
      
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
            var box = $("#record");
            for(var i = 0; i < item.length; i ++){
            	if(i == 10 ){
            		break;
            	}
              var operate = "";
              switch(item[i].operate){
                case '0': 
                  operate = '登陆了广告管理系统';
                  break;
                case '1': 
                  operate = '录入了一条广告';
                  break;
                case '2': 
                  operate = '修改了一条广告';
                  break;
                case '3': 
                  operate = '发布了一条广告';
                  break;
                case '4': 
                  operate = '关闭了一条广告';
                  break;
              }
              var date = (new Date().getDay()) ;
              var txt = '<div class="feed-element">'+
                          '<div class="media-body">'+
                              '<span class="text-success">' + item[i].username + '</span> ' + operate + 
                              '<br><small class="text-muted">'+ item[i].date +'  ' + item[i].time +'</small>'+
                          '</div>'+
                      '</div>';
              box.append(txt);
      		}

            box.append('<a href="toRecord" class="block m-t">查看更多</a>');
          }
        }
      });

      $.ajax({
        type : "GET",
        url : "getFirstPagePara",
        dataType : "json",
        contentType : "application/json; charset=utf-8",
        data : {},
        success : function(data) {
          $("#MCounts").text(data.MCounts);
          $("#DCounts").text(data.DCounts);
          $("#AllCounts").text(data.AllCounts);
          $("#clickCounts").text(data.clickCounts);
          $("#revCounts").text(data.revCounts);
          $("#mnum").html((data.MCounts/data.AllCounts)*100+'% <i class="fa fa-bolt">');
          $("#dnum").html((data.DCounts/data.AllCounts)*100+'% <i class="fa fa-bolt">');

        }
      });
      
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
  </script>
</body>
</html>