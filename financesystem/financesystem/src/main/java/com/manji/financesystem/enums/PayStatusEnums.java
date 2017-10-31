package com.manji.financesystem.enums;

/**
 * 支付状态枚举
 * Created by Administrator on 2017/2/25.
 */
public enum  PayStatusEnums {

    W_PAY("1","未支付"),
    Y_PAY("2","已支付"),
    YY_PAY("3","已预付"),
    X_PAY("4","线下支付"),
    FREE("5","免单"),
    O_PAY("6","仅预定");
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
    private PayStatusEnums(String code, String message) {
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
    public static PayStatusEnums getByCode(String code) {
        for (PayStatusEnums _enum : values()) {
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
    public static java.util.List<PayStatusEnums> getAllEnum() {
        java.util.List<PayStatusEnums> list = new java.util.ArrayList<PayStatusEnums>(
                values().length);
        for (PayStatusEnums _enum : values()) {
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
        PayStatusEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }
}
