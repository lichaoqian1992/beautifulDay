/**
 * 页面初始加载
 */
var str="";//保存同意和拒绝的数据的充值单号
var type="";//保存审批的到底是同意还是拒绝
var userIdList="";//保存审批的充值用户
var moneyList="";//保存审批的充值金额
var roleTypeList="";//角色类型
var roleValueList="";//角色值
var rechargeTypeList="";//保存审批的充值类型
var faburen = "";//发布人
var transnList = "";//业务单号
var oprationType = "";//用于判断是撤销充值还是充值
$(function(){
    $(".select1").uedSelect({
        width : 345
    });
    $(".select2").uedSelect({
        width : 167
    });
    $(".select3").uedSelect({
        width : 100
    });
    $(".tiptop a").click(function(){
        $(".tip1").fadeOut(200);//提示框
        $(".tip3").fadeOut(200);
    });
    $(".sure").click(function(){
        $(".tip1").fadeOut(200);
    });
    $(".cancel").click(function(){
        $(".tip1").fadeOut(200);
        $(".tip3").fadeOut(200);
    });
    searchInfo(1);
})
/**
 * 获取选中的数据的信息
 */
function getInfoParam(){
    str="";//保存同意和拒绝的数据的充值单号
    userIdList="";//保存审批的充值用户
    moneyList="";//保存审批的充值金额
    roleTypeList="";//角色类型
    roleValueList="";//角色值
    rechargeTypeList="";//保存审批的充值类型
    transnList = "";//业务单号
    oprationType = "";//用于判断是撤销充值还是充值
    var getCheckBox=document.getElementsByClassName('mycheckbox');//得到checkBox
    for(var i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(getCheckBox[i].checked == true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                str += tds[2].innerHTML+",";
                transnList += tds[4].innerHTML+",";
                userIdList += tds[6].innerHTML+",";
                roleTypeList += tds[8].innerHTML+",";
                roleValueList += tds[9].innerHTML+",";
                moneyList += tds[10].innerHTML+",";
                rechargeTypeList += tds[13].innerHTML+",";
                oprationType += tds[14].innerHTML+",";
            }
        }
    }
}

/**
 * 提示信息
 * @param message
 */
function showMessage(message){
    $("#message").empty();
    $("#message").append(message);
    $(".tip1").fadeIn(200);
}
/**
 * 批量同意和拒绝的提示信息
 * @param message
 */
function showMessage2(str){
    $("#reasons").val("");
    $(".tip3").fadeIn(200);
    $("#czId").val(str);
}
/**
 * 比较日期的大小
 */
function checkDate(){
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    if(startTime != "" && endTime != ""){
        if(new Date(startTime).getTime() >= new Date(endTime).getTime()){
            qikoo.dialog.alert("开始日期不能大于结束日期");
            $("#startTime").val("");
            $("#endTime").val("");
            return;
        }
    }
}
/**
 * 同意或者拒绝是获得的充值单号
 */
function getId(){
    str = "";
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    for(var i=0;i<selectCount();i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked == true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                str += tds[2].innerHTML + ",";
            }
        }
    }
    return str;
}
/**
 * 判断选中的数据个数
 */
function selectCount(){
    var j=0;
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    for(var   i=0;i<getCheckBox.length;i++){
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
 * 全选
 */
function selectAll(){
    str = "";
    userIdList = "";
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    var getAllCheckBox = document.getElementsByName('checkAll');
    for(var   i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(getAllCheckBox[0].checked == true){
                whichObj.checked = true;
            }else{
                whichObj.checked = false;
            }
        }
    }
}
/**
 * 同意一个
 */
function agreeOne(obj){
    type = "";
    str="";//保存同意和拒绝的数据的充值单号
    userIdList="";//保存审批的充值用户
    moneyList="";//保存审批的充值金额
    roleTypeList="";//角色类型
    roleValueList="";//角色值
    rechargeTypeList="";//保存审批的充值类型
    transnList = "";//业务单号
    oprationType = "";//用于判断是撤销充值还是充值
    type = "同意";
    str = $(obj).parent().parent().find("#myId").text()+",";
    userIdList = $(obj).parent().parent().find("#myUserName").text()+",";
    rechargeTypeList = $(obj).parent().parent().find("#rechargeTypeList").text()+",";
    moneyList = $(obj).parent().parent().find("#moneyList").text()+",";
    roleTypeList += $(obj).parent().parent().find("#roleTypeList").text()+",";
    roleValueList += $(obj).parent().parent().find("#roleValueList").text()+",";
    transnList += $(obj).parent().parent().find("#transnList").text()+",";
    oprationType = $(obj).parent().parent().find("#oprationType").text()+",";
    var myStatus = $(obj).parent().parent().find("#myStatus").text();
    if(myStatus == "已拒绝" || myStatus == "已审核"){
        qikoo.dialog.alert("操作错误,"+myStatus);
        return false;
    }else{
        showMessage2(str);
    }

}
/**
 * 拒绝
 */
function refuseOne(obj){
    str = "";
    oprationType = "";
    type = "拒绝";
    str = $(obj).parent().parent().find("#myId").text()+",";//
    //userIdList = $(obj).parent().parent().find("#myUserName").text()+",";
    oprationType = $(obj).parent().parent().find("#oprationType").text()+",";
    var myStatus = $(obj).parent().parent().find("#myStatus").text();
    if(myStatus == "已拒绝" || myStatus == "已审核"){
        qikoo.dialog.alert("操作错误,"+myStatus);
        return false;
    }else{
        showMessage2(str);
    }
}
/**
 * 批量同意
 */
function agreeBctch(){
    type = "";
    type = "同意";
    if(selectCount() <=0){
        qikoo.dialog.alert("请选择要同意的数据");
        return false;
    }else{
        //调用赋值的方法
        getInfoParam();
        showMessage2(str);

    }
}
/**
 * 批量拒绝
 */
function refuseBctch(){
    type = "";
    type = "拒绝";
    if(selectCount() <=0){
        qikoo.dialog.alert("请选择要拒绝的数据");
        return false;
    }else{
        //调用赋值的方法
        getInfoParam();
        showMessage2(str);
    }
}
/**
 * 同意、拒绝确认操作
 */
function doSure(){
    var reason = $("#reasons").val();
    //alert(str+"==="+transnList+"==="+userIdList+"==="+roleTypeList+"==="+roleValueList+"==="+moneyList+"==="+rechargeTypeList+"==="+oprationType);
    $(".tip3").fadeOut(200);
        $.ajax({
            type:"GET",
            url:"/userInsideRecharge/examByAccountor",
            dataType:"json",
            data:{
                idList:str,
                agreeOrRefuse:type,
                reason:reason,
                creater:$("#checkPeople").val()
            },success:function(data){
                console.log(data);
                if(data.description == "SUCCESS"){
                    qikoo.dialog.alert(data.obj);
                    searchInfo(1);
                }else if(data.description == "FAIL"){
                    qikoo.dialog.alert(data.obj);
                }
            }
        });
}
/**
 * 点击选中checkBox
 */
function selectOne(obj){
    if(obj.checked == true){
        obj.parentNode.parentNode.style.backgroundColor="rgb(14, 179, 245)";
    }else{
        obj.parentNode.parentNode.style.backgroundColor="white";
    }
}
/**
 * 查询我的审批
 */
function searchInfo(clickPage){
    var txt = "";
    var czId = $("#rechargeId").val();
    var personRelease = $("#personRelease").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var checkStatus = $("#checkStatus").val();
    var checkPeople = $("#checkPeople").val();
    if(clickPage == undefined || null == clickPage || 0 == clickPage){
        clickPage = 1;
    }
    $.ajax({
        type:"GET",
        url:"/userInsideRecharge/queryInsideRechargeOrder",
        dataType:"json",
        data:{
            rechargeOrderNo:czId,
            peopleRelease:personRelease,
            checkPeople:checkPeople,
            startTime:startTime,
            endTime:endTime,
            checkStatus:checkStatus,
            pageNum:clickPage
        },success:function(data){
            if(data.description == "SUCCESS"){
                console.log(data);
                for(var i=0;i<data.resultList.length;i++){
                    state = data.resultList[i].status;
                    category = data.resultList[i].rechargeType;
                    checkStatus = data.resultList[i].checkStatus;
                    txt +="<tr><td><input class='mycheckbox' type='checkbox' value='' onclick='selectOne(this)'/></td><td>"
                        +(i+1)+
                        "</td><td id='myId'>"
                        +data.resultList[i].orderNo+
                        "</td><td style='display: none;'>"
                        +data.resultList[i].excelNo+
                        "</td><td id='transnList'>"
                        +data.resultList[i].tranSN+
                        "</td><td style='display: none;'>"
                        +data.resultList[i].excelName+
                        "</td><td id='myUserName' style='display: none;'>"
                        +data.resultList[i].userId+
                        "</td><td>"
                        +data.resultList[i].userKey+
                        "</td><td id='roleTypeList'>"
                        +data.resultList[i].roleType+
                        "</td><td id='roleValueList' style='display: none;'>"
                        +data.resultList[i].roleValue+
                        "</td><td id='moneyList'>"
                        +data.resultList[i].rechargeMoney+
                        "</td><td>"
                        +data.resultList[i].personRelease+
                        "</td><td>"
                        +data.resultList[i].personApproving+
                        "</td><td id='rechargeTypeList'>"
                        +category+
                        "</td><td id='oprationType'>"
                        +state+
                        "</td><td id='myStatus'>"
                        +checkStatus+
                        "</td><td>"
                        +data.resultList[i].createTime+
                        "</td><td>"
                        +data.resultList[i].approvalTime+
                        "</td><td>"
                        +data.resultList[i].checkTime+
                        "</td><td>"
                        +data.resultList[i].remark+
                        "</td><td><a href='#' onclick='agreeOne(this)' style='color: blue'>同意</a>&nbsp;<a href='#' onclick='refuseOne(this)' style='color: blue'>拒绝</a>"
                    "</td><tr>"
                }
                $("#myTbody").html(txt);
                fy(data.totalCount,clickPage,"fy","countNumber","dpage");
            }
        }
    });
}
/**
 * 显示我审批的充值订单
 */
function getDetailByPage(clickPage){
    var txt = "";
    if(clickPage == undefined || clickPage == null || "" == clickPage){
        clickPage = 1;
    }
    $("#myTable").html("");
    $.ajax({
        type:"get",
        url:"/userInsideRecharge/queryMyDealOrder",
        dataType:"json",
        data:{
            rechargeOrderNo:$("#rechargeId2").val(),
            checkPeople:$("#checkPeople").val(),
            startTime:$("#startTime2").val(),
            endTime:$("#endTime2").val(),
            checkStatus:$("#checkStatus").val(),
            pageNum:clickPage
        },success:function(data){
            console.log(data);
            if(data.description == "SUCCESS"){
                for(var i=0;i<data.resultList.length;i++){
                    state = data.resultList[i].status;
                    category = data.resultList[i].rechargeType;
                    checkStatus = data.resultList[i].checkStatus;
                    txt +="<tr><td>"
                        +(i+1)+
                        "</td><td id='myId'>"
                        +data.resultList[i].orderNo+
                        "</td><td style='display: none;'>"
                        +data.resultList[i].excelNo+
                        "</td><td id='transnList'>"
                        +data.resultList[i].tranSN+
                        "</td><td style='display: none;'>"
                        +data.resultList[i].excelName+
                        "</td><td id='myUserName' style='display: none;'>"
                        +data.resultList[i].userId+
                        "</td><td>"
                        +data.resultList[i].userKey+
                        "</td><td id='roleTypeList'>"
                        +data.resultList[i].roleType+
                        "</td><td id='roleValueList' style='display: none;'>"
                        +data.resultList[i].roleValue+
                        "</td><td id='moneyList'>"
                        +data.resultList[i].rechargeMoney+
                        "</td><td>"
                        +data.resultList[i].personRelease+
                        "</td><td>"
                        +data.resultList[i].personApproving+
                        "</td><td id='rechargeTypeList'>"
                        +category+
                        "</td><td id='oprationType'>"
                        +state+
                        "</td><td id='myStatus'>"
                        +checkStatus+
                        "</td><td>"
                        +data.resultList[i].createTime+
                        "</td><td>"
                        +data.resultList[i].approvalTime+
                        "</td><td>"
                        +data.resultList[i].checkTime+
                        "</td><td>"
                        +data.resultList[i].remark+
                    "</td><tr>"
                }
            }
            $("#myTable").html(txt);
            fy(data.totalCount,clickPage,"fy1","countNumber1","dpage1");
        }

    });

}
function reset() {
    $("#personRelease").val("");
    $("#rechargeId").val("");
    $("#startTime").val("");
    $("#endTime").val("");
    $("#checkStatus").val("");
}
function reset2() {
    $("#personRelease2").val("");
    $("#rechargeId2").val("");
    $("#startTime2").val("");
    $("#endTime2").val("");
    $("#checkStatus2").val("");
    $("#czStatus").val("");
}
