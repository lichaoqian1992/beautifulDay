package com.manji.mlife.model;

/**
 * 飞机订单乘客的信息类
 * @author gaochao
 * 2016年8月4日下午3:49:46
 * Items
 *
 */
public class Items {

	//多个乘客的属性数组集合
		private String[] listName;//乘客姓名
		private String[] listPhone;//手机号码
		private String[] listCertificates;//证件号
	
	public String[] getListName() {
			return listName;
		}

		public void setListName(String[] listName) {
			this.listName = listName;
		}

		public String[] getListPhone() {
			return listPhone;
		}

		public void setListPhone(String[] listPhone) {
			this.listPhone = listPhone;
		}

		public String[] getListCertificates() {
			return listCertificates;
		}

		public void setListCertificates(String[] listCertificates) {
			this.listCertificates = listCertificates;
		}

	private String seatCode;//舱位编码
	
	private String itemId="5500301";//选择的飞机票标准商品编号
	
	private String contactName;//订票联系人
	
	private String contactTel;//订票联系人电话
	
	private String flightNo ;//航班号
	
	private String date;//出发日期
	
	private String from;//起飞站点(机场)三字码

	private String to;//抵达站点(机场)三字码
	
	private String companyCode;//航空公司编码
	
	private String flightCompanyName;//航空公司
	
	private String seatMsg;//舱位
	
	
	public String getFlightCompanyName() {
		return flightCompanyName;
	}

	public void setFlightCompanyName(String flightCompanyName) {
		this.flightCompanyName = flightCompanyName;
	}
	
	public String getSeatMsg() {
		return seatMsg;
	}

	public void setSeatMsg(String seatMsg) {
		this.seatMsg = seatMsg;
	}

	public String getSeatCode() {
		return seatCode;
	}

	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
