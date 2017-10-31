$(function(){
	$(".mui-bar .right_text").click(function(){
		var type = $(this).attr("type");
		if(type == "1" || type == "3"){
			$(this).attr("type","2");
			$(".list_popup").show();
		}else{
            $(this).attr("type","3");
            $(".list_popup").hide();
		}
	})
})
function resolved(sheet_id){
	
	window.location.href="/sheet/buyersApp/resolved?sheet_id="+sheet_id;
}
/**
 * 点击已解决
 * @param obj
 */
function deal(obj){
	$("#sheet_no").val(obj);
	$(".tip_mask").show();
}
function cancel(){
	$(".tip_mask").hide();
}
function makeSure(){
	var sheet_no = $("#sheet_no").val();
	$.ajax({
		type:"get",
		url:"/sheet/buyersApp/updateStatus",
		dataType:"json",
		data:{
			status:6,
			sheet_id:sheet_no
		},success:function(data){
			if(data.message == "SUCCESS"){
				
				window.location.href="/sheet/buyersApp/myInfo?codeReport=01-02-03";
				$(".tip_mask").hide();
			}
		}
			
	});
}