package com.manji.mlife.model;

/**
 * 航班信息类
 * 
 * @author gaochao 2016年8月17日上午10:11:45 Airlines
 *
 */
public class Airlines {

	private String flightCompanyName;//航空公司
	private String flightNo;//航班号
	private String depTime;//到达时间
	private String orgCityName;//到达城市
	private String arriTime;//到达时间
	private Integer parPrice;//单价
	private String discount;//折扣
	private String seatMsg;//舱位
	private String date;//日期
	private Integer adultAirportTax;//机建
	private Integer adultFuelTax;//燃油
	private Integer totlPrice;//总价
	
	
	public Airlines() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getParPrice() {
		return parPrice;
	}


	public void setParPrice(Integer parPrice) {
		this.parPrice = parPrice;
	}

	public Integer getAdultAirportTax() {
		return adultAirportTax;
	}

	public void setAdultAirportTax(Integer adultAirportTax) {
		this.adultAirportTax = adultAirportTax;
	}


	public Integer getAdultFuelTax() {
		return adultFuelTax;
	}

	public void setAdultFuelTax(Integer adultFuelTax) {
		this.adultFuelTax = adultFuelTax;
	}


	public Integer getTotlPrice() {
		
		return totlPrice;
	}


	public void setTotlPrice(Integer totlPrice) {
		this.totlPrice = totlPrice;
	}

	public String getFlightCompanyName() {
		return flightCompanyName;
	}

	public void setFlightCompanyName(String flightCompanyName) {
		this.flightCompanyName = flightCompanyName;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getOrgCityName() {
		return orgCityName;
	}

	public void setOrgCityName(String orgCityName) {
		this.orgCityName = orgCityName;
	}

	public String getArriTime() {
		return arriTime;
	}

	public void setArriTime(String arriTime) {
		this.arriTime = arriTime;
	}




	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getSeatMsg() {
		return seatMsg;
	}

	public void setSeatMsg(String seatMsg) {
		this.seatMsg = seatMsg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
