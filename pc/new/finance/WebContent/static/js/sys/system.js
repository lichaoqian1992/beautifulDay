$(function() {
	optimization();
});

function submitRecord(page){
	$.ajax({
        url: '/finance/sys/submitRecord',
        type: 'GET',
        dataType: "json",
        async:false,
        data:{
        	"page":page,
        	"userName":$("#userName").val(),
        	"startTime":$("#startTime").val(),
        	"atr":"ajax"
        },
        success: function (data) {
        	console.log(data);
        	$('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.pagedo.allPageNumber);
            $(".pull-left q:eq(1)").html(data.pagedo.pageNumber);
            $(".lastpage").val(data.pagedo.numberPages);
            //循环数据
            $(".page").val(data.pagedo.pageNumber);
            $(data.refoundList.list).each(function(){
                $(".table-bordered tbody").append("<tr><td>" + this.sumId + "</td>" +
                    "<td>" + this.SUBMIT_TIME + "</td>" +
                    "<td>" + this.USER_NAME + "</td>" +
                    "<td>" + this.MOBILE + "</td>" +
                    "<td><p>" + this.SUBMIT_INFO + "</p></td></tr>");
            });
        }
    });
	optimization();
}

function systemLogRecord(page){
	$.ajax({
        url: '/finance/sys/systemLogRecord',
        type: 'GET',
        dataType: "json",
        async:false,
        data:{
        	"page":page,
        	"timeSelect":$("#timeselect").val(),
        	"atr":"ajax"
        },
        success: function (data) {
        	console.log(data);
        	$('.table-bordered tbody').children().remove();
            $(".pull-left q:eq(0)").html(data.pagedo.allPageNumber);
            $(".pull-left q:eq(1)").html(data.pagedo.pageNumber);
            $(".lastpage").val(data.pagedo.numberPages);
            //循环数据
            $(".page").val(data.pagedo.pageNumber);
            $(data.refoundList.list).each(function(){
                $(".table-bordered tbody").append("<tr><td>" + this.sumId + "</td>" +
                    "<td>" + this.account_name + "</td>" +
                    "<td>" + this.create_time + "</td>" +
                    "<td>" + this.log_info + "</td></tr>");
            });
        }
    });
	optimization();
}

function Confluence(page){
	if($(".xuaze").val()=="报送记录"){
		submitRecord(page);
	}else if($(".xuaze").val()=="操作日志"){
		systemLogRecord(page);
	}
}

function optimization(){
	$(".table-bordered tbody tr td").each(function(){
		if($(this).html()=="" || $(this).html()=="null"){
			$(this).html("暂无记录");
		}
	});
}
/*********************分页*************************/
/*下一页*/
function previousPage(){
	if((parseInt($(".page").val())+1)>parseInt($(".lastpage").val())){
		layer.msg('这已经是最后一页了！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	Confluence(parseInt($(".page").val())+1);
}

/*上一页*/
function nextPage(){
	if((parseInt($(".page").val())-1)<1){
		layer.msg('这已经是第一页了！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	Confluence(parseInt($(".page").val())-1);
}

/*首页*/
function homePage(){
	if((parseInt($(".page").val()))==1){
		layer.msg('这已经是第一页了！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	Confluence(1);
}

/*尾页*/
function Shadowe(){
	if((parseInt($(".page").val()))==parseInt($(".lastpage").val())){
		layer.msg('这已经是最后一页了！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	Confluence(parseInt($(".lastpage").val()));
}

/*GO*/
function goPage(){
	var page=1;
	if((parseInt($(".page_numberAll").val()))==parseInt($(".page").val())){
		layer.msg('正在当前页！', {
			icon: 1,
			time: 1000
		});
		return;
	}
	
	if((parseInt($(".page_numberAll").val()))>parseInt($(".lastpage").val())){
		$(".page_numberAll").val($(".lastpage").val());
		page=parseInt($(".lastpage").val());
	}
	
	if((parseInt($(".page_numberAll").val()))<1){
		$(".page_numberAll").val(1);
		page=1;
	}
	Confluence(parseInt($(".page_numberAll").val()));
}