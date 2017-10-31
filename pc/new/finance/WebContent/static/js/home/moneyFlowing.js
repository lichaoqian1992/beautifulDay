/*总汇详情js*/
var page;
var userId;
var orderid;
var pageid;
$(function(){
	$(document).on('click','.check_file',function(){
		if(page==""){
			page=1
		}
		userId=parseInt($(this).parent().parent().find(".userId").val());
		ConfluenceChild(page,userId);
	});
	
	$(document).on('click','.btn-primaryChild',function(){
		ConfluenceChild(1,userId);
	});
	optimization();
});

function flowDetails(page,userId,type){
	$.ajax({
        url: '/finance/home/flowDetails',
        type: 'GET',
        dataType: "json",
        async:false,
        data:{
        	"page":page,
        	"userId":userId,
        	"orderNo":orderid,
        	"type":type
        },
        success: function (data) {
            $(".one").html(data.pagedoChild.allPageNumber);
            $(".tow").html(data.pagedoChild.pageNumber);
            $(".lastpageChild").val(data.pagedoChild.numberPages);
            //循环数据
            $(".pageChild").val(data.pagedoChild.pageNumber);
        	$('.liuShuiMingXiChild tbody').children().remove();
        	$(data.refoundListChild.list).each(function(){
	             $(".liuShuiMingXiChild tbody").append("<tr><td>" + this.sumId + "</td>" +
	                 "<td>" + this.add_time + "</td>" +
	                 "<td>" + this.old_value + "</td>" +
	                 "<td>" + this.value + "</td>" +
	                 "<td>" + this.new_value + "</td>" +
	                 "<td>" + this.type + "</td>" +
	                 "<td>" + this.order_no + "</td>" +
	                 "<td>" + this.remark + "</td></tr>"
	             );
            });
        }
    });
	optimization();
}

function ConfluenceChild(page,userId){
	$(".input-group").each(function(){
		orderid=$(this).find("input").val();
	});
	$(".nav-tabs li").each(function(){
		if($(this).hasClass('active')){
			if($(this).text()=="用户账单余额"){
				flowDetails(page,userId,'Buyer');
			}else if($(this).text()=="商家账户余额"){
				flowDetails(page,userId,'shop');
			}
		}
	});
	
}

/**
 * 导出excel
 */
function toExcelChild(){
	$(".nav-tabs li").each(function(){
		$(".input-group").each(function(){
			orderid=$(this).find("input").val();
		});
		if($(this).hasClass('active')){
			if($(this).text()=="商家账户余额"){
				window.location.href = "/finance/home/toExcel?userId="+userId+"&orderNo="+orderid+"&dataType=shopInfo";
			}else if($(this).text()=="用户账单余额"){
				window.location.href = "/finance/home/toExcel?userId="+userId+"&orderNo="+orderid+"&dataType=Buyer";
			}
		}
	});
}

/*********************分页*************************/
/*下一页*/
function previousPageChild(){
	if((parseInt($(".pageChild").val())+1)>parseInt($(".lastpageChild").val())){
		layer.msg('这已经是最后一页了！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	ConfluenceChild(parseInt($(".pageChild").val())+1,userId);
}

/*上一页*/
function nextPageChild(){
	if((parseInt($(".pageChild").val())-1)<1){
		layer.msg('这已经是第一页了！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	ConfluenceChild(parseInt($(".pageChild").val())-1,userId);
}

/*首页*/
function homePageChild(){
	if((parseInt($(".pageChild").val()))==1){
		layer.msg('这已经是第一页了！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	ConfluenceChild(1,userId);
}

/*尾页*/
function ShadoweChild(){
	if((parseInt($(".pageChild").val()))==parseInt($(".lastpageChild").val())){
		layer.msg('这已经是最后一页了！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	ConfluenceChild(parseInt($(".lastpageChild").val()),userId);
}

/*GO*/
function goPageChild(){
	$(".pull-leftChild").each(function(){
		pageid=$(this).find("input").val();
	});
	var page=1;
	if((parseInt(pageid))==parseInt($(".pageChild").val())){
		layer.msg('正在当前页！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	
	if((parseInt(pageid))>parseInt($(".lastpageChild").val())){
		$(".page_numberChild").val($(".lastpageChild").val());
		page=parseInt($(".lastpageChild").val());
	}
	
	if((parseInt(pageid))<1){
		$(".page_numberChild").val(1);
		page=1;
	}
	$(".pull-leftChild").each(function(){
		pageid=$(this).find("input").val();
	});
	ConfluenceChild(parseInt(pageid),userId);
}