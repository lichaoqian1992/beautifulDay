package com.manji.backstage.service.logger;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.logger.LoggerMapper;
import com.manji.backstage.model.logger.SignLog;
import com.manji.backstage.model.account.Account;
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
import com.manji.backstage.model.logger.SmsLog;
import com.manji.backstage.model.logger.VoucherLog;
import com.manji.backstage.model.logger.WordsFilterLog;
import com.manji.backstage.utils.TimeUtils;
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


@Service
public class LoggerServiceImpl implements LoggerService {

	@Autowired
	private LoggerMapper mapper;

	@Override
	public List<LoginLog> loginLogList() {
		
		return mapper.loginLogList();
	}

	@Override
	public List<SignLog> signLogList() {
		
		return mapper.signLogList();
	}

	@Override
	public List<SmsLog> smsLogList() {
		
		return mapper.smsLogList();
	}
	
	@Override
	public List<EmailLog> emailLogList() {
		
		return mapper.emailLogList();
	}
	
	@Override
	public List<FileDownLog> fileDownLogList() {
		
		return mapper.fileDownLogList();
	}


	@Override
	public List<AmountLog> amountLogList() {
		
		return mapper.amountLogList();
	}

	@Override
	public List<VoucherLog> voucherLogList() {
		
		return mapper.voucherLogList();
	}

	@Override
	public List<PointLog> pointLogList() {
		
		return mapper.pointLogList();
	}

	@Override
	public List<CreditLog> creditLogList() {
		
		return mapper.creditLogList();
	}

	@Override
	public List<ReputationLog> reputationLogList() {
		
		return mapper.reputationLogList();
	}

	@Override
	public List<LoginLog> queryLoginLogByUserId(String user_id) {
		
		return mapper.queryLoginLogByUserId(Integer.valueOf(user_id));
	}


	@Override
	public List<SignLog> querySignLogByUserId(String user_id) {
	
		return mapper.querySignLogByUserId(Integer.valueOf(user_id));
	}

	@Override
	public List<AmountLog> queryAmountLogByUserId(String user_id) {
	
		return mapper.queryAmountLogByUserId(Integer.valueOf(user_id));
	}

	@Override
	public List<PointLog> queryPointLogByUserId(String user_id) {
		
		return mapper.queryPointLogByUserId(Integer.valueOf(user_id));
	}

	@Override
	public List<CreditLog> queryCreditLogByUserId(String user_id) {
		
		return mapper.queryCreditLogByUserId(Integer.valueOf(user_id));
	}

	@Override
	public List<ReputationLog> queryReputationLogByUserId(String user_id) {
		
		return mapper.queryReputationLogByUserId(Integer.valueOf(user_id));
	}

	@Override
	public List<VoucherLog> queryVoucherLogByUserId(String user_id) {
		
		return mapper.queryVoucherLogByUserId(Integer.valueOf(user_id));
	}

	@Override
	public List<AmountLog> queryAmountLogByOrder(String order_no) {
		
		return mapper.queryAmountLogByOrder(order_no);
	}

	@Override
	public List<PointLog> queryPointLogByOrder(String order_no) {
		
		return mapper.queryPointLogByOrder(order_no);
	}

	@Override
	public List<CreditLog> queryCreditLogByOrder(String order_no) {
		
		return mapper.queryCreditLogByOrder(order_no);
	}

	@Override
	public List<ReputationLog> queryReputationLogByOrder(String order_no) {
		
		return mapper.queryReputationLogByOrder(order_no);
	}

	@Override
	public List<VoucherLog> queryVoucherLogByOrder(String order_no) {
		
		return mapper.queryVoucherLogByOrder(order_no);
	}

	



	@Override
	public KeyWords getKeyWordsList(int id) {
		return mapper.getKeyWordsList(id);
	}

	@Override
	public Page<KeyWords> queryKeyWords(KeyWordsVo vo) {
		Page<KeyWords> page =new Page<KeyWords>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<KeyWords> dataList =mapper.queryKeyWords(vo);
		int totalCount =mapper.countKeyWords(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public void addKeyWords(KeyWords keywords) {
		
		String time=TimeUtils.getCurrentTime();
		keywords.setAdd_time(time);
		mapper.addKeyWords(keywords);
		
	}

	@Override
	public boolean updKeyWords(KeyWords keywords) {
		
		if(mapper.updKeyWords(keywords)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean delKeyWords(int id) {
		
		if(mapper.delKeyWords(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}
	
	
	@Override
	public KeyHashs getKeyHashsList(int id) {
		return mapper.getKeyHashsList(id);
	}
	
	@Override
	public Page<KeyHashs> queryKeyHashs(KeyHashsVo vo) {
		Page<KeyHashs> page =new Page<KeyHashs>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<KeyHashs> dataList =mapper.queryKeyHashs(vo);
		int totalCount =mapper.countKeyHashs(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addKeyHashs(KeyHashs keyhashs) {
		
		String time=TimeUtils.getCurrentTime();
		keyhashs.setAdd_time(time);
		mapper.addKeyHashs(keyhashs);
		
	}
	
	@Override
	public boolean updKeyHashs(KeyHashs keyhashs) {
		
		if(mapper.updKeyHashs(keyhashs)>0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delKeyHashs(int id) {
		
		if(mapper.delKeyHashs(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}

	
	@Override
	public WordsFilterLog getWordsFilterLogList(int id) {
		return mapper.getWordsFilterLogList(id);
	}
	
	@Override
	public Page<WordsFilterLog> queryWordsFilterLog(WordsFilterLogVo vo) {
		Page<WordsFilterLog> page =new Page<WordsFilterLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<WordsFilterLog> dataList =mapper.queryWordsFilterLog(vo);
		int totalCount =mapper.countWordsFilterLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addWordsFilterLog(WordsFilterLog wfl) {
		
		String time=TimeUtils.getCurrentTime();
		wfl.setAdd_time(time);
		mapper.addWordsFilterLog(wfl);
		
	}
	
	@Override
	public boolean updWordsFilterLog(WordsFilterLog wfl) {
		
		if(mapper.updWordsFilterLog(wfl)>0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delWordsFilterLog(int id) {
		
		if(mapper.delWordsFilterLog(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}
	
	@Override
	public FileFilterLog getFileFilterLogList(int id) {
		return mapper.getFileFilterLogList(id);
	}
	
	@Override
	public Page<FileFilterLog> queryFileFilterLog(FileFilterLogVo vo) {
		Page<FileFilterLog> page =new Page<FileFilterLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<FileFilterLog> dataList =mapper.queryFileFilterLog(vo);
		int totalCount =mapper.countFileFilterLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addFileFilterLog(FileFilterLog ffl) {
		
		String time=TimeUtils.getCurrentTime();
		ffl.setAdd_time(time);
		mapper.addFileFilterLog(ffl);
		
	}
	
	@Override
	public boolean updFileFilterLog(FileFilterLog ffl) {
		
		if(mapper.updFileFilterLog(ffl)>0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delFileFilterLog(int id) {
		
		if(mapper.delFileFilterLog(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}
	

	@Override
	public ReportLog getReportLogList(int id) {
		return mapper.getReportLogList(id);
	}
	
	@Override
	public Page<ReportLog> queryReportLog(ReportLogVo vo) {
		Page<ReportLog> page =new Page<ReportLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ReportLog> dataList =mapper.queryReportLog(vo);
		int totalCount =mapper.countReportLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addReportLog(ReportLog rl) {
		
		String time=TimeUtils.getCurrentTime();
		rl.setAdd_time(time);
		mapper.addReportLog(rl);
		
	}
	
	@Override
	public boolean updReportLog(ReportLog rl) {
		
		if(mapper.updReportLog(rl)>0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delReportLog(int id) {
		
		if(mapper.delReportLog(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public List<FileDownLog> getFileDownLog() {
		return mapper.getFileDownLog();
	}

	@Override
	public List<BalanceLog> getBalanceLogList() {
		return mapper.getBalanceLogList();
	}

	@Override
	public List<SmsLog> querySmsLogList(SmsLog querylog) {
		return mapper.querySmsLogList(querylog);
	}

	@Override
	public List<EmailLog> queryEmailLogList(EmailLog querylog) {
		return mapper.queryEmailLogList(querylog);
	}
	
	@Override
	public List<FileDownLog> queryFileDownLogList(FileDownLog querylog) {
		return mapper.queryFileDownLogList(querylog);
	}
	

	@Override
	public List<VoucherLog> queryVoucherLog(VoucherLog queryLog) {
		return null;
	}

	@Override
	public Page<LoginLog> queryLoginLog(LoginLogVo vo) {

		Page<LoginLog> page =new Page<LoginLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<LoginLog> dataList =mapper.queryLoginLog(vo);
		int totalCount =mapper.countLoginLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
		
		}

	@Override
	public LoginLog getLoginLog(int id) {
		return mapper.getLoginLog(id);
	}

	@Override
	public boolean updLoginLog(LoginLog ll) {
		if(mapper.updLoginLog(ll)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delLoginLog(int id) {
		if(mapper.delLoginLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<SignLog> querySignLog(SignLogVo vo) {
		Page<SignLog> page =new Page<SignLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<SignLog> dataList =mapper.querySignLog(vo);
		int totalCount =mapper.countSignLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public SignLog getSignLog(int id) {
		return mapper.getSignLog(id);
	}

	@Override
	public boolean updSignLog(SignLog sl) {
		if(mapper.updSignLog(sl)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delSignLog(int id) {
		if(mapper.delSignLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<SmsLog> querySmsLog(SmsLogVo vo) {
		Page<SmsLog> page =new Page<SmsLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<SmsLog> dataList =mapper.querySmsLog(vo);
		int totalCount =mapper.countSmsLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public Page<EmailLog> queryEmailLog(EmailLogVo vo) {
		Page<EmailLog> page =new Page<EmailLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<EmailLog> dataList =mapper.queryEmailLog(vo);
		int totalCount =mapper.countEmailLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public Page<FileDownLog> queryFileDownLog(FileDownLogVo vo) {
		Page<FileDownLog> page =new Page<FileDownLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<FileDownLog> dataList =mapper.queryFileDownLog(vo);
		int totalCount =mapper.countFileDownLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public SmsLog getSmsLog(int id) {
		return mapper.getSmsLog(id);
	}
	
	@Override
	public EmailLog getEmailLog(int id) {
		return mapper.getEmailLog(id);
	}
	
	@Override
	public FileDownLog getFileDownLog(int id) {
		return mapper.getFileDownLog(id);
	}

	@Override
	public boolean updSmsLog(SmsLog sl) {
		if(mapper.updSmsLog(sl)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updEmailLog(EmailLog el) {
		if(mapper.updEmailLog(el)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updFileDownLog(FileDownLog fdl) {
		if(mapper.updFileDownLog(fdl)>0){
			return true;
		}
		return false;
	}
	 
	@Override
	public void addSmsLog(SmsLog sl){
		mapper.addSmsLog(sl);
	}
	
	@Override
	public void addEmailLog(EmailLog el){
		mapper.addEmailLog(el);
	}
	
	
	@Override
	public Page<AmountLog> queryAmountLog(AmountLogVo vo) {
		Page<AmountLog> page =new Page<AmountLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<AmountLog> dataList =mapper.queryAmountLog(vo);
		int totalCount =mapper.countAmountLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public AmountLog getAmountLog(int id) {
		return mapper.getAmountLog(id);
	}

	@Override
	public boolean updAmountLog(AmountLog al) {
		if(mapper.updAmountLog(al)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delAmountLog(int id) {
		if(mapper.delAmountLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<VoucherLog> queryAmountLog(VoucherLogVo vo) {
		Page<VoucherLog> page =new Page<VoucherLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<VoucherLog> dataList =mapper.queryVoucherLog(vo);
		int totalCount =mapper.countVoucherLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public VoucherLog getVoucherLog(int id) {
		return mapper.getVoucherLog(id);
	}

	@Override
	public boolean updVoucherLog(VoucherLog vl) {
		if(mapper.updVoucherLog(vl)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delVoucherLog(int id) {
		if(mapper.delVoucherLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<PointLog> queryPointLog(PointLogVo vo) {
		Page<PointLog> page =new Page<PointLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<PointLog> dataList =mapper.queryPointLog(vo);
		int totalCount =mapper.countPointLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public PointLog getPointLog(int id) {
		return mapper.getPointLog(id);
	}

	@Override
	public boolean updPointLog(PointLog pl) {
		if(mapper.updPointLog(pl)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delPointLog(int id) {
		if(mapper.delPointLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<CreditLog> queryCreditLog(CreditLogVo vo) {
		Page<CreditLog> page =new Page<CreditLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<CreditLog> dataList =mapper.queryCreditLog(vo);
		int totalCount =mapper.countCreditLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public CreditLog getCreditLog(int id) {
		return mapper.getCreditLog(id);
	}

	@Override
	public boolean updCreditLog(CreditLog cl) {
		if(mapper.updCreditLog(cl)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delCreditLog(int id) {
		if(mapper.delCreditLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ReputationLog> queryReputationLog(ReputationLogVo vo) {
		Page<ReputationLog> page =new Page<ReputationLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ReputationLog> dataList =mapper.queryReputationLog(vo);
		int totalCount =mapper.countReputationLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ReputationLog getReputationLog(int id) {
		return mapper.getReputationLog(id);
	}

	@Override
	public boolean updReputationLog(ReputationLog rl) {
		if(mapper.updReputationLog(rl)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delReputationLog(int id) {
		if(mapper.delReputationLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<PageViewLog> queryPageViewLog(PageViewLogVo vo) {
		Page<PageViewLog> page =new Page<PageViewLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<PageViewLog> dataList =mapper.queryPageViewLog(vo);
		int totalCount =mapper.countPageViewLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public PageViewLog getPageViewLog(long id) {
		return mapper.getPageViewLog(id);
	}

	@Override
	public boolean updPageViewLog(PageViewLog pvl) {
		if(mapper.updPageViewLog(pvl)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delPageViewLog(int id) {
		if(mapper.delPageViewLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<DeviceLog> queryDeviceLog(DeviceLogVo vo) {
		Page<DeviceLog> page =new Page<DeviceLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<DeviceLog> dataList =mapper.queryDeviceLog(vo);
		int totalCount =mapper.countDeviceLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public DeviceLog getDeviceLog(long id) {
		return mapper.getDeviceLog(id);
	}

	@Override
	public boolean updDeviceLog(DeviceLog dl) {
		if(mapper.updDeviceLog(dl)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delDeviceLog(long id) {
		if(mapper.delDeviceLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delSmsLog(int id) {
		if(mapper.delSmsLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delEmailLog(int id) {
		if(mapper.delEmailLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delFileDownLog(int id) {
		if(mapper.delFileDownLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<RoleAudit> queryRoleAudit(RoleAuditVo vo) {
		Page<RoleAudit> page =new Page<RoleAudit>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<RoleAudit> dataList =mapper.queryRoleAudit(vo);
		int totalCount =mapper.countRoleAudit(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public RoleAudit getRoleAudit(int id) {
		
		return mapper.getRoleAudit(id);
	}

	@Override
	public boolean updRoleAudit(RoleAudit ra) {
		
		if(mapper.updRoleAudit(ra)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean addRoleAudit(RoleAudit ra) {
		mapper.addRoleAudit(ra);
		return true;
	}

	@Override
	public boolean delRoleAudit(int id) {
		if(mapper.delRoleAudit(id)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public Page<AuditLog> queryAuditLog(AuditLogVo vo) {
		Page<AuditLog> page =new Page<AuditLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<AuditLog> dataList =mapper.queryAuditLog(vo);
		int totalCount =mapper.countAuditLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public AuditLog getAuditLog(int id) {

		return mapper.getAuditLog(id);
	}

	@Override
	public boolean updAuditLog(AuditLog al) {

		if(mapper.updAuditLog(al)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean addAuditLog(AuditLog al) {
		
		mapper.addAuditLog(al);
		return true;
	}

	@Override
	public boolean delAuditLog(int id) {
		if(mapper.delAuditLog(id)>0){
			return true;
		}
		return false;
	}

	
	
	
}
