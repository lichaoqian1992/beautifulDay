/**
 * Created by Administrator on 2017/2/24.
 */
/**全局变量*/
var countNum = "";
/**页面初始加载*/
$(function(){
    searchInfo(1);
});
/**查询商家信息*/
function searchInfo(clickPage){
    var txt = "";
    getCount();
    if(clickPage == undefined || null == clickPage || "" == clickPage){
        clickPage = 1;
    }
    var shopName = $("#shopName").val();
    $.ajax({
        type:"GET",
        url:"/dtOrders/getCompanyInfo",
        dataType:"json",
        async: false,
        data:{
            shopName:shopName,
            pageNum:clickPage
        },success:function(data){
            $("#userAccountData").html("");
            console.log(data);
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    for(var i=0;i<data.resultList.length;i++){
                        txt += "<tr><td>"
                                +(i+1)+
                               "</td><td>"
                                +data.resultList[i].userId+
                                "</td><td>"
                                +data.resultList[i].userName+
                                "</td><td class='myTd'>"
                                +data.resultList[i].content+
                                "</td><td class='myTd' onclick='showPic(this)'>"
                                +data.resultList[i].pics+
                                "</td><td>"
                                +data.resultList[i].tag+
                                "</td><td class='myTd' onclick='showPic(this)'>"
                                +data.resultList[i].pcLogo+
                                "</td><td>"
                                +data.resultList[i].mobile+
                                "</td><td>"
                                +data.resultList[i].address+
                                "</td><td>"
                                +data.resultList[i].area+
                                "</td><td>"
                                +data.resultList[i].telephone+
                                "</td><td>"
                                +data.resultList[i].cardType+
                                "</td><td>"
                                +data.resultList[i].cardNum+
                                "</td><td class='myTd' onclick='showPic(this)'>"
                                +data.resultList[i].cardPic+
                                "</td><td>"
                                +data.resultList[i].legalPerson+
                                "</td><td>"
                                +data.resultList[i].legalTel+
                                "</td><td>"
                                +data.resultList[i].legalIdCard+
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
/**查询总的数据*/
function getCount(){
    var shopName = $("#shopName").val();
    $.ajax({
        type:"GET",
        url:"/dtOrders/getCount",
        dataType:"json",
        data:{
            shopName:shopName
        },success:function(data){
            if(data.description == "SUCCESS"){
                countNum = data.obj.countNum;
            }
        }
    });
}
/**点击显示图片*/
function showPic(obj){
    $("#showPic").html("");
    $("#showPic").fadeIn(200);
    var url = obj.innerHTML;
    $("#showPic").html("<div class='tiptop'><span>图片展示</span><a onclick='closeWindow()'></a></div><div class='tipinfo'><span><img id='Img1' src='"+url+"'/></span></div>");
}
function closeWindow(){
    $(".tip2").fadeOut(200);
}
/**重制*/
function reset(){
    $("#shopName").val("");
}
