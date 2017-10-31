/**
 * Created by Administrator on 2017/1/20.
 */
var myData = [];
var myValue = [];
$(function(){
    //根据登录人判断是否可以下载充值记录,
    $.ajax({
        type:"get",
        url:"/userRecharge/getUserRole",
        dataType:"json",
        data:{
            userName:$("#checkPeople").val()
        },success:function(data){
            if(data.description != "SUCCESS"){
                $("#today").hide();
                $("#month").hide();
            }
        }
    });
    //显示平台的商家总数量和当月及当日的业务流水量
    $.ajax({
        type: "GET",
        url: "/monthAndDailySheet/getDay",
        dataType: "json",
        //async:false,
        success: function (data) {
            console.log(data);
            $("#pintaiMessage").html(data.obj);
        }
    });
    /*var a="<th></th>";
    var b="<th></th>";
    $.ajax({
        type: "GET",
        url: "/monthAndDailySheet/getEveryIndustryManagementSituation",
        dataType: "json",
        async:false,
        success: function (data) {
            if (data.statusEnum == "SUCCESS") {
                for(i=0;i<data.resultList.length;i++) {
                    a += "<th>" + data.resultList[i].title + "</th>";
                    b += "<td>" + data.resultList[i].amountMoney + "</td>";
                }
            }
        }
    });
    $("#a1").append(a);
    $("#a2").append(b);*/
    /**
     * 柱状图
     * @type {string}
     */
    $.ajax({
        type: "GET",
        url: "/monthAndDailySheet/getEveryIndustryManagementSituation",
        dataType: "json",
        async:false,
        success: function (data) {
            if (data.statusEnum == "SUCCESS") {
                for(var i=0;i<data.resultList.length;i++) {
                    myData.push(data.resultList[i].title);
                    myValue.push(data.resultList[i].amountMoney);
                }
            }
        }
    });
    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
// 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('test'));

            var option = {
                tooltip: {
                    show: true
                },
                legend: {
                    data:['各行业经营消费图']
                },
                xAxis : [
                    {
                        type : 'category',
                        data : myData
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"各行业经营消费图",
                        "type":"bar",
                        "data": myValue
                    }
                ]
            };
            // 为echarts对象加载数据
            myChart.setOption(option);
        }
    );
    var date2 = new Date().getFullYear() + "-" + (new Date().getMonth() + 1);
    queryMonthSheet(date2);
});



//查询按钮
function queryDailySheet() {
    var starttime = $("#startTime").val();
    var billtxt = "";
    $.ajax({
        type: "GET",
        url: "/monthAndDailySheet/getDailySheet",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: {startTime: starttime},
        success: function (data) {
            if (data.statusEnum == "SUCCESS") {
                var bill = data.obj;
                $("#content").html("");//清空
                billtxt = "<tr><td>"
                    + bill.startTime +
                    " </td><td id='a1'>"
                    + bill.beginAmount +
                    " </td><td id='a2'>"
                    + bill.incomeAmount +
                    " </td><td>"
                    + bill.expenditureAmount +
                    " </td><td>"
                    + bill.endAmount +
                    " </td></tr>";
            }
            $("#content").html(billtxt);//清空
        }
    });
}
//查询月报表
function queryMonthSheet(starttime) {
    var starttime2;
    if (starttime != null) {
        starttime2 = starttime;
    } else {
        starttime2 = $("#startTime2").val();
    }
    var billtxt = "";
    $.ajax({
        type: "GET",
        url: "/monthAndDailySheet/getMonthSheet",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: {startTime: starttime2},
        success: function (data) {
            if (data.statusEnum == "SUCCESS") {
                var bill = data.obj;
                $("#content2").html("");//清空
                billtxt = "<tr><td>"
                    + bill.startMonthTime +
                    " </td><td id='b1'>"
                    + bill.beginMonthAmount +
                    " </td><td id='b2'>"
                    + bill.incomeMonthAmount +
                    " </td><td>"
                    + bill.expenditureMonthAmount +
                    " </td><td>"
                    + bill.endMonthAmount +
                    " </td></tr>";
            }
            $("#content2").html(billtxt);//清空
        }
    });
}
//显示收入支出的详情
function showDetail(){
    alert("显示详情");
}
//判断所选日期不能大于当前日期
function getDays() {
    var getDate = $("#startTime").val();
    var myTime = new Date();
    var nowDate = myTime.getFullYear() + "-" + ((myTime.getMonth() + 1) > 10 ? (myTime.getMonth() + 1) : "0" + (myTime.getMonth() + 1)) + "-" + (myTime.getDate() > 10 ? myTime.getDate() : "0" + myTime.getDate());
    if (new Date(getDate).getTime() > myTime.getTime()) {
        showMessage("操作错误！所选日期不能大于当前日期！");
        $("#startTime").val(nowDate);
    }
}
//格式化日期,同时判断所选月份不能大于当前月份
function getMonth() {
    var time = $("#startTime2").val();
    var myTime = new Date(time);
    var myTime2 = new Date();
    var year = myTime2.getFullYear();
    var month= myTime2.getMonth()+2;
    var newTime = myTime.getFullYear() + "-" + ((myTime.getMonth() + 1) > 10 ? (myTime.getMonth() + 1) : "0" + (myTime.getMonth() + 1));
    if(month>12){
        month = "01";
        year +=1;
    }
    if(myTime.getTime() >= new Date(year+"-"+month+"-01").getTime()){
        showMessage("操作错误！所选月份不能大于当前月份！");
        $("#startTime2").val(year+"-"+((month-1)>10?(month-1):"0"+(month-1)));
    }else{
        $("#startTime2").val(newTime);
    }
}
/**
 * 下载账单信息
 */
function downLoad(str){
    var startTime = "";
    var excelType = "";
    if(str == "today"){
        //1.先判断今日是否有金额变动
        //alert(document.getElementById('a1').innerHTML);
        //if($("#a1").innerHTML == 0 && $("#a2").innerHTML == 0){
        //    showMessage("今日无交易,不能导出excel");
        //}
        startTime = $("#startTime").val();
        excelType = "day";
    }else if(str == "toMonth"){
        //if(document.getElementById("b1").val() == 0 && document.getElementById("b2").val() == 0){
        //    showMessage("查询月无交易,不能导出excel");
        //}
        startTime = $("#startTime2").val();
        excelType = "month";
    }
    //查询日月报表详情并导出
    //location.href="/systemPush/test.html";
    location.href="/toExcel/excelDayOrMonthDetail?startTime="+startTime+"&excelType="+excelType;
}



