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
                    全国地区站点信息<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">站点配置</a><i class="icon-angle-right"></i></li>
              <li><a href="#">全国地区站点信息</a></li>
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
                  <a  class="btn blue" href="insApoSite"><i class="icon-pencil"></i>新增全国地区站点信息</a>
                </div>
              </div>
              
              <div class="portlet-body">
	              <div class=row_fluid>
					<ul>
						<li>站点名称：<input type="text" id="name" class="small m-wrap" ></li>
					   	<li>服务状态：
							<select  id="shop_status"	 style="width:102px">
								<option value="-1">不限</option>
								<option value="0">关闭</option>
								<option value="1">开启</option>
							</select>
						</li>
					   	<li>站点状态：
							<select  id="status"	 style="width:102px">
								<option value="-1">不限</option>
								<option value="0">未生效</option>
								<option value="1">已生效</option>
								<option value="2">已关闭</option>
							</select>
						</li>
						
						<li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
					</ul>	
				</div>	
              
              
              
              <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                
                  <table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

                    <thead>
                      <tr role="row">
                        <th>序号</th>
                        <th>自动编号</th>
                        <th>站点名称</th>
                        <th>站点绑定地区</th>
                        <th>站点绑定地区编码</th>
                        <th>站点地区级别</th>
                        <th>站点上级编号</th>
                        <th>站点状态</th>
                        <th>站点关闭原因</th>
                        <th>站点服务状态</th>
                        <th>站点商家阈值</th>
                        <th>当前站点商家数</th>
                        <th>站点网址</th>
                        <th>站点添加时间</th>
                        <th>背景图片</th>
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
        menuact("03_01_01");
        queryinfo(1);
    });
  
  function queryinfo(index){
    
  if(index==0||index==""){
    index =1;
  }
  
  
  
  ajaxdatas ="index="+index;
  
  if($("#name").val()!=""){
    ajaxdatas+="&name="+$("#name").val();
  } 
  if($("#shop_status").val()!=""){
    ajaxdatas+="&shop_status="+$("#shop_status").val();
  } 
  
  if($("#status").val()!=""){
    ajaxdatas+="&status="+$("#status").val();
  } 
  
  
  $.ajax({
    type : "GET",
    url : "queryApoSite",
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
          var name=item.name;
          var area=item.area;
          var area_code=item.area_code;
          var area_level=item.area_level;
          var parent_id=item.parent_id;
          var status=item.status;
          var remark=item.remark;
          var shop_status=item.shop_status;
          var shop_min_number=item.shop_min_number;
          var shop_number=item.shop_number;
          var url=item.url;
          var add_time=item.add_time;
          var site_banner=item.site_banner;
          var stateStr = "";
	      	switch (status) {
	      	case 0:
	      	  stateStr = "未生效";
	      	  break;
	      	case 1:
	      	  stateStr = "已生效";
	      	  break;
	      	case 2:
	      	  stateStr = "已关闭";
	      	  break;
	      	default:
	      	  stateStr = "";
	      	}
          var shopStr = "";
          	switch (shop_status) {
	      	case 0:
	      		shopStr = "关闭";
	      	  break;
	      	case 1:
	      		shopStr = "开启";
	      	  break;
	      	default:
	      		shopStr = "";
	      	}
           var rowhtml ="<tr>"+
           "<td>"+no+"</td>"+
           "<td>"+id+"</td>"+
           "<td>"+name+"</td>"+
           "<td>"+area+"</td>"+
           "<td>"+area_code+"</td>"+
           "<td>"+area_level+"</td>"+
           "<td>"+parent_id+"</td>"+
           "<td>"+stateStr+"</td>"+
           "<td>"+remark+"</td>"+
           "<td>"+shopStr+"</td>"+
           "<td>"+shop_min_number+"</td>"+
           "<td>"+shop_number+"</td>"+
           "<td>"+url+"</td>"+
           "<td>"+add_time+"</td>"+
           "<td>"+site_banner+"</td>"+
           "<td>"+"<a href='readApoSite?id="+id+"'>修改</a><a href='delApoSite?id="+id+"'>删除</a>"+"</td>"+
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