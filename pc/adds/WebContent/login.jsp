<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  <title>登陆</title>
  <link rel="stylesheet" type="text/css" href="${base}/css/login.css">
</head>
<body>
  <div class="a"></div>
  <div class="v">
    <h2>Welcome<span><i>·</i><i>·</i><i class="i">·</i></span></h2>
    <div class="b" id="b">
      <form action="login" method="post">
      	<input type="text" class="input" name="username" placeholder="Username" />
      <br />
      <input type="password" name="password" class="input" placeholder="Password" />
      <button type="submit" class="submit">Login</button>
      </form>
    </div>
  </div>
  <script src="${base}/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
      $(".submit").click(function(){
        $(".b").css("transform","translate(0px,-260px)");
        $(".b").css("opacity","0");
        $("h2").css("transform","translate(0px,130px)");
        $("h2 span").css("opacity","1");
        $("h2 span i").css("animation-play-state","running");
        window.setTimeout(function(){ location.href="toMain";}, 5000);
      });
    });
  </script>
</body>
</html>