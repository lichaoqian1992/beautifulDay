/**
 * Created by pudding on 2017-6-27.
 */

$(function(){
    Data(1);

    //上一页
    $('.kf_page_prev').click(function(){
        var page =$('#pageNumber').val()-1;
        if(page == 0){
        	showMessage("已经是第一页");
        	return;
        }
        Data(page);
    })

    //下一页
    $('.kf_page_next').click(function(){
        var page =$('#pageNumber').val()+1;
        if(page > $("#lastPageNumber").val()*1){
        	showMessage("已经是最后一页");
        	return;
        }
        Data(page);
    })
})

$(function(){
    $(".kf_add_consult").click(function(){
        $(".kf_mode_add, .kf_mode_shield").show();
    });
    $(".kf_table").on("click",".kf_table_a_edit",function(){
        $(".kf_mode_edit, .kf_mode_shield").show();
    });

    $(".kf_mode_btn_close").click(function(){
        $(".kf_mode_content, .kf_mode_shield").hide();
    });

    //点击确认添加时
    $(".kf_mode_btn_add_ok").click(function(){
        var dept_name=$('#add_dept_name').val();
        var dept_type=$('#add_dept_type').val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/singleSign/dept/add",
            dataType: "json",
            data:{
                dept_name:dept_name,
                type:dept_type,
                status:1
            },success:function(date){
                if (date.isok=="success"){
                    $(".kf_mode_tips").show().find(".kf_mode_tips_box").text("修改成功!");
                    interval = setInterval(function () {
                        $(".kf_mode_tips").hide();
                        //刷新列表
                        var page = $('#pageNumber').val();
                        Data(page);
                        //关闭弹出窗
                        $(".kf_mode_content, .kf_mode_shield").hide();
                        clearInterval(interval);
                    }, 2000);
                }else{
                    $(".kf_mode_tips").show().find(".kf_mode_tips_box").text("修改失败!");
                    interval = setInterval(function () {
                        $(".kf_mode_tips").hide();
                        clearInterval(interval);
                    }, 2000);
                }
            }
        });
    });
});


//弹出修改窗绑定数据
function updatedept(id){
    $(".kf_mode_update, .kf_mode_shield_update").show();
    $('#update_dept_id').val(id);
    //绑定初始数据
    $.ajax({
        async: false,
        type: "GET",
        url: "/singleSign/dept/findDeptByid",
        dataType: "json",
        data: {
            dept_id: id
        }, success: function (data) {
            $('#update_dept_name').val(data.dept.dept_name);
            $('#update_dept_type').val(data.dept.type);
            $('#updatesataus').val(data.dept.status);
        }
    });
}


$(function() {

    $(".kf_mode_btn_close_update").click(function () {
        //关闭新增用户弹出窗
        $(".kf_mode_update, .kf_mode_shield_update").hide();
    });
    $(".kf_mode_btn_update_ok").click(function () {
        var dept_id=$('#update_dept_id').val();
        var dept_name=$('#update_dept_name').val();
        var dept_type=$('#update_dept_type').val();
        var dept_status=$('#updatesataus').val();

        $.ajax({
            async: false,
            type: "GET",
            url: "/singleSign/dept/update",
            dataType: "json",
            data: {
                id: dept_id,
                dept_name: dept_name,
                type: dept_type,
                status: dept_status
            }, success: function (date) {
                if (date.isok == 'success') {
                    $(".kf_mode_tips").show().find(".kf_mode_tips_box").text("修改成功!");
                    interval = setInterval(function () {
                        $(".kf_mode_tips").hide();
                        //刷新列表
                        var page = $('#pageNumber').val();
                        Data(page);
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
    });



});

//绑定基础数据
function Data(page){

    var dept_name=$('#dept_name').val();

    if (page == null || page < 1) {
        page = 1;
    }
    if (page > $('#lastPageNumber').val()) {
        page = $('#lastPageNumber').val();
    }

    $.ajax({
        async: false,
        type: "GET",
        url: "/singleSign/dept/query",
        dataType: "json",
        data: {
            page: page,
            dept_name: dept_name
        }, success: function (date) {
        	console.log(date.deptList);
        	
            var txt = "";
            var dept_type = "";
            var dept_status="";
            for (var i = 0; i < date.deptList.list.length; i++) {
                if (date.deptList.list[i].type == '1') {
                    dept_type = '检查部门';
                } else {
                    dept_type = '执行部门';
                }

                if (date.deptList.list[i].status == 1) {
                    dept_status = '正常';
                } else {
                    dept_status = '异常';
                }

                txt +="<tr><td>"+date.deptList.list[i].id+
                      "</td><td>"+date.deptList.list[i].dept_name+
                      "</td><td>"+dept_type+
                      "</td><td>"+dept_status+
                      "</td><td><a style='cursor: pointer;color: #63baf8' onclick='updatedept("+date.deptList.list[i].id+")'>点击查看</a></td></tr>";
            }
            $("#Data").empty();
            $(".kf_page_size span").empty();
            $("#Data").html(txt);
            $("#pageNumber").val(date.deptList.pageNumber);
            $("#lastPageNumber").val(date.deptList.totalPage);
            $("#totlepage").html(date.deptList.totalPage);
            $("#pageSize").html(date.deptList.pageSize);
            //显示当前页码
            for(var i=0;i<date.deptList.totalPage;i++){
                if((i+1)==date.deptList.pageNumber){
                    $(".kf_page_size span").append("<li onclick='Data("+(i+1)+")' class='active'><a>"+(i+1)+"</a></li>");
                }else{
                    $(".kf_page_size span").append("<li onclick='Data("+(i+1)+")'><a>"+(i+1)+"</a></li>");
                }
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