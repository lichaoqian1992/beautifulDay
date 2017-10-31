/**
 * 查询详情
 */
function showDetails(obj,code){
	
	window.location.href="/sheet/buyersApp/suggestInfo?sheet_no="+obj+"&codeReport="+code;
	/*$.ajax({
		type:"get",
		url:"/sheet/buyersApp/suggestInfo",
		dataType:"json",
		data:{
			sheet_no:obj
		},success:function(data){
			if(data.message == "SUCCESS"){
				window.location.href=""
			}
		}
	});*/	
}
function showMessage(message){
	
	$("#content").html(message);
	$(".btn_tip_content3").show();
	setTimeout(function(){
		$(".btn_tip_content3").hide();
	}, 1000);
}
function cancle(){
	$("#message").hide();
}
function makeSure(){
	$("#message").hide();
}