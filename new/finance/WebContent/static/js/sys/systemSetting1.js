$(function() {
	find();
			$(".tab-content").on("click",".set_box_itme input[type='radio']",function(){
				var par=$(this).parent();
				par.parent().siblings(".set_box_duty_name").text(par.text());
			});
			
			
			$(".tab-content").on("click",".set_box_add .href_a",function(){//添加单笔充值权限
				var html='<div class="set_box">'+
				'<div class="set_box_itme">单笔充值权限金额：<input type="text" value="0"> - <input type="text" value="0">元（含）</div>'+
				'<div class="set_box_itme">每日不超过：<input type="text" value="0">元（含）</div>'+
				'<div class="set_box_itme">审批人角色：'+
					'<select class="set_box_duty">'+
	                    '<option selected="" value="5">资金部负责人</option>'+
	                    '<option value="4">财务经理</option>'+
	                    '<option value="2">财务副总裁</option>'+
	                    '<option value="3">董事长</option>'+
                    '</select></div>'+
					                    
				'<div class="set_box_itme">审批人：<span class="set_box_duty_radio">--</span><span class="set_box_duty_name hide"></span></div>'+
				'<span class="set_box_close">删除</span>'+
				'</div>';
				$(this).parent().siblings(".set_box_list").append(html);
			});
			
			$(".tab-content").on("click",".set_box .set_box_close",function(){//添加单笔充值权限
				$(this).parents(".set_box").remove();
			});
			
			
			$(".tab-content").on("change",".set_box_duty",function(){//审批人角色改变事件
				var xx = "";
				//根据角色查询对应的人
				$.ajax({
					type:"get",
					url:"/finance/sys/findRoleAndConfig",
					dataType:"json",
					async:false,
					data:{
						id:$(this).val()
					},success:function(data){
						console.log(data);
						xx='<label class=""><input type="radio" checked="checked" name="name5">'+data[0].REALNAME+'</label>';
						
					}
				});
				$(this).parent().next().find(".set_box_duty_radio").html(xx);
			});
			
});
/**
 * 查询充值审批人的金额配置信息
 */
function find(){
	var txt = "";
	$.ajax({
		type:"get",
		url:"/finance/sys/findRoleAndConfig",
		dataType:"json",
		success:function(data){
			console.log(data);
			for(var i=0;i<data.length;i++){
				var s = "",m = "",n = "",o = "";
				if(data[i].role_id == "3"){
					 s = 'selected="selected"';
					var ss = 'name1';
					
				}else if(data[i].role_id == "2"){
					 m = 'selected="selected"';
					var ss = 'name2';
					
				}else if(data[i].role_id == "4"){
					 n = 'selected="selected"';
					var ss = 'name3';
					
				}else if(data[i].role_id == "5"){
					 o = 'selected="selected"';
					var ss = 'name4';
					
				}
				if(data[i].TOTAL_MONEY>50000){
					data[i].TOTAL_MONEY = "无限制";
				}
				if(data[i].SINGLE_MAX_MONEY>10000){
					data[i].SINGLE_MAX_MONEY = "无限制";
				}
				var xx = '<select class="set_box_duty" disabled="disabled">'+
                '<option value="3" '+s+'>董事长</option>'+
                '<option value="2" '+m+'>财务副总裁</option>'+
                '<option value="4" '+n+'>财务经理</option>'+
                '<option value="5" '+o+'>资金部负责人</option>'+ 
                '</select>';
				txt +='<div class="set_box"><div class="set_box_itme">'+
					'单笔充值权限金额：<input type="hidden" value="'+data[i].id+'" name="m10">'+
					'<input type="text" value="'+data[i].SINGLE_MIN_MONEY+'" disabled="disabled" name="m1"/>'+
					'-'+
					'<input type="text" value="'+data[i].SINGLE_MAX_MONEY+'" disabled="disabled" name="m2"/>元（含）'+
				'</div>'+
				'<div class="set_box_itme">'+
					'每日不超过：'+
					'<input type="text" value="'+data[i].TOTAL_MONEY+'" disabled="disabled" name="m3"/>元（含）'+
				'</div>'+
				'<div class="set_box_itme">'+
					'审批人角色：'+xx+
                '</div>'+
				'<div class="set_box_itme">'+
                	'审批人：'+
                	'<span class="set_box_duty_radio">'+
                    	'<label class="hide"><input type="radio" checked="checked" name="'+ss+'">'+data[i].REALNAME+'</label>'+
                     '</span><span class="set_box_duty_name">'+data[i].REALNAME+'</span>'+
                '</div></div>'
			}
			$("#content").html(txt);
		}
	});
	
}
/**
 * 保存金额配置
 */
function saveConfig(obj){
	var minMoney = [];
	var maxMoney = [];
	var totalMoney = [];
	var type = [];
	var id = [];
	//保存和修改切换
	$(obj).addClass("hide").siblings().removeClass("hide");
	var sib_box=$(obj).parent().siblings(".set_box_list");
	var sib_input=sib_box.find("input[type='text']");
	var sib_label=sib_box.find("label");
	var sib_span=sib_box.find(".set_box_duty_name");
	var sib_add=$(obj).parent().siblings(".set_box_add");
	var select=$(".set_box_duty");
	if($(obj).hasClass("btn_update")){
		sib_add.removeClass("hide");
		sib_input.prop("disabled",false);
		sib_label.removeClass("hide");
		sib_span.addClass("hide");
		select.prop("disabled",false);
	}else if($(obj).hasClass("btn_save")){
		sib_input.prop("disabled",true);
		sib_label.addClass("hide");
		sib_span.removeClass("hide");
		select.prop("disabled",true);
		$(".set_box_close").hide();
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		var a = document.getElementsByName("m1");
		var b = document.getElementsByName("m2");
		var c = document.getElementsByName("m3");
		var d = document.getElementsByClassName("set_box_duty")
		var e = document.getElementsByName("m10");
	  for(var i=0; i<a.length; i++){
		  //判断输入的是否有效
		  if(b[i].value != "无限制" || c[i].value != "无限制"){
			  if(!reg.test(a[i].value) || !reg.test(b[i].value) || !reg.test(c[i].value)){
					  layer.msg('第'+(i+1)+'行金额格式有误，请检查！', {
							icon: 1,
							time: 3000
						});
					  return;
			  }
		  }else{
			  b[i].value = 9999999;
			  c[i].value = 9999999;
		  }
		  if(parseFloat(a[i].value)>parseFloat(b[i].value)){
			  layer.msg('第'+(i+1)+'行的单笔最小金额不能大于单笔最大金额，请检查！', {
					icon: 1,
					time: 3000
				});
			  return;
		  }
		  if(parseFloat(b[i].value)>parseFloat(c[i].value)){
			  layer.msg('第'+(i+1)+'行的单笔最大金额不能大于总金额，请检查！', {
					icon: 1,
					time: 3000
				});
			  return;
		  }
		  for(var j=i+1;j<a.length;j++){
			  if(parseFloat(a[i].value)>parseFloat(a[j].value) && parseFloat(a[i].value)<parseFloat(b[j].value)){
				  layer.msg('第'+j+'行和'+(j+1)+'行的金额范围重复，请检查！', {
						icon: 1,
						time: 3000
					});
				  return;
			  }
			  if(d[i].value == d[j].value){
				  layer.msg('第'+(i+1)+'行和'+(j+1)+'行的审批人角色重复，请检查！', {
						icon: 1,
						time: 3000
					});
				  return;
			  }
		  }
		  minMoney[i] = a[i].value;
		  maxMoney[i] = b[i].value;
		  totalMoney[i] = c[i].value;
		  type[i] = d[i].value;
		  id[i] = e[i].value;
	  }
	  console.log(minMoney+" "+maxMoney+" "+totalMoney+" "+type+" "+id);
	 
	  
	  $.ajax({
		  type:"get",
		  url:"/finance/sys/saveOrUpdate",
		  dataType:"json",
		  data:{
			  "minMoney":minMoney,
			  "maxMoney":maxMoney,
			  "totalMoney":totalMoney,
			  "type":type,
			  "id":id
		  },traditional: true,//这里设置为true,后端直接写参数名，如果没有加，那么后端用minMoney[]
		  success:function(data){
			  console.log(data);
			  if(data.aa == "SUCCESS"){
				  layer.msg('保存成功！', {
						icon: 1,
						time: 1000
					});
				  sib_add.addClass("hide");
				  find();
			  }else{
				  layer.msg('保存失败，请联系技术人员！', {
						icon: 1,
						time: 1000
					});
			  }
		  }
	  });
	}
}