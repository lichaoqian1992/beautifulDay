package com.manji.backstage.controller.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.account.Account;
import com.manji.backstage.model.account.Recharge;
import com.manji.backstage.model.account.Transaction;
import com.manji.backstage.model.account.Withdrawals;
import com.manji.backstage.model.account.YiJiAccount;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.model.operation.Files;
import com.manji.backstage.service.account.AccountService;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.account.AccountVo;
import com.manji.backstage.vo.account.RechargeVo;
import com.manji.backstage.vo.account.TransactionVo;
import com.manji.backstage.vo.account.WithdrawalsVo;
import com.manji.backstage.vo.account.YiJiAccountVo;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.message.NoticeVo;
@Controller
public class AccountController extends BaseController{
	@Autowired
	private AccountService service;
	@Autowired
	private LoggersService logService;
	
	
	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("account");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	
	
	
	
	@RequestMapping(value ="/AccountCounts")
	
	public String countAccountGroup(HttpServletRequest req){
		List<Account> list= service.countAccountGroup();
		
		
		req.setAttribute("accountpielist",Array(list));
		return "account/account_counts_list"; 
	}
	
	
	
	@RequestMapping("/selAccount")
	public String selAccount(HttpServletRequest req){
		
	
		
		req.setAttribute("accountstate", "1");
		return "account/account_normal_list";
	}
	
	@RequestMapping("/selEAccount")
	public String selEAccount(HttpServletRequest req){
		
		req.setAttribute("accountstate", "9");
		return "account/account_exception_list";
	}
	
	@RequestMapping("/selNAccount")
	public String selNAccount(HttpServletRequest req){
		
		req.setAttribute("accountstate", "0");
		return "account/account_no_list";
	}
	
	@RequestMapping(value ="/queryAccount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAccount(HttpServletRequest req,AccountVo vo){
		
		Message msg =createMsg();
		Json(vo);
		Page<Account> page=service.queryAccount(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
			
		}
		
		return Json(msg);
	}
	
	@RequestMapping("/readAccount")
	public String readAccount(HttpServletRequest req,Account account){
		
		Account act =service.getAccount(account.getId());
		
		req.setAttribute("accountinfo", Json(act));
		
		return "account/account_detail";
	}
	

	@RequestMapping(value ="/updAccount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAccount(HttpServletRequest req,Account account){
		Message msg =createMsg();
		
		Account act =service.getAccount(account.getId());
//		saveLog(createLog(req),"修改",Json(act),"");
		
		saveLog(createLog(req),LogRemark.UPD,Json(act),LogRemark.UPDACCOUNT);
		if(service.updAccount(account)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		
		
		return Json(msg);
	}
	
	@RequestMapping("/selRecharge")
	public String selRecharge(HttpServletRequest req){
		
		
		return "account/account_recharge_list";
	}
	
	@RequestMapping(value ="/queryRecharge", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRecharge(HttpServletRequest req,RechargeVo vo){
		Message msg =createMsg();
		Json(vo);
		Page<Recharge> page =service.queryRecharge(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
			
		}
		
		return Json(msg);
	}
	
	@RequestMapping(value ="/updRecharge", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRecharge(HttpServletRequest req ,Recharge rec){
		
		Message msg =createMsg();
		
		Recharge recs =service.getRecharge(rec.getId());
		
		saveLog(createLog(req),LogRemark.UPD,Json(recs),LogRemark.UPDRECHARGE);
		
		if(service.updRecharge(rec)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	@RequestMapping("/selTransaction")
	public String selTransaction(HttpServletRequest req){
		
		return "account/account_transaction_list";
	}
	
	@RequestMapping(value ="/queryTransaction", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryTransaction(HttpServletRequest req,TransactionVo vo){
		Message msg =createMsg();
		
		Page<Transaction> page =service.queryTransaction(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
			
		}
		
		return Json(msg);
	}
	
	@RequestMapping(value ="/updTransaction", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updTransaction(HttpServletRequest req,Transaction tran){
		Message msg =createMsg();
		
		Transaction tra =service.getTransaction(tran.getId());
		
		saveLog(createLog(req),LogRemark.UPD,Json(tra),LogRemark.UPDTRANSACTION);
		
		if(service.updTransaction(tran)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	@RequestMapping("/selWithdrawals")
	public String selWithdrawals(HttpServletRequest req){
		
		
		return "account/account_withdrawals_list";
	}
	
	@RequestMapping(value ="/queryWithdrawals", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryWithdrawals(HttpServletRequest req,WithdrawalsVo vo){
		Message msg =createMsg();
		
		Page<Withdrawals> page =service.queryWithdrawals(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
			
		}
		return Json(msg);
	}
	
	@RequestMapping("/readRecharge")
	public String readRecharge(HttpServletRequest req,int id){
		
		Recharge rec =service.getRecharge(id);
		req.setAttribute("rechargeinfo", Json(rec));
		
		return "account/account_recharge_upd";
	}
	@RequestMapping("/readTransaction")
	public String readTransaction(HttpServletRequest req,int id){
		Transaction tra =service.getTransaction(id);
		req.setAttribute("transactioninfo", Json(tra));
		return "account/account_transaction_upd";
	}
	@RequestMapping("/readWithDrawals")
	public String readWithDrawals(HttpServletRequest req, int id){
		Withdrawals wit =service.getWithdrawals(id);
		req.setAttribute("withdrawalsinfo", Json(wit));
		return "account/account_withdrawals_upd";
	}
	
	
	
	@RequestMapping("/selYiJiAccount")
	public String selaccountYiJiAccount(HttpServletRequest req){
		
		return "account/account_yijiaccount_list";
	}
	
	
	@RequestMapping(value ="/queryYiJiAccount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryYiJiAccount(HttpServletRequest req,YiJiAccountVo vo){
		
		Message msg =createMsg();
		
		Page<YiJiAccount> page =service.queryYiJiAccount(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insYiJiAccount")
	public String insYiJiAccount(HttpServletRequest req){
		
		return "account/account_yijiaccount_add";
	}
	@RequestMapping(value ="/addYiJiAccount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addYiJiAccount(HttpServletRequest req,YiJiAccount YiJiAccount){
		Message msg =createMsg();
		YiJiAccount.setAdd_time(TimeUtils.getCurrentTime());
		service.addYiJiAccount(YiJiAccount);
		msg.setStatus("0");
		
		return Json(msg);
	}
	@RequestMapping("/readYiJiAccount")
	public String readYiJiAccount(HttpServletRequest req,int id){
		YiJiAccount yijiaccount =service.getYiJiAccount(id);
		req.setAttribute("yjainfo", Json(yijiaccount));
		return "account/account_yijiaccount_upd";
	}
	@RequestMapping(value ="/updYiJiAccount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updYiJiAccount(HttpServletRequest req,YiJiAccount yja){
		Message msg =createMsg();
		if(service.updYiJiAccount(yja)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/delYiJiAccount")
	public String delYiJiAccount(HttpServletRequest req ,int id){
		Message msg =createMsg();
		if(service.delYiJiAccount(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}

		return "redirect:/selYiJiAccount";
	}
	
	
}
