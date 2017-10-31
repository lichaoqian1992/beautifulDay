package com.manji.data.controller.user;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.user.WeChatPageVo;
import com.manji.data.service.UserService;

public class WeChatController extends Controller {

	private UserService service = new UserService();

	public void index() {

		WeChatPageVo vo = getBean(WeChatPageVo.class, "");

		Page<Record> page = service.getWeChatPage(vo);

		setAttr("page", page);

		render("list.html");
	}

	public void detail() {

	}

	public void insert() {

	}

	public void update() {

	}

	public void delete() {

	}

}
