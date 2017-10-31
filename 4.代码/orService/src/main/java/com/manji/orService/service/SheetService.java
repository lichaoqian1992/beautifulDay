package com.manji.orService.service;


import com.github.pagehelper.PageInfo;
import com.manji.orService.dao.CountSheetDao;
import com.manji.orService.dao.JournalRecord;
import com.manji.orService.dao.SheetLogDao;
import com.manji.orService.dao.account.Account;
import com.manji.orService.vo.*;

import java.util.*;

public interface SheetService {

    boolean insertSheet(Map<String, Object> map, Account account, boolean submit);

    boolean updateSheet(Map<String, Object> map, Account account, boolean submit);

    PageInfo selectSheet(SelectSheetVo selectSheetVo,JurisdictionVo juris);

    HashMap<String, Object> selectSheetDetails(int id);

    boolean insertSheetLog(int descrType, int sheetId, String descrInfo, String contentInfo, Account account, HandleVo handleVo, String pics);

    boolean insertCcSend(String sendListId, int sheetId, Account account);

    boolean delSheet(int sheetId);

    boolean submitSheet(int sheetId, Account account, HandleVo handleVo);

    Boolean rejectSheet(int sheetId, String content, Account account);

    CountSheetDao countSheet(int orFounderId);

    boolean remark(RemarkVo remarkVo, Account account);

    boolean recovery(int id);

    Boolean upSendStatus(int sendId);

    boolean assign(AssignVo assignVo, Account account, boolean isReject);

    boolean close(RemarkVo remarkVo, Account account);

    JournalRecord journalRecord(int id);

    boolean examine(ExamineVo examineVo, Account account, boolean booSolve);

    boolean finishedProcess(AssignVo assignVo, Account account);

    boolean informations(int sheetId, int type, String sendList, String content, Account account);

    List<HashMap<String, Object>> selInformations(int userId);

    boolean clearInformations(int userId);

    List<HashMap<String, Object>> sheetSelf(Account account);

    SheetLogDao selectLog(int id);

    int isEdit(int sheetId, Account account);

    int isDel(int sheetId, Account account);

    int isSubmit(int sheetId, Account account);

    int isRecovery(int sheetId, Account account);

    int isAssign(int sheetId, Account account);

    int isClose(int sheetId, Account account);

    int isReset(int sheetId, Account account);

    int isExamine(int sheetId, Account account);

    int isFinishedProcess(int sheetId, Account account);

    int isReject(int sheetId, Account account);

    HashMap<String,Object> pushInfo(Account account);

    boolean clearInformationsOne(int id);


}
