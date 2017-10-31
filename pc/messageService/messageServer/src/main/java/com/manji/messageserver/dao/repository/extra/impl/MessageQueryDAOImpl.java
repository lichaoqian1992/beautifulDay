package com.manji.messageserver.dao.repository.extra.impl;

import com.manji.messageserver.dao.entity.extra.MessageDetailQueryDO;
import com.manji.messageserver.dao.entity.extra.MessageInfoDO;
import com.manji.messageserver.dao.repository.extra.MessageQueryDAO;
import com.manji.messageserver.requestParam.QueryHistoryRecordRequestParam;
import com.manji.messageserver.utils.PageUtil;
import com.manji.messageserver.utils.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2016-12-19.
 */
@Repository
public class MessageQueryDAOImpl implements MessageQueryDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MessageInfoDO> query(String beginDate, String endDate) {
        List<MessageInfoDO> doList = new ArrayList<MessageInfoDO>();
        String sql = "select message.msg_id,messagedetail.type,messagedetail.url,message.`timestamp` from message INNER JOIN messagedetail on message.msg_id=messagedetail.msg_id and messagedetail.type!='txt' and messagedetail.type!='loc' and  message.`timestamp` >=? and message.`timestamp`< ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, beginDate);
        query.setParameter(2, endDate);
        List<Object[]> resultList = query.getResultList();
        for (Object[] objs : resultList) {
            MessageInfoDO messageInfoDO = new MessageInfoDO();
            messageInfoDO.setMsgId(objs[0].toString());
            messageInfoDO.setType(objs[1].toString());
            messageInfoDO.setUrl(objs[2].toString());
            messageInfoDO.setTime(objs[3].toString());
            doList.add(messageInfoDO);
        }
        return doList;
    }

    @Override
    public List<MessageDetailQueryDO> queryHistoryRecord(QueryHistoryRecordRequestParam param) {
        List<MessageDetailQueryDO> list = new ArrayList<MessageDetailQueryDO>();
        String sql = "select a.msg_id,a.come,a.toperson,a.chat_type,b.type,b.msg,b.url,b.filename,b.addr,b.lat,b.lng,a.`timestamp` from message a INNER JOIN messagedetail b on a.msg_id=b.msg_id where 1=1";
        StringBuffer stringBuffer = new StringBuffer(sql);
        if (StringUtils.isNotEmpty(param.getSendMessageUser())) {
            stringBuffer.append(" and come = '"+param.getSendMessageUser()+"'");
        }
        if (StringUtils.isNotEmpty(param.getReceiveMessageUser())) {
            stringBuffer.append(" and toperson='"+param.getReceiveMessageUser()+"'");
        }
        Pageable pageable = PageUtil.getPageable(param);

        Query nativeQuery = entityManager.createNativeQuery(stringBuffer.toString());
        nativeQuery.setFirstResult(pageable.getOffset());
        nativeQuery.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = nativeQuery.getResultList();
        for (Object[] objs : resultList) {
            MessageDetailQueryDO messageDetailQueryDO = new MessageDetailQueryDO();
            messageDetailQueryDO.setMsgId(objs[0].toString());
            messageDetailQueryDO.setCome(objs[1].toString());
            messageDetailQueryDO.setToperson(objs[2].toString());
            messageDetailQueryDO.setChatType(objs[3].toString());
            messageDetailQueryDO.setType(objs[4].toString());
            if (objs[5] != null) {
                messageDetailQueryDO.setMsg(objs[5].toString());
            }
            if (objs[6] != null) {
                messageDetailQueryDO.setUrl(objs[6].toString());
            }
            if (objs[7] != null) {
                messageDetailQueryDO.setFileName(objs[7].toString());
            }
            if (objs[8] != null) {
                messageDetailQueryDO.setAddr(objs[8].toString());
            }
            if (objs[9] != null) {
                messageDetailQueryDO.setLat((Double) objs[9]);
            }
            if (objs[10] != null) {
                messageDetailQueryDO.setLng((Double) objs[10]);
            }
            messageDetailQueryDO.setTimestamp(objs[11].toString());
            list.add(messageDetailQueryDO);
        }
        return list;
    }
}
