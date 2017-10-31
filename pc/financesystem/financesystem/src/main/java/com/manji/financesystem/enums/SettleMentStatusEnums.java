package com.manji.financesystem.enums;

/**
 * 结算状态枚举
 * Created by Administrator on 2017/2/25.
 */
public enum SettleMentStatusEnums {

    W_SET("0","未结算"),
    Y_SET("1","已结算");

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
    private SettleMentStatusEnums(String code, String message) {
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
    public static SettleMentStatusEnums getByCode(String code) {
        for (SettleMentStatusEnums _enum : values()) {
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
    public static java.util.List<SettleMentStatusEnums> getAllEnum() {
        java.util.List<SettleMentStatusEnums> list = new java.util.ArrayList<SettleMentStatusEnums>(
                values().length);
        for (SettleMentStatusEnums _enum : values()) {
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
        SettleMentStatusEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }
}
