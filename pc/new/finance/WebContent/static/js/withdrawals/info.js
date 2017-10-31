/**
 * Created by pudding on 2017-4-22.
 */
var sxf='<div class="layer_box_content"><div class="withdrawalDepositForm_decide_radio"> <label> <input type="radio" name="counterFee"  checked="checked" value="0"/>默认手续费规则执行 </label> <label> <input type="radio" name="counterFee" value="1" />免手续费 </label> <label class="color_red">(此修改下次提现生效)</label> </div></div>';

$(function() {
    var userid=$("#userid").val();
    var roletype=$("#usertype").val();
    /**
     * 查询此用户当前手续费
     */
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/staticSxf",
        data:{
            userid:userid,
            roletype:roletype
        },
        success:function(data){
            if (data.w_commission=='1'){
                sxf='<div class="layer_box_content"><div class="withdrawalDepositForm_decide_radio"> <label> <input type="radio" name="counterFee"   value="0"/>默认手续费规则执行 </label> <label> <input type="radio" name="counterFee" checked="checked" value="1" />免手续费 </label> <label class="color_red">(此修改下次提现生效)</label> </div></div>';
            }
        }
    });


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
                content: sxf, //iframe的url
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





    /**
     * 返回
     */
    $('#close').click(function(){
        history.back(-1);
    });

    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });
    bd();
    findVouchar();
});
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
    var wstatus=$("#wstatus").val();
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

    })


}

