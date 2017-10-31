/**
 * Created by Administrator on 2017/2/17.
 */
var myData = [];
var myValue = [];
$(function(){
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
});
function mess(obj){
    alert($(obj).val());
};
