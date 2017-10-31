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
                    菜单信息<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">导航信息</a><i class="icon-angle-right"></i></li>
              <li><a href="#">菜单信息</a></li>
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
                  <a  class="btn blue" href="insMenu"><i class="icon-pencil"></i>新增菜单信息</a>
                </div>
              </div>
              
              <div class="portlet-body">
              <div class="row-fluid ">
                <div class="span2">标题：<input type="text" id="title" class="small m-wrap" ></div>
                <div class="span2">类别：
                	<select class="m-wrap small" id="type">
						<option value=""></option>
						<option value="PC">PC</option>
						<option value="WAP">WAP</option>
						<option value="APP">APP</option>
						<option value="APP_ACT">APP_ACT</option>
						<option value="APP_HOTEL_CATEGORY">APP_HOTEL_CATEGORY</option>
					</select>
                </div>
                <div class="span2">启用状态：
                	<select class="m-wrap small" id="status">
						<option value="0">停用</option>
						<option value="1">启用</option>
					</select>
                </div>
                <div class="span2"><a  class="btn btn-primary search_sub"
                            onclick="queryinfo(1)">搜索</a></div>
                 
                
              </div>
              
              <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                
                  <table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

                    <thead>
                      <tr role="row">
                        <th>序号</th>
                        <th>自动编号</th>
                        <th>标题</th>
                        <th>图标</th>
                        <th>链接</th>
                        <th>类别</th>
                        <th>排序数字</th>
                        <th>显示地区</th>
                        <th>是否启用</th>
                        <th>站点编号</th>
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
        menuact("04_01_01");
    });
  
  function queryinfo(index){
    
  if(index==0||index==""){
    index =1;
  }
  ajaxdatas ="index="+index;
  if($("#title").val() != ""){
	  ajaxdatas+="&title="+$("#title").val();
  }
  if($("#type").val()!=""){
    ajaxdatas+="&type="+$("#type").val();
  }
  if($("#status").val()!=""){
    ajaxdatas+="&status="+$("#status").val();
  }
  $.ajax({
    type : "GET",
    url : "queryMenu",
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
          var title=item.title;
          var icon=item.icon;
          var url=item.url;
          var type=item.type;
          var sort_id=item.sort_id;
          var show_area=item.show_area;
          var status=item.status;
          var site_id=item.site_id;
		
          var areaStr = '';
          switch(show_area){
        	case 0:
        		areaStr = "主站";
        		break;
        	case 1:
        		areaStr = "省市";
        		break;
        	case 2:
        		areaStr = "区县";
        		break;
        	case 3:
        		areaStr = "乡镇";
        		break;
        	default:
        		areaStr = "";
        }
          switch(status){
          	case 0:
          		status = "停用";
          		break;
          	case 1:
          		status = "启用";
          		break;
          }
           var rowhtml ="<tr>"+
           "<td>"+no+"</td>"+
           "<td>"+id+"</td>"+
           "<td>"+title+"</td>"+
           "<td><img style='width:100px' src="+icon+"></td>"+
           "<td>"+url+"</td>"+
           "<td>"+type+"</td>"+
           "<td>"+sort_id+"</td>"+
           "<td>"+areaStr+"</td>"+
           "<td>"+status+"</td>"+
           "<td>"+site_id+"</td>"+
           "<td>"+"<a href='readMenu?id="+id+"'>修改</a>&nbsp<a href='delMenu?id="+id+"'>删除</a>"+"</td>"+
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