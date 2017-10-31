package com.manji.messageserver.service;

import com.manji.messageserver.dao.entity.MessageDetailDO;
import com.manji.messageserver.requestParam.QueryHistoryRecordRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
/**
 * Created by pudding on 2016-12-20.
 */
public interface HistoryRecordService {

    public MessageDetailDO getMessageDetail(String msgid);

    public BaseResult queryHistoryRecord(QueryHistoryRecordRequestParam requestParam);
}
