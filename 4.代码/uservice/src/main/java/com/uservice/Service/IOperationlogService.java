package com.uservice.Service;

import java.util.Date;

/**
 * Created by pudding on 2017-9-5.(YYR)
 */
public interface IOperationlogService {

    boolean insertOperationlog(int op_type, String content, int user_id, Date add_time,String important);
}
