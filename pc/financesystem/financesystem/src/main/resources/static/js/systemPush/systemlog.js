var countNum;


$(function(){
    searchInfo(1);
})


function searchInfo(clickPage){
    var txt = "";
    if(clickPage == undefined || null == clickPage || "" == clickPage){
        clickPage = 1;
    }
    //执行查询商家订单详情的方法
    $.ajax({
        type:"GET",
        url:"/system/findAllSystemlog",
        dataType:"json",
        async: false,
        data:{
            page:clickPage
        },success:function(data){
            countNum=data.totalCount;
            $("#myBody").html("");
            if(data.resultList.length>0){
                for(var i=0;i<data.resultList.length;i++){
                    txt += "<tr><td>"
                        +(i+1)+
                        "</td><td>"
                        +data.resultList[i].user_name+
                        "</td><td>"
                        +data.resultList[i].log_info+
                        "</td><td>"
                        +data.resultList[i].create_time+
                        "</td></tr>"
                }
                $("#myBody").html(txt);
            }else{
                showMessage("暂无数据");
            }
        }
    });
   fy(countNum , clickPage , "fy" , "countNumber1" , "dpage");
}