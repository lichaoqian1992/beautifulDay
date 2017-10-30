<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">
       <!--  <meta charset="utf-8"> -->
        <title>满集网数据总后台</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
<!--         <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'> -->   
     	<link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">

       

    </head>

    <body>

        <div class="page-container">
            <h1>满集网数据总后台</h1>
            <form action="login" method="post">
                <input type="text" name="username" class="username" placeholder="Username" required="">
                <input type="password" name="password" class="password" placeholder="Password"required="">
                <button type="submit">登录</button>
                <div class="error"><span>+</span></div>
            </form>
            
        </div>
        
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>

    </body>

</html>

