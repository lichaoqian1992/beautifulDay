package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.AcctMapper;
import com.manji.backstage.model.acct.Account;
import com.manji.backstage.model.acct.Recharge;
import com.manji.backstage.model.acct.ScoreLog;
import com.manji.backstage.model.acct.Transaction;
import com.manji.backstage.model.acct.UserBalanceDetail;
import com.manji.backstage.model.acct.UserBalanceLog;
import com.manji.backstage.model.acct.Withdrawals;
import com.manji.backstage.model.acct.YiJiAccount;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.AmountLog;
import com.manji.backstage.model.logger.CreditLog;
import com.manji.backstage.model.logger.PointLog;
import com.manji.backstage.model.logger.ReputationLog;
import com.manji.backstage.model.logger.VoucherLog;
import com.manji.backstage.service.AcctService;
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

@Service
public class AcctServiceImpl implements AcctService {

	@Autowired
	private AcctMapper mapper;

	@Override
	public List<Account> countAccountGroup() {
		List<Account> list = mapper.countAccountGroup();
		return list;
	}

	// dt_user_accountinfo 用户账户信息表
	@Override
	public Page<Account> queryAccount(AccountVo vo) {

		Page<Account> page = new Page<Account>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<Account> dataList = mapper.queryAccount(vo);
		int totalCount = mapper.countAccount(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public Account getAccount(int id) {
		return mapper.getAccount(id);
	}

	// dt_user_recharge 用户充值记录信息表
	// dt_user_transaction 用户转账记录信息表
	// dt_user_withdrawals 用户提现记录信息表

	@Override
	public Page<Recharge> queryRecharge(RechargeVo vo) {

		Page<Recharge> page = new Page<Recharge>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		System.out.println(vo.getStatus());
		int totalCount = mapper.countRecharge(vo);

		List<Recharge> dataList = mapper.queryRecharge(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public Page<Transaction> queryTransaction(TransactionVo vo) {
		Page<Transaction> page = new Page<Transaction>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		int totalCount = mapper.countTransaction(vo);
		List<Transaction> dataList = mapper.queryTransaction(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public Page<Withdrawals> queryWithdrawals(WithdrawalsVo vo) {
		Page<Withdrawals> page = new Page<Withdrawals>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		int totalCount = mapper.countWithdrawals(vo);
		List<Withdrawals> dataList = mapper.queryWithdrawals(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public Recharge getRecharge(int id) {
		return mapper.getRecharge(id);
	}

	@Override
	public Transaction getTransaction(int id) {
		return mapper.getTransaction(id);
	}

	@Override
	public Withdrawals getWithdrawals(int id) {
		return mapper.getWithdrawals(id);
	}

	@Override
	public boolean updAccount(Account account) {
		if (mapper.updAccount(account) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updRecharge(Recharge rec) {
		if (mapper.updRecharge(rec) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updTransaction(Transaction tran) {

		if (mapper.updTransaction(tran) > 0) {
			return true;
		}
		return false;
	}

	// dt_user_amount_log 用户余额日志表

	@Override
	public Page<AmountLog> queryAmountLog(AmountLogVo vo) {
		Page<AmountLog> page = new Page<AmountLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<AmountLog> dataList = mapper.queryAmountLog(vo);
		int totalCount = mapper.countAmountLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public AmountLog getAmountLog(int id) {
		return mapper.getAmountLog(id);
	}

	@Override
	public boolean updAmountLog(AmountLog al) {
		if (mapper.updAmountLog(al) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delAmountLog(int id) {
		if (mapper.delAmountLog(id) > 0) {
			return true;
		}
		return false;
	}

	// dt_user_voucher_log 用户虚拟全日志表

	@Override
	public Page<VoucherLog> queryVoucherLog(VoucherLogVo vo) {
		Page<VoucherLog> page = new Page<VoucherLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<VoucherLog> dataList = mapper.queryVoucherLog(vo);
		int totalCount = mapper.countVoucherLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public VoucherLog getVoucherLog(int id) {
		return mapper.getVoucherLog(id);
	}

	@Override
	public boolean updVoucherLog(VoucherLog vl) {
		if (mapper.updVoucherLog(vl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delVoucherLog(int id) {
		if (mapper.delVoucherLog(id) > 0) {
			return true;
		}
		return false;
	}

	// dt_user_point_log 用户积分日子表
	@Override
	public Page<PointLog> queryPointLog(PointLogVo vo) {
		Page<PointLog> page = new Page<PointLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<PointLog> dataList = mapper.queryPointLog(vo);
		int totalCount = mapper.countPointLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public PointLog getPointLog(long id) {
		return mapper.getPointLog(id);
	}

	@Override
	public boolean updPointLog(PointLog pl) {
		if (mapper.updPointLog(pl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delPointLog(long id) {
		if (mapper.delPointLog(id) > 0) {
			return true;
		}
		return false;
	}

	// dt_user_credit_log 用户信用日志表
	@Override
	public Page<CreditLog> queryCreditLog(CreditLogVo vo) {
		Page<CreditLog> page = new Page<CreditLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<CreditLog> dataList = mapper.queryCreditLog(vo);
		int totalCount = mapper.countCreditLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public CreditLog getCreditLog(int id) {
		return mapper.getCreditLog(id);
	}

	@Override
	public boolean updCreditLog(CreditLog cl) {
		if (mapper.updCreditLog(cl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delCreditLog(int id) {
		if (mapper.delCreditLog(id) > 0) {
			return true;
		}
		return false;
	}

	// dt_user_reputation_log 用户信誉日志表

	@Override
	public Page<ReputationLog> queryReputationLog(ReputationLogVo vo) {
		Page<ReputationLog> page = new Page<ReputationLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ReputationLog> dataList = mapper.queryReputationLog(vo);
		int totalCount = mapper.countReputationLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public ReputationLog getReputationLog(int id) {
		return mapper.getReputationLog(id);
	}

	@Override
	public boolean updReputationLog(ReputationLog rl) {
		if (mapper.updReputationLog(rl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delReputationLog(int id) {
		if (mapper.delReputationLog(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<YiJiAccount> queryYiJiAccount(YiJiAccountVo vo) {

		Page<YiJiAccount> page = new Page<YiJiAccount>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<YiJiAccount> dataList = mapper.queryYiJiAccount(vo);
		int totalCount = mapper.countYiJiAccount(vo);

		page.transform(totalCount, dataList);

		return page;

	}

	@Override
	public void addYiJiAccount(YiJiAccount yja) {
		mapper.addYiJiAccount(yja);
	}

	@Override
	public YiJiAccount getYiJiAccount(int id) {
		return mapper.getYiJiAccount(id);
	}

	@Override
	public boolean updYiJiAccount(YiJiAccount yja) {
		if (mapper.updYiJiAccount(yja) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delYiJiAccount(int id) {
		if (mapper.delYiJiAccount(id) > 0) {
			return true;
		}
		return false;
	}
	

	//4.16.2.dt_user_balance_detail(账户结算子表)
	@Override
	public Page<UserBalanceDetail> queryUserBalanceDetail(UserBalanceDetailVo vo) {
		Page<UserBalanceDetail> page =new Page<UserBalanceDetail>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserBalanceDetail(vo);
		List<UserBalanceDetail> list =mapper.queryUserBalanceDetail(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserBalanceDetail(UserBalanceDetail og) {
		mapper.addUserBalanceDetail(og);
	}
	
	@Override
	public UserBalanceDetail getUserBalanceDetail(int id) {
		return mapper.getUserBalanceDetail(id);
	}
	
	@Override
	public boolean updUserBalanceDetail(UserBalanceDetail og) {
		if(mapper.updUserBalanceDetail(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserBalanceDetail(int id) {
		if(mapper.delUserBalanceDetail(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//4.15.1.dt_user_balance_log(账户结算主表)
	@Override
	public Page<UserBalanceLog> queryUserBalanceLog(UserBalanceLogVo vo) {
		Page<UserBalanceLog> page =new Page<UserBalanceLog>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserBalanceLog(vo);
		List<UserBalanceLog> list =mapper.queryUserBalanceLog(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserBalanceLog(UserBalanceLog og) {
		mapper.addUserBalanceLog(og);
	}
	
	@Override
	public UserBalanceLog getUserBalanceLog(int id) {
		return mapper.getUserBalanceLog(id);
	}
	
	@Override
	public boolean updUserBalanceLog(UserBalanceLog og) {
		if(mapper.updUserBalanceLog(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserBalanceLog(int id) {
		if(mapper.delUserBalanceLog(id)>0){
			return true;
		}
		return false;
	}
	
	
	//4.15.1.(商家信誉日志)
		@Override
		public Page<ScoreLog> queryScoreLog(ScoreLogVo vo) {
			Page<ScoreLog> page =new Page<ScoreLog>();
			
			if(vo.getIndex()==0||"".equals(vo.getIndex())){
				vo.setIndex(1);
				page.setIndex(1);
			}else{
				page.setIndex(vo.getIndex());
			}
			
			int count =mapper.countScoreLog(vo);
			List<ScoreLog> list =mapper.queryScoreLog(vo);
			
			page.transform(count, list);
			return page;
		}
		
		@Override
		public void addScoreLog(ScoreLog og) {
			mapper.addScoreLog(og);
		}
		
		@Override
		public ScoreLog getScoreLog(int id) {
			return mapper.getScoreLog(id);
		}
		
		@Override
		public boolean updScoreLog(ScoreLog og) {
			if(mapper.updScoreLog(og)>0){
				return true;
			}
			return false;
		}
		
		@Override
		public boolean delScoreLog(int id) {
			if(mapper.delScoreLog(id)>0){
				return true;
			}
			return false;
		}
}
