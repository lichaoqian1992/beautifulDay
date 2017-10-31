<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>满集生活缴费平台</title>
<style type="text/css">
label{
width:100px;
text-align:right;
margin-right:10px !important;
margin-left:10px !important;
}
	
	.font-exLarge1 {
    font-size: 30px;
    font-weight: bold;
    line-height: 54px;
}
</style>
	
</head>
<body>
	<div class="pure-g">
	<div  class="pure-u-1">
	<%@ include file="../header.jsp" %>
	</div>
	</div>
	<div id="main" class="pure-g">
	<div  class="pure-u-1-8">
	<%@ include file="../menu.jsp" %>
	</div>
	<div id="maincontent" class="pure-u-5-6 mainbackground">
		 <div style="border-bottom: 1px solid #dcdcdc;margin-bottom: 10px;">
				 <h2>
			                           有线电视充值&nbsp;&nbsp;   
				     
			     </h2>
	 	</div>
	 <div  class="well well-sm" >
	 <form name="form1" class="pure-form pure-form-aligned"   action="${ctx}/catv/catvCreateBill" onsubmit="return GKsubmit()">
	 			<div> <c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="padding-left:60px;color:#F00; width:100%; text-align:left;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose></div>
				<div>
				       <label>区 域:</label>
				       <select style="width: 100px;padding-top: 5px;"  id="province" name="province">
								<option value="-1">--省份--</option>
								<option value="v2070">重庆</option>
				       			<option value="v2129">甘肃</option>
								<option value="v1955">浙江</option>
								<option value="v2089">天津</option>
								<option value="v2101">安徽</option>
								<option value="v1953">北京</option>
								<option value="v2282">湖北</option>
								<option value="v2159">山西</option>
								<option value="v2210">山东</option>
								<option value="v2171">辽宁</option>
								<option value="v1967">河南</option>
								<option value="v1986">海南</option>
								<option value="v2043">内蒙古</option>
								<option value="v2198">河北</option>
								<option value="v2091">贵州</option>
								<option value="v1918">湖南</option>
								<option value="v2142">新疆</option>
								<option value="v1933">四川</option>
								<option value="v1988">西藏</option>
								<option value="v1904">黑龙江</option>
								<option value="v2228">广东</option>
								<option value="v2280">上海</option>
								<option value="v2186">江西</option>
								<option value="v2260">宁夏</option>
								<option value="v2269">陕西</option>
								<option value="v2118">广西</option>
								<option value="v2056">江苏</option>
								<option value="v2072">云南</option>
								<option value="v1892">吉林</option>
								<option value="v2250">青海</option>
								<option value="v2297">福建</option>
				       </select>
						<select style="width: 100px;padding-top: 5px;"  id="city" name="city">
						<option selected="selected"  >--城市--</option>
						</select>
				 </div>
				 <br>
				 <div>
						<label>单 位:</label>
						<select style="width: 200px;height: 30px;"name="itemId"  id="area">
						<option value="-1" selected="selected" >--请选择缴费单位--</option>
						</select> 
						<font color='red'><span id="errs1"></span></font>
				</div>
				<br>
			<!--rechargeAccount	String 	必须 	298377889		缴费账号或户号，卡号 号码为1到20位数字 -->
			<div>
				<label>账 号:</label>
					<div style="display:inline-block;">
					<input type="text" class="input-text" value="" autocomplete="off"  name="rechargeAccount"
						placeholder="账号/卡号" style="height: 30px;" id="rechargeAccount"
						maxlength="20"/> 
						<br>
				
					</div>
						<font color='red'><span id="errs"></span></font>
						
				 	 <div id="exLargeNumberTr" style="display: none;margin-top: 10px; margin-left: 110px;">
				            	<!-- 展示号码 -->
				      <span class="font-exLarge orange"  id="enlargeNum"></span>
		             </div>
						
			</div>
				
	     	<br>
			<!--充值金额，支持两位小数 -->
						<div>
				        <label>金 额:</label>
						<div style="display:inline-block;" >
							<input type="text" class="input-text" value="" autocomplete="off" name="rechargeAmount"
								placeholder="任意充" style="height: 30px; width: 100px;"  id="rechargeAmount"
								maxlength="8"
								data-original-title="可购买范围:150-6000" 
								onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"
								onafterpaste="this.value=this.value.replace(/[^\d]/g,'')" 
								/>
								<font color="red"><span id="errs2">*可购买范围:150-6000元</span></font>
								<br>
						</div>
						
						</div>						
						<br>
						<div>
				         <label>零售价格:</label>
					 	  <div id="exLargeNumberTr1" class="input-content" style="display: none;margin-left: 115px;display: inline;margin-top: -35px;">
					            	<!-- 展示号码 -->
					      <span class="font-exLarge1 orange" style="display: inline;"  id="enlargeNum1"></span>
			              </div>
						</div>
						<br>
						<div style="margin-left: 80px;">
						 <div class="alert alert-info">
							<h4>服务说明：</h4>
							<ul>
								<li>1.尊敬的用户，本次充值可用于有线电视的全部业务种类，可能激活处于欠费停止的业务。</li>
								<li>2.如需了解业务办理情况请拨打023-96868查询。</li>
								<li>3.目前支持的地区重庆、山东、辽宁、陕西、江苏、浙江。</li>
							</ul>
					     </div>
						
						</div>
			<div class="row cl" style="margin-left: 120px;" >
				<div class="col-9 col-offset-3">
				<input type="text" value = "提交订单" disabled="disabled" id="placesubmit" class="btn btn-warning btn-large hide">
				<input class="btn btn-warning btn-large" id="submitdisabled"  type="submit" style=" width: 100px;text-align:center;"  value="提交订单">
				</div>
			</div>
			<br>
		</form>
	</div>
	</div>
	</div>
    <div id="btn2" class="sgBtn" style="display: none;">弹窗2(提示)</div>
    
<script type="text/javascript">

//账号
$("#rechargeAccount").keyup(function(){
	/* 展示输入信息 */
	var str=$("#rechargeAccount").val();
	$("#exLargeNumberTr")[0].style.display="block";
	$("#enlargeNum")[0].innerText =str;
})
//金额
$("#rechargeAmount").keyup(function(){
	/* 展示输入信息 */
	var str=$("#rechargeAmount").val();
	if(str.indexOf('.') > 0) {
	
	} else {
		str=str+".00";
	}	
	
	$("#exLargeNumberTr1")[0].style.display="block";
	$("#enlargeNum1")[0].innerText =str;
	
})
	$(".index5").addClass("check");
	$(document).ready(function() {
				$("#province").on("change",function() {
						            $("#city").empty();
						            provincevid = $(this).val();
						            if (provincevid == '') {
						                return;
						            }
						                var provinceName=$(this).find("option:selected").text(); //获取select选择的Text :
						            	
						                $("#city").append('<option selected="selected"  >--城市--</option>');
						            
						            	switch (provinceName) {
						            	
										case "重庆":
							                $("#city").append('<option value="">'+provinceName+'</option>');
											break;
											
										case "北京":
							                $("#city").append('<option value="">'+provinceName+'</option>');

											break;
										case "上海":
							                $("#city").append('<option value="">'+provinceName+'</option>');

											break;
										case "天津":
							                $("#city").append('<option value="">'+provinceName+'</option>');

											break;
										case "内蒙古":
											
							                $("#city").append('<option value="">'+provinceName+'全区</option>');

											break;
											
										case "新疆":
											
							                $("#city").append('<option value="">'+provinceName+'全区</option>');

											break;
											
										case "西藏":
							                $("#city").append('<option value="">'+provinceName+'全区</option>');

											break;
											
										case "宁夏":
											
							                $("#city").append('<option value="">'+provinceName+'全区</option>');

											break;
											
										case "上海":
							                $("#city").append('<option value="">'+provinceName+'</option>');

											break;
										case "全国":
											
							                $("#city").append('<option value="">'+provinceName+'</option>');

											break;
											
										default:
											
							                $("#city").append('<option value="">'+provinceName+'全省</option>');
											
											break;
										}
						            	
						        });
							$("#city").trigger("chosen:updated");

				$("#city").on("change",function() {
							$("#area").empty();
							$("#area").append('<option value="-1">--请选择缴费单位--</option>');
							//ajax 异步加载缴费单位
							$.ajax({
								type : "get",
								url  : "${ctx}/catv/unitsList",
								data : 'provinceVid=' + $("#province").val(),
								success : function(data){
									if(data=='[]'){
										$("#area").empty();
										$("#area").append('<option value="-1">--该地区服务未开通--</option>');
										
										var txt=  "该地区服务未开通";
										window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
										return false;
									}
									var d=eval(data);//解析  
							    	var select = document.getElementById("area");
							    	select.length = 1;//设置只能选择一个
							    	select.options[0].selected = true;//设置默认选中第一个
								    $(d).each(function(index,entity){
								    	
								    	var option = document.createElement("option");
								    	option.setAttribute("value",entity['vid']);//设置option属性值
								    	//截取公司名字和充值类型
								    	var arry=new Array();
								    	var name =entity['vname'];
								    	    arry=name.split(" ");
								    	    var danwei;
								    	    var neixin;
								    	    if(arry.length>4){
								    	    	
								    	    	danwei=arry[2];
										    	neixin=arry[4];
								    	    }else{
								    	     danwei=arry[1];
								    	     neixin=arry[2];
								    	    }
								    	neixin=neixin.substring(neixin.length-2,neixin.length);
								    	        
								    	option.appendChild(document.createTextNode(danwei+" "+neixin));
								    	
								    	select.appendChild(option);//向select增加option 
									
									});  
								},
							});
							
						});
			});
				

</script>
<script type="text/javascript">
//^(0|[1-9][0-9]{0,9})+(\.[0-9]{1,2})?$
		///[0-9]+(\.[0-9]+)?/
/*  提交订单     */
	function GKsubmit() {
	
		//有线电视单位校验
		document.getElementById("errs1").innerText = "";
		var area = $("#area").val();//单位
		if (area=='-1') {
			document.getElementById("errs1").innerText = "*缴费单位不能为空";
			return false;
		}
	
		//有线电视账号校验
		var r = $("#rechargeAccount").val();//账号
		document.getElementById("errs").innerText = "";
		var r=$("#rechargeAccount").val();
		if(r==null||r==""){
			document.getElementById("errs").innerText = "*账号/卡号不能为空";
			return false;
		}
		//^[0-9]{20}$
		if (!(/^[0-9]{1,20}$/.test(r))) {
			document.getElementById("errs").innerText = "*账号/卡号为1到20位数字";
			return false;
		}
		
		//有线电视价格校验 
		document.getElementById("errs2").innerText = "";
		var re = $("#rechargeAmount").val();//价格
		if(re==""||re==null){
			document.getElementById("errs2").innerText = "*充值金额不能为空";
			return false;
		}
		if(parseInt(re)<parseInt("150")||parseInt(re)>parseInt("6000")){
			document.getElementById("errs2").innerText = "*可购买范围:150-6000元";
			return false;
		}
		if (!(/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/.test(re))||re==0.0) {
			document.getElementById("errs2").innerText = "*充值金额格式错误";
			return false;
		}
		/* if(re.indexOf('.') > 0) {
		} else {
			$("#rechargeAmount").val(re+".00");
		}	 */
	}
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