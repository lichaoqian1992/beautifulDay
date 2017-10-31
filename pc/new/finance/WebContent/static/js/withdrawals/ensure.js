/**
 * Created by pudding on 2017-4-25.
 */

//通过条件查询数据
function findpram(pages) {
    var clickPage=$("#page").html();
    if ($(pages).html()=="GO"){
        clickPage=$('#findpages').val();
        if (clickPage<1){
            clickPage=1;
        }
        if (parseInt(clickPage)>parseInt($("#totalpage").val())){
            clickPage=$("#totalpage").val();
        }
    }else if ($(pages).html()=="首页"){
        clickPage=1;
    }else if ($(pages).html()=="尾页"){
        clickPage=$('#totalpage').val();
    }else if ($(pages).html()=="上一页"){
        clickPage=$('#page').html()-1;
        if (clickPage<1){
            clickPage=1;
        }
    }else if ($(pages).html()=="下一页"){
        clickPage=$('#page').html()-(-1);
        if (clickPage>$("#totalpage").val()){
            clickPage=$("#totalpage").val();
        }
    }

    var id = $("#withdrawalsNo").val();
    var userType = $("#userRoleType").val();
    var status = $("#withdrawalsStatus").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var role=$("#roleselect").val();
    var username=$("#user_name").val();
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/ensures",
        dataType: "json",
        async:false,
        data: {
            withDrawalsId: id,
            accountType: userType,
            status: status,
            startTime: startTime,
            endTime: endTime,
            role:role,
            username:username,
            pageNum:clickPage
        },
        success: function (data) {
            var txt = "";

            if (data.page.list.length==0){
                txt=' <tr> <td colspan="20" class="table_no_data">暂时没有数据 ! </td> </tr>';
            }

            for(var i=0;i< data.page.list.length;i++){
                var caozuo="<button class='btn btn-primary' value='"+data.page.list[i].id+"' isOrderNumber='"+data.page.list[i].withdrawals_no+"'>确认</button> " +
                    "<button class='btn btn-default' value='"+data.page.list[i].id+"'  isOrderNumber='"+data.page.list[i].withdrawals_no+"'>撤回</button> " +
                    "<a href='/finance/withdrawals/info?nav=nav11&id=+"+data.page.list[i].id+"' class='href_a'>查看详情</a>";
                var status="待确认";
                txt+="<tr><td class='dtd'><div style='width:50px;!important;'><input type='checkbox' name='withrow' value='"+data.page.list[i].id+"'><input type='hidden' value='"+(data.page.list[i].total_money-data.page.list[i].commission)+"'></div></td><td  class='dtd'><div style='width:50px;!important;'>"+data.page.list[i].index+
                    "</div></td><td class='dtd'><div style='width:270px;!important;'>"+data.page.list[i].withdrawals_no+
                    "</div></td><td class='dtd'><div style='width:70px;!important;'>"+data.page.list[i].user_role_type+
                    "</div></td><td class='dtd'><div style='width:100px;!important;'>"+data.page.list[i].username+
                    "</div></td><td>"+data.page.list[i].shop_name+
                    "</td><td>"+data.page.list[i].bank_user+
                    "</td><td>"+data.page.list[i].bank_name+
                    "</td><td>"+data.page.list[i].bank_address+
                    "</td><td>"+data.page.list[i].bank_card+
                    "</td><td>"+data.page.list[i].total_money+
                    "</td><td>"+data.page.list[i].commission+
                    "</td><td>"+(data.page.list[i].total_money-data.page.list[i].commission)+
                    "</td><td>"+data.page.list[i].add_time+
                    "</td><td>"+status+
                    "</td><td>"+caozuo+
                    "</td></tr>"
            }
            $("#number").html(data.page.totalRow);
            $("#page").html(data.page.pageNumber);
            $("#totalpage").val(data.page.totalPage);
            $("#userAccountData").empty();
            $("#userAccountData").html(txt);
        }
    });
}


function tongjibz(){
    var num=0;
    var p=0.00;
        $('input[name="withrow"]:checked').each(function(index){
            p+=parseFloat($(this).next().val());
            num+=1;
        });
    $('#xzb').html(num);
    $('#xzy').html(p);
}

$(function() {
    findpram();

    //全选功能
    $("#checkAll").click(function() {
        if ($(this).prop("checked")){
            $("input[name='withrow']").prop("checked", true);
        }else{
            $("input[name='withrow']").prop("checked",false);
        }
        tongjibz();
    });

    //点击任意check统计选中笔数和选中总金额
    $("input[name='withrow']").click(function(){
        tongjibz();
    });

    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });

    /*
     * 弹出窗
     */
    $(".batchOk").click(function(){//批量确认
        var idlist="";
        var sum=0;
        $('input[name="withrow"]:checked').each(function(index){
            idlist+=','+$(this).val();
            sum+=1;
        });
        idlist=idlist.substring(1,idlist.length);
        if (sum==0){
            layer.msg('请选择数据!', {icon: 2,time: 1500});
            return;
        }
        layer.confirm('共'+sum+'笔提现订单，确认进行提现操作？', {
            btn: ['确认','取消'], //按钮
            title:"确认充值提示",
        }, function(){
            var remark="审核通过(处理中)";
            $.ajax({
                type: "GET",
                url: "/finance/withdrawals/approval",
                dataType: "json",
                data: {
                    id: idlist,
                    status: 4,
                    remark: remark
                },
                success: function (data) {
                    if (data.isok == "success") {
                        layer.msg('确认成功', {icon: 1,time: 1500});
                        findpram();
                    } else if (data.isok == "err") {
                        layer.msg('服务器发生未知错误,请联系管理员', {icon: 2,time: 1500});
                    } else {
                        layer.msg(data.isok, {icon: 2,time: 1500});
                    }
                }
            });
        });
    });

    $(".table").on("click",".btn-primary",function(){//确认
        var idlist=$(this).val();
        layer.confirm('确认提现订单'+$(this).attr("isOrderNumber")+'可以进行提现操作？', {
            btn: ['确认','取消'], //按钮
            title:"确认充值提示",
        }, function(){
                var remark="审核通过(处理中)";
                $.ajax({
                    type: "GET",
                    url: "/finance/withdrawals/approval",
                    dataType: "json",
                    data: {
                        id: idlist,
                        status: 4,
                        remark: remark
                    },
                    success: function (data) {
                        if (data.isok == "success") {
                            layer.msg('确认成功', {icon: 1,time: 1500});
                            findpram();
                        } else if (data.isok == "err") {
                            layer.msg('服务器发生未知错误,请联系管理员', {icon: 2,time: 1500});
                        } else {
                            layer.msg(data.isok, {icon: 2,time: 1500});
                        }
                    }
                });
        });
    });

    $(".table").on("click",".btn-default",function(){//撤回
        $(".deblocking_account_content>label i").text($(this).attr("isOrderNumber"));
        var idlist=$(this).val();
        layer.open({
            type: 1,
            title:"撤回提现订单",
            btn: ['确认','取消'], //按钮
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '250px'], //宽高
            content: $(".deblocking_account_box").html(),
            yes:function(){
                var alerts=$('.Revoketext').last().val();
                if (alerts==""){
                    layer.msg('请输入撤回原因!', {icon: 2,time: 1500});
                }
                $.ajax({
                    type: "GET",
                    url: "/finance/withdrawals/cancel",
                    dataType: "json",
                    data: {
                        id: idlist,
                        alert:alerts
                    },
                    success:function(data){
                        if (data.description="success"){
                            layer.msg('成功撤回提现订单!', {icon: 1,time: 1500});
                            findpram();
                            layer.closeAll();
                        }else{
                            layer.msg('提现订单撤回失败!', {icon: 2,time: 1500});
                        }
                    }
                })
            }
        });
    });

});

$("#tableDiv").scroll(function(){//给table外面的div滚动事件绑定一个函数
    var left=$("#tableDiv").scrollLeft();//获取滚动的距离
    var trs=$("#tableDiv table tr");//获取表格的所有tr
    trs.each(function(i){//对每一个tr（每一行）进行处理
        //获得每一行下面的所有的td，然后选中下标为0的，即第一列，设置position为相对定位
        //相对于父div左边的距离为滑动的距离，然后设置个背景颜色，覆盖住后面几列数据滑动到第一列下面的情况
        //如果有必要也可以设置一个z-index属性
        $(".dtd").css("left",left-1);
        // $(this).children().eq(0).css({"position":"relative","top":"0px","left":left,"background-color":"#000"});
        // $(this).children().eq(1).css({"position":"relative","top":"0px","left":left,"background-color":"#000"});
    });
});