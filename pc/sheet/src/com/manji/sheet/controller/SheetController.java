package com.manji.sheet.controller;

import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.model.common.SheetResult;
import com.manji.sheet.model.reqParam.SheetParam;
import com.manji.sheet.model.reqParam.WorkOrderParam;
import com.manji.sheet.service.ManageService;
import com.manji.sheet.service.SheetService;
import com.manji.sheet.utils.WorkerNumberUtil;

/**
 * 本地登陆操作
 * 
 * @author Administrator
 *
 */


public class SheetController extends Controller {

	private SheetService service = new SheetService();
	private ManageService manageService =new ManageService();
	private WorkerNumberUtil WorkerUtil;
	private int pageSize=20;

	public void index() {
		
	}


	/**
	 * 受理页面
	 */
	public void list() {
		if(!getPara("ajax","").equals("")){
			WorkOrderParam workOrder=new WorkOrderParam();
			workOrder=Verification(workOrder);
			Page<Record> managePage=manageService.Acceptance(Integer.parseInt(getPara("page","1").toString()),pageSize,workOrder);
			managePage=orderSum(managePage);
			renderJson(managePage);
		}else{
			Map<String, Object> map=manageService.fromType();
			if(!getPara("status","").equals("")){
				setAttr("status",getPara("status"));
			}
			setAttr("fromList",(List<Record>) map.get("fromList"));
			setAttr("typeList",(List<Record>) map.get("typeList"));
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
			List<Record> WorkInfo=(List<Record>) map.get("WorkInfo");
			List<Record> RefundList=null;
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

			}
			setAttr("WorkNumber",WorkNumber);
			setAttr("WorkInfo",WorkInfo);
			setAttr("RefundList",RefundList);
			render("workorder_Pending.html");
		}
	}

	public void accQuery() {

		SheetParam param = getBean(SheetParam.class, "");

		Page<Record> page = service.getSheetPage(param);

		SheetResult result = new SheetResult();

		if (page.getTotalRow() > 0) {

			result.setCode("0000");
			result.setMessage("成功");
			result.setResult(page);
		}

	}

	/**
	 * 处理页面
	 */
	public void dealt() {

		render("dealt.html");
	}

	public void deaQuery() {

		SheetParam param = getBean(SheetParam.class, "");

		Page<Record> page = service.getSheetPage(param);

		SheetResult result = new SheetResult();

		if (page.getTotalRow() > 0) {

			result.setCode("0000");
			result.setMessage("成功");
			result.setResult(page);
		}

	}

	/**
	 * 推送
	 */
	public void push() {

		render("push.html");
	}

	public void pusQuery() {
		SheetParam param = getBean(SheetParam.class, "");

		Page<Record> page = service.getSheetPage(param);

		SheetResult result = new SheetResult();

		if (page.getTotalRow() > 0) {

			result.setCode("0000");
			result.setMessage("成功");
			result.setResult(page);
		}

	}

	/**
	 * 工单历史
	 */
	public void histoty() {

		render("history.html");
	}

	public void hisQuery() {
		SheetParam param = getBean(SheetParam.class, "");

		Page<Record> page = service.getSheetPage(param);

		SheetResult result = new SheetResult();

		if (page.getTotalRow() > 0) {

			result.setCode("0000");
			result.setMessage("成功");
			result.setResult(page);
		}

	}

	/**
	 * 詳情頁面
	 */
	public void detail() {

		//检查参数sheet_id
		String sheet_id_str =getPara("id");
		long sheet_id =0;
		if(null ==sheet_id_str||"".equals(sheet_id_str)){
			
			render("sb");
			
		}else{
			
			sheet_id =Long.valueOf(sheet_id_str);
		}
		
		
		//工单主表信息
		Record sheetRec =service.getSheetById(sheet_id);
		
		String sheet_status =sheetRec.get("status");
		
		setAttr("sheet", sheetRec);
		//工单资料信息
		List<Record> sheetInfos =service.getSheetInfos(sheet_id);
		
		setAttr("sheetInfo", sheetInfos);
		//工单业务信息
		Record sheetBusyinfo =service.getSheetBusyinfo(sheet_id);
		
		
		//订单信息
		
		
		//退单信息
		
		
		switch(sheet_status){
		case "1":
			
			break;
		case "2":
			
			break;
			
		case "3":
			
			break;
		case "4":
			
			break;
		
		default :
			render("detail.html");
		}
		
		
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

}
