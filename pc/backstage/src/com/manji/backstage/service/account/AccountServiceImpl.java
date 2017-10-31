package com.manji.backstage.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.account.AccountMapper;
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

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper mapper;

	@Override
	public List<Account> countAccountGroup(){
		List<Account> list =  mapper.countAccountGroup();
		return list;
	}
	
	
	@Override
	public Page<Account> queryAccount(AccountVo vo) {

		Page<Account> page =new Page<Account>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Account> dataList =mapper.queryAccount(vo);
		int totalCount =mapper.countAccount(vo);
		
		

		page.transform(totalCount, dataList);
		
		
		return page;
	}

	@Override
	public Account getAccount(int id) {
		return mapper.getAccount(id);
	}

	@Override
	public Page<Recharge> queryRecharge(RechargeVo vo) {
		
		Page<Recharge> page =new Page<Recharge>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		System.out.println(vo.getStatus());
		int totalCount =mapper.countRecharge(vo);
		
		List<Recharge> dataList =mapper.queryRecharge(vo);

		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public Page<Transaction> queryTransaction(TransactionVo vo) {
		Page<Transaction> page =new Page<Transaction>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int totalCount =mapper.countTransaction(vo);
		List<Transaction> dataList=mapper.queryTransaction(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public Page<Withdrawals> queryWithdrawals(WithdrawalsVo vo) {
		Page<Withdrawals> page =new Page<Withdrawals>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int totalCount =mapper.countWithdrawals(vo);
		List<Withdrawals> dataList =mapper.queryWithdrawals(vo);
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
		if(mapper.updAccount(account)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updRecharge(Recharge rec) {
		if(mapper.updRecharge(rec)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updTransaction(Transaction tran) {

		if(mapper.updTransaction(tran)>0){
			return true;
		}
		return false;
	}
	
	
	@Override
	public Page<YiJiAccount> queryYiJiAccount(YiJiAccountVo vo) {
	
		Page<YiJiAccount> page =new Page<YiJiAccount>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<YiJiAccount> dataList =mapper.queryYiJiAccount(vo);
		int totalCount =mapper.countYiJiAccount(vo);
		
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
		if(mapper.updYiJiAccount(yja)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delYiJiAccount(int id) {
		if(mapper.delYiJiAccount(id)>0){
			return true;
		}
		return false;
	}
	
	
}
