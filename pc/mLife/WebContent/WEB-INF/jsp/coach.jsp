<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>满集生活缴费平台</title>
<!-- 日期插件 -->
<script src="${ctx}/My97DatePicker/WdatePicker.js"></script>

<style type="text/css">
.divinput {
	width: 150px;
	height: 30px;
	position: relative;
}

.divinput .divinput_input {
	width: 100%;
	height: 100%;
	border: 1px solid #ececec;
}

.divinput .divinput_hide {
	height: auto;
	position: absolute;
	border: 1px solid #ececec;
	border-top: none;
	background-color: #ffffff;
	z-index: 99;
	margin-top: 0;
	width:156px;
}

.divinput .divinput_hide ul:first-child li {
	margin-top: 5px;
}

.divinput .divinput_hide ul li {
	margin-bottom: 5px;
	padding-left: 5px;
}
.divinput .divinput_hide ul li:hover{
 cursor:pointer;
 background-color:#ececec;
}
.hide {
	display: none;
}

.divinput .divinput_hide ul {

margin-left:-40px;

}

</style>

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
	<div id="main" class="pure-g" >
		<div class="pure-u-1-8">
			<%@ include file="menu.jsp"%>
		</div>
		<div id="maincontent" class="pure-u-5-6 mainbackground ">
		
			<div style="border-bottom: 1px solid #dcdcdc; margin-bottom: 10px;">
				<h2>汽车票预订</h2>
			</div>
			<div class="section">

				<div id="errorShow" style="display: none" class="alert alert-error">
					<strong id="errorKey"></strong> <span id="errorMsg"></span>
				</div>
				<div>
						  	 <c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose>	
				</div>
				<div class="well well-sm ticket-search-form">
					<div class="list list-horizontal list-horizontal-compact">
						<div class="item item-input">
							<label class="input-label" for="">出发城市：</label>
							<div class="input-content">
							<div class="divinput" style="width:150px;">
								<input type="text" autocomplete="off"
									class="form-control ui-autocomplete-input" id="startCity"
									name="startCity"><span role="status" aria-live="polite"
									class="ui-helper-hidden-accessible"></span>
									
									<div class="divinput_hide hide" id="divstartcity">
									<ul id="startcity">
									</ul>
									</div>
									</div>
							</div>
						</div>
						<div class="item item-input">
							<label class="input-label" for="">出发日期：</label>
							<div class="input-content">
								    <!-- <input type="text"
									onclick="WdatePicker({minDate:'%y-%M-{%d+1}'})"
									 class="form-control" id="startTime" name="startTime">
									  -->
									  
									 <input id ="starttime"  class="form-control" type="text" onclick="WdatePicker({minDate:'%y-%M-{%d}'})"/> 
							</div>
						</div>
					</div>
					<div class="list list-horizontal list-horizontal-compact">
						<div class="item item-input">
							<label class="input-label" for="">到达城市：</label>
							<div class="input-content">
							<div class="divinput" style="width:156px;">
								<input type="text" autocomplete="off"
									class="form-control ui-autocomplete-input" id="endCity"
									name="endCity"><span role="status" aria-live="polite"
									class="ui-helper-hidden-accessible"></span>
									<div class="divinput_hide hide" id="divendcity">
									<ul id="endcity">
									</ul>
									</div>
							</div>
							</div>
							</div>
						
						<div class="item item-input">
							<label class="input-label" for=""></label> <a id="btn_search"
								class="btn btn-wide btn-primary" href="javascript:void(0);" >查询</a>
						</div>

					</div>
				</div>

				<!-- 筛选 -->
				<div class="well well-sm">
					<div class="well well-sm">
						<div style="width: 120px" class="alert alert-info">
							提示：先查询，再筛选</div>
						<div class="list list-vertical list-vertical-compact">
							<div class="item item-input">
								<label class="input-label" for="">发车时间：</label>
								<div class="input-content horizontal">
										<a id="timeAll" class="text-label text-label-primary item selectAll"
											href="javascript:void(0);">全部</a>
										<!-- <label class="checkbox item" for=""><input type="radio" value="0-0" id="shangwu" name="gotime">全部</label>  -->
										<label class="checkbox item" for="shangwu"><input type="radio" value="360-720" id="shangwu" name="gotime">上午（06:00-12:00）</label> 
										<label class="checkbox item" for="xiawu"><input type="radio" value="720-1080" id="xiawu" name="gotime">下午（12:00-18:00）</label>
										<label class="checkbox item" for="wanshang"><input type="radio" value="1080-1440" id="wanshang" name="gotime">晚上（18:00-24:00）</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<table id="resultTable"
					class="table table-bordered table-striped table-centered">
					<thead>
						<tr>
							<th>发车时间</th>
							<th>发/到站</th>
							<th>车型/车次</th>
							<th>票价</th>
							<th>余票</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="coachLines" role="alert" aria-live="polite" aria-relevant="all">
					</tbody>
				</table>
				<div class="alert alert-info">
					<h5>购票须知</h5>
					1、汽车票相关业务处理时间为8:00-22:00；<br> 2、平台不支持退票或改签，请自行去汽车站办理；<br>
					3、目前购买汽车票无需实名制，乘客可以由亲友代为购票；<br> 4、只支持购买成人票，不支持儿童票、学生票；<br>
					5、每个订单最多可以购买3张车票；<br>
					6、由于订票时无法在线选座，因此取票信息中不包含座位号，您去车站取票时取票系统会自动生成座位号<br>
					7、若取票过程中出现问题，请在发车前半小时与平台客服联系处理，超过发车时间的订单不予处理。<br>
				</div>
				<script src="/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

			</div>
		</div>





	</div>
	<script>
	
	/* 全选按钮功能 */
	
		var classAll = $(".selectAll");
		
		classAll.click(function(){
			$(this).siblings('.checkbox').find("input").attr("checked",false);
			loadCoachLine();
		});
	
	
	
	$(".index10").addClass("check");
	
	var startstationdata ="";
	var endstationdata ="";
	var lineList ="";
	$(document).ready(
			function() {
				$.ajax({
					type : "GET",
					url : "coach/getStartStation",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : {},
					success : function(data) {
						var list = eval(data);
						startstationdata =list;
					}

				});
			});
	
	

	
	function query() {
		
		
		var queryData={
				"startCity" : $("#startstr").val(),
				"endCity" : $("#endstr").val(),
				"date" : $("#startTime").val()
		};
		
		$.ajax({
			type : "GET",
			url : "coach/getLines",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : queryData,
			success : function(data) {
				lineList = eval(data);
				var da = "";
				for (var i = 0; i < lineList.length; i++) {
					var coachline = lineList[i];
					var txt = "<tr><th>"
							+ coachline.dptDate
							+ "	"
							+ coachline.dptTime
							+ "</th><th>始:"
							+ coachline.dptStation
							+ "<br>终:"
							+ coachline.arrStation
							+ "</th><th>"
							+ coachline.coachType+"/"+coachline.coachNO
							+ "</th><th>"
							+ (parseFloat(coachline.ticketPrice) + parseFloat(coachline.fee)).toFixed(2)
							+ "</th><th>"
							+coachline.ticketLeft+
							"</th><th><form action='coach/orderTickets' method='POST'><input type='hidden' id='coachLineJson' name='coachLineJson' value='"
							+JSON.stringify(coachline)+
							"'><input type='submit' class='pure-button'  value='预订' ></form></th></tr>";
								
					da += txt;

				}
				//da+="</table>"
				$("#coachLines").html(da);
			}
			

		})

};
	
	
	
$("#startCity").keyup(function(){
	 var str =$("#startCity").val();
		$("#startcity li").remove();
		var count=0;
		$.each(startstationdata, function(i, obj) {
			
			var name = obj.name;
			if(str!=""&&(name.indexOf(str))!=-1&&count<10){
				count++;
				$("#startcity").append("<li value="+name+" onclick='startclick(this)'>"+name+"</li>");
			}
		});
		if($("#startcity li").length>0){
			$("#divstartcity").removeClass("hide");
		}else{
			$("#divstartcity").addClass("hide");
		}
})	;




function startclick(obj){
	 //alert($(obj).text());
	 $("#startCity").val($(obj).text())	;
	 $("#divstartcity").addClass("hide");
	 
	 $.ajax({
			type : "GET",
			url : "coach/getEndStation",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {
				"startCity" : $("#startCity").val()
			},
			success : function(data) {
				var list2 = eval(data);
				
				endstationdata =list2;
			}

		});
};





$("#endCity").keyup(function(){
	
	var str =$("#endCity").val();
	$("#endcity li").remove();
	var count=0;
	$.each(endstationdata, function(i, obj) {
		var name = obj.name;
		//var quanpin = obj.quanpin;
		if(str!=""&&(name.indexOf(str))!=-1&&count<10){
			count++;
			$("#endcity").append("<li value="+name+" onclick='endclick(this)'>"+name+"</li>");
			
		}
		
	});
	
	if($("#endcity li").length>0){
		
		$("#divendcity").removeClass("hide");
	}
	else
		{
		$("#divendcity").addClass("hide");
		}
});
function endclick(obj){
 //alert($(obj).text());
 $("#endCity").val($(obj).text())	;
 $("#divendcity").addClass("hide");
  
};

$("#btn_search").click(function(){
	var sc = $("#startCity").val();
	var ec = $("#endCity").val();
	var st = $("#starttime").val();
	if(sc == "" || sc == null || undefined == sc){
		fp = "请选择出发城市";
		window.wxc.xcConfirm(fp, window.wxc.xcConfirm.typeEnum.confirm);
		return false;
	}
	if(ec == "" || ec == null || undefined == ec){
		fp = "请选择到达城市";
		window.wxc.xcConfirm(fp, window.wxc.xcConfirm.typeEnum.confirm);
		return false;
	}
	if(st == "" || st == null || undefined == st){
		fp = "请选择出发时间";
		window.wxc.xcConfirm(fp, window.wxc.xcConfirm.typeEnum.confirm);
		return false;
	}
	var queryData={
			"startCity" : $("#startCity").val(),
			"endCity" : $("#endCity").val(),
			"date" : $("#starttime").val()
	};
	//点击查询以后给出一个我提示，表示系统正在运行，没有卡死
	$("#win-wrapper").fadeIn();
	$.ajax({
		type : "GET",
		url : "coach/getLines",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : queryData,
		success : function(data) {
				$("#win-wrapper").fadeOut();
				lineList = eval(data);
				if(lineList == null || "" == lineList || undefined == lineList){
					var txt = "此条件查询无记录";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
					$("#btn_search").text("查询");
					return false;
				}
				//var da = "<table>";
				var da = "";
				for (var i = 0; i < lineList.length; i++) {
					var coachline = lineList[i];
					var txt = "<tr><th>"
 							+ coachline.dptDate
							+ "	"
							+ coachline.dptTime.substring(0,coachline.dptTime.length - 3)
							+ "</th><th>始:"
							+ coachline.dptStation
							+ "<br>终:"
							+ coachline.arrStation
							+ "</th><th>"
							+ coachline.coachType+"/"+coachline.coachNO
							+ "</th><th>¥"
							+ (parseFloat(coachline.ticketPrice) + parseFloat(coachline.fee)).toFixed(2)
							+ "</th><th>"
							+coachline.ticketLeft
							+"</th><th><form action='coach/orderTickets' method='POST'><input type='hidden' id='coachLineJson' name='coachLineJson' value='"
							+JSON.stringify(coachline)+
							"'><input type='submit' class='pure-button'  value='预订' ></form></th></tr>";
							//+"</th><th><a class='btn-btn-default' href='javascript:void(0);' data='"
							//+JSON.stringify(coachline)
							//+"' onclick='coachsel(this)'>预订</a>";		
							da += txt;

				}
				//da+="</table>"
				$("#coachLines").html(da);
				$("#btn_search").text("查询");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			$("#win-wrapper").fadeOut(5000);
		}
		

	});

	$("#btn_search").text("查询中...");
	
	
});

$("input[type='radio']").change(function(){
	loadCoachLine();
});
	
function loadCoachLine(){
	var gotime ="";
	$("input[type='radio']").each(function(){
		 
		 if($(this).is(':checked')){
			 gotime=$(this).attr('value');
		 };
	 });
	if(gotime ==""){
		gotime="0-1440";
	 }
	
	var gotimes= new Array(); //定义一数组 
	gotimes=gotime.split("-"); //字符分割 
	var gotime1 =gotimes[0];
	var gotime2 =gotimes[1];
	var da="";
		
	for (var i = 0; i < lineList.length; i++) {
		var coachline = lineList[i];

		var time =coachline.dptTime;
		var times= new Array(); //定义一数组 
		times=time.split(":"); //字符分割 
		var mins =Number(Number(times[0])*Number(60))+Number(times[1]);
		if(gotime1 !=gotime2&&(parseInt(mins)<parseInt(gotime1)||parseInt(mins)>parseInt(gotime2))){

		}else{
			var txt = "<tr><th>"
					+ coachline.dptDate
					+ "	"
				    + coachline.dptTime
					+ "</th><th>始:"
					+ coachline.dptStation
					+ "<br>终:"
					+ coachline.arrStation
					+ "</th><th>"
					+ coachline.coachType+"/"+coachline.coachNO
					+ "</th><th>¥"
					+ (parseFloat(coachline.ticketPrice) + parseFloat(coachline.fee)).toFixed(2)
					+ "</th><th>"
					+coachline.ticketLeft
					+"</th><th><form action='coach/orderTickets' method='POST'><input type='hidden' id='coachLineJson' name='coachLineJson' value='"
					+JSON.stringify(coachline)+
					"'><input type='submit' class='pure-button'  value='预订' ></form></th></tr>";
					//+"</th><th><a class='btn-btn-default' href='javascript:void(0);' data='"
					//+JSON.stringify(coachline)
					//+"' onclick='coachsel(this)'>预订</a>";
			da += txt;
		}
	
	}
	
	$("#coachLines").html(da);
}
	
	
//判断是否维护
if($("#wh").length){
	$("#btn_search").addClass("hide");
}else{
	$("#btn_search").removeClass("hide");
}
	
</script>
<!-- <script language="javascript">
	var scrollFunc=function(e){ 
	  e=e || window.event; 
	  if(e.wheelDelta && event.ctrlKey){//IE/Opera/Chrome 
	   event.returnValue=false;
	  }else if(e.detail){//Firefox 
		  
	   event.returnValue=false; 
	  
	  } 
	 }  
	 
	 /*注册事件*/ 
	 if(document.addEventListener){ 
	 document.addEventListener('DOMMouseScroll',scrollFunc,false); 
	 }//W3C 
	 window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome/Safari 

 </script>
 -->
</body>
</html>