package com.manji.finance.utils;

import java.util.List;

import com.manji.finance.system.UserSmsLogDO;

/**
 * 发送短信工具类
 * @author Administrator
 *
 */
public class SmsLogsUtils {

	/**
	 * 发送短信
	 * @return
	 */
	public static int sendMessage(List<UserSmsLogDO> list){
		int count = 0;
		for(int i=0;i<list.size();i++){
			String sql = "insert into dt_user_sms_log(user_id,user_role_type,user_role_value,type,content,user_ip,mobile,add_time,send_status,send_time) values ('"+list.get(i).getUserId()+"','"+list.get(i).getUserRoleType()+"','"+list.get(i).getUserRoleValue()+"','"+list.get(i).getType()+"','"+list.get(i).getContent()+"','"+list.get(i).getUserIp()+"','"+list.get(i).getMobile()+"','"+list.get(i).getAddTime()+"','"+list.get(i).getStatus()+"','"+list.get(i).getSendTime()+"')";
			int a = DButils.sqlserver.update(sql);
			count += a;
		}
		return count;
	}
	
}
