<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html">
<html>
<head>
<title>天气预报</title>
	<link href="${ctx}/css/style.css" rel="stylesheet" />
    <link href="${ctx}/css/frame.css" rel="stylesheet" />
    <!--页面主要样式文件-->
    <link href="${ctx}/css/category-icons.css" rel="stylesheet" />
    <!--类目图标样式-->
    <link href="${ctx}/css/sale-qmui.css" rel="stylesheet" />
    <link href="${ctx}/css/style(1).css" rel="stylesheet" />
    <link href="${ctx}/css/datepicker.css" rel="stylesheet" />
    <link href="${ctx}/css/jquery-ui-1.8.21.custom.css" rel="stylesheet" />
    <link href="${ctx}/css/jquery.qtip.min.css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx}/js/jquery-1.11.2.min.js"></script>
<%--     <script src="${ctx}/js/jquery.cookie.js"></script> --%>
    <script src="${ctx}/js/sea-debug.js"></script>
    <script src="${ctx}/js/config.js"></script>
    <script src="${ctx}/js/config(1).js"></script>
<%--     <script src="${ctx}/js/calendar.js"></script> --%>
    <script src="${ctx}/js/zh-cn.js"></script>
	<link href="${ctx}/css/pure-min.css" rel="stylesheet" />

<link type="text/css" rel="stylesheet" href="${ctx}/css/weather.css" />
<script type="text/javascript" src="${ctx}/js/weather.js"></script>
<script type="text/javascript">
function findsWeather() {
	var citycode=$("#city").val();
    var cityname=document.getElementById(citycode).innerHTML;	
   //默认省市
	var region = $("#province").val();
	window.location ="${ctx}/weather?id=3&cityname="+encodeURI(encodeURI(cityname))+"&citycode="+citycode+"&region="+encodeURI(encodeURI(region));
}

</script>
<script type="text/javascript">
	$(function() {
		$("#province").empty();
		var p=$("#region").val();
		for (var i = 0; i < prolist.ps.length; i++) {
			//var pName =prolist.ps[i].proName;
			if(p==i){
				$("#province").append(
						"<option selected=selected    id='"+i+"'  value='"+i+"'>"
								+ prolist.ps[i].proName + "</option>");
			} else{  
				$("#province").append(
						"<option  id='"+i+"'  value='"+i+"'>"
								+ prolist.ps[i].proName + "</option>");
			    }   
		}
		findCity();
	});
	function findCity() {
		var p = $("#province").val();
		//var cityid=$("#cityid").val();
		$("#city").empty(); 
		
		$("#city").append('<option selected="selected">请选择城市</option>');

		for (var i = 0; i < prolist.ps.length; i++) {
		 	if (i==p) {
				for (var j = 0; j < prolist.ps[i].citys.length; j++) {
					
					if(cityid==prolist.ps[i].citys[j].pcode){
						
						$("#city").append(
								"<option  selected=selected   id='"+prolist.ps[i].citys[j].pcode+"'  value='"+prolist.ps[i].citys[j].pcode+"'>"
										+ prolist.ps[i].citys[j].proName
							 			+ "</option>");
					}else{	 	 	
					$("#city").append(
							"<option    id='"+prolist.ps[i].citys[j].pcode+"'  value='"+prolist.ps[i].citys[j].pcode+"'>"
									+ prolist.ps[i].citys[j].proName
						 			+ "</option>");
			 	   } 
			   }
			}
		}
	}
</script>
<style type="text/css">
#l0{
  left: 188px;
}
#l1{
  left: 338px;
}
#l2{
  left: 488px;
}
#l3{
  left: 638px;
}

</style>

</head>
<body>

<div class="pure-g">
	<div  class="pure-u-1">
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	</div>
	</div>
	<div id="main" class="pure-g">
	<div  class="pure-u-1-8">
	<%@ include file="/WEB-INF/jsp/menu.jsp" %>
	</div>
	<div id="maincontent" class="pure-u-5-6 mainbackground">
		
	<div>
	<c:if test="${obj.retData!='数据查询异常!'&&obj.errNum=='0'}">
	<div class="op_weather4_twoicon_container_div">
		<div class="op_weather4_twoicon" style="height: 424px; border-radius:5px;">
			 <div class="address-select" style="background-color: #116BBD; "> 
			    <select  id="province" style="width: 95px;" onchange="findCity()">
				</select>
				<select  style="width: 95px;" id="city"  onchange="findsWeather()">
				</select>
				<input style="color: black;" type="hidden" id="region" value="${obj.retData.region}">
				<input style="color: black;" type="hidden" id="cityid" value="${obj.retData.cityid}">
<!--		<button style="color: black;"  onclick="findsWeather()">更换</button>
 -->		      </div> 
			<div weath-eff="[]" weath-bg="cloudy" target="_blank"
				class="op_weather4_twoicon_today OP_LOG_LINK">
				<p class="op_weather4_twoicon_date">${obj.retData.city}&nbsp;&nbsp;&nbsp;&nbsp;
				${obj.retData.today.week}&nbsp;&nbsp;${obj.retData.today.date} (实时：${obj.retData.today.curTemp})</p>
				<div
					style="background-image: url('${ctx}/images/weather/${obj.retData.today.code}.png');; *background: none; *filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src=http://www.baidu.com/aladdin/img/new_weath/bigicon/3.png)"
					class="op_weather4_twoicon_icon"></div>
				<p class="op_weather4_twoicon_temp">
				 
					${obj.retData.today.hightemp} ~ ${obj.retData.today.lowtemp}
				</p>
				<p class="op_weather4_twoicon_weath">${obj.retData.today.type}</p>
				<p class="op_weather4_twoicon_wind">${obj.retData.today.fengxiang}-${obj.retData.today.fengli }</p> 
				<!--PM标准    0-35优，35-75良，75-115轻度污染，115-150中度污染，150-250重度污染，250及以上严重污染-->
				<!--background 优良(绿色)，轻度中度污染(橙色)，严重污染(红色)-->
				<c:if test="${obj.retData.today.aqi!='null'}">
				<c:if test="${obj.retData.today.aqi>=0&&obj.retData.today.aqi<35}">
					<p class="op_weather4_twoicon_pm25">
						空气质量<em style="background:green;"><b>${obj.retData.today.aqi}</b>&nbsp;优</em>
					</p>
				</c:if>
				<c:if test="${obj.retData.today.aqi>=35&&obj.retData.today.aqi<75}">
					<p class="op_weather4_twoicon_pm25">
						空气质量<em style="background: green"><b>${obj.retData.today.aqi}</b>&nbsp;良</em>
					</p>
				</c:if>
				<c:if test="${obj.retData.today.aqi>=75&&obj.retData.today.aqi<115}">
					<p class="op_weather4_twoicon_pm25">
						空气质量<em style="background: orange;"><b>${obj.retData.today.aqi}</b>&nbsp;轻度污染</em>
					</p>
				</c:if>
				<c:if test="${obj.retData.today.aqi>=115&&obj.retData.today.aqi<150}">
					<p class="op_weather4_twoicon_pm25">
						空气质量<em style="background: orange"><b>${obj.retData.today.aqi}</b>&nbsp;中度污染</em>
					</p>
				</c:if>
				<c:if test="${obj.retData.today.aqi>=150&&obj.retData.today.aqi<250}">
					<p class="op_weather4_twoicon_pm25">
						空气质量<em style="background: red;"><b>${obj.retData.today.aqi}</b>&nbsp;重度污染</em>
					</p>
				</c:if>
				<c:if test="${obj.retData.today.aqi>=250}">
					<p class="op_weather4_twoicon_pm25">
						空气质量<em style="background: red;"><b>${obj.retData.today.aqi}</b>&nbsp;重度污染</em>
					</p>
				</c:if>
				</c:if>
				<c:if test="${obj.retData.today.aqi=='null'}">
				      <p class="op_weather4_twoicon_pm25">
						空气质量<em style="background: gray;"><b>暂无数据</b></em>
					  </p>
				</c:if>
			</div> 
			
	<c:forEach var="f"  items="${obj.retData.forecast}" begin="0" varStatus="s">
			
			<div weath-eff="{&quot;halo&quot;:1}" weath-bg="daytime"
				target="_blank" id="l${s.index}" 
				class="op_weather4_twoicon_day OP_LOG_LINK">
				<div class="op_weather4_twoicon_hover"></div>
				<div class="op_weather4_twoicon_split"></div>
				<p class="op_weather4_twoicon_date">${f.week}</p>
				<p class="op_weather4_twoicon_date_day">${f.date}</p>
				<div
					style="background-image: url('${ctx}/images/weather/${f.code}.png'); *background: none; *filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src=http://www.baidu.com/aladdin/img/new_weath/icon/5.png)"
					
					class="op_weather4_twoicon_icon"></div>
					

				<p class="op_weather4_twoicon_temp">${f.hightemp} ~ ${f.lowtemp}</p>
				<p class="op_weather4_twoicon_weath">${f.type}</p>
				<p class="op_weather4_twoicon_wind">${f.fengxiang}-${f.fengli}</p>
			</div>			
	</c:forEach>

			<!--阴天-->
			<div bg-name="cloudy" class="op_weather4_twoicon_bg"
				style="border-radius:5px; display: none; background-image: -webkit-linear-gradient(top, #485663, #a1b8ca); background-image: -moz-linear-gradient(top, #485663, #a1b8ca); background-image: -o-linear-gradient(top, #485663, #a1b8ca); background-image: -ms-linear-gradient(top, #485663, #a1b8ca); filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, StartColorStr=#485663, EndColorStr=#a1b8ca); z-index: 0.01; opacity: 1; height: 424px;">
			</div>


			<!--晴天-->
			<div bg-name="daytime" class="op_weather4_twoicon_bg"
				style="border-radius:5px; background-image: -webkit-linear-gradient(top, #0d68bc, #72ade0); background-image: -moz-linear-gradient(top, #0d68bc, #72ade0); background-image: -o-linear-gradient(top, #0d68bc, #72ade0); background-image: -ms-linear-gradient(top, #0d68bc, #72ade0); filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, StartColorStr=#0d68bc, EndColorStr=#72ade0); z-index: 2; opacity: 1; height: 424px;">
			</div>
		</div>

		<div class="op_weather4_xiala">
			<div style="display: block;" class="op_weather4_xltabcontent">
				<div class="op_weather4_xltab c-clearfix">
					<ul>
						<li data-click="{fm:'beha'}"
							class="OP_LOG_BTN op_weather4_xlactive"><span
							class="op_weather4_xlfilter">相关指数</span></li>
					</ul>
				</div>
				<div style="display: block;" class="op_weather4_xlcon">
					<ul class="op_weather4_xltop c-clearfix">
						<c:forEach items="${obj.retData.today.index}" var="i" end="4" begin="0">
						<li class="op_weather4_xlzs"style="position: relative; z-index: 6; float: left;">
							<div class="op_weather4_xlzstit">${i.name}&nbsp;:&nbsp;&nbsp;&nbsp;${i.index}</div>
								<div class="">
									<div class="op_weather4_xltiptitle">${i.details}</div>
								</div>
							</li>
						 </c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</c:if>
	<c:if test="${obj.retData=='数据查询异常!'||obj.errNum!='0'}">
	<div class="op_weather4_twoicon_container_div">
		<div class="op_weather4_twoicon" style="height: 424px;">	
			 <div style="background-color: #116BBD;" class="address-select"> 
			    <select id="province" onchange="findCity()" style="width: 95px;">
				</select>
				<select  style="width: 95px;"  id="city" onchange="findsWeather()">
				</select>
<!-- 				<button onclick="findsWeather()">更换</button>
 -->		      </div>	
		      <div weath-eff="[]" weath-bg="cloudy" target="_blank"
				class="op_weather4_twoicon_today OP_LOG_LINK">
		      <p class="op_weather4_twoicon_temp">
						暂无数据,请稍后再试!
			  </p>	
			  </div>
					<!--阴天-->
			<div bg-name="cloudy" class="op_weather4_twoicon_bg"
				style="display: none; background-image: -webkit-linear-gradient(top, #485663, #a1b8ca); background-image: -moz-linear-gradient(top, #485663, #a1b8ca); background-image: -o-linear-gradient(top, #485663, #a1b8ca); background-image: -ms-linear-gradient(top, #485663, #a1b8ca); filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, StartColorStr=#485663, EndColorStr=#a1b8ca); z-index: 0.01; opacity: 1; height: 424px;">
			</div>
			
			
			<!--晴天-->
			<div bg-name="daytime" class="op_weather4_twoicon_bg"
				style="background-image: -webkit-linear-gradient(top, #0d68bc, #72ade0); background-image: -moz-linear-gradient(top, #0d68bc, #72ade0); background-image: -o-linear-gradient(top, #0d68bc, #72ade0); background-image: -ms-linear-gradient(top, #0d68bc, #72ade0); filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, StartColorStr=#0d68bc, EndColorStr=#72ade0); z-index: 2; opacity: 1; height: 424px;">
			</div>
			</div>
			</div>
	</c:if>
	</div>
	</div>
	
</body>
</html>
