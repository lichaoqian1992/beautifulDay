//点击首页
function firstPage(){
	if(1 == $("#nowPage").val()){
		layer.msg('已经是第一页', {
			icon: 1,
			time: 1000
		});
		return;
	}
	findByCondition(1);
}
//点击上一页
function upPage(){
	var page = $("#nowPage").val()*1-1;
	if(page<1){
		layer.msg('已经是第一页', {
			icon: 1,
			time: 1000
		});
		return;
	}
	findByCondition(page);
}
//点击下一页
function nextPage(){
	var page = $("#nowPage").val()*1+1;
	if(page>$("#lastPage").val()){
		layer.msg('已经是最后一页', {
			icon: 1,
			time: 1000
		});
		return;
	}
	findByCondition(page);
}
//点击末页
function lastPage(){
	var page = $("#lastPage").val();
	if(page == $("#nowPage").val() || page == 0){
		layer.msg('已经是最后一页', {
			icon: 1,
			time: 1000
		});
		return;
	}
	findByCondition(page);
}
//跳转到指定页
function toPage(){
	var reg = /[1-9]\d*/;
	var page = $("#toPage").val();
	if(!reg.test(page)){
		layer.msg('页码不正确', {
			icon: 1,
			time: 1000
		});
		return;
	}
	if(page>$("#lastPage").val()){
		layer.msg('没有那么多页', {
			icon: 1,
			time: 1000
		});
		return;
	}
	if(page<=0){
		layer.msg('页码不能小于1', {
			icon: 1,
			time: 1000
		});
		return;
	}
	if(page == $("#nowPage").val()){
		layer.msg('已经是第'+page+'页', {
			icon: 1,
			time: 1000
		});
		return;
	}
	findByCondition(page);
}