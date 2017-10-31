package com.manji.financesystem.enums;

/**
 * Created by pudding on 2017-2-3.
 */
public enum RechargeStatus {
    NOT_PAY("0", "未支付"),
    HAVE_TO_PAY("1", "已支付"),
    REFUND("2", "申请退款中"),
    REFUND_SUCCESS("3", "退款成功"),
    REFUND_FAIL("4", "退款失败");

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
    private RechargeStatus(String code, String message) {
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
    public static RechargeStatus getByCode(String code) {
        for (RechargeStatus _enum : values()) {
            if (_enum.getCode().equalsIgnoreCase(code)) {
                return _enum;
            }
        }
        return null;
    }

    public static RechargeStatus getByMsg(String msg) {
        for (RechargeStatus _enum : values()) {
            if (_enum.getMessage().equals(msg)) {
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
    public static java.util.List<RechargeStatus> getAllEnum() {
        java.util.List<RechargeStatus> list = new java.util.ArrayList<RechargeStatus>(
                values().length);
        for (RechargeStatus _enum : values()) {
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
        RechargeStatus _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static String getCodeByMsg(String msg) {
        if (msg == null) {
            return null;
        }
        RechargeStatus _enum = getByMsg(msg);
        if (_enum == null) {
            return null;
        }
        return _enum.getCode();
    }


}
