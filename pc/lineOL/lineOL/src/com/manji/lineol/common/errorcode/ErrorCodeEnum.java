package com.manji.lineol.common.errorcode;

public enum ErrorCodeEnum {
	UNKNOWN_ERROR("UNKNOWN_ERROR", "未知异常"), 
	SERVICE_TYPE_ERROR("SERVICE_TYPE_ERROR", "服务类型错误或状态错误"),
	SHOPID_ISNULL_ERROR("SHOPID_ISNULL_ERROR", "shopId为空"),
	SHOP_NOT_EXIST("SHOP_NOT_EXIST","商家不存在"),
	SERVICE_IS_DISABLE("SERVICE_IS_DISABLE","服务是开启的"),
	SHOP_CONFIG_NOT_EXIST("SHOP_CONFIG_NOT_EXIST","商家配置信息不存在"),
	SHOP_QUEUE_NOT_EXIST("SHOP_QUEUE_NOT_EXIST","商家队列不存在"),
	SHOP_QUEUE_NOT_EXIST_USER_INFO("SHOP_QUEUE_NOT_EXIST_USER_INFO","商户队列中不存在用户预约信息"),
	QUEUE_INFO_NOT_EXIST("QUEUE_INFO_NOT_EXIST","队列信息不存在"),
	NO_RESERVATION_INFORMATION("NO_RESERVATION_INFORMATION","没有用户预约信息"),
	USER_INFO_NOT_EXIST("USER_INFO_NOT_EXIST","用户信息不存在");
	

	/** 枚举值 */
	private final String code;

	/** 枚举描述 */
	private final String message;

	/**
	 *
	 * @param code
	 *            枚举值
	 * @param message
	 *            枚举描述
	 */
	private ErrorCodeEnum(String code, String message) {
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
	public static ErrorCodeEnum getByCode(String code) {
		for (ErrorCodeEnum _enum : values()) {
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
	public static java.util.List<ErrorCodeEnum> getAllEnum() {
		java.util.List<ErrorCodeEnum> list = new java.util.ArrayList<ErrorCodeEnum>(values().length);
		for (ErrorCodeEnum _enum : values()) {
			list.add(_enum);
		}
		return list;
	}

	/**
	 * 通过code获取msg
	 * 
	 * @param code
	 *            枚举值
	 * @return
	 */
	public static String getMsgByCode(String code) {
		if (code == null) {
			return null;
		}
		ErrorCodeEnum _enum = getByCode(code);
		if (_enum == null) {
			return null;
		}
		return _enum.getMessage();
	}
}
