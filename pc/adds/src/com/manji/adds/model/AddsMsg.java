package com.manji.adds.model;

public class AddsMsg {

	private String errCode ="0";
	private String errInfo ="";
	private Object result;
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "AddMsg [errCode=" + errCode + ", errInfo=" + errInfo + ", result=" + result + "]";
	}
	
	
	
	
	
	
}
