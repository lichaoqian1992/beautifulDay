/**
 * 修改有OA的充值信息
 */
var orderNo = "";
$(function(){
	
	//得到链接中的参数值
	var url = location.search;
    if(url.indexOf("?") != -1){
        var orderNo2 = url.split("?")[1].split("=")[1];
        orderNo = orderNo2;
    }
    findRec(orderNo);
	
});
/**
 * 查询信息
 */
function findRec(orderNo){
	
	$.ajax({
		type:"get",
		url:"/finance/rec/findByOrderNo",
		dataType:"json",
		data:{
			orderNo:orderNo,
			recType:"非充值记录",
			pageNum:1
		},success:function(data){
			console.log(data);
			var xx = data.list[0];
			$("#id").val(xx.ID);
			$("#oaId").val(xx.OANO);
			$("#rechargeType").val(xx.RECHARGE_TYPE);
			$("#accountName").val(xx.USER_KEY);
			$("#userName").val(xx.USER_NAME);
			$("#idCard").val(xx.IDCARD);
			$("#money").val(xx.RECHARGE_MONEY);
			if(xx.withDrawls == "不可提现"){
		        $("input[name=moneytype]:eq(1)").attr("checked",'checked');
		        $("#withDrawls").val(4);
		    }else{
		        $("input[name=moneytype]:eq(0)").attr("checked",'checked');
		        $("#withDrawls").val(6);
		    }
			var rechargeType = document.getElementById("rechargeType").options;
		    for(var i=0;i<rechargeType.length;i++){
		    	if(rechargeType[i].text == xx.RECHARGE_TYPE){
		    		rechargeType[i].selected = true;
		    	}
		    }
		}
		
	});
}
/**
 * 重新提交充值信息
 */
function update(){
	var id = $("#id").val();
	var oaNo = $("#oaId").val();
	var rechargeType = $("#rechargeType").val();
	var accountName = $("#accountName").val();
	var userName = $("#userName").val();
	var idCard = $("#idCard").val();
	var accountMoney = $("#money").val();
	var type = "修改";
	$.ajax({
		type:"get",
		url:"/finance/rec/saveRechargeInfo",
		dataType:"json",
		data:{
			PERSON_RELEASE:$("#creater").val(),
			RECHARGE_TYPE:rechargeType,
			USER_KEY:accountName,
			USER_NAME:userName,
            IDCARD:idCard,
            RECHARGE_MONEY:accountMoney,
            withDrawls:$("#withDrawls").val(),
            RECHARGE_WAY:"OA充值",
            CHECK_STATUS:"1",
            OANO:oaNo,
            REMARK:type,
            ID:id
		},success:function(data){
			if(data.aa == "SUCCESS"){
				if(type == "修改"){
					layer.msg('修改成功', {
						icon: 1
					});
				}else{
					layer.msg('添加成功', {
						icon: 1
					});
				}
				window.location.href="/finance/rec/apply";
			}
			//清空信息
			/*$("#accountName").val("");
		    $("#userName").val("");
		    $("#idCard").val("");
		    $("#money").val("");
		    $("#accountName1").val("");
		    $("#userName2").val("");
		    $("#idCard1").val("");
		    $("#rechargeMoney1").val("");*/
		}
	});
}