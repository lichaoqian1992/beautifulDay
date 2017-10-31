<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>满集生活缴费平台</title>
</head>
<body>

	<div class="pure-g">
		<div class="pure-u-1">
			<%@ include file="header.jsp"%>
		</div>
	</div>
	<!-- 弹出层 -->
	<div id="win-wrapper" style="overflow-y :auto;overflow :yes;">
		<div id="win" style="margin-top: 300px;width: 200px;height: 200px;">
		<div class="win-title"><span>提示</span></div>
			<div class="win-body">
				<p>正在操作，请稍后。。。</p>
			</div>
	  	</div>
  	</div>
	<div id="main" class="pure-g">
		<div class="pure-u-1-8">
			<%@ include file="menu.jsp"%>
		</div>
		<div id="maincontent" class="pure-u-5-6 mainbackground">
			<div style="border-bottom: 1px solid #dcdcdc; margin-bottom: 10px;">
				<h2>游戏充值&nbsp;&nbsp;</h2>
			</div>
			<div>

				<div class="searchGroup">
					<div class="searchBar searchBar-full">
						<div class="input-append">
							<input type="text" name="searchname" id="searchname"
								autocomplete="off">
							<button class="btn btn-warning" id="searchButton" onclick="gamesel()">
								<i class="icon icon-white icon-search" ></i>
							</button>
						</div>
						<input type="hidden" id="cid" name="cid" value="54092">
					</div>
				</div>

				<table class="table table-bordered centered table-game-temp01"
					name="tabDiv">
					<thead>
						<tr>
							<th colspan="4" style="text-align: center"><h3>游戏列表</h3></th>
						</tr>
					</thead>
					<tbody id="gamelist">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
	$(".index6").addClass("check");
	var gamedata ="";
	$(document).ready(
			function() {
				//请求游戏充值数据
				$("#win-wrapper").fadeIn();
				$.ajax({
					type : "GET",
					url : "game/getList",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : {},
					success : function(data) {
						$("#win-wrapper").fadeOut();
						var gamelist = eval(data);
						gamedata =gamelist;
						
						var gamelisttxt ="";
						gamelisttxt+="<tr>"
						
							
						$.each(gamelist, function(i, obj) {
							var id = obj.gameId;
							var name = trim(obj.gameName);
							
							gamelisttxt+="<td style='width:25%;'><a href='game/gameBill?gameId="+id+"&gameName="+name+"'>"+name+"</a></td>";
							
							if((parseInt(i)+1)%4==0){
								gamelisttxt+="</tr><tr>";
							}
						});
							gamelisttxt+="</tr>";
						$("#gamelist").html(gamelisttxt);
						
					}

				});
			});
	
	$("#searchname").keyup(function(){
		
		 gamesel();
		
		
	});
	
	
	function gamesel(){
		
		var str =$("#searchname").val();
		var refreshgametxt ="<tr>";
		var count =0;
		if("" !=str){
			$.each(gamedata, function(i, game) {
				var name = game.gameName;
				
				if((name.indexOf(str))!=-1){
					count++;
					var id = game.gameId;
					refreshgametxt+="<td style='width:25%;'><a href='game/gameBill?gameId="+id+"&gameName="+name+"'>"+name+"</a></td>";
					
					if(parseInt(count)%4==0){
						refreshgametxt+="</tr><tr>";
					}
				}
				
			});
			
			if(refreshgametxt.length<5){
					refreshgametxt="<th>无查询结果</th>";
				}
			refreshgametxt+="</tr>";
			$("#gamelist").html(refreshgametxt);
		}else{
			
			
			refreshgametxt ="<tr>";
			
			$.each(gamedata, function(i, game) {
				var id = game.gameId;
				var name = game.gameName;
				
				refreshgametxt+="<td style='width:25%;'><a href='game/gameBill?gameId="+id+"&gameName="+name+"'>"+name+"</a></td>";
				
				if((parseInt(i)+1)%4==0){
					refreshgametxt+="</tr><tr>";
				}
			});
				refreshgametxt+="</tr>";
			$("#gamelist").html(refreshgametxt);
			
			
		}
		
		
	};
				
	function trim(str){ //删除左右两端的空格　　
	    return str.replace(/(^\s*)|(\s*$)/g, "");
	}	
	</script>

</body>
</html>