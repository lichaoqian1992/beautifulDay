	//列表背景颜色设置
	$(function(){
			$("tbody tr:even").addClass("evenCRL");
			$("tbody tr:odd").addClass("oddCRL");
			
			$("tbody tr").hover(function(){
				$(this).addClass("trHover");
			},function(){
				$(this).removeClass("trHover");
			});
		});
		//删除列表弹窗
		$(".list_del").click(function(){
			$(".shadow").show();
			var h=$("body").height();
			var w=$("body").width();
			$("body").css("overflowY","hidden");
			$(".shadow").css({
				width:w,
				height:h,
				display:"block"
			});
			$(".del_pop").show();
		});
		//关闭弹窗
		$(".del_btn input").click(function(){
			$(".shadow").hide();
			$(".del_pop").hide();
			$("body").css("overflowY","auto");
		});
	//名称设置单独选中
		$("body").on("click",".list_work input",function(){
			var parents=$(this).parents(".list_work").find("input[type='checkbox']:checked");//找到每一行中所有的复选框
			var parent_box=$(this).parents(".list_work").find("li");//找到每一行中所有的复选框的父级
			if($(this).prop("checked")){
				$(this).prev("i").addClass("check_active");
				if(parents.length==parent_box.length){
					$(this).parents(".list_work").prev(".all_check").find("input").prop("checked",true);
					$(this).parents(".list_work").prev(".all_check").find("i").addClass("check_active");
				}else{
					return;	
				};
			}else{
				$(this).prev("i").removeClass("check_active");
				$(this).parents(".list_work").prev(".all_check").find("input").prop("checked",false);
				$(this).parents(".list_work").prev(".all_check").find("i").removeClass("check_active");
			};
		});
		//名称设置全选
		$("body").on("click",".all_check input",function(){
			if($(this).prop("checked")){
				$(this).prev("i").addClass("check_active");
				$(this).parent().next(".list_work").find("input").prop("checked",true);
				$(this).parent().next(".list_work").find("i").addClass("check_active");
			}else{
				$(this).prev("i").removeClass("check_active");
				$(this).parent().next(".list_work").find("input").prop("checked",false);
				$(this).parent().next(".list_work").find("i").removeClass("check_active");
			};	
		});
		//控制角色名称的字数
		$(".role_text input").blur(function(){
			if($(this).val().length>=15){
				alert("角色名称不能超过15个字")
				$(this).val("");
			}else{
				return;
			};
		});
		
		//系统日志下拉框
		$(".sys_select,.select_text").click(function(){
			if($(".select").css('display')=="block"){
				$(".sys_select").removeClass("select_active");
				$(".select").hide();
			}else{
				$(".sys_select").addClass("select_active");
				$(".select").show();
			};
			return false;
		});
		$(".select li").click(function(){
			$(".select_text").text($(this).text());
			$(this).parent().hide();;
			$(".sys_select").removeClass("select_active");
		});
		$("body").click(function(){
			if($(".select").css('display')=="block"){
				$(".sys_select").removeClass("select_active");
				$(".select").hide();
			}else{
				return;
			};
		});
	
		//新增用户中角色名称下拉选择
		$(".select_p").click(function(){
			if($(".user_select_ul").css('display')=="block"){
				$(this).parent().removeClass("border_blue");
				$(".user_select_ul").hide();
			}else{
				$(this).parent().addClass("border_blue");
				$(".user_select_ul").show();
			};
			return false;
		});
		$(".user_select_ul li").click(function(){
			$(".select_p").text($(this).text());
			$(this).parent().hide();
			$(".input").removeClass("border_blue");
		});
		$("body").click(function(){
			if($(".user_select_ul").css('display')=="block"){
				$(".user_select_ul").hide();
				$(".input").removeClass("border_blue");
			}else{
				return;
			};
		});
		
		//新增用户文本框获取焦点时间
		$(".input input[type='text'],.input input[type='password']").focus(function(){
			$(this).parent().addClass("border_blue");
			return false;
		});
		$(".input input[type='text'],.input input[type='password']").click(function(){
			if($(this).val().length!=0){
				$(this).next(".error_x").show();
			}else{
				$(this).next(".error_x").hide();
			};
		})
		//情况文本输入框里的内容
		$(".error_x").click(function(){
			$(this).prev().val("");
			$(this).hide();
		});
		$(".input input[type='text'],.input input[type='password']").blur(function(){
			$(this).next(".error_x").fadeOut(120);
			$(this).parent().removeClass("border_blue");
		});
		//键盘弹起事件
		$(".input input[type='text'],.input input[type='password']").keyup(function(){
			$(this).next(".error_x").show();
			$(this).parents("li").find(".error_tips").hide();
			$(this).parents("li").find(".error_tips").find("span").text("");
		});
		
		//保存验证
		/*$(".submit").click(function(){
			var userName=$("#username").val();
			var paswd=$("#paswd").val();
			var re_paswd=$("#re_paswd").val();
			var name=$("#name").val();
			var tel=$("#tel").val();
			var reg1 = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;//6-20位字母和数字组合
			var reg3=/[\u4E00-\u9FA5]{2,4}/
			var reg4=/^1[34578]\d{9}$/
			//用户名规则判断
			if(userName==""){
				$("#username").parents("li").find(".error_tips").show();
				$("#username").parents("li").find(".error_tips").find("span").text("请输入信息");
			}else if(!reg1.test(userName)){
				$("#username").parents("li").find(".error_tips").show();
				$("#username").parents("li").find(".error_tips").find("span").text("请填写6-20位字母和数字组合的账号");
			}else{
				$("#username").parents("li").find(".error_tips").hide();
				$("#username").parents("li").find(".error_tips").find("span").text("")
			};
			//密码验证
			if(paswd==""){
				$("#paswd").parents("li").find(".error_tips").show();
				$("#paswd").parents("li").find(".error_tips").find("span").text("请输入信息");
			}
			else if(!reg1.test(paswd)){
				$("#paswd").parents("li").find(".error_tips").show();
				$("#paswd").parents("li").find(".error_tips").find("span").text("请输入6-20位字母和数字组合的密码");
			}else{
				$("#paswd").parents("li").find(".error_tips").hide();
				$("#paswd").parents("li").find(".error_tips").find("span").text("");
			};
			
			if(re_paswd==""){
				$("#re_paswd").parents("li").find(".error_tips").show();
				$("#re_paswd").parents("li").find(".error_tips").find("span").text("请输入信息");
			}
			else if(paswd!=re_paswd){
				$("#re_paswd").parents("li").find(".error_tips").show();
				$("#re_paswd").parents("li").find(".error_tips").find("span").text("两次输入的密码不一致");
			}else if(!reg1.test(re_paswd)){
				$("#re_paswd").parents("li").find(".error_tips").show();
				$("#re_paswd").parents("li").find(".error_tips").find("span").text("请输入6-20位字母和数字组合的密码");
			}else{
				$("#re_paswd").parents("li").find(".error_tips").hide();
				$("#re_paswd").parents("li").find(".error_tips").find("span").text("");
			};
			//真实姓名验证
			if(name==""){
				$("#name").parents("li").find(".error_tips").show();
				$("#name").parents("li").find(".error_tips").find("span").text("请输入信息");
			}else if(!reg3.test(name)){
				$("#name").parents("li").find(".error_tips").show();
				$("#name").parents("li").find(".error_tips").find("span").text("请输入6个字符以内的中文名字");
			}else{
				$("#name").parents("li").find(".error_tips").hide();
				$("#name").parents("li").find(".error_tips").find("span").text("");
			};
			
			//手机号码验证
			if(tel==""){
				$("#tel").parents("li").find(".error_tips").show();
				$("#tel").parents("li").find(".error_tips").find("span").text("请输入信息");
			}else if(!reg4.test(tel)){
				$("#tel").parents("li").find(".error_tips").show();
				$("#tel").parents("li").find(".error_tips").find("span").text("请输入正确的手机号码");
			}else{
				$("#tel").parents("li").find(".error_tips").hide();
				$("#tel").parents("li").find(".error_tips").find("span").text("")
			};	
			//保存按钮弹窗
			if($(".error_tips").css('display')=="none"){
				$(".shadow").show();
				var h=$("body").height();
				var w=$("body").width();
				$("body").css("overflowY","hidden");
				$(".shadow").css({
					width:w,
					height:h,
					display:"block"
				});
				$(".edit_pop").show();
				if($(".edit_pop").css('display')=="block"){
					setTimeout(function(){
						$(".shadow").hide();
						$(".edit_pop").hide();
					},2000);
				};
			}else{
				return;
			}
		});*/
		
		//编辑用户-重设密码弹窗
		$(".reset_password").click(function(){
			$(".shadow").show();
			var h=$("body").height();
			var w=$("body").width();
			$("body").css("overflowY","hidden");
			$(".shadow").css({
				width:w,
				height:h,
				display:"block"
			});
			$(".password_pop").show();
		});
		//取消按钮关闭弹窗
		$(".pop_btn_cancel").click(function(){
			$(".shadow").hide();
			$(".password_pop").hide();
			$("body").css("overflowY","auto");
		});
		//确认按钮验证密码
		$(".pop_btn_confirm").click(function(){
			var paswd=$("#paswd").val();
			var re_paswd=$("#re_paswd").val();
			var reg1 = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;//6-20位字母和数字组合
			//密码验证
			if(paswd==""){
				$("#paswd").parents("li").next(".erro").html("请输入密码");
			}else if(!reg1.test(paswd)){
				$("#paswd").parents("li").next(".erro").html("请输入6-20位字母和数字组合的密码");
			}else{
				$("#paswd").parents("li").next(".erro").html("");
			};
			//确认密码验证
			if(re_paswd==""){
				$("#re_paswd").parents("li").next(".erro").html("请输入密码");
			}else if(paswd!=re_paswd){
				$("#re_paswd").parents("li").next(".erro").html("两次输入密码不一致");
			}else if(!reg1.test(paswd)){
				$("#re_paswd").parents("li").next(".erro").html("请输入6-20位字母和数字组合的密码");
			}else{
				$("#re_paswd").parents("li").next(".erro").html("");
				$(".shadow").hide();
				$(".password_pop").hide();
				$("body").css("overflowY","auto");
			};	
		});
