<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <section id="main">
    <header class="ui-header ui-header-positive ui-border-b">
      <i class="ui-icon-return" onclick="closePage()"></i><h1>排队信息 </h1>
    </header>
    <section class="ui-container">
      <section>
      <c:if test="${shopQueueInfo.userShopQueueInfoVos == null}">
        <p class="ui-txt-highlight text-center" style="padding: 100px 0;">商家已关闭预定服务</p>
      </c:if>
      <c:if test="${shopQueueInfo.userShopQueueInfoVos != null}">
      <table id="shopList" class="ui-table ui-border">
        <thead>
          <tr><th>名称</th><th>排队人数</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${shopQueueInfo.userShopQueueInfoVos}" var="item">
          
          <tr>
            
            <td>${item.queueName}</td>
            <td>${item.totalCount}人排队</td>
            <td><button style="margin-top:8px;" class="ui-btn-s ui-btn-danger book"> 预约</button><input type="hidden" class="typeas" value="${item.queueTypeAs}"></td>
          </tr>
          
        </c:forEach>
        </tbody>
      </table>
      </c:if>
      </section>
    </section>
    <section id="booked" class="hide ui-containe">
      <p class="ui-txt-highlight text-center" style="padding: 100px 0;"><span id="uname"></span> 商家已经呼叫您去吃饭啦！</p>
      <p class="ui-txt-highlight text-center">请记住您的号码：<span id="num"></span></p>
    </section>
  </section>
  
  
  <script type="text/javascript" src="../../../js/zepto.min.js"></script>
  <script type="text/javascript" src="../../../js/frozen.js"></script>
  <script type="text/javascript">
    $(function(){
      clearVal();

      $('.updateLine').on('tap',function(){
        showInfo();
      });
      
      $("#shopList tbody").on('tap','.book',function(){
    	  var typeas = $(this).siblings('.typeas').val();
    	  $.ajax({
    		  type: 'GET',
 	        data: {},
 	        url: '/lineOL/customer/customerSubscribe/${shopId}/'+typeas+'/${shopQueueInfo.userName}',
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
    		  
    	  })
      });
    });

    function showInfo(){
      $('#userInfo').removeClass('hide');
      $('#userInfo').siblings('section').addClass('hide');
    }
    function showMain(){
      $('#main').removeClass('hide');
      $('#main').siblings('section').addClass('hide');
    }
    function clearVal(){
      $('.ui-icon-close').hide();
      $('.ui-icon-close').tap(function(){
        $(this).siblings('input').val(''); 
        $(this).hide();
      });
      $('input').blur(function(){
        $(this).siblings('.ui-icon-close').hide();
      });
      $('input').focus(function(){
        if($(this).val() != ''){
          $(this).siblings('.ui-icon-close').show();
        }else{
          $(this).siblings('.ui-icon-close').hide();
        }
      });
    }
    checkBook();
    function checkBook(){
    	$.ajax({
    		type: 'GET',
        data: {},
        url: '/lineOL/customer/queryUserWhetherBook/${shopId}/${shopQueueInfo.userName}',
        success: function(data){
        	console.log(data);
        	if(data.statusEnum == 'SUCCESS'){
        		$("#booked").removeClass("hide");
        		$("#booked").siblings('section').addClass("hide");
        		$("#num").text(data.obj.number);
        		$("#uname").text(data.obj.userId);
        	}
        }
    	});
    }
    
  </script>
</body>
</html>