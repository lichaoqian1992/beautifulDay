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
    	$("#descr").val(descr);
    	//设置按钮为不可点击
    	$("#mybutton").attr("disabled",true);
    	
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
    })
//  $(".mask_content_btn .span1").click(function(){
//  	$(".tip_mask").hide();
//  })
//  $(".mask_content_btn .span2").click(function(){
//  	$(".tip_mask").hide();
//  	$(".success_mask").show();
//  	setTimeout(function(){
//  		$(".success_mask").hide();
//  	},1000)
//  })
})
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
