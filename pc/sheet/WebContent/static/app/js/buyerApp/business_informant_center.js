/**
 * 提交工单
 */
function makeSure(){
	var descr = $("#desc").val();
	alert(descr);
	if(descr == ""){
		showMessage("描述不能为空");
		return;
	}
	return;
	$.ajax({
		type:"post",
		url:"/sheet/buyersApp/shopReport",
		dataType:"json",
		data:{
			status:1,//待处理
			reportType:"02_01",
			reportTitle:"RSH",
			sponsor_id:1,
			sponsor:"张三",
			sponsor_type:"1",
			sponsor_contact:"18888888888",
			sheetFrom:"03_04",
			priority_level:1,
			is_push:0,
			//描述
			descr:"看他不爽，坑我？",
			pics:"www.manjiwang.com",
			//业务关系
			shopInfo:862469
		},success:function(data){
			if(data.message == "SUCCESS"){
				$(".tip_mask").hide();
		    	$(".success_mask").show();
		    	setTimeout(function(){
		    		$(".success_mask").hide();
		    	},1000)
			}
		}
	});
	
}