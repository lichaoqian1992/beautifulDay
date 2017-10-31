package com.manji.finance.home.enums;

/**
 * Created by pudding on 2017-1-22.
 */
public enum UrlState {

    weixin("1", "weixinDetail"),
    zhifubao("2", "zhifubaoDetail"),
    card("3", "cardDetail"),
    voucher("4", "voucherDetail"),
    refound("5", "refoundDetail"),
    amount("6", "amountDetail"),
    techService("7", "techServiceDetail"),
    finance("8", "financeDetail"),
    bond("9", "bondDetail"),
    pltService("10", "pltServiceDetail"),
    shop("11", "shopDetail"),
	user("12", "userDetail"),
    advance("13", "advanceDetail"),
    actual("14", "actualDetail");
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
    private UrlState(String code, String message) {
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
    public static UrlState getByCode(String code) {
        for (UrlState _enum : values()) {
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
    public static java.util.List<UrlState> getAllEnum() {
        java.util.List<UrlState> list = new java.util.ArrayList<UrlState>(
                values().length);
        for (UrlState _enum : values()) {
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
        UrlState _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static UrlState getByMsg(String msg) {
        for (UrlState _enum : values()) {
            if (_enum.getMessage().equals(msg)) {
                return _enum;
            }

        }
        return null;
    }


    public static String getCodeByMsg(String msg) {
        if (msg == null) {
            return null;
        }
        UrlState _enum = getByMsg(msg);
        if(_enum==null){
            return null;
        }
        return _enum.getCode();
    }


}
