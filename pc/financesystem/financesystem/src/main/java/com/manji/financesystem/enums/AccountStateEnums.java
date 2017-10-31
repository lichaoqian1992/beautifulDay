package com.manji.financesystem.enums;

/**
 * Created by pudding on 2017-1-22.
 */
public enum AccountStateEnums {

    NORMAL("1", "正常"),
    FREEZE("0", "冻结"),
    ABNORMAL("9", "异常");

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
    private AccountStateEnums(String code, String message) {
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
    public static AccountStateEnums getByCode(String code) {
        for (AccountStateEnums _enum : values()) {
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
    public static java.util.List<AccountStateEnums> getAllEnum() {
        java.util.List<AccountStateEnums> list = new java.util.ArrayList<AccountStateEnums>(
                values().length);
        for (AccountStateEnums _enum : values()) {
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
        AccountStateEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static AccountStateEnums getByMsg(String msg) {
        for (AccountStateEnums _enum : values()) {
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
        AccountStateEnums _enum = getByMsg(msg);
        if(_enum==null){
            return null;
        }
        return _enum.getCode();
    }


}
