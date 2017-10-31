package com.manji.messageserver.common.enums;

/**
 * Created by pudding on 2016-12-12.
 * 响应状态枚举
 */
public enum StatusEnum {

    FAIL("FAIL", "处理失败"),
    SUCCESS("SUCCESS", "处理成功");

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;


    /**
     * @param code    枚举值
     * @param message 枚举描述
     */
    private StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return VerifyCardResultEnum
     */
    public static StatusEnum getByCode(String code) {
        for (StatusEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }


    /**
     * 获取全部枚举
     *
     * @return List<VerifyCardResultEnum>
     */
    public static java.util.List<StatusEnum> getAllEnum() {
        java.util.List<StatusEnum> list = new java.util.ArrayList<StatusEnum>(
                values().length);
        for (StatusEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }


    /**
     * 通过code获取msg
     *
     * @param code 枚举值
     * @return
     */
    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        }
        StatusEnum _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }


}
