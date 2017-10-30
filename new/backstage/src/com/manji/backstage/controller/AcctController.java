package com.manji.backstage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.model.acct.Account;
import com.manji.backstage.model.acct.Recharge;
import com.manji.backstage.model.acct.ScoreLog;
import com.manji.backstage.model.acct.Transaction;
import com.manji.backstage.model.acct.UserBalanceDetail;
import com.manji.backstage.model.acct.UserBalanceLog;
import com.manji.backstage.model.acct.Withdrawals;
import com.manji.backstage.model.acct.YiJiAccount;
import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.AmountLog;
import com.manji.backstage.model.logger.CreditLog;
import com.manji.backstage.model.logger.PointLog;
import com.manji.backstage.model.logger.ReputationLog;
import com.manji.backstage.model.logger.VoucherLog;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.user.UserOauth;
import com.manji.backstage.service.AcctService;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.acct.AccountVo;
import com.manji.backstage.vo.acct.RechargeVo;
import com.manji.backstage.vo.acct.ScoreLogVo;
import com.manji.backstage.vo.acct.TransactionVo;
import com.manji.backstage.vo.acct.UserBalanceDetailVo;
import com.manji.backstage.vo.acct.UserBalanceLogVo;
import com.manji.backstage.vo.acct.WithdrawalsVo;
import com.manji.backstage.vo.acct.YiJiAccountVo;
import com.manji.backstage.vo.logger.AmountLogVo;
import com.manji.backstage.vo.logger.CreditLogVo;
import com.manji.backstage.vo.logger.PointLogVo;
import com.manji.backstage.vo.logger.ReputationLogVo;
import com.manji.backstage.vo.logger.VoucherLogVo;

@Controller
public class AcctController extends BaseController {
	@Autowired
	private AcctService service;
	@Autowired
	private LoggersService logService;

	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("acct");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}

	// dt_user_accountinfo 用户账户信息表

	@RequestMapping(value = "/AccountCounts")

	public String countAccountGroup(HttpServletRequest req) {
		List<Account> list = service.countAccountGroup();

		req.setAttribute("accountpielist", Array(list));
		return "account/account_counts_list";
	}

	@RequestMapping("/selAccount")
	public String selAccount(HttpServletRequest req) {

		req.setAttribute("accountstate", "1");
		return "account/account_normal_list";
	}

	@RequestMapping("/selEAccount")
	public String selEAccount(HttpServletRequest req) {

		req.setAttribute("accountstate", "9");
		return "account/account_exception_list";
	}

	@RequestMapping("/selNAccount")
	public String selNAccount(HttpServletRequest req) {

		return "account/account_no_list";
	}

	@RequestMapping(value = "/queryAccount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAccount(HttpServletRequest req, AccountVo vo) {

		Message msg = createMsg();
		Json(vo);
		Page<Account> page = service.queryAccount(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");

		}

		return Json(msg);
	}

	@RequestMapping("/readAccount")
	public String readAccount(HttpServletRequest req, Account account) {

		Account act = service.getAccount(account.getId());

		req.setAttribute("accountinfo", Json(act));

		return "account/account_detail";
	}

	@RequestMapping(value = "/updAccount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAccount(HttpServletRequest req, Account account) {
		Message msg = createMsg();

		Account act = service.getAccount(account.getId());
		// saveLog(createLog(req),"修改",Json(act),"");

		saveLog(createLog(req), LogRemark.UPD, Json(act), LogRemark.UPDACCOUNT);
		if (service.updAccount(account)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	// dt_user_recharge 用户充值记录信息表

	@RequestMapping("/selRecharge")
	public String selRecharge(HttpServletRequest req) {

		return "account/account_recharge_list";
	}

	@RequestMapping(value = "/queryRecharge", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRecharge(HttpServletRequest req, RechargeVo vo) {
		Message msg = createMsg();
		Json(vo);
		Page<Recharge> page = service.queryRecharge(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");

		}

		return Json(msg);
	}

	@RequestMapping(value = "/updRecharge", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRecharge(HttpServletRequest req, Recharge rec) {

		Message msg = createMsg();

		Recharge recs = service.getRecharge(rec.getId());

		saveLog(createLog(req), LogRemark.UPD, Json(recs), LogRemark.UPDRECHARGE);

		if (service.updRecharge(rec)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	// dt_user_transaction 用户转账记录信息表

	@RequestMapping("/selTransaction")
	public String selTransaction(HttpServletRequest req) {

		return "account/account_transaction_list";
	}

	@RequestMapping(value = "/queryTransaction", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryTransaction(HttpServletRequest req, TransactionVo vo) {
		Message msg = createMsg();

		Page<Transaction> page = service.queryTransaction(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");

		}

		return Json(msg);
	}

	@RequestMapping(value = "/updTransaction", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updTransaction(HttpServletRequest req, Transaction tran) {
		Message msg = createMsg();

		Transaction tra = service.getTransaction(tran.getId());

		saveLog(createLog(req), LogRemark.UPD, Json(tra), LogRemark.UPDTRANSACTION);

		if (service.updTransaction(tran)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	// dt_user_withdrawals 用户提现记录信息表

	@RequestMapping("/selWithdrawals")
	public String selWithdrawals(HttpServletRequest req) {

		return "account/account_withdrawals_list";
	}

	@RequestMapping(value = "/queryWithdrawals", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryWithdrawals(HttpServletRequest req, WithdrawalsVo vo) {
		Message msg = createMsg();

		Page<Withdrawals> page = service.queryWithdrawals(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");

		}
		return Json(msg);
	}

	@RequestMapping("/readRecharge")
	public String readRecharge(HttpServletRequest req, int id) {

		Recharge rec = service.getRecharge(id);
		req.setAttribute("rechargeinfo", Json(rec));

		return "account/account_recharge_upd";
	}

	@RequestMapping("/readTransaction")
	public String readTransaction(HttpServletRequest req, int id) {
		Transaction tra = service.getTransaction(id);
		req.setAttribute("transactioninfo", Json(tra));
		return "account/account_transaction_upd";
	}

	@RequestMapping("/readWithDrawals")
	public String readWithDrawals(HttpServletRequest req, int id) {
		Withdrawals wit = service.getWithdrawals(id);
		req.setAttribute("withdrawalsinfo", Json(wit));
		return "account/account_withdrawals_upd";
	}

	// dt_user_amount_log 用户余额日志表

	@RequestMapping("/selAmountLog")
	public String selAmountLog(HttpServletRequest req) {

		return "logger/logger_amount_list";
	}

	@RequestMapping(value = "/queryAmountLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAmountLog(HttpServletRequest req, AmountLogVo vo) {
		Message msg = createMsg();

		Page<AmountLog> page = service.queryAmountLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/readAmountLog")
	public String readAmountLog(HttpServletRequest req, int id) {
		AmountLog al = service.getAmountLog(id);
		req.setAttribute("amountloginfo", Json(al));
		return "logger/logger_amount_upd";
	}

	@RequestMapping("/delAmountLog")
	public String delAmountLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		AmountLog al = service.getAmountLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(al), LogRemark.DELAMOUNTLOG);
		if (service.delAmountLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selAmountLog";
	}

	@RequestMapping(value = "/updAmountLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAmountLog(HttpServletRequest req, AmountLog al) {
		Message msg = createMsg();
		AmountLog amount = service.getAmountLog(al.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(amount), LogRemark.UPDAMOUNTLOG);
		if (service.updAmountLog(al)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	// dt_user_voucher_log 用户虚拟全日志表

	@RequestMapping("/selVoucherLog")
	public String selVoucherLog(HttpServletRequest req) {

		return "logger/logger_voucher_list";
	}

	@RequestMapping(value = "/queryVoucherLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryVoucher(HttpServletRequest req, VoucherLogVo vo) {
		Message msg = createMsg();

		Page<VoucherLog> page = service.queryVoucherLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/readVoucherLog")
	public String readVoucherLog(HttpServletRequest req, int id) {
		VoucherLog vl = service.getVoucherLog(id);
		req.setAttribute("voucherloginfo", Json(vl));
		return "logger/logger_voucher_upd";

	}

	@RequestMapping("/delVoucherLog")
	public String delVoucherLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		VoucherLog vl = service.getVoucherLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(vl), LogRemark.DELVOUCHERLOG);
		if (service.delVoucherLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selVoucherLog";
	}

	@RequestMapping(value = "/updVoucherLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updVoucherLog(HttpServletRequest req, VoucherLog vl) {
		Message msg = createMsg();
		VoucherLog voucher = service.getVoucherLog(vl.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(voucher), LogRemark.UPDVOUCHERLOG);
		if (service.updVoucherLog(vl)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	// dt_user_point_log 用户积分日子表

	@RequestMapping("/selPointLog")
	public String selPointLog(HttpServletRequest req) {
		System.out.println("hello");

		// List<PointLog> list=service.pointLogList();
		//
		// req.setAttribute("pointloglist", Array(list));
		return "logger/logger_point_list";
	}

	/**
	 * 查询积分日志
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryPointLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPointLog(HttpServletRequest req, PointLogVo vo) {
		Message msg = createMsg();
		Page<PointLog> page = service.queryPointLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/readPointLog")
	public String readPointLog(HttpServletRequest req, long id) {
		PointLog pl = service.getPointLog(id);
		req.setAttribute("pointloginfo", Json(pl));
		return "logger/logger_point_upd";
	}

	@RequestMapping("/delPointLog")
	public String delPointLog(HttpServletRequest req, long id) {
		Message msg = createMsg();
		PointLog pl = service.getPointLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(pl), LogRemark.DELPOINTLOG);
		if (service.delPointLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "redirect :/selPointLog";
	}

	@RequestMapping(value = "/updPointLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPointLog(HttpServletRequest req, PointLog pl) {
		Message msg = createMsg();
		PointLog point = service.getPointLog(pl.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(point), LogRemark.UPDPOINTLOG);
		if (service.updPointLog(pl)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	// dt_user_credit_log 用户信用日志表

	@RequestMapping("/selCreditLog")
	public String selCreditLog(HttpServletRequest req) {

		return "logger/logger_credit_list";
	}

	@RequestMapping(value = "/queryCreditLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCreditLog(HttpServletRequest req, CreditLogVo vo) {
		Message msg = createMsg();

		Page<CreditLog> page = service.queryCreditLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/readCreditLog")
	public String readCreditLog(HttpServletRequest req, int id) {
		CreditLog cl = service.getCreditLog(id);
		req.setAttribute("creditloginfo", Json(cl));
		return "logger/logger_credit_upd";
	}

	@RequestMapping("/delCreditLog")
	public String delCreditLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		CreditLog cl = service.getCreditLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(cl), LogRemark.DELCREDITLOG);
		if (service.delCreditLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect :/selCreditLog";
	}

	@RequestMapping(value = "/updCreditLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updCreditLog(HttpServletRequest req, CreditLog cl) {
		Message msg = createMsg();
		CreditLog credit = service.getCreditLog(cl.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(credit), LogRemark.UPDCREDITLOG);
		if (service.updCreditLog(cl)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	// dt_user_reputation_log 用户信誉日志表

	@RequestMapping("/selReputationLog")
	public String selReputationLog(HttpServletRequest req) {

		return "logger/logger_reputation_list";
	}

	@RequestMapping(value = "/queryReputationLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryReputationLog(HttpServletRequest req, ReputationLogVo vo) {
		Message msg = createMsg();

		Page<ReputationLog> page = service.queryReputationLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/readReputationLog")
	public String readReputationLog(HttpServletRequest req, int id) {
		ReputationLog rl = service.getReputationLog(id);
		req.setAttribute("reputationofinfo", Json(rl));
		return "logger/logger_reputation_upd";

	}

	@RequestMapping(value = "/updReputationLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updReputationLog(HttpServletRequest req, ReputationLog rl) {
		Message msg = createMsg();
		ReputationLog agt = service.getReputationLog(rl.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(agt), LogRemark.UPDREPUTATIONLOG);

		if (service.updReputationLog(rl)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/delReputationLog")
	public String delReputationLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		ReputationLog rl = service.getReputationLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(rl), LogRemark.DELREPUTATIONLOG);
		if (service.delReputationLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "redirect:/selReputationLog";
	}

	// dt_user_yiji_account 以及付账户对应表

	@RequestMapping("/selYiJiAccount")
	public String selaccountYiJiAccount(HttpServletRequest req) {

		return "account/account_yijiaccount_list";
	}

	@RequestMapping(value = "/queryYiJiAccount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryYiJiAccount(HttpServletRequest req, YiJiAccountVo vo) {

		Message msg = createMsg();

		Page<YiJiAccount> page = service.queryYiJiAccount(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/insYiJiAccount")
	public String insYiJiAccount(HttpServletRequest req) {

		return "account/account_yijiaccount_add";
	}

	@RequestMapping(value = "/addYiJiAccount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addYiJiAccount(HttpServletRequest req, YiJiAccount yiji) {
		Message msg = createMsg();
		yiji.setAdd_time(TimeUtils.getCurrentTime());
		service.addYiJiAccount(yiji);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(yiji),LogRemark.ADDYIJIACCOUNT);
		return Json(msg);
	}

	@RequestMapping("/readYiJiAccount")
	public String readYiJiAccount(HttpServletRequest req, int id) {
		YiJiAccount yijiaccount = service.getYiJiAccount(id);
		req.setAttribute("yjainfo", Json(yijiaccount));
		return "account/account_yijiaccount_upd";
	}

	@RequestMapping(value = "/updYiJiAccount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updYiJiAccount(HttpServletRequest req, YiJiAccount yja) {
		Message msg = createMsg();
		YiJiAccount o =service.getYiJiAccount(yja.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDYIJIACCOUNT);
		if (service.updYiJiAccount(yja)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/delYiJiAccount")
	public String delYiJiAccount(HttpServletRequest req, int id) {
		Message msg = createMsg();
		
		YiJiAccount o =service.getYiJiAccount(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELYIJIACCOUNT);
		if (service.delYiJiAccount(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "redirect:/selYiJiAccount";
	}
	

	//4.16.2.dt_user_balance_detail(账户结算子表)
	
	@RequestMapping("/selUserBalanceDetail")
	public String selUserBalanceDetail(HttpServletRequest req){
		
		return "account/account_balancedetail_list";
	}
	@RequestMapping(value ="/queryUserBalanceDetail", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserBalanceDetail(HttpServletRequest req,UserBalanceDetailVo vo){
		Message msg =createMsg();
		
		Page<UserBalanceDetail> page =service.queryUserBalanceDetail(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserBalanceDetail")
	public String insUserBalanceDetail(HttpServletRequest req){
		
		return "account/account_balancedetail_add";
	}
	@RequestMapping(value ="/addUserBalanceDetail", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserBalanceDetail(HttpServletRequest req,UserBalanceDetail og){
		Message msg =createMsg();
		service.addUserBalanceDetail(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDBALANCEDETAIL);
		return Json(msg);
		
	}
	@RequestMapping("/readUserBalanceDetail")
	public String readUserBalanceDetail(HttpServletRequest req,int id){
		UserBalanceDetail og =service.getUserBalanceDetail(id);
		req.setAttribute("balancedetail", Json(og));
		return "account/account_balancedetail_upd";
	}
	@RequestMapping(value ="/updUserBalanceDetail", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserBalanceDetail(HttpServletRequest req,UserBalanceDetail og){
		Message msg =createMsg();
		UserBalanceDetail o =service.getUserBalanceDetail(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDBALANCEDETAIL);
		if(service.updUserBalanceDetail(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserBalanceDetail")
	public String delUserBalanceDetail(HttpServletRequest req,int id){
		
		UserBalanceDetail o =service.getUserBalanceDetail(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELBALANCEDETAIL);
		
		if(service.delUserBalanceDetail(id)){
			
		}
		return "redirect:/selUserBalanceDetail";
	}
	
	
	
	
	//4.15.1.dt_user_balance_log(账户结算主表)
	
	@RequestMapping("/selUserBalanceLog")
	public String selUserBalanceLog(HttpServletRequest req){
		
		return "account/account_balancelog_list";
	}
	@RequestMapping(value ="/queryUserBalanceLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserBalanceLog(HttpServletRequest req,UserBalanceLogVo vo){
		Message msg =createMsg();
		
		Page<UserBalanceLog> page =service.queryUserBalanceLog(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserBalanceLog")
	public String insUserBalanceLog(HttpServletRequest req){
		
		return "account/account_balancelog_add";
	}
	@RequestMapping(value ="/addUserBalanceLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserBalanceLog(HttpServletRequest req,UserBalanceLog og){
		Message msg =createMsg();
		service.addUserBalanceLog(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDBALANCELOG);
		return Json(msg);
		
	}
	@RequestMapping("/readUserBalanceLog")
	public String readUserBalanceLog(HttpServletRequest req,int id){
		UserBalanceLog og =service.getUserBalanceLog(id);
		req.setAttribute("balancelog", Json(og));
		return "account/account_balancelog_upd";
	}
	@RequestMapping(value ="/updUserBalanceLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserBalanceLog(HttpServletRequest req,UserBalanceLog og){
		Message msg =createMsg();
		UserBalanceLog o =service.getUserBalanceLog(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDBALANCELOG);
		if(service.updUserBalanceLog(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserBalanceLog")
	public String delUserBalanceLog(HttpServletRequest req,int id){
		
		UserBalanceLog o =service.getUserBalanceLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELBALANCELOG);
		
		if(service.delUserBalanceLog(id)){
			
		}
		return "redirect:/selUserBalanceLog";
	}
	
	//4.16.2.dt_user_balance_detail(账户结算子表)
	
		@RequestMapping("/selScoreLog")
		public String selScoreLog(HttpServletRequest req){
			
			return "account/account_scorelog_list";
		}
		@RequestMapping(value ="/queryScoreLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryScoreLog(HttpServletRequest req,ScoreLogVo vo){
			Message msg =createMsg();
			
			Page<ScoreLog> page =service.queryScoreLog(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/insScoreLog")
		public String insScoreLog(HttpServletRequest req){
			
			return "account/account_scorelog_add";
		}
		@RequestMapping(value ="/addScoreLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addScoreLog(HttpServletRequest req,ScoreLog og){
			Message msg =createMsg();
			try{
				service.addScoreLog(og);
				msg.setStatus("0");
				saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDBALANCEDETAIL);
			}catch(Exception e){
				msg.setStatus("1");
				e.printStackTrace();
			}
			return Json(msg);
			
		}
		@RequestMapping("/readScoreLog")
		public String readScoreLog(HttpServletRequest req,int id){
			ScoreLog og =service.getScoreLog(id);
			req.setAttribute("balancedetail", Json(og));
			return "account/account_scorelog_upd";
		}
		@RequestMapping(value ="/updScoreLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updScoreLog(HttpServletRequest req,ScoreLog og){
			Message msg =createMsg();
			ScoreLog o =service.getScoreLog(og.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDBALANCEDETAIL);
			if(service.updScoreLog(og)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
		}
		@RequestMapping("/delScoreLog")
		public String delScoreLog(HttpServletRequest req,int id){
			
			ScoreLog o =service.getScoreLog(id);
			saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELBALANCEDETAIL);
			
			if(service.delScoreLog(id)){
				
			}
			return "redirect:/selScoreLog";
		}

}
