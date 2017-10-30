package com.manji.backstage.service;

import java.util.List;

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

public interface AcctService {

	// dt_user_accountinfo 用户账户信息表

	List<Account> countAccountGroup();

	Page<Account> queryAccount(AccountVo vo);

	Account getAccount(int id);

	// dt_user_recharge 用户充值记录信息表
	// dt_user_transaction 用户转账记录信息表
	// dt_user_withdrawals 用户提现记录信息表

	Page<Recharge> queryRecharge(RechargeVo vo);

	Page<Transaction> queryTransaction(TransactionVo vo);

	Page<Withdrawals> queryWithdrawals(WithdrawalsVo vo);

	Recharge getRecharge(int id);

	Transaction getTransaction(int id);

	Withdrawals getWithdrawals(int id);

	boolean updAccount(Account account);

	boolean updRecharge(Recharge rec);

	boolean updTransaction(Transaction tran);

	// dt_user_amount_log 用户余额日志表

	Page<AmountLog> queryAmountLog(AmountLogVo vo);

	AmountLog getAmountLog(int id);

	boolean updAmountLog(AmountLog al);

	boolean delAmountLog(int id);

	// dt_user_voucher_log 用户虚拟全日志表

	Page<VoucherLog> queryVoucherLog(VoucherLogVo vo);

	VoucherLog getVoucherLog(int id);

	boolean updVoucherLog(VoucherLog vl);

	boolean delVoucherLog(int id);

	// dt_user_point_log 用户积分日子表

	Page<PointLog> queryPointLog(PointLogVo vo);

	PointLog getPointLog(long id);

	boolean updPointLog(PointLog pl);

	boolean delPointLog(long id);

	// dt_user_credit_log 用户信用日志表

	Page<CreditLog> queryCreditLog(CreditLogVo vo);

	CreditLog getCreditLog(int id);

	boolean updCreditLog(CreditLog cl);

	boolean delCreditLog(int id);

	// dt_user_reputation_log 用户信誉日志表

	Page<ReputationLog> queryReputationLog(ReputationLogVo vo);

	ReputationLog getReputationLog(int id);

	boolean updReputationLog(ReputationLog rl);

	boolean delReputationLog(int id);

	Page<YiJiAccount> queryYiJiAccount(YiJiAccountVo vo);

	void addYiJiAccount(YiJiAccount YiJiAccount);

	YiJiAccount getYiJiAccount(int id);

	boolean updYiJiAccount(YiJiAccount YiJiAccount);

	boolean delYiJiAccount(int id);
	
	//4.16.2.dt_user_balance_detail(账户结算子表)

	Page<UserBalanceDetail> queryUserBalanceDetail(UserBalanceDetailVo vo);
	
	UserBalanceDetail getUserBalanceDetail(int id);
	
	boolean updUserBalanceDetail(UserBalanceDetail si);
	
	void addUserBalanceDetail(UserBalanceDetail si);
	
	boolean delUserBalanceDetail(int id);
	
	//4.15.1.dt_user_balance_log(账户结算主表)
	
	Page<UserBalanceLog> queryUserBalanceLog(UserBalanceLogVo vo);
	
	UserBalanceLog getUserBalanceLog(int id);
	
	boolean updUserBalanceLog(UserBalanceLog si);
	
	void addUserBalanceLog(UserBalanceLog si);
	
	boolean delUserBalanceLog(int id);
	
	//4.15.1.(商家信誉日志)
	
	Page<ScoreLog> queryScoreLog(ScoreLogVo vo);
	
	ScoreLog getScoreLog(int id);
	
	boolean updScoreLog(ScoreLog si);
	
	void addScoreLog(ScoreLog si);
	
	boolean delScoreLog(int id);
		

}
