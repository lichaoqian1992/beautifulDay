/**
 * 维护用户的信息
 * Created by liuyingjie on 2017/3/1.
 */
/**
 * 页面初始加载
 * 根据不同登录人的权限，加载相应的可用功能
 */
$(function(){
    if($("#checkPeople").val() != "admin"){
        $("#add").hide();
        $("#resetPassword").hide();
    }
    queryUserInfo();
    a();

});
function a(){
    $("#roleName").empty();
    $("#roleName2").empty();
    $.ajax({
        type:"get",
        url:"/systemPush/getRole",
        dataType:"json",
        success:function(data){
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    $("#roleName").append("<option value=''></option>");//entity 变量
                    $("#roleName2").append("<option value=''></option>");//entity 变量
                    for(var i=0;i<data.resultList.length;i++){
                        $("#roleName").append("<option value='"+data.resultList[i].id+"'>"+data.resultList[i].userName+"</option>");//entity 变量
                        $("#roleName2").append("<option value='"+data.resultList[i].id+"'>"+data.resultList[i].userName+"</option>");//entity 变量
                    }
                }
            }
        }
    });
}
/**选择一条数据*/
function selectOne(obj){
    //1.先移除所有tr的背景颜色
    var roleName = $(obj).parent().parent().find("#roleName3").text();

    var getCheckBox=document.getElementsByName('checkAll');
    for(var i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            whichObj.checked = false;
            whichObj.parentNode.parentNode.style.backgroundColor="white";
        }
    }
    $(obj).attr("checked", true);
    //给选中的tr一个颜色标识
    if(obj.checked == true){
        obj.parentNode.parentNode.style.backgroundColor="rgb(14, 179, 245)";
    }
    if($("#checkPeople").val() != $(obj).parent().parent().find("#userName2").text()){
        //只能修改用户的权限名称，其他的都不允许修改
        $("#newPassword").attr("disabled",true);
        $("#realName2").attr("disabled",true);
        $("#mobile2").attr("disabled",true);
        $("#email2").attr("disabled",true);
    }else if($("#checkPeople").val() == "admin"){
        //不允许修改自己的权限
        $("#newPassword").attr("disabled",false);
        $("#realName2").attr("disabled",false);
        $("#mobile2").attr("disabled",false);
        $("#email2").attr("disabled",false);
    }else{
        //不允许修改自己的权限
        $("#roleName2").attr("disabled",true);
        $("#newPassword").attr("disabled",false);
        $("#realName2").attr("disabled",false);
        $("#mobile2").attr("disabled",false);
        $("#email2").attr("disabled",false);
    }
    var xx = document.getElementById("roleName2");
    for(var i=0;i<xx.length;i++){
        if(xx[i].text == roleName)
            xx[i].selected = true;
    }
    $("#userId2").val($(obj).parent().parent().find("#userId3").text());
    $("#userName3").val($(obj).parent().parent().find("#userName2").text());
    $("#newPassword").val("");
    //$("#roleName2").text($(obj).parent().parent().find("#roleName3").text());
    $("#oldPassword").val($(obj).parent().parent().find("#newPassword3").text());
    $("#realName2").val($(obj).parent().parent().find("#realName3").text());
    $("#mobile2").val($(obj).parent().parent().find("#mobile3").text());
    $("#email2").val($(obj).parent().parent().find("#email3").text());
}
//添加按钮
function add(){

    $(".tip").fadeIn(200);
}
function modify(){
    if($("#userId2").val() == ""){
        qikoo.dialog.alert("请选择数据");
        return;
    }
    $(".tip2").fadeIn(200);
}
/**
 * 重置密码
 */
function resetPassword(){
    if($("#userId2").val() == ""){
        qikoo.dialog.alert("请选择数据");
        return;
    }
    $.ajax({
        type:"get",
        url:"/systemPush/resetUserPassword",
        dataType:"json",
        data:{
            id:$("#userId2").val(),
            creater:$("#checkPeople").val()
        },success:function(data){
            if(data.description == "SUCCESS"){
                qikoo.dialog.alert("重置成功");
                queryUserInfo();
            }else if(data.description == "ERROR"){
                qikoo.dialog.alert("参数错误");
            }
        }
    });
}
/**添加用户*/
function save(){
    var userName = $("#userName").val();
    var password = $("#passWord").val();
    var realName = $("#realName").val();
    var mobile = $("#mobile").val();
    var email = $("#email").val();
    var roleName =$("#roleName").val();
    if(userName == "" || password == "" || realName == "" || mobile == "" || email == "" || roleName == ""){
        qikoo.dialog.alert("请填写所有信息");
        return false;
    }
    $.ajax({
        type:"get",
        url:"/systemPush/addUser",
        dataType:"json",
        data:{
            userName:userName,
            password:password,
            realName:realName,
            mobile:mobile,
            email:email,
            roleName:roleName,
            creater:$("#checkPeople").val()
        },success:function(data){
            $(".tip").fadeOut(200);
            if(data.description == "SUCCESS"){
                qikoo.dialog.alert("添加成功");
                queryUserInfo();
            }else if(data.description == "EXIT"){
                qikoo.dialog.alert("用户已存在");
            }else if(data.description == "ERROR"){
                qikoo.dialog.alert("参数错误");
            }
        }
    });
}
/**修改用户信息*/
function modifyInfo(){
    var userId = $("#userId2").val();
    var password = $("#newPassword").val();
    var oldPassword = $("#oldPassword").val();
    var realName = $("#realName2").val();
    var mobile = $("#mobile2").val();
    var email = $("#email2").val();
    var roleName = $("#roleName2").val();
    if(realName == "" || mobile == "" || email == ""){
        qikoo.dialog.alert("真实姓名或电话号码或邮箱不能为空");
        return;
    }
    $.ajax({
        type:"get",
        url:"/systemPush/modifyUser",
        dataType:"json",
        data:{
            id:userId,
            password:password,
            realName:realName,
            mobile:mobile,
            email:email,
            roleName:roleName,
            creater:$("#checkPeople").val()
        },success:function(data){
            $(".tip2").fadeOut(200);
            if(data.description == "SUCCESS"){
                qikoo.dialog.alert("修改成功");
                queryUserInfo();
            }else if(data.description == "ERROR"){
                qikoo.dialog.alert("参数错误");
            }
        }
    });

}
function queryUserInfo(){
    var txt = "";
    var userName = $("#checkPeople").val();
    $.ajax({
        type:"get",
        url:"/systemPush/queryUserInfo",
        dataType:"json",
        data:{
            userName:userName
        },
        success:function(data){
            $("#myBody").html("");
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    for(var i=0;i<data.resultList.length;i++){
                        txt += "<tr><td><input name='checkAll' type='checkbox' onclick='selectOne(this)'/></td><td>"
                            +(i+1)+
                            "</td><td id='userId3'>"
                            +data.resultList[i].id+
                            "</td><td id='userName2'>"
                            +data.resultList[i].userName+
                            "</td><td id='roleName3'>"
                            +data.resultList[i].roleName+
                            "</td><td id='newPassword3'>"
                            +data.resultList[i].password+
                            "</td><td id='realName3'>"
                            +data.resultList[i].realName+
                            "</td><td id='mobile3'>"
                            +data.resultList[i].mobile+
                            "</td><td id='email3'>"
                            +data.resultList[i].email+
                            "</td><td>"
                            +data.resultList[i].loginTime+
                            "</td><td>"
                            +data.resultList[i].createTime+
                            "</td><td>"
                            +data.resultList[i].loginCount+
                            "</td></tr>"
                    }
                }
                $("#myBody").html(txt);
            }
        }
    });
}

