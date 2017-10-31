package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.acct.Account;
import com.manji.backstage.model.acct.Recharge;
import com.manji.backstage.model.acct.ScoreLog;
import com.manji.backstage.model.acct.Transaction;
import com.manji.backstage.model.acct.UserBalanceDetail;
import com.manji.backstage.model.acct.UserBalanceLog;
import com.manji.backstage.model.acct.Withdrawals;
import com.manji.backstage.model.acct.YiJiAccount;
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
@Resource
public interface AcctMapper {

	// dt_user_accountinfo 用户账户信息表
	List<Account> countAccountGroup();

	Account getAccount(int id);

	int countAccount(AccountVo vo);

	List<Account> queryAccount(AccountVo vo);

	// dt_user_recharge 用户充值记录信息表
	// dt_user_transaction 用户转账记录信息表
	// dt_user_withdrawals 用户提现记录信息表

	int countRecharge(RechargeVo vo);

	List<Recharge> queryRecharge(RechargeVo vo);

	int countTransaction(TransactionVo vo);

	List<Transaction> queryTransaction(TransactionVo vo);

	int countWithdrawals(WithdrawalsVo vo);

	List<Withdrawals> queryWithdrawals(WithdrawalsVo vo);

	Recharge getRecharge(int id);

	Transaction getTransaction(int id);

	Withdrawals getWithdrawals(int id);

	int updAccount(Account account);

	int updRecharge(Recharge rec);

	int updTransaction(Transaction tran);

	// dt_user_amount_log 用户余额日志表

	List<AmountLog> queryAmountLog(AmountLog queryLog);

	int countAmountLog(AmountLogVo vo);

	AmountLog getAmountLog(int id);

	int updAmountLog(AmountLog al);

	int delAmountLog(int id);

	// dt_user_voucher_log 用户虚拟全日志表

	List<VoucherLog> queryVoucherLog(VoucherLogVo vo);

	int countVoucherLog(VoucherLogVo vo);

	VoucherLog getVoucherLog(int id);

	int updVoucherLog(VoucherLog vl);

	int delVoucherLog(int id);

	// dt_user_point_log 用户积分日子表

	List<PointLog> queryPointLog(PointLogVo vo);

	int countPointLog(PointLogVo vo);

	PointLog getPointLog(long id);

	long updPointLog(PointLog pl);

	long delPointLog(long id);

	// dt_user_credit_log 用户信用日志表

	List<CreditLog> queryCreditLog(CreditLogVo vo);

	int countCreditLog(CreditLogVo vo);

	CreditLog getCreditLog(int id);

	int updCreditLog(CreditLog cl);

	int delCreditLog(int id);

	// dt_user_reputation_log 用户信誉日志表

	List<ReputationLog> queryReputationLog(ReputationLogVo vo);

	int countReputationLog(ReputationLogVo vo);

	ReputationLog getReputationLog(int id);

	int updReputationLog(ReputationLog cl);

	int delReputationLog(int id);

	List<YiJiAccount> queryYiJiAccount(YiJiAccountVo vo);

	int countYiJiAccount(YiJiAccountVo vo);

	void addYiJiAccount(YiJiAccount yja);

	YiJiAccount getYiJiAccount(int id);

	int updYiJiAccount(YiJiAccount yja);

	int delYiJiAccount(int id);


	//4.15.1.dt_user_balance_log(账户结算主表)
	
	List<UserBalanceLog> queryUserBalanceLog(UserBalanceLogVo vo);
	
	int countUserBalanceLog(UserBalanceLogVo vo);
	
	UserBalanceLog getUserBalanceLog(int id);
	
	int updUserBalanceLog(UserBalanceLog art);
	
	void addUserBalanceLog(UserBalanceLog art);
	
	int delUserBalanceLog(int id);
	
	//4.16.2.	dt_user_balance_detail(账户结算子表)
	
	List<UserBalanceDetail> queryUserBalanceDetail(UserBalanceDetailVo vo);
	
	int countUserBalanceDetail(UserBalanceDetailVo vo);
	
	UserBalanceDetail getUserBalanceDetail(int id);
	
	int updUserBalanceDetail(UserBalanceDetail art);
	
	void addUserBalanceDetail(UserBalanceDetail art);
	
	int delUserBalanceDetail(int id);
	
	//4.15.1.(商家信誉日志)
	
	List<ScoreLog> queryScoreLog(ScoreLogVo vo);
	
	int countScoreLog(ScoreLogVo vo);
	
	ScoreLog getScoreLog(int id);
	
	int updScoreLog(ScoreLog art);

	void addScoreLog(ScoreLog art);
	
	int delScoreLog(int id);
	
}
