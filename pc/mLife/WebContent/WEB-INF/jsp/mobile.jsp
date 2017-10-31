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
                            手机充值&nbsp;&nbsp;   
     </h2>
	</div>
	<div>
	<div class="well well-sm">
	<form action="mobile/createBill" method="POST">
	
	 <table class="form form-large orderForm">
	  <tbody style="float:left;">
	  	 <tr>
	  	 
	  	 <c:choose>
	  	 <c:when test="${errMsg!=null}">
	  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
	  	 </c:when>
	  	 </c:choose>
						
	  	 </tr>
         <tr>
         <td class="label-td"><label for="">充值号码：</label></td>
         <td> <input type="text" name="mobileNo" autocomplete="off" maxlength="11" id="mobileNo" class="input-xlarge input-t pushr">
              <span id="isRepeat" style="display: none;color: red;margin-left: 270px;margin-top: -25px;"></span>
              <span class="badge badge-success" style="display: none;margin-left: 270px;margin-top: -20px;width: 20px;"id="areaInfoIcon"> <i class="icon icon-white icon-ok"></i>
			  </span> 
         </td>
         </tr>
		 <tr id="exLargeNumberTr" style="display: none">
			<td class="label-td"><label for="">&nbsp;</label></td>
			<td><span class="font-exLarge orange" id="enlargeNum"></span>
			<span class="inline-help" id="areaInfo"></span></td>
		 </tr>
		 
         <tr id="faceValueTr" style="display: block;">
         	<td class="label-td"><label for="">选择面值：</label></td>
         	<td>
         		<ul class="horizonal valueSelection valueSelection-radio" id="faceValues">
         			<li><label class="radio">10元</label></li>
         			<li><label class="radio">20元</label></li>
         			<li><label class="radio">30元</label></li>
         			<li><label class="radio">50元</label></li>
         			<li><label class="radio">100元</label></li>
         			<li><label class="radio">200元</label></li>
         		</ul>
        	 </td>
         </tr>
         
         <tr id="gcateTr" style="display: none">
        	 <td class="label-td"><label for="">商品名称：</label></td>
         	<td>
         		<span  id="itemName"></span>
         	</td>
         </tr>
         
         <tr id="salePriceTr" style="display: none">
    		<td class="label-td v-middle"><label for="">零售价格：</label></td>
    		<td >
    		<span class="font-large orange" id="salePrice"></span>
    	</td>
		</tr>
		

		
		<tr id="submitTr">
    		<td class="label-td"><label for="">&nbsp;</label></td>
    		<td>
    			<input type="hidden" name="mobileAmount" id="mobileAmount" >
		 		<input type="hidden" name="mobilePrice" id="mobilePrice" >
		 		<input type="hidden" name="mobileItemId" id="mobileItemId" >
				<input type="hidden" name="mobileItemName" id="mobileItemName" >
				<input type="hidden" name="mobileNum" id="mobileNum" >
    			<input type="text" value = "提交订单" disabled="disabled" id="placesubmit" class="btn btn-warning btn-large hide">
        		<input type="submit" class="btn btn-warning btn-large"  disabled="disabled" name="createbill_btn" id="submitdisabled" value="提交订单">
    		</td>
		</tr>

		 
	</tbody>	 
	</table>
	<div>
	
	
	</div>
	
	</form>
	</div>
	</div>
	</div>
	
	</div>
	<script>
	$(".index1").addClass("check");
	
	$("#faceValues .radio").click(function(obj){
		$(".radio").removeClass("selected");
		$(this).addClass("selected");
		
		var value =this.innerText;
		var amount =value.substring(0,(value.length-1));

		var mobileNo=$("#mobileNo").val();
		//不等于空才进来
		if(mobileNojiaoyan()){
			$.ajax({
				type : "GET",
				url : "mobile/getPrice",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {
					"mobileNo" : mobileNo,
					"amount" : amount,
				},
				success : function(data) {
					var mobileitemjson = eval(data);
					//销售价格
					$("#salePriceTr")[0].style.display="block";
					$("#salePrice")[0].innerText =Number(amount).toFixed(2);
					//商品名称
					$("#gcateTr")[0].style.display="block";
					$("#itemName")[0].innerText =mobileitemjson.itemName;
					
					$("#mobilePrice").val(mobileitemjson.advicePrice);
					$("#mobileItemId").val(mobileitemjson.itemId);
					$("#mobileAmount").val(amount);
					$("#mobileItemName").val(mobileitemjson.itemName);
					$("#submitdisabled").attr("disabled",false);
				}
			});
		}
		
		//销售价格
		$("#salePriceTr")[0].style.display="block";
		$("#salePrice")[0].innerText =Number(amount).toFixed(2);
		
	});
	// 判断是否维护
	if($("#wh").length){
		$("#placesubmit").removeClass("hide");
		$("#submitdisabled").addClass("hide");
	}else{
		$("#placesubmit").addClass("hide");
		$("#submitdisabled").removeClass("hide");
	}
	
	$("#mobileNo").keyup(function(){
		mobileNojiaoyan();
	 });
	
	    function mobileNojiaoyan(){
	    	var str =$("#mobileNo").val();
			$("#exLargeNumberTr")[0].style.display="block";
			$("#enlargeNum")[0].innerText =str;
			if(str.length==11){
				var exp = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|177)\d{8}$/
				if (!exp.test($("#mobileNo").val())) {
					$("#isRepeat")[0].style.display="block";
					$("#isRepeat")[0].innerText="请输入正确的电话号码";
				}else{
					$.ajax({
						type : "GET",
						url : "mobile/getArea",
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						data : {
							mobileNo :$("#mobileNo").val()
						},
						success : function(data) {
							var mobileAreaJson = eval(data);
							if(mobileAreaJson.errCode='0'){
								var areaInfo =mobileAreaJson.province+" "+mobileAreaJson.operator;
								$("#isRepeat")[0].style.display="none";
								$("#areaInfoIcon")[0].style.display="block";
								$("#areaInfo")[0].innerText=areaInfo;
								$("#areaInfo")[0].style.display="";
								$("#faceValueTr")[0].style.display="block";
							}
						}
					});
					
					return true;
				}
			}else{
				$("#isRepeat")[0].style.display="block";
				$("#isRepeat")[0].innerText="请输入正确的电话号码";
				$("#areaInfoIcon")[0].style.display="none";
				$("#areaInfo")[0].style.display="none";
				return false;
			};
		} 
	</script>
</body>
</html>