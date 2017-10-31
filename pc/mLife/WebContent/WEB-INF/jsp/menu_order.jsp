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
           		<li><a style="cursor:default"><h3><b>我的订单</b></h3></a></li>
                <li><a class="abc index20 "  onclick="loadhtml2('toOrder')">充值订单</a></li>
                <li><a class="abc index21 "  onclick="loadhtml2('toTrafficOrder')" >票务订单</a></li>

            </ul>
        </div>
</div>
                    

	
</body>
</html>