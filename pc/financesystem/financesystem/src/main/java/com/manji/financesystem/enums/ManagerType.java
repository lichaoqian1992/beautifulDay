package com.manji.financesystem.enums;

/**
 * 管理类型枚举
 * Created by Administrator on 2017/3/3.
 */
public enum ManagerType {
    DATA_SUCCESS("0","直营店"),
    DATA_FAIL("1","加盟店");

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
    private ManagerType(String code , String message){
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
    public static ManagerType getByCode(String code){
        for(ManagerType e:values()){
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
        ManagerType allStatusEnum = getByCode(code);
        if(allStatusEnum == null){
            return null;
        }
        return allStatusEnum.getMessage();
    }
}
