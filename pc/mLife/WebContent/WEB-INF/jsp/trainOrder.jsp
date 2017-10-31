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
					火车票 <span class="label label-info">服务说明</span>
					<div class="pop-content" style="display: none;">
						<span class="corner"><i>&nbsp;</i></span>
						<li>如何购买火车票？</li>
						<br>
						<li>1.填写订单信息</li>
						<br>
						<li>2.提交订单并支付</li>
						<br>
						<li>3.我们将会第一时间进行出票</li>
						<li style="border-top: 1px solid #EEE; border-bottom: none"
							class="line">火车票买到后，怎么领取？</li> <br>
						<li>1.火车站售票窗口</li>
						<li>2.客票代售点：任意代售点取票，根据铁道部规定需加收5元/张的服务费</li>
						<li>3.部分动车组列车可持二代居民身份证直接检票进站</li>
						<li style="border-top: 1px solid #EEE; border-bottom: none"
							class="line">护照/港澳台通行证等如何取票？</li>
						<li>1.请持上述证件前往火车站售票窗口取票。</li>
						<li style="border-top: 1px solid #EEE; border-bottom: none"
							class="line">温馨提示：</li>
						<li>1.同日期、车次，相关证件只能购票一次。</li>
						<li>2.请输入正确的证件号码，乘客姓名与证件号码必须与乘车时所使用证件上的名字和号码一致。</li>
						<li>3.请确保您的身份信息需通过12306网站核验，如未核验，可至当地火车站进行核验。</li>
						<li>4.您的资金是安全的，如不能出票，票款会在3-7个工作日内退回到您的支付账户。</li>
						<li>5.支付成功后，我们会短信通知您出票情况，请您耐心等待。</li>
					</div>
				</h2>


			</div>
			<div class="section">
				<div class="well">
					<h4 class="brand-focus">
						<span id="trainNumber" class="text-info">${trainLine.trainNumber }<input
							type="hidden" value="G8504" name="trainNumber"></span>  &nbsp;&nbsp;日期:<span
							id="startDate">${trainDate}</span> <br> <small
							id="startEndInfo">${trainLine.currentStartStationName }(${trainLine.startTime })&nbsp;&nbsp;--&nbsp;&nbsp;
							${trainLine.currentEndStationName}(${trainLine.endTime })
						</small>
						<input type="hidden" id="trainLineJson" value='${trainLineJson}'>
						<input id="from" value=${trainLine.currentStartStationName } type="hidden">
						<input id="to" value=${trainLine.currentEndStationName } type="hidden">
						<input id="myDate" value=${trainDate} type="hidden">
					</h4>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">座次信息</h3>
					</div>
					<div class="panel-body list list-vertical" id="seatsUl" >

					</div>
				</div>
				
				
				
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<a id="addCustomer" class="right" href="javascript:void(0);">增加乘客</a>
						<h3 class="panel-title">乘客信息</h3>
					</div>
					<table class="table table-striped table-centered">
						<thead>
							<tr>
								<th>座位类型</th>
								<th>乘客</th>
								<th>证件类型</th>
								<th>证件号码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="customerInfo">
							<tr class="trainpassengers">
								<td><select  name ="seaetname" onchange="resumemoney();" class="free Customer seatp seatname" id="seatname">
								</select></td>
								<td><input type="text" style="width: 90px;"
									placeholder="请输入乘客姓名" name="customerName"
									class="input-mini customerName" maxlength="25" autocomplete="off"><span id="err_passengername" style='color:red; visibility:hidden;'>请输入姓名</span></td>
								<td><select class="free" id="identifyType"
									name="identifyType">
										<option value="二代身份证">二代身份证</option>
								</select></td>
								<td><input type="text" class="identifyNumber" placeholder="请输入身份证号"
									name="identifyNumber" maxlength="20" autocomplete="off"><span style='color:red; visibility:hidden;'>请输入身份证号</span>
									</td>
								<td><a href="javascript:void(0)" class="btn" onclick="delpassenger(this)">删除</a></td>
							</tr>
						</tbody>
					</table>
					<span class="right" id="errorInfo"></span>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">其他信息</h3>
					</div>
					<div class="panel-body list list-vertical">
						<div class="item item-input">
							<label class="input-label" for="">联系人：</label>
							<div class="input-content">
								<input type="text" class="form-control" id="concatName"
									maxlength="10" value="" name="concatName" autocomplete="off"><span style='color:red; visibility:hidden;'>请输入联系人姓名</span>
							</div>
						</div>
						<div class="item item-input">
							<label class="input-label" for="">联系电话：</label>
							<div class="input-content">
								<input type="text" class="form-control" maxlength="11"
									id="telphone" name="telphone" autocomplete="off"><span style='color:red; visibility:hidden;'>请输入联系人电话</span>
							</div>
						</div>
						<div class="item item-input">
							<label class="input-label" for="">保险：</label>
							<div class="input-content">
								<select class="form-control select-free item" id="insuranceName"
									name="insuranceName">
									<option >不购买保险</option>
									
								</select> 

								<div class="with-pop with-pop-inline"></div>

							</div>
						</div>
					</div>
				</div>

				
				
				
				
				<div id="service-contact-train" class="service-contact"
					style="display: none;">
					<a class="close" href="javascript:void(0);">×</a>

					<div class="content">
						<p>欢迎使用本站火车票网上代购服务，使用本服务前必须仔细阅读本协议，用户同意本协议中所有内容后方可对接使用。</p>

						<h3>一、购票</h3>
						<h4>1.购票时间</h4>
						<p>
							本站火车票相关业务处理时间为<span style="color: red;">8:00-22:00</span>，此时间段内您可以进行查询、购买、退票等火车票业务相关操作。请仔细关注下我们的处理时间，处理时间外如果无法乘车、无法退票等问题，本站不承担相应责任。
						</p>
						<h4>2.购票差价</h4>
						<p>由于火车票存在多种坐席及车次满足用户的需要，所以存在在购票过程中存在差价的问题，本站对其差价处理本着多退少补的原则，用户需积极配合补款以保证服务正常完成。</p>
						<h4>3.购实名制有效证件及购买数量</h4>
						<p>
							有效身份证件包括：<span style="color: red;">居民身份证、按规定可使用的有效护照、港澳居民来往内地通行证、中华人民共和国来往港澳通行证、台湾居民来往大陆通行证</span>等24种。为了提高购票速度，目前平台只支持二代居民身份证购票。<span
								style="color: blue;">一张有效身份证件同一乘车日期同一车次只能购买一张车票。</span>
						</p>
						<h4>
							4.出票失败“<span style="color: red;">身份待核验</span>”
						</h4>
						<p>“待核验”:指乘车人的身份信息未经核验，需持二代居民身份证原件到车站售票窗口或铁路客票代售点办理核验。</p>
						<h4>5.购票手续费</h4>
						<p>
							<span style="color: red;">禁止代理商向用户收取票面价格以外的手续费。</span>
						</p>
						<h4>6.保险</h4>
						<h4>保险险种：20元列车意外伤害保险，详情如下：</h4>
						<p>1、20元保险由新华人寿提供（出行关爱交通意外伤害保险A款 )；</p>
						<p>2、保额：20元列车意外伤害保险最高赔付￥50万元人民币；</p>
						<p>3、份数限制：每人每车次限购1份；</p>
						<p>4、保险有效期：当次列车有效，被保险人以乘客身份乘坐从事合法客运的列车期间（自持有效车票检票进入车厢时起，至抵达车票载明的终点离开所乘客运火车车厢时止）；</p>
						<p>5、年龄：凡零周岁(出生满30天)以上；</p>
						<p>6、保单：数据电文是合法的合同表现形式，电子保单与纸质保单具有同等法律效力，请妥善保存。
							20元电子保单可在新华人寿网站（ www.newchinalife.com ）上查询和下载；</p>
						<p>9、理赔：请致电新华人寿客服热线（95567）或登录新华人寿网站（www.newchinalife.com）办理；</p>
						<p>10、咨询：产品详细条款可访问新华人寿网站（www.newchinalife.com）或致电新华人寿客服热线（95567）进行垂询</p>


						<h3>二：取票</h3>
						<p>购票成功后，可凭订票使用的身份证在全国各火车站售票窗口、自助售票机或者铁路客票代售点取票。</p>

						<h3>三、退票</h3>
						<p>退票处理时间8:00-22:00。</p>
						<p>
							根据铁道部退票规定，退票手续费根据铁路公司梯次方案收取：<span style="color: red;">票面乘车站开车前48小时以上的，退票时收取票价5%的退票费；开车前24小时以上、不足48小时的，退票时收取票价10%的退票费；开车前不足24小时的，退票时收取票价20%退票费。</span>请给客服30分钟的处理订单时间，处理时间内导致的退票手续费金额变化我站不予承担。
						</p>
						<h4>1.如果由于铁路系统原因（如无票、身份待核验等）车票预订失败，不需要支付。</h4>
						<h4>
							2.<span style="color: red;">未取票，发车前大于4小时</span>
						</h4>
						<p>整单退票：支持在线订单整退。</p>
						<p>部分退票：暂不支持部分车票退票在线申请退票。如订单中部分车票需退票，请联系货源客服处理。</p>
						<p>分批退票：不支持订单中部分车票分批退票，如需退票请联系客服处理，该退票处理方式与火车站退票处理方式相似，待退款额至平台账户后由客服手动操作退票退款。</p>
						<h4>
							3.<span style="color: red;">未取票，发车前小于4小时</span>
						</h4>
						<p>
							<span style="color: blue;">退票：需您携带购票时所使用的乘车人有效证件原件取火车票，在发车前去任意火车站退票窗口办理退票。二代居民身份证无法自动识读或使用其他有效身份证件购票的还需订单号码，经售票人员验证一致后按现行规定办理。尽量保留退票凭证加快处理退票流程。</span>
						</p>
						<p>退款处理：待退款额至平台账户后由客服手动操作退票退款。</p>
						<h4>
							4.<span style="color: red;">已取票</span>
						</h4>
						<p>需您携带购票时所使用的乘车人有效证件原件和火车票在发车前去任意火车站退票窗口办理退票。尽量保留退票凭证加快处理退票流程。</p>
						<p>退款处理：待退款额至平台账户后由客服手动操作退票退款。</p>
						<h4>5.保险退单</h4>
						<p>
							<span style="color: blue;">保险不能单独退保，</span>
						</p>
						<p>
							<span style="color: blue;">在线退票退保险：在线申请退款时保险可以一并退款；</span>
						</p>
						<p>
							<span style="color: blue;">火车站退票退保险：火车站退票退保险的需根据具体情况判断是否予以退保险。</span>
						</p>
						<h4>6.退票退款到账时间</h4>
						<p>具体以实际到账时间为准，一般3-5个工作日。已取票火车站退票用户，退款到账时间为3-20个工作日。</p>

						<h3>四、改签</h3>
						<h4>1.不受理改签业务，需用户自行在发车前去车站办理。</h4>
						<h4>2.在车站窗口办理改签或退票时，须出具购票时所使用的乘车人二代居民身份证原件，二代居民身份证无法自动识读或使用其他有效身份证件购票的还需订单号码，经售票人员验证一致后按现行规定办理。</h4>

						<h3>五、免责声明</h3>
						<p>1.本站提供的火车票查询信息全部来自火车站及政府等信息平台（包括但不限于）。</p>
						<p>2.本站提供的车次信息、票价信息（包括但不限于）仅作为参考，用户以政府或者相关单位提供的官方数据为准，在本站上代购的火车票的车次、票价、始发时间、到站时间等以实际为准。</p>
						<p>3.本站对此等信息的准确性、完整性、合法性或真实性均不承担责任，此外，本站对任何使用或者提供网站信息的商业活动及风险不承担任何责任。</p>
						<p>4.本站禁止代理商向用户收取票面以外的服务费用，因此产生的纠纷与本站无关。</p>
						<p>5.本站禁止代理商未经用户的允许出售保险，因此产生的纠纷与本站无关。</p>
						<p>6.您应该对使用本站提供的服务结果自行承担风险。</p>
						<p>7.本站有补充此协议的权利。</p>

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
				

				<p id="saleprice" class="grand-total">
					合计：<strong class="text-danger" id="totalprice"></strong>元<br>
					车票价格：<span id="trainprice"></span>元 <span id="insuranceprice">保险费用：0元
						</span><br> 
				</p>


				<p class="submit-content">
					<label class="checkbox" for=""><input type="checkbox"
						id="agree_chk" value="1" name="agree" class="input_check">
						我已阅读理解并同意 <a class="contact-toggle"  id="agreements" href="javascript:void(0);">《平台火车票服务协议》</a>
						</label>
				</p>
				<div class="form-btn-content">
					<form action="createOrder" method="POST" id="myForm">
						<input type="hidden" id="trainLineJsonN" value='${trainLineJson}' name="trainLineJson">
						<input type="hidden" id="passengersN" value='${trainLineJson}' name="passengers">
						<input type="hidden" id="contactJsonN" value='${trainLineJson}' name="contactJson">
						<input type="hidden" id="totalPriceN" value='${trainLineJson}' name="totalPrice">
						<button id="createbill_btn" class="btn btn-primary btn-lg disabled" href="javascript:void(0);">确认并提交订单</button> 
						<a class="btn btn-default btn-lg" href="/front/ntraintickets/index/54098">取消预定</a>
					</form>
				</div>
			</div>
	</div>
	</div>
	
	<script>
	/* 乘客信息 */
	var customerNameVal = [];
	var seatNameVal = [];
	var identifyNumberVal = [];
	var passagers = "";
	var contactData = {};
	/* 新增加 */
	(function(){
		
		/* 获取加载保险 */
		$.ajax({
			type : "GET",
			url : "getInsuranceItems",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(data){
				var insuranceItems = eval(data);
				var insuranceName = $("#insuranceName");
				
				
				for(var i = 0; i < insuranceItems.length; i++){
					var itemName = insuranceItems[i].itemName.replace(/\s+/g,",");
					console.log(insuranceItems[i]);
					var price = itemName.replace(/[^0-9]/ig,"");
					var option = "<option data-price='"+price+"' value='"+insuranceItems[i].itemId+"'>"+ itemName +"</option>";
					insuranceName.append(option);
				}
			}
		});
		
		var customerInfo = $("#customerInfo");
		var createBillBtn = $("#createbill_btn");
		var seatName = $("#customerInfo .seatname"),
			customerName = $("#customerInfo .customerName"),
			identifyNumber = $("#customerInfo .identifyNumber");

		
		$(document).on('click','#addCustomer',function(){
			
			seatName = $("#customerInfo .seatname");
			customerName = $("#customerInfo .customerName");
			identifyNumber = $("#customerInfo .identifyNumber");
			resumemoney();
		});
		
		
		
		
		
		/* 联系人信息 */
		var concatName;
		var concatPhone;
		
		
		$("#concatName").blur(function(){
			concatName = $(this).val();
			if($(this).val() == ""){
				$(this).parent().find("span").css("visibility","visible");
			}else{
				$(this).parent().find("span").css("visibility","hidden");
			}
		});
		$("#telphone").blur(function(){
			concatPhone = $(this).val();
			if($(this).val() == ""){
				$(this).parent().find("span").css("visibility","visible");
			}else{
				$(this).parent().find("span").css("visibility","hidden");
			}
		});
		console.log($("#trainLineJson").val());
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
		/* 提交 */
		$(document).on("click","#createbill_btn",function(){
			//提交之前要确保所有的信息都填写完整
			var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//校验身份证
			var exp = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|177)\d{8}$/;//校验电话号码
			//***************************************************************
			var cd = $(".identifyNumber").parent().parent();//找到有多少个<tr>
			// alert("行数："+cd.length);
			var sfzval,sfzval2,idCard,ck,iSum = 0,info = "";;
				for (var i = 0; i < cd.length; i++) {
					var seatType = cd.find("select").find("option:selected").text().split("二代身份证")[i];
					if(!resumemoney2(seatType , i)){
						return false;
					}
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
			//联系人信息\
			if($("#concatName").val() == "" || null == $("#concatName").val() || undefined == $("#concatName").val()){
				var str1 = "联系人不能为空"
				window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
				return false;
			}
			if($("#telphone").val() == "" || null == $("#telphone").val() || undefined == $("#telphone").val()){
				var str1 = "联系电话不能为空"
				window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
				return false;
			}
			if(!exp.test($("#telphone").val())){
				var str1 = "电话号码有误，请检查"
				window.wxc.xcConfirm(str1, window.wxc.xcConfirm.typeEnum.confirm);
				return false;
			}
			var passagers = "";
			var customerNameVal = [];
			var seatNameVal = [];
			var identifyNumberVal = [];
			var seatName = $("#customerInfo .seatname"),
			customerName = $("#customerInfo .customerName"),
			identifyNumber = $("#customerInfo .identifyNumber");
			if(customerName){
				customerName.each(function(index,item) {
					customerNameVal.push($(item).val());
				});
			}
			if(seatName){
				seatName.each(function(index,item) {
					seatNameVal.push($(item).find("option:selected").text());
				});
			}
			if(identifyNumber){
				identifyNumber.each(function(index,item) {
					identifyNumberVal.push($(item).val());
				});
			}
// 			alert(customerNameVal.length);
			for(var i = 0; i < customerNameVal.length; i++){
				passagers += customerNameVal[i] + "," + concatPhone + "," + identifyNumberVal[i] + "," + seatNameVal[i] + ";";
				customerNameVal.splice(i,1);//清空姓名
				identifyNumberVal.splice(i,1);//清空身份证号码
				i--;
				console.log(passagers);//打印信息
			}
// 			alert("揭去之前的数据："+passagers+"长度："+passagers.length);
			passagers = passagers.substring(0,passagers.length-1);//去掉最后一个“;”号
			var insuranceId = $("#insuranceName").find("option:selected").val();
			contactData = {
					"contactName" : concatName,
					"contactTel" : concatPhone,
					"insuranceId" : insuranceId,
					"from" : $("#from").val(),
					"to" : $("#to").val(),
					"date" :$("#myDate").val()
				}
			var totalprice = parseFloat($("#totalprice").html());
// 			alert("提交的乘客信息："+passagers);
			//需要提交的信息
			$("#passengersN").val(passagers);
			$("#contactJsonN").val(JSON.stringify(contactData));
			$("#totalPriceN").val(totalprice);
			$("#createbill_btn").addClass("disabled");
			$("#myForm").submit();
			/* $.ajax({
				url:"createOrder",
				data:{
					trainLineJson:$("#trainLineJson").val(),
					passengers: passagers,
					contactJson:JSON.stringify(contactData),
					totalPrice: totalprice
				},
				type:"POST",
				success:function(data){
					$("#createbill_btn").removeClass("disabled");
					window.location.href='/';
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
// 					$("#win-wrapper").fadeOut();
					var txt = XMLHttpRequest.responseText.toString();
					alert(XMLHttpRequest.status+" -- "+textStatus+"  ++ "+errorThrown);
					txt = txt.split("h1")[1].replace(/[^\u4e00-\u9fa5]/g, "");//只得到所有的字符串里面的汉字
					var str1 = txt.replace(/(.).*\1/g,"$1");//去除重复的字符串
// 					alert("你好你".replace(/([.\s\S]{1})(?:\1+)/g,"$1"));
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
					$("#createbill_btn").removeClass("disabled");
				}
			}); */
			
		});
		
		
		
		
	})();
	
	</script>
	<script>
	$(".index11").addClass("check");
	var passengercount=1;
	
	$(document).ready(function (){

	var seats = ${trainSeats };
	
	
	var seatstxt ="<p class='horizontal'>";
	for (var i = 0; i < seats.length; i++){
		var seat = seats[i];
		var tic = seat.remainderTrainTickets?seat.remainderTrainTickets:0;
		
		seatstxt +="<label class='checkbox item' for='seat_businessseat '><input type='radio' style='display:none;'  seatprice='"
		+seat.seatPrice +
		"' value='"
		+seat.seatName+
		"' data='"
		+seat.remainderTrainTickets+
		"' value2='"
		+seat.seatId+
		"' name='seat'>"
		+seat.seatName+"(￥"+seat.seatPrice+"元)余票"+ tic +
		"张  </label>";
		
	
		$(".Customer").append("<option value='"
				+seat.seatPrice+
				"'>"
				+seat.seatName+
				"</option> ");
		
	}
	seatstxt +="</p>";
	$("#seatsUl").html(seatstxt);
	resumemoney();
	});
	
	
	
	
	$("#addCustomer").click(function(){
		
		if(passengercount<5){
			var tbody = $("#customerInfo");
			
			$(".free").removeClass("Customer");
			
			tbody.append("<tr class='trainpassengers'>"
					+"<td><select onchange='resumemoney();' class='free Customer seatp seatname'></select></td>"
					+"<td><input type='text' style='width: 90px;' placeholder='请输入乘客姓名' name='customerName'	class='input-mini customerName' maxlength='25' autocomplete='off'><span style='color:red; visibility:hidden;'>请输入姓名</span></td>"
					+"<td><select class='free' id='identifyType' name='identifyType'><option value='二代身份证'>二代身份证</option></select></td>"
				  	+"<td><input type='text' class='identifyNumber'name='identifyNumber' placeholder='请输入身份证号' maxlength='20' autocomplete='off'><span style='color:red; visibility:hidden;'>请输入身份证号</span></td>"
			  		+"<td><a href='javascript:void(0)' onclick='delpassenger(this)' class='btn'>删除</a></td>"
				 	+"</tr>"		
			);
			
			passengercount++;
			//$("#passengercount")[0].innerText=passengercount;
			var seats =${trainSeats };
			for (var i = 0; i < seats.length; i++){
				var seat = seats[i];
				$(".Customer").append("<option value='"
						+seat.seatPrice+
						"'>"
						+seat.seatName+
						"</option> ");
			}
			
			//var tprice=${coachLine.ticketPrice};
			//var fee =${coachLine.fee};
			//var totalprice=Number(Number(tprice)*Number(passengercount)) + Number(fee);
			//$("#advicePrice")[0].innerText="合计:"+totalprice+"元";
			if(parseInt(passengercount)==5){
				$(".free").removeClass("Customer");
			}
		}else{
			window.wxc.xcConfirm("最多添加5位乘客", window.wxc.xcConfirm.typeEnum.confirm);
		}
		resumemoney();
		checkInput();
	});
	function checkInput(){
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
	};
	checkInput();
	//删除乘客
	function delpassenger(obj){
		var jg = $(obj).closest('tr').find("select").val();//得到当前点击行的火车票的价格
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
// 		$("#passengercount")[0].innerText=passengercount;
		var tm = $("#trainprice").html();
		var zm = $("#totalprice").html();
// 		alert(tm+" "+zm);
		resumemoney3(tm , zm , jg);//删除以后价格的变动
		getPassenger(myIndex);
		
	}
	//得到乘客的信息
	function getPassenger(myIndex){
		var customerNameVal = [];
		var seatNameVal = [];
		var identifyNumberVal = [];
		var seatName = $("#customerInfo .seatname"),
			customerName = $("#customerInfo .customerName"),
			identifyNumber = $("#customerInfo .identifyNumber");
		var cd = $(".identifyNumber").parent().parent();//找到有多少个<tr> 删除以后的行数
		for(var i=0; i<cd.length; i++){
			if(customerName){
				customerName.each(function(index,item) {
					customerNameVal.push($(item).val());
				});
			}
			if(seatName){
				seatName.each(function(index,item) {
					seatNameVal.push($(item).find("option:selected").text());
				});
			}
			if(identifyNumber){
				identifyNumber.each(function(index,item) {
					identifyNumberVal.push($(item).val());
				});
			}
			passagers = "";
			for(var j = 0; j < customerNameVal.length; j++){
				passagers += customerNameVal[j] + "," + "1," + identifyNumberVal[j] + ";";
				customerNameVal.splice(j,1);//清空姓名
				identifyNumberVal.splice(j,1);//清空身份证号码
				j--;
			}
			passagers = passagers.substring(0,passagers.length-1);
		}
		var pl = passagers.split(";");
		var passager_ = "";
		for(var i=0;i<pl.length;i++){
			var idx = i;
			if(idx != myIndex){
				passager_ += pl[idx]+";";
			}
		}
// 		alert("删除之前："+passager_);
		passagers = "";
		console.log(passager_);
		for(var i=0; i < passager_.length; i++){
			passagers += passager_[i];
		}
// 		alert("删除后："+passagers);
// 		a();
	}
	$("input[name='agree']").click(function (){
		if($(this).is(':checked')){
			$("#createbill_btn").removeClass("disabled");
		}else{
			
			$("#createbill_btn").addClass("disabled");
		}
		
	});
	
	$("#agreements").click(function(){
		
		if($("#service-contact-train")[0].style.display=="none"){
			$("#service-contact-train")[0].style.display="block";
			
		}else{
			$("#service-contact-train")[0].style.display="none";
			
		}
	})
	
	$("#insuranceName").change(function(){
		resumemoney();
	});
	function resumemoney2(seatType , j){
		var flag;
		var seats = ${trainSeats };
// 		var obj = document.getElementById("seatname");
// 		var txt = obj.options[obj.selectedIndex].text;
		for (var i = 0; i < seats.length; i++){
			var seat = seats[i];
			var tic = seat.remainderTrainTickets?seat.remainderTrainTickets:0;
// 			alert("类型："+seatType+" "+seat.seatName);
			if(seatType == seat.seatName){
				if(tic == 0){
					window.wxc.xcConfirm("第"+(j+1)+"行的【"+seatType+"】无剩余票，请选择其他座位类型", window.wxc.xcConfirm.typeEnum.confirm);
					flag = false;
					return false;
				}else{
					flag= true;
				}
			}
			continue;
		}
		var totalprice =0;
		var trainprice = 0;
		var insuranceprice = 0;
		$(".seatp").each(function(){
			trainprice += Number($(this).val());
			insuranceprice += isNaN(Number($("#insuranceName").find("option:selected").data("price")))?0:Number($("#insuranceName").find("option:selected").data("price")) ;
		});
		totalprice = trainprice + insuranceprice + 1;
		$("#totalprice")[0].innerText =totalprice
		$("#trainprice")[0].innerText =trainprice
		$("#insuranceprice")[0].innerText = "保险费用："+ insuranceprice +"元"
		return flag;
	}
	function resumemoney(){
		var flag;
		var seats = ${trainSeats };
		var obj = document.getElementById("seatname");
		var txt = obj.options[obj.selectedIndex].text;
// 		alert(txt);
		for (var i = 0; i < seats.length; i++){
			var seat = seats[i];
			var tic = seat.remainderTrainTickets?seat.remainderTrainTickets:0;
			if(txt.trim() == seat.seatName){
				if(tic == 0){
					window.wxc.xcConfirm("【"+txt+"】无剩余票，请选择其他座位类型", window.wxc.xcConfirm.typeEnum.confirm);
					flag = false;
					return false;
				}else{
					flag= true;
				}
			}
		}
		var totalprice =0;
		var trainprice = 0;
		var insuranceprice = 0;
		$(".seatp").each(function(){
			trainprice += Number($(this).val());
			insuranceprice += isNaN(Number($("#insuranceName").find("option:selected").data("price")))?0:Number($("#insuranceName").find("option:selected").data("price")) ;
		});
		totalprice = trainprice + insuranceprice;
		$("#totalprice")[0].innerText =totalprice
		$("#trainprice")[0].innerText =trainprice
		$("#insuranceprice")[0].innerText = "保险费用："+ insuranceprice +"元"
		return flag;
	}
	//删除时候的金额变动;所有乘客的金额加起来就是总金额
	function resumemoney3(tm , zm , jg){
// 		alert(zm - tm);
		//得到删除的车票的价格
		//得到乘客的数量，看剩下多少个tr
		var cd = $(".identifyNumber").parent().parent();//找到有多少个<tr>
		//车票金额
		var tmoney = tm - jg;
		//保险费用
		var bx = 0;
		if(zm -tm == 0){
			bx = zm -tm;
		}else{
			bx = zm - tm - ((zm - tm)/(cd.length + 1));
		}
		//总金额
		var zmoney = tmoney + bx;
// 		alert("车票："+tmoney+"保险："+bx+"总金额："+zmoney);
		$("#totalprice")[0].innerText =zmoney
		$("#trainprice")[0].innerText =tmoney
		$("#insuranceprice")[0].innerText = "保险费用："+ bx +"元"
	}
	
	function a(){
		
		$(".trainpassengers").each(function(){
			
			var passenger =$(this);
			alert( passenger.children("td:eq(0)").text());
			alert( passenger.children("td:eq(1)").text());
			alert( passenger.children("td:eq(2)").text());
			
			
		});
		
		
	};
	
	</script>

</body>
</html>