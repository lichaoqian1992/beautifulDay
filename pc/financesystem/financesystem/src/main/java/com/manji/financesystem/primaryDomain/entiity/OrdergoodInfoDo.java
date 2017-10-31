package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-3-2.
 */
@Data
public class OrdergoodInfoDo {

    /**id*/
    private  long id;

    /**对应订单ID*/
    private long order_id;

    /**快递ID*/
    private int express_id;

    /**快递单号*/
    private  String express_no;

    /**发货状态*/
    private String express_status;

    /**发货时间*/
    private  Date express_time;

     /** 收货人姓名*/
     private  String accept_name;

     /**邮政编码*/
     private  String post_code;

     /** 联系电话*/
     private  String telephone;

     /**手机号码*/
     private  String mobile;

     /**电子邮箱*/
     private  String email;

     /** 所属省市区*/
     private  String area;

     /** 收货地址*/
     private  String address;

     /**买家是否延迟收货*/
     private  String is_receipt;

     /**卖家是否延迟发货*/
     private  String is_deliver;




}
