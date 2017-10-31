package com.manji.mlife.model;


/**
 * 交易流水表的订单状态和支付状态
 * @author gaochao
 * 2016年7月11日下午3:49:15
 * State
 *
 */
public class State {
	
	//tradeNo,state,pstate

	private String tradeNo;//订单编号
	
	private String state;//订单状态
	
	private String pstate;//支付状态

	
	
	public State() {
		super();
		// TODO Auto-generated constructor stub
	}

	public State(String tradeNo, String state, String pstate) {
		super();
		this.tradeNo = tradeNo;
		this.state = state;
		this.pstate = pstate;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPstate() {
		return pstate;
	}

	public void setPstate(String pstate) {
		this.pstate = pstate;
	}
	
	
	
}
