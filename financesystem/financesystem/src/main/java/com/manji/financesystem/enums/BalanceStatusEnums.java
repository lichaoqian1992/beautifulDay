package com.manji.financesystem.enums;

/**
 * Created by pudding on 2017-1-19.
 * 结算状态的枚举
 */
public enum BalanceStatusEnums {

    WAITING_BALANCE("1", "待结算"),
    ALREADY_BALANCE("2", "已结算"),
    ALREADY_ABANDON("4", "已作废");

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
    private BalanceStatusEnums(String code, String message) {
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
    public static BalanceStatusEnums getByCode(String code) {
        for (BalanceStatusEnums _enum : values()) {
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
    public static java.util.List<BalanceStatusEnums> getAllEnum() {
        java.util.List<BalanceStatusEnums> list = new java.util.ArrayList<BalanceStatusEnums>(
                values().length);
        for (BalanceStatusEnums _enum : values()) {
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
        BalanceStatusEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }


}
