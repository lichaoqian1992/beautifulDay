/**
 * 页面初始加载
 */
var str="";//用于删除的时候保存ID组
//用于批量发送短信的组合
var roleType3 = "";
var roleValue3 = "";
var mobile3 = "";
$(function(){
    queryUser();
    getMessage();
});
//添加按钮
function add(){
    $("#userName").val("");
    $("#tel").val("");
    $(".tip").fadeIn(200);
}
/**
 * 获取选中的数据个数
 */
function selectCount(obj){
    if(obj.checked == true){
        obj.parentNode.parentNode.style.backgroundColor="rgb(14, 179, 245)";
    }else{
        obj.parentNode.parentNode.style.backgroundColor="white";
    }
    /*var j=0;
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    for(var   i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked == true){
                j++;
            }
        }
    }
    return j;*/
}
/**
 * 获取选中行的数据信息
 */
function getresource(){
    str="";
    roleType3 = "";
    roleValue3 = "";
    mobile3 = "";
    var str6="";
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    for(var i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked == true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                var str5="";
                for(var m=2;m<tds.length;m++){
                    str5 = str5 + tds[m].innerHTML + ",";//一行的数据
                }
                str6 += str5+";";
            }
        }
    }
    for(var n=0;n<str6.split(";").length-1;n++){
        str+=str6.split(";")[n].split(",")[0]+",";
        roleType3+=str6.split(";")[n].split(",")[2]+",";
        roleValue3+=str6.split(";")[n].split(",")[3]+",";
        mobile3+=str6.split(";")[n].split(",")[4]+",";
    }
}
//修改按钮
function modify(){
    var str4 = "";
    var   getCheckBox=document.getElementsByClassName('mycheckbox');
    var   j=0;
    for(var   i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked==true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                for(var m = 2;m < tds.length;m++){
                    str4 = str4 + tds[m].innerHTML + ",";
                }
                j++;
            }
        }
    }
    var myStr = str4.split(",");
    $("#userId2").val(myStr[0]);
    $("#userName2").val(myStr[1]);
    $("#roleType2").val(myStr[2]);
    $("#roleValue2").val(myStr[3]);
    $("#mobile2").val(myStr[4]);
    $("#createtime2").val(myStr[5]);
    $("#modifytime2").val(myStr[6]);
    if(j<=0){
        qikoo.dialog.alert("请选择要修改的数据");
        return false;
    }else if(j>1){
        qikoo.dialog.alert("只能选择一条数据，请重新选择");
        for(var   i=0;i<getCheckBox.length;i++){
            whichObj=getCheckBox[i];
            if(whichObj.type=="checkbox"){
                if(whichObj.checked==true){
                    whichObj.checked=false;
                    whichObj.parentNode.parentNode.style.backgroundColor="white";
                }
            }
        }
        return false;
    }else{
        $(".tip2").fadeIn(200);
        return true;
    }

}
//修改信息
function modifyInfo(){
    var userId = $("#userId2").val();
    var mobile = $("#mobile2").val();
    $.ajax({
        type:"GET",
        url:"/systemPush/modifySystemInfo",
        dataType:"json",
        data:{
            userId:userId,
            mobile:mobile
        },success:function(data){
            if(data.description == "SUCCESS"){
                qikoo.dialog.alert("修改成功");
                $(".tip2").fadeOut(200);
                queryUser();
            }else{
                qikoo.dialog.alert("修改失败");
            }
        }
    });

}
//删除数据
function removeData(){
    var   getCheckBox=document.getElementsByClassName('mycheckbox');
    var   j=0;
    for(var   i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked==true){
                j++;
            }
        }
    }
    if(j<=0){
        qikoo.dialog.alert("请选择要删除的数据");
        return false;
    }else{
        $(".tip3").fadeIn(200);
        return true;
    }

}
function removeInfo(){
    getresource();
    $(".tip3").fadeOut(200);
    $.ajax({
        type:"GET",
        url:"/systemPush/deleteSystemInfo",
        dataType:"json",
        data:{
            userIdList:str,
        },success:function(data){
            if(data.description == "SUCCESS"){
                qikoo.dialog.alert("删除成功");
                $(".tip3").fadeOut(200);
                queryUser();
            }else{
                qikoo.dialog.alert("删除失败");
            }
        }

    });
}
//查询信息
function queryUser(){
    var txt="";
    $.ajax({
        type:"GET",
        url:"/systemPush/getSystemInfo",
        dataType:"json",
        success:function(data){
            var length = data.resultList.length;
            for(var i=0;i<length;i++){
                txt += "<tr><td><input  class='mycheckbox' type='checkbox' onclick='selectCount(this)'/></td><td>"
                    +(i+1)+
                    "</td><td id='userid'>"
                    +data.resultList[i].userId+
                    "</td><td id='username'>"
                    +data.resultList[i].userName+
                    "</td><td id='roletype'>"
                    +data.resultList[i].roleType+
                    "</td><td id='rolevalue'>"
                    +data.resultList[i].roleValue+
                    "</td><td id='mobile'>"
                    +data.resultList[i].mobile+
                    "</td><td id='createtime'>"
                    +data.resultList[i].createTime+
                    "</td><td id='modifytime'>"
                    +data.resultList[i].modifyTime+
                    "</td></tr>"
            }
            $("#myBody").empty();
            $("#myBody").html(txt);
        }
    });
}
/**
 * 添加用户
 */
function save(){
    var userName = $("#userName").val();
    var mobile = $("#tel").val();
    if(userName == "" || mobile == ""){
        qikoo.dialog.alert("用户名和电话号码不能为空");
        return false;
    }
    $(".tip").fadeOut(200);
    $.ajax({
        type:"GET",
        url:"/systemPush/saveSystemInfo",
        dataType:"json",
        data:{
            userName:userName,
            mobile:mobile
        },success:function(data){
            $("#message").empty();
            if(data.description == "SUCCESS"){
                queryUser();
            }else if(data.description == "EXIST"){
                qikoo.dialog.alert("电话号码已存在，请重新添加");

            }else if(data.description == "NOT EXIST"){
                qikoo.dialog.alert("用户错误，请检查");

            }else if(data.description == "ERROR"){
                qikoo.dialog.alert("出错了，请检查");

            }
        }
    });
}
function getMessage(){
    $.ajax({
        type:"get",
        url:"/systemPush/showMessage",
        dataType:"json",
        success:function(data){
            $("#dayMessage").val(data.description);
            console.log(data.description);
        }
    });
}
/**
 * 发送短信
 */
function sendMessage(){
    var   getCheckBox=document.getElementsByClassName('mycheckbox');
    var   j=0;
    for(var   i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked==true){
                j++;
            }
        }
    }
    if(j<=0){
        qikoo.dialog.alert("请选择要发送的对象");
        return false;
    }else{
        getresource();
        $.ajax({
            type:"GET",
            url:"/systemPush/sendMessage",
            dataType:"json",
            data:{
                userIdList:str,
                userRoleType:roleType3,
                userRoleValue:roleValue3,
                mobile:mobile3,
                content:$("#dayMessage").val()
            },success:function(data){
                $("#message").empty();
                if(data.description == "SUCCESS"){
                    qikoo.dialog.alert("消息发送成功");
                }
            }

        });
    }
}
//全选
function selectAll(){
    str="";
    roleType3 = "";
    roleValue3 = "";
    mobile3 = "";
    var str6="";
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    var getAllCheckBox = document.getElementsByName('checkAll');
    for(var   i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(getAllCheckBox[0].checked == true){
                whichObj.checked = true;

                var str5 = "";
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                for(var m = 2;m < tds.length;m++){
                    str5 = str5 + tds[m].innerHTML + ",";//一行的数据
                }
                str6 += str5+";";
            }else{
                whichObj.checked = false;
            }
        }
    }
    for(var n=0;n<str6.split(";").length-1;n++){
        str+=str6.split(";")[n].split(",")[0]+",";
        roleType3+=str6.split(";")[n].split(",")[2]+",";
        roleValue3+=str6.split(";")[n].split(",")[3]+",";
        mobile3+=str6.split(";")[n].split(",")[4]+",";
    }
}