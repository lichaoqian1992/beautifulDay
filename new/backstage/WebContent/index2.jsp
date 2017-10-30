<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">
	<title>满集总后台数据管理系统</title>
	<link href="${base}/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${base}/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
    <link href="${base}/css/animate.css" rel="stylesheet">
    <link href="${base}/css/style.css?v=2.2.0" rel="stylesheet">
</head>    

<body class="white-bg">

	<div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">M+J</h1>

            </div>
            
           <!--  <h3>欢迎使用 </h3> -->

            <form class="m-t" role="form" action="login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" name="username"required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" name="password" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


                <p class="text-muted text-center"> 
                </p>

            </form>
        </div>
    </div>
	

	<!-- Mainly scripts -->
    <script src="${base}/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/js/bootstrap.min.js?v=3.4.0"></script>

</body>
</html>