package com.manji.datahost.utils;

public enum OrderTypeEnums {
	
	SysGive("SysGive","系统处理"),
    Recharge("Recharge","在线充值"),
    Consumption("Consumption","消费付款"),
    ConsumptionBack("ConsumptionBack","消费退款"),
    BuyGoods("BuyGoods","购买商品"),
    Convert("Convert","积分兑换"),
    WithDrawals("WithDrawals","用户提现"),
    WithDrawalsAuto("WithDrawalsAuto","自动提现"),
    WithDrawalsBack("WithDrawalsBack","提现退回"),
    Transaction("Transaction","转账/付款"),
    TransferOut("TransferOut","转出"),
    TransferIn("TransferIn","转入"),
    OrderBack("OrderBack","订单退款"),
    OrderPreSettlement("OrderPreSettlement","订单预结算"),
    OrderSettlement("OrderSettlement","订单结算"),
    FixedAccount("FixedAccount","批量充值"),
    ActiveActivity("ActiveActivity","参与活动");



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
    private OrderTypeEnums(String code, String message) {
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
    public static OrderTypeEnums getByCode(String code) {
        for (OrderTypeEnums _enum : values()) {
            if (_enum.getCode().equalsIgnoreCase(code)) {
                return _enum;
            }
        }
        return null;
    }

    public static OrderTypeEnums getByMsg(String msg) {
        for (OrderTypeEnums _enum : values()) {
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
    public static java.util.List<OrderTypeEnums> getAllEnum() {
        java.util.List<OrderTypeEnums> list = new java.util.ArrayList<OrderTypeEnums>(
                values().length);
        for (OrderTypeEnums _enum : values()) {
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
        OrderTypeEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static String getCodeByMsg(String msg) {
        if (msg == null) {
            return null;
        }
        OrderTypeEnums _enum = getByMsg(msg);
        if(_enum==null){
            return null;
        }
        return _enum.getCode();
    }

}
