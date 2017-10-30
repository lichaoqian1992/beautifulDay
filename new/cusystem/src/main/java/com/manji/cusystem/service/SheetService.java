package com.manji.cusystem.service;

import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.vo.sheets.AssignSheetVo;
import com.manji.cusystem.vo.sheets.ExamineSheetVo;
import com.manji.cusystem.vo.sheets.QuerySheetsVo;
import com.manji.cusystem.vo.sheets.SheetsVo;

/**
 * Created by Administrator on 2017/9/4.
 */
public interface SheetService {

    BaseResult addSheet(SheetsVo sheets, Account user);

    BaseResult updateSheet(SheetsVo sheets, Account user,String sheet_id);

    BaseResult findSheets(QuerySheetsVo vo,Account user);

    BaseResult findSheetCount(Account user);

    BaseResult findAllDept(Account user);

    BaseResult findDeptUser(String deptId,Account user);

    BaseResult findExaminePeople(Account user);

    BaseResult selectSheetDetail(Account user, String sheetId);

    BaseResult selectSheetLog(Account user, String sheetId);

    BaseResult submitSheet(Account user, String sheetId);

    BaseResult deleteSheet(Account user, String sheetId);

    BaseResult examineSheet(ExamineSheetVo vo, Account user);

    BaseResult saveOrSubmit(SheetsVo vo, Account user, String type);

    BaseResult remark(String sheetId, Account user, String remark);

    BaseResult backSheet(String reason, String sheetId, Account user,String type);

    BaseResult recoverSheet(String sheetId, Account user);

    BaseResult assignSheet(AssignSheetVo vo, Account user);

    BaseResult saveAssignSheet(SheetsVo sheets, Account user, String content);
}
