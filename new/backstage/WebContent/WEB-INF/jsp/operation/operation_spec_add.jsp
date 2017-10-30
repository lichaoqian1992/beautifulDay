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
        .spec_pt1{
            padding-top: 6px;
        }
        .spec_pt1 span{
            color:#0933D4;
            margin: 0 30px 0 20px;
        }
        .spec_add_img,.spec_add_other,.spec_update_img,.spec_update_other{
            /* display:none; */
            width:500px;
            min-height: 260px;
            background: #fff;
            padding:30px 20px;
            border: 1px solid #000;
            position: relative;
            margin: 0 auto;     
            top:-300px;
        }
        .form-group{
        overflow:hidden;
        zoom:1;
        }
       input.form-control.other_name_add,input.form-control.other_num_add,input.form-control.img_name_add,input.form-control.img_num_add{
       		height:28px;
       }
    </style>


</head>

<body class="page-header-fixed">
   <div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container-fluid">

        <a class="brand" href="index.html">
        <img src="media/image/logo.png" alt="logo" />
        </a>
        <div class="navbar hor-menu hidden-phone hidden-tablet" style="display:block">
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
                                    内容派生的商品规格信息
              <!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">发布信息</a><i class="icon-angle-right"></i></li>
              <li><a href="selSpec"> 内容派生的商品规格信息</a></li>
            </ul>
          </div>
        </div>
        <div class="row-fluid margin-bottom-20">

        </div>
        <div class="row-fluid">
          <div class="span12">
             <div class="portlet box  grey">
                <div class="portlet-title" style="height:30px">
                <!-- <div class="caption">
                  <span class="hidden-480">新增用户信息</span>
                </div> -->
              </div>
              
              <div class="portlet-body form">
                <div class="tabbable portlet-tabs">
                  <ul class="nav nav-tabs">
                    <li><a href="#portlet_tab3" data-toggle="tab">&nbsp;</a></li>
                  </ul>
                  <div class="tab-content">
                    <div class="tab-pane active" id="portlet_tab1">
                    
                    </div>
                    
                       <div class="form-horizontal">
                       <div class="form-group">
                       	<div class="row-fluid">
                            <label class="span1 ">分类选择：</label>
                            <input type="hidden" id="categoryid1">
                            <div class="span2">
                                <select class="form-control m-b" id="firstcate">
                                </select>
                            </div>
                            <div class="span2">
                                <select class="form-control m-b" id="secondcate">
                                </select>
                            </div>
                            <div class="span2">
                                <select class="form-control m-b" id="thirdcate">
                                </select>
                            </div>
                        </div>
                        </div>
                        <br>
                        <div class="form-group">
                         
                            <label class="">图片规格：</label>
                            <div class="col-lg-8 spec_pt1">
                                <div style="display: block;">
                                    <table class="table table-bordered table-hover img_table">
                                        <thead>
                                            <tr>
                                                <th>规格名称</th>
                                                <th>操作</th>  
                                            </tr>
                                        </thead>
                                        <tbody id="pictablebody">
                                            
                                        </tbody>
                                    </table>
                                    <div>
                                        <a href="javascript:;" id="spec_add_img">添加图片规格</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <label class="">其他规格：</label>
                            <div class="col-lg-8 spec_pt1">
                                <div style="display: block;">
                                    <table class="table table-bordered table-hover other_table">
                                        <thead>
                                            <tr>
                                                <th>规格名称</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tablebody">
                                            
                                        </tbody>
                                    </table>
                                    <div>
                                        <a href="javascript:;" id="spec_add_other">添加其他规格</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <button class="btn btn-w-m btn-success ajax_btn" type="submit">保  存</button>
                                <button class="btn btn-w-m btn-default" type="submit" onclick="javascript:history.back();">返  回</button>
                            </div>
                        </div>
                        </div>
                    <!--添加图片规格-->
                        <div class="spec_add_img" style="display:none">
                            <div class="form-horizontal">
                                <form action="addMainSpec" method="post" enctype="multipart/form-data">
                              			<div class="form-group row-fluid">
                                          <label class="col-lg-3 control-label" style="margin-left:5%">名称：</label>
                                          <input name="categoryid" id="categoryid" type="text">
                                          <input name="type" value="main" type="hidden">
                                          <input type="text" name="name" class="form-control img_name_add" placeholder="请输入规格名称"> 
                                      </div><br><br>  
                                      <div class="form-group row-fluid" style="margin-left:5%">
                                          <label class="col-lg-3 control-label">数值：</label>
                                          <input type="text" name="values" class="form-control img_num_add" placeholder="请输入规格值，多个值用“,”隔开"> 
                                      </div><br><br> 
                                      <div class="form-group row-fluid" style="margin-left:5%">
                                          <label class="col-lg-3 control-label">图片：</label>
                                          <input type="file" name="file" multiple> 
                                      </div><br><br>                           
                                      <div class="form-group" style="margin-left:-16%">
                                          <div class="col-lg-offset-3 text-center">
                                              <button class="btn  btn-success addsave_img" type="submit">保  存</button>
                                              <span class="btn  btn-default close_add">返  回</span>
                                          </div>
                                      </div>
                              		</form>
                            </div>
                        </div>
                        <!--添加其他规格-->
                        <div class="spec_add_other" style="display:none">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="span3 control-label">名称：</label>
                                    <div class="span7">
                                        <input type="text" class="form-control other_name_add" placeholder="请输入规格名称"> 
                                    </div>
                                </div>
                                <div class="form-group" style="margin-top:30px; margin-bottom:60px">
                                    <label class="span3 control-label">数值：</label>
                                    <div class="span7">
                                        <input type="text" class="form-control other_num_add" placeholder="请输入规格值，多个值用“,”隔开"> 
                                    </div>
                                </div>                               
                                <div class="form-group" style="margin-left:26%" >
                                    <div class="col-lg-offset-3 span9">
                                        <button class="btn btn-w-m btn-success addsave_other" type="submit">保  存</button>
                                        <span class="btn btn-w-m btn-default close_add">返  回</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                             
                             <!--修改图片规格-->
                        <div class="spec_update_img" style="display:none">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">名称：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control img_name" value=""> 
                                    </div>
                                </div>
                                <div class="form-group" style="margin-top: 40px;">
                                    <label class="col-lg-3 control-label">数值：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control img_num" value=""> 
                                    </div>
                                </div>                         
                                <div class="form-group">
                                    <div class="col-lg-offset-3 col-lg-9" align="center" style="margin-top: 40px;">
                                        <button class="btn btn-w-m btn-success" id="save_update_img" type="submit">保  存</button>
                                        <span class="btn btn-w-m btn-default close_update">返  回</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--修改其他规格-->
                        <div class="spec_update_other" style="display:none">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">名称：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control other_name" value=""> 
                                    </div>
                                </div>
                                <div class="form-group" style="margin-top: 40px;">
                                    <label class="col-lg-3 control-label">数值：</label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control other_num" value="">
                                    </div>
                                </div>                         
                                <div class="form-group">
                                    <div class="col-lg-offset-3 col-lg-9" align="center" style="margin-top: 40px;">
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
		 $(document).ready(function () {
			App.init(); 
			firstcate();
			menuact("04_03_07");
			
         });
      
	/* 	 function onkey(key){
             var li=key+" ul li"; 

             $('.abc').css("display","block");
             $('.k'+key).css("display","block");
             $('.k'+li).css("display","block"); 
         } */
		 
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
				$("#pictablebody").html("");
				$("#tablebody").html("");
				var tcateid =$("#thirdcate").val();
				
				$("#categoryid").val(tcateid);
				$.ajax({
					type : "GET",
					url : "querySpec",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : {category_id:$("#categoryid").val()},
					success : function(data) {
						console.log(data);
						var speclist =data.result;
						var tablecontImg ="";
						var tablecontOther ="";
						$.each(speclist,function(i, specitem) {
							
							var no =Number(i+1);
							var id =specitem.thirdId;
							var fname =specitem.firstName;
							var sname =specitem.secondName;
							var tname =specitem.thirdName;
							var values =specitem.specStr;
							var str = values.split(",");
							if(values != ""){
								for(var i=0;i<str.length;i++){
									if(str[i] != "undefined" ){
										if(i == 0){
											tablecontImg += "<tr>"+
											"<td>"+str[i]+"</td>"+
											"<td>"+"<a href='chgSpec?category_id="+id+"'>编辑</a>&nbsp;</td>"+
											"</tr>";
										}else{
											tablecontOther += "<tr>"+
											"<td>"+str[i]+"</td>"+
											"<td>"+"<a href='chgSpec?category_id="+id+"'>编辑</a>&nbsp;</td>"+
											"</tr>";
										}
									}
								}
							}
						});
						if(tablecontImg.length>0){
							$("#spec_add_img").css("display","none");
							$("#spec_add_other").hide();
							$(".ajax_btn").hide();
							$("#pictablebody").html(tablecontImg);
						}
						if(tablecontOther.length>0){
							$("#tablebody").html(tablecontOther);
						}
						
						
					}
					
				})
				
			});
		 
			//规格添加、删除、编辑
			/*弹窗*/
			//添加图片规格
            $('#spec_add_img').click(function() {
            	if($("#thirdcate").val() == 0){
            		alert("请先选择分类");
            		return;
            	}
            	
            	var tr_key=$('.img_table').find('tr:eq(1)').find('td:eq(0)').text();
            	//alert(tr_key.length);
            	if(tr_key.length>0)
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
            	if($("#thirdcate").val() == 0){
            		alert("请先选择分类");
            		return;
            	}
                $(".spec_add_other").css("display","block");
            });

          	//保存添加图片规格
            $('.addsave_img').click(function() {
                //var tr_num = $('.other_table').find('tr:eq(' + (trNuma) + ')').find('td:eq(1)').text();
                var tr_num = parseInt($(".img_table tr:last td:first").text());
                if(tr_num>0){tr_num=tr_num+1;}else{tr_num=1;}
                var img_name_add = $(".img_name_add").val();
                var img_num_add = $(".img_num_add").val();
                if(img_name_add == "" || img_num_add == ""){
                	alert("信息不完整");
                	
                	return false;
                }
                var tr = '<tr><td style="display:none;">' + tr_num + '</td><td>' + img_name_add + '</td><td>' + img_num_add + '</td><td><a class="open_update_img" style="cursor:pointer;">编辑</a></td></tr>';
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
                var tr = '<tr><td style="display:none;">' + tr_num + '</td><td>' + other_name_add + '</td><td>' + other_num_add + '</td><td><a class="open_update_other" style="cursor:pointer;">编辑</a>&nbsp;<a class="open_delete_other" style="cursor:pointer;">删除</a></td></tr>';
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
                if(img_name_n == "" || img_num_n == ""){
                	alert("信息不完整");
                	return false;
                }
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
		 	
			};
		 
		 
            
       
	</script>





</body>

</html>
