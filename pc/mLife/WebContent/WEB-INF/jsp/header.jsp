<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
	<!-- jquery -->
	<script src="${ctx}/js/jquery-1.11.2.min.js"></script>
	<!-- 弹窗js/css -->
	<link rel="stylesheet" type="text/css" href="${ctx}/css/xcconfirm.css" />
	<script src="${ctx}/js/xcconfirm.js" type="text/javascript"charset="utf-8"></script>

    <link href="${ctx}/css/style.css" rel="stylesheet" />
    <link href="${ctx}/css/frame.css" rel="stylesheet" />
    <!--页面主要样式文件-->
    <link href="${ctx}/css/category-icons.css" rel="stylesheet" />
    <!--类目图标样式-->
    <link href="${ctx}/css/sale-qmui.css" rel="stylesheet" />
    <link href="${ctx}/css/style(1).css" rel="stylesheet" />
    <link href="${ctx}/css/jquery-ui-1.8.21.custom.css" rel="stylesheet" />
    <script src="${ctx}/js/sea-debug.js"></script>
    <script src="${ctx}/js/config.js"></script>
    <script src="${ctx}/js/config(1).js"></script>
    <script src="${ctx}/js/zh-cn.js"></script>
	<link href="${ctx}/css/pure-min.css" rel="stylesheet" />

<script type="text/javascript">
$(document).ready(
	 function weather() {
		$.ajax({   
		    type:"GET",   
		    url:"${ctx}/weather?id=0",
		    dataType:"html",        
		    contentType:"application/json; charset=utf-8",                 
		    data:{} ,   
		    success:function(data){
		    	$("#weather").empty();
		    	 $("#weather").html(data);
			}
		})
	})
</script>


<style type="text/css">
</style>

</head>
<body>


	<div id="header">
		<h1 id="logo">
			<a href="http://www.manjiwang.com" class="text-hide"> <img
				src="${ctx}/img/20150209182348015_3824.png" alt="">
			</a>
		</h1>

		<ul class="main-nav" id="main-nav">
			<li class="item nav-item item-odd" id="nav-21005">
			<a style="cursor: pointer;" id="life"><i class="iconfont iconfont-buy">&#xe602;</i><span>便民</span></a>
				 </li>
			<li class="item nav-item item-odd id=" id="nav-21086">
				<a style="cursor: pointer;" id="order"><i class="iconfont iconfont-order">&#xe601;</i><span>订单</span></a>
			</li>
			<li class="item nav-item item-odd" id="nav-21085" style="display:none;">
			<a style="cursor: pointer;" id="manage"><i class="icon icon-manage"></i><span>管理</span></a>
			</li>
			
			<li>
				<div class="account-info" id="user" >
					<c:choose>
						<c:when test="${user_name==null}">
							<a href="/mLife" class="user-info" id="login">请登录</a>
						</c:when>
						<c:otherwise>
    				欢迎您<a href="javascript:void(0);" class="user-info" id="username">${user_name }</a>&nbsp;
    				<a href="logout" class="user-info" id="logout">退出登录</a>
						</c:otherwise>
					</c:choose>
			
					
				</div>
				<div class="pure-u-1-3" id="weather"
					style="cursor: pointer; height: 50px; width: 250px; color: white; margin-top: 25px; float: right;">
				</div>
			</li>

		</ul>
	</div>
	<script>
$("#weather").click(function(){
	window.location.href="${ctx}/weather?id=1"; 
});

$("#life").click(function(){
	window.location.href="${ctx}/toMobile"; 
});
$("#order").click(function(){
	
	var str =$("#order").text();

	
	if(""==str){
		window.location.href="http://www.baidu.com";
	}else{
		window.location.href="${ctx}/toOrder"; 
	}
	
	
	
	
});
$("#manage").click(function(){
	window.location.href="${ctx}/toManage"; 
});





</script>


</body>
</html>