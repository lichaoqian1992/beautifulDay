
$(function() {
	//http://localhost:8080/finance/rec/record?nav=nav7&orderNo=1
	var url = location.search;
    if(url.indexOf("orderNo") != -1){
        var orderNo = url.split("?")[1].split("&")[1].split("=")[1];
        $("#orderNo").val(orderNo);
        //doHref(orderNo);
    }
	findByCondition(1);
				/*时间插件调用
				 */
				$('.datepicker').datepicker({
					format: 'yyyy-mm-dd',
					autoclose: true,
					language: 'cn'
				});
				
				/*
				 * 弹出窗
				 */
				$(".table").on("click",".rechargeDetails",function(event){//查看详情
					event.preventDefault();
					layer.open({
						type: 2,
						title:"充值单详情",
						btn: ['关闭'], //按钮
						shade: 0.8,
						shadeClose: true,
						skin: 'layui-layer-rim', //加上边框
						area: ['850px', '500px'], //宽高
						content: $(this).attr("href"),
						yes:function(){
							layer.closeAll();
						}
					});
				});

			});
/**
 * 查询充值记录
 */
function findByCondition(pageNum){
	findAcount();
	if(pageNum == undefined || null == pageNum || "" == pageNum){
		pageNum =1;
	}
	var txt = "";
	//判断一下时间是否正确
	if(new Date($("#startTime").val()).getTime()>new Date($("#endTime").val()).getTime()){
		layer.msg('开始时间不能大于结束时间', {
			icon: 2,
			time: 1000
		});
		return;
	}
	$.ajax({
		type:"get",
		url:"/finance/rec/findRecord",
		dataType:"json",
		async:false,
		data:{
			orderNo:$("#orderNo").val(),
			accountName:$("#user_key").val(),
			status:$("#status").val(),
			rechargeWay:$("#recharge_way").val(),
			startTime:$("#startTime").val(),
			endTime:$("#endTime").val(),
			pageNum:pageNum,
			recType:"充值记录",
			remark:$("#remark").val()
		},success:function(data){
			console.log(data);
			if(data.list.length>0){
				for(var i=0;i<data.list.length;i++){
					if(data.list[i].RECHARGE_WAY == "自主添加" || data.list[i].RECHARGE_WAY == "OA充值"){
						data.list[i].RECHARGE_WAY = "OA充值";
					}else{
						data.list[i].RECHARGE_WAY = "临时充值";
					}
					txt +="<tr><td><input type='checkbox'></td><td>"
						+(i+1)+
						"</td><td>"
						+data.list[i].tranSN+
						"</td><td>"
						+data.list[i].RECHARGE_ORDER_NO+
						"</td><td>"
						+data.list[i].RECHARGE_WAY+
						"</td><td>"
						+data.list[i].RECHARGE_TYPE+
						"</td><td>"
						+data.list[i].USER_NAME+
						"</td><td><a style='cursor:pointer;' target='_blank' href='/finance/home/userDetail?nickName="+data.list[i].USER_KEY+"&nav=nav5'>"
						+data.list[i].USER_KEY+
						"</td><td>"
						+data.list[i].RECHARGE_MONEY+
						"</td><td>"
						+data.list[i].ARRIVAL_MONEY+
						"</td><td>"
						+data.list[i].STATUS+
						"</td><td>"
						+data.list[i].ARRIVAL_TIME+
						"</td><td>"
						+data.list[i].REMARK+
						"</td><td><a href='rechargeDetails.html?orderNO="+data.list[i].RECHARGE_ORDER_NO+"' class='href_a rechargeDetails'>详情</a></td></tr>";
						
				}
			}else{
				txt = "<tr><td colspan='13' class='table_no_data'>暂无数据！</td></tr>"
			}
			$("#firstPage").val(1);
			$("#nowPage").val(data.pageNumber);
			$("#lastPage").val(data.totalPage);
			$("#pageInfo").html("共"+data.totalRow+"条数据，当前显示第 "+data.pageNumber+" 页  ，到");
			$("#recordInfo").html(txt);
		}
	});
}
/**
 * 查询当日和当月的充值总金额
 */
function findAcount(){
	$.ajax({
		type:"get",
		url:"/finance/rec/findAcount",
		dataType:"json",
		success:function(data){
			$("#daysCount").html("当日累计充值："+data.aa.split(";")[0]);
			$("#monthCount").html("当月累计充值："+data.aa.split(";")[1]);
		}
	});
}
/**
 * 导出excel
 */
function toExcel(){
	//先判断是否有数据
	var mytable3=document.getElementById("recordInfo").getElementsByTagName("tr");
	var no = $(mytable3).eq(0).find("td").eq(0).html();
	if(no == "暂无数据！"){
		layer.msg('没有数据，不允许导出', {
			icon: 2
		});
		return;
	}
	window.location.href = "/finance/rec/toExcel?orderNo="+$("#orderNo").val()+"&userName="+$("#user_key").val()+"&status="+$("#status").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&rechargeWay="+$("#recharge_way").val();
}