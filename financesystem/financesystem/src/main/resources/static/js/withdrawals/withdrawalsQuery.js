/**
 * Created by Administrator on 2017/2/16.
 */
var countNum;
var countNum2;
var stayWithawals = 0;
var stayMoney = 0;
var accountId = "";
var accountType = "";
$(function(){
    searchInfo(1);

});


function  getCountDetail(accountType,accountId){
    $.ajax({
        type:"GET",
        url:"/withdrawals/getCountDetail",
        dataType:"json",
        data:{
            accountType:accountType,
            accountId:accountId
        },success:function(data){
            countNum2 = data.description;
        }
    });
}
/**
 * 点击显示商家或者用户的订单详细信息，并可以使用Excel导出
 * @param obj
 */
function showOrderDetail(obj){
    var accountId1 = $(obj).parents("tr").find("td").get(2).innerHTML;
    var accountType1 =$(obj).parents("tr").find("td").get(3).innerHTML;
    var txt = "";
    if(accountId1 != undefined && "" != accountId1 && null != accountId1 && accountType1 != undefined && "" != accountType1 && null != accountType1){
        accountId = accountId1;
        accountType = accountType1;
    }
    getCountDetail(accountType,accountId);
    getDetailByPage(1);
}

/**
 * 查看说明
 */
function showremark(data){
    $('#tipinfo').fadeIn();
    $('#tipinfotext').html($(data).next().val());
}



function getDetailByPage(clickPage){
    var txt = "";
    if(clickPage == undefined || null == clickPage || "" == clickPage){
        clickPage = 1;
    }
    //执行查询商家订单详情的方法
    $.ajax({
        type:"GET",
        url:"/withdrawals/getOrderDetail",
        dataType:"json",
        async: false,
        data:{
            accountType:accountType,
            accountId:accountId,
            pageNum:clickPage
        },success:function(data){
            $("#accountDetail").html("");
            if(data.resultList.length>0){
                for(var i=0;i<data.resultList.length;i++){
                    txt += "<tr><td>"
                        +(i+1)+
                        "</td><td>"
                        +data.resultList[i].orderNo+
                        "</td><td>"
                        +data.resultList[i].orderType+
                        "</td><td>"
                        +data.resultList[i].orderTitle+
                        "</td><td>"
                        +data.resultList[i].shopUserId+
                        "</td><td>"
                        +data.resultList[i].shopUserRoleType+
                        "</td><td>"
                        +data.resultList[i].userId+
                        "</td><td>"
                        +data.resultList[i].userRoleType+
                        "</td><td>"
                        +data.resultList[i].expressType+
                        "</td><td>"
                        +data.resultList[i].expressFee+
                        "</td><td>"
                        +data.resultList[i].message+
                        "</td><td>"
                        +data.resultList[i].remark+
                        "</td><td>"
                        +data.resultList[i].isInvoice+
                        "</td><td>"
                        +data.resultList[i].invoiceTitle+
                        "</td><td>"
                        +data.resultList[i].orderAmount+
                        "</td><td>"
                        +data.resultList[i].voucher+
                        "</td><td>"
                        +data.resultList[i].payableAmount+
                        "</td><td>"
                        +data.resultList[i].realAmount+
                        "</td><td>"
                        +data.resultList[i].status+
                        "</td><td>"
                        +data.resultList[i].addTime+
                        "</td><td>"
                        +data.resultList[i].confirmTime+
                        "</td><td>"
                        +data.resultList[i].invalidTime+
                        "</td><td>"
                        +data.resultList[i].completeTime+
                        "</td><td>"
                        +data.resultList[i].paymentId+
                        "</td><td>"
                        +data.resultList[i].paymentFee+
                        "</td><td>"
                        +data.resultList[i].paymentStatus+
                        "</td><td>"
                        +data.resultList[i].paymentTime+
                        "</td><td>"
                        +data.resultList[i].settlementStatus+
                        "</td><td>"
                        +data.resultList[i].settlementTime+
                        "</td><td>"
                        +data.resultList[i].isDelete+
                        "</td><td>"
                        +data.resultList[i].rejectRemark+
                        "</td></tr>"
                }
                $(".tip").fadeIn();
                $("#accountDetail").html(txt);
            }else{
                qikoo.dialog.alert("暂无数据");
            }
        }
    });
    fy(countNum2 , clickPage , "fy1" , "countNumber1" , "dpage1");
}
function closeWindow(){
    $(".tip").fadeOut(200);
}
/**
 * 查询操作
 */
function searchInfo(clickPage){
    layer.load();
    var txt = "";
    var id = $("#withdrawalsNo").val();
    var userType = $("#userRoleType").val();
    var status = $("#withdrawalsStatus").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    if(clickPage == null || undefined == clickPage || "" == clickPage){
        clickPage = 1;
    }
    $.ajax({
        type:"GET",
        url:"/withdrawals/getWithDrawals",
        dataType:"json",
        data:{
            withDrawalsId:id,
            accountType:userType,
            status:status,
            startTime:startTime,
            endTime:endTime,
            pageNum:clickPage
        },success:function(data){
            //console.log(data);
            if(data.description == "SUCCESS"){
                getCount();
                for(var i=0;i<data.resultList.length;i++){
                    userType= data.resultList[i].userRoleTyoe;
                    status = data.resultList[i].status;
                    txt += "<tr>" +
                        "<td><input type='checkbox' name='withrow' value='"+data.resultList[i].id+"'/><input type='hidden' value='"+status+"'/></td>" +
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
                        "</td><td>"
                        +data.resultList[i].complete_time+
                        "</td><td><input type='button' value='查看详情'   onclick='showremark(this)'><input type='hidden' value='"+data.resultList[i].remark+"'></td>" +
                        "<td>"+status+"</td><td><input type='button' value='查看订单' onclick='showOrderDetail(this)'></td></tr>";
                }


                getCountAndMoney();
                txt +="<tr  style='height: 10px;border-top: dashed black 1px;color: #ff3b76;font-weight: bold'>"
                    +"<td>统计</td>"
                    +"<td colspan='5' style='text-align: center'>待提现总数量</td>"
                    +"<td id='todayAmount' style='text-align: center'>"+stayWithawals+"</td>"
                    +"<td colspan='6' style='text-align: center'>待提现总金额</td>"
                    +"<td id='toMorrow' style='text-align: center'>"+stayMoney+"</td>"
                    +"</tr>"
                $("#userAccountData").empty();
                $("#userAccountData").html(txt);
                fy(countNum , clickPage,"fy","countNumber" , "dpage");

                //关闭加载动画
                setTimeout(function(){
                    layer.closeAll('loading');
                }, 500);

            }
        }

    });
}
/*$.extend({
 ExtForm: function ($Url, $Form, Fun) {
 console.log($Url);
 $.ajax({
 url: $Url,//请求的url地址
 type: "POST",
 dataType: "json",//返回格式为json
 async: true,//请求是否异步，默认为异步，这也是ajax重要特性
 data: $Form.serialize(),
 success: function (data) { //提交成功的回调函数
 if (data.ErrCode == 0) {
 Fun(data);
 }
 },
 error: function (XmlHttpRequest, textStatus, errorThrown) { //错误
 //dialog({ title: '提示', content: "状态：" + textStatus + "；出错提示：" + errorThrown, okValue: '确定', ok: function () { } }).showModal();
 }
 });
 },
 ExtAjax: function (Url, Fun) {
 $.ajax({
 type: "get", url: Url, dataType: "json", success: function (data) {
 if (data.ErrCode == 0) {
 Fun(data);
 } else {
 if (data.Data == 'user') {
 login();
 } else {
 layer.msg(data.Message, { icon: 2 });
 }
 }
 }, error: function (msg) { }
 });
 },
 ExtHtml: function (Url, Obj) { $.ajax({ type: 'get', url: Url, async: true, dataType: "html", success: function (data) { if (Obj == '') return; $(Obj).empty(); $(Obj).html(data); } }); },
 });*/
/**
 * 根据条件查询的数据总条数
 */
function getCount(){
    var id = $("#withdrawalsNo").val();
    var userType = $("#userRoleType").val();
    var status = $("#withdrawalsStatus").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    //查询一下总的数据
    $.ajax({
        type:"GET",
        url:"/withdrawals/getCount",
        dataType:"json",
        async: false,
        data:{
            withDrawalsId:id,
            accountType:userType,
            status:status,
            startTime:startTime,
            endTime:endTime
        },success:function(data){
            if(data.description == "SUCCESS"){
                console.log(data);
                countNum = data.obj.remark;
            }
        }
    });
}
/**
 * 查询待提现用户数量和总金额
 */
function getCountAndMoney(){
    $.ajax({
        type:"GET",
        url:"/withdrawals/getCountAndMoney",
        dataType:"json",
        async: false,
        success:function(data){
            if(data.description == "SUCCESS"){
                stayWithawals = data.obj.remark;
                stayMoney = data.obj.amount;
            }
        }
    });

}
/**
 * 导出EXCEL
 */
function exportTable(){
    location.href="/toExcel/excelWithDraw?accountId="+accountId+"&accountType="+accountType;
}
/**
 * 重置操作
 */
function reset(){
    $("#withdrawalsNo").val("");
    $("#userRoleType").val("");
    $("#withdrawalsStatus").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}

/**
 * 修改提现状态
 */
function updatestatus(data){
    var status=$(data).val();
    var idlist="";
    var remark="";
    $('input[name="withrow"]:checked').each(function(index){
        idlist+=','+$(this).val();
    });
    idlist=idlist.substring(1,idlist.length);
    if (status=='退回'){
        status=2;
        remark="管理退回提现";
    }else if (status=='冻结'){
        status=3;
        remark="管理冻结提现 ";
    }else if (status=='通过'){
        status=4;
        remark="审核通过(处理中)";
    }

    $.ajax({
        type:"GET",
        url:"/withdrawals/HttpWithdrawals",
        dataType:"json",
        data:{
            id:idlist,
            stats:status,
            remark:remark
        },
        success:function(data){
            if (data.description=="success"){
                qikoo.dialog.alert("操作成功");
                searchInfo(1);
            }else if (data.description=="err"){
                qikoo.dialog.alert("服务器发生未知错误,请联系管理员");
            }else{
                qikoo.dialog.alert(data.description);
            }
        }
    })

}



$(function(){
    /**
     * checkbox绑定
     */
    $("#checkAll").click(function() {
        $('input[name="withrow"]').attr("checked",this.checked);
    });

});






