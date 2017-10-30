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

var Buser = new Vue({
    el:"#orderInfo",
    data:{
        buyers: ''
    },
    methods:{
        info:function(event){
           //跳转到用户详情页面
            window.open("/finance/ord/fundUserInfo?userId="+event);
        }
    }
});


var Buyerpages=new Vue({
    el:"#Buyerpage",
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
        url: "/finance/ord/findUserAccountAll",
        dataType: "json",
        data: {
            username:finds.username,
            number:page
        },success:function(data){
            Buser.buyers=data.pageList.list;
            Buyerpages.number=data.pageList.totalRow;
            Buyerpages.page=data.pageList.pageNumber;
            Buyerpages.totalpage=data.pageList.totalPage;
        }
    })
}




