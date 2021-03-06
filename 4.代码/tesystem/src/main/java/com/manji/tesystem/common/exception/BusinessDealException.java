package com.manji.tesystem.common.exception;

/**
 * 
 * 项目名称：common-simple 类名称：BusinessDealException 类描述：
 * 创建时间：2014年10月28日 上午10:50:33 
 * @version
 */
public class BusinessDealException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7970147372045781333L;

	private String code;

	public BusinessDealException(String code, String msg) {
		super(msg);
		setCode(code);
	}

	public BusinessDealException(String msg) {
		super(msg);
		setCode(code);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
