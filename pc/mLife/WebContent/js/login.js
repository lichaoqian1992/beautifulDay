//====================初始化验证表单====================
$(function () {
    $("body").keydown(function (e) {
        if (e.which == "13") {
            $("#btn").click();
        }
    });
    $("#password,#username,#captcha").focus(function () {
        $(".error").html("");
    });
    //提交表单
    $("#btn").click(function () {
        if ($.trim($("#username").val()) == "") {
            $(".error").html("温馨提示：请输入用户名/手机号/邮箱！");
            return false;
        }
        if ($.trim($("#password").val()) == "") {
            $(".error").html("温馨提示：请输入登录密码！");
            return false;
        }
        if ($.trim($("#captcha").val()) == "") {
            $(".error").html("温馨提示：请输入验证码！");
            return false;
        }
        else {
        	 $loading = $('.loading');
             $login = $('.nc-login-mode');
             $nc = $('.nc-login-api');
             $loading.css('display', 'block');
             $nc.css('display', 'none');
             $login.css('display', 'none');
        	$.ajax({
        		type:"post",
        		url:"login",
        	    data:$('#login_form').serialize(),   
        	    error: function(request) {
                    alert("Connection error");
                },
                success: function(msg) {
                	if(msg=='success'){
                        $result = $('#result').val();
                        if ($result != '') {
                            location.href = $result;
                        } else {
                            location.href = $("#login_form").attr("url");
                        }
                	}else{
                	setTimeout(function () { 
                        $(".error").html(msg);
                        
                        image();
                        
                        $loading.css('display', 'none');
                        $login.css('display', 'block');
                        $nc.css('display', 'block'); 
                        },800);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    var msg = "状态：" + textStatus + "；出错提示：" + errorThrown;
                    $(".error").html(msg);
                    return false;
                }
            });
        }
        
    });
});