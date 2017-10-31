package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * 商家信息实体类
 * Created by Administrator on 2017/2/24.
 */
@Data
public class ShopInfoDO {
    /**商家ID*/
    private String userId;
    /**商家名称*/
    private String userName;
    /**商家简介*/
    private String content;
    /**店铺图片*/
    private String pics;
    /**店铺关键字*/
    private String tag;
    /**店铺标志图片*/
    private String pcLogo;
    /**联系电话*/
    private String mobile;
    /**联系地址*/
    private String address;
    /**店铺所在区域*/
    private String area;
    /**公司电话*/
    private String telephone;
    /**证件类型*/
    private String cardType;
    /**证件号码*/
    private String cardNum;
    /**证件图片*/
    private String cardPic;
    /**法人代表*/
    private String legalPerson;
    /**法人手机号码*/
    private String legalTel;
    /**法人身份证号*/
    private String legalIdCard;
    /**总数据*/
    private int countNum;
}
