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
                   系统管理菜单信息<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">管理员管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">系统管理菜单信息</a></li>
            </ul>
          </div>
        </div>

        <div class="row-fluid margin-bottom-20">

        </div>

        <div class="row-fluid">

          <div class="span12">

            <div class="portlet box grey">
              <div class="portlet-title">
                <div class="caption"> </div>
                <div class="actions">
                  <a  class="btn blue" href="insNavigation"><i class="icon-pencil"></i>新增系统管理菜单信息</a>
                </div>
              </div>
              
              <div class="portlet-body">


                <div class=row_fluid>
                  <ul>
                    <li>是否锁定:
                      <select id="is_lock"  style="width:102px;">
                        <option value="-1">请选择</option>
                        <option value="0">不锁定</option>
                        <option value="1">锁定</option>
                      </select></li>

                    <li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
                  </ul>
                </div>




                <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                
                  <table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

                    <thead>
                      <tr role="row">
                        <th>序号</th>
                        <th>自动编号</th>
                        <th>父级导航ID</th>
                        <th>所属频道ID</th>
                        <th>导航类别</th>
                        <th>导航表示</th>
                        <th>导航标题</th>
                        <th>导航副标题</th>
                        <th>图标地址</th>
                        <th>链接地址</th>
                        <th>排序数字</th>
                        <th>是否锁定</th>
                        <th>备注说明</th>
                        <th>权限资源</th>
                        <th>是否系统默认</th>
                        <th>操作</th>
                      </tr>
                    </thead>

                    <tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">
                    </tbody>
                    </table>
                  <div class="row-fluid">
                    <div class="span6">
                      <div class="dataTables_info" id="dividersummery"></div>
                    </div>
                    <div class="span6">
                      <div class="dataTables_paginate paging_bootstrap pagination" id="devider">
                        
                      </div>
                    </div>
                  </div>

                </div>
              </div>
            </div>
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
        queryinfo(1);
        menuact("05_04_06");
    });
  
  function queryinfo(index){
    
  if(index==0||index==""){
    index =1;
  }




    var ajaxdatas ="index="+index;


    if($("#is_lock").val()!='-1'){
      ajaxdatas+="&is_lock="+$("#is_lock").val();
    }




    $.ajax({
    type : "GET",
    url : "queryNavigation",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : ajaxdatas,
    success : function(freshdata) {
      
      if(freshdata.status ==0){
        var page =freshdata.result;
        var pageindex =page.index;
        var totalcount =page.totalCount;
        var pagecount =page.pageCount;
        var data =page.dataList;
        
        
        var tablehtml="";
        $.each(data,function(i,item){
          
          var no =Number(i+1);
          
          var id=item.id;
          var parent_id=item.parent_id;
          var channel_id=item.channel_id;
          var nav_type=item.nav_type;
          var name=item.name;
          var title=item.title;
          var sub_title=item.sub_title;
          var icon_url=item.icon_url;
          var link_url=item.link_url;
          var sort_id=item.sort_id;
          var is_lock=item.is_lock;
          var remark=item.remark;
          var action_type=item.action_type;
          var is_sys=item.is_sys;

          switch (is_sys){
            case 0:
              is_sys="否"
              break
            case 1:
              is_sys="是"
              break
          }

          switch (is_lock){
            case 0:
              is_lock="不锁定"
              break
            case 1:
              is_lock="锁定"
              break
          }




           var rowhtml ="<tr>"+
           "<td>"+no+"</td>"+
           "<td>"+id+"</td>"+
           "<td>"+parent_id+"</td>"+
           "<td>"+channel_id+"</td>"+
           "<td>"+nav_type+"</td>"+
           "<td>"+name+"</td>"+
           "<td>"+title+"</td>"+
           "<td>"+sub_title+"</td>"+
           "<td>"+icon_url+"</td>"+
           "<td>"+link_url+"</td>"+
           "<td>"+sort_id+"</td>"+
           "<td>"+is_lock+"</td>"+
           "<td>"+remark+"</td>"+
           "<td>"+action_type+"</td>"+
           "<td>"+is_sys+"</td>"+
           "<td>"+"<a href='readNavigation?id="+id+"'>修改</a><a href='delNavigation?id="+id+"'>删除</a>"+"</td>"+
           "</tr>";
          
          tablehtml+=rowhtml;
               
        })
        $("#tablebody").html(tablehtml);
        
        
        deviderset(totalcount,pagecount,pageindex);
      }else{
    	  alert("暂无数据！");
      }
      
      
      
    }
  
  });
  
  
  
  
    
  }
  
  function deviderset(totalcount,pagecount,pageindex){
    
    
    var summery ="共"+totalcount+"条数据--共"+pagecount+"页";
    $("#dividersummery").html(summery);
    
    var deviderhtml="";
    if(pageindex==1){
      deviderhtml+="<ul><li  class='prev ' style='display:none'><a  onclick='queryinfo("+Number(pageindex-1)+")'>← <span class='hidden-480'>Prev</span></a></li>";
    }else{
      deviderhtml+="<ul><li  id='firstdiv' class='prev '><a  onclick='queryinfo("+Number(pageindex-1)+")'>← <span class='hidden-480'>Prev</span></a></li>";
    }
    
    
    if(pagecount<6){
      
      for(var i=1;i<pageindex;i++){
        
        deviderhtml+="<li ><a onclick='queryinfo("+i+")'>"+i+"</a></li>";
        
      }
      
      
    }else{
      
      if(pageindex<3){
        for(var j=1;j<6;j++){
          
          deviderhtml+="<li ><a onclick='queryinfo("+j+")'>"+j+"</a></li>";
          
        }
        
      }else if(pageindex<=pagecount-2){
        
        var m =pageindex-2;
        for(var k=0;k<5;k++){
          
          deviderhtml+="<li ><a onclick='queryinfo("+m+")'>"+m+"</a></li>";
          m++;
        }
        
        
      }else if(pageindex>pagecount-2){
        var n =pageindex-4;
        for(var q=0;q<5;q++){
          deviderhtml+="<li ><a onclick='queryinfo("+n+")'>"+n+"</a></li>";
          n++;
        }
      }
      
    }
    

    if(pageindex ==pagecount){
      deviderhtml+="<li id='lastdiv ' style='display:none'><a  onclick='queryinfo("+Number(pageindex+1)+")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
    }else{
      deviderhtml+="<li id='lastdiv '><a  onclick='queryinfo("+Number(pageindex+1)+")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
    }
    
    $("#devider").html(deviderhtml);
  }

  </script>

</body>

</html>