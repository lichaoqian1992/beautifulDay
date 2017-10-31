$(function(){
	$(".resolved_content textarea").keyup(function(){
		var total = $(this).val();
		$(".text_total .span1").text(total.length);
	})
})
