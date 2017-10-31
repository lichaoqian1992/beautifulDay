<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>满集生活缴费平台 </title>

</head>
<body >
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
	<div style="border-bottom: 1px solid #dcdcdc;margin-bottom: 10px;">
	 <h2>
                            游戏点卡&nbsp;&nbsp;   
     </h2>
	</div>
	<div>
	
	
		<div class="searchGroup">
					<div class="searchBar searchBar-full">
						<div class="input-append">
							<input type="text" name="searchname" id="searchname"
								autocomplete="off">
							<button class="btn btn-warning" id="searchButton" onclick="cardsel()">
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
					<tbody id="cardlist">
					</tbody>
				</table>
	
	
	
	
	
	</div>
	
	
	

	</div>
	
	
	
	
	
	
	</div>
	<script>
	$(".index7").addClass("check");
	
	var carddata ="";
	$(document).ready(
			function() {
				
				//请求卡密数据
				$.ajax({
					type : "GET",
					url : "game/getCardList",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : {},
					success : function(data) {
						var cardlist = eval(data);
						carddata =cardlist;
						
						var gamelisttxt ="";
						gamelisttxt+="<tr>"
						
						$.each(cardlist, function(i, obj) {
							
							var id = obj.itemId;
							var name = obj.itemName.trim();
							
							gamelisttxt+="<td style='width:25%;'><a href='game/cardBill?itemId="+id+"&itemName="+name+"'>"+name+"</a></td>";
							
							if((parseInt(i)+1)%4==0){
								gamelisttxt+="</tr><tr>";
							}
						});
							gamelisttxt+="</tr>";
							$("#cardlist").html(gamelisttxt);
							
						
					}
				});
			});
				
	function cardsel(){
		
		var str =$("#searchname").val();
		alert(str);
		var count=0;
		var refreshcardtxt ="<tr>";
		
		if("" !=str){
		$.each(carddata, function(i, card) {
			
			var name = card.itemName;
			
			if((name.indexOf(str))!=-1){
				count++;
				var id = card.itemId;
				refreshcardtxt+="<td style='width:25%;'><a href='game/gameBill?itemId="+id+"&itemName="+name+"'>"+name+"</a></td>";
				if(parseInt(count)%4==0){
					refreshcardtxt+="</tr><tr>";
				}
			}

			
		});
			if(refreshcardtxt.length<5){
				refreshcardtxt="<th>无查询结果</th>";
			}
		refreshcardtxt+="</tr>";
		$("#cardlist").html(refreshcardtxt);
		
		}else{
			var refreshcardtxt ="";
			refreshcardtxt+="<tr>"
			
				
			$.each(gamelist, function(i, card) {
				
				var id = card.itemId;
				var name = card.itemName;
				
				refreshcardtxt+="<td style='width:25%;'><a href='game/cardBill?itemId="+id+"&itemName="+name+"'>"+name+"</a></td>";
				
				if((parseInt(i)+1)%4==0){
					refreshcardtxt+="</tr><tr>";
				}
			});
			refreshcardtxt+="</tr>";
			$("#cardlist").html(refreshcardtxt);
			
			
			
		}
		
		
	};
			
	
	
	</script>

</body>
</html>