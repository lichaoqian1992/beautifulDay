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
    <link href="js/plugins/layer/skin/layer.css" rel="stylesheet">
    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

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
                    <li class="active">
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
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>广告位管理</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="toMain">主页</a>
                        </li>   
                        <li>
                            <strong>广告位列表</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
    
                </div>
            </div>
            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            
                            <div class="row adds-header ibox-content form-horizontal">
                                <div class="row adds-header form-horizontal">
                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">广告位ID：</label>
                                            <div class="col-sm-8">
                                                <input type="text" maxlength="2" name="" id="pl_id_search" class="form-control" placeholder="id为纯数字">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">终端：</label>
                                            <div class="col-sm-8">
                                                <input type="text" name="" id="channel_search" class="form-control" placeholder="PC端为0，APP端为1">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">类型：</label>
                                            <div class="col-sm-8">
                                                <input type="text" maxlength="1" id="type_search" name="" class="form-control" placeholder="请输入0-8的数字">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" id="searchPl" class="btn btn-primary pull-right">查询</button>
                                        <!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newPlace">新增</button> -->
                                    </div>
                                </div>
                                
                                <table class="table table-striped table-bordered table-hover " id="placeList">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>广告位类型</th>
                                            <th>广告方式</th>
                                            <th>图片数量</th>
                                            <th>终端类型</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
           <!--  <div class="modal inmodal" id="newPlace" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span>
                           </button>
                           <h4 class="modal-title">新增用户</h4>
                       </div>
                       <small class="font-bold">
                           <div class="modal-body">
                               <form class="form-horizontal m-t" id="signupForm" novalidate="novalidate">
                                   <div class="form-group">
                                       <label class="col-sm-3 control-label">姓氏：</label>
                                       <div class="col-sm-8">
                                           <input id="firstname" name="firstname" class="form-control" type="text">
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label class="col-sm-3 control-label">名字：</label>
                                       <div class="col-sm-8">
                                           <input id="lastname" name="lastname" class="form-control" type="text" aria-required="true" aria-invalid="false">
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label class="col-sm-3 control-label">用户名：</label>
                                       <div class="col-sm-8">
                                           <input id="username" name="username" class="form-control" type="text" aria-required="true" aria-invalid="true">
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label class="col-sm-3 control-label">密码：</label>
                                       <div class="col-sm-8">
                                           <input id="password" name="password" class="form-control" type="password">
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label class="col-sm-3 control-label">确认密码：</label>
                                       <div class="col-sm-8">
                                           <input id="password" name="password" class="form-control" type="password">
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label class="col-sm-3 control-label">E-mail：</label>
                                       <div class="col-sm-8">
                                           <input id="email" name="email" class="form-control" type="email">
                                       </div>
                                   </div>
                               </form>
                           </div>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                               <button type="submit" class="btn btn-primary">保存</button>
                           </div>
                       </small>
                   </div>
                   <small class="font-bold">
                   </small>
               </div>
               <small class="font-bold">
               </small>
           </div> -->
            <div class="modal inmodal" id="updatePlace" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span>
                            </button>
                            <h4 class="modal-title">修改广告位</h4>
                        </div>
                        <small class="font-bold">
                            <div class="modal-body">
                                <form class="form-horizontal m-t" id="updateForm" novalidate="novalidate" method="POST" action="updPlace">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">广告位：</label>
                                        <div class="col-sm-8">
                                            <input id="pl_id" name="pl_id" disabled class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">终端：</label>
                                        <div class="col-sm-8">
                                            <input id="channel" class="form-control" disabled type="text" aria-required="true" aria-invalid="false">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">广告位类型：</label>
                                        <div class="col-sm-8">
                                            <input id="type" name="type" class="form-control" type="text" aria-required="true" aria-invalid="false">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">广告方式：</label>
                                        <div class="col-sm-8">
                                            <input id="display" name="display" class="form-control" type="text" aria-required="true" aria-invalid="false">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">图片数量：</label>
                                        <div class="col-sm-8">
                                            <input id="number" name="number" class="form-control" type="text">
                                        </div>
                                    </div>
                                   
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">状态：</label>
                                        <div class="col-sm-8">
                                            <input id="flag" name="flag" class="form-control" type="text">
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <ul class="unstyled col-sm-offset-3">
                                        <li>类型：
                                            <ul>
                                                <li>0==>主页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1==>餐饮美食</li>
                                                <li>2==>休闲娱乐&nbsp;&nbsp;&nbsp;&nbsp;3==>酒店住宿</li>
                                                <li>4==>旅游观光&nbsp;&nbsp;&nbsp;&nbsp;5==>日用百货</li>
                                                <li>6==>外卖服务&nbsp;&nbsp;&nbsp;&nbsp;7==>农村电商</li>
                                                <li>8==>便民生活</li>
                                            </ul>
                                        </li>
                                        <li>方式：0==>一张&nbsp;&nbsp;&nbsp;&nbsp;1==>轮播</li>
                                        <li>终端：0==>PC&nbsp;&nbsp;&nbsp;&nbsp;1==>APP</li>
                                        <li>状态：0==>关&nbsp;&nbsp;&nbsp;&nbsp;1==>开</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary" id="updateSubmit" data-dismiss="modal">保存</button>
                            </div>
                        </small>
                    </div>
                    <small class="font-bold">
                    </small>
                </div>
                <small class="font-bold">
                </small>
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


    <!-- Data Tables -->
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/hplus.js?v=2.2.0"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
    <!-- layer javascript -->
    <script src="js/plugins/layer/layer.min.js"></script>
    

    <!-- Page-Level Scripts -->
    <script>
    
        $(document).ready(function () {
            var placeData = [];
            getList({});
            var oTable =$('#placeList');
            $("#searchPl").click(function(){
                var query = {
                    pl_id: $('#pl_id_search').val(),
                    channel: $("#channel_search").val(),
                    type: $("#type_search").val()
                }
                getList(query);
            })
            $("#placeList").on('click',".newAdds",function(){

            });
            //弹出修改广告位
            $("#placeList").on('click',".updatebtn",function(){
                var id = Number($(this).parent().parent().find('td').first().text().split(" ")[0]);
                $.ajax({
                    type : "GET",
                    url : "queryPlace",
                    dataType : "json",
                    contentType : "application/json; charset=utf-8",
                    data : {
                        "pl_id": id,
                        "channel": 0
                    },
                    success : function(data) {
                        if(data.errCode == 0){
                            var data = data.result;
                            console.log(data)
                            $("#pl_id").val(data.pl_id);
                            $("#pl_id1").val(data.pl_id);
                            $("#type").val(data.type);
                            $("#display").val(data.display);
                            $("#number").val(data.number);
                            $("#channel").val(data.channel);
                            $("#channel1").val(data.channel);
                            $("#flag").val(data.flag);
                        }
                        
                    }
                });
            });
            
            //弹出修改广告位
            
            $("#updateSubmit").click(function(){
            console.log($("#flag").val())
                $.ajax({
                    type : "get",
                    url : "updPlace",
                    dataType : "json",
                    contentType : "application/json; charset=utf-8",
                    data : {
                        "pl_id": $("#pl_id").val(),
                        "channel": $("#channel").val(),
                        "type": $("#type").val(),
                        "display" : $("#display").val(),
                        "number" : $("#number").val(),
                        "flag" : $("#flag").val()
                    },
                    success : function(data) {
                        if(data.errCode == 0){
                            layer.alert(data.result, 9);
                        }else{
                            layer.alert(data.errInfo, 8);
                        }
                        getList();
                    }
                });
                
            });
            
            // 更行表格
            function updateTable(){
                oTable.dataTable({
                    "destroy": true,
                    "data": placeData
                });
            }
            
            // 加载全部电脑端数据
            function getList(query){
                $.ajax({
                    type : "GET",
                    url : "queryPlaces",
                    dataType : "json",
                    contentType : "application/json; charset=utf-8",
                    data : query,
                    success : function(data) {
                        if(data.errCode == 0){
                            
                            var data = data.result;
                            placeData = [];
                            for(var i = 0; i < data.length; i++){
                                var type = "";
                                switch (data[i].type){
                                    case "0": 
                                        type = "主页";
                                    break;
                                    
                                    case "1": 
                                        type = "餐饮美食";
                                    break;
                                    
                                    case "2": 
                                        type = "休闲娱乐";
                                    break;
                                    
                                    case "3": 
                                        type = "酒店住宿";
                                    break;
                                    
                                    case "4": 
                                        type = "旅游观光";
                                    break;
                                    
                                    case "5": 
                                        type = "日用百货";
                                    break;
                                    
                                    case "6": 
                                        type = "外卖服务";
                                    break;
                                    
                                    case "7": 
                                        type = "农村电商";
                                    break;
                                    
                                    case "8": 
                                        type = "便民生活";
                                    break;
                                }
                                var channel = data[i].channel == 0 ? "PC" : "APP";
                                var display = data[i].display == 1 ? "轮播" : "一张";
                                var flag = data[i].flag == 0 ? "关" : "开";
                                var id = data[i].pl_id.length == 1 ? "0"+data[i].pl_id : data[i].pl_id;
                                var txt = [
                                             id+' 号位',
                                             type,
                                             display,
                                             data[i].number,
                                             channel,
                                             flag,
                                             '<button type="button" data-toggle="modal" data-target="#updatePlace" class="un-margin btn btn-xs btn-outline btn-info updatebtn">修改</button>&nbsp;&nbsp;&nbsp;&nbsp;<form action="toAddAddsFromPlace" method="GET" style="display:inline;"><input type="hidden" name="pl_id" value="'+data[i].pl_id+'"><input type="hidden" name="channel" value="'+data[i].channel+'"><button type="submit" class="un-margin btn btn-xs btn-outline btn-primary newAdds">新增</button></form>'
                                ];
                                placeData.push(txt);
                            }
                        }
                        updateTable();
                    }
                });
            }
            
            
            
        });
        
        
        function submit(id){
            document.getElementById(id).submit();
        }
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
    </script>

</body>

</html>
    