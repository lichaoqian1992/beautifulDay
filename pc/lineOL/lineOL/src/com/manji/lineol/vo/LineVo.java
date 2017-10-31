package com.manji.lineol.vo;

import java.io.Serializable;

public class LineVo implements Serializable{
	
	private static final long serialVersionUID = 6166987549198574080L;
	
	//用户ID
	private String userId;
	
	//用户号码
	private int number;
	
	//用户排队时的时间
	private Long time;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "LineVo [userId=" + userId + ", number=" + number + ", time=" + time + "]";
	}
	
	
	

}
