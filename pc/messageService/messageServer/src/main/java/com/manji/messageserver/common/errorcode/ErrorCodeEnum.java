package com.manji.messageserver.common.errorcode;

/**
 * Created by pudding on 2016-12-12.
 */
public enum ErrorCodeEnum {
    UNKNOWN_ERROR("UNKNOWN_ERROR","未知异常"),
    PARAM_VALIDATION_FAIL("PARAM_VALIDATION_FAIL","参数校验失败"),
    INVALID_ARGUMENTS("INVALID_ARGUMENTS","无效的参数"),
    DATA_DUPLICATE("DATA_DUPLICATE","数据重复"),
    DATA_NOT_EXIST("DATA_NOT_EXIST","数据不存在"),
    DB_ERROR("DB_ERROR","数据库异常"),
    MESSAGE_DETAIL_NOT_EXIST("MESSAGE_DETAIL_NOT_EXIST","消息不存在");





    /** 枚举值 */
    private final String code;

    /** 枚举描述 */
    private final String message;

    /**
     *
     * @param code 枚举值
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
