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
<title>满集数据后台系统</title>
  <link href="media/css/jqpagination.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/style.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
  <link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
  <link rel="shortcut icon" href="media/image/favicon.ico" />



    <style type="text/css">
        .row-pd{
            padding:10px 0;
        }
    </style>
</head>

<body  class="page-header-fixed">
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
            <h3 class="page-title" style="margin:20px 0px 15px 0px">
                    属性列表<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">属性管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">属性列表</a></li>
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
                  <!-- <a  class="btn blue" href="insAttri"><i class="icon-pencil"></i>添加属性</a> -->
                </div>
              </div>
                   <div class="portlet-body">
               <div class="span2" style="display:none;">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
              
              
      <%--         <div class="row-fluid ">  
                <input type="hidden" id="state" value="${accountstate } ">
<!--                 <div class="span2">用户ID：<input type="text" id="useridstr"class="small m-wrap" ></div>
 -->                
                <div class="span2">
                <input type="hidden" id="categoryid">
                    <select id="firstcate" class="small m-wrap" >
                    </select>
                </div>
                <div class="span2">
                    <select id="secondcate" class="small m-wrap" >
                    </select>
                </div>
                <div class="span2">
                    <select id="thirdcate" class="small m-wrap" >
                    </select>
                </div>
               <div class="col-lg-4">
                   
              </div>
               
                
              </div> --%>
                 
              <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                  <table id="attritable" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                       <th>序号</th>
                       <th>名称</th>
                       <th>数值</th>
                       <th>是否必选</th>
                      </tr>
                    </thead>
                    <tbody id="tablebody">
                      
                    </tbody>
                  </table>
                    
                  <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <a class="btn btn-w-m btn-success" type="submit"
                          href="chgAttri?attri_id=${attri_id}">修 改</a>
                        <a class="btn btn-w-m btn-default" type="submit"
                          href="selAttri">返 回</a>
                      </div>
                    </div>
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
  



	<%-- <div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
		<div id="side-menu">
			<ul class="nav" >
				<li class="nav-header abc"></li>
				
				
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">品牌管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="selBrand">品牌列表</a></li>
						<li><a href="insBrand">新增品牌</a></li>
					</ul></li>
					
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">规格管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="selSpec">规格列表</a></li>
						<li><a href="insSpec">新增规格</a></li>
					</ul></li>
					
				<li class="active k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">属性管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li class="active"><a href="selAttri">属性列表</a></li>
						<li><a href="insAttri">新增属性</a></li>
					</ul></li>
				
				
				<li class="k2"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">用户组别管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li class="active"><a href="selUserGroup">用户组别列表</a></li>
						<li><a href="*">用户组别统计</a></li>
						<li><a href="*">用户组别调整</a></li>
						<li><a href="*">用户组别变化记录</a></li>
					</ul></li>

				<li class="k2"><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">用户安全</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">用户验证记录</a></li>
						<li><a href="*">用户安全状态</a></li>

					</ul></li>
					
					
				<li class="k11"><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">登录用户管理</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">登录用户列表</a></li>
						<li><a href="*">分配用户角色</a></li>

					</ul></li>
					
				<li class="k11"><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">权限管理</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">角色管理</a></li>
						<li><a href="*">权限管理</a></li>
						<li><a href="*">资源管理</a></li>
					</ul></li>
					
				<li class="k11"><a><i class="fa fa fa-globe"></i> <span
						class="nav-label">操作记录管理</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="*">操作记录查询</a></li>
						
					</ul></li>


				
			</ul>
			</div>
		</div>
		</nav>

		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
						href="#"><i class="fa fa-bars"></i> </a>
					<!-- <form role="search" class="navbar-form-custom" method="post"
						action="search_results.html"> -->
					<div class="row border-bottom">
						<div class="col-lg-9">
							<div class="form-group" style="width: 1000px">
								<ul class="nav navbar-nav menu_first">

									<li class="dropdown"><a  class="dropdown-toggle" onclick="onkey(1)"
										> 运维 </a></li>
									<li class="dropdown"><a  class="dropdown-toggle" onclick=""
										> 用户 </a></li>
									<li class="dropdown"><a   class="dropdown-toggle" onclick=""
										> 商家 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 代理</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 账户 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 交易 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 消息 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 日志</a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 监控 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 配置 </a></li>
									<li class="dropdown"><a class="dropdown-toggle" onclick=""
										> 登录 </a></li>

								</ul>

		

							</div>
						</div>
						
				
						
						<ul class="nav navbar-top-links navbar-right">
							<li><span>${sessionScope.username}</span></li>
							<li><a href="loginout"> <i class="fa fa-sign-out"></i>
									退出
							</a></li>
						</ul>

					</div>
				</div>


				</nav>
			</div>
			<div id="maincontent">
				<div class="row wrapper border-bottom white-bg page-heading">
					<div class="col-lg-10">
						<h2>属性详情</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a href="selAttri">属性管理</a></li>
							<li><strong>属性详情</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>

				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">

									<form class="form-horizontal">
										<div class="form-group">
											<label class="col-lg-2 control-label">分类：</label>
											<div class="row col-lg-6 spec_pt1">
												<div class="col-md-2">
													<strong><span id="catefirst"></span></strong>
												</div>
												<div class="col-md-1">
													<strong><span id="catesecond"></span></strong>
												</div>
												<div class="col-md-3">
													<strong><span id="catethird"></span></strong>
												</div>
											</div>
											
											<!-- <div class="col-lg-6 spec_pt1 "><span id="categoryinfo"></span></div> -->
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">其他规格：</label>
											<div class="col-lg-6 spec_pt1">
												<div style="display: block;">
													<table id="attritable" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th>序号</th>
																<th>名称</th>
																<th>数值</th>
																<th>是否必选</th>
															</tr>
														</thead>
														<tbody id="tablebody">
															
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<a class="btn btn-w-m btn-success" type="submit"
													href="chgAttri?attri_id=${attri_id}">修 改</a>
												<a class="btn btn-w-m btn-default" type="submit"
													href="selAttri">返 回</a>
											</div>
										</div>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div> --%>
	
	
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
		$(document).ready(function(){
			onkey(1);
			setcate();
			setattris();
		});
	 function onkey(key){
            /* var ul=key+" ul";*/
            var li=key+" ul li"; 

            $('#side-menu li').css("display","none");
            $('.abc').css("display","block");
            $('.k'+key).css("display","block");
            $('.k'+li).css("display","block"); 
            /* $('.k'+key).addClass("active");
            $('.k'+ul).addClass("in"); */
        }
	
		function setcate(){
			
			var c =${catagoryinfo };
			var cateinfo =c.first_name+"-"+c.second_name+"-"+c.third_name;
			
			$("#catefirst").html(c.first_name);
			$("#catesecond").html(c.second_name);
			$("#catethird").html(c.third_name);
		/* 	$("#categoryinfo").html(cateinfo); */
			
			
			
		};
		
		function setattris(){
			var list =${attris };
			console.log(list);
			var tablecont ="";
			$.each(list,function(i, attriitem) {
				var no =Number(i+1);
				var title =attriitem.title;
				var values =attriitem.values;
				var state ="否";
				if(title.indexOf("*")>=0){
					state="是";
					title =title.substring(1);
				}
				
				
				var newRow = "<tr>"+
				"<td>"+no+"</td>"+
				"<td>"+title+"</td>"+
				"<td>"+values+"</td>"+
				"<td>"+state+"</td>"+
				"</tr>";
				/* $("#attritable tr:last").after(newRow); */
				
				tablecont +=newRow;
				
			});
			$("#tablebody").html(tablecont);
		};
		
		
		
	</script>


	





</body>

</html>
