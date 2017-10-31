/**
 * 说明：调用该JS的时候，前端只需要调用fy(a,b,c)这个方法，a代表总数据，b代表点击的页码，c代表需要显示分页的div的Id
 * 需要注意的是：页面中查询的函数方法名称必须是searchInfo(b),b代表点击的页码。
 */
/**
 * 页面初始加载
 */
document.write("<script language=javascript src='../../static/js/dialog/js/qikoo.js'></script>");
document.write("<link rel='stylesheet' href='../../static/js/dialog/css/qikoo.css' type='text/css' />");
document.write("<link rel='stylesheet' href='../../static/js/dialog/css/store.css' type='text/css' />");
var countPage = 0;//总页数
var myPageID = "";//主要是页面涉及到2个分页的时候，用于区分目前操作的是哪一个分页
var myNowPageID = "";
var myCountID = "";
$(function(){

});
/**
 * 分页显示 ,目前每页显示20条数据
 * @param countNum 总条数
 * @param pageNum 当前页，也就是点击的页
 */
function fy(countNum,clickPage,pageID,countID,nowPageID){
    myPageID = pageID;
    myNowPageID = nowPageID;
    myCountID = countID;
    var txt = "";
    var txt1 = "";
    var txt2 = "";
    var txt3 = "";
    if(clickPage == undefined || "" == clickPage || null == clickPage || clickPage == 1){
        clickPage = 1;
    }
    txt = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">1</a></li><li class="paginItem" style="float: left"><a style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">......</a></li>';
    //算出总页数
    if(countNum>0){
        if(countNum%20>0){
            countPage = parseInt(countNum/20)+1;
        }else{
            countPage = parseInt(countNum/20);
        }
        if(countPage<=6){
            for(var j=1;j<=countPage;j++){
                txt2 += '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+j+'</a></li>';
            }
        }else{
            if(clickPage>=3 && clickPage<countPage-4){
                txt1 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+clickPage+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(clickPage)+1)+'</a></li>';
                txt3 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(countPage-2)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-1)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+countPage+'</a></li>';
                txt2 = txt+txt1 +'<li class="paginItem" style="float: left"><a href="javascript:;" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">......</a></li>'+txt3;
            }else if(clickPage>=countPage-4){
                txt3 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-4)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-3)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-2)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-1)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+countPage+'</a></li>';
                txt2 = txt+txt3;
            }else if(clickPage == 1){
                txt1 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+1+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+2+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+3+'</a></li>';
                txt3 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(countPage-2)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(countPage-1)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+countPage+'</a></li>';
                txt2 = txt1 +'<li class="paginItem" style="float: left"><a href="javascript:;" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">......</a></li>'+txt3;
            }else{
                txt1 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+1+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+2+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+3+'</a></li>';
                txt3 = '<li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-2)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+(parseInt(countPage)-1)+'</a></li><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">'+countPage+'</a></li>';
                txt2 = txt1+'<li class="paginItem" style="float: left"><a href="javascript:;" style="float:left;width:31px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;border-left:none;color:#3399d5;">......</a></li>'+txt3;
            }
        }
    }
    $("#"+myPageID).empty();
    $("#"+myPageID).append('<div class="message" style="height: 30px;margin-top: 10px;">共<i style="color: blue;" id="'+myCountID+'">'+countNum+'</i>条记录，当前显示第&nbsp;<i style="color: blue;" id="'+myNowPageID+'">'+clickPage+'&nbsp;</i>页</div>' +
        '<ul style="right:16px;margin-top:10px;float: right;margin-top: -25px;"><li class="paginItem" style="float: left"><a href="javascript:;" onclick="tz1(this)"  style="float:left;width:61px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;">上一页</a></li>'+txt2+'<li class="paginItem"  style="float: left"><a href="javascript:;" onclick="tz1(this)" style="float:left;width:61px;height:28px;border:1px solid #DDD; text-align:center;line-height:30px;">下一页</a></li></ul>');
}
/**
 * 点击页数，返回一个点击的页码
 */
function tz1(obj){
    var nowPage = $("#"+myNowPageID).text().trim();//当前页
    var clickPage = $(obj).text();//点击的页
    var countNum = $("#"+myCountID).text().trim()//总数据
    if(clickPage == "上一页"){
        if(nowPage == 1){
            qikoo.dialog.alert("操作错误！已经是第一页了！");
            //showMessage('操作错误！已经是第一页了！');
            clickPage = 1;
            return;
        }else{
            clickPage = parseInt(nowPage) -1;
        }
    }else if(clickPage == "下一页"){
        if(nowPage == countPage){
            qikoo.dialog.alert("操作错误！已经是第最后一页了！");
            //showMessage('操作错误！已经是第最后一页了！');
            clickPage = countPage;
            return;
        }else{
            clickPage = parseInt(nowPage) +1;
        }
    }
    if(countNum>0){
        if (nowPage == clickPage) {
            qikoo.dialog.alert("操作错误！已经是第" + clickPage + "页了！");
            //showMessage('操作错误！已经是第' + clickPage + '页了！');
            return;
        }else{
            $("#"+myNowPageID).text(clickPage + " ");
            /**
             * 查询操作开始==========================================================>
             */
            if(myNowPageID == "dpage"){
                searchInfo(clickPage);
            }else{
                getDetailByPage(clickPage);
            }
            /**
             * 查询操作结束==========================================================>
             */
            //fy(countNum , clickPage , myPageID);
        }
    }
}
//消息提示框
function showTips(txt,time,status)
{
    var htmlCon = '';
    if(txt != ''){
        if(status != 0 && status != undefined){
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:#4AAF33;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../static/images/ok.png" style="vertical-align: middle;margin-right:5px;" alt="OK，"/>'+txt+'</div>';
        }else{
            htmlCon = '<div class="tipsBox" style="width:260px;padding:10px;background-color:rgba(255, 16, 8, 0.46);border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="../../static/images/ts.png" style="vertical-align: middle;margin-right:5px;" alt="Error，"/>'+txt+'</div>';
        }
        $('body').prepend(htmlCon);
        if(time == '' || time == undefined){
            time = 1500;
        }
        setTimeout(function(){ $('.tipsBox').remove(); },time);
    }
}
/**
 * 提示框
 */
function showMessage(str){
    $(".hint").css({"display":"block"});
    $(".box").css({"display":"block"});
    $("#hint-in2").html(str);
}
/**
 * 隐藏提示框
 */
function closeMessage(){
    $(".hint").css({"display":"none"});
    $(".box").css({"display":"none"});
}
/**
 * 比较日期的大小
 */
function checkDate(){
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    if(startTime != "" && endTime != ""){
        if(new Date(startTime).getTime() >= new Date(endTime).getTime()){
            showMessage("开始日期不能大于结束日期");
            $("#startTime").val("");
            $("#endTime").val("");
            return;
        }
    }
}