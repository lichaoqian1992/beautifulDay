/**
 * Created by pudding on 2017-8-9.
 */
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//Date.prototype.Format = function (fmt) { //author: meizz
//    var o = {
//        "M+": this.getMonth() + 1, //月份
//        "d+": this.getDate(), //日
//        "H+": this.getHours(), //小时
//        "m+": this.getMinutes(), //分
//        "s+": this.getSeconds(), //秒
//        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
//        "S": this.getMilliseconds() //毫秒
//    };
//    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//    for (var k in o)
//        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//    return fmt;
//}


$(function() {
    /*时间插件调用
     */
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'cn'
    });
    //设置默认时间为当天时间
    //var time1 = new Date().Format("yyyy-MM-dd");
    //search.time=time1;
    bd(1);
});


var search=new Vue({
    el:"#search",
    data:{
        time:''
    },methods:{
        find:function(event){
            bd(1);
        }
    }
});

var Loginfo=new Vue({
    el:'#info',
    data:{
        logAll:'',
        number:0,
        page:1,
        totalpage:0,
        findpages:1
    },methods:{
        //通过参数查询
        findpram:function(event) {
            bd(this.findpages);
        },
        //首页
        findpram1:function(event){
            bd(1);
        },
        //下一页
        findpram2:function(event){
            this.page+=1;
            if (this.page>this.totalpage){
                this.page=this.totalpage;
            }
            bd(this.page);
        },
        //上一页
        findpram3:function(event){
            this.page-=1;
            if (this.page<1){
                this.page=1;
            }
            bd(this.page);
        },
        //尾页
        findpram4:function(event){
            bd(this.totalpage);
        },
        //修改状态
        updateisdispoy:function(event){
            $.ajax({
                type: "GET",
                url: "/finance/ord/updateisdispoy",
                dataType: "json",
                data: {
                    log_id:event
                },success:function(data){
                    if (data.i>0){
                        bd(Loginfo.page);
                    }
                }
            })
        },
        //通过商家(用户)名跳转至商家或用户
        findInfoByname:function(log){

                if (log.user_role_type=='商家'){
                    if (log.user_name!=''){
                        window.open("/finance/ord/findBusinessDetails?user_id="+log.user_id+"&err_logId="+log.id);
                    }else{
                        layer.msg("抱歉没有查询到此商家!",{
                            icon:2
                        });
                    }
                }else{
                    if (log.user_name!='') {
                        window.open("/finance/ord/fundUserInfo?userId=" + log.user_id+"&err_logId="+log.id);
                    }else{
                        layer.msg("抱歉没有查询到此用户!",{
                            icon:2
                        });
                    }
                }
            }




    }
})


function bd(page){
    $.ajax({
        type: "GET",
        url: "/finance/ord/findaccountErrInfo",
        dataType: "json",
        data: {
            number:page,
            time:$('#time').val(),
        },success:function(data){
            Loginfo.logAll=data.accountPage.list;
            Loginfo.number=data.accountPage.totalRow;
            Loginfo.page=data.accountPage.pageNumber;
            Loginfo.totalpage=data.accountPage.totalPage;
        }
    })
}
