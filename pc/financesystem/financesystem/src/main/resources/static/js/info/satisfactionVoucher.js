/**
 * Created by Administrator on 2017/1/22.
 */
/*页面初始加载*/
var allContent="";
var countNum = 0;
var countPage = 0;
$(function(){
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
});
/**
 * 查询满意券的账户信息
 * @param clickPage
 */
function searchInfo(clickPage){
    var userName = $("#userName").val();
    var roleType = $("#roleType").val();
    var acountStatus = $("#acountStatus").val();
    var aval;
    var txt = "";
    if(clickPage == "" || undefined == clickPage || null == clickPage){
        aval = 1;
    }else{
        aval = clickPage;
    }
    getCount1();//查询数据总条数
    $.ajax({
        type: "GET",
        url: "/monthAndDailySheet/getSatisfactionVoucher",
        dataType: "json",
        //async:false,
        data:{userName:userName,roleType:roleType,pageNumber:aval,status:acountStatus},
        success: function (data) {
            if (data.statusEnum == "SUCCESS") {
                $("#myTable").empty();
                for(var i=0;i<data.resultList.length;i++){
                    status1 = data.resultList[i].status;
                    roleType1 = data.resultList[i].roleType;
                    switch (status1){
                        case  '1':
                            status1 = "正常";
                            break;
                        case '0':
                            status1 = "冻结";
                            break;
                        case '9':
                            status1 = "异常";
                            break;
                    }
                    switch (roleType1){
                        case  'buyer':
                            roleType1 = "用户";
                            break;
                        case 'shop':
                            roleType1 = "商家";
                            break;
                    }
                    txt += "<tr><td>"+(i+1)+"</td><td class='userId' style='display: none;'>"+data.resultList[i].userId+"</td><td>"+data.resultList[i].userName+"</td><td>"+roleType1+"</td><td>"+data.resultList[i].voucher+"</td><td>"+status1+"</td><td><a href='#' class='tablelink' onclick='getDetailInfo(this)'>交易明细</a></td></tr>";
                }
                $("#myTable").append(txt);
                fy(countNum,aval,"myPageInfo","countNumber" , "dpage");
            }
        }
    });
}
/**
 * 查询当前条件下的总数据
 */
function getCount1(){
    var userName = $("#userName").val();
    var roleType = $("#roleType").val();
    var acountStatus = $("#acountStatus").val();
    var txt2='<ul class="paginList" id="myPage"><li class="paginItem" id="countNumber"><a href="javascript:;"><span class="pagepre"></span></a></li>';
    var txt1="";
    var txt3="";
    $.ajax({
        type: "GET",
        url: "/monthAndDailySheet/getCountAndPage",
        dataType: "json",
        async:false,
        data:{userName:userName,roleType:roleType,status:acountStatus},
        success: function (data) {
            if (data.statusEnum == "SUCCESS") {
                //清空原来的分页
                $("#myPageInfo").empty();
                var count = data.resultList[0].count;//总数据
                countNum = count;
            }
        }
    });
}
/**
 * 查询交易明细
 * @param obj
 */
function getDetailInfo(obj){
    var txt = "";
    var count;
    var clickPage;
    $(".tip").fadeIn(200);
    var userId = $(obj).parent().parent().find('.userId').text();//用混编号
    $.ajax({
        type:'GET',
        url:'/monthAndDailySheet/getVoucherDetail',
        dataType:'json',
        data:{userId:userId},
        success:function(data){
            if(data.statusEnum == "SUCCESS"){
                $("#accountDetail").empty();
                $("#fy").empty();
                count = data.resultList.length;
                if(data.resultList.length == 0){
                    showMessage('该用户没有对应的交易详情记录');
                }
                //先存储数据===========查询的所有数据
                for(var n=0;n<count;n++){
                    /*txt += "<tr><td>"
                            +(n+1)+
                            "</td><td>"
                            +data.resultList[n].userId+
                            "</td><td>"
                            +data.resultList[n].userRoleType+
                            "</td><td>"
                            +data.resultList[n].orderNo+
                            "</td><td>"
                            +data.resultList[n].orderTitle+
                            "</td><td>"
                            +data.resultList[n].voucher+
                            "</td><td>"
                            +data.resultList[n].addTime+
                            "</td><td>"
                            +data.resultList[n].completeTime+
                            "</td><td></tr>"*/

                    allContent += 'userId='+data.resultList[n].userId+';userRoleType='+data.resultList[n].userRoleType+';orderNo='+data.resultList[n].orderNo+';orderTitle='+data.resultList[n].orderTitle+';voucher='+data.resultList[n].voucher+';addTime='+data.resultList[n].addTime+';completeTime='+data.resultList[n].completeTime+'};'
                    }
                //$("#accountDetail").html(txt);
                if(clickPage == undefined || null == clickPage || "" == clickPage){
                    clickPage = 1;
                }
                    fenyeShow(clickPage,count);
                }
            }
        });
}
function fenyeShow(pageClick,count){
    //分析数据
    var pageLength;
    var roleType1;
    var nLength = allContent.split("};")[0].split(";").length;
    for(var x=pageClick;x<parseInt(pageClick)+1;x++){
        var txt1="";
        if(count<20*x){
            pageLength = count;
        }else if(count > 20*x){
            pageLength=20*x;
        }else{
            pageLength=20;
        }
        var a=1;
        for(var y=20*x-19;y<=pageLength;y++){
            var txt="";
            for(var z=0;z<nLength;z++){
                roleType1 = allContent.split("};")[y-1].split(";")[z].split("=")[1];
                switch (roleType1){
                    case  'Buyer':
                        roleType1 = "用户";
                        break;
                    case  'buyer':
                        roleType1 = "用户";
                        break;
                    case 'shop':
                        roleType1 = "商家";
                        break;
                    case 'Shop':
                        roleType1 = "商家";
                        break;
                }
                txt+="<td>"+roleType1+"</td>";
            }
            txt1+="<tr><td>"+a+"</td>"+txt+"</tr>";//一行数据
            a++;
        }
        $("#accountDetail").html(txt1);
        fy2(count,pageClick,"fy");
    }
}
function fy2(countNum,clickPage,pageID){
    var txt = "";
    var txt1 = "";
    var txt2 = "";
    var txt3 = "";
    if(clickPage == undefined || "" == clickPage || null == clickPage || clickPage == 1){
        clickPage = 1;
    }
    txt = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">1</a></li><li class="paginItem" style="float: left"><a style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">......</a></li>';
    //算出总页数
    if(countNum>0){
        if(countNum%20>0){
            countPage = parseInt(countNum/20)+1;
        }else{
            countPage = parseInt(countNum/20);
        }
        if(countPage<=6){
            for(var j=1;j<=countPage;j++){
                txt2 += '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+j+'</a></li>';
            }
        }else{
            if(clickPage>=3 && clickPage<countPage-4){
                txt1 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+clickPage+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(clickPage)+1)+'</a></li>';
                txt3 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(countPage-2)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-1)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+countPage+'</a></li>';
                txt2 = txt+txt1 +'<li class="paginItem" style="float: left"><a href="javascript:;" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">......</a></li>'+txt3;
            }else if(clickPage>=countPage-4){
                txt3 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-4)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-3)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-2)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-1)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+countPage+'</a></li>';
                txt2 = txt+txt3;
            }else if(clickPage == 1){
                txt1 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+1+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+2+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+3+'</a></li>';
                txt3 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(countPage-2)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(countPage-1)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+countPage+'</a></li>';
                txt2 = txt1 +'<li class="paginItem" style="float: left"><a href="javascript:;" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">......</a></li>'+txt3;
            }else{
                txt1 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+1+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+2+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+3+'</a></li>';
                txt3 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-2)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-1)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+countPage+'</a></li>';
                txt2 = txt1+'<li class="paginItem" style="float: left"><a href="javascript:;" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">......</a></li>'+txt3;
            }
        }
    }
    $("#"+pageID).empty();
    $("#"+pageID).append('<div class="message" style="height: 30px;margin-top: 10px;">共<i style="color: blue;" id="countNumber2">'+countNum+'</i>条记录，当前显示第&nbsp;<i style="color: blue;" id="dpage2">'+clickPage+'&nbsp;</i>页</div>' +
        '<ul style="right:16px;margin-top:10px;float: right;margin-top: -25px;"><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz2(this)"  style="float:left;width:61px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;">上一页</a></li>'+txt2+'<li class="paginItem"  style="float: left"><a href="javascript:;" onclick="tz2(this)" style="float:left;width:61px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;">下一页</a></li></ul>');
}
/**
 *
 */
function tz2(obj){
    var nowPage = $("#dpage2").text().trim();//当前页
    var clickPage = $(obj).attr("text");//点击的页
    var countNum = $("#countNumber2").text().trim()//总数据
    if(clickPage == "上一页"){
        if(nowPage == 1){
            showMessage('操作错误！已经是第一页了！');
            clickPage = 1;
            return;
        }else{
            clickPage = parseInt(nowPage) -1;
        }
    }else if(clickPage == "下一页"){
        if(nowPage == countPage){
            showMessage('操作错误！已经是第最后一页了！');
            clickPage = countPage;
            return;
        }else{
            clickPage = parseInt(nowPage) +1;
        }
    }
    if(countNum>0){
        if (nowPage == clickPage) {
            showMessage('操作错误！已经是第' + clickPage + '页了！');
            return;
        }else{
            $("#dpage2").text(clickPage + " ");
            fenyeShow(clickPage , countNum);
        }
    }
}
//关闭弹出框
function closeWindow(){
    $(".tip").fadeOut(200);
}
function reset(){
    $("#userName").val("");
    $("#roleType").val("");
    $("#acountStatus").val("");
}
