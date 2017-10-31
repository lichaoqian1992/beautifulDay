var descr = "";
$(function(){
	$(".informant_list ul li button").click(function(){
		$(".informant_list ul li button").removeClass("li_active");
		$(this).addClass("li_active");
		$("#type_code").val($(this).val());
		
		
	})
	$(".problem_type textarea").keyup(function(){
		var total = $(this).val();
		$(".word_total_span1").text(total.length);
	})
    $(".mui-content-padded").on("click", ".mui-btn", function() {
    	descr = $("#desc").val();
    	if($("#type_code").val().length <= 0){
    		showMessage("请选择举报类型");
    		return;
    	}
    	if(descr == ""){
    		showMessage("描述不能为空!");
    		return;
    	}
    	//校验电话号码
    	var str = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|177)\d{8}$/;
    	if($("#sponsor_contact").val() != undefined){
    		if($("#sponsor_contact").val() != null || $("#sponsor_contact").val() != "" || $("#sponsor_contact").val() != undefined){
        		if(!str.test($("#sponsor_contact").val())){
        			showMessage("电话号码有误！");
            		return;
        		}
        	}else{
        		showMessage("联系方式必填！");
        		return;
        	}
    	}
    	$("#descr").val(descr);
    	$(".tip_mask").show();
    })
    $(".mask_content_btn .span1").click(function(){
    	$(".tip_mask").hide();
    })
    $(".mask_content_btn .span2").click(function(){
    	var code = $("#type_code").val()
    	//先判断是交易纠纷，然后判断该订单的纠纷类型是非已经申请过了，不然就提示已经发起过了
    	if(code.split("_")[0] == "06" || code.split("_")[0] == "07"){
    		//根据单号和类型编码查询是否存在
    		$.ajax({
    			type:"get",
    			url:"/sheet/buyersApp/checkRepeat",
    			dataType:"json",
    			data:{
    				orderId:$("#orderId").val(),
    				code:$("#type_code").val()
    			},success:function(data){
    				if(data.message != "SUCCESS"){
    					//设置按钮为不可点击
    			    	$("#mybutton").attr("disabled",true);
    					$("#myForm").submit();
    					$(".tip_mask").hide();
    			    	$(".success_mask").show();
    			    	setTimeout(function(){
    			    		$(".success_mask").hide();
    			    	},1000)
    				}else{
    					showMessage("已经申请过该纠纷类型了");
    				}
    			}
    		});
    	}else{
    		//设置按钮为不可点击
        	$("#mybutton").attr("disabled",true);
    		$("#myForm").submit();
    		$(".tip_mask").hide();
        	$(".success_mask").show();
        	setTimeout(function(){
        		$(".success_mask").hide();
        	},1000)
    	}
    })
})
function showMessage(message){
	$("#content").html(message);
	$("#message").show();
}
function cancle(){
	$("#message").hide();
}
function makeSure(){
	$("#message").hide();
}
