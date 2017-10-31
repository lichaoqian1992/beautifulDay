$(function(){
		$(".list_first textarea").keyup(function(){
		var total = $(this).val();
		$(".word_total_span1").text(total.length);
	})
})
