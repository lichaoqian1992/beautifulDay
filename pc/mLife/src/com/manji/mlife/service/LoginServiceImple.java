package com.manji.mlife.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.mapper.SimpleCodeMapper;
import com.manji.mlife.model.SimpleCode;
import com.manji.mlife.utils.LoadJson;

import net.sf.json.JSONObject;

@Service
public class LoginServiceImple implements LoginService {

	@Autowired
	private  SimpleCodeMapper codeMapper;
	
	@Override
	public String getUserUrl() {

		String url =codeMapper.getValue("UserURL");

		return url;
	}

	@Override
	public boolean isModelOn(String modelName) {
		
		
		String result =codeMapper.getValue(modelName);
		
		if("on".equals(result.trim())){
			return true;
		}else{
			return false;
		}
		
	
	}
	public List<SimpleCode> queryAll(){
		List<SimpleCode> l = codeMapper.getAll();
		return l;
	}

	@Override
	public void updateStatus(String value , String codename) {
		try {
			SimpleCode sc = new SimpleCode();
			sc.setCodename(codename);
			sc.setValue(value);
			codeMapper.updateStstus(sc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public Map<String, String> getUserInfoBySession(String sessionId) {
//		System.out.println(sessionId);
		Map<String, String> map =new HashMap<String, String>();
		
		if(null !=sessionId&&!"".equals(sessionId)){
//		System.out.println(1);
		String url =codeMapper.getValue("UserURL");
//		System.out.println(url);
		map.put("action", "GetUserInfoBySessionId");
		map.put("sessionid", sessionId);
//		System.out.println(url);
		try {
			String loadJSON = LoadJson.loadJSON(url, map);
			System.out.println(loadJSON);
			JSONObject obj = JSONObject.fromObject(loadJSON);
			//判读获取的信息是否可用
			if(obj.getString("status").equals("1")){
				JSONObject jsonObject = obj.getJSONObject("data");
				//判断账户是否正常
//		        "status": 0,// 账户状态,0正常,1待验证,2待审核,3锁定,4黑名单
//		        "is_del": 0,//是否删除 0正常 1删除
				if(jsonObject.getString("status").equals("0")&&jsonObject.getString("is_del").equals("0")){
					
					map.put("userName", jsonObject.getString("user_name"));
					map.put("result", "0");
				}
			}else{
				//未获取到用户的错误信息返回
					map.put("errMsg", obj.getString("msg"));
					map.put("result", "1");
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			map.put("result", "1");
		}
		}
		
		
		return map;
	}
	
	

}
