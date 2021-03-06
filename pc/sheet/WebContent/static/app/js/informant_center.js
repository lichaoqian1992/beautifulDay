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
    		if($("#jiufen").val() == "TRD"){
    			showMessage("请选择纠纷类型");
    		}else{
    			showMessage("请选择举报类型");
    		}
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
    					
    					postM();
    					
    				}else{
    					showMessage("已经申请过该纠纷类型了");
    				}
    			}
    		});
    	}else{
    		postM();
    	}
    })
})

function postM(){
	
	var ajaxbg = $(".background1, .progressBar1"); 
	$("#mybutton").attr("submited",false);
	$(".tip_mask").hide();
	$(".success_mask2").show(); 
	setTimeout(function(){
		var formData = new FormData($("#myForm")[0]); 
		
		$.ajax({
			type:"post",
			url:"/sheet/buyersApp/shopReport",
			dataType:"json",
			data:formData,
			cache: false,
			processData: false,
	        contentType: false, 
			success:function(data){
				$(".success_mask2").hide(); 
				if(data.message == "SUCCESS"){
					var code = $("#type_code").val().split("_")[0];
					$(".success_mask").show();
					//如果不是举报商家/商品
					if(code != "01" && code != "02"){
						setTimeout(function(){
							$(".success_mask").hide();
						}, 1000);
						window.location.href = "/sheet/buyersApp/myInfo?codeReport="+code;
					}else{
						setTimeout(function(){
		        			closePage();
		        		}, 1500);
					}
				}
			}
		});
	},1000);
}

function showMessage(message){
	
	$("#content").html(message);
	$(".btn_tip_content3").show();
	setTimeout(function(){
		$(".btn_tip_content3").hide();
	}, 2000);
}
function cancle(){
	$("#message").hide();
}
function makeSure(){
	$("#message").hide();
}
