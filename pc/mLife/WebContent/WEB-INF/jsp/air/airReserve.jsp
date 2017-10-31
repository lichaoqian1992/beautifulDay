<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>满集生活缴费平台</title>
<style type="text/css">
.fa-save::before, .fa-floppy-o::before {
    content:'\00A0 \2014';
}
.fa {
    display: inline-block;
    font-family: FontAwesome;
    font-size: 14px;
    font-style: normal;
    font-weight: normal;
    line-height: 1;
    position: relative;
    text-align: center;
    top: 0;
    vertical-align: baseline;
}
.text-td{
	
max-width: 100px;

}
.well-light {
    background-color: #FFF !important;
}
</style>


</head>
<body>
	<div class="pure-g">
		<div class="pure-u-1">
			<%@ include file="../header.jsp"%>
		</div>
	</div>
	<div id="main" class="pure-g">
		<div class="pure-u-1-8">
			<%@ include file="../menu.jsp"%>
		</div>
		<div id="maincontent" class="pure-u-5-6 mainbackground">
			<div class="section">
				<ul class="step-nav horizontal">
					<li class="item active"><i class="step-num">1</i> <span
						class="char">提交订单</span></li>
					<li class="item"><i class="step-num">2</i> <span class="char">确认并支付</span>
					</li>
					<li class="item"><i class="step-num">3</i> <span class="char">成功</span>
					</li>
				</ul>
				<h2>机票订单</h2>
				<div class="section-title">
					<h3>航班信息</h3>
					<a class="btn pure-button-primary" href="javascript:history.go(-1);">重新查询</i>
					</a>
				</div>
			</div>
				<div>
					<div class="ticket-brand-infos-content well horizontal">
						<h4 class="carriage-info item">
							${airline.flightCompanyName} <span class="text-info">${airline.flightNo}</span>
						</h4>
						<p class="path-infos item" style="margin-left: 30px;">
							起飞： <strong>${date} &nbsp;&nbsp;${airline.depTime}</strong>
							${airline.orgCityName} <br> 到达： <strong> ${date}
								&nbsp;&nbsp;${airline.arriTime}</strong>${airline.dstCityName}
						</p>
						<p class="price-infos item" style="font-size: 14px !important;">
							<strong class="text-danger" style="font-size: 14px !important;">单张票价:¥<span id="intoPrice">${totlPrice}</span>
							</strong> / 成人（含票面价${airSeat.parPrice }+机建费${airline.adultAirportTax}+燃油费${airline.adultFuelTax}）${airSeat.seatMsg} 
						</p>
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
				<!-- 表单 -->
					<!-- 航班信息 -->
				<form action="${ctx}/air/airOrderCreate" id="from" method="post">
					<!-- 航空公司 -->
					<input type="hidden" name="flightCompanyName" value="${airline.flightCompanyName}">
					<!-- 舱位 -->
					<input type="hidden" name="seatMsg" value="${airSeat.seatMsg}">
					
					<!--舱位编码  -->
					<input type="hidden" name="seatCode" value="${airSeat.seatCode}">
					<!--航班号  -->
					<input type="hidden" name="flightNo" value="${airline.flightNo}">
					<!-- 出发日期 -->
					<input type="hidden" name="date" value="${date}">
					<!-- 起飞站点(机场)三字码 -->
					<input type="hidden" name="from" value="${airline.orgCity}">
					<!-- 抵达站点(机场)三字码 -->
					<input type="hidden" name="to" value="${airline.dstCity}">
					<!-- 航空公司编码 -->
					<input type="hidden" name="companyCode" value="${airSeat.airlineCode}">
					<!--乘客信息  -->
					<div class="section">
						<div class="section-title">
							<h3>乘客信息</h3>
						</div>
					</div>
					<div class="well well-sm well-light">
						<div class="alert alert-info">
							<strong>注意：</strong> 填写乘客信息请务必保证您的手机号码填写正确，以便及时接收到行程信息
						</div>
						<table class="pure-table pure-table-bordered" style="min-width: 100%">
							<thead>
								<tr>
									<th>类型</th>
									<th>姓名</th>
									<th>证件类型</th>
									<th>证件号码</th>
									<th>手机号码</th>
									<th>操作</th>
								</tr>
							</thead>
							<!-- 乘客信息 -->
							<tbody id="passengerTable">

							</tbody>
						</table>
						<div class="form-btn-content" style="padding-bottom: 10px;">
							<a id="addcustomerButton"
								class="btn-primary pure-button pure-button-primary"
								href="javascript:void(0)" onclick="addck()">增加乘客</a>
						</div>
					</div>

					<!--联系人信息  -->
					<div class="section-title well-light" >
						<h4>联系人信息</h4>
						<table class="pure-table pure-table-bordered"
							style="margin-top: 10px; ">
							<thead>
								<tr>
									<th>联系人:</th>
									<th>联系电话:</th>
									
								</tr>
							</thead>
							<tr>
								<td><input type="text" id="contactName" style="width: 118px;" autocomplete="off"  name="contactName"></td>
								<td ><input type="text" id="contactTel"  style="width: 118px;" autocomplete="off"  name="contactTel"></td>
								
							</tr>
						</table>

					</div>
					<!--保险  -->
					<div class="section-title well-light" >
						<h4>保险</h4>
						<table class="pure-table pure-table-bordered"
							style="margin-top: 10px; width: 130px">
							<thead>
								<tr>
									<th>保险:</th>
								</tr>
							</thead>
							<tr>
								<td>
								<select style="width: 130px;">
										<option value="" selected="selected">不购买保险</option>
								</select>
								</td>
							</tr>
						</table>
					</div>

					<p class="grand-total"
						style="font-size: 18px !important; font-weight: bold; text-align: right;">
						合计:<span id="totalPrice" class="text-danger">${totlPrice}</span>
						（单张含票面价${airSeat.parPrice }+机建费${airline.adultAirportTax}+燃油费${airline.adultFuelTax}+保险费0.0）
					</p>
					<div class="well">
						<h4 class="text-danger" style="font-size: 15px;">仅供参考！请以航空公司为准！</h4>
						<h5 style="line-height: 20px;">退票规定：</h5>
						<p>
							1. &nbsp;交易完成，退订订单，支持选择子单退订。<br>
							2.“退废票”时间限制：下单当天同时距离起飞时间四小时以上，详细见平台规则。
						</p>
					</div>
				</form>
				<!-- 平台飞机票服务协议--------------------->
				<div id="xy" class="well agreement"  style=": none;">
					<div class="content">
						<p>欢迎使用本站飞机票网上代购服务，使用本服务前必须仔细阅读本协议，用户同意本协议中所有内容后方可对接使用。</p>
						<h5>一、购票时间</h5>
						<p>
							本站机票购票为8：00-22：00，在购票时间段内，你可以查询、购买。 <span class="text-danger">售后处理时间8:00-22:00，请仔细关注下我们的处理时间，处理时间外如果无法乘机、无法退票等问题，本站不承担相应责任。</span>
						</p>
						<h5>二、出票</h5>
						<p>购票成功后，会收到确认出票短信及电子票号；出票成功后，带着订票时有效身份证去值机柜台拿登机牌，请提前2小时办理登机。</p>
						<p>
							<span class="text-danger">报销凭证：行程单。</span> 可以在机场打印。
						</p>
						<p>小贴士：什么是行程单，如何获取？</p>
						<p>a. 什么是行程单？</p>
						<p>电子机票行程单是旅客购买电子机票的付款报销凭证，但不作为机场办理乘机手续和安全检查的必要证明。行程单最晚在航班起飞后7天内打印。电子客票行程单就是国内机票的报销凭证。国家规定行程单只能打印一次，无法再次打印。</p>
						<p>b.如何获取？</p>
						<p>目前暂不支持行程单的打印及快递服务，如有需要，请自行前往机场寻找对应的航空公司柜台处打印。</p>
						<h5>机票行程单打印须知：</h5>
						<p>a 在航空公司以及各机票售票处，未能在乘坐飞机后7日内领取行程单的旅客，可以在购票站补打。</p>
						<p>b 在机场领取行程单的乘客，请务必在起飞后30天内领取，超过30天以后申请打印行程单服务无效。</p>
						<p>c 在指定的机场柜台行程单领取，一般的航空公司都会在机场设置机行程单领取柜台，可以咨询机场工作人员。</p>
						<h5>三、退废票</h5>
						<p>1.航空公司上班时间内，出票当日可提交退废票，退废票手续费10元/张（奥凯航空废票手续费20元/张），特殊节假日期间航空公司有权随时调整退废票规则，实际请款以航空公司最新公布规定为准；</p>
						<p>2.非当天起飞航班在航空公司下班前1小时提交退票申请；</p>
						<p>3.当天起飞航班在飞机起飞前3小时提交退废票申请；</p>
						<p>4.特殊产品订单废票规则根据出票条件而定；</p>
						<p>5.金鹿废票：编号里有多个人的订单，不能分开提交废票申请；</p>
						<p>6.特殊节假日不允许废票；</p>
						<p>
							<span class="text-danger">特别提醒：</span>
							凡是客票中备注了"不得退票"的客票，按照航空公司规定均只退机建然后税费。另本站展示信息均来源于航空公司客规，仅供参考，一切规定以航空公司最新标准为准；因此产生的问题，我站不承担相应责任。
						</p>
						<p>各大航空公司退废票规定：http://staticb2b.liantuo.com/liantuo/html/tuip.html。（请复制到浏览器地址栏中打开）</p>
						<p class="text-info">注意：退废票类型</p>
						<p class="text-info">无需联系客服，用户自行在线退废票：</p>
						<p class="text-info">【当日废票】：当日作废，收取10元手续费，退票选"退废票"选项</p>
						<p class="text-info">【当日废票】：当日作废，奥凯航空公司收取20元手续费，退票选"退废票"选项</p>
						<p class="text-info">【自愿退票】：客人自愿退票，按客规收取手续费</p>
						<p class="text-info">请根据以下情况联系平台客服处理退票：</p>
						<p class="text-info">【非自愿退票】：航班延误、取消，申请全退</p>
						<p class="text-info">【非自愿退票】：客人因病无法乘机，申请全退，需病退证明</p>
						<p class="text-info">【非自愿退票】：前段延误导致后期无法乘坐，申请全退（填写前段票号）</p>
						<p class="text-info">【升舱换开】：申请全退（填写新票号）</p>
						<p class="text-info">【升舱换开】：升舱换开，申请自愿退票</p>
						<p class="text-info">【升舱换开】：名字错，换开重出，申请全退（填写新票号）</p>
						<h5>四、退废票款项到账时间</h5>
						<p>自愿退票订单，将在三个工作日内完成退款；</p>
						<p>非自愿的或者异常订单按照航空公司相关规定处理；</p>
						<p>非自愿退票PNR中有UN的，升舱重购的，7个工作日内退款；</p>
						<p>正常非自愿退票10个工作日退款；</p>
						<p>升舱换开、差额退款的在7-15工作日退款；</p>
						<p>病退、迫降，航空公司倒闭等特殊原因导致需要退票的在一个月内完成退款；</p>
						<p>注：以上规定在用户提交相应证明后完成提交退废票的订单，按照航空公司客票规定处理退票退款；</p>
						<p>如因航班延误、天气原因等需出具航空公司相关证明全退处理的客票，请用户及时提交证明加快处理速度；</p>
						<p>注：以上规定未涉及的情况按国内运输客规规定执行。</p>
						<h5>五、改签</h5>
						<p>目前暂不支持改签业务，需用户自行联系航空公司办理。</p>
						<p class="text-danger">注意：姓名填写错误，将无法更改，只能废票，请仔细核对用户身份证号及姓名。</p>
						<h5>六、免责声明</h5>
						<p>1.本站提供的机票查询的班次信息、票价信息（包括但不限于）仅作为参考，用户以具体付款为准，在本站上代购的机票的班次、票价、始飞时间、抵达时间等以实际为准。</p>
						<p>2.本站对此等信息的准确性、完整性、合法性或真实性均不承担责任，此外，本站对任何使用或者提供网站信息的商业活动及风险不承担任何责任。</p>
						<p>3.本站禁止代理商未经用户的允许出售保险，因此产生的纠纷与本站无关。</p>
						<p>4.您应该对使用本站提供的服务结果自行承担风险。</p>
						<p>5.本站有补充此协议的权利。</p>
					</div>
				</div>




				<div class="form-btn-content">
					<label> <input id="agree_chk" class="" type="checkbox"
						onclick="agree_chk()" />我已阅读并同意 
						<a class="agree-toggle"
						href="javascript:void(0)"
						onclick="airfuwu()"
						style="color: blue;">《平台飞机票服务协议》 </a>
					</label> <br> 
					<a style="width: 120px;" id="confirmAirOrderBtn"
						class="btn-primary pure-button pure-button-primary"
						disabled="disabled" href="javascript:void(0)">确认并提交订单</a>
					&nbsp;&nbsp; 
					<a class="btn-primary pure-button pure-button-primary"
						href="javascript:history.go(-1);">取消预定</a>
				</div>


				<br> <br> <br>
				<!-- 提示框 -->
				<div class="sgBtn" style="display: none;">弹窗3(警告)</div>
			</div>
		</div>





<script type="text/javascript">
	//乘客数量初始化
	var s;
	//乘客是否保存状态
	var a = 0;
	$(function() {
		//进来第一次调用addck方法
		intoaddck();
	})
	//初始乘客
	function intoaddck() {
		s = $("#passengerTable").children().length;
		s = parseInt(s) + parseInt('1');
		var tr = "<tr>"
				+ "<td><select class='text-td' > <option value='0'>成人票</option></select></td>"
				+ "<td><input   type='text'autocomplete='off'  name='listName'></td>"
				+ "<td  ><select class='text-td' > <option value='1'>身份证</option></select></td>"
				+ "<td><input   type='text'autocomplete='off' name='listCertificates'></td>"
				+ "<td><input   type='text' autocomplete='off' name='listPhone'></td>"
				+ "<td>"
				+ "<a id='' class='btn' href='javascript:void(0)' onclick='save($(this))'>保存</a>&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<a id='' class='btn' href='javascript:void(0)'  onclick='removetr($(this))'>取消</a>" 
				+ "</td>"
				+ "</tr>";
		$("#passengerTable").append(tr);

	}

	
	
	/*添加乘客 */
	function addck() {
		//添加乘客时先判断之前的乘客信息是否保存
		if (a == 0) {
			var txt = "请先保存乘客信息";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		//初始乘客状态
		a = 0;
		s = $("#passengerTable").children().length;
		if (s < 5) {
			var tr = "<tr>"
				+ "<td ><select class='text-td' > <option value='0'>成人票</option></select></td>"
				+ "<td ><input   type='text' autocomplete='off'  name='listName'></td>"
				+ "<td ><select class='text-td' > <option value='1'>身份证</option></select></td>"
				+ "<td><input   type='text' autocomplete='off'  name='listCertificates'></td>"
				+ "<td><input   type='text' autocomplete='off' name='listPhone'></td>"
				+ "<td>"
				+ "<a id='' class='btn' href='javascript:void(0)' onclick='save($(this))'>保存</a>&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<a id='' class='btn' href='javascript:void(0)'  onclick='removetr($(this),"
				+ 0 + ")'>取消</a>" + "</td>"
				+ "</td>" + "</tr>";
			$("#passengerTable").append(tr);
			//改变合计价格
			//初始价格变量
			var Price = $("#intoPrice").html()
			//获取原价
			var totalPrice = $("#totalPrice").html();
			//改变价格
			var newtotalPrice = parseInt(totalPrice) + parseInt(Price);
			//覆盖原来的价格
			$("#totalPrice").html(newtotalPrice + ".00");
		} else {
			showPopup();
		}

	}

	/* 保存乘客信息加校验 */
	function save(e) {

		var inps = $(e).parent().parent().find('input');//移除a标签的父级的父级元素tr

		//获取当前的数字
		//校验用户名
		var name = inps[0].value;
		if (name == '' || name == null) {
			var txt = "请填写正确用户名";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}

		//校验身份证号
		var sid = inps[1].value;
		//定义一个全国地区的对象
		var sfz = isCardID(sid);
		//将验证通过的身份证号放到一个全局数组里面
		if (sfz != true) {
			var txt = sfz;
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}else{
			//校验身份证号码是否重复
		     var sfztr=$(e).parent().parent().prevAll();
			
			
			for (var i = 0; i < sfztr.length; i++) {
				 
				var array_element = sfztr[i];

				var sfzinps=$(array_element).find('input');

				var sfzval=sfzinps[1].value;

				if(sid==sfzval){
					var txt = "身份证号码重复,一人一证.";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
					return false;
				}
			}
		}

		//校验电话号码
		var phone = inps[2].value;
		if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(phone))) {
			var txt = "请填写正确的手机号码";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		//隐藏填写的信息
		var trck = $(e).parent().parent().remove();
		//$(trck).css("display", "none");
		//展示乘客信息
		var tr = '<tr>'
				+ '<td >成人票</td>'
				+ '<td>'
				+ name
				+ '<input type="hidden" name="listName" value='+name+'>'
				+ '</td>'
				+ '<td>身份证</td>'
				+ '<td>'
				+ sid
				+ '<input type="hidden" name="listCertificates" value='+sid+'>'
				+ '</td>'
				+ '<td>'
				+ phone
				+ '<input type="hidden" name="listPhone" value='+phone+'>'
				+ '</td>'
				+ '<td>'
				+ '<a  class="btn" href="javascript:void(0)" onclick="removetr($(this))">删除</a>'
				+ '</td>'
			    + '</tr>';
		//添加到tbody
		$("#passengerTable").append(tr);
		a = 1;
	}

	/* 校验身份证号 */
	var aCity = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	};
	function isCardID(sId) {
		var iSum = 0;
		var info = "";
		if (!/^\d{17}(\d|x)$/i.test(sId))
			return "你输入的身份证长度或格式错误";
		sId = sId.replace(/x$/i, "a");
		if (aCity[parseInt(sId.substr(0, 2))] == null)
			return "你的身份证地区非法";
		sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-"
				+ Number(sId.substr(12, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d
				.getDate()))
			return "身份证上的出生日期非法";
		for (var i = 17; i >= 0; i--)
			iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
		if (iSum % 11 != 1)
			return "你输入的身份证号非法";
		//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女");//此次还可以判断出输入的身份证号的人性别
		return true;
	}

	/* 移除乘客信息 */
	function removetr(e) {
		/* //移除当前元素上一个兄弟节点
		if (s != 0) {
			$(e).parent().parent().prev().remove();
		} */
		//var a=$(e)[0].tagName; 元素节点
		$(e).parent().parent().remove();//移除a标签的父级的父级元素tr
		//移除乘客改变总价格
		//初始价格变量
		var Price = $("#intoPrice").html()
		//获取原价
		var totalPrice = $("#totalPrice").html();
		//改变价格
		var newtotalPrice = parseInt(totalPrice) - parseInt(Price);
		//覆盖原来的价格
		$("#totalPrice").html(newtotalPrice + ".0");
		//删除乘客改变乘客状态
		a = 1;
	}

	//提交之前的检验
	function tijiao() {
		//乘客信息校验
		s = $("#passengerTable").children().length;
		if (s == 0) {
			var txt = "请至少添加一位乘客!";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}

		if (a == 0) {
			var txt = "请先保存乘客信息";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}

		//订票人姓名校验
		var contactName = $("#contactName").val();
		if (contactName == '' || contactName == ' ') {
			var txt = "请填写订票人姓名";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		//订票人电话校验
		var contactTel = $("#contactTel").val();
		if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(contactTel))) {
			var txt = "订票人电话错误";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}

		//检验通过提交表单
		$("#win-wrapper").fadeIn();
		$("#from").submit();

	}

	function agree_chk() {
		//平台服务条款校验
		var checkbox = $("input[type='checkbox']").is(':checked');
		if (checkbox) {
			$("#confirmAirOrderBtn").attr("disabled", false);
			$("#confirmAirOrderBtn").attr('onclick', 'tijiao()');
		} else {
			$("#confirmAirOrderBtn").attr("disabled", true);
			$("#confirmAirOrderBtn").attr('onclick', '');
		}

	}
</script>
<!-- 弹出层 -->
<script type="text/javascript">
	function showPopup() {
		var txt = "最多只能添加5名乘客!";
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
	}
</script>







		<script type="text/javascript">
		function airfuwu() {
			var display=$("#xy").css("display");
			if(display=='none'){
				$('#xy').css('display', 'block');
			}else{
				$('#xy').css('display', 'none');

			}

		} 
		
		</script>
</body>
</html>