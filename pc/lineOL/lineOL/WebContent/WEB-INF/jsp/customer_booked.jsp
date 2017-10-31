<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href="../../../css/frozen.css">
  <link rel="stylesheet" type="text/css" href="../../../css/appstyle.css">
</head>
<body>
<script type="text/javascript">
        var browser = { 
           versions: function() { 
               var u = navigator.userAgent; 
               return { 
                   trident: u.indexOf('Trident') > -1, //IE内核 
                   presto: u.indexOf('Presto') > -1, //opera内核 
                   webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核 
                   gecko: u.indexOf('Firefox') > -1, //火狐内核Gecko 
                   mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端 
                   ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios 
                   android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android 
                   iPhone: u.indexOf('iPhone') > -1 , //iPhone 
                   iPad: u.indexOf('iPad') > -1, //iPad 
                   webApp: u.indexOf('Safari') > -1 //Safari 
               }; 
           }() 
        } 
        function closePage(){
             var jsonstr = "{\"action\":\"close\"}";
            if ( browser.versions.ios 
               || browser.versions.iPhone 
               || browser.versions.iPad) { 
               window.webkit.messageHandlers.Native.postMessage(jsonstr);
            }
            if ( browser.versions.android ) { 
                window.paySucess.clickOnAndroid(jsonstr);
            }
             
         }
</script>
  <section id="userInfo" class="ui-container">
    <header class="ui-header ui-header-positive ui-border-b">
      <i class="ui-icon-return" onclick="closePage()"></i><h1>排队信息 </h1>
    </header>
    <section class="ui-container" style="background-color: #FFF; padding: 15px 0;">
      <div class="ui-whitespace">
      
        <p class="ui-txt-default">${customerQueueInfo.userName}</p>
        <p class="ui-txt-default">您当前的排队号是<span>${customerQueueInfo.no}</span>号</p>
        <p class="ui-txt-default">在您前面还有<span>${customerQueueInfo.myCurrentSize}</span>位用户</p>
        <p class="ui-txt-default">请耐心等待。</p>
      </div>
      <div class="ui-btn-wrap">
        <button class="ui-btn-lg ui-btn-danger" id="cancelBook">
          取消预约
        </button>
      </div>
    </section>
  <script type="text/javascript" src="../../../js/zepto.min.js"></script>
  <script type="text/javascript" src="../../../js/frozen.js"></script>
  <script>
  $("#cancelBook").tap(function(){
	   $.ajax({
		   type: 'GET',
	          data: {},
	          url: '/lineOL/customer/customerCancelSubscribe/${shopId}/${customerQueueInfo.typeAs}/${customerQueueInfo.userName}',
	          success: function(data){
	        	  var dia=$.dialog({
                 title:'温馨提示',
                 content:data.description,
                 button:["确认"]
             });
	        	  setTimeout(function(){
                window.location.href = '/lineOL/customer/customerQuery/${sessionId}/${shopId}'
              },1000);
	          }
	   });
  });
  </script>
</body>
</html>