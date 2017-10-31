<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>满集生活缴费平台</title>
<script type="text/javascript"  src="${ctx}/js/jquery-1.11.2.min.js" charset="utf-8"></script>

<script type="text/javascript">
	
	
	$(function () {
		$("#logger").click(function () {
			
			$.ajax({
				    type: "get",
				     url: "${ctx}/public/logger",
		         success: function (data) {
		        	 var txt=data;
		 			 window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
		         }
			 });
			$(this).attr("disabled","false");
		}) 
	}) 
	
	
</script>

</head>
<body>

		<h3>公共管理服务</h3>
		<table class="pure-table pure-table-horizontal">
		<tr>
		  <td>日志状态</td>
		  <td>
			<input class="btn btn-warning btn-large" id="logger"  type="button" style=" width: 100px;"  
			value="&nbsp;&nbsp;&nbsp;&nbsp;启动日志&nbsp;&nbsp;&nbsp;&nbsp;">
		  </td>
		  
		  <td>服务状态</td>
		    <td>
		    <input class="btn btn-warning btn-large" id="fw"  type="button"  onclick="javascript:window.location='${ctx}/qmcs/start';"   style=" width: 100px;"  
			value="&nbsp;&nbsp;&nbsp;&nbsp;启动服务&nbsp;&nbsp;&nbsp;&nbsp;">
		    </td>
		  <td>异常状态</td>
		  <td><input type="radio" name="radioyc" value="5">开启</td>
		  
		  <td><input type="radio" name="radioyc" value="6">关闭</td>
		</tr>
		
		</table>
        <div id="btn2" class="sgBtn" style="display: none;">弹窗2(提示)</div>
        <div style="float: left;">
        	<table class="pure-table pure-table-horizontal" border="1">
        	<thead>
        		<tr>
        			<th colspan="3">PC版本</th>
        		</tr>
        		<tr>
        			<th style="width: 100px;">功能模块</th>
        			<th>状态</th>
        			<th>操作</th>
        		</tr>
        	</thead>
        	<tbody id="content"></tbody>
        </table>
        </div>
        <div style="margin-left: 400px;">
        	<table class="pure-table pure-table-horizontal" border="1">
        	<thead>
        		<tr>
        			<th colspan="3">APP版本</th>
        		</tr>
        		<tr>
        			<th style="width: 100px;">功能模块</th>
        			<th>状态</th>
        			<th>操作</th>
        		</tr>
        	</thead>
        	<tbody id="appcontent"></tbody>
        </table>
        </div>
</body>
<script type="text/javascript">
	$(function(){
		showMessage();
	});
	function showMessage(){
		var billtxt,APPbilltxt;
		//查询数据库各模块的状态，是否开启
		$.ajax({
			type:"GET",
			url:"${ctx}/getStatus",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success:function(data){
				for(var i=0;i<data.length;i++){
					bill = data[i];
					if(bill.codetype == "SWITCH"){
						billtxt +="<tr><th class='pcodd'>"
						    +bill.codename+
							"</th><th class='oId'>"
							+bill.value+
							"</th><th>"
							+"<a href='javascript:;' class='showDetail'>切换</a> "+
							"</th></tr>"
					}else if(bill.codetype == "APPSWITCH"){
						APPbilltxt +="<tr><th class='pcodd'>"
						    +bill.codename+
						    "</th><th class='oId'>"
						    +bill.value+
							"</th><th>"
							+"<a href='javascript:;' class='showDetail'>切换</a> "+
							"</th></tr>"
					}
				}
				$("#content").html(billtxt);
				$("#appcontent").html(APPbilltxt);
			}
		});
	}
	//改变开关的状态
	function changeStatus(codename,value){
		$.ajax({
			type:"GET",
			url:"${ctx}/changeStatus",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data:{
				value:value,
				codename:codename
			},
			success : function(data){
				if(data == "1"){
					showMessage();
				}
			}
		});
	}
	$(document).on('click','.showDetail',function(){
		var codename = $(this).parent().parent().find('.pcodd').text().trim();
		var value = $(this).parent().parent().find('.oId').text().trim();
		if(value == "on"){
			value = "off";
		}else if(value == "off"){
			value = "on";
		}
		changeStatus(codename,value);
	});
</script>
</html>