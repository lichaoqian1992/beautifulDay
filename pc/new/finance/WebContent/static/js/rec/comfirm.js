/**
 * 充值确认
 */
$(function() {
	findByCondition(1);
	/*时间插件调用
	 */
	$('.datepicker').datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true,
		language: 'cn'
	});
	
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
 * 查询充值信息
 */
function findByCondition(pageNum){
	if(pageNum == undefined || null == pageNum || "" == pageNum){
		pageNum =1;
	}
	var txt = "";
	var orderNo = $("#orderNo").val();
	var accountName = $("#accountName").val();
	var rechargeType = $("#rechargeType").val();
	var rechargeWay = $("#rechargeWay").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	//判断一下时间是否正确
	if(new Date(startTime).getTime()>new Date(endTime).getTime()){
		layer.msg('开始时间不能大于结束时间', {
			icon: 2,
			time: 1000
		});
		return;
	}
	$.ajax({
		type:"get",
		url:"/finance/rec/findApplyRec",
		dataType:"json",
		data:{
			orderNo:orderNo,
			accountName:accountName,
			checkStatus:"2",
			rechargeType:rechargeType,
			rechargeWay:rechargeWay,
			startTime:startTime,
			endTime:endTime,
			pageNum:pageNum,
			recType:"充值确认",
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
						"</td><td id='orderNo'>"
						+data.list[i].RECHARGE_ORDER_NO+
						"</td><td>"
						+data.list[i].RECHARGE_WAY+
						"</td><td>"
						+data.list[i].OANO+
						"</td><td>"
						+data.list[i].RECHARGE_TYPE+
						"</td><td>"
						+data.list[i].USER_NAME+
						"</td><td id='accountName'><a style='cursor:pointer;' target='_blank' href='/finance/home/userDetail?nickName="+data.list[i].USER_KEY+"&nav=nav5'>"
						+data.list[i].USER_KEY+
						"</td><td>"
						+data.list[i].RECHARGE_MONEY+
						"</td><td>"
						+data.list[i].withDrawls+
						"</td><td>"
						+data.list[i].CREATE_TIME+
						"</td><td>"
						+data.list[i].REMARK+
						"</td><td style='color:red;'>"
						+data.list[i].CHECK_STATUS+
						"</td><td>"+
						"<button class='btn btn-primary agreed_review' onclick='makeSure(this)'>确认充值</button>"+
						"<button class='btn btn-primary withdraw_audit' onclick='backOrder(this)'>撤回</button>"+
						"<a href='rechargeDetails.html?orderNO="+data.list[i].RECHARGE_ORDER_NO+"' class='href_a rechargeDetails'>查看详情</a>"+
						"</td></tr>";
				}
			}else{
				txt = "<tr><td colspan='13' style='text-align:center;'>暂无数据</td></tr>"
			}
			$("#firstPage").val(1);
			$("#nowPage").val(data.pageNumber);
			$("#lastPage").val(data.totalPage);
			$("#pageInfo").html("共"+data.totalRow+"条数据，当前显示第 "+data.pageNumber+" 页  ，到");
			$("#content").html(txt);
		}
	});
}
/**
 * 撤回充值单
 */
function backOrder(obj){
	var orderNo = $(obj).parent().parent().find("#orderNo").text();
	var xx = '<div class="layer_box" style="display:block;"><div class="deblocking_account_box"><div class="deblocking_account_content">'+
	'<label id="message">确定撤回充值单'+orderNo+'？</label><div class="layer_group"><label class="layer_title">请填写原因:</label><div class="layer_content">'+
			'<textarea placeholder="请输入撤回原因" id="reason"></textarea></div></div></div></div></div>';
	
	layer.open({
		type: 1,
		title:" 撤回充值单",
		btn: ['确认','取消'], //按钮
		skin: 'layui-layer-rim', //加上边框
		area: ['420px', '250px'], //宽高
		content: xx,
		yes:function(){
			//执行撤回的逻辑
			var reason = $("#reason").val();
			$.ajax({
				type:"get",
				url:"/finance/rec/backOrder",
				dataType:"json",
				data:{
					orderNo:orderNo,
					reason:reason,
					creater:$("#creater").val()
				},success:function(data){
					console.log(data);
					if(data.aa == "SUCCESS"){
						layer.msg('撤回成功', {time:1000,icon: 1},function(){
							layer.closeAll();
						});
					}else{
						layer.msg('撤回失败', {time:1000,icon: 1},function(){
							layer.closeAll();
						});
					}
					findByCondition(1);
				}
			});
		}
	});
}
/**
 * 确认充值提示框
 * @param obj
 */
function makeSure(obj){
	var orderNo = $(obj).parent().parent().find("#orderNo").text();
	var accountName = $(obj).parent().parent().find("#accountName").text();
	layer.confirm('确认充值单'+orderNo+'可以进行充值操作？', {
		title:"确认充值提示",
	  	btn: ['确认','取消'] //按钮
	}, function(){
		var a = orderNo+";";
		var b = accountName+";";
		confirmRec(a,b);
	  	//layer.msg('确认充值', {time:1000,icon: 1});
	});
}
/**
 * 批量充值提示框
 */
function batchMakeSure(){
	//获取总数据
	var orderNo = "";
	var accountName = "";
	var mytable3=document.getElementById("content").getElementsByTagName("tr");
	for(var i=0;i<mytable3.length;i++){
		if($(mytable3).eq(i).find("td").eq(2).html() != undefined && undefined !=$(mytable3).eq(i).find("td").eq(7).html()){
			orderNo += $(mytable3).eq(i).find("td").eq(2).html()+";";
			accountName += $(mytable3).eq(i).find("td").eq(7).html()+";";
		}
	}
	if(orderNo.length>0){
		layer.confirm('共'+mytable3.length+'笔充值单，确认通过审核？', {
			title:"确认充值提示",
		  	btn: ['确认','取消'] //按钮
		}, function(){
			confirmRec(orderNo,accountName);
		  	//layer.msg('确认充值', {time:1000,icon: 1});
		});
	}else{
		layer.msg("没有可同意的数据", {time:1000,icon: 1});
	}
}
/**
 * 确认充值，传参：充值单号和充值账号
 */
function confirmRec(orderList,accountList){
	layer.load();
	$.ajax({
		type:"get",
		url:"/finance/rec/confirmRec",
		dataType:"json",
		async:false,
		data:{
			orderList:orderList,
			accountNameList:accountList,
			creater:$("#creater").val()
		},success:function(data){
			setTimeout(function(){
                layer.closeAll('loading');
            }, 500);
			console.log(data);
			layer.msg(data.aa, {time:3000,icon: 1});
			findByCondition(1);
		}
	});
}
