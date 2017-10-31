/**
 * Created by pudding on 2017-4-25.
 */

/**
 * 资金流动详细信息
 * @param pages
 */
function bd(pages){
    //var clickPage=$("#page").html();
    //if ($(pages).html()=="GO"){
    //    clickPage=$('#findpages').val();
    //    if (clickPage<1){
    //        clickPage=1;
    //    }
    //    if (clickPage>$("#totalpage").val()){
    //        clickPage=$("#totalpage").val();
    //    }
    //}else if ($(pages).html()=="首页"){
    //    clickPage=1;
    //}else if ($(pages).html()=="尾页"){
    //    clickPage=$('#totalpage').val();
    //}else if ($(pages).html()=="上一页"){
    //    clickPage=$('#page').html()-1;
    //    if (clickPage<1){
    //        clickPage=1;
    //    }
    //}else if ($(pages).html()=="下一页"){
    //    clickPage=$('#page').html()-(-1);
    //    if (clickPage>$("#totalpage").val()){
    //        clickPage=$("#totalpage").val();
    //    }
    //}
    var userid=$("#userid").val();
    var usertype=$('#usertype').val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var txt="";
    var wstatus=$('#wstatus').val();
    $.ajax({
        type:"GET",
        url:"/finance/withdrawals/getAccountLog",
        dataType:"json",
        data:{
            page:'1',
            userid:userid,
            usertype:usertype,
            startTime:startTime,
            endTime:endTime,
            wstatus:wstatus
        },
        success:function(data){
            for (var i=0;i<data.pageRecord.list.length;i++){
                var value=data.pageRecord.list[i].val;
                var href="/finance/withdrawals/infoalso?userId="+data.pageRecord.list[i].order_user_id+"&orders_old="+data.pageRecord.list[i].order_no+"";
                if (data.pageRecord.list[i].order_user_id!="-1"){
                    var dfzh="<a style='cursor: pointer' href='"+href+"' target='_blank'>对方账户</a>"
                }else{
                    var dfzh=""
                }

                if (value>=0){
                    value="<td style='color: green'>+"+data.pageRecord.list[i].val+"</td>";
                }else{
                    value="<td style='color: red'>"+data.pageRecord.list[i].val+"</td>";
                }
                txt+="<tr><td>"+data.pageRecord.list[i].add_time+
                    "</td><td>" +data.pageRecord.list[i].old_value+
                    "</td>"+value+
                    "<td>" +data.pageRecord.list[i].new_value+
                    "</td><td>" +data.pageRecord.list[i].type+
                    "</td><td>" +data.pageRecord.list[i].order_no+dfzh+
                    "</td><td>" +data.pageRecord.list[i].remark+
                    "</td></tr>";

            }
            //$("#number").html(data.pageRecord.totalRow);
            //$("#page").html(data.pageRecord.pageNumber);
            //$("#totalpage").val(data.pageRecord.totalPage);
            $("#userAccountData").empty();
            $("#userAccountData").html(txt);

        }
    });
}



//查询代金券交易明细
function  findVouchar(vpages) {
    var userid = $("#userid").val();
    var startTime = $("#vstartTime").val();
    var endTime = $("#vendTime").val();
    var txt = "";
    //var clickPage = $("#vpage").html();
    //if ($(vpages).html() == "GO") {
    //    clickPage = $('#vfindpages').val();
    //    if (clickPage < 1) {
    //        clickPage = 1;
    //    }
    //    if (clickPage > $("#vtotalpage").val()) {
    //        clickPage = $("#vtotalpage").val();
    //    }
    //} else if ($(vpages).html() == "首页") {
    //    clickPage = 1;
    //} else if ($(vpages).html() == "尾页") {
    //    clickPage = $('#vtotalpage').val();
    //} else if ($(vpages).html() == "上一页") {
    //    clickPage = $('#vpage').html() - 1;
    //    if (clickPage < 1) {
    //        clickPage = 1;
    //    }
    //} else if ($(vpages).html() == "下一页") {
    //    clickPage = $('#vpage').html() - (-1);
    //    if (clickPage > $("#vtotalpage").val()) {
    //        clickPage = $("#vtotalpage").val();
    //    }
    //}
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/getVoucherLog",
        dataType: "json",
        data: {
            //page: '1',
            userid: userid,
            startTime: startTime,
            endTime: endTime
        }, success: function (data) {
            for (var i = 0; i < data.pageRecord.list.length; i++) {
                var value = data.pageRecord.list[i].val;
                var href = "/finance/withdrawals/infoalso?userId=" + data.pageRecord.list[i].order_user_id + "&orders_old=" + data.pageRecord.list[i].order_no + "";
                if (data.pageRecord.list[i].order_user_id != "-1") {
                    var dfzh = "<a style='cursor: pointer' href='" + href + "' target='_blank'>对方账户</a>"
                } else {
                    var dfzh = ""
                }

                if (value >= 0) {
                    value = "<td style='color: green'>+" + data.pageRecord.list[i].val + "</td>";
                } else {
                    value = "<td style='color: red'>" + data.pageRecord.list[i].val + "</td>";
                }
                txt += "<tr><td>" + data.pageRecord.list[i].add_time +
                    "</td><td>" + data.pageRecord.list[i].old_value +
                    "</td>" + value +
                    "<td>" + data.pageRecord.list[i].new_value +
                    "</td><td>" + data.pageRecord.list[i].type +
                    "</td><td>" + data.pageRecord.list[i].order_no + dfzh +
                    "</td><td>" + data.pageRecord.list[i].remark +
                    "</td></tr>";
            }
            //$("#vnumber").html(data.pageRecord.totalRow);
            //$("#vpage").html(data.pageRecord.pageNumber);
            //$("#vtotalpage").val(data.pageRecord.totalPage);
            $("#vuserAccountData").empty();
            $("#vuserAccountData").html(txt);
        }

    })
}

$(function() {
    /**
     * 修改提现手续费
     */
    $('.counterFee').click(function(){
        layer.open({
            type: 1,
            title: '修改提现手续费',
            btn: ['确认', '取消'], //按钮
            shadeClose: true,
            shade: 0.8,
            area: ['580px', '280px'],
            content: '<div class="layer_box_content"><div class="withdrawalDepositForm_decide_radio"> <label> <input type="radio" name="counterFee"  checked="checked" value="0"/>默认手续费规则执行 </label> <label> <input type="radio" name="counterFee" value="1"/>免手续费 </label> <label class="color_red">(此修改下次提现生效)</label> </div></div>', //iframe的url
            yes: function() {
                //获取手续费状态
                var stus=$('input[name="counterFee"]:checked').val();
                //获取用户id 类型
                var userId=$('#userid').val();
                var usertype=$('#usertype').val();



                //执行设置手续费规则
                $.ajax({
                    type: "GET",
                    url: "/finance/withdrawals/CounterFee",
                    dataType: "json",
                    data: {
                        stus:stus,
                        userId:userId,
                        usertype:usertype
                    }, success: function (data) {
                        if (data.isok=="success"){
                            layer.msg("修改成功!",{
                                icon:1
                            });
                        }else{
                            layer.msg("修改失败!",{
                                icon:2
                            });
                        }
                        //关闭操作窗口
                        //layer.closeAll();
                    }
                });

            }
        });
    });

    bd();
    findVouchar();
    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });

    $(".withdrawalDepositForm_decide_radio input[type='radio']").click(function(){
        var istype=$(this).attr("istype");

        if(istype=='withdrawal_success'){
            $(".withdrawalDepositForm_decide_textarea_box").addClass("hide");
            $('#alert').val("");
        }else{
            $(".withdrawalDepositForm_decide_textarea_box").removeClass("hide");
        }

    });


    /**
     * 返回
     */
    $('#close').click(function(){
        location.href="/finance/withdrawals/record";
    });

    /**
     *点击确认时
     */
    $('#commit').click(function(){
        var istype=$(".withdrawalDepositForm_decide_radio input[type='radio']:checked").attr("istype")
        if ($('#userun').val()=='0'){
            var ptext="提示：该提现账户已被冻结，不能进行一下操作！";
            $(".withdrawalDepositForm_decide p").text(ptext);
            return;
        }

        var idlist=$("#id").val();
        if(istype=="withdrawal_success"){
            var remark="提现完成";
            $.ajax({
                type: "GET",
                url: "/finance/withdrawals/multyApproval",
                dataType: "json",
                data: {
                    id: idlist,
                    status: 5,
                    remark: remark,
                    confirmwhether:1
                },
                success: function (data) {
                    if (data.isok == "success") {
                        layer.msg('提现已成功', {icon: 1,time: 1500});
                        location.href="/finance/withdrawals/record";
                    } else if (data.isok == "err") {
                        layer.msg('服务器发生未知错误,请联系管理员', {icon: 2,time: 1500});
                    } else {
                        layer.msg(data.isok, {icon: 2,time: 1500});
                    }
                }
            });
        }else if(istype=="withdrawal_failure"){
            var remark="提现失败";
            var alert=$('#alert').val();
            $.ajax({
                type: "GET",
                url: "/finance/withdrawals/multyApprovalerr",
                dataType: "json",
                data: {
                    id: idlist,
                    status: 2,
                    remark: remark,
                    confirmwhether:2,
                    alert:alert
                },
                success: function (data) {
                    if (data.isok == "success") {
                        layer.msg('提现已退回', {icon: 1,time: 1500});
                        location.href="/finance/withdrawals/record";
                    } else if (data.isok == "err") {
                        layer.msg('服务器发生未知错误,请联系管理员', {icon: 2,time: 1500});
                    } else {
                        layer.msg(data.isok, {icon: 2,time: 1500});
                    }
                }
            });
        }


    });
});