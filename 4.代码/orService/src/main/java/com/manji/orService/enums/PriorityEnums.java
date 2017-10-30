package com.manji.orService.enums;

/**
 * 优先级
 * Created by Administrator on 2017/9/4.
 */
public enum PriorityEnums {

    P1("P1", "非常急"),
    P2("P2", "紧急"),
    P3("P3", "一般"),
    P4("P4", "低");
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
    private PriorityEnums(String code, String message) {
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
    public static PriorityEnums getByCode(String code) {
        for (PriorityEnums _enum : values()) {
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
    public static java.util.List<PriorityEnums> getAllEnum() {
        java.util.List<PriorityEnums> list = new java.util.ArrayList<PriorityEnums>(
                values().length);
        for (PriorityEnums _enum : values()) {
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
        PriorityEnums _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static PriorityEnums getByMsg(String msg) {
        for (PriorityEnums _enum : values()) {
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
        PriorityEnums _enum = getByMsg(msg);
        if(_enum==null){
            return null;
        }
        return _enum.getCode();
    }
}
