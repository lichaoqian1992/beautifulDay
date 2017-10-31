package com.manji.finance.withdrawals.Enums;

/**
 * Created by pudding on 2017-1-22.
 * 用户角色类型
 */
public enum UserRoleTypeEnums {

    USER("Buyer", "用户"),
    SHOPPER("Shop", "商家"),
    Manager("Manager","管理员");


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
    private UserRoleTypeEnums(String code, String message) {
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
    public static UserRoleTypeEnums getByCode(String code) {
        for (UserRoleTypeEnums _enum : values()) {
            if (_enum.getCode().equalsIgnoreCase(code)) {
                return _enum;
            }
        }
        return null;
    }

    public static UserRoleTypeEnums getByMsg(String msg) {
        for (UserRoleTypeEnums _enum : values()) {
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
    public static java.util.List<UserRoleTypeEnums> getAllEnum() {
        java.util.List<UserRoleTypeEnums> list = new java.util.ArrayList<UserRoleTypeEnums>(
                values().length);
        for (UserRoleTypeEnums _enum : values()) {
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
        UserRoleTypeEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static String getCodeByMsg(String msg) {
        if (msg == null) {
            return null;
        }
        UserRoleTypeEnums _enum = getByMsg(msg);
        if(_enum==null){
            return null;
        }
        return _enum.getCode();
    }


}
