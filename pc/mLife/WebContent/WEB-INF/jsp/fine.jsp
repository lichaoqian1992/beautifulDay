<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>满集生活缴费平台</title>

<!-- 日期控件 -->
<script src="${ctx}/My97DatePicker/WdatePicker.js"></script>

</head>

<style type="text/css">
.valueSelection{width:100px;margin-left: -35px;}
</style>
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
		<div id="maincontent" class="pure-u-5-6 mainbackground page-wrapper">
			<div style="border-bottom: 1px solid #dcdcdc; margin-bottom: 10px;">
				<h2>交通罚款&nbsp;&nbsp;</h2>
			</div>
			<form action="fine/createBill" method="post"
				id="chargeForm" novalidate="novalidate">
				<div class="section">
				<div><c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose>	</div>
					<div class="well well-sm">
						<div class="list list-vertical">
							<div class="item item-input">
								<label class="input-label" for="">违章区域：</label>
								
								<div class="input-content horizontal">
									<select class="form-control select-free item" id="provinceMenu"
										name="provinceMenu" onchange="getcity()"></select> 
									<select class="form-control select-free item" id="cityMenu"
										name="cityMenu" onchange="getItem()"></select> 
									<span style="display: none;" id="newProvinceUL"></span> 
									<span style="display: none;" id="newCityUL"></span>
								</div>
							</div>
							<div id="payMode" style="" class="item item-input">
								<label class="input-label" for="">处理方式：</label>
								<ul class="horizonal valueSelection valueSelection-radio"
									id="payType" name="payType">
									<li><label for="v83293" state="1" class="selected">手工单</label></li>
								</ul>
							</div>
							<div id="carNumberDiv" style="" class="item item-input">
								<label class="input-label" for="">车牌号码：</label>
								<div class="input-content">
									<select class="form-control select-free item" id="carNumber1"
										name="carNumber1">
									</select> 
									<input type="text" autocomplete="off" onpaste="return false;"
										class="form-control" id="carNumber2" name="carNumber2" onchange="toOne()">
									<input type="hidden" id="carNo" name="carNo">
									<span class="error" style="display: none;" id="forcarNumber2">请填写车辆号码</span>
									
								</div>
							</div>
							<div id="ticketNumDiv" style="" class="item item-input">
								<label class="input-label" for="">处罚单号：</label>
								<div class="input-content">
									<span class="placeholder"><input type="text"
										placeholder="请填写完整处罚单号" class="form-control placeholderB"
										maxlength="20" id="ticketNum" name="fineNo"
										autocomplete="off"></span>
									<span style="display: none;" class="error" id="forticketNum">请填写完整处罚单号</span>
								</div>
							</div>
							
							<div id="carTypeDiv" style="display: none"
								class="item item-input">
								<label class="input-label" for="">车辆类型：</label>
								<div class="input-content">
									<select class="form-control" id="carType" name="carType">
										<!-- <option value="" selected="">请选择车辆类型</option>
										<option value="02">小型车</option>
										<option value="01">大型车</option>
										<option value="06">外籍汽车</option>
										<option value="07">两、三轮摩托车</option>
										<option value="11">境外摩托车</option>
										<option value="12">外籍摩托车</option>
										<option value="15">挂车</option>
										<option value="26">香港出入境车</option>
										<option value="27">澳门出入境车</option> -->
									</select>
								</div>
							</div>
							
							<div id="driverLicenceNumDiv" style="display: none"
								class="item item-input">
								<label class="input-label" for="">发动机号：</label>
								<div class="input-content">
									<input type="text" maxlength="18" class="form-control"
										id="engineNo" name="engineId" placeholder="请填写完整发动机号"
										autocomplete="off">
								</div>
							</div>
							
							<div id="frameDiv" style="display: none" class="item item-input">
								<label class="input-label" for="">车架号：</label>
								<div class="input-content">
									<span class="placeholder"><input type="text"
										placeholder="请填写完整车架号" class="form-control placeholderB"
										maxlength="20" id="frameId" name="frameId" autocomplete="off"></span>
								</div>
							</div>

							<div id="illegalTimeDiv" style="" class="item item-input">
								<label class="input-label" for=""> 处罚时间：</label>
								<div class="input-content">
									<input id ="illegalTime" name="illegalTime" placeholder="请选择处罚时间" class="form-control" type="text" onclick="WdatePicker()"/>
									
									<span
										style="display: none;" id="forillegalTime" class="error">请选择处罚时间</span>
								</div>
							</div>
							
							<div id="carOwnerNameDiv" style="" class="item item-input">
								<label class="input-label" for="">车主姓名：</label>
								<div class="input-content">
									<input type="text" class="form-control" maxlength="15" placeholder="请填写车主姓名"
										id="carOwnerName" name="name" autocomplete="off">
								</div>
							</div>
							<div id="carOwnerTelDiv" style="" class="item item-input">
								<label class="input-label" for="">车主手机：</label>
								<div class="input-content">
									<input type="text" autocomplete="off" class="form-control" placeholder="请填写手机号码"
										id="carOwnerTel" name="mobile">
								</div>
							</div>

							


							<div id="fineAmountDiv" style="" class="item item-input">
								<label class="input-label" for=""> 处罚金额：</label>
								<div class="input-content">
									<input type="text" aliasname="处罚金额" class="form-control" placeholder="请填写处罚金额"
										maxlength="15" id="fineFee" name="fineFee" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" onchange ="numberfixed(this)" onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " >
								</div>
							</div>
							<div id="lateFeesDiv" style="display:block;" class="item item-input">
								<label class="input-label" for=""> 滞纳金：</label>
								<div class="input-content">
									<input type="text" aliasname="滞纳金" class="form-control" 
										maxlength="15" id="lateFees" name="delayFee" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" onchange ="numberfixed2(this)"
										autocomplete="off"><span style="display: none;"
										class="error" id="forlateFees">请填写滞纳金</span>
								</div>
							</div>
							
							<div id="serviceChargeDiv" style="" class="item item-input">
								<label class="input-label" for=""> 服务费：</label>
								<div class="input-content">
									<input type="text" aliasname="服务费" class="form-control"
										maxlength="15" id="serviceFee" name="serviceFee"
										autocomplete="off" readonly="readonly" 	>
								</div>
							</div>
							<div id="orderTotalAmountDiv" style="" class="item item-input">
								<label class="input-label" for=""> 订单总金额：</label>
								<div class="input-content">
									<span id="enlargeNum"
											style="width: 50%; display: inline-block; word-break: break-all"
											class="font-exLarge  orange"></span>
								</div>
								<input type="hidden" id="totalFee" name="totalFee">
								<input type="hidden" id="itemId" name="itemId">
								<input type="hidden" id="itemName" name="itemName">
							</div>
							

							<div id="submit_manual_div" style="" class="item item-input">
								<div class="input-content">
								<input type="text" value = "提交订单" disabled="disabled" id="placesubmit" class="btn btn-warning btn-large hide">
									<Button type="button" id="btn_submit_manual" class="btn btn-warning btn-large"
										href="javascript:void(0);">提交订单</Button>
								</div>
							</div>

						</div>
					</div>
				</div>
			</form>


			<div class="alert alert-info">
				<h5>服务说明：</h5>
				<ul>
					<li>1.暂不支持缴含滞纳金及扣分项的违章。</li>
					<li>2.需要提供处理单号。</li>
					<li>3.每天10：30处理前一天订单，周五12:00之后的订单，周一处理。</li>
					<li>4.需要1-3个工作日处理到账。</li>
					
				</ul>
			</div>


		</div>
	</div>
	<script>
	$(".index8").addClass("check");
	//页面初始化
	$(document).ready(
			function() {
				//省份选择添加选项
				var province = document.getElementById("provinceMenu");
				province.options.add(new Option("请选择省份", "0"));
				var provinces = new Array("北京", "上海", "重庆", "安徽", "福建", "甘肃",
						"广东", "广西", "贵州", "海南", "河北", "黑龙江", "河南", "香港", "湖北",
						"湖南", "江苏", "江西", "吉林", "辽宁", "澳门", "内蒙古", "宁夏", "青海",
						"山东", "山西", "陕西", "四川", "台湾", "天津", "新疆", "西藏", "云南",
						"浙江");
				for (var i = 0; i < provinces.length; i++) {
					province.options.add(new Option(
							provinces[provinces.length - i - 1],
							provinces[provinces.length - i - 1]));
				}
				
				province.options[0].selected = true;
				var city =document.getElementById("cityMenu");
				city.options.add(new Option("请选择城市", "0")); 
				
				//车辆类型
				var cartype =$("#carType");
				cartype.append("<option value=''>请选择车辆类型</option>");
				cartype.append("<option value='02'>小型车</option>");
				cartype.append("<option value='01'>大型车</option>");
				cartype.append("<option value='06'>外籍汽车</option>");
				cartype.append("<option value='07'>两、三轮摩托车</option>");
				cartype.append("<option value='11'>境外摩托车</option>");
				cartype.append("<option value='12'>外籍摩托车</option>");
				cartype.append("<option value='15'>挂车</option>");
				cartype.append("<option value='26'>香港出入境车</option>");
				cartype.append("<option value='27'>澳门出入境车</option>");
				
				
				var carnumber1 =$("#carNumber1");
				carnumber1.append("<option value='京'>京</option>");
				carnumber1.append("<option value='粤'>粤</option>");
				carnumber1.append("<option value='津'>津</option>");
				carnumber1.append("<option value='冀'>冀</option>");
				carnumber1.append("<option value='晋'>晋</option>");
				carnumber1.append("<option value='辽'>辽</option>");
				carnumber1.append("<option value='吉'>吉</option>");
				carnumber1.append("<option value='黑'>黑</option>");
				carnumber1.append("<option value='沪'>沪</option>");
				carnumber1.append("<option value='苏'>苏</option>");
				carnumber1.append("<option value='浙'>浙</option>");
				carnumber1.append("<option value='皖'>皖</option>");
				carnumber1.append("<option value='闽'>闽</option>");
				carnumber1.append("<option value='赣'>赣</option>");
				carnumber1.append("<option value='鲁'>鲁</option>");
				carnumber1.append("<option value='豫'>豫</option>");
				carnumber1.append("<option value='鄂'>鄂</option>");
				carnumber1.append("<option value='湘'>湘</option>");
				carnumber1.append("<option value='蒙'>蒙</option>");
				carnumber1.append("<option value='桂'>桂</option>");
				carnumber1.append("<option value='琼'>琼</option>");
				carnumber1.append("<option value='渝'>渝</option>");
				carnumber1.append("<option value='川'>川</option>");
				carnumber1.append("<option value='贵'>贵</option>");
				carnumber1.append("<option value='云'>云</option>");
				carnumber1.append("<option value='藏'>藏</option>");
				carnumber1.append("<option value='陕'>陕</option>");
				carnumber1.append("<option value='甘'>甘</option>");
				carnumber1.append("<option value='青'>青</option>");
				carnumber1.append("<option value='宁'>宁</option>");
				carnumber1.append("<option value='新'>新</option>");
			});
	
	function comCarNo() {
		$("#carNo").val("");
		var carnumber1 = $("#carNumber1").val();
		var carnumber2 = $("#carNumber2").val();
		var carNo = carnumber1 + carnumber2;
		$("#carNo").val(carNo);
		$("#engineDiv")[0].style.display="";
		$("#frameDiv")[0].style.display="";
		$("#carOwnerNameDiv")[0].style.display="";
		$("#carOwnerTelDiv")[0].style.display="";

	}
	//得到车牌号码
	function toOne(){
		var carNo = $("#carNumber1").val() + $("#carNumber2").val();
		$("#carNo").val(carNo);
	}
	
	function getItem() {

		if($("#cityMenu").val()!=0)	{
		$.ajax({
			type : "GET",
			url : "fine/getItem",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {
				"province" : $("#provinceMenu").val(),
				"city" : $("#cityMenu").val()
			},
			success : function(data) {
				var fineitem = eval(data);
				$("#itemId").val(fineitem.itemId);
				$("#itemName").val(fineitem.itemName);
				$("#serviceFee").val(Number(fineitem.fineFee).toFixed(2));
			}
		});
		
	}

	};
	
	function dicNum(obj) {
		var num = $(obj).val();
		var tnum = Number(num).toFixed(2);
		$(obj).val(tnum);
		countMoney();
	};

	function countMoney() {

		var finefee = $("#fineFee").val();
		var delayfee = $("#delayFee").val();
		var serviceFee = $("#serviceFee").val();
		if (null == finefee) {
			finefee = "0";
		}
		if (null == delayfee) {
			delayfee = "0";
		}
		if (null == serviceFee) {
			fee = "0";
		}

		var total = Number(finefee) + Number(delayfee) + Number(fee) + 1;

		$("#totalFee").val(total.toFixed(2));

	};
	
	
	function getcity() {
		var city =document.getElementById("cityMenu");
		var carnumber1 =$("#carNumber1");
		carnumber1.empty();
		city.options.length = 0;
		switch ($("#provinceMenu").val()) {
		case "安徽":
			var cityOptions = new Array("合肥", "安庆", "蚌埠", "亳州", "巢湖", "滁州",
					"阜阳", "贵池", "淮北", "淮化", "淮南", "黄山", "九华山", "六安", "马鞍山",
					"宿州", "铜陵", "屯溪", "芜湖", "宣城");
			carnumber1.append("<option value='皖'>皖</option>");
			break;
		case "北京":
			var cityOptions = new Array("东城", "西城", "崇文", "宣武", "朝阳", "丰台",
					"石景山", "海淀", "门头沟", "房山", "通州", "顺义", "昌平", "大兴", "平谷",
					"怀柔", "密云", "延庆");
			carnumber1.append("<option value='京'>京</option>");
			break;
		case "重庆":
			var cityOptions = new Array("重庆");
			carnumber1.append("<option value='渝'>渝</option>");
			break;
		case "福建":
			var cityOptions = new Array("福州", "福安", "龙岩", "南平", "宁德", "莆田",
					"泉州", "三明", "邵武", "石狮", "永安", "武夷山", "厦门", "漳州");
			carnumber1.append("<option value='闽'>闽</option>");
			break;
		case "甘肃":
			var cityOptions = new Array("兰州", "白银", "定西", "敦煌", "甘南", "金昌",
					"酒泉", "临夏", "平凉", "天水", "武都", "武威", "西峰", "张掖");
			carnumber1.append("<option value='甘'>甘</option>");
			break;
		case "广东":
			var cityOptions = new Array("广州", "潮阳", "潮州", "澄海", "东莞", "佛山",
					"河源", "惠州", "江门", "揭阳", "开平", "茂名", "梅州", "清远", "汕头", "汕尾",
					"韶关", "深圳", "顺德", "阳江", "英德", "云浮", "增城", "湛江", "肇庆", "中山",
					"珠海");
			carnumber1.append("<option value='粤'>粤</option>");
			break;
		case "广西":
			var cityOptions = new Array("南宁", "百色", "北海", "桂林", "防城港", "河池",
					"贺州", "柳州", "钦州", "梧州", "玉林");
			carnumber1.append("<option value='桂'>桂</option>");
			break;
		case "贵州":
			var cityOptions = new Array("贵阳", "安顺", "毕节", "都匀", "凯里", "六盘水",
					"铜仁", "兴义", "玉屏", "遵义");
			carnumber1.append("<option value='黔'>黔</option>");
			break;
		case "海南":
			var cityOptions = new Array("海口", "儋县", "陵水", "琼海", "三亚", "通什",
					"万宁");
			carnumber1.append("<option value='琼'>琼</option>");
			break;
		case "河北":
			var cityOptions = new Array("石家庄", "保定", "北戴河", "沧州", "承德", "丰润",
					"邯郸", "衡水", "廊坊", "南戴河", "秦皇岛", "唐山", "新城", "邢台", "张家口");
			carnumber1.append("<option value='冀'>冀</option>");
			break;
		case "黑龙江":
			var cityOptions = new Array("哈尔滨", "北安", "大庆", "大兴安岭", "鹤岗", "黑河",
					"佳木斯", "鸡西", "牡丹江", "齐齐哈尔", "七台河", "双鸭山", "绥化", "伊春");
			carnumber1.append("<option value='黑'>黑</option>");
			break;
		case "河南":
			var cityOptions = new Array("郑州", "安阳", "鹤壁", "潢川", "焦作", "济源",
					"开封", "漯河", "洛阳", "南阳", "平顶山", "濮阳", "三门峡", "商丘", "新乡",
					"信阳", "许昌", "周口", "驻马店");
			carnumber1.append("<option value='豫'>豫</option>");
			break;
		case "香港":
			var cityOptions = new Array("香港", "九龙", "新界");
			carnumber1.append("<option value='港'>港</option>");
			break;
		case "湖北":
			var cityOptions = new Array("武汉", "恩施", "鄂州", "黄冈", "黄石", "荆门",
					"荆州", "潜江", "十堰", "随州", "武穴", "仙桃", "咸宁", "襄阳", "襄樊", "孝感",
					"宜昌");
			carnumber1.append("<option value='鄂'>鄂</option>");
			break;
		case "湖南":
			var cityOptions = new Array("长沙", "常德", "郴州", "衡阳", "怀化", "吉首",
					"娄底", "邵阳", "湘潭", "益阳", "岳阳", "永州", "张家界", "株洲");
			carnumber1.append("<option value='湘'>湘</option>");
			break;
		case "江苏":
			var cityOptions = new Array("南京", "常熟", "常州", "海门", "淮安", "江都",
					"江阴", "昆山", "连云港", "南通", "启东", "沭阳", "宿迁", "苏州", "太仓",
					"泰州", "同里", "无锡", "徐州", "盐城", "扬州", "宜兴", "仪征", "张家港",
					"镇江", "周庄");
			carnumber1.append("<option value='苏'>苏</option>");
			break;
		case "江西":
			var cityOptions = new Array("南昌", "抚州", "赣州", "吉安", "景德镇", "井冈山",
					"九江", "庐山", "萍乡", "上饶", "新余", "宜春", "鹰潭");
			carnumber1.append("<option value='赣'>赣</option>");
			break;
		case "吉林":
			var cityOptions = new Array("长春", "白城", "白山", "珲春", "辽源", "梅河",
					"吉林", "四平", "松原", "通化", "延吉");
			carnumber1.append("<option value='吉'>吉</option>");
			break;
		case "辽宁":
			var cityOptions = new Array("沈阳", "鞍山", "本溪", "朝阳", "大连", "丹东",
					"抚顺", "阜新", "葫芦岛", "锦州", "辽阳", "盘锦", "铁岭", "营口");
			carnumber1.append("<option value='辽'>辽</option>");
			break;
		case "澳门":
			var cityOptions = new Array("澳门");
			carnumber1.append("<option value='澳'>澳</option>");
			break;
		case "内蒙古":
			var cityOptions = new Array("呼和浩特", "阿拉善盟", "包头", "赤峰", "东胜",
					"海拉尔", "集宁", "临河", "通辽", "乌海", "乌兰浩特", "锡林浩特");
			carnumber1.append("<option value='蒙'>蒙</option>");
			break;
		case "宁夏":
			var cityOptions = new Array("银川", "固源", "石嘴山", "吴忠");
			carnumber1.append("<option value='宁'>宁</option>");
			break;
		case "青海":
			var cityOptions = new Array("西宁", "德令哈", "格尔木", "共和", "海东", "海晏",
					"玛沁", "同仁", "玉树");
			carnumber1.append("<option value='青'>青</option>");
			break;
		case "山东":
			var cityOptions = new Array("济南", "滨州", "兖州", "德州", "东营", "菏泽",
					"济宁", "莱芜", "聊城", "临沂", "蓬莱", "青岛", "曲阜", "日照", "泰安", "潍坊",
					"威海", "烟台", "枣庄", "淄博");
			carnumber1.append("<option value='鲁'>鲁</option>");
			break;
		case "上海":
			var cityOptions = new Array("崇明", "黄浦", "卢湾", "徐汇", "长宁", "静安",
					"普陀", "闸北", "虹口", "杨浦", "闵行", "宝山", "嘉定", "浦东", "金山", "松江",
					"青浦", "南汇", "奉贤");
			carnumber1.append("<option value='沪'>沪</option>");
			break;
		case "山西":
			var cityOptions = new Array("太原", "长治", "大同", "候马", "晋城", "离石",
					"临汾", "宁武", "朔州", "忻州", "阳泉", "榆次", "运城");
			carnumber1.append("<option value='晋'>晋</option>");
			break;
		case "陕西":
			var cityOptions = new Array("西安", "安康", "宝鸡", "汉中", "渭南", "商州",
					"绥德", "铜川", "咸阳", "延安", "榆林");
			carnumber1.append("<option value='陕'>陕</option>");
			break;
		case "四川":
			var cityOptions = new Array("成都", "巴中", "达川", "德阳", "都江堰", "峨眉山",
					"涪陵", "广安", "广元", "九寨沟", "康定", "乐山", "泸州", "马尔康", "绵阳",
					"眉山", "南充", "内江", "攀枝花", "遂宁", "汶川", "西昌", "雅安", "宜宾",
					"自贡", "资阳");
			carnumber1.append("<option value='川'>川</option>");
			break;
		case "台湾":
			var cityOptions = new Array("台北", "基隆", "台南", "台中", "高雄", "屏东",
					"南投", "云林", "新竹", "彰化", "苗栗", "嘉义", "花莲", "桃园", "宜兰", "台东",
					"金门", "马祖", "澎湖");
			carnumber1.append("<option value='台'>台</option>");
			break;
		case "天津":
			var cityOptions = new Array("天津", "和平", "东丽", "河东", "西青", "河西",
					"津南", "南开", "北辰", "河北", "武清", "红挢", "塘沽", "汉沽", "大港", "宁河",
					"静海", "宝坻", "蓟县");
			carnumber1.append("<option value='津'>津</option>");
			break;
		case "新疆":
			var cityOptions = new Array("乌鲁木齐", "阿克苏", "阿勒泰", "阿图什", "博乐",
					"昌吉", "东山", "哈密", "和田", "喀什", "克拉玛依", "库车", "库尔勒", "奎屯",
					"石河子", "塔城", "吐鲁番", "伊宁");
			carnumber1.append("<option value='新'>新</option>");
			break;
		case "西藏":
			var cityOptions = new Array("拉萨", "阿里", "昌都", "林芝", "那曲", "日喀则",
					"山南");
			carnumber1.append("<option value='藏'>藏</option>");
			break;
		case "云南":
			var cityOptions = new Array("昆明", "大理", "保山", "楚雄", "大理", "东川",
					"个旧", "景洪", "开远", "临沧", "丽江", "六库", "潞西", "曲靖", "思茅", "文山",
					"西双版纳", "玉溪", "中甸", "昭通");
			carnumber1.append("<option value='滇'>滇</option>");
			break;
		case "浙江":
			var cityOptions = new Array("杭州", "安吉", "慈溪", "定海", "奉化", "海盐",
					"黄岩", "湖州", "嘉兴", "金华", "临安", "临海", "丽水", "宁波", "瓯海", "平湖",
					"千岛湖", "衢州", "江山", "瑞安", "绍兴", "嵊州", "台州", "温岭", "温州",
					"余姚", "舟山");
			carnumber1.append("<option value='浙'>浙</option>");
			break;
		default:
			var cityOptions = new Array("请选择城市");
			break;
		}
		city.options.length = 0;
		for (var i = 0; i < cityOptions.length; i++) {
			city.options.add(new Option(cityOptions[cityOptions.length - i - 1],cityOptions[cityOptions.length - i - 1]));
		}
		city.options.add(new Option("选择城市", "0"));
		city.options[0].selected = true;
	};
	

	function numberfixed(obj){
		var num1 =$(obj).val();
		var num2 =Number(num1).toFixed(2);
		$(obj).val(num2);
		
		var num3 =$("#serviceFee").val();
		var num4 = $("#lateFees").val();
		$("#enlargeNum")[0].innerText =Number(Number(num2)+Number(num3)+Number(num4)).toFixed(2);
// 		$("#enlargeNum")[0].innerText="1";
		$("#totalFee").val(Number(Number(num2)+Number(num3)+Number(num4)).toFixed(2));
	};
	function numberfixed2(obj){
		var num1 =$(obj).val();
		var num2 =Number(num1).toFixed(2);
		$(obj).val(num2);
		
		var num3 =$("#serviceFee").val();
		var num4 = $("#fineFee").val();
		$("#enlargeNum")[0].innerText =Number(Number(num2)+Number(num3)+Number(num4)).toFixed(2);
// 		$("#enlargeNum")[0].innerText="1";
		$("#totalFee").val(Number(Number(num2)+Number(num3)+Number(num4)).toFixed(2));
	};
	
	$("#ticketNum").change(function(){

		$("#carTypeDiv")[0].style.display="";
		$("#driverLicenceNumDiv")[0].style.display="";
		$("#frameDiv")[0].style.display="";
		
	});
	
	$("#carNumber2").blur(function(){
		if(!checkCarNumber()){
			$("#forcarNumber2").show().text('请输入正确的车牌号')
		}else{
			$("#forcarNumber2").hide();
		}
	})
	function checkCarNumber(){
		var r = $("#carNumber2").val();
		var flag;
		if (!(/^[A-Z]{1}[A-Z_0-9]{5}$/.test(r))) {
			flag = false;
		}else{
			flag = true;
		}
		return flag;
	}
	function checkName(){
		var r = $("#carOwnerName").val();
		var flag;
		if (!(/^[\u4e00-\u9fa5 ]{2,20}$/.test(r))) {
			flag = false;
		}else{
			flag = true;
		}
		return flag;
	}
	function checkTel(){
		var r = $("#carOwnerTel").val();
		var flag;
		if (!(/^1[3|4|5|7|8]\d{9}$/.test(r))) {
			flag = false;
		}else{
			flag = true;
		}
		return flag;
	}
	function check(){
		if(checkCarNumber() && checkName() && checkTel()){
			$("#btn_submit_manual").attr("disabled",false);
		}else{
			$("#btn_submit_manual").attr("disabled",true);
		}
	}
	$(document).click(function(){
		check();
	})
	$("#btn_submit_manual").attr("disabled",true);
	
	// 判断是否维护
	if($("#wh").length){
		$("#placesubmit").removeClass("hide");
		$("#btn_submit_manual").addClass("hide");
	}else{
		$("#placesubmit").addClass("hide");
		$("#btn_submit_manual").removeClass("hide");
	}
	 
	$("#btn_submit_manual").click(function(){
		if($("#carType").val() == ""){
			window.wxc.xcConfirm("请选择车辆类型", window.wxc.xcConfirm.typeEnum.confirm);
			retrun;
		}
		$("#chargeForm").submit();
	});
	</script>

</body>
</html>