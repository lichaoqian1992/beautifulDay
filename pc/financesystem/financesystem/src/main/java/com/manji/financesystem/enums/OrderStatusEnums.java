package com.manji.financesystem.enums;

/**
 * 订单状态枚举
 * Created by Administrator on 2017/2/25.
 */
public enum OrderStatusEnums {
    W_PAY("1","生成订单"),
    Y_PAY("2","确认订单"),
    YY_PAY("3","完成订单"),
    X_PAY("4","取消订单"),
    FREE("5","锁定订单"),
    O_PAY("6","卖家取消订单");
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
    private OrderStatusEnums(String code, String message) {
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
    public static OrderStatusEnums getByCode(String code) {
        for (OrderStatusEnums _enum : values()) {
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
    public static java.util.List<OrderStatusEnums> getAllEnum() {
        java.util.List<OrderStatusEnums> list = new java.util.ArrayList<OrderStatusEnums>(
                values().length);
        for (OrderStatusEnums _enum : values()) {
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
        OrderStatusEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }
}
