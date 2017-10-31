/**
 * Created by pudding on 2017-1-20.
 */
var userName = "";
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
    searchInfo(1);

    //翻页
    $("#accountinfo li").click(function () {
        var valpage = $("#userAccountPage").text();
        var count = $("#userAccountCount").text();
        $("#accountinfo li").removeClass("current");
        $(this).addClass("current");
        if ($(this).text() == "...") {
            $("#accountinfo li").removeClass("current");
            $("#accountinfo li:eq(1)").addClass("current");
            return;
        }
        if ($(this).text() == "") {
            var val = $(this).find("span").attr("class");
            if (val == "pagepre") {
                var a1 = parseInt(valpage) - 1;
                if (a1 <= 0) {
                    showTips('操作错误！数据没有上一页！', 2000, 0);
                } else {
                    queryShopsAccountInfo(a1);
                }
            } else {
                var a2 = parseInt(valpage) + 1;
                var a2 = a2 * 20 - 19;
                if (a2 <= count) {
                    queryShopsAccountInfo(parseInt(valpage) + 1);
                } else {
                    showTips('操作错误！数据没有下一页！', 2000, 0);
                }
            }
        } else {
            var currentPage = Number($(this).text()) * 20 - 19;
            if (currentPage <= count) {
                queryShopsAccountInfo($(this).text());
            } else {
                showTips('操作错误！数据没有下一页！', 2000, 0);
                $("#accountinfo li").removeClass("current");
                $("#accountinfo li:eq(1)").addClass("current");
            }


        }
    })


});
KE.show({
    id: 'content7',
    cssPath: './index.css'
});
$("#usual1 ul").idTabs();
$('.tablelist tbody tr:odd').addClass('odd');


function searchInfo(page) {
    $.ajax({
        url: '/info/queryShopsAccountInfoJson/' + page,
        type: 'GET',
        data: {"userName": $("#username").val(), "roleType": $("#userRole").val(), "state": $("#accountType").val()},
        dataType: "json",
        async: false,
        success: function (data) {
            $("#userAccountPage").text(data.page);
            $("#userAccountCount").text(data.totalCount)
            $('#userAccountData').remove();
            $("#userAccount").append("<tbody id='userAccountData'></tbody>");

            for (i = 0; i < data.resultList.length; i++) {
                $("#userAccountData").append("<tr style='height: 10px'>" +
                    "<td>" + Number(i + 1) + "</td>" +
                    "<td style='display: none'>" + data.resultList[i].userId + "</td>" +
                    "<td id='userName'>" + data.resultList[i].userName + "</td>" +
                    "<td>" + data.resultList[i].roleType + "</td>" +
                    "<td>" + data.resultList[i].state + "</td>" +
                    "<td>" + data.resultList[i].amount + "</td>" +
                    "<td>" + data.resultList[i].allowAmount + "</td>" +
                    "<td><a href='#' onclick='openInfoDetail(this)' class='tablelink'>交易明细</a></td></tr>");
            }
            fy(data.totalCount,data.page,"fy","countNumber","dpage");
        }
    });


}

function showTips(txt, time, status) {
    var htmlCon = '';
    if (txt != '') {
        if (status != 0 && status != undefined) {
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:#4AAF33;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../../static/images/ok.png" style="vertical-align: middle;margin-right:5px;" alt="OK，"/>' + txt + '</div>';
        } else {
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:rgba(255, 16, 8, 0.46);border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../../static/images/err5.png" style="vertical-align: middle;margin-right:5px;" alt="Error，"/>' + txt + '</div>';
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

function queryAccountInfo(a) {
    queryShopsAccountInfo(a);
}

function reset() {
    $("#username").val("");
    $("#userRole").val("");
    $("#accountType").val("");

}

var getEventTarget = function(){
    if(window.event){
        return window.event;
    }else{
        var e = arguments.callee.caller;
        while(e.caller!=null){
            e = e.caller;
        }
        return e.arguments[0];
    }
}

function openInfoDetail(obj) {
    $(".tip").fadeIn(200);
    userName=$(obj).parent().parent().find("#userName").text();
    queryUserAccountInfoDetailJson(1,userName);


}




