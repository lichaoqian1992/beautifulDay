$(function() {

	//执行单击事件
	$(".kf_form_penalize_execute_btn_a").click(function() {
		if(!$(this).hasClass("kf_form_penalize_execute_btn_a_no")) {
			$(".kf_mode_affirm .kf_display_table_cell p").text("请仔细核对仲裁结果和执行处罚是否一至。");
			$(".kf_mode_affirm, .kf_mode_shield").show();
			$(".kf_mode_shield").attr("istype", "zhix");
		}
	});

	//推送单击事件
	$(".kf_form_btn_tuis").click(function() {
		if(!$(this).hasClass("kf_btn_disabled")) {
			$(".kf_mode_affirm .kf_display_table_cell p").text("请确定将仲裁结果推送给发起人和被发起人。");
			$(".kf_mode_affirm, .kf_mode_shield").show();
			$(".kf_mode_shield").attr("istype", "tuisong");
		}
	});


	//查看物流
	$(".kf_check_logistics").click(function() {
		$(".kf_mode_logistics, .kf_mode_shield").show();
	});

	//关闭物流
    $(".guanbiwuliu").click(function() {
        $(".kf_mode_logistics, .kf_mode_shield").hide();
    });


	/*
	 *  弹出框 结束
	 */

	//收缩内容
	$(".kf_form_content_toggle").click(function() {
		if(!$(this).hasClass("active")) {
			$(this).addClass("active");
			$(this).parent().siblings(".kf_form_toggle_box").hide();
		} else {
			$(this).removeClass("active");
			$(this).parent().siblings(".kf_form_toggle_box").show();
		}

	});

	//处理说明字数
	$(".kf_form_text_textarea_length").keyup(function() {
		var len = $(this).val().length;
		$(this).siblings(".kf_form_text_number").find("i").text(len);
	});

	//上传图片
//	$(".kf_upload_img").on("click", ".kf_upload_box", function() {
//		var par = $(this).parents(".kf_upload_img"),
//			len = par.find(".kf_upload_item").length;
//		var html = '<li class="kf_upload_item"><span class="kf_upload_box"></span>' +
//			'<a class="kf_uploat_item_close kf_hide" href="javascript:void(0);"></a></li>';
//
//		if(len < 5 && $(this).find("img").length == 0) {
//			par.append(html);
//		}
//
//		if($(this).find("img").length == 0) {
//			$(this).append('<img src="images/shanchu.png" />').siblings(".kf_uploat_item_close").removeClass("kf_hide");
//			par.siblings(".kf_form_text_number").find("i").text(len);
//		}
//
//	});

	//删除上传图片
	$(".kf_upload_img").on("click", ".kf_uploat_item_close", function() {
		var par = $(this).parents(".kf_upload_img"),
			item = par.find(".kf_upload_item");
		len = item.length;
		var html = '<li class="kf_upload_item"><span class="kf_upload_box"></span>' +
			'<a class="kf_uploat_item_close kf_hide" href="javascript:void(0);"></a></li>';

		if(len == 5 && item.eq(len - 1).find(".kf_upload_box").html() != "") {
			par.append(html);
		} else if(item.eq(len - 1).find(".kf_upload_box").html() == "") {
			len -= 1; //页面本来有一个没有图片的
		}
		$(this).parents(".kf_upload_item").remove(); //删除图片
		par.siblings(".kf_form_text_number").find("i").text(len - 1); //len-1为上传的图片个数减1
	});

});