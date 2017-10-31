package com.manji.sheet.model.reqParam;

public class EvaluateParam {

	/**工单编号*/
	private String sheet_no;
	/**说明*/
	private String content;
	/**是否受理*/
	private String type;
	/**工单状态*/
	private int status;
	
	private int exe_dept_id;
	private String exe_dept;
	private String sup_dept;
	private int sup_dept_id;
	private String pics;
	
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public int getExe_dept_id() {
		return exe_dept_id;
	}
	public void setExe_dept_id(int exe_dept_id) {
		this.exe_dept_id = exe_dept_id;
	}
	public String getExe_dept() {
		return exe_dept;
	}
	public void setExe_dept(String exe_dept) {
		this.exe_dept = exe_dept;
	}
	public String getSup_dept() {
		return sup_dept;
	}
	public void setSup_dept(String sup_dept) {
		this.sup_dept = sup_dept;
	}
	public int getSup_dept_id() {
		return sup_dept_id;
	}
	public void setSup_dept_id(int sup_dept_id) {
		this.sup_dept_id = sup_dept_id;
	}
	public String getSheet_no() {
		return sheet_no;
	}
	public void setSheet_no(String sheet_no) {
		this.sheet_no = sheet_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	} 
	
}
