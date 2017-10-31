package com.manji.mlife.Vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 回调参数接口类
 * @author gaochao
 * 2016年8月19日上午11:58:39
 * BackVo
 *
 */
public class BackVo {
	
	
	private String	err_code;//错误代码	0成功，其他失败标识
	private String	pay_money;///实际支付金额	
	private String	order_no;//三方订单号	原样返回
	private String	out_order_no;//满集订单号
	private String	pay_date;//交易日期	
	private String  sign;//MD5签名
	
	
	
	
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getPay_money() {
		return pay_money;
	}
	public void setPay_money(String pay_money) {
		this.pay_money = pay_money;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getOut_order_no() {
		return out_order_no;
	}
	public void setOut_order_no(String out_order_no) {
		this.out_order_no = out_order_no;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	
	
	
	
	
	


}
