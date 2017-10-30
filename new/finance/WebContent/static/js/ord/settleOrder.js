/**
 * Created by pudding on 2017-8-18.
 */

$(function(){
    Shop.userId=$('#userId').val();
    date(1);
});

var Shop = new Vue({
    el:"#orderInfo",
    data:{
        Shops: '',
        userId:$('#userId').val()
    },
    methods:{
        info:function(event){
            //跳转到用户详情页面
            window.location.href = "/finance/ord/findBusinessDetails?user_id="+event;
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
        url: "/finance/ord/findAllSettlement",
        dataType: "json",
        data: {
            number:page,
            user_id:Shop.userId
        },success:function(data){
            Shop.Shops=data.pageList.list;
            Shoppages.number=data.pageList.totalRow;
            Shoppages.page=data.pageList.pageNumber;
            Shoppages.totalpage=data.pageList.totalPage;
        }
    })
}