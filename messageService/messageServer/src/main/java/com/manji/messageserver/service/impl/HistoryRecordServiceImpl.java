package com.manji.messageserver.service.impl;

import com.manji.messageserver.common.errorcode.ErrorCodeEnum;
import com.manji.messageserver.dao.entity.MessageDetailDO;
import com.manji.messageserver.dao.entity.extra.MessageDetailQueryDO;
import com.manji.messageserver.dao.repository.MessageDetailDAO;
import com.manji.messageserver.dao.repository.extra.MessageQueryDAO;
import com.manji.messageserver.requestParam.QueryHistoryRecordRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.responseResult.PagedQueryResult;
import com.manji.messageserver.service.HistoryRecordService;
import com.manji.messageserver.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by pudding on 2016-12-20.
 */
@Service
public class HistoryRecordServiceImpl implements HistoryRecordService {

    @Autowired
    private MessageDetailDAO messageDetailDAO;

    @Autowired
    private MessageQueryDAO messageQueryDAO;

    /**
     * 下载历史文件
     * @param msgid
     * @return
     */
    @Override
    public MessageDetailDO getMessageDetail(String msgid) {
        MessageDetailDO messageDetailDO = messageDetailDAO.findByMsgId(msgid);
        AssertUtil.notNull(messageDetailDO, ErrorCodeEnum.MESSAGE_DETAIL_NOT_EXIST);
        return messageDetailDO;
    }
    /**
     * 查询历史消息
     * @param requestParam
     * @return
     */
    @Override
    public BaseResult queryHistoryRecord(QueryHistoryRecordRequestParam requestParam) {
        List<MessageDetailQueryDO> list = messageQueryDAO.queryHistoryRecord(requestParam);
        return PagedQueryResult.successResult(list,requestParam.getPage(),list.size());
    }
}
