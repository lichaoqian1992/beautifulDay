package com.manji.backstage.mapper.logger;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.logger.SignLog;
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

@Resource
public interface LoggerMapper {

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
	
	List<LoginLog> queryLoginLogByUserId(int user_id);
	
	List<SignLog> querySignLogByUserId(int user_id);

	List<AmountLog> queryAmountLogByUserId(int user_id);

	List<PointLog> queryPointLogByUserId(int user_id);

	List<CreditLog> queryCreditLogByUserId(int user_id);

	List<ReputationLog> queryReputationLogByUserId(int user_id);

	List<VoucherLog> queryVoucherLogByUserId(int user_id);

	List<AmountLog> queryAmountLogByOrder(String order_no);

	List<PointLog> queryPointLogByOrder(String order_no);

	List<CreditLog> queryCreditLogByOrder(String order_no);

	List<ReputationLog> queryReputationLogByOrder(String order_no);

	List<VoucherLog> queryVoucherLogByOrder(String order_no);

	



	KeyWords getKeyWordsList(int id);

	List<KeyWords> queryKeyWords(KeyWords keywords);
	
	int countKeyWords(KeyWordsVo vo);

	void addKeyWords(KeyWords keywords);

	int updKeyWords(KeyWords keywords);

	int delKeyWords(int id);
	

	KeyHashs getKeyHashsList(int id);

	List<KeyHashs> queryKeyHashs(KeyHashs keyhashs);
	
	int countKeyHashs(KeyHashsVo vo);

	void addKeyHashs(KeyHashs keyhashs);

	int updKeyHashs(KeyHashs keyhashs);

	int delKeyHashs(int  id);
	
	
	WordsFilterLog getWordsFilterLogList(int id);

	List<WordsFilterLog> queryWordsFilterLog(WordsFilterLog WordsFilterLog);
	
	int countWordsFilterLog(WordsFilterLogVo vo);

	void addWordsFilterLog(WordsFilterLog WordsFilterLog);

	int updWordsFilterLog(WordsFilterLog WordsFilterLog);

	int delWordsFilterLog(int id);
	
	
	FileFilterLog getFileFilterLogList(int id);

	List<FileFilterLog> queryFileFilterLog(FileFilterLog FileFilterLog);
	
	int countFileFilterLog(FileFilterLogVo vo);

	void addFileFilterLog(FileFilterLog FileFilterLog);

	int updFileFilterLog(FileFilterLog FileFilterLog);

	int delFileFilterLog(int id);
	

	ReportLog getReportLogList(int id);

	List<ReportLog> queryReportLog(ReportLog ReportLog);
	
	int countReportLog(ReportLogVo vo);

	void addReportLog(ReportLog ReportLog);

	int updReportLog(ReportLog ReportLog);

	int delReportLog(int id);
	


	List<FileDownLog> getFileDownLog();

	List<BalanceLog> getBalanceLogList();

	List<SmsLog> querySmsLogList(SmsLog querylog);
	
	List<EmailLog> queryEmailLogList(EmailLog querylog);
	
	List<FileDownLog> queryFileDownLogList(FileDownLog querylog);

	



	
	
	
	
	
	
	List<LoginLog> queryLoginLog(LoginLogVo vo);
	int countLoginLog(LoginLogVo vo);
	LoginLog getLoginLog(int id);
	int updLoginLog(LoginLog ll);
	int delLoginLog(int id);

	
	List<SignLog> querySignLog(SignLogVo vo);
	int countSignLog(SignLogVo vo);
	SignLog getSignLog(int id);
	int updSignLog(SignLog sl);
	int delSignLog(int id);

	List<SmsLog> querySmsLog(SmsLogVo vo);
	int countSmsLog(SmsLogVo vo);
	SmsLog getSmsLog(int id);
	void addSmsLog(SmsLog sl);
	int updSmsLog(SmsLog sl);
	int delSmsLog(int id);
	
	List<EmailLog> queryEmailLog(EmailLogVo vo);
	int countEmailLog(EmailLogVo vo);
	EmailLog getEmailLog(int id);
	void addEmailLog(EmailLog el);
	int updEmailLog(EmailLog el);
	int delEmailLog(int id);
	
	List<FileDownLog> queryFileDownLog(FileDownLogVo vo);
	int countFileDownLog(FileDownLogVo vo);
	FileDownLog getFileDownLog(int id);
	int updFileDownLog(FileDownLog fdl);
	int delFileDownLog(int id);
	
	List<AmountLog> queryAmountLog(AmountLog queryLog);
	int countAmountLog(AmountLogVo vo);
	AmountLog getAmountLog(int id);
	int updAmountLog(AmountLog al);
	int delAmountLog(int id);

	List<VoucherLog> queryVoucherLog(VoucherLogVo vo);
	int countVoucherLog(VoucherLogVo vo);
	VoucherLog getVoucherLog(int id);
	int updVoucherLog(VoucherLog vl);
	int delVoucherLog(int id);

	List<PointLog> queryPointLog(PointLogVo vo);
	int countPointLog(PointLogVo vo);
	PointLog getPointLog(int id);
	int updPointLog(PointLog pl);
	int delPointLog(int id);

	List<CreditLog> queryCreditLog(CreditLogVo vo);
	int countCreditLog(CreditLogVo vo);
	CreditLog getCreditLog(int id);
	int updCreditLog(CreditLog cl);
	int delCreditLog(int id);

	List<ReputationLog> queryReputationLog(ReputationLogVo vo);
	int countReputationLog(ReputationLogVo vo);
	ReputationLog getReputationLog(int id);
	int updReputationLog(ReputationLog cl);
	int delReputationLog(int id);

	
	List<PageViewLog> queryPageViewLog(PageViewLogVo vo);
	int countPageViewLog(PageViewLogVo vo);
	PageViewLog getPageViewLog(long id);
	int updPageViewLog(PageViewLog pvl);
	int delPageViewLog(int id);

	List<DeviceLog> queryDeviceLog(DeviceLogVo vo);
	int countDeviceLog(DeviceLogVo vo);
	DeviceLog getDeviceLog(long id);
	int updDeviceLog(DeviceLog dl);
	int delDeviceLog(long id);

	List<RoleAudit> queryRoleAudit(RoleAuditVo vo);

	int countRoleAudit(RoleAuditVo vo);

	RoleAudit getRoleAudit(int id);

	int updRoleAudit(RoleAudit ra);

	void addRoleAudit(RoleAudit ra);

	int delRoleAudit(int id);

	List<AuditLog> queryAuditLog(AuditLogVo vo);

	int countAuditLog(AuditLogVo vo);

	AuditLog getAuditLog(int id);

	int updAuditLog(AuditLog al);

	void addAuditLog(AuditLog al);

	int delAuditLog(int id);
	
	
}
