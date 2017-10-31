<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</script>
</head>
<body>
	<c:if test="${obj.retData!='数据查询异常!'&&obj.errNum=='0'}">
			<div>
				<span>${obj.retData.city}</span>&nbsp;
				<span>${obj.retData.today.type}</span>
				<span><img alt="" src="${ctx}/images/weather/${obj.retData.today.code}.png"></span>
				<span>${obj.retData.today.hightemp} ~ ${obj.retData.today.lowtemp}</span>
			</div> 
	</c:if>
	<c:if test="${obj.retData=='数据查询异常!'||obj.errNum!='0'}">
		<div >
			
						<span>数据查询异常!</span>	
		</div>
	</c:if>
</body>
</html>
