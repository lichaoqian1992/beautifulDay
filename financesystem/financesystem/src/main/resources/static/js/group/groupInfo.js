/**
 * 集团账户
 * Created by liuyingjie on 2017/3/3.
 */
$(function(){
    searchInfo(1);
});
function searchInfo(clickPage){
    var txt = "";
    var groupName = $("#groupName").val();
    var managerType = $("#managerType").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    $("#userAccountData").html("");
    if(clickPage == undefined){
        clickPage = 1;
    }
    $.ajax({
        type:"get",
        url:"/group/queryGroupInfo",
        dataType:"json",
        data:{
            groupName:groupName,
            managerType:managerType,
            startTime:startTime,
            endTime:endTime,
            pageNum:clickPage
        },success:function(data){
            console.log(data);
            if(data.description == "SUCCESS"){
                if(data.resultList.length>0){
                    for(var i=0;i<data.resultList.length;i++){
                        txt +="<tr><td><input name='checkAll' type='checkbox' onclick='selectAll()'/></td><td>"
                            +(i+1)+
                            "</td><td>"
                            +data.resultList[i].userId+
                            "</td><td>"
                            +data.resultList[i].roleType+
                            "</td><td>"
                            +data.resultList[i].roleValue+
                            "</td><td>"
                            +data.resultList[i].userName+
                            "</td><td>"
                            +data.resultList[i].shopCount+
                            "</td><td>"
                            +data.resultList[i].settledCount+
                            "</td><td>"
                            +data.resultList[i].notAllowAmount+
                            "</td><td>"
                            +data.resultList[i].managerType+
                            "</td><td>"
                            +data.resultList[i].addTime+
                            "</td><td>"
                            +data.resultList[i].updateTime+
                            "</td><td>"
                            +data.resultList[i].updateStatus+
                            "</td><td>"
                            +data.resultList[i].groupStatus+
                            "</td></tr>"
                    }
                    $("#userAccountData").html(txt);
                }else{
                    qikoo.dialog.alert("此条件查询无记录");
                }
            }
            fy(data.totalCount,clickPage,"fy","countNumber","dpage");
        }
    });
}

function reset(){
    $("#groupName").val("");
    $("#managerType").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}
