package com.manji.cusystem.dao.conversation;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/23.
 */
@Data
public class ConversationCount {

    private int cus_user_id;
    private String cus_user_account;
    private String cus_user_name;
    private String cus_code;
    private int count;
}
