$(function(){
	$(".state_apply2 span").click(function(){
		$(this).addClass("span_active");
		$(".state_apply3 span").removeClass("span_active");
		$(".state2_container").hide();
		$(".state1_container").show()
	})
		$(".state_apply3 span").click(function(){
		$(this).addClass("span_active");
		$(".state_apply2 span").removeClass("span_active");
		$(".state1_container").hide();
		$(".state2_container").show();
	})
	$(".state2_li").click(function(){
		
		
	});
})

