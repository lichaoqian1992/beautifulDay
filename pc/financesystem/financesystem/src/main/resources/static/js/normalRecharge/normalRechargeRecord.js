/**
 * Created by pudding on 2017-2-3.
 */
$(document).ready(function (e) {
    $(".select1").uedSelect({
        width: 345
    });
    $(".select2").uedSelect({
        width: 167
    });
    $(".select3").uedSelect({
        width: 100
    });
    //初始加载数据
    searchInfo(1);
    queryStatisticsAmount();
   /* $(".paginList li").click(function () {
        var valpage = $("#accountPage").text();
        var count = $("#accountpagecount").text();
        $(".paginList li").removeClass("current");
        $(this).addClass("current");
        if ($(this).text() == "...") {
            $(".paginList li").removeClass("current");
            $(".paginList li:eq(1)").addClass("current");
            return;
        }
        if ($(this).text() == "") {
            var val = $(this).find("span").attr("class");
            if (val == "pagepre") {
                var a1 = parseInt(valpage) - 1;
                if (a1 <= 0) {
                    showTips('操作错误！数据没有上一页！', 2000, 0);
                } else {
                    queryUserNormalRechargeRecordList(a1);
                }
            } else {
                var a2 = parseInt(valpage) + 1;
                var a2 = a2 * 20 - 19;
                if (a2 <= count) {
                    queryUserNormalRechargeRecordList(parseInt(valpage) + 1);
                } else {
                    showTips('操作错误！数据没有下一页！', 2000, 0);
                }
            }
        } else {
            var currentPage = Number($(this).text()) * 20 - 19;
            if (currentPage <= count) {
                queryUserNormalRechargeRecordList($(this).text());
            } else {
                showTips('操作错误！数据没有下一页！', 2000, 0);
                $(".paginList li").removeClass("current");
                $(".paginList li:eq(1)").addClass("current");
            }
        }
    });*/
});
/**
 * 查询总数据
 */
function queryStatisticsAmount(){
    $.ajax({
        url: '/rechargeManage/statisticsSumAmount',
        type: 'GET',
        dataType: "json",
        async: false,
        success: function (data) {
            $("#todayAmount").text(data.obj.toDayAmount);
            $("#toMorrow").text(data.obj.theCurrentMonthAmount);
        }
    });
}
/**
 * 分页查询
 * @param page
 */
function searchInfo(page) {
    $.ajax({
        url: '/rechargeManage/queryNormalRechargeList/' + page,
        type: 'GET',
        data: {
            "userName": $("#username").val(),
            "status": $("#payType").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val()
        },
        dataType: "json",
        async: false,
        success: function (data) {
            $('#userRechargeData').children().remove();

            //循环数据
            for (i = 0; i < data.resultList.length; i++) {
                $("#userRechargeData").append("<tr style='height: 10px'>" +
                    "<td>" + Number(i + 1) + "</td>" +
                    "<td>" + data.resultList[i].userId + "</td>" +
                    "<td>" + data.resultList[i].transactionNo + "</td>" +
                    "<td>" + data.resultList[i].paymentName + "</td>" +
                    "<td>" + data.resultList[i].transactionMoney + "</td>" +
                    "<td>" + data.resultList[i].userName + "</td>" +
                    "<td>" + data.resultList[i].status + "</td>" +
                    "<td>" + data.resultList[i].addTime + "</td>" +
                    "<td>" + data.resultList[i].notifyTime + "</td>" +
                    "<td>" + data.resultList[i].remark + "</td></tr>");
            }
            $("#userRechargeData").append("<tr  style='height: 10px;border-top: dashed black 1px;color: #ff3b76;font-weight: bold'><td>统计</td><td colspan='3' style='text-align: center'>当日累计充值总金额</td><td id='todayAmount' style='text-align: center'>1</td><td colspan='4' style='text-align: center'>当月累计充值总金额</td><td id='toMorrow' style='text-align: center'>20</td></tr>");
            fy(data.totalCount,data.page,"fy","countNumber" , "dpage");
            /*$("#accountpagecount").text(data.totalCount);
            $("#accountPage").text(data.page);*/
        }
    });
}

function showTips(txt, time, status) {
    var htmlCon = '';
    if (txt != '') {
        if (status != 0 && status != undefined) {
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:#4AAF33;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../../static/images/ok.png" style="vertical-align: middle;margin-right:5px;" alt="OK，"/>' + txt + '</div>';
        } else {
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:rgba(128, 57, 65, 0.46);border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../../static/images/err5.png" style="vertical-align: middle;margin-right:5px;" alt="Error，"/>' + txt + '</div>';
        }
        $('body').prepend(htmlCon);
        if (time == '' || time == undefined) {
            time = 1500;
        }
        setTimeout(function () {
            $('.tipsBox').remove();
        }, time);
    }
}

function reset() {
    $("#payType").val("");
    $("#username").val("");
    $("#startTime").val("");
    $("#endTime").val("");

}

//导出Excel表格
function exportTable(){
    location.href="/rechargeManage/exportExcel?userName="+$("#username").val()+"&status="+$("#payType").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"";
}