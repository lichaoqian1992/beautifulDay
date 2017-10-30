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
								内容扩展属性		<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
							<li><a href="#">发布信息</a><i class="icon-angle-right"></i></li>
							<li><a href="#">内容扩展属性</a></li>
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
									<a  class="btn blue" href="insArticleAttributeField"><i class="icon-pencil"></i>新增内容扩展属性</a>
								</div>
							</div>
							
							<div class="portlet-body">
							<div class="row-fluid ">
			                <div class="span2">编辑器类型：
			                	<select class="m-wrap small" id="editor_type">
			                		<option value=""></option>
									<option value="0">标准型</option>
									<option value="1">简洁型</option>
								</select>
			                </div>
			                <div class="span2">是否必填：
			                	<select class="m-wrap small" id="is_required">
			                		<option value=""></option>
									<option value="0">非必填</option>
									<option value="1">必填</option>
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
												<td>列名</td>
												<td>标题</td>
												<td>控件类型</td>
												<td>字段类型</td>
												<td>字段长度</td>
												<td>小数点位数</td>
												<td>选项列表</td>
												<td>默认值</td>
												<td>是否必填</td>
												<td>是否密码框</td>
												<td>是否允许HTML</td>
												<td>编辑器类型</td>
												<td>验证提示信息</td>
												<td>验证失败提示</td>
												<td>验证正则表达式</td>
												<td>排序数字</td>
												<td>系统默认</td>

												

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
		    queryinfo(1);
		    menuact("04_03_04");
		});
	
	function queryinfo(index){
		
	if(index==0||index==""){
		index =1;
	}
	
	
	
	ajaxdatas ="index="+index;
	
	if($("#is_required").val()!=""){
		ajaxdatas+="&is_required="+$("#is_required").val();
	}
	if($("#editor_type").val()!=""){
		ajaxdatas+="&editor_type="+$("#editor_type").val();
	}
	
	$.ajax({
		type : "GET",
		url : "queryArticleAttributeField",
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
					var	name=item.name;
					var	title=item.title;
					var	control_type=item.control_type;
					var	data_type=item.data_type;
					var	data_length=item.data_length;
					var	data_place=item.data_place;
					var	item_option=item.item_option;
					var	default_value=item.default_value;
					var	is_required=item.is_required;
					var	is_password=item.is_password;
					var	is_html=item.is_html;
					var	editor_type=item.editor_type;
					var	valid_tip_msg=item.valid_tip_msg;
					var	valid_error_msg=item.valid_error_msg;
					var	valid_pattern=item.valid_pattern;
					var	sort_id=item.sort_id;
					var	is_sys=item.is_sys;

					switch(is_required){
			          	case 0:
			          		is_required = "非必填";
			          		break;
			          	case 1:
			          		is_required = "必填";
			          		break;
			          }
					switch(editor_type){
			          	case 0:
			          		editor_type = "标准型";
			          		break;
			          	case 1:
			          		editor_type = "简洁型";
			          		break;
			          }
					
					 var rowhtml ="<tr>"+
					 "<td>"+no+"</td>"+
					 "<td>"+name+"</td>"+
					 "<td>"+title+"</td>"+
					 "<td>"+control_type+"</td>"+
					 "<td>"+data_type+"</td>"+
					 "<td>"+data_length+"</td>"+
					 "<td>"+data_place+"</td>"+
					 "<td>"+item_option+"</td>"+
					 "<td>"+default_value+"</td>"+
					 "<td>"+is_required+"</td>"+
					 "<td>"+is_password+"</td>"+
					 "<td>"+is_html+"</td>"+
					 "<td>"+editor_type+"</td>"+
					 "<td>"+valid_tip_msg+"</td>"+
					 "<td>"+valid_error_msg+"</td>"+
					 "<td>"+valid_pattern+"</td>"+
					 "<td>"+sort_id+"</td>"+
					 "<td>"+is_sys+"</td>"+


					 "<td>"+"<a href='readArticleAttributeField?id="+id+"'>修改</a><br><a href='delArticleAttributeField?id="+id+"'>删除</a>"+"</td>"+
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