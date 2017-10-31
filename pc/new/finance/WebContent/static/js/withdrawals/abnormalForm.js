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

    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });

    $(".withdrawalDepositForm_decide_radio input[type='radio']").click(function(){

        var istype=$(this).attr("istype"),
            placeholder="",ptext="";

        if(istype=="normal"){
            placeholder="输入原因";
        }else if(istype=="send_back"){
            placeholder="输入退回提现申请的原因";
            ptext="提示：退回提现申请，此次提现将失败";
        }else if(istype=="Freeze_money"){
            placeholder="输入冻结本次提现的原因";
            ptext="提示：冻结本笔提现，该笔提现将延后处理";
        }else if(istype=="frozen_account"){
            placeholder="输入冻结账户的原因";
            ptext="提示：冻结账户后，账户将不能进行任何资金操作";
        }
        $(".withdrawalDepositForm_decide_textarea").attr("placeholder",placeholder);
        $(".withdrawalDepositForm_decide p").text(ptext);
    });

    $(".withdrawalDepositForm_decide_textarea").keydown(function(event){
        textarea_len(this,event);
    });
    $(".withdrawalDepositForm_decide_textarea").click(function(event){
        textarea_len(this,event);
    });

    function textarea_len(_this,event){
        var key_code = event.keyCode;
        var maxlength=parseInt($(_this).attr("maxlength"));
        var len=$(_this).val().length;
        if(key_code==32){
            if(len==maxlength){
                $(".withdrawalDepositForm_decide_textarea_max em").text(maxlength);
            }else{
                $(".withdrawalDepositForm_decide_textarea_max em").text(len);
            }
        }else{
            var len=$(_this).val().length;
            $(".withdrawalDepositForm_decide_textarea_max em").text(len);
        }
    }

});


$(function () {
    //返回
    $('.closeinfo').click(function(){
        window.location.href="/finance/withdrawals/abnormal?nav=nav9";
    });
    bd();
    findVouchar();
})


/**
 * 资金流动详细信息
 * @param pages
 */
function bd(pages) {
    //var clickPage = $("#page").html();
    //if ($(pages).html() == "GO") {
    //    clickPage = $('#findpages').val();
    //    if (clickPage < 1) {
    //        clickPage = 1;
    //    }
    //    if (clickPage > $("#totalpage").val()) {
    //        clickPage = $("#totalpage").val();
    //    }
    //} else if ($(pages).html() == "首页") {
    //    clickPage = 1;
    //} else if ($(pages).html() == "尾页") {
    //    clickPage = $('#totalpage').val();
    //} else if ($(pages).html() == "上一页") {
    //    clickPage = $('#page').html() - 1;
    //    if (clickPage < 1) {
    //        clickPage = 1;
    //    }
    //} else if ($(pages).html() == "下一页") {
    //    clickPage = $('#page').html() - (-1);
    //    if (clickPage > $("#totalpage").val()) {
    //        clickPage = $("#totalpage").val();
    //    }
    //}
    var userid = $("#userid").val();
    var usertype=$('#usertype').val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var txt = "";
    var wstatus=$('#wstatus').val();
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/getAccountLog",
        dataType: "json",
        data: {
            page: '1',
            userid: userid,
            usertype:usertype,
            startTime: startTime,
            endTime: endTime,
            wstatus:wstatus
        },
        success: function (data) {
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
            //$("#number").html(data.pageRecord.totalRow);
            //$("#page").html(data.pageRecord.pageNumber);
            //$("#totalpage").val(data.pageRecord.totalPage);
            $("#userAccountData").empty();
            $("#userAccountData").html(txt);

        }
    });

}


//查询代金券交易明细
function  findVouchar(vpages){
    var userid=$("#userid").val();
    var startTime = $("#vstartTime").val();
    var endTime = $("#vendTime").val();
    var txt="";
    //var clickPage=$("#vpage").html();
    //if ($(vpages).html()=="GO"){
    //    clickPage=$('#vfindpages').val();
    //    if (clickPage<1){
    //        clickPage=1;
    //    }
    //    if (clickPage>$("#vtotalpage").val()){
    //        clickPage=$("#vtotalpage").val();
    //    }
    //}else if ($(vpages).html()=="首页"){
    //    clickPage=1;
    //}else if ($(vpages).html()=="尾页"){
    //    clickPage=$('#vtotalpage').val();
    //}else if ($(vpages).html()=="上一页"){
    //    clickPage=$('#vpage').html()-1;
    //    if (clickPage<1){
    //        clickPage=1;
    //    }
    //}else if ($(vpages).html()=="下一页"){
    //    clickPage=$('#vpage').html()-(-1);
    //    if (clickPage>$("#vtotalpage").val()){
    //        clickPage=$("#vtotalpage").val();
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
        },success:function(data) {
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

    });
}





/**
 * 修改提现状态
 */
function updatestatus() {
    var status = $('input[name="stuts"]:checked').val();
    var id = $('#id').val();
    var alerts = $('#yctextarea').val();


    //判定为冻结账户
    if (status == '冻结账户') {
        if (alerts == "" || alerts == null) {
            layer.msg("请输入操作原因!", {
                icon: 2
            });
            return;
        }
        $.ajax({
            url: "/finance/withdrawals/AccountStateFrozen",
            dataType: "json",
            data: {
                id: id,
                remark: alerts
            }, success: function (data) {
                if (data.isok == "success") {
                    layer.msg("此账户已冻结!", {
                        icon: 1
                    });
                    location.href = "/finance/withdrawals/abnormal?nav=nav9";
                } else {
                    layer.msg("冻结账户失败!", {
                        icon: 2
                    });
                }
            }
        });
        return;
    }

    //判定为正常时
    if (status == '正常') {
        $.ajax({
            type: "POST",
            traditional: true,
            url: "/finance/withdrawals/Toexamine",
            dataType: "json",
            async: false,
            data: {
                id: id,
                alert: alerts
            }, success: function (data) {
                if (data.isok == "success") {
                    layer.msg("操作成功!", {
                        icon: 1
                    });
                    location.href = "/finance/withdrawals/abnormal?nav=nav9";
                } else {
                    layer.msg("服务器发生错误操作失败!", {
                        icon: 2
                    });
                }
            }
        });
        return;
    }

    //冻结本笔提现
    if (status == '冻结本笔提现') {
        var status = 3;
        var remark = "管理冻结提现 ";
    } else if (status == '退回') {
        var status = 2;
        var remark = "管理退回提现 ";
    }
    if (alerts == "" || alerts == null) {
        layer.msg("请输入操作原因!", {
            icon: 2
        });
        return;
    }
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/Frozen",
        dataType: "json",
        data: {
            id: id,
            status: status,
            remark: remark,
            alert: alerts
        },
        success: function (data) {
            if (data.isok == "err") {
                layer.msg("服务器发生错误操作失败!", {
                    icon: 2
                });
            } else {
                if (data.isok == "") {
                    layer.msg("操作成功!", {
                        icon: 1
                    });
                    location.href = "/finance/withdrawals/abnormal?nav=nav9";
                } else {
                    layer.msg(data.isok, {
                        icon: 2
                    });
                }
            }
        }
    });





}