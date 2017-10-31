package com.life.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.life.model.Result;
import com.life.service.ExcelService;
import com.life.service.SaturationService;
import com.life.service.TaskService;
import com.life.utils.DateUtils;
import com.life.utils.PicUtils;
import com.life.utils.zipPicsUtils;

public class ExcelController extends Controller{

	private ExcelService service = new ExcelService();
	
	private TaskService taskService = new TaskService();
	
	private SaturationService saturationService = new SaturationService();
	
	public void index(){
		//查询分类
		List<Record> list = service.findLang();
		setAttr("lang", list);
		String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		new DateUtils();
		String monday = DateUtils.getCurrentMonday();
		setAttr("d", d);
		setAttr("m", monday);
		
		render("excel.html");
	}
	public void excel2(){
		//查询分类
		List<Record> list = service.findLang();
		setAttr("lang", list);
		
		String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		new DateUtils();
		String monday = DateUtils.getYestoday();
		setAttr("d", d);
		List<Record> project = Db.find("select p.id,p.name from zt_project p where p.id in(select DISTINCT t.project from zt_task t inner join zt_action a on a.objectID = t.id AND date(a.date) = date('"+d+"'))");
		setAttr("project", project);
		setAttr("m", monday);
		
		render("excel2.html");
	}
	/**
	 * 查询数据
	 */
	public void query(){
		
		String langL = getPara("langL");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		String typeL = getPara("typeL");
		String columnL = getPara("columnL");
		
		List<Record> list = service.query(langL,startTime,endTime,typeL,columnL);
		
		renderJson(list);
		
	}
	
	public void query2(){
		
		String langL = getPara("langL");
		String startTime = getPara("startTime");
		String project = getPara("project");
		List<Result> list = service.queryByTime(langL,startTime,"查询",project);
		
		renderJson(list);
	}
	/**
	 * 导出excel
	 */
	public void toExcel(){

		String langL = getPara("langL");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		String typeL = getPara("typeL");
		String columnL = getPara("columnL");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("langL", langL);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("typeL", typeL);
		map.put("columnL", columnL);
		
		String path = service.toExcel(map);
		
		renderFile(new File(path));
		
		
	}
	/**
	 * 导出excel
	 * @throws ParseException 
	 */
	public void toExcel2() throws ParseException{
		
		String d = getPara("time");
		String langL = getPara("langL");
		String project = getPara("project");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("startTime", d);
		map.put("endTime", d);
		map.put("langL", langL);
		map.put("project", project);
		
		String path = service.toExcel2(map);
		
		renderFile(new File(path));
	}
	/**
	 * 查询需求信息
	 */
	public void excel3(){
		
		List<Record> list = service.findProduct();
		
		setAttr("backData", list);
		
		render("excel3.html");
		
	}
	/**
	 * 查询工作量
	 */
	public void toExcel4(){
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String type = getPara("name");
		
		//List<Record> list = new ArrayList<Record>();
		
		List<Record> list = service.findFinished(stime , etime+" 23:59:59" , type);
		
		
		
		setAttr("list", list);
		renderJson();
	}
	
	/**
	 * 查询接收、完成、在手上的任务量/BUG量
	 */
	public void excel4(){
		
		new DateUtils();
		//查询分类
		List<Record> list = service.findLang();
		setAttr("lang", list);
		String stime = DateUtils.getCurrentMonday();//当前时间
		String etime = DateUtils.getToday();//本周一
		
		setAttr("s", stime);
		setAttr("e", etime);
		
		render("excel4.html");
	}
	
	public void toExcel44(){
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String groupType = getPara("type");
		//1.获取任务接收量
		List<Record> accept_task = service.getTaskOrBug(stime , etime+" 23:59:59" ,"accept_task",groupType);
		//2.获取任务完成量
		List<Record> finished_task = service.getTaskOrBug(stime , etime+" 23:59:59" , "finished_task",groupType);
		//3.获取任务当前量（与时间无关）
		List<Record> doing_task = service.getTaskOrBug(stime , etime+" 23:59:59" , "doing_task",groupType);
		//4.获取BUG接收量
		List<Record> accept_bug = service.getTaskOrBug(stime , etime+" 23:59:59" , "accept_bug",groupType);
		//5.获取BUG完成量
		List<Record> finished_bug = service.getTaskOrBug(stime , etime+" 23:59:59" , "finished_bug",groupType);
		//6.获取BUG当前量（与时间无关）
		List<Record> doing_bug = service.getTaskOrBug(stime , etime+" 23:59:59" , "doing_bug",groupType);
		
		setAttr("accept_task", accept_task);
		setAttr("finished_task", finished_task);
		setAttr("doing_task", doing_task);
		setAttr("accept_bug", accept_bug);
		setAttr("finished_bug", finished_bug);
		setAttr("doing_bug", doing_bug);
		
		renderJson();
	}
	/**
	 * 出错率
	 */
	public void errorRate(){
		
		new DateUtils();
		
		String stime = DateUtils.getCurrentMonday();//当前时间
		String etime = DateUtils.getToday();//本周一
		
		setAttr("s", stime);
		setAttr("e", etime);
		render("errorRate.html");
	}
	/**
	 * 查询BUG出错率
	 */
	public void queryError(){
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String type = getPara("type");
		System.out.println("======开始执行queryError方法，参数："+stime+","+etime+"===============");
		
		List<Record> list = service.findErrorBug(stime, etime, type);
		
		setAttr("list", list);
		renderJson();
		
	}
	/**
	 * 饱和度
	 */
	public void saturation(){
		
		//查询分类
		List<Record> list = service.findLang();
		setAttr("lang", list);
		new DateUtils();
		
		String stime = DateUtils.getCurrentMonday();//当前时间
		String etime = DateUtils.getToday();//本周一
		
		setAttr("s", stime);
		setAttr("e", etime);
		
		render("saturation.html");
	}
	/**
	 * 饱和度
	 * @throws ParseException
	 */
	public void findSaturation() throws ParseException{
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String type = getPara("type");
		setAttr("list", service.findSaturation(stime+" 00:00:00", etime + " 23:59:59", type));
		renderJson();
		
	}
	//#####################################饱和度#############################
	/**
	 * 饱和度
	 */
	public void saturation1(){
		
		//查询分类
		List<Record> list = service.findLang();
		setAttr("lang", list);
		new DateUtils();
		
		String stime = DateUtils.getCurrentMonday();//当前时间
		String etime = DateUtils.getToday();//本周一
		
		setAttr("s", stime);
		setAttr("e", etime);
		
		render("saturation1.html");
	}
	/**
	 * 饱和度
	 * @throws ParseException
	 */
	public void findSaturation1() throws ParseException{
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String type = getPara("type");
		setAttr("list", saturationService.findSaturation(stime+" 00:00:00", etime + " 23:59:59", type));
		renderJson();
		
	}
	public void task(){
		
		new DateUtils();
		
		String stime = DateUtils.getCurrentMonday();//当前时间
		String etime = DateUtils.getToday();//本周一
		
		setAttr("s", stime);
		setAttr("e", etime);
		//查询分类
		List<Record> list = service.findLang();
		setAttr("lang", list);
		render("task.html");
	}
	/**
	 * 任务量
	 * @throws ParseException
	 */
	public void findTask() throws ParseException{
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String type = getPara("type");
		
		System.out.println("type:------ " + type);
		
		setAttr("list", service.findTask(stime, etime, type));
		renderJson();
		
	}
	//#############################任务量start#############################
	public void task1(){
		new DateUtils();
		String stime = DateUtils.getCurrentMonday();//当前时间
		String etime = DateUtils.getToday();//本周一
		
		setAttr("s", stime);
		setAttr("e", etime);
		//查询分类
		List<Record> list = service.findLang();
		setAttr("lang", list);
		render("task1.html");
	}
	/**
	 * 任务量 ---分组
	 * @throws ParseException
	 */
	public void findTask1() throws ParseException{
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String type = getPara("type");
		
		System.out.println("type:------ " + type);
		//task量
		setAttr("list", taskService.findTaskGroupZhu_number(stime + " 00:00:00", etime + " 23:59:59", type));
		//task工时
		setAttr("listTime", taskService.findTaskGroupZhu_Time(stime + " 00:00:00", etime + " 23:59:59", type));
		render("taskTable_group.html");
		
	}
	/**
	 * 任务量 ---需求
	 * @throws ParseException
	 */
	public void findTask2() throws ParseException{
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String type = getPara("type");
		
		System.out.println("type:------ " + type);
		//工时
		setAttr("list", taskService.findTaskxuqiu_time(stime + " 00:00:00", etime + " 23:59:59", type));
		//task量
		setAttr("list_num", taskService.findTaskxuqiu_num(stime + " 00:00:00", etime + " 23:59:59", type));
		render("taskTable_xuqiu.html");
		
	}
	//#############################任务量end#############################
	/**
	 * 延迟量
	 */
	public void delay(){
		new DateUtils();
		
		String stime = DateUtils.getCurrentMonday();//当前时间
		String etime = DateUtils.getToday();//本周一
		
		setAttr("s", stime);
		setAttr("e", etime);
		//查询分类
		List<Record> list = service.findLang();
		setAttr("lang", list);
		render("delay.html");
	}
	public void findDelay(){
		
		String stime = getPara("stime");
		String etime = getPara("etime");
		String type = getPara("type");
		String group = getPara("group");
		
		setAttr("list", service.findDelay(stime, etime, type,group));
		renderJson();
	}
	/**
	 * 日报统计
	 */
	public void dailyCount(){
		
		new DateUtils();
		
		String stime = DateUtils.getToday();//当前时间
		
		setAttr("s", stime);
		setAttr("project", Db.find("select DISTINCT p.id,p.`name` from zt_task t inner join zt_project p on p.id=t.project"));
		
		render("dailycount.html");
		
	}
	
	public void findDailyCount(){
		
		String stime = getPara("stime");
		String project = getPara("project");
		Record result = service.findDailyCount(stime,project);
		
		setAttr("result", result);
		renderJson();
		
	}
	@SuppressWarnings("static-access")
	public void toFile() throws ClientProtocolException, IOException{
		
		/*//File file = getFile().getFile(); 
		
		File file = new File("C:/Users/Administrator/Desktop/111.jpg");
		
		ImgCompressUtils imgCom = new ImgCompressUtils(file);  
        String path = imgCom.resizeFix(2000, 2000);  
		File f = new File(path);
		new PicUtils();
		//String url = "http://servicetest.manji.com/AppService.asmx/UploadFile";
		String message = PicUtils.postPic(f);
		f.delete();
		//String message = new HttpClientUtils().postUrl(file,url);
		
		System.out.println(message);*/
		File originalFile = new File("C:/Users/Administrator/Desktop/111.jpg");
		File resizedFile = new File("C:/Users/Administrator/Desktop/456.jpg");
		
		new zipPicsUtils().resize(originalFile, resizedFile, 1, 0.5f);  
		new PicUtils();
		String message = PicUtils.postPic(resizedFile);
		resizedFile.delete();
		renderJson(message);
		
	}
	
}
