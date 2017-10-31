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

<link href="media/css/jqpagination.css" rel="stylesheet" type="text/css" />
<link href="media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />
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
.row-pd {
	padding: 10px 0;
}

.attri_pt1 {
	padding-top: 6px;
}

.attri_pt1 span {
	color: #0933D4;
	margin: 0 30px 0 20px;
}

.attri_add, .attri_update {
	display: none;
	width: 450px;
	height: 250px;
	z-index:1000;
	/* position:absolute; */
	background: #fff;
	padding: 30px 20px;
	border: 1px solid #ccc;
	/* position: relative; */
/* 	margin: 0 auto;
	top: -200px; */
}

.returnsave {
	color: #000;
	float: right;
	display: none;
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
						<h3 class="page-title" style="margin: 20px 0px 15px 0px">
							属性列表
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="*">主页</a><i
								class="icon-angle-right"></i></li>
							<li><a href="#">属性管理</a><i class="icon-angle-right"></i></li>
							<li><a href="#">属性列表</a></li>
						</ul>
					</div>
				</div>
				
				<div class="row-fluid margin-bottom-20">

        </div>
				
				<div class="span12">
				   <div class="portlet box grey">
				      <div class="portlet-title">
				           <div class="caption"> </div>
				                <div class="actions">
				                    <a  class="btn blue" href="javascript:;" id="attri_add"><i class="icon-pencil"></i>添加属性</a>
				                </div>
                    </div>
              <div class="portlet-body">    
                 <div class="row-fluid ">  
				<!-- <div class="wrapper wrapper-content animated fadeInRight"> -->
					<!-- <div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content"> -->

									<div class="form-horizontal">
										<div class="form-group">
											<div class="span8">
											   <div class="pull-left">分类：<span id="categoryinfo"></span></div>
                      </div>
											<!-- <div class="col-lg-6 spec_pt1"> -->
												<!-- <span id="categoryinfo"></span> -->
											<!-- </div> -->
										</div>
									</div>
								</div>
								
								
											<!-- <div class="col-lg-8 spec_pt1"> -->
												
													<table id="attritable"
														class="table table-bordered table-hover properties_table">
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
													<!-- <div>
														<a href="javascript:;" id="attri_add"><img
															src="img/u865.png"></a>
													</div> -->
												</div>
											<!-- </div> -->
										 </div> 
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button class="btn btn-w-m btn-success ajax_btn"
													type="submit" onclick="javascript:location.reload();">保 存</button>
												<button class="btn btn-w-m btn-default" type="submit"
													onclick="javascript:history.back();">返 回</button>
											</div>
										</div>
									</div>
										<!-- 添加 -->
									<div class="attri_add">
										<div class="form-horizontal">
											<div class="form-group">
												<div class="row-fluid">
													<label class="span2 control-label">名称：</label> <input
														type="text" class="form-control attri_add_name span4">
												</div>
											</div>
											<br>
											<div class="form-group">
												<div class="row-fluid">
													<label class="span2 control-label">数值：</label> <input
														type="text" class="form-control attri_add_num span4">
												</div>
											</div>
											<br>
											<div class="form-group">
												<div class="row-fluid">
													<label class="span2 control-label">必填：</label> <input
														type="checkbox" class="form-control attri_add_check span1">
												</div>
											</div>
											<br>
											<div class="form-group">
												<div class="row-fluid text-center">
													<button class="btn btn-w-m btn-success save_add"
														type="submit">保存</button>
													<span class="btn btn-w-m btn-default close_add">返回</span>
												</div>
											</div>
										</div>
									</div>
	
									<!-- 修改 -->
									<div class="attri_update">
										<div class="form-horizontal">
											<div class="form-group">
												 <div class="row-fluid">
													<label class="span2 control-label">名称：</label>
													<input type="text" class="form-control properties_name span4">
												 </div>
										  </div><br>
											<div class="form-group">
												 <div class="row-fluid">
													  <label class="span2 control-label">数值：</label>
														<input type="text" class="form-control properties_num span4">
												 </div> 
											</div><br>
											<div class="form-group">
											   <div class="row-fluid">
												   <label class="span2 control-label">必填：</label>
													 <input type="checkbox"  class="form-control properties_check span1">
											   </div>
											</div><br>
											<div class="form-group">
												<div class="row-fluid text-center">
													<button class="btn btn-w-m btn-success success_update"
														type="submit">保存</button>
													<span class="btn btn-w-m btn-default close_update">返回</span>
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
			setcate();
			settable();
			
			$(".closeModal").click(function(){
				  $("#modal").hide();
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
			//编辑属性
	        $(".attri_update_a").click(function() {
	        	  console.log("编辑属性");
	            //获取对应参数
	            trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
	            console.log(trNum);
	            //tdNum = $(this).parent().find('td').index($(this)[0])+ 1;//获得table的列号
	            var properties_name = $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').text(); 
	            var properties_num = $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').text();
	            var properties_check = $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(3)').find('input');
	            hasChk = properties_check.is(':checked');
	            //alert(hasChk);
	            if(hasChk){
	                $(".properties_check").prop("checked",true);
	            }
	            else
	            {
	                $(".properties_check").prop("checked",false);
	            }
	            $(".properties_name").val(properties_name);
	            $(".properties_num").val(properties_num);
	            /* $(".attri_update").css("display","block"); */  
	            /* console.log("111");
	            console.log($(e));
	            var name = $(this).parent().parent().find('td:eq(1)').text();
	            var number = $(this).parent().parent().find('td:eq(2)').text();
	            $("#modal").show();
	            $('.modal-body').append('<label>ddd</label>');
	             */
	            console.log("111");
	            letDivCenter('.attri_update');
	        });
	        //修改属性值
	        $(".success_update").click(function() {
	            var properties_name_n = $(".properties_name").val();
	            var properties_num_n = $(".properties_num").val();
	            $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').html(properties_name_n);
	            $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').html(properties_num_n);
				
	            //修改复选框
	            if($(".properties_check").prop("checked")){
	                $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(3)').find('input').prop("checked",true);
	            }else{
	                $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(3)').find('input').prop("checked",false);
	            }
	            $(".attri_update").css("display","none");
	        });
	        $(".close_update").click(function() {
	            $(".attri_update").css("display","none");
	        });
	        
			//添加属性
	        $("#attri_add").click(function() {
/*             	$(".attri_add").css("display","block");
 */            	letDivCenter('.attri_add');
            });
	      //保存添加属性值
	        $(".save_add").click(function() {
	            var attri_add_name = $(".attri_add_name").val();
	            var attri_add_num = $(".attri_add_num").val();            
				
	            //修改复选框
	            if($(".attri_add_check").prop("checked")){
	                var checke='<input type="checkbox" checked disabled>';
	            }else{
	            	var checke='<input type="checkbox" disabled>';
	            }
	            var tr_num = parseInt($(".properties_table tr:last td:first").text());
                if(tr_num>0){tr_num=tr_num+1;}else{tr_num=1;}
               	
                var tr = '<tr><td>' + tr_num + '</td><td>' + attri_add_name + '</td><td>' + attri_add_num + '</td><td>' + checke + '</td><td><a href="javascript:;" class="attri_update_a">编辑</a>&nbsp;<a href="javascript:;" class="attri_delete"  >删除</a></td></tr>';
                $(".properties_table").append(tr);
                $("properties_table tr:eq(2)").after(tr);
                //$(".attri_add").html(checke);
	            $(".attri_add").css("display","none");
	            
	            $(".attri_add_name").val("");
	            $(".attri_add_num").val("");
	            $(".attri_add_check").attr('checked',false);
	        });
	        $(".close_add").click(function() {
	            $(".attri_add").css("display","none");
	        });
	        /*操作新添加的元素*/
            //修改属性
            $(".properties_table").on("click",".attri_update_b", function() {
                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
                //alert(trNum);
                var properties_name = $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(1)').text(); 
                var properties_num = $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(2)').text();
                var properties_check = $('.properties_table').find('tr:eq(' + (trNum) + ')').find('td:eq(3)').find('input');
	            hasChk = properties_check.is(':checked');
	            //alert(hasChk);
	            if(hasChk){
	                $(".properties_check").prop("checked",true);
	            }
	            else
	            {
	                $(".properties_check").prop("checked",false);
	            }
	            $(".properties_name").val(properties_name);
	           	$(".properties_num").val(properties_num);
	            $(".attri_update").css("display","block"); 
	          
	            
            });
          	//删除属性
             $(".properties_table").on("click",".attri_delete", function() {
            	  var gnl=confirm("确定要删除?");
            	  if (gnl==true){
		                trNum = $(this).parent().parent().parent().find('tr').index($(this).parent().parent()[0])+ 1;//获得table的行号
		                $('.properties_table').find('tr:eq(' + (trNum) + ')').remove();
                    }else{
                      return false;
                    }
            }); 
            
            /* function delSure(){
            	
	            var gnl=confirm("确定要删除?");
	            if (gnl==true){
	              return true;
	            }else{
	              return false;
	            }
            } */
            /*操作新添加的元素*/
          	//生成json数据
            $(".ajax_btn").click(function() {
                var t_oper = $(".properties_table tr").length;
                //alert(t_other);
                var k="";
                var g=";";
               	//循环属性
                for(i=1;i<t_oper;i++)
                {
                    var name = $('.properties_table').find('tr:eq(' + (i) + ')').find('td:eq(1)').text();
                    var num  = $('.properties_table').find('tr:eq(' + (i) + ')').find('td:eq(2)').text();
                    var check = $('.properties_table').find('tr:eq(' + (i) + ')').find('td:eq(3)').find('input');
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
                var id =${attri_id };
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "updAttri",
                    dataType : "json",
                    data:  {
                    	attri_id:id, 
                    	content:k
                    	},
                    success: function (data) {
                        if (data.status==0) {
                        	 alert("保存成功");
                        	//var hrefstr ="readAttri?attri_id="+id;
                            window.location.href="readAttri?attri_id="+id;
                        	
                        }
                    }
                });
               
                
            });
		});
		
		function letDivCenter(attrClass){
			var top = ($(window).height()-250)/2;
			var left = ($(window).width()-450)/2;
			var scrollTop = $(document).scrollTop();
			var scrollLeft = $(document).scrollLeft();
			console.log(top);
			console.log(left);
			console.log(scrollTop);
			console.log(scrollLeft);
			$(attrClass).css({
				position:'absolute',
				top:top + scrollTop+'px',
				left:left + scrollLeft+'px'
			}).show();
		}
		
		function setcate(){
			
			var c =${catagoryinfo };
			var cateinfo =c.first_name+"-"+c.second_name+"-"+c.third_name;
			$("#categoryinfo").html(cateinfo);
			
			
			
		};
		
		/* $('#attri_add').on('click', function(){
            $.layer({
                 type: 1,                    
                 shade: [0],//遮罩透明度                    
                 area: ['auto', 'auto'],
                 offset:['100px', ''],
                 title: false,
                 border: [0],                    
                 page: {dom : '.attri_add'}
             });
         }); */
		
		
		
		 function settable(){
			 var tablecont ="";
			 var attrilist =${attrijson };
			 $.each(attrilist,function(i, attriitem) {
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
				"<td>"+"<input type='checkbox' value='' disabled>"+"</td>"+
				"<td>"+"<a href='javascript:;' class='attri_update_a'>编辑</a>"+"&nbsp;"+"<a href='javascript:;' class='attri_delete'>删除</a>"+"&nbsp;"+"</td>"+
				"</tr>";
				/* $("#attritable tr:last").after(newRow); */
				tablecont+=newRow;
				 
			 });
			 //var temprow ='';
			/*  $("#attritable tr:last").after(temprow); */
			
			 //tablecont+=temprow;
			$("#tablebody").html(tablecont);
			
		 };
			
	</script>
	
</body>

</html>
