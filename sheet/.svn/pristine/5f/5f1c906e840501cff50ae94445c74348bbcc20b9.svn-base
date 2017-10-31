function pageDate(data) {
    $(".kf_page_text i:eq(0)").text(data.pageSize);
    $(".kf_page_text i:eq(1)").text(data.totalPage);
    $("#pageNumber").val(data.pageNumber);
    $("#lastPageNumber").val(data.totalPage);
    $(".kf_page_size span").children().remove();
    for(var i=0;i<data.totalPage;i++){
        if((i+1)==data.pageNumber){
            $(".kf_page_size span").append("<li onclick='DateTraversal("+(i+1)+")' class='active'><a>"+(i+1)+"</a></li>");
        }else{
            $(".kf_page_size span").append("<li onclick='DateTraversal("+(i+1)+")'><a>"+(i+1)+"</a></li>");
        }
    }
}

function prevDate(){
    if((parseInt($("#pageNumber").val())-1)<1){
    	$(".kf_mode_tips").show().find(".kf_mode_tips_error").text("这已经是第一页了!");
    	interval = setInterval(function() {
			$(".kf_mode_content, .kf_mode_shield").hide();
			clearInterval(interval);
		}, 1000);
        return;
    }
    DateTraversal(parseInt($("#pageNumber").val())-1);
}

function nextDate(){
    if((parseInt($("#pageNumber").val())+1)>parseInt($("#lastPageNumber").val())){
    	$(".kf_mode_tips").show().find(".kf_mode_tips_error").text("这已经是最后一页了!");
    	interval = setInterval(function() {
			$(".kf_mode_content, .kf_mode_shield").hide();
			clearInterval(interval);
		}, 1000);
        return;
    }
    DateTraversal(parseInt($("#pageNumber").val())+1);
}
