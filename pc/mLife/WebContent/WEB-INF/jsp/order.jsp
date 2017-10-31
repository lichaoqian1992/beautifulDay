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
               <a name="queryBtn" id="btn_myOrder_search" onclick ="queryBills()" class="btn btn-primary" href="javascript:void(0);">查询</a>
               <a class="toggle-link " id="detailquery" style="cursor: pointer;">高级选项</a>
            </div>
            
             <ul>
                 <li >
                    <div class="input-prepend">
                        <select id="goodsType" name="goodsType" onchange="queryBills()">
                            <option value="">订单类型</option>
                            <option value="Mobile">话费充值</option>
                            <option value="MobileFlow">流量充值</option>
                            <option value="WaterCoal">水电气缴费</option>
                            <option value="GK">固话宽带</option>
                            <option value="TV">有限电视</option>
                            <option value="Game">游戏充值</option>
                            <option value="Card">卡密</option>
                            <option value="Fine">交通罚款</option>
                            <option value="GasCard">加油卡充值</option>
                        </select>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on">起始</span><span class="placeholder"><input id ="starttime"  class="form-control" type="text" onclick="WdatePicker()" 

placeholder="初始日期" onchange="checkDate1()"/></span>
                    </div>
                    -
                    <div class="input-prepend">
                        <span class="add-on">结束</span><span class="placeholder"><input id ="endtime"  class="form-control" type="text" onclick="WdatePicker()" 

placeholder="截止日期" onchange="checkDate2()"/></span>
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
                        <span class="add-on">订单编号</span><span class="placeholder"><input type="text" placeholder="订单编号" class="placeholderB input-text " 

id="orderNumber" name="orderNumber" onkeyup="queryBills()"></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on">充值帐号</span><span class="placeholder"><input type="text" id="rechargeAccount" placeholder="充值帐号" 

class="placeholderB input-text " name="rechargeAccount" onkeyup="queryBills()"></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <select id="orderStatus" name="orderStatus" onchange="queryBills()">
                            <option value="">请选择订单状态</option>
                            <option value="0">待付款</option>
                            <option value="1">充值中</option>
                            <option value="2">成功</option>
                            <option value="3">已撤销</option>
                        </select>
                    </div>
                </li>
            </ul>
        </div>
		<table class="table table-bordered table-striped centered dataTable" id="dataTable" aria-describedby="dataTable_info">
			<thead>
				<tr >
					<th>时间</th>
					<th>订单编号</th>
					<th>商品名称</th>
					<th>充值账号</th>
					<th>状态</th>
					<th>付款金额</th>
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
	<!-- 弹出层     订单详情-->
	<div id="win-wrapper">
		<div id="win">
			<div class="win-title"><span>订单详情</span><span style="float:right; cursor:pointer; padding:0 10px;" class="win-close">×</span></div>
			<div class="win-body">
				<p><span class="order-title">订单名称：</span><span class="outerName">......</span><span style="float:right;cursor:pointer; padding:0 10px;color: blue;" class="win-sc">删除</span>
				<span style="float:right; cursor:pointer; padding:0 10px;color: blue;" class="win-fk">继续付款</span></p>
				<p><span  class="order-title">订单号：</span><span class="outerid">......</span></p>
				<p><span  class="order-title">付款编号：</span><span class="mjorder">......</span></p>
				<p><span class="order-title">下单时间：</span><span class="ctime">......</span></p>
				<p><span class="order-title">完成时间：</span><span class="etime">......</span></p>
				<p><span class="order-title">订单金额：</span><span class="outerPrice">......</span></p>
				<p><span class="order-title">订单状态：</span><span class="outerstate">.......</span></p>
				<p><span class="order-title">订单类型：</span><span class="outerType">.......</span></p>
				<p><span class="order-title">下单人：</span><span class="userName">......</span></p>
			</div>
		</div>
	</div>
	<!-- 订单继续付款 弹出框 -->
	 <div id="win-wrapper2">
		<div id="win2">
			<div class="win-title"><span>订单详情</span><span style="float:right; cursor:pointer; padding:0 10px;" class="win-close2">×</span></div>
			<div class="win-body">
				<p><span class="order-title">订单名称：</span><span class="outerName2">......</span><span style="float:right;cursor:pointer; padding:0 10px;color: blue;" class="win-sc2">取消</span>
				<span style="float:right; cursor:pointer; padding:0 10px;color: blue;" class="win-fk2">确认付款</span></p>
				<p><span  class="order-title">订单号：</span><span class="outerid2">......</span></p>
				<p><span class="order-title">下单时间：</span><span class="ctime2">......</span></p>
				<p><span class="order-title">充值账号：</span><span class="account2">......</span></p>
				<p><span class="order-title">订单金额：</span><span class="outerPrice2">......</span></p>
				<p><span class="order-title">订单状态：</span><span class="outerstate2">.......</span></p>
				<p><span class="order-title">订单类型：</span><span class="outerType2">.......</span></p>
				<p><span class="order-title">下单人：</span><span class="userName2">......</span></p>
				<form action="order/continuePay" method="post" id="myPay">
					<input type="hidden" name="order_id" id="order_id">
					<input type="hidden" name="order_title" id="order_title">
					<input type="hidden" name="order_money" id="order_money">
				</form>
				<input type="hidden" id="account">
			</div>
		</div>
	</div>
	<!-- 提示框 -->
	<div   class="sgBtn" style="display: none;">弹窗3(警告)</div>
	
<script>
	/* 日期功能 */
	var today = $("#date_today");
	var yesterday = $("#date_yesterday");
	var curMonth = $("#date_curMonth");
	var startTime = $("#starttime");
	var endTime = $("#endtime");
	
	function dateNow(){
		var d = new Date();
		function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
		var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1) + '-'+addzero(d.getDate());
		return s;
	}
	function dateYesterday(){
		var d = new Date();
		function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
		var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1) + '-'+addzero(d.getDate()-1);
		return s;
	}
	function dateMonth(){
		var d = new Date();
		function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
		var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1) + '-01';
		return s;
	}
	
	today.click(function(){
		yesterday.css("color","black");curMonth.css("color","black");
		today.css("color","#F60");
		startTime.val(dateNow());
		endTime.val(dateNow());
		queryBills();
	});
	yesterday.click(function(){
		curMonth.css("color","black");today.css("color","black");
		yesterday.css("color","#F60");
		startTime.val(dateYesterday());	
		endTime.val(dateNow());
		queryBills();
	});
	curMonth.click(function(){
		//取消其他的颜色
		today.css("color","black");yesterday.css("color","black");
		curMonth.css("color","#F60");
		startTime.val(dateMonth());	
		endTime.val(dateNow());
		queryBills();
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


queryBills();

$(".index20").addClass("check");
$("#starttime").val("");
$("#endtime").val("");

$("#detailquery").click(function(){
	if($("#detailqueryitem")[0].style.display=="none"){
		$("#detailqueryitem")[0].style.display="block"
	}else{
		$("#detailqueryitem")[0].style.display="none";
	}
});

//查询
	function queryBills(obj){
		var pageobj=1;
		if(obj!=null){
			 pageobj=$(obj).attr("value");
		}
		
		var starttime =$("#starttime").val();
		var endtime =$("#endtime").val();
		var ordertype =$("#goodsType").val();
		var orderno =$("#orderNumber").val();
		var rechargeaccount =$("#rechargeAccount").val();
		var orderstate =$("#orderStatus").val();
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
		$.ajax({
			type : "GET",
			url : "order/getChargeBills",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {
				goodsType : $("#goodsType").val(),//订单类型
				starttime:starttime,//开始时间
				endtime:endtime,//结束时间
				orderNumber:$("#orderNumber").val(),//订单号
				rechargeAccount:$("#rechargeAccount").val(),//充值账号
				orderStatus:$("#orderStatus").val(),//订单状态
				userName:$("#username").text(),
				page:pageobj
			},
			success : function(data) {
				if(data==0){
					var txt = "亲，请登录后查看哟！";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
					return false;
				}
				var itmes =eval(data);
				
				var bills=itmes.list
				
				var billtxt ="";
				for(var i =0;i<bills.length;i++){
					var bill =bills[i];
					var state="";
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
					default :
						state="待处理";
					
					}
					billtxt +="<tr class='odd'><th>"
								    +bill.ctime+
									" </th><th class='oId'>"
									+bill.outerid+
									" </th><th class ='bh' style='display:none'>"
									+bill.tradeno+
									" </th><th>"
									+bill.title+
									" </th><th class='mycount'>"
									+bill.name+
									"</th><th>"
									+state+
									" </th><th>"
									+Number(bill.paycash).toFixed(2)+
									" </th><th>"
									+"<a href='javascript:;' class='showDetail'>详情</a> "+
									" </th></tr>"
				}
				
				$("#bills").html(billtxt); 
				$(".pagination").html("");
				var pagination="<span>第 "+itmes.page+"/"+itmes.totalPage+"页</span>"
					
				    if(itmes.page!=1){
				    	 var page=(parseInt(itmes.page)-parseInt("1"));
					     pagination+="<a href='javascript:void(0);' value='1' class='firstPage' onclick='queryBills($(this))'>&nbsp;</a>"
					                +"<a href='javascript:void(0);' class='previousPage' value="+page+" onclick='queryBills($(this))'>&nbsp;</a>"
					}
					 for (var int = 1; int <= itmes.totalPage; int++) {
						 if(itmes.page!=int){
							 pagination+="<a href='javascript:void(0);' onclick='queryBills($(this))'  value="+int+">"+int

+"</a>"
						 }else{
							 pagination+="<span class='currentPage'>"+int+"</span>"
						 }
					  }
				
					if(itmes.page!=itmes.totalPage){
				    	 var page=(parseInt(itmes.page)+parseInt("1"));
						 pagination+="<a class='nextPage' value="+page+" onclick='queryBills($(this))'   href='javascript:void(0);'>&nbsp;</a>"
						            +"<a class='lastPage'  value="+itmes.totalPage+" onclick='queryBills($(this))'  href='javascript:void(0);'>&nbsp;</a>"
					}
					pagination+="<br>";
					$(".pagination").append(pagination);
			}
		});
	};
	function trim(str){ //删除左右两端的空格　　
	     return str.replace(/(^\s*)|(\s*$)/g, "");
 	};
/* 详情 */
	$(document).on('click','.showDetail',function(){
		var id = trim($(this).parent().parent().find('.oId').text());
		var mycount = trim($(this).parent().parent().find('.mycount').text());
		$("#account").val(mycount);
		var outerid = $("#win .outerid"),
			ctime = $("#win .ctime"),
			outerName = $("#win .outerName"),
			outerPrice = $("#win .outerPrice"),
			outerType = $("#win .outerType"),
			etime = $("#win .etime"),
			mjorder = $("#win .mjorder"),
			outerstate = $("#win .outerstate"),
			userName = $("#win .userName");
		$.ajax({
			type : "GET",
			url : "order/getChargeBills",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {
				orderNumber : id,
				userName:$("#username").text(),
				page:'0'
			},
			success : function(data) {
				outerid.text(data[0].outerid);
				ctime.text(data[0].ctime);
				outerName.text(data[0].title);
				outerPrice.text(Number(data[0].price).toFixed(2)+'元');
				userName.text(data[0].username);
				etime.text(data[0].etime);
				$("#order_id").val(data[0].outerid);
				$("#order_title").val(data[0].title);
				$("#order_money").val(Number(data[0].price).toFixed(2));
				mjorder.text(data[0].mjoder);
					var state="";
					var type="";
					switch(data[0].state){
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
						default :
							state="待处理";
					}
					switch(data[0].type){
					case 'Mobile':
						type ="话费充值";
						break;
					case 'MobileFlow':
						type ="流量充值";
						break;
					case 'WaterCoal':
						type="水电气缴费";
						break;
					case 'GK':
						type="固话宽带";
						break;
					case 'TV':
						type="有限电视";
						break;
					case 'Game':
						type = "游戏充值";
						break;
					case 'Card':
						type = "卡密";
						break;
					case 'Fine':
						type = "交通罚款";
						break;
					case 'GasCard':
						type = "加油卡充值";
						break;
					default :
						type="未知";
				}
					outerstate.text(state);
					outerType.text(type);
				//如果订单的状态时待付款，就可以继续付款，否则不显示
				if(data[0].state == 0 || data[0].state == 4){
					$(".win-fk")[0].style.display = "block";
				}else{
					$(".win-fk")[0].style.display = "none";
				}
				//目前只允许删除订单状态是：待付款、已撤销
				if(data[0].state == 0 || data[0].state == 3){
					$(".win-sc")[0].style.display = "block";
				}else{
					$(".win-sc")[0].style.display = "none";
				}
				$("#win-wrapper").fadeIn();
			}
		});
	});
	//点击继续付款
	$(".win-fk").click(function(){
		$("#win-wrapper2").fadeIn();
		$(".outerName2").html($(".outerName").html());
		$(".outerid2").html($(".outerid").html());
		$(".ctime2").html($(".ctime").html());
		$(".account2").html($("#account").val());
		$(".outerPrice2").html($(".outerPrice").html());
		$(".outerstate2").html($(".outerstate").html());
		$(".outerType2").html($(".outerType").html());
		$(".userName2").html($(".userName").html());
// 		$("#myPay").submit();
		
	});
	//确认付款
	$(".win-fk2").click(function(){
		$("#win-wrapper2").fadeIn();
		$("#myPay").submit();
		
	});
	$(".win-sc2").click(function(){
		$("#win-wrapper2").fadeOut();
		$("#win-wrapper").fadeOut();
	});
	//点击删除，删除订单（包括删除trade表中的和trafficOrder表里面对应的数据）
	$(".win-sc").click(function(){
		$.ajax({
			type : "GET",
			url : "order/deleteOrder",
			dataType : "text",
			contentType : "application/json; charset=utf-8",
			data : {
				outerId : $("#order_id").val()
			},success : function(data){
				if(data == "success"){
					$("#win-wrapper").fadeOut();
					window.wxc.xcConfirm("删除成功", window.wxc.xcConfirm.typeEnum.confirm);
					queryBills();
				}else{
					window.wxc.xcConfirm(data, window.wxc.xcConfirm.typeEnum.confirm);
				}
			}
		});
	});
	$(".win-close").click(function(){
		$("#win-wrapper").fadeOut();
	});
	$(".win-close2").click(function(){
		$("#win-wrapper2").fadeOut();
	});
	$("#win-wrapper").click(function(){
		$("#win-wrapper").fadeOut();
	});
	$("#win-wrapper2").click(function(){
		$("#win-wrapper2").fadeOut();
	});
	$("#win").click(function(){
		return false;
	})
	$("#win2").click(function(){
		return false;
	})

</script>

</body>
</html>