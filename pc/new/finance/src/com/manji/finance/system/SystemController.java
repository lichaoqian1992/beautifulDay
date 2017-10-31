package com.manji.finance.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.home.model.pageDo;
import com.manji.finance.index.MyInterceptor;
import com.manji.finance.system.requestParams.UserParams;

@Before(MyInterceptor.class)
public class SystemController extends Controller{

	
	SystemService service = new SystemService();
	/**
	 * 查询交易报送人
	 */
	public void findSysPush(){
		
		List<Record> list = service.findSysPush();
		setAttr("aa", list);
		renderJson();
		
	}
	/**
	 * 保存交易报送人
	 */
	public void saveOrUpdateSys(){
		String[] a=getParaValues("id");
		String[] b = getParaValues("name");
		String[] c = getParaValues("mobile");
		
		if(service.saveOrUpdateSys(a,b,c)>0){
			setAttr("aa", "SUCCESS");
		}else{
			setAttr("aa", "FAIL");
		}
		renderJson();
	}
	
	
	
	/**
	 * 查询角色和对应的人
	 */
	public void findRoleAndConfig(){
		String id = getPara("id");
		renderJson(service.findRoleAndConfig(id));
	}
	/**
	 * 修改或者新增审批人
	 */
	public void saveOrUpdate(){
		String[] a=getParaValues("minMoney");
		String[] b = getParaValues("maxMoney");
		String[] c = getParaValues("totalMoney");
		String[] d = getParaValues("type");
		String[] e = getParaValues("id");
		if(service.saveOrUpdate(a,b,c,d,e)>0){
			setAttr("aa", "SUCCESS");
		}else{
			setAttr("aa", "FAIL");
		}
		renderJson();
	}
	/**
	 * 查询所有角色
	 */
	public void findRole(){
		
		renderJson(service.findRole());
	}
	/**
	 * 新增或者修改角色权限
	 */
	public void jurisdiction(){
		String type = getPara("type");
		String id = getPara("id");
		setAttr("aa", type+";"+id);
		render("jurisdictionAdd.html");
	}
	/**
	 * 查询角色拥有的权限
	 */
	public void findJurisdictionByRole(){
		String roleId = getPara("roleId");
		List<Record> list = service.findJurisdictionByRole(roleId);
		renderJson(list);
	}
	/**
	 * 新增或者修改角色的权限
	 */
	public void saveJurisdiction(){
		String userId = getPara("roleId");
		String url = getPara("url");
		if(service.saveJurisdiction(userId,url)>0){
			setAttr("aa", "SUCCESS");
		}else{
			setAttr("aa", "ERROR");
		}
		renderJson();
	}
	/**
	 * 根据角色ID删除角色
	 */
	public void delRoleById(){
		String id = getPara("roleId");
		Boolean b = service.delRoleById(id);
		if(b){
			setAttr("aa", "SUCCESS");
		}else{
			setAttr("aa", "ERROR");
		}
		renderJson();
	}
	public void index(){
		renderText("sb2");
		
	}
	/**
	 * 员工管理
	 */
	public void staffManager(){
		
		render("staffManagement1.html");
	}
	/**
	 * 修改员工信息
	 */
	public void updateStaff(){
		
		render("staffUpdate.html");
	}
	/**
	 * 新增员工
	 */
	public void staffAdd(){
		render("staffAdd.html");
	}
	/**
	 * 查找员工信息
	 */
	public void findStaff(){
		
		List<Record> r = service.findStaff(getPara("userName")).getList();
		renderJson(r);
	}
	/**
	 * 权限设置
	 */
	public void jurisdictionAdd(){
		
		render("staffManagement2.html");
	}
	
	/**
	 * 充值金额权限设置
	 */
	public void recAmountSettings(){
		
		
		render("systemSettings1.html");
	}
	/**
	 * 提现金额权限设置
	 */
	public void withAmountSettings(){
		if(!getPara("atr","").equals("")){
			String[] financeDepartmen=getParaValues("financeDepartmen");
			String[] financeManager=getParaValues("financeManager");
			String[] financeVicePresident=getParaValues("financeVicePresident");
			String[] chairman=getParaValues("chairman");
			String stus=service.withAmountSettingsUpdate(financeDepartmen,financeManager,financeVicePresident,chairman);
			setAttr("stus",stus);
			renderJson();
		}else{
			List<Record> list=service.withAmountSettings();
			setAttr("withAmount",list);
			render("systemSettings2.html");
		}
	}
	
	/**
	 * 每日交易报送设置
	 * 
	 */
	public void daylyRepSettings(){
		findSysPush();
		render("systemSettings3.html");
	}
	
	/**
	 * 异常提现条件设置
	 */
	public void withRulesSettings(){
		if(!getPara("atr","").equals("")){
			Record record=new Record();
			record.set("exceptionOne",getPara("exceptionOne","0"));
			record.set("exceptionTow",getPara("exceptionTow","0"));
			record.set("exceptionThere",getPara("exceptionThere","0"));
			record.set("exceptionFour",getPara("exceptionFour","0"));
			record.set("exceptionFive",getPara("exceptionFive","0"));
			String stus=service.withRulesSettingsUpdate(record);
			setAttr("stus",stus);
			renderJson();
		}else{
			List<Record> list=service.withRulesSettings();
			setAttr("withRules",list.get(0));
			render("systemSettings4.html");
		}
	}
	
	
	public void daylyRepHistory(){
		
		render("daylyRepHisorty.html");
	}
	
	
	public void actLogs(){
		
		
		
		render("actLogs.html");
	}
	/**
	 * 查询员工信息
	 */
	public void findStaffInfo(){
		Record user = getSessionAttr("user");
		int pageNum = getParaToInt("pageNum");
		Page<Record> page = service.findStaffInfo(pageNum,user.get("T_CONFIG_ID").toString());
		renderJson(page);
	}
	/**
	 * 新增或者修改用户信息
	 */
	public void saveAndUpdateStaff(){
		
		UserParams u = getBean(UserParams.class,"");
		if(u.getId() != null){
			//修改
			if(service.updateStaff(u)>0){
				setAttr("aa", "USUCCESS");
			}else if(service.updateStaff(u) == 0){
				setAttr("aa", "EXIT");
			}else{
				setAttr("aa", "UFAIL");
			}
		}else{
			//保存
			if(service.saveStaff(u)>0){
				setAttr("aa", "SUCCESS");
			}else{
				setAttr("aa", "EXIT");
			}
		}
		renderJson();
	}
	/**
	 * 删除员工
	 */
	public void delStaff(){
		
		String name = getPara("userName");
		
		if(service.delStaff(name)>0){
			setAttr("aa", "SUCCESS");
		}else{
			setAttr("aa", "FAIL");
		}
		renderJson();
	}
	/**
	 * 重置密码
	 */
	public void resetPass(){
		
		String userName = getPara("userName");
		String oldPass = getPara("oldPass");
		String newPass = getPara("newPass");
		int xx = service.resetPass(userName,oldPass,newPass);
		if(xx == 0){
			setAttr("aa", "FAIL");
		}else if(xx == 1){
			setAttr("aa", "SUCCESS");
		}else if(xx == 2){
			setAttr("aa", "ERROR");
		}
		renderJson();
	}
	
	/**
	 * 报送记录
	 */
	public void submitRecord(){
		getReportUser();
		String userName=getPara("userName","");
		String selectTime=getPara("startTime","");
		Page<Record> list=service.submitRecord(Integer.parseInt(getPara("page","1")),20,userName,selectTime);
		list=orderSum(list);
		Integer listCount=service.submitRecordCount(userName,selectTime);
		pageDo pagedo=this.storage(listCount);
		setAttr("refoundList",list);
		setAttr("pagedo", pagedo);
		
		if(getPara("atr","").equals("")){
			getReportUser();
			render("submitRecord.html");
		}else{
			renderJson();
		}
	}
	
	/**
	 * 获得每日汇报发送人
	 */
	public void getReportUser(){
		setAttr("ReportUserList", service.getReportUserselect());
	}
	
	/**
	 * 操作日志
	 */
	public void systemLogRecord(){
		String timeSelect=getPara("timeSelect","");
		Page<Record> list=service.systemLogRecord(Integer.parseInt(getPara("page","1")),20,timeSelect);
		list=orderSum(list);
		Integer listCount=service.systemLogRecordCount(timeSelect);
		pageDo pagedo=this.storage(listCount);
		setAttr("refoundList",list);
		setAttr("pagedo", pagedo);
		
		if(getPara("atr","").equals("")){
			render("systemLog.html");
		}else{
			renderJson();
		}
	}
	
	/**
	 * 重置密码
	 */
	public void allResetPass(){
		String userName = getPara("userName");
		String oldPass = getPara("oldPass");
		String newPass = getPara("newPass");
		int xx = service.resetPass(userName,oldPass,newPass);
		if(xx == 0){
			setAttr("updateType", "FAIL");
		}else if(xx == 1){
			removeSessionAttr("user");			
			setAttr("updateType", "SUCCESS");
		}else if(xx == 2){
			setAttr("updateType", "ERROR");
		}
		renderJson();
	}
	
	/*获取编号*/
	public Page<Record> orderSum(Page<Record> list){
		int i=1;
		for(Record  x : list.getList()){
			x.set("sumId",i);
			i++;
		}
		
		return list;
	}
	
	/*参数存储*/
	public pageDo storage(Integer listCount){
		pageDo pagedo=new pageDo();
		pagedo.setPageNumber(Integer.parseInt(getPara("page","1")));
		pagedo.setAllPageNumber(listCount);
		pagedo.setNumberPages((listCount%20==0?listCount/20:listCount/20+1));
		return pagedo;
	}
}
