<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>满集生活缴费平台</title>

<!--日期控件 -->
<script  type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<!-- 飞机站点js -->
<script src="${ctx}/js/air_stations.js"></script>

<style type="text/css">
ul, ol {
	list-style: none;
}

.divinput {
	width: 150px;
	height: 30px;
	position: relative;
}

.divinput .divinput_input {
	width: auto;
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
	width: 142px;
}

.divinput .divinput_hide ul:first-child li {
	margin-top: 5px;
}

.divinput .divinput_hide ul li {
	margin-bottom: 5px;
	margin-left: -40px !important;
	padding-left: 5px;
}

.list-inline {
	padding: 0 !important;
}

.list-inline .divinput_hide ul li:hover {
	cursor: pointer;
	background-color: #ececec;
}

.list-inline .li_child {
	float: left;
	line-height: 25px;
}

.hide {
	display: none;
}

.divinput {
	margin-left: 5px;
	margin-right: 20px;
}

.input-text {
	height: 30px !important;
	margin-left: 5px;
	margin-top: -28px;
}

.li_child .pure-button-primary {
	margin-left: 730px;
	margin-top: -72px;
}
</style>

<style type="text/css">

.text-muted {
	color: #999999;
}

.refund-privacy {
	cursor: pointer;
	display: inline-block;
	position: relative;
}

.refund-privacy .well .corner {
	border-bottom-color: #e3e3e3;
	left: 225px;
	top: -15px;
}

.refund-privacy .well .corner, .refund-privacy .well .corner i {
	border-color: transparent;
	border-style: dashed dashed solid;
	border-width: 8px;
	position: absolute;
}

.text-danger {
	color: #a94442;
}

.refund-privacy .well {
	display: none;
	left: -216px;
	position: absolute;
	text-align: left;
	top: 23px;
	width: 460px;
	z-index: 50;
}

.well {
	background-color: #f5f5f5;
	border: 1px solid #e3e3e3;
	border-radius: 2px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05) inset;
	margin-bottom: 18px;
	min-height: 20px;
	padding: 19px;
}

.font-large {
	font-size: 16px;
	font-weight: bold;
}

.font-la {
	font-size: 12px;
	font-weight: bold;
}

.font-Price {
	font-size: 18px;
	font-weight: bold;
}

.font-jr {
	font-size: 15px;
	font-weight: bold;
}

.orange {
	color: #f60;
}

.btn-default {
	background-color: #FF7200;
}

.btn-d {
	background-color: #68CBE0;
}
.airs-con{
background-color:#fff;
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
			<div style="border-bottom: 1px solid #dcdcdc; margin-bottom: 10px;">
				<h2>搜索航班&nbsp;&nbsp;</h2>
			</div>
			<div class="well well-sm">
				<div>
						  	 <c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose>	
				</div>
				<form action="" method="post" class="pure-form pure-form-aligned">
					<div class="bus_content">
						<ul class="list-inline">
							<li class="li_child">出发城市:</li>
							<li class="li_child">
								<div class="divinput" style="width: 150px;" >
									<input type="text" class="divinput_input" id="startstr"
										autocomplete="off"/> <input type="hidden" id="from" 
										name="from" />
									<div class="divinput_hide hide" id="divstartcity">
										<ul id="startcity">
										</ul>
									</div>
								</div>
							</li> &nbsp;&nbsp;
							<li class="li_child">到达城市:</li>
							<li class="li_child">
								<div class="divinput" style="width: 150px;">
									<input type="text" class="divinput_input" id="endstr"
										autocomplete="off" /> <input type="hidden" id="to" name="to" />
									<div class="divinput_hide hide" id="divendcity">
										<ul id="endcity">
										</ul>
									</div>
								</div>
							</li> &nbsp;&nbsp;
							<li class="li_child">乘车日期:</li>
							<li>
							<input type="text" name="date" style="width: 185px"
								onclick="WdatePicker({minDate:'%y-%M-{%d}'})"
								id="date" placeholder="出发日期" autocomplete="off"  class="input-text Wdate" 
								datatype="*" nullmsg="出发日期不能为空" />
								</li>
							<li class="li_child">
							<input id="ding" class="pure-button pure-button-primary" type="button" onclick="sshb()" value="搜索航线">
							</li>
						</ul>
					</div>
				</form>
				
				
				<!-- 换行 -->

		<div class="alert alert-info"
					style="margin-top: 10px; line-height: 15px;">
					<h5 style="line-height: 30px;">重要提示：</h5>
					<p>1、以下票面价不包含机场建设费、燃油附加税。</p>
					<p>2、特殊舱位价格变化快，以下特殊舱位价格仅作参考，以订座成功后价格为准。</p>
				</div>
		<!-- 筛选框 -->
		<div class="well well-sm">
            <div class="list list-vertical list-vertical-compact">
                <div style="width: 120px" class="alert alert-info">
								提示：先查询，再筛选
				</div>
                <div class="item item-input">
                    <label for="" class="input-label">起飞时间：</label>

                    <div class="input-content horizontal">
                        <a href="javascript:onclick=findairs();"  class="text-label text-label-primary item">全部</a>
                        <label  class="checkbox item"><input name="timeCheckName"  value="0-6" type="radio">早晨（0~6点）</label>
                        <label  class="checkbox item"><input name="timeCheckName"  value="6-12" type="radio">上午（6~12点）</label>
                        <label  class="checkbox item"><input name="timeCheckName"  value="12-13" type="radio">中午（12~13点）</label>
                        <label  class="checkbox item"><input name="timeCheckName"  value="13-18" type="radio">下午（13~18点）</label>
                        <label  class="checkbox item"><input name="timeCheckName"  value="18-24" type="radio">晚上（18~24点）</label>
                    </div>
                </div>
                <div id="companydiv" class="item item-input">
                   <label  class="input-label">航空公司：</label>
                     <div class="input-content horizontal">
                        <a href="javascript:onclick=findairsCompany();"  class="text-label text-label-primary item">全部</a>
                        <label  class="checkbox item"><input name="flightCompany"  value="CA" type="radio">中国航空</label>
                        <label  class="checkbox item"><input name="flightCompany"  value="CZ" type="radio">南方航空</label>
                        <label  class="checkbox item"><input name="flightCompany"  value="FM" type="radio">上海航空</label>
                        <label  class="checkbox item"><input name="flightCompany"  value="MU" type="radio">东方航空</label>
                        <label  class="checkbox item"><input name="flightCompany"  value="HO" type="radio">吉祥航空</label>
                        <label  class="checkbox item"><input name="flightCompany"  value="HU" type="radio">海南航空</label>
                        <label  class="checkbox item"><input name="flightCompany"  value="3U" type="radio">四川航空</label>
                        <label  class="checkbox item"><input name="flightCompany"  value="SC" type="radio">山东航空</label>
                    </div>
                </div>
                <div id="seatdiv" class="item item-input">
                    <label for="" class="input-label">舱位：</label>
                    <div class="input-content horizontal">
                        <a href="javascript:onclick=findairsSeatCode();"  class="text-label text-label-primary item">全部</a>
                        <label  class="checkbox item"><input name="seatCode"  value="经济舱" type="radio">经济舱</label>
                        <label  class="checkbox item"><input name="seatCode"  value="特价舱位" type="radio">特价舱位</label>
                        <label  class="checkbox item"><input name="seatCode"  value="商务舱" type="radio">商务舱</label>
                        <label  class="checkbox item"><input name="seatCode"  value="头等舱" type="radio">头等舱</label>
                    </div>
                </div>
            </div>
        </div>
	<!-- 航线列表 -->
				<div>

					<table
						class="table table-hover table-centered airplane-info-list">
						<thead>
							<tr>
								<th class="text-left">航班信息</th>
								<th>起抵时间</th>
								<th>起抵机场</th>
								<th class="fix-1">舱位</th>
								<th class="fix-2">票面价</th>
								<th class="fix-3">余票</th>
								<th class="fix-4">操作</th>
							</tr>
						</thead>
						<tbody id="airTable">
						</tbody>
					</table>
				</div>
				<!-- 购票须知 -->
				<div class="alert alert-info"
					style="margin-bottom: 0; margin-top: 10px; line-height: 20px;">
					<h5 style="line-height: 30px;">购票须知</h5>
					1、机票相关业务处理时间为8:00-22:00 <br> 2、机票业务不做改签处理 <br>
					3、机票支持在线申请退废票和自愿退票，非自愿退票请根据平台规则联系客服处理 <br>
					4、出票当日提交"退废票"，具体以航司规定为准 <br> 5、请仔细核对用户身份证号以及姓名，以免造成不必要的损失 <br>
					6、行程单(报销凭证)机场提供打印服务 <br> 7、目前不支持飞机票保险货源
				</div>

			</div>
			<br> <br> <br>
		</div>
	</div>
		<!-- 弹出层 -->
	<div id="win-wrapper" style="overflow-y :auto;overflow :yes;width: 300px;height: 150px;margin-left: 1200px;margin-top: 600px;">
		<div id="win" style="margin-top:20px; width: 280px;height: 100px;">
			<div class="win-body">
				<table border="1" style="width: 280;height: 80px;">
					<tr><td rowspan="2">成人票</td><td>&nbsp;</td><td>起飞前2小时</td><td>起飞后2小时</td></tr>
					<tr><td>机票退订费百分比</td><td>1</td><td>2</td></tr>
				</table>
			</div>
	  	</div>
  	</div>
<!-- 提示框 -->
	<div   class="sgBtn" style="display: none;">弹窗3(警告)</div>



<script type="text/javascript">
	/* 获取飞机票站点集合 */
	var stationdata = '';
	var last;
	$(document).ready(function() {
		//改变搜索按钮的值
		$("#ding").val("搜索航班");

		/* var List = air_stations.air_stations_list_response;
		var st = List.stations;
		stationdata = st.station;
		alert(stationdata); */
		//先查询数据库所有的机场信息
		/* var inputInfo = $("#startstr").val();//出发城市
		var outputInfo = $("#endstr").val();//到达城市
		$.ajax({
			type:"GET",
			url:"air/getAirport",
			dataType:"json",
			contentType : "application/json; charset=utf-8",
			data:{startCity:inputInfo,endCity:outputInfo}
			,success:function(data){
				stationdata = data;
			}
		}); */
	})
</script>

<!-- 校验搜索信息 -->
<script type="text/javascript">
	function check() {
		var f = $("#from").val();
		var t = $("#to").val();
		//乘车时间
		var d = $("#date").val();
		//获取系统的当前时间
		var mydate = new Date();
		var str = "" + mydate.getFullYear() + "-";
		str += (mydate.getMonth() + 1) + "-";
		str += mydate.getDate();
		if (f == null || f == "" || t == null || t == "" || d == null
				|| d == "") {
			var txt = "请填写正确的搜索信息";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		if (f == t) {
			var txt = "出发站和到达站不能相同";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		/*两个时间大小的比较校验 */
		var start = new Date(d.replace("-", "/").replace("-", "/"));
		var tady = new Date(str.replace("-", "/").replace("-", "/"));
		if (tady > start) {
			var txt = "乘车时间错误";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		$("#ding").val("搜索中......");

		return true;
	}

	/* 舱位列表 */
	function airSeats(id) {
		var f = $("#0" + id).html();
		if (f == "收起所有舱位") {
			$("#0" + id).html("展开所有舱位");
			$(".1" + id).css("display","none");
			return;
		}
		$(".1" + id).css("display","table-row");
		$("#0" + id).html("收起所有舱位");
		
	}

	/* 筛选框校验 时间*/
	function deptime() {
		var time = $("#time").val();
		if (time == '') {
			var txt = "请选择筛选条件";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		$("#sxfrom").submit();
	}
	
	
	
	/* 航空公司 */
	function companyCode() {
		var hk = $("#hk").val();
		if (hk == '') {
			var txt = "请选择筛选条件";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
			return false;
		}
		$("#sxfrom").submit();
	}
	
</script>


<script type="text/javascript">

var airsListInfo=new Array();//航班信息集合
var airSeatsList=new Array();//航班子集合信息
/**
 * 异步加载无刷新页面加载航班信息
 */
function sshb() {
	 $("#airTable").empty();
	//校验搜索信息
	if(!check()){
		return false;
	}
	var from = $("#from").val();
	var to = $("#to").val();
	//乘车时间
	var date = $("#date").val();
	var name1 = $("#startstr").val().split("(")[0];
	 var name2 = $("#endstr").val().split("(")[0];
	//获取航班信息
	 $.ajax({
         url: "${ctx}/air/airLineslist",
         type: "post",
         dataType:"json",
         data: 'from='+from+'&to='+to+'&date='+date+'&start='+name1+'&end='+name2,
         success: function (data) {
        	 $("#ding").val("搜索航线");
        	 if(data==0){
        		var txt = "暂无航班信息,请稍后重试";
     			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
     			return false;
        	 }else{
        	 airsListInfo=[];
        	 airsListInfo=data;
        	 airsList(airsListInfo);
        	 }
         }
	 });
}

//遍历航班信息
function airsList(airlinesList) {
	//清除之前的航班列表
	 $("#airTable").empty();
	 for (var i = 0; i < airlinesList.length; i++) {
		 var airName1 = $("#startstr").val().split("(")[0];
		 var airName2 = $("#endstr").val().split("(")[0];
		  var tbodyHead='<tr class="text-airs">'
			+'<td class="text-left">'
			//航空公司信息
			+'<p>'+airlinesList[i].flightCompanyName + airlinesList[i].flightNo +'<br>机型'+ airlinesList[i].planeType +'</p>'
			+'</td>'
			+'<td>'
			//起飞到达时间
			+'<p>'+airlinesList[i].depTime+'<br>'+airlinesList[i].arriTime+'</p>'
			+'</td>'
			+'<td>'
			//起到城市航站楼
			+'<p>'+airlinesList[i].orgCityName+'<br>' +airlinesList[i].dstCityName+'</p>'
			+'</td>'
			//遍历第一个子航班
					+'<td>'
					+'<p>'
					//舱位
					+'<span class="carriage">'+airlinesList[i].airSeats[0].seatMsg+'</span><br>'
					+'</p>' 
					+'<div class="text-muted refund-privacy">'
					+'<p class="pp">退改签</p>'
					+'<div style="display: none;" class="well">'
					+'	<span class="corner"><i></i></span>'
					+'	<h4 class="text-danger">仅供参考！请以航空公司为准！</h4>'
					+'	<h4>退票规定：</h4>'
					+'	<p></p>'
					+'	<h4>改签规定：</h4>'
					+'	<p></p>'
					+'</div>'
					+'</div>'
					+'</td>'
					+'<td>'
					+'<p class="price">¥'+airlinesList[i].airSeats[0].parPrice +'&nbsp;&nbsp;</p>'
					+'</td>';
					
					//余票状态seatStatus
					if(airlinesList[i].airSeats[0].seatStatus=='A'){
					    tbodyHead +='<td class="fix-3"><span class="text-label text-label-success">充足</span></td>';
					}else{
						tbodyHead +='<td class="fix-3">'+airlinesList[i].airSeats[0].seatStatus+'<span class="text-label text-label-warning">紧张</span></td>';
					}
					
					tbodyHead +='<td>'
				     +'<form  method="POST" action="air/airReserve">'
					 +'<input type="hidden"  name="date" value='+$("#date").val()+'>'
				     +'<input type="hidden"  name="airlines" value='+JSON.stringify(airlinesList[i])+'>'
				     +'<input type="hidden"  name="airSeat"  value=0>'
					 +'<a class="btn btn-default btn-block" onclick="airsOrder(this)">预定</a>'
					 +'</form>'
					if(airlinesList[i].airSeats.length!=1){
					tbodyHead +="<a  href=javascript:onclick=airSeats('"+airlinesList[i].flightNo+"'); id='0"+airlinesList[i].flightNo+"'  class='btn btn-block js-hide-line-toggle-down'>展开所有舱位 </a>";
					}
					tbodyHead +='</tr>';
		     $("#airTable").append(tbodyHead);
			 //子航班信息展示
			 airSeatsList=[];
			 airSeatsList =airlinesList[i].airSeats;
			 for (var a = 1; a <airSeatsList.length; a++){
		      var tbodyMiddle='<tr style="display:none;" id="1'+airlinesList[i].flightNo+'"  class="js-hide-line 1'+airlinesList[i].flightNo+'">'
					+'<td colspan="7">'
					+'<table class="table">'
					+'<tbody>'
					+'<tr>'
					+'<td colspan="3">&nbsp;</td>'
					+'<td class="fix-1">'
					+'<p>'
					//舱位
					+'<span class="carriage">'+airSeatsList[a].seatMsg+'</span><br>'
					+'</p>'
					+'<div class="text-muted refund-privacy">'
					+'	退改签'
					+'	<div class="well">'
					+'		<span class="corner"><i></i></span>'
					+'		<h4 class="text-danger">仅供参考！请以航空公司为准！</h4>'
					+'		<h4>退票规定：</h4>'
					+'			<p></p>'
					+'			<h4>改签规定：</h4>'
					+'				<p></p>'
					+'			</div>'
					+'		</div>'
					+'	</td>'
					+'	<td class="fix-2">'
					//价格优惠
					+'		<p class="price">¥'+airSeatsList[a].parPrice+'&nbsp;&nbsp;'
					+'		</p>'
					+'		</td>';
					//余票状态seatStatus
					if(airSeatsList[a].seatStatus=='A'){
						tbodyMiddle +='<td class="fix-3"><span class="text-label text-label-success">充足</span></td>';
					}else{ 
						tbodyMiddle +='<td class="fix-3">'+airSeatsList[a].seatStatus+'<span class="text-label text-label-warning">紧张</span></td>';
					}
					tbodyMiddle+='<td class="fix-4">'
						     +'<form id="airseatStatus" method="POST" action="air/airReserve">'
							 +'<input type="hidden"  name="date" value='+$("#date").val()+'>'
						     +'<input type="hidden"  name="airlines" value='+JSON.stringify(airlinesList[i])+'>'
						     +'<input type="hidden"  name="airSeat"  value='+a+'>'
							 +'<a class="btn btn-default btn-block" onclick=seatStatus(this)>预定</a>'
							 +'</form>'
					+'</td>'
					+'</tr>'
					+'</tbody>'
					+'</table>'
					+'</td>'
					+'</tr>';
				    $("#airTable").append(tbodyMiddle);
			 }; 
					 //燃油机建费用
		                var tbodytTail='<tr class="additional-cost">'
						+'<td colspan="7" class="text-left">机建 / 燃油：¥'+airlinesList[i].adultAirportTax+'/ ¥'+airlinesList[i].adultFuelTax +'</td>'
			            +'</tr>';
		              
			            $("#airTable").append(tbodytTail);
	}
	 //设置斑马纹表格
	 $("#airTable").find(".text-airs").each(function (index) {
         if (index % 2 == 0)
        	 
             $(this).addClass("airs-con");
         else
        	 {

             $(this).removeClass("airs-con");
        	 
        	 }
     });
	 //当鼠标到达“退改签”的时候显示退改签需要注意的事项
	 $(".pp").mouseover(function(){
		 $("#win-wrapper").fadeIn();
	 });
	 $(".pp").mouseout(function(){
		 $("#win-wrapper").fadeOut();
	 });
	 
} 
</script>

<script type="text/javascript">
	
	$(".index12").addClass("check");
	
	//查询全部航班信息
	function findairs() {
		//重置radio按钮
		$('input:radio[name=timeCheckName]').attr('checked',false);
		changeAirs();
	}
	function findairsCompany() {
		//重置radio按钮
		$('input:radio[name=flightCompany]').attr('checked',false);
		changeAirs();
	}
	
	function findairsSeatCode() {
		//重置radio按钮
		$('input:radio[name=seatCode]').attr('checked',false);
		changeAirs();
	}
	
	//航班信息筛选框
	
	 $("input[name=timeCheckName]").change(function (){
		 
		 	changeAirs();
		 });
		 
	 $("input[name='flightCompany']").change(function (){
		 	changeAirs();
		 });
	 
	 $("input[name='seatCode']").change(function (){
		 	changeAirs();
		 });
	 
	 function changeAirs() {
		 
		 if(airsListInfo==''){
			  return false;
		 }
		 typearray=[];
		//时间
		$("input[name=timeCheckName]").each(function(){
			 
			 if($(this).is(':checked')){
				 typearray[0]=($(this).attr('value'));
			 }
		 });
		//航空公司
		$("input[name='flightCompany']").each(function(){
			 
			 if($(this).is(':checked')){
				 typearray[1]=$(this).attr('value');
			 };
		 });
		
		//舱位
		$("input[name='seatCode']").each(function(){
			 
			 if($(this).is(':checked')){
				 typearray[2]=$(this).attr('value');
			 };
		 });
		
		if(typearray.length==0){
			
	        airsList(airsListInfo);

		}else{
			    arrryList0=airsListInfo;
			    arrryList1=[];

			var time=typearray[0];//时间
			
			var flightCompany=typearray[1];//航空公司
			
			
			var seatCode=typearray[2];//舱位
			
			
			if(time!=null){
					var gotimes= new Array(); 
					    gotimes=time.split("-"); //字符分割 
					var gotime1 =gotimes[0];
					var gotime2 =gotimes[1];
					
					  for (var i = 0; i < arrryList0.length; i++) {
						   //起
						   	var depTime= new Array(); 
						   
						        depTime=arrryList0[i].depTime.split(":");//时间
							var dt =depTime[0];
						        
							if(parseInt(dt)>=parseInt(gotime1)&&parseInt(dt)<parseInt(gotime2)){
							    //添加满足条件的航班
								arrryList1.push(arrryList0[i]);
							}
					   }
					  
					  
					 if(arrryList1.length!=0){
						 arrryList0=[];
						 arrryList0=arrryList1;
						 arrryList1=[];
					 }else{
						 arrryList0=[];
					 }
				  
			}
			
			//航空公司信息
			if(flightCompany!=null){
				  for (var i = 0; i < arrryList0.length; i++) {
					  
					    Code=arrryList0[i].flightCompanyCode;//公司
					    
						if(Code==flightCompany){
						    //添加满足条件的航班
							arrryList1.push(arrryList0[i]);
						}
				   }
				  if(arrryList1.length!=0){
						 arrryList0=[];
						 arrryList0=arrryList1;
						 arrryList1=[];
					 }else{
						 arrryList0=[];
					 }
			
			}
			
			//舱位信息
			if(seatCode!=null){
				 for (var i = 0; i < arrryList0.length; i++) {
					  
					   var Code=arrryList0[i].airSeats;
					   
					   for (var int2 = 0; int2 < Code.length; int2++) {
						   	 
							 if(Code[0].seatMsg==seatCode){
			            		 //添加满足条件的航班
			            		arrryList1.push(arrryList0[i]);
			            		 break;
			            	}
						
						}
			            	
					}
				
				 if(arrryList1.length!=0){
					 arrryList0=[];
					 arrryList0=arrryList1;
					 arrryList1=[];
				 }else{
					 arrryList0=[];
				 }
					  
			 }
         	   airsList(arrryList0); 
			}
			
		}
	 
		 //提交父航班表单信息
		
		function airsOrder(obj) {
			
			   $(obj).parent().submit();
		}
		  //提交子航班表单信息
		  
		function seatStatus(obj) {
			
			   $(obj).parent().submit();
		}
		
	
	</script>

	<!-- 飞机站点 -->
	<script type="text/javascript">
	function getMessage(str){
		var count = 0;
		var stationdata = '';
		var inputInfo = $("#startstr").val();//出发城市
		var outputInfo = "";//到达城市
		$.ajax({
			type:"GET",
			url:"air/getAirport",
			dataType:"json",
			contentType : "application/json; charset=utf-8",
			data:{startCity:inputInfo,endCity:outputInfo}
			,success:function(data){
				stationdata = data;
				//如果输入的是拼音，那么就是字母
				$.each(stationdata,function(i, obj) {
									/* if(exp.test(str)){
										str = str.toUpperCase();
										var name = obj.airJm;
										name = name.toUpperCase();
									}else{
										var name = obj.airName;
									} */
									var name = obj.airName;
									var code = obj.airId;
									if (str != "" && count < 10) {
										count++;
										$("#startcity")
												.append(
														'<li  value='
																+ code
																+ ' onclick="startclick(this)">'
																+ obj.airName
																+ '('
																+ code
																+ ')'
																+ '</li>');
									}

								});
				if ($("#startcity li").length > 0) {
					$("#divstartcity").removeClass("hide");
				} else {
					$("#divstartcity").addClass("hide");
				}
			}
		});
	}
		/* 出发站 */
		var exp = /^[a-zA-Z]$/;
		$("#startstr").keyup(function(event) {
			$("#startcity li").remove();
			var str = $("#startstr").val();
			last=event.timeStamp;
	 		 setTimeout(function(){
	 			 if(last-event.timeStamp==0){
	 				getMessage(str);
	 			 }
	 		 },1000);
		});
		//出发站点的值
		function startclick(obj) {

			$("#startstr").val($(obj).text());

			$("#from").attr("value", $(obj).attr("value"));

			$("#divstartcity").addClass("hide");
		}

		/* 到达站 弹出层*/
		function a(){
			var stationdata = '';
			var inputInfo = "";//出发城市
			var outputInfo = $("#endstr").val();//到达城市
			var str = document.getElementById("endstr").value;
			var count = 0;
			$.ajax({
				type:"GET",
				url:"air/getAirport",
				dataType:"json",
				contentType : "application/json; charset=utf-8",
				data:{startCity:inputInfo,endCity:outputInfo}
				,success:function(data){
					stationdata = data;
					$.each(stationdata, function(i, obj) {
						if(exp.test(str)){
							str = str.toUpperCase();
							var name = obj.airJm;
							name = name.toUpperCase();
						}else{
							var name = obj.airName;
						}
						var code = obj.airId;
						if (str != "" && count < 10) {
							count++;
							$("#endcity").append(
									'<li value=' + code
											+ ' onclick=endclick(this)>' + obj.airName
											+ '(' + code + ')' + '</li>');
						}

					});

					if ($("#endcity li").length > 0) {
						$("#divendcity").removeClass("hide");
					} else {
						$("#divendcity").addClass("hide");
					}
				}
			});
		}
		/**当键盘输入的太快的时候，keyup事件反应不过来，会导致里面的函数有部分不能执行，所以我们设置keyup里面函数执行的延迟时间，这样当keyup事件完成以后在执行函数就不会出现上述情况**/
		$("#endstr").keyup(function(event) {
					$("#endcity li").remove();
			 		last=event.timeStamp;
			 		 setTimeout(function(){
			 			 if(last-event.timeStamp==0){
			 				 a();
			 			 }
			 		 },1000);
				});
		/*  到达站的值*/
		function endclick(obj) {

			$("#endstr").val($(obj).text());
			$("#to").attr("value", $(obj).attr("value"));
			$("#divendcity").addClass("hide");
		}
		
		//判断是否维护
		if($("#wh").length){
			$("#ding").addClass("hide");
		}else{
			$("#ding").removeClass("hide");
		}
	</script>


</body>
</html>