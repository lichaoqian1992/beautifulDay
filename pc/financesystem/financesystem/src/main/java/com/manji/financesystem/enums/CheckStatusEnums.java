package com.manji.financesystem.enums;

/**
 * 审批状态
 * Created by Administrator on 2017/2/21.
 */
public enum CheckStatusEnums {

    W_CHECK("0","未审核"),
    D_CHECK("1","待审核"),
    Y_CHECK("2","已审核"),
    Y_REFUSE("3","已拒绝");

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
    private CheckStatusEnums(String code , String message){
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
    public static CheckStatusEnums getByCode(String code){
        for(CheckStatusEnums e:values()){
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
        CheckStatusEnums allStatusEnum = getByCode(code);
        if(allStatusEnum == null){
            return null;
        }
        return allStatusEnum.getMessage();
    }
}
