package com.manji.finance.home.enums;

/**
 * Created by pudding on 2017-1-17.
 * 账户信息类型
 */
public enum AccountTypeEnum {

    YHK_LFT("15","用户银行卡支付金额汇总"),
    ZFB("3","支付宝"),
    WX_ZF("12","微信支付"),
    ZFB_MODEL_PAY("6","用户支付宝支付金额汇总"),
    WX_GZH_PAY("7","用户微信支付金额汇总"),
    BANK_CARD_PAY("10","银行卡支付"),
    ZFB_SDK_PAY("13","支付宝sdk支付"),
    WX_SM_PAY("8","微信扫码支付"),
    TECHNICAL_SERVICE_FEE("TECHNICAL_SERVICE_FEE","技术服务费汇总"),
    WITHDRAWALS_FEE("WITHDRAWALS_FEE","金融手续费汇总"),
    VOUCHER("VOUCHER","满意券支出汇总"),
    SHOUHOU("SHOUHOU","售后退款汇总"),
    USER_BALANCE_PAY("USER_BALANCE_PAY","用户余额汇总"),
    SHOP_BALANCE_PAY("SHOP_BALANCE_PAY","商家余额汇总"),
	MARGIN("MARGIN","保证金汇总"),
	PLATFORM("PLATFORM","平台使用服务费汇总"),
	BALANCE_PAY("BALANCE_PAY","用户余额支付金额汇总"),
    ADVANCE("ADVANCE","预支出总额"),
    REAL("REAL","已支出总额");

    /** 枚举值 */
    private final String code;

    /** 枚举描述 */
    private final String message;

    /**
     *
     * @param code 枚举值
     * @param message 枚举描述
     */
    private AccountTypeEnum(String code, String message) {
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
    public static AccountTypeEnum getByCode(String code) {
        for (AccountTypeEnum _enum : values()) {
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
    public static java.util.List<AccountTypeEnum> getAllEnum() {
        java.util.List<AccountTypeEnum> list = new java.util.ArrayList<AccountTypeEnum>(
                values().length);
        for (AccountTypeEnum _enum : values()) {
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
        AccountTypeEnum _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }



}
