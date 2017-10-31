package com.manji.desystem.common.enums;

/**
 * 错误码
 * Created by pudding on 2017-1-22.
 */

public enum ErrorCodeEnums {

    Success(0,"成功"),
    UnknownError(1,"未知错误"),
    ServiceTemporarilyUnavailable(2,"服务暂不可用"),
    UnsupportedopenapiMethod(3,"未知的方法"),
    InvalidParameter(100,"参数错误"),
    UserNull(101,"未知用户"),
    IllegalOperation(102,"非法操作"),
    PicUpload(103,"图片上传错误"),
    BUSSINESS_HANDLE_ERROR(104, "校验，业务处理出现异常");

    /**
     * 枚举值
     */
    private final int code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * @param code    枚举值
     * @param message 枚举描述
     */
    private ErrorCodeEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return String.valueOf(code);
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
    public static ErrorCodeEnums getByCode(String code) {
        for (ErrorCodeEnums _enum : values()) {
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
    public static java.util.List<ErrorCodeEnums> getAllEnum() {
        java.util.List<ErrorCodeEnums> list = new java.util.ArrayList<ErrorCodeEnums>(
                values().length);
        for (ErrorCodeEnums _enum : values()) {
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
        ErrorCodeEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static ErrorCodeEnums getByMsg(String msg) {
        for (ErrorCodeEnums _enum : values()) {
            if (_enum.getMessage().equals(msg)) {
                return _enum;
            }

        }
        return null;
    }


    public static String getCodeByMsg(String msg) {
        if (msg == null) {
            return "";
        }
        ErrorCodeEnums _enum = getByMsg(msg);
        if(_enum==null){
            return "";
        }
        return _enum.getCode();
    }


}
