package com.manji.backstage.service.account;

import java.util.List;

import com.manji.backstage.model.account.Account;
import com.manji.backstage.model.account.Recharge;
import com.manji.backstage.model.account.Transaction;
import com.manji.backstage.model.account.Withdrawals;
import com.manji.backstage.model.account.YiJiAccount;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.model.operation.Files;
import com.manji.backstage.vo.account.AccountVo;
import com.manji.backstage.vo.account.RechargeVo;
import com.manji.backstage.vo.account.TransactionVo;
import com.manji.backstage.vo.account.WithdrawalsVo;
import com.manji.backstage.vo.account.YiJiAccountVo;
import com.manji.backstage.vo.message.NoticeVo;

public interface AccountService {

	List<Account> countAccountGroup();
	
	Page<Account> queryAccount(AccountVo vo);

	Account getAccount(int id);

	Page<Recharge> queryRecharge(RechargeVo vo);

	Page<Transaction> queryTransaction(TransactionVo vo);

	Page<Withdrawals> queryWithdrawals(WithdrawalsVo vo);

	Recharge getRecharge(int id);

	Transaction getTransaction(int id);

	Withdrawals getWithdrawals(int id);

	boolean updAccount(Account account);

	boolean updRecharge(Recharge rec);

	boolean updTransaction(Transaction tran);
	
	
	Page<YiJiAccount> queryYiJiAccount(YiJiAccountVo vo);

	void addYiJiAccount(YiJiAccount YiJiAccount);

	YiJiAccount getYiJiAccount(int id);

	boolean updYiJiAccount(YiJiAccount YiJiAccount);

	boolean delYiJiAccount(int id);

	
}
