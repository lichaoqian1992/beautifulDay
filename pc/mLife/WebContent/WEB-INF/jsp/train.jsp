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
	width: 156px;
}

.divinput .divinput_hide ul:first-child li {
	margin-top: 5px;
}

.divinput .divinput_hide ul li {
	margin-bottom: 5px;
	padding-left: 5px;
}

.divinput .divinput_hide ul li:hover {
	cursor: pointer;
	background-color: #ececec;
}

.hide {
	display: none;
}

.divinput .divinput_hide ul {
	margin-left: -40px;
}
</style>

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
		<div id="maincontent" class="pure-u-5-6 mainbackground ">

			<div style="border-bottom: 1px solid #dcdcdc; margin-bottom: 10px;">
				<h2>火车票预订</h2>

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
				<!-- 查询输入 -->
				<div class="well well-sm ticket-search-form">
					<div class="list list-horizontal list-horizontal-compact">
						<div class="item item-input">
							<label class="input-label" for="">出发城市：</label>
							<div class="input-content">
								<div class="divinput" style="width: 150px;">
									<input type="text" autocomplete="off"
										class="form-control ui-autocomplete-input" id="startCity"
										name="startCity"><span role="status"
										aria-live="polite" class="ui-helper-hidden-accessible"></span>
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
									 <input id ="starttime"  class="form-control" type="text" onclick="WdatePicker({minDate:'%y-%M-{%d}'})"/>
								</div>
							</div>
						</div>
						<div class="list list-horizontal list-horizontal-compact">
							<div class="item item-input">
								<label class="input-label" for="">到达城市：</label>
								<div class="input-content">
									<div class="divinput" style="width: 150px;">
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
								<label class="input-label" for=""></label> 
								<a id="btn_search" class="btn btn-wide btn-primary" href="javascript:void(0);">查询</a>
							</div>

						</div>
					</div>
					<!-- 筛选 -->
					<div class="well well-sm">
						<div class="well well-sm well-forbid">
							<div style="width: 120px" class="alert alert-info">
								提示：先查询，再筛选</div>
							<div class="list list-vertical list-vertical-compact">
								<div class="item item-input">
									<label class="input-label" for="">车型选择：</label>
									<div class="input-content horizontal">
										<a id="classAll" class="text-label text-label-primary item selectAll"
											href="javascript:void(0);">全部</a> <label
											class="checkbox item" for=""><input type="checkbox"
											name="classRadio" value="高铁" id="class_G">GC-高铁/城际</label> <label
											class="checkbox item" for=""><input type="checkbox"
											name="classRadio" value="动车" id="class_D">D-动车</label> <label
											class="checkbox item" for=""><input type="checkbox"
											name="classRadio" value="直达" id="class_Z">Z-直达</label> <label
											class="checkbox item" for=""><input type="checkbox"
											name="classRadio" value="特快" id="class_T">T-特快</label> <label
											class="checkbox item" for=""><input type="checkbox"
											name="classRadio" value="快速" id="class_K">K-快速</label> <label
											class="checkbox item" for=""><input type="checkbox"
											name="classRadio" value="其他" id="class_QT">其他</label>
									</div>
								</div>
								<div class="item item-input">
									<label class="input-label" for="">发车时间：</label>
									<div class="input-content horizontal">
										<a id="timeAll" class="text-label text-label-primary item selectAll" href="javascript:void(0);">全部</a>
										<label class="checkbox item" for="zaoshang"><input type="radio"
											value="0-360" id="zaoshang" name="gotime">早上（00:00-06:00）</label>
										<label class="checkbox item" for="shangwu"><input
											type="radio" value="360-720" id="shangwu" name="gotime">上午（06:00-12:00）</label>
										<label class="checkbox item" for="xiawu"><input
											type="radio" value="720-1080" id="xiawu" name="gotime">下午（12:00-18:00）</label>
										<label class="checkbox item" for="wanshang"><input
											type="radio" id="wanshang" value="1080-1440" name="gotime">晚上（18:00-24:00）</label>
									</div>
								</div>
								
							</div>
						</div>
					</div>
					<table id="resultTable"
						class="table table-bordered table-striped table-centered">
						<thead>
							<tr>
								<th>车次</th>
								<th>发/到站</th>
								<th>发车/到达时间</th>
								<th>耗时</th>
								<th>参考票价</th>
								<th>余票</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="trainTickets">
						</tbody>
					</table>
					<div class="alert alert-info">

						<h5>购票须知</h5>
						1.火车票相关业务处理时间为8:00-22:00<br> 2.发车前4小时以内退款不做处理，请自行去火车站退票<br>
						3.不受理改签业务，需用户自行在发车前去车站办理<br> 4.火车票新版上线，支持购买卧铺票、硬座<br>
						5.禁止代理商向用户收取票面价格以外的手续费<br>
						6.提交订单时如提示身份证待核验，乘车人需带二代身份证到火车站通过身份认证才能网上购票<br>
						7.若取票过程中出现问题，请在发车前半小时与平台客服联系处理，超过发车时间的订单不予处理。

					</div>


				</div>
			</div>






		</div>
	<script>
	/* 全选按钮功能 */
		
			var classAll = $(".selectAll");
			
			classAll.click(function(){
				$(this).siblings('.checkbox').find("input").attr("checked",false);
				a();
			});
		
	
	
	
		$(".index11").addClass("check");
		
		var stationdata = "";
		var trainlist ="";
		$(document).ready(
				function() {
					$.ajax({
						type : "GET",
						url : "train/getStation",
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						data : {},
						success : function(data) {
							var list = eval(data);
							stationdata =list;
						}

					});
				});
		
	
		
		
		
		
		
		 $("#startCity").keyup(function(){
				var str =$("#startCity").val();
				
				$("#startcity li").remove();
				var count=0;
				$.each(stationdata, function(i, obj) {
					var name = obj.name;
					var quanpin = obj.quanpin;
					if(str!=""&&(name.indexOf(str))!=-1&&count<10){
						count++;
						$("#startcity").append("<li value="+name+" onclick='startclick(this)'>"+name+"</li>");
					}
					
				});
				
				if($("#startcity li").length>0){
					$("#divstartcity").removeClass("hide");
				}
				else
					{
					$("#divstartcity").addClass("hide");
					}
			});
		function startclick(obj){
			 //alert($(obj).text());
			 $("#startCity").val($(obj).text())	;
			 $("#divstartcity").addClass("hide");
		 
		}
		
		 $("#endCity").keyup(function(){
				var str =$("#endCity").val();
				$("#endcity li").remove();
				var count=0;
				$.each(stationdata, function(i, obj) {
					var name = obj.name;
					var quanpin = obj.quanpin;
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
		 }
		
		$("#btn_search").click(function(){
			
			var queryData={
					"startCity" : $("#startCity").val(),
					"endCity" : $("#endCity").val(),
					"date" : $("#starttime").val()
				};
			$.ajax({
				type : "GET",
				url : "train/getTicket",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : queryData,
				success : function(data) {
					if(data == null || data=="" || data == undefined){
						$("#resultTable").html("<p style='font-size:20px;text-align:center;'>没有符合筛选条件的车次，请修改筛选条件</p>");
		        	}else{
		        		var trainList = eval(data);
		        		// 给全局变量赋值
						trainlist=trainList;
						var da = "";
						
						for (var i = 0; i < trainList.length; i++) {
							var trainLine = trainList[i];
							var time_run = trainLine.runTime
							
							if(time_run > 0){
								
								var seatList =trainLine.trainSeats;
										
								var	txt ="<tr><th>"+trainLine.trainNumber+"<br>"+trainLine.trainTypeName+
								"</th><th>"+trainLine.startTime+"<br>"+trainLine.endTime+
								"</th><th>"+trainLine.currentStartStationName+"<br>"+trainLine.currentEndStationName+
								"</th><th>"+timeStamp(time_run)
								var ticket_txt ="</th><th>"
								var remind_txt ="</th><th>"
								for(var j=0;j<seatList.length;j++){
									
									var seats =seatList[j];
									ticket_txt +=seats.seatName+":"+seats.seatPrice+"元<br>";
									var tickets_ = 0;
									if(seats.remainderTrainTickets){
										tickets_ = seats.remainderTrainTickets;
									}else{
										tickets_ = '无';
									}
									remind_txt +=tickets_+"<br>";
								}
								
								var	txt =txt+ticket_txt+remind_txt+
							
								"</th><th><form id='_form' method='POST' action='train/orderTickets'><input type='hidden' id='date' name='date' value='"
								+$("#starttime").val()+
								"'><input type='hidden' id='trainLineJson' name='trainLineJson' value='"
								+JSON.stringify(trainLine)+
								"'><input type='submit' class='pure-button'  value='预订' ></form></th></tr>";
								da += txt;
							}
						}
						
						$("#trainTickets").html(da);
						$("#btn_search").text("查询");
					}
				},
				error: function(data){
					$("#resultTable").html("服务器返回结果出错!");
					$("#btn_search").text("查询");
				},
			})
			$("#btn_search").text("查询中...");
		});
		
		
		 $("input[name=classRadio]").change(function (){
			 a();
		 });
		 
		 $("input[type='radio']").change(function (){
			a();
		 });
		 
		 function a(){
			 var typearray = new Array();
			 var time ="";
			$("input[name=classRadio]").each(function(){
				 
				 if($(this).is(':checked')){
					 typearray.push($(this).attr('value'));
				 }
			 });
			$("input[type='radio']").each(function(){
				 
				 if($(this).is(':checked')){
					 time=$(this).attr('value');
				 };
			 });
			 
			if(time ==""){
				 time="0-1440";
			 }
			
			
			
			var gotimes= new Array(); //定义一数组 
			gotimes=time.split("-"); //字符分割 
			var gotime1 =gotimes[0];
			var gotime2 =gotimes[1];
			
			var trainlinetxt="";
			for(var i=0;i<trainlist.length;i++){
				var trainline =trainlist[i];
				var time_run = trainline.runTime
				if(time_run){
				var times= new Array(); //定义一数组 
				times=trainline.startTime.split(":"); //字符分割 
				var mins =Number(Number(times[0])*Number(60))+Number(times[1]);
				
				var typeflg=new Boolean();
				var timeflg=new Boolean();
				typeflg =false;
				timeflg =false;
				if(typearray.length==0){
					typeflg =true;
				}else{
					for(var j =0;j<typearray.length;j++){
						if(typearray[j]==trainline.trainTypeName){
							typeflg =true;
						}
					}
				}
				
				if(parseInt(mins)>=parseInt(gotime1)&&parseInt(mins)<parseInt(gotime2)){
					timeflg =true;
				}
				
				
				if(typeflg&&timeflg){
					var seatList =trainline.trainSeats;
					var	txt ="<tr><th>"+trainline.trainNumber+"<br>"+trainline.trainTypeName+
					"</th><th>"+trainline.startTime+"<br>"+trainline.endTime+
					"</th><th>"+trainline.currentStartStationName+"<br>"+trainline.currentEndStationName+
					"</th><th>"+timeStamp(time_run)
					var ticket_txt ="</th><th>"
					var remind_txt ="</th><th>"
					for(var k=0;k<seatList.length;k++){
						
						var seats =seatList[k];
						ticket_txt +=seats.seatName+":"+seats.seatPrice+"元<br>";
						var tickets_ = 0;
						if(seats.remainderTrainTickets){
							tickets_ = seats.remainderTrainTickets;
						}else{
							tickets_ = '无';
						}
						remind_txt +=tickets_+"<br>";
					}
					
					var	txt =txt+ticket_txt+remind_txt+
					"</th><th><form id='_form' method='POST' action='train/orderTickets'><input type='hidden' id='date' name='date' value='"
					+$("#starttime").val()+
					"'><input type='hidden' id='trainLineJson' name='trainLineJson' value='"
					+JSON.stringify(trainline)+
					"'><input type='submit' class='pure-button'  value='预订' ></form></th></tr>";
					trainlinetxt += txt;
				}
				}
			}
			$("#trainTickets").html(trainlinetxt);
		 }
		 
		
		
		function timeStamp( second_time ){ 
			var time = parseInt(second_time) + "秒";  
			if( parseInt(second_time )> 60){ 
			    var second = parseInt(second_time) % 60;  
			    var min = parseInt(second_time / 60);  
			    time = min + "分";  
			      
			    if( min > 60 ){  
			        min = parseInt(second_time / 60) % 60;  
			        var hour = parseInt( parseInt(second_time / 60) /60 );  
			        time = hour + "小时" + min + "分";  
			  
			        if( hour > 24 ){  
			            hour = parseInt( parseInt(second_time / 60) /60 ) % 24;  
			            var day = parseInt( parseInt( parseInt(second_time / 60) /60 ) / 24 );  
			            time = day + "天" + hour + "小时" + min + "分";  
			        }  
			    }  
			}  
			return time;          
		}  
		
		//判断是否维护
		if($("#wh").length){
			$("#btn_search").addClass("hide");
		}else{
			$("#btn_search").removeClass("hide");
		}
		
	</script>

</body>
</html>