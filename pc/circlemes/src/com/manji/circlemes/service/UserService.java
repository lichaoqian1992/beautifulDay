package com.manji.circlemes.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.manji.circlemes.model.common.NetRes;
import com.manji.circlemes.model.common.Result;
import com.manji.circlemes.model.common.ResultCode;

import net.sf.json.JSONObject;

public class UserService {

	private NetService netService = new NetService();
	
	private static Logger logger = Logger.getLogger(RelService.class);  
	
	public Result registerUser(String name) {

		Result r = new Result();

		Map<String, String> map = new HashMap<String, String>();
		map.put("username", name);
		map.put("password", name);

		String json = JSONObject.fromObject(map).toString();

		NetRes nr = netService.doNetPostWithAuth("https://a1.easemob.com/1177161212178229/mjms/users", json);

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

	public Result queryUser(String name) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms";
		url += "/users" + "/" + name;

		NetRes nr = netService.doNetGetWithAuth(url);
		System.out.println(JSONObject.fromObject(nr).toString());
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

	public Result disableUser(String name) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms";

		url += "/" + name + "/deactivate";

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

	public Result relieveUser(String name) {

		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms";

		url += "/" + name + "/activate";

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

	public Result disconnect(String name) {
		
		Result r = new Result();

		String url = "https://a1.easemob.com/1177161212178229/mjms";

		url += "/" + name + "/disconnect";

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

	public Result deleteUser(String name) {

		Result r =new Result();
		
		String url  = "https://a1.easemob.com/1177161212178229/mjms";
		
		url +="/"+name+"/users";
		
		NetRes nr =netService.doNetDeleteWithAuth(url);
		

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
