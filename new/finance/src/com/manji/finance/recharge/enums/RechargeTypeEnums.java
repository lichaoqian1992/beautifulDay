package com.manji.finance.recharge.enums;

/**
 * Created by Administrator on 2017/2/21.
 */
public enum RechargeTypeEnums {

    N_TIXIAN("4","不可提现"),
    Y_TIXIAN("6","可以提现"),
    YUANGONG("0","员工工资"),
    GONGCHENG("1","工程款"),
    XINGZHEN("2","行政款"),
    CEHUA("3","营销策划"),
	KOUJIAN("5","扣减"),
	OTHER("7","其他");

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
    private RechargeTypeEnums(String code , String message){
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
    public static RechargeTypeEnums getByCode(String code){
        for(RechargeTypeEnums e:values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }
    /**
     * 通过枚举描述获得枚举值
     * @param message
     * @return
     */
    private static RechargeTypeEnums getByMessage(String message) {
        for(RechargeTypeEnums e:values()){
            if(e.getMessage().equals(message)){
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
        RechargeTypeEnums allStatusEnum = getByCode(code);
        if(allStatusEnum == null){
            return null;
        }
        return allStatusEnum.getMessage();
    }

    /**
     * 通过message获得code
     * @param message
     * @return
     */
    public static String getCodeByMessage(String message){
        if(message == null){
            return null;
        }
        RechargeTypeEnums allStatusEnum = getByMessage(message);
        if(allStatusEnum == null){
            return null;
        }
        return allStatusEnum.getCode();
    }

}
