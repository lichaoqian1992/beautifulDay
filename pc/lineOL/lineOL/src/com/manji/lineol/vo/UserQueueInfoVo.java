package com.manji.lineol.vo;

import java.io.Serializable;

public class UserQueueInfoVo implements Serializable {

	private static final long serialVersionUID = 982484144403665624L;

	// 商家Id
	private String shopId;

	// 用户名称
	private String userName;

	// 号码
	private int no;

	// 我当前多少位
	private String myCurrentSize;

	private String status;

	private String typeAs;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMyCurrentSize() {
		return myCurrentSize;
	}

	public void setMyCurrentSize(String myCurrentSize) {
		this.myCurrentSize = myCurrentSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeAs() {
		return typeAs;
	}

	public void setTypeAs(String typeAs) {
		this.typeAs = typeAs;
	}

	@Override
	public String toString() {
		return "UserQueueInfoVo [shopId=" + shopId + ", userName=" + userName + ", no=" + no + ", myCurrentSize="
				+ myCurrentSize + ", status=" + status + ", typeAs=" + typeAs + "]";
	}

}
