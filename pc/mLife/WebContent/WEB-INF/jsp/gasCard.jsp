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
				<h2>加油卡&nbsp;&nbsp;</h2>
			</div>
			<div class="well well-sm">

				<div class="section">
					<form action ="gasCard/createBill" method="POST" >
						<table class="form form-large">

							<tr>
						  	 <c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose>	
						  	 </tr>

							<tr id="operatorstat">
								<td class="label-td"><label for="">选择供应商：</label></td>
								<td>
									<ul class="horizonal valueSelection valueSelection-radio"
										id="page_project">
										<li><label value="cnpc" for="cnpc"
											class="gascard">中石油</label></li>
										<li><label value="sinopec" for="sinopec" class="gascard">中石化</label></li>
									</ul> 
									<input type="hidden" name="operator" id="operator"  >
									<input type="hidden" name="itemId" id="itemId"  >
									<input type="hidden" name="itemName" id="itemName"  >
								</td>
							</tr>

							<tr id="areastat" style="display:none">
								<td class="label-td"><label for="">选择地区：</label></td>
								<td><select style="width: 254px;" class="pushr valid"
									id="area" name="area">
										<option value="全国">全国</option>
								</select></td>
							</tr>

							<tr style="display:none" id="faceValueTr">
								<td class="label-td"><label for="">选择面值：</label></td>
								<td>
									<ul id="faceValues"
										class="horizonal valueSelection valueSelection-radio">
									</ul>
								</td>
							</tr>


							<tr>
								<td class="label-td"><label for="">加油卡卡号：</label></td>
								<td><input type="text" maxlength="19" style="width: 240px;"
									class="input-text-big" name="gasCardNo" autocomplete="off"
									id="cardNo"><span id="queryCardInfo"></span> <a
									class="label label-info" id="queryGasCardInfo"
									href="javascript:void(0);" onclick="gascardqueryinfo()">点击查看</a></td>
							</tr>
							<tr id="comfirmcardno" style="display:none">
								<td class="label-td"><label for="">确认加油卡卡号：</label></td>
								<td><span class="font-exLarge orange" id="enlargeNum"></span></td>
							</tr>
							
							<tr id="customerTelLi">
								<td class="label-td"><label for="customerTel">持卡人姓名：</label></td>
								<td><input type="text" autocomplete="off"
									style="width: 240px;" name="gasCardName" id="customerName"> <!-- 设置输入框只能输入数字 -->
								</td>
							</tr>
							<tr id="customerTelLi">
								<td class="label-td"><label for="customerTel">手机号码：</label></td>
								<td><input type="text" autocomplete="off"
									style="width: 240px;" name="gasCardTel" id="customerTel" maxlength="11" 
									onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <!-- 设置输入框只能输入数字 -->
									<span style="display: none;color: red;margin-left: 10px;" id="showNo"></span></td>
							</tr>
							<!-- 显示在电话号码下面的内容    显示电话号码 -->
							<tr id="comfirmtelno" style="display:none">
								<td class="label-td"><label for="">确认手机号码：</label></td>
								<td><span class="font-exLarge orange" id="etelNum"></span></td>
							</tr>
							
							<tr id="buyNumber">
								<td class="label-td"><label for="">订单金额：</label></td>
								<td><span class="font-exLarge orange" id="enlargemoney"></span>
								<input id="myMoney" name="rechargeAmount" type="hidden"><!-- 加油卡充值金额 -->
								</td>
							</tr>

							<tr style="display: none" id="submitTr">
								<td class="label-td"><label for="">&nbsp;</label></td>
								<td>
<!-- 								<input type="text" value = "提交订单" disabled="disabled" id="placesubmit" class="btn btn-warning btn-large hide"> -->
								<input type="submit" value="提交订单" id="createbill_btn"
									name="createbill_btn" 
									class="btn btn-warning btn-large disabled"></td>
							</tr>
						</table>
					</form>
					
				</div>

			</div>
							<div class="alert alert-info">
						<h4>服务说明：</h4>
						<ul>
							
							<li>1.支持全国中石油、中石化加油卡充值</li>
							<li>2.为保证及时通知到您，请填写接收通知手机号码</li>

						</ul>
					</div>
						</div>
		</div>
		<script>
	$(".index9").addClass("check");
	//获得面额列表
	function getitems(){
		var facevalue =$("#faceValues");
		$.ajax({
	        type:"GET",   
	        url:"gasCard/getCom",   
	        dataType:"json",        
	        contentType:"application/json; charset=utf-8",                 
	        data:{operator :$("#operator").val()} ,   
	        success:function(data){
	        	var info =eval(data);
				var tmp =new Array();
				for(var k=0;k<info.length;k++){
					tmp[k] =info[k];
				}
	        	for(var i=0;i<tmp.length;i++){
	        		for(var j=0;j<tmp.length-1;j++){
	        			var itemi =tmp[j];
	        			var itemj =tmp[j+1];
		        		var amounti =itemi.itemName.replace(/[^0-9]/ig,""); 
		        		var amountj =itemj.itemName.replace(/[^0-9]/ig,""); 
		        		if(parseInt(amounti)>parseInt(amountj)){
		        			tmp[j] =itemj;
		        			tmp[j+1]=itemi;
		        		}
	        		}
	        	}
	        	facevalue.empty();
	        	
	        	$.each(tmp, function(i, item){
	        		var id =item.itemId;
	        		var name =item.itemName;
	        		$("#itemId").val(id);
	        		$("#itemName").val(name);
	        		var amount =name.replace(/[^0-9]/ig,"");
	        		facevalue
					.append("<li><label class='radio' onclick ='itemsel("+id+","+amount+",this)'>"+amount+"</label></li>");
				});
	        }
	}); 
	}
	
	//查询货源
	function itemsel(id ,amount,obj){
		$(".radio").removeClass("selected");
		$(obj).addClass("selected");
		$("#enlargemoney")[0].innerText="￥"+Number(amount).toFixed(2); //设置两位小数
		$("#myMoney").val(Number(amount));
		
	}
	
	//选则面额
	var old = false;
	$(".gascard").click(function(){				//$(".gascard")表示对所有的 名为gascard的控件都有实用 
		$(".gascard").removeClass("selected");//移除选择事件
		$(this).addClass("selected");//设置当前选择的选择事件 
		old = true;
		$("#operator").val($(this).attr("value"));
		
		
		getitems();//查询加油卡的面值 10、20、30、、、 
		
		$("#faceValueTr")[0].style.display="";
		
	});
	var a = false;
	$("#faceValues").click(function(){
		a = true;
	});
	
	//卡号查询
	function gascardqueryinfo(){
		var gascardno=$("#cardNo").val();
		if(""==gascardno){
			alert("请输入卡号");
		}else{
			$.ajax({
		        type:"GET",   
		        url:"gasCard/queryCardInfo",   
		        dataType:"json",        
		        contentType:"application/json; charset=utf-8",                 
		        data:{
		        	operator:$("#operator").val(),
		        	gasCardNo:$("#cardNo").val(),
		        } ,   
		        success:function(data){
		        	var info =eval(data);
		        	$("#queryCardInfo")[0].innerText=info.cardState;
		        	//$("#queryGasCardInfo")[0].style.display="none";
		        	
		        }
		}); 
		}
	};
	
	
	$("#cardNo").keyup(function(){
// 		if(old){
// 			if(a){
				$("#comfirmcardno")[0].style.display="";
				
				var str =$("#cardNo").val();
				
				$("#enlargeNum")[0].innerText =str;
// 				return false;
// 			}else{
// 				alert("请先选择充值金额");
// 				$("#cardNo").val("");
// 				a = true;
// 			}
// 		}else{
// 			alert("请先选择供应商");
// 			$("#cardNo").val("");
// 			old = true;
// 		}
	})
	//校验手机号码 
	$("#customerTel").keyup(function(){
		//1.设置显示号码的控件可见
		$("#submitTr")[0].style.display="";
		//2.把电话号码放到控件中去 
		var telNo = $("#customerTel").val();//得到输入的电话号码
		$("#etelNum")[0].innerText=telNo;
		//3.使用正则表达式校验输入的电话号码是否正确 
		if(telNo.length == 11){
			var exp = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;//电话号码以13、14、15、17、18开始
			//如果输入的电话号码不符合这个规则，则提示请输入正确的电话号码 
			if(!exp.test(telNo)){
				$("#createbill_btn").addClass("disabled");
				$("#showNo")[0].style.display = "";
				$("#showNo")[0].innerText = "电话号码输入有误"
				$("#comfirmtelno")[0].style.display="none";
			}else{
				$("#showNo")[0].style.display = "";
				$("#showNo")[0].innerText = "";
				$("#createbill_btn").removeClass("disabled");
			}
		}else{
			$("#createbill_btn").addClass("disabled");
			$("#showNo")[0].style.display = "";
			$("#showNo")[0].innerText = "电话号码输入有误";
		}
	});
	//提交订单
	$("#createbill_btn").click(function(){
		if($("#cardNo").val() == null || "" == $("#cardNo").val()){
			var txt=  "请填写加油卡卡号";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		if(old){
			if(!a){
				var txt=  "请选择充值金额";
				window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
				a = true;
				return false;
			}
		}else{
			var txt=  "请选择供应商";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			old = true;
			return false;
		}
		
	});
	
	// 判断是否维护
	if($("#wh").length){
		$("#placesubmit").removeClass("hide");
		$("#createbill_btn").addClass("hide");
	}else{
		$("#placesubmit").addClass("hide");
		$("#createbill_btn").removeClass("hide");
	}
	
	</script>
</body>
</html>