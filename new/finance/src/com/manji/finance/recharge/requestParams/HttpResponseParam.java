package com.manji.finance.recharge.requestParams;
/**
 * 充值以后返回的参数
 * @author Administrator
 *
 */
public class HttpResponseParam {

	/**错误代码*/
    private String errCode;
    /**错误消息*/
    private String message;
    /**请求接口名*/
    private String action;
    /**返回的业务单号*/
    private String querySN;
    /**发送的业务处理单号*/
    private String tranSN;
    /**返回代码*/
    private String resultCode;
    /**返回数据*/
    private String resultData;

    private String rawStr;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getQuerySN() {
		return querySN;
	}

	public void setQuerySN(String querySN) {
		this.querySN = querySN;
	}

	public String getTranSN() {
		return tranSN;
	}

	public void setTranSN(String tranSN) {
		this.tranSN = tranSN;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultData() {
		return resultData;
	}

	public void setResultData(String resultData) {
		this.resultData = resultData;
	}

	public String getRawStr() {
		return rawStr;
	}

	public void setRawStr(String rawStr) {
		this.rawStr = rawStr;
	}
    
}
