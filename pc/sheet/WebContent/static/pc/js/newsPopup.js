$(function(){
	$(".kf_footer_news img").click(function(){
		var type = $(this).attr("type");
		if(type == "1" || type == "3"){
			$(this).attr("type",2);
			$(this).attr("src","/sheet/static/pc/images/home_down.png");
			$(".kf_footer_ul1").show();
			$(".kf_footer_ul2").hide();
			$(".kf_footer_li2").show();
			
		}else{
			$(this).attr("type",3);
			$(this).attr("src","/sheet/static/pc/images/home_up.png");
			$(".kf_footer_ul1").hide();
			$(".kf_footer_ul2").hide();
		}

	})
    $(".kf_footer_li2 span").click(function(){
		$(".kf_footer_li2").hide();
		$(".kf_footer_ul2").show();
	})
})
