package com.manji.finance.recharge.requestParams;
/**
 * 调用接口时传的参数
 * @author Administrator
 *
 */
public class HttpRequestParam {
	
	/**访问接口授权码*/
    private String accessKey;

    /**业务处理单号*/
    private String tranSN;

    /**时间戳*/
    private String nonceStr;

    /**返回的字符串数据*/
    private String rawStr;

    /**请求的接口方法名称*/
    private String action;

    /**签名字符串*/
    private String sign;

    /**充值的类型 6可提现 4不可提现*/
    private String withdraw;

    /**业待充值用户id*/
    private int userId;

    /**待充值用户角色类型*/
    private String roleType;

    /**待充值用户角色值*/
    private int roleValue;

    /**充值金额*/
    private double money;

    /**tranSN业务处理单号*/
    private String rechargeTranSN;
    
    /**金额是增加还是减少*/
    private int option;

    /**备注信息*/
    private String remark;

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getTranSN() {
		return tranSN;
	}

	public void setTranSN(String tranSN) {
		this.tranSN = tranSN;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getRawStr() {
		return rawStr;
	}

	public void setRawStr(String rawStr) {
		this.rawStr = rawStr;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public int getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(int roleValue) {
		this.roleValue = roleValue;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getRechargeTranSN() {
		return rechargeTranSN;
	}

	public void setRechargeTranSN(String rechargeTranSN) {
		this.rechargeTranSN = rechargeTranSN;
	}
    
}
