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
        .attri_pt1{
            padding-top: 6px;
        }
        .attri_pt1 span{
            color:#0933D4;
            margin: 0 30px 0 20px;
        }
        .attri_add,.attri_update{
            display:none;
            width:400px;
            height: 300px;
            background: #fff;
            padding:30px 20px;
            border: 1px solid #ccc;
            position: relative;
            margin: 0 auto;     
            top:-300px;
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
				
				
				<li class="active k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">品牌管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="selBrand">品牌列表</a></li>
						<li class="active"><a href="insBrand">新增品牌</a></li>
					</ul></li>
					
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">规格管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="sel">规格列表</a></li>
						<li><a href="ins">新增规格</a></li>
					</ul></li>
					
				<li class="active k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">属性管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="selAttri">属性列表</a></li>
						<li class="active"><a href="insAttri">新增属性</a></li>
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
						<h2>添加属性</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a href="selAttri">属性管理</a></li>
							<li><strong>添加属性</strong></li>
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
										<input type="hidden" id="categoryid">
											<label class="col-lg-2 control-label">分类选择：</label>
											<div class="col-lg-10 attri_pt1">
												<div class="col-md-3">
													<select class="form-control m-b" id="firstcate">
													</select>
												</div>
												<div class="col-md-3">
													<select class="form-control m-b" id="secondcate">
													</select>
												</div>
												<div class="col-md-3">
													<select class="form-control m-b" id="thirdcate">
													</select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">属性添加：</label>
											<div class="col-lg-8 attri_pt1">
												<div style="display: block;">
													<table class="table table-bordered table_op">
														<thead>
															<tr>
																<th>序号</th>
																<th>名称</th>
																<th>数值</th>
																<th>必填</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody id="tablebody">
															
															
														</tbody>
													</table>
													<div>
														<a href="javascript:;" id="attri_add"><img
															src="img/u865.png"></a>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button class="btn btn-w-m btn-success ajax_btn" type="submit">保
													存</button>
												<button class="btn btn-w-m btn-default" type="submit"
													onclick="javascript:history.back();">返 回</button>
											</div>
										</div>
										
									</div>






									<div class="attri_add">
											<div class="form-horizontal">
												<div class="form-group">
													<label class="col-lg-3 control-label">名称：</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" id="addattriname">
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-3 control-label">数值：</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" id="addattrivalues">
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-3 control-label">是否必填：</label>
													<div class="col-lg-6">
														<input type="checkbox" value="0" id="addattristatus">
													</div>
												</div>
												<div class="form-group">
													<div class="col-lg-offset-3 col-lg-9">
														<a class="btn btn-w-m btn-success" onclick="addAttri()">保
															存</a>
														<span class="btn btn-w-m btn-default close_add">返
															回</span>
													</div>
												</div>
											</div>
										</div>
										<div class="attri_update">
											<div class="form-horizontal">
												<div class="form-group">
													<label class="col-lg-3 control-label">名称：</label>
													<div class="col-lg-6">
														<input type="text" class="form-control op_name">
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-3 control-label">数值：</label>
													<div class="col-lg-6">
														<input type="text" class="form-control op_num">
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-3 control-label">必填：</label>
													<div class="col-lg-6">
														<input type="checkbox" class="op_ckeck" value="">
													</div>
												</div>
												<div class="form-group">
													<div class="col-lg-offset-3 col-lg-9">
														<button class="btn btn-w-m btn-success save_update" type="submit">保
															存</button>
														<span class="btn btn-w-m btn-default close_update">返
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
	
		$(document).ready(function(){
			onkey(1);
			firstcate();
			
				$('#attri_add').click(function(){
		            $(".attri_add").css("display","block");
		         });
				
				$(".attri_update_a").click(function() {
		           $(".attri_update").css("display","block");
		       });
			$(".close_add").click(function() {
		           $(".attri_add").css("display","none");
		       });
		       $(".close_update").click(function() {
		           $(".attri_update").css("display","none");
		       });
		       
		     	//修改属性
	            $(".updAttri").click(function() {
	                //获取对应参数
	                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
	                var op_name = $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').text(); 
	                var op_num = $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').text();
	                var op_check = $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(3)').find('input');
		            hasChk = op_check.is(':checked');
		            //alert(hasChk);
		            if(hasChk){
		                $(".op_ckeck").prop("checked",true);
		            }
		            else
		            {
		                $(".op_ckeck").prop("checked",false);
		            }
	                $(".op_name").val(op_name);
	                $(".op_num").val(op_num);	                
	                $(".attri_update").css("display","block");
	            });
	            //保存修改属性
	            $(".save_update").click(function() {
	                var op_name_n = $(".op_name").val();
	                var op_num_n = $(".op_num").val();
	                $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').html(op_name_n);
	                $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').html(op_num_n);
	              	//修改复选框
		            if($(".op_ckeck").prop("checked")){
		                $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(3)').find('input').prop("checked",true);
		            }else{
		                $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(3)').find('input').prop("checked",false);
		            }
	                $(".attri_update").css("display","none");
	            });
	          //删除属性
	            $('.delAttri').click(function() {
	                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
	                //alert(trNum);
	                $('.table_op').find('tr:eq(' + (trNum) + ')').remove();
	            });
		       /*操作新添加的元素*/
	            //修改属性
	            $(".table_op").on("click",".open_update", function() {
	            	//获取对应参数
	                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
	                var op_name = $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').text(); 
	                var op_num = $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').text();
	                var op_check = $('.table_op').find('tr:eq(' + (trNum) + ')').find('td:eq(3)').find('input');
		            hasChk = op_check.is(':checked');
		            //alert(hasChk);
		            if(hasChk){
		                $(".op_ckeck").prop("checked",true);
		            }
		            else
		            {
		                $(".op_ckeck").prop("checked",false);
		            }
		            
	                $(".op_name").val(op_name);
	                $(".op_num").val(op_num);	                
	                $(".attri_update").css("display","block");
	            });
	          	//删除属性
	            $(".table_op").on("click",".open_delete", function() {
	                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
	                $('.table_op').find('tr:eq(' + (trNum) + ')').remove();
	            });
	            /*操作新添加的元素*/
	          //生成json数据
	            $(".ajax_btn").click(function() {
	                var t_oper = $(".table_op tr").length;
	                //alert(t_other);
	                var k="";
	                var g=";";
	               	//循环属性
	                for(i=1;i<t_oper;i++)
	                {
	                    var name = $('.table_op').find('tr:eq(' + (i) + ')').find('td:eq(1)').text();
	                    var num  = $('.table_op').find('tr:eq(' + (i) + ')').find('td:eq(2)').text();
	                    var check = $('.table_op').find('tr:eq(' + (i) + ')').find('td:eq(3)').find('input');
	    	            hasChk = check.is(':checked');
	    	            //alert(hasChk);
	    	            if(hasChk){
	    	                name= "*"+ name;
	    	            }
	    	            
	                    k+=name +':' + num + ";";
	                    
	                }
	                //alert(json_other);
	               //json_html='{'+json_img+json_other+'}'; 
	               //var jsonToStr=JSON.stringify(json_html)
	            	//alert(k); 
	                //$(".test_box").html(json_html);
	                //var id =${attri_id };
	                $.ajax({
	                    type: "GET",
	                    contentType: "application/json",
	                    url: "addAttri",
	                    dataType : "json",
	                    data:  {
	                    	category_id:$("#categoryid").val(), 
	                    	content:k
	                    	},
	                    success: function (data) {
	                        if (data.status==0) {
	                        	location.reload(true);     
	                            //window.location.reload();
	                        }
	                    }
	                })
	            });
			
		});
		
		
		 function firstcate(){
				$("#firstcate").prepend("<option value='0'>全部一级分类</option>"); 
				$("#secondcate").prepend("<option value='0'>全部二级分类</option>"); 
				$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
				$.ajax({
					type : "GET",
					url : "firstCategory",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : [],
					success : function(data) {
						var catelist =data.result;
						$.each(catelist,function(i, cateitem) {
							var id =cateitem.id;
							var title =cateitem.title;
							$("#firstcate").append("<option value='"+id+"'>"+title+"</option>"); 
						});
						
					}});
			};
			
			$("#firstcate").change(function(){
				$("#secondcate").empty();
				$("#secondcate").prepend("<option value='0'>全部二级分类</option>"); 
				$("#thirdcate").empty();
				$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
				var fcateid =$("#firstcate").val();
				$("#categoryid").val(fcateid);
				
				$.ajax({
					type : "GET",
					url : "secondCategory",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : {id:$("#categoryid").val()},
					success : function(data) {
						var catelist =data.result;
						$.each(catelist,function(i, cateitem) {
							var id =cateitem.id;
							var title =cateitem.title;
							$("#secondcate").append("<option value='"+id+"'>"+title+"</option>"); 
						});
						/* $("#secondcate").prepend("<option value='0'>全部二级分类</option>");  */
					}});
				
			});
			
			$("#secondcate").change(function(){
				$("#thirdcate").empty();
				$("#thirdcate").prepend("<option value='0'>全部三级分类</option>"); 
				var scateid =$("#secondcate").val();
				$("#categoryid").val(scateid);
				
				$.ajax({
					type : "GET",
					url : "secondCategory",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : {id:$("#categoryid").val()},
					success : function(data) {
						var catelist =data.result;
						$.each(catelist,function(i, cateitem) {
							var id =cateitem.id;
							var title =cateitem.title;
							$("#thirdcate").append("<option value='"+id+"'>"+title+"</option>"); 
						});
						/* $("#secondcate").prepend("<option value='0'>全部二级分类</option>");  */
					}});
				
			});
			
			$("#thirdcate").change(function(){
				
				var tcateid =$("#thirdcate").val();
				$("#categoryid").val(tcateid);
				
			});
			
			
			function addAttri(){
				var trSeq = $("#tablebody tr").length;
				
				var no =Number(trSeq+1);
				var name   =$("#addattriname").val();
				var values =$("#addattrivalues").val();
				
				if($("#addattristatus").prop("checked")){
	                var checke='<input type="checkbox"  checked disabled>';
	            }else{
	            	var checke='<input type="checkbox" disabled>';
	            }
				
				$("input:checkbox:checked").val()
				//alert(name);
				//alert(values);
				var rowstr ="<tr>"+
							"<td>"+no+"</td>"+
							"<td>"+name+"</td>"+
							"<td>"+values+"</td>"+
							"<td>"+checke+"</td>"+
							"<td>"+"<a class='open_update'>编辑</a>&nbsp;<a class='open_delete'>删除</a>"+"</td>"+
							"</tr>";
							
							
				$("#tablebody").append(rowstr); 
				
				$("#addattriname").val("");
				$("#addattrivalues").val("");
				$(".attri_add").css("display","none");
				
			};
			
			
			function updAttri(obj){
				
				
				
				
			}
			
			function delAttri(obj){
				$()
				
				
			}
			
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
