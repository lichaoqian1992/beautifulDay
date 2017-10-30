/*首页js*/

var accountStartTime;
var accountEndTime;
var dayTime;
var monthTime;
var statisticsStartTime; 
var statisticsEndTime;
var statisticsObject;
$(function(){
    /*赋初始时间账户信息汇总*/
	if(nowtime(getCurrentMonthFirst())==nowtime(new Date())){
		 $("#accountStartTime").val(getYesterda());
		 
		 /*赋初始时间数据统计*/
	     $("#statisticsStartTime").val(getYesterda());

	}else{
		 $("#accountStartTime").val(nowtime(getCurrentMonthFirst()));
		 
		 /*赋初始时间数据统计*/
	     $("#statisticsStartTime").val(nowtime(getCurrentMonthFirst()));
	}
   
    
    
    /*赋初始时间日报表*/
    $("#dayTime").val(getYesterda());
    $("#monthTime").val(nowYearDay());
    
    /*日月报表切换*/
    $(".ri").click(function(){$(".reportType").val(0);});
    $(".yue").click(function(){$(".reportType").val(1);});
    
    /*小数转换*/
    Decimal();
    
    /*数据统计*/
    statistics();
    
    /*发送报送信息*/
    reportalso();
    
})

/*信息总汇*/
function account(){

	accountStartTime=$("#accountStartTime").val();
	accountEndTime=$("#accountEndTime").val();

	if(accountStartTime==accountEndTime){
        var today=new Date(accountStartTime);
        var yesterday_milliseconds=today.getTime()+1000*60*60*24;

        var yesterday=new Date();
        yesterday.setTime(yesterday_milliseconds);

        var strYear=yesterday.getFullYear();
        var strDay=yesterday.getDate();
        var strMonth=yesterday.getMonth()+1;
        if(strMonth<10)
        {
            strMonth="0"+strMonth;
        }
        var strYesterday=strYear+"-"+strMonth+"-"+strDay;
        accountEndTime=strYesterday;
	}

	 $.ajax({
        type:"get",
        url:"/finance/home/information",
        dataType:"json",
        contentType : "application/json; charset=utf-8",
        data:{
        	accountStartTime:accountStartTime,
        	accountEndTime:accountEndTime
        },
        success:function(data){
        	for(i=0;i<data.dataCountFront.length;i++) {
        		var xuan=".money_change:eq(0) .money_change_itme:eq("+i+")";
        		$(xuan).find("label").html(data.dataCountFront[i].Name);
        		if(data.dataCountFront[i].allmoney==0){
        			$(xuan).find("span").html(0);
        		}else{
        			$(xuan).find("span").html((data.dataCountFront[i].allmoney).toFixed(2));
        		}
        		
            }
        	
        	for(i=0;i<data.dataCountBack.length;i++) {
        		var xuan=".money_change:eq(1) .money_change_itme:eq("+i+")";
        		$(xuan).find("label").html(data.dataCountBack[i].Name);
        		if(data.dataCountBack[i].allmoney==0){
        			$(xuan).find("span").html(0);
        		}else{
        			$(xuan).find("span").html((data.dataCountBack[i].allmoney).toFixed(2));
        		}
        	
            }
        	
        	for(i=0;i<data.dataCountExpenditure.length;i++) {
        		var xuan=".money_change:eq(2) .money_change_itme:eq("+i+")";
        		$(xuan).find("label").html(data.dataCountExpenditure[i].Name);
        		if(data.dataCountExpenditure[i].allmoney==0){
        			$(xuan).find("span").html(0);
        		}else{
        			$(xuan).find("span").html((data.dataCountExpenditure[i].allmoney).toFixed(2));
        		}
        	
            }
        	
        	for(i=0;i<data.dataCountBalance.length;i++) {
        		var xuan=".money_change:eq(3) .money_change_itme:eq("+i+")";
        		$(xuan).find("label").html(data.dataCountBalance[i].Name);
        		if(data.dataCountBalance[i].allmoney==0){
        			$(xuan).find("span").html(0);
        		}else{
        			$(xuan).find("span").html((data.dataCountBalance[i].allmoney).toFixed(2));
        		}
        		
            }

            // for(i=0;i<data.dataCountplatformActivities.length;i++) {
            //     var xuan=".money_change:eq(4) .money_change_itme:eq("+i+")";
            //     $(xuan).find("label").html(data.dataCountplatformActivities[i].Name);
            //     if(data.dataCountplatformActivities[i].allmoney==0){
            //         $(xuan).find("span").html(0);
            //     }else{
            //         $(xuan).find("span").html((data.dataCountplatformActivities[i].allmoney).toFixed(2));
            //     }
            //
            // }
        }
	 });
}

/*日报表*/
function ribaobiao(){
	dayTime=$("#dayTime").val();
	
    $.ajax({
        type: "GET",
        url: "/finance/home/daylyReport",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: {dayTime:dayTime},
        success: function (data) {
           $("#dayStatement tbody tr td:eq(0)").html(data.reportForm.selectTime);
           $("#dayStatement tbody .color_success").html((data.reportForm.capitalLiuRu).toFixed(2));
           $("#dayStatement tbody .color_red").html((data.reportForm.capitalLiuChu).toFixed(2));
           $("#dayStatement tbody .color_default").html((data.reportForm.beginningBalance).toFixed(2));
           $(".rihref").attr("href","riyueselect?time="+data.reportForm.selectTime+"&type=ri");
        }
    });
}

/*月报表*/
function yuebaobiao(){
	monthTime=$("#monthTime").val();
	
	if(monthTime==""){
		$("#monthTime").val(nowYearDay());
		return;
	}
	
	var type;
	var d = new Date();
	if(parseInt(monthTime.substring(5,monthTime.length))==(d.getMonth()+1) || monthTime==""){
		monthTime=nowtime(new Date());
    }else{
        var t= new Date(monthTime+"-1");
        var date=new Date(t.getFullYear(), t.getMonth() + 1, 0, 23, 59, 59)
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        if(month==12){
            month=0;
            year=year+1;
        }
        var currentdate = year + seperator1 + (parseInt(month)+1) + seperator1 + 1;
        monthTime=currentdate;
        type="true"
    }
	
    $.ajax({
        type: "GET",
        url: "/finance/home/MonthlyReport",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: {monthTime:monthTime,type:type},
        async:false,
        success: function (data) {
        	 $("#monthStatement tbody tr td:eq(0)").html(data.monthReportForm.selectTime);
        	 $(".allmoney").html((data.monthReportForm.beginningBalance).toFixed(2));
             $("#monthStatement tbody .color_success").html((data.monthReportForm.capitalLiuRu).toFixed(2));
             $("#monthStatement tbody .color_red").html((data.monthReportForm.capitalLiuChu).toFixed(2));
             $("#monthStatement tbody .color_default").html((data.monthReportForm.openingBalance).toFixed(2));
             $(".yuehref").attr("href","riyueselect?time="+data.monthReportForm.selectTime+"&type=yue");
        }
    });
}

/*报表*/
function report(){
	if($(".reportType").val()==0){
		ribaobiao();
	}else if($(".reportType").val()==1){
		yuebaobiao();
	}
}

/*数据统计*/
function statistics(){
	statisticsStartTime=$("#statisticsStartTime").val(); 
	statisticsEndTime=$("#statisticsEndTime").val();

    if(statisticsStartTime==statisticsEndTime){
        var today=new Date(accountStartTime);
        var yesterday_milliseconds=today.getTime()+1000*60*60*24;

        var yesterday=new Date();
        yesterday.setTime(yesterday_milliseconds);

        var strYear=yesterday.getFullYear();
        var strDay=yesterday.getDate();
        var strMonth=yesterday.getMonth()+1;
        if(strMonth<10)
        {
            strMonth="0"+strMonth;
        }
        var strYesterday=strYear+"-"+strMonth+"-"+strDay;
        statisticsEndTime=strYesterday;
    }

	$.ajax({
        type: "GET",
        url: "/finance/home/dataCount",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async:false,
        data: {statisticsStartTime:statisticsStartTime,statisticsEndTime:statisticsEndTime},
        success: function (data) {
        	console.log(data);
        	statisticsObject=data.dataCount;
        }
    });
	pieChart();
}


/*每日交易汇报*/
function reportalso(){
	$.ajax({
        type: "GET",
        url: "/finance/home/selectSendDayly",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async:false,
        data: {statisticsStartTime:statisticsStartTime,statisticsEndTime:statisticsEndTime},
        success: function (data) {
        	/*报送内容：one*/
        	$(".oneInfo font:eq(0)").html((data.reportFormSendDay.capitalLiuRu).toFixed(2));
        	$(".oneInfo font:eq(1)").html((data.reportFormSendDay.capitalLiuChu).toFixed(2));
        	$(".oneInfo font:eq(2)").html((data.reportFormSendDay.beginningBalance).toFixed(2));
        	
        	/*报送内容：tow*/
        	$(".towInfo font:eq(0)").html((data.SendDaylyOrder.allOrderMoney).toFixed(2));
        	$(".towInfo font:eq(1)").html((data.SendDaylyOrder.successOrderMoney).toFixed(2));
        	$(".towInfo q:eq(0)").html((data.SendDaylyOrder.allOrderNum));
        	$(".towInfo q:eq(1)").html((data.SendDaylyOrder.successOrderNum));
        }
    });
}


/************************工具*****************************/
//获取一个时间的第一天
function getCurrentMonthFirst(){
    var date=new Date();
    date.setDate(1);
    return date;
}

//当前时间前一天
function getYesterda(){
	var today=new Date();
    var yesterday_milliseconds=today.getTime()-1000*60*60*24;
     
    var yesterday=new Date();      
    yesterday.setTime(yesterday_milliseconds);      
        
    var strYear=yesterday.getFullYear();   
    var strDay=yesterday.getDate();   
    var strMonth=yesterday.getMonth()+1;   
    if(strMonth<10)   
    {   
        strMonth="0"+strMonth;   
    }  
    if(strDay<10)   
    {   
    	strDay="0"+strDay;   
    }  
    var strYesterday=strYear+"-"+strMonth+"-"+strDay;   
    
    return strYesterday;
}

//将当前时间转换成yyyymmdd格式
function nowtime(mydate){
    var str = "" + mydate.getFullYear();
    var mm = mydate.getMonth()+1
    if(mydate.getMonth()>9){
        str +="-"+ mm;
    }
    else{
        str += "-0" + mm;
    }
    if(mydate.getDate()>9){
        str +="-"+mydate.getDate();

    }
    else{
        str += "-0" + mydate.getDate();
    }
    return str;
}

//获取当前年月
function nowYearDay(){
	function p(s) {
	    return s < 10 ? '0' + s: s;
	}

	var myDate = new Date();
	//获取当前年
	var year=myDate.getFullYear();
	//获取当前月
	var month=myDate.getMonth()+1;
	//获取当前日
	var date=myDate.getDate(); 
	var h=myDate.getHours();       //获取当前小时数(0-23)
	var m=myDate.getMinutes();     //获取当前分钟数(0-59)
	var s=myDate.getSeconds();  

	var now=year+'-'+p(month);
	return now;

}

function Decimal(){
	$(".money_change_itme").each(function(){
    	$(this).find(".color_success").html(parseFloat($(this).find(".color_success").html()));
    });
    
    $(".color_default").each(function(){
    	$(this).html(parseFloat($(this).html()).toFixed(2));
    });
    
    $(".table-bordered .color_success").each(function(){
    	$(this).html(parseFloat($(this).html()).toFixed(2));
    });
    
    $(".color_red").each(function(){
    	$(this).html(parseFloat($(this).html()).toFixed(2));
    });
}

/*饼状图*/
function pieChart(){
	var sw=0.0;
	var cy=0.0;
	var jd=0.0;
	var swmoney=0.0;
	var cymoney=0.0;
	var jdmoney=0.0;
	if(statisticsObject.length==0){
		$(".pie_chart").html("<p class='chart_title' style='padding-top:20px;'>暂无数据</p>");
		return false;
	}
	for(var i=0;i<statisticsObject.length;i++){
		if(statisticsObject[i].order_type=="good"){
			sw=statisticsObject[i].percentage;
			swmoney=statisticsObject[i].amountMoney;
		}
		if(statisticsObject[i].order_type=="book_food"){
			cy=statisticsObject[i].percentage;
			cymoney=statisticsObject[i].amountMoney;
		}
		if(statisticsObject[i].order_type=="book_room"){
			jd=statisticsObject[i].percentage;
			jdmoney=statisticsObject[i].amountMoney;
		}
	}
	
	//饼图
	var donutData = [{
		label: "售货-"+(sw*100).toFixed(2)+"%  -￥"+swmoney,
		data: swmoney,
		color: "#4e7fbb"
	}, {
		label: "餐饮-"+(cy*100).toFixed(2)+"%  -￥"+cymoney,
		data: cymoney,
		color: "#be4f4c"
	}, {
		label: "酒店-"+(jd*100).toFixed(2)+"%  -￥"+jdmoney,
		data: jdmoney,
		color: "#99b958"
	}, {
		label: "休闲娱乐-0%  -￥0",
		data: 0,
		color: "#7e63a0"
	}];
	$.plot(".pie_chart", donutData, {
		series: {
			pie: {
				show: true,
				radius: 1,
				innerRadius: 0.5,
				label: {
					show: true,
					radius: 2 / 3,
					formatter: labelFormatter,
					threshold: 0.1
				}

			}
		}
	});

	function labelFormatter(label, series) {
		return "<div style='font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;'>" +
			label
			"<br/></div>";
			//Math.round(series.percent) + "%
	}


}