package com.manji.circlemes.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.manji.circlemes.model.common.NetRes;
import com.manji.circlemes.model.common.Result;
import com.manji.circlemes.model.common.ResultCode;

import net.sf.json.JSONObject;

public class RelService {

	private NetService netService = new NetService();
	private static Logger logger = Logger.getLogger(RelService.class);

	public Result addFriend(String name, String friend) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms/users";

		url += "/" + name + "/contacts/users/" + friend;

		NetRes nr = netService.doNetPostWithAuth(url, "");

		if (nr.getCode() == 200) {
			r.setCode("0000");
			r.setData(nr.getContent());
		} else {
			r.setCode("0004");
			r.setData(ResultCode.CODE0004);
		}
		logger.info(JSONObject.fromObject(nr).toString());

		return r;
	}

	public Result delFriend(String name, String friend) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms/users";

		url += "/" + name + "/contacts/users/" + friend;

		NetRes nr = netService.doNetDeleteWithAuth(url);

		if (nr.getCode() == 200) {
			r.setCode("0000");
			r.setData(nr.getContent());
		} else {
			r.setCode("0004");
			r.setData(ResultCode.CODE0004);
		}

		logger.info(JSONObject.fromObject(nr).toString());
		return r;
	}

	public Result selFriends(String name) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms/users";

		url += "/" + name + "/contacts/users";

		NetRes nr = netService.doNetGetWithAuth(url);

		if (nr.getCode() == 200) {
			r.setCode("0000");
			r.setData(nr.getContent());
		} else {
			r.setCode("0004");
			r.setData(ResultCode.CODE0004);
		}
		logger.info(JSONObject.fromObject(nr).toString());
		return r;
	}

	public Result addBlocks(String name, String blocks) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms/users";

		url += "/" + name + "/blocks/users";

		String[] block = blocks.split(",");

		Map<String, String[]> map = new HashMap<String, String[]>();

		map.put("usernames", block);

		String json = JSONObject.fromObject(map).toString();

		NetRes nr = netService.doNetPostWithAuth(url, json);

		if (nr.getCode() == 200) {
			r.setCode("0000");
			r.setData(nr.getContent());
		} else {
			r.setCode("0004");
			r.setData(ResultCode.CODE0004);
		}
		logger.info(JSONObject.fromObject(nr).toString());

		return r;
	}

	public Result delBlock(String name, String block) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms/users";

		url += "/" + name + "/blocks/users/" + block;

		NetRes nr = netService.doNetDeleteWithAuth(url);

		if (nr.getCode() == 200) {
			r.setCode("0000");
			r.setData(nr.getContent());
		} else {
			r.setCode("0004");
			r.setData(ResultCode.CODE0004);
		}
		logger.info(JSONObject.fromObject(nr).toString());

		return r;

	}

	public Result selBlocks(String name) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms/users";

		url += "/" + name + "/blocks/users";

		NetRes nr = netService.doNetGetWithAuth(url);

		if (nr.getCode() == 200) {
			r.setCode("0000");
			r.setData(nr.getContent());
		} else {
			r.setCode("0004");
			r.setData(ResultCode.CODE0004);
		}
		logger.info(JSONObject.fromObject(nr).toString());

		return r;
	}

}
