/**
 * 页面初始加载
 */
var dayCount = 0;
var monthCount = 0;
var countNum = 0;
$(function(){
    $(".select1").uedSelect({
        width: 345
    });
    $(".select2").uedSelect({
        width: 167
    });
    $(".select3").uedSelect({
        width: 140
    });
    //根据登录人判断是否可以下载充值记录,
    $.ajax({
        type:"get",
        url:"/userRecharge/getUserRole",
        dataType:"json",
        data:{
            userName:$("#checkPeople").val()
        },success:function(data){
            if(data.description != "SUCCESS"){
                $("#toExcel").hide();
            }
        }
    });
    getDMCount();

});
/**
 * 获取当日和当月的充值总金额
 */
function getDMCount(){
    $.ajax({
        type:"GET",
        url:"/userRecharge/getDMCount",
        dataType:"json",
        success:function(data){
            dayCount = data.obj.dayCount;
            monthCount = data.obj.monthCount;
            searchInfo();
        }
    });
}
/**
 * 获取总条数
 */
function getCount(){
    var userName = $("#username").val();
    var status = $("#userRole").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    $.ajax({
        type:"GET",
        url:"/userRecharge/getCount",
        dataType:"json",
        data:{
            userName:userName,
            status:status,
            startTime:startTime,
            endTime:endTime
        },
        success:function(data){
            countNum = "";
            countNum = data.obj.remark;
        }
    });
}
//消息提示框
function showTips(txt,time,status)
{
    var htmlCon = '';
    if(txt != ''){
        if(status != 0 && status != undefined){
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:#4AAF33;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../../static/images/ok.png" style="vertical-align: middle;margin-right:5px;" alt="OK，"/>'+txt+'</div>';
        }else{
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:rgba(255, 16, 8, 0.46);border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../../static/images/ts.png" style="vertical-align: middle;margin-right:5px;" alt="Error，"/>'+txt+'</div>';
        }
        $('body').prepend(htmlCon);
        if(time == '' || time == undefined){
            time = 1500;
        }
        setTimeout(function(){ $('.tipsBox').remove(); },time);
    }
}
/**
 * 条件查询内部充值记录
 */
function searchInfo(num){
    var txt = "";
    var state = "";
    var category = "";
    var userName = $("#username").val();
    var status = $("#userRole").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var pageNum;
    if(num == null || undefined == num || "" == num){
        pageNum = 1;
    }else{
        pageNum = num;
    }
    $.ajax({
        type:"GET",
        url:"/userRecharge/getMyRecharge",
        dataType:"json",
        async: false,
        data:{
            userName:userName,
            status:status,
            startTime:startTime,
            endTime:endTime,
            pageNum:pageNum
        },
        success:function(data){
            console.log(data);
            $("#userAccountData").empty();
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    for(var i=0;i<data.resultList.length;i++){
                        state = data.resultList[i].status;
                        category = data.resultList[i].rechargeType;
                        checkStatus = data.resultList[i].checkStatus;
                        txt +="<tr><td><input class='mycheckbox' type='checkbox' value='' onclick='selectOne(this)'/></td><td>"
                            +(i+1)+
                            "</td><td id='myId'>"
                            +data.resultList[i].orderNo+
                            "</td><td>"
                            +data.resultList[i].excelNo+
                            "</td><td>"
                            +data.resultList[i].excelName+
                            "</td><td id='userIdList'>"
                            +data.resultList[i].userId+
                            "</td><td>"
                            +data.resultList[i].userKey+
                            "</td><td>"
                            +data.resultList[i].roleType+
                            "</td><td>"
                            +data.resultList[i].rechargeMoney+
                            "</td><td>"
                            +data.resultList[i].personRelease+
                            "</td><td>"
                            +data.resultList[i].personApproving+
                            "</td><td>"
                            +category+
                            "</td><td>"
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
                $("#userAccountData").html(txt + "<tr  style='height: 10px;border-top: dashed black 1px;color: #ff3b76;font-weight: bold'><td>统计</td><td colspan='4' style='text-align: center'>当日累计充值总金额</td><td colspan='4' id='todayAmount' style='text-align: center'>" + dayCount + "</td><td colspan='5' style='text-align: center'>当月累计充值总金额</td><td colspan='4' id='toMorrow' style='text-align: center'>" + monthCount + "</td></tr>");
            }
            fy(data.totalCount, pageNum , "fy","countNumber","dpage");
        }
    });
}
/**
 * 导出Excel
 */
function exportTable(){
    location.href="/toExcel/excelInside?userName="+$("#username").val()+"&status="+$("#userRole").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"";
}

function reset() {
    //document.location.reload();
    $("#userRole").val("");
    $("#username").val("");
    $("#startTime").val("");
    $("#endTime").val("");

}