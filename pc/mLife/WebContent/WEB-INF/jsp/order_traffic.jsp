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
<!-- 分页样式 -->
<link href="${ctx}/css/page.css" rel="stylesheet" />

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
	<div id="main" class="pure-g" style="backgroundcolor:#FFFFFF">
		<div class="pure-u-1-8">
			<%@ include file="menu_order.jsp"%>
		</div>
		<div id="maincontent" class="pure-u-5-6 mainbackground page-wrapper">
		
			<div class="search-form">
        
            <div class="trigger-content">
               <a name="queryBtn" id="btn_myOrder_search" class="btn btn-primary" href="javascript:void(0);" onclick="searchbill()">查询</a>
               <a class="toggle-link " id="detailquery" style="cursor: pointer;">高级选项</a>
            </div>
            
             <ul>
                 <li >
                    <div class="input-prepend">
                        <select id="goodsType" name="goodsType" onchange="searchbill()">
                            <option value=''>票务类型</option>
                            <option value='Coach'>汽车票</option>
                            <option value='Train'>火车票</option>
                            <option value='Air'>飞机票</option>
                        </select>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on">起始</span><span class="placeholder"><input id ="starttime"  class="form-control" type="text" onclick="WdatePicker()" placeholder="初始日期" onchange="checkDate1()"/></span>
                    </div>
                    -
                    <div class="input-prepend">
                        <span class="add-on">结束</span><span class="placeholder"><input id ="endtime"  class="form-control" type="text" onclick="WdatePicker()" placeholder="截止日期" onchange="checkDate2()"/></span>
                    </div>
                </li>
                <li style="">
                    <div class="btn-group">
                        <a class="btn" href="#" id="date_today">今天</a>
                        <a class="btn" href="#" id="date_yesterday">昨天</a>
                        <a class="btn" href="#" id="date_curMonth">本月</a>
                    </div>
                </li>
            </ul>
            <ul id="detailqueryitem" style="display:none">
                <li>
                    <div class="input-prepend">
                        <span class="add-on">订单编号</span><span class="placeholder"><input type="text" placeholder="订单编号" class="placeholderB input-text " id="orderNumber" name="orderNumber" onchange="searchbill()"></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on">出发城市</span><span class="placeholder"><input type="text" id="startcity" placeholder="出发城市" class="placeholderB input-text " name="startcity" onchange="searchbill()"></span>
                    </div>
                </li>
                                <li>
                    <div class="input-prepend">
                        <span class="add-on">到达城市</span><span class="placeholder"><input type="text" id="endcity" placeholder="到达城市" class="placeholderB input-text " name="endcity" onchange="searchbill()"></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <select id="orderStatus" name="orderStatus" onchange="searchbill()">
                            <option value="">请选择订单状态</option>
                            <option value="1">成功</option>
                            <option value="2">充值中</option>
                            <option value="3">已撤销</option>
                            <option value="4">未付款</option>
                        </select>
                    </div>
                </li>
            </ul>
        </div>




<input type="hidden"  id="id" name="itemprice" value=""><input type="hidden" id="billtypeA">
<table class="table table-bordered table-striped centered dataTable" id="dataTable" aria-describedby="dataTable_info">
	<thead>
		<tr >
			<th>订单编号</th>
			<th>订单类型</th>
			<th>创建时间</th>
			<th>出发城市</th>
			<th>到达城市</th>
			<th>订单状态</th>
			<th>订单金额</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody  id="bills">
		<tr class="odd">
			<td valign="top" colspan="10" class="dataTables_empty">请选择条件后，按查询按钮开始查询</td>
		</tr>
	</tbody>
</table>

	<!-- 分页 -->
	<div class="pagination" style="margin-bottom: 10px;">
	   
	 </div>


			

		</div>
	</div>
	<!-- 弹出层 -->
	<div id="win-wrapper" style="overflow-y :auto;overflow :yes;">
		<div id="win" style="margin-top: 10px;">
			<div class="win-title"><span>订单详情</span><span style="float:right; cursor:pointer; padding:0 10px;" class="win-close">×</span></div>
			<div class="win-body">
				<p><span class="order-title">订单名称：</span><span class="outerName">......</span><span style="float:right; cursor:pointer; padding:0 10px;color: blue;" class="win-sc">删除</span>
				<span style="float:right; cursor:pointer; padding:0 10px;color: blue;" class="win-tk">退款</span></p>
				<p><span class="myTradeNo" style="display: none;"></span></p>
				<p><span class="order-title">订单金额：</span><span class="outerPrice">......</span></p>
				<p><span class="order-title">其他金额：</span><span class="otherrPrice">......</span></p>
				<p><span class="order-title">支付金额：</span><span class="paycash">......</span></p>
				<p><span class="order-title">订单状态：</span><span class="outerstate">.......</span></p>
 				<p><span class="order-title">出发时间：</span><span class="startTimeA">.......</span></p>
				<p><span class="order-title">联系电话：</span><span class="mobile">......</span></p>
			</div>
			<div class="win-body" id="one" style="display: none">
				<div class="win-title"><span>乘客1信息</span></div>
				<p><span  class="order-title">订单编号：</span><span class="outerid">......</span></p>
				<p><span class="order-title">订单状态：</span><span class="seatname1">......</span></p>
				<p><span class="order-title">支付金额：</span><span class="paycash1">......</span></p>
				<p><span class="order-title">乘车人：</span><span class="userName">......</span></p>
				<p><span class="order-title">证件号码：</span><span class="idcardno">......</span></p>
			</div>
			<div class="win-body" id="two" style="display: none">
				<div class="win-title"><span>乘客2信息</span></div>
				<p><span  class="order-title">订单编号：</span><span class="outerid2">......</span></p>
				<p><span class="order-title">订单状态：</span><span class="seatname2">......</span></p>
				<p><span class="order-title">支付金额：</span><span class="paycash2">......</span></p>
				<p><span class="order-title">乘车人：</span><span class="userName2">......</span></p>
				<p><span class="order-title">证件号码：</span><span class="idcardno2">......</span></p>
			</div>
			<div class="win-body" id="three" style="display: none">
				<div class="win-title"><span>乘客3信息</span></div>
				<p><span  class="order-title">订单编号：</span><span class="outerid3">......</span></p>
				<p><span class="order-title">订单状态：</span><span class="seatname3">......</span></p>
				<p><span class="order-title">支付金额：</span><span class="paycash3">......</span></p>
				<p><span class="order-title">乘车人：</span><span class="userName3">......</span></p>
				<p><span class="order-title">证件号码：</span><span class="idcardno3">......</span></p>
			</div>
			<form action="order/continuePay" method="post" id="myPay">
				<input type="hidden" name="order_id" id="order_id">
				<input type="hidden" name="order_title" id="order_title">
				<input type="hidden" name="order_money" id="order_money">
			</form>
			<input id="myOuterid" type="hidden">
	  </div>
  </div>
<script>
$(document).ready(function(){
	searchbill();
});
/* 日期功能 */
var today = $("#date_today");
var yesterday = $("#date_yesterday");
var curMonth = $("#date_curMonth");
var startTime = $("#starttime");
var endTime = $("#endtime");


//得到当前的日期
function dateNow(){
	var d = new Date();
	function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1) + '-'+addzero(d.getDate());
	return s;
}
//得到昨天的日期
function dateYesterday(){
	var d = new Date();
	function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1) + '-'+addzero(d.getDate()-1);
	return s;
}
//得到每个月的第一天
function dateMonth(){
	var d = new Date();
	function addzero(v) {
		if (v < 10) return '0' + v;
		return v.toString();
	}
	var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1) + '-01';
	return s;
}
//点击今天的按钮触发的事件
today.click(function(){
	yesterday.css("color","black");curMonth.css("color","black");
	today.css("color","#F60");
	startTime.val(dateNow());
	endTime.val(dateNow());
	searchbill();
});
//点击昨天的按钮触发的事件
yesterday.click(function(){
	curMonth.css("color","black");today.css("color","black");
	yesterday.css("color","#F60");
	startTime.val(dateYesterday());	
	endTime.val(dateNow());
	searchbill();
});
//点击本月的按钮触发的事件 
curMonth.click(function(){
	today.css("color","black");yesterday.css("color","black");
	curMonth.css("color","#F60");
	startTime.val(dateMonth());	
	endTime.val(dateNow());
	searchbill();
});
//校验开始日期和结束日期
function checkDate1(){
	//1.先得到两个日期
	var date1 = $("#starttime").val();
	var date2 = $("#endtime").val();
	if((new Date(date1)).getTime() > (new Date(date2)).getTime()){
		var txt=  "开始时间不能大于结束时间";
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
		$("#starttime").val("");
		return false;
	}
}
//校验开始日期和结束日期
function checkDate2(){
	//1.先得到两个日期
	var date1 = $("#starttime").val();
	var date2 = $("#endtime").val();
	if((new Date(date1)).getTime() > (new Date(date2)).getTime()){
		var txt=  "结束时间不能小于开始时间";
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
		$("#endtime").val("");
		return false;
	}
}
function trim(str){ //删除左右两端的空格　　
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
$(".index21").addClass("check");
$("#starttime").val("");
$("#endtime").val("");

$("#detailquery").click(function(){
	if($("#detailqueryitem")[0].style.display=="none"){
		$("#detailqueryitem")[0].style.display="block"
	}else{
		$("#detailqueryitem")[0].style.display="none";
	}
});
//点击查询按钮，查询数据库的订单信息
function searchbill(obj){
	var pageobj=1;
	if(obj!=null){
		 pageobj=$(obj).attr("value");
	}
	
	var starttime =$("#starttime").val();//开始时间
	var endtime =$("#endtime").val();//结束时间
	var ordertype =$("#goodsType").val();//订单类型
	var orderno =$("#orderNumber").val();//订单编号
	var orderstate =$("#orderStatus").val();//订单状态
	//处理结束时间。查询的时候结束时间默认加一天
	if(endtime != null && undefined != endtime && "" != endtime){
		var enddate = new Date(new Date(endtime).getTime()+24*60*60*1000);
		function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
		endtime = enddate.getFullYear().toString() + '-'+addzero(enddate.getMonth() + 1) + '-'+addzero(enddate.getDate());
		//只选择了结束时间，没有选择开始时间
		if(starttime == null || undefined == starttime || "" == starttime){
			starttime = "1970-01-01";
		}
	}
	//只选择了开始时间，没有选择结束时间
	if(starttime != null && undefined != starttime && "" != starttime){
		if(endtime == null || undefined == endtime || "" == endtime){
			endtime = dateNow();
		}
	}
	if($("#username ").text().length == 0){
		window.wxc.xcConfirm("请先登录哦！",window.wxc.xcConfirm.typeEnum.confirm);
		return false;
	}
	//如果没有选择开始时间或者结束时间
	$.ajax({
		type : "GET",
		url : "order/getTrafficBills",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			goodsType : $("#goodsType").val(),
			starttime:starttime,
			endtime:endtime,
			orderNumber:$("#orderNumber ").val(),
			startCity:$("#startcity").val(),
			endCity:$("#endcity").val(),
			orderStatus:$("#orderStatus ").val(),
			userName:$("#username ").text(),
			page:pageobj//分页的页码
		},
		success : function(data) {
			var bills =eval(data);
			var list = bills.list;
			var billtxt ="";
			for(var i =0;i<list.length;i++){
				var bill =list[i];
				var title =bill.title;
				var citys= new Array(); //定义一数组 
				citys=title.split("-"); //字符分割 
				var startcity =citys[0];
				var endcity =citys[1];
				var date =citys[2];
				var state="";
				var id = bill.outerid;
				var city = bill.title;
				var c = new Array;
				c = city.split("-")
				var scity=c[0];
				var ecity=c[1];
				//处理订单状态
				switch(bill.state){
				case '0':
					state ="待付款";
					break;
				case '1':
					state ="充值中";
					break;
				case '2':
					state="成功";
					break;
				case '3':
					state="已撤销";
					break;
				case '4':
					state="充值失败";
					break;
				case '5':
					state="退款中";
					break;
				default :
					state="待处理";
				
				}
				//处理订单类型
				var type = "";
				switch(bill.type){
				case 'Coach':
					type = "汽车票";
					break;
				case 'Train':
					type = "火车票";
					break;
				case 'Air':
					type= "飞机票";
					break;
				}
				billtxt +="<tr class='odd'><th class='Touterid'>"
							    +id+
								" </th><th class='oId' style='display:none'>"
								+bill.tradeno+
								"</th><th class = 'bType'>"
								+type+
								"</th><th>"
								+bill.ctime+
								" </th><th>"
								+scity+
								" </th><th>"
								+ecity+
								" </th><th class='state5'>"
								+state+
								" </th><th>"
								+Number(bill.price).toFixed(2)+
								" </th><th>"
// 								+"<a href='order/getDetail?orderNumber="+id+"'>详情</a> <a href=' '>退款</a>"+
								+"<a href='javascript:;' class='showDetail'>详情</a>"+
								" </th></tr>"
			}
			
			$("#bills").html(billtxt);
			$(".pagination").html("");
			var pagination="<span>第 "+bills.page+"/"+bills.totalPage+"页</span>"
		    if(bills.page!=1){
		    	 var page=(parseInt(bills.page)-parseInt("1"));
			     pagination+="<a href='javascript:void(0);' value='1' class='firstPage' onclick='searchbill($(this))'>&nbsp;</a>"
			                +"<a href='javascript:void(0);' class='previousPage' value="+page+" onclick='searchbill($(this))'>&nbsp;</a>"
			}
			 for (var int = 1; int <= bills.totalPage; int++) {
				 if(bills.page!=int){
					 pagination+="<a href='javascript:void(0);' onclick='searchbill($(this))'  value="+int+">"+int+"</a>"
				 }else{
					 pagination+="<span class='currentPage'>"+int+"</span>"
				 }
			  }
		
			if(bills.page!=bills.totalPage){
		    	 var page=(parseInt(bills.page)+parseInt("1"));
				 pagination+="<a class='nextPage' value="+page+" onclick='searchbill($(this))'   href='javascript:void(0);'>&nbsp;</a>"
				            +"<a class='lastPage'  value="+bills.totalPage+" onclick='searchbill($(this))'  href='javascript:void(0);'>&nbsp;</a>"
			}
			pagination+="<br>";
			$(".pagination").append(pagination);
		}
	});
}
var flag=0;
/* 详情 */
$(document).on('click','.showDetail',function(){
	$(".win-tk")[0].style.display = "block";
	var ida = $(this).parent().parent().find('.oId').text();//得到表格前面的订单编号
	var outerid = $(this).parent().parent().find('.Touterid').text();//得到表格前面的订单编号
	var billType = $(this).parent().parent().find('.bType').text();//得到表格前面订单类型
	var state = $(this).parent().parent().find('.state5').text();//得到表格前面订单类型
	$("#billtypeA").val(billType);
	$("#myOuterid").val(outerid);
	//如果是汽车票，那么就不显示退款的标签
	//得到弹出框里面的所有的值
	var ctime = $("#win .ctime"),
		outerName = $("#win .outerName"),
		outerPrice = $("#win .outerPrice"),
		otherPrice = $("#win .otherrPrice"),
		paycash = $("#win .paycash"),
		outerType = $("#win .outerType"),
		etime = $("#win .etime"),
		outerstate = $("#win .outerstate"),
		mobile = $("#win .mobile"),
		startTimeA = $("#win .startTimeA");
	//单独的信息
	var idcardno1 = $("#win .idcardno"),
		userName1 = $("#win .userName"),
		outerid1 = $("#win .outerid"),
		paycash1 = $("#win .paycash1"),
		seatname1 = $("#win .seatname1"),
		idcardno2 = $("#win .idcardno2"),
		userName2 = $("#win .userName2"),
		outerid2 = $("#win .outerid2"),
		paycash2 = $("#win .paycash2"),
		seatname2 = $("#win .seatname2"),
		idcardno3 = $("#win .idcardno3"),
		userName3 = $("#win .userName3"),
		outerid3 = $("#win .outerid3"),
		paycash3 = $("#win .paycash3"),
		seatname3 = $("#win .seatname3"),
		myTradeNo = $("#win .myTradeNo");
	$.ajax({
		type:"GET",
		url:"order/getDetails",
		dataType:"json",
		contentType : "application/json; charset=utf-8",
		data:{
			orderNumber : ida
		},
		success : function(data){
			if(data.length == 1){
				$("#one")[0].style.display = "block";
				$("#two")[0].style.display = "none";
				$("#three")[0].style.display = "none";
			}else if(data.length == 2){
				$("#one")[0].style.display = "block";
				$("#two")[0].style.display = "block";
				$("#three")[0].style.display = "none";
			}else if(data.length == 3){
				$("#one")[0].style.display = "block";
				$("#two")[0].style.display = "block";
				$("#three")[0].style.display = "block";
			}
			var paycash4="";
			var ordertype="";
			//显示窗口，并且给窗口赋值
			var a = new Array(),
				b = new Array(),
				c = new Array(),
				d = new Array();
			a = data[0].passengername.split(",");
			b = data[0].orderno.split(",");
			c = data[0].idcardno.split(",");
			d = data[0].paycash.split(",");
			f = data[0].seatname.split(",");
			for(var i=0;i<d.length-1;i++){
				if(d[i] == ""){
					paycash4 = 0;
				}else{
					paycash4 = Number(paycash4) +Number(d[i]);
				}
			}
			idcardno1.text(c[0]);//证件号码
			userName1.text(a[0]);//乘车人
			paycash1.text(Number(d[0]).toFixed(2)+"元");
			outerid1.text(b[0]);//订单号
			idcardno2.text(c[1]);//证件号码
			userName2.text(a[1]);//乘车人
			paycash2.text(Number(d[1]).toFixed(2)+"元");
			outerid2.text(b[1]);//订单号
			idcardno3.text(c[2]);//证件号码
			userName3.text(a[2]);//乘车人
			paycash3.text(Number(d[2]).toFixed(2)+"元");
			outerid3.text(b[2]);//订单号
			if(flag != 0){
				$("#one").remove(0);
				$("#two").remove(0);
				flag = 0;
			}
			//复制div，里面的值也被赋值了
// 			for(var j=0;j<a.length-1;j++){
// 				$("#win").append($("#one").clone());
// 				$("#win").append($("#two").clone());
//  				var Odiv=document.createElement("div"); 
//  				$("#win-body")[0].style.background-color=="red";
// 				var bodydiv = document.getElementById("win");
// 				bodydiv.appendChild(Odiv);
// 				var Ospan=document.createElement("span");//创建一个span
//  				Odiv.class = "win-body";
// 				Ospan.class = j;
// 				Odiv.appendChild(Ospan);//在div内创建一个span
// 				flag = 1;
// 			}
			//给原始的div框赋值
// 			for(var i=0;i<a.length-1;i++){
// 				idcardno.text(c[i]);//证件号码
// 				userName.text(a[i]);//乘车人
// 				outerid.text(b[i]);//订单号
// 			}
			myTradeNo.text(data[0].tradno);
			outerstate.text(state);
			outerName.text(data[0].title);//标题
			mobile.text(data[0].passengertel);//联系电话
			startTimeA.text(data[0].starttime);//出发时间
			outerPrice.text(Number(data[0].totalfaceprice).toFixed(2)+"元");//订单金额
			otherPrice.text(Number(data[0].totalotherfee).toFixed(2)+"元");//其他金额
			paycash.text(Number(paycash4).toFixed(2)+"元");//支付金额
			seatname1.text(f[0]);
			seatname2.text(f[1]);
			seatname3.text(f[2]);
			$("#order_id").val(trim(outerid));
			$("#order_title").val(data[0].title);
			$("#order_money").val(Number(paycash4));
// 			$("#trainno1").html(data[0].tradno);
// 			$("#starttime1").html(data[0].starttime);
// 			$("#endtime1").html(data[0].recevietime);
// 			$("#startcity1").html(data[0].startstation);
// 			$("#receviecity1").html(data[0].receviestation);
// 			$("#mobile1").html(data[0].passengertel);
// 			$("#idtype1").html(data[0].idcardtype);
			if(trim($(".outerstate").text()) == "待付款" || trim($(".outerstate").text()) == "充值失败"){
				$(".win-tk").text("继续付款");
			}else{
			 	if(billType == "汽车票"){
		 			$(".win-tk")[0].style.display = "none";
		 		}
			}
			$("#win-wrapper").fadeIn();//显示弹出框
		}
	});
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
//点击退款
$(".win-tk").click(function(){
	//退款需要传值为 ： 主订单的单号和从订单的单号集合
	//实现退款的功能 【火车票出发前4小时内不能在线退款，需要自己去火车站退票】
	if($(".win-tk").text() == "退款"){
		$(".win-tk").text("退款中...");
		var tradeNo = $(".myTradeNo").text();
		var orderId1 = $(".outerid").text();
		var orderId2 = $(".outerid2").text();
		var orderId3 = $(".outerid3").text();
		var status = trim($(".outerstate").text());//订单状态
		var firsttime = $(".startTimeA").text();//出发时间
		var tkz = '退款中',
			ytk = '已退款';
		if(status == tkz || status == ytk || status=="待付款"){
			
			var txt = "此订单"+status;
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			$(".win-tk").text("退款");
			return false;
		}
		var orderId;
		if(orderId2.length==0){
			orderId2 = "";
		}
		if(orderId3 == "......" || orderId3.length==0){
			orderId3 = "";
		}
		orderId = orderId1+","+orderId2+","+orderId3;
		var orderTypeA = $("#billtypeA").val();
		//判断退票的是火车票还是飞机票
		if(orderTypeA == "火车票"){
			ordertypeA = "0";
			//得到火车出发的时间，然后和当前时间作比较，如果是4小时内，那么就不允许在线退票
			var ndate = (new Date()).getTime();//当前时间的毫秒数
			var cdate = new Date($(".startTimeA").html()).getTime();
			var timekey = 4 * 60 * 60 *1000;
			if(cdate - ndate < timekey){
				window.wxc.xcConfirm("出发前4小时内不支持在线退票", window.wxc.xcConfirm.typeEnum.confirm);
				return;
			}
		}else{
			orderTypeA = "1";
		}
		//判断是退票还是退废票
		var fp;
		var l = (new Date()).getTime();//当前时间的毫秒数
	/* <<<<<<< .mine
		var m = (new Date(firsttime)).getTime();//起飞时间
		alert("当前时间："+l);
		alert("起飞时间："+m);
		if((m + 4*60*60*1000) < l){//起飞后4小时以后
	======= */
		var date = firsttime;
		date = date.substring(0,19);    
		date = date.replace(/-/g,'/');
		var m = (new Date(date)).getTime();//起飞时间
		if( (parseInt(m)-4*60*60*1000) > parseInt(l)){//起飞后4小时以前
			//退废票
			fp = "1";
		}else if(parseInt(l) < parseInt(m)){//还未起飞
			//退票
			fp = "3";
		}else if(parseInt(l) > parseInt(m) && parseInt(l) < parseInt(m)+4*60*60*1000){//起飞后4小时以内
			fp = "还未到退废票的时间";
			window.wxc.xcConfirm(fp, window.wxc.xcConfirm.typeEnum.confirm);
			$(".win-tk").text("退款");
			return false;
		}
		$.ajax({
			type : "GET",
			url : "order/payBack",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {
				tradeNo : tradeNo,
				orderId : orderId,
				returnType :fp,//退订的是正常的票还是废票
				type :orderTypeA//飞机票还是火车票
			},
			success : function(data){
				$(".win-tk").text("退款");
				if(data == 1){
					$("#win-wrapper").fadeOut();//关闭窗口，刷新页面
					searchbill();
				}
			},
			//返回的错误信息
			error : function(XMLHttpRequest, textStatus, errorThrown){
				var txt = XMLHttpRequest.responseText;
				window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
				$(".win-tk").text("退款");
			}
		});
	}else if($(".win-tk").text() == "继续付款"){
		//必须先判断一下，当前时间是否已经超过出发时间
		//1.得到出发时间的毫秒数
		var stime = new Date($(".startTimeA").html()).getTime();
		var ntime = new Date().getTime();
		if(ntime - stime >= 0){
			window.wxc.xcConfirm("继续付款失败，已超过出发时间", window.wxc.xcConfirm.typeEnum.confirm);
			return;
		}
		//实现继续付款的功能
		window.wxc.xcConfirm("请确认订单信息", window.wxc.xcConfirm.typeEnum.confirm);
		$(".win-tk").html("确认付款");
		$(".win-sc").html("取消");
	}else if($(".win-tk").text() == "确认付款"){
		$("#myPay").submit();
	}
});
//删除票务订单
$(".win-sc").click(function(){
	//删除票务订单，先要删除trafficOrder里面的数据，然后删除trade里面的数据
	//传值只需要传一个订单编号
	if($(".win-sc").text() == "删除"){
		$.ajax({
			type : "GET",
			url : "order/deleteTrafficOrder",
			dataType : "text",
			contentType : "application/json;charset=utf-8",
			data : {
				orderId : $("#myOuterid").val()
			},
			success : function(data){
				if(data == "success"){
					$("#win-wrapper").fadeOut();//关闭窗口，刷新页面
					window.wxc.xcConfirm("删除成功", window.wxc.xcConfirm.typeEnum.confirm);
					searchbill();
				}else{
					window.wxc.xcConfirm(data, window.wxc.xcConfirm.typeEnum.confirm);
				}
			}
		});
	}else{
		$("#win-wrapper").fadeOut();//关闭窗口，刷新页面
	}
});
</script>

</body>
</html>