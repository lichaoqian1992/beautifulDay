/**
 * Created by pudding on 2017-1-18.
 */
var code=0;
$(function () {
    //$(".tablelink").click(function () {
    //    searchInfo(1);
    //});
    $(".tiptop a").click(function () {
        $(".tip").fadeOut(200);
        $(".paginList li").removeClass("current");
        $(".paginList li:eq(1)").addClass("current");
    });
    /*$(".paginList li").click(function () {
        var valpage=$("#accountPage").text();
        var count=$("#accountpagecount").text();
        //alert(valpage);
        //alert(count);
        $(".paginList li").removeClass("current");
        $(this).addClass("current");
        if($(this).text()=="..."){
            $(".paginList li").removeClass("current");
            $(".paginList li:eq(1)").addClass("current");
            return;
        }
        if ($(this).text() == "") {
            var val = $(this).find("span").attr("class");
            if (val == "pagepre") {

                var a1=parseInt(valpage)-1;
                if(a1<=0){
                    showTips('操作错误！数据没有上一页！',2000,0);
                }else{
                    if (!isNaN(code)) {
                        getOnlinePayData(a1, pid);
                    } else {
                        if (code == "TECHNICAL_SERVICE_FEE") {
                            getBalanceData(a1);

                        } else {
                            getWithdrawalsData(a1);
                        }
                    }
                }
            } else {
                var a1=parseInt(valpage)+1;
                var a1=a1*20-19;
                if(a1<=count){
                    if (!isNaN(code)) {
                        getOnlinePayData(parseInt(valpage)+1, pid);
                    } else {
                        if (code == "TECHNICAL_SERVICE_FEE") {
                            getBalanceData(parseInt(valpage)+1);

                        } else {
                            getWithdrawalsData(parseInt(valpage)+1)
                        }
                    }
                }else{
                    showTips('操作错误！数据没有下一页！',2000,0);
                }
            }
        }else{
            var currentPage=Number($(this).text())*20-19;
            if(currentPage<=count){
                var page = Number($(this).text());

                if (!isNaN(code)) {
                    getOnlinePayData(page, pid);
                } else {
                    if (code == "TECHNICAL_SERVICE_FEE") {
                        getBalanceData(page);
                    } else {
                        getWithdrawalsData(page);
                    }
                }
            }else{
                showTips('操作错误！数据没有下一页！',2000,0);
                $(".paginList li").removeClass("current");
                $(".paginList li:eq(1)").addClass("current");
            }
        }
    });*/
})
function searchInfo(clickPage){
    if(clickPage == undefined || null == clickPage || "" == clickPage){
        clickPage = 1;
    }
    if (!isNaN(code)) {
        getOnlinePayData(clickPage, code);
    } else {
        if (code == "TECHNICAL_SERVICE_FEE") {
            getBalanceData(clickPage);

        } else {
            getWithdrawalsData(clickPage,code);
        }
    }
}
function showDetail(obj){
    $(".tip").fadeIn(200);
    // getOnlinePayData(1,);
    code = $(obj).parent().prev().text();
    searchInfo(1);
}
function getWithdrawalsData(page,code){
    $.ajax({
        url: '/info/getUserWithdrawalsList/'+ page ,
        type: 'GET',
        data: {
            code:code
        },
        dataType: "json",
        success: function (data) {
            $('#accountDetail').children().remove();

            $("#accountDetail").append("<thead>" +
                "<tr>" +
                "<th>编号<i class='sort'><img src='../../../static/images/px.gif'/></i></th>" +
                "<th>用户id</th>" +
                "<th>用户角色</th>" +
                "<th>用户角色值</th>" +
                "<th>提现账户ID</th>" +
                "<th>提现订单号</th>" +
                "<th>银行卡名称</th>" +
                "<th>银行卡卡号</th>" +
                "<th>银行卡地区</th>" +
                "<th>详细开户地址</th>" +
                "<th>提现金额</th>" +
                "<th>提现状态</th>" +
                "<th>生成时间</th>" +
                "<th>完成时间</th>" +
                    //"<th>支付回调数据</th>" +
                "<th>三方支付交易号</th>" +
                    // "<th>支付成功同步回调地址</th>" +
                    // "<th>提现处理说明</th>" +
                "<th>是否删除</th>" +
                "<th>提现手续费</th>" +
                "<th>开户姓名</th>" +
                "<th>提现满意券</th>" +
                "<th>提现总金额</th>" +
                "</tr></thead>");
            $("#accountDetail").append("<tbody id='tbodydata'></tbody>");
            //循环数据
            for (i = 0; i < data.resultList.length; i++) {
                $("#tbodydata").append("<tr style='height: 10px'>" +
                    "<td>" + Number(i + 1) + "</td>" +
                    "<td>" + data.resultList[i].userId + "</td>" +
                    "<td>" + data.resultList[i].userRoleTyoe + "</td>" +
                    "<td>" + data.resultList[i].userRoleValue + "</td>" +
                    "<td>" + data.resultList[i].accountId + "</td>" +
                    "<td>" + data.resultList[i].withdrawalsNo + "</td>" +
                    "<td>" + data.resultList[i].bankName + "</td>" +
                    "<td>" + data.resultList[i].bankCard + "</td>" +
                    "<td>" + data.resultList[i].bankArea + "</td>" +
                    "<td>" + data.resultList[i].bankAddress + "</td>" +
                    "<td>" + data.resultList[i].amount + "</td>" +
                    "<td>" + data.resultList[i].status + "</td>" +
                    "<td>" + data.resultList[i].add_time + "</td>" +
                    "<td>" + data.resultList[i].complete_time + "</td>" +
                        // "<td>"+data.resultList[i].transactionTxt+"</td>" +
                    "<td>" + data.resultList[i].transactionNo + "</td>" +
                        //  "<td>"+data.resultList[i].remark+"</td>" +
                    "<td>" + data.resultList[i].isDel + "</td>" +
                    "<td>" + data.resultList[i].commission + "</td>" +
                    "<td>" + data.resultList[i].bank_user + "</td>" +
                    "<td>" + data.resultList[i].voucher + "</td>" +
                    "<td>" + data.resultList[i].totalMoney + "</td></tr>");
            }

            //$("#accountpagecount").text(data.totalCount);
            //$("#accountPage").text(data.page);
            fy(data.totalCount,data.page,"fy","countNumber","dpage");

        }
    });
}
function getBalanceData(page){


    $.ajax({
        url: '/info/getBalanceLogList/'+ page ,
        type: 'GET',
        data: {},
        dataType: "json",
        success: function (data) {
            $('#accountDetail').children().remove();

            $("#accountDetail").append("<thead>" +
                "<tr>" +
                "<th>编号<i class='sort'><img src='../../../static/images/px.gif'/></i></th>" +
                "<th>用户id</th>" +
                "<th>用户角色</th>" +
                "<th>用户角色值</th>" +
                "<th>订单来源</th>" +
                "<th>订单类型</th>" +
                "<th>订单编号</th>" +
                "<th>记录标识</th>" +
                "<th>订单标题</th>" +
                "<th>查看地址</th>" +
                "<th>结算金额</th>" +
                "<th>结算代金券</th>" +
                "<th>结算积分</th>" +
                "<th>预期结算时间</th>" +
                    //"<th>支付回调数据</th>" +
                "<th>实际结算时间</th>" +
                "<th>结算状态</th>" +
                    // "<th>支付成功同步回调地址</th>" +
                "<th>记录写入时间</th>" +
                "<th>是否删除</th>" +
                    // "<th>支付成功异步通知地址</th>" +
                "</tr></thead>");
            $("#accountDetail").append("<tbody id='tbodydata'></tbody>");
            //循环数据

            for (i = 0; i < data.resultList.length; i++) {
                $("#tbodydata").append("<tr style='height: 10px'>" +
                    "<td>" + Number(i + 1) + "</td>" +
                    "<td>" + data.resultList[i].userId + "</td>" +
                    "<td>" + data.resultList[i].roleType + "</td>" +
                    "<td>" + data.resultList[i].roleValue + "</td>" +
                    "<td>" + data.resultList[i].orderPlatform + "</td>" +
                    "<td>" + data.resultList[i].orderType + "</td>" +
                    "<td>" + data.resultList[i].orderNo + "</td>" +
                    "<td>" + data.resultList[i].balanceHash + "</td>" +
                    "<td>" + data.resultList[i].orderTitle + "</td>" +
                    "<td>" + data.resultList[i].orderUrl + "</td>" +
                    "<td>" + data.resultList[i].balanceAmount + "</td>" +
                    "<td>" + data.resultList[i].balanceVoucher + "</td>" +
                    "<td>" + data.resultList[i].balancePoint + "</td>" +
                    "<td>" + data.resultList[i].willBalanceDate + "</td>" +
                        // "<td>"+data.resultList[i].transactionTxt+"</td>" +
                    "<td>" + data.resultList[i].realBalanceDate + "</td>" +
                    "<td>" + data.resultList[i].balanceState + "</td>" +
                        //"<td>"+data.resultList[i].returnUrl+"</td>" +
                    "<td>" + data.resultList[i].addTime + "</td>" +
                    "<td>" + data.resultList[i].isDel + "</td></tr>");
            }

            //$("#accountpagecount").text(data.totalCount);
            //$("#accountPage").text(data.page);
            fy(data.totalCount,data.page,"fy","countNumber","dpage")

        }
    });

}

var pid = 0;
//获取第三方支付交易明细
function getOnlinePayData(page, paymentId) {
    pid = paymentId;
    $.ajax({
        url: '/info/getOnlinePayAccountInfoList/' + page + '/' + paymentId + '',
        type: 'GET',
        data: {},
        dataType: "json",
        success: function (data) {
            $('#accountDetail').children().remove();

            $("#accountDetail").append("<thead>" +
                "<tr>" +
                "<th>编号<i class='sort'><img src='../../../static/images/px.gif'/></i></th>" +
                "<th>付款用户id</th>" +
                "<th>付款用户角色</th>" +
                "<th>付款用户角色值</th>" +
                "<th>订单类型</th>" +
                "<th>订单id列表</th>" +
                "<th>使用代金券数量</th>" +
                "<th>支付方式名称</th>" +
                "<th>支付类型表id</th>" +
                "<th>添加时间</th>" +
                "<th>支付回调时间</th>" +
                "<th>支付成功的单号</th>" +
                "<th>支付成功的金额</th>" +
                "<th>支付用户标识</th>" +
                    //"<th>支付回调数据</th>" +
                "<th>支付状态</th>" +
                "<th>是否删除</th>" +
                    // "<th>支付成功同步回调地址</th>" +
                "<th>支付类型</th>" +
                "<th>期望支付的金额</th>" +
                    // "<th>支付成功异步通知地址</th>" +
                "<th>合作通道</th>" +
                "<th>检查时间</th>" +
                "<th>检查用户</th>" +
                "<th>备注</th>" +
                "</tr></thead>");
            $("#accountDetail").append("<tbody id='tbodydata'></tbody>");
            //循环数据

            for (i = 0; i < data.resultList.length; i++) {
                $("#tbodydata").append("<tr style='height: 10px'>" +
                    "<td>" + Number(i + 1) + "</td>" +
                    "<td>" + data.resultList[i].userId + "</td>" +
                    "<td>" + data.resultList[i].userRoleType + "</td>" +
                    "<td>" + data.resultList[i].userRoleValue + "</td>" +
                    "<td>" + data.resultList[i].orderType + "</td>" +
                    "<td>" + data.resultList[i].orderIdList + "</td>" +
                    "<td>" + data.resultList[i].useVoucher + "</td>" +
                    "<td>" + data.resultList[i].paymentName + "</td>" +
                    "<td>" + data.resultList[i].paymentId + "</td>" +
                    "<td>" + data.resultList[i].addTime + "</td>" +
                    "<td>" + data.resultList[i].notifyTime + "</td>" +
                    "<td>" + data.resultList[i].transactionNo + "</td>" +
                    "<td>" + data.resultList[i].transactionMoney + "</td>" +
                    "<td>" + data.resultList[i].transactionIdentity + "</td>" +
                        // "<td>"+data.resultList[i].transactionTxt+"</td>" +
                    "<td>" + data.resultList[i].status + "</td>" +
                    "<td>" + data.resultList[i].isDel + "</td>" +
                        //"<td>"+data.resultList[i].returnUrl+"</td>" +
                    "<td>" + data.resultList[i].paymentType + "</td>" +
                    "<td>" + data.resultList[i].payment_money + "</td>" +
                        //"<td >"+data.resultList[i].notifyUrl+"</td>" +
                    "<td>" + data.resultList[i].partnerChannel + "</td>" +
                    "<td>" + data.resultList[i].censorDate + "</td>" +
                    "<td>" + data.resultList[i].censorUser + "</td>" +
                    "<td>" + data.resultList[i].remark + "</td></tr>");
            }
            //$("#accountpagecount").text(data.totalCount);
            //$("#accountPage").text(data.page);
            fy(data.totalCount,data.page,"fy","countNumber","dpage");

        }
    });
};
function showTips(txt,time,status)
{
    var htmlCon = '';
    if(txt != ''){
        if(status != 0 && status != undefined){
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:#4AAF33;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../../static/images/ok.png" style="vertical-align: middle;margin-right:5px;" alt="OK，"/>'+txt+'</div>';
        }else{
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:rgba(128, 57, 65, 0.46);border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../../static/images/err5.png" style="vertical-align: middle;margin-right:5px;" alt="Error，"/>'+txt+'</div>';
        }
        $('body').prepend(htmlCon);
        if(time == '' || time == undefined){
            time = 1500;
        }
        setTimeout(function(){ $('.tipsBox').remove(); },time);
    }
}

