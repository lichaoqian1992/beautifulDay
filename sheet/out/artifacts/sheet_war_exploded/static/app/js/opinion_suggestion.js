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
    	$("#myForm").submit();
    	
    	$(".success_mask").show();
    	setTimeout(function(){
    		$(".success_mask").hide();
    	},1000)
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
    	$("#message").show();
    }
    function cancle(){
    	$("#message").hide();
    }
    function makeSure(){
    	$("#message").hide();
    }
