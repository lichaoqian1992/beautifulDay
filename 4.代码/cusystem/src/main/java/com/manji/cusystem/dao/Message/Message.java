package com.manji.cusystem.dao.Message;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/10.
 */
@Data
public class Message {

    private int cus_id;
    private int cus_type;
    private String cus_content;
    private String cus_count;
    private String cus_url;
    private String cus_time;
    private String cus_send_time;
    private String cus_accept_type;
    private String cus_kind;
    private String cus_theme;
    private int cus_status;
}
