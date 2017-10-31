/**
 * Created by pudding on 2017-1-23.
 */

var userName="";
$(function () {

    $(".tablelink").click(function () {
        $(".tip").fadeIn(200);
        userName = "";
        userName = $(this).parent().parent().find("#userName").text();
        queryUserAccountInfoDetailJson(1, userName);
    });

    $(".tiptop a").click(function () {
        $(".tip").fadeOut(200);

    });

    //翻页
    $("#userAccountDetail li").click(function () {
        var valpage = $("#userAccountDetailPage").text();
        var count = $("#userAccountDetailCount").text();
        $("#userAccountDetailCount li").removeClass("current");
        $(this).addClass("current");
        if ($(this).text() == "...") {
            $("#userAccountDetail li").removeClass("current");
            $("#userAccountDetail li:eq(1)").addClass("current");
            return;
        }
        if ($(this).text() == "") {
            var val = $(this).find("span").attr("class");
            if (val == "pagepre") {
                var a1 = parseInt(valpage) - 1;
                if (a1 <= 0) {
                    showTips('操作错误！数据没有上一页！', 2000, 0);
                } else {
                    queryUserAccountInfoDetailJson(a1,userName);
                }
            } else {
                var a2 = parseInt(valpage) + 1;
                var a2 = a2 * 20 - 19;
                if (a2 <= count) {
                    queryUserAccountInfoDetailJson(parseInt(valpage) + 1,userName);
                } else {
                    showTips('操作错误！数据没有下一页！', 2000, 0);
                }
            }
        } else {
            var currentPage = Number($(this).text()) * 20 - 19;
            if (currentPage <= count) {
                queryUserAccountInfoDetailJson($(this).text(),userName);

            } else {
                showTips('操作错误！数据没有下一页！', 2000, 0);
                $("#userAccountDetail li").removeClass("current");
                $("#userAccountDetail li:eq(1)").addClass("current");
            }


        }





    });

    $("#detailQuery").click(function(){
        queryUserAccountInfoDetailJson(1,userName);




    });


});


function queryUserAccountInfoDetailJson(page, userName) {
    $.ajax({
        url: '/info/queryUserAccountInfoDetailJson/' + userName + '/' + page + '',
        type: 'GET',
        data: {"orderNo": $("#orderNo").val(), "roleType": $("#userRoleType").val()},
        dataType: "json",
        success: function (data) {
            $("#userAccountDataDetail").remove();
            $("#userAccountDetailCount").text(data.totalCount);
            $("#userAccountDetailPage").text(data.page);
            $("#accountDetail").append("<tbody id='userAccountDataDetail'></tbody>");
            for (i = 0; i < data.resultList.length; i++) {
                $("#userAccountDataDetail").append("<tr style='height: 10px'>" +
                    "<td>" + Number(i + 1) + "</td>" +
                    "<td>" + data.resultList[i].userName + "</td>" +
                    "<td>" + data.resultList[i].userRoleType + "</td>" +
                    "<td>" + data.resultList[i].accountId + "</td>" +
                    "<td>" + data.resultList[i].value + "</td>" +
                    "<td>" + data.resultList[i].type + "</td>" +
                    "<td>" + data.resultList[i].orderNo + "</td>" +
                    "<td>" + data.resultList[i].oldValue + "</td>" +
                    "<td>" + data.resultList[i].newValue + "</td>" +
                    "<td>" + data.resultList[i].transactionNo + "</td>" +
                    "<td>" + data.resultList[i].addTime + "</td></td>");
            }
            fy(data.totalCount,data.page,"fy1","countNumber1","dpage1");

        }
    });


}
function getDetailByPage(page){

    queryUserAccountInfoDetailJson(page, userName);
}
