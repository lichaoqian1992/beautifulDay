package com.manji.desystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manji.desystem.common.exception.BusinessDealException;
import com.manji.desystem.common.util.FastJsonUtil;
import com.manji.desystem.dao.Personnel;
import com.manji.desystem.dao.Reason;
import com.manji.desystem.feignInterface.DataHostInterface;
import com.manji.desystem.feignInterface.LoginInterface;
import com.manji.desystem.feignInterface.SheetInterface;
import com.manji.desystem.mapper.SheetMapper;
import com.manji.desystem.service.FeignSheetService;
import com.manji.desystem.vo.AssignVo;
import com.manji.desystem.vo.CommonSheetVo;
import com.manji.desystem.vo.ExamineVo;
import com.manji.desystem.vo.SelectSheetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeignSheetServiceImpl implements FeignSheetService {

    @Autowired
    private SheetInterface sheetInterface;

    @Autowired
    private LoginInterface loginInterface;

    @Autowired
    private DataHostInterface dataHostInterface;

    @Autowired
    private SheetMapper sheetMapper;

    @Override
    public JSONObject insertSheet(CommonSheetVo commonSheetVo, String sessionId) throws Exception {
        return sheetInterface.insertSheet(commonSheetVo, sessionId);
    }

    @Override
    public JSONObject updateSheet(CommonSheetVo commonSheetVo, String sessionId) throws Exception {
        return sheetInterface.updateSheet(commonSheetVo, sessionId);
    }

    @Override
    public JSONObject selectSheet(SelectSheetVo selectSheetVo, String sessionId) throws Exception {
        return sheetInterface.selectSheet(selectSheetVo, sessionId);
    }

    @Override
    public JSONObject selectSheetDetails(int id, Integer sendId, String sessionId) throws Exception {

        JSONObject object = sheetInterface.selectSheetDetails(id, sendId, sessionId);
        HashMap<String, Object> map = null;

        if (object.get("datas") != null) {

            map = (HashMap<String, Object>) object.get("datas");
            Personnel personnel = new Personnel();
            List<Personnel> sendList = new ArrayList<Personnel>();

            String sendIdList = map.get("sendId").toString();
            Integer personId = Integer.parseInt(map.get("personId").toString());
            JSONObject listPerson = loginInterface.findByUserId(personId).getJSONObject(0);

            if (listPerson.size() > 0) {
                personnel = FastJsonUtil.getSingleBean(listPerson.toJSONString(), Personnel.class);
                personnel.setId(personId);
            }

            if (sendIdList != null && !sendIdList.equals("")) {
                String[] sends = sendIdList.split(",");
                for (String send : sends) {
                    JSONObject sendPersonnel = loginInterface.findByUserId(Integer.parseInt(send)).getJSONObject(0);
                    Personnel child = FastJsonUtil.getSingleBean(sendPersonnel.toJSONString(), Personnel.class);
                    child.setId(Integer.parseInt(send));
                    sendList.add(child);
                }
            }

            map.put("personList", personnel);
            map.put("sendList", sendList);

        }

        return object;
    }

    @Override
    public JSONObject delSheet(int id, String sessionId) throws Exception {
        return sheetInterface.delSheet(id, sessionId);
    }

    @Override
    public JSONObject submitSheet(int id, String sessionId) throws Exception {
        return sheetInterface.submitSheet(id, sessionId);
    }

    @Override
    public JSONObject countSheet(String sessionId) throws Exception {
        return sheetInterface.countSheet(sessionId);
    }

    @Override
    public JSONObject saveSubmitSheet(CommonSheetVo commonSheetVo, Integer type, String sessionId) throws Exception {

        JSONObject object = null;
        if (type == 1) {
            if (commonSheetVo.getOrId() == null) {
                object = sheetInterface.insertSheet(commonSheetVo, sessionId);
            } else {
                if (commonSheetVo.getOrId().equals("")) {
                    object = sheetInterface.insertSheet(commonSheetVo, sessionId);
                }
                object = sheetInterface.updateSheet(commonSheetVo, sessionId);
            }
        } else if (type == 2) {
            object = sheetInterface.saveSubmitSheet(commonSheetVo, sessionId);
        }

        return object;
    }

    @Override
    public JSONObject remark(int id, String content, String sessionId, String pics) throws Exception {
        return sheetInterface.remark(id, content, sessionId, pics);
    }

    @Override
    public JSONObject recovery(int id, String sessionId) throws Exception {
        return sheetInterface.recovery(id, sessionId);
    }

    @Override
    public JSONObject assign(AssignVo assignVo, String sessionId) throws Exception {
        return sheetInterface.assign(assignVo, sessionId);
    }

    @Override
    public JSONObject close(int id, String content, String sessionId) throws Exception {
        return sheetInterface.close(id, content, sessionId);
    }

    @Override
    public JSONObject reset(int id, String content, String sessionId) throws Exception {
        return sheetInterface.reset(id, content, sessionId);
    }

    @Override
    public JSONObject journalRecord(int id) throws Exception {
        return sheetInterface.journalRecord(id);
    }

    @Override
    public JSONObject examine(ExamineVo examineVo, String sessionId) throws Exception {
        return sheetInterface.examine(examineVo, sessionId);
    }

    @Override
    public JSONObject finishedProcess(AssignVo assignVo, String sessionId) throws Exception {
        return sheetInterface.assign(assignVo, sessionId);
    }

    @Override
    public JSONObject rejectSheet(int id, String content, String sessionId) throws Exception {
        return sheetInterface.rejectSheet(id, content, sessionId);
    }

    @Override
    public JSONObject saveAssign(CommonSheetVo commonSheetVo, String sessionId) throws Exception {
        return sheetInterface.saveAssign(commonSheetVo, sessionId);
    }

    @Override
    public JSONObject selInformations(String sessionId) throws Exception {
        return sheetInterface.selInformations(sessionId);
    }

    @Override
    public JSONObject clearInformations(String sessionId) throws Exception {
        return sheetInterface.clearInformations(sessionId);
    }

    @Override
    public JSONObject sheetSelf(String sessionId) throws Exception {
        return sheetInterface.sheetSelf(sessionId);
    }

    @Override
    public JSONObject pushInfo(String sessionId) throws Exception {
        return sheetInterface.pushInfo(sessionId);
    }

    @Override
    public JSONObject clearInformationsOne(Integer informaId) throws Exception {
        return sheetInterface.clearInformationsOne(informaId);
    }

    @Override
    public List<Reason> findFirstReason() throws Exception {

        List<Reason> reason = sheetMapper.findFirstReason();

        return reason;
    }

    @Override
    public List<Reason> findSecondReason(Integer id) throws Exception {

        String code = sheetMapper.findSecondCode(id);

        code = code + "_%";

        List<Reason> reason = sheetMapper.findSecondReason(code);

        return reason;
    }

    @Override
    public JSONObject findCustomer(String userName, String mobile, String shopName, String userType) {

        JSONObject back_userInfo = null;
        String cus_tel = "";
        String person_name = "";
        String reg_time = "";
        String area = "";

        if (userType.equals("Buyer")) {
            back_userInfo = dataHostInterface.getAccountByName(userName, mobile, shopName);//用户信息
            if (back_userInfo.getJSONObject("result") != null) {
                cus_tel = back_userInfo.getJSONObject("result").get("mobile") == null ? "--" : back_userInfo.getJSONObject("result").get("mobile").toString();
                person_name = back_userInfo.getJSONObject("result").get("person_name") == null ? "--" : back_userInfo.getJSONObject("result").get("person_name").toString();
                area = back_userInfo.getJSONObject("result").get("area") == null ? "--" : back_userInfo.getJSONObject("result").get("area").toString();
            }
        } else if (userType.equals("Shop")) {
            back_userInfo = dataHostInterface.getShopInfo(userName, mobile, shopName);//商家信息
            if (back_userInfo.getJSONObject("result") != null) {
                cus_tel = back_userInfo.getJSONObject("result").get("shop_mobile") == null ? "--" : back_userInfo.getJSONObject("result").get("shop_mobile").toString();
                person_name = back_userInfo.getJSONObject("result").get("name") == null ? "--" : back_userInfo.getJSONObject("result").get("name").toString();
                area = back_userInfo.getJSONObject("result").get("area") == null ? "--" : back_userInfo.getJSONObject("result").get("area").toString();
            }
        } else if (userType.equals("Agent")) {
            back_userInfo = dataHostInterface.getAgentInfo(userName, mobile, shopName);//合伙人信息
            if (back_userInfo.getJSONObject("result") != null) {
                cus_tel = back_userInfo.getJSONObject("result").get("telephone") == null ? "--" : back_userInfo.getJSONObject("result").get("telephone").toString();
                person_name = back_userInfo.getJSONObject("result").get("person_name") == null ? "--" : back_userInfo.getJSONObject("result").get("person_name").toString();
                reg_time = back_userInfo.getJSONObject("result").get("add_time") == null ? "--" : back_userInfo.getJSONObject("result").get("add_time").toString();
                area = back_userInfo.getJSONObject("result").get("agent_area") == null ? "--" : back_userInfo.getJSONObject("result").get("agent_area").toString();
            }
        } else if (userType.equals("other")) {
            back_userInfo = dataHostInterface.getOtherInfo(mobile);//合伙人信息
            if (back_userInfo.getJSONObject("result") != null) {
                cus_tel = back_userInfo.getJSONObject("result").get("mobile") == null ? "--" : back_userInfo.getJSONObject("result").get("mobile").toString();
                person_name = back_userInfo.getJSONObject("result").get("user_name") == null ? "--" : back_userInfo.getJSONObject("result").get("user_name").toString();
                area = back_userInfo.getJSONObject("result").get("cus_area") == null ? "--" : back_userInfo.getJSONObject("result").get("cus_area").toString();
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (back_userInfo.getJSONObject("result") != null) {
            map = back_userInfo.getJSONObject("result");

            map.put("cus_tel", cus_tel);
            map.put("person_name", person_name);
            map.put("reg_time", reg_time);
            map.put("area", area);
        }

        if (back_userInfo.get("code").toString().equals("200")) {
            back_userInfo = (JSONObject) JSONObject.toJSON(map);
        } else {
            throw new BusinessDealException(back_userInfo.get("message").toString());
        }

        return back_userInfo;
    }

    @Override
    public Object getUserNameByPersonName(String personName) {

        JSONObject back = dataHostInterface.getUserNameByPersonName(personName);
        Object object = null;

        if (back.get("code").toString().equals("200")) {
            object = back.get("result");
        } else {
            throw new BusinessDealException(back.get("message").toString());
        }

        return object;
    }

}