 $(function(){
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
     zj.errlog=$('#err_logId').val();
     bd(1);

});


 var cx=new Vue({
     el:"#cx",
     data:{
         startTime:'',
         endTime:'',
         wstatus:'0'
     },
     methods:{
         findusernme:function(event){
             this.startTime=$('#startTime').val();
             this.endTime=$('#endTime').val();
             bd(1);
         }
     }
 });

 var zj=new Vue({
     el:"#userAccountData",
     data:{
         accountLog:'',
         errlog:0
     },
     methods:{
         df:function(order,user_id){
             location.href='/finance/withdrawals/infoalso?userId='+user_id+'&orders_old='+order+'';

         }
     }
 });

 var pageLog=new Vue({
    el:"#page",
     data:{
         number:'',
         page:1,
         totalpage:'',
         findpages:1
     },
     methods:{
         findpram:function(event) {
             bd(this.findpages);
         },
         findpram1:function(event){
             bd(1);
         },
         findpram2:function(event){
             this.page+=1;
             if (this.page>this.totalpage){
                 this.page=this.totalpage;
             }
             bd(this.page);
         },
         findpram3:function(event){
             this.page-=1;
             if (this.page<1){
                 this.page=1;
             }
             bd(this.page);
         },
         findpram4:function(event){
             bd(this.totalpage);
         }
     }
 });



function bd(page) {
    $.ajax({
        type: "GET",
        url: "/finance/withdrawals/getAccountLog",
        dataType: "json",
        data: {
            page: page,
            userid: $('#userid').val(),
            usertype: '用户',
            startTime: cx.startTime,
            endTime: cx.endTime,
            wstatus:cx.wstatus
        }, success: function (data) {
            zj.accountLog=data.pageRecord.list;
            pageLog.number=data.pageRecord.totalRow;
            pageLog.page=data.pageRecord.pageNumber;
            pageLog.totalpage=data.pageRecord.totalPage;
        }
    })
}

