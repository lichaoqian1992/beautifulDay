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
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title" style="margin: 20px 0px 15px 0px">
							用户所用属性模板
							<!-- <small>正常账户列表</small> -->
						</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="toMain">主页</a><i
								class="icon-angle-right"></i></li>
							<li><a href="#">模板配置</a><i class="icon-angle-right"></i></li>
							<li><a href="selAttri"> 用户所用属性模板</a></li>
						</ul>
					</div>
				</div>

				<div class="row-fluid margin-bottom-20"></div>

						<div class="portlet box grey">
							<div class="portlet-title">
								<div class="caption"></div>
							</div>
							<div class="portlet-body" style="height:600px">
								<div class=" ">
									<div class="form-horizontal">
										<div class="form-group">
											<div class="span8">
												<div class="pull-left">
													分类：<span id="categoryinfo"></span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class=" " style="margin:50px 0 50px 0;">
									<div class="form-horizontal">
										<div class="form-group">
											<div class="span8">
												<div class="pull-left">
													属性：<textarea rows="5" style="width:1000px;" id="attri" ></textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
								<br>
								<div class="" style="margin-top:135px">
										<button class="btn btn-w-m btn-success ajax_btn" type="submit"
											onclick="javascript:<!-- location.reload() -->;">保 存</button>
										<a class="btn btn-w-m btn-default"
											href="selAttri">返 回</a>
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
			setcate();
			menuact("03_03_04");
			var attri = ${attr};
            console.log(attri);
            $("#attri").val(attri.content);
            var id = attri.id;
            /*操作新添加的元素*/
          	//生成json数据
            $(".ajax_btn").click(function() {
               
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "updAttri",
                    dataType : "json",
                    data:  {
                    	attri_id:id, 
                    	content:$("#attri").val()
                    	},
                    success: function (data) {
                    	console.log(data);
                        if (data.status==0) {
                        	 alert("保存成功");
                             window.location.href="chgAttri?attri_id="+id; 
                        	
                        }
                    }
                }); 
            });
		});
		
		function setcate(){
			
			var c =${catagoryinfo };
			var cateinfo =c.first_name+"-"+c.second_name+"-"+c.third_name;
			$("#categoryinfo").html(cateinfo);
		};
		
			
	</script>
	
</body>

</html>
