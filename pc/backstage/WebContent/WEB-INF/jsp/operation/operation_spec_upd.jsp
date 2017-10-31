<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<link href="media/css/jqpagination.css" rel="stylesheet" type="text/css" />
<link href="media/css/bootstrap.min.css" rel="stylesheet"
  type="text/css" />
<!-- <link href="media/css/bootstrap-responsive.min.css" rel="stylesheet"
  type="text/css" /> -->
<link href="media/css/font-awesome.min.css" rel="stylesheet"
  type="text/css" />
<link href="media/css/style-metro.css" rel="stylesheet" type="text/css" />
<link href="media/css/style.css" rel="stylesheet" type="text/css" />
<link href="media/css/style-responsive.css" rel="stylesheet"
  type="text/css" />
<link href="media/css/default.css" rel="stylesheet" type="text/css"
  id="style_color" />
<link href="media/css/uniform.default.css" rel="stylesheet"
  type="text/css" />
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
        .spec_add_img,.spec_update,.spec_update_img,.spec_add_other{
            display:none;
            width:600px;
            height: min-height;
            background: #fff;
            padding:20px 10px;
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

<body class="page-header-fixed">
  <div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container-fluid">
        <a class="brand" href="index.html"> <img
          src="media/image/logo.png" alt="logo" />
        </a>
        <div class="navbar hor-menu hidden-phone hidden-tablet">
          <div class="navbar-inner">
            <jsp:include page="/WEB-INF/jsp/banner.html" flush="true" />
          </div>
        </div>
        <a href="javascript:;" class="btn-navbar collapsed"
          data-toggle="collapse" data-target=".nav-collapse"> <img
          src="media/image/menu-toggler.png" alt="" />
        </a>
        <jsp:include page="/WEB-INF/jsp/userinfo.jsp" flush="true" />
      </div>
    </div>
  </div>
  <div class="page-container row-fluid">
    <div class="page-sidebar nav-collapse collapse">
      <jsp:include page="/WEB-INF/jsp/menu.html" flush="true" />
    </div>
    <div class="page-content">
      <div class="container-fluid">
        <div class="row-fluid">
          <div class="span12">
            <h3 class="page-title" style="margin: 20px 0px 15px 0px">规格列表</h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i
                class="icon-angle-right"></i></li>
              <li><a href="#">规格管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">规格列表</a></li>
            </ul>
          </div>
          <div class="row-fluid margin-bottom-20">

          </div>
          <div class="span12">
            <div class="portlet box grey">
              <div class="portlet-title" style="height:30px">
              </div>
              <div class="portlet-body">
                    <div class="form-horizontal">
		                    <div class="row-fluid">
		                      
		                      <div class="span8" class="margin-left:20px">
		                         <label >分类：</label> <span id="categoryinfo"></span>
		                      </div>
                 </div>
                 
                 <div class="dataTables_wrapper portlet form-inline">
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
									<div style="margin-bottom:20px">
										<a href="javascript:;" id="spec_add_img">添加图片规格</a>
									</div>

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
                    <a href="javascript:;" id="spec_add_other">添加其他规格</a> 
                  </div><br>
									
									<div class="form-group">
                       <div class="col-lg-offset-3 col-lg-9">
                         <a class="btn  btn-default " href="javascript:window.history.go(-1);">返回</a>
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
                                      <div class="form-group" style="margin-left:23%">
                                          <div class="col-lg-offset-3 col-lg-9">
                                              <button class="btn  btn-success addsave_img" type="submit">保  存</button>
                                              <span class="btn  btn-default close_addu">返  回</span>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                              <!--添加其他规格-->
                              <div class="spec_add_other">
                                  <!-- <div class="form-horizontal"> -->
                                      <div class="form-group row-fluid" style="margin-left:5%">
                                          <label class="span3 control-label">名称：</label>
                                          <input type="text" class="form-control name_add_other span8" placeholder="请输入规格名称"> 
                                      </div><br><br>
                                      <div class="form-group row-fluid" style="margin-left:5%">
                                          <label class="span3 control-label">数值：</label>
                                          <input type="text" class="form-control num_add_other span8" placeholder="请输入规格值，多个值用“,”隔开"> 
                                      </div><br><br>                               
                                      <div class="form-group" style="margin-left:-16%">
                                          <div class="row-fluid text-center">
                                              <button class="btn btn-w-m  btn-success addsave_other" type="submit">保  存</button>
                                              <span class="btn btn-w-m  btn-default close_addu">返  回</span>
                                          </div>
                                      </div>
                                 <!--  </div> -->
                              </div>
                  <!-- 弹出修改其他规格 -->
                  <div class="spec_update" >
                      <div class="form-horizontal">
                        <div class="row">
	                          <label class="col-lg-3 control-label" style="margin-left:-70px">规格名称：</label>
	                          <div class="col-lg-3">
	                            <input type="text" class="pull-left" id="upd_spec_name" >
	                            <input type="hidden" class="" id="upd_spec_id">
	                          </div>
	                          <div class="col-lg-3" >
	                            <a class="ajaxsave_othername" style="margin-left:4%">修改</a><span class='returnsave_a'>操作成功！</span>
	                          </div>
                        </div><br><br>
                        <div class="form-group">
                          <label class="">规格值：</label>
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
                                              <a href="javascript:;" id="other_upload_add"><img src=""></a>
                                          </div>  
                          </div>
                        </div>
                        <br>
                        <div class="form-group" style="margin-left:67px">
                          <div class=" col-lg-9">
                            
                            <span class="btn  btn-default " onclick="closeupdate()">返回</span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- 弹出修改图片规格 -->
                    <div class="spec_update_img" >
                      <div class="form-horizontal">
                        <div class="form-group">
                          <div class="col-lg-3">
                          <label class=" control-label">规格名称：</label>
                          </div>
                          
                          <div class="col-lg-5" style="margin-left:-21px">
                            <input type="text" class="form-control" id="upd_spec_name_img">
                            <input type="hidden" class="form-control" id="upd_spec_id_img">
                            
                          </div>
                          <div class="col-lg-2" style="line-height:34px;">
                            <a class="ajaxsave_imgname">修改</a>
                          </div>
                          <div class="col-lg-2 updsuccess" style="line-height:34px;display:none">
                            <span class="returnsave_b">操作成功！</span>
                          </div>
                        </div><br>
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
	                              <a href="javascript:;" id="other_upload_add_img"><img src=""></a>
	                          </div>  
                          </div>
                        </div><br>
                        <div class="form-group">
                          <div class="col-lg-offset-3 col-lg-9">
                            
                            <span class="btn  btn-default close_img_up">返回</span>
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
  <script src="media/js/jquery-migrate-1.2.1.min.js"
    type="text/javascript"></script>
  <script src="media/js/jquery-ui-1.10.1.custom.min.js"
    type="text/javascript"></script>
  <script src="media/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
  <script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>
  <script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>
  <script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>
  <script src="media/js/app.js"></script>
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
                location.reload();
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
                    	console.log("修改j");
                    	console.log(data);
                        if (data.status == 0) {
                        	$(".updsuccess").show();
                           console.log("修改成功");
                           /*  window.location.reload(); */
                        }else{
                        	console.log("修改失败");
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
								"<td>"+"<a onclick='updspec(this)' data='"+itemstr+"' class='btn '>修改</a>&nbsp;<a class='other_del btn '>删除</a>"+
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
				"<td>"+"<button onclick='updspecimg(this)' data='"+picitemstr+"' class='btn btn-primary'>修改</button>"+
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





</body>

</html>
