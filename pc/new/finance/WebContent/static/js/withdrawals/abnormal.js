//点击判定跳转判定页面
function  pd(data){
    window.location.href="/finance/withdrawals/abnormalForm?nav=nav9&id="+$(data).next().val();
};

//点击判定跳转详情页面
function  xq(data){
    window.location.href="/finance/withdrawals/info?nav=nav9&id="+$(data).next().val();
};

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

    var txt = "";
    var id = $("#withdrawalsNo").val();
    var userType = $("#userRoleType").val();
    var status = $("#withdrawalsStatus").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var username=$("#user_name").val();
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/findabnormal",
        dataType: "json",
        data: {
            pageNum:clickPage,
            withDrawalsId: id,
            accountType: userType,
            status: status,
            startTime: startTime,
            endTime: endTime,
            username:username
        },
        success: function (data) {
            var caozuo="";
            var status="";
            if (data.page.list.length==0){
                txt=' <tr> <td colspan="20" class="table_no_data">暂时没有数据 ! </td> </tr>';
            }

            for(var i=0;i< data.page.list.length;i++){
                if (data.page.list[i].state=='0'){
                    status="冻结账户";
                    caozuo="<button class='btn btn-primary deblocking_account' value='"+data.page.list[i].id+"'>解锁账户</button>";
                }else{
                    if (data.page.list[i].status=='0'||data.page.list[i].status=='4'||data.page.list[i].status=='5'){
                        status="待判定";
                        caozuo="<button class='btn btn-primary' id='pd' onclick='pd(this)'>判定</button><input type='hidden' value='"+data.page.list[i].id+"'/>"
                    }else if (data.page.list[i].status=='1'||data.page.list[i].status=='2'){
                        status="退回";
                        caozuo="<button class='btn btn-primary' onclick='xq(this)'>详情</button><input type='hidden' value='"+data.page.list[i].id+"'/>"
                    }else if (data.page.list[i].status=='3'){
                        status="冻结本笔提现";
                        caozuo="<button class='btn btn-primary cancel_freeze' value='"+data.page.list[i].id+"'>取消冻结</button>"
                    }
                }
                txt+="<tr><td class='dtd'><div style='width:50px;!important;'><input type='checkbox'></div></td><td class='dtd'><div style='width:50px;!important;'>"+data.page.list[i].index+
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



$(function() {
    findpram();
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
    $(".table").on("click",".cancel_freeze",function(){//取消冻结
        var id=$(this).val();
       layer.open({
            type: 1,
            title:"确认提示",
            btn: ['确认','取消'], //按钮
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '250px'], //宽高
            content: $(".cancel_freeze_box").html(),
            yes:function(){
                var alerts=$(".textTUN").last().val();
                var status=0;
                var remark="管理员解冻"
                if (alerts==""||alerts==null){
                    layer.msg("请输入解冻原因!",{
                        icon:2
                    });
                    return;
                }
                $.ajax({
                    url:"/finance/withdrawals/Frozen",
                    dataType:"json",
                    data:{
                        id:id,
                        alert:alerts,
                        remark:remark,
                        status:status
                    },success:function(data) {
                        if (data.smslog=="err"){
                            layer.msg("发送短信失败!",{
                                icon:2
                            });
                        }
                        if (data.isok!="err"){
                            layer.msg("此提现单已解冻!",{
                                icon:1
                            });
                            findpram();
                        }else{
                            layer.msg("提现单解冻失败!",{
                                icon:2
                            });
                        }
                    }
                });
                layer.closeAll();
            }
        });
    });


    $(".table").on("click",".deblocking_account",function(){//解锁账户

        var id=$(this).val();
        layer.open({
            type: 1,
            title:"确认提示",
            btn: ['确认','取消'], //按钮
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '250px'], //宽高
            content: $(".deblocking_account_box").html(),
            yes:function(){
                var alerts=$(".textUN").last().val();

                if (alerts==""||alerts==null){
                    layer.msg("请输入解锁原因!",{
                        icon:2
                    });
                    return;
                }
                $.ajax({
                    url:"/finance/withdrawals/AccountStateUnFrozen",
                    dataType:"json",
                    data:{
                        id:id,
                        remark:alerts
                    },success:function(data) {

                        if (data.isok=="success"){
                            layer.msg("此账户已解锁!",{
                                icon:1
                            });
                            findpram();
                        }else{
                            layer.msg("解锁账户失败!",{
                                icon:2
                            });
                        }
                    }
                });
                layer.closeAll();
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



