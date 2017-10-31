package com.manji.financesystem.errorcode;

/**
 * Created by pudding on 2017-1-16.
 */
public enum ErrorCodeEnum {
    UNKNOWN_ERROR("UNKNOWN_ERROR", "未知异常"),
    LOGIN_ERROR("LOGIN_ERROR", "用户名或密码错误");

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
    private ErrorCodeEnum(String code, String message) {
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
    public static ErrorCodeEnum getByCode(String code) {
        for (ErrorCodeEnum _enum : values()) {
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
    public static java.util.List<ErrorCodeEnum> getAllEnum() {
        java.util.List<ErrorCodeEnum> list = new java.util.ArrayList<ErrorCodeEnum>(
                values().length);
        for (ErrorCodeEnum _enum : values()) {
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
        ErrorCodeEnum _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }


}
