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
				<h2>水电气缴费&nbsp;&nbsp;</h2>
			</div>
			<div class="well well-sm">
			<div>
						  	 <c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose>	
				</div>
				<form action="waterCoal/createBill" method="post"
					id="chargeForm" novalidate="novalidate">
					<div class="mbody">
						<div class="section">
							<div id="errorShow" style="display: none"
								class="alert alert-error">
								<strong id="errorKey"></strong> <span id="errorMsg"></span>
							</div>
							<table class="form form-large orderForm">
									<tr >
										<td class="label-td"><label for="">缴费项目：</label></td>
										<td>
											<ul class="horizonal valueSelection valueSelection-radio"
												id="page_project">
												<li><label data-param="c2670" class="waterCoalModetype"for="value-水费" id="water">水费</label></li>
												<li><label data-param="c2680" class="waterCoalModetype"for="value-电费" >电费</label></li>
												<li><label data-param="c2681" class="waterCoalModetype" for="value-燃气费" >燃气费</label></li>
											</ul>
											<input type="hidden" name="waterCoalMode" id="waterCoalMode" value="">
										</td>
									</tr>
									<tr style="" id="page_projectTr">
										<td class="label-td"><label for="">城市选择：</label></td>
										<td>
											<select class="input-small" id="provinceMenu" onchange="getcity()"></select> 
											<select class="input-small" id="cityMenu" onchange="getprop()"><option>请选择城市</option></select>
										</td>
									</tr>

									<tr>
										<td class="label-td"><label for="">缴费单位：</label></td>
										<td><select id="unitList" onchange="getItemId()"><option value="0">--请选择缴费公司--</option></select></td>
									</tr>
									<tr id="payTypeTr">
										<td class="label-td"><label id="payTypeLabel" for="">户号：</label></td>
										<td><input type="text" class="input-xlarge input-t pushr"
											maxlength="40" id="account" name="waterCoalAccount" autocomplete="off"></td>
									</tr>

									<tr style="display: none;" id="acctPeriodTr">
										<td class="label-td"><label id="payTypeLabel" for="">账期：</label></td>
										<td><select id="acctPeriod" name="acctPeriod"
											class="exclude"></select><span style="padding-left: 10px;">(请优先选择最早一期缴费,否则缴费可能失败)</span>
										</td>
									</tr>

									<tr style="display: none" id="confirmAccount">
										<td class="label-td"><label for="">&nbsp;</label></td>
										<td><span id="enlargeNum"
											style="width: 50%; display: inline-block; word-break: break-all"
											class="font-exLarge  orange"></span></td>
									</tr>

									<tr style="" id="queryInfoTr">
										<td class="label-td"><label for="">查询账单：</label></td>
										<td><span id="queryAccountResult"></span> <a class="btn"
											id="queryAccountInfo" href="javascript:void(0);">显示账单</a> <a
											style="display: none" class="label" id="queryAccountWarn"
											href="javascript:void(0);"></a></td>
									</tr>

									<tr style="display: none" id="arrearageBill">
										<td class="label-td"><label for="">欠费帐单：</label></td>
										<td>
											<ul class="vertical">
												<li style="display: none" id="balanceTr">当前余额：<span
													id="balance"></span>
												</li>
												<li>应缴金额：<span id="PE_AMTValue"></span>
												</li>
												<li>用户名称：<span id="PE_USR_NMValue"></span>
												</li>
												<li id="lastBalTr">上期余额：<span id="lastBal"></span>
												</li>
												<li>用户地址：<span id="BEG_DTValue"></span>
												</li>
												<li style="display: none" id="mounthLi">缴费月份：<span
													id="mounthSpan"></span>
												</li>
												<li style="display: none" id="moreLi"><a id="more"
													href="javascript:void(0)">显示更多...</a></li>
											</ul>
										</td>
									</tr>
									<tr style="display: none" id="mobileBillInfo">
										<td class="label-td"><label for="">欠费帐单：</label></td>
										<td>
											<ul class="vertical">
												<li>用户名：<span id="customerName"></span>；
												</li>
												<li>缴费金额：<span id="payAmount"></span>
												</li>
											</ul>
										</td>
									</tr>

									<tr style="" id="buyAmount">
										<td class="label-td"><label for="">缴费金额：</label></td>
										<td><input type="text" placeholder="任意充"
											class="input-small pushr tips" id="rechargeAmount"
											name="waterCoalAmount" autocomplete="off"
											data-original-title="可购买范围:0.01-1000000000" 
											onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"
											onafterpaste="this.value=this.value.replace(/[^\d]/g,'')" 
											onchange="numberfixed(this)"></td>
									</tr>

									<tr style="" id="salePriceTr">
										<td class="label-td v-middle"><label for="">零售价格：</label></td>
										<td id="salePrice" style ="display:none"><span class="font-large orange" id="_saleprice" name="_saleprice"><small>元</small></span>
										<input type="hidden" id="totalPrice" name ="totalPrice" value="">
									</td>
									</tr>
									<tr style="" id="submitTr">
										<td class="label-td"><label for="">&nbsp;</label></td>
										<td><input type="text" value = "提交订单" disabled="disabled" id="placesubmit" class="btn btn-warning btn-large hide">
										<input type="submit" value="提交订单" id="createbill_btn"
											name="createbill_btn" class="btn btn-warning btn-large"></td>
									</tr>
								</tbody>
							</table>

						</div>

						<div class="alert alert-info">
							<h5>服务说明：</h5>
							<ul>
								<li>1.请先查询后缴费</li>
								<li>2.处理时限为T 3工作日</li>
								<li>3.除湖南地区外，请勿重复缴费</li> 
								<li>支持全国90%地区</li>
							</ul>
						</div>
					</div>
					<input type="hidden" id="waterCoalAccount" name="waterCoalAccount">
					<input type="hidden" id="waterCoalItemId" name="waterCoalItemId">
				</form>
			</div>

		</div>
	</div>
	
	<!-- 弹出层 -->
	<div id="win-wrapper" style="overflow-y :auto;overflow :yes;">
		<div id="win" style="margin-top: 50px;">
			<div class="win-title"><span>账户详情</span><span style="float:right; cursor:pointer; padding:0 10px;" class="win-close">×</span></div>
			<div class="win-body">
				<p><span class="order-title">订单账号：</span><span class="accountno">......</span></p>
				<p><span class="order-title">开始日期：</span><span class="beginDate">......</span></p>
				<p><span class="order-title">结束日期：</span><span class="endDate">......</span></p>
				<p><span class="order-title">地址：</span><span class="customerAddress">......</span></p>
				<p><span class="order-title">付款金额：</span><span class="payAmount">.......</span></p>
 				<p><span class="order-title">公司名称：</span><span class="customerName">.......</span></p>
				<p><span class="order-title">滞纳金：</span><span class="penalty">......</span></p>
			</div>
			
	  </div>
  </div>
	<script>
	/* $("#createbill_btn").click(function(){
		$.ajax({
			type:"GET",
			url:"",
			dataType:""
		});
	}); */
	function getItemId(){
		var itemId = $("#unitList option:selected").val();
		$("#waterCoalItemId").val(itemId);
	}
	// 显示信息
	$("#queryAccountInfo").click(function(){
		var account = $("#account").val();
		var itemId = $("#unitList option:selected").val();
		$("#waterCoalAccount").val(account);
		$("#waterCoalItemId").val(itemId);
		$.ajax({
			type : "GET",
			url : "waterCoal/queryWaterCoalBill",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {
				account : account,
				itemId : itemId
			},
			success : function(data){
				console.log(data)
				if(data[0].accountNo == "" && data[0].beginDate == "" && data[0].endDate == ""){
					window.wxc.xcConfirm("该账号没有未缴纳的账单", window.wxc.xcConfirm.typeEnum.confirm);
					return false;
				}
				$("#win .accountno").text(data[0].accountNo);
				$("#win .beginDate").text(data[0].beginDate);
				$("#win .endDate").text(data[0].endDate);
				$("#win .customerAddress").text(data[0].customerAddress);
				$("#win .payAmount").text(data[0].payAmount);
				$("#win .customerName").text(data[0].customerName);
				$("#win .penalty").text(data[0].penalty);
				$("#win-wrapper").fadeIn();//显示弹出框
			}
		})
		
	});
	
	//点击X关闭窗口
	$(".win-close").click(function(){
		$("#win-wrapper").fadeOut();
	});
	//点击页面其他地方也关闭窗口
	$("#win-wrapper").click(function(){
		$("#win-wrapper").fadeOut();
	});
	//点击窗口没有效果，返回false
	$("#win").click(function(){
		return false;
	})
	
	/* 校验 */
		$("#createbill_btn").attr('disabled',true);
	function check(){
		function checkNum(){
			var flag;
			var account = $("#account").val();
			if(account != ""){
				flag = true
			}else{
				flag = false
			}
			return flag;
		}
		function checkMoney(){
			var flag;
			var rechargeAmount = $('#rechargeAmount').val();
			if(rechargeAmount == "" || rechargeAmount == null){
				flag = false
			}else{
				flag = true
			}
			return flag;
		}
		if(checkNum() && checkMoney()){
			$("#createbill_btn").attr('disabled',false);
		}else{
			$("#createbill_btn").attr('disabled',true);
		}
	}
	$(document).click(function(){
		check();
	})
	
	
	
		$(".index3").addClass("check");
		
		//省份选择
		$(document).ready(
				function() {
					var province = document.getElementById("provinceMenu");
					province.options.add(new Option("--请选择省份--", "0"));
					var provinces = new Array("北京", "上海", "重庆", "安徽", "福建", "甘肃",
							"广东", "广西", "贵州", "海南", "河北", "黑龙江", "河南", "香港", "湖北",
							"湖南", "江苏", "江西", "吉林", "辽宁", "澳门", "内蒙古", "宁夏", "青海",
							"山东", "山西", "陕西", "四川", "台湾", "天津", "新疆", "西藏", "云南",
							"浙江");

					for (var i = 0; i < provinces.length; i++) {
						//province.Options[i].value=provinces[i];
						//province.Options[i].txt=provinces[i];

						province.options.add(new Option(
								provinces[provinces.length - i - 1],
								provinces[provinces.length - i - 1]));
					}
					
					province.options[0].selected = true;
					$("#water").addClass("selected");
					$("#waterCoalMode").val($("#water").attr("data-param"));
					

				});

		
		
		
		//查询缴费公司
		function getprop() {
			$.ajax({
				type : "get",
				url : "waterCoal/getProps",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {
					"province" : $("#provinceMenu").val(),//省份
					"city" : $("#cityMenu").val(),			//城市
					"mode" : $("#waterCoalMode").val()
				},
				success : function(data) {
					var props = eval(data);
					var prop = document.getElementById("unitList");
					prop.options.length = 0;//删除所有的option
					if(props[props.length - 1].itemName == "此条件查询无记录"){
						window.wxc.xcConfirm("该城市尚无缴费单位", window.wxc.xcConfirm.typeEnum.confirm);
						return;
					}
					prop.options.add(new Option("--请选择缴费公司--", "0"));
					prop.options[0].selected = true;
					for (var i = 0; i < props.length; i++) {
						prop.options.add(new Option(props[props.length - i - 1].itemName,
								props[props.length - i - 1].itemId));
					}
				}
			})

		};
		
		$("#queryAccountInfo").click(function(){
			$.ajax({
				type : "get",
				url : "waterCoal/queryWaterCoalBill",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {
					"account" : $("#account").val(),
					"itemId" : $("#unitList").val()		
				},
				success : function(data) {
					var bills = eval(data);
					
					if("1" ==bills.errCode){
						$("#queryAccountResult")[0].innerText =bills.errMsg;
					}else{
						
						
						
					}
				}
			})
			
			
		});
		
		
		$(".waterCoalModetype").click(function(){
			$(".waterCoalModetype").removeClass("selected");
			$(this).addClass("selected");
			$("#waterCoalMode").val($(this).attr("data-param"));
			//清除选择的城市信息
			document.getElementById("provinceMenu")[0].selected=true;
			document.getElementById("cityMenu")[0].selected=true;
			document.getElementById("unitList")[0].selected=true;
		})
		
		function numberfixed(obj){
		var num1 =$(obj).val();
		var num2 =Number(num1).toFixed(2);
		$(obj).val(num2);
		
		var num3 =Number(Number(num1)+Number(1)).toFixed(2);
		
		$("#salePrice")[0].style.display="";
		$("#_saleprice")[0].innerText =num3;
		$("#totalPrice").val(num3);
		checkerrMsg();
		check();
		
	};
		
		
		
		function getcity() {
			var city = document.getElementById("cityMenu");
			document.getElementById("unitList").options.length=1;//选择城市之前清查询到的空燃气公司 
			switch ($("#provinceMenu").val()) {
			case "安徽":
				var cityOptions = new Array("合肥", "滁州","马鞍山","芜湖", "池州");
				break;
			case "北京":
				var cityOptions = new Array("北京");
				break;
			case "重庆":
				var cityOptions = new Array("重庆");
				break;
			case "福建":
				var cityOptions = new Array("福州", "龙岩", "南平", "宁德", "莆田","泉州", "厦门", "漳州");
				break;
			case "广东":
				var cityOptions = new Array("汕头", "深圳");
				break;
			case "广西":
				var cityOptions = new Array("南宁");
				break;
			case "贵州":
				var cityOptions = new Array("贵阳","毕节","遵义");
				break;
			case "海南":
				var cityOptions = new Array("海口");
				break;
			case "河北":
				var cityOptions = new Array("石家庄", "保定", "沧州", "承德","邯郸", "衡水","唐山","邢台");
				break;
			case "黑龙江":
				var cityOptions = new Array("哈尔滨");
				break;
			case "河南":
				var cityOptions = new Array("郑州","开封","南阳", "周口", "驻马店");
				break;
			case "湖北":
				var cityOptions = new Array("武汉","咸宁");
				break;
			case "湖南":
				var cityOptions = new Array("长沙", "娄底", "邵阳", "湘潭", "岳阳", "永州", "衡阳");
				break;
			case "江苏":
				var cityOptions = new Array("南京", "常州", "淮安",
						"连云港", "南通", "宿迁", "苏州",
						"泰州", "无锡", "徐州", "盐城", "扬州", "宜兴","镇江");
				break;
			case "江西":
				var cityOptions = new Array("南昌","九江","新余");
				break;
			case "吉林":
				var cityOptions = new Array("白城","四平");
				break;
			case "辽宁":
				var cityOptions = new Array("沈阳");
				break;
			case "山东":
				var cityOptions = new Array("济南", "滨州","德州", "东营",
						"济宁", "莱芜", "聊城", "临沂", "青岛", "日照", "泰安", "潍坊",
						"威海", "烟台", "枣庄", "淄博");
				break;
			case "上海":
				var cityOptions = new Array("浦东");
				break;
			case "山西":
				var cityOptions = new Array("太原", "长治", "大同","晋城","吕梁","晋中",
						"临汾", "朔州", "忻州", "阳泉", "榆次", "运城");
				break;
			case "陕西":
				var cityOptions = new Array("西安", "安康", "宝鸡", "汉中", "渭南", "商州",
						"铜川", "咸阳", "延安", "榆林");
				break;
			case "四川":
				var cityOptions = new Array("成都", "巴中", "达州", "德阳","广安", "广元","乐山", "泸州", "马尔康", "绵阳",
						"眉山", "南充", "内江", "攀枝花", "遂宁", "西昌", "雅安", "宜宾","自贡", "资阳");
				break;
			case "天津":
				var cityOptions = new Array("天津");
				break;
			case "新疆":
				var cityOptions = new Array("克拉玛依");
				break;
			case "云南":
				var cityOptions = new Array("昆明");
				break;
			case "浙江":
				var cityOptions = new Array("杭州","湖州", "嘉兴", "金华","丽水", "宁波","衢州","绍兴","台州","温州");
				break;
			default:
				var cityOptions = new Array("--请选择城市--", "0");
				break;
			}
			city.options.length = 0;
			for (var i = 0; i < cityOptions.length; i++) {

				city.options.add(new Option(cityOptions[cityOptions.length - i - 1],
						cityOptions[cityOptions.length - i - 1]));

			}
			city.options.add(new Option("--请选择城市--", "0"));
			city.options[0].selected = true;
		};
		
		// 判断是否维护
		function checkerrMsg(){
			if($("#wh").length>0){
				$("#placesubmit").removeClass("hide");
				$("#createbill_btn").addClass("hide");
			}else{
				$("#placesubmit").addClass("hide");
				$("#createbill_btn").removeClass("hide");
			}
		}
	</script>

</body>
</html>