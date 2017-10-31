package com.manji.data.controller.user;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.user.UserPageVo;
import com.manji.data.service.UserService;

public class UserController extends Controller {

	private UserService service = new UserService();

	public void index() {

		UserPageVo vo = getBean(UserPageVo.class, "");

		Page<Record> page = service.getUserPage(vo);

		setAttr("page", page);

		render("list.html");
	}

	public void detail() {

	}

	public void update() {

	}

	public void insert() {

	}
}
