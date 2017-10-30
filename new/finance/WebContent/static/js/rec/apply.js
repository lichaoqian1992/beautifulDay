/**
 * 充值申请页面相关操作
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
	var checkStatus = $("#checkStatus").val();
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
			checkStatus:checkStatus,
			rechargeType:rechargeType,
			rechargeWay:rechargeWay,
			startTime:startTime,
			endTime:endTime,
			pageNum:pageNum,
			recType:"充值申请"
		},success:function(data){
			console.log(data);
			if(data.list.length>0){
				for(var i=0;i<data.list.length;i++){
					var ss = "";
					if(data.list[i].RECHARGE_WAY == "自主添加" || data.list[i].RECHARGE_WAY == "OA充值"){
						data.list[i].RECHARGE_WAY = "OA充值";
					}else{
						data.list[i].RECHARGE_WAY = "临时充值";
					}
					if(data.list[i].CHECK_STATUS != "被撤回"){
						ss = "style='display:none;'"
					}
					if(data.list[i].RECHARGE_WAY != "临时充值"){
						//.html?orderNO="+data.list[i].RECHARGE_ORDER_NO;
						var mm = "/finance/rec/rechargePayOA?orderNo="+data.list[i].RECHARGE_ORDER_NO;
					}else{
						var mm = "/finance/rec/rechargePayNoOA?orderNO="+data.list[i].RECHARGE_ORDER_NO;
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
						"</td><td>"
						+data.list[i].USER_KEY+
						"</td><td>"
						+data.list[i].RECHARGE_MONEY+
						"</td><td>"
						+data.list[i].withDrawls+
						"</td><td>"
						+data.list[i].CREATE_TIME+
						"</td><td style='color:red;'>"
						+data.list[i].CHECK_STATUS+
						"</td><td>"+
						"<a class='btn btn-primary' href='"+mm+"'"+ss+">修改</a>"+
						"<button class='btn btn-primary rechargeCancellation'"+ss+" onclick='xx(this)'>作废</button>"+
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
 * 作废充值单
 * @param obj
 */
function xx(obj){
	var orderNo = $(obj).parent().parent().find("#orderNo").text();
	var xx = '<div class="layer_box" style="display:block;"><div class="deblocking_account_box"><div class="deblocking_account_content">'+
	'<label id="message">确定将充值单'+orderNo+'作废？</label><div class="layer_group"><label class="layer_title">请填写原因:</label><div class="layer_content">'+
			'<textarea placeholder="请输入作废原因" id="reason"></textarea></div></div></div></div></div>';
	
layer.open({
	type: 1,
	title:"作废充值单",
	btn: ['确认','取消'], //按钮
	skin: 'layui-layer-rim', //加上边框
	area: ['420px', '250px'], //宽高
	content: xx,
	yes:function(){
		var reason = $("#reason").val();
		if(reason == null || "" == reason){
			layer.msg('请填写作废原因', {
				icon: 2,
				time: 1000
			});
			return;
		}
		$.ajax({
			type:"get",
			url:"/finance/rec/cancelOrder",
			dataType:"json",
			data:{
				orderNo:orderNo,
				reason:reason,
				creater:$("#creater").val()
			},success:function(data){
				if(data.aa == "SUCCESS"){
					layer.msg('作废成功', {time:1000,icon: 1},function(){
						layer.closeAll();
					});
					findByCondition(1);
				}else{
					layer.msg('作废失败', {time:1000,icon: 1},function(){
						layer.closeAll();
					});
				}
			}
		});
		
	}
});
}