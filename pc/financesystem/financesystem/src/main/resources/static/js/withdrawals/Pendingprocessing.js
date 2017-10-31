var countNum;


$(function(){
    searchInfo(1);
})


function searchInfo(clickPage){
    var txt = "";
    if(clickPage == undefined || null == clickPage || "" == clickPage){
        clickPage = 1;
    }
    var withDrawalsId=$('#withdrawalsNo').val();
    //执行查询商家订单详情的方法
    $.ajax({
        type:"GET",
        url:"/withdrawals/getPendingprocessing",
        dataType:"json",
        async: false,
        data:{
            pageNum:clickPage,
            withDrawalsId:withDrawalsId
        },success:function(data){
            countNum=data.totalCount;
            $("#myBody").html("");
            if(data.resultList.length>0){
                for(var i=0;i<data.resultList.length;i++){
                    var userType= data.resultList[i].userRoleTyoe;
                    txt += "<tr>" +
                        "<td><input type='checkbox' name='withrow' value='"+data.resultList[i].id+"'/></td>" +
                        "<td>"
                        +(i+1)+
                        "</td><td>"
                        +data.resultList[i].userId+
                        "</td><td id='accountType'>"
                        +userType+
                        "</td><td>"
                        +data.resultList[i].accountId+
                        "</td><td>"
                        +data.resultList[i].withdrawalsNo+
                        "</td><td>"
                        +data.resultList[i].bankCard+
                        "</td><td>"
                        +data.resultList[i].bankName+
                        "</td><td>"
                        +data.resultList[i].amount+
                        "</td><td>"
                        +data.resultList[i].voucher+
                        "</td><td>"
                        +data.resultList[i].commission+
                        "</td><td>"
                        +data.resultList[i].totalMoney+
                        "</td><td>"
                        +data.resultList[i].add_time+
                        "</td><td> "+data.resultList[i].complete_time+"</td></tr>";
                }
                $("#myBody").html(txt);
            }else{
                showMessage("暂无数据");
            }
        }
    });
   fy(countNum , clickPage , "fy" , "countNumber1" , "dpage");
}


/**
 * 重置操作
 */
function reset(){
    $("#withdrawalsNo").val("");
}

$(function(){
    /**
     * checkbox绑定
     */
    $("#checkAll").click(function() {
        $('input[name="withrow"]').attr("checked",this.checked);
    });

    /**
     * 导出明细
     */
    $("#excl").click(function() {
        window.location.href="/toExcel/excelPendingprocessing";
    });

});

/**
 * 确认完成
 */
function updatestatus(){
    var idlist="";
    var remark="";
    $('input[name="withrow"]:checked').each(function(index){
        idlist+=','+$(this).val();
    });
    idlist=idlist.substring(1,idlist.length);

    $.ajax({
        type: "GET",
        url: "/withdrawals/HttpWithdrawals",
        dataType: "json",
        data: {
            id: idlist,
            stats: 5,
            remark: remark
        },
        success: function (data) {
            if (data.description == "success") {
                showMessage("操作成功");
                searchInfo(1);
            } else if (data.description == "err") {
                showMessage("服务器发生未知错误,请联系管理员");
            } else {
                showMessage(data.description);
            }
        }
    });

}