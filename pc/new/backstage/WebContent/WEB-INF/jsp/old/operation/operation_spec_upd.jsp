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

<link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
<link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
<link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=2.2.0" rel="stylesheet">
<link href="js/plugins/layer/skin/layer.css" rel="stylesheet">
<script src="js/jquery-2.1.1.min.js"></script>


<style type="text/css">
        .row-pd{
            padding:10px 0;
        }
        .spec_pt1{
            padding-top: 6px;
        }
        .spec_pt1 span{
            color:#0933D4;
            margin: 0 30px 0 20px;
        }
        .spec_add_img,.spec_update,.spec_update_img,.spec_add_other{
            display:none;
            width:600px;
            height: min-height;
            background: #fff;
            padding:30px 20px;
            border: 1px solid #ccc;
            position: relative;
            margin: 0 auto;     
            top:-200px;
        }
        .spec_update
        {
        	max-height:450px;        	
        	overflow:auto;
        }
        .returnsave,.returnsave_b,.returnsave_a{
        	color:#78FF00 !important;
        	float:right;
        	display:none;
        }
    </style>

</head>

<body>
	<div id="wrapper">
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
					
				<li class="active k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">规格管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li class="active"><a href="selSpec">规格列表</a></li>
						<li><a href="insSpec">新增规格</a></li>
					</ul></li>
					
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">属性管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="selAttri">属性列表</a></li>
						<li><a href="insAttri">新增属性</a></li>
					</ul></li>
				
				
				<li class="k2"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">用户组别管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="selUserGroup">用户组别列表</a></li>
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
						
						<script>
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
						<h2>修改规格</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>规格管理</a></li>
							<li><strong>修改规格</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div>
				</div>






				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="col-lg-2 control-label">分类：</label>
											<div class="col-lg-10 spec_pt1">
												<div class="col-lg-6 spec_pt1"><span id="categoryinfo"></span></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">图片规格：</label>
											<div class="col-lg-6 spec_pt1">
												<div style="display: block;">
													<table class="table table-bordered table-hover img_table">
														<thead>
															<tr>
																<th>序号</th>
																<th>名称</th>
																<th>数值</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody id="pictablebody">
															
														</tbody>
													</table>
													<div>
				                                       	<a href="javascript:;" id="spec_add_img"><img src="img/u865.png"></a>
				                                   	</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">其他规格：</label>
											<div class="col-lg-8 spec_pt1">
												<div style="display: block;">
													<table class="table table-bordered table-hover other_table">
														<thead>
															<tr>
																<th>序号</th>
																<th>名称</th>
																<th>数值</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody id="tablebody">
															
														</tbody>
													</table>
													<div>
				                                        <a href="javascript:;" id="spec_add_other"><img src="img/u865.png"></a>
				                                    </div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<a class="btn btn-w-m btn-success" type="submit" href="readSpec?category_id=${category_id } ">保
													存</a>
												<button class="btn btn-w-m btn-default" type="submit"
													onclick="javascript:history.back();">返 回</button>
											</div>
										</div>								
										
									</div>
									<!--添加图片规格-->
			                        <div class="spec_add_img">
			                            <div class="form-horizontal">
			                                <div class="form-group">
			                                    <label class="col-lg-3 control-label">名称：</label>
			                                    <div class="col-lg-7">
			                                        <input type="text" class="form-control img_name_add" placeholder="请输入规格名称"> 
			                                    </div>
			                                </div>
			                                <div class="form-group">
			                                    <label class="col-lg-3 control-label">数值：</label>
			                                    <div class="col-lg-7">
			                                        <input type="text" class="form-control img_num_add" placeholder="请输入规格值，多个值用“,”隔开"> 
			                                    </div>
			                                </div>                               
			                                <div class="form-group">
			                                    <div class="col-lg-offset-3 col-lg-9">
			                                        <button class="btn btn-w-m btn-success addsave_img" type="submit">保  存</button>
			                                        <span class="btn btn-w-m btn-default close_addu">返  回</span>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                        <!--添加其他规格-->
			                        <div class="spec_add_other">
			                            <div class="form-horizontal">
			                                <div class="form-group">
			                                    <label class="col-lg-3 control-label">名称：</label>
			                                    <div class="col-lg-7">
			                                        <input type="text" class="form-control name_add_other" placeholder="请输入规格名称"> 
			                                    </div>
			                                </div>
			                                <div class="form-group">
			                                    <label class="col-lg-3 control-label">数值：</label>
			                                    <div class="col-lg-7">
			                                        <input type="text" class="form-control num_add_other" placeholder="请输入规格值，多个值用“,”隔开"> 
			                                    </div>
			                                </div>                               
			                                <div class="form-group">
			                                    <div class="col-lg-offset-3 col-lg-9">
			                                        <button class="btn btn-w-m btn-success addsave_other" type="submit">保  存</button>
			                                        <span class="btn btn-w-m btn-default close_addu">返  回</span>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
									<!-- 弹出修改其他规格 -->
									<div class="spec_update" >
											<div class="form-horizontal">
												<div class="form-group">
													<label class="col-lg-3 control-label">规格名称：</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" id="upd_spec_name">
														<input type="hidden" class="form-control" id="upd_spec_id">
													</div>
													<div class="col-lg-3">
														<a class="ajaxsave_othername">修改</a><span class='returnsave_a'>操作成功！</span>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-3 control-label">规格值：</label>
													<div class="col-lg-9">														
														<table class="table table-bordered table-hover up_table">
														<thead>
															<tr>
																<th>序号</th>
																<th>规格值</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody id="specupdtable">
															
														</tbody>
													</table>
													<div>
					                                    <a href="javascript:;" id="other_upload_add"><img src="img/u865.png"></a>
					                                </div>	
													</div>
												</div>
												<div class="form-group">
													<div class="col-lg-offset-3 col-lg-9">
														
														<span class="btn btn-w-m btn-default " onclick="closeupdate()">返
															回</span>
													</div>
												</div>
											</div>
										</div>
										<!-- 弹出修改图片规格 -->
										<div class="spec_update_img" >
											<div class="form-horizontal">
												<div class="form-group">
													<label class="col-lg-2 control-label">规格名称：</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" id="upd_spec_name_img">
														<input type="hidden" class="form-control" id="upd_spec_id_img">
														
													</div>
													<div class="col-lg-2" style="line-height:34px;">
														<a class="ajaxsave_imgname">修改</a>
													</div>
													<div class="col-lg-2" style="line-height:34px;">
														<span class="returnsave_b">操作成功！</span>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label">规格值：</label>
													<div class="col-lg-9">
														
														<table class="table table-bordered table-hover up_table_img">
														<thead>
															<tr>
																<th>序号</th>
																<th>规格值</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody id="specupdtable_img">
															
														</tbody>
													</table>
													<div>
					                                    <a href="javascript:;" id="other_upload_add_img"><img src="img/u865.png"></a>
					                                </div>	
													</div>
												</div>
												<div class="form-group">
													<div class="col-lg-offset-3 col-lg-9">
														
														<span class="btn btn-w-m btn-default close_img_up">返
															回</span>
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
	</div>
	
	<script>
	
		$(document).ready(function (){
			onkey(1);
			setcateinfo();
			settableinfo();
			//添加图片规格
            $('#spec_add_img').click(function() {
            	var tr_key=$('.img_table').find('tr:eq(1)').find('td:eq(0)').text();
            	//alert(tr_key);
            	if(tr_key>0)
           		{
           			alert("图片规格只能有一个！");
           			return false;
           		}
            	else{
            		$(".spec_add_img").css("display","block");
            	}
            });
			//添加其他规格
            $('#spec_add_other').click(function() {
            	$(".spec_add_other").css("display","block");
            });
			//关闭添加
            $(".close_addu").click(function() {
                $(".spec_add_img").css("display","none");
                $(".spec_add_other").css("display","none");
                $(".spec_update_img").css("display","none");
            });
          	//关闭图片修改
            $(".close_img_up").click(function() {
                $(".spec_update_img").css("display","none");
            });
          	//添加新的图片属性值
            $("#other_upload_add_img").click(function() {
            	var tr_num = parseInt($(".up_table_img tr:last td:first").text());
                //alert(tr_num);
                //var tr_num=parseInt(tr_num);
                if(tr_num>0){tr_num=tr_num+1;}else{tr_num=1;}
            	var dd ="<tr>"+
						"<td>"+tr_num+"</td>"+
						"<td>"+"<input type='text' value=''></td>"+
						"<td>"+"<a class='ajaxsave_imgvalue_new'>保存修改</a><span class='returnsave'>操作成功！</span>"+"</td>"+
						"</tr>";
                $(".up_table_img").append(dd);
                $("up_table_img tr:eq(2)").after(dd);
                
            });
          	//添加新的属性值
            $("#other_upload_add").click(function() {
            	var tr_num = parseInt($(".up_table tr:last td:first").text());
                //alert(tr_num);
                //var tr_num=parseInt(tr_num);
                if(tr_num>0){tr_num=tr_num+1;}else{tr_num=1;}
            	var dd ="<tr>"+
						"<td>"+tr_num+"</td>"+
						"<td>"+"<input type='text' value=''></td>"+
						"<td>"+"<a class='ajaxsave_value_new'>保存修改</a><span class='returnsave'>操作成功<span>"+"</td>"+
						"</tr>";
                $(".up_table").append(dd);
                $("up_table tr:eq(2)").after(dd);
            });
            /*提交图片规格*/
            //提交新增图片规格
            $(".addsave_img").click(function() {
            	var cateid =${category_id };
            	//alert($(".img_name_add").val()+":"+$(".img_num_add").val())
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "addMainSpec",
                    dataType : "json",
                    data:  {
                    	name:"*"+$(".img_name_add").val(),
                    	values:$(".img_num_add").val(),
                    	categoryid:cateid
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                            location.reload(true);
                        }
                    }
                })
            });
          	//提交新增其他规格
            $(".addsave_other").click(function() {
            	var cateid =${category_id };
            	//alert($(".name_add_other").val()+":"+$(".num_add_other").val())
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "addMainSpec",
                    dataType : "json",
                    data:  {
                    	name:$(".name_add_other").val(),
                    	values:$(".num_add_other").val(),
                    	categoryid:cateid
                    	},
                    success: function (data) {
                    	if (data.status == 0) {
                            location.reload(true);
                        }
                    }
                })
            });
          	//提交保存图片规格名称
            $(".ajaxsave_imgname").click(function() {
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                
                $('.returnsave_b').css("display","block"); 
            	//$('.spec_update_img').find('tr:eq(' + (trNum) + ')').find('td:eq(4)').find('span').css("display","block");
            	var title="*"+$("#upd_spec_name_img").val();
               $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "updSpec",
                    dataType : "json",
                    data:  {
                    	id:$("#upd_spec_id_img").val(),
                    	title:title
                    	},
                    success: function (data) {
                        if (data == 'true') {
                            alert("success!");
                            //window.location.reload();
                        }
                    }
                })
            });
          	//提交保存图片规格数值(已有的)           
            $(".spec_update_img").on("click",".ajaxsave_imgvalue", function() {
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                
                var id = $('.up_table_img').find('tr:eq(' + (trNum) + ')').find('td:eq(0)').find('input').val(); 
                var title = $('.up_table_img').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').find('input').val();
                $('.up_table_img').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').find('span').css("display","block");
                //alert(id+title);
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "updSpec",
                    dataType : "json",
                    data:  {
                    	id:id,
                    	title:title
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                            
                        }else{
                        	alert("修改失败");
                        }
                    }
                })
            });
          	//提交保存图片规格数值(新增的)           
            $(".spec_update_img").on("click",".ajaxsave_imgvalue_new", function() {
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                 
                var title = $('.up_table_img').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').find('input').val();
                $('.up_table_img').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').find('span').css("display","block");
                //alert($("#upd_spec_id_img").val()+title);
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "addSecondSpec",
                    dataType : "json",
                    data:  {
                    	spec_id:$("#upd_spec_id_img").val(),
                    	title:title
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                            
                        }else{
                        	alert("修改失败");
                        }
                    }
                })
            });
          	//提交删除图片规格数值
            $(".spec_update_img").on("click",".ajaxdel_imgvalue", function() {
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                 
            	var id = $('.up_table_img').find('tr:eq(' + (trNum) + ')').find('td:eq(0)').find('input').val();
                //alert(id);
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "delSecondSpec",
                    dataType : "json",
                    data:  {
                    	spec_id:id
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                            
                        }else{
                        	alert("删除失败");
                        }
                    }
                })
            });
          	/*提交其他规格*/
          	//提交保存其他规格名称
            $(".ajaxsave_othername").click(function() {
            	//alert($("#upd_spec_id").val()+":"+$("#upd_spec_name").val());
				
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "updSpec",
                    dataType : "json",
                    data:  {
                    	id:$("#upd_spec_id").val(),
                    	title:$("#upd_spec_name").val()
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                            //alert("success!");
                            //window.location.reload();
                        	$('.returnsave_a').css("display","block");
                        }else{
                        	alert("修改失败");
                        }
                    }
                })
            });
          	//提交保存其他规格数值(已有的)           
            $(".spec_update").on("click",".ajaxsave_value", function() {
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                
                var id = $('.up_table').find('tr:eq(' + (trNum) + ')').find('td:eq(0)').find('input').val(); 
                var title = $('.up_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').find('input').val();
                $('.up_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').find('span').css("display","block");
                //alert(id+title);
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "updSpec",
                    dataType : "json",
                    data:  {
                    	id:id,
                    	title:title
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                           
                        }else{
                        	alert("修改失败");
                        }
                    }
                })
            });
          	//提交保存图片规格数值(新增的)           
            $(".spec_update").on("click",".ajaxsave_value_new", function() {
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                 
                var title = $('.up_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').find('input').val();
                $('.up_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').find('span').css("display","block");
                //alert($("#upd_spec_id").val()+title);
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "addSecondSpec",
                    dataType : "json",
                    data:  {
                    	spec_id:$("#upd_spec_id").val(),
                    	title:title
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                           
                          
                        }else{
                        	
                        }
                    }
                })
            });
          	//提交删除其他规格数值
            $(".spec_update").on("click",".ajaxdel_value", function() {
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                 
            	var id = $('.up_table').find('tr:eq(' + (trNum) + ')').find('td:eq(0)').find('input').val();
                //alert(id);
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "delSecondSpec",
                    dataType : "json",
                    data:  {
                    	spec_id:id
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                            alert("success!");
                            //window.location.reload();
                        }else{
                        	
                        }
                    }
                })
            });
          	//提交删除其他规格
            $(".other_del").click(function() {
            	var cateid =${category_id };
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                 
            	var id = $('.other_table').find('tr:eq(' + (trNum) + ')').find('td:eq(0)').find('input').val();
                //alert(id);
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "delMainSpec",
                    dataType : "json",
                    data:  {
                    	spec_id:id	,
                    	category_id:cateid
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                            //alert("success!");
                            location.reload();
                        }else{
                        	
                        }
                    }
                })
            });
		});
			
		
		function settableinfo(){
			
			var specs =${speclist };
			var picspec ="";
			var nonepicspecs="";
			var tablestr ="";
			
			$.each(specs,function(i, specitem) {
				
				if(!specitem.name.indexOf("*")){
					picspec =specitem;
					/* alert(specitem.name); */
				}else{
					
					var no =Number(i+1);
					var id =specitem.id;
					var name =specitem.name;
					var secondspeclist =specitem.specList;
					
					var specstr ="";
					$.each(secondspeclist,function(j, secondspecitem) {
						if(j!=0){
							specstr+=",";
						}
						specstr+=secondspecitem.title;
					});				
					
					var itemstr=JSON.stringify(specitem);
					var rowstr ="<tr>"+
								"<td>"+no+"<input type='hidden' value='"+ id +"'></td>"+
								"<td>"+name+"</td>"+
								"<td>"+specstr+"</td>"+
								"<td>"+"<a onclick='updspec(this)' data='"+itemstr+"' class='btn '>修改</a><a class='other_del'>删除</a>"+
								"</tr>";								
					tablestr +=rowstr;
				} 
			});
			$("#tablebody").html(tablestr);
			
			if(picspec!=""){
				
				var no =1;
				var id =picspec.id;
				var name =picspec.name;
				var name=name.replace("*","");
				var secondpicspeclist =picspec.specList;
				var picspecstr ="";
				$.each(secondpicspeclist,function(k, secondpicspecitem) {
					if(k!=0){
						picspecstr+=",";
					}
					picspecstr+=secondpicspecitem.title;
				});
				
				var picitemstr=JSON.stringify(picspec);
				var picrowstr ="<tr>"+
				"<td>"+no+"</td>"+
				"<td>"+name+"</td>"+
				"<td>"+picspecstr+"</td>"+
				"<td>"+"<a onclick='updspecimg(this)' data='"+picitemstr+"' class='btn '>修改</a>"+
				"</tr>";
				
				$("#pictablebody").html(picrowstr);
				
			};
		};
		//修改图片规格
		function updspecimg(obj){
			var data =$(obj).attr("data");
			var item =JSON.parse(data);
			var itemname=item.name;
			var itemname=itemname.replace("*","");
			//alert(item.name);	
			$("#upd_spec_name_img").val(itemname);
			$("#upd_spec_id_img").val(item.id);			
			var itemlist=item.specList;
			var tablehtml="";
			$.each(itemlist,function(k,seconditem){
			
				var no =Number(k+1);
				var id =seconditem.id;
				var name =seconditem.title;
				//alert(name);
				var name=name.replace("*","");
				var updrow ="<tr>"+
							"<td>"+no+"<input type='hidden' value='"+ id +"'></td>"+
							"<td>"+"<input type='text' value='"+name+"'></td>"+
							"<td><a class='ajaxsave_imgvalue'>保存修改</a>&nbsp;<a class='ajaxdel_imgvalue'>删除</a><span class='returnsave'>操作成功！</span></td>"+
							"</tr>";
				tablehtml+=updrow;			
			});
			$("#specupdtable_img").html(tablehtml);
			 $(".spec_update_img").css("display","block");
		};
		//修改其他规格
		function updspec(obj){
			var data =$(obj).attr("data");
			var item =JSON.parse(data);

			$("#upd_spec_id").val(item.id);
			$("#upd_spec_name").val(item.name);
			
			var itemlist=item.specList;
			var tablehtml="";
			$.each(itemlist,function(k,seconditem){
			
				var no =Number(k+1);
				var id =seconditem.id;
				var name =seconditem.title;

				var updrow ="<tr>"+
							"<td>"+no+"<input type='hidden' value='"+ id +"'></td>"+
							"<td>"+"<input type='text' value='"+name+"'></td>"+
							"<td>"+"<a class='ajaxsave_value'>保存修改</a>"+"&nbsp;<a class='ajaxdel_value'>删除</a><span class='returnsave'>操作成功！</span></td>"+
							"</tr>";
				tablehtml+=updrow;
			
			});
			$("#specupdtable").html(tablehtml);
			 $(".spec_update").css("display","block");
		};
		
		
		function closeupdate(){			
			 $(".spec_update").css("display","none");
			 location.reload();
		}
		
		function setcateinfo(){
			
			var cateinfo =${catagorymap };
			var catestr =cateinfo.firstName+"—"+cateinfo.secondName+"—"+cateinfo.thirdName;

			$("#categoryinfo").html(catestr);
			
		};
		
		$('#spec_add').on('click', function(){
            $.layer({
                 type: 1,                    
                 shade: [0],//遮罩透明度                    
                 area: ['auto', 'auto'],
                 offset:['100px', ''],
                 title: false,
                 border: [0],                    
                 page: {dom : '.spec_add'}
             });
         });
        
         $(".spec_update_a").click(function() {
             $(".spec_update").css("display","block");
         });
         $(".close_update").click(function() {
             $(".spec_update").css("display","none");
         });
		
	</script>


	<!-- Mainly scripts -->

	<script src="js/bootstrap.min.js?v=3.4.0"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="js/hplus.js?v=2.2.0"></script>
	<script src="js/plugins/pace/pace.min.js"></script>

	<!-- jQuery UI -->
	<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

	<!-- GITTER -->
	<script src="js/plugins/gritter/jquery.gritter.min.js"></script>

	<!-- EayPIE -->
	<script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>

	<!-- Sparkline -->
	<script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

	<!-- Sparkline demo data  -->
	<script src="js/demo/sparkline-demo.js"></script>
	
	<script src="js/plugins/layer/layer.min.js"></script>





</body>

</html>
