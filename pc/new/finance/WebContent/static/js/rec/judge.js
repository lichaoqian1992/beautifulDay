/**
 * 充值审核页面js
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
 * 查询未审核的充值数
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
			checkStatus:"1",
			rechargeType:rechargeType,
			rechargeWay:rechargeWay,
			startTime:startTime,
			endTime:endTime,
			pageNum:pageNum,
			recType:"充值审核",
			remark:$("#remark").val()
		},success:function(data){
			console.log(data);
			var countMoney = 0;
			if(data.list.length>0){
				for(var i=0;i<data.list.length;i++){
					if(data.list[i].RECHARGE_WAY == "自主添加" || data.list[i].RECHARGE_WAY == "OA充值"){
						data.list[i].RECHARGE_WAY = "OA充值";
					}else{
						data.list[i].RECHARGE_WAY = "临时充值";
					}
					countMoney += data.list[i].RECHARGE_MONEY;
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
						"</td><td><a style='cursor:pointer;' target='_blank' href='/finance/home/userDetail?nickName="+data.list[i].USER_KEY+"&nav=nav5'>"
						+data.list[i].USER_KEY+
						"</a></td><td>"
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
						"<button class='btn btn-primary agreed_review' onclick='agree(this)'>同意</button>"+
						"<button class='btn btn-primary withdraw_audit' onclick='backOrder(this)'>撤回</button>"+
						"<a href='rechargeDetails.html?orderNO="+data.list[i].RECHARGE_ORDER_NO+"' class='href_a rechargeDetails'>查看详情</a>"+
						"</td></tr>";
				}
			}else{
				txt = "<tr><td colspan='14' style='text-align:center;'>暂无数据</td></tr>"
			}
			$("#daysCount").html("充值总金额："+countMoney+"元");
			$("#firstPage").val(1);
			$("#nowPage").val(data.pageNumber);
			$("#lastPage").val(data.totalPage);
			$("#pageInfo").html("共"+data.totalRow+"条数据，当前显示第 "+data.pageNumber+" 页  ，到");
			$("#content").html(txt);
		}
	});
}
/*function toUser(obj){
	var name = $(obj).text().trim();
	window.location.href="/finance/home/userDetail?nickName="+name+"&nav=nav5";
}*/
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
			if(reason == null || "" == reason){
				layer.msg('请填写撤回原因', {
					icon: 2,
					time: 1000
				});
				return;
			}
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
 * 单个同意
 * @param obj
 */
function agree(obj){
	var orderNo = $(obj).parent().parent().find("#orderNo").text();
	layer.confirm('确认充值单'+orderNo+'通过审核？', {
		title:"确认提示",
	  	btn: ['确认','取消'] //按钮
	}, function(){
		makeSure(orderNo+";");
	  	
	});
}
/**
 * 批量同意
 */
function batchAgree(){
	//获取总数据
	var orderNo = "";
	var mytable3=document.getElementById("content").getElementsByTagName("tr");
	for(var i=0;i<mytable3.length;i++){
		if($(mytable3).eq(i).find("td").eq(2).html() != undefined){
			orderNo += $(mytable3).eq(i).find("td").eq(2).html()+";";
		}	
	}
	if(orderNo.length>0){
		layer.confirm('共'+mytable3.length+'笔充值单，确认通过审核？', {
			title:"确认提示",
		  	btn: ['确认','取消'] //按钮
		}, function(){
		  	makeSure(orderNo);
		});
	}else{
		layer.msg("没有可审核的数据", {time:1000,icon: 1});
	}
}
/**
 * 同意或者批量同意充值申请
 */
function makeSure(orderNoList){
	$.ajax({
		type:"get",
		url:"/finance/rec/batchAgree",
		dataType:"json",
		data:{
			orderList:orderNoList,
			creater:$("#creater").val()
		},success:function(data){
			console.log(data);
			if(data.aa == "SUCCESS"){
				layer.msg('审核通过', {time:1000,icon: 1});
			}else{
				layer.msg('审核失败', {time:1000,icon: 1});
			}
			findByCondition(1);
		}
	});
}