package com.manji.financesystem.enums;

/**
 * Created by pudding on 2017-1-19.
 * 提现状态
 */
public enum WithdrawalsStatus {

    //    0待审核 1用户撤销提现 2管理退回提现 3管理冻结提现 4审核通过(处理中) 5提现成功
    WAITING_AUDIT("0", "待审核"),
    USER_REVOCATION("1", "用户撤销提现"),
    MANAGE_REVOCATION("2", "管理退回提现"),
    MANAGE_FREEZE("3", "管理冻结提现"),
    BEING_PROCESSED("4", "审核通过(处理中)"),
    WITHDRAWALS_SUCCESS("5", "提现成功");


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
    private WithdrawalsStatus(String code, String message) {
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
    public static WithdrawalsStatus getByCode(String code) {
        for (WithdrawalsStatus _enum : values()) {
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
    public static java.util.List<WithdrawalsStatus> getAllEnum() {
        java.util.List<WithdrawalsStatus> list = new java.util.ArrayList<WithdrawalsStatus>(
                values().length);
        for (WithdrawalsStatus _enum : values()) {
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
        WithdrawalsStatus _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }
}
