package com.manji.financesystem.enums;

/**
 * Created by pudding on 2017-1-19.
 */
public enum IsDeleteEnums {
    HAVE_DELETED("1", "删除"),
    NOT_DELETED("0", "正常");


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
    private IsDeleteEnums(String code, String message) {
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
    public static IsDeleteEnums getByCode(String code) {
        for (IsDeleteEnums _enum : values()) {
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
    public static java.util.List<IsDeleteEnums> getAllEnum() {
        java.util.List<IsDeleteEnums> list = new java.util.ArrayList<IsDeleteEnums>(
                values().length);
        for (IsDeleteEnums _enum : values()) {
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
        IsDeleteEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

}
