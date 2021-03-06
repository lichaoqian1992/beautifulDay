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
                    安卓APP版本管理<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">版本管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">安卓APP版本管理</a></li>
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
                  <a  class="btn blue" href="insAppVersion"><i class="icon-pencil"></i>新增</a>
                </div>
              </div>
              
              <div class="portlet-body">
              <div class="row-fluid ">
               <!--  <div class="span2">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
                <div class="span2"><a  class="btn btn-primary search_sub"
                            onclick="queryinfo(1)">搜索</a></div>
                 -->
                
              </div>
              
              
              
              <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                
                  <table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

                    <thead>
                      <tr role="row">
                        <th>序号</th>
                        <th>自动编号</th>
                        <th>版本号</th>
                        <th>下载链接</th>
                        <th>上传时间</th>
                        <th>类型</th>
                        <th>是否强制更新</th>
                        <th>更新次数</th>
                        <th>用户类型</th>
                        <th>操作</th>
                      </tr>
                    </thead>

                    <tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">
                    </tbody>
                    </table>
                 <jsp:include page="/WEB-INF/jsp/pager.html" flush="true"/>  

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
        setactive("151");
    });
  
  function queryinfo(index){
    
  if(index==0||index==""){
    index =1;
  }
  
  
  
  ajaxdatas ="index="+index;
  
  if($("#userid").val()!=""){
    ajaxdatas+="&user_id="+$("#userid").val();
  }
  
  
  $.ajax({
    type : "GET",
    url : "queryAppVersion",
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
          var ver_name=item.ver_name;
          var ver_url=item.ver_url;
          var rel_datetime=item.rel_datetime;
          var ver_category=item.ver_category;
          var is_up=item.is_up;
          var count=item.count;
          var type=item.type;

          
           
           var rowhtml ="<tr>"+
           "<td>"+no+"</td>"+
           "<td>"+id+"</td>"+
           "<td>"+ver_name+"</td>"+
           "<td>"+ver_url+"</td>"+
           "<td>"+rel_datetime+"</td>"+
           "<td>"+ver_category+"</td>"+
           "<td>"+is_up+"</td>"+
           "<td>"+count+"</td>"+
           "<td>"+type+"</td>"+
           "<td>"+"<a href='readAppVersion?id="+id+"'>修改</a><a href='delAppVersion?id="+id+"'>删除</a>"+"</td>"+
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
  
  
  </script>

</body>

</html>