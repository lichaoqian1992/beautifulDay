 document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e.keyCode==13){ // enter 键
                //要做的事情
            	$(".kf_tipname").css("display","none");
        		$(".kf_name").removeClass("kf_bordercolor");
        		
        		$(".kf_tippassword").css("display","none");
        		$(".kf_password").removeClass("kf_bordercolor");
        		
        		$(".kf_tipcode").css("display","none");
        		$(".kf_code").removeClass("kf_bordercolor");
        		
        		$(".kf_tipcode1").css("display","none");
        		$(".kf_code").removeClass("kf_bordercolor");
        		
        		$(".kf_tipcode2").css("display","none");
        		$(".kf_code").removeClass("kf_bordercolor");
        		if($("#username").val() == ""){
        			$(".kf_tipname").css("display","block");
        			$(".kf_name").addClass("kf_bordercolor");
        			return;
        		}
        		if($("#password").val() == ""){
        			$(".kf_tippassword").css("display","block");
        			$(".kf_password").addClass("kf_bordercolor");
        			return;
        		}
        		if($("#code").val() == ""){
        			$(".kf_tipcode").css("display","block");
        			$(".kf_code").addClass("kf_bordercolor");
        			return;
        		}
        		login();
            }
        };
        
      //登录实现
        function login(){
        	
        	var name = $("#username").val();
        	var password = $("#password").val();
        	var code = $("#code").val();
        	if(name == "" || password == "" || code == ""){
        		layer.msg('请填写所有信息！', {
		 			icon: 1,
		 			time: 1000
		 		});
        		return;
        	}
        	$.ajax({
        		
        		type:"get",
        		url:"/data/login",
        		dataType:"json",
        		data:{
        			name:name,
        			password:password,
        			code:code
        		},success:function(data){
        			console.log(data);
        			if(data.aa == "NOTEXIT"){
        				layer.msg('账号不存在！', {
        		 			icon: 1,
        		 			time: 1000
        		 		});
        			}else if(data.aa == "ERROR"){
        				layer.msg('密码错误！', {
        		 			icon: 1,
        		 			time: 1000
        		 		});
        			}else if(data.aa == "YZM"){
        				layer.msg('验证码错误！', {
        		 			icon: 1,
        		 			time: 1000
        		 		});
        			}else if(data.aa == "NOAUTH"){
        				layer.msg('该账号暂无权限！', {
        		 			icon: 1,
        		 			time: 1000
        		 		});
        			}else{
        				console.log(data.role);
                        console.log(data.menu);

                        window.location.href=data.menu[0].child[0].url;
        			}
        			
        		}
        		
        	});
        }
        
        
        
        
        
$(function(){
	/*if (getCookie("uname") != null){
		$(".kf_login img").attr("type",2);
		$(".kf_login img").attr("src","/data/static/images/icon/login_state2.png");//选中状态
	    $('#username').val(getCookie("uname"));
	    $('#password').val(getCookie("upwd"));
	}*/
	//createCode();
	//用户提示
	$(".kf_name .kf_input1").blur(function(){
		$(".kf_tipcode2").css("display","none");
		$(".kf_code").addClass("kf_bordercolor");
		var value = $(this).val();
		if(value == ''){
			$(".kf_tipname").css("display","block");
			$(".kf_name").addClass("kf_bordercolor");
		}else{
			$(".kf_tipname").css("display","none");
			$(".kf_name").removeClass("kf_bordercolor");
			
			$(".kf_tipcode2").css("display","none");
			$(".kf_code").removeClass("kf_bordercolor");
		}
	})
	$(".kf_name .kf_input1").focus(function(){
		$(".kf_tipcode2").css("display","none");
		$(".kf_code").addClass("kf_bordercolor");
		var value = $(this).val();
		if(value == ''){
			$(".kf_tipname").css("display","block");
			$(".kf_name").addClass("kf_bordercolor");
		}else{
			$(".kf_tipname").css("display","none");
			$(".kf_name").removeClass("kf_bordercolor");
			
			/*$(".kf_tipcode2").css("display","none");
			$(".kf_code").removeClass("kf_bordercolor");*/
		}
	})
	//密码提示
	$(".kf_password .kf_input1").blur(function(){
		$(".kf_tipcode2").css("display","none");
		$(".kf_code").addClass("kf_bordercolor");
		var value = $(this).val();
		if(value == ''){
			$(".kf_tippassword").css("display","block");
			$(".kf_password").addClass("kf_bordercolor");
		}else{
			$(".kf_tippassword").css("display","none");
			$(".kf_password").removeClass("kf_bordercolor");
		}
	})
	$(".kf_password .kf_input1").focus(function(){
		$(".kf_tipcode2").css("display","none");
		$(".kf_code").addClass("kf_bordercolor");
		var value = $(this).val();
		if(value == ''){
			$(".kf_tippassword").css("display","block");
			$(".kf_password").addClass("kf_bordercolor");
		}else{
			$(".kf_tippassword").css("display","none");
			$(".kf_password").removeClass("kf_bordercolor");
		}
	})
	$(".kf_code .kf_input2").blur(function(){
		$(".kf_tipcode1").css("display","none");
		$(".kf_tipcode2").css("display","none");
		$(".kf_code").addClass("kf_bordercolor");
		var value = $(this).val();
		if(value == ''){
			$(".kf_tipcode1").css("display","none");
			$(".kf_code").removeClass("kf_bordercolor");
			$(".kf_tipcode").css("display","block");
			$(".kf_code").addClass("kf_bordercolor");
		}else{
			$(".kf_tipcode").css("display","none");
			$(".kf_code").removeClass("kf_bordercolor");
		}
	})
	$(".kf_code .kf_input2").focus(function(){
		var value = $(this).val();
		$(".kf_tipcode1").css("display","none");
		$(".kf_tipcode2").css("display","none");
		$(".kf_tipcode3").css("display","none");
		$(".kf_tipcode4").css("display","none");
		$(".kf_code").addClass("kf_bordercolor");
		if(value == ''){
			$(".kf_tipcode").css("display","block");
			$(".kf_code").addClass("kf_bordercolor");
		}else{
			$(".kf_tipcode").css("display","none");
			$(".kf_code").removeClass("kf_bordercolor");
		}
	})	
	
	$(".kf_login img").click(function(){
		var type = $(".kf_login img").attr("type");
		if(type == "1" || type == "3" ){
			$(this).attr("type",2);
			$(this).attr("src","/data/static/images/icon/login_state2.png");//选中状态
			setCookie("uname", $("#username").val(), 60);
		    setCookie("upwd", $("#password").val(), 60);
		}else{
			$(this).attr("type",3);
			$(this).attr("src","/data/static/images/icon/login_state1.png");
		}
	});
	if($("#message").val() == "ERROR"){
		$(".kf_tipcode2").css("display","block");
		$(".kf_code").addClass("kf_bordercolor");
    }else if($("#message").val() == "NOTEXIT"){
    	$(".kf_tipcode3").css("display","block");
		$(".kf_code").addClass("kf_bordercolor");
    }else if($("#message").val() == "YZM"){
    	$(".kf_tipcode1").css("display","block");
		$(".kf_code").addClass("kf_bordercolor");
    }else if($("#message").val() == "NOTAUTH"){
    	$(".kf_tipcode4").css("display","block");
		$(".kf_code").addClass("kf_bordercolor");
    }
	$(".kf_login_btn").click(function(){
		$(".kf_tipname").css("display","none");
		$(".kf_name").removeClass("kf_bordercolor");
		
		$(".kf_tippassword").css("display","none");
		$(".kf_password").removeClass("kf_bordercolor");
		
		$(".kf_tipcode").css("display","none");
		$(".kf_code").removeClass("kf_bordercolor");
		
		$(".kf_tipcode1").css("display","none");
		$(".kf_code").removeClass("kf_bordercolor");
		
		$(".kf_tipcode2").css("display","none");
		$(".kf_code").removeClass("kf_bordercolor");
		$(".kf_tipcode3").css("display","none");
		$(".kf_code").removeClass("kf_bordercolor");
		if($("#username").val() == ""){
			$(".kf_tipname").css("display","block");
			$(".kf_name").addClass("kf_bordercolor");
			return;
		}
		if($("#password").val() == ""){
			$(".kf_tippassword").css("display","block");
			$(".kf_password").addClass("kf_bordercolor");
			return;
		}
		if($("#code").val() == ""){
			$(".kf_tipcode").css("display","block");
			$(".kf_code").addClass("kf_bordercolor");
			return;
		}
		/*if($("#code").val().toUpperCase() != code.toUpperCase()){
			$(".kf_tipcode1").css("display","block");
			$(".kf_code").addClass("kf_bordercolor");
			return;
		}*/
		$("#myForm").submit();
	});
	
})


var code;
function createCode() {
    code = "";
    var codeLength = 4; //验证码的长度
    var checkCode = document.getElementById("checkCode");
    var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
    'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
    for (var i = 0; i < codeLength; i++)
    {
        var charNum = Math.floor(Math.random() * 52);
        code += codeChars[charNum];
    }
    if (checkCode)
    {
        checkCode.className = "code";
        checkCode.innerHTML = code;
    }
}
function validateCode()
{
    var inputCode = document.getElementById("inputCode").value;
    if (inputCode.length <= 0)
    {
        alert("请输入验证码！");
    }
    else if (inputCode.toUpperCase() != code.toUpperCase())
    {
        alert("验证码输入有误！");
        createCode();
    }
    else
    {
        alert("验证码正确！");
    }       
} 
/**
 * 设置cookie
 * @param name
 * @param value
 */
function setCookie(name , value){
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
/**
 * 读取cookie
 * @param name
 * @returns
 */
function getCookie(name){
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg)) 
		return unescape(arr[2]);
	else 
		return null;
}
/**
 * 删除cookie
 * @param name
 */
function delCookie(name) {
	 var exp = new Date();
	 exp.setTime(exp.getTime() - 1);
	 var cval = getCookie(name);
	 if (cval != null) 
		 document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
function getYzm(){
	/*$.ajax({
		type:"get",
		url:"/data/img",
		dataType:"json",
		data:{
			name:$("#username").val(),
			password:$("#password").val()
		},
		success:function(data){
			window.location.href="/sheet/?name="+$("#username").val()+"&password="+$("#password").val();
		},error:function(data){
			window.location.href="/sheet/?name="+$("#username").val()+"&password="+$("#password").val();
		}
	});*/
	window.location.href="/data/index";
}
