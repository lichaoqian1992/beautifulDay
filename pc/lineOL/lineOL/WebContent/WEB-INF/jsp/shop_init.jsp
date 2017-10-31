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
  <link rel="stylesheet" type="text/css" href="../../css/frozen.css">
  <link rel="stylesheet" type="text/css" href="../../css/appstyle.css">
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
      <div class="ui-btn-wrap">
        <button id="onoff" class="ui-btn ui-btn-danger">
          开关
        </button>
        <a href="/lineOL/shop/shopQueueConfig/${shopId }" class="ui-btn ui-btn-danger addLine">
          配置
        </a>
      </div>
      <section>
      <c:if test="${shopQueueVo.status==0}">
          <table id="shopList" class="ui-table ui-border">
		        <thead>
		          <tr><th>名称</th><th>当前号码</th><th>等待人数</th><th></th></tr>
		        </thead>
		        <tbody>
		          
		        </tbody>
		      </table>
      </c:if>
      <c:if test="${shopQueueVo.status==1}">
        <p class="ui-txt-highlight text-center" style="padding: 100px 0;">服务开启没有配置信息</p>
      </c:if>
      <c:if test="${shopQueueVo.status==2}">
        <p class="ui-txt-highlight text-center" style="padding: 100px 0;">服务已关闭</p>
      </c:if>
      <c:if test="${shopQueueVo.status==3}">
        <p class="ui-txt-highlight text-center" style="padding: 100px 0;">服务已关闭且没有配置信息</p>
      </c:if>
      </section>
    </section>
  </section>
   <div class="ui-dialog" id="onoff-confire">
    <div class="ui-dialog-cnt">
      <div class="ui-dialog-bd">
        <div>
          <h4>温馨提示</h4>
          <div class="tipinfo">确认关闭吗？</div>
        </div>
      </div>
      <div class="ui-dialog-ft ui-btn-group">
        <button type="button" data-role="button" class="select" id="onoff-ok">确认</button>
        <button type="button" data-role="button" class="cancel">取消</button>
      </div>
    </div>
  </div>
      
  </section>
  <script type="text/javascript" src="../../js/zepto.min.js"></script>
  <script type="text/javascript" src="../../js/frozen.js"></script>
  <script type="text/javascript">
    $(function(){
      clearVal();
      var asnum = '';
      $('.updateLine').on('tap',function(){
        showInfo();
      });
      
      var status = ${shopQueueVo.status};
      if(status == 0 || status == 1){
    	  $("#onoff").text("关闭");
      }else{
    	  $("#onoff").text("开启");
      }
      $('.ui-dialog .cancel').tap(function(){
    	  $('.ui-dialog').removeClass('show');
      });
      
      $("#onoff").tap(function(){
    	  if(status == 0 || status == 1){
          $("#onoff-confire .tipinfo").text('确认关闭吗？');
        }else{
        	$("#onoff-confire .tipinfo").text('确认开启吗？');
        }
        $("#onoff-confire").addClass('show');
      });
      $("#onoff-ok").tap(function(){
    	  if(status == 0 || status == 1){
          onoff(1);
        }else{
          onoff(0);
        }
      })
      
      
      if(status == 0){
    	  fetchData();
      }
      
      $('#shopList tbody').on('tap','.call',function(){
    	  var typeAlias = $(this).siblings('.typeAlias').val();
    	  var oNum = $(this).parent().siblings('.asnumber');
    	  var idx = $(this).parent().parent().index();
    	  var obj = $(this).parent().parent().find('.peopleNum').first();
    	  console.log(obj)
    	  $.ajax({
    		  type: 'GET',
              data: {},
              url: '/lineOL/shop/nextNumber/${shopId}/'+typeAlias,
              success: function(data){
            	  console.log(data)
            	  
            	  if(data.obj.size == '1' && data.obj.userId == '0000'){
            		  var dia=$.dialog({
                      title:'温馨提示',
                      content:'没有预约用户了',
                      button:["确认"]
                  });
            		  oNum.first().text('无');
            		  console.log(oNum)
            	  }else if(data.obj.size > '1' && data.obj.userId == '0000'){
            		  var dia=$.dialog({
                      title:'温馨提示',
                      content:'呼叫失败，请再叫一次',
                      button:["确认"]
                  });
            		  oNum.first().text('无');
            	  }else{
            		  var dia=$.dialog({
                      title:'温馨提示',
                      content:typeAlias+data.obj.number+'号呼叫成功',
                      button:["确认"]
                  });
            		  oNum.first().text(typeAlias+data.obj.number);
            	  }
            	  upData(idx,obj);
              }
    	  });
      });
    });
    function onoff(serviceType){
    	var shopId = ${shopId};
        $.ajax({
          
          type: 'GET',
          data: {
            serviceType: serviceType,
            shopId: shopId
          },
          url: '/lineOL/shop/enableOrDisableQueueService/'+serviceType+'/'+shopId,
          success: function(data){
        	  var dia=$.dialog({
                  title:'温馨提示',
                  content:data.description,
                  button:["确认"]
              });
        	  setTimeout(function(){location.reload();},1000);
          }
        });
    }
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
    
    function fetchData(){
    	var shopId = ${shopId};
        $.ajax({
          
          type: 'GET',
          data: {
            shopId: shopId
          },
          url: '/lineOL/shop/queryShopQueueInfo/'+shopId,
          success: function(data){
        	  console.log(data)
            if(data.code == 'SUCCESS'){
            	  var data = data.resultList;
            	  var box = $("#shopList tbody");
            	  box.empty();
            	  for(var i = 0; i < data.length; i++){
            		  var item = '<tr><td>'+data[i].typeName+'</td><td class="asnumber">无</td><td class="peopleNum">'+data[i].totalCount+'</td><td><input type="hidden" class="typeAlias" value="'+data[i].typeAs+'"><button style="margin-top: 8px;" class="ui-btn-s ui-btn-danger call"> 叫号</button></td></tr>'
            		  box.append(item);
            	  }
            }
          }
        });
    }
    
    function upData(idx,obj){
        var shopId = ${shopId};
          $.ajax({
            
            type: 'GET',
            data: {
              shopId: shopId
            },
            url: '/lineOL/shop/queryShopQueueInfo/'+shopId,
            success: function(data){
              if(data.code == 'SUCCESS'){
                  var data = data.resultList;
                  
                  obj.text(data[idx].totalCount);
              }
            }
          });
      }
  
 

  </script>
</body>
</html>