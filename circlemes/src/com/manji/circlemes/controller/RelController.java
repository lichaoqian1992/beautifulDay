package com.manji.circlemes.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.manji.circlemes.interceptor.SignInterceptor;
import com.manji.circlemes.model.common.Result;
import com.manji.circlemes.service.RelService;
import com.manji.circlemes.validator.RelValidator;
import com.manji.circlemes.validator.UserValidator;

//@Before(SignInterceptor.class)
public class RelController extends Controller {

	private RelService service = new RelService();

	public void index() {

	}

	/**
	 * 添加好友关系
	 * 
	 * name 添加人 friend 被添加人
	 */
	@Before(RelValidator.class)
	public void addFriend() {

		Result r = new Result();

		String name = getPara("name");
		String friend = getPara("friend");

		r = service.addFriend(name, friend);

		renderJson(r);

	}

	/**
	 * 删除好友关系
	 * 
	 * name 解除人 friend 被解除人
	 */
	@Before(RelValidator.class)
	public void delFriend() {

		Result r = new Result();

		String name = getPara("name");
		String friend = getPara("friend");

		r = service.delFriend(name, friend);

		renderJson(r);
	}

	/**
	 * 查询好友
	 * 
	 * name 查询人
	 */
	@Before(UserValidator.class)
	public void selFriends() {

		Result r = new Result();

		String name = getPara("name");

		r = service.selFriends(name);

		renderJson(r);
	}

	/**
	 * 添加用户黑名单
	 * 
	 * name 添加用户 blocks 被添加黑名单用户
	 */
	@Before(RelValidator.class)
	public void addBlocks() {

		Result r = new Result();

		String name = getPara("name");
		String blocks = getPara("blocks");

		r = service.addBlocks(name, blocks);

		renderJson(r);
	}

	/**
	 * 删除黑名单
	 * 
	 * name 操作用户 block 被移除用户
	 */
	@Before(RelValidator.class)
	public void delBlock() {
		Result r = new Result();

		String name = getPara("name");
		String block = getPara("block");

		r = service.delBlock(name, block);

		renderJson(r);
	}

	/**
	 * 查询用户黑名单
	 * 
	 * name 操作用户
	 * 
	 */
	@Before(UserValidator.class)
	public void selBlocks() {
		Result r = new Result();

		String name = getPara("name");

		r = service.selBlocks(name);

		renderJson(r);

	}

}
