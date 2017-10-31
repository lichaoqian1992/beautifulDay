package com.manji.backstage.mapper.account;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.account.Account;
import com.manji.backstage.model.account.Recharge;
import com.manji.backstage.model.account.Transaction;
import com.manji.backstage.model.account.Withdrawals;
import com.manji.backstage.model.account.YiJiAccount;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.vo.account.AccountVo;
import com.manji.backstage.vo.account.RechargeVo;
import com.manji.backstage.vo.account.TransactionVo;
import com.manji.backstage.vo.account.WithdrawalsVo;
import com.manji.backstage.vo.account.YiJiAccountVo;
import com.manji.backstage.vo.message.NoticeVo;
@Resource
public interface AccountMapper {
	
	List<Account> countAccountGroup(); 
	
	Account getAccount(int id);

	int countAccount(AccountVo vo);

	List<Account> queryAccount(AccountVo vo);

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
	
	
	List<YiJiAccount> queryYiJiAccount(YiJiAccountVo vo);

	int countYiJiAccount(YiJiAccountVo vo);

	void addYiJiAccount(YiJiAccount YiJiAccount);

	YiJiAccount getYiJiAccount(int id);

	int updYiJiAccount(YiJiAccount YiJiAccount);

	int delYiJiAccount(int id);

}
