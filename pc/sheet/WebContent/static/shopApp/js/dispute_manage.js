$(function(){
	$(".header_title li .span1").click(function(){
		$(".header_title li span").removeClass("span_active");
		$(this).addClass("span_active");
		$(".list1").show();
		$(".list2").hide();
		$(".list3").hide();
	})
	$(".header_title li .span2").click(function(){
		$(".header_title li span").removeClass("span_active");
		$(this).addClass("span_active");
		$(".list1").hide();
		$(".list2").show();
		$(".list3").hide();
	})
	$(".header_title li .span3").click(function(){
		$(".header_title li span").removeClass("span_active");
		$(this).addClass("span_active");
		$(".list1").hide();
		$(".list2").hide();
		$(".list3").show();
	})
})
