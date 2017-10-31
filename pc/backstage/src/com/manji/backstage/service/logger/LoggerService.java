package com.manji.backstage.service.logger;

import java.util.List;


import com.manji.backstage.model.logger.SmsLog;
import com.manji.backstage.model.logger.VoucherLog;
import com.manji.backstage.model.logger.WordsFilterLog;
import com.manji.backstage.vo.logger.AmountLogVo;
import com.manji.backstage.vo.logger.AuditLogVo;
import com.manji.backstage.vo.logger.CreditLogVo;
import com.manji.backstage.vo.logger.DeviceLogVo;
import com.manji.backstage.vo.logger.EmailLogVo;
import com.manji.backstage.vo.logger.FileDownLogVo;
import com.manji.backstage.vo.logger.FileFilterLogVo;
import com.manji.backstage.vo.logger.KeyHashsVo;
import com.manji.backstage.vo.logger.KeyWordsVo;
import com.manji.backstage.vo.logger.LoginLogVo;
import com.manji.backstage.vo.logger.PageViewLogVo;
import com.manji.backstage.vo.logger.PointLogVo;
import com.manji.backstage.vo.logger.ReportLogVo;
import com.manji.backstage.vo.logger.ReputationLogVo;
import com.manji.backstage.vo.logger.RoleAuditVo;
import com.manji.backstage.vo.logger.SignLogVo;
import com.manji.backstage.vo.logger.SmsLogVo;
import com.manji.backstage.vo.logger.VoucherLogVo;
import com.manji.backstage.vo.logger.WordsFilterLogVo;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.AmountLog;
import com.manji.backstage.model.logger.AuditLog;
import com.manji.backstage.model.logger.BalanceLog;
import com.manji.backstage.model.logger.CreditLog;
import com.manji.backstage.model.logger.DeviceLog;
import com.manji.backstage.model.logger.EmailLog;
import com.manji.backstage.model.logger.FileDownLog;
import com.manji.backstage.model.logger.FileFilterLog;
import com.manji.backstage.model.logger.KeyHashs;
import com.manji.backstage.model.logger.KeyWords;
import com.manji.backstage.model.logger.LoginLog;
import com.manji.backstage.model.logger.PageViewLog;
import com.manji.backstage.model.logger.PointLog;
import com.manji.backstage.model.logger.ReportLog;
import com.manji.backstage.model.logger.ReputationLog;
import com.manji.backstage.model.logger.RoleAudit;
import com.manji.backstage.model.logger.SignLog;

public interface LoggerService {
	
	
	
	List<LoginLog> loginLogList();
	
	

	List<SignLog> signLogList();

	List<SmsLog> smsLogList();

	List<EmailLog> emailLogList();
	
	List<FileDownLog> fileDownLogList();
	
	List<AmountLog> amountLogList();

	List<VoucherLog> voucherLogList();

	List<PointLog> pointLogList();

	List<CreditLog> creditLogList();

	List<ReputationLog> reputationLogList();
	
	List<LoginLog> queryLoginLogByUserId(String user_id);

	List<SignLog> querySignLogByUserId(String user_id);

	List<AmountLog> queryAmountLogByUserId(String user_id);

	List<PointLog> queryPointLogByUserId(String user_id);

	List<CreditLog> queryCreditLogByUserId(String user_id);

	List<ReputationLog> queryReputationLogByUserId(String user_id);

	List<VoucherLog> queryVoucherLogByUserId(String user_id);

	List<AmountLog> queryAmountLogByOrder(String order_no);

	List<PointLog> queryPointLogByOrder(String order_no);

	List<CreditLog> queryCreditLogByOrder(String order_no);

	List<ReputationLog> queryReputationLogByOrder(String order_no);

	List<VoucherLog> queryVoucherLogByOrder(String order_no);

	


	KeyWords getKeyWordsList(int id);

	Page<KeyWords> queryKeyWords(KeyWordsVo vo);
	
	void addKeyWords(KeyWords keywords);

	boolean updKeyWords(KeyWords keywords);

	boolean delKeyWords(int id);
	

	KeyHashs getKeyHashsList(int id);

	Page<KeyHashs> queryKeyHashs(KeyHashsVo vo);
	
	void addKeyHashs(KeyHashs keyhashs);

	boolean updKeyHashs(KeyHashs keyhashs);

	boolean delKeyHashs(int id);
	
	
	WordsFilterLog getWordsFilterLogList(int id);
	
	Page<WordsFilterLog> queryWordsFilterLog(WordsFilterLogVo vo);
	
	void addWordsFilterLog(WordsFilterLog wfl);
	
	boolean updWordsFilterLog(WordsFilterLog wfl);
	
	boolean delWordsFilterLog(int id);
	
	
	FileFilterLog getFileFilterLogList(int id);
	
	Page<FileFilterLog> queryFileFilterLog(FileFilterLogVo vo);
	
	void addFileFilterLog(FileFilterLog wfl);
	
	boolean updFileFilterLog(FileFilterLog wfl);
	
	boolean delFileFilterLog(int id);
	

	ReportLog getReportLogList(int id);
	
	Page<ReportLog> queryReportLog(ReportLogVo vo);
	
	void addReportLog(ReportLog rl);
	
	boolean updReportLog(ReportLog rl);
	
	boolean delReportLog(int id);



	
	
	
	List<FileDownLog> getFileDownLog();

	List<BalanceLog> getBalanceLogList();

	List<SmsLog> querySmsLogList(SmsLog querylog);

	List<EmailLog> queryEmailLogList(EmailLog querylog);

	List<FileDownLog> queryFileDownLogList(FileDownLog querylog);
	
	List<VoucherLog> queryVoucherLog(VoucherLog queryLog);


	
	

	Page<LoginLog> queryLoginLog(LoginLogVo vo);

	LoginLog getLoginLog(int id);

	boolean updLoginLog(LoginLog ll);

	boolean delLoginLog(int id);

	
	Page<SignLog> querySignLog(SignLogVo vo);
	
	SignLog getSignLog(int id);

	boolean updSignLog(SignLog sl);

	boolean delSignLog(int id);


	Page<SmsLog> querySmsLog(SmsLogVo vo);
	SmsLog getSmsLog(int id);
	void addSmsLog(SmsLog sl);
	boolean updSmsLog(SmsLog sl);
	boolean delSmsLog(int id);
	
	Page<EmailLog> queryEmailLog(EmailLogVo vo);
	EmailLog getEmailLog(int id);
	void addEmailLog(EmailLog el);
	boolean updEmailLog(EmailLog el);
	boolean delEmailLog(int id);
	
	Page<FileDownLog> queryFileDownLog(FileDownLogVo vo);
	FileDownLog getFileDownLog(int id);
	boolean updFileDownLog(FileDownLog fdl);
	boolean delFileDownLog(int id);
	
	Page<AmountLog> queryAmountLog(AmountLogVo vo);
	AmountLog getAmountLog(int id);
	boolean updAmountLog(AmountLog al);
	boolean delAmountLog(int id);



	Page<VoucherLog> queryAmountLog(VoucherLogVo vo);
	VoucherLog getVoucherLog(int id);
	boolean updVoucherLog(VoucherLog vl);
	boolean delVoucherLog(int id);



	Page<PointLog> queryPointLog(PointLogVo vo);
	PointLog getPointLog(int id);
	boolean updPointLog(PointLog pl);
	boolean delPointLog(int id);



	Page<CreditLog> queryCreditLog(CreditLogVo vo);
	CreditLog getCreditLog(int id);
	boolean updCreditLog(CreditLog cl);
	boolean delCreditLog(int id);



	Page<ReputationLog> queryReputationLog(ReputationLogVo vo);
	ReputationLog getReputationLog(int id);
	boolean updReputationLog(ReputationLog rl);
	boolean delReputationLog(int id);



	Page<PageViewLog> queryPageViewLog(PageViewLogVo vo);
	PageViewLog getPageViewLog(long l);
	boolean updPageViewLog(PageViewLog pvl);
	boolean delPageViewLog(int id);



	Page<DeviceLog> queryDeviceLog(DeviceLogVo vo);
	DeviceLog getDeviceLog(long l);
	boolean updDeviceLog(DeviceLog dl);
	boolean delDeviceLog(long id);



	Page<RoleAudit> queryRoleAudit(RoleAuditVo vo);

	RoleAudit getRoleAudit(int id);

	boolean updRoleAudit(RoleAudit ra);

	boolean addRoleAudit(RoleAudit ra);

	boolean delRoleAudit(int id);

	Page<AuditLog> queryAuditLog(AuditLogVo vo);

	AuditLog getAuditLog(int id);

	boolean updAuditLog(AuditLog al);

	boolean addAuditLog(AuditLog al);

	boolean delAuditLog(int id);

	
	


	
	
	
}
