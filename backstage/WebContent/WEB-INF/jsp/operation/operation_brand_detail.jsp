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
					<jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true" />
				</div>
				<div class="page-content">
				    <div class="container-fluid">
				         <div class="row-fluid">
					          <div class="span12">
				            <h3 class="page-title" style="margin: 20px 0px 15px 0px">
				                                                                                        品牌库
				              <!-- <small>正常账户列表</small> -->
				            </h3>
				            <ul class="breadcrumb">
				              <li><i class="icon-home"></i> <a href="toMain">主页</a><i
				                class="icon-angle-right"></i></li>
				              <li><a href="#">品牌管理</a><i class="icon-angle-right"></i></li>
				              <li><a href="selBrand"> 品牌库</a></li>
				            </ul>
				          </div>
					        </div>
					        <div class="row-fluid margin-bottom-20">

                  </div>
                  <div class="row-fluid">
                     <div class="span12">
                        <div class="portlet box grey">
													<div class="portlet-title">
														<div class="caption"></div>
														<div class="actions">
															<!-- <a  class="btn blue" href="insAttri"><i class="icon-pencil"></i>添加属性</a> -->
														</div>
													</div>
													<div class="portlet-body">
													   <%-- <div class="span2" style="display:none;">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
																<div id="sample_2_wrapper"
																	class="dataTables_wrapper form-inline" role="grid">
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
																				href="chgAttri?attri_id=${attri_id}">修 改</a> <a
																				class="btn btn-w-m btn-default" type="submit"
																				href="selAttri">返 回</a>
																		</div>
																	</div>
																	<div class="row-fluid">
																		<div class="span6">
																			<div class="dataTables_info" id="dividersummery"></div>
																		</div>
																		<div class="span6">
																			<div class="dataTables_paginate paging_bootstrap pagination"
																				id="devider"></div>
																		</div>
																	</div>
								
																</div> --%>
																
																
																<div class="wrapper wrapper-content animated fadeInRight">

																	<div class="ibox-content">
																		<form class="form-horizontal">
																			<div class="form-group">
																			  <div class="row-fluid">
																					<label class="span2 control-label">品牌名称：</label>
																					<div class="span6">
																						<div class="form-control">
																							<span id="brandname"></span>
																						</div>
																					</div>
																				</div>
																			</div>
																			<div class="form-group">
																			  <div class="row-fluid">
																						<label class="span2 control-label">品牌LOGO：</label>
																						<div class="span6" id="brandlogo"></div>
																			  </div>
																			</div>
																			<div class="form-group">
																			  <div class="row-fluid">
																				  <label class="span2 control-label">类别设置：</label>
																					<div class="span6">
																						<div class="">
									
																							<input type="radio" checked="checked"
																								value="0" name="brand_type"><span>国 内</span>
																							<input type="radio" value="1"
																								name="brand_type" disabled="disabled"><span>国 外</span>&nbsp;
																						</div>
																				  </div>
																				</div>
																			</div>
																			<div class="form-group">
																			   <div class="row-fluid">
																						<label class="span2 control-label">品牌介绍：</label>
																						<div class="span6">
																							<p id="brandintro"></p>
																						</div>
																			   </div>
																			</div>
																			<div class="form-group">
																				<div class="col-lg-offset-2 col-lg-10" style="margin-left:17%">
																					<a class="btn btn-w-m btn-success"
																						href="chgBrand?id=${brand_id } ">修改</a> <a
																						class="btn btn-w-m btn-default" type="submit"
																						href="selBrand">返 回</a>
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
		$(document).ready(function(){
			onkey(1);
			menuact("03_05_01");
			var brand =${branditem };
			var name =brand.name;
			var logo =brand.logo;
			var type =brand.brand_type;
			var intro =brand.introduction;
			
			
			$("#brandname").html(name);
			$("#brandlogo").html("<img src='"+logo+" ' width='180' height='80'/>");
			
			if(0==type){
				$("input[name='brand_type'][value=0]").attr("checked",true); 
			}else if(1==type){
				$("input[name='brand_type'][value=1]").attr("checked",true); 
			}
			
			$("#brandintro").html(intro);
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
    
	</script>








</body>

</html>
