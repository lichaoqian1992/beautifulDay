<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="Keywords" content="" />
    <meta name="Description" content="" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <title>用户登录_满集网</title>
    <link rel="stylesheet" type="text/css" href="css/ui.base.css" />
    <link rel="stylesheet" type="text/css" href="css/ui.member.header.css" />
    <link rel="stylesheet" type="text/css" href="css/ui.member.login.css" />
    <link rel="stylesheet" type="text/css" href="css/ui.font-awesome.min.css" />
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <style type="text/css">
    .site-footer{font-size: 14px;line-height: 1.5em;z-index: 1;position: relative;}
    .site-footer .footer-service{padding: 15px 0 20px;border-bottom: 1px solid #dfdfdf;}
    .site-footer .footer-links{padding: 20px 0;border-bottom: 1px solid #dfdfdf;color: #6d6d6d;}
    .site-footer .footer-info{padding: 15px 0 15px;font-size: 12px;}
    .site-footer .list-service{margin: 0;padding: 0;list-style-type: none;}
    .site-footer .list-service li{float: left;width: 20%;}
    .site-footer .list-service li a{display: block;font-size: 12px;color: #333;background: url(images/footer-icon.png) no-repeat center 0;height: 35px;line-height: 35px;}
    .site-footer .list-service li a .iconfont{font-size: 30px;color: #6d6d6d;}
    .site-footer .list-service li a:hover .iconfont{color: #ff4a00;}
    .site-footer .list-service li a.ic1{background-position: 0 0;}
    .site-footer .list-service li a.ic2{background-position: -243px 0;}
    .site-footer .list-service li a.ic3{background-position: -489px 0;}
    .site-footer .list-service li a.ic4{background-position: -737px 0;}
    .site-footer .list-service li a.ic5{background-position: -984px 0;}
    .site-footer .list-service li a strong{display: block;font-size: 16px;font-weight: 400;color: #666;text-indent: 60px;}
    .site-footer .col-links{float: left;width: 190px;margin: 0;border-right: 1px solid #dfdfdf;text-align: center;}
    .site-footer .col-links dt{margin-bottom: 10px;font-size: 16px;}
    .site-footer .col-links dd{margin: 0 0 6px;}
    .site-footer .col-links dd a{color: #6d6d6d;font-size: 12px;}
    .site-footer .col-links dd a:hover{color: #ff4a00;}
    .site-footer .col-links-first{margin-left: 0;}
    .site-footer .col-contact{float: right;width: 250px;text-align: center;}
    .site-footer .col-contact p{margin: 0 0 15px;}
    .site-footer .col-contact p.phone{margin-bottom: 5px;font-size: 26px;line-height: 1;color: #E31939;}
    .site-footer .info-text{float: left;width: 100%;margin: 0 auto;text-align: center;margin-bottom: 10px;}
    .site-footer .info-text a:hover{color: #ff4a00;}
    .site-footer .info-text .nav_bottom a{padding: 0 8px;}
    .site-footer .info-text .logo-mini{float: left;width: 40px;height: 40px;margin-right: 15px;background: #ff4a00;text-align: center;}
    .site-footer .info-text .logo-mini i{font-size: 28px;color: #fff;}
    .site-footer .info-text .sep{margin: 0 3px;}
    .site-footer .info-text p{margin: 0;line-height: 25px;}
    .site-footer .info-links{clear: both;}
    .site-footer .info-links img{width: auto;height: 20px;}
    .title{font-size: 20px;font-weight: 500;padding-left: 10px; border-left:1px solid #ddd;vertical-align:middle;}
    .pngFix{-ms-interpolation-mode: bicubic;}
    .content{height: 500px;}
    .error{padding-left: 5px;height: 20px;color: #D64848;font-size: 13px;}
    .nc-login{height: 420px;}
    .loading{text-align: center;height: 420px;line-height: 420px;}
    .public-head-layout .site-logo img{ max-width:340px; max-height:50px;}
    .public-head-layout .site-logo{ width:500px;}
    .foot_icon{text-align:center;} 
    .foot_icon{height: 32px; line-height: 32px; text-align: center; margin-bottom: 20px;}
    .w1210{width: 1210px; margin: 0 auto;}
    .foot_icon a{display: inline-block; background: #e0e0e0; padding: 0 5px;}
    .foot_icon img{float: left;}
    </style>
    <script type="text/javascript">
        $(function () { $('input').placeholder(); });
    </script>
</head>
<% 

String flag = (String)session.getAttribute("flag")==null?"":(String)session.getAttribute("flag");
String name = "";
String password = "";
try{ 
    Cookie[] cookies=request.getCookies(); 
    if(cookies!=null){ 
	for(int i=0;i<cookies.length;i++){ 
		
	    if(cookies[i].getName().equals("cookie_user")){ 
	    	
		String value = 	cookies[i].getValue();
		if(value!=null&&!"".equals(value)){
			
		    name=cookies[i].getValue().split("-")[0]; 
		    
		    if(cookies[i].getValue().split("-")[1]!=null && !cookies[i].getValue().split("-")[1].equals("null")){
		    
		       password=cookies[i].getValue().split("-")[1]; 
	       }
         }
       } 
	request.setAttribute("name",name); 
	request.setAttribute("passward",password); 
	} 
	} 
}catch(Exception e){ 
e.printStackTrace(); 
} 
%> 

<body>
    <div class="header-wrap">
        <header class="public-head-layout wrapper">
            <h1 class="site-logo">
            <!--logo start-->
            
<a href="http://www.manjiwang.com/index.html"><img src="picture/logo1.png" width="319px" height="45px" /></a>

            <!--logo end-->
            
            <span class="title">欢迎登录满集生活</span></h1>
            <div class="nc-regist-now">
                <span class="avatar"><img src="picture/default_user_portrait.gif"></span> <span>
                    Hi，欢迎来到满集网<br>
                    还不是本站会员？立即<a title="" href="http://www.manjiwang.com/register/user_name.html" class="register">注册</a>
                </span>
            </div>
        </header>
    </div>
    <div class="content" style="background:url('images/login.jpg') no-repeat center;">
        <div class="nc-login-layout content" > 
            <div class="nc-login">
                <div class="arrow"></div>
                <div class="nc-login-mode" style="display:block;">
                    <ul class="tabs-nav">
                        <li>
                            <a href="#default" class="tabulous_active">
                                用户登录
                                <i></i>
                            </a>
                        </li>
                        <!--<li><a href="#mobile">手机动态码登录<i></i></a></li>-->
                        <span class="tabulousclear"></span>
                    </ul>
                    <div id="tabs_container" class="tabs-container" style="height: 268px;">
                        <div id="default" class="tabs-content" style="position: absolute; top: 0px;">
                            <form id="login_form" class="nc-login-form"  url="${ctx}/toMobile"  method="post">
                                <dl>
                                    <dt>账&nbsp;&nbsp;&nbsp;号：</dt>
                                    <dd>
                                        <input type="text" value="<%=name %>"     class="text" autocomplete="off" name="username" placeholder="用户名/邮箱/已验证手机" id="username" maxlength="30" style="color: rgb(187, 187, 187);"  />
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>密&nbsp;&nbsp;&nbsp;码：</dt>
                                    <dd>
                                        <input type="password" value="<%=password %>"  class="text" name="password" autocomplete="off" placeholder="6-20个大小写英文字母、符号或数字" maxlength="30"  id="password" style="color: rgb(187, 187, 187);" />
                                    </dd>
                                </dl>
                                <div class="code-div mt15">
                                    <dl>
                                        <dt>验证码：</dt>
                                        <dd>
                                            <input type="text" name="checkcode" autocomplete="off" class="text w100" placeholder="输入验证码" id="captcha" maxlength="4" style="color: rgb(187, 187, 187);" />
                                        </dd>
                                    </dl>
                                    <span>
                                        <img src="${ctx}/checkImg" name="codeimage" id="codeimage">
                                        <a class="makecode" href="javascript:void(0)" onclick="image()">看不清，换一张</a>
                                    </span>
                                </div>
                                <div class="handle-div">
                                    <span class="auto">
                                        <input type="checkbox" class="checkbox" name="flag" id="flag" value="1" <%if(flag!=null && flag.equals("1")){%> checked ; value ="1"; <%}else {%> value="0" <%;}%> />记住密码
                                        <em>请勿在公用电脑上使用</em>
                                    </span>
                                    <a class="forget" href="http://www.manjiwang.com/repassword.html">忘记密码？</a>
                                </div>
                                <input type="hidden" id="result" name="result" value="" />
                                <div class="submit-div">
                                    <input type="button" id="btn"  class="submit" value="登&nbsp;&nbsp;&nbsp;录" >
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="error"></div>
                </div>
                <div class="loading" style="display:none;">
                    <img src="picture/loading-0.gif" />
                </div>
                <input type="hidden" id="turl" value="/index.html" />
                
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--底部-->
    <script src="js/_footer.aspx" type="text/javascript"></script>
    <!--底部-->
    <script type="text/javascript">
    function image() {
    	$('#codeimage').attr('src','${ctx}/checkImg?time=' + Math.random());
	}
    
    </script>
</body>
</html>