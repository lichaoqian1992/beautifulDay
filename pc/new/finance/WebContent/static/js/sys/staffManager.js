/**
 * 员工管理
 */
var userName = "";
$(function() {
	
	//得到参数
	//得到链接中的参数值
	var url = location.search;
    if(url.indexOf("id") != -1){
        var orderNo2 = url.split("?")[1].split("=")[1];
        userName = orderNo2;
    }
    if(userName != ""){
    	updateStaff(userName);
    }
	findByCondition(1);
	if($("#creater").val() != "admin"){
		$(".btn-success").hide();
	}
	
});
/**
 * 查询员工信息
 */
function findByCondition(pageNum){
	var txt = "";
	$.ajax({
		type:"get",
		url:"/finance/sys/findStaffInfo",
		dataType:"json",
		data:{
			pageNum:1
		},success:function(data){
			console.log(data);
			if(data.list.length>0){
				if($("#creater").val() != "admin"){
					var s = "style='display:none;'";
				}
				for(var i=0;i<data.list.length;i++){
					txt += "<tr><td>"
						+(i+1)+
						"</td><td id='userName'>"
						+data.list[i].USERNAME+
						"</td><td>"
						+data.list[i].REALNAME+
						"</td><td>"
						+data.list[i].role_alias+
						"</td><td>"
						+data.list[i].MOBILE+
						"</td><td>"
						+data.list[i].EMAIL+
						"</td><td>"
						+data.list[i].CREATE_TIME+
						"</td><td><a href='/finance/sys/updateStaff?id="+data.list[i].USERNAME+"'&nav=nav22 class='href_a'>修改</a><a href='#' class='href_a delete_staff' onclick='delStaff(this)' "+s+">删除</a></td></tr>"
				}
			}else{
				txt = "<tr><td colspan='8'>暂无数据</td></tr>"
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
 * 修改员工信息
 */
function updateStaff(obj){
	var rechargeType = document.getElementById("roleType");
	if($("#creater").val() != "admin"){
		$("#roleType").attr("disabled","disabled");
	}
	$.ajax({
		type:"get",
		url:"/finance/sys/findStaff",
		dataType:"json",
		data:{
			userName:obj
		},success:function(data){
			console.log(data);
			var list = data[0];
			$("#realName").val(list.REALNAME);
			$("#userName").val(list.USERNAME);
			$("#mobile").val(list.MOBILE);
			$("#email").val(list.EMAIL);
			$("#id").val(list.ID);
			for(var i=0;i<rechargeType.length;i++){
		    	if(rechargeType[i].value == list.id){
		    		rechargeType[i].selected = true;
		    	}
		    }
		}
	});
	
}
/**
 * 保存信息
 */
function saveStaff(){
	var id = $("#id").val();
	var userName = $("#userName").val();
	var realName = $("#realName").val();
	var roleType = $("#roleType").val();
	var mobile = $("#mobile").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var s = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|177)\d{8}$/;
	//校验基本信息
	if(password != $("#mpassword").val()){
		layer.msg('前后密码不一致', {icon: 1});
		return;
	}
	
	if(password != null && password.length<6){
		layer.msg('密码长度不够', {icon: 1});
		return;
	}
	if(userName == "" || realName == "" || roleType == "" || mobile == "" || email == "" || password == ""){
		layer.msg('请填写全部信息', {icon: 1});
		return;
	}
	if(!s.test(mobile)){
		layer.msg('电话号码格式不正确', {icon: 1});
		return;
	}
	$.ajax({
		type:"get",
		url:"/finance/sys/saveAndUpdateStaff",
		dataType:"json",
		data:{
			id:id,
			userName:userName,
			realName:realName,
			roleType:roleType,
			mobile:mobile,
			email:email,
			password:password
		},success:function(data){
			if(data.aa == "SUCCESS"){
				window.location.href="/finance/sys/staffManager?nav=nav22";
			}else if(data.aa == "EXIT"){
				layer.msg('角色已存在', {
        			icon: 1,
        			time: 1000
        		});
			}else if(data.aa == "USUCCESS"){
				layer.msg('修改成功', {
        			icon: 1,
        			time: 1000
        		});
				setTimeout(function () { 
					window.location.href="/finance/sys/staffManager?nav=nav22"
                }, 1000);
			}else if(data.aa == "UFAIL"){
				layer.msg('修改失败', {
        			icon: 1,
        			time: 1000
        		});
			}
			
		}
	});
}
/**
 * 删除员工
 */
function delStaff(obj){
	layer.confirm('删除后账号不可恢复, 请谨慎操作! 是否确认删除', {
	  	btn: ['确定','取消'], //按钮
	  	title:"删除提示"
	}, function(){
		var name = $(obj).parent().parent().find("#userName").text();
		$.ajax({
			type:"get",
			url:"/finance/sys/delStaff",
			dataType:"json",
			data:{
				userName:name
			},success:function(data){
				if(data.aa == "SUCCESS"){
					layer.msg('删除成功', {icon: 1});
					findByCondition(1);
				}else{
					layer.msg('删除失败', {icon: 1});
				}
			}
		});
	  	
	});
}
/**
 * 重置密码
 */
function resetPass(){
	
	var txt = '<div class="layer_box" style="display:block;"><div class="update_password_box"><div class="rechargePay_list"><div class="group_box">'+
	'<label class="group_label">旧密码：</label><div class="group_content"><input class="form-control" type="text" value="" id="oldPas" placeholder="请输入正确密码" />'+
'</div></div><div class="group_box"><label class="group_label">新密码：</label><div class="group_content">'+
		'<input class="form-control" type="text" value="" id="newPas" placeholder="6-20位英文字母和数字的组合" />'+
	'</div></div><div class="group_box"><label class="group_label">确认密码：</label><div class="group_content">'+
		'<input class="form-control" type="text" value="" id="mPas" placeholder="6-20位字符" /></div></div></div></div></div>';

	layer.open({
		type: 1,
		btn: ['保存', '取消'], //按钮
		title: "重置密码",
		skin: 'layui-layer-rim', //加上边框
		area: ['500px', '300px'], //宽高
		content: txt,
		yes:function(){
			//判断信息
			if($("#newPas").val() != $("#mPas").val()){
				layer.msg('前后密码不一致', {icon: 1});
				return;
			}
			if($("#newPas").val().length<6){
				layer.msg('密码长度不够', {icon: 1});
				return;
			}
			$.ajax({
				type:"get",
				url:"/finance/sys/resetPass",
				dataType:"json",
				data:{
					userName:$("#userName").val(),
					oldPass:$("#oldPas").val(),
					newPass:$("#newPas").val()
				},success:function(data){
					if(data.aa == "SUCCESS"){
						layer.msg('重置成功,新密码'+$("#newPas").val(), {
							icon: 1,
							time:1000
						},function(){
							layer.closeAll();
						});
					}else if(data.aa == "FAIL"){
						layer.msg('操作失败，请联系技术人员', {icon: 1});
					}else{
						layer.msg('原密码错误', {icon: 1});
					}
				}
			});
			
		}
	});
	
	
}
/**
 * 取消按钮
 */
function cancel(){
	window.location.href="/finance/sys/staffManager?nav=nav22";
}