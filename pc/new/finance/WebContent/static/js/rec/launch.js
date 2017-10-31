//全局变量
var rechargeWay = "OA充值"
var withDrawls = "";
var type = "";
var xx = "";
	$(function() {
		findRechargeInfo('OA充值');	
		var a = document.getElementsByName("checkType");
	    $(a).click(function(){
	    	type = "";
	        for(var i=0;i<a.length;i++){
	            if(a[i].checked){
	                if(a[i].value == "1"){
	                	$("#id").val("");
	                	rechargeWay = "OA充值";
	                	findRechargeInfo(rechargeWay);	
	                }else if(a[i].value == "2"){
	                	$("#id").val("");
	                	rechargeWay = "自主添加";
	                	findRechargeInfo(rechargeWay);	
	                }
	            }
	        }
	    });	
	    var b = document.getElementsByName("withDrawls2");
	    $(b).click(function(){
	        withDrawls = "";
	        for(var i=0;i<b.length;i++){
	            if(b[i].checked){
	               withDrawls = b[i].value;
	               $("#withDrawls").val(b[i].value);
	            }
	        }
	    });
	    var c = document.getElementsByName("withDrawls");
	    $(c).click(function(){
	        withDrawls = "";
	        for(var i=0;i<c.length;i++){
	            if(c[i].checked){
	               withDrawls = c[i].value;
	               $("#withDrawls").val(c[i].value);
	            }
	        }
	    });
	    $(".rechargePay_type_tab input[type='radio']").click(function() {
	    	//$("#id").val("");
			var id = $(this).attr("isid");
			if(id == "#pay_type_download") {
				$(".rechargePay_list .download_table_tips").removeClass("hide");
			} else {
				$(".rechargePay_list .download_table_tips").addClass("hide");
			}
			$(id).addClass("active").siblings().removeClass("active");
		});
	});
	function getThis(obj){
		$("#withDrawls").val($(obj).val());
	}
/**
*根据账号查询姓名
*/
function findUserInfo(type){
    if(type == "临时充值"){
        var a = $("#accountName").val();
    }else if(type == "自主添加"){
        var a = $("#accountName1").val();
    }else{
    	var a = $("#accountName2").val();
    }
    $.ajax({
        type:"get",
        url:"/finance/rec/findUserInfo",
        dataType:"json",
        data:{
        	accountName:a
        },success:function(data){
        	console.log(data.message);
        	if(data.message.length > 0){
        		/*<option selected="selected" value="0">员工工资</option>
				<option value="1">工程款</option>
				<option value="2">行政款</option>
				<option value="3">营销策划</option>*/
        		var txt = "",t = "";
        		var role_type = data.message.split(";")[0].split(",");
        		var role_value = data.message.split(";")[1].split(",");
        		var role_name = "";
        		for(var i=0;i<role_type.length;i++){
        			if(role_type[i] == "Shop"){
        				role_name = "商家";
        			}else if(role_type[i] == "Buyer"){
        				role_name = "用户";
        			}else if(role_type[i] == "Agent"){
        				role_name = "代理";
        			}else if(role_type[i] == "Admin"){
        				role_name = "管理员";
        			}
        			t = role_type[i]+","+role_value[i]+","+data.message.split(";")[3];
        			txt += '<option value="'+t+'">'+role_name+'</option>';
        		}
        		if(type == "临时充值"){
        			$("#role3").html(txt);
                    $("#userName").val(data.message.split(";")[2].split(",")[0]);
                    $("#idCard").val(data.message.split(";")[2].split(",")[1]);
                }else if(type == "自主添加"){
                	$("#role2").html(txt);
                	$("#userName2").val(data.message.split(";")[2].split(",")[0]);
                    $("#idCard1").val(data.message.split(";")[2].split(",")[1]);
                }else if(type == "OA充值"){
                	$("#role1").html(txt);
                	$("#userName3").val(data.message.split(";")[2].split(",")[0]);
                    $("#idCard2").val(data.message.split(";")[2].split(",")[1]);
                }
        	}else{
        		layer.msg('账号不存在，请检查', {
    				icon: 2,
    				time: 2000
    			});
        		$("#accountName1").val("");
        		$("#accountName2").val("");
        	}
        }
    });
}
//根据金额查询审批人
function findRoleByMoney(){
	var money = $("#checkPeople").val();
	var mytable3=document.getElementById("onceRecharge").getElementsByTagName("tr");
	var name = $(mytable3).eq(0).find("td").eq(8).html();
	$.ajax({
		type:"get",
		url:"/finance/rec/findRoleByMoney",
		dataType:"json",
		data:{
			money:money
		},success:function(data){
			console.log(data);
			if(mytable3.length>1){
				if(name != data[0].role_alias){
					$("#checkPeople").val("");
					$("#roleName").val("");
					$("#realName").val("");
					$("#userId").val("");
					$("#min").val("");
					$("#max").val("");
					layer.msg('审批人应该是同一个，请重新选择！', {
	    				icon: 2,
	    				time: 1000
	    			});
				}else{
					$("#roleName").val(data[0].role_alias);
					$("#realName").val(data[0].REALNAME);
					$("#userId").val(data[0].id);
					$("#min").val(data[0].SINGLE_MIN_MONEY);
					$("#max").val(data[0].SINGLE_MAX_MONEY);
				}
			}else{
				$("#roleName").val(data[0].role_alias);
				$("#realName").val(data[0].REALNAME);
				$("#userId").val(data[0].id);
				$("#min").val(data[0].SINGLE_MIN_MONEY);
				$("#max").val(data[0].SINGLE_MAX_MONEY);
			}
		}
	});	
}
/**
 * 保存临时充值的信息
 * @param type
 */
function addRechargeOnce(rechargeWay){
	//1.校验信息是否填写完整
	var status = "";
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(rechargeWay == "临时充值"){
		var rechargeType =$("#rechargeType").val();//充值类型
	    var userName =$("#userName").val();//姓名
	    var idCard =$("#idCard").val();//身份证
	    var accountName =$("#accountName").val();//账号
	    var accountMoney =$("#money").val();//金额
	    var user_id =$("#role3").val();//账号id
	    var remark = $("#backRemark3").val();
	    var oaNo = "--";
	    if($("#checkPeople").val().length <= 0){
	    	layer.msg('请选择审批金额', {
				icon: 2,
				time: 1000
			});
	        return false;
	    }
	    //1000、5000、10000、20000
	    if(accountMoney*1 <= $("#min").val()*1 || accountMoney*1 > $("#max").val()*1){
	    	layer.msg('充值金额错误了', {
				icon: 2,
				time: 1000
			});
	        return;
	    }
	    if( $('input:radio[name="withDrawls"]:checked').length<=0){
	    	layer.msg('请选择可否提现', {
				icon: 2,
				time: 1000
			});
	        return;
	    }
	    if($("#backRemark3").val() == ""){
    		layer.msg('请填写充值备注', {
				icon: 2,
				time: 1000
			});
    		return;
    	}
	}else if(rechargeWay == "自主添加"){
		var rechargeType =$("#rechargeType1").val();//充值类型
	    var userName =$("#userName2").val();//姓名
	    var idCard =$("#idCard1").val();//身份证
	    var accountName =$("#accountName1").val();//账号
	    var accountMoney =$("#rechargeMoney1").val();//金额
	    var oaNo = $("#oaNo1").val();
	    var user_id =$("#role2").val();//账号id
	    if(rechargeType != 5){
	    	if( $('input:radio[name="withDrawls2"]:checked').length<=0){
		    	layer.msg('请选择可否提现', {
					icon: 2,
					time: 1000
				});
		        return;
		    }
	    	if($("#backRemark").val() == ""){
	    		layer.msg('请填写充值备注', {
					icon: 2,
					time: 1000
				});
	    		return;
	    	}
	    var remark = $("#backRemark").val();
	    }else{
	    	if($("#backReason").val() == ""){
	    		layer.msg('请填写扣减原因', {
					icon: 2,
					time: 1000
				});
	    		return;
	    	}
	    	var remark = $("#backReason").val();
	    }
	}else{
		var rechargeType =$("#rechargeType2").val();//充值类型
	    var userName =$("#userName3").val();//姓名
	    var idCard =$("#idCard2").val();//身份证
	    var accountName =$("#accountName2").val();//账号
	    var accountMoney =$("#rechargeMoney2").val();//金额
	    var oaNo = $("#oaNo2").val();
	    var user_id =$("#role1").val();//账号id
	    var remark = $("#oaRemark").val();
	}
	
    var id = $("#id").val();
    if(rechargeType.length == 0){
    	layer.msg('请选择充值类型', {
			icon: 2,
			time: 1000
		});
        return false;
    }
    if(accountName.length == 0 || userName.length == 0 || idCard.length == 0){
        
        layer.msg('请填写充值账号信息', {
			icon: 2,
			time: 1000
		});
        return;
    }
    if(accountMoney.length == 0){
    	layer.msg('请选择充值金额', {
			icon: 2,
			time: 1000
		});
        return;
    }
    if(rechargeType != 5){
   	 if(!reg.test(accountMoney)){
   	    	layer.msg('充值金额格式错误', {
   				icon: 2,
   				time: 1000
   			});
   	        return;
   	    }
   }else{
	   if(accountMoney == 0){
		   layer.msg('充值金额不能为0', {
  				icon: 2,
  				time: 1000
  			});
  	        return;
	   }
   }
	//2.提交充值
    if(type == ""){
    	if($("#add").html() == "保存" || $("#temporary1").html() == "保存" || xx == "保存"){
    		
    		type = "修改";
        	status = "0";
        }else{
        	type = "添加";
        }
    }else if(type == "修改"){
    	status = "0";
    }
    //先查询审批人信息、根据充值金额
	$.ajax({
		type:"get",
		url:"/finance/rec/saveRechargeInfo",
		dataType:"json",
		data:{
			PERSON_RELEASE:$("#creater").val(),
			RECHARGE_TYPE:rechargeType,
			USER_KEY:accountName,
			USER_NAME:userName,
            IDCARD:idCard,
            CHECK_STATUS:status,
            RECHARGE_MONEY:accountMoney,
            withDrawls:$("#withDrawls").val(),
            RECHARGE_WAY:rechargeWay,
            OANO:oaNo,
            addOrUpdate:type,
            REMARK:remark,
            ID:id,
            ROLE_TYPE:user_id
		},success:function(data){
			if(data.aa == "SUCCESS"){
				if(type == "修改"){
					layer.msg('修改成功', {
						icon: 1
					});
				}else{
					layer.msg('添加成功', {
						icon: 1
					});
				}
				type = "";
				findRechargeInfo(rechargeWay);	
			}
			$("#add").html("添加"); 
			$("#temporary1").html("添加");
			$("#zhlx3").show(); 
			$("#zhlx2").show(); 
			$("#zhlx1").show(); 
			//清空信息
			$("#accountName").val("");
		    $("#userName").val("");
		    $("#idCard").val("");
		    $("#money").val("");
		    $("#accountName1").val("");
		    $("#userName2").val("");
		    $("#idCard1").val("");
		    $("#rechargeMoney1").val("");
		    $("#backReason").val("");
		}
	});
}
//查询临时充值的充值信息
function findRechargeInfo(way){
	$("#checkPeople").val("");
	$("#rechargeType").val("");
	$("#accountName").val("");
	$("#userName").val("");
	$("#idCard").val("");
	$("#money").val("");
	$("#realName").val("");
	$("#roleName").val("");
	if(way == "OA充值"){
		//清除信息
		$("#oaNo1").val("");
		$("#rechargeType1").val("");
		$("#accountName1").val("");
		$("#userName2").val("");
		$("#idCard1").val("");
		$("#rechargeMoney1").val("");
		
		var a = document.getElementsByName("checkType");
		for(var i=0;i<a.length;i++){
	        if(a[i].checked){
	            if(a[i].value == "1"){
	            	$("#id").val("");
	            	way = "OA充值";
	            	
	            }else if(a[i].value == "2"){
	            	$("#id").val("");
	            	way = "自主添加";
	            		
	            }
	        }
	    }
	}
	$("#id").val("");
	var txt = "";
	var j = 0;
	var trColor = "";
	var countNum = 0;
	var acountMoney = 0;
	$.ajax({
		type:"get",
		url:"/finance/rec/findRechargeInfo",
		dataType:"json",
		data:{
			rechargeType:way
		},success:function(data){
			console.log(data);
			if(data.length>0){
				if(way == "临时充值"){
					var s = "style='display:none;'";
				}else{
					var x = "style='display:none;'";
				}
				for(var i=0;i<data.length;i++){
					//判断导入的数据是否有效
					if(data[i].OANO != "" && data[i].RECHARGE_TYPE != "" && data[i].USER_KEY != "" && data[i].RECHARGE_MONEY != "" && data[i].withDrawls != "" && data[i].ROLE_TYPE != ""){
                        j++;
                        trColor = "black";
                        acountMoney += data[i].RECHARGE_MONEY;
                    }else{
                        trColor = "red";
                    }
					if(data[i].ROLE_TYPE == "Shop"){
						data[i].ROLE_TYPE = "商家";
					}else if(data[i].ROLE_TYPE == "Buyer"){
						data[i].ROLE_TYPE = "用户";
					}else if(data[i].ROLE_TYPE == "Agent"){
						data[i].ROLE_TYPE = "代理";
					}else if(data[i].ROLE_TYPE == "Admin"){
						data[i].ROLE_TYPE = "管理员";
					}
					countNum += data[i].RECHARGE_MONEY*1;
					var aa = 'delRechargeInfoByOrderNo(this,"'+way+'")';
					var bb = 'updateInfo(this,"'+way+'")';
					txt +="<tr style='color: "+trColor+"'><td>"
						+(i+1)+
						"</td><td style='display:none;' id='orderNo'>"
						+data[i].ID+
						"</td><td id='oaNo'"+s+">"
						+data[i].OANO+
						"</td><td id='rechargeType'>"
						+data[i].RECHARGE_TYPE+
						"</td><td id='userName'>"
						+data[i].USER_NAME+
						"</td><td id='idCard'>"
						+data[i].IDCARD+
						"</td><td id='accountName'>"
						+data[i].USER_KEY+
						"</td><td id='rmoney'>"
						+data[i].RECHARGE_MONEY+
						"</td><td id='roleName'"+x+">"
						+data[i].PERSON_APPROVING+
						"</td><td id='realName'"+x+">"
						+data[i].PERSON_APPROVING_REALNAME+
						"</td><td id='withdrawls2'>"
						+data[i].withDrawls+
						"</td><td id='myType'>"
						+data[i].ROLE_TYPE+
						"</td><td id='myRemark'>"
						+data[i].REMARK+
						"</td><td><a href='#' class='href_a' onclick='"+bb+"'>修改</a><a href='#' class='href_a temporaryPay_delete' onclick='"+aa+"'>删除</a></td></tr>";
				}
				//成功导入3条充值数据，有效数据2条，无效数据1条！
				$("#uploadMessage").html("成功导入"+data.length+"条充值数据，有效数据"+j+"条，无效数据"+(data.length-j)+"条！");
				txt += "<tr><td colspan='12'>充值总金额："+countNum+"元</td></tr>";
			}else{
				txt = "<tr><td colspan='20' class='table_no_data'>还未录入数据！</td></tr>";
			}
			$("#onceRecharge").html(txt);
			$("#oaRecharge").html(txt);
		}
	});
}
/**
 * 根据充值单号删除充值单信息
 */
function delRechargeInfoByOrderNo(obj,way){
	var id = $(obj).parent().parent().find("#orderNo").text();
	layer.confirm('确定要删除该条数据！', {
		btn: ['确定', '取消'] //按钮
	}, function() {
		$.ajax({
			type:"get",
			url:"/finance/rec/delRechargeInfoByOrderNo",
			dataType:"json",
			data:{
				id:id
			},success:function(data){
				console.log(data);
				layer.msg(data.aa, {
					icon: 1
				});
				//调用查询充值信息的方法
			
				findRechargeInfo(way);
			}
		});
	});	
}
/**
 * 修改充值信息
 * @param obj
 */
function updateInfo(obj,way){
	var shenpi = "";
	var a = $(obj).parent().parent().find("#orderNo").text();
	var b = $(obj).parent().parent().find("#accountName").text();
	var c = $(obj).parent().parent().find("#userName").text();
	var d = $(obj).parent().parent().find("#idCard").text();
	var e = $(obj).parent().parent().find("#rmoney").text();
	var f = $(obj).parent().parent().find("#withdrawls2").text();
	var g = $(obj).parent().parent().find("#roleName").text();
	var h = $(obj).parent().parent().find("#realName").text();
	var im = $(obj).parent().parent().find("#rechargeType").text();
	var oaNo = $(obj).parent().parent().find("#oaNo").text();
	var myType = $(obj).parent().parent().find("#myType").text();
	var myRemark = $(obj).parent().parent().find("#myRemark").text();
	var x = 0;
	if(way == "临时充值"){
		$("#accountName").val(b);
	    $("#userName").val(c);
	    $("#idCard").val(d);
	    $("#money").val(e);
	    $("#roleName").val(g);
	    $("#realName").val(h);
	    $("#id").val(a);
	    $("#zhlx3").hide();
	    $("#backRemark3").val(myRemark);
	    if(e>0 && e<=1000){
	    	x = 1000;
	    	$("#min").val(0);
	    	$("#max").val(1000);
	    }else if(e>1000 && e<=5000){
	    	x = 5000;
	    	$("#min").val(1000);
	    	$("#max").val(5000);
	    }else if(e>5000 && e<=10000){
	    	x = 10000;
	    	$("#min").val(5000);
	    	$("#max").val(10000);
	    }else if(e>10000){
	    	x = 20000;
	    	$("#min").val(20000);
	    	$("#max").val(9999999);
	    }
	    $("#checkPeople").val(x);
	    if(f == "不可提现"){
	        $("input[name=withDrawls]:eq(1)").attr("checked",'checked');
	        $("#withDrawls").val(4);
	    }else{
	        $("input[name=withDrawls]:eq(0)").attr("checked",'checked');
	        $("#withDrawls").val(6);
	    }
	    var rechargeType = document.getElementById("rechargeType").options;
	    for(var i=0;i<rechargeType.length;i++){
	    	if(rechargeType[i].text == im){
	    		rechargeType[i].selected = true;
	    		if(im == "员工工资"){
	    			$("#rechargeType").val(0);
	    		}else if(im == "工程款"){
	    			$("#rechargeType").val(1);
	    		}else if(im == "行政款"){
	    			$("#rechargeType").val(2);
	    		}else if(im == "营销策划"){
	    			$("#rechargeType").val(3);
	    		}
	    	}
	    }
	    $("#add").html("保存");
	}else if(way == "自主添加"){
		$("#remark").hide();
		if(f == "其他"){
			$("#reason").show();
			$("#backReason").val(myRemark);
		}
		$("#accountName1").val(b);
	    $("#userName2").val(c);
	    $("#idCard1").val(d);
	    $("#rechargeMoney1").val(e);
	    $("#id").val(a);
	    $("#oaNo1").val(oaNo);
	    $("#zhlx2").hide();
	    $("#backRemark").val(myRemark);
	    var all_options = document.getElementById("checkPeople").options;
	    for (var i=0; i<all_options.length; i++){
	        if(g == "资金部负责人"){
	            shenpi = "0-1000元（含）";
	        }else if(g == "财务经理"){
	            shenpi = "1000-5000元（含）";
	        }else if(g == "财务副总裁"){
	            shenpi = "5000-10000元（含）";
	        }else if(g == "董事长"){
	            shenpi = "10000元以上";
	        }
	        if (all_options[i].text == shenpi)  // 根据option标签的ID来进行判断  测试的代码这里是两个等号
	        {
	        	all_options[i].selected = true;
	        }
	    }
	    $("#mywithdrawls").show();
    	$("#reason").hide();
	    if(f == "其他"){
	    	$("#mywithdrawls").hide();
	    	$("#reason").show();
	    }else if(f == "不可提现"){
	        $("input[name=withDrawls2]:eq(1)").attr("checked","checked");
	        $("#withDrawls").val(4);
	    }else{
	        $("input[name=withDrawls2]:eq(0)").attr("checked","checked");
	        $("#withDrawls").val(6);
	    }
	    var rechargeType = document.getElementById("rechargeType1").options;
	    for(var i=0;i<rechargeType.length;i++){
	    	if(rechargeType[i].text == im){
	    		rechargeType[i].selected = true;
	    		if(im == "员工工资"){
	    			$("#rechargeType1").val(0);
	    		}else if(im == "工程款"){
	    			$("#rechargeType1").val(1);
	    		}else if(im == "行政款"){
	    			$("#rechargeType1").val(2);
	    		}else if(im == "营销策划"){
	    			$("#rechargeType1").val(3);
	    		}else if(im == "扣减"){
	    			$("#rechargeType1").val(5);
	    		}
	    	}
	    }
	    $("#temporary1").html("保存");
	}else{
		//先赋值
		if(im == "员工工资"){
			var aa = 'selected="selected"';	
		}else if(im == "工程款"){
			var bb = 'selected="selected"';
		}else if(im == "行政款"){
			var cc = 'selected="selected"';
		}else if(im == "营销策划"){
			var dd = 'selected="selected"';
		}
		 if(f == "不可提现"){
			 var ff = 'checked="checked"';
	        $("#withDrawls").val(4);
	    }else{
	    	var gg = 'checked="checked"';
	        $("#withDrawls").val(6);
	    }
		 var mm = "'OA充值'";
		 var zz = "findUserInfo("+mm+")";
		var xx='<div class="layer_box" style="display:block;"><div class="form_box"><div class="rechargePay_list"><div class="group_box">'+
			'<label class="group_label"><i class="color_red">*</i>OA文件编号：</label>'+
			'<div class="group_content">'+
				'<input class="form-control" type="text" value="'+oaNo+'" id="oaNo2" /></div></div>'+
		'<div class="group_box">'+
			'<label class="group_label"><i class="color_red">*</i>充值类型：</label>'+
			'<div class="group_content"><select class="form-control" id="rechargeType2">'+
					'<option '+aa+' value="0">员工工资</option>'+
					'<option '+bb+' value="1">工程款</option>'+
					'<option '+cc+' value="2">行政款</option>'+
					'<option '+dd+' value="3">营销策划</option>'+
					'<option '+dd+' value="4">扣减</option>'+
				'</select>'+
			'</div>'+
		'</div>'+
		'<div class="group_box">'+
			'<label class="group_label"><i class="color_red">*</i>充值帐户：</label>'+
			'<div class="group_content">'+
				'<input class="form-control" type="text" value="'+b+'" id="accountName2" onchange="'+zz+'"/>'+
			'</div>'+
		'</div>'+
		'<div class="group_box">'+
			'<label class="group_label">姓名：</label>'+
			'<div class="group_content">'+
				'<input class="form-control" type="text" value="'+c+'" id="userName3" />'+
			'</div>'+
		'</div>'+
		'<div class="group_box">'+
		'<label class="group_label"><i class="color_red">*</i>充值金额：</label>'+
		'<div class="group_content">'+
			'<input class="form-control" type="text" value="'+e+'" id="rechargeMoney2" />'+
		'</div>'+
	'</div>'+
		'<div class="group_box">'+
			'<label class="group_label">身份证号：</label>'+
			'<div class="group_content">'+
				'<input class="form-control" type="text" value="'+d+'" id="idCard2" />'+
			'</div>'+
		'</div>'+
		'<div class="group_box">'+
			'<label class="group_label"><i class="color_red">*</i>可否提现：</label>'+
			'<div class="group_content">'+
				'<label>'+
					'<input type="radio" name="moneytype" '+gg+' id="" value="6" onclick="getThis(this)"/>可提现'+
				'</label>'+
				'<label>'+
					'<input type="radio" name="moneytype" '+ff+' id="" value="4" onclick="getThis(this)"/>不可提现'+
				'</label>'+
			'</div>'+
		'</div>'+
		'<div class="group_box">'+
			'<label class="group_label"><i class="color_red">*</i>备注：</label>'+
			'<div class="group_content">'+
				'<label>'+
					'<input type="text" id="oaRemark" value="'+myRemark+'"/>'+
				'</label>'+
			'</div>'+
		'</div>'+
	'</div>'+
'</div></div>';
	    $("#id").val(a);
		layer.open({
			type: 1,
			title: "修改",
			btn: ['保存', '取消'], //按钮
			skin: 'layui-layer-rim', //加上边框
			area: ['500px', '500px'], //宽高
			content: xx,
			yes: function() {
				type="修改";
				xx = "保存";
				addRechargeOnce(way);
				layer.closeAll();
				/*layer.msg('修改成功', {
					time: 1000,
					icon: 1
				}, function() {
					layer.closeAll();
				});*/
			}
		});
	}
	
    
}
/**
 * 发送短信验证码
 */
function sendMessage(obj){
	var temp = 0;
	var rechargeMoney = 0;
	var mytable3=document.getElementById("onceRecharge").getElementsByTagName("tr");
	//获取最大金额
	for(var i=0;i<mytable3.length-1;i++){
        var c = $(mytable3).eq(i).find("td").eq(7).html()*1;
        rechargeMoney += c;
        if(mytable3.length <= 2){
            temp = c;
        }else{
            for(var j=i+1;j<mytable3.length-1;j++){
                var d = $(mytable3).eq(j).find("td").eq(7).html()*1;
                if(c>d){
                    temp = c;
                }else{
                    temp=d;
                }
            }
        }
    }
	if(mytable3.length-1>0){
		//执行发送短信验证码的业务逻辑
		$.ajax({
	            type:"GET",
	            url:"/finance/rec/getYzm",
	            dataType:"json",
	            data:{
	                creater:$("#creater").val(),
	                money:rechargeMoney,
	                number:mytable3.length-1,
	                maxMoney:temp
	            },success:function(data){
	            	if(!$(obj).attr("disabled")) {
	            		if(data.aa == "SUCCESS"){
	            			layer.msg('验证短信已发送', {
		            			icon: 1,
		            			time: 1000
		            		});
		            		countDown(obj);
	            		}else if(data.aa == "ERROR"){
	            			layer.msg('验证短信发送失败', {
		            			icon: 1,
		            			time: 1000
		            		});
	            		}else if(data.aa == "FULL"){
	            			layer.msg('该审核人今日审核额度已满', {
		            			icon: 1,
		            			time: 1000
		            		});
	            		}
	            	}
	            }
		});
	}else{
		layer.msg('没有要提交申请的数据', {
			icon: 1
		});
	}
}
/**
 * 提交充值申请
 */
function updateCheckStatus(obj){
	var id = "";
	var money = 0;
	var yzm = $("#yzm").val();
	var mytable3=document.getElementById("onceRecharge").getElementsByTagName("tr");
	if(mytable3.length-1<=0){
		layer.msg('没有可提交的数据', {
			icon: 1
		});
		return;
	}
    for(var i=0;i<mytable3.length-1;i++){
        var c = $(mytable3).eq(i).find("td").eq(1).html();
        id += c+";";
        money += $(mytable3).eq(i).find("td").eq(6).html()*1;
    }
    if(obj == "临时充值"){
    	//判断是否填写验证码
        if($("#yzm").val() == ""){
        	layer.msg('请填写验证码', {
    			icon: 1
    		});
            return;
        }
    }
  //判断验证码是否正确
	$.ajax({
		type:"get",
		url:"/finance/rec/updateCheckStatus",
		dataType:"json",
		async:false,
		data:{
			idList:id,
			yzm:yzm,
			operator:$("#creater").val(),
			type:obj
		},success:function(data){
			console.log(data);
			if(data.aa == "SUCCESS"){
				layer.msg('提交成功', {
					icon: 1
				});
				if(obj == "临时充值"){
					findRechargeInfo('临时充值');
				}else if(rechargeWay == "OA充值"){
					findRechargeInfo('OA充值');
			    	
				}else{
					findRechargeInfo('自主添加');
				}
				$("#yzm").val("");
			}else if(data.aa == "YZMERROR"){
				layer.msg('验证码错误', {
					icon: 2
				});
			}else{
				layer.msg('提交失败，请联系技术人员！', {
					icon: 2
				});
			}
		}
	});
}
//倒计时
function countDown(_this) {
	var m = 9;
	var s = 59;
	var time = setInterval(function() {
		$(_this).attr("disabled", "disabled");
		if(s < 10) {
			$(_this).text('剩余时间' + m + ':0' + s);
		} else {
			$(_this).text('剩余时间' + m + ':' + s);
		}
		s--;
		if(s < 0) {
			s = 59;
			m--;
		}
		if(s == 0 && m == 0) {
			clearInterval(time);
			$(_this).text('发送验证短信').removeAttr("disabled");
		}
	}, 1000)
}
/**
 * 获取文件名称
 */
function getFileName(){
	$("#fileName").val(($("#file").val()));
}
/**
 * 文件上传
 */
function uploadExcel(){
	if($("#fileName").val() == ""){
		layer.msg('请选择要上传的文件', {
			icon: 1
		});
		return false;
	}else{
		$("#upload").submit();
		$("#fileName").val("");
	}
}
/**
 * 下载excel模板
 */
function downLoadExcel(){
    //下载excel模板
    window.location.href="/finance/rec/downLoadExcel";
}
/**
 * 当选择扣减的时候，隐藏提现，显示原因
 */
function checkRType(obj){
	
	if($(obj).val() == 5){
		$("#mywithdrawls").hide();
		$("#reason").show();
		$("#remark").hide();
	}else{
		$("#mywithdrawls").show();
		$("#reason").hide();
		$("#remark").show();
	}
	
}