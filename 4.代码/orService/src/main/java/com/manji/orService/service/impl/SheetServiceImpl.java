package com.manji.orService.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manji.orService.dao.CountSheetDao;
import com.manji.orService.dao.JournalRecord;
import com.manji.orService.dao.SheetDao;
import com.manji.orService.dao.SheetLogDao;
import com.manji.orService.dao.account.Account;
import com.manji.orService.dao.account.Role;
import com.manji.orService.mapper.SheetMapper;
import com.manji.orService.service.SheetService;
import com.manji.orService.vo.*;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SheetServiceImpl implements SheetService {

    @Autowired  SheetMapper sheetMapper;

    private int descrType=0;
    private int sheetId=0;
    private String descrInfo="";
    private String contentInfo="";

    /**
     * 新增工单
     * @param map
     * @param account
     * @return
     */

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean insertSheet(Map<String,Object> map,Account account,boolean submit){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();



        InfoVo infoVo=(InfoVo) map.get("infoVo");
        SheetVo sheetVo=(SheetVo) map.get("sheetVo");
        HandleVo handleVo=(HandleVo) map.get("handleVo");
        ConSheetVo conSheetVo=new ConSheetVo();

        sheetVo.setOrFounderId(account.getUser().getId());

        //赋值部门
        String dept="";
        List<Role> role=account.getRole();
        for(Role x: role){
            dept+=x.getDept_name();
        }

        sheetVo.setOrFounderDept(dept);


        boolean infoVoBoo=sheetMapper.insertInfo(infoVo);//新增内容
        if(!infoVoBoo){
            return false;
        }

        boolean sheetVoBoo=sheetMapper.insertSheet(sheetVo);//新增工单
        if(!sheetVoBoo){
            return false;
        }

        conSheetVo.setCusCid(sheetVo.getOrId());
        conSheetVo.setCusInfoId(infoVo.getCusId());
        conSheetVo.setCusType("0");
        conSheetVo.setCusIsdel("1");
        boolean conSheetVoBoo=sheetMapper.insertConSheet(conSheetVo);//新增关联关系
        if(!conSheetVoBoo){
            return false;
        }

        sheetId=Integer.parseInt(sheetVo.getOrId());
        descrType=1;
        descrInfo="创建工单";
        contentInfo="";
        boolean booLog=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,handleVo,"");//记录日志
        if(!booLog){
            return false;
        }

        if(submit){
            int sheetId=Integer.parseInt(sheetVo.getOrId());
            boo=AopService.submitSheet(sheetId,account,handleVo);
            if(!boo){
                return false;
            }
        }

        return boo;
    }

    /**
     * 修改工单
     * @param map
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean updateSheet(Map<String,Object> map,Account account,boolean submit){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();

        InfoVo infoVo=(InfoVo) map.get("infoVo");
        SheetVo sheetVo=(SheetVo) map.get("sheetVo");
        HandleVo handleVo=(HandleVo) map.get("handleVo");
        ConSheetVo conSheetVo=new ConSheetVo();

        conSheetVo.setCusCid(sheetVo.getOrId());

        ConSheetVo conSheetInfo=sheetMapper.slConSheetDao(conSheetVo);//判断有没有此工单
        if(conSheetInfo==null){
            return false;
        }

        infoVo.setCusId(conSheetInfo.getCusInfoId());

        boolean infoVoBoo=sheetMapper.updateInfo(infoVo);//修改内容
        boolean sheetVoBoo=sheetMapper.updateSheet(sheetVo);//修改工单

        if(sheetVoBoo!=true || infoVoBoo!=true){
            return false;
        }

        sheetId=Integer.parseInt(sheetVo.getOrId());
        descrType=2;
        descrInfo="修改工单";
        contentInfo="";
        boolean booLog=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,handleVo,"");//记录日志
        if(!booLog){
            return false;
        }

        if(submit){
            int sheetId=Integer.parseInt(sheetVo.getOrId());//提交
            boo=AopService.submitSheet(sheetId,account,handleVo);
            if(!boo){
                return false;
            }

        }

        return boo;
    }

    /**
     * 查询工单
     * @param selectSheetVo
     * @return
     */
    @Override
    public PageInfo selectSheet(SelectSheetVo selectSheetVo,JurisdictionVo juris) {

        int pageNum=Integer.parseInt(selectSheetVo.getPageNum());
        int pageSize=Integer.parseInt(selectSheetVo.getPageSize());
        juris.setSelectSheetVo(selectSheetVo);

        PageHelper.startPage(pageNum,pageSize);

        List<HashMap<String,Object>> hashMapList=null;

        if(selectSheetVo.getOrPlate().equals("sendshet")){
            hashMapList=sheetMapper.selectSend(selectSheetVo);
        }else if(selectSheetVo.getOrPlate().equals("alshet")){
            hashMapList=sheetMapper.selectSheetAll(juris);
        }else if(selectSheetVo.getOrPlate().equals("pendshet") || selectSheetVo.getOrPlate().equals("dealshet") || selectSheetVo.getOrPlate().equals("rejeshet") || selectSheetVo.getOrPlate().equals("ovfishet") || selectSheetVo.getOrPlate().equals("deleshet")){
            hashMapList=sheetMapper.selectSheet(selectSheetVo);
        }

        PageInfo pageInfo = new PageInfo(hashMapList);

        return pageInfo;
    }

    /**
     * 查询工单详情
     * @param id
     * @return
     */
    @Override
    public HashMap<String,Object> selectSheetDetails(int id) {

        HashMap<String,Object> hashMapList=sheetMapper.selectSheetDetails(id);

        sheetMapper.clearInformationsSheetId(id);

        return hashMapList;
    }


    /**
     * 新增日志记录
     * @param descrType
     * @param sheetId
     * @param descrInfo
     * @param contentInfo
     * @param account
     * @param handleVo
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.MANDATORY)
    public boolean insertSheetLog(int descrType , int sheetId , String descrInfo , String contentInfo , Account account,HandleVo handleVo,String pics) {

        boolean boo=true;
        SimpleDateFormat Time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SheetLogVo sheetLogVo = new SheetLogVo();
        sheetLogVo.setOrSheetId(String.valueOf(sheetId));
        sheetLogVo.setOrPersonId(account.getUser().getId());
        sheetLogVo.setOrPerson(account.getUser().getVsername());
        String dept="";
        List<Role> role=account.getRole();
        for(Role x: role){
            dept+=x.getDept_name();
        }
        sheetLogVo.setOrPersonDept(dept);
        if(handleVo!=null){
            sheetLogVo.setOrHandleId(handleVo.getOrHandleId());
            sheetLogVo.setOrHandle(handleVo.getOrHandle());
            sheetLogVo.setOrHandleDept(handleVo.getOrHandleDept());
        }
        sheetLogVo.setOrOperationTime(Time.format(new Date()));
        sheetLogVo.setOrOperationTypeId(String.valueOf(descrType));
        sheetLogVo.setOrOperationTypeDescr(descrInfo);
        sheetLogVo.setOrOperationContent(contentInfo);
        sheetLogVo.setOrOperationPics(pics);

        boolean booLog=sheetMapper.insertSheetLog(sheetLogVo);
        if(!booLog){
           return false;
        }

        return boo;
    }

    /**
     * 新增抄送信息
     * @param sendListId
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.MANDATORY)
    public boolean insertCcSend(String sendListId,int sheetId,Account account) {

        boolean boo=true;

        if(!sendListId.equals("") && sendListId!=null ){
            SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();
            SimpleDateFormat Time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            CcSendVo ccSendVo = new CcSendVo();
            ccSendVo.setOrCcSheetId(String.valueOf(sheetId));
            ccSendVo.setOrCcTime(Time.format(new Date()));
            ccSendVo.setOrCcPersonId(account.getUser().getId());
            ccSendVo.setOrCcPersonName(account.getUser().getVsername());

            String dept="";
            List<Role> role=account.getRole();
            for(Role x: role){
                dept+=x.getDept_name();
            }
            ccSendVo.setOrCcPersonDept(dept);

            if(sendListId!=null){
                String[] sendIds = sendListId.split(",");
                for(String sendId : sendIds){
                    ccSendVo.setOrCcId(sendId);
                    boolean booSend=sheetMapper.insertCcSend(ccSendVo);
                    if(!booSend){
                        return false;
                    }
                }
            }

            boo=AopService.informations(sheetId,1,sendListId,"",account);//发送站内信息

            if(!boo){
                return boo;
            }
        }

        return boo;
    }

    /**
     * 删除工单
     * @param sheetId
     * @return
     */
    @Override
    @Transactional
    public boolean delSheet(int sheetId){

        boolean boo=true;

        ConSheetVo conSheetVo=new ConSheetVo();

        conSheetVo.setCusCid(String.valueOf(sheetId));
        conSheetVo.setCusType("0");

        ConSheetVo conSheetInfo=sheetMapper.slConSheetDao(conSheetVo);//判断有没有此工单
        if(conSheetInfo==null){
            return false;
        }

        SheetDao sheetDao=sheetMapper.selectSheetId(sheetId);
        if(sheetDao.getOrSheetStatus()==6){

            boolean delSheet=sheetMapper.delSheet(Integer.parseInt(conSheetInfo.getCusCid()));
            boolean delInfo=sheetMapper.delInfo(Integer.parseInt(conSheetInfo.getCusInfoId()));
            boolean delCon=sheetMapper.delCon(Integer.parseInt(conSheetInfo.getCusId()));

            if(delSheet==false || delInfo==false  || delCon==false ){
                return false;
            }

        }else if(sheetDao.getOrSheetStatus()==1){
            conSheetInfo.setCusIsdel("0");
            boo=sheetMapper.delSheetUp(conSheetInfo);

            if(!boo){
                return boo;
            }

            SheetVo sheetVo=new SheetVo();
            sheetVo.setOrId(String.valueOf(sheetId));
            sheetVo.setOrSheetStatus("6");

            boo=sheetMapper.updateSheet(sheetVo);

            if(!boo){
                return boo;
            }

        }else{
            return false;
        }

        return boo;
    }

    /**
     * 提交工单
     * @param sheetId
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean submitSheet(int sheetId,Account account,HandleVo handleVo){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();

        SheetVo sheetVo=new SheetVo();
        sheetVo.setOrId(String.valueOf(sheetId));
        sheetVo.setOrPersonId(handleVo.getOrHandleId());
        sheetVo.setOrSheetStatus("2");
        boo=sheetMapper.updateSheet(sheetVo);//修改工单状态
        if(!boo){
            return boo;
        }

        SheetDao sheetDao = sheetMapper.selectSheetId(sheetId);

        boo=insertCcSend(sheetDao.getOrSendList(),sheetId,account);//新增抄送信息
        if(!boo){
            return boo;
        }

        descrType=3;
        descrInfo="指派工单给"+handleVo.getOrHandle()+"<"+handleVo.getOrHandleDept()+">";
        contentInfo="";
        boo=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,handleVo,"");//记录日志
        if(!boo){
            return boo;
        }

        boo=AopService.informations(sheetId,2,null,"",account);//发送站内信息

        if(!boo){
            return boo;
        }

        return boo;
    }


    /**
     * 驳回工单
     * @param sheetId
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Boolean rejectSheet(int sheetId,String content,Account account){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();
        HandleVo handleVo=new HandleVo();

        SheetLogDao sheetLogDao=sheetMapper.selectSheetlog(sheetId,3);
        if(sheetLogDao==null){
            return false;
        }

        SheetVo sheetVo=new SheetVo();
        sheetVo.setOrId(String.valueOf(sheetId));
        sheetVo.setOrPersonId(String.valueOf(sheetLogDao.getOrPersonId()));
        sheetVo.setOrSheetStatus("5");
        boo=sheetMapper.updateSheet(sheetVo);//修改工单状态
        if(!boo){
            return boo;
        }

        handleVo.setOrHandle(sheetLogDao.getOrPerson());
        handleVo.setOrHandleId(String.valueOf(sheetLogDao.getOrPersonId()));
        handleVo.setOrHandleDept(sheetLogDao.getOrHandleDept());

        descrType=7;
        descrInfo="驳回工单";
        contentInfo=content;
        boo=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,handleVo,"");//记录日志
        if(!boo){
            return boo;
        }

        boo=AopService.informations(sheetId,4,null,"",account);//发送站内信息

        if(!boo){
            return boo;
        }


        return boo;
     }

    /**
     * 查询所有工单数量
     * @return
     */
    @Override
    public CountSheetDao countSheet(int orFounderId){

        List<SheetStatusVo>  countList=sheetMapper.countSheet(orFounderId);
        List<SheetStatusVo>  countList2=sheetMapper.countSheet2(orFounderId);
        List<SheetStatusVo>  countList3=sheetMapper.countSheet3(orFounderId);

        CountSheetDao countSheetDao=new CountSheetDao();

        for(SheetStatusVo x : countList){
            if(x.getStatus()==2){
                countSheetDao.setDealshetCount(x.getCount());
            }
            if(x.getStatus()==3){
                countSheetDao.setOvfishetCount(x.getCount());
            }
            if(x.getStatus()==5){
                countSheetDao.setRejeshetCount(x.getCount());
            }
        }

        for(SheetStatusVo x : countList2){
            if(x.getStatus()==1){
                countSheetDao.setPendshetCount(x.getCount());
            }
        }

        for(SheetStatusVo x : countList3){
            countSheetDao.setSendshetCount(x.getCount());
        }

        return countSheetDao;
    }

    /**
     * 备注
     * @param remarkVo
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean remark(RemarkVo remarkVo,Account account){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();

        descrType=6;
        sheetId=Integer.parseInt(remarkVo.getId());
        descrInfo="添加备注";
        contentInfo=remarkVo.getContent();

        boo=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,null,remarkVo.getPics());//记录日志
        if(!boo){
            return boo;
        }

        boo=AopService.informations(sheetId,3,null,contentInfo,account);//发送站内信息

        if(!boo){
            return boo;
        }


        return boo;
    }

    /**
     * 恢复工单
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean recovery(int id){

        boolean boo=true;
        SheetVo sheetVo=new SheetVo();
        sheetVo.setOrId(String.valueOf(id));
        sheetVo.setOrSheetStatus("1");

        boo=sheetMapper.updateSheet(sheetVo);//修改工单状态
        if(!boo){
            return boo;
        }

        boo=sheetMapper.recovery(id);//修改中间表关系
        if(!boo){
            return boo;
        }

        return boo;
    }


    /**
     * 修改抄送信息状态(查看)
     * @param sendId
     * @return
     */
    @Override
    @Transactional
    public Boolean upSendStatus(int sendId){

        boolean sendStatus=sheetMapper.upSendStatus1(sendId);

        return sendStatus;
    }

    /**
     * 指派工单
     * @param assignVo
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean assign( AssignVo assignVo,Account account,boolean isReject){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();

        SheetVo sheetVo=new SheetVo();
        HandleVo handleVo=new HandleVo();

        /*储存SheetVo*/
        sheetVo.setOrId(assignVo.getId());
        sheetVo.setOrPersonId(assignVo.getOrHandleId());
        sheetVo.setOrSendList(assignVo.getOrSendList());

        if(isReject){
            sheetVo.setOrSheetStatus("2");
        }

        /*储存HandleVo*/
        handleVo.setOrHandleId(assignVo.getOrHandleId());
        handleVo.setOrHandle(assignVo.getOrHandle());
        handleVo.setOrHandleDept(assignVo.getOrHandleDept());


        boo=sheetMapper.updateSheet(sheetVo);//修改sheet表数据
        if(!boo){
            return boo;
        }

        boo=AopService.insertCcSend(sheetVo.getOrSendList(),Integer.parseInt(sheetVo.getOrId()),account);//新增抄送信息

        if(!boo){
            return boo;
        }

        sheetId=Integer.parseInt(sheetVo.getOrId());
        descrType=3;
        descrInfo="指派工单给"+handleVo.getOrHandle()+"<"+handleVo.getOrHandleDept()+">";
        contentInfo=assignVo.getContent();

        boo=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,handleVo,"");//记录日志
        if(!boo){
            return boo;
        }

        boo=AopService.informations(sheetId,2,null,"",account);//发送站内信息

        if(!boo){
            return boo;
        }

        return boo;
    }

    /**
     * 关闭工单
     * @param remarkVo
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean close(RemarkVo remarkVo,Account account){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();

        SheetVo sheetVo=new SheetVo();

        sheetVo.setOrId(remarkVo.getId());
        sheetVo.setOrSheetStatus("4");

        boo=sheetMapper.updateSheet(sheetVo);//修改sheet表数据
        if(!boo){
            return boo;
        }

        sheetId=Integer.parseInt(remarkVo.getId());
        descrType=5;
        descrInfo="关闭工单";
        contentInfo=remarkVo.getContent();

        boo=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,null,"");//记录日志
        if(!boo){
            return boo;
        }

        return boo;
    }

    /**
     * 查询工单日志
     * @param id
     * @return
     */
    @Override
    public JournalRecord journalRecord(int id){

        JournalRecord journal=new JournalRecord();

        List<HashMap<String,Object>> detailsSheetRecord=null;

        detailsSheetRecord=sheetMapper.detailsSheetRecord(id,"status");//状态
        journal.setStatusRecords(detailsSheetRecord);

        detailsSheetRecord=sheetMapper.detailsSheetRecord(id,"memo");//备注
        journal.setMemoRecords(detailsSheetRecord);

        detailsSheetRecord=sheetMapper.detailsSheetRecord(id,"");//所有
        journal.setAllRecords(detailsSheetRecord);

        return journal;
    }

    /**
     * 审核工单
     * @param examineVo
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean examine(ExamineVo examineVo,Account account,boolean booSolve){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();

        SheetVo sheetVo=new SheetVo();
        HandleVo handleVo=new HandleVo();

        if(booSolve){
            sheetVo.setOrId(examineVo.getId());
            sheetVo.setOrSheetStatus("4");

            boo=sheetMapper.updateSheet(sheetVo);//修改sheet表数据
            if(!boo){
                return boo;
            }

            sheetId=Integer.parseInt(examineVo.getId());
            descrType=5;
            descrInfo="关闭工单";
            contentInfo=examineVo.getExamineResult()+"<br/>"+
                    "客户的问题是否得到解决？："+examineVo.getIsSolve()+"<br/>"+
                    "客户对我们的服务是否满意？："+examineVo.getServiceEvaluation()+"<br/>"+
                    examineVo.getContent();

            boo=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,null,"");//记录日志
            if(!boo){
                return boo;
            }
        }else{
            SheetLogDao sheetLogDao=sheetMapper.selectSheetlog(Integer.parseInt(examineVo.getId()),4);//获取提交给彭怡的人员

            if(sheetLogDao==null){
                return false;
            }

            sheetVo.setOrId(examineVo.getId());
            sheetVo.setOrPersonId(String.valueOf(sheetLogDao.getOrPersonId()));

            boo=sheetMapper.updateSheet(sheetVo);//修改sheet表数据
            if(!boo){
                return boo;
            }

            sheetId=Integer.parseInt(examineVo.getId());
            descrType=8;
            descrInfo="审核未通过";
            contentInfo=examineVo.getExamineResult()+"<br/>"+
                    "客户的问题是否得到解决？："+examineVo.getIsSolve()+"<br/>"+
                    "客户对我们的服务是否满意？："+examineVo.getServiceEvaluation()+"<br/>"+
                    examineVo.getContent();

            handleVo.setOrHandleId(String.valueOf(sheetLogDao.getOrPersonId()));
            handleVo.setOrHandle(sheetLogDao.getOrPerson());
            handleVo.setOrHandleDept(sheetLogDao.getOrHandleDept());

            boo=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,handleVo,"");//记录日志
            if(!boo){
                return boo;
            }

            boo=AopService.informations(sheetId,2,null,"",account);//发送站内信息

            if(!boo){
                return boo;
            }

        }

        return boo;
    }

    /**
     * 处理完毕
     * @param assignVo
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean finishedProcess( AssignVo assignVo,Account account){

        boolean boo=true;
        SheetServiceImpl AopService=(SheetServiceImpl)AopContext.currentProxy();

        SheetVo sheetVo=new SheetVo();
        HandleVo handleVo=new HandleVo();

        sheetVo.setOrId(assignVo.getId());
        sheetVo.setOrSheetStatus("3");
        sheetVo.setOrPersonId(assignVo.getOrHandleId());

        boo=sheetMapper.updateSheet(sheetVo);//修改sheet表数据
        if(!boo){
            return boo;
        }

        sheetId=Integer.parseInt(assignVo.getId());
        descrType=4;
        descrInfo="处理完毕";
        contentInfo=assignVo.getContent();

        handleVo.setOrHandle(assignVo.getOrHandle());
        handleVo.setOrHandleId(assignVo.getOrHandleId());
        handleVo.setOrHandleDept(assignVo.getOrHandleDept());

        boo=AopService.insertSheetLog(descrType,sheetId,descrInfo,contentInfo,account,handleVo,assignVo.getPics());//记录日志
        if(!boo){
            return boo;
        }

        boo=AopService.informations(sheetId,2,null,"",account);//发送站内信息

        if(!boo){
            return boo;
        }

        return boo;
    }


    /**
     * 新增站内信息
     * @param sheetId
     * @param content
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.MANDATORY)
    public boolean informations(int sheetId,int type,String sendList,String content,Account account) {

        boolean boo=true;
        InformationsVo informationsVo=new InformationsVo();
        SimpleDateFormat Time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SheetDao sheetDao=sheetMapper.selectSheetId(sheetId);//查询工单

        if(sheetDao==null){
            return false;
        }

        if(sendList!=null && type==1){
            String[] sendIds = sendList.split(",");
            for(String sendId : sendIds){
                informationsVo.setOrSheetId(sheetId);
                informationsVo.setOrHandleId(Integer.parseInt(sendId));
                informationsVo.setOrPerson(account.getUser().getVsername());

                String dept="";
                List<Role> role=account.getRole();
                for(Role x: role){
                    dept+=x.getDept_name();
                }

                informationsVo.setOrPersonDept(dept);
                informationsVo.setOrPersonId(Integer.parseInt(account.getUser().getId()));
                informationsVo.setOrInformationsTime(Time.format(new Date()));
                informationsVo.setOrRelevantContent(content);
                informationsVo.setOrInformationsType(type);

                boo=sheetMapper.insertInformations(informationsVo);
                if(!boo){
                    return boo;
                }
            }
        }else if(sendList==null && type!=1){
            informationsVo.setOrSheetId(sheetId);
            informationsVo.setOrHandleId(sheetDao.getOrPersonId());
            informationsVo.setOrPerson(account.getUser().getVsername());

            String dept="";
            List<Role> role=account.getRole();
            for(Role x: role){
                dept+=x.getDept_name();
            }

            informationsVo.setOrPersonDept(dept);
            informationsVo.setOrPersonId(Integer.parseInt(account.getUser().getId()));
            informationsVo.setOrInformationsTime(Time.format(new Date()));
            informationsVo.setOrRelevantContent(content);
            informationsVo.setOrInformationsType(type);

            boo=sheetMapper.insertInformations(informationsVo);

            if(!boo){
                return boo;
            }
        }


        return boo;
    }


    /**
     * 查看站内信息
     * @param userId
     */
    @Override
    public List<HashMap<String,Object>> selInformations(int userId){

        List<HashMap<String,Object>> map=null;
        map=sheetMapper.selInformations(1,userId);

        return map;
    }

    /**
     * 清除站内信息
     * @param userId
     */
    @Override
    @Transactional
    public boolean clearInformations(int userId){

        boolean boo=true;

        boo=sheetMapper.clearInformations(userId);

        return boo;
    }

    /**
     * 查询自己的工单
     * @param account
     * @return
     */
    @Override
    public List<HashMap<String,Object>> sheetSelf(Account account){

        List<HashMap<String,Object>> map=null;
        int userId=Integer.parseInt(account.getUser().getId());
        String dept="";

        List<Role> role=account.getRole();
        for(Role x: role){
            dept+=x.getDept_name();
        }

        map=sheetMapper.sheetSelf(userId,dept);

        return map;
    }

    /**
     * 推送信息查看
     * @param account
     * @return
     */
    @Override
    public HashMap<String,Object> pushInfo(Account account){

        int userId=Integer.parseInt(account.getUser().getId());

        HashMap<String,Object> map=sheetMapper.pushInfo(userId);

        return map;
    }

    /**
     * 关闭推送信息
     * @param id
     * @return
     */
    @Override
    public boolean clearInformationsOne(int id) {

        boolean boo=sheetMapper.clearInformationsOne(id);

        return boo;
    }


    /*******************************************************/


    /**
     * 查询最后一次修改工单的记录
     * @param id
     * @return
     */
    @Override
    public SheetLogDao selectLog(int id){

        SheetLogDao sheetLogDao=sheetMapper.selectLog(id);
        return sheetLogDao;
    }



    /**
     * 判断是否可以编辑
     * @param sheetId
     * @return
     */
    @Override
    public int isEdit(int sheetId,Account account){

        String dept="";
        int userId=Integer.parseInt(account.getUser().getId());

        List<Role> role=account.getRole();
        for(Role x: role){
            dept+=x.getDept_name();
        }

        int count=sheetMapper.isEdit(sheetId,dept,userId);

        return count;
    }

    /**
     * 判断是否可以删除
     * @param sheetId
     * @return
     */
    @Override
    public int isDel(int sheetId,Account account){

        String dept="";
        int userId=Integer.parseInt(account.getUser().getId());

        List<Role> role=account.getRole();
        for(Role x: role){
            dept+=x.getDept_name();
        }

        int count=sheetMapper.isDel(sheetId,dept,userId);

        return count;
    }

    /**
     * 是否可以提交
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    public int isSubmit(int sheetId,Account account){

        String dept="";
        int userId=Integer.parseInt(account.getUser().getId());

        List<Role> role=account.getRole();
        for(Role x: role){
            dept+=x.getDept_name();
        }

        int count=sheetMapper.isOperation(sheetId,dept,userId,1);

        return count;
    }

    /**
     * 是否可以恢复
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    public int isRecovery(int sheetId,Account account){

        String dept="";
        int userId=Integer.parseInt(account.getUser().getId());

        List<Role> role=account.getRole();
        for(Role x: role){
            dept+=x.getDept_name();
        }

        int count=sheetMapper.isOperation(sheetId,dept,userId,6);

        return count;
    }

    /**
     * 是否可以指派
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    public int isAssign(int sheetId,Account account){

        int userId=Integer.parseInt(account.getUser().getId());

        int count=sheetMapper.isAssign(sheetId,userId);

        return count;
    }

    /**
     * 是否可以关闭工单
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    public int isClose(int sheetId,Account account){

        String dept="";
        int userId=Integer.parseInt(account.getUser().getId());

        List<Role> role=account.getRole();
        for(Role x: role){
            dept+=x.getDept_name();
        }

        int count=sheetMapper.isClose(sheetId,dept,userId);

        return count;
    }

    /**
     * 是否可以重置工单
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    public int isReset(int sheetId,Account account){

        int userId=Integer.parseInt(account.getUser().getId());

        int count=sheetMapper.isReset(sheetId,userId);

        return count;
    }

    /**
     * 是否可以审核工单
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    public int isExamine(int sheetId,Account account){

        int userId=Integer.parseInt(account.getUser().getId());

        int count=sheetMapper.isReset(sheetId,userId);

        return count;

    }


    /**
     * 是否可以处理完毕
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    public int isFinishedProcess(int sheetId,Account account){

        int userId=Integer.parseInt(account.getUser().getId());

        int count=sheetMapper.isReject(sheetId,userId);

        return count;

    }

    /**
     * 是否可以驳回
     * @param sheetId
     * @param account
     * @return
     */
    @Override
    public int isReject(int sheetId,Account account){

        int userId=Integer.parseInt(account.getUser().getId());

        int count=sheetMapper.isReject(sheetId,userId);

        return count;

    }


}
