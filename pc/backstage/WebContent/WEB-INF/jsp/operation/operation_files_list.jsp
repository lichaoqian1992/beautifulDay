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
                    上传文件列表<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">上传文件管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">上传文件列表</a></li>
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
                  <a  class="btn blue" href="insFiles"><i class="icon-pencil"></i>新增上传文件</a>
                </div>
              </div>
              
              <div class="portlet-body">
              
              <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                
                  <table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

                    <thead>
                      <tr role="row">
                        <th>序号</th>
                        <th>文件名称</th>
                        <th>文件路径</th>
                        <th>文件存储服务器</th>
                        <th>文件上传时间</th>
                        <th>上传用户ID</th>
                        <th>被使用次数</th>
                        <th>文件状态</th>
                        <th>文件类型</th>
                        <th>后缀名</th>
                        <th>存储物理路径</th>
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
        onkey(1);
        queryinfo(1);
        setactive("191");
    });
  
  function queryinfo(index){
    
  if(index==0||index==""){
    index =1;
  }
  
  
  
  ajaxdatas ="index="+index;

  $.ajax({
    type : "GET",
    url : "queryFiles",
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
          var file_name=item.file_name;
          var file_path=item.file_path;
          var file_md5=item.file_md5;
          var file_server=item.file_server;
          var file_uptime=item.file_uptime;
          var file_upuser=item.file_upuser;
          var file_usetimes=item.file_usetimes;
          var file_state=item.file_state;
          var file_type=item.file_type;
          var file_endwith=item.file_endwith;
          var file_fullpath=item.file_fullpath;

          
           
           var rowhtml ="<tr>"+
           "<td>"+no+"</td>"+
           "<td>"+file_name+"</td>"+
           "<td>"+file_path+"</td>"+
           "<td>"+file_server+"</td>"+
           "<td>"+file_uptime+"</td>"+
           "<td>"+file_upuser+"</td>"+
           "<td>"+file_usetimes+"</td>"+
           "<td>"+file_state+"</td>"+
           "<td>"+file_type+"</td>"+
           "<td>"+file_endwith+"</td>"+
           "<td>"+file_fullpath+"</td>"+
           "<td>"+"<a href='readFiles?id="+id+"'>修改</a><br><a href='delFiles?id="+id+"'>删除</a>"+"</td>"+
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