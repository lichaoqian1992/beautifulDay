var orderNo = "";
$(function() {
	//得到参数
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
	var myMoney = 0; 
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
			//审批人
			$("#realName").val(xx.PERSON_APPROVING_REALNAME);
			$("#roleName").val(xx.PERSON_APPROVING);
			$("#roleNameId").val(xx.PERSON_APPROVING_ID);
			//充值类型
			var rechargeType = document.getElementById("rechargeType").options;
		    for(var i=0;i<rechargeType.length;i++){
		    	if(rechargeType[i].text == xx.RECHARGE_TYPE){
		    		rechargeType[i].selected = true;
		    	}
		    }
		    //审批人
		    var pMoney = document.getElementById("pMoney").options;
		    for(var i=0;i<pMoney.length;i++){
		    	if(xx.PERSON_APPROVING == "资金部负责人"){
		    		myMoney = "0-1000元（含）";
		    	}else if(xx.PERSON_APPROVING == "财务经理"){
		    		myMoney = "1000-5000元（含）";
		    	}else if(xx.PERSON_APPROVING == "财务副总裁"){
		    		myMoney = "5000-10000元（含）";
		    	}else if(xx.PERSON_APPROVING == "董事长"){
		    		myMoney = "10000元以上";
		    	}
		    	if(pMoney[i].text == myMoney){
		    		pMoney[i].selected = true;
		    	}
		    }
		}
		
	});
}
/**
 * 发送短信验证码
 */
function sendMessage(obj){
	var temp = $("#money").val();;
	var rechargeMoney = $("#money").val();
	$.ajax({
        type:"GET",
        url:"/finance/rec/getYzm",
        dataType:"json",
        data:{
            creater:$("#creater").val(),
            money:rechargeMoney,
            number:1,
            maxMoney:temp
        },success:function(data){
        	if(!$(obj).attr("disabled")) {
        		if(data.aa == "SUCCESS"){
        			layer.msg('验证短信已发送', {
            			icon: 1,
            			time: 1000
            		});
            		countDown(obj);
        		}else if(data.aa == "ERROR"){
        			layer.msg('验证短信发送失败', {
            			icon: 1,
            			time: 1000
            		});
        		}else if(data.aa == "FULL"){
        			layer.msg('该审核人今日审核额度已满', {
            			icon: 1,
            			time: 1000
            		});
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
	var oaNo = "--";
	var rechargeType = $("#rechargeType").val();
	var accountName = $("#accountName").val();
	var userName = $("#userName").val();
	var idCard = $("#idCard").val();
	var accountMoney = $("#money").val();
	var type = "修改";
	if($("#yzm").val() == ""){
    	layer.msg('请填写验证码', {
			icon: 1
		});
        return;
    }
	$.ajax({
		type:"get",
		url:"/finance/rec/updateRecBack",
		dataType:"json",
		data:{
			creater:$("#creater").val(),
			rechargeType:rechargeType,
			accountName:accountName,
			userName:userName,
            idCard:idCard,
            money:accountMoney,
            withdrawlsType:$("#withDrawls").val(),
            rechargeWay:"临时充值",
            oaNo:oaNo,
            checkStatus:"1",
            yzm:$("#yzm").val(),
            id:id
		},success:function(data){
			console.log(data);
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
			}else if(data.aa == "ERROR"){
				layer.msg('验证码错误', {
					icon: 1
				});
			}else{
				layer.msg('修改失败', {
					icon: 1
				});
			}
		}
	});
}
//倒计时
function countDown(_this) {
	var m = 9;
	var s = 59;
	var time = setInterval(function() {
		$(_this).attr("disabled", "disabled");
		if(s < 10) {
			$(_this).text('剩余时间' + m + ':0' + s);
		} else {
			$(_this).text('剩余时间' + m + ':' + s);
		}
		s--;
		if(s < 0) {
			s = 59;
			m--;
		}
		if(s == 0 && m == 0) {
			clearInterval(time);
			$(_this).text('发送验证短信').removeAttr("disabled");
		}
	}, 1000)
}