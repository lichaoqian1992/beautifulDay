package com.manji.finance.recharge.enums;

/**
 * 用户的充值状态
 * Created by Administrator on 2017/2/21.
 */
public enum AllStatusEnum {
    DATA_SUCCESS("1","原始数据导入成功"),
    DATA_FAIL("2","原始数据异常"),
    ACCOUNT_DRECHARGE("3","账户待充值"),
    ACCOUNT_YRECHARGE("4","账户预充值"),
    RECHARGE_FAIL("5","充值失败"),
    RECHARGE_SUCCESS("6","充值成功"),
    YRECHARGE_BACK("7","预撤销充值"),
    YRECHARGE_FAIL("8","撤销充值失败"),
    YRECHARGE_SUCCESS("9","撤销充值成功"),
    DATA_CORRECATION("10","数据修正"),
    DATA_CANCEL("11","数据作废");

    /**
     * 枚举值
     */
    private final String code;
    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 枚举值，枚举描述
     * @param code
     * @param message
     */
    private AllStatusEnum(String code , String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){return code;}

    public String getMessage(){return message;}

    /**
     * 通过枚举值获得枚举描述
     * @param code
     * @return
     */
    public static AllStatusEnum getByCode(String code){
        for(AllStatusEnum e:values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }

    /**
     * 通过code获得message
     * @param code
     * @return
     */
    public static String getMessageByCode(String code){
        if(code == null){
            return null;
        }
        AllStatusEnum allStatusEnum = getByCode(code);
        if(allStatusEnum == null){
            return null;
        }
        return allStatusEnum.getMessage();
    }


}
