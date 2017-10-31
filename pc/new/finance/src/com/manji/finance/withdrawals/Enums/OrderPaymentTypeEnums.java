package com.manji.finance.withdrawals.Enums;

/**
 * Created by pudding on 2017-1-22.
 * 用户角色类型
 */
public enum OrderPaymentTypeEnums {

    good("good", "商品订单"),
    book_table("book_table", "订位订单"),
    book_food("book_food", "订餐订单"),
    book_room("book_room","订餐订单"),
    distribution("distribution","外卖订单"),
    Virtual("Virtual","团购订单"),
    sms("sms","短信"),
    advert("advert","广告"),
    business("business","业务"),
    job("job","人才信息"),
    pay("pay","付款"),
    all("all","全部类型"),
    book("book","预定");

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
    private OrderPaymentTypeEnums(String code, String message) {
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
    public static OrderPaymentTypeEnums getByCode(String code) {
        for (OrderPaymentTypeEnums _enum : values()) {
            if (_enum.getCode().equalsIgnoreCase(code)) {
                return _enum;
            }
        }
        return null;
    }

    public static OrderPaymentTypeEnums getByMsg(String msg) {
        for (OrderPaymentTypeEnums _enum : values()) {
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
    public static java.util.List<OrderPaymentTypeEnums> getAllEnum() {
        java.util.List<OrderPaymentTypeEnums> list = new java.util.ArrayList<OrderPaymentTypeEnums>(
                values().length);
        for (OrderPaymentTypeEnums _enum : values()) {
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
        OrderPaymentTypeEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static String getCodeByMsg(String msg) {
        if (msg == null) {
            return null;
        }
        OrderPaymentTypeEnums _enum = getByMsg(msg);
        if(_enum==null){
            return null;
        }
        return _enum.getCode();
    }


}
