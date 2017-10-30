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
    var confirmwhether=$("#confirmwhether").val();
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/records",
        dataType: "json",
        async:false,
        data: {
            withDrawalsId: id,
            accountType: userType,
            status: status,
            startTime: startTime,
            endTime: endTime,
            confirmwhether:confirmwhether,
            pageNum:clickPage
        },
        success: function (data) {
            var txt = "";

            if (data.page.list.length==0){
                txt=' <tr> <td colspan="20" class="table_no_data">暂时没有数据 ! </td> </tr>';
            }

            for(var i=0;i< data.page.list.length;i++){
                if (data.page.list[i].confirmwhether=='0'){
                    var status="<td style='color: red'>人工处理</td>";
                    var caozuo="<a href='/finance/withdrawals/dispose?nav=nav12&id="+data.page.list[i].id+"' class='href_a'>处理</a>";
                }else if (data.page.list[i].confirmwhether=='1'){
                    var status="<td style='color: green'>提现成功</td>";
                    var caozuo="<a href='/finance/withdrawals/info?nav=nav12&id="+data.page.list[i].id+"' class='href_a'>详情</a><span class='href_a' onclick='tuihui("+data.page.list[i].id+")'>退回</span>";
                }else if (data.page.list[i].confirmwhether=='2'){
                    var status="<td style='color: red'>提现失败</td>";
                    var caozuo="<a href='/finance/withdrawals/info?nav=nav12&id="+data.page.list[i].id+"' class='href_a'>详情</a>";
                }
                txt+="<tr><td class='dtd'><div style='width:50px;!important;'><input type='checkbox' name='withrow' value='"+data.page.list[i].id+"'><input type='hidden' value='"+(data.page.list[i].total_money-data.page.list[i].commission)+"'></div></td><td class='dtd'><div style='width:50px;!important;'>"+data.page.list[i].index+
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
                    "</td><td>--"+
                    "</td>"+status+
                    "<td>"+caozuo+
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

function tuihui(id){
    if (!confirm("确认退回已完成提现？")){
        return;
    }
    var alert=prompt("请输入退回原因:");

    if (alert==null){
        return;
    }
    var status = 2;
    var remark = "管理退回提现 ";
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/multyApprovalerr",
        dataType: "json",
        data: {
            id: id,
            status: status,
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
    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });

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
            var remark="完成";
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
                        findpram();
                        layer.msg('确认成功', {icon: 1,time: 1500});
                    } else if (data.isok == "err") {
                        layer.msg('服务器发生未知错误,请联系管理员', {icon: 2,time: 1500});
                    } else {
                        layer.msg(data.isok, {icon: 2,time: 1500});
                    }
                }
            });
        });
    });


    /**
     * 导出excl
     */
    $("#excl").click(function(){

        var id = $("#withdrawalsNo").val();
        var userType = $("#userRoleType").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var confirmwhether=$("#confirmwhether").val();
        var idlist='';
        $('input[name="withrow"]:checked').each(function(index){
            idlist+=','+$(this).val();
        });
        idlist=idlist.substring(1,idlist.length);
        window.location.href="/finance/withdrawals/exclinfo?withDrawalsId="+id+"&accountType="+userType+"&startTime="+startTime+"&endTime="+endTime+"&confirmwhether="+confirmwhether+"&idlist="+idlist+"";
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





