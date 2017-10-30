package com.manji.backstage.model.order;
//4.16.1.	dt_order_log(账户结算主表)
public class OrderLog {
	int id;
	int order_id;
	int uid;
	int beginstate;
	int endstate;
	String createtime;
	String developername;
	String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBeginstate() {
		return beginstate;
	}
	public void setBeginstate(int beginstate) {
		this.beginstate = beginstate;
	}
	public int getEndstate() {
		return endstate;
	}
	public void setEndstate(int endstate) {
		this.endstate = endstate;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getDevelopername() {
		return developername;
	}
	public void setDevelopername(String developername) {
		this.developername = developername;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
