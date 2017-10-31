<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />

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
        .spec_add_img,.spec_add_other,.spec_update_img,.spec_update_other{
            display:none;
            width:500px;
            min-height: 260px;
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
				
				
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">品牌管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="selBrand">品牌列表</a></li>
						<li><a href="insBrand">新增品牌</a></li>
					</ul></li>
					
				<li class="active k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">规格管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ><a href="selSpec">规格列表</a></li>
						<li class="active"><a href="insSpec">新增规格</a></li>
					</ul></li>
					
				<li class="k1"><a><i class="fa fa-th-large"></i> <span
						class="nav-label">属性管理</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li class="active"><a href="selAttri">属性列表</a></li>
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
			<div id="maincontent ">
				<div class="row wrapper border-bottom white-bg page-heading">
					<div class="col-lg-10">
						<h2>添加规格</h2>
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>规格管理</a></li>
							<li><strong>添加规格</strong></li>
						</ol>
					</div>
					<div class="col-lg-2"></div> 	
				</div>

           

				<div class="wrapper wrapper-content animated fadeInRight">

  				<div class="ibox-content">
                   <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-lg-2 control-label">分类选择：</label>
                            <div class="col-lg-10 spec_pt1">
                            <input type="hidden" id="categoryid">
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
                            <label class="col-lg-2 control-label">图片规格：</label>
                            <div class="col-lg-8 spec_pt1">
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
                                <button class="btn btn-w-m btn-success ajax_btn" type="submit">保  存</button>
                                <button class="btn btn-w-m btn-default" type="submit" onclick="javascript:history.back();">返  回</button>
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
                                        <span class="btn btn-w-m btn-default close_add">返  回</span>
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
                                        <input type="text" class="form-control other_name_add" placeholder="请输入规格名称"> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">数值：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control other_num_add" placeholder="请输入规格值，多个值用“,”隔开"> 
                                    </div>
                                </div>                               
                                <div class="form-group">
                                    <div class="col-lg-offset-3 col-lg-9">
                                        <button class="btn btn-w-m btn-success addsave_other" type="submit">保  存</button>
                                        <span class="btn btn-w-m btn-default close_add">返  回</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--修改图片规格-->
                        <div class="spec_update_img">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">名称：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control img_name" value=""> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">数值：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control img_num" value=""> 
                                    </div>
                                </div>                         
                                <div class="form-group">
                                    <div class="col-lg-offset-3 col-lg-9">
                                        <button class="btn btn-w-m btn-success" id="save_update_img" type="submit">保  存</button>
                                        <span class="btn btn-w-m btn-default close_update">返  回</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--修改其他规格-->
                        <div class="spec_update_other">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">名称：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control other_name" value=""> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">数值：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control other_num" value="">
                                    </div>
                                </div>                         
                                <div class="form-group">
                                    <div class="col-lg-offset-3 col-lg-9">
                                        <button class="btn btn-w-m btn-success" id="save_update_other" type="submit">保  存</button>
                                        <span class="btn btn-w-m btn-default close_update">返  回</span>
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
		 $(document).ready(function () {
			
			onkey(1);
			firstcate();
			//规格添加、删除、编辑
			/*弹窗*/
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

          	//保存添加图片规格
            $('.addsave_img').click(function() {
                //var tr_num = $('.other_table').find('tr:eq(' + (trNuma) + ')').find('td:eq(1)').text();
                var tr_num = parseInt($(".img_table tr:last td:first").text());
                if(tr_num>0){tr_num=tr_num+1;}else{tr_num=1;}
                var img_name_add = $(".img_name_add").val();
                var img_num_add = $(".img_num_add").val();
                var tr = '<tr><td>' + tr_num + '</td><td>' + img_name_add + '</td><td>' + img_num_add + '</td><td><a class="open_update_img">编辑</a></td></tr>';
                $(".img_table").append(tr);
                $("img_table tr:eq(2)").after(tr);
                $(".spec_add_img").css("display","none");
            });
          	//保存添加其他规格
            $('.addsave_other').click(function() {
                //var tr_num = $('.other_table').find('tr:eq(' + (trNuma) + ')').find('td:eq(1)').text();
                var tr_num = parseInt($(".other_table tr:last td:first").text());
                if(tr_num>0){tr_num=tr_num+1;}else{tr_num=1;}
                var other_name_add = $(".other_name_add").val();
                var other_num_add = $(".other_num_add").val();
                var tr = '<tr><td>' + tr_num + '</td><td>' + other_name_add + '</td><td>' + other_num_add + '</td><td><a class="open_update_other">编辑</a>&nbsp;<a class="open_delete_other">删除</a></td></tr>';
                $(".other_table").append(tr);
                $("other_table tr:eq(2)").after(tr);
                $(".spec_add_other").css("display","none");
            });
            //修改图片规格
            $(".img_update").click(function() {
                //获取对应参数
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                var img_name = $('.img_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').text(); 
                var img_num = $('.img_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').text();
                $(".img_name").val(img_name);
                $(".img_num").val(img_num);
                $(".spec_update_img").css("display","block");
            });
            //保存修改图片规格
            $("#save_update_img").click(function() {
                var img_name_n = $(".img_name").val();
                var img_num_n = $(".img_num").val();
                $('.img_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').html(img_name_n);
                $('.img_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').html(img_num_n);
                $(".spec_update_img").css("display","none");
            });

            //修改其他规格
            $(".other_update").click(function() {
                //获取对应参数
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                var other_name = $('.other_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').text(); 
                var other_num = $('.other_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').text();
                $(".other_name").val(other_name);
                $(".other_num").val(other_num);
                $(".spec_update_other").css("display","block");
            });
            //修改其他属性值
            $("#save_update_other").click(function() {
                var other_name_n = $(".other_name").val();
                var other_num_n = $(".other_num").val();
                $('.other_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').html(other_name_n);
                $('.other_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').html(other_num_n);
                $(".spec_update_other").css("display","none");
            });
            $(".close_update").click(function() {
                $(".spec_update_img").css("display","none");
                $(".spec_update_other").css("display","none");
            });
            $(".close_add").click(function() {
                $(".spec_add_img").css("display","none");
                $(".spec_add_other").css("display","none");
            });
            /*弹窗*/            
            //删除图片规格列
            $('.delete_img').click(function() {
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                //alert(trNum);
                $('.img_table').find('tr:eq(' + (trNum) + ')').remove();
            });
            //删除其他规格列
            $('.delete_other').click(function() {
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                //alert(trNum);
                $('.other_table').find('tr:eq(' + (trNum) + ')').remove();
            });
            /*操作新添加的元素*/
            //修改图片规格
            $(".img_table").on("click",".open_update_img", function() {
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                //alert(trNum);
                var img_name = $('.img_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').text(); 
                var img_num = $('.img_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').text();
                $(".img_name").val(img_name);
                $(".img_num").val(img_num);
                $(".spec_update_img").css("display","block");
            });
            //修改其他规格
            $(".other_table").on("click",".open_update_other", function() {
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                //alert(trNum);
                var other_name = $('.other_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').text(); 
                var other_num = $('.other_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').text();
                $(".other_name").val(other_name);
                $(".other_num").val(other_num);
                $(".spec_update_other").css("display","block");
            });
            //删除图片规格
            $(".img_table").on("click",".open_delete_img", function() {
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                $('.img_table').find('tr:eq(' + (trNum) + ')').remove();
            });
            //删除其他规格
            $(".other_table").on("click",".open_delete_other", function() {
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                $('.other_table').find('tr:eq(' + (trNum) + ')').remove();
            });
            /*操作新添加的元素*/
            
            //生成json数据
            $(".ajax_btn").click(function() {
                var t_img = $(".img_table tr").length;
                var t_other = $(".other_table tr").length;
                //alert(t_other);
                var k="";
                var p="";
                var g=",";
                //图片规格
                for(i=1;i<t_img;i++)
                {
                    var name = $('.img_table').find('tr:eq(' + (i) + ')').find('td:eq(1)').text();
                    var num  = $('.img_table').find('tr:eq(' + (i) + ')').find('td:eq(2)').text();
                    k=k+'{"name":"*' + name +'","num":"' + num+'"}';
                    if(i<t_img-1)
                    {
                        k=k+g;
                    }
                }
                //其他规格
                for(j=1;j<t_other;j++)
                {
                    var name = $('.other_table').find('tr:eq(' + (j) + ')').find('td:eq(1)').text();
                    var num  = $('.other_table').find('tr:eq(' + (j) + ')').find('td:eq(2)').text();
                    p=p+'{"name":"' + name +'","num":"' + num+'"}';
                    if(j<t_other-1)
                    {
                        p=p+g;
                    }
                    //alert(k);
                }
                if(k){
                    json_img='"img_list":[' + k +']';
                }
                else{
                    json_img='"img_list":[]';
                }
                if(p){
                    json_other=',"other_list":[' + p +']';
                }
                else{
                    json_other=',"other_list":[]';
                }
                //alert(json_other);
               json_html='{'+json_img+json_other+'}'; 
               var jsonToStr=JSON.stringify(json_html)
            	//alert(json_html); 
                //$(".test_box").html(json_html);

                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "addSpec",
                    dataType : "json",
                    data:  {
                    	 category_id:$("#categoryid").val(),
                    	category_name:$("#thirdcate").val(), 
                    	json:json_html
                    	},
                    success: function (data) {
                        if (data.status ==0) {
                            location.reload(true);
                        }else if(data.status ==1)
                        {
                        	alert("添加失败。失败原因：该分类下已存在规格,请到规格修改中添加");
                       
                        }else if(date.status ==2){
                        	alert("添加失败。失败原因：图片规格必须有一个");
                        	
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
