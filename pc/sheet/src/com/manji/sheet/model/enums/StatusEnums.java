package com.manji.sheet.model.enums;
/**
 * Created by Administrator on 2017/2/21.
 */
public enum StatusEnums {

    N_TIXIAN("1","待受理"),
    Y_TIXIAN("2","不受理"),
    YUANGONG("3","处理中"),
    GONGCHENG("4","等待仲裁"),
    XINGZHEN("5","等待推送消息"),
    CEHUA("6","已完成"),
	DETAIL("7","已解决");

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
    private StatusEnums(String code , String message){
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
    public static StatusEnums getByCode(String code){
        for(StatusEnums e:values()){
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
    private static StatusEnums getByMessage(String message) {
        for(StatusEnums e:values()){
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
        StatusEnums allStatusEnum = getByCode(code);
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
        StatusEnums allStatusEnum = getByMessage(message);
        if(allStatusEnum == null){
            return null;
        }
        return allStatusEnum.getCode();
    }

}
