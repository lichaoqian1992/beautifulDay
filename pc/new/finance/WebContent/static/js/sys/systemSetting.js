$(function() {
	$(".exceptionOne").val(parseInt($(".exceptionOne").val()));
	$(".exceptionTow").val(parseInt($(".exceptionTow").val()));
	$(".exceptionThere").val(parseInt($(".exceptionThere").val()));
	$(".exceptionFour").val(parseInt($(".exceptionFour").val()));
});

function WithdrawalsSave(){
	var exceptionOne=$('.exceptionOne').val();
    var exceptionTow=$('.exceptionTow').val();
    var exceptionThere=$('.exceptionThere').val();
    var exceptionFour=$('.exceptionFour').val();
    var exceptionFive=$('.exceptionFive').val();
    
    $.ajax({
        url:"/finance/sys/withRulesSettings",
        type:"GET",
        async: false,
        data:{
        	exceptionOne:exceptionOne,
        	exceptionTow:exceptionTow,
        	exceptionThere:exceptionThere,
        	exceptionFour:exceptionFour,
        	exceptionFive:exceptionFive,
        	"atr":"ajax"
        },
        success:function(data){
            if(data.stus=="SUCCESS"){
            	layer.msg('修改成功！', {
        			icon: 1,
        			time: 1000
        		});
            	setTimeout(function () { 
                    location.reload();
                }, 1000);
            }else{
            	layer.msg('服务器繁忙,请稍后再试！', {
        			icon: 1,
        			time: 1000
        		});
            }
        },error:function(){
        	layer.msg('服务器繁忙,请稍后再试！', {
    			icon: 1,
    			time: 1000
    		});
        }
    });
}

