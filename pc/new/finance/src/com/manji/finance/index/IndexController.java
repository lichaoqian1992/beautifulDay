package com.manji.finance.index;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.system.SystemLogsRepository;
import com.manji.finance.utils.DecriptUtil;
public class IndexController extends Controller{

	IndexService service = new IndexService();
	
	SystemLogsRepository systemLogsRepository = new SystemLogsRepository();
	
	public void index(){

		render("login.html");
	}
	/**
	 * 退出登录
	 */
	@Before(MyInterceptor.class)
	public void loginOut(){
		Record r = getSessionAttr("user");
		//记录登录日志
		Record record = new Record();
		String content = r.get("REALNAME")+"在"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"退出系统！";
		record.set("account_name", r.get("USERNAME"));
		record.set("log_info", content);
		record.set("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		record.set("real_name", r.get("REALNAME"));
		systemLogsRepository.saveSysLogs(record);
		
		removeSessionAttr("user");
		render("login.html");
	}
	public void test(){
		renderText("sb");
	}
	public void unauthorizedRole(){
		render("500.html");
	}
	
	@Before(LoginValidator.class)
	public void login(){
		List<Record> list1 = new ArrayList<Record>();
		List<Record> list2 = new ArrayList<Record>();
		List<Record> list3 = new ArrayList<Record>();
		List<Record> list4 = new ArrayList<Record>();
		List<Record> list5 = new ArrayList<Record>();
		List<Record> list6 = new ArrayList<Record>();
		
		String username =getPara("username");
		String password =getPara("password");
		//校验登录
		try{
			
			Subject user = SecurityUtils.getSubject();//获取当前登录用户信息
			String newPass = DecriptUtil.SHA1(password);//加密后的密码
			//判断是否登录，没有登录则执行登录
			if(!user.isAuthenticated()){
				//创建用户名和密码的Token
				UsernamePasswordToken token = new UsernamePasswordToken(username,newPass);
				//执行登录
				user.login(token);
			}
			if(service.findUser(username, password).size()>0){
				getSession().setMaxInactiveInterval(-1);
				Record r = service.findUser(username, password).get(0);
				setSessionAttr("user",r);
				//查询权限页面
				List<Record> list = service.findMenu(username);
				//不同的模块，用不同的参数
				//首页
				for(int i=0;i<list.size();i++){
					//主菜单
					if(list.get(i).get("hierarchy").toString().equals("1")){
						list1.add(list.get(i));
						
					}else if(list.get(i).get("hierarchy").toString().equals("2")){
						if(list.get(i).get("sort").toString().split(",")[0].equals("2")){
							//充值
							list2.add(list.get(i));
							
						}else if(list.get(i).get("sort").toString().split(",")[0].equals("3")){
							//提现
							list3.add(list.get(i));
							
						}else if(list.get(i).get("sort").toString().split(",")[0].equals("4")){
							//集团
							list4.add(list.get(i));
							
						}else if(list.get(i).get("sort").toString().split(",")[0].equals("5")){
							//订单
							list5.add(list.get(i));
							
						}else if(list.get(i).get("sort").toString().split(",")[0].equals("6")){
							//系统
							list6.add(list.get(i));
							
						}
					}
				}
				//设置session永不失效
				setSessionAttr("main",list1);
				setSessionAttr("rec",list2);
				setSessionAttr("withdrawls",list3);
				setSessionAttr("group",list4);
				setSessionAttr("order",list5);
				setSessionAttr("sys",list6);
				redirect("/home/toHome");

				//记录登录日志
				Record record = new Record();
				String content = r.get("REALNAME")+"在"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"登录系统！";
				record.set("account_name", username);
				record.set("log_info", content);
				record.set("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				record.set("real_name", r.get("REALNAME"));
				systemLogsRepository.saveSysLogs(record);
			}else{
				setSessionAttr("message","ERROR");
			}
			
		}catch(Exception e){
			setAttr("message", "ERROR");
			e.printStackTrace();
			
		}finally{
			setAttr("message", "ERROR");
		}
	}
	
	
}
