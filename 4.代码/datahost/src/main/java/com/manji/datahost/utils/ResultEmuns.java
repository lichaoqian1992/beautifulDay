package com.manji.datahost.utils;


public enum ResultEmuns {
	
	SUCCESS(200,"请求成功"),
	NODATA(100,"没有数据"),
	PARAM(300,"参数错误"),
	NOACCESS(400,"没有权限"),
	ERROR(500,"未知错误");
	
	private final Integer code;
	private final String message;
	
	private ResultEmuns(Integer code,String message){
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
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
    public static ResultEmuns getByCode(String code) {
        for (ResultEmuns _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    public static ResultEmuns getByMsg(String msg) {
        for (ResultEmuns _enum : values()) {
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
    public static java.util.List<ResultEmuns> getAllEnum() {
        java.util.List<ResultEmuns> list = new java.util.ArrayList<ResultEmuns>(
                values().length);
        for (ResultEmuns _enum : values()) {
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
        ResultEmuns _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    public static Integer getCodeByMsg(String msg) {
        if (msg == null) {
            return null;
        }
        ResultEmuns _enum = getByMsg(msg);
        if(_enum==null){
            return null;
        }
        return _enum.getCode();
    }

	
}
