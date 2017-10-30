
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if(r != null) return unescape(r[2]);
	return null; //返回参数值
}

function load(){
	
	var nav=getUrlParam("nav");
	$(".treeview-menu li").removeClass("active");
	
	var par=$("a[nav='"+nav+"']").parents(".treeview");
	if(par.length==1){
		par.addClass("active").siblings().removeClass("active");
	}
	$("a[nav='"+nav+"']").parent().addClass("active");
}
load();