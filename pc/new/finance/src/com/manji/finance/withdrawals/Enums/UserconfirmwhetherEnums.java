package com.manji.finance.withdrawals.Enums;

/**
 * Created by pudding on 2017-1-22.
 * 用户角色类型
 */
public enum UserconfirmwhetherEnums {

    rengong("0", "人工处理中"),
    chenggong("1", "提现成功"),
    shibai("2", "提现失败");


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
    private UserconfirmwhetherEnums(String code, String message) {
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
    public static UserconfirmwhetherEnums getByCode(String code) {
        for (UserconfirmwhetherEnums _enum : values()) {
            if (_enum.getCode().equalsIgnoreCase(code)) {
                return _enum;
            }
        }
        return null;
    }

    public static UserconfirmwhetherEnums getByMsg(String msg) {
        for (UserconfirmwhetherEnums _enum : values()) {
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
    public static java.util.List<UserconfirmwhetherEnums> getAllEnum() {
        java.util.List<UserconfirmwhetherEnums> list = new java.util.ArrayList<UserconfirmwhetherEnums>(
                values().length);
        for (UserconfirmwhetherEnums _enum : values()) {
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
        UserconfirmwhetherEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static String getCodeByMsg(String msg) {
        if (msg == null) {
            return null;
        }
        UserconfirmwhetherEnums _enum = getByMsg(msg);
        if(_enum==null){
            return null;
        }
        return _enum.getCode();
    }


}
