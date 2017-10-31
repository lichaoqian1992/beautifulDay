package com.manji.financesystem.enums;

/**
 * 集团账户状态枚举
 * Created by Administrator on 2017/3/3.
 */
public enum GroupStatusEnums {
    DATA_SUCCESS("0","申请中"),
    DATA_FAIL("1","已通过"),
    ACCOUNT_DRECHARGE("2","已拒绝"),
    ACCOUNT_YRECHARGE("3","拒绝并禁止再次申请"),
    RECHARGE_FAIL("4","临时禁用");

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
    private GroupStatusEnums(String code , String message){
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
    public static GroupStatusEnums getByCode(String code){
        for(GroupStatusEnums e:values()){
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
        GroupStatusEnums allStatusEnum = getByCode(code);
        if(allStatusEnum == null){
            return null;
        }
        return allStatusEnum.getMessage();
    }
}
