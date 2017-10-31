<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>满集生活缴费平台</title>

</head>
<body>
	<div class="pure-g">
		<div  class="pure-u-1">
		<%@ include file="header.jsp" %>
		</div>
	</div>
	<div id="main" class="pure-g">
		<div  class="pure-u-1-8">
		<%@ include file="menu.jsp" %>
		</div>
		<div id="maincontent" class="pure-u-5-6 mainbackground">
			<div class="alert alert-danger margin-20" role="alert">
			    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			    下单失败(${Error});
			    <a class="btn" href="javascript:history.go(-1);"> <i
							class="fa fa-refresh" style="font-style:inherit"> 重新下单</i>
				</a>
			</div>
		</div>
	</div>
</body>
</html>