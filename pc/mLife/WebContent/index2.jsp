<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>便民</title>

	<link href="${ctx}/css/style.css" rel="stylesheet" />
    <link href="${ctx}/css/frame.css" rel="stylesheet" />
    
    <!--页面主要样式文件-->
    <link href="${ctx}/css/category-icons.css" rel="stylesheet" />
    <!--类目图标样式-->
    <link href="${ctx}/css/sale-qmui.css" rel="stylesheet" />
    <link href="${ctx}/css/style(1).css" rel="stylesheet" />
    <link href="${ctx}/css/datepicker.css" rel="stylesheet" />
    
    <link href="${ctx}/css/jquery-ui-1.8.21.custom.css" rel="stylesheet" />
    
    <link href="${ctx}/css/jquery.qtip.min.css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx}/js/jquery-1.11.2.min.js"></script>
    <script src="${ctx}/js/jquery.cookie.js"></script>
    <script src="${ctx}/js/sea-debug.js"></script>
    <script src="${ctx}/js/config.js"></script>
    <script src="${ctx}/js/config(1).js"></script>
    <script src="${ctx}/js/calendar.js"></script>
    <script src="${ctx}/js/zh-cn.js"></script>
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
</head>
<body >

	<div class="pure-g">
	<div  class="pure-u-1">

	
	<%@ include file="WEB-INF/jsp/header.jsp" %>

	</div>
	</div>
	
	<div id="main" class="pure-g">
	<div  class="pure-u-1-12">
	  
	<%@ include file="WEB-INF/jsp/menu.jsp" %>
	
	
	</div>
	<div id="maincontent" class="pure-u-11-12">
	
	
	
	</div>
	
	
	
	</div>
	
	
	

</body>
</html>