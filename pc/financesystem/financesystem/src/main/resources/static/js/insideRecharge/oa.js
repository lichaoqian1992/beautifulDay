/**
 * OA文件编号信息
 * Created by Administrator on 2017/3/6.
 */
$(function(){
    queryOaInfo(1);
});
/**
 * 新增OA编号信息
 */
function inputInfo(){
    var oaNo = $("#oaNo").val();
    var creater = $("#creater").val();
    $.ajax({
        type:"get",
        url:"/userRecharge/inputOaInfo",
        dataType:"json",
        data:{
            oaNo:oaNo,
            creater:creater
        },success:function(data){
            if(data.description == "SUCCESS"){
                showMessage("录入成功");
                $("#at1").removeClass("selected");
                $("#at2").addClass("selected");
                $("#tab1").hide();
                $("#tab2").show();
                queryOaInfo(1);
            }else if(data.description == "EXIT"){
                showMessage("数据已存在");
            }else if(data.description == "ERROR"){
                showMessage("参数错误");
            }
        }
    });
}
/**
 * 查询OA信息
 */
function queryOaInfo(clickPage){
    var txt = "";
    if(clickPage == undefined || null == clickPage || "" == clickPage){
        clickPage = 1;
    }
    $.ajax({
        type:"get",
        url:"/userRecharge/queryOaInfo",
        dataType:"json",
        data:{
            pageNum:clickPage
        },
        success:function(data){
            $("#myTbody").html("");
            if(data.description == "SUCCESS"){
                console.log(data);
                if(data.resultList.length>0){
                    for(var i=0;i<data.resultList.length;i++){
                        txt +="<tr><td>"
                            +(i+1)+
                            "</td><td>"
                            +data.resultList[i].oaNo+
                            "</td><td>"
                            +data.resultList[i].creater+
                            "</td><td>"
                            +data.resultList[i].createtime+
                            "</td><td>"
                            +data.resultList[i].remark+
                            "</td></tr>"
                    }
                    $("#myTbody").html(txt);
                    fy(data.totalCount,clickPage,"fy","countNumber","dpage");
                }else{
                    showMessage("此条件查询无记录");
                }
            }
        }
    });
}
