var xmlhttp=null,
	jsonobj,
	HomeUrl="http://www.weather.com.cn/weather/",//链接源
	HomeDataUrl="http://m.weather.com.cn",//数据源
	rs="http://61.4.185.48:81/g/",//数据接口
	id1,
	url=window.location.href,
	start=url.indexOf("id"),
	end=url.indexOf("T"),
	GetUrlParms = function()    
	{
		var args=new Object();   
		var query=window.location.search.substring(1);//获取查询串   
		var pairs=query.split("&");//在逗号处断开   
		for(var    i=0;i<pairs.length;i++)   
		{   
			var pos=pairs[i].indexOf('=');//查找name=value   
			if(pos==-1)   continue;//如果没有找到就跳过   
			var argname=pairs[i].substring(0,pos);//提取name   
			var value=pairs[i].substring(pos+1);//提取value   
			args[argname]=unescape(value);//存为属性   
		}
		return args;
	};
//判断是否带有参数
var args = new Object();
	args = GetUrlParms();
if (args["id"] == undefined || args["id"] == ""){
	start = -1;
}
if (args["T"] == undefined || args["T"] == ""){
	end = -1;
}
var setCookie = function(name, value) {
	var argv = setCookie.arguments; 
	var argc = setCookie.arguments.length; 
	var expires = (argc > 2) ? argv[2] : null; 
	if(expires!=null)
	{ 
		var LargeExpDate = new Date ();
		LargeExpDate.setTime(LargeExpDate.getTime() + (expires*365*24*60*60*1000*10));
	} 
	document.cookie = name + "=" + escape (value)+((expires == null) ? "" : ("; expires=" +LargeExpDate.toGMTString())); 
}
var getCookie = function(Name){
	var search = Name + "=" ;
	if(document.cookie.length > 0){
		offset = document.cookie.indexOf(search) 
		if(offset != -1)
		{
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if(end == -1){ end = document.cookie.length;}
			return unescape(document.cookie.substring(offset, end));
		} 
		else return "";
	} 
}
var createXMLHTTPRequext = function(){
	if (window.XMLHttpRequest){xmlhttp = new XMLHttpRequest(); }
	if (window.ActiveXObject){
		var activeX = ['Msxml2.XMLHTTP.6.0','Msxml2.XMLHTTP.3.0','Msxml2.XMLHTTP'];
		for (var i = 0; i < activeX.length; ++i) {
			try {
				xmlhttp = new ActiveXObject(activeX[i]);
				break;
			} catch(e) {}
		}
		if (!xmlhttp ){xmlhttp = new ActiveXObject('Microsoft.XMLHTTP'); }
	}
}
var HandleStateChange = function(){
	if (xmlhttp.readyState == 4)
	{
		var jsontext =xmlhttp.responseText;
		var func = new Function("return " + jsontext);
		jsonobj = func();
	} 
}
var PostOrder = function(xmldoc){
	createXMLHTTPRequext();
	xmlhttp.open("GET", xmldoc,false);
	xmlhttp.onreadystatechange= HandleStateChange;
	xmlhttp.send(null);
}
var returndata = function(id){
	var datastr1;
	if(id==""){
		datastr1=HomeDataUrl+'/data/'+str+'.html';
	}else{
		datastr1=HomeDataUrl+'/data/'+id+'.html';
	}
	PostOrder(datastr1);
	HandleStateChange();
	var parseData=new Object();
	with(jsonobj.weatherinfo){
		parseData={
			city: {innerHTML: city},
			temp1: {innerHTML: temp1},
			index_d: {innerHTML: index_d},
			index_uv: {innerHTML: index_uv},
			small1: {
				src:"weather_"+(fchh==18?(img2==99?img1:img2):img1)+".png",
				title: "今日天气指数 紫外线指数:"+index_uv+"。"+"穿衣指数:"+index_d
			}
		}
	}
	for( var m in parseData){
		var node=document.getElementById(m);
		var sets=parseData[m];
		if(m == "temp1")
		{//取最高温度
			if(node){for( var prop in sets ){node[prop]=sets[prop].substr(sets[prop].indexOf('~')+1);}}
		}else{
			if(node){for( var prop in sets ){node[prop]=sets[prop];}}
		}
	}
	//更新时间
	document.getElementById('weatherWidget_time').innerHTML = "更新时间  "+new Date().toLocaleTimeString().substr(0,5);
}
if(start!=-1){
	var first=start+parseInt(3);
	call=url.substring(first,end);
	returndata(call);
}else{
	var cookie_info= getCookie('newcity1');
	if(!cookie_info){
		var js = document.createElement("script"); 
		js.setAttribute("type", "text/javascript");
		js.setAttribute("src",rs);
		document.body.insertBefore(js, null);
		function id_callback(){
			std = id;
			if(typeof(id)=="undefined")
			{
				id1="101010100";
				returndata(id1);
			}else{
				id1=std;
				time=new Date();
				time.setTime(time.getTime()+365*24*60*60*1000*10);
				date=time.toGMTString();
				document.cookie = "newcity1=" + escape(std)+ ";expires="+date;
				returndata(std);
			}
		}
	}else{
		id1=cookie_info;
		returndata(id1);
	}
}
var vjAcc="",
	wrUrl="http://c.wrating.com/",//星期
	wrSv=0;
var vjTrack = function(C){
	var B = vjValidateTrack();
	if(B===false){return }
	var A=wrUrl+"a.gif"+vjGetTrackImgUrl(C);
	document.write('<div style="display:none"><img src="'+A+'" id="wrTagImage" width="1" height="1"/></div>');
	vjSurveyCheck()
}
var vjEventTrack = function(D){
	var C=vjValidateTrack();
	if(C===false){return }
	var B=wrUrl+"a.gif"+vjGetTrackImgUrl(D);
	var A=new Image();
	A.src=B;
	A.onload=function(){}
}
var vjValidateTrack = function(){
	if(document.location.protocol=="file:"){return false}
	if(vjAcc==""){
		return false
	}else{
		if(wrUrl.substr(wrUrl.length-1,1)!="/"){wrUrl+="/"}
	}
	return true
}
function vjGetTrackImgUrl(S){
	var M=0;
	var N="expires=Fri, 1 Jan 2038 00:00:00 GMT;";
	var T=document.location;
	var P=document.referrer.toString();
	var D;var H=vjGetDomainFromUrl(T);
	var K;var V;var Y="";var L=vjFlash();
	var G="";var Z="";var J="";
	var O=navigator.appName+" "+navigator.appVersion;
	var F=new Date();var X=F.getTimezoneOffset()/-60;
	var A=0;var U="";var R="";
	if(typeof (H[1])!="undefined"){V=H[1]}else{if(typeof (H[0])!="undefined"){V=H[0]}}
	if(P!=""){
		Y=vjGetKeyword(P)
	}else{
		if((O.indexOf("MSIE")>=0)&&(parseInt(O.substr(O.indexOf("MSIE")+5),4)>=5)&&(O.indexOf("Mac")==-1)&&(navigator.userAgent.indexOf("Opera")==-1)){
			try{
				document.documentElement.addBehavior("#default#homePage");
				if(document.documentElement.isHomePage(location.href)){P="ishomepage"}
			}catch(W){}
		}
	}
	if(navigator.cookieEnabled){M=1}
	if(self.screen){
		G=screen.width+"x"+screen.height+"x"+screen.colorDepth
	}else{
		if(self.java){
		var Q=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			G=Q.width+"x"+Q.height+"x0"
		}
	}
	if(navigator.language){
		Z=navigator.language.toLowerCase()
	}else{
		if(navigator.browserLanguage){
			Z=navigator.browserLanguage.toLowerCase()
		}else{
			Z="-"
		}
	}
	if(navigator.javaEnabled()){A=1}
	if(M==1){
		D=document.cookie;
		if(D.indexOf("vjuids=")<0){
			K=vjVisitorID();
			document.cookie="vjuids="+escape(K)+";"+N+";domain="+V+";path=/;"
		}else{
			K=vjGetCookie("vjuids")
		}
		if(D.indexOf("vjlast=")<0){
			U="30";
			var E=vjGetTimestamp(F.getTime()).toString();
			R=E+"."+E+".30"
		}else{
			var a=vjGetCookie("vjlast");
			var C=a.split(".");
			var B="";
			if(typeof (C[0])!="undefined"){
				R=C[0].toString()
			}else{
				R=vjGetTimestamp(F.getTime()).toString()
			}
			if(typeof (C[1])!="undefined"){
				var I=new Date(parseInt(C[1])*1000);
				if(I.toDateString()!=F.toDateString()){
					R+="."+vjGetTimestamp(F.getTime()).toString();
					if(parseInt(vjGetTimestamp(F.getTime())-parseInt(C[1]))/86400>30){U="2"}else{U="1"}
					if(typeof (C[2])!="undefined"){U+=C[2].substr(0,1)}else{U+="0"}
				}else{
					R+="."+C[1].toString();
					if(typeof (C[2])!="undefined"){U+=C[2]}else{U="10"}
				}
			}else{
				R+="."+vjGetTimestamp(F.getTime()).toString();
				if(typeof (C[2])!="undefined"){U+=C[2]}else{U="10"}
			}
			R+="."+U
		}
		document.cookie="vjlast="+R+";"+N+";domain="+V+";path=/;"
	}
	J="?a="+F.getTime().toString(16)+"&t=&i="+escape(K);
	J+="&b="+escape(T)+"&c="+vjAcc;J+="&s="+G+"&l="+Z;J+="&z="+X+"&j="+A+"&f="+escape(L);
	if(P!=""){J+="&r="+escape(P)+"&kw="+Y}
	J+="&ut="+U+"&n=";
	if(typeof (S)=="undefined"){J+="&js="}else{J+="&js="+escape(S)}
	J+="&ck="+M;return J
}
function vjGetTimestamp(A){
	return Math.round(A/1000)
}
function vjGetKeyword(C){
	var A=[["baidu","wd"],["baidu","q1"],["google","q"],["google","as_q"],["yahoo","p"],["msn","q"],["live","q"],["sogou","query"],["youdao","q"],["soso","w"],["zhongsou","w"],["zhongsou","w1"]];
	var B=vjGetDomainFromUrl(C.toString().toLowerCase());
	var D=-1;var E="";
	if(typeof (B[0])=="undefined"){return""}
	for(i=0;i<A.length;i++){
		if(B[0].indexOf("."+A[i][0]+".")>=0){
			D=-1;D=C.indexOf("&"+A[i][1]+"=");
			if(D<0){D=C.indexOf("?"+A[i][1]+"=")}
			if(D>=0){
				E=C.substr(D+A[i][1].length+2,C.length-(D+A[i][1].length+2));
				D=E.indexOf("&");
				if(D>=0){E=E.substr(0,D)}
				if(E==""){return""}else{return A[i][0]+"|"+E}
			}
		}
	}
	return""
}
function vjGetDomainFromUrl(E){
	if(E==""){return false}
	E=E.toString().toLowerCase();
	var F=[];
	var C=E.indexOf("//")+2;
	var B=E.substr(C,E.length-C);
	var A=B.indexOf("/");
	if(A>=0){F[0]=B.substr(0,A)}else{F[0]=B}
	var D=F[0].match(/[^.]+\.(com.cn|net.cn|gov.cn|cn|com|net|org|gov|cc|biz|info)+$/);
	if(D){if(typeof (D[0])!="undefined"){F[1]=D[0]}}
	return F
}
function vjVisitorID(){
	var A=vjHash(document.location+document.cookie+document.referrer).toString(16);
	var B=new Date();return A+"."+B.getTime().toString(16)+"."+Math.random().toString(16)
}
function vjHash(C){
	if(!C||C==""){return 0}
	var B=0;
	for(var A=C.length-1;A>=0;A--){
		var D=parseInt(C.charCodeAt(A));B=(B<<5)+B+D
	}
	return B
}
function vjGetCookie(D){
	var B=D+"=";var F=B.length;var A=document.cookie.length;var E=0;
	while(E<A){
		var C=E+F;
		if(document.cookie.substring(E,C)==B){return vjGetCookieVal(C)}
		E=document.cookie.indexOf(" ",E)+1;
		if(E==1){break}
	}
	return null
}
function vjGetCookieVal(B){
	var A=document.cookie.indexOf(";",B);
	if(A==-1){A=document.cookie.length}
	return unescape(document.cookie.substring(B,A))
}
function vjFlash(){
	var _flashVer="-";
	var _navigator=navigator;
	if(_navigator.plugins&&_navigator.plugins.length){
		for(var ii=0;ii<_navigator.plugins.length;ii++){
			if(_navigator.plugins[ii].name.indexOf("Shockwave Flash")!=-1){
				_flashVer=_navigator.plugins[ii].description.split("Shockwave Flash ")[1];
				break
			}
		}
	}else{
		if(window.ActiveXObject){
			for(var ii=10;ii>=2;ii--){
				try{
					var fl=eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash."+ii+"');");
					if(fl){_flashVer=ii+".0";break}
				}catch(e){}
			}
		}
	}
	return _flashVer
}
function vjSurveyCheck(){
	if(wrSv<=0){return }
	var C=new Date();
	var A=C.getTime();
	var D=Math.random(A);
	if(D<=parseFloat(1/wrSv)){
		var B=document.createElement("script");
		B.type="text/javascript";
		B.id="wratingSuevey";
		B.src="http://tongji.wrating.com/survey/check.php?c="+vjAcc;document.getElementsByTagName("head")[0].appendChild(B)
	}
};
var rndid = Math.floor(Math.random()*11+1); //1-10
if (rndid < 10){ rndid="0" + rndid; }
var vjAcc="860010-215101"+rndid+"00";
vjTrack("");