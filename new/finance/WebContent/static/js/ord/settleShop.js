/**
 * Created by pudding on 2017-8-18.
 */
$(function(){
    date(1);
})

var finds=new Vue({
    el:"#findun",
    data:{
        username:''
    },
    methods:{
        findusernme:function(event){
            date(1);
        }
    }
})

var Shop = new Vue({
    el:"#orderInfo",
    data:{
        Shops: ''
    },
    methods:{
        info:function(event){
            //跳转到用户详情页面
            window.open("/finance/ord/findBusinessDetails?user_id="+event);
        },
        orderInfo:function(event){
            window.open("/finance/ord/settleOrder?number=1&user_id="+event);
        }
    }
});


var Shoppages=new Vue({
    el:"#Shoppage",
    data:{
        number:'',
        page:1,
        totalpage:'',
        findpages:1
    },
    methods:{
        findpram:function(event) {
            date(this.findpages);
        },
        findpram1:function(event){
            date(1);
        },
        findpram2:function(event){
            this.page+=1;
            if (this.page>this.totalpage){
                this.page=this.totalpage;
            }
            date(this.page);
        },
        findpram3:function(event){
            this.page-=1;
            if (this.page<1){
                this.page=1;
            }
            date(this.page);
        },
        findpram4:function(event){
            date(this.totalpage);
        }
    }
});


function date(page){
    $.ajax({
        type: "GET",
        url: "/finance/ord/findShopBySettle",
        dataType: "json",
        data: {
            number:page,
            shopname:finds.username
        },success:function(data){
            Shop.Shops=data.pageList.list;
            Shoppages.number=data.pageList.totalRow;
            Shoppages.page=data.pageList.pageNumber;
            Shoppages.totalpage=data.pageList.totalPage;
        }
    })
}