$(function(){
	//查询拥有权限的角色
	findByCondition();
	
	//加载将要修改的角色的权限
	//得到参数
	//得到链接中的参数值
	var url = location.search;
	console.log(url);
    if(url.indexOf("?") != -1){
    	//http://localhost:8080/finance/sys/jurisdiction?type=update&id=8&nav=nav22
        var type = url.split("?")[1].split("=")[1].split("&")[0];
        var id = url.split("?")[1].split("=")[2].split("&")[0];
        if(type == "update"){
        	//查询
        	var roleType = document.getElementById("roleType");
        	for(var i=0;i<roleType.length;i++){
        		if(roleType[i].value == id){
        			roleType[i].selected = true;
        		}
        	}
        	findJurisdictionByRole();
        }else if(type == "add"){
        	
        }
    }
    if($("#creater").val() != "admin"){
		$(".btn-success").hide();
	}
    
    
    
});
/**
 * 查询角色信息
 */
function findByCondition(){
	var txt = "";
	var aa = "";
	var bb = "";
	$.ajax({
		type:"get",
		url:"/finance/sys/findRole",
		dataType:"json",
		success:function(data){
			if(data.list.length>0){
				for(var i=0;i<data.list.length;i++){
					if(data.list[i].role_name == "admin"){
						aa = data.list[i].role_alias+"(系统管理员角色不允许改名，也不允许修改其操作权限)";
						bb = "style='cursor:not-allowed'";
					}else{
						aa = data.list[i].role_alias;
						bb = "";
					}
					var cc = 'delRole("'+data.list[i].id+'")';
					var dd = 'updateJurisdiction("'+data.list[i].id+'")';
					txt += "<tr><td>"
						+data.list[i].id+
							"</td><td style='display:none;'>"
						+data.list[i].role_name+
							"</td><td>"
						+aa+
							"</td><td><a class='href_a' onclick='"+dd+"'"+bb+">修改</a><a class='href_a delete_jurisdiction' "+bb+" onclick='"+cc+"'>删除</a></td></tr>"
				}
			}else{
				txt = "<tr><td colspan='3'>暂无数据</td></tr>";
			}
			$("#firstPage").val(1);
			$("#nowPage").val(data.pageNumber);
			$("#lastPage").val(data.totalPage);
			$("#pageInfo").html("共"+data.totalRow+"条数据，当前显示第 "+data.pageNumber+" 页  ，到");
			$("#content").html(txt);
		}
		
	});
	
}
/**
 * 根据角色查询角色的权限页面
 */
function findJurisdictionByRole(){
	
	var roleId = $("#roleType").val();
	$.ajax({
		type:"get",
		url:"/finance/sys/findJurisdictionByRole",
		dataType:"json",
		data:{
			roleId:roleId
		},
		success:function(data){
			console.log(data);
			var name = document.getElementsByClassName('jurisdiction');
			for(var j=0;j<name.length;j++){
				whichObj=name[j];
				whichObj.checked = false;
				if(data.length>0){
					for(var i=0;i<data.length;i++){
						if(data[i].id == whichObj.value){
							whichObj.checked = true;
						}
					}
				}else{
					//暂无权限
					whichObj.checked = false;
				}
			}
		}
		
	});
	
}
/**
 * 保存权限
 */
function saveJurisdiction(){
	var url = "";
	var roleId = $("#roleType").val();
	//1.得到所有权限页面
	var name = document.getElementsByClassName('jurisdiction');
	for(var i=0;i<name.length;i++){
		if(name[i].checked){
			url += name[i].value+";";
		}
	}
	$.ajax({
		type:"get",
		url:"/finance/sys/saveJurisdiction",
		dataType:"json",
		data:{
			roleId:roleId,
			url:url
		},
		success:function(data){
			if(data.aa == "SUCCESS"){
				window.location.href="/finance/sys/jurisdictionAdd?nav=nav22";
			}
		}
	});
}
/**
 * 修改权限
 */
function updateJurisdiction(obj){
	if($("#creater").val() != "admin"){
		layer.open({
			type: 1,
			title:"修改提示",
			skin: 'layui-layer-rim', //加上边框
			area: ['300px', '150px'], //宽高
			content: '<div style="line-height:90px;text-align: center">您不是系统管理员，没有操作权限！</div>'
		});
		return;
	}else{
		if(obj == "1"){
			layer.open({
				type: 1,
				title:"修改提示",
				skin: 'layui-layer-rim', //加上边框
				area: ['300px', '150px'], //宽高
				content: '<div style="line-height:90px;text-align: center">系统管理员权限不允许修改，也不允许删除！</div>'
			});
			return;
		}else{
			window.location.href="/finance/sys/jurisdiction?type=update&id="+obj+"&nav=nav22";
		}
	}
}
/**
 * 删除角色
 * @param obj
 */
function delRole(obj){
	if($("#creater").val() != "admin"){
		layer.open({
			type: 1,
			title:"修改提示",
			skin: 'layui-layer-rim', //加上边框
			area: ['300px', '150px'], //宽高
			content: '<div style="line-height:90px;text-align: center">您不是系统管理员，没有操作权限！</div>'
		});
		return;
	}else{
		if(obj == "1"){
			layer.open({
				type: 1,
				title:"删除提示",
				skin: 'layui-layer-rim', //加上边框
				area: ['300px', '150px'], //宽高
				content: '<div style="line-height:90px;text-align: center">系统管理员权限不允许修改，也不允许删除！</div>'
			});
			return;
		}
		$.ajax({
			type:"get",
			url:"/finance/sys/delRoleById",
			dataType:"json",
			data:{
				roleId:obj
			},success:function(data){
				if(data.aa == "SUCCESS"){
					layer.msg('删除成功！', {
						icon: 1,
						time: 1000
					});
					findByCondition();
				}else{
					layer.msg('角色已经被使用，请先解除！', {
						icon: 1,
						time: 1000
					});
				}
			}	
		});	
	}
}

function selectRec(obj){
	var rec = document.getElementsByName('rec');;
	for(var i=0;i<rec.length;i++){
		if(obj.checked){
			rec[i].checked = true;
		}else{
			rec[i].checked = false;
		}
	}
}

function selectWit(obj){
	var rec = document.getElementsByName('wit');;
	for(var i=0;i<rec.length;i++){
		if(obj.checked){
			rec[i].checked = true;
		}else{
			rec[i].checked = false;
		}
	}
}
function selectOrd(obj){
	var rec = document.getElementsByName('ord');;
	for(var i=0;i<rec.length;i++){
		if(obj.checked){
			rec[i].checked = true;
		}else{
			rec[i].checked = false;
		}
	}
}

function selectSys(obj){
	var rec = document.getElementsByName('sys');;
	for(var i=0;i<rec.length;i++){
		if(obj.checked){
			rec[i].checked = true;
		}else{
			rec[i].checked = false;
		}
	}
}
/*function sys(){
	var j = 0;
	var rec = document.getElementsByName('sys');
	var rec1 = document.getElementsByName('sys1');
	rec1.checked=false;
	for(var i=0;i<rec.length;i++){
		if(rec[i].checked){
			j++;
		}
	}
	if(j == rec.length){
		rec1.checked=true;
	}else{
		rec1.checked=false;
	}
}*/