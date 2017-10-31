package com.manji.circlemes.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.circlemes.model.common.NetRes;
import com.manji.circlemes.service.NetService;
import com.manji.circlemes.service.RelService;

import net.sf.json.JSONArray;

public class UserThread implements Runnable{

	private NetService service =new NetService();
	private static Logger logger = Logger.getLogger(UserThread.class);  
	
	
	@Override
	public void run() {
	
		logger.info("===================================用户批量开始");
		int no =1;
		
		Page<Record> userPage = Db.use("manji").paginate(no, 20, "select id,user_name", "from dt_users  where is_del=?" ,0);
		
		int pageCount =userPage.getTotalPage();
		
		
//		List<Map<String,String>> users =new ArrayList<Map<String,String>>();
		
		//进入循环
		for(int i=0;i<pageCount;i++){
			//获得分页数据
			Page<Record> page =Db.use("manji").paginate(no, 20, "select id,user_name", "from dt_users  where is_del=?" ,0);
			
			List<Record> list =page.getList();
			
			List<Map<String,String>> users =new ArrayList<Map<String,String>>();
			
			for(int m=0;m<list.size();m++){
				
				Record rec =list.get(m);
				
				Map<String,String> temp =new HashMap<String,String>();
				
				int id =rec.getInt("id");
				
				temp.put("username", id+"Buyer"+"manji");
				temp.put("password", id+"Buyer"+"manji");
				
				users.add(temp);
				
			}
			
			
//			logger.info(JSONArray.fromObject(users).toString());
			
			String json =JSONArray.fromObject(users).toString();
			
			logger.info("====================第"+no+"次发送："+json);
			
			NetRes nr = service.doNetPostWithAuth("https://a1.easemob.com/1177161212178229/mjms/users", json);
//
			int code=nr.getCode();
			logger.info("====================结果为："+code);
//			Record l_Rec=new Record();
//			
//			l_Rec.set("type","user").set("state", code+"").set("data", json);
//			Db.use("local").save("cir_batch", l_Rec);
			
			try {
				Thread.sleep(1500);
				logger.info("休眠2s");
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
			no++;
		}
		
		
		
		
		logger.info("==============================用户批量完成");
		
	}

}
