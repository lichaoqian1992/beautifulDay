<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>满集生活缴费平台</title>

<script type="text/javascript">
/* 取消飞机订单 */
function airOrderCancel() {
	 $.ajax({
         url: "${ctx}/air/airOrderCancel",
         type: "get",
         data: 'tradeNo='+$("#tradeNo").val(),
         success: function (data) {
        	 
        	 if(data==0){
        		var txt = "订单取消失败";
				window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
				
        	 }else{
        		var txt = "订单取消成功";
 				window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
        	 }
        	 
        	 setTimeout("airMian()",1000);// 设置一个超时对象
         }
})
}

function airMian() {
	
	var url="${ctx}/toAir";
	open(url,'_top');
}
</script>

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
				<ul class="step-nav horizontal" style="margin-top: -6px;">
					<li class="item"><i class="step-num">1</i> <span class="char">提交订单</span></li>
					<li class="item active"><i class="step-num">2</i> <span
						class="char">确认并支付</span></li>
					<li class="item"><i class="step-num">3</i> <span class="char">成功</span></li>
				</ul>
				<h2>确认订单信息</h2>
				<form novalidate="novalidate" id="confirmForm"  action=""  method="post">
					<input name="CSRFToken" value="1471514670479" type="hidden">
					<div class="well well-light">
						<div class="list list-vertical">
							<div class="item item-input">
								<label for="" class="input-label">机票信息：</label>
								<div class="input-content">
									<div class="ticket-brand-infos-content well well-sm horizontal">
										<h4 class="carriage-info item">
										${flightCompanyName}<span class="text-info">${flightNo}</span>
										</h4>
										<p class="path-infos item">
											起飞：<strong>${airInfo.startTime}</strong>  <br>到达：<strong>${airInfo.recevieTime}</strong>
	
										</p>
										<p class="price-infos item">出发站:&nbsp;<strong>${airInfo.startStation}</strong>&nbsp;&nbsp;到达站:&nbsp;<strong>${airInfo.recevieStation}</strong>
										&nbsp;&nbsp;舱位:&nbsp;<strong>${seatMsg}</strong>	<br/> 订单主编号:&nbsp;<strong>${airInfo.tradeNo}</strong>&nbsp;&nbsp;&nbsp;&nbsp;订单创建时间:&nbsp;<strong>${airInfo.ctime}</strong></p>
									</div>
								</div>
							</div>
							<div class="item item-input">
								<label for="" class="input-label">乘客信息：</label>
								<div class="input-content">
									<table
										class="table table-striped table-bordered table-centered">
										<thead>
											<tr>
												<th>类型</th>
												<th>姓名</th>
												<th>证件号码</th>
												<th>票号</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${airInfo.ticketOrders}" var="info">
											<tr>
												<td>成人票</td>
												<td>${info.passengerName}</td>
												<td>${info.idcardNo}</td>
												<td>${info.ticketNo}</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="item item-input">
								<label for="" class="input-label">订单状态：</label>
								<div class="input-content">
									<p class="char"><strong> ${airInfo.stateName}</strong></p>
								</div>
								<div class="font-large orange" style="font-size: 14px;"></div>
							</div>
							<div class="item item-input">
								<label for="" class="input-label">保险：</label>
								<div class="input-content">
									<p class="char"><strong>暂无保险服务</strong></p>
								</div>
								<div class="font-large orange" style="font-size: 14px;"></div>
							</div>
							
							<div class="item item-input">
								<label for="" class="input-label">联系人信息：</label>
								<div class="input-content">
									<p class="char">${airInfo.contactName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										${airInfo.contactTel} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
								</div>
								<div class="font-large orange" style="font-size: 14px;"></div>
							</div>
							
							<div class="item item-input">
								<label for="" class="input-label">价格：</label>
								<div class="input-content">
										<p class="price" style="margin-top: 0px;"><strong>${airInfo.totalPayCash}元</strong> </p>
								</div>
								<div class="font-large orange" style="font-size: 14px;"></div>
							</div>
							<div class="alert alert-info">
							<h4>服务说明：</h4>
							<ul>
								<li>1.支付需在预定成功后半小时内完成，否则自动取消预定。</li>
							</ul>
					         </div>
							
						</div>
						
						<div class="form-btn-content" id="confirmPay">
							<input id="tids"value=""
								type="hidden"> 
								<a id="confirmBill" href="${ctx}/air/airOrderPay;" class="btn btn-primary btn-lg">确认并支付</a>
								<input id="tradeNo" type="hidden"   value="${airInfo.tradeNo}">
								<a id="cancelTrade" href="javascript:void(0)" onclick="airOrderCancel();"
								class="btn btn-primary btn-lg">取消订单</a>
						</div>

						<div id="showBook" style="display: none; margin-left: 20px;">
							<div id="bookingMessage">订单预定中,请稍等。。。</div>
							<div id="progress" style="float: left;"></div>
						</div>

						<div id="ployInfo" style="display: none" class="noploy amount "
							eachamount="300" dailyamount="2,000" name="NONE"></div>
					
					</div>
				</form>
			</div>
		</div>
	</div>
		<div   class="sgBtn"style="display: none;">弹窗3(警告)</div>
	
</body>
</html>