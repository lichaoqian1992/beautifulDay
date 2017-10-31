/**
 * 商家订单信息和商家基本信息
 * Created by Administrator on 2017/2/25.
 */
var countNum = "";
/**页面初始加载*/
$(function(){
    searchInfo(1);
})
/**查询方法*/
function searchInfo(clickPage){
    var txt = "";
    var shopName = $("#shopName").val();
    var orderStatus = $("#orderStatus").val();
    if(clickPage == undefined || null == clickPage || "" == clickPage){
        clickPage = 1;
    }
    getCount();
    $.ajax({
        type:"GET",
        url:"/dtOrders/getShopOrderInfo",
        dataType:"json",
        async:false,
        data:{
            shopName:shopName,
            orderStatus:orderStatus,
            pageNum:clickPage
        },success:function(data){
            $("#userAccountData").html("");
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    console.log(data);
                    for(var i=0;i<data.resultList.length;i++){
                        txt += "<tr><td>"
                                +(i+1)+
                                "</td><td>"
                                +data.resultList[i].userId+
                                "</td><td>"
                                +data.resultList[i].userName+
                                "</td><td>"
                                +data.resultList[i].area+
                                "</td><td>"
                                +data.resultList[i].mobile+
                                "</td><td>"
                                +data.resultList[i].percentage+
                                "</td><td>"
                                +data.resultList[i].orderNo+
                                "</td><td>"
                                +data.resultList[i].orderType+
                                "</td><td>"
                                +data.resultList[i].orderTitle+
                                "</td><td>"
                                +data.resultList[i].orderAmount+
                                "</td><td>"
                                +data.resultList[i].payableAmount+
                                "</td><td>"
                                +data.resultList[i].realAmount+
                                "</td><td>"
                                +data.resultList[i].status+
                                "</td><td>"
                                +data.resultList[i].paymentStatus+
                                "</td><td>"
                                +data.resultList[i].settlementStatus+
                                "</td><td>"
                                +data.resultList[i].settlementTime+
                                "</td></tr>"
                    }
                    $("#userAccountData").html(txt);
                }else{
                    qikoo.dialog.alert("此条件查询无记录");
                }
                fy(countNum,clickPage,"fy","countNumber","dpage");
            }
        }
    });
}
/**查询总数方法*/
function getCount(){
    var shopName = $("#shopName").val();
    var orderStatus = $("#orderStatus").val();
    $.ajax({
        type:"GET",
        url:"/dtOrders/getOrderCount",
        dataType:"json",
        data:{
            shopName:shopName,
            orderStatus:orderStatus
        },success:function(data){
            countNum = data.obj.countNum;
        }
    });
}
/**导出EXCEl*/
function exportTable(){
    location.href="/toExcel/excelOrders?shopName="+$("#shopName").val()+"&orderStatus="+$("#orderStatus").val();
}
/**重置*/
function reset(){
    $("#shopName").val("");
    $("#orderStatus").val("");
}
