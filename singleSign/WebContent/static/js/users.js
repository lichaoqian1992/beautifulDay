/**
 * Created by pudding on 2017-6-22.
 */

$(function(){
    data(1);
    findrole();
    findroleUpdate();
    //上一页
    $('.kf_page_prev').click(function(){
        var page =$('#pageNumber').val()-1;
        if(page == 0){
        	showMessage("已经是第一页");
        	return;
        }
        data(page);
    });

    //下一页
    $('.kf_page_next').click(function(){
        var page =$('#pageNumber').val()*1+1;

        if(page > $("#lastPageNumber").val()*1){
        	showMessage("已经是最后一页");
        	return;
        }
        data(page);
    });
    
    $(".systemandrole").on("click",".del",function(){
    	$(this).parent().parent().parent().remove();
    });

    //檢測邮政编码
    jQuery.validator.addMethod("isZipCode", function(value, element) {
        $(".kf_mode_add .record .kf_form_sum").each(function(){
            system.push($(this).val());
        });

        if(system.length<=0){
            return false;
        }else{
            return true;
        }
    }, "请正确填写您的邮政编码");

});

//绑定增加用户对应项目角色
function findrole(){
    var system=$('#savesystemInsert').val();
    $.ajax({
        async: false,
        type: "GET",
        url: "/singleSign/user/findRoleBySystem",
        dataType: "json",
        data: {
            system:system
            },success:function(data) {
                var txt="";
                for (var i=0;i<data.roleList.length;i++){
                    txt+="<option value='"+data.roleList[i].id+"'>"+data.roleList[i].role_name+"</option>";
                }
                $("#saveroleInsert").empty();
                $("#saveroleInsert").html(txt);
            }
        });
}

//绑定增加用户对应项目角色
function findroleUpdate(){
    var system=$('#savesystemUpdate').val();
    $.ajax({
        async: false,
        type: "GET",
        url: "/singleSign/user/findRoleBySystem",
        dataType: "json",
        data: {
            system:system
            },success:function(data) {
                var txt="";
                for (var i=0;i<data.roleList.length;i++){
                    txt+="<option value='"+data.roleList[i].id+"'>"+data.roleList[i].role_name+"</option>";
                }
                $("#saveroleUpdate").empty();
                $("#saveroleUpdate").html(txt);
            }
        });
}


    //新增用户
    $(function () {
        $(".kf_add_consult").click(function () {
            $(".kf_mode_add, .kf_mode_shield").show();
        });
        $(".kf_mode_btn_close").click(function () {
            //关闭新增用户弹出窗
            $(".error").empty();
            $(".kf_mode_content, .kf_mode_shield").hide();
        });
        //点击修改项目时查询出全部对应角色(添加)
        $('#savesystemInsert').change(function () {
            findrole();
        });
        
        //点击修改项目时查询出全部对应角色（修改）
        $('#savesystemUpdate').change(function () {
        	findroleUpdate();
        });

        $(".kf_mode_btn_add_ok").click(function () {
            $("#insertForm").validate({
                onsubmit:true,// 是否在提交是验证
                onfocusout:false,// 是否在获取焦点时验证
                onkeyup :false,// 是否在敲击键盘时验证
                errorElement: 'div',
                errorPlacement: function(error, element) {
                    if ($(element).parent().hasClass('datetimepicker')) {
                        error.insertAfter(element.parent());
                    } else {
                        error.insertAfter(element);
                    }
                },
                rules: {//规则
                    "insertUserName": {
                        required: true,
                        rangelength:[6,20],
                        remote: {//这里是验证用户名是否重复
                            type: "GET",
                            url: "/singleSign/user/verificationName",
                            dataType: "json",
                            data: {
                                username: function() {
                                    return $("#saveusername").val();
                                }
                            }
                            ,dataFilter: function (data) {
                                if(parseInt(data)<=0){
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                        }
                    },
                    "insertPassword": {
                        required: true,
                        rangelength:[6,20],
                    },
                    "insertPasswordAlso": {
                        required: true,
                        rangelength:[6,20],
                        equalTo: "#savepassword"
                    },

                    "insertNickName": {
                        required: true
                    },
                    "insertPhone": {
                        required: true
                    },
                },
                messages:{//验证错误信息
                    "insertUserName": {
                        required: "登录账号必填",
                        rangelength: "请输入6-20位英文字母和数字的组合",
                        remote:"用户名重复"
                    },
                    "insertPassword": {
                        required: "密码必填",
                        rangelength: "请输入6-20字符"
                    },
                    "insertPasswordAlso": {
                        required: "密码必填",
                        rangelength: "请输入6-20字符",
                        equalTo: "请再次输入相同的密码"
                    },
                    "insertNickName": {
                        required: "真实姓名"
                    },
                    "insertPhone": {
                        required: "手机号码必填"
                    },
                },
                submitHandler: function(form) { //通过之后回调
                    InsertAjaxRequest();
                },
                invalidHandler: function(form, validator) {return false;}
            });
        });

    });

//绑定修改数据
function updatedata(id){
    $(".kf_mode_update, .kf_mode_shield_update").show();
    $('#updateuserid').val(id);
    $(".error").empty();
    //绑定初始数据
    $.ajax({
        async: false,
        type: "GET",
        url: "/singleSign/user/finduserByid",
        dataType: "json",
        data: {
            id: id
        }, success: function (data) {
            $('#updateusername').val(data.user.username);
            $('#updatepassword').val(data.user.password);
            $('#updatedept').val(data.user.dept_id);
            $('#updatesataus').val(data.user.status);
            $('#updateRealName').val(data.user.realName);
            $('#updateMobile').val(data.user.mobile);
            var txt="";

            for (var i=0;i<data.user_role.length;i++){
                txt+="<div class='record'><span class='kf_group_title'>"+(i+1)+":</span><div class='kf_group_content'>" +
                	"<input value='"+data.user_role[i].sys_id+","+data.user_role[i].role_id+"' class='kf_form_sum' type='hidden'style='width: 185px;' readonly='true'>"+
                    "<input value='"+data.user_role[i].s+"' class='kf_form_text' type='text'style='width: 185px;' readonly='true'>" +
                    "<input value='"+data.user_role[i].r+"' class='kf_form_text' type='text'style='width: 185px;' readonly='true'>" +
                    "<span style='margin-left:5px;'><label class='kf_btn del'>删除</label></span></div></div>";
            }
            $('.kf_mode_update .systemandrole').empty();
            $('.kf_mode_update .systemandrole').html(txt);

        }
    });
}

//修改模块
$(function(){
    $(".kf_mode_btn_close_update").click(function(){
        //关闭新增用户弹出窗
        $(".kf_mode_update, .kf_mode_shield_update").hide();
    });
    $(".kf_mode_btn_update_ok").click(function() {
        $("#updateForm").validate({
            onsubmit:true,// 是否在提交是验证
            onfocusout:false,// 是否在获取焦点时验证
            onkeyup :false,// 是否在敲击键盘时验证
            errorElement: 'div',
            errorPlacement: function(error, element) {
                if ($(element).parent().hasClass('datetimepicker')) {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            },
            rules: {//规则
                "updateUserName": {
                    required: true,
                    rangelength:[6,20],
                    remote: {//这里是验证用户名是否重复
                        type: "GET",
                        url: "/singleSign/user/verificationName",
                        dataType: "json",
                        data: {
                            id: function() {
                                return $('#updateuserid').val();
                            },
                            username: function() {
                                return $('#updateusername').val();
                            }
                        }
                        ,dataFilter: function (data) {
                            if(parseInt(data)<=0){
                                return true;
                            }else{
                                return false;
                            }
                        }
                    }
                },
                "updatePassword": {
                    required: true,
                    rangelength:[6,20]
                },
                "updateNickName": {
                    required: true
                },
                "updatePhone": {
                    required: true
                 },
            },
            messages:{//验证错误信息
                "updateUserName": {
                    required: "登录账号必填",
                    rangelength: "请输入6-20位英文字母和数字的组合",
                    remote:"用户名重复"
                },
                "updatePassword": {
                    required: "密码必填",
                    rangelength: "请输入6-20字符"
                },
                "updateNickName": {
                    required: "真实姓名"
                },
                "updatePhone": {
                    required: "手机号码必填",
                },
            },
            submitHandler: function(form) { //通过之后回调
                updateAjaxRequest();
            },
            invalidHandler: function(form, validator) {return false;},
        });
    });
});
//绑定用户基本数据
function data(page) {
    var dept_id = $('#dept_id').val();
    var username = $('#username').val();
    if (page == null || page < 1) {
        page = 1;
    }
    if (page > $('#lastPageNumber').val()) {
        page = $('#lastPageNumber').val();
    }

    $.ajax({
        async: false,
        type: "GET",
        url: "/singleSign/user/findAllusers",
        dataType: "json",
        data: {
            page: page,
            username: username,
            dept_id: dept_id
        }, success: function (data) {

            var txt = "";
            var user_stuts = "";
            for (var i = 0; i < data.userList.list.length; i++) {
                if (data.userList.list[i].status == 1) {
                    user_stuts = '正常';
                } else {
                    user_stuts = '异常';
                }

                txt += "<tr><td>" + data.userList.list[i].id +
                    "</td><td>" + data.userList.list[i].username +
                    "</td><td>" + data.userList.list[i].dept_name +
                    "</td><td>" + data.userList.list[i].realName +
                    "</td><td>" + data.userList.list[i].mobile +
                    "</td><td>" + user_stuts +
                    "</td><td><a style='cursor: pointer;color: #63baf8' onclick='updatedata("+data.userList.list[i].id+")'>点击查看</a></td></tr>"
            }
            $("#data").empty();
            $(".kf_page_size span").empty();
            $("#data").html(txt);
            $("#pageNumber").val(data.userList.pageNumber);
            $("#lastPageNumber").val(data.userList.totalPage);
            $("#totlepage").html(data.userList.totalPage);
            $("#pageSize").html(data.userList.pageSize);
            //显示当前页码
            for(var i=0;i<data.userList.totalPage;i++){
                if((i+1)==data.userList.pageNumber){
                    $(".kf_page_size span").append("<li onclick='data("+(i+1)+")' class='active'><a>"+(i+1)+"</a></li>");
                }else{
                    $(".kf_page_size span").append("<li onclick='data("+(i+1)+")'><a>"+(i+1)+"</a></li>");
                }
            }
        }

    });
}

//修改
function addRecord(){
	var sysId=$("#savesystemUpdate option:selected").val();
	var roleId=$("#saveroleUpdate option:selected").val();
	var sysText=$("#savesystemUpdate option:selected").text();
	var roleText=$("#saveroleUpdate option:selected").text();
	var num =0;
	var stu="success";
	$(".kf_mode_update .record .kf_form_sum").each(function(){
		var arr=$(this).val().split(',');
		if(sysId==arr[0]){
			showMessage("已录入该的系统的角色");
			stu="error";
			return false;
		}
	})
	
	if(stu=="success"){
		if($(".kf_mode_update .record:last .kf_group_title").text()==""){
			num=0;
		}else{
			num= parseInt($(".kf_mode_update .record:last .kf_group_title").text().replace(/[^0-9]/ig,""));
		}
		
		var txt="<div class='record' style='margin-top:5px;'><span class='kf_group_title'>"+(num+1)+":</span><div class='kf_group_content'>" +
		"<input value='"+sysId+","+roleId+"' class='kf_form_sum' type='hidden'style='width: 185px;' readonly='true'>"+
	    "<input value='"+sysText+"' class='kf_form_text' type='text'style='width: 185px;' readonly='true'>" +
	    "<input value='"+roleText+"' class='kf_form_text' type='text'style='width: 185px;' readonly='true'>" +
	    "<span style='margin-left:5px;'><label class='kf_btn del'>删除</label></span></div></div>";

		$('.kf_mode_update .systemandrole').append(txt);
	}
}

//新增
function saveRecord(){
	var sysId=$("#savesystemInsert option:selected").val();
	var roleId=$("#saveroleInsert option:selected").val();
	var sysText=$("#savesystemInsert option:selected").text();
	var roleText=$("#saveroleInsert option:selected").text();
	var num =0;
	var stu="success";
	$(".kf_mode_add .record .kf_form_sum").each(function(){
		var arr=$(this).val().split(',');
		if(sysId==arr[0]){
			showMessage("已录入该的系统的角色");
			stu="error";
			return false;
		}
	})
	
	if(stu=="success"){
		if($(".kf_mode_add .record:last .kf_group_title").text()==""){
			num=0;
		}else{
			num= parseInt($(".kf_mode_add .record:last .kf_group_title").text().replace(/[^0-9]/ig,""));
		}
		
		var txt="<div class='record' style='margin-top:5px;'><span class='kf_group_title'>"+(num+1)+":</span><div class='kf_group_content'>" +
		"<input value='"+sysId+","+roleId+"' class='kf_form_sum' type='hidden'style='width: 185px;' readonly='true'>"+
	    "<input value='"+sysText+"' class='kf_form_text' type='text'style='width: 185px;' readonly='true'>" +
	    "<input value='"+roleText+"' class='kf_form_text' type='text'style='width: 185px;' readonly='true'>" +
	    "<span style='margin-left:5px;'><label class='kf_btn del'>删除</label></span></div></div>";

		$('.kf_mode_add .systemandrole').append(txt);
	}
}

//修改(ajax请求)
function updateAjaxRequest(){
    var id=$('#updateuserid').val();
    var username=$('#updateusername').val();
    var password=$('#updatepassword').val();
    var dept=$('#updatedept').val();
    var status=$('#updatesataus').val();
    var realName=$('#updateRealName').val();
    var mobile=$('#updateMobile').val();
    var system=[];

    $(".kf_mode_update .record .kf_form_sum").each(function(){
        system.push($(this).val());
    })

    $.ajax({
        async: false,
        type: "GET",
        url: "/singleSign/user/updateUser",
        dataType: "json",
        traditional: true,
        data: {
            id: id,
            username:username,
            password:password,
            dept:dept,
            status:status,
            realName:realName,
            mobile:mobile,
            system:system
        }, success: function (data1) {
            if (data1.isok == 'success') {
                $(".kf_mode_tips").show().find(".kf_mode_tips_box").text("修改成功!");
                interval = setInterval(function () {
                    $(".kf_mode_tips").hide();
                    //刷新列表
                    var page = $('#pageNumber').val();
                    data(page);
                    //关闭弹出窗
                    $(".kf_mode_update, .kf_mode_shield_update").hide();
                    clearInterval(interval);
                }, 2000);

            } else {
                $(".kf_mode_tips").show().find(".kf_mode_tips_box").text("修改失败!");
                interval = setInterval(function () {
                    $(".kf_mode_tips").hide();
                    clearInterval(interval);
                }, 2000);
            }
        }
    });
}

//新增(ajax请求)
function InsertAjaxRequest(){

    //点击确认添加时
    var username = $('#saveusername').val();
    var password = $('#savepassword').val();
    var qpassword = $('#saveqpassword').val();
    var realName=$('#insertRealName').val();
    var mobile=$('#insertMobile').val();
    var dept = $('#savedept').val();
    var system=[];

    $(".kf_mode_add .record .kf_form_sum").each(function(){
        system.push($(this).val());
    });

    if(system.length<=0){
        $(".kf_mode_tips").show().find(".kf_mode_tips_box").text("系统角色未选择!");
        interval = setInterval(function () {
            $(".kf_mode_tips").hide();
            clearInterval(interval);
        }, 1000);
        return;
    }

    //请求增加账户
    $.ajax({
        async: false,
        type: "GET",
        url: "/singleSign/user/saveUsers",
        dataType: "json",
        traditional: true,
        data: {
            username: username,
            password: password,
            dept: dept,
            realName:realName,
            mobile:mobile,
            system: system,
        }, success: function (data1) {
            if (data1.isok == 'success') {
                $(".kf_mode_tips").show().find(".kf_mode_tips_box").text("增加成功!");
                interval = setInterval(function () {
                    $(".kf_mode_tips").hide();
                    //刷新列表
                    var page = $('#pageNumber').val();
                    data(page);
                    //关闭弹出窗
                    $(".kf_mode_content, .kf_mode_shield").hide();
                    clearInterval(interval);
                }, 2000);

            } else {
                $(".kf_mode_tips").show().find(".kf_mode_tips_box").text("增加失败!");
                interval = setInterval(function () {
                    $(".kf_mode_tips").hide();
                    clearInterval(interval);
                }, 2000);
            }

        }
    });

}
function showMessage(m){
	
	$(".tankuang").show().find(".kf_mode_tips_error").text(m);
	setTimeout(function(){
		$(".tankuang").hide();
	}, 1000);
}
