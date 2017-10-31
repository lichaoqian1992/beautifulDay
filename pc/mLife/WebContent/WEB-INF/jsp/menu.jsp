<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>menu</title>
<style>
.pure-menu ul li{
font-size:13px;
cursor: pointer;
}
.pure-menu ul li .check{
font-size:14px;
color:#df514d !important;
background:#fff;
font-weight:700;
}
.pure-menu ul li .abc:hover {
background:#fcfcfc !important;}
.pure-menu {
margin-top:10px !important;
border:none !important;
background-color:#ececec !important;
padding-bottom:5px;
height:100%;
}
</style>
<script type="text/javascript">  


function loadhtml2(url){
	
	window.location.href='${ctx}/'+url; 
}


</script>

</head>
<body>
      
      <div id="menu" style="margin-right:10px;">
        <div class="pure-menu pure-menu-open">
           
            <ul>
                <li><a class="abc index1" onclick="loadhtml2('toMobile')">话费充值</a></li>
                <li><a class="abc index2" onclick="loadhtml2('toMobileFlo')">流量充值</a></li>
                <li><a class="abc index3" onclick="loadhtml2('toWaterCoal')">水电气缴费</a></li>
                <li><a class="abc index4" onclick="loadhtml2('toGk')">固话宽带</a></li>
                <li><a class="abc index5" onclick="loadhtml2('toCatv')">有线电视</a></li>
                <li><a class="abc index6" onclick="loadhtml2('toGame')">游戏充值</a></li>
               <!--<li><a class="abc index7" onclick="loadhtml2('toCard')">卡密</a></li>  --> 
                <li><a class="abc index8" onclick="loadhtml2('toFine')">交通罚款</a></li>
                <li><a class="abc index9" onclick="loadhtml2('toGasCard')">加油卡充值</a></li>
                <li><a class="abc index10" onclick="loadhtml2('toCoach')">汽车票</a></li>
                <li><a class="abc index11" onclick="loadhtml2('toTrain')">火车票</a></li>
                <li><a class="abc index12" onclick="loadhtml2('toAir')">飞机票</a></li>
            </ul>
        </div>
</div>
                    
	
</body>
</html>