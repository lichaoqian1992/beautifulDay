/**
 * 权限管理
 * Created by Administrator on 2017/3/11.
 */
var userId = "";
var roleId = "";
var userName = "";
$(function(){
    queryUserInfo();
    queryJurisdiction();
});
/**查询用户，职位信息*/
function queryUserInfo(){
    var txt = "";
    var userName = $("#checkPeople").val();
    $.ajax({
        type:"get",
        url:"/systemPush/queryUserAndRoleInfo",
        dataType:"json",
        success:function(data){
            console.log(data);
            $("#myBody").html("");
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    for(var i=0;i<data.resultList.length;i++){
                        txt += "<tr><td><input name='checkAll' type='checkbox' onclick='selectUser(this)'/></td><td>"
                            +(i+1)+
                            "</td><td id='userId3' style='display: none;'>"
                            +data.resultList[i].userId+
                            "</td><td id='userName3'>"
                            +data.resultList[i].userName+
                            "</td><td>"
                            +data.resultList[i].configName+
                            "</td><td style='word-break: break-all;'>"
                            +data.resultList[i].roleName+
                            "</td><td style='word-break: break-all;'>"
                            +data.resultList[i].roleDescription+
                            "</td></tr>"
                    }
                }
                $("#myBody").html(txt);
            }
        }
    });
}
/**查询页面信息*/
function queryJurisdiction(){
    var txt = "";
    $("#myBody1").html("");
    $.ajax({
        type:"get",
        url:"/systemPush/queryJurisdiction",
        dataType:"json",
        success:function(data){
            console.log(data);
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    for(var i=0;i<data.resultList.length;i++){
                        txt += "<tr><td><input name='checkRole' type='checkbox' onclick='selectRole(this)'/></td><td>"
                            +(i+1)+
                            "</td><td style='word-wrap:break-word;width: 300px;'>"
                            +data.resultList[i].pageUrl+
                            "</td><td>"
                            +data.resultList[i].pageDescription+
                            "</td><td style='display: none;'>"
                            +data.resultList[i].roleId+
                            "</td><td>"
                            +data.resultList[i].roleName+
                            "</td><td>"
                            +data.resultList[i].desciption+
                            "</td><td>"
                            +data.resultList[i].createtime+
                            "</td></tr>"
                    }
                }
                $("#myBody1").html(txt);
            }
        }

    });
}
/**设置单选选择用户信息*/
function selectUser(obj){
    userId = $(obj).parent().parent().find("#userId3").text();
    userName = $(obj).parent().parent().find("#userName3").text();
    var getOneCheckBox = document.getElementsByName('checkAll');
    if(obj.checked == true){
        for(var i=0;i<getOneCheckBox.length;i++){
            whichObj=getOneCheckBox[i];
            if(whichObj.type=="checkbox"){
                whichObj.checked = false;
                whichObj.parentNode.parentNode.style.backgroundColor="white";
            }
        }
    }
    obj.checked = true;
    //给选中的tr一个颜色标识
    if(obj.checked == true){
        obj.parentNode.parentNode.style.backgroundColor="rgb(14, 179, 245)";
    }
}
function selectRole(obj){
    if(obj.checked == true){
        obj.parentNode.parentNode.style.backgroundColor="rgb(14, 179, 245)";
    }else{
        obj.parentNode.parentNode.style.backgroundColor="white";
    }
}
/**
 * 获取选中的数据个数
 */
function selectUserCount(){
    var j=0;
    var getCheckBox=document.getElementsByName('checkAll');
    for(var i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked == true){
                j++;
            }
        }
    }
    return j;
}
/**
 * 获取选中的数据个数
 */
function selectRoleCount(){
    roleId = "";
    var getOneCheckBox = document.getElementsByName('checkRole');
    for(var i=0;i<getOneCheckBox.length;i++){
        whichObj=getOneCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked == true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                roleId += tds[4].innerHTML+",";
            }
        }
    }
}
/**绑定用户和页面*/
function Binding(){
    selectRoleCount();
    if(selectUserCount() == 0){
        qikoo.dialog.alert("请选择用户");
        return;
    }
    if(roleId == "" || null == roleId){
        qikoo.dialog.alert("请选择要分配的权限");
        return;
    }
    //绑定权限
    $.ajax({
        type:"get",
        url:"/systemPush/binding",
        dataType:"json",
        data:{
            userId:userId,
            userName:userName,
            roleId:roleId,
            creater:$("#checkPeople").val()
        },success:function(data){
            if(data.description == "SUCCESS"){
                queryUserInfo();
                queryJurisdiction();
                qikoo.dialog.alert("绑定成功");
            }else if(data.description == "EXIT"){
                qikoo.dialog.alert("绑定失败，权限已存在");
            }else if(data.description == "ERROR"){
                qikoo.dialog.alert("绑定失败，参数错误");
            }
        }
    });
}
/**
 * 解除绑定
 */
function unBinding(){
    selectRoleCount();
    if(selectUserCount() == 0){
        qikoo.dialog.alert("请选择用户");
        return;
    }
    if(roleId == "" || null == roleId){
        qikoo.dialog.alert("请选择要解除的权限");
        return;
    }
    $.ajax({
        type:"get",
        url:"/systemPush/unBinding",
        dataType:"json",
        data:{
            userId:userId,
            userName:userName,
            roleId:roleId,
            creater:$("#checkPeople").val()
        },success:function(data){
            if(data.description == "SUCCESS"){
                queryUserInfo();
                queryJurisdiction();
                qikoo.dialog.alert("解绑成功");
            }else if(data.description == "NOTEXIT"){
                qikoo.dialog.alert("解绑失败，权限不存在");
            }else if(data.description == "ERROR"){
                qikoo.dialog.alert("解绑失败，参数错误");
            }
        }
    });

}
