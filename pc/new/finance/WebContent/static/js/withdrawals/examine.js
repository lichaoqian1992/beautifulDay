/**
 * Created by pudding on 2017-4-22.
 */
$(function(){
    findpram(0);
})

/**
 * 退回提现
 */
function  ths(s){
    var id=$(s).next().val();
    layer.open({
        type: 1,
        title: '',
        btn: ['确认', '取消'], //按钮
        shadeClose: true,
        shade: 0.8,
        area: ['320px', '260px'],
        content: '<textarea class="withdrawalDepositForm_decide_textarea" placeholder="输入原因" maxlength="50" id="yctextarea"></textarea>', //iframe的url
        yes: function() {
            var remark = "管理退回提现 ";
            var alerts=$('.withdrawalDepositForm_decide_textarea').val();
            var status=2;
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
                success:function(data){
                    if (data.isok == "err") {
                        layer.msg("服务器发生错误操作失败!", {
                            icon: 2
                        });
                    } else {
                        if (data.isok == "") {
                            layer.msg("操作成功!", {
                                icon: 1
                            });
                            findpram();
                            layer.closeAll();
                            $.ajax({
                                type: "GET",
                                url: "/finance/withdrawals/cancel",
                                dataType: "json",
                                data: {
                                    id: id,
                                    alert: alerts
                                }
                            });
                        } else {
                            layer.msg(data.isok, {
                                icon: 2
                            });
                        }
                    };


                }
            });

        }
    });
}



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
    //var role=$("#role").val();
    var username=$("#user_name").val();
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/examines",
        dataType: "json",
        data: {
            withDrawalsId: id,
            accountType: userType,
            status: status,
            startTime: startTime,
            endTime: endTime,
            //role:role,
            username:username,
            pageNum:clickPage
        },
        success: function (data) {
            var txt = "";
            if (data.page.list.length==0){
                txt=' <tr> <td colspan="20" class="table_no_data">暂时没有数据 ! </td> </tr>';
            }
            for(var i=0;i< data.page.list.length;i++){
                var caozuo="<button class='btn btn-primary' value='"+data.page.list[i].id+"'>提交审批</button> <a class='href_a'href='/finance/withdrawals/info?nav=nav10&id=+"+data.page.list[i].id+"'>查看详情</a><a class='href_a' style='color: red' onclick='ths(this)'>退回</a><input type='hidden' value='"+data.page.list[i].id+"'/>";
                var status="待审批";
                txt+="<tr><td class='dtd'><div style='width:50px;!important;'><input type='checkbox' name='withrow' value='"+data.page.list[i].id+"'></div></td><td class='dtd'><div style='width:50px;!important;'>"+data.page.list[i].index+
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

    //全选功能
    $("#checkAll").click(function() {
        if ($(this).prop("checked")){
            $("input[name='withrow']").prop("checked", true);
        }else{
            $("input[name='withrow']").prop("checked",false);
        }

    });


    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });

    //审核人切换
    //$(".withdrawalAudit_tab").click(function() {
    //    var rolsname=$(this).find(".rolename").val();
    //    if (rolsname=="会计审批"){
    //        $("#role").val("8");
    //    }else if (rolsname=="财务经理审批"){
    //        $("#role").val("4");
    //    }else if (rolsname=="财务副总裁审批"){
    //        $("#role").val("2");
    //    }else if (rolsname=="董事长审批"){
    //        $("#role").val("3");
    //    }
    //    $(this).addClass("active").parent().siblings().find(".withdrawalAudit_tab").removeClass("active");
    //    findpram();
    //});

    //提交审核
    $(".batch_processing").click(function() { //批量审批
        layer.open({
            type: 1,
            title: '',
            btn: ['确认', '取消'], //按钮
            shadeClose: true,
            shade: 0.8,
            area: ['1180px', '500px'],
            content: tablehtml(), //iframe的url
            yes: function() {
                updatedetailed();
            }
        });
    });
    $(".table").on("click", ".btn", function() { //表格提交审批
        var id=$(this).val();
        layer.open({
            type: 1,
            title: '',
            btn: ['确认', '取消'], //按钮
            shadeClose: true,
            shade: 0.8,
            area: ['1180px', '320px'],
            content: tablehtmlone(id), //iframe的url
            yes: function() {
                updatedetailed();
            }
        });
    });


    /**
     * 批量提交审批(提现记录详情)
     */
    function tablehtml() {
        var sum=0;
        var zje=0.00;
        var Arry = [];
        var tbody = "";
        var thead = "";
        var tfood="";
        $('input[name="withrow"]:checked').each(function (index) {
            sum += 1
            Arry.push($(this).val());
        });
        var shrname="";
        var shmoble="";
        //var id=$('#role').val();
        $.ajax({
            type: "GET",
            url: "/finance/withdrawals/findAllWithDrawalsByid",
            dataType: "json",
            async:false,
            traditional: true,
            data: {
                idList: Arry
                //id:id
            }, success: function (data) {
                shrname=data.shrname;
                shmoble=data.shmoble;
                for (var i = 0; i < data.list.length; i++) {
                	var t = data.list[i].total_money.toFixed(2);
                	var c = data.list[i].commission.toFixed(2);
                	var p = t-c;
                    zje += p;
                    //alert(zje);
                    tbody += '<tr><input type="hidden" class="did" value="'+data.list[i].id+'"/><td>' + (i + 1) + '</td>' +
                        '<td>'+data.list[i].withdrawals_no+'</td>' +
                        '<td>'+data.list[i].user_role_type+'</td>' +
                        '<td>'+data.list[i].username+'</td>' +
                        '<td>'+data.list[i].bank_name+'</td>' +
                        '<td>'+data.list[i].bank_card+'</td>' +
                        '<td>'+(data.list[i].total_money-data.list[i].commission)+'</td>' +
                        '<td>'+data.list[i].commission+'</td>' +
                        '<td class="maxsum">'+data.list[i].total_money+'</td>' +
                        '<td>'+data.list[i].add_time+'</td></tr>';
                }

                thead = '<thead><tr><th>编号</th><th>提现订单号</th><th>提现用户类型</th><th>提现账号</th><th>银行卡名称</th><th>银行卡账号</th><th>提现金额（元）</th><th>提现手续费</th><th>提现总金额</th><th>提交时间</th></tr></thead>';
                 tfood = '<tfoot><tr><td colspan="20">' +
                    '此次审批共<span class="sum">' + sum + '</span>笔，总金额<span class="color_red totalMoney">'+zje.toFixed(2)+'</span>，' +
                   // '审批人每日审核额度为'+data.rechargeConfig.T_TOTAL_MONEY+'，今日还可审批<span class="color_red money">'+data.money+'</span>' +
                    '</td></tr></tfoot>';
            }
        });
        var label = '<label class="layer_box_label_title">您提交了'+sum+'笔提现订单进行审批，如果不再更改请发送验证码给审批人。</label>';
        var note = '<div class="col-md-12 finance_time finance_time_padding_left0">' +
            '<div class="form-group">' +
            '<label class="control-label">短信验证码：</label>' +
            '<div class="input-group">' +
            '<input type="text" class="form-control" id="tipcodetexts" value="">' +
            '</div></div>' +
            '<button class="btn btn-info note_send">发送验证短信</button>' +
            '<span class="tips_withdrawalAudit">提示：请将提现明细拍照发送审批人('+shrname+')，电话:('+shmoble+')，让他知晓具体信息!</span></div>';

        return '<div class="layer_box_content">' + label + '<table class="table table-bordered">' + thead + '<tbody>' + tbody + '</tbody>' + tfood + '</table>' + note + '</div>';
    }


    /**
     * 单条提交审批
     */
    function tablehtmlone(ids) {
        var sum=1;
        var zje=0.00;
        var Arry = [];
        var tbody = "";
        var thead = "";
        var tfood="";
        var shrname="";
        var shmoble="";
        Arry.push(ids);
        //var id=$('#role').val();
        $.ajax({
            type: "GET",
            url: "/finance/withdrawals/findAllWithDrawalsByid",
            dataType: "json",
            async:false,
            traditional: true,
            data: {
                idList: Arry,
               // id:id
            }, success: function (data) {
                shrname=data.shrname;
                shmoble=data.shmoble;
                for (var i = 0; i < data.list.length; i++) {
                    zje+=(data.list[i].total_money-data.list[i].commission);
                    tbody += '<tr><input type="hidden" class="did" value="'+data.list[i].id+'"/><td>' + (i + 1) + '</td>' +
                        '<td>'+data.list[i].withdrawals_no+'</td>' +
                        '<td>'+data.list[i].user_role_type+'</td>' +
                        '<td>'+data.list[i].username+'</td>' +
                        '<td>'+data.list[i].bank_name+'</td>' +
                        '<td>'+data.list[i].bank_card+'</td>' +
                        '<td>'+(data.list[i].total_money-data.list[i].commission)+'</td>' +
                        '<td>'+data.list[i].commission+'</td>' +
                        '<td class="maxsum">'+data.list[i].total_money+'</td>' +
                        '<td>'+data.list[i].add_time+'</td></tr>';
                }

                thead = '<thead><tr><th>编号</th><th>提现订单号</th><th>提现用户类型</th><th>提现账号</th><th>银行卡名称</th><th>银行卡账号</th><th>提现金额（元）</th><th>提现手续费</th><th>提现总金额</th><th>提交时间</th></tr></thead>';
                tfood = '<tfoot><tr><td colspan="20">' +
                    '此次审批共<span class="sum">' + sum + '</span>笔，总金额<span class="color_red totalMoney">'+zje+'</span>，' +
                    //'审批人每日审核额度为<span>'+data.rechargeConfig.T_TOTAL_MONEY+'</span>，今日还可审批<span class="color_red  money">'+data.money+'</span>' +
                    '</td></tr></tfoot>';
            }
        });
        var label = '<label class="layer_box_label_title">您提交了'+sum+'笔提现订单进行审批，如果不再更改请发送验证码给审批人。</label>';
        var note = '<div class="col-md-12 finance_time finance_time_padding_left0">' +
            '<div class="form-group">' +
            '<label class="control-label">短信验证码：</label>' +
            '<div class="input-group">' +
            '<input type="text" class="form-control " id="tipcodetext" value=""/>' +
            '</div></div>' +
            '<button class="btn btn-info note_send">发送验证短信</button>' +
            '<span class="tips_withdrawalAudit">提示：请将提现明细拍照发送审批人('+shrname+')，电话:('+shmoble+')，让他知晓具体信息!</span></div>';
        return '<div class="layer_box_content">' + label + '<table class="table table-bordered">' + thead + '<tbody>' + tbody + '</tbody>' + tfood + '</table>' + note + '</div>';
    }


    //发送短信验证
    $(document).on("click", ".note_send", function() {
        if(!$(this).attr("disabled")){

            var sum=parseInt($(".sum").last().html());

            if (sum==0){
                layer.msg("请选择数据!",{
                    icon:2
                });
                return;
            }

            //var msum=parseInt($(".totalMoney").last().html());
            var msum=$(".totalMoney").last().html();
            var maxsum=0.00;
            //var dqm=parseInt($('.money').last().html());
            //if (sum>dqm){
            //    layer.msg("已经超出当日审批金额,验证码发送失败!",{
            //        icon:2
            //    });
            //    return;
            //}
            $('.maxsum').each(function(index){
                if(parseFloat($(this).html())>maxsum){
                    maxsum=$(this).html();
                }
            });
            //var cid=$('#role').val();


            countDown(this);

            $.ajax({
                type: "GET",
                url: "/finance/withdrawals/SendverificationCode",
                dataType: "json",
                data: {
                    sum: sum,
                    msum: msum,
                    maxsum: maxsum,
                    //cid: cid
                }, success: function (data) {
                    if (data.isok == "success") {
                        $('#cid').val(data.cid);
                        layer.msg("验证码已发送至审核人!",{
                            icon:1
                        });
                    } else {
                        layer.msg("验证码发送失败!",{
                            icon:2
                        });
                    }
                }
            });


        }

    });

    //修改审核状态
    function updatedetailed(){
        var Arry=[];
        var newcode=$("#tipcodetext").val();
        if (newcode==""||newcode==null){
             newcode=$("#tipcodetexts").val();
        }
        var cid=$("#cid").val();

        $('.did').each(function(){
            Arry.push($(this).val());
        });
        if (Arry.length==0){
            layer.msg("请选择数据!",{
                icon:2
            });
            return;
        }


        $.ajax({
            type: "GET",
            url: "/finance/withdrawals/updatedetailed",
            dataType: "json",
            traditional: true,
            data: {
                newcode: newcode,
                idlist: Arry,
                cid: cid
            },
            success:function(data){
                if (data.isok=="Errcode"){
                    layer.msg("验证码错误!",{
                        icon:2
                    });
                  }else if (data.isok=="success"){
                    layer.msg("此批提现单审核成功!",{
                        icon:1
                    });
                    //rolscount();
                    //rolssumMoney();
                    //rolsYescount();
                    findpram();
                    layer.closeAll();
                }else{
                    layer.msg("审核失败!",{
                        icon:2
                    });
                }
            }
        });


    }




    //倒计时
    function countDown(_this) {
        var m = 9;
        var s = 10;
        var time=setInterval(function() {
            $(_this).attr("disabled","disabled");
            if(s < 10) {
                $(_this).text('剩余时间'+m + ':0' + s);
            } else {
                $(_this).text('剩余时间'+m + ':' + s);
            }
            s--;
            if(s < 0) {
                s = 59;
                m--;
            }
            if(s==0 && m==0){
                clearInterval(time);
                $(_this).text('发送验证短信').removeAttr("disabled");
            }
        }, 1000)
    }

    //rolscount();
    //rolssumMoney();
    //rolsYescount();
    //$('.withdrawalAudit_tab').eq(0).click();
});



/**
* 查询(更新)对应权限待审核
 */
function rolscount(){
   $.ajax({
       type:"GET",
      url:"/finance/withdrawals/findAllRechargeConfigCount",
       dataType:"json",
       success:function(data){
           for (var i=0;i<data.dlist.length;i++){
               if (data.dlist[i]==null){
                   $(".dsh").eq(i).html(0);
                }else {
                   $(".dsh").eq(i).html(data.dlist[i]);
               }

           }
        }
    })
}


/**
 * 查询(更新)对应权限待审核金额
 */
function rolssumMoney(){
    $.ajax({
        type:"GET",
        url:"/finance/withdrawals/findAllRechargeConfigSumMoney",
        dataType:"json",
        success:function(data){
            for (var i=0;i<data.lid.length;i++){
                if (data.lid[i]==null){
                    $(".dshmony").eq(i).html("0.00");
                }else{
                    $(".dshmony").eq(i).html(data.lid[i]);
                }

            }
        }
    })
}

/**
 * 查询(更新)对应权限已审核条数
 */
function rolsYescount(){
    $.ajax({
        type:"GET",
        url:"/finance/withdrawals/findAllRechargeConfigAudited",
        dataType:"json",
        success:function(data){
            for (var i=0;i<data.lis.length;i++){
                if (data.lis[i]==null){
                    $(".ysh").eq(i).html(0);
                }else{
                    $(".ysh").eq(i).html(data.lis[i]);
                }
            }
        }
    })
}


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


