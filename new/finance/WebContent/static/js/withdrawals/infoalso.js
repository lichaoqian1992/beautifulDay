/**
 * Created by pudding on 2017-4-22.
 */
$(function() {
    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });
    recSelect(1);
    widSelect(1);
    bd();
});

function recSelect(page){
    $.ajax({
        url: '/finance/withdrawals/recSelect',
        type: 'GET',
        dataType: "json",
        async:false,
        data:{
            "page":page,
            "resNO":$("#recNo").val(),
            "userId":$(".userId").val()
        },
        success: function (data) {
            $(".table-bordered #rec").children().remove();
            $(".rec_box .pull-left q:eq(0)").html(data.recSelect.totalRow);
            $(".rec_box .pull-left q:eq(1)").html(data.recSelect.pageNumber);
            $(".rec_box .lastpage").val(data.recSelect.totalPage);
            //循环数据
            $(".rec_box .page").val(data.recSelect.pageNumber);
            if(data.recSelect.totalPage!=0){
                $(".rec_box ").show();
                $(data.recSelect.list).each(function(){
                    $(".table-bordered #rec").append("<tr><td>" + this.USER_ID + "</td><td>" + this.tranSN + "</td>" +
                        "<td>" + this.RECHARGE_ORDER_NO + "</td>" +
                        "<td>" + this.RECHARGE_WAY + "</td>" +
                        "<td>" + this.RECHARGE_TYPE + "</td>" +
                        "<td>" + this.USER_NAME + "</td>" +
                        "<td>" + this.USER_KEY + "</td>" +
                        "<td>" + this.RECHARGE_MONEY + "</td>" +
                        "<td>" + this.ARRIVAL_MONEY + "</td>" +
                        "<td>" + this.STATUS+ "</td>" +
                        "<td>" + this.ARRIVAL_TIME + "</td></tr>");
                });
            }else{
                $(".table-bordered #rec").append("<tr><td colspan='11' class='table_no_data'>暂无数据！</td></tr>");
                $(".rec_box ").hide();
            }
        }
    });
}


function widSelect(page){
    $.ajax({
        url: '/finance/withdrawals/widSelect',
        type: 'GET',
        dataType: "json",
        async:false,
        data:{
            "page":page,
            "widNO":$("#widNo").val(),
            "userId":$(".userId").val()
        },
        success: function (data) {
            $(".table-bordered #wid").children().remove();
            $(".wid_box .pull-left q:eq(0)").html(data.widSelect.totalRow);
            $(".wid_box .pull-left q:eq(1)").html(data.widSelect.pageNumber);
            $(".wid_box .lastpage").val(data.widSelect.totalPage);
            //循环数据
            $(".wid_box .page").val(data.widSelect.pageNumber);
            if(data.widSelect.totalPage!=0){
                $(".wid_box ").show();
                $(data.widSelect.list).each(function(){
                    $(".table-bordered #wid").append("<tr><td>" + this.user_id + "</td>" +
                        "<td>" + this.user_role_type + "</td>" +
                        "<td>" + this.amount + "</td>" +
                        "<td>" + this.commission + "</td>" +
                        "<td>" + this.withdrawals_no + "</td>" +
                        "<td>" + this.status + "</td>" +
                        "<td>" + this.bank_name + "</td>" +
                        "<td>" + this.bank_card+ "</td>" +
                        "<td>" + this.bank_address + "</td>" +
                        "<td>" + this.add_time + "</td>" +
                        "<td>" + this.remark+ "</td>" +
                        "<td>" + this.bank_user + "</td>"+
                        "<td>" + this.complete_time + "</td></tr>")
                });
            }else{
                $(".table-bordered #wid").append("<tr><td colspan='21' class='table_no_data'>暂无数据！</td></tr>");
                $(".wid_box").hide();
            }
        }
    });
}


/**
 * 资金流动详细信息
 * @param pages
 */
function bd(pages){
    //var clickPage=$("#pagelog").html();
    //if ($(pages).html()=="GO"){
    //    clickPage=$('#findpageslog').val();
    //    if (clickPage<1){
    //        clickPage=1;
    //    }
    //    if (clickPage>$("#totalpagelog").val()){
    //        clickPage=$("#totalpagelog").val();
    //    }
    //}else if ($(pages).html()=="首页"){
    //    clickPage=1;
    //}else if ($(pages).html()=="尾页"){
    //    clickPage=$('#totalpagelog').val();
    //}else if ($(pages).html()=="上一页"){
    //    clickPage=$('#pagelog').html()-1;
    //    if (clickPage<1){
    //        clickPage=1;
    //    }
    //}else if ($(pages).html()=="下一页"){
    //    clickPage=$('#pagelog').html()-(-1);
    //    if (clickPage>$("#totalpagelog").val()){
    //        clickPage=$("#totalpagelog").val();
    //    }
    //}
    var userid=$(".userId").val();
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
                if ($(".orders_old").val()==data.pageRecord.list[i].order_no){
                    var ordersno="<span style='color: #00a7d0'>"+data.pageRecord.list[i].order_no+"</span>";
                }else{
                    var ordersno=data.pageRecord.list[i].order_no;
                }

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
                    "</td><td>" +ordersno+dfzh+
                    "</td><td>" +data.pageRecord.list[i].remark+
                    "</td></tr>";

            }
            //$("#numberlog").html(data.pageRecord.totalRow);
            //$("#pagelog").html(data.pageRecord.pageNumber);
            //$("#totalpagelog").val(data.pageRecord.totalPage);
            $("#userAccountData").empty();
            $("#userAccountData").html(txt);

        }
    });
}

/*********************rec分页*************************/
/*下一页*/
function previousPagerec(){
    if((parseInt($(".rec_box .page").val())+1)>parseInt($(".rec_box .lastpage").val())){
        layer.msg('这已经是最后一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    recSelect(parseInt($(".rec_box .page").val())+1);
}

/*上一页*/
function nextPagerec(){
    if((parseInt($(".rec_box .page").val())-1)<1){
        layer.msg('这已经是第一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    recSelect(parseInt($(".rec_box .page").val())-1);
}

/*首页*/
function homePagerec(){
    if((parseInt($(".rec_box .page").val()))==1){
        layer.msg('这已经是第一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    recSelect(1);
}

/*尾页*/
function Shadowerec(){
    if((parseInt($(".rec_box .page").val()))==parseInt($(".rec_box .lastpage").val())){
        layer.msg('这已经是最后一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    recSelect(parseInt($(".rec_box .lastpage").val()));
}

/*GO*/
function goPagerec(){
    var page=1;
    if((parseInt($(".rec_box .page_numberAll").val()))==parseInt($(".rec_box .page").val())){
        layer.msg('正在当前页！', {
            icon: 1,
            time: 1000
        });
        return;
    }

    if((parseInt($(".rec_box .page_numberAll").val()))>parseInt($(".rec_box .lastpage").val())){
        $(".rec_box .page_numberAll").val($(".rec_box .lastpage").val());
        page=parseInt($(".rec_box .lastpage").val());
    }

    if((parseInt($(".rec_box .page_numberAll").val()))<1){
        $(".rec_box .page_numberAll").val(1);
        page=1;
    }
    recSelect(parseInt($(".rec_box .page_numberAll").val()));
}

/*********************wid分页*************************/
/*下一页*/
function previousPagewid(){
    if((parseInt($(".wid_box .page").val())+1)>parseInt($(".wid_box .lastpage").val())){
        layer.msg('这已经是最后一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    widSelect(parseInt($(".wid_box .page").val())+1);
}

/*上一页*/
function nextPagewid(){
    if((parseInt($(".wid_box .page").val())-1)<1){
        layer.msg('这已经是第一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    widSelect(parseInt($(".wid_box .page").val())-1);
}

/*首页*/
function homePagewid(){
    if((parseInt($(".wid_box .page").val()))==1){
        layer.msg('这已经是第一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    widSelect(1);
}

/*尾页*/
function Shadowewid(){
    if((parseInt($(".wid_box .page").val()))==parseInt($(".wid_box .lastpage").val())){
        layer.msg('这已经是最后一页了！', {
            icon: 1,
            time: 1000
        });
        return;
    }
    widSelect(parseInt($(".wid_box .lastpage").val()));
}

/*GO*/
function goPagewid(){
    var page=1;
    if((parseInt($(".wid_box .page_numberAll").val()))==parseInt($(".wid_box .page").val())){
        layer.msg('正在当前页！', {
            icon: 1,
            time: 1000
        });
        return;
    }

    if((parseInt($(".wid_box .page_numberAll").val()))>parseInt($(".wid_box .lastpage").val())){
        $(".wid_box .page_numberAll").val($(".wid_box .lastpage").val());
        page=parseInt($(".wid_box .lastpage").val());
    }

    if((parseInt($(".wid_box .page_numberAll").val()))<1){
        $(".wid_box .page_numberAll").val(1);
        page=1;
    }
    widSelect(parseInt($(".wid_box .page_numberAll").val()));
}
