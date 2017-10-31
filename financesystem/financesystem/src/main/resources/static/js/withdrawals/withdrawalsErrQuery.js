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
            console.log(data);
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
        url:"/withdrawals/getErrWithDrawals",
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
                        "</td><td>"+status+"</td><td style='color:red'>"+data.resultList[i].alter+"</td></tr>";
                }


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
        url:"/withdrawals/getErrCount",
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
 *管理员批量判定异常订单
 */
function Toexamine(){
    var arry = new Array();

    $('input[name="withrow"]:checked').each(function(index){
        arry.push($(this).val());
    });
    if(arry.length==0){
        qikoo.dialog.alert("请选择操作数据!");
        return;
    }
    $.ajax({
        type:"POST",
        traditional: true,
        url:"/withdrawals/Toexamine",
        dataType:"json",
        async: false,
        data:{
            idArry:arry
        },success:function(data){
            if (data.description=="success"){
                qikoo.dialog.alert("操作成功!");
                searchInfo(1);
            }else{
                qikoo.dialog.alert("服务器发生错误操作失败!");
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
    var info=$('.title').val();
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
    }

    $.ajax({
        type:"GET",
        url:"/withdrawals/FrozenOrReturn",
        dataType:"json",
        data:{
            id:idlist,
            stats:status,
            remark:remark,
            info:info
        },
        success:function(data){
            if (data.description=="success"){
                if (data.obj==""){
                    qikoo.dialog.alert("操作成功");
                }else{
                   //返回操作失败的提现
                    qikoo.dialog.alert(data.obj);
                }
                searchInfo(1);
                closeToexamine();
            }else if (data.description=="err"){
                qikoo.dialog.alert("服务器发生未知错误,请联系管理员");
                closeToexamine();
            }else{
                qikoo.dialog.alert(data.description);
                closeToexamine();
            }
        }
    })

}

/**
 * 弹出操作窗
 */
function showToexamine(){
    $('.tip').fadeIn();
    $('.title').val();
}
function closeToexamine(){
    $(".tip").fadeOut(200);
}
$(function(){
    /**
     * checkbox绑定
     */
    $("#checkAll").click(function() {
        $('input[name="withrow"]').attr("checked",this.checked);
    });

});







