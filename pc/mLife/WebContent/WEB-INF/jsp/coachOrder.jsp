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
		<div id="maincontent" class="pure-u-5-6 mainbackground page-wrapper">
	

			<div class="section title-section">
				<ul class="step-nav horizontal">
					<li class="item"><i class="step-num">1</i><span class="char">选择车次</span></li>
					<li class="item active"><i class="step-num">2</i><span
						class="char">提交订单</span></li>
					<li class="item"><i class="step-num">3</i><span class="char">确认并支付</span></li>
					<li class="item"><i class="step-num">4</i><span class="char">成功</span></li>
				</ul>
				<h2 class="with-pop">
					汽车票 <span class="label label-info">服务说明</span>
				</h2>

			</div>
			<div class="section">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">乘车信息</h3>
					</div>
					<input type="hidden" value="50" id="singleTicketPrice"
						name="singleTicketPrice">
					<input type="hidden" id="coachLineJson" value='${coachLineJson }'>
					<table class="table table-striped table-centered">
					
						<thead>
							<tr>
								<th>发车时间</th>
								<th>始发/终点</th>
								<th>出发站/到达站</th>
								<th>车型/车次</th>
								<th>票价</th>
							</tr>
						</thead>
						<tbody id="coachInfo">
							<tr>
								<td>${coachLine.departure }&nbsp;${coachLine.dptTime }</td>
								<td>${coachLine.departure}<br>${coachLine.destination}</td>
								<td>${coachLine.dptStation}<br>${coachLine.arrStation}</td>
								<td>${coachLine.coachType}<br>${coachLine.coachNO}</td>
								<td id="ticketPrice">￥${coachLine.ticketPrice + coachLine.fee}*<span id="passengercount">1</span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a id="addCustomer" class="right" href="javascript:void(0);">增加乘客</a>
						<h3 class="panel-title">乘客信息</h3>
					</div>
					<table class="table table-striped table-centered">
						<thead>
							<tr>
								<th>类型</th>
								<th>乘客姓名</th>
								<th>证件类型</th>
								<th>证件号码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="customerInfo">

							<tr class="coachpassengers">
								<td>成人票</td>
								<td><input type="text"
									name="customerName" class="input-mini customerName" maxlength="10" id="customerName1"
									autocomplete="off" ><span id="err_passengername" style='color:red; visibility:hidden;'>请输入姓名</span></td>
								<td><select class="free" id="identifyType"
									name="identifyType">
										<option value="身份证">二代身份证</option>
								</select></td>
								<td class="aaaa"><input type="text" class="identifyNumber"
									name="identifyNumber" maxlength="20" autocomplete="off" >
									<span style='color:red; visibility:hidden;'>请输入身份证号</span></td>
								<td><a href="javascript:void(0)" onclick="delpassenger(this)" class="btn">删除</a></td>
							</tr>
						</tbody>
					</table>
					<span class="right" id="errorInfo"></span>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">联系人信息</h3>
					</div>
					<div class="panel-body list list-vertical">
						<div class="item item-input">
							<label class="input-label" for="">联系人姓名：</label>
							<div class="input-content">
								<input type="text" class="form-control" id="contactName"
									maxlength="10" value="" name="contactName" autocomplete="off"><span style='color:red; visibility:hidden;'>请输入联系人姓名</span>
							</div>
						</div>
						<div class="item item-input">
							<label class="input-label" for="">联系人身份证号：</label>
							<div class="input-content">
								<input type="text" class="form-control" id="idNumber"
									maxlength="20" value="" name="idNumber" autocomplete="off"><span style='color:red; visibility:hidden;'>请输入联系人身份证号</span>
							</div>
						</div>
						<div class="item item-input">
							<label class="input-label" for="">联系人电话：</label>
							<div class="input-content">
								<input type="text" class="form-control" maxlength="11"
									id="contactTel" name="contactTel" autocomplete="off"><span style='color:red; visibility:hidden;'>请输入联系人电话</span>
							</div>
						</div>

						<div class="item item-input">
							<label class="input-label" for="">保险：</label>
							<div class="input-content">
								<select class="form-control select-free item" id="insuranceName"
									name="insuranceName">

									<option value="">不购买保险</option>
								</select> <span id="insuranceNumber" class="char item"></span>

								<div class="with-pop with-pop-inline"></div>

							</div>
						</div>


						<input type="hidden" id="insuranceFee" value="0">
						<!-- 保险零售价-->
						<input type="hidden" id="insuranceFeeIn" value="0">
						<!-- 保险零进价-->

					</div>
				</div>

				<p class="grand-total" id="saleprice">
					<strong id="advicePrice" class="text-danger"></strong><br>
					<span id="trainPrice">车票价格：${coachLine.ticketPrice +coachLine.fee}元</span> <span id="insurancePrice">保险费用：0元
						服务费用：${coachLine.fee}元</span><br> 
				</p>
				<div id="service-contact-train" class="service-contact"
					style="display: none;">
					<a class="close" href="javascript:agreementHide();">×</a>
					<div class="content">
						购票须知<br> 非常感谢您使用千米网汽车票
						(下称本网站)提供的售票服务。为了保护您的合法权益请您在使用服务前仔细阅读以下内容。一旦您选择提交订单按钮，将视为您完全同意本网站的《服务条款》、《购票须知》以及其他提示条款。如您不同意完全接受以上条款，请放弃使用本网站提供的服务，由此造成的不便，请您谅解。<br>
						本网站为您提供本汽车票售票服务。为了持续为旅客提供便捷服务，需要向使用购票服务的用户收取一定服务费。如您不愿意支付此项购票服务费，请放弃购票服务，选择前往车站购票。<br>
						第一条 关于购票<br> 1：网上可以提前几天购票？<br>
						各地网上订票预售期一般在1-7天不等，重大节假日车站可能会提前预售期，请您留意！<br> 2：千米网网上订票收取手续费吗？<br>
						由于运营成本的考虑，千米网会收取一定的手续费，具体请以网站最新变化为准！<br>
						3：网上订票能否用护照、香港身份证、台胞证或其他有效证件购票呢？<br>
						网上订票只支持大陆居民第二代身份证号码，其他证件无法下单购票。<br> 4：身份证丢失或过期影响网上订票吗？<br>
						过期的身份证号码可能会导致无法顺利取票，建议您用其他有效身份证号码进行订票。<br>
						5：汽车票有没有实施实名制？可以让亲朋好友代购票吗？<br> 目前购买汽车票无需实名制，乘客可以由亲友代为购票。<br>
						6：订票成功后为什么没显示座位号信息？<br>
						由于订票时无法在线选座，因此取票信息中不包含座位号，您去车站取票时取票系统会自动生成座位号。<br>
						7：一次性购买多张车票要如何操作？<br>
						每个订单最多可以购买3张车票，由于不同车站要求不同，单个身份信息可能存在不能连续购买同一个车站的多个车次的车票。<br>
						8：网上订票能同时预订往返汽车票吗？<br> 预订下单时不能同时购买往返汽车票，返程车次需要单独预订呢~<br>
						9：网上可以预订儿童票吗？<br> 目前网上暂时无法预订儿童票，请您到发车站指定窗口进行咨询购买，感谢您的理解！<br>
						10：儿童票购买标准是怎样的？<br>
						一般情况下，持一张全价票的旅客可免费携带1.20米以下儿童一人乘车，但购票时应事先向售票员声明，不供给座位；儿童身高1.20-1.50米需购买儿童票，提供座位；超过1.50米应购买全价票。由于每个汽车站的规定不同，具体要以当地汽车站信息为准。<br>
						11：网上可以用学生证或其他优待证购买优惠票吗？<br> 千米网目前仅售全价票，暂无活动优惠和折扣票出售。<br>
						12：什么时间段可以预订汽车票？<br> 千米网提供08:00-22:00网上订票服务。<br>
						13：预订并提交订单后必须要立即支付吗？<br>
						提交订单后请您在系统提示时间内完成支付，一旦支付超时，订单会自动取消，需要您重新预订下单。<br>
						14：为什么付款成功后系统会提示出票失败？<br>
						因网络繁忙、余票不足、或者车站临时调整班次等原因，您付款成功后也不能保证100%出票，如付款成功后出票失败，建议您更换其他班次或前往车站购买，系统会在3个工作日内退款至您的付款账户中。感谢您的理解！<br>
						第二条 车次咨询问题<br> 1：为什么出发地栏提示找不到输入的城市名称？<br>
						该区域暂未开通网上预售票功能，您暂不能预订。<br> 2：为什么车次信息中没显示目的地车站名？<br>
						千米网暂时无法查询到目的地车站信息，建议您向乘车站窗口或咨询台工作人员进行咨询。<br>
						3：为什么车次信息中没显示抵达目的地的时间？<br>
						由于长途汽车行驶途中存在不确定因素，所以暂无法查询到准确的抵达时间，请您向车站窗口或咨询台工作人员进行咨询。<br>
						4：为什么去往同一地点的不同车次票价有差异？<br>
						由于不同车次的车型、行车路线有所差异，同一路线的不同车次票价会略有不同，详细情况您可以向乘车站进行咨询呢。<br> 第三条
						取票问题<br> 1：网上预订车票后要如何取票呢？<br>
						购票成功后，千米网会生成取票信息并发送短信给您，请您凭该取票短信和身份证原件到发车站自助取票机或服务专窗取票。<br>
						2：订票成功后没有收到取票短信怎么办？<br>
						如未收到取票短信，您可以联系千米网客服查询取票信息，也可以拨打客服电话咨询。<br>
						3：有没有规定发车前多久要去车站取票？<br>
						为避免塞车、出行人数较多等因素影响您的顺利出行，建议您提前半小时至一小时前往车站取票。<br>
						4：订票成功后发现填写的手机号码错误该怎么办？<br>
						订票时填写的手机号码错误会导致您无法收到取票短信，您可以联系千米网客服查询取票信息，凭取票信息和身份证取票。<br>
						5：可以由亲朋好友代取票吗？<br> 可以由他人代取票，代取人需凭取票信息和取票人身份证前往自助取票机或人工取票窗口取票。<br>
						6：没带身份证/凭身份证号能取票吗？<br> 由于各地车站规定不同，为确保您取票顺利，建议您携带身份证。<br>
						7：身份证过期了影响取票吗？<br> 身份证过期可能会导致无法顺利取票，建议您用其他有效的身份证号码进行订票。<br>
						8：车站自助取票机取不出来票该怎么办？<br>
						第一步请您先确认是否跑错了始发车站，第二步请您检查取票短信是否完整，是否被分成几条显示，避免取票时输入的信息有缺失。此外，核实取票时输入的取票信息（身份证号、取票号、取票密码、订单号）是否有误。如果自助取票机仍然无法出票，则可以去人工服务专窗取票。<br>
						9：可以提前几天去车站取票吗？<br> 您成功订票后可以提前几天取票的。<br>
						10：超过车站下班时间还能去车站顺利取票吗？<br>
						一般情况下车站在最晚班次发车前都不会下班的，您可以前往自助取票机或人工窗口取票，但也有可能个别车站会提前下班，建议在车站上班时间进行取票。<br>
						11：可以直接刷身份证取票吗？<br> 不能直接刷身份证取票乘车，您需要凭取票信息前往乘车站换取纸质车票再乘车。<br>
						12：网上订票后，可以去同城的其他车站取票上车吗？<br> 您需要按照订票信息在乘车站取票，同城其他车站是无法取票的。<br>
						第四条 退票改签问题<br> 1：网上可以直接退票或改签吗？<br>
						千米网暂不支持网上退票及改签，您需在先去车站换取纸质车票再前往窗口办理退改签。感谢您的理解！<br>
						2：车站退改签要收取多少手续费？<br>
						一般情况下，离开车时间2小时前办理退改签，按票面额10%收取手续费；班车离开车时间2小时以内办理退改签，按票面额20%收取手续费；班车开车后1小时内办理退改签，按票面额50%收取手续费；班车开车1小时后不办理退改签。由于每个汽车站的规定不同，具体要以乘车站规定为准呢~<br>
						3：错过了发车时间，可以退改签吗？<br>
						各地车站规定不同，一般情况下错过发车时间1个小时是不能办理退改签的，具体要以乘车站规定为准。<br>
						4：订票时选错了出发日期/车票数量，可以退票或改签吗？<br>
						您可以先去车站将订错的车票取出来，然后前往人工窗口办理退票或改签，车站会收取部分手续费。<br>
						5：一张订单包含3张车票，其中1张需要退票/改签，该如何操作？<br>
						您可以前往车站将3张车票一并取出，然后将需要退票/改签的车票拿到人工窗口办理。<br>
						6：我在线下车站申请退票后，网上的订票手续费会退给我吗？<br> 对不起，网上的订票手续费是不退的。<br>
						7：我在线下车站申请退票后，车站没有退现金，而是告知我退款到支付账户内，我怎么拿回我订票的钱？<br>
						您可以拨打我们客服电话进行咨询。<br> 第五条 订单退款问题：<br>
						1：订单出票失败了，需要我发起退款申请吗？<br>
						订单出票失败后会自动变更为退款订单，系统会在3个工作日内自动办理原路退款，无需您主动发起退款申请，您可以实时查看订单状态了解退款进度，订单出票失败后，退款时系统会给您发送短信通知，请您留意查收。<br>
						2：退款订单什么时候能退款到账？<br>
						千米网会在3-7个工作日内自动办理原路退款，视银行或第三方支付平台规定，约1-10个工作日到账。<br>
						3：失败订单退款是全额退的吗？手续费会退吗？<br> 失败订单是全额退回至您原先支付的账户的，包括订票手续费。<br>
						第六条 免责说明<br>
						1、由于全国各客运站会随时调整车次、票价、坐席等信息，故本网显示产品信息，如车次、票价、票价、发车时间等信息仅供参考，最终以实际购买的产品为准。<br>
						2、如因客户提供错误的订单信息（姓名、证件号码、日期、车次、座位类型等）或者因客户自身原因导致无法取票、车票丢失、车票损毁等情况所导致的损失，客户需自行承担相关损失费用。<br>
						3、配送订单因如下情况造成订单延迟或无法配送等，千米网将不承担责任：<br> ①客户提供错误信息、不详细信息；<br>
						②货物送达无人签收，由此造成的重复配送所产生的费用及相关的后果；<br>
						③不可抗力，例如：自然灾害、交通戒严、突发战争等。<br>
						4、订单退款将按承诺时限退款到客户订单支付的原渠道，客户支付渠道到客户账户的时间由支付渠道决定，请您与支付渠道沟通联系。<br>
						5、由于汽车票信息可能随时变化，以及网络传输问题，千米网提供的此类信息可能并非最新或存在误差，因此仅供旅客作为一般参考，任何公司或个人不能将此作为千米网做出任何承诺或做出任何保证的依据。<br>
						6、千米网提供的是汽车票代购服务，您接受本协议，意味着您同意我们使用您填写的乘客信息及取票信息进行代购，对于您违反购票服务条款引起的争议和法律问题，由您自行承担.<br>
						第七条 解决争议适用法律法规约定<br>
						在您的预订生效后，如果在本须知或订单约定内容履行过程中，您对相关事宜的履行发生争议，您同意按照中华人民共和国颁布的相关法律法规来解决争议，并同意接受千米所在地人民法院的管辖。<br>


					</div>
				</div>

				<div id="service-contact-insurance" class="service-contact"
					style="display: none;">
					<a class="close" href="javascript:void(0);">×</a>
					<div style="display: none;" id="insuranceTplId48034"
						name="insuranceTplId" class="content">
						<h4>1、保险方案简介：</h4>
						<p>利安人寿火车乘客意外伤害保险计划一</p>
						<table border="1px solid #DDD">
							<tbody>
								<tr>
									<td>产品名称</td>
									<td>保险责任项目</td>
									<td>每份保险金额</td>
									<td>保险期间</td>
									<td>保费</td>
								</tr>
								<tr>
									<td>利安人寿火车乘客意外伤害保险</td>
									<td>轨道交通意外伤害保障</td>
									<td>人民币5万元</td>
									<td>当次车次</td>
									<td>人民币2元/份</td>
								</tr>
							</tbody>
						</table>
						<p></p>
						<p>利安人寿火车乘客意外伤害保险计划二</p>
						<table border="1px solid #DDD">
							<tbody>
								<tr>
									<td>产品名称</td>
									<td>保险责任项目</td>
									<td>每份保险金额</td>
									<td>保险期间</td>
									<td>保费</td>
								</tr>
								<tr>
									<td>利安人寿火车乘客意外伤害保险</td>
									<td>轨道交通意外伤害保障</td>
									<td>人民币20万元</td>
									<td>当次车次</td>
									<td>人民币5元/份</td>
								</tr>
							</tbody>
						</table>
						<p></p>
						<p>新华人寿火车乘客意外伤害保险计划三</p>
						<table border="1px solid #DDD">
							<tbody>
								<tr>
									<td>产品名称</td>
									<td>保险责任项目</td>
									<td>每份保险金额</td>
									<td>保险期间</td>
									<td>保费</td>
								</tr>
								<tr>
									<td>新华人寿20元列车意外伤害保险</td>
									<td>轨道交通意外伤害保障</td>
									<td>人民币50万元</td>
									<td>当次车次</td>
									<td>人民币20元/份</td>
								</tr>
							</tbody>
						</table>
						<p></p>
						<p>注：根据中国保监会《关于父母为其未成年子女投保以死亡为给付保险金条件人身保险有关问题的通知》（保监发 [2010]95
							号）文件的规定，自2011年4月1日起，父母为其未成年子女（未满 18
							周岁）投保人身保险，在被保险人成年之前，各保险合同约定的被保险人死亡给付的保险金额总和、被保险人死亡时各保险公司实际支付的保险金总和均不得超过人民币
							10 万元（航空意外伤害死亡保额不受此限制）。超出限额的部分，保险公司不承担保险责任；但投保人有权要求返还对应的保险费。</p>

						<h4>2.
							保险对象：凡年龄在出生30天至80周岁的身体健康，乘坐火车的旅客，可作为被保险人投保本保险。成年人必须为本人投保；未成年人必须是其父母作为投保人。</h4>
						<h4>3.份数限制：
							每个被保险人在本公司的交通意外伤害保险计划同一保险期间内限保两份，超过限额投保的，本公司仅在限额内承担保险责任；但投保人有权要求返还对应的保险费。</h4>
						<h4>4.保险有效期：以保险单载明的保险期限为准，在保险期限内，为被保险人持有效车票以乘客身份乘坐火车，自进入火车车厢时起至抵达目的地走出车厢止，在此期间遭受的意外伤害；</h4>
						<h4>5.保单生效日前可申请退保（乘车日之前）；</h4>
						<h4>6.适用条款：本保险计划一、二适用条款为《利安人寿保险股份有限公司交通工具意外伤害保险（B款）条款》；本保险计划三适应条款为《新华人寿出行关爱交通意外伤害保险A款
							》。</h4>
						<h4>7.保险公司将委托明亚保险经纪有限公司为有需要的客户提供保险定额发票以作报销。保险定额发票仅作报销凭证，不是保单凭证；</h4>
						<h4>8.数据电文是合法的合同表现形式，电子保单与纸质保单具有同等法律效力，请妥善保存。您可登陆利安人寿保险网站www.lianlife.com或拨打利安人寿全国统一客服专线40080-80080进行保单验真查询；20元电子保单可在新华人寿网
							www.newchinalife.com 上查询和下载；</h4>
						<h4>9.本保险产品销售代理方是明亚保险经纪有限公司，客服电话：400-101-1136；本保险产品销售方是利安人寿保险股份有限公司，客服电话：40080-80080；以及新华人寿，客服热线95567。</h4>
						<h4>10.投保人及被保险人声明及确认：投保人、被保险人同意购买且认可保险金额，并已阅读保险条款的全部内容，了解并接受条款中保险责任、免除保险人责任、理赔服务等在内的重要事项，且投保信息填写真实正确，如有隐瞒或不实告知，愿意承担由此带来的法律责任。</h4>

						<h4>
							<a
								onclick="window.open('/help/trainAgreement/dzhjjfwwtxys.pdf');"
								target="_blank" href="javascript:void(0);"><span
								style="color: blue;">电子化经纪服务委托协议书</span></a> 及 <a
								onclick="window.open('/help/trainAgreement/jtgjywshbxtk.pdf');"
								target="_blank" href="javascript:void(0);"><span
								style="color: blue;">交通工具意外伤害保险（B款）条款</span></a>
						</h4>
					</div>
					<div style="display: none;" id="insuranceTplId48053"
						name="insuranceTplId" class="content">

						<h4>一、江苏千米网络科技有限公司为其平台客户及平台用户的客户通过其网络平台投保乘客意外险提供信息技术服务。</h4>

						<h4>
							二、通过江苏千米网络科技有限公司网络平台投保乘客意外险的客户（即投保人），均已仔细阅读中国人民财产保险股份有限公司<a
								onclick="window.open('/help/trainAgreement/piccInsuranceTk.pdf');"
								target="_blank" href="javascript:void(0);"><span
								style="color: blue;">《营运交通工具乘客意外伤害保险条款》</span></a>，尤其是对其中免除保险人责任的条款（包括但不限于责任免除条款、投保人被保险人义务等），已充分理解并接受上述内容，同意以此作为订立保险合同的依据，自愿投保如下保障：
						</h4>
						<p>保险期间：火车票始发时间起3日内</p>
						<p>保 险 费：5元</p>
						<p>保障责任：营运火车乘客意外伤害保险 保额10万元</p>
						<p>营运汽车乘客意外伤害保险 保额5万元</p>

						<h4>三、中国人民财产保险股份有限公司南京市分公司作为保险人，为通过江苏千米网络科技有限公司网络平台投保乘客意外险的客户提供保险责任保障、保单查询、报案理赔等服务，服务电话025－95518。</h4>

					</div>
				</div>

				<p class="submit-content">
					<label class="checkbox" for=""><input type="checkbox"
						id="agree_chk" value="1" name="agree" class="input_check">
						我已阅读理解并同意 <a class="contact-toggle" href="javascript:void(0);" onclick="agreements1()">《平台汽车票服务协议》</a>
						</label>
				</p>
				<div class="form-btn-content">
				<form action="createOrder" method="POST" onsubmit=" return coachsubmit()" id="myForm">
					<input type="hidden" name="coachLineJson" id="coachLine" value='${coachLineJson }'>
					<input type="hidden" name="passengersStr" id="passengerinfo" value="">
					<input type="hidden" name="contactJson" id="contactor" value="">
					<input type="hidden" name="totalPrice" id="totalPriceN" value="">
					<input type="button" id="createbill_btn" class="btn btn-primary btn-lg disabled" href="javascript:void(0);" value="确认并提交订单"> 
<!-- 					<a class="btn btn-default btn-lg" href="mLife/toCoach">取消预定</a> -->
						<a class="btn btn-default btn-lg" href="javascript:history.go(-1);">取消预定</a>
				</form>
				</div>
			</div>



		</div>
	</div>
	
	<script>
	/* 增加乘客 */
		var customerInfo = $("#customerInfo");
		var createBillBtn = $("#createbill_btn");
		var customerName = $("#customerInfo .customerName"),
			identifyNumber = $("#customerInfo .identifyNumber");
		
		/* 乘客信息 */
		var customerNameVal = [];
		var identifyNumberVal = [];
		
		var passagers = "";
		var contactData = {};
		
		/* 联系人信息 */
		var concatName;
		var idnumber;
		var concatPhone;
		
		$("#contactName").blur(function(){
			concatName = $(this).val();
			if($(this).val() == ""){
				$(this).parent().find("span").css("visibility","visible");
			}else{
				$(this).parent().find("span").css("visibility","hidden");
			}
		});
		$("#contactTel").blur(function(){
			concatPhone = $(this).val();
			if($(this).val() == ""){
				$(this).parent().find("span").css("visibility","visible");
			}else{
				$(this).parent().find("span").css("visibility","hidden");
			}
		});
		$("#idNumber").blur(function(){
			idnumber = $(this).val();
			if($(this).val() == ""){
				$(this).parent().find("span").css("visibility","visible");
			}else{
				$(this).parent().find("span").css("visibility","hidden");
			}
		});
		//增加乘客信息
		$(document).on('click',function(){
			customerName = $("#customerInfo .customerName");
			identifyNumber = $("#customerInfo .identifyNumber");
		});
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
		//提交订单信息
		createBillBtn.click(function(){
			//提交之前要确保所有的信息都填写完整
			var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//校验身份证
			var exp = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|177)\d{8}$/;//校验电话号码
			//***************************************************************
			var cd = $(".identifyNumber").parent().parent();//找到有多少个<tr>
			// alert("行数："+cd.length);
			var sfzval,sfzval2,idCard,ck,iSum = 0,info = "";;
				for (var i = 0; i < cd.length; i++) {
					//校验是否为空
					ck = cd.find("input")[2*i].value;
					sfzval=cd.find("input")[2*i+1].value;//得到第i行第一个乘客的身份证号码
					// alert(i);
					// alert(sfzval);
					if(ck == "" || null == ck || undefined == ck){
						var str1 = "第"+(i+1)+"行的乘客姓名不能为空"
						window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
						return false;
					}
					if(sfzval == "" || null == sfzval || undefined == sfzval){
						var str1 = "第"+(i+1)+"行的身份证号码不能为空"
						window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
						return false;
					}
					if(!/^\d{17}(\d|x)$/i.test(sfzval)){
						var str1 = "第"+(i+1)+"行的身份证号码长度或格式有误，请检查"
							window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
							return false;
					}
					sfzval = sfzval.replace(/x$/i, "a");
					if (aCity[parseInt(sfzval.substr(0, 2))] == null){
						window.wxc.xcConfirm("第"+(i+1)+"行的身份证地区非法", window.wxc.xcConfirm.typeEnum.confirm);
						return false;
					}
					sBirthday = sfzval.substr(6, 4) + "-" + Number(sfzval.substr(10, 2)) + "-"
					+ Number(sfzval.substr(12, 2));
					var d = new Date(sBirthday.replace(/-/g, "/"));
					if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d
							.getDate())){
						window.wxc.xcConfirm("第"+(i+1)+"行的身份证上的出生日期非法", window.wxc.xcConfirm.typeEnum.confirm);
						return false;
					}
					for (var m = 17; m >= 0; m--)
						iSum += (Math.pow(2, m) % 11) * parseInt(sfzval.charAt(17 - m), 11);
					if (iSum % 11 != 1){
						window.wxc.xcConfirm("第"+(i+1)+"行的身份证号非法", window.wxc.xcConfirm.typeEnum.confirm);
						return false;
					}
					for(var j = i+1;j < cd.length; j++){
						sfzval2=cd.find("input")[2*j+1].value;//得到第i行第一个乘客的身份证号码
						if(sfzval==sfzval2){
							var txt = "第"+(i+1)+"行和第"+(j+1)+"行的身份证号码重复!";
							window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
							return false;
							break;
						} 
						continue;
					}
					continue;
				}
			//联系人信息
			if($("#contactName").val() == "" || null == $("#contactName").val() || undefined == $("#contactName").val()){
				var str1 = "联系人不能为空"
				window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
				return false;
			}
			if($("#idNumber").val() == "" || null == $("#idNumber").val() || undefined == $("#idNumber").val()){
				var str1 = "身份证号码不能为空"
				window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
				return false;
			}
			if(!isIDCard1.test($("#idNumber").val())){
				var str1 = "身份证号码有误，请检查"
					window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
					return false;
			}
			if($("#contactTel").val() == "" || null == $("#contactTel").val() || undefined == $("#contactTel").val()){
				var str1 = "联系电话不能为空"
				window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
				return false;
			}
			if(!exp.test($("#contactTel").val())){
				var str1 = "电话号码有误，请检查"
				window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
				return false;
			}
			//先判断passagers中有没有信息，如果有则直接使用删除后的信息，没有则使用加载的信息
			if(passagers == ""){
				if(customerName){
					customerName.each(function(index,item) {
						customerNameVal.push($(item).val());
					});
				}
				if(identifyNumber){
					identifyNumber.each(function(index,item) {
						identifyNumberVal.push($(item).val());
					});
				}
				for(var i = 0; i < customerNameVal.length; i++){
					passagers += customerNameVal[i] + "," + "1," + identifyNumberVal[i] + ";";
					customerNameVal.splice(i,1);//清空姓名
					identifyNumberVal.splice(i,1);//清空身份证号码
					i--;
					console.log(passagers);
				}
				passagers = passagers.substring(0,passagers.length-1);
			}
			$("#passengerinfo").val(passagers);
			contactData = {
				"contactName" : concatName,
				"idNumber" : idnumber,
				"contactTel" : concatPhone
			}
			$("#contactor").val(JSON.stringify(contactData));
			$("#totalPriceN").val(parseFloat($("#advicePrice").text().split(":")[1]));
// 			alert("删除前："+passagers);
			//console.log(concatPhone);
			//console.log(JSON.stringify(contactData));  //{"contactName":"1357897987","idNumber":"55555555555555555555","contactTel":"18888888888"}
			//点击提交以后弹出一个提示框表示：正在操作，请稍后。。。
			$("#win-wrapper").fadeIn();
			$("#myForm").submit();
			/* AJAX只需要传过来就行了，俩参数，一个车票参数，一个乘客拼接的参数 */
			/* $.ajax({
				url:"createOrder",
				data:{
					coachLineJson:$("#coachLineJson").val(),
					passengersStr: passagers,
					contactJson:JSON.stringify(contactData),
					totalPrice:parseFloat($("#advicePrice").text().split(":")[1])
				},
				type:"POST",
				success:function(data){
					$("#win-wrapper").fadeOut();
					window.location.href='/';
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$("#win-wrapper").fadeOut();
					alert(XMLHttpRequest.status+"---"+textStatus+"----"+errorThrown);
					var txt = XMLHttpRequest.responseText.toString();
					txt = txt.replace(/[^\u4e00-\u9fa5]/g, "");//只得到所有的字符串里面的汉字
					var str1 = txt.replace(/(.).*\1/g,"$1");//去除重复的字符串
					window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.error);
				}
			});*/
			passagers = "";
		}); 
		
	a();
	$(".index10").addClass("check");
	var passengercount=1;
	
	$(document).ready(
		function() {
				
		var tprice=${coachLine.ticketPrice+coachLine.fee};
		var fee =${coachLine.fee};
		var totalprice=Number(tprice) + Number(fee);
		$("#advicePrice")[0].innerText="合计:"+totalprice.toFixed(2)+"元";
		$("#totalPrice").val(totalprice.toFixed(2));
		});
	$("#addCustomer").click(function(){
		
		if(passengercount<3){
			var tbody = $("#customerInfo");
			//tbody
			
			//		.append("<tr><th>成人票</th><th><input type='text'><th><input type='text'><th><input type='text'><th><a class='btn' onclick='delpassenger(this)'>删除</a></th></th></tr> ");
			tbody.append("<tr class='coachpassengers'>"
					+"<td>成人票</td>"
					+"<td><input type='text' name='customerName' class='input-mini customerName' maxlength='10' autocomplete='off'><span style='color:red; visibility:hidden;;'>请输入姓名</span></td>"
					+"<td><select class='free' id='identifyType' name='identifyType'><option value='二代身份证'>二代身份证</option></select></td>"
				  	+"<td><input type='text' class='identifyNumber' name='identifyNumber' maxlength='20' autocomplete='off'><span style='color:red; visibility:hidden;'>请输入身份证号</span></td>"
			  		+"<td><a href='javascript:void(0)' onclick='delpassenger(this)' class='btn'>删除</a></td>"
				 	+"</tr>"		
			);
			passengercount++;
			$("#passengercount")[0].innerText=passengercount;
			
			
			var tprice=${coachLine.ticketPrice};
			var fee =${coachLine.fee};
			var totalprice=(tprice+fee)*Number(passengercount) + fee;
			$("#advicePrice")[0].innerText="合计:"+totalprice.toFixed(2)+"元";
			$("#totalPrice").val(totalprice.toFixed(2));
			
		}else{
			window.wxc.xcConfirm("最多添加3位乘客哦", window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		
		a();
		
		
	});
	//删除乘客信息
	function delpassenger(obj){
		var myIndex = $(obj).closest('tr').index();//得到点击的是第几行
		var tr =$(obj).parent();//当前点击的行
		// alert(myIndex);
		var a = $(".identifyNumber").parent().parent();//得到tr的个数
		if(a.length == 1){
			window.wxc.xcConfirm("至少保留一位乘客", window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		$(tr).parent().remove();
		passengercount--;
		getPassenger(myIndex);
		var tprice=${coachLine.ticketPrice};
		var fee =${coachLine.fee};
		var totalprice=(tprice+fee)*Number(passengercount) + 1;
		$("#passengercount")[0].innerText=passengercount;
		//计算合计(删除乘客的时候动态的减少合计金额)
		$("#advicePrice")[0].innerText="合计:"+totalprice.toFixed(2)+"元";
		
	}
	
	function getPassenger(myIndex){
		var cd = $(".identifyNumber").parent().parent();//找到有多少个<tr>
		for(var i=0; i<cd.length; i++){
			
			if(customerName){
				customerName.each(function(index,item) {
					customerNameVal.push($(item).val());
				});
			}
			if(identifyNumber){
				identifyNumber.each(function(index,item) {
					identifyNumberVal.push($(item).val());
				});
			}
			for(var i = 0; i < customerNameVal.length; i++){
				passagers += customerNameVal[i] + "," + "1," + identifyNumberVal[i] + ";";
				customerNameVal.splice(i,1);//清空姓名
				identifyNumberVal.splice(i,1);//清空身份证号码
				i--;
			}
			passagers = passagers.substring(0,passagers.length-1);
		}
		var pl = passagers.split(";");
		var passager_ = "";
		for(var i=0;i<pl.length;i++){
			var idx = i;
			if(idx != myIndex){
				passager_ += pl[idx];
			}
		}
		passagers = "";
		console.log(passager_);
		for(var i=0; i < passager_.length; i++){
			passagers += passager_[i];
		}
// 		alert("删除后："+passagers);
		a();
	}
	function a(){
		var customerName = $(".customerName");
		var identifyNumber = $(".identifyNumber");
		customerName.blur(function(){
			if($(this).val() == ""){
				$(this).parent().find("span").css("visibility","visible");
			}else{
				$(this).parent().find("span").css("visibility","hidden");
			}
		});
		identifyNumber.blur(function(){
			if($(this).val() == ""){
				$(this).parent().find("span").css("visibility","visible");
			}else{
				$(this).parent().find("span").css("visibility","hidden");
			}
		});
		
		return false;
	};
	
	function agreements1(){
		if($("#service-contact-train")[0].style.display=="none"){
			$("#service-contact-train")[0].style.display="block"
		}else{
			agreementHide();
		}
	};
	
	function agreementHide(){
		$("#service-contact-train")[0].style.display="none";
	};
	
	$("input[name='agree']").click(function (){
		if($(this).is(':checked')){
			$("#createbill_btn").removeClass("disabled");
		}else{
			
			$("#createbill_btn").addClass("disabled");
		}
		
	});
	
	function coachsubmit(){
		
		if(a()){
			return false;
		}
		
	}
	
	
	
	</script>

</body>
</html>