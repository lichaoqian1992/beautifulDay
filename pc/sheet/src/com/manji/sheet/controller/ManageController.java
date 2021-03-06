package com.manji.sheet.controller;


import java.io.File;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.intercepter.LocalIntercepter;
import com.manji.sheet.model.reqParam.InsertOrder;
import com.manji.sheet.model.reqParam.WorkOrderParam;
import com.manji.sheet.service.ManageService;
import com.manji.sheet.service.PCService;
import com.manji.sheet.utils.ExcelUtils;
import com.manji.sheet.utils.WorkerNumberUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by Administrator on 2017/6/17.
 */
@Before(LocalIntercepter.class)
public class ManageController extends Controller {
  

    private ManageService manageService = new ManageService();
    private PCService service = new PCService();
    private WorkerNumberUtil WorkerUtil;
    private int pageSize=20;

    public void listAdd(){
    	String code=getPara("code");
    	setAttr("code", WorkerUtil.getcodeReport(code));
    	if(code.equals("04")){
    		setAttr("title","咨询工单-新增");
    		render("sheetAdd/workorder_add.html");
    	}else if(code.equals("05")){
    		setAttr("title","建议工单-新增");
    		render("sheetAdd/workorder_add2.html");
    	}else{
    		setAttr("title","举报满集员工-新增");
    		render("sheetAdd/workorder_add3.html");
    	}
    }
    
    /**
     * 受理页面
     */
    public void list() {
        if(!getPara("ajax","").equals("")){
        	JSONObject user = getSessionAttr("user");
            WorkOrderParam workOrder=new WorkOrderParam();
            workOrder=Verification(workOrder);
            workOrder.setUserDeptId(user.get("dept_id").toString());
            Page<Record> managePage=manageService.Acceptance(Integer.parseInt(getPara("page","1").toString()),pageSize,workOrder);
            managePage=orderSum(managePage);
            renderJson(managePage);
        }else{
            Map<String, Object> map=manageService.fromType();
            if(!getPara("status","").equals("")){
                setAttr("status",getPara("status"));
            }
            JSONObject user = getSessionAttr("user");
        	List<Record> exeDeptList = manageService.newSheet(Integer.parseInt(user.get("dept_id").toString()));
        	
        	String title="";
        	if(getPara("nav","").equals("nav1")){
        		title="工单受理";
            }else if(getPara("nav","").equals("nav2")){
        		title="工单处理";
            }else if(getPara("nav","").equals("nav3")){
        		title="结果推送";
            }else if(getPara("nav","").equals("nav4")){
        		title="工单历史";
            }else if(getPara("nav","").equals("nav5")){
        		title="超时工单";
            }
        	
        	
        	setAttr("nav",getPara("nav",""));
        	setAttr("deptId",user.get("dept_id").toString());
        	setAttr("exeDeptList",exeDeptList);
            setAttr("fromList",(List<Record>) map.get("fromList"));
            setAttr("typeList",(List<Record>) map.get("typeList"));
            setAttr("title",title);
            render("accept_list.html");
        }
    }
    /**
     *工单详情界面
     */
    public void acceptDetail() {
        if(!getPara("ajax","").equals("")){
            List<Record> RefundShopList= manageService.SelectRefundShop(Integer.parseInt(getPara("id")));
            renderJson(RefundShopList);
        }else{
            int id=Integer.parseInt(getPara("id",""));
            Map<String, Object> map=manageService.SelectWorkNumber(id);
            Record WorkNumber=(Record) map.get("WorkNumber");
            Record WorkInfoGoods=(Record) map.get("WorkInfoGoods");
            List<Record> WorkInfo=(List<Record>) map.get("WorkInfo");
            //检查部门查询
            List<Record> dept=manageService.dept();
            List<Record> RefundList=null;
            if(!WorkNumber.get("OneCode").toString().equals("07")){
            	if(!WorkNumber.get("order_id").equals("") && WorkNumber.get("order_id")!=null && Integer.parseInt(WorkNumber.get("order_id").toString())!=0){
                    if(manageService.detilOrderCount(Integer.parseInt(WorkNumber.get("order_id").toString()))>0){
                        //退单详情
                        Map<String, Object> detilMapInfo=manageService.SelectRefund((Record) map.get("WorkNumber"));
                        RefundList=(List<Record>)detilMapInfo.get("RefundList");
                    }
                    //交易纠纷订单信息
                    Map<String, Object> OrderMapInfo=manageService.SelectOrderInfo((Record) map.get("WorkNumber"));
                    setAttr("OrderHave","true");
                    setAttr("rc",(Record)OrderMapInfo.get("rc"));
                    setAttr("BuyersList",(List<Record>)OrderMapInfo.get("BuyersList"));
                    setAttr("logistics",(Record)OrderMapInfo.get("logistics"));
                    setAttr("ShipAddress",(Record)OrderMapInfo.get("ShipAddress"));
                    setAttr("BuyersInfo",(Record)OrderMapInfo.get("BuyersInfo"));
                    setAttr("remarkRecordAll",(Record)OrderMapInfo.get("remarkRecordAll"));
                }
           }else{
        	   Record evaluate=manageService.evaluateTop(WorkNumber.get("sheet_no").toString());
        	   setAttr("evaluate",evaluate);
        	   Record shopCommentScore = service.shopCommentScore(Integer.parseInt(evaluate.get("order_id").toString()));
   			   setAttr("shopCommentScore",shopCommentScore);
   			   //商品评论
	   		   List<Record> commentScore = manageService.comment(evaluate.get("order_id").toString());
                JSONArray jsonArr = JSONArray.fromObject(commentScore);
                System.out.println(jsonArr.toString());
	   		   setAttr("orderComment",manageService.orderComment(evaluate.get("order_id").toString()));
	   		   setAttr("commentScore",commentScore);
	   		   setAttr("isShow","true");
           }
            
            //商品查询
            Record articleRecord=manageService.getBusiness(id);
            List<Record> articleList=manageService.getGoodsInfo(articleRecord.get("order_id").toString(), articleRecord.get("article_id").toString());
            setAttr("articleList",articleList);
            setAttr("articleBusiness",articleRecord);
            
            //流程日志
            String status=manageService.sheetStatus(id).get("status").toString();
            if(status.equals("2") || status.equals("6") || status.equals("3")){
            	 Record processLog=manageService.processLog(status,id);
            	 setAttr("processLog",processLog);
        	}else if(status.equals("4") || status.equals("5")){
        		Record processLog=manageService.processLog(status,id);
           	 	setAttr("processLog",processLog);
           	 	List<Record> seHandle=manageService.selectHandle(id);
           	 	setAttr("seHandle",seHandle);
        	}
            
            //查询处理流程时间/查询处理人
            Map<String,Object> technologicalMap=manageService.technologicalProcess(id);
            Record flowlogRecord=(Record)technologicalMap.get("flowlog");
            Record timeRecord=(Record)technologicalMap.get("timeRecord");
            setAttr("flowlogRecord",flowlogRecord);
            setAttr("timeRecord",timeRecord);

            //商家保证金类型查询
            Record articleBond=manageService.getBusiness(id);
            List<Record> articleListBond=manageService.getBusinessUser(articleBond.get("shop_user_id").toString());
            
            if(getPara("nav","").equals("nav4")){
        		setAttr("disabled","disabled");
        		setAttr("disabledNo","disabledNo");
            }
            
            setAttr("articleListBond",articleListBond);
            
            setAttr("WorkInfoGoods",WorkInfoGoods);
            setAttr("dept",dept);
            setAttr("WorkNumber",WorkNumber);
            setAttr("WorkInfo",WorkInfo);
            setAttr("RefundList",RefundList);
            setAttr("status",status);
            setAttr("title","工单详情");
            render("workorder_Pending.html");
        }
    }

    /**
     * 新增工单
     */
    public void InsertWorkNumber(){
    	InsertOrder insertOrder=getBean(InsertOrder.class);
    	insertOrder.setPath(getPara("pics",""));
    	manageService.InsertWorkNumber(insertOrder);
    	redirect("/manage/list?status=acceptance&nav=nav1");
    }

    /**
     * 工单（受理/不受理）
     */
    public void acceptance(){
    	JSONObject user = getSessionAttr("user");
    	int tance=getParaToInt("tance",0);
    	int implement=getParaToInt("implement",0);
    	int supervision=getParaToInt("supervision",0);
    	int sheetId=getParaToInt("sheetId",0);
    	String explain=getPara("explain","");
    	
    	String IsAccept=manageService.IsAccept(tance, implement, supervision,sheetId,explain,user);
    	renderText(IsAccept);
    }
    
    /**
     *  工单处理
     */
    public void handle(){
    	String path=getPara("pics","");
    	JSONObject user = getSessionAttr("user");
    	int sheetId=getParaToInt("sheetId",0);
     	String explain=getPara("explain","");
     	if(getPara("Ishandle","").equals("true")){
     		if(getPara("aClasscode","").equals("true")){
        		String reputation=getPara("reputation","");
            	String bond=getPara("bond","");
            	String compensate=getPara("compensate","");
            	String closedShop=getPara("closedShop","");
            	String frozen=getPara("frozen","");
            	String shopId=getPara("shopId","");
       	
            	manageService.handle(path,reputation,bond,compensate,closedShop,frozen,shopId,explain,user,sheetId);
        	}else if(getPara("aClasscode","").equals("false")){
        		String evaluate=getPara("evaluate","");
        		String pingjia=getPara("pingjia","");
        		manageService.handlealso(path,evaluate,explain,user,sheetId,pingjia);
        	}
     	}else if(getPara("Ishandle","").equals("punishFalse")){
     		String evaluate="false";
    		String pingjia="punishFalse";
    		manageService.handlealso(path,evaluate,explain,user,sheetId,pingjia);
     	}else{
            String evaluate="false";
            String pingjia=getPara("pingjia","");
            manageService.handlealso(path,evaluate,explain,user,sheetId,pingjia);
        }
    	
    	redirect("/manage/list?status=already&nav=nav2");
    }
    
    /**
     * 执行仲裁
     */
    public void arbitration(){
    	JSONObject user = getSessionAttr("user");
    	int sheet_id=Integer.parseInt(getPara("sheet_id"));
    	String stu=manageService.arbitration(sheet_id,user);
    	renderJson(stu);
    }
    
    /**
     * 查询是否仲裁
     */
    public void IsArbitration(){
    	int sheet_id=Integer.parseInt(getPara("sheet_id"));
    	String stu=manageService.IsArbitration(sheet_id);
    	renderJson(stu);
    }
    
    /**
     * 推送
     */
    public void Push(){
    	JSONObject user = getSessionAttr("user");
    	int sheet_id=Integer.parseInt(getPara("sheet_id"));
    	String stu=manageService.Push(sheet_id,user);
    	renderJson(stu);
    }
    
    /**
     * 改变日志阅读状态
     */
    public void flowState(){
    	int sheetId=Integer.parseInt(getPara("sheetId"));
    	int status=Integer.parseInt(getPara("status"));
    	manageService.flowState(sheetId,status);
    	renderJson();
    }
    
    
    public WorkOrderParam Verification(WorkOrderParam WorkOrder){
        WorkOrderParam workOrder=new WorkOrderParam();
        workOrder.setStatus(getPara("status",""));
        workOrder.setSearch(getPara("search",""));
        workOrder.setStartTime(getPara("startTime",""));
        workOrder.setEndTime(getPara("endTime",""));
        workOrder.setSourceCode(getPara("sourceCode",""));
        workOrder.setTypeCode(getPara("typeCode",""));
        return workOrder;
    }

    public Page<Record> orderSum(Page<Record> list){
        int i=1;
        for(Record  x : list.getList()){
            x.set("sumId",i);
            i++;
        }
        return list;
    }
    
    /**
     * 导出toExcel
     */
    public void toExcel(){
    	ExcelUtils e = new ExcelUtils();
		String path ="";
		JSONObject user = getSessionAttr("user");
        WorkOrderParam workOrder=new WorkOrderParam();
        workOrder=Verification(workOrder);
        workOrder.setUserDeptId(user.get("dept_id").toString());
		path=e.toExcelList(workOrder);
    	renderFile(new File(path));
    }

}
