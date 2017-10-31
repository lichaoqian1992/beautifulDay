package com.manji.sheet.model.enums;

public enum ResultEmuns {

	//404、500、403、200
	
	_200("200","操作成功"),
    _404("404","数据不存在"),
    _403("403","参数不全"),
    _500("500","操作失败");

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
    private ResultEmuns(String code , String message){
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
    public static ResultEmuns getByCode(String code){
        for(ResultEmuns e:values()){
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
    private static ResultEmuns getByMessage(String message) {
        for(ResultEmuns e:values()){
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
        ResultEmuns allStatusEnum = getByCode(code);
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
        ResultEmuns allStatusEnum = getByMessage(message);
        if(allStatusEnum == null){
            return null;
        }
        return allStatusEnum.getCode();
    }
}
