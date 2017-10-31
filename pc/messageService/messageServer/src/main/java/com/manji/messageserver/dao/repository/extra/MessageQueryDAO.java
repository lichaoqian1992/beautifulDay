package com.manji.messageserver.dao.repository.extra;

import com.manji.messageserver.dao.entity.extra.MessageDetailQueryDO;
import com.manji.messageserver.dao.entity.extra.MessageInfoDO;
import com.manji.messageserver.requestParam.QueryHistoryRecordRequestParam;
import java.util.List;

/**
 * Created by pudding on 2016-12-19.
 */
public interface MessageQueryDAO{

    public List<MessageInfoDO> query(String beginDate,String endDate);

    public List<MessageDetailQueryDO> queryHistoryRecord(QueryHistoryRecordRequestParam param);
}
