/*总汇详情js*/
$(function () {
    optimization();
});

/*售后退款*/
function refoundDetail(page) {
    $.ajax({
        url: '/finance/home/refoundDetail',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "orderNo": $("#orderNo").val(),
            "bankOrderNo": $("#bankOrderNo").val(),
            "orderType": $("#orderType").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            $(data.refoundList.list).each(function () {
                $(".table-bordered tbody").append("<tr><td>" + this.sumId + "</td>" +
                    "<td>" + this.update_time + "</td>" +
                    "<td>" + this.back_return_no + "</td>" +
                    "<td>" + this.order_no + "</td>" +
                    "<td>" + this.order_title + "</td>" +
                    "<td>" + this.gname + "</td>" +
                    "<td>" + this.uname + "</td>" +
                    "<td>" + this.title + "</td>" +
                    "<td>" + this.alltk + "</td>" +
                    "<td>" + this.tuikuandj + "</td>" +
                    "<td>" + this.tkxianjin + "</td></tr>");
            });
        }
    });
    optimization();
}

/*满意卷支出*/
function voucherDetail(page) {
    $.ajax({
        url: '/finance/home/voucherDetail',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "orderNo": $("#orderNo").val(),
            "orderType": $("#orderType").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            $(data.refoundList.list).each(function () {
                $(".table-bordered tbody").append("<tr><td>" + this.sumId + "</td>" +
                    "<td>" + this.complete_time + "</td>" +
                    "<td>" + this.order_no + "</td>" +
                    "<td>" + this.order_title + "</td>" +
                    "<td>" + this.gname + "</td>" +
                    "<td>" + this.uname + "</td>" +
                    "<td>" + this.title + "</td>" +
                    "<td>" + this.order_amount + "</td>" +
                    "<td>" + this.real_amount + "</td>" +
                    "<td>" + this.voucher + "</td></tr>");
            });
        }
    });
    optimization();
}

/*金融手续费支出*/
function financeDetail(page) {
    $.ajax({
        url: '/finance/home/financeDetail',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "orderNo": $("#orderNo").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            $(data.refoundList.list).each(function () {
                $(".table-bordered tbody").append("<tr><td>" + this.sumId + "</td>" +
                    "<td>" + this.complete_time + "</td>" +
                    "<td>" + this.withdrawals_no + "</td>" +
                    "<td>" + this.nick_name + "</td>" +
                    "<td>" + this.bank_user + "</td>" +
                    "<td>" + this.bank_name + "</td>" +
                    "<td>" + this.bank_card + "</td>" +
                    "<td>" + this.total_money + "</td>" +
                    "<td>" + this.commission + "</td>" +
                    "<td>" + this.arrival + "</td></tr>");
            });
        }
    });
    optimization();
}

/*用户账单余额*/
function userDetail(page) {
    $.ajax({
        url: '/finance/home/userDetail',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "nickName": $("#nickName").val(),
            "userType": $("#userType").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.liuShuiMingXi tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            $(data.refoundList.list).each(function () {
                $(".liuShuiMingXi tbody").append("<tr><td style='display: none;'><input type='text' class='userId' value='" + this.user_id + "'/></td><td>" + this.sumId + "</td>" +
                    "<td><a href='/finance/withdrawals/infoalso?userId=" + this.user_id + "'>" + this.user_name + "</a></td>" +
                    "<td>" + this.person_name + "</td>" +
                    "<td>" + this.state + "</td>" +
                    "<td>" + this.amount + "</td>" +
                    "<td>" + this.allow_amount + "</td><td><a class='href_a check_file'>查看资金流水</a></td></tr>");
            });
        }
    });
    optimization();
}


/*商家账单余额*/
function shopDetail(page) {
    $.ajax({
        url: '/finance/home/shopDetail',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "nickName": $("#nickName").val(),
            "userType": $("#userType").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.liuShuiMingXi tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);

            $(data.refoundList.list).each(function () {
                $(".liuShuiMingXi tbody").append("<tr><td style='display: none;'><input type='text' class='userId' value='" + this.user_id + "'/></td><td>" + this.sumId + "</td>" +
                    "<td><a href='/finance/ord/findBusinessDetails?user_id=" + this.user_id + "'>" + this.name + "</a></td>" +
                    "<td>" + this.user_name + "</td>" +
                    "<td>" + this.area + "</td>" +
                    "<td>" + this.mobile + "</td>" +
                    "<td>" + this.TAG + "</td>" +
                    "<td>" + this.state + "</td>" +
                    "<td>" + this.amount + "</td>" +
                    "<td>" + this.allow_amount + "</td><td><a class='href_a check_file'>查看资金流水</a></td></tr>");
            });
        }
    });
    optimization();
}

/*第三方支付*/
function thirdPartyDetail(page, url, type) {
    $.ajax({
        url: url,
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "orderNo": $("#orderNo").val(),
            "orderType": $("#orderType").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            var typeName = "";
            if (type == "weixin") {
                typeName = "微信支付";
            } else if (type == "card") {
                typeName = "银行卡支付";
            } else if (type == "zhifubao") {
                typeName = "支付宝支付";
            } else if (type == "amount") {
                typeName = "余额支付";
            }
            $(data.refoundList.list).each(function () {
                var status=this.status;
                if (type == "amount") {
                    status="已支付"
                }

                $(".table-bordered tbody").append("<tr><td>" + this.sumId + "</td>" +
                    "<td>" + this.complete_time + "</td>" +
                    "<td>" + this.order_no + "</td>" +
                    "<td>" + this.order_title + "</td>" +
                    "<td>" + this.gname + "</td>" +
                    "<td>" + this.uname + "</td>" +
                    "<td>" + typeName + "</td>" +
                    "<td>"+status+"</td>" +
                    "<td>" + this.order_amount + "</td>" +
                    "<td>" + this.real_amount + "</td>" +
                    "<td>" + this.voucher + "</td></tr>");
            });
        }
    });
    optimization();
}

/*技术服务费*/
function techServiceDetail(page) {
    $.ajax({
        url: '/finance/home/techServiceDetail',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "orderNo": $("#orderNo").val(),
            "orderType": $("#orderType").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val(),
            "atr": "ajax"
        },
        success: function (data) {
            console.log(data);
            $('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            $(data.refoundList.list).each(function () {
                $(".table-bordered tbody").append("<tr><td>" + this.sumId + "</td>" +
                    "<td>" + this.complete_time + "</td>" +
                    "<td>" + this.order_no + "</td>" +
                    "<td>" + this.gnick + "</td>" +
                    "<td>" + this.gname + "</td>" +
                    "<td>" + this.TAG + "</td>" +
                    "<td>" + this.uname + "</td>" +
                    "<td>" + this.title + "</td>" +
                    "<td>" + this.real_amount + "</td>" +
                    "<td>" + this.voucher + "</td>" +
                    "<td>" + this.order_amount + "</td>" +
                    "<td>" + this.user_percentage + "</td>" +
                    "<td>" + this.technical + "</td>" +
                    "<td>" + this.real_shop_money + "</td></tr>");
            });
        }
    });
    optimization();
}


/*预支出总额*/
function advanceDetail(page) {
    $.ajax({
        url: '/finance/home/advanceDetail',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "activeType": $("#activeType").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            if (data.refoundList.list != null) {
                $(data.refoundList.list).each(function (index) {
                    $(".table-bordered tbody").append("<tr><td>" + (index + 1) + "</td>" +
                        "<td>" + this.obtainTime + "</td>" +
                        "<td>" + this.userName + "</td>" +
                        "<td>" + this.activeType + "</td>" +
                        "<td>" + this.subsidyMoney + "</td></tr>");
                });
            }else{
                $(".table-bordered tbody").append("<tr><td colspan='5' style='text-align: center;'>暂无数据</td></tr>");
            }
        }
    });
    optimization();
}


/*已支出总额*/
function actualDetail(page) {
    $.ajax({
        url: '/finance/home/actualDetail',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "orderNo": $("#orderNo").val(),
            "orderType": $("#orderType").val(),
            "activeType": $("#activeType").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            if (data.refoundList.list != null) {
                $(data.refoundList.list).each(function (index) {
                    $(".table-bordered tbody").append("<tr><td>" + (index + 1) + "</td>" +
                        "<td>" + this.useTime + "</td>" +
                        "<td>" + this.orderNo + "</td>" +
                        "<td>" + this.orderType + "</td>" +
                        "<td>" + this.shopName + "</td>" +
                        "<td>" + this.userName + "</td>" +
                        "<td>" + this.payType + "</td>" +
                        "<td>" + this.orderAmount + "</td>" +
                        "<td>" + this.realAmount + "</td>" +
                        "<td>" + this.activeType + "</td>" +
                        "<td>" + this.subsidyMoney + "</td></tr>");
                });
            }else{
                $(".table-bordered tbody").append("<tr><td colspan='11' style='text-align: center;'>暂无数据</td></tr>");
            }
        }
    });
    optimization();
}

/*日月详情流水查询*/
function riyueselectDetail(page) {
    $.ajax({
        url: '/finance/home/riyueselect',
        type: 'GET',
        dataType: "json",
        async: false,
        data: {
            "page": page,
            "orderNo": $("#orderNo").val(),
            "orderType": $("#orderType").val(),
            "startTime": $("#startTime").val(),
            "endTime": $("#endTime").val(),
            "atr": "ajax"
        },
        success: function (data) {
            $('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.refoundList.totalRow);
            $(".pull-left q:eq(1)").html(data.refoundList.pageNumber);
            $(".lastpage").val(data.refoundList.totalPage);
            //循环数据
            $(".page").val(data.refoundList.pageNumber);
            $(data.refoundList.list).each(function () {
                $(".table-bordered tbody").append("<tr><td>" + this.sumId + "</td>" +
                    "<td>" + this.complete_time + "</td>" +
                    "<td>" + this.payment_time + "</td>" +
                    "<td>" + this.order_no + "</td>" +
                    "<td>" + this.order_title + "</td>" +
                    "<td>" + this.name + "</td>" +
                    "<td>" + this.user_name + "</td>" +
                    "<td>" + this.payment_name + "</td>" +
                    "<td>" + this.order_amount + "</td>" +
                    "<td>" + this.user_percentage + "</td>" +
                    "<td>" + this.technical + "</td>" +
                    "<td>" + this.voucher + "</td>" +
                    "<td>" + this.real_amount + "</td>" +
                    "<td>" + this.commission + "</td>" +
                    "<td>" + this.real_shop_money + "</td></tr>");
            });
        }
    });
    optimization();
}


function Confluence(page) {
    $(".nav-tabs li").each(function () {
        if ($(this).hasClass('active')) {
            if ($(this).text() == "满意券支出") {
                voucherDetail(page);
            } else if ($(this).text() == "售后退款单") {
                refoundDetail(page);
            } else if ($(this).text() == "金融手续费") {
                financeDetail(page);
            } else if ($(this).text() == "用户账单余额") {
                userDetail(page);
            } else if ($(this).text() == "商家账户余额") {
                shopDetail(page);
            } else if ($(this).text() == "微信支付订单") {
                thirdPartyDetail(page, '/finance/home/weixinDetail', 'weixin');
            } else if ($(this).text() == "支付宝支付订单") {
                thirdPartyDetail(page, '/finance/home/zhifubaoDetail', 'zhifubao');
            } else if ($(this).text() == "银行卡支付订单") {
                thirdPartyDetail(page, '/finance/home/cardDetail', 'card');
            } else if ($(this).text() == "技术服务费") {
                techServiceDetail(page);
            } else if ($(this).text() == "余额支付订单") {
                thirdPartyDetail(page, '/finance/home/amountDetail', 'amount');
            } else if ($(this).text() == "预支出总额") {
                advanceDetail(page);
            } else if ($(this).text() == "已支出总额") {
                actualDetail(page);
            }

        }
    });
    if ($("#zijin").val() == "true") {
        riyueselectDetail(page);
    }

}

function optimization() {
    $(".table-bordered tbody tr td").each(function () {
        if ($(this).html() == "" || $(this).html() == "null") {
            $(this).html("暂无记录");
        }
    });
}


/**
 * 导出excel
 */
function toExcel() {
    $(".nav-tabs li").each(function () {
        if ($(this).hasClass('active')) {
            if ($(this).text() == "支付宝支付订单") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=zhifubao";
            } else if ($(this).text() == "银行卡支付订单") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=card";
            } else if ($(this).text() == "微信支付订单") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=weixin";
            } else if ($(this).text() == "余额支付订单") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=amount";
            } else if ($(this).text() == "技术服务费") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=techService";
            } else if ($(this).text() == "金融手续费") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=finance";
            } else if ($(this).text() == "满意券支出") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=voucher";
            } else if ($(this).text() == "售后退款单") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=refound";
            } else if ($(this).text() == "商家账户余额") {
                window.location.href = "/finance/home/toExcel?nickName=" + $("#nickName").val() + "&userType=" + $("#userType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=shop";
            } else if ($(this).text() == "用户账单余额") {
                window.location.href = "/finance/home/toExcel?nickName=" + $("#nickName").val() + "&userType=" + $("#userType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=user";
            } else if ($(this).text() == "已支出总额") {
                window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&activeType=" + $("#activeType").val() + "&dataType=actual&allcount="+$(".pull-left q:eq(0)").html();
            } else if ($(this).text() == "预支出总额") {
                window.location.href = "/finance/home/toExcel?startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&activeType=" + $("#activeType").val() + "&dataType=advance&allcount="+$(".pull-left q:eq(0)").html();
            }
        }
    });
    if ($("#zijin").val() == "true") {
        window.location.href = "/finance/home/toExcel?orderNo=" + $("#orderNo").val() + "&orderType=" + $("#orderType").val() + "&startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&dataType=riyue";
    }
}

/*********************分页*************************/
/*下一页*/
function previousPage() {
    if ((parseInt($(".page").val()) + 1) > parseInt($(".lastpage").val())) {
        layer.msg('这已经是最后一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    Confluence(parseInt($(".page").val()) + 1);
}

/*上一页*/
function nextPage() {
    if ((parseInt($(".page").val()) - 1) < 1) {
        layer.msg('这已经是第一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    Confluence(parseInt($(".page").val()) - 1);
}

/*首页*/
function homePage() {
    if ((parseInt($(".page").val())) == 1) {
        layer.msg('这已经是第一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    Confluence(1);
}

/*尾页*/
function Shadowe() {
    if ((parseInt($(".page").val())) == parseInt($(".lastpage").val())) {
        layer.msg('这已经是最后一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    if(parseInt($(".lastpage").val())==0){
        Confluence(1);
    }else{
        Confluence(parseInt($(".lastpage").val()));
    }
}

/*GO*/
function goPage() {
    var page = 1;
    if ((parseInt($(".page_numberAll").val())) == parseInt($(".page").val())) {
        layer.msg('正在当前页！', {
            icon: 1,
            time: 1000
        });
        return;
    }

    if ((parseInt($(".page_numberAll").val())) > parseInt($(".lastpage").val())) {
        $(".page_numberAll").val($(".lastpage").val());
        page = parseInt($(".lastpage").val());
    }

    if ((parseInt($(".page_numberAll").val())) < 1) {
        $(".page_numberAll").val(1);
        page = 1;
    }
    Confluence(parseInt($(".page_numberAll").val()));
}