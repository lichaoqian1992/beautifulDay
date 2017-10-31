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
/**
 * 关闭页面
 */			
function closePage() {
	var jsonstr = '{"action":"close"}';
	console.log(jsonstr);
	if (browser.versions.ios ||
		browser.versions.iPhone ||
		browser.versions.iPad) {
		// alert("");
		window.webkit.messageHandlers.Native.postMessage(jsonstr);
	}
	if (browser.versions.android) {
		// alert("");
		window.android.clickOnAndroid(jsonstr);
	}
}