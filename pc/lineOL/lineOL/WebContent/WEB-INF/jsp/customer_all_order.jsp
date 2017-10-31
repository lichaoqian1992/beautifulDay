<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/frozen.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/appstyle.css">
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
      <i class="ui-icon-return" onclick="closePage()"></i><h1>所有预约 </h1>
    </header>
    <section class="ui-container">
      <table id="bookList" class="ui-table ui-border">
        <thead>
          <tr><th>店铺名</th><th>队列号</th><th>等待人数</th></tr>
        </thead>
        <tbody>
          
        </tbody>
      </table>
    </section>
  </section>
  
  
  <script type="text/javascript" src="${ctx}/js/zepto.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/frozen.js"></script>
  <script type="text/javascript">
    $(function(){
      clearVal();
      
    });

   
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
    fetchData();
    function fetchData(){
      $.ajax({
        type: 'GET',
        data: {},
        url: '/lineOL/customer/queryUserQueueAllInfo/${sessionId}',
        success: function(data){
          if(data.code == 'SUCCESS'){
            var list = data.resultList;
            var box = $("#bookList tbody");
            box.empty();
            console.log(list)
            if(list.length > 0){
            	for(var i = 0; i < list.length; i++){
                    
                    var item = '<tr>'+
                                  '<td>'+list[i].shopName+'</td>'+
                                  '<td>'+list[i].typeAs + list[i].number+'</td>'+
                                  '<td>'+list[i].currentPosition+'人</td>'+
                                '</tr>';
                    box.append(item);
                  }
            }else{
            	$("#bookList thead").empty();
            	var item = '<p class="ui-txt-highlight text-center" style="padding: 100px 0;">没有预约信息</p>';
            	box.append(item);
            }
          }
        }
      });
    }
   
    
  </script>
</body>
</html>