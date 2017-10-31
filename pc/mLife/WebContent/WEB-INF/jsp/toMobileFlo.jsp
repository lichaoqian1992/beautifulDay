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
				<h2>流量充值&nbsp;&nbsp;</h2>
			</div>
			<div>
				<div class="well well-sm">
					<form action="mobileFlow/flowCreateBill" method="get">
						<table class="form form-large orderForm">
							<tbody style="float: left;">
							<tr>
						  	 <c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose>	
						  	 </tr>
								<tr>
									<td class="label-td"><label for="">充值号码：</label></td>
									<td>
									<input type="text" name="mobileNo" autocomplete="off"
										maxlength="11" id="mobileNo"
										class="input-xlarge input-t pushr"> 
										<span
										id="isRepeat"
										style="display: none; color: red; margin-left: 270px; margin-top: -25px;">
										</span>
										<span class="badge badge-success"
										style="display: none; margin-left: 270px; margin-top: -20px; width: 20px;"
										id="areaInfoIcon">
										 <i class="icon icon-white icon-ok"></i>
									</span>
									</td>
									
								</tr>

								<tr id="exLargeNumberTr" style="display: none">
									<td class="label-td"><label for="">&nbsp;</label></td>
									<td><span class="font-exLarge orange" id="enlargeNum"></span>
										<span class="inline-help" id="areaInfo"></span></td>
								</tr>

								<tr id="faceValueTr" style="display: block;">
									<td class="label-td">
									<label for="">选择面值：</label></td>
									<td width="200px;">
										<ul class="horizonal valueSelection valueSelection-radio"
											id="faceValues">
											<li><label class="radio">20M</label></li>
											<li><label class="radio">50M</label></li>
											<li><label class="radio">100M</label></li>
											<li><label class="radio">200M</label></li>
											<li><label class="radio">500M</label></li>
											<li><label class="radio">1G</label></li>
										</ul>
									</td>
								</tr>
								<tr id="gcateTr" style="display: none">
									<td class="label-td"><label for="">商品名称：</label></td>
									<td><span id="itemName"></span></td>
								</tr>

								<tr id="salePriceTr" style="display: none">
									<td class="label-td v-middle"><label for="">零售价格：</label></td>
									<td><span class="font-large orange" id="salePrice"></span>
									</td>
								</tr>
								<tr id="submitTr">
									<td class="label-td"><label for="">&nbsp;</label></td>
									<td>
									
										
										<input type="hidden" name="itemId" id="itemId"  autocomplete="off">
										<input type="text" value = "提交订单" disabled="disabled" id="placesubmit" class="btn btn-warning btn-large hide">
										<input type="submit" class="btn btn-warning btn-large"
										
										disabled="disabled" name="createbill_btn" id="submitdisabled"
										value="提交订单">
										</td>
								</tr>
							</tbody>
						</table>
						<div></div>
					</form>
				</div>
			</div>
		</div>

		<div id="btn2" class="sgBtn" style="display: none;">弹窗2(提示)</div>

		<script type="text/javascript">
			$(".index2").addClass("check");

		
			
			
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
								type:"get",
								url:"${ctx}/mobileFlow/getPhoneInfo",
								//contentType:"application/json;charset=utf-8",
								//数据格式是json串，商品信息
								 data :'mobileNo='+str,
								 success:function(data){//返回json结果
									 
									  var d=data;
							 		  var a=d.substring(data.length-2,data.length);
							 		  //设置商品运营商
							 		  if(a=='移动'){
							 			  $("#faceValues").empty();
							 			  var li='<li><label class="radio">30M</label></li>'
							 			  		+'<li><label class="radio">70M</label></li>'
							 			  		+'<li><label class="radio">150M</label></li>'
							 			  		+'<li><label class="radio">500M</label></li>'
							 			  		+'<li><label class="radio">1G</label></li>';
							 			  $("#faceValues").append(li);
							 		  }
							 		  
							 		  if(a=='电信'){
							 			  
							 			 $("#faceValues").empty();
							 			  var li='<li><label class="radio">30M</label></li>'
							 			  		+'<li><label class="radio">50M</label></li>'
							 			  		+'<li><label class="radio">100M</label></li>'
							 			  		+'<li><label class="radio">200M</label></li>'
							 			  		+'<li><label class="radio">500M</label></li>'
							 			  		+'<li><label class="radio">1G</label></li>';
							 			  $("#faceValues").append(li);
							 			  
							 		  }
							 		  if(a=='联通'){
							 			 $("#faceValues").empty();
							 			  var li='<li><label class="radio">20M</label></li>'
							 			  		+'<li><label class="radio">50M</label></li>'
							 			  		+'<li><label class="radio">100M</label></li>'
							 			  		+'<li><label class="radio">200M</label></li>'
							 			  		+'<li><label class="radio">500M</label></li>'
							 			  		+'<li><label class="radio">1G</label></li>';
							 			  $("#faceValues").append(li);
							 		  }
							 			$("#gcateTr")[0].style.display="none";
										$("#itemName")[0].innerText ="";
										
										$("#salePriceTr")[0].style.display ="none";
										$("#salePrice")[0].innerText ="";

										$("#isRepeat")[0].style.display="none";
										
										$("#areaInfoIcon")[0].style.display="block";
										
										$("#areaInfo")[0].innerText=data;
										
										$("#areaInfo")[0].style.display="";
										
										$("#faceValueTr")[0].style.display="block";
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
			
			    
			    
				$("#faceValues").on('click',".radio",function(obj){
					var price = 0;
					$(".radio").removeClass("selected");
					$(this).addClass("selected");
					
					var value =$(this).text();
					if(value == "20M"){
						price = 3;
					}else if(value == "50M"){
						price = 6;
					}else if(value == "100M"){
						price = 15;
					}else if(value == "200M"){
						price = 25;
					}else if(value == "500M"){
						price = 45;
					}else if(value == "1G"){
						price = 50;
					}
					var mobileNo=$("#mobileNo").val();
					
					function checkNumber(){
						var flag;
						var str =$("#mobileNo").val();
						if(str.length==11){
							var exp = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|177)\d{8}$/
							if (!exp.test($("#mobileNo").val())) {
								$("#isRepeat")[0].style.display="block";
								$("#isRepeat")[0].innerText="请输入正确的电话号码";
								flag = false;
							}else{
								flag = true;
							}
						}
						return flag;
					}
					//不等于空才进来
					if(checkNumber()){
						//通过ajax异步加载技术拿到商品的的价格
						$.ajax({
							type : "get",
							url : "${ctx}/mobileFlow/flowItems",
							data : 'mobileNo=' + mobileNo + '&flow='+value,
							complete : function(data) {
								var jsonData = eval("(" + data.responseText + ")"); //才可以通过jsonData.name访问，而且这种情况下，需要是complete而不是success  
								
								if (jsonData == 0) {
									//商品名称
									$("#gcateTr")[0].style.display="block";
									$("#itemName")[0].innerText ="暂无货源";
									//销售价格
									$("#salePriceTr")[0].style.display="block";
									$("#salePrice")[0].innerText =Number(0).toFixed(2);
									
									$("#submitdisabled").attr("disabled",true);

								} else {
									//销售价格
									$("#salePriceTr")[0].style.display="block";
									$("#salePrice")[0].innerText =Number(jsonData.advicePrice).toFixed(2);
									//商品名称
									$("#gcateTr")[0].style.display="block";
									
									$("#itemName")[0].innerText =jsonData.itemName;
									
								 	$("#itemId").val(jsonData.itemId);
								 	
									$("#submitdisabled").attr("disabled",false);
								}
							}
						});
					}else{
						//当没有填写电话号码的时候，点击流量包也有一个价格
						$("#salePriceTr")[0].style.display ="block";
						$("#salePrice")[0].innerText = Number(price).toFixed(2);
					}
				});
			    
			    
			    
			    
			    
			    
			    
				// 判断是否维护
				if($("#wh").length){
					$("#placesubmit").removeClass("hide");
					$("#submitdisabled").addClass("hide");
				}else{
					$("#placesubmit").addClass("hide");
					$("#submitdisabled").removeClass("hide");
				}
		</script>
</body>
</html>