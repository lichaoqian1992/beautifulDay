package com.manji.backstage.model.order;
//4.17.1.dt_order_ queue (账户结算主表)
public class OrderQueue {
	int id;
	int order_id;
	int state;
	int queuetype;
	String executetime;
	String createtime;
	String completiontime;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getQueuetype() {
		return queuetype;
	}
	public void setQueuetype(int queuetype) {
		this.queuetype = queuetype;
	}
	public String getExecutetime() {
		return executetime;
	}
	public void setExecutetime(String executetime) {
		this.executetime = executetime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCompletiontime() {
		return completiontime;
	}
	public void setCompletiontime(String completiontime) {
		this.completiontime = completiontime;
	}
	
}
