package com.manji.data.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.user.FlagPageVo;
import com.manji.data.model.request.user.OauthPageVo;
import com.manji.data.model.request.user.UserCodePageVo;
import com.manji.data.model.request.user.UserPageVo;
import com.manji.data.model.request.user.VerifyPageVo;
import com.manji.data.model.request.user.WeChatPageVo;

public class UserService {

	
	///////////////////////////////////////////////////////////user 用户信息
	public Page<Record> getUserPage(UserPageVo vo) {

		StringBuffer sb =new StringBuffer("from dt_users where 1=1 ");
		
		if(!"".equals(vo.getUser_name())){
			sb.append("and user_name ="+vo.getUser_name());
		}
		
		if(!"".equals(vo.getStatus())){
			sb.append("and status ="+vo.getStatus());
		}
		
		sb.append("order by id desc");
		
		Page<Record> page =Db.paginate(vo.getIndex(), vo.getSize(), "select *", sb.toString());
		
		
		return page;
	}
	
	
	
	/////////////////////////////////////////////////////wechat 用户微信信息
	public Page<Record> getWeChatPage(WeChatPageVo vo) {
		
		StringBuffer sb =new StringBuffer("from dt_user_wechat where 1=1 ");
		
		if(!"".equals(vo.getNick_name())){
			sb.append("and nick_name ="+vo.getNick_name());
		}
		
		sb.append("order by id desc");
		
		Page<Record> page =Db.paginate(vo.getIndex(), vo.getSize(), "select *", sb.toString());
		
		return page;
	}


    ////////////////////////////////////////////////flag 用户申请标识信息
	public Page<Record> getFlagPage(FlagPageVo vo) {
		
		StringBuffer sb =new StringBuffer("from dt_user_from_value where 1=1 ");

		
		sb.append("order by id desc");
		
		Page<Record> page =Db.paginate(vo.getIndex(), vo.getSize(), "select *", sb.toString());
		
		return page;
	}


	////////////////////////////////////////////////////code 用户随机码信息
	public Page<Record> getCodePage(UserCodePageVo vo) {
		
		StringBuffer sb =new StringBuffer("from dt_user_code where 1=1 ");

		
		sb.append("order by id desc");
		
		Page<Record> page =Db.paginate(vo.getIndex(), vo.getSize(), "select *", sb.toString());
		
		return page;
		
	}


	//////////////////////////////////////////////////oauth 验证信息
	public Page<Record> getOauthPage(OauthPageVo vo) {

		StringBuffer sb =new StringBuffer("from dt_user_oauth where 1=1 ");
		
		sb.append("order by id desc");
		
		Page<Record> page =Db.paginate(vo.getIndex(), vo.getSize(), "select *", sb.toString());
		
		return page;
	}


	/////////////////////////////////////////////////////验证信息 verify
	public Page<Record> getVerifyPage(VerifyPageVo vo) {
		
		StringBuffer sb =new StringBuffer("from dt_user_safeprotect where 1=1 ");
		
		sb.append("order by id desc");
		
		Page<Record> page =Db.paginate(vo.getIndex(), vo.getSize(), "select *", sb.toString());
		
		return page;
	}
	
	
	
	
	

}
