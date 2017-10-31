<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<script src="${ctx}/js/jquery-1.11.2.min.js"></script>
<style type="text/css">
.font-large {
    font-size: 30px;
    font-weight: bold;
    line-height: 45px;
}
.orange {
    color: #f60;
}

td {
    padding: 4px 10px;
}
tr{

height: 45px;
}
</style>
</head>
<body>
	<div class="">
		<h3>手机流量订单</h3>
	</div>
	<br>
	<div class="pd-20">
		<table class="form form-large form-orderConfirm">
			<tbody>
				<tr>
					<td class="label-td v-middle"><label for="">充值号码:</label></td>
					<td><span class="font-large orange">${orderInfo.rechargeAccount}</span></td>
				</tr>
				<tr>
					<td class="label-td "><label for="">应收金额:</label></td>
					<td>${orderInfo.saleAmount}元</td>
				</tr>
				<tr>
					<td class="label-td"><label for="">商品名称:</label></td>
					<td>${orderInfo.itemName}</td>
				</tr>
				<tr>
					<td class="label-td"><label for="">订单编号:</label></td>
					<td>${orderInfo.billId}</td>
				</tr>
				<tr id="amountVerify" style="display: none">
					<td class="label-td">
					<td>
				</tr>
				<tr id="amountVerifyTipsTr" style="display: none">
				<tr>
					<td class="label-td">
					<td></td>
				</tr>
				<tr>
					<td class="label-td"><label for=""> </label></td>
					<td>
						<button id="confirmBill" class="btn btn-large btn-warning">确认购买</button>
						<a class="linkbtn linkbtnLarge" data="54089" href="javascript:history.go(-1);"
						name="continuBy">重新下单</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- 控制菜单导航栏高度 -->
	<script type="text/javascript">
		$(function() {
			var meheight = $(".content-wrap").height();
			$("#menu").css("height", meheight);
		})
	</script>
</body>
</html>