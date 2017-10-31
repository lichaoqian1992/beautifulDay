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
	<div id="main" class="pure-g">
		<div class="pure-u-1-8">
			<%@ include file="menu.jsp"%>
		</div>
		<div id="maincontent" class="pure-u-5-6 mainbackground">
			<div style="border-bottom: 1px solid #dcdcdc; margin-bottom: 10px;">
				<h2>游戏充值&nbsp;&nbsp;</h2>
			</div>
			<div>
				<div class="well well-sm">
				<div class="section">
					<form action="createGameBill" method="post"  id="gamebill">
						<input type="hidden" name="itemId" id="itemId" value=""> 
						<input type="hidden" name="itemName" id="itemName" value="">
						<input type="hidden"  id="itemprice" name="totalAmount" value="">
						
						<table class="form form-large orderForm">
							<tbody>
							<tr>
						  	 <c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose>	
						  	 </tr>
								<tr  id="page_projectTr">
									<td class="label-td"><label for="">商品名称：</label></td>
									<td><label for="" id="goodsName">${gameName }</label></td>
								</tr>

								<tr id="facevalueLi" style="">
									<td class="label-td"><label for="" id="facevalueLabel">充值金额：</label></td>
									<td>
										<ul class="horizonal valueSelection valueSelection-radio"
											id="facevalueUl">
										</ul>
									</td>
								</tr>

								<tr  id="gameaccount">
									<td class="label-td"><label for="">输入帐号：</label></td>
									<td><input type="text" name="rechargeAccount" id="gameAccount"
										class="input-text-big pushr" maxlength="50" autocomplete="off">
									</td>
								</tr>
								<tr style="display: none;"id="gamecomfirmaccount">
									<td class="label-td v-middle"><label for="">确认帐号：</label>
									</td>
									<td><span id="confirrmGame" class="font-exLarge orange"></span>
									</td>
								</tr>

								<tr id="purchaseSumLi" >
									<td class="label-td"><label for="">购买数量：</label></td>
									<td><input name="itemNum" type="text"
										id="txtPurchaseSum" value="1" class="pushr"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" maxlength="3/"
										autocomplete="off"></td>
								</tr>

								<tr style="display: none;" id="gameprice">
									<td class="label-td v-middle"><label for="">订单价格：</label></td>
									<td><input type="hidden" name="saleprice" id="saleprice"
										class="shorter pushr" readonly="readonly"> <span
										class="font-large orange" id="toprice" name="toprice"><small>元</small></span>
									</td>
								</tr>
								<tr style="display: none;" id="gamesubmit">
									<td class="label-td"><label for="">&nbsp;</label></td>
									<td>
									<input type="text" value = "提交订单" disabled="disabled" id="placesubmit" class="btn btn-warning btn-large hide">
										<input type="submit" class="btn btn-large btn-warning disabled" id="btn" value="提交订单" />

									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				</div>
			</div>
		</div>
	</div>


	<script>
	$(".index6").addClass("check");
	
	
	$(document).ready(function() {
				var data =${itemListJson };
				var gamelistdata =eval(data);
				var facevalue =$("#facevalueUl");
				$.each(gamelistdata, function(i, gameitem){
					
					var amount =gameitem.facePriceValue;
					var id =gameitem.itemId;
					
					facevalue
					.append("<li><label class='radio' onclick ='itemsel("+id+","+amount+",this)'>"+amount+"</a></li>");
					
				});
			});
	
	
	function itemsel(id,amount,obj){
	
		$(".radio").removeClass("selected");
		$(obj).addClass("selected");	
		
		$("#itemId").val(id);
		$("#saleprice").val(amount);
		$("#txtPurchaseSum").val("1");
		$("#itemprice").val(amount);
		$("#gameaccount")[0].style.display="";
		$("#purchaseSumLi")[0].style.display="";
		$("#gameprice")[0].style.display="";
		//$("#_saleprice").val(amount);
		$("#toprice")[0].innerText=amount.toFixed(2);
		
		$("#gamesubmit")[0].style.display="";
	};
	
	$("#gameaccount").keyup(function(){
		var str=$("#gameAccount").val();
		$("#gamecomfirmaccount")[0].style.display="";
		$("#confirrmGame")[0].innerText =str;
		
		if(str==""){
			$("#btn").addClass("disabled");
		}else{
			$("#btn").removeClass("disabled");
		}
		
	});
	
	$("#txtPurchaseSum").keyup(function(){
		
		var num=$("#txtPurchaseSum").val();
		var itemprice=$("#itemprice").val();
		var newn =Number(itemprice)*Number(num);
		$("#toprice")[0].innerText=newn.toFixed(2);
		
	});
	
	// 判断是否维护
	if($("#wh").length){
		$("#placesubmit").removeClass("hide");
		$("#btn").addClass("hide");
	}else{
		$("#placesubmit").addClass("hide");
		$("#btn").removeClass("hide");
	}
	
	</script>
</body>
</html>