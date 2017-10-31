package com.manji.mlife.Vo;

import java.util.List;

public class TrainLineVo {
	
	private String from;
	private String to;
	private String date;
	private String trainNumber;
	private String trainTypeName;
	private String currentEndStationName;
	private String currentStartStationName;
	private String startTime;
	private String endTime;
	private String runTime;
	private List<TrainSeat> trainSeats;
	
	public List<TrainSeat> getTrainSeats() {
		return trainSeats;
	}
	public void setTrainSeats(List<TrainSeat> trainSeats) {
		this.trainSeats = trainSeats;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getTrainTypeName() {
		return trainTypeName;
	}
	public void setTrainTypeName(String trainTypeName) {
		this.trainTypeName = trainTypeName;
	}
	public String getCurrentEndStationName() {
		return currentEndStationName;
	}
	public void setCurrentEndStationName(String currentEndStationName) {
		this.currentEndStationName = currentEndStationName;
	}
	public String getCurrentStartStationName() {
		return currentStartStationName;
	}
	public void setCurrentStartStationName(String currentStartStationName) {
		this.currentStartStationName = currentStartStationName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	
	
	
	

}


