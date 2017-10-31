/**
 * Created by pudding on 2017-2-5.
 */
var str = "";
var type = "";
var userIdList = "";
var countNum = 0;
var oldTime = "";
var nowTime = "";
/**保存接口所需要的参数值*/
var orderNo = "";
var rechargeType = "";//充值类型
var userId = "";//充值用户Id
var roleType = "";//角色类型
var roleValue = "";//角色值
var rechargeTranSN = "";//业务单号
var rechargeMoney = 0;
$(function () {
    $("#win-wrapper").fadeIn();
    $(".select1").uedSelect({
        width : 300
    });
    $(".select2").uedSelect({
        width : 167
    });
    $(".select3").uedSelect({
        width : 100
    });
    $(".tiptop a").click(function(){
        $(".tip1").fadeOut(200);//提示框
        $(".tip2").fadeOut(200);
        $(".tip3").fadeOut(200);
        $(".tip4").fadeOut(200);
        $(".tip5").fadeOut(200);
        $(".tip6").fadeOut(200);
    });
    $(".sure").click(function(){
        $(".tip1").fadeOut(200);
    });
    $(".cancel").click(function(){
        $(".tip1").fadeOut(200);
        $(".tip2").fadeOut(200);
        $(".tip3").fadeOut(200);
        $(".tip4").fadeOut(200);
        $(".tip5").fadeOut(200);
        $(".tip6").fadeOut(200);
    });
    //发布充值
    $("#rechargePush").click(function(){
        if($("#oaNo").val() == "" || null == $("#oaNo").val()){
            qikoo.dialog.alert("OA文件编号不能为空");
            return;
        }
        if($("#excelName").val() == "" || null == $("#excelName").val()){
            qikoo.dialog.alert("Excel名称不能为空");
            return;
        }
        $("#upload").submit();
        //先判断OA文件编号是否正确
        /*$.ajax({
            type:"get",
            url:"/userRecharge/queryOaInfo",
            dataType:"json",
            data:{
                oaNo:$("#oaNo").val(),
                pageNum:1
            },success:function(data){
                if(data.description == "SUCCESS"){
                    if(data.totalCount<=0){
                        showMessage("OA文件编号不正确");
                    }else{
                        $("#upload").submit();
                    }
                }
            }
        });*/
    });
    $("#temporary").click(function(){
        var w1 = document.getElementById("w1");
        var w2 = document.getElementById("w2");
        w1.style.display = "none";
        w2.style.display = "block";
    });
    searchInfo(1);
})
/**
 * 提示信息
 * @param message
 */
function showMessage(message){
    $("#message").empty();
    $("#message").append(message);
    $(".tip1").fadeIn(200);
}
function showMessage3(message){
    $("#message2").empty();
    $("#message2").append(message);
    $(".tip2").fadeIn(200);
}
function showMessage5(message){
    $("#message5").empty();
    $("#message5").append(message);
    $(".tip5").fadeIn(200);
}
/**批量撤销弹出框*/
function showMessage4(str){
    $(".tip4").fadeIn(200);
    $("#cxId").val(str);
}
/**
 * 批量报批弹出框
 * @param message
 */
function showMessage2(str){
    $(".tip3").fadeIn(200);
    $("#czId").val(str);
}
/**
 * 获取选中的数据的信息
 */
function getInfoParam(){
    str="";//保存同意和拒绝的数据的充值单号
    /*userIdList="";//保存审批的充值用户
    moneyList="";//保存审批的充值金额
    roleTypeList="";//角色类型
    roleValueList="";//角色值
    rechargeTypeList="";//保存审批的充值类型
    transnList = "";//业务单号
    oprationType = "";//用于判断是撤销充值还是充值*/
    var getCheckBox=document.getElementsByClassName('mycheckbox');//得到checkBox
    for(var i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(getCheckBox[i].checked == true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                str += tds[2].innerHTML+",";
                /*transnList += tds[4].innerHTML+",";
                userIdList += tds[6].innerHTML+",";
                roleTypeList += tds[8].innerHTML+",";
                roleValueList += tds[9].innerHTML+",";
                moneyList += tds[10].innerHTML+",";
                rechargeTypeList += tds[13].innerHTML+",";
                oprationType += tds[14].innerHTML+",";*/
            }
        }
    }
}
/**
 * 批量报批
 */
function agreeBctch(){
    str = "";
    type = "";
    type = "同意";
    if(selectCount() <=0){
        qikoo.dialog.alert("请选择要报批的数据");
        return false;
    }else{
        showMessage2(getId());
    }
}
/**
 * 批量充值
 */
function rollBackBctch(){
    str = "";
    type = "";
    type = "同意";
    if(selectCount() <=0){
        qikoo.dialog.alert("请选择要充值的数据");
        return false;
    }else{
        showMessage4(getId());
    }
}
/**
 * 报批一个
 */
function agreeOne(obj){
    str = "";
    userIdList = "";
    str = $(obj).parent().parent().find("#myId").text()+",";
    userIdList = $(obj).parent().parent().find("#userIdList").text()+",";
    var check_status = $(obj).parent().parent().find("#myStatus").text();
    if($(obj).parent().parent().find("#state").text() == "充值成功"){
        qikoo.dialog.alert("报批错误，已经充值成功");
        return;
    }
    if(check_status == "待审核" || check_status == "已审核"){
        qikoo.dialog.alert("报批错误，审核状态错误");
        return;
    }
    $("#czId").val(str);
    $("#userId3").val(userIdList);
    showMessage3("确定报批吗？")
}
/**
 * 确认充值
 */
function rollbackOne(obj){
    rechargeType = "";//充值类型
    userId = "";//充值用户Id
    roleType = "";//角色类型
    roleValue = "";//角色值
    rechargeTranSN = "";//业务单号
    rechargeMoney = "";
    orderNo = "";
    orderNo = $(obj).parent().parent().find("#myId").text()+",";
    userId = $(obj).parent().parent().find("#userIdList").text()+",";
    roleType = $(obj).parent().parent().find("#roleType").text()+",";
    roleValue = $(obj).parent().parent().find("#roleValue").text()+",";
    rechargeTranSN = $(obj).parent().parent().find("#rechargeTranSN").text()+",";
    rechargeType = $(obj).parent().parent().find("#rechargeType").text()+",";
    rechargeMoney = $(obj).parent().parent().find("#rechargeMoney").text()+",";

    var check_status = $(obj).parent().parent().find("#myStatus").text();
    var state44 = $(obj).parent().parent().find("#state").text();
    if(check_status != "已审核"){
        qikoo.dialog.alert("充值错误，审批状态错误");
        return;
    }
    if(state44 == "充值成功"){
        qikoo.dialog.alert("充值错误，充值状态错误");
        return;
    }
    $("#czId").val(str);
    $("#userId3").val(userIdList);
    showMessage5("确定要充值吗？")
}
/**
 * 同意或者拒绝是获得的充值单号
 */
function getId(){
    str = "";
    userIdList = "";
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    for(var i=0;i<getCheckBox.length;i++){
         whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(whichObj.checked == true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                str += tds[2].innerHTML + ",";
                userIdList += tds[7].innerHTML + ",";
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
 * 单选
 */
function selectOne(obj){
    if(obj.checked == true){
        obj.parentNode.parentNode.style.backgroundColor="rgb(14, 179, 245)";
    }else{
        obj.parentNode.parentNode.style.backgroundColor="white";
    }
}
/**
 * 全选
 */
function selectAll(){
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    var getAllCheckBox = document.getElementsByName('checkAll');
    for(var i=0;i<getCheckBox.length;i++){
        whichObj1=getCheckBox[i];
        if(whichObj1.type=="checkbox"){
            if(getAllCheckBox[0].checked == true){
                whichObj1.checked = true;
                var tr = whichObj1.parentNode.parentNode;
                var tds = tr.cells;
                if(tds[16].innerHTML == "待审核"){
                    whichObj1.checked = false;
                }
            }else{
                whichObj1.checked = false;
            }
        }
    }
}
/**
 * 批量报批
 */
function doSure(){
    var flag = true;
    //先判断选中的数据的审批状态是否正确
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    for(var i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(getCheckBox[i].checked == true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                if(tds[16].innerHTML != "未审核" && tds[16].innerHTML != "已拒绝"){//只有未审核和已拒绝的数据可以报批
                    flag = false;
                }
                str += tds[2].innerHTML+",";
            }
        }
    }
    $(".tip3").fadeOut(200);
    if(flag){
        getId();
        checkApproval(userIdList);
    }else{
        qikoo.dialog.alert("所选数据审核状态错误");
        return;
    }
}
/**
 * 批量充值，只能充值审批状态是已审核的订单
 */
function doSure2(){
    rechargeType = "";//充值类型
    userId = "";//充值用户Id
    roleType = "";//角色类型
    roleValue = "";//角色值
    rechargeTranSN = "";//业务单号
    rechargeMoney = "";
    orderNo = "";
    var flag = true;
    //先判断选中的数据的审批状态是否正确
    var getCheckBox=document.getElementsByClassName('mycheckbox');
    for(var i=0;i<getCheckBox.length;i++){
        whichObj=getCheckBox[i];
        if(whichObj.type=="checkbox"){
            if(getCheckBox[i].checked == true){
                var tr = whichObj.parentNode.parentNode;
                var tds = tr.cells;
                if(tds[16].innerHTML != "已审核"){
                    flag = false;
                }else{
                    if(tds[15].innerHTML == "充值成功"){
                        flag = false;
                    }else{
                        orderNo += tds[2].innerHTML+",";
                        rechargeTranSN += tds[3].innerHTML+",";
                        userId += tds[7].innerHTML+",";
                        roleType += tds[9].innerHTML+",";
                        roleValue += tds[10].innerHTML+",";
                        rechargeMoney += tds[11].innerHTML+",";
                        rechargeType += tds[14].innerHTML+",";
                    }
                }
            }
        }
    }
    $(".tip4").fadeOut(200);
    if(flag){
        //撤销充值的逻辑
        chexiao();
    }else{
        qikoo.dialog.alert("订单审批状态错误");
        return;
    }
}
/**
 * 充值逻辑
 */
function chexiao(){
    $(".tip5").fadeOut();
    $("#win-wrapper").fadeIn();
    //alert(orderNo+"="+rechargeTranSN+"="+userId+"="+roleType+"="+roleValue+"="+rechargeMoney+"="+rechargeType);
    $.ajax({
        type:"GET",
        url:"/userInsideRecharge/examRechargeOrder",
        dataType:"json",
        data:{
            idList:orderNo,
            creater:$("#personRelease").val(),
            userIdList:userId,
            moneyList:rechargeMoney,
            roleTypeList:roleType,
            roleValueList:roleValue,
            rechargeTypeList:rechargeType
        },success:function(data){
            $("#win-wrapper").fadeOut();
            if(data.description == "SUCCESS"){
                qikoo.dialog.alert(data.obj);
                searchInfo(1);
            }
        }
    });


}
/**
 * 判断是否是密集充值
 */
function checkApproval(userIdList){
    $(".tip2").fadeOut(200);
    if(userIdList == "" || undefined == userIdList || null == userIdList){
        userIdList = $("#userId3").val();
    }
    var description = "";
    $.ajax({
        type:"GET",
        url:"/userRecharge/checkApproval",
        dataType:"json",
        data:{
            userIdList:userIdList
        },success:function(data){
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    for(var i=0;i<data.resultList.length;i++){
                        if(data.resultList[i].status == "ERROR"){
                            description += data.resultList[i].userId+",";
                        }
                    }
                    if(description.length>0){
                        qikoo.dialog.alert("用户"+description+"属于密集充值,请2小时后再试");
                        selectAll();
                    }else{
                        baopi();
                    }
                }else{
                    baopi();
                }
            }
        }
    });

}
/**
 * 报批
 */
function baopi(){
    //alert(str);
    $(".tip2").fadeOut(200);
    $.ajax({
        type:"GET",
        url:"/userRecharge/approvalOrder",
        dataType:"json",
        data:{
            idList:str,
            peopleRelease:$("#personRelease").val(),
            operationType:"报批充值"
        },success:function(data){
            if(data.description == "SUCCESS"){
                qikoo.dialog.alert("报批成功");
                searchInfo(1);
            }else if(data.description == "ERROR"){
                qikoo.dialog.alert("报批失败，当前操作属于密集充值");
            }
        }
    });
}
function getCount(){
    countNum = "";
    var checkStatus = "";
    var rechargeOrderNo = $("#rechargeOrderNo").val();
    var rechargeType = $("#rechargeType2").val();
    var status = $("#status").val();
    var checkStatus = $("#checkStatus").val();
    var checkPeople = $("#checkPeople").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    $.ajax({
        type:"GET",
        url:"/userRecharge/getCount",
        dataType:"json",
        data:{
            rechargeOrderNo:rechargeOrderNo,
            rechargeType:rechargeType,
            status:status,
            checkStatus:checkStatus,
            checkPeople:checkPeople,
            startTime:startTime,
            endTime:endTime,
            peopleRelease:$("#personRelease").val()
        },success:function(data){
            if(data.description == "SUCCESS"){
                countNum = data.obj.remark;
            }
        }
    });

}
/**
 * 查询我的充值订单
 */
function searchInfo(page){
    $("#win-wrapper").fadeIn();
    var myColor = "";
    var oa = "";
    var txt = "";
    var state = "";
    var category = "";
    var checkStatus = "";
    var rechargeOrderNo = $("#rechargeOrderNo").val();
    var rechargeType = $("#rechargeType2").val();
    var status = $("#status").val();
    var checkStatus = $("#checkStatus").val();
    var checkPeople = $("#checkPeople").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    $.ajax({
        type:"GET",
        url:"/userRecharge/getMyRecharge",
        dataType:"json",
        async: false,
        data:{
            rechargeOrderNo:rechargeOrderNo,
            rechargeType:rechargeType,
            status:status,
            checkStatus:checkStatus,
            checkPeople:checkPeople,
            startTime:startTime,
            endTime:endTime,
            pageNum:page,
            peopleRelease:$("#personRelease").val()
        },success:function(data){
            $("#win-wrapper").fadeOut();
            console.log(data);
            $("#myTbody").html("");
            if(data.description == "SUCCESS"){
                for(var i=0;i<data.resultList.length;i++){
                    state = data.resultList[i].status;
                    category = data.resultList[i].rechargeType;
                    checkStatus = data.resultList[i].checkStatus;
                    //判断充值状态，对应不同的颜色
                    if(state == "充值成功"){
                        myColor = "style='background-color:green'";
                    }else if(state == "撤销充值成功"){
                        myColor = "style='background-color:red'";
                    }
                    if(data.resultList[i].oaNo == 0){
                        oa = "无";
                    }else{
                        oa = data.resultList[i].oaNo;
                    }
                    txt +="<tr ondblclick='modifyInfo(this)'><td><input class='mycheckbox' type='checkbox' value='' onclick='selectOne(this)'/></td><td>"
                        +(i+1)+
                        "</td><td id='myId'>"
                        +data.resultList[i].orderNo+
                        "</td><td style='display: none;'>"
                        +data.resultList[i].excelNo+
                        "</td><td id='rechargeTranSN'>"
                        +data.resultList[i].tranSN+
                        "</td><td>"
                        +oa+
                        "</td><td style='display: none;'>"
                        +data.resultList[i].excelName+
                        "</td><td id='userIdList' style='display: none;'>"
                        +data.resultList[i].userId+
                        "</td><td>"
                        +data.resultList[i].userKey+
                        "</td><td id='roleType'>"
                        +data.resultList[i].roleType+
                        "</td><td style='display: none;' id='roleValue'>"
                        +data.resultList[i].roleValue+
                        "</td><td id='rechargeMoney'>"
                        +data.resultList[i].rechargeMoney+
                        "</td><td>"
                        +data.resultList[i].personRelease+
                        "</td><td>"
                        +data.resultList[i].personApproving+
                        "</td><td id='rechargeType'>"
                        +category+
                        "</td><td id='state'"+myColor+">"
                        +state+
                        "</td><td id='myStatus'>"
                        +checkStatus+
                        "</td><td>"
                        +data.resultList[i].personMakeSure+
                        "</td><td>"
                        +data.resultList[i].createTime+
                        "</td><td>"
                        +data.resultList[i].checkTime+
                        "</td><td>"
                        +data.resultList[i].remark+
                        "</td><td><a href='#' onclick='agreeOne(this)' style='color: blue'>报批</a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='rollbackOne(this)' style='color: blue'>充值</a>"
                        "</td><tr>"
                }
                $("#myTbody").html(txt);
            }
            fy(data.totalCount,page,"fy","countNumber" , "dpage");
        }
    });
}
/**
 * 获取验证码
 */
function getYzm(){
    //先判断前面的信息是否填写
    $("#yzm").val();
    var accountId = $("#accountId").val();
    var accountMoney = $("#accountMoney").val();
    if(accountId == "" || accountMoney == ""){
        qikoo.dialog.alert("充值账号和充值金额不能为空！");
        return false;
    }
    if($("#getyzm").html() == "点击获取" || "重新获取" == $("#getyzm").html()){
        $.ajax({
            type:"GET",
            url:"/userRecharge/getYzm",
            dataType:"json",
            data:{
                userName:$("#personRelease").val(),
                accountMoney:$("#accountMoney").val()
            },
            success:function(data){
                //这里返回生成的随机验证码，用于后面的判断验证码是否正确
                console.log(data);
                if(data.description == "SUCCESS"){
                    qikoo.dialog.alert("验证码发送成功");
                    //设置一个倒计时，表明多久以后才可以再次发送验证码
                    var i = 60;
                    var timer = setInterval(function(){
                        if(i== -1){
                            clearInterval(timer);
                            $("#getyzm").html("重新获取");
                        }else{
                            $("#getyzm").attr("disabled",true);
                            $("#getyzm").html("重新获取("+i+")s");
                            --i;
                        }
                    },1000);
                    oldTime = (new Date()).valueOf();
                }else if(data.description == "FULL"){
                    qikoo.dialog.alert("该审批人今日审批金额已达上限")
                }
            }
        });
    }else{
        qikoo.dialog.alert("请"+$("#getyzm").html().split("(")[1].split(")")[0]+"s后再试");
        return;
    }
}
/**
 * 验证验证码是否正确
 */
function checkYzm(){

    var yzm = $("#yzm").val();
    $.ajax({
        type:"GET",
        url:"/userRecharge/checkYzm",
        dataType:"json",
        data:{
            Yzm:yzm
        },success:function(data){
            if(data.description == "SUCCESS"){
                qikoo.dialog.alert("验证码正确");
                doRecharge();
            }else if(data.description == "FAIL"){
                qikoo.dialog.alert("验证码错误");
            }else if(data.description == "OVERDU"){
                qikoo.dialog.alert("验证码已过期");
            }
        }
    });
}
/**
 * 临时充值
 */
function temporaryRecharge(){
    var accountId = $("#accountId").val();
    var rechargeType3 = $("#rechargeType3").val();
    var yzm = $("#yzm").val();
    if(accountId == "" || rechargeType3 == "" || yzm == ""){
        qikoo.dialog.alert("充值账号或者验证码不能为空！");
        return false;
    }
    checkYzm();
}
/**执行充值的操作*/
function doRecharge(){
    var excelNo = $("#excelNo2").val();
    var userName = $("#accountId").val();
    var accountMoney = $("#accountMoney").val();
    var rechargeType = $("#rechargeType3").val();
    $.ajax({
        type:"get",
        url:"/userRecharge/doRecharge",
        dataType:"json",
        data:{
            rechargeOrderNo:excelNo,
            userName:userName,
            rechargeType:rechargeType,
            accountMoney:accountMoney,
            peopleRelease:$("#personRelease").val()
        },success:function(data){
            if(data.description == "NOT EXIT"){
                qikoo.dialog.alert("充值账号不存在");
            }else if(data.description == "NOT BUYER"){
                qikoo.dialog.alert("充值账号只能是个人用户");
            }else if(data.description == "SUCCESS"){
                qikoo.dialog.alert("录入成功");
                $("#at1").removeClass("selected");
                $("#at2").addClass("selected");
                $("#tab1").hide();
                $("#tab2").show();
                searchInfo(1);
            }
            /*else if(data.description == "FAIL"){
             showMessage("充值失败");
             }else if(data.description == "FULL"){
             showMessage("审核人今日审核额度已满");
             }*/
        }

    });
}
/**
 * 重置按钮
 */
function reset(){
    $("#rechargeOrderNo").val("");
    $("#checkPeople").val("");
    $("#rechargeType2").val("");
    $("#status").val("");
    $("#checkStatus").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}
/**
 * 双击修改充值订单信息
 */
function modifyInfo(obj){
    //只能修改会计退回的充值订单，而且只能修改充值金额和充值账号
    if(obj.cells[16].innerHTML != "已拒绝"){
        qikoo.dialog.alert("只能修改已拒绝的充值订单");
        return;
    }
    //赋值进去
    $("#orderNo").val(obj.cells[2].innerHTML);
    $("#userId").val(obj.cells[7].innerHTML);
    $("#userName").val(obj.cells[8].innerHTML);
    $("#money").val(obj.cells[11].innerHTML);
    $(".tip6").fadeIn(200);
}
/**
 * 修改订单信息
 */
function modifyOrderInfo(){
    var orderNo = $("#orderNo").val();
    var userId = $("#userId").val();
    var userName = $("#userName").val();
    var money = $("#money").val();
    if(userName == "" || null == userName || money == "" || null == money){
        qikoo.dialog.alert("充值账号或者金额不能为空");
        return;
    }
    $(".tip6").fadeOut(200);
    $.ajax({
        type:"get",
        url:"/userRecharge/modifyOrderInfo",
        dataType:"json",
        data:{
            rechargeOrderNo:orderNo,
            userName:userName,
            accountMoney:money,
            peopleRelease:$("#personRelease").val()
        },success:function(data){
            if(data.description == "SUCCESS"){
                searchInfo(1);
                qikoo.dialog.alert(data.obj);
            }
        }

    });

}

