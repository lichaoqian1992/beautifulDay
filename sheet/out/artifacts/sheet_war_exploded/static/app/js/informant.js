$(function(){
//  var hight = $(".list_fourth").height();
//  var hight2 = hight-40;
//  $(".fourth_item2 .item2_left .item2_left_line").css("height",hight2);
})
function buchong(sheet_id){
	window.location.href="/sheet/buyersApp/addInfo?sheet_id="+sheet_id;
}
function resolved(sheet_id){
	window.location.href="/sheet/buyersApp/resolved?sheet_id="+sheet_id;
}
/**
 * 点击已解决
 * @param obj
 */
function deal2(obj){
	$("#sheet_no").val(obj);
	$(".tip_mask").show();
}
function cancel2(){
	$(".tip_mask").hide();
}
function makeSure2(){
	var sheet_no = $("#sheet_no").val();
	$.ajax({
		type:"get",
		url:"/sheet/buyersApp/updateStatus",
		dataType:"json",
		data:{
			status:6,
			sheet_id:sheet_no
		},success:function(data){
			if(data.message != "ERROR"){
				var code = data.message.type_code.split("_")[0];
				window.location.href="/sheet/buyersApp/myInfo?sponsoId="+data.message.sponsor_id+"&codeReport="+code;
				$(".tip_mask").hide();
			}
		}
			
	});
}