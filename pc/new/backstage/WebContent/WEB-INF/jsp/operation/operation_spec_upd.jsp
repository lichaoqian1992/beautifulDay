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
         .spec_add_img,.spec_update,.spec_update_img,.spec_add_other2{
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
        	margin-top: -100px;"
        }
        .returnsave,.returnsave_b,.returnsave_a{
        	color:#78FF00 !important;
        	float:right;
        	display:none;
        }
        a {
        	cursor:pointer
        }
    </style>

</head>

<body class="page-header-fixed">
	
	<iframe  id="submitFrame" style="display: none;width:0; height:0" name="submitFrame"  src="about:blank"></iframe>
	
  <div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container-fluid">
        <a class="brand" href="index.html"> <img
          src="media/image/logo.png" alt="logo" />
        </a>
        <div class="navbar hor-menu hidden-phone hidden-tablet">
          <div class="navbar-inner">
            <jsp:include page="/WEB-INF/jsp/banner.jsp" flush="true" />
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
      <jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true" />
    </div>
    <div class="page-content">
      <div class="container-fluid">
          <div class="span12">
            <h3 class="page-title" style="margin: 20px 0px 15px 0px">内容派生的商品规格信息</h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i
                class="icon-angle-right"></i></li>
              <li><a href="#">发布信息</a><i class="icon-angle-right"></i></li>
              <li><a href="selSpec">内容派生的商品规格信息</a></li>
            </ul>
          </div>
          <div class="row-fluid margin-bottom-20">

          </div>
        <div class="row-fluid">
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
									<table class="table table-bordered table-hover img_table" style="line-height: 100px;;">
										<thead>
											<tr>
												<th>名称</th>
												<th>数值</th>
												<th>图片地址</th>
												<th style="width:60px">操作</th>
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
                              		<form action="addMainSpec" method="post" enctype="multipart/form-data">
                              			<div class="form-group row-fluid">
                                          <label class="col-lg-3 control-label" style="margin-left:5%">名称：</label>
                                          <input name="categoryid" value="${category_id }" type="hidden">
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
                                              <span class="btn  btn-default close_addu">返  回</span>
                                          </div>
                                      </div>
                              		</form>
                              </div>
                              <!--添加其他规格-->
                              <div class="spec_add_other">
                              		<form action="addMainSpec" method="post" enctype="multipart/form-data">
                                      <div class="form-group row-fluid" style="margin-left:5%">
                                          <label class="span3 control-label">名称：</label>
                                          <input name="categoryid" value="${category_id }" type="hidden">
                                          <input name="type" value="other" type="hidden">
                                          <input type="text" name="name" class="form-control name_add_other span8" placeholder="请输入规格名称"> 
                                      </div><br><br>
                                      <div class="form-group row-fluid" style="margin-left:5%">
                                          <label class="span3 control-label">数值：</label>
                                          <input type="text" name="values" class="form-control num_add_other span8" placeholder="请输入规格值，多个值用“,”隔开"> 
                                      </div><br><br> 
                                      <div class="form-group row-fluid" style="margin-left:5%;display: none;">
                                          <label class="col-lg-3 control-label">图片：</label>
                                          <input type="file" name="file" multiple> 
                                      </div><br><br>                               
                                      <div class="form-group" style="margin-left:-16%">
                                          <div class="row-fluid text-center">
                                              <button class="btn btn-w-m  btn-success addsave_other" type="submit">保  存</button>
                                              <span class="btn btn-w-m  btn-default close_addu">返  回</span>
                                          </div>
                                      </div>
                                     </form>
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
	                            <a class="ajaxsave_othername" style="margin-left:4%">保存</a><span class='returnsave_a'>操作成功！</span>
	                          </div>
                        </div><br><br>
                        <div class="form-group">
                          <label class="">规格值：</label>
                          <div class="col-lg-9">                            
                            <table class="table table-bordered table-hover up_table">
                            <thead>
                              <tr>
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
                    
                    
                    
                    
                    <!-- <div class="spec_add_img" style="display:none">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="span3 control-label">名称：</label>
                                    <div class="span7">
                                        <input type="text" class="form-control img_name_add" placeholder="请输入规格名称"> 
                                    </div>
                                </div>
                                <div class="form-group" style="margin-top:30px; margin-bottom:60px">
                                    <label class="span3 control-label">数值：</label>
                                    <div class="span7">
                                        <input type="text" class="form-control img_num_add" placeholder="请输入规格值，多个值用“,”隔开"> 
                                    </div>
                                </div>                               
                                <div class="form-group" style="margin-left:26%">
                                    <div class="col-lg-offset-3 span9">
                                        <button class="btn btn-w-m btn-success addsave_img" type="submit">保  存</button>
                                        <span class="btn btn-w-m btn-default close_add">返  回</span>
                                    </div>
                                </div>
                            </div>
                        </div> -->
                    
                    <!-- 弹出修改图片规格 -->
                    <div class="spec_update_img" >
                      <div class="form-horizontal">
                        <div class="form-group">
                          <div class="col-lg-3">
                          <label class=" control-label">规格名称：</label>
                          </div>
                          
                          <div class="col-lg-4" style="margin-left:-21px">
                            <input type="text" class="form-control" id="upd_spec_name_img">
                            <input type="hidden" class="form-control" id="upd_spec_id_img">
                            <input type="hidden" id="img_id">
                            <a class="ajaxsave_imgname" >保存</a>
                            <span class="returnsave_b">操作成功！</span>
                          </div>
                          <div class="col-lg-2" style="line-height:34px;">
                          <div class="col-lg-2 updsuccess" style="line-height:34px;display:none">
                          </div>
                          </div>
                        </div><br>
                        <div class="form-group">
                          <label class="col-lg-2 control-label">规格值：</label>
                          <div class="col-lg-9">
                            
                            <table class="table table-bordered table-hover up_table_img">
                            <thead>
                              <tr>
                                <th>规格值</th>
                                <th>图片</th>
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
				<div id="myDiv" class="spec_add_other2">
				  	<form id="mySubmit" action="updSpect" method="POST" enctype="multipart/form-data">
				  	<div class="form-group row-fluid" style="margin-left:5%">
	                       <label class="span3 control-label">ID：</label>
	                       <input type="text" id="myId" name="id" readonly="readonly">
	                   </div><br><br>
	                   <div class="form-group row-fluid" style="margin-left:5%">
	                       <label class="span3 control-label">名称：</label>
	                       <input type="text" id="myTitle" name="title">
	                   </div><br><br>
	                   <div class="form-group row-fluid" style="margin-left:5%">
	                       <label class="span3 control-label">图片：</label>
	                       <img id="icon" style="width: 100px;height: 100px;">
	                        <input id="url_img" name="url_img" type="hidden">
	                       <input id="upFile2" type="file" id="file" name="file" style="display: none">  
	                       <a id="upAgain2" onclick="uploadpicdiv2()" style="margin-left:5%">重新上传</a>
	                   </div><br><br>                            
	                   <div class="form-group" style="margin-left:-16%">
	                       <div class="row-fluid text-center">
	                           <input class="btn  btn-default " type="submit" value="保存"><!-- onclick="mySubmit()" -->
				  			   <a class="btn  btn-default " href="javascript:window.history.go(0);">返回</a>
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
 </div> 
	
  <script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
  <script src="media/js/jquery.form.js" type="text/javascript"></script>
  <script src="media/js/jquery.form.min.js" type="text/javascript"></script>
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
			menuact("04_03_07");
			setcateinfo();
			settableinfo();
			//添加图片规格
            $('#spec_add_img').click(function() {
            	var tr_key=$('.img_table').find('tr:eq(1)').find('td:eq(0)').text();
            	if(tr_key != "")
           		{
           			alert("图片规格只能有一个！");
           			return false;
           		}else{
            		$(".spec_add_img").css("display","block");
            		//$("#myDiv").show();
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
						/* "<td>"+tr_num+"</td>"+ */
						"<td>"+"<input type='text' value=''></td>"+
						"<td>"+"<a class='ajaxsave_imgvalue_new'>修改</a><span class='returnsave'>操作成功！</span>"+"</td>"+
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
						/* "<td>"+tr_num+"</td>"+ */
						"<td>"+"<input type='text' value=''></td>"+
						"<td>"+"<a class='ajaxsave_value_new'>修改</a><span class='returnsave'>操作成功<span>"+"</td>"+
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
                    type: "POST",
                    contentType: "application/json",
                    url: "addMainSpec",
                    dataType : "json",
                    data:  {
                    	name:$(".name_add_other").val(),
                    	values:$(".num_add_other").val(),
                    	categoryid:cateid,
                    	type:"other"
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
                var img_url = $('.up_table_img').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').find('input').val();
                $('.up_table_img').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').find('span').css("display","block");
                
                $("#myId").val(id);
            	$("#myTitle").val(title);
            	$("#icon").attr("src",img_url);
            	$("#url_img").val(img_url);
            	$(".spec_update_img").css("display","none");
            	$("#myDiv").show();
                //var id = $("#upd_spec_id_img").val();	//父类ID
                //var title = $("#upd_spec_name_img").val();	
                //alert(id+"=========="+title);
               	/* $.ajax({
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
                        	$(".returnsave"+trNum).show();
                        }else{
                        	alert("修改失败");
                        }
                    }
                }) */
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
                var is_del = $(this).text();
                var a="";
                var color="";
                if(is_del == "禁用"){
                	is_del = 1;
                }else if(is_del == "启用"){
                	is_del = "0";
                }
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "delSecondSpec",
                    dataType : "json",
                    async:false,
                    data:  {
                    	spec_id:id,
                    	is_del:is_del
                    	},
                    success: function (data) {
                        if (data.status == 0) {
                        	if(is_del == 1){
                            	a = "启用";
                            	color="green";
                            }else if(is_del == 0){
                            	a = "禁用";
                            	color="red";
                            }
                        }else{
                        	alert("删除失败");
                        }
                    }
                })
                $(this).text(a);
               	$(this).css("color",color);
               
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
            	var is_del = $(this).text();
            	trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号                 
            	var id = $('.up_table').find('tr:eq(' + (trNum) + ')').find('td:eq(0)').find('input').val();
                if(is_del == "禁用"){
                	is_del = 1;
                }else if(is_del == "启用"){
                	is_del = 0;
                }
                var a = "",color = "";
               	$.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "delSecondSpec",
                    dataType : "json",
                    async:false,
                    data:  {
                    	spec_id:id,
                    	is_del:is_del
                    },success: function (data) {
                        if (data.status == 0) {
                        	if(is_del == 1){
                            	a = "启用";
                            	color="green";
                            }else if(is_del == 0){
                            	a = "禁用";
                            	color="red";
                            }
                        }else{
                        	
                        }
                    }
                })
                $(this).text(a);
               	$(this).css("color",color);
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
			console.log(specs);
			var picspec ="";
			var nonepicspecs="";
			var tablestr ="";
			
			$.each(specs,function(i, specitem) {
				if(!specitem.name.indexOf("*")){
					picspec =specitem;
				}else{
					
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
								/* "<td>"+no+"<input type='hidden' value='"+ id +"'></td>"+ */
								"<td>"+name+"</td>"+
								"<td>"+specstr+"</td>"+
								"<td>"+"<a onclick='updspec(this)' data='"+itemstr+"' class='btn '>修改</a>"+
								"</tr>";								
					tablestr +=rowstr;
				} 
			});
			$("#tablebody").html(tablestr);
			
			if(picspec!=""){
				
				var no =1;
				var id =picspec.id;
				console.log(picspec);
				var name =picspec.name;
				var name=name.replace("*","");
				var secondpicspeclist =picspec.specList;
				var picspecstr ="";
				var imgUrlStr ="";
				$.each(secondpicspeclist,function(k, secondpicspecitem) {
					if(k == 0){
						imgUrlStr+=secondpicspecitem.img_url;
						picspecstr+=secondpicspecitem.title;
					}else{
						if(secondpicspecitem.img_url != ""){
							imgUrlStr+=","+secondpicspecitem.img_url;
						}
						if(secondpicspecitem.title != ""){
							picspecstr+=","+secondpicspecitem.title;
						}
					}
					
				});
				$("#img_id").val(id);
				var picitemstr=JSON.stringify(picspec);
				var picrowstr ="<tr>"+
				/* "<td style='display:none;'>"+id+"</td>"+ */
				"<td>"+name+"</td>"+
				"<td>"+picspecstr+"</td>"+
				"<td style='overflow: hidden;width:1100px;'>"+imgUrlStr+"</td>"+
				"<td>"+"<button onclick='updspecimg(this)' data='"+picitemstr+"' class='btn btn-primary'>修改</button>"+
				"</tr>";
				
				$("#pictablebody").html(picrowstr);
				
			};
		};
		//修改图片规格
		function updspecimg(obj){
			
        	$("#myDiv").hide(); 
        	$(".spec_update").css("display","none");
			
			var status = "";
			var s = "";
			var data =$(obj).attr("data");
			var item =JSON.parse(data);
			var itemname=item.name;
			var itemname=itemname.replace("*","");
			//alert(item.name);	
			$("#upd_spec_name_img").val(itemname);
			$("#upd_spec_id_img").val(item.id);	//父类id		
			var itemlist=item.specList;
			var tablehtml="";
			//alert(itemlist);
			$.each(itemlist,function(k,seconditem){
				console.log(seconditem);
				var no =Number(k+1);
				var id =seconditem.id;
				var name =seconditem.title;
				var is_del = seconditem.is_del;
				var img_url = seconditem.img_url;
				if(is_del == 0){//启用
					status = "禁用";
					s = "style='color:red;'";
				}else{
					status = "启用";
					s = "style='color:green;'";
				}
				var name=name.replace("*","");
				var updrow ="<tr>"+
							"<td style='display:none;'>"+id+"<input type='hidden' value='"+ id +"'></td>"+
							"<td>"+"<input type='text' style='width:100px;' value='"+name+"'></td>"+
							"<td style='display:none;'><input type='text' style='width:100px;' value='"+img_url+"'></td>"+
							"<td><img src='"+img_url+"' style='height:100px;width:100px;'></td>"+
							"<td><a class='ajaxsave_imgvalue'>修改</a>&nbsp;<a class='ajaxdel_imgvalue' id='updateDel'"+s+">"+status+"</a><span class='returnsave'"+no+">操作成功！</span></td>"+
							"</tr>";
				tablehtml+=updrow;			
			});
			$("#specupdtable_img").html(tablehtml);
			 $(".spec_update_img").css("display","block");
		};
		//修改其他规格
		function updspec(obj){
			$(".spec_update_img").css("display","none");
        	$("#myDiv").hide();
			var status = "";
			var s = "";
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
				var is_del = seconditem.is_del;
				if(is_del == 0){//启用
					status = "禁用";
					s = "style='color:red;'";
				}else{
					status = "启用";
					s = "style='color:green;'";
				}
				var updrow ="<tr>"+
							"<td style='display:none'><input type='hidden' value='"+ id +"'></td>"+ 
							"<td>"+"<input type='text' value='"+name+"'></td>"+
							"<td>"+"<a class='ajaxsave_value'>保存</a>"+"&nbsp;<a class='ajaxdel_value'"+s+">"+status+"</a><span class='returnsave'>操作成功！</span></td>"+
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
		/* function mySubmit(){
			$("#mySubmit").submit();
			/* $("#mySubmit").ajaxSubmit(function(data){ 
				
				window.location.href="javascript:window.history.go(0);";
				$(".spec_update_img").css("display","block");
            	$("#myDiv").hide(); 
				
			}); 
			window.location.href="javascript:window.history.go(0);";
		} */
		function uploadpicdiv2(){
			$("#icon").hide();
			$('#upFile2').show();
			$('#upAgain2').hide();
		}
	</script>





</body>

</html>
