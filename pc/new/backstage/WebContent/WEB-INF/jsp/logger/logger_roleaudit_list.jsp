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
                角色后台管理员审核分配   <!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">角色后台管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#"> 角色后台管理员审核分配</a></li>
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
                  <a  class="btn blue" href="insRoleAudit"><i class="icon-pencil"></i>新增 角色后台管理员审核分配</a>
                </div>
              </div>
              
              <div class="portlet-body">


                <div class=row_fluid>
                  <ul>
                    <li>分配类型:
                      <select id="audit_type"  style="width:102px;">
                        <option value="-1">请选择</option>
                        <option value="0">商家入驻</option>
                        <option value="1">商家变更</option>
                        <option value="2">商品审核</option>
                        <option value="3">纠错审核</option>
                      </select></li>

                    <li style="width:80px"><a class="btn btn-primary search_sub" style="margin-top:-9px;" onclick="queryinfo(1)">搜索</a></li>
                  </ul>
                </div>



                <!-- <div class="row-fluid ">
                  <div class="span2">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
                  <div class="span2"><a  class="btn btn-primary search_sub"
                              onclick="queryinfo(1)">搜索</a></div>


                </div> -->
              
              
              
              <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                
                  <table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

                    <thead>
                      <tr role="row">
                        <th>序号</th>
                        <th>新增/编辑ID</th>
                        <th>业务人员ID</th>
                        <th>审核用户ID</th>
                        <th>录入用户ID</th>
                        <th>检查用户ID</th>
                        <th>分配类型</th>
                        <th>分配对应值</th>
                        <th>分配增加时间</th>
                        <th>审核完成时间</th>
                        <th>审核结果</th>
                        <th>录入开始时间</th>
                        <th>录入完成时间</th>
                        <th>录入结果</th>
                        <th>检查完成时间</th>
                        <th>检查结果</th>
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
        menuact("05_02_04");
    });
  
  function queryinfo(index){
    
  if(index==0||index==""){
    index =1;
  }
  
  
  
  var ajaxdatas ="index="+index;
  
  if($("#audit_type").val()!="-1"){
    ajaxdatas+="&audit_type="+$("#audit_type").val();
  }
  
  
  $.ajax({
    type : "GET",
    url : "queryRoleAudit",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : ajaxdatas,
    success : function(freshdata) {
      console.log(freshdata);
      if(freshdata.status ==0){
        var page =freshdata.result;
        var pageindex =page.index;
        var totalcount =page.totalCount;
        var pagecount =page.pageCount;
        var data =page.dataList;


        
        var tablehtml="";
        $.each(data,function(i,item){
          var no =Number(i+1);
          var id =item.id;
          var add_user_id =item.add_user_id;
          var bus_user_id =item.bus_user_id;
          var audit_user_id =item.audit_user_id;
          var input_user_id =item.input_user_id;
          var check_user_id =item.check_user_id;
          var audit_type =item.audit_type;
          var audit_value =item.audit_value;
          var add_time =item.add_time;
          var audit_end_time =item.audit_end_time;
          var audit_result =item.audit_result;
          var input_begin_time =item.input_begin_time;
          var input_end_time =item.input_end_time;
          var input_result =item.input_result;
          var check_end_time =item.check_end_time;
          var check_result =item.check_result;
          switch (audit_type){
            case 0:
              audit_type="商家入驻"
              break
            case 1:
              audit_type="商家变更"
              break
            case 2:
              audit_type="商品审核"
              break
            case 3:
              audit_type="纠错审核"
              break
          }
           
           var rowhtml ="<tr>"+
           "<td>"+no+"</td>"+
           "<td>"+add_user_id+"</td>"+
           "<td>"+bus_user_id+"</td>"+
           "<td>"+audit_user_id+"</td>"+
           "<td>"+input_user_id+"</td>"+
           "<td>"+check_user_id+"</td>"+
           "<td>"+audit_type+"</td>"+
           "<td>"+audit_value+"</td>"+
           "<td>"+add_time+"</td>"+
           "<td>"+audit_end_time+"</td>"+
           "<td>"+audit_result+"</td>"+
           "<td>"+input_begin_time+"</td>"+
           "<td>"+input_end_time+"</td>"+
           "<td>"+input_result+"</td>"+
           "<td>"+check_end_time+"</td>"+
           "<td>"+check_result+"</td>"+
           "<td>"+"<a href='readRoleAudit?id="+id+"'>修改</a><a href='delRoleAudit?id="+id+"'> 删除</a>"+"</td>"+
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