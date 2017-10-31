/**
 * Created by pudding on 2017-7-22.
 */
$(function() {
    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });
    bd(1);
});


var cx=new Vue({
    el:"#cx",
    data:{
        status:'',
        paymentType:'',
        startTime:'',
        endTime:'',
        user_name:''
    },methods:{
        findusernme:function(event){
            bd(1);
        }
    }
});

var pays = new Vue({
    el:"#payInfo",
    data:{
        payAll: ''
    },
    methods:{
        finduserinfo:function(event){
            //跳转到用户详情页面
            window.open("/finance/ord/fundUserInfo?userId="+event);
        }
    }
});


var payspages=new Vue({
    el:"#Buyerpage",
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


function bd(page){
    $.ajax({
        type: "GET",
        url: "/finance/ord/findPaymentInfo",
        dataType: "json",
        data: {
            number:page,
            status:cx.status,
            paymentType:cx.paymentType,
            startTime:$('#startTime').val(),
            endTime:$('#endTime').val(),
            user_name:cx.user_name
        },success:function(data){
            pays.payAll=data.recordPage.list;
            payspages.number=data.recordPage.totalRow;
            payspages.page=data.recordPage.pageNumber;
            payspages.totalpage=data.recordPage.totalPage;
        }
    })
}