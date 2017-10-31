/**
 * Created by luobairan on 2016/10/28.
 */
function subLogin() {
    var $username = $("#username").val();
    var $password = $("#password").val();


    if ($username.length > 0 && $password.length > 0) {
        $.ajax({
            url: "/loginSystem",
            type: "POST",
            data: {username: $username, password: $password},
            dataType: "json",
            cache:false,
            async:false,
            success: function (result) {
                if(result.statusEnum=="FAIL"){
                    $.messager.alert('系统消息','用户名或密码错误！','error');
                }else if(result.statusEnum=="SUCCESS"){
                    window.location="/home/main.html";
                }
            },
            fail: function (status) {

            }
        });


    }

}
