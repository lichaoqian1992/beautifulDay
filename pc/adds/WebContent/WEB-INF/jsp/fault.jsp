<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html manifest="framework.appcache">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告管理系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body class="gray-bg">
    
    <div class="middle-box text-center animated fadeInDown">
        <h1>500</h1>
        <h3 class="font-bold">服务器内部错误</h3>
        <div class="error-desc">
           <object type="application/x-shockwave-flash" style="outline:none;" data="http://cdn.abowman.com/widgets/hamster/hamster.swf?" width="288" height="225">
                <param name="movie" value="http://cdn.abowman.com/widgets/hamster/hamster.swf?">
                <param name="AllowScriptAccess" value="always">
                <param name="wmode" value="opaque">
            </object>
            <br/>您可以返回主页看看
            <br/><a href="toMain" class="btn btn-primary m-t">主页</a>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="${base}/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/js/bootstrap.min.js"></script>
    <script src="${base}/js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${base}/js/hplus.js?v=2.2.0"></script>
    <script src="${base}/js/plugins/pace/pace.min.js"></script>

</body>

</html>
