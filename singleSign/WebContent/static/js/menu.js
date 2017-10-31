//列表背景颜色设置
$(function () {
    bindMenu(1);
    /*关闭弹框*/
    $(".kf_mode_btn_close_update").click(function(){
        $(".kf_mode_update, .kf_mode_shield_update").hide();
    });

    /*提交表单*/
    $(".kf_mode_btn_update_ok").click(function(){
        submitForm("/singleSign/menu/updateMenu");
    })

    /*提交表单*/
    $(".kf_mode_btn_insert_ok").click(function(){
        submitForm("/singleSign/menu/insertMenu");
    })
});

//修改menu
function updateMenu(id){
    $(".kf_mode_title").text("编辑导航");
    $(".kf_mode_update, .kf_mode_shield_update").show();
    $(".kf_mode_btn_insert_ok").hide();
    $(".kf_mode_btn_update_ok").show();
    $(".error").empty();
    $("#id").attr("name","menu.id");
    $("#id").val(id);

    //获取数据
    $.ajax({
        type: "GET",
        url: "/singleSign/menu/menuId",
        dataType: "json",
        data: {id:id},
        async: false,
        success: function (singleMenu) {
            $("#deptCodeUp").val(singleMenu.sys_id);
            $("#powerNameUp").val(singleMenu.title);
            $("#hierarchyUp").val(singleMenu.layer);
            $("#addressCodeUp").val(singleMenu.address);
            $("#routeUp").val(singleMenu.url);
            $("#powerTypeUp").val(singleMenu.type);
            $("#sortUp").val(singleMenu.sort);
            $("#statusUp").val(singleMenu.is_del);
        }
    });
}

//新增
function insertMenu(){
    $(".kf_mode_title").text("新增导航");
    $(".kf_mode_btn_update_ok").hide();
    $(".kf_mode_btn_insert_ok").show();
    $("form")[0].reset();
    $("#id").removeAttr("name");
    $(".kf_mode_update, .kf_mode_shield_update").show();
    $(".error").empty();
}

//提交
function submitForm(url){
    $("#cForm").validate({
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
            "menu.title": {
                required: true
            },
            "menu.layer": {
                required: true
            },
            "menu.address": {
                required: true
            },
            "menu.type": {
                required: true
            },
            "menu.sort": {
                required: true
            },


        },
        messages:{//验证错误信息
            "menu.title": {
                required: "权限名称必填"
            },
            "menu.layer": {
                required: "层级必填"
            },
            "menu.address": {
                required: "地址代码必填"
            },
            "menu.type": {
                required: "权限类型必填"
            },
            "menu.sort": {
                required: "排序必填"
            },
        },
        submitHandler: function(form) { //通过之后回调
            operation(url);
        },
        invalidHandler: function(form, validator) {return false;}
    });
}

//ajax请求(提交)
function operation(url){
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        data:$("form").serialize(),
        async: false,
        success: function (menuUp) {
            if(menuUp.code="0000"){
            	showMessage(menuUp.data);
                interval = setInterval(function () {
                    $(".kf_mode_tips").hide();
                    //刷新列表
                    var page = $('#pageNumber').val();
                    bindMenu(page);
                    //关闭弹出窗
                    $(".kf_mode_update, .kf_mode_shield_update").hide();
                    clearInterval(interval);
                }, 2000);
            }else{
            	showMessage(menuUp.data);
                interval = setInterval(function () {
                    $(".kf_mode_tips").hide();
                    clearInterval(interval);
                }, 2000);
            }
        }
    });
}

//绑定基础数据
function bindMenu(page){
    var menuTxt="";
    $.ajax({
        type:"GET",
        url:"/singleSign/menu/menuList",
        dataType:"json",
        data:{
            pages:20,
            currentPage:page,
            powerName:$("#powerName").val(),
            deptCode:$("#deptCode").val()
        },
        async: false,
        success:function (menu) {
            $("#Date").children().remove();
            if(menu.totalRow>0){
                $(menu.list).each(function(index){
                    menuTxt+=["<tr>",
                        "<td>"+(index+1)+"</td>",
                        "<td>"+this.system_name+"</td>",
                        "<td>"+this.title+"</td>",
                        "<td>"+this.layer+"</td>",
                        "<td>"+this.address+"</td>",
                        "<td>"+this.url+"</td>",
                        "<td>"+this.type+"</td>",
                        "<td>"+this.sort+"</td>",
                        "<td>"+this.is_del+"</td>",
                        "<td style='cursor: pointer;' onclick=\"updateMenu("+this.id+");\">操作</td>",
                        "</tr>"].join("");
                });
            }else{
                menuTxt="<tr colspan='10'>暂无数据</tr>";
            }
            $("#Date").html(menuTxt);
            pageDate(menu);
        }
    });
}

function pageDate(data) {
    $(".kf_page_text i:eq(0)").text(data.pageSize);
    $(".kf_page_text i:eq(1)").text(data.totalPage);
    $("#pageNumber").val(data.pageNumber);
    $("#lastPageNumber").val(data.totalPage);
    $(".kf_page_size span").children().remove();
    for(var i=0;i<data.totalPage;i++){
        if((i+1)==data.pageNumber){
            $(".kf_page_size span").append("<li onclick='bindMenu("+(i+1)+")' class='active'><a>"+(i+1)+"</a></li>");
        }else{
            $(".kf_page_size span").append("<li onclick='bindMenu("+(i+1)+")'><a>"+(i+1)+"</a></li>");
        }
    }
}

function prevDate(){
    if((parseInt($("#pageNumber").val())-1)<1){
    	showMessage("已经是第一页了!");
       
        return;
    }
    bindMenu(parseInt($("#pageNumber").val())-1);
}

function nextDate(){
    if((parseInt($("#pageNumber").val())+1)>parseInt($("#lastPageNumber").val())){
    	showMessage("已经是最后一页!");
        
        return;
    }
    bindMenu(parseInt($("#pageNumber").val())+1);
}
function showMessage(m){
	
	$(".kf_mode_tips2").show().find(".kf_mode_tips_error").text(m);
	setTimeout(function(){
		$(".kf_mode_tips2").hide();
	}, 2000);
}

