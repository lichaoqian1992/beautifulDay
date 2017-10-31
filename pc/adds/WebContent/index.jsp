<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>advertisements</title>
</head>
<body>
<a href ="teeee">hello</a>
<a href ="getFirstPagePara">gaaaa</a>
<img src="/upload/1115.jpg"/>
 <input id="upload-pic" class="file" name="files" type="file" multiple data-preview-file-type="any" data-upload-url="addAdds">

<br>

<object type="application/x-shockwave-flash" style="outline:none;" data="http://cdn.abowman.com/widgets/hamster/hamster.swf?" width="288" height="225">
<param name="movie" value="http://cdn.abowman.com/widgets/hamster/hamster.swf?">
<param name="AllowScriptAccess" value="always">
<param name="wmode" value="opaque">
</object>


  <input type="file" name="file">  
<form action="test2" method="post" enctype="multipart/form-data">  
    选择文件:
    <input id="upload-pic" class="file" name="files" type="file" multiple data-preview-file-type="any" >
    
    <input type="text" name="title">
    
    <input type="submit" value="提交">   
</form>  





</body>
</html>