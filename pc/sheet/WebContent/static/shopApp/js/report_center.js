$(function(){
	/*$('#short_title').val($('#cshort_title').val());
	$('#code_type').val($('#ccode_type').val());*/

	$(".report_type").click(function(){
		$(".mask_all").show();
	})
	$(".mask_content_list li").click(function(){
		$(".mask_all").hide();
		var value = $(this).text();
		$(".report_type .span2").text(value);

		$("#code_type").val($(this).prev().html());
		$("#short_title").val($(this).next().html());
	})
	$(".mask_content3 .item_last").click(function(){
		$(".mask_all").hide();
	})
	//$(".report_btn").click(function(){
	//	if($("#descr").val() == ""){
	//		$(".tishi").show();
	//		setTimeout(function(){
	//			$(".tishi").hide();
	//		},1000)
	//		return;
	//	}else if($('#short_title').val() == ""){
	//		$(".tishi2").show();
	//		setTimeout(function(){
	//			$(".tishi2").hide();
	//		},1000)
	//		return;
	//	}else{
	//		$(".btn_tip_content").show();
	//		setTimeout(function(){
	//			$(".btn_tip_content").hide();
	//		},1000)
	//	}
	//})
	$(".report_problem textarea").keyup(function(){
		var total = $(this).val();
		$(".text_total .span1").text(total.length);
	})
    //补充资料的弹窗的js
	$(".update_btn").click(function(){
		var descr=$('#descr').val();
		if (descr==''){
			$(".add_mask_success").html("请填写资料");
			$(".add_mask_success").show();
			setTimeout(function(){
				$(".add_mask_success").hide();
			},1000);
			return;
		}
		$(".add_mask_all").show();
	})
	$(".add_mask_cancel").click(function(){
		$(".add_mask_all").hide();
	})
	//$(".add_mask_confirm").click(function(){
	//	var sheet_id=$('#sheet_id').val();
	//	window.location.href="/sheet/shopApp/findSheetInfo?sheet_id="+sheet_id+"";
	//})
})


$(function(){
	$(".content a").click(function(){
		if($("#descr").val() != ""){
			$(".tel_mask").show();
		}else{
			if ($(this).next().val()=='j'){
				location.href="/sheet/shopApp/Myreport";
			}else if ($(this).next().val()=='y'){
				location.href="/sheet/shopApp/Proposal";
			}
		}

	})
	$(".mask_content_bottom .a1").click(function(){
		$(".tel_mask").hide();
	});

	$('.update-back').click(function(){
		if($("#descr").val() != ""){
			$(".tel_mask").show();
		}else{
			history.back(-1);
		}
	})
})
